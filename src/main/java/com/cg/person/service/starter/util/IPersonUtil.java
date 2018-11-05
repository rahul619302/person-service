package com.cg.person.service.starter.util;

import com.cg.person.service.starter.payload.Response;
import com.cg.person.service.starter.pojo.Person;

public interface IPersonUtil {

    Response getResponse(Response response, Person person, String name);
    Response getAllPersons();
    Response invalidPersonIdResponse();
}
