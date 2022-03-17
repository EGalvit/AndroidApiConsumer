package com.example.volleydemo;

public class Person {

    private int personId;
    private String fullName;
    private String email;
    private String note;

    public Person() {
    }

    public Person(int personId, String fullName, String email, String note) {
        this.personId = personId;
        this.fullName = fullName;
        this.email = email;
        this.note = note;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

