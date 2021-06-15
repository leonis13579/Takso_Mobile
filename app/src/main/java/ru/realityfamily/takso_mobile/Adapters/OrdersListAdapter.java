package ru.realityfamily.takso_mobile.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.util.List;

import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.MapFragment;
import ru.realityfamily.takso_mobile.Fragment.Order.SingleOrder;
import ru.realityfamily.takso_mobile.Logic.MapOrderLogic;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.Order;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.ViewElements.MapBottomSheetBehavior;
import ru.realityfamily.takso_mobile.databinding.BottomSheetBinding;
import ru.realityfamily.takso_mobile.databinding.OrderListElementBinding;

public class OrdersListAdapter extends RecyclerView.Adapter<OrdersListAdapter.OrdersHistoryViewHolder> {
    List<Order> orders;

    public OrdersListAdapter(List<Order> orders) {
        this.orders = orders;

        ServiceLocator.GetInstance().mapOrderLogic.PutOrdersOnMap(orders);
    }

    @NonNull
    @Override
    public OrdersHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrdersHistoryViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_element, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersHistoryViewHolder holder, int position) {
        holder.orderHistoryElementTime.setText("Заказ от " + orders.get(position).getCreateTime());
        holder.orderHistoryElementDestinationFrom.setText(orders.get(position).getAddressFrom());
        holder.orderHistoryElementPrice.setText((String.format("%.2f", orders.get(position).getPrice()) + " руб."));

        holder.orderListElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order.setInstance(orders.get(position));

                ServiceLocator.GetInstance().mainActivity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(
                                BottomSheetBinding.bind(MapBottomSheetBehavior.getInstance().getBottomSheetContainer()).bottomSheetContainer.getId(),
                                new SingleOrder()
                        )
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrdersHistoryViewHolder extends RecyclerView.ViewHolder{

        TextView orderHistoryElementTime;
        TextView orderHistoryElementDestinationFrom;
        TextView orderHistoryElementPrice;

        LinearLayout orderListElement;

        public OrdersHistoryViewHolder(@NonNull View itemView) {
            super(itemView);

            OrderListElementBinding binding = OrderListElementBinding.bind(itemView);

            orderListElement = binding.orderListElement;

            orderHistoryElementTime = binding.orderListElementTime;
            orderHistoryElementDestinationFrom = binding.orderListElementDestinationFrom;
            orderHistoryElementPrice = binding.orderListElementPrice;
        }
    }
}
