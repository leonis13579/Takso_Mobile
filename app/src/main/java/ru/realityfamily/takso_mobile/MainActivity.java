package ru.realityfamily.takso_mobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.AuthFragment;
import ru.realityfamily.takso_mobile.Fragment.MyFragment;
import ru.realityfamily.takso_mobile.Fragment.PersonFragment;
import ru.realityfamily.takso_mobile.Logic.Network.LineNetworkLogic;
import ru.realityfamily.takso_mobile.Logic.Network.PersonNetworkLogic;
import ru.realityfamily.takso_mobile.Models.AuthData;
import ru.realityfamily.takso_mobile.Models.Car;
import ru.realityfamily.takso_mobile.Models.Person;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;
import ru.realityfamily.takso_mobile.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    MainActivity mainActivity = this;

    BackButtonStatus backButtonStatus;
    public enum BackButtonStatus{
        back,
        person
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ServiceLocator.init(this, this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getWindow().setStatusBarColor(getColor(R.color.backgroundSecond));

        changeBackButton(BackButtonStatus.back);

        AuthFragment af = new AuthFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContent, af).addToBackStack(af.Title).commit();
        binding.appBarTitle.setText(af.Title);
    }

    public void changeFragment(MyFragment fragment) {
        binding.appBarTitle.setText(fragment.Title);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContent, fragment).addToBackStack(fragment.Title).commit();
        getSupportFragmentManager().executePendingTransactions();

        if (backButtonStatus == BackButtonStatus.back) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                binding.appBarBackBtn.setVisibility(View.VISIBLE);
            } else {
                binding.appBarBackBtn.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void backFragment() {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 1) {
            fm.popBackStack();
            String Title = fm.getBackStackEntryAt(fm.getBackStackEntryCount() - 2).getName();
            binding.appBarTitle.setText(Title);
        }

        if (fm.getBackStackEntryCount() < 3 && backButtonStatus == BackButtonStatus.back) {
            binding.appBarBackBtn.setVisibility(View.INVISIBLE);
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
                binding.appBarBackBtn.setImageResource(R.drawable.back_arrow);
                binding.appBarBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backFragment();
                    }
                });

                if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                    binding.appBarBackBtn.setVisibility(View.VISIBLE);
                }
                break;

            case person:
                binding.appBarBackBtn.setImageResource(R.drawable.person);
                binding.appBarBackBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ServiceLocator.GetInstance().personNetworkLogic.getDriverInfo(mainActivity);
                    }
                });

                binding.appBarBackBtn.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void setAppBarExtrBtn(int Visibility) {
        binding.appBarExtrBtn.setVisibility(Visibility);
    }

    public void setAppBarExtrBtn(int DrawableResource, View.OnClickListener Listener) {
        binding.appBarExtrBtn.setVisibility(View.VISIBLE);
        binding.appBarExtrBtn.setImageResource(DrawableResource);
        binding.appBarExtrBtn.setOnClickListener(Listener);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
                backFragment();
                return false;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ServiceLocator.GetInstance().lineNetworkLogic.closeLine(mainActivity, this, null);
    }
}