package com.kiabdu.mytasks.service;

import com.kiabdu.mytasks.dto.UserDTO;
import com.kiabdu.mytasks.model.User;
import com.kiabdu.mytasks.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO userDTO) {
        User user = new User();

        user.setEmail(userDTO.getEmail());
        user.setHash(hashPassword(userDTO.getPassword()));

        return userRepository.save(user);
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
}
