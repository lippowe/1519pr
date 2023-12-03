package com.example.a0103pr1519.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a0103pr1519.R;
import com.example.a0103pr1519.data.Orders;
import com.example.a0103pr1519.data.Services;
import com.example.a0103pr1519.data.Users;
import com.example.a0103pr1519.data_base.DataBaseManager;
import com.example.a0103pr1519.databinding.FragmentServiceItemBinding;

public class ServiceItemFragment extends Fragment {

    FragmentServiceItemBinding binding;
    Services service;
    Users user;
    DataBaseManager dataBaseManager;
    public ServiceItemFragment() {
        // Required empty public constructor
    }
    public ServiceItemFragment(Services service, Users user) {
        this.service = service;
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
        binding.textViewNameSer.setText(service.getName());
        binding.textViewDescSer.setText(service.getDescription());
        binding.textViewCostSer.setText(String.valueOf(service.getCost()));
        binding.buttonRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Orders order = new Orders(user.getId(), service.getId(), binding.editTextDate.getText().toString());
                dataBaseManager.addOrder(order);
                Toast.makeText(getContext(), "Вы успешно записались, приянтого массажа!", Toast.LENGTH_SHORT).show();
                ServicesFragment servicesFragment = new ServicesFragment(user);
                getParentFragmentManager().beginTransaction()
                        .remove(ServiceItemFragment.this)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentServiceItemBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}