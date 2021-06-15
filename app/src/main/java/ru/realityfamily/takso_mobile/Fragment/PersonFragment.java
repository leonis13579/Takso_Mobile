package ru.realityfamily.takso_mobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.Person.Driver.CarsList;
import ru.realityfamily.takso_mobile.Fragment.Person.PersonInfoFragment;
import ru.realityfamily.takso_mobile.Logic.Network.LineNetworkLogic;
import ru.realityfamily.takso_mobile.Logic.Notify;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Car;
import ru.realityfamily.takso_mobile.Models.Line;
import ru.realityfamily.takso_mobile.Models.Person;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.databinding.PersonFragmentBinding;

public class PersonFragment extends MyFragment {

    public PersonFragment() {
        this.Title = "Личный кабинет";
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        PersonFragmentBinding binding = PersonFragmentBinding.inflate(inflater, container, false);

        MainActivity ma = (MainActivity) getActivity();
        ma.changeBackButton(MainActivity.BackButtonStatus.back);
        ma.setAppBarExtrBtn(View.INVISIBLE);

        binding.personInfoImage.setImageResource(R.drawable.driver);
        binding.personInfoName.setText(Person.getInstance().getLastName() + " " + Person.getInstance().getName());
        binding.personInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.changeFragment(new PersonInfoFragment());
            }
        });

        binding.personCarName.setText(Car.getInstance().getName());
        binding.personCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ma.changeFragment(new CarsList());
            }
        });

        if (!Line.getInstance().isOnLine()) {
            binding.personExit.setText(R.string.personExit);
            binding.personExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ma.clearHistory();
                    ma.changeFragment(new AuthFragment());
                }
            });
        } else {
            binding.personExit.setText("Выйти с линии");
            binding.personExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ServiceLocator.GetInstance().lineNetworkLogic.closeLine((MainActivity) getActivity(), getContext(), binding.personExit);
                }
            });
        }


        return binding.getRoot();
    }
}
