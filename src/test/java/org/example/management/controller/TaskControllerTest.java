package org.example.management.controller;

import org.example.management.model.dto.TaskDto;
import org.example.management.model.request.TaskCreateRequest;
import org.example.management.model.request.TaskEditRequest;
import org.example.management.service.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TaskControllerTest {

    private final TaskService taskService = mock(TaskService.class);
    private final TaskController taskController = new TaskController(taskService);

    @Test
    void createTask() {
        TaskCreateRequest request = new TaskCreateRequest();
        UUID expectedId = UUID.randomUUID();

        when(taskService.create(request)).thenReturn(expectedId);

        ResponseEntity<UUID> response = taskController.create(request);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedId, response.getBody());
        verify(taskService, times(1)).create(request);
    }

    @Test
    void editTask() {
        UUID taskId = UUID.randomUUID();
        TaskEditRequest request = new TaskEditRequest();

        ResponseEntity<Void> response = taskController.editTask(taskId, request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(taskService, times(1)).editTask(taskId, request);
    }

    @Test
    void deleteTask() {
        UUID taskId = UUID.randomUUID();

        ResponseEntity<Void> response = taskController.deleteTask(taskId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(taskService, times(1)).deleteTask(taskId);
    }

    @Test
    void getAllTasks() {
        TaskDto taskDto = new TaskDto();
        List<TaskDto> tasks = List.of(taskDto);

        when(taskService.findTasks(null, 0, 10)).thenReturn(tasks);

        ResponseEntity<List<TaskDto>> response = taskController.getAllTasks(0, 10, null);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(tasks, response.getBody());
        verify(taskService, times(1)).findTasks(null, 0, 10);
    }
}
