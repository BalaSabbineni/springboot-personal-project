package com.personalProject.personalProject.repository;

import com.personalProject.personalProject.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    Optional<List<Beneficiary>> findByUser_Id(Long user_Id);

    /**
     *
     * @param beneficiaryId is the column name in Beneficiary Entity
     * @param user_Id is the column name in Beneficiary Entity with @JoinColumn(name = "user_id")
     *                Names must match to Spring Data JPA automatically generate queries JPA
     */
    void deleteByBeneficiaryIdAndUser_Id(Long beneficiaryId, Long user_Id);
    // OR both methods are same, deletes
    @Modifying
    @Query("DELETE FROM Beneficiary b WHERE b.beneficiaryId = :beneficiaryId AND b.user.id = :user_Id")
    void deleteByBeneficiaryIdAndUserId(@Param("beneficiaryId") Long beneficiaryId, @Param("user_Id") Long userId);

    void deleteByUser_Id(Long user_id);

    Optional<Beneficiary> findByBeneficiaryNameAndUser_Id(String beneficiaryName, Long user_Id);

}
