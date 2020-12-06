package ru.realityfamily.takso_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import ru.realityfamily.takso_mobile.Fragment.AuthFragment;
import ru.realityfamily.takso_mobile.Fragment.MyFragment;
import ru.realityfamily.takso_mobile.Fragment.PersonFragment;
import ru.realityfamily.takso_mobile.Models.AuthData;

public class MainActivity extends AppCompatActivity {

    ImageView appBarBackBtn;
    ImageView appBarExtrBtn;
    TextView appBarTitle;

    public static AuthData authData;

    BackButtonStatus backButtonStatus;
    public enum BackButtonStatus{
        back,
        person
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appBarBackBtn = findViewById(R.id.appBarBackBtn);
        appBarExtrBtn = findViewById(R.id.appBarExtrBtn);
        appBarTitle = findViewById(R.id.appBarTitle);

        changeBackButton(BackButtonStatus.back);

        AuthFragment af = new AuthFragment(getString(R.string.auth));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContent, af).addToBackStack(af.Title).commit();
        appBarTitle.setText(af.Title);
    }

    public void changeFragment(MyFragment fragment) {
        appBarTitle.setText(fragment.Title);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContent, fragment).addToBackStack(fragment.Title).commit();
        getSupportFragmentManager().executePendingTransactions();

        if (backButtonStatus == BackButtonStatus.back) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                appBarBackBtn.setVisibility(View.VISIBLE);
            } else {
                appBarBackBtn.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void backFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
            String Title = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 2).getName();
            appBarTitle.setText(Title);
        }

        if (fm.getBackStackEntryCount() < 3 && backButtonStatus == BackButtonStatus.back) {
            appBarBackBtn.setVisibility(View.INVISIBLE);
        }
    }

    public MainActivity clearHistory() {
        getSupportFragmentManager().popBackStack(null ,FragmentManager.POP_BACK_STACK_INCLUSIVE);

        return this;
    }

    public void changeBackButton(BackButtonStatus backButtonStatus) {
        this.backButtonStatus = backButtonStatus;
        switch (backButtonStatus) {
            case back:
                appBarBackBtn.setImageResource(R.drawable.back_arrow);
                appBarBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backFragment();
                    }
                });

                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    appBarBackBtn.setVisibility(View.VISIBLE);
                }
                break;

            case person:
                appBarBackBtn.setImageResource(R.drawable.person);
                appBarBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changeFragment(new PersonFragment("Личный кабинет"));
                    }
                });

                appBarBackBtn.setVisibility(View.VISIBLE);
                break;
        }

    }
}