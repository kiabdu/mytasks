package com.kiabdu.mytasks.service;

import com.kiabdu.mytasks.config.HttpSession;
import com.kiabdu.mytasks.dto.TaskDTO;
import com.kiabdu.mytasks.dto.UserDTO;
import com.kiabdu.mytasks.model.Task;
import com.kiabdu.mytasks.model.User;
import com.kiabdu.mytasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        if(user == null){
            System.out.println("login failed");
            return null;
        }

        if(hashPassword(password).equals(user.getHash())){
            System.out.println("login successfull");
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
        task.setName(taskDTO.getName());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());

        User user = userRepository.findUserById(userId);
        user.getTasks().add(task);
    }

    public boolean authenticateSession(int userId){
        return session.getActivityStatus(userId);
    }

    public List<Task> getAllTasks(int userId) {
        return getUser(userId).getTasks();
    }

    public void logOut(int userId) {
        session.removeAttribute(userId);
    }
}
