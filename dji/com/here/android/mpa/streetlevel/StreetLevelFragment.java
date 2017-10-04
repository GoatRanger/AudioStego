package com.here.android.mpa.streetlevel;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.ei;

@TargetApi(11)
@HybridPlus
public class StreetLevelFragment extends Fragment {
    private ei a = new ei();

    public StreetLevelFragment() {
        setRetainInstance(true);
    }

    public void init(OnEngineInitListener onEngineInitListener) {
        this.a.a(getActivity(), onEngineInitListener);
    }

    public void init(Context context, OnEngineInitListener onEngineInitListener) {
        this.a.a(context, onEngineInitListener);
    }

    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        this.a.a(attributeSet);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.a.a(getActivity(), viewGroup, bundle);
    }

    public void onPause() {
        this.a.a();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.a.b();
    }

    public void onDestroyView() {
        this.a.c();
        super.onDestroyView();
    }

    public void onDestroy() {
        this.a = null;
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.a.a(bundle);
        super.onSaveInstanceState(bundle);
    }

    public Rect getCopyrightBoundaryRect() {
        return this.a.d();
    }

    public void setCopyrightBoundaryRect(Rect rect) {
        this.a.a(rect);
    }

    public int getCopyrightLogoWidth() {
        return this.a.f();
    }

    public int getCopyrightLogoHeight() {
        return this.a.g();
    }

    public int getCopyrightMargin() {
        return this.a.e();
    }

    public void setCopyrightMargin(int i) {
        this.a.a(i);
    }

    public StreetLevelModel getStreetLevelModel() {
        return this.a.h();
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.a.a(onTouchListener);
    }

    public StreetLevelGesture getStreetLevelGesture() {
        return this.a.i();
    }

    public void setBlankStreetLevelImageVisible(boolean z) {
        this.a.a(z);
    }

    public void getScreenCapture(OnScreenCaptureListener onScreenCaptureListener) {
        this.a.a(onScreenCaptureListener);
    }
}
