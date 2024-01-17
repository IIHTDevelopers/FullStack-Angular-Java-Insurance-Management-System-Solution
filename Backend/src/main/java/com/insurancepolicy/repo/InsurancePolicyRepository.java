package com.insurancepolicy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.insurancepolicy.entity.InsurancePolicy;

public interface InsurancePolicyRepository extends JpaRepository<InsurancePolicy, Long> {

}
