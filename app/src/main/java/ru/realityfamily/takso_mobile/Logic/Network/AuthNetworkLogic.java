package ru.realityfamily.takso_mobile.Logic.Network;

import android.util.Log;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.AuthFragment;
import ru.realityfamily.takso_mobile.Fragment.MapFragment;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;
import ru.realityfamily.takso_mobile.R;

public class AuthNetworkLogic {
    public void login(MainActivity mainActivity) {
        ServiceLocator.GetInstance().myRetrofit.login(AuthData.getInstance()).enqueue(new Callback<AuthData>() {
            @Override
            public void onResponse(Call<AuthData> call, Response<AuthData> response) {
                MyRetrofit.logging(response);
                if (response.isSuccessful()) {
                    AuthData.getInstance().setInstance(response.body());

                    mainActivity.clearHistory();
                    mainActivity.changeFragment(new MapFragment());
                } else {
                    Toast.makeText(mainActivity, "Вы ввели некорректные логин или пароль", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AuthData> call, Throwable t) {

            }
        });
    }

    public void register(MainActivity mainActivity) {
        ServiceLocator.GetInstance().myRetrofit.register(AuthData.getInstance()).enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                MyRetrofit.logging(response);
                if (response.isSuccessful()) {
                    mainActivity.clearHistory().changeFragment(new AuthFragment());
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
    }
}
