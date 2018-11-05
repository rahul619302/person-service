package com.cg.person.service.starter.service;

import com.cg.person.service.starter.payload.Request;
import com.cg.person.service.starter.payload.Response;

public interface IPersonService {
    Response savePerson(Request request);

    Response getPerson(Integer id);
}
