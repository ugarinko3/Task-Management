package org.example.managment.exception;

public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}
