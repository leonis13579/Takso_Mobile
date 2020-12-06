package ru.realityfamily.takso_mobile.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.R;

public class MapFragment extends MyFragment {

    public MapFragment(String Title) {
        this.Title = Title;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_fragment, container, false);

        MainActivity ma = ((MainActivity) getActivity());
        ma.changeBackButton(MainActivity.BackButtonStatus.person);

        return v;
    }
}
