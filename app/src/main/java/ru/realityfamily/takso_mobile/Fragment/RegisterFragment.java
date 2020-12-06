package ru.realityfamily.takso_mobile.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.R;

import static ru.realityfamily.takso_mobile.MainActivity.authData;

public class RegisterFragment extends MyFragment {
    EditText login;
    EditText pass;
    EditText rep_pass;
    Button reg_button;

    public RegisterFragment(String Title) {
        this.Title = Title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_fragment, container, false);

        login = v.findViewById(R.id.reg_login);
        pass = v.findViewById(R.id.reg_password);
        rep_pass = v.findViewById(R.id.reg_repeat_pass);
        reg_button = v.findViewById(R.id.registerButton);

        rep_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!rep_pass.getText().toString().equals(pass.getText().toString())) {
                    rep_pass.setTextColor(Color.RED);
                } else {
                    rep_pass.setTextColor(Color.BLACK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        reg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!login.getText().toString().isEmpty() &&
                    !pass.getText().toString().isEmpty() &&
                    !rep_pass.getText().toString().isEmpty()) {
                    if (pass.getText().toString().equals(rep_pass.getText().toString())) {
                        authData.setLogin(login.getText().toString());
                        authData.setPassword(pass.getText().toString());

                        // нужно дописать метод отправки данных и все, что ниже нужно обернуть в ситуации получения удачного ответа

                        ((MainActivity) getActivity()).clearHistory().changeFragment(new AuthFragment(getString(R.string.auth)));
                    } else {
                        Toast.makeText(getContext(), "Пароли введенные вами ранее не соападают", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Вы не ввели все необходимые данные для регистрации", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return v;
    }
}
