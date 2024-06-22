package com.kiabdu.mytasks.service;

import com.kiabdu.mytasks.dto.TaskDTO;
import com.kiabdu.mytasks.model.Task;
import com.kiabdu.mytasks.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task addTask(TaskDTO taskDTO){
        Task task = new Task();

        task.setTask_name(taskDTO.getName());
        task.setTask_description(taskDTO.getDescription());
        task.setTask_dueDate(taskDTO.getDueDate());

        return taskRepository.save(task);
    }
}
