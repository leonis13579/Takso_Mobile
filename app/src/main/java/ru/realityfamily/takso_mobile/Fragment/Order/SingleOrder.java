package ru.realityfamily.takso_mobile.Fragment.Order;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.MapFragment;
import ru.realityfamily.takso_mobile.Fragment.MyFragment;
import ru.realityfamily.takso_mobile.Logic.Network.OrderNetworkLogic;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Order;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.ViewElements.MapBottomSheetBehavior;
import ru.realityfamily.takso_mobile.databinding.SingleorderFragmentBinding;

public class SingleOrder extends MyFragment {
    SingleOrder singleOrder = this;
    SingleorderFragmentBinding binding;

    public SingleOrder() {
        ServiceLocator.GetInstance().mapOrderLogic.DriveToClient(Order.getInstance());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SingleorderFragmentBinding.inflate(inflater, container, false);
        MapBottomSheetBehavior.getInstance().getBottomSheetBehavior().setPeekHeight(160);
        setData();
        return binding.getRoot();
    }

    public void setData() {
        binding.orderPrice.setText((String.format("%.2f", Order.getInstance().getPrice()) + " руб."));
        binding.orderDistance.setText(Order.getInstance().getOrderDistance());
        binding.orderFrom.setText(Order.getInstance().getAddressFrom());
        binding.orderTimeToOrDistanceTo.setText(Order.getInstance().getDistanceTo() + " / " + Order.getInstance().getTimeTo());

        switch (Order.getInstance().getStatus()) {
            case Created:
                binding.orderCommit.setText(R.string.commit);
                binding.orderCommit.setBackgroundResource(R.color.Green);
                binding.orderCommit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ServiceLocator.GetInstance().orderNetworkLogic.takeOrder(singleOrder);
                    }
                });
                break;
            case Waiting:
                MapBottomSheetBehavior.getInstance().setHideable(false);

                binding.orderCommit.setText(R.string.CancelOrder);
                binding.orderCommit.setBackgroundResource(R.color.Red);
                binding.orderCommit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ServiceLocator.GetInstance().orderNetworkLogic.closeOrder();
                        MapBottomSheetBehavior.getInstance().setState(BottomSheetBehavior.STATE_HIDDEN);
                    }
                });
                break;
            case Performing:
                MapBottomSheetBehavior.getInstance().setHideable(false);

                binding.orderCommit.setVisibility(View.GONE);
                break;
        }
    }
}
