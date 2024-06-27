package com.kiabdu.mytasks.repository;

import com.kiabdu.mytasks.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmailContaining(String email);
    User getUserByEmail(String email);

    default User getUserById(Long userId) {
        return null;
    }
}
