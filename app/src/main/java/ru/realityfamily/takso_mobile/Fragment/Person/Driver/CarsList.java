package ru.realityfamily.takso_mobile.Fragment.Person.Driver;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.Adapters.CarsListAdapter;
import ru.realityfamily.takso_mobile.Adapters.OrdersListAdapter;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.MyFragment;
import ru.realityfamily.takso_mobile.Logic.Network.CarNetworkLogic;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.Car;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.databinding.CarsListFragmentBinding;

public class CarsList extends MyFragment {

    public CarsList() {
        this.Title = "Выберите свободную машину";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        CarsListFragmentBinding binding = CarsListFragmentBinding.inflate(inflater, container, false);

        binding.carsListRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ServiceLocator.GetInstance().carNetworkLogic.getCarList((MainActivity) getActivity(), binding.carsListRecyclerView);

        binding.carListButton.setVisibility(Car.getInstance().getName().equals("") ? View.GONE : View.VISIBLE);
        binding.carListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceLocator.GetInstance().carNetworkLogic.unpinDriver((MainActivity) getActivity());
            }
        });

        return binding.getRoot();
    }
}
