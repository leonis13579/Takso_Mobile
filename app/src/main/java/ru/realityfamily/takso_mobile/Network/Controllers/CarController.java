package ru.realityfamily.takso_mobile.Network.Controllers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Car;
import ru.realityfamily.takso_mobile.Models.Order;

public interface CarController {
    @GET("/cars")
    Call<List<Car>> getAllCars();

    @POST("/cars/get_driver_car")
    Call<Car> getCar(@Body AuthData authData);

    @POST("/car/{carId}/addDriver")
    Call<Boolean> addDriver(@Path("carId") Long carId, @Body AuthData authData);

    @POST("/cars/unpinCar")
    Call<Boolean> unpinCar(@Body AuthData authData);
}
