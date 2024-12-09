package org.example.management.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.management.model.enums.TaskPriority;
import org.example.management.model.enums.TaskStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class TaskDto {
    @Schema(description = "ID задачи")
    private UUID id;

    @Schema(description = "Заголовок")
    private String title;

    @Schema(description = "Описание")
    private String description;

    @Schema(description = "Статут задачи")
    private TaskStatus status;

    @Schema(description = "Приоритет задачи")
    private TaskPriority priority;

    @Schema(description = "Комментарии")
    private List<CommentDto> comments;

    @Schema(description = "Автор задачи")
    private String author;

    @Schema(description = "Исполнитель")
    private String executor;

    @Schema(description = "Дата создания")
    private LocalDate createdAt;
}
