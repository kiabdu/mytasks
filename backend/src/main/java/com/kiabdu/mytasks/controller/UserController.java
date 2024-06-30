package com.kiabdu.mytasks.controller;

import com.kiabdu.mytasks.dto.TaskDTO;
import com.kiabdu.mytasks.dto.UserDTO;
import com.kiabdu.mytasks.model.Task;
import com.kiabdu.mytasks.model.User;
import com.kiabdu.mytasks.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RequestMapping("/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<User> signUp(@RequestBody UserDTO userDTO){
        User newUser;

        try {
            newUser = userService.createUser(userDTO);
            return ResponseEntity.ok(newUser);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<User> logIn(@RequestBody UserDTO userDTO){
        User user;

        try {
            user = userService.authenticateUser(userDTO);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/checkSession")
    public ResponseEntity<User> checkSession(int userId){
        if(userService.authenticateSession(userId)){
            return ResponseEntity.ok(userService.getUser(userId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody TaskDTO taskDTO, @RequestParam int userId){
        if(userService.authenticateSession(userId)){
            userService.addTask(userId, taskDTO);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping("/updateTask")
    public ResponseEntity<?> updateTask(@RequestBody TaskDTO taskDTO, @RequestParam int userId){
        if(userService.authenticateSession(userId)){
            userService.updateTask(userId, taskDTO);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/getAllTasks")
    public ResponseEntity<List<Task>> getAllTasks(@RequestParam int userId){
        if(userService.authenticateSession(userId)){
            List<Task> tasks = userService.getAllTasks(userId);
            return ResponseEntity.ok(tasks);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logOut(@RequestParam int userId){
        userService.logOut(userId);
        return ResponseEntity.ok("logout successfull");
    }
}
