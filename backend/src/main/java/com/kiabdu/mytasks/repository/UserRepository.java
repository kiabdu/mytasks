package com.kiabdu.mytasks.repository;

import com.kiabdu.mytasks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserById(int userId);
}
