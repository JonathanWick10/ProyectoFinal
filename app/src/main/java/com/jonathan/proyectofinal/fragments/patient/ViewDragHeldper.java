package com.jonathan.proyectofinal.fragments.patient;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.customview.widget.ViewDragHelper;

import com.google.android.material.card.MaterialCardView;

class ViewDragHelperCallback extends ViewDragHelper.Callback {

    @Override
    public void onViewCaptured(@NonNull View capturedChild, int activePointerId) {
        if (capturedChild instanceof MaterialCardView) {
            ((MaterialCardView)capturedChild).setDragged(true);
        }
    }

    @Override
    public void onViewReleased(@NonNull View releaseChild, float xVel, float yVel) {
        if (releaseChild instanceof MaterialCardView) {
            ((MaterialCardView)releaseChild).setDragged(false);
        }
    }

    @Override
    public boolean tryCaptureView(@NonNull View child, int pointerId) {
        return false;
    }
}