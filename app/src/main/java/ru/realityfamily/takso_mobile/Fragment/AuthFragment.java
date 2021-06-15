package ru.realityfamily.takso_mobile.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Logic.Network.AuthNetworkLogic;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.databinding.AuthFragmentBinding;

public class AuthFragment extends MyFragment {
    AuthFragmentBinding binding;

    public AuthFragment() {
        this.Title = "Авторизация";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AuthFragmentBinding.inflate(inflater, container, false);

        if (!AuthData.getInstance().getLogin().isEmpty()) {
            Log.d("AuthFragment", AuthData.getInstance().getLogin());
            binding.loginLogin.setText(AuthData.getInstance().getLogin());
        }
        binding.regAdvice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(new RegisterFragment());
            }
        });
        binding.loginEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.loginLogin.getText().toString().isEmpty() && !binding.loginPass.getText().toString().isEmpty()) {
                    AuthData.getInstance().setLogin(binding.loginLogin.getText().toString());
                    AuthData.getInstance().setPassword(binding.loginPass.getText().toString());

                    ServiceLocator.GetInstance().authNetworkLogic.login((MainActivity) getActivity());
                }
            }
        });

        return binding.getRoot();
    }
}
