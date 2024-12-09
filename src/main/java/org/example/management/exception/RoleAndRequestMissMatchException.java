package org.example.management.exception;

/**
 * Исключение, для обозначения несовпадения запроса и роли
 */
public class RoleAndRequestMissMatchException extends RuntimeException {
    public RoleAndRequestMissMatchException(String message) {
        super(message);
    }
}
