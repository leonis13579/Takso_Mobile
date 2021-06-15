package ru.realityfamily.takso_mobile.Network.Controllers;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import ru.realityfamily.takso_mobile.Models.AuthData;

public interface AuthController {
    @POST("/auth/login")
    Call<AuthData> login(@Body AuthData authData);

    @POST("/auth/register")
    Call<Boolean> register(@Body AuthData authData);
}
