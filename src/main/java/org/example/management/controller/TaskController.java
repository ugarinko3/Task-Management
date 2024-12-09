package org.example.management.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.example.management.model.dto.TaskDto;
import org.example.management.model.enums.TaskPriority;
import org.example.management.model.enums.TaskStatus;
import org.example.management.model.request.*;
import org.example.management.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @PostMapping
    @Operation(summary = "Создание задачи")
    public ResponseEntity<UUID> create(@RequestBody TaskCreateRequest taskCreateRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.create(taskCreateRequest));
    }

    @PutMapping("/{taskId}")
    @Operation(summary = "Изменение задачи")
    public ResponseEntity<Void> editTask(@PathVariable UUID taskId, @RequestBody TaskEditRequest taskEditRequest) {
        taskService.editTask(taskId, taskEditRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{taskId}")
    @Operation(summary = "Удаление задачи")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID taskId) {
        taskService.deleteTask(taskId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{taskId}/priority")
    @Operation(summary = "Изменение приоритета задачи")
    public ResponseEntity<String> editPriority(@PathVariable UUID taskId, @RequestParam TaskPriority priority) {
        taskService.editPriority(taskId, priority);
        return ResponseEntity.ok().body("Приоритет задачи успешно изменен");
    }

    @PutMapping("/{taskId}/status")
    @Operation(summary = "Изменение статуса задачи")
    public ResponseEntity<Void> changeStatusTask(@PathVariable UUID taskId, @RequestParam TaskStatus status) {
        taskService.changeStatus(taskId, status);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{taskId}/executor/{executorId}")
    @Operation(summary = "Назначение исполняемого задачи")
    public ResponseEntity<String> changeExecutor(@PathVariable UUID taskId, @PathVariable UUID executorId) {
        taskService.changeExecutor(taskId, executorId);
        return ResponseEntity.ok().body("Исполняемый назначен");
    }

    @PostMapping("/find")
    @Operation(summary = "Просмотреть все задачи")
    public ResponseEntity<List<TaskDto>> getAllTasks(@RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size,
                                                     @RequestBody(required = false) GetFilterTask getFilterTask) {
        return ResponseEntity.ok().body(taskService.findTasks(getFilterTask, page, size));
    }
}
