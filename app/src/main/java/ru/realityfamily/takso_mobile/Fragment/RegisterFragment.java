package ru.realityfamily.takso_mobile.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Logic.Network.AuthNetworkLogic;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.databinding.RegisterFragmentBinding;

public class RegisterFragment extends MyFragment {
    private RegisterFragmentBinding binding;

    public RegisterFragment() { this.Title = "Регистрация"; }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = RegisterFragmentBinding.inflate(inflater, container, false);

        binding.regRepeatPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!binding.regRepeatPass.getText().toString().equals(binding.regPassword.getText().toString())) {
                    binding.regRepeatPass.setTextColor(Color.RED);
                } else {
                    binding.regRepeatPass.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.regLogin.getText().toString().isEmpty() &&
                    !binding.regPassword.getText().toString().isEmpty() &&
                    !binding.regRepeatPass.getText().toString().isEmpty()) {
                    if (binding.regPassword.getText().toString().equals(binding.regRepeatPass.getText().toString())) {
                        Log.d("Register", "Started reg");
                        AuthData.getInstance().setLogin(binding.regLogin.getText().toString());
                        AuthData.getInstance().setPassword(binding.regPassword.getText().toString());

                        ServiceLocator.GetInstance().authNetworkLogic.register((MainActivity) getActivity());
                    } else {
                        Toast.makeText(getContext(), "Пароли введенные вами ранее не соападают", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Вы не ввели все необходимые данные для регистрации", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }
}
