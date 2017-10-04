package com.here.android.mpa.mapping;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.here.android.mpa.common.CopyrightLogoPosition;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewRect;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.bn;

@Online
@TargetApi(11)
public class MapFragment extends Fragment {
    private final bn a = new bn();

    public MapFragment() {
        setRetainInstance(true);
    }

    public void init(OnEngineInitListener onEngineInitListener) {
        this.a.a(getActivity(), onEngineInitListener);
    }

    public void init(Context context, OnEngineInitListener onEngineInitListener) {
        this.a.a(context, onEngineInitListener);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.a.a(getActivity().getApplicationContext(), layoutInflater, viewGroup, bundle);
    }

    @TargetApi(12)
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        if (VERSION.SDK_INT > 11) {
            super.onInflate(activity, attributeSet, bundle);
            this.a.a(attributeSet, bundle);
        }
    }

    public void onResume() {
        super.onResume();
        this.a.a();
    }

    public void onPause() {
        this.a.b();
        super.onPause();
    }

    public void onDestroyView() {
        this.a.c();
        super.onDestroyView();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.a.a(bundle);
        super.onSaveInstanceState(bundle);
    }

    public Map getMap() {
        return this.a.d();
    }

    public ViewRect getClipRect() {
        return this.a.e();
    }

    public void setClipRect(ViewRect viewRect, PointF pointF) {
        this.a.a(viewRect, pointF);
    }

    public void setClipRect(ViewRect viewRect) {
        this.a.a(viewRect);
    }

    public Rect getCopyrightBoundaryRect() {
        return this.a.f();
    }

    public void setCopyrightBoundaryRect(Rect rect) {
        this.a.a(rect);
    }

    public int getCopyrightMargin() {
        return this.a.g();
    }

    public int getCopyrightLogoWidth() {
        return this.a.i();
    }

    public int getCopyrightLogoHeight() {
        return this.a.j();
    }

    public void setCopyrightMargin(int i) {
        this.a.a(i);
    }

    public CopyrightLogoPosition getCopyrightLogoPosition() {
        return this.a.h();
    }

    public void setCopyrightLogoPosition(CopyrightLogoPosition copyrightLogoPosition) {
        this.a.a(copyrightLogoPosition);
    }

    public MapGesture getMapGesture() {
        return this.a.k();
    }

    public void addOnMapRenderListener(OnMapRenderListener onMapRenderListener) {
        this.a.a(onMapRenderListener);
    }

    public void removeOnMapRenderListener(OnMapRenderListener onMapRenderListener) {
        this.a.b(onMapRenderListener);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.a.a(onTouchListener);
    }

    public void setMapMarkerDragListener(MapMarker$OnDragListener mapMarker$OnDragListener) {
        this.a.a(mapMarker$OnDragListener);
    }

    public void getScreenCapture(OnScreenCaptureListener onScreenCaptureListener) {
        this.a.a(onScreenCaptureListener);
    }
}
