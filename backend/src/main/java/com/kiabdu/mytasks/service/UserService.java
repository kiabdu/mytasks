package com.kiabdu.mytasks.service;

import com.kiabdu.mytasks.config.HttpSession;
import com.kiabdu.mytasks.dto.TaskDTO;
import com.kiabdu.mytasks.dto.UserDTO;
import com.kiabdu.mytasks.model.Task;
import com.kiabdu.mytasks.model.User;
import com.kiabdu.mytasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private HttpSession session;

    public UserService(){
        session = new HttpSession();
    }

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
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();

        if(userRepository.findUserByEmail(email) == null) {
            User user = new User();

            user.setEmail(email);
            user.setHash(hashPassword(password));

            return userRepository.save(user);
        }

        return null;
    }

    public User authenticateUser(UserDTO userDTO) {
        String email = userDTO.getEmail();
        String password = userDTO.getPassword();
        User user = userRepository.findUserByEmail(email);

        if(hashPassword(password).equals(user.getHash())){
            session.setActivityStatus(user.getId(), true);
            return user;
        }

        return null;
    }

    public User getUser(int userId) {
        return userRepository.findUserById(userId);
    }

    public void addTask(int userId, TaskDTO taskDTO){
        Task task = new Task();

        System.out.println("isCompleted: " + taskDTO.isCompleted());

        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setCompleted(taskDTO.isCompleted());

        User user = userRepository.findUserById(userId);
        user.getTasks().add(task);
        userRepository.save(user);
    }

    public boolean authenticateSession(int userId){
        return session.getActivityStatus(userId);
    }

    public List<Task> getAllTasks(int userId) {
        User user = userRepository.findUserById(userId);
        List<Task> tasks = new ArrayList<>();

        if(user != null){
            tasks = user.getTasks();
        }

        return tasks;
    }

    public void logOut(int userId) {
        session.removeAttribute(userId);
    }

    public void updateTask(int userId, TaskDTO taskDTO) {
        User user = userRepository.findUserById(userId);
        List<Task> tasks = user.getTasks();

        for(Task task: tasks){
            if(task.getName().equals(taskDTO.getName())){
                task.setDescription(taskDTO.getDescription());
                task.setDueDate(taskDTO.getDueDate());
                task.setCompleted(taskDTO.isCompleted());
                break;
            }
        }

        user.setTasks(tasks);
        userRepository.save(user);
    }
}
