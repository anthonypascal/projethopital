package com.company.users.usersobjects;

public class UserObject {

    String name;
    String firstName;

    UserObject(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }

    public PatientObject setName(String name) {
        this.name = name;
        return null;
    }

    public PatientObject setFirstName(String firstName) {
        this.firstName = firstName;
        return null;
    }
}
