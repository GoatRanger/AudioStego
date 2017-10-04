package com.here.android.mpa.ar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
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
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker$OnDragListener;
import com.here.android.mpa.mapping.OnMapRenderListener;
import com.nokia.maps.ag;
import com.nokia.maps.annotation.HybridPlus;

@TargetApi(14)
@HybridPlus
public class CompositeFragment extends Fragment {
    private final ag a = new ag();

    public CompositeFragment() {
        setRetainInstance(true);
    }

    public void init(OnEngineInitListener onEngineInitListener) {
        this.a.a(getActivity(), onEngineInitListener);
    }

    public void init(Context context, OnEngineInitListener onEngineInitListener) {
        this.a.a(context, onEngineInitListener);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.a.a(getActivity(), layoutInflater, viewGroup, bundle);
    }

    @TargetApi(12)
    public void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        this.a.a(attributeSet, bundle);
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

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.a.a(onTouchListener);
    }

    public ARController getARController() {
        return this.a.l();
    }

    public int getWidth() {
        return this.a.m();
    }

    public int getHeight() {
        return this.a.n();
    }

    public void onSaveInstanceState(Bundle bundle) {
        this.a.a(bundle);
        super.onSaveInstanceState(bundle);
    }

    public void getScreenCapture(OnScreenCaptureListener onScreenCaptureListener) {
        this.a.a(onScreenCaptureListener);
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

    public void setCopyrightMargin(int i) {
        this.a.a(i);
    }

    public int getCopyrightLogoWidth() {
        return this.a.i();
    }

    public int getCopyrightLogoHeight() {
        return this.a.j();
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

    public void setMapMarkerDragListener(MapMarker$OnDragListener mapMarker$OnDragListener) {
        this.a.a(mapMarker$OnDragListener);
    }
}
