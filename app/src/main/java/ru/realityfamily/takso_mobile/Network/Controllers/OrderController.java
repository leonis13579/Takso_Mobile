package ru.realityfamily.takso_mobile.Network.Controllers;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Order;

public interface OrderController {
    @GET("/orders/{orderId}/info")
    Call<Order> getOrderInfo(@Path("orderId") Long orderId);

    @POST("/orders/close")
    Call<Order> CloseOrder(@Body Order order);

    @GET("/orders/history/driver/{driverId}")
    Call<List<Order>> getDriverHistory(@Path("driverId") Long driverId);

    @GET("/orders/active")
    Call<List<Order>> getActiveOrders();

    @POST("/orders/{orderId}/take/{driverId}")
    Call<Order> takeOrder(@Path("driverId") Long driverId, @Path("orderId") Long orderId);
}
