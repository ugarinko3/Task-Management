package org.example.management.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class CommentDto {

    @Schema(description = "ID комментария")
    private UUID id;

    @Schema(description = "Email пользователя")
    private String user;

    @Schema(description = "Текст комментария")
    private String content;

    @Schema(description = "Дата создания")
    private LocalDateTime createdAt;
}
