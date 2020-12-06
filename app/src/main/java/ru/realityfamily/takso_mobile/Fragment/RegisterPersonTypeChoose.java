package ru.realityfamily.takso_mobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.R;

public class RegisterPersonTypeChoose extends MyFragment {

    public RegisterPersonTypeChoose(String Title) {
        this.Title = Title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.register_persontype_choose, container, false);

        Button clientChoose = v.findViewById(R.id.clientChoose);
        Button driverChoose = v.findViewById(R.id.driverChoose);

        clientChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.authData.setType(AuthData.PersonType.Client);
                ((MainActivity) getActivity()).changeFragment(new RegisterFragment("Зарегистрироваться"));
            }
        });
        driverChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.authData.setType(AuthData.PersonType.Driver);
                ((MainActivity) getActivity()).changeFragment(new RegisterFragment("Зарегистрироваться"));
            }
        });

        return v;
    }
}
