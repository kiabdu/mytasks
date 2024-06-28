package com.kiabdu.mytasks.controller;

import com.kiabdu.mytasks.dto.TaskDTO;
import com.kiabdu.mytasks.dto.UserDTO;
import com.kiabdu.mytasks.model.User;
import com.kiabdu.mytasks.repository.UserRepository;
import com.kiabdu.mytasks.service.UserService;
import com.kiabdu.mytasks.config.HttpSession;
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
    @Autowired
    private UserRepository userRepository;

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

    //nur für kontrollzwecke weil h2-console probleme macht, vor dem pushen löschen
    @GetMapping("/getAll")
    public List<User> getAllUsers(){
        return userRepository.findAll();
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
    public ResponseEntity<?> addTask(@RequestBody TaskDTO taskDTO, int userId){
        if(userService.authenticateSession(userId)){
            userService.addTask(userId, taskDTO);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
