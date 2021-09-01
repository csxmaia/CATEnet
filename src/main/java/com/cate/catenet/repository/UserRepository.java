package com.cate.catenet.repository;

import com.cate.catenet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<List<User>> findByStatus(String status);

    Optional<User> findByEmail(String email);
}
