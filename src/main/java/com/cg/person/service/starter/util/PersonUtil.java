package com.cg.person.service.starter.util;

import com.cg.person.service.starter.constant.PersonConstants;
import com.cg.person.service.starter.payload.Response;
import com.cg.person.service.starter.pojo.Person;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonUtil implements IPersonUtil {

    public Response getResponse(Response response, Person person, String name) {
        Map<String, Object> map = response.getMap();
        map.put(name, person);
        response.setMap(map);
        return response;
    }

    public Response getAllPersons() {
        Response response = new Response(PersonConstants.S402, PersonConstants.S402.getMessage());
        List<String> list = Arrays.asList("employee", "student");
        Map<String, Object> map = new LinkedHashMap<>(4);
        map.put("message", "Please Use Only Below Person Type");
        map.put("personTypes", list);
        response.setMap(map);
        return response;
    }

    public Response invalidPersonIdResponse(){
        return new Response(PersonConstants.S401, PersonConstants.S401.getMessage());
    }
}
