package ru.realityfamily.takso_mobile.Logic.Network;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.PersonFragment;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Car;
import ru.realityfamily.takso_mobile.Models.Person;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;

public class PersonNetworkLogic {


    public void getDriverInfo(MainActivity mainActivity) {
        ServiceLocator.GetInstance().myRetrofit
                .getDriverInfo(AuthData.getInstance().getToken())
                .enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        MyRetrofit.logging(response);
                        if (response.isSuccessful()) {
                            Person.getInstance().setInstance(response.body());
                            ServiceLocator.GetInstance().carNetworkLogic.getCar(mainActivity);
                        }
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }

    public void postDriverLogic(MainActivity mainActivity) {
        ServiceLocator.GetInstance().myRetrofit
                .postDriverInfo(AuthData.getInstance().getToken(), Person.getInstance())
                .enqueue(new Callback<Person>() {
                    @Override
                    public void onResponse(Call<Person> call, Response<Person> response) {
                        MyRetrofit.logging(response);
                        if (response.isSuccessful()) {
                            Person.getInstance().setInstance(response.body());

                            mainActivity.backFragment();
                        }
                    }

                    @Override
                    public void onFailure(Call<Person> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
    }
}
