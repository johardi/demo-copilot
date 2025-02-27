package com.example.service;

import javax.inject.Singleton;
import java.util.regex.Pattern;

@Singleton
public class OrcidFormatValidator {
    private static final Pattern ORCID_PATTERN = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{3}[\\dX]$");

    public boolean isValid(String orcid) {
        if (!ORCID_PATTERN.matcher(orcid).matches()) {
            return false;
        }
        
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
}
