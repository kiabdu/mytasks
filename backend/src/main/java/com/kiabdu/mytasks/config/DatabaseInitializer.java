package com.kiabdu.mytasks.config;

import com.kiabdu.mytasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializer {
    @Autowired
    private TaskRepository taskRepository;

}
