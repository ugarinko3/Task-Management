package org.example.management.configuration.mapper;

import lombok.RequiredArgsConstructor;
import org.example.management.model.dto.CommentDto;
import org.example.management.model.entity.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Добавляет кастомный маппинг сущности {@link Comment} в {@link CommentDto}
 */
@RequiredArgsConstructor
@Configuration
public class CommentToCommentDtoMapper {

    private final ModelMapper modalMapper;

    @PostConstruct
    public void setupMapper() {
        modalMapper.createTypeMap(Comment.class, CommentDto.class)
            .addMappings(m -> m.map(comment -> comment.getUser().getEmail(), CommentDto::setUser));
    }
}
