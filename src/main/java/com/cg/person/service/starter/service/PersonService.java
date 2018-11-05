package com.cg.person.service.starter.service;

import com.cg.person.service.starter.constant.PersonConstants;
import com.cg.person.service.starter.dao.IPersonDao;
import com.cg.person.service.starter.payload.Request;
import com.cg.person.service.starter.payload.Response;
import com.cg.person.service.starter.pojo.Person;
import com.cg.person.service.starter.template.IWebServiceCall;
import com.cg.person.service.starter.util.IPersonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED)
public class PersonService implements IPersonService {

    @Autowired
    private IPersonDao personDao;
    @Autowired
    private IPersonUtil personUtil;
    @Autowired
    private IWebServiceCall webServiceCall;


    @Override
    @Transactional(readOnly = false)
    public Response savePerson(Request request) {
        Response response = null;
        Integer id = personDao.getMaxId();
        request.setParam3((id != null ? id + 1 : 1) + "");
        if ("employee".equalsIgnoreCase(request.getParam2()))
            response = webServiceCall.callPostService("employee/save", "employee", request);
        else if ("student".equalsIgnoreCase(request.getParam2()))
            response = webServiceCall.callPostService("student/save", "student", request);
        else
            response = personUtil.getAllPersons();

        if (PersonConstants.S200.getMessage().equalsIgnoreCase(response.getStatusDiscription())) {
            Person person = new Person(0, request.getParam1(), request.getParam2());
            person = personDao.save(person);
            response = personUtil.getResponse(response, person, "person");
        }
        return response;
    }

    @Override
    public Response getPerson(Integer id) {

        Optional<Person> personOptional = personDao.findById(id);
        if (!personOptional.isPresent())
            return personUtil.invalidPersonIdResponse();

        Response response = null;
        Person person = personOptional.get();
        if ("employee".equalsIgnoreCase(person.getType()))
            response = webServiceCall.callGetService("employee/get", "employee", id + "");
        if ("student".equalsIgnoreCase(person.getType()))
            response = webServiceCall.callGetService("student/get", "student", id + "");

        return response;
    }


}