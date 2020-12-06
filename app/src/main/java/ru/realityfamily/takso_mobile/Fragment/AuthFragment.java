package ru.realityfamily.takso_mobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.R;

public class AuthFragment extends MyFragment {

    EditText login;
    EditText password;
    Button enter;
    TextView register;

    String login_value = "";

    public AuthFragment(String Title) {
        this.Title = Title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.auth_fragment, container, false);

        login = v.findViewById(R.id.login_login);
        password = v.findViewById(R.id.login_pass);
        enter = v.findViewById(R.id.login_enterButton);
        register = v.findViewById(R.id.reg_advice);

        if (!login_value.isEmpty()) {
            login.setText(login_value);
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).changeFragment(new RegisterFragment(getString(R.string.register)));
            }
        });
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity ma = (MainActivity) getActivity();
                ma.clearHistory();
                ma.changeFragment(new MapFragment("Заказы"));
            }
        });

        return v;
    }

    public void setLogin(String reg_login) {
        this.login_value = reg_login;
    }

    private AuthFragment getFragment() {
        return this;
    }
}
