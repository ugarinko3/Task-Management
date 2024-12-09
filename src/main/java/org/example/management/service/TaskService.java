package org.example.management.service;

import lombok.RequiredArgsConstructor;
import org.example.management.configuration.specification.TaskSpecification;
import org.example.management.exception.EntityNotFoundException;
import org.example.management.exception.RoleAndRequestMissMatchException;
import org.example.management.model.dto.TaskDto;
import org.example.management.model.entity.Task;
import org.example.management.model.entity.User;
import org.example.management.model.enums.Role;
import org.example.management.model.enums.TaskPriority;
import org.example.management.model.enums.TaskStatus;
import org.example.management.model.request.*;
import org.example.management.repository.TaskRepository;
import org.example.management.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final ModelMapper modelMapper;
    private final TaskRepository taskRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final UserRepository userRepository;

    /**
     * Создание задачи
     *
     * @param taskCreateRequest
     * @return
     */
    @Transactional
    public UUID create(TaskCreateRequest taskCreateRequest) {
        Task task = modelMapper.map(taskCreateRequest, Task.class);
        task.setAuthor(customUserDetailsService.getCurrentUser());
        taskRepository.save(task);
        return task.getId();
    }

    /**
     * Изменение задачи
     *
     * @param taskId
     * @param taskEditRequest
     */
    @Transactional
    public void editTask(UUID taskId, TaskEditRequest taskEditRequest) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Такого задания не существует"));
        modelMapper.map(taskEditRequest, task);
        taskRepository.save(task);
    }

    /**
     * Удаление задачи
     *
     * @param taskId
     */
    @Transactional
    public void deleteTask(UUID taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new EntityNotFoundException("Такого задания не существует");
        }
        taskRepository.deleteById(taskId);
    }

    /**
     * Изменение приоритета
     *
     * @param id
     * @param priority
     */
    @Transactional
    public void editPriority(UUID id, TaskPriority priority) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Такого задания не существует"));
        task.setPriority(priority);
        taskRepository.save(task);
    }

    /**
     * Изменение исполняемого пользователя
     *
     * @param taskId
     * @param executorId
     */
    @Transactional
    public void changeExecutor(UUID taskId, UUID executorId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new EntityNotFoundException("Такого задания не существует"));
        task.setExecutor(userRepository.findById(executorId)
                .orElseThrow(() -> new EntityNotFoundException("Такого пользователя не существует")));
        taskRepository.save(task);
    }

    /**
     * Получить все задачи
     *
     * @param getFilterTask запрос на фильтрацию задач
     * @return список задач
     */
    @Transactional
    public List<TaskDto> findTasks(GetFilterTask getFilterTask, Integer page, Integer size) {
        if (getFilterTask == null) getFilterTask = new GetFilterTask();
        PageRequest pageable = PageRequest.of(page, size);
        Specification<Task> specification = Specification
                .where(TaskSpecification.hasAuthor(getFilterTask.getLoginAuthor()))
                .and(TaskSpecification.hasExecutor(getFilterTask.getLoginExecutor()))
                .and(TaskSpecification.hasStatus(getFilterTask.getTaskStatus()));

        return modelMapper.map(taskRepository.findAll(specification, pageable).getContent(),
                new TypeToken<List<TaskDto>>() {
                }.getType());
    }

    /**
     * Изменение статуса задачи
     *
     * @param taskId
     * @param taskStatus
     */
    @Transactional
    public void changeStatus(UUID taskId, TaskStatus taskStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new EntityNotFoundException("Такого задания не существует"));
        User user = customUserDetailsService.getCurrentUser();

        if (!user.getRole().equals(Role.ROLE_ADMIN) && !user.equals(task.getExecutor())) {
            throw new RoleAndRequestMissMatchException("Не хватает прав для изменения статуса");
        }

        task.setStatus(taskStatus);
        taskRepository.save(task);
    }
}
