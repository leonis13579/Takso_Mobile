package ru.realityfamily.takso_mobile.Logic.Network;

import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.Adapters.CarsListAdapter;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.PersonFragment;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Car;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;

public class CarNetworkLogic {
    public void getCar(MainActivity mainActivity) {
        ServiceLocator.GetInstance().myRetrofit.getCar(AuthData.getInstance()).enqueue(new Callback<Car>() {
            @Override
            public void onResponse(Call<Car> call, Response<Car> response) {
                MyRetrofit.logging(response);
                if (response.isSuccessful()) {
                    Car.getInstance().setInstance(response.body());
                    mainActivity.changeFragment(new PersonFragment());
                }
            }

            @Override
            public void onFailure(Call<Car> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void getCarList(MainActivity mainActivity, RecyclerView recyclerView) {
        ServiceLocator.GetInstance().myRetrofit.getAllCars().enqueue(new Callback<List<Car>>() {
            @Override
            public void onResponse(Call<List<Car>> call, Response<List<Car>> response) {
                MyRetrofit.logging(response);
                if (response.isSuccessful()) {
                    recyclerView.setAdapter(new CarsListAdapter(mainActivity, response.body()));
                }
            }

            @Override
            public void onFailure(Call<List<Car>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void addDriver(MainActivity mainActivity, List<Car> carList, int position) {
        ServiceLocator.GetInstance().myRetrofit
                .addDriver(carList.get(position).getId(), AuthData.getInstance())
                .enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        MyRetrofit.logging(response);
                        if (response.isSuccessful()) {
                            Car.getInstance().setInstance(carList.get(position));
                            mainActivity.backFragment();
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {

                    }
                });
    }

    public void unpinDriver(MainActivity mainActivity) {
        ServiceLocator.GetInstance().myRetrofit
                .unpinCar(AuthData.getInstance())
                .enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                MyRetrofit.logging(response);
                if (response.isSuccessful()) {
                    Car.getInstance().setInstance(null);
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}
