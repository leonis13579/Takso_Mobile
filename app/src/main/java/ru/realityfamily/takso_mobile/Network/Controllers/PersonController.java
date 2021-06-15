package ru.realityfamily.takso_mobile.Network.Controllers;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.realityfamily.takso_mobile.Models.Person;

public interface PersonController {

    @GET("/driver/{driverId}/getinfo")
    Call<Person> getDriverInfo(@Path("driverId") Long driverId);

    @POST("/driver/{driverId}/postinfo")
    Call<Person> postDriverInfo(@Path("driverId") Long driverId, @Body Person person);
}
