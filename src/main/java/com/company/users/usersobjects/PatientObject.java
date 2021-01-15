package com.company.users.usersobjects;

public class PatientObject extends UserObject {
    private int secSoc;
    private String postalAddress;
    private String phoneNumber;
    private String mailAdress;

    public PatientObject(int secSoc, String lastName, String firstName, String mailAddress, String postalAddress, String phoneNumber) {
        super(lastName, firstName);
        this.secSoc = secSoc;
        this.postalAddress = postalAddress;
        this.mailAdress = mailAddress;
        this.phoneNumber = phoneNumber;
    }

    public int getSecSoc() {
        return secSoc;
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getFirstName() {
        return super.getFirstName();
    }

    public String getMailAdress() {
        return mailAdress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    @Override
    public PatientObject setName(String name) {
        super.setName(name);
        return this;
    }

    @Override
    public PatientObject setFirstName(String firstName) {
        super.setFirstName(firstName);
        return this;
    }

    public PatientObject setSecSoc(int secSoc) {
        this.secSoc = secSoc;
        return this;
    }

    public PatientObject setMailAdress(String mailAdress) {
        this.mailAdress = mailAdress;
        return this;
    }

    public PatientObject setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public PatientObject setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
        return this;
    }
}
