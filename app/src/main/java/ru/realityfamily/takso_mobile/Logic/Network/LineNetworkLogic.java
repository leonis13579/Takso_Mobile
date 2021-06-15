package ru.realityfamily.takso_mobile.Logic.Network;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.AuthFragment;
import ru.realityfamily.takso_mobile.Fragment.Order.OrderList;
import ru.realityfamily.takso_mobile.Logic.Notify;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Line;
import ru.realityfamily.takso_mobile.Models.Order;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.ViewElements.MapBottomSheetBehavior;
import ru.realityfamily.takso_mobile.databinding.BottomSheetBinding;

public class LineNetworkLogic {

    public void addLine(MainActivity mainActivity, Context context) {
        ServiceLocator.GetInstance().myRetrofit
                .addLine(AuthData.getInstance().getToken())
                .enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                MyRetrofit.logging(response);
                if (response.isSuccessful()) {
                    Line.getInstance().setOnLine(true);
                    ServiceLocator.GetInstance().notify.sendNotify(context, "Вы вышли на линию", "Пора взять первый заказ");

                    mainActivity.setAppBarExtrBtn(R.drawable.list, new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (Order.getInstance() == null || Order.getInstance().getStatus() == Order.Statuses.Created) {
                                MapBottomSheetBehavior.getInstance().getBottomSheetBehavior().setPeekHeight(0);
                                mainActivity.getSupportFragmentManager().beginTransaction().replace(
                                        BottomSheetBinding.bind(MapBottomSheetBehavior.getInstance().getBottomSheetContainer()).bottomSheetContainer.getId(),
                                        new OrderList()
                                ).commit();
                                MapBottomSheetBehavior.getInstance().getBottomSheetBehavior().setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                            }
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void closeLine(MainActivity mainActivity, Context context, Button personExit) {
        ServiceLocator.GetInstance().myRetrofit
                .closeLine(AuthData.getInstance().getToken())
                .enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                MyRetrofit.logging(response);
                if (response.isSuccessful()) {
                    Line.getInstance().setOnLine(false);
                    // должен быть метод на отправку сигнала о завершении линии
                    ServiceLocator.GetInstance().notify.sendNotify(context, "Вы вышли с линии", "Надеемся на скорую встречу с вами");

                    if (personExit != null) {
                        personExit.setText(R.string.personExit);
                        personExit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mainActivity.clearHistory();
                                mainActivity.changeFragment(new AuthFragment());
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }
}
