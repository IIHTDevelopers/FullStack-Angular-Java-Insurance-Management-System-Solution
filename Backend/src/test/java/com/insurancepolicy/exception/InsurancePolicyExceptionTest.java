package com.insurancepolicy.exception;

import static com.insurancepolicy.utils.MasterData.getInsurancePolicyDTO;
import static com.insurancepolicy.utils.TestUtils.currentTest;
import static com.insurancepolicy.utils.TestUtils.exceptionTestFile;
import static com.insurancepolicy.utils.TestUtils.testReport;
import static com.insurancepolicy.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.insurancepolicy.controller.InsurancePolicyController;
import com.insurancepolicy.dto.InsurancePolicyDTO;
import com.insurancepolicy.service.InsurancePolicyService;
import com.insurancepolicy.utils.MasterData;

@WebMvcTest(InsurancePolicyController.class)
@AutoConfigureMockMvc
public class InsurancePolicyExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InsurancePolicyService insurancePolicyService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreatePolicyInvalidDataException() throws Exception {
		InsurancePolicyDTO policyDTO = getInsurancePolicyDTO();
		policyDTO.setPolicyNumber(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/policies")
				.content(MasterData.asJsonString(policyDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdatePolicyInvalidDataException() throws Exception {
		InsurancePolicyDTO policyDTO = getInsurancePolicyDTO();
		policyDTO.setPolicyNumber(null);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/policies/" + policyDTO.getPolicyId())
				.content(MasterData.asJsonString(policyDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		System.out.println(result.getResponse().getStatus());
		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetPolicyByIdResourceNotFoundException() throws Exception {
		InsurancePolicyDTO policyDTO = getInsurancePolicyDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Insurance Policy not found");

		when(this.insurancePolicyService.getInsurancePolicyById(policyDTO.getPolicyId()))
				.thenThrow(new NotFoundException("Insurance Policy not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/policies/" + policyDTO.getPolicyId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdatePolicyByIdResourceNotFoundException() throws Exception {
		InsurancePolicyDTO policyDTO = getInsurancePolicyDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Insurance Policy not found");

		when(this.insurancePolicyService.updateInsurancePolicy(eq(1234l), any()))
				.thenThrow(new NotFoundException("Insurance Policy not found"));

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/policies/" + 1234l)
				.content(MasterData.asJsonString(policyDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testDeletePolicyByIdResourceNotFoundException() throws Exception {
		InsurancePolicyDTO policyDTO = getInsurancePolicyDTO();
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Insurance Policy not found");

		when(this.insurancePolicyService.deleteInsurancePolicy(policyDTO.getPolicyId()))
				.thenThrow(new NotFoundException("Insurance Policy not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/policies/" + policyDTO.getPolicyId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
