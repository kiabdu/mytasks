package com.kiabdu.mytasks.service;

import com.kiabdu.mytasks.dto.TaskDTO;
import com.kiabdu.mytasks.dto.UserDTO;
import com.kiabdu.mytasks.model.Task;
import com.kiabdu.mytasks.model.User;
import com.kiabdu.mytasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private String generateSalt(){
        byte[] saltBytes = "mytasks".getBytes();
        String salt = "";

        //ich möchte nur die Zahlen aus dem Array ziehen
        for(byte b: saltBytes){
            salt += (b);
        }

        return salt;
    }

    private String hashPassword(String password){
        byte[] passwordBytes = password.getBytes();
        String saltedHash = "";

        for(byte b: passwordBytes){
            saltedHash += (b);
        }

        return generateSalt() + saltedHash;
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();

        user.setEmail(userDTO.getEmail());
        user.setHash(hashPassword(userDTO.getPassword()));

        return userRepository.save(user);
    }

    public User authenticateUser(UserDTO userDTO) {
        String email = userDTO.getEmail();

        if(userRepository.existsByEmailContaining(email)){
            User user = userRepository.getUserByEmail(email);
            if(hashPassword(userDTO.getPassword()).equals(user.getHash())){
                System.out.println("login successfull");
                return user;
            }
        }

        return null;
    }

    public User getUser(Long userId) {
        return userRepository.getUserById(userId);
    }

    public void addTask(User user, TaskDTO taskDTO){
        Task task = new Task();
        task.setTask_name(taskDTO.getName());
        task.setTask_description(taskDTO.getDescription());
        task.setTask_dueDate(task.getTask_dueDate());

        user.getTasks().add(task);
    }
}
