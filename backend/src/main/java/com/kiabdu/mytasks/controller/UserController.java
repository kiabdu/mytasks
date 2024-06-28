package com.kiabdu.mytasks.controller;

import com.kiabdu.mytasks.dto.TaskDTO;
import com.kiabdu.mytasks.dto.UserDTO;
import com.kiabdu.mytasks.model.User;
import com.kiabdu.mytasks.service.UserService;
import com.kiabdu.mytasks.config.HttpSession;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<User> logIn(@RequestBody UserDTO userDTO, HttpSession session){
        User user;

        try {
            user = userService.authenticateUser(userDTO);
            session.setAttribute("userId", user.getId());
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @GetMapping("/checkSession")
    public ResponseEntity<User> checkSession(HttpSession session){
         Long userId = (Long)session.getAttribute("userId");
         User user = userService.getUser(userId);

         if(userId != null){
             return ResponseEntity.ok(user);
         } else {
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
         }
    }

    @PostMapping("/addTask")
    public ResponseEntity<?> addTask(@RequestBody TaskDTO taskDTO, HttpSession session){
        Long userId = (Long)session.getAttribute("userId");
        User user = userService.getUser(userId);

        if(userId != null){
            userService.addTask(user, taskDTO);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
