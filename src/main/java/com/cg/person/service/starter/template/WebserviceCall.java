package com.cg.person.service.starter.template;

import com.cg.person.service.starter.payload.Request;
import com.cg.person.service.starter.payload.Response;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource("classpath:person.properties")
public class WebserviceCall implements IWebServiceCall {

    @Autowired
    private RestTemplate restTemplate;
    @Value( "${employee_service_url}" )
    private String employeeUrl;
    @Value( "${student_service_url}" )
    private String studentUrl;

    @Override
    public Response callPostService(String endUrl, String type, Request request) {
        String url = "";
        if ("student".equals(type))
            url = studentUrl + endUrl;
        else if ("employee".equals(type))
            url = employeeUrl + endUrl;
        ResponseEntity<Response> response = restTemplate.postForEntity(url, request, Response.class);
        return response.getBody();
    }

    @Override
    public Response callGetService(String endUrl, String type, String... params) {
        String pathVariable = "";
        for (String param : params) {
            pathVariable = pathVariable + "/" + param;
        }
        String url = "";
        if ("student".equals(type))
            url = studentUrl + endUrl;
        else if ("employee".equals(type))
            url = employeeUrl + endUrl;
        ResponseEntity<Response> response = restTemplate.getForEntity(url + "/" + pathVariable, Response.class);
        return response.getBody();
    }
}
