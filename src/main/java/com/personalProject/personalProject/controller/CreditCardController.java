package com.personalProject.personalProject.controller;

import com.personalProject.personalProject.dto.AboutCard;
import com.personalProject.personalProject.entity.CreditCard;
import com.personalProject.personalProject.exceptions.UserNotFoundException;
import com.personalProject.personalProject.repository.CreditCardRepository;
import com.personalProject.personalProject.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/{userId}/creditCard")
public class CreditCardController {

    private final UserRepository userRepository;
    private final CreditCardRepository creditCardRepository;

    /*
    * Added to test bean config with static properties
    * Added config in yaml file and created bean with properties in CardConfig
     */
    private final AboutCard aboutCard;

    public CreditCardController(UserRepository userRepository, CreditCardRepository creditCardRepository, AboutCard aboutCard) {
        this.userRepository = userRepository;
        this.creditCardRepository = creditCardRepository;
        this.aboutCard = aboutCard;
    }

    @PostMapping("/open")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard open(@PathVariable Long userId, @RequestParam int limit) {
        var user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        CreditCard creditCard = CreditCard.builder()
                .user(user)
                .cardNumber(user.getAccountNumber())
                .cvv(Integer.parseInt(user.getAccountNumber().substring(2, 5)))
                .expirationDate(LocalDate.now().plusYears(5))
                .aboutCard(aboutCard)
                .creditLimit(limit).build();
        return creditCardRepository.save(creditCard);

    }

    @GetMapping()
    public List<CreditCard> getAllCreditCards() {
        return Optional.of(creditCardRepository.findAll()).orElseThrow(UserNotFoundException::new);
    }

    @PutMapping("/{cardNumber}/update")
    public CreditCard updateCreditCard(@RequestBody int limit, @PathVariable String cardNumber) {
        var updatedCreditCard = creditCardRepository.findByCardNumber(cardNumber).orElseThrow(() -> new RuntimeException("Card Not found"));
        updatedCreditCard.setCreditLimit(limit);
        return creditCardRepository.save(updatedCreditCard);
    }

    @Transactional
    @DeleteMapping("/{cardNumber}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCard(@PathVariable String cardNumber) {
        creditCardRepository.deleteByCardNumber(cardNumber);
    }

    @GetMapping("all")
    public List<CreditCard> getAllCreditCardsForUser(@PathVariable Long userId) {
        return creditCardRepository.findByUser_id(userId);
    }

}
