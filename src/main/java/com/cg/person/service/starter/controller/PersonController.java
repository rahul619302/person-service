package com.cg.person.service.starter.controller;

import com.cg.person.service.starter.payload.Request;
import com.cg.person.service.starter.payload.Response;
import com.cg.person.service.starter.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private IPersonService personService;

    @PostMapping("/save")
    public Response savePerson(@RequestBody Request request){
        return personService.savePerson(request);
    }

    @GetMapping("/get/{id}")
    public Response getPerson(@PathVariable Integer id){
        return personService.getPerson(id);
    }
}
