package com.kiabdu.mytasks.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Embeddable
public class Task {
    private String task_name;
    private String task_description;
    private LocalDate task_dueDate;

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
