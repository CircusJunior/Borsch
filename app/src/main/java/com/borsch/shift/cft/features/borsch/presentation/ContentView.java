package com.borsch.shift.cft.features.borsch.presentation;

import com.borsch.shift.cft.features.MvpView;
import com.borsch.shift.cft.features.borsch.domain.model.Fridge;

public interface ContentView extends MvpView {
    void showFridge(Fridge fridge);

    void showError(String message);
}
