package com.kiabdu.mytasks.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Embeddable
public class Task {
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "dueDate")
    private LocalDate dueDate;

    @Column(name = "completed")
    private boolean completed;

    public String getName() {
        return name;
    }

    public void setName(String task_name) {
        this.name = task_name;
    }

    public void setDescription(String task_description) {
        this.description = task_description;
    }

    public void setDueDate(LocalDate task_dueDate) {
        this.dueDate = task_dueDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
