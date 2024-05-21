package com.ms.common.enums;

public enum NameType {
    FULLNAME ("fullname"),
    SURNAME("surname"),
    FIRSTNAME("firstname");

    public final String value;

    private NameType(String value){
        this.value = value;
    }



}
