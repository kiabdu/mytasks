package com.kiabdu.mytasks.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id", nullable = false, unique = true)
    private Long task_id;

    @Column(name = "task_name", nullable = false, length = 100)
    private String task_name;

    @Column(name = "task_description", length = 500)
    private String task_description;

    @Column(name = "task_dueDate")
    private LocalDate task_dueDate;

    public void setTask_id(Long taskId) {
        this.task_id = taskId;
    }

    public Long getTask_id() {
        return task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public LocalDate getTask_dueDate() {
        return task_dueDate;
    }

    public void setTask_dueDate(LocalDate task_dueDate) {
        this.task_dueDate = task_dueDate;
    }
}
