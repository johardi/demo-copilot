package com.example.service;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.io.*;
import java.util.Optional;
import java.util.stream.Collectors;
import org.json.JSONObject;

@Singleton
public class OrcidApiService {
    private static final String ORCID_API_URL = "https://orcid.org/";
    private final OrcidFormatValidator formatValidator;

    @Inject
    public OrcidApiService(OrcidFormatValidator formatValidator) {
        this.formatValidator = formatValidator;
    }

    public boolean exists(String orcid) throws IOException {
        if (!formatValidator.isValid(orcid)) {
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

    public Optional<String> getFullName(String orcid) throws IOException {
        if (!formatValidator.isValid(orcid)) {
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
