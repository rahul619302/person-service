package com.cg.person.service.starter.template;

import com.cg.person.service.starter.payload.Request;
import com.cg.person.service.starter.payload.Response;

public interface IWebServiceCall {

    Response callPostService(String endUrl, String type, Request request);
    Response callGetService(String endUrl, String type, String... params);
}
