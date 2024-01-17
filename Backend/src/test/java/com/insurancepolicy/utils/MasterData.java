package com.insurancepolicy.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.insurancepolicy.dto.InsurancePolicyDTO;

public class MasterData {

    public static InsurancePolicyDTO getInsurancePolicyDTO() {
        InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
        policyDTO.setPolicyId(1L);
        policyDTO.setPolicyNumber("POL123");
        policyDTO.setPolicyType("Health");
        policyDTO.setPremiumAmount(new BigDecimal("150.00"));
        policyDTO.setStartDate(new Date());
        policyDTO.setEndDate(new Date(System.currentTimeMillis() + 86400000)); // Adding one day to the current date
        policyDTO.setActive(true);
        policyDTO.setCustomerId(123);
        return policyDTO;
    }

    public static List<InsurancePolicyDTO> getInsurancePolicyDTOList() {
        List<InsurancePolicyDTO> policyDTOList = new ArrayList<>();

        InsurancePolicyDTO policyDTO = new InsurancePolicyDTO();
        policyDTO.setPolicyId(1L);
        policyDTO.setPolicyNumber("POL123");
        policyDTO.setPolicyType("Health");
        policyDTO.setPremiumAmount(new BigDecimal("150.00"));
        policyDTO.setStartDate(new Date());
        policyDTO.setEndDate(new Date(System.currentTimeMillis() + 86400000)); // Adding one day to the current date
        policyDTO.setActive(true);
        policyDTO.setCustomerId(123);

        policyDTOList.add(policyDTO);

        return policyDTOList;
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            final String jsonContent = mapper.writeValueAsString(obj);

            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String randomStringWithSize(int size) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < size; i++) {
            s.append("A");
        }
        return s.toString();
    }
}
