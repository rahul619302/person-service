package com.cg.person.service.starter.controller;

import com.cg.person.service.starter.constant.PersonConstants;
import com.cg.person.service.starter.payload.Request;
import com.cg.person.service.starter.payload.Response;
import com.cg.person.service.starter.service.IPersonService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bouncycastle.cert.ocsp.Req;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    private IPersonService personService;
    @InjectMocks
    private PersonController personController;
    @InjectMocks
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;
    private Response expectedResponse;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
        expectedResponse = new Response(PersonConstants.S200, PersonConstants.S200.getMessage());
    }

    @Test
    void savePerson() throws Exception {
        BDDMockito.given(personService.savePerson(Mockito.any(Request.class))).willReturn(expectedResponse);
        callPostRequest("/person/save");
    }

    @Test
    void getPerson() throws Exception {
        BDDMockito.given(personService.getPerson(Mockito.any(Integer.class))).willReturn(expectedResponse);
        callGetRequest("/person/get/1");
    }

    private void callPostRequest(String url) throws Exception {
        String acctualResponse = mockMvc.perform(MockMvcRequestBuilders
                .post(url)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new Request()))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        Assert.assertThat(objectMapper.writeValueAsString(expectedResponse), Is.is(acctualResponse));
    }

    private void callGetRequest(String url) throws Exception {
        String acctualResponse = mockMvc.perform(MockMvcRequestBuilders
                .get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        Assert.assertThat(objectMapper.writeValueAsString(expectedResponse), Is.is(acctualResponse));
    }
}