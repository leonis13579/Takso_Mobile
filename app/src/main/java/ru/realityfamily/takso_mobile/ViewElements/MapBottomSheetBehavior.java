package ru.realityfamily.takso_mobile.ViewElements;

import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import org.jetbrains.annotations.NotNull;

import ru.realityfamily.takso_mobile.Fragment.MapFragment;
import ru.realityfamily.takso_mobile.Fragment.MyFragment;
import ru.realityfamily.takso_mobile.databinding.BottomSheetBinding;

public class MapBottomSheetBehavior extends BottomSheetBehavior {
    private static MapBottomSheetBehavior instance;
    private BottomSheetBehavior bottomSheetBehavior;
    private FrameLayout bottomSheetContainer;

    private MapBottomSheetBehavior (FrameLayout bottomSheetContainer) {
        this.bottomSheetContainer = bottomSheetContainer;
        this.bottomSheetBehavior = MapBottomSheetBehavior.from(bottomSheetContainer);

        if (MapFragment.getMapInstance() != null) {
            bottomSheetBehavior.addBottomSheetCallback(new BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull @NotNull View bottomSheet, int newState) {
                    if (newState == BottomSheetBehavior.STATE_HIDDEN && MapFragment.getMapInstance() != null) {
                        MapFragment.getMapInstance().getMap().getMapObjects().clear();
                    }
                }

                @Override
                public void onSlide(@NonNull @NotNull View bottomSheet, float slideOffset) {

                }
            });
        }
    }

    public static MapBottomSheetBehavior getInstance() {
        if (instance != null) {
            return instance;
        } else {
            return null;
        }
    }

    public static void setInstance(FrameLayout inBottomSheetContainer) {
        instance = new MapBottomSheetBehavior(inBottomSheetContainer);
    }

    public FrameLayout getBottomSheetContainer() {
        return bottomSheetContainer;
    }

    public BottomSheetBehavior getBottomSheetBehavior() {
        return bottomSheetBehavior;
    }
}
