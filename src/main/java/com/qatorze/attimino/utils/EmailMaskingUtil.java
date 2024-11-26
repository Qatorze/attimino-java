package com.qatorze.attimino.utils;

public class EmailMaskingUtil {
	
	public static String maskEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format.");
        }
        
        String[] parts = email.split("@");
        String username = parts[0];
        String domain = parts[1];
        
        // Mantient visible juste les 3 premiers caractères du nom du User
        int visibleChars = Math.min(3, username.length());
        String visiblePart = username.substring(0, visibleChars);
        
        // Cache le restant du nom du User avec des asterisques
        String maskedUsername = visiblePart + "*".repeat(username.length() - visibleChars);
        
        return maskedUsername + "@" + domain;
    }
}
