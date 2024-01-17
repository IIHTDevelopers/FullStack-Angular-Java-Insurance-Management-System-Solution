package com.insurancepolicy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurancepolicy.dto.InsurancePolicyDTO;
import com.insurancepolicy.service.InsurancePolicyService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/api/policies")
public class InsurancePolicyController {

	@Autowired
	private InsurancePolicyService insurancePolicyService;

	@GetMapping
	public ResponseEntity<List<InsurancePolicyDTO>> getAllPolicies() {
		List<InsurancePolicyDTO> policies = insurancePolicyService.getAllPolicies();
		return new ResponseEntity<>(policies, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<InsurancePolicyDTO> getPolicyById(@PathVariable Long id) {
		InsurancePolicyDTO policy = insurancePolicyService.getInsurancePolicyById(id);
		return new ResponseEntity<>(policy, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<InsurancePolicyDTO> createPolicy(@Valid @RequestBody InsurancePolicyDTO insurancePolicyDTO) {
		InsurancePolicyDTO createdPolicy = insurancePolicyService.createInsurancePolicy(insurancePolicyDTO);
		return new ResponseEntity<>(createdPolicy, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<InsurancePolicyDTO> updatePolicy(@PathVariable Long id,
			@Valid @RequestBody InsurancePolicyDTO insurancePolicyDTO) {
		InsurancePolicyDTO updatedPolicy = insurancePolicyService.updateInsurancePolicy(id, insurancePolicyDTO);
		return new ResponseEntity<>(updatedPolicy, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePolicy(@PathVariable Long id) {
		insurancePolicyService.deleteInsurancePolicy(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
