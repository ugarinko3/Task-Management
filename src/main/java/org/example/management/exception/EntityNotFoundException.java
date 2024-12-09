package org.example.management.exception;

/**
 * Исключение, для обозначения того, что сущность не найдена
 */
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
