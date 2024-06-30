package com.kiabdu.mytasks.dto;

import java.time.LocalDate;

public class TaskDTO {
    private String name;
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }
}
