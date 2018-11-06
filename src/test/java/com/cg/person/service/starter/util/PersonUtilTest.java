package com.cg.person.service.starter.util;

import com.cg.person.service.starter.constant.PersonConstants;
import com.cg.person.service.starter.payload.Response;
import com.cg.person.service.starter.pojo.Person;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PersonUtilTest {

    @InjectMocks
    private PersonUtil personUtil;

    @Test
    void getResponse() {
        Map<String, Object> map=new HashMap<>();
        Response response=new Response();
        response.setMap(map);
        Person person=new Person(1, "Rahul Singh", "student");
        map.put("person", person);
        Response response1=personUtil.getResponse(response, person, "person");
        Assert.assertThat(response, Is.is(response1));
    }

    @Test
    void getAllPersons() {
        Map<String, Object> map=new HashMap<>();
        Response response=new Response(PersonConstants.S402, PersonConstants.S402.getMessage());
        List<String> list = Arrays.asList("employee", "student");
        map.put("message", "Please Use Only Below Person Type");
        map.put("personTypes", list);
        response.setMap(map);
        Response response1=personUtil.getAllPersons();
        Assert.assertThat(response, Is.is(response1));
    }

    @Test
    void invalidPersonIdResponse() {
        Response response=new Response(PersonConstants.S401, PersonConstants.S401.getMessage());
        Response response1=personUtil.invalidPersonIdResponse();
        Assert.assertThat(response, Is.is(response1));
    }
}