package com.personalProject.personalProject.auth.repository;

import com.personalProject.personalProject.auth.entity.UserRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAuthRepository extends JpaRepository<UserRegister, Long> {

    Optional<UserRegister> findByUserName(String userName);

}
