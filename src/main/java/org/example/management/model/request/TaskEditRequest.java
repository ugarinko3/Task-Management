package org.example.management.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.example.management.model.enums.TaskPriority;

/**
 * Реквест для изменения комментария
 */
@Setter
@Getter
@Schema(description = "Изменение задачи")
public class TaskEditRequest {

    @Schema(description = "Заголовок задачи", example = "Создать TaskManagement")
    private String title;

    @Schema(description = "Описание задачи", example = "Почесать ногу")
    private String description;

    @Schema(description = "Приоритет задачи", example = "MEDIUM")
    private TaskPriority priority;
}
