package org.example.management.exception;

/**
 * Исключение, для обозначения ошибки создания токена
 */
public class JwtException extends RuntimeException {
    public JwtException(String message) {
        super(message);
    }
}
