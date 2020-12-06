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

import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.R;

import static ru.realityfamily.takso_mobile.MainActivity.authData;

public class AuthFragment extends MyFragment {

    EditText login;
    EditText password;
    Button enter;
    TextView register;

    public AuthFragment(String Title) {
        this.Title = Title;
        if (authData == null) {
            authData = new AuthData();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.auth_fragment, container, false);

        login = v.findViewById(R.id.login_login);
        password = v.findViewById(R.id.login_pass);
        enter = v.findViewById(R.id.login_enterButton);
        register = v.findViewById(R.id.reg_advice);

        if (!authData.getLogin().isEmpty()) {
            Log.d("AuthFragment", authData.getLogin());
            login.setText(authData.getLogin());
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(new RegisterPersonTypeChoose("Выберите кем вы хотите зарегистрироваться"));
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!login.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
                    authData.setLogin(login.getText().toString());
                    authData.setPassword(password.getText().toString());

                    MainActivity ma = (MainActivity) getActivity();
                    ma.clearHistory();
                    ma.changeFragment(new MapFragment("Заказы"));
                }
            }
        });

        return v;
    }

    private AuthFragment getFragment() {
        return this;
    }
}
