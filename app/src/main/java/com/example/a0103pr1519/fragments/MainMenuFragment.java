package com.example.a0103pr1519.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.a0103pr1519.R;
import com.example.a0103pr1519.data.Users;
import com.example.a0103pr1519.databinding.FragmentMainMenuBinding;
import com.google.android.material.navigation.NavigationBarView;


public class MainMenuFragment extends Fragment {
    FragmentMainMenuBinding binding;
    Users user;
    public MainMenuFragment() {
        // Required empty public constructor
    }
    public MainMenuFragment(Users user){
        this.user = user;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        KabinetFragment kabinetFragment = new KabinetFragment(user);
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView2, kabinetFragment, null)
                .setReorderingAllowed(true)
                .addToBackStack("kab")
                .commit();
        binding.bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.kabinet){
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView2, kabinetFragment, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("kab")
                            .commit();
                }
                if (item.getItemId() == R.id.services){
                    ServicesFragment servicesFragment = new ServicesFragment(user);
                    getChildFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainerView2, servicesFragment, null)
                            .setReorderingAllowed(true)
                            .addToBackStack("reg")
                            .commit();
                }
                return true;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMainMenuBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }
}