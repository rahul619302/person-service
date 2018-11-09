package com.cg.person.service.starter.util;

import com.cg.person.service.starter.payload.Response;
import com.cg.person.service.starter.pojo.Person;

public interface IPersonUtil {

    Response getSuccessResponse();
    Response getAllPersons();
    Response invalidPersonIdResponse();
}
