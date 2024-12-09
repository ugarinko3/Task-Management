package org.example.management.model.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

/**
 * Реквест для добавления комментария
 */
@Data
public class CommentRequest {

    @Schema(description = "ID Задачи")
    private UUID taskId;

    @Schema(description = "Комментарий")
    private String content;
}
