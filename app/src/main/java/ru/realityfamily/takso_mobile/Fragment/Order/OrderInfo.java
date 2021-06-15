package ru.realityfamily.takso_mobile.Fragment.Order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.MyFragment;
import ru.realityfamily.takso_mobile.Logic.Network.OrderNetworkLogic;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Order;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.ViewElements.MapBottomSheetBehavior;
import ru.realityfamily.takso_mobile.databinding.OrderInfoBinding;

public class OrderInfo extends MyFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        OrderInfoBinding binding = OrderInfoBinding.inflate(inflater, container, false);

        binding.orderInfoTimeToInfo.setText(getString(R.string.orderInfoTimeToDriver));

        binding.orderInfoTimeTo.setText(Order.getInstance().getTimeTo());
        binding.orderInfoFrom.setText(Order.getInstance().getAddressFrom());
        binding.orderInfoTo.setText(Order.getInstance().getAddressTo());
        binding.orderInfoPrice.setText(String.format("%.2f", Order.getInstance().getPrice()) + " руб.");
        switch (Order.getInstance().getStatus()) {
            case Created:
                binding.orderInfoStatus.setText("Создан");
                break;
            case Waiting:
                binding.orderInfoStatus.setText("Ищем машину");
                break;
            case Performing:
                binding.orderInfoStatus.setText("Выполняется");
                break;
            case Finished:
                binding.orderInfoStatus.setText("Завершен");
                break;
            case Canceled_by_Client:
                binding.orderInfoStatus.setText("Отменен клиентом");
                break;
            case Canceled_by_Driver:
                binding.orderInfoStatus.setText("Отменен водителем");
                break;
        }
        binding.orderInfoCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceLocator.GetInstance().orderNetworkLogic.closeOrder();
            }
        });

        return binding.getRoot();
    }
}
