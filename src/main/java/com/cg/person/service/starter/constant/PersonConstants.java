package com.cg.person.service.starter.constant;

public enum PersonConstants {

    S200("Success"), S400("Excetion Occured"), S401("Invalid Person Id"), S402("Invalid Person Type");

    private String message;

    PersonConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
