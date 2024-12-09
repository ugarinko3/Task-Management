package org.example.management.configuration.specification;

import lombok.experimental.UtilityClass;
import org.example.management.model.entity.Task;
import org.example.management.model.enums.TaskStatus;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class TaskSpecification {

    /**
     * Создает спецификацию для фильтрации автора по логину
     *
     * @param author Логин автора
     * @return {@link Task}
     */
    public static Specification<Task> hasAuthor(String author) {
        return (root, query, criteriaBuilder) ->
                author != null && !author.isEmpty() ?
                        criteriaBuilder.equal(root.get("author").get("email"), author) : null;
    }

    /**
     * Создает спецификацию для фильтрации исполнителя по логину
     *
     * @param executor Логин исполнителя
     * @return {@link Task}
     */
    public static Specification<Task> hasExecutor(String executor) {
        return (root, query, criteriaBuilder) ->
                executor != null && !executor.isEmpty() ?
                        criteriaBuilder.equal(root.get("executor").get("email"), executor) : null;
    }

    /**
     * Создает спецификацию для фильтрации исполнителя/автора по статусу задачи
     *
     * @param status Статус задачи
     * @return {@link Task}
     */
    public static Specification<Task> hasStatus(TaskStatus status) {
        return (root, query, criteriaBuilder) ->
                status != null ? criteriaBuilder.equal(root.get("status"), status) : null;
    }
}
