package com.example.a0103pr1519.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a0103pr1519.R;
import com.example.a0103pr1519.adapters.OrderAdapter;
import com.example.a0103pr1519.data.Orders;
import com.example.a0103pr1519.data.Users;
import com.example.a0103pr1519.data_base.DataBaseManager;
import com.example.a0103pr1519.databinding.FragmentKabinetBinding;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.ObjIntConsumer;

public class KabinetFragment extends Fragment {
    FragmentKabinetBinding binding;
    Users user;
    List<Orders> ordersList;
    DataBaseManager dataBaseManager;

    public KabinetFragment() {
        // Required empty public constructor
    }

    public KabinetFragment(Users user) {
        this.user = user;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBaseManager = new DataBaseManager(getContext());
        dataBaseManager.openDb();
        ordersList = dataBaseManager.getOrders();
        for (int i = 0; i < ordersList.size(); i++){
            if (ordersList.get(i).getIdUser() != user.getId())
                ordersList.remove(i);
        }
        ordersList.sort(new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
                return Integer.compare(Integer.parseInt(o1.getDate().split("\\.")[2]), Integer.parseInt(o2.getDate().split("\\.")[2]));
            }
        }.thenComparing(new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
                return Integer.compare(Integer.parseInt(o1.getDate().split("\\.")[1]), Integer.parseInt(o2.getDate().split("\\.")[1]));
            }
        }).thenComparing(new Comparator<Orders>() {
            @Override
            public int compare(Orders o1, Orders o2) {
                return Integer.compare(Integer.parseInt(o1.getDate().split("\\.")[0]), Integer.parseInt(o2.getDate().split("\\.")[0]));
            }
        }));
        OrderAdapter orderAdapter = new OrderAdapter(getContext(), ordersList);
        binding.recyclerViewOrders.setAdapter(orderAdapter);
        binding.textViewName.append("\n" + user.getFirstName());
        binding.buttonEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegFragment regFragment = new RegFragment(user);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView2, regFragment)
                        .setReorderingAllowed(true)
                        .addToBackStack("editUser")
                        .commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentKabinetBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }
}