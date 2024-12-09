package org.example.management.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.example.management.model.enums.TaskPriority;

/**
 * Реквест для создания задачи
 */
@Setter
@Getter
@Schema(description = "Создание задачи")
public class TaskCreateRequest {

    @Schema(description = "Заголовок задачи", example = "Создать TaskManagement")
    private String title;

    @Schema(description = "Описание задачи", example = "Написать проект мечты")
    private String description;

    @Schema(description = "Приоритет задачи", example = "MEDIUM")
    private TaskPriority priority;

    public TaskCreateRequest() {
        this.priority = TaskPriority.LOW;
    }
}
