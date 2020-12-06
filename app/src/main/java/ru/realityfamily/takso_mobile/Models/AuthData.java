package ru.realityfamily.takso_mobile.Models;

public class AuthData {
    public enum PersonType {
        Client,
        Driver
    }

    String login = "";
    String password = "";
    PersonType type;
    String token = "";


    public AuthData() {

    }

    public AuthData(String login, String password, PersonType type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonType getType() {
        return type;
    }

    public void setType(PersonType type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
