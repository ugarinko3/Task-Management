package org.example.management.service;

import lombok.RequiredArgsConstructor;
import org.example.management.exception.EntityNotFoundException;
import org.example.management.exception.RoleAndRequestMissMatchException;
import org.example.management.model.entity.Comment;
import org.example.management.model.entity.Task;
import org.example.management.model.entity.User;
import org.example.management.model.enums.Role;
import org.example.management.model.request.CommentRequest;
import org.example.management.repository.CommentRepository;
import org.example.management.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final TaskRepository taskRepository;
    private final CommentRepository commentRepository;
    private final CustomUserDetailsService customUserDetailsService;

    /**
     * Добавление комментария к задачи
     *
     * @param commentRequest сущность коментария
     */
    @Transactional
    public void addComment(CommentRequest commentRequest) {
        Task task = taskRepository.findById(commentRequest.getTaskId())
                .orElseThrow(() -> new EntityNotFoundException("Такой задачи не существует"));

        User user = customUserDetailsService.getCurrentUser();

        if (!user.getRole().equals(Role.ROLE_ADMIN) && !user.equals(task.getExecutor())) {
            throw new RoleAndRequestMissMatchException("Не хватает прав для изменения статуса");
        }

        Comment comment = Comment.builder()
                .task(task)
                .user(customUserDetailsService.getCurrentUser())
                .content(commentRequest.getContent())
                .build();

        commentRepository.save(comment);
    }
}
