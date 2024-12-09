package org.example.management.configuration.mapper;

import lombok.RequiredArgsConstructor;
import org.example.management.model.dto.TaskDto;
import org.example.management.model.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Добавляет кастомный маппинг сущности {@link Task} в {@link TaskDto}.
 */
@RequiredArgsConstructor
@Configuration
public class TaskToTaskDtoMapper {

    private final ModelMapper modalMapper;

    @PostConstruct
    public void setupMapper() {
        modalMapper.createTypeMap(Task.class, TaskDto.class)
                .addMappings(m -> {
                    m.map(task -> task.getAuthor().getEmail(), TaskDto::setAuthor);
                    m.map(task -> task.getExecutor().getEmail(), TaskDto::setExecutor);
                });
    }
}
