package com.insurancepolicy.service;

import java.util.List;

import com.insurancepolicy.dto.InsurancePolicyDTO;

public interface InsurancePolicyService {
	List<InsurancePolicyDTO> getAllPolicies();

	InsurancePolicyDTO getInsurancePolicyById(Long id);

	InsurancePolicyDTO createInsurancePolicy(InsurancePolicyDTO insurancePolicyDTO);

	InsurancePolicyDTO updateInsurancePolicy(Long id, InsurancePolicyDTO insurancePolicyDTO);

	boolean deleteInsurancePolicy(Long id);
}