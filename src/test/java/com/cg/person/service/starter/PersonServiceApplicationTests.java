package com.cg.person.service.starter;

import com.cg.person.service.starter.constant.PersonConstants;
import com.cg.person.service.starter.controller.PersonController;
import com.cg.person.service.starter.payload.Request;
import com.cg.person.service.starter.payload.Response;
import com.cg.person.service.starter.pojo.Person;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonServiceApplicationTests {

	@Autowired
	private PersonController personController;
	@InjectMocks
	private ObjectMapper objectMapper;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
	}

	@Test
	void savePerson() throws Exception {
		Map<String, Object> requestMap=new HashMap<>();
		requestMap.put("designation", "Developer");
		List<Map<String, String>> addressList=new ArrayList<>();
		Map<String, String> addressMap=new HashMap<>();
		addressMap.put("flatNo", "A904");
		addressMap.put("area", "Sector-15, Belapur CBD");
		addressMap.put("city", "Navi Mumbai");
		addressMap.put("state", "Maharastra");
		addressMap.put("country", "India");
		addressList.add(addressMap);
		requestMap.put("address_list", addressList);
		Request request=new Request("Rahul Singh","employee","", requestMap);


		Response expectedResponse = new Response(PersonConstants.S200, PersonConstants.S200.getMessage());
		Map<String, Object> responseMap=new HashMap<>();
		Map<String, Object> employee=new HashMap<>();
		employee.put("id", 2);
		employee.put("name", "Rahul Singh");
		employee.put("designation", "Developer");

		List<Map<String, Object>> addressList1=new ArrayList<>();
		Map<String, Object> addressMap1=new HashMap<>();
		addressMap1.put("id", 1);
		addressMap1.put("flatNo", "A904");
		addressMap1.put("area", "Sector-15, Belapur CBD");
		addressMap1.put("city", "Navi Mumbai");
		addressMap1.put("state", "Maharastra");
		addressMap1.put("country", "India");
		addressList1.add(addressMap1);
		employee.put("addresses",addressList1);

		Person person=new Person(1, "Rahul Singh", "employee");

		responseMap.put("employee", employee);
		responseMap.put("person", person);

		expectedResponse.setMap(responseMap);

		callPostRequest("/person/save", request, expectedResponse);
	}

	@Test
	void getPerson() throws Exception {
		callGetRequest("/person/get/1", null);
	}

	private void callPostRequest(String url, Request request, Response expectedResponse) throws Exception {
		String acctualResponse = mockMvc.perform(MockMvcRequestBuilders
				.post(url)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(request))
				.contentType(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();
		Assert.assertThat(objectMapper.writeValueAsString(expectedResponse), Is.is(acctualResponse));
	}

	private void callGetRequest(String url, Response expectedResponse) throws Exception {
		String acctualResponse = mockMvc.perform(MockMvcRequestBuilders
				.get(url)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn().getResponse().getContentAsString();
		Assert.assertThat(objectMapper.writeValueAsString(expectedResponse), Is.is(acctualResponse));
	}

}
