package org.example.managment.exception;

/**
 * Не заполненые поля
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
