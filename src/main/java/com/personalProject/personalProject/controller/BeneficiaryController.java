package com.personalProject.personalProject.controller;

import com.personalProject.personalProject.entity.Beneficiary;
import com.personalProject.personalProject.exceptions.UserNotFoundException;
import com.personalProject.personalProject.repository.BeneficiaryRepository;
import com.personalProject.personalProject.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1//users/{userId}/beneficiaries")
@Slf4j
public class BeneficiaryController {

    private final static Logger logger = LoggerFactory.getLogger(BeneficiaryController.class);

    private final UserRepository userRepository;
    private final BeneficiaryRepository beneficiaryRepository;

    public BeneficiaryController(UserRepository userRepository, BeneficiaryRepository beneficiaryRepository) {
        this.userRepository = userRepository;
        this.beneficiaryRepository = beneficiaryRepository;
    }

    @PostMapping("create")
    public ResponseEntity<Beneficiary> create(@RequestBody Beneficiary request, @PathVariable Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with ID " + userId + " not found"));
        var beneficiaryRe = Beneficiary.builder()
                .beneficiaryName(request.getBeneficiaryName())
                .accountNumber(user.getAccountNumber())
                .user(user).build();
        var beneficiary = beneficiaryRepository.save(beneficiaryRe);

        return ResponseEntity.status(HttpStatus.CREATED).body(beneficiary);
    }

    @PutMapping("/{beneficiaryId}")
    public Beneficiary updateBeneficiary(@RequestBody Beneficiary beneficiary, @PathVariable Long beneficiaryId) {
        Beneficiary bene = beneficiaryRepository.findById(beneficiaryId).orElseThrow(() -> new RuntimeException("Beneficiary Not Found"));
        bene.setBeneficiaryName(beneficiary.getBeneficiaryName());

        return beneficiaryRepository.save(bene);
    }

    @Transactional
    @DeleteMapping("/{beneficiaryId}")
    public ResponseEntity<Void> deleteBeneficiary(@PathVariable Long beneficiaryId, @PathVariable Long userId) {
        beneficiaryRepository.deleteByBeneficiaryIdAndUserId(beneficiaryId, userId);
        return ResponseEntity.noContent().build();
    }

    @Transactional
    @DeleteMapping
    public ResponseEntity<Void> deleteBeneficiaryByUserId(@PathVariable Long userId) {
        beneficiaryRepository.deleteByUser_Id(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public List<Beneficiary> getBeneficiaries(@PathVariable Long userId) {
        return beneficiaryRepository.findByUser_Id(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
    }

    @GetMapping("/{beneficiaryName}")
    public Beneficiary getBeneficiaryByName(@PathVariable Long userId, @PathVariable String beneficiaryName) {
        return beneficiaryRepository.findByBeneficiaryNameAndUser_Id(beneficiaryName, userId)
                .orElseThrow(() -> new RuntimeException("Beneficiary Not Found"));
    }
}
