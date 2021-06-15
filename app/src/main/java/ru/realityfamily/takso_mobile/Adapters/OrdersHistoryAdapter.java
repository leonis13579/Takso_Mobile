package ru.realityfamily.takso_mobile.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.realityfamily.takso_mobile.Models.Order;
import ru.realityfamily.takso_mobile.R;

public class OrdersHistoryAdapter extends RecyclerView.Adapter<OrdersHistoryAdapter.OrdersHistoryViewHolder> {
    List<Order> orders;

    public OrdersHistoryAdapter(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrdersHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdersHistoryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_history_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersHistoryViewHolder holder, int position) {
        holder.orderHistoryElementTime.setText("Заказ от " + orders.get(position).getCreateTime());
        holder.orderHistoryElementDestinationFrom.setText(orders.get(position).getAddressFrom());
        holder.orderHistoryElementDestinationTo.setText(orders.get(position).getAddressTo());
        holder.orderHistoryElementPrice.setText((String.format("%.2f", orders.get(position).getPrice()) + " руб."));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrdersHistoryViewHolder extends RecyclerView.ViewHolder{

        TextView orderHistoryElementTime;
        TextView orderHistoryElementDestinationFrom;
        TextView orderHistoryElementDestinationTo;
        TextView orderHistoryElementPrice;

        public OrdersHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            orderHistoryElementTime = itemView.findViewById(R.id.orderHistoryElementTime);
            orderHistoryElementDestinationFrom = itemView.findViewById(R.id.orderHistoryElementDestinationFrom);
            orderHistoryElementDestinationTo = itemView.findViewById(R.id.orderHistoryElementDestinationTo);
            orderHistoryElementPrice = itemView.findViewById(R.id.orderHistoryElementPrice);
        }
    }
}
