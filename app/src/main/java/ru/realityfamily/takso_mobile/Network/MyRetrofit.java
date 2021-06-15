package ru.realityfamily.takso_mobile.Network;

import android.util.Log;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Car;
import ru.realityfamily.takso_mobile.Models.Order;
import ru.realityfamily.takso_mobile.Models.Person;
import ru.realityfamily.takso_mobile.Network.Controllers.AuthController;
import ru.realityfamily.takso_mobile.Network.Controllers.CarController;
import ru.realityfamily.takso_mobile.Network.Controllers.LineController;
import ru.realityfamily.takso_mobile.Network.Controllers.OrderController;
import ru.realityfamily.takso_mobile.Network.Controllers.PersonController;

public class MyRetrofit {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.1.82:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    AuthController authController = retrofit.create(AuthController.class);
    public Call<Boolean> register(AuthData authData) {
        return authController.register(authData);
    }
    public Call<AuthData> login(AuthData authData) {
        return authController.login(authData);
    }


    PersonController personController = retrofit.create(PersonController.class);
    public Call<Person> getDriverInfo(Long driverId) {
        return personController.getDriverInfo(driverId);
    }
    public Call<Person> postDriverInfo(Long driverId, Person person) {
        return personController.postDriverInfo(driverId, person);
    }


    OrderController orderController = retrofit.create(OrderController.class);
    public Call<Order> closeOrder(Order order) {
        return orderController.CloseOrder(order);
    }
    public Call<List<Order>> getDriverHistory(Long driverId) {
        return orderController.getDriverHistory(driverId);
    }
    public Call<List<Order>> getActiveOrders() {
        return orderController.getActiveOrders();
    }
    public Call<Order> updateOrder(Long orderId) {
        return orderController.getOrderInfo(orderId);
    }
    public Call<Order> takeOrder(Long driverId, Long orderId) { return orderController.takeOrder(driverId, orderId);}


    CarController carController = retrofit.create(CarController.class);
    public Call<List<Car>> getAllCars() {
        return carController.getAllCars();
    }
    public Call<Car> getCar(AuthData authData) {
        return carController.getCar(authData);
    }
    public Call<Boolean> addDriver(Long carId, AuthData authData) {
        return carController.addDriver(carId, authData);
    }
    public Call<Boolean> unpinCar(AuthData authData) {
        return carController.unpinCar(authData);
    }


    LineController lineController = retrofit.create(LineController.class);
    public Call<Boolean> addLine(Long driverId) {
        return lineController.addLine(driverId);
    }
    public Call<Boolean> checkLine(Long driverId) {
        return lineController.checkLine(driverId);
    }
    public Call<Void> closeLine(Long driverId) {
        return lineController.closeLine(driverId);
    }


    public static void logging(Response response) {
        if (response.isSuccessful()) {
            Log.d("Retrofit log", "URL: " + response.raw().request().url() +
                    "\nCode: " + response.code());
        } else {
            Log.e("Retrofit log", "URL: " + response.raw().request().url() +
                    "\nCode: " + response.code());
        }
    }
}
