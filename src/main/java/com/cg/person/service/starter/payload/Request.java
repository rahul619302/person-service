package com.cg.person.service.starter.payload;

import java.util.Map;

public class Request {

    private String param1;
    private String param2;
    private String param3;
    private Map<String, Object> map;

    public Request() {
    }

    public Request(String param1, String param2, String param3, Map<String, Object> map) {
        this.param1 = param1;
        this.param2 = param2;
        this.param3 = param3;
        this.map = map;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Request{" +
                "param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", param3='" + param3 + '\'' +
                ", map=" + map +
                '}';
    }
}
