package ru.realityfamily.takso_mobile.Fragment.Order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import ru.realityfamily.takso_mobile.Fragment.MyFragment;
import ru.realityfamily.takso_mobile.R;

public class OrderCreate extends MyFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.order_create, container, false);

        return v;
    }
}
