package org.example.management.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.example.management.model.enums.TaskStatus;

/**
 * Фильтры при получении задачи
 */
@Data
public class GetFilterTask {

    @Schema(description = "Логин автора задачи")
    private String loginAuthor;

    @Schema(description = "Логин исполнителя задачи")
    private String loginExecutor;

    @Schema(description = "Статус задачи")
    private TaskStatus taskStatus;
}
