package com.example.a0103pr1519.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a0103pr1519.R;
import com.example.a0103pr1519.data.Orders;
import com.example.a0103pr1519.data.Users;
import com.example.a0103pr1519.data_base.DataBaseManager;
import com.example.a0103pr1519.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {
    FragmentLoginBinding binding;
    Users user;
    DataBaseManager dataBaseManager;

    public LoginFragment() {
        // Required empty public constructor
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
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editTextLogin.getText().toString().equals("") || binding.editTextPass.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
                } else {
                    user = dataBaseManager.getUser(binding.editTextLogin.getText().toString(), binding.editTextPass.getText().toString());
                    if (user.getLogin() != null) {
                        MainMenuFragment mainMenuFragment = new MainMenuFragment(user);
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainerView, mainMenuFragment, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("login")
                                .commit();
                    } else {
                        Toast.makeText(getContext(), "Вы ввели неправильный логин или пароль", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        binding.buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, RegFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("reg")
                        .commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}