package com.example.a0103pr1519;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.a0103pr1519.data_base.DataBaseManager;
import com.example.a0103pr1519.databinding.ActivityMainBinding;
import com.example.a0103pr1519.fragments.LoginFragment;
import com.example.a0103pr1519.fragments.MainMenuFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerView, LoginFragment.class, null)
                .setReorderingAllowed(true)
                .disallowAddToBackStack()
                .commit();
    }

}