package ru.realityfamily.takso_mobile.Logic;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.RequestPoint;
import com.yandex.mapkit.RequestPointType;
import com.yandex.mapkit.directions.DirectionsFactory;
import com.yandex.mapkit.directions.driving.DrivingOptions;
import com.yandex.mapkit.directions.driving.DrivingRoute;
import com.yandex.mapkit.directions.driving.DrivingRouter;
import com.yandex.mapkit.directions.driving.DrivingSession;
import com.yandex.mapkit.directions.driving.VehicleOptions;
import com.yandex.mapkit.directions.driving.VehicleType;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.Map;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.runtime.Error;
import com.yandex.runtime.image.ImageProvider;
import com.yandex.runtime.network.NetworkError;
import com.yandex.runtime.network.RemoteError;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.realityfamily.takso_mobile.DI.ServiceLocator;
import ru.realityfamily.takso_mobile.Fragment.MapFragment;
import ru.realityfamily.takso_mobile.Fragment.Order.SingleOrder;
import ru.realityfamily.takso_mobile.Models.Order;
import ru.realityfamily.takso_mobile.R;
import ru.realityfamily.takso_mobile.ViewElements.MapBottomSheetBehavior;
import ru.realityfamily.takso_mobile.databinding.BottomSheetBinding;

public class MapOrderLogic {
    Context context;

    public MapOrderLogic (Context context) {
        this.context = context;
    }

    public void PutOrdersOnMap(List<Order> orders) {
        if (MapFragment.getMapInstance() != null) {
            MapObjectCollection mapObjects = MapFragment.getMapInstance().getMap().getMapObjects();

            mapObjects.clear();

            for (Order order : orders) {
                List<String> points = Arrays.asList(order.getPointFrom().split(", "));
                Point mapPoint = new Point(Double.parseDouble(points.get(0)) + 0.00005, Double.parseDouble(points.get(1)));
                PlacemarkMapObject placemark = mapObjects.addPlacemark(mapPoint, ImageProvider.fromResource(context, R.drawable.marker));
                placemark.setUserData(order);
                placemark.addTapListener(objectTapListener);
            }
        }
    }

    public void DriveToClient(Order order) {
        if (MapFragment.getMapInstance() != null && MapFragment.getUserLocationLayer().cameraPosition() != null) {
            DirectionsFactory.initialize(context);

            DrivingRouter drivingRouter = DirectionsFactory.getInstance().createDrivingRouter();

            Map map = MapFragment.getMapInstance().getMap();
            map.getMapObjects().clear();
            map.getMapObjects().addCollection();

            List<String> points = Arrays.asList(order.getPointFrom().split(", "));
            Point mapFromPoint = new Point(Double.parseDouble(points.get(0)) + 0.00005, Double.parseDouble(points.get(1)));
            map.getMapObjects().addPlacemark(mapFromPoint, ImageProvider.fromResource(context, R.drawable.marker));

            DrivingOptions drivingOptions = new DrivingOptions();
            VehicleOptions vehicleOptions = new VehicleOptions();

            List<RequestPoint> requestPoints = new ArrayList<>();

            requestPoints.add(new RequestPoint(
                    MapFragment.getUserLocationLayer().cameraPosition().getTarget(),
                    RequestPointType.WAYPOINT,
                    null
            ));

            requestPoints.add(new RequestPoint(
                    mapFromPoint,
                    RequestPointType.WAYPOINT,
                    null
            ));
            drivingRouter.requestRoutes(requestPoints, drivingOptions, vehicleOptions, new DrivingSession.DrivingRouteListener() {
                @Override
                public void onDrivingRoutes(@NonNull @NotNull List<DrivingRoute> list) {
                    map.getMapObjects().addPolyline(list.get(0).getGeometry());
                }

                @Override
                public void onDrivingRoutesError(@NonNull @NotNull Error error) {
                    String errorMessage = "Ошибка построения маршрута";
                    if (error instanceof RemoteError) {
                        errorMessage = "RemoteError";
                    } else if (error instanceof NetworkError) {
                        errorMessage = "Ошибка сети при построении маршрута";
                    }

                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private MapObjectTapListener objectTapListener = new MapObjectTapListener() {
        @Override
        public boolean onMapObjectTap(@NonNull @NotNull MapObject mapObject, @NonNull @NotNull Point point) {
            Order.setInstance((Order) mapObject.getUserData());

            ServiceLocator.GetInstance().mainActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(
                            BottomSheetBinding.bind(MapBottomSheetBehavior.getInstance().getBottomSheetContainer()).bottomSheetContainer.getId(),
                            new SingleOrder()
                    )
                    .commit();
            return true;
        }
    };
}
