package com.cg.person.service.starter.payload;

import com.cg.person.service.starter.constant.PersonConstants;

import java.util.Map;

public class Response {

    private PersonConstants status;
    private String statusDiscription;
    private Map<String, Object> map;

    public Response() {
    }

    public Response(PersonConstants status, String statusDiscription) {
        this.status = status;
        this.statusDiscription = statusDiscription;
    }

    public Response(PersonConstants status, String statusDiscription, Map<String, Object> map) {
        this.status = status;
        this.statusDiscription = statusDiscription;
        this.map = map;
    }

    public PersonConstants getStatus() {
        return status;
    }

    public void setStatus(PersonConstants status) {
        this.status = status;
    }

    public String getStatusDiscription() {
        return statusDiscription;
    }

    public void setStatusDiscription(String statusDiscription) {
        this.statusDiscription = statusDiscription;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", statusDiscription='" + statusDiscription + '\'' +
                ", map=" + map +
                '}';
    }
}
