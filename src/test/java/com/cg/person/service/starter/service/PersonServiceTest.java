package com.cg.person.service.starter.service;

import com.cg.person.service.starter.constant.PersonConstants;
import com.cg.person.service.starter.dao.IPersonDao;
import com.cg.person.service.starter.payload.Request;
import com.cg.person.service.starter.payload.Response;
import com.cg.person.service.starter.pojo.Person;
import com.cg.person.service.starter.template.IWebServiceCall;
import com.cg.person.service.starter.util.IPersonUtil;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private IPersonDao personDao;
    @Mock
    private IPersonUtil personUtil;
    @Mock
    private IWebServiceCall webServiceCall;
    @InjectMocks
    private PersonService personService;
    private Response response;
    private Person person;

    @BeforeEach
    void setUp() {
        response = new Response(PersonConstants.S200, PersonConstants.S200.getMessage());
        person = new Person(1, "Rahul Singh", "student");
    }

    @Test
    void savePerson() {
        Request request = new Request("Rahul Singh", "employee", "1", null);
        BDDMockito.given(personDao.getMaxId()).willReturn(1);
        BDDMockito.given(webServiceCall.callPostService(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(Request.class))).willReturn(response);
        BDDMockito.given(personDao.save(Mockito.any(Person.class))).willReturn(person);
        BDDMockito.given(personUtil.getResponse(Mockito.any(Response.class), Mockito.any(Person.class), Mockito.any(String.class))).willReturn(response);
        Response response1 = personService.savePerson(request);
        Assert.assertThat(response, Is.is(response1));
    }

    @Test
    void getPerson() {
        BDDMockito.given(personDao.findById(Mockito.anyInt())).willReturn(Optional.of(person));
        Response response11=new Response(PersonConstants.S401, PersonConstants.S401.getMessage());
        BDDMockito.given(personUtil.invalidPersonIdResponse()).willReturn(response11);
        BDDMockito.given(webServiceCall.callGetService(Mockito.any(String.class), Mockito.any(String.class), Mockito.any(String.class))).willReturn(response);
        Response response1 = personService.getPerson(1);
        Assert.assertThat(response, Is.is(response1));
    }
}