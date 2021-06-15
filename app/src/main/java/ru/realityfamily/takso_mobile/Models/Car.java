package ru.realityfamily.takso_mobile.Models;

import retrofit2.http.Body;

public class Car {
    private static Car Instance;
    public static Car getInstance() {
        if (Instance == null) {
            Instance = new Car();
        }
        return Instance;
    }
    private Car() {
    }

    Long id = 0L;
    String codeName = "";
    String name = "";

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Car setInstance(Car car) {
        Instance = car;
        return Instance;
    }
}
