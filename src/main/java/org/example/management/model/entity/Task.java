package org.example.management.model.entity;

import lombok.Getter;
import lombok.Setter;
import org.example.management.model.enums.TaskPriority;
import org.example.management.model.enums.TaskStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Сущность представляющая задачу в системе
 */
@Entity
@Getter
@Setter
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", nullable = false)
    private TaskPriority priority;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private User executor;

    @Column(name = "created_at")
    private LocalDate createdAt;

    public Task() {
        this.createdAt = LocalDate.now();
        this.status = TaskStatus.PENDING;
    }
}
