package ru.realityfamily.takso_mobile.Models;

public class AuthData {
    private static AuthData Instance = new AuthData();

    public static AuthData getInstance() {
        if (Instance == null) {
            Instance = new AuthData();
        }

        return Instance;
    }

    public static AuthData setInstance(AuthData authData) {
        Instance = authData;
        return Instance;
    }

    String login = "";
    String password = "";
    Long token = 0L;
    String type = "Driver";


    private AuthData() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
        this.password = "";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getToken() {
        return token;
    }

    public void setToken(Long token) {
        this.token = token;
    }
}
