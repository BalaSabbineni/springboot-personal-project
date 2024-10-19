package com.personalProject.personalProject.repository;

import com.personalProject.personalProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    Optional<User> findByAccountNumber(String accountNumber);

    Boolean existsByAccountNumber(String accountNumber);


}
