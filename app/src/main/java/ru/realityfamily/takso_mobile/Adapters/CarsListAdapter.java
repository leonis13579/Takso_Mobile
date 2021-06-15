package ru.realityfamily.takso_mobile.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.MapFragment;
import ru.realityfamily.takso_mobile.Logic.Network.CarNetworkLogic;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Car;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;
import ru.realityfamily.takso_mobile.R;

public class CarsListAdapter extends RecyclerView.Adapter<CarsListAdapter.CarsListViewHolder> {

    MainActivity mainActivity;
    List<Car> carList;

    public CarsListAdapter(MainActivity mainActivity, List<Car> carList) {
        this.mainActivity = mainActivity;
        this.carList = carList;
    }

    @NonNull
    @Override
    public CarsListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarsListViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cars_list_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CarsListViewHolder holder, int position) {
        holder.carsListElementName.setText(carList.get(position).getName());
        holder.carsListElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ServiceLocator.GetInstance().carNetworkLogic.unpinDriver(mainActivity);
                ServiceLocator.GetInstance().carNetworkLogic.addDriver(mainActivity, carList, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarsListViewHolder extends RecyclerView.ViewHolder {

        TextView carsListElementName;
        RelativeLayout carsListElement;

        public CarsListViewHolder(@NonNull View itemView) {
            super(itemView);

            carsListElement = itemView.findViewById(R.id.carsListElement);
            carsListElementName = itemView.findViewById(R.id.carsListElementName);
        }
    }
}
