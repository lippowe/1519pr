package com.example.a0103pr1519.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a0103pr1519.R;
import com.example.a0103pr1519.data.Orders;
import com.example.a0103pr1519.data.Services;
import com.example.a0103pr1519.data_base.DataBaseManager;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    List<Orders> ordersList;
    LayoutInflater layoutInflater;
    DataBaseManager dataBaseManager;
    public OrderAdapter (Context context, List<Orders> ordersList){
        this.layoutInflater = LayoutInflater.from(context);
        this.ordersList = ordersList;
    }
    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.order_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        dataBaseManager = new DataBaseManager(holder.itemView.getContext());
        dataBaseManager.openDb();
        Orders order = ordersList.get(position);
        holder.tvDateOrder.setText(order.getDate());
        holder.tvNameOrder.setText(dataBaseManager.getService(order.getIdService()).getName());
        dataBaseManager.closeDb();
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameOrder, tvDateOrder;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameOrder = itemView.findViewById(R.id.textViewNameOrder);
            tvDateOrder = itemView.findViewById(R.id.textViewDate);
        }
    }
}
