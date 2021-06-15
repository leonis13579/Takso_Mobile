package ru.realityfamily.takso_mobile.Fragment.Order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import ru.realityfamily.takso_mobile.Adapters.OrdersListAdapter;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.MyFragment;
import ru.realityfamily.takso_mobile.Logic.Network.OrderNetworkLogic;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.databinding.OrdersListFragmentBinding;

public class OrderList extends MyFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        OrdersListFragmentBinding binding = OrdersListFragmentBinding.inflate(inflater, container, false);

        binding.orderListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        // получение всех открытых заказов
        ServiceLocator.GetInstance().orderNetworkLogic.getActiveOrders(binding.orderListRecyclerView);

        return binding.getRoot();
    }
}
