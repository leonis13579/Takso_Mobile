package ru.realityfamily.takso_mobile.Fragment.Person;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.MyFragment;
import ru.realityfamily.takso_mobile.Logic.Network.PersonNetworkLogic;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Person;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.databinding.PersonInfoFragmentBinding;

public class PersonInfoFragment extends MyFragment {
    public PersonInfoFragment() {
        this.Title = "Информация о пользователе";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PersonInfoFragmentBinding binding = PersonInfoFragmentBinding.inflate(inflater, container, false);

        binding.personInfoName.setText(Person.getInstance().getName());
        binding.personInfoLastName.setText(Person.getInstance().getLastName());
        binding.personInfoCodeNameInfo.setVisibility(View.VISIBLE);
        binding.personInfoCodeName.setVisibility(View.VISIBLE);
        binding.personInfoCodeName.setText(Person.getInstance().getCodeName());
        binding.personInfoEmail.setText(Person.getInstance().getEmail());
        binding.personInfoPhone.setText(Person.getInstance().getEmail());

        binding.personInfoCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Person.getInstance().getName().equals(binding.personInfoName.getText().toString()) ||
                        !Person.getInstance().getLastName().equals(binding.personInfoLastName.getText().toString()) ||
                        !Person.getInstance().getCodeName().equals(binding.personInfoCodeName.getText().toString()) ||
                        !Person.getInstance().getEmail().equals(binding.personInfoEmail.getText().toString()) ||
                        !Person.getInstance().getPhone().equals(binding.personInfoPhone.getText().toString())) {
                    Person.getInstance().setName(binding.personInfoName.getText().toString());
                    Person.getInstance().setLastName(binding.personInfoLastName.getText().toString());
                    Person.getInstance().setCodeName(binding.personInfoCodeName.getText().toString());
                    Person.getInstance().setEmail(binding.personInfoEmail.getText().toString());
                    Person.getInstance().setPhone(binding.personInfoPhone.getText().toString());

                    ServiceLocator.GetInstance().personNetworkLogic.postDriverLogic((MainActivity) getActivity());
                }
            }
        });

        return binding.getRoot();
    }
}
