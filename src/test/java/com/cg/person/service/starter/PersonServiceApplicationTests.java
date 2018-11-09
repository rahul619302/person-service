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
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonServiceApplicationTests {

    @Autowired
    private PersonController personController;
    @InjectMocks
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private Request request;
    private Response expectedResponse;
    private Map<String, Object> requestMap;
    private Map<String, Object> responseMap;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        requestMap = new LinkedHashMap<>();
        request = getRequest();
        expectedResponse = new Response(PersonConstants.S200, PersonConstants.S200.getMessage());
    }

    @Test
    void savePerson() throws Exception {
        String acctualResponse = callPostRequest("/person/save", request);
        Assert.assertThat(objectMapper.writeValueAsString(expectedResponse), Is.is(acctualResponse));
    }

    @Test
    void getPerson() throws Exception {
        callPostRequest("/person/save", request);
        buildExpectedResponse();
        String acctualResponse = callGetRequest("/person/get/1");
        Response actualRes=modifyResponse(acctualResponse);
        Assert.assertThat(objectMapper.writeValueAsString(expectedResponse), Is.is(objectMapper.writeValueAsString(actualRes)));
    }

    private void buildExpectedResponse() {
        responseMap = new LinkedHashMap<>();
        Map<String, Object> employee = buildEmployee(1, "Rahul Singh", "Developer");
        List<Map<String, Object>> addressList1 = buildAddressList();
        employee.put("addresses", addressList1);
        responseMap.put("employee", employee);
        expectedResponse.setMap(responseMap);
    }

    private List<Map<String, Object>> buildAddressList() {
        List<Map<String, Object>> addressList = new ArrayList<>();
        Map<String, Object> addressMap1 = new LinkedHashMap<>();
        addressMap1.put("id", 0);
        addressMap1.putAll(getAddressMap());
        addressList.add(addressMap1);
        return addressList;
    }

    private Map<String, Object> buildEmployee(int id, String name, String designation) {
        Map<String, Object> employee = new LinkedHashMap<>();
        employee.put("id", id);
        employee.put("name", name);
        employee.put("designation", designation);
        return employee;
    }

    private Response modifyResponse(String acctualResponse) throws java.io.IOException {
        Response actualRes = objectMapper.readValue(acctualResponse, Response.class);
        Map<String, Object> map = actualRes.getMap();
        Map<String, Object> employeeMap = (Map<String, Object>) map.get("employee");
        List<Map<String, Object>> addressMaps = (List<Map<String, Object>>) employeeMap.get("addresses");
        List<Map<String, Object>> addressMapss = modifyAddressId(addressMaps);
        employeeMap.put("addresses", addressMapss);
        map.put("employee", employeeMap);
        return actualRes;
    }

    private List<Map<String, Object>> modifyAddressId(List<Map<String, Object>> addressMaps) {
        List<Map<String, Object>> addressMapss = new LinkedList<>();
        for (Map<String, Object> address : addressMaps) {
            address.put("id", 0);
            addressMapss.add(address);
        }
        return addressMapss;
    }

    private Request getRequest() {
        requestMap.put("designation", "Developer");
        List<Map<String, String>> addressList = new ArrayList<>();
        Map<String, String> addressMap = getAddressMap();
        addressList.add(addressMap);
        requestMap.put("address_list", addressList);
        return new Request("Rahul Singh", "employee", "", requestMap);
    }

    private Map<String, String> getAddressMap() {
        Map<String, String> addressMap = new LinkedHashMap<>();
        addressMap.put("flatNo", "A904");
        addressMap.put("area", "Sector-15, Belapur CBD");
        addressMap.put("city", "Navi Mumbai");
        addressMap.put("state", "Maharastra");
        addressMap.put("country", "India");
        addressMap.put("addressType", "office");
        return addressMap;
    }

    private String callPostRequest(String url, Request request) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(request))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
    }

    private String callGetRequest(String url) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
    }

}
