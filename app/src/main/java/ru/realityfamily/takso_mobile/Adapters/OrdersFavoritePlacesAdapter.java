package ru.realityfamily.takso_mobile.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.realityfamily.takso_mobile.Fragment.MapFragment;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.R;

public class OrdersFavoritePlacesAdapter extends RecyclerView.Adapter<OrdersFavoritePlacesAdapter.OrdersFavoritePlacesViewHolder> {

    MainActivity mainActivity;
    List<String> ordersFavoritePlaces;

    public OrdersFavoritePlacesAdapter(MainActivity mainActivity, List<String> ordersFavoritePlaces) {
        this.mainActivity = mainActivity;
        this.ordersFavoritePlaces = ordersFavoritePlaces;
    }

    @NonNull
    @Override
    public OrdersFavoritePlacesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdersFavoritePlacesViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_favorite_places_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersFavoritePlacesViewHolder holder, int position) {
        holder.ordersFavoritePlacesName.setText(ordersFavoritePlaces.get(position));
        holder.ordersFavoritePlacesElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.clearHistory();
                mainActivity.changeFragment(new MapFragment());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ordersFavoritePlaces.size();
    }

    public class OrdersFavoritePlacesViewHolder extends RecyclerView.ViewHolder {

        TextView ordersFavoritePlacesName;
        RelativeLayout ordersFavoritePlacesElement;

        public OrdersFavoritePlacesViewHolder(@NonNull View itemView) {
            super(itemView);

            ordersFavoritePlacesElement = itemView.findViewById(R.id.ordersFavoritePlacesElement);
            ordersFavoritePlacesName = itemView.findViewById(R.id.ordersFavoritePlacesName);
        }
    }
}
