package ru.realityfamily.takso_mobile.Logic.Network;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.Adapters.OrdersHistoryAdapter;
import ru.realityfamily.takso_mobile.Adapters.OrdersListAdapter;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.Order.OrderInfo;
import ru.realityfamily.takso_mobile.Fragment.Order.SingleOrder;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Order;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.ViewElements.MapBottomSheetBehavior;

public class OrderNetworkLogic {


    public void updateOrder(Long orderId) {
        ServiceLocator.GetInstance().myRetrofit
                .updateOrder(orderId)
                .enqueue(new Callback<Order>() {
                    @Override
                    public void onResponse(Call<Order> call, Response<Order> response) {
                        MyRetrofit.logging(response);
                        if (response.isSuccessful()) {
                            Order.getInstance().setInstance(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Order> call, Throwable t) {

                    }
                });
    }

    public void closeOrder() {
        Order.getInstance().setStatus(Order.Statuses.Canceled_by_Driver);

        ServiceLocator.GetInstance().myRetrofit.closeOrder(Order.getInstance()).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                MyRetrofit.logging(response);
                if (response.isSuccessful()) {
                    Order.getInstance().setInstance(response.body());

                    MapBottomSheetBehavior.getInstance().setHideable(true);
                    MapBottomSheetBehavior.getInstance().setState(BottomSheetBehavior.STATE_HIDDEN);
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getHistory(RecyclerView recyclerView) {
        ServiceLocator.GetInstance().myRetrofit
                .getDriverHistory(AuthData.getInstance().getToken())
                .enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                        MyRetrofit.logging(response);
                        if (response.isSuccessful()) {
                            recyclerView.setAdapter(new OrdersHistoryAdapter(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t) {

                    }
                });
    }

    public void getActiveOrders(RecyclerView recyclerView) {
        ServiceLocator.GetInstance().myRetrofit
                .getActiveOrders()
                .enqueue(new Callback<List<Order>>() {
                    @Override
                    public void onResponse(Call<List<Order>> call, Response<List<Order>> response) {
                        MyRetrofit.logging(response);
                        if (response.isSuccessful()) {
                            recyclerView.setAdapter(new OrdersListAdapter(response.body()));
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Order>> call, Throwable t) {

                    }
                });
    }

    public void takeOrder(SingleOrder singleOrder) {
        ServiceLocator.GetInstance().myRetrofit.takeOrder(AuthData.getInstance().getToken(), Order.getInstance().getId()).enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                MyRetrofit.logging(response);
                if (response.isSuccessful()) {
                    Order.getInstance().setInstance(response.body());
                    singleOrder.setData();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {

            }
        });
    }
}
