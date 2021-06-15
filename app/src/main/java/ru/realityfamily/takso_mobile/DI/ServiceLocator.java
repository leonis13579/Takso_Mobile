package ru.realityfamily.takso_mobile.DI;

import android.content.Context;

import ru.realityfamily.takso_mobile.Logic.MapOrderLogic;
import ru.realityfamily.takso_mobile.Logic.Network.AuthNetworkLogic;
import ru.realityfamily.takso_mobile.Logic.Network.CarNetworkLogic;
import ru.realityfamily.takso_mobile.Logic.Network.LineNetworkLogic;
import ru.realityfamily.takso_mobile.Logic.Network.OrderNetworkLogic;
import ru.realityfamily.takso_mobile.Logic.Network.PersonNetworkLogic;
import ru.realityfamily.takso_mobile.Logic.Notify;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.Network.MyRetrofit;

public class ServiceLocator {
    public MapOrderLogic mapOrderLogic;
    public AuthNetworkLogic authNetworkLogic;
    public CarNetworkLogic carNetworkLogic;
    public LineNetworkLogic lineNetworkLogic;
    public OrderNetworkLogic orderNetworkLogic;
    public PersonNetworkLogic personNetworkLogic;
    public Notify notify;
    public MyRetrofit myRetrofit;
    public MainActivity mainActivity;

    private static ServiceLocator instance;

    private ServiceLocator(Context context, MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        mapOrderLogic = new MapOrderLogic(context);
        authNetworkLogic = new AuthNetworkLogic();
        carNetworkLogic = new CarNetworkLogic();
        lineNetworkLogic = new LineNetworkLogic();
        orderNetworkLogic = new OrderNetworkLogic();
        personNetworkLogic = new PersonNetworkLogic();
        notify = new Notify();
        myRetrofit = new MyRetrofit();
    }

    public static void init(Context context, MainActivity mainActivity) {
        instance = new ServiceLocator(context, mainActivity);
    }

    public static ServiceLocator GetInstance() {
        return instance;
    }
}
