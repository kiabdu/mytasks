package com.kiabdu.mytasks.controller;

import com.kiabdu.mytasks.dto.TaskDTO;
import com.kiabdu.mytasks.model.Task;
import com.kiabdu.mytasks.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/api")
@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getAllTasks(){
        List<Task> allTasks;

        try{
            allTasks = taskService.getAllTasks();
            System.out.println("jilz-nazi-pussy-lan");
            return ResponseEntity.ok(allTasks);
        } catch (Exception e){
            System.out.println("man was ein nuhutzius");
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO taskDTO){
        Task newTask;
        try {
            newTask = taskService.addTask(taskDTO);
            return ResponseEntity.ok(newTask);
        } catch (Exception e){
            return ResponseEntity.status(500).build();
        }
    }
}
