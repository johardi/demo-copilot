package com.example.service;

import javax.inject.Singleton;
import java.util.regex.Pattern;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.stream.Collectors;
import org.json.JSONObject;

@Singleton
public class OrcidValidator {
    private static final Pattern ORCID_PATTERN = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{3}[\\dX]$");
    private static final String ORCID_API_URL = "https://orcid.org/";

    public boolean isValid(String orcid) {
        if (!ORCID_PATTERN.matcher(orcid).matches()) {
            return false;
        }
        
        // Check checksum
        String digitsOnly = orcid.replace("-", "").toLowerCase();
        int total = 0;
        for (int i = 0; i < 15; i++) {
            int digit = Character.getNumericValue(digitsOnly.charAt(i));
            total = (total + digit) * 2;
        }
        
        int remainder = total % 11;
        int result = (12 - remainder) % 11;
        char checksum = result == 10 ? 'x' : Character.forDigit(result, 10);
        
        return checksum == digitsOnly.charAt(15);
    }

    public boolean exists(String orcid) throws IOException {
        if (!isValid(orcid)) {
            return false;
        }

        URL url = URI.create(ORCID_API_URL + orcid).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/vnd.orcid+json");

        try {
            return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
        } finally {
            conn.disconnect();
        }
    }

    public boolean validateAndExists(String orcid) {
        try {
            return exists(orcid);
        } catch (IOException e) {
            return false;
        }
    }

    public Optional<String> getFullName(String orcid) throws IOException {
        if (!isValid(orcid)) {
            return Optional.empty();
        }

        URL url = URI.create(ORCID_API_URL + orcid).toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/vnd.orcid+json");

        try {
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return Optional.empty();
            }

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String response = reader.lines().collect(Collectors.joining());
                JSONObject json = new JSONObject(response);
                JSONObject person = json.getJSONObject("person");
                JSONObject name = person.getJSONObject("name");
                String givenNames = name.getJSONObject("given-names").getString("value");
                String familyName = name.getJSONObject("family-name").getString("value");
                return Optional.of(givenNames + " " + familyName);
            }
        } finally {
            conn.disconnect();
        }
    }
}
