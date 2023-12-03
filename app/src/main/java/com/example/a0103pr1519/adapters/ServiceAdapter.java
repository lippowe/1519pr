package com.example.a0103pr1519.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a0103pr1519.R;
import com.example.a0103pr1519.data.Services;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.ViewHolder> {
    public interface OnServiceClickListener{
        void onServiceClick(Services service, int position);
    }
    public OnServiceClickListener onClickListener;
    private final List<Services> servicesList;
    private final LayoutInflater layoutInflater;
    public ServiceAdapter(Context context, List<Services> servicesList, OnServiceClickListener onClickListener){
        this.onClickListener = onClickListener;
        this.layoutInflater = LayoutInflater.from(context);
        this.servicesList = servicesList;
    }

    @NonNull
    @Override
    public ServiceAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.service_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Services services = servicesList.get(position);
        holder.tvName.setText(services.getName());
        holder.tvCost.setText(String.valueOf(services.getCost()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onServiceClick(services, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return servicesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvCost;
        @SuppressLint("CutPasteId")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCost = itemView.findViewById(R.id.textViewCost);
            tvName = itemView.findViewById(R.id.textViewName);
        }
    }
}
