package com.insurancepolicy.functional;

import static com.insurancepolicy.utils.MasterData.getInsurancePolicyDTO;
import static com.insurancepolicy.utils.MasterData.getInsurancePolicyDTOList;
import static com.insurancepolicy.utils.TestUtils.businessTestFile;
import static com.insurancepolicy.utils.TestUtils.currentTest;
import static com.insurancepolicy.utils.TestUtils.testReport;
import static com.insurancepolicy.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class InsurancePolicyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InsurancePolicyService insurancePolicyService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetAllPolicies() throws Exception {
		List<InsurancePolicyDTO> policyDTOS = getInsurancePolicyDTOList();

		when(this.insurancePolicyService.getAllPolicies()).thenReturn(policyDTOS);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/policies")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(policyDTOS)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testGetPolicyById() throws Exception {
		InsurancePolicyDTO policyDTO = getInsurancePolicyDTO();
		when(this.insurancePolicyService.getInsurancePolicyById(policyDTO.getPolicyId())).thenReturn(policyDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/policies/" + policyDTO.getPolicyId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(policyDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testCreatePolicy() throws Exception {
		InsurancePolicyDTO policyDTO = getInsurancePolicyDTO();

		when(this.insurancePolicyService.createInsurancePolicy(any())).thenReturn(policyDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/policies")
				.content(MasterData.asJsonString(policyDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(policyDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testUpdatePolicy() throws Exception {
		InsurancePolicyDTO policyDTO = getInsurancePolicyDTO();

		when(this.insurancePolicyService.updateInsurancePolicy(eq(policyDTO.getPolicyId()), any()))
				.thenReturn(policyDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/policies/" + policyDTO.getPolicyId())
				.content(MasterData.asJsonString(policyDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(policyDTO)) ? "true"
						: "false"),
				businessTestFile);
	}

	@Test
	public void testDeletePolicy() throws Exception {
		InsurancePolicyDTO policyDTO = getInsurancePolicyDTO();
		when(this.insurancePolicyService.deleteInsurancePolicy(policyDTO.getPolicyId())).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/policies/" + policyDTO.getPolicyId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}
}
