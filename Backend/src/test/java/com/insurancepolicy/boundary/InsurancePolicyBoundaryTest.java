package com.insurancepolicy.boundary;

import static com.insurancepolicy.utils.TestUtils.boundaryTestFile;
import static com.insurancepolicy.utils.TestUtils.currentTest;
import static com.insurancepolicy.utils.TestUtils.testReport;
import static com.insurancepolicy.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.insurancepolicy.dto.InsurancePolicyDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class InsurancePolicyBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testPolicyNumberNotNull() throws IOException {
		InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
		policyDTO.setPolicyNumber(null);
		Set<ConstraintViolation<InsurancePolicyDTO>> violations = validator.validate(policyDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPolicyTypeNotNull() throws IOException {
		InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
		policyDTO.setPolicyType(null);
		Set<ConstraintViolation<InsurancePolicyDTO>> violations = validator.validate(policyDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPremiumAmountNotNull() throws IOException {
		InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
		policyDTO.setPremiumAmount(null);
		Set<ConstraintViolation<InsurancePolicyDTO>> violations = validator.validate(policyDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testStartDateNotNull() throws IOException {
		InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
		policyDTO.setStartDate(null);
		Set<ConstraintViolation<InsurancePolicyDTO>> violations = validator.validate(policyDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testEndDateNotNull() throws IOException {
		InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
		policyDTO.setEndDate(null);
		Set<ConstraintViolation<InsurancePolicyDTO>> violations = validator.validate(policyDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPolicyTypeMinSize() throws IOException {
		InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
		policyDTO.setPolicyType("A"); // Less than 2 characters
		Set<ConstraintViolation<InsurancePolicyDTO>> violations = validator.validate(policyDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPolicyTypeMaxSize() throws IOException {
		InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
		policyDTO.setPolicyType("A".repeat(51)); // More than 50 characters
		Set<ConstraintViolation<InsurancePolicyDTO>> violations = validator.validate(policyDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPremiumAmountPositive() throws IOException {
		InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
		policyDTO.setPremiumAmount(BigDecimal.valueOf(-0.01)); // Less than 0.01
		Set<ConstraintViolation<InsurancePolicyDTO>> violations = validator.validate(policyDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPolicyNumberMaxSize() throws IOException {
		InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
		policyDTO.setPolicyNumber("A".repeat(21)); // More than 20 characters
		Set<ConstraintViolation<InsurancePolicyDTO>> violations = validator.validate(policyDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
