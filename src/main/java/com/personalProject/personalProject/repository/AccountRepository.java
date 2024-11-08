package com.personalProject.personalProject.repository;

import com.personalProject.personalProject.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<List<Account>> findAccountsByUserId(long userId);
}
