package com.example.a0103pr1519.fragments;

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
import com.example.a0103pr1519.data.Users;
import com.example.a0103pr1519.data_base.DataBaseManager;
import com.example.a0103pr1519.databinding.FragmentRegBinding;

public class RegFragment extends Fragment {
    Users user;
    DataBaseManager dataBaseManager;
    FragmentRegBinding binding;

    public RegFragment() {
        // Required empty public constructor
    }

    public RegFragment(Users user) {
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
        if (user != null) {
            binding.textViewReg.setText("Редактирование");
            binding.buttonReg.setVisibility(View.GONE);
            binding.editTextLogin.setVisibility(View.GONE);
            binding.editTextPassword.setText(user.getPassword());
            binding.editTextFam.setText(user.getSecondName());
            binding.editTextName.setText(user.getFirstName());
            binding.editTextOtch.setText(user.getSurName());
            binding.editTextDate.setText(user.getDateBirth());
        }
        else {
            binding.buttonEdit.setVisibility(View.GONE);
        }
        binding.buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editTextPassword.getText().toString().equals("")
                        || binding.editTextFam.getText().toString().equals("")
                        || binding.editTextName.getText().toString().equals("")
                        || binding.editTextOtch.getText().toString().equals("")
                        || binding.editTextDate.getText().toString().equals(""))
                    Toast.makeText(getContext(), "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
                else {
                    try{
                        Users updateUser = new Users();
                        updateUser.setId(user.getId());
                        updateUser.setLogin(user.getLogin().toString());
                        updateUser.setPassword(binding.editTextPassword.getText().toString());
                        updateUser.setSecondName(binding.editTextFam.getText().toString());
                        updateUser.setFirstName(binding.editTextName.getText().toString());
                        updateUser.setSurName(binding.editTextOtch.getText().toString());
                        updateUser.setDateBirth(binding.editTextDate.getText().toString());
                        dataBaseManager.updateUser(updateUser);
                        Toast.makeText(getContext(), "Вы изменили свой профиль ", Toast.LENGTH_SHORT).show();
                        KabinetFragment kabinetFragment = new KabinetFragment(updateUser);
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainerView2, kabinetFragment, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("reg")
                                .commit();
                    }
                    catch (Exception exception){
                        Toast.makeText(getContext(), exception.toString(), Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
        binding.buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.editTextLogin.getText().toString().equals("")
                        || binding.editTextPassword.getText().toString().equals("")
                        || binding.editTextFam.getText().toString().equals("")
                        || binding.editTextName.getText().toString().equals("")
                        || binding.editTextOtch.getText().toString().equals("")
                        || binding.editTextDate.getText().toString().equals(""))
                    Toast.makeText(getContext(), "Все поля должны быть заполнены", Toast.LENGTH_SHORT).show();
                else {
                    try {
                        Users newUser = new Users();
                        newUser.setLogin(binding.editTextLogin.getText().toString());
                        newUser.setPassword(binding.editTextPassword.getText().toString());
                        newUser.setSecondName(binding.editTextFam.getText().toString());
                        newUser.setFirstName(binding.editTextName.getText().toString());
                        newUser.setSurName(binding.editTextOtch.getText().toString());
                        newUser.setDateBirth(binding.editTextDate.getText().toString());
                        dataBaseManager.addUser(newUser);
                        Toast.makeText(getContext(), "Вы зарегистрировались! Теперь нужно войти в приложение", Toast.LENGTH_SHORT).show();
                        getParentFragmentManager().beginTransaction()
                                .replace(R.id.fragmentContainerView, LoginFragment.class, null)
                                .setReorderingAllowed(true)
                                .addToBackStack("reg")
                                .commit();
                    } catch (Exception exception) {
                        Toast.makeText(getContext(), exception.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegBinding.inflate(getLayoutInflater(), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dataBaseManager.closeDb();
    }
}