package com.insurancepolicy.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.insurancepolicy.dto.InsurancePolicyDTO;
import com.insurancepolicy.entity.InsurancePolicy;
import com.insurancepolicy.exception.NotFoundException;
import com.insurancepolicy.repo.InsurancePolicyRepository;
import com.insurancepolicy.service.InsurancePolicyService;

@Service
public class InsurancePolicyServiceImpl implements InsurancePolicyService {

	@Autowired
	private InsurancePolicyRepository insurancePolicyRepository;

	@Override
	public List<InsurancePolicyDTO> getAllPolicies() {
		List<InsurancePolicy> policies = insurancePolicyRepository.findAll();
		return policies.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public InsurancePolicyDTO getInsurancePolicyById(Long id) {
		Optional<InsurancePolicy> optionalPolicy = insurancePolicyRepository.findById(id);
		if (optionalPolicy.isPresent()) {
			return convertToDTO(optionalPolicy.get());
		} else {
			throw new NotFoundException("Insurance Policy not found");
		}
	}

	@Override
	@Transactional
	public InsurancePolicyDTO createInsurancePolicy(InsurancePolicyDTO insurancePolicyDTO) {
		InsurancePolicy policy = convertToEntity(insurancePolicyDTO);
		policy = insurancePolicyRepository.save(policy);
		return convertToDTO(policy);
	}

	@Override
	@Transactional
	public InsurancePolicyDTO updateInsurancePolicy(Long id, InsurancePolicyDTO insurancePolicyDTO) {
		Optional<InsurancePolicy> optionalPolicy = insurancePolicyRepository.findById(id);
		if (optionalPolicy.isPresent()) {
			InsurancePolicy policy = optionalPolicy.get();
			policy = convertToEntity(insurancePolicyDTO);
			return convertToDTO(policy);
		} else {
			throw new NotFoundException("Insurance Policy not found");
		}
	}

	@Override
	@Transactional
	public boolean deleteInsurancePolicy(Long id) {
		Optional<InsurancePolicy> optionalPolicy = insurancePolicyRepository.findById(id);
		if (optionalPolicy.isPresent()) {
			insurancePolicyRepository.deleteById(id);
			return true;
		} else {
			throw new NotFoundException("Insurance Policy not found");
		}
	}

	private InsurancePolicyDTO convertToDTO(InsurancePolicy policy) {
		InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
		policyDTO.setPolicyId(policy.getPolicyId());
		policyDTO.setPolicyNumber(policy.getPolicyNumber());
		policyDTO.setPolicyType(policy.getPolicyType());
		policyDTO.setPremiumAmount(policy.getPremiumAmount());
		policyDTO.setStartDate(policy.getStartDate());
		policyDTO.setEndDate(policy.getEndDate());
		policyDTO.setActive(policy.isActive());
		policyDTO.setCustomerId(policy.getCustomerId());
		return policyDTO;
	}

	private InsurancePolicy convertToEntity(InsurancePolicyDTO policyDTO) {
		InsurancePolicy policy = new InsurancePolicy();
		policy.setPolicyId(policyDTO.getPolicyId());
		policy.setPolicyNumber(policyDTO.getPolicyNumber());
		policy.setPolicyType(policyDTO.getPolicyType());
		policy.setPremiumAmount(policyDTO.getPremiumAmount());
		policy.setStartDate(policyDTO.getStartDate());
		policy.setEndDate(policyDTO.getEndDate());
		policy.setActive(policyDTO.isActive());
		policy.setCustomerId(policyDTO.getCustomerId());
		return policy;
	}
}
