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
    void getSuccessResponse() {
        Assert.assertThat(new Response(PersonConstants.S200, PersonConstants.S200.getMessage()), Is.is(personUtil.getSuccessResponse()));
    }

    @Test
    void getAllPersons() {
        Map<String, Object> map=new HashMap<>();
        Response response=new Response(PersonConstants.S402, PersonConstants.S402.getMessage());
        List<String> list = Arrays.asList("employee", "student");
        map.put("message", "Please Use Only Below Person Type");
        map.put("personTypes", list);
        response.setMap(map);
        Assert.assertThat(response, Is.is(personUtil.getAllPersons()));
    }

    @Test
    void invalidPersonIdResponse() {
        Assert.assertThat(new Response(PersonConstants.S401, PersonConstants.S401.getMessage()), Is.is(personUtil.invalidPersonIdResponse()));
    }
}