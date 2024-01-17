package com.insurancepolicy.dto;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class InsurancePolicyDTO {

	private Long policyId;

	@NotNull(message = "Policy number is required")
	@Size(min = 3, max = 10)
	private String policyNumber;

	@NotNull(message = "Policy type is required")
	private String policyType;

	@NotNull(message = "Premium amount is required")
	private BigDecimal premiumAmount;

	@NotNull(message = "Start date is required")
	private Date startDate;

	@NotNull(message = "End date is required")
	private Date endDate;

	private boolean isActive;

	private int customerId;

	public InsurancePolicyDTO() {
		super();
	}

	public InsurancePolicyDTO(Long policyId, String policyNumber, String policyType, BigDecimal premiumAmount,
			Date startDate, Date endDate, boolean isActive, int customerId) {
		super();
		this.policyId = policyId;
		this.policyNumber = policyNumber;
		this.policyType = policyType;
		this.premiumAmount = premiumAmount;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isActive = isActive;
		this.customerId = customerId;
	}

	public Long getPolicyId() {
		return policyId;
	}

	public void setPolicyId(Long policyId) {
		this.policyId = policyId;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public BigDecimal getPremiumAmount() {
		return premiumAmount;
	}

	public void setPremiumAmount(BigDecimal premiumAmount) {
		this.premiumAmount = premiumAmount;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "InsurancePolicy [policyId=" + policyId + ", policyNumber=" + policyNumber + ", policyType=" + policyType
				+ ", premiumAmount=" + premiumAmount + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", isActive=" + isActive + ", customerId=" + customerId + "]";
	}
}
