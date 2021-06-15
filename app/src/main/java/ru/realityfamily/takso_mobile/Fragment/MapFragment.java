package ru.realityfamily.takso_mobile.Fragment;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKit;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.map.CameraListener;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.CameraUpdateReason;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.i18n.I18nManagerFactory;
import com.yandex.runtime.image.ImageProvider;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Locale;

import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Logic.Network.LineNetworkLogic;
import ru.realityfamily.takso_mobile.MainActivity;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.ViewElements.MapBottomSheetBehavior;
import ru.realityfamily.takso_mobile.databinding.MapFragmentBinding;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MapFragment extends MyFragment {
    private MapFragmentBinding binding;

    private static MapView mapInstance;
    private static UserLocationLayer userLocationLayerInstance;

    private UserLocationObjectListener objectListener;
    private CameraListener cameraListener;

    public MapFragment() {
        this.Title = "Заказы";
    }

    public static MapView getMapInstance() {
        return mapInstance;
    }
    public static UserLocationLayer getUserLocationLayer() {
        return userLocationLayerInstance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        MapKitInit();

        binding = MapFragmentBinding.inflate(inflater, null, false);

        PutMapToUser();

        MainActivity ma = ((MainActivity) getActivity());
        ma.changeBackButton(MainActivity.BackButtonStatus.person);

        MapBottomSheetBehavior.setInstance(binding.bottomSheet.bottomSheetContainer);
        MapBottomSheetBehavior.getInstance().getBottomSheetBehavior().setHideable(true);

        ma.setAppBarExtrBtn(R.drawable.ic_wireless_symbol, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userLocationLayerInstance.cameraPosition() != null) {
                    ServiceLocator.GetInstance().lineNetworkLogic.addLine(ma, getContext());
                } else {
                    Toast.makeText(getContext(), "Дождитесь обнаружения вашей геопозиции", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }


    @Override
    public void onStart() {
        super.onStart();
        binding.mapView.onStart();
        MapKitFactory.getInstance().onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mapInstance = null;
    }

    @Override
    public void onStop() {
        super.onStop();
        binding.mapView.onStop();
        MapKitFactory.getInstance().onStop();
    }

    private void MapKitInit() {
        try {
            MapKitFactory.getInstance();
        } catch (UnsatisfiedLinkError e) {
            MapKitFactory.setApiKey(getString(R.string.key));
            MapKitFactory.setLocale("ru_RU");
            MapKitFactory.initialize(getContext());
        }

        CheckPermissions();
    }

    private void CheckPermissions() {
        int permissionLocation = getActivity().checkSelfPermission(ACCESS_FINE_LOCATION);
        if (permissionLocation != PERMISSION_GRANTED) {
            getActivity().requestPermissions(new String[]{ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void initMap() {
        MapKit mapKit = MapKitFactory.getInstance();
        userLocationLayerInstance = mapKit.createUserLocationLayer(binding.mapView.getMapWindow());

        mapInstance = binding.mapView;

        objectListener = new UserLocationObjectListener() {
            @Override
            public void onObjectAdded(@NonNull @NotNull UserLocationView userLocationView) {
                userLocationLayerInstance.setAnchor(
                        new PointF(
                                (float) (binding.mapView.getWidth() * 0.5),
                                (float) (binding.mapView.getHeight() * 0.5)
                        ),
                        new PointF(
                                (float) (binding.mapView.getWidth() * 0.5),
                                (float) (binding.mapView.getHeight() * 0.83)
                        )
                );

                userLocationView.getArrow().setIcon(ImageProvider.fromResource(
                        getContext(), R.drawable.user_arrow)
                );

                userLocationView.getAccuracyCircle().setFillColor(Color.BLUE & 0x99ffffff);
            }

            @Override
            public void onObjectRemoved(@NonNull @NotNull UserLocationView userLocationView) {}
            @Override
            public void onObjectUpdated(@NonNull @NotNull UserLocationView userLocationView, @NonNull @NotNull ObjectEvent objectEvent){}
        };

        cameraListener = (map, cameraPosition, cameraUpdateReason, b) -> {
            if (b) {
                binding.mapView.getMap().move(
                        new CameraPosition(cameraPosition.getTarget(), 14.0f, 0.0f, 0.0f),
                        new Animation(Animation.Type.SMOOTH, 2),
                        null
                );
            }
        };
    }

    private void PutMapToUser() {
        initMap();

        userLocationLayerInstance.setVisible(true);

        userLocationLayerInstance.setObjectListener(objectListener);
        binding.mapView.getMap().addCameraListener(cameraListener);
    }
}
