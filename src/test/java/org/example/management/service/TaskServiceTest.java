package org.example.management.service;

import org.example.management.exception.EntityNotFoundException;
import org.example.management.model.entity.Task;
import org.example.management.model.enums.TaskPriority;
import org.example.management.model.request.TaskCreateRequest;
import org.example.management.model.request.TaskEditRequest;
import org.example.management.repository.TaskRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    private final TaskRepository taskRepository = mock(TaskRepository.class);
    private final CustomUserDetailsService customUserDetailsService = mock(CustomUserDetailsService.class);
    private final TaskService taskService = new TaskService(
            null, taskRepository, customUserDetailsService, null
    );

    @Test
    void editTask_NotFound() {
        UUID taskId = UUID.randomUUID();
        TaskEditRequest request = new TaskEditRequest();

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(
                EntityNotFoundException.class,
                () -> taskService.editTask(taskId, request)
        );

        assertEquals("Такого задания не существует", exception.getMessage());
        verify(taskRepository, times(1)).findById(taskId);
    }

    @Test
    void editPriority_Success() {
        UUID taskId = UUID.randomUUID();
        Task task = new Task();

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        taskService.editPriority(taskId, TaskPriority.HIGH);

        assertEquals(TaskPriority.HIGH, task.getPriority());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void deleteTask_Success() {
        UUID taskId = UUID.randomUUID();

        when(taskRepository.existsById(taskId)).thenReturn(true);

        taskService.deleteTask(taskId);

        verify(taskRepository, times(1)).deleteById(taskId);
    }
}
