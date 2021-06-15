package ru.realityfamily.takso_mobile.Models;

public class Person {
    String name = "";
    String lastName = "";
    String codeName = "";
    String email = "";
    String phone = "";
    String login = AuthData.getInstance().login;

    private static Person Instance;
    public static Person getInstance() {
        if (Instance == null) {
            Instance = new Person();
        }
        return Instance;
    }
    public Person setInstance(Person person) {
        Instance = person;
        return Instance;
    }

    private Person() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
