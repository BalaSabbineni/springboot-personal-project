package com.personalProject.personalProject.repository;

import com.personalProject.personalProject.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard,Long> {

    Optional<CreditCard> findByCardNumber(String cardNumber);

    void deleteByCardNumber(String cardNumber);

    List<CreditCard> findByUser_id(Long user_id);
}
