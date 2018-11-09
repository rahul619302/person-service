package com.cg.person.service.starter.constant;

public enum PersonConstants {

    S200("Success"), S400("Excetion Occured"), S401("Invalid Person Id"), S402("Invalid Person Type"), S403("Duplicate Person Id");;

    private String message;

    PersonConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
