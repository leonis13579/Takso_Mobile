package ru.realityfamily.takso_mobile.Network.Controllers;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LineController {
    @POST("/line/{driverId}")
    public Call<Boolean> addLine(@Path("driverId") Long driverId);

    @POST("/line/{driverId}/check")
    public Call<Boolean> checkLine(@Path("driverId") Long driverId);

    @POST("/line/{driverId}/close")
    public Call<Void> closeLine(@Path("driverId") Long driverId);
}
