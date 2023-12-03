package com.example.a0103pr1519.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a0103pr1519.R;
import com.example.a0103pr1519.adapters.ServiceAdapter;
import com.example.a0103pr1519.comporators.ComparatorByCost;
import com.example.a0103pr1519.data.Services;
import com.example.a0103pr1519.data.Users;
import com.example.a0103pr1519.data_base.DataBaseManager;
import com.example.a0103pr1519.databinding.FragmentServicesBinding;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ServicesFragment extends Fragment {
    FragmentServicesBinding binding;
    List<Services> servicesList = new ArrayList<>();
    DataBaseManager dataBaseManager;
    Users user;

    public ServicesFragment() {
        // Required empty public constructor
    }

    public ServicesFragment(Users user){
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
        //setServices();
        servicesList = dataBaseManager.getServices();
        servicesList.sort(new ComparatorByCost());
        ServiceAdapter.OnServiceClickListener onServiceClickListener = new ServiceAdapter.OnServiceClickListener() {
            @Override
            public void onServiceClick(Services service, int position) {
                ServiceItemFragment serviceItemFragment = new ServiceItemFragment(service, user);
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView2, serviceItemFragment, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("serItem")
                        .commit();
            }
        };
        ServiceAdapter servicesAdapter = new ServiceAdapter(getContext(),servicesList, onServiceClickListener);
        binding.recyclerViewServices.setAdapter(servicesAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentServicesBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }
    public void setServices(){
        dataBaseManager.addService(new Services("Тайский массаж", 10000, "Очень тяжелый массаж"));
        dataBaseManager.addService(new Services("Балийский массаж", 20000, "Массаж по азиатски массаж"));
        dataBaseManager.addService(new Services("Аромомассаж", 35000, "Делается с помощью эфирных масел"));
        dataBaseManager.addService(new Services("Массаж для релаксации", 11000, "Помогает снять стресс"));
        dataBaseManager.addService(new Services("Антицеллюлитный массаж", 16000, "Выравнивает кожу и помогает немного похудеть"));
        dataBaseManager.addService(new Services("Лимфодернажный массаж", 55000, "Эта процедура может выполняться вручную или аппаратным методом"));
        dataBaseManager.addService(new Services("Массаж горячими камнями", 17000, "Это вид спа-процедур популярен из-за того, чтоб вызывает приятные ощущения"));
        dataBaseManager.addService(new Services("Оздоровительный массаж", 12000, "Сочетает техники шведского и классического массажа"));
        dataBaseManager.addService(new Services("Массаж ног", 4000, "Помогает расслабить ноги после тяжелого дня"));
        dataBaseManager.addService(new Services("Массаж рук", 3000, "Помогает расслабить руки после тяжелого дня"));
        dataBaseManager.addService(new Services("Массаж спины", 6500, "Помогает расслабить спину после тяжелого дня"));
        dataBaseManager.addService(new Services("Ультразвуковая чистка лица", 2500, "Помогает очистить лицо после тяжелого дня"));
        dataBaseManager.addService(new Services("Массаж шейно-воротниковой зоны", 2500, "Помогает расслабить шею после тяжелого дня"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}