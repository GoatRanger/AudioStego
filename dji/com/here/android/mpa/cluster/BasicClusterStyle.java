package com.here.android.mpa.cluster;

import com.nokia.maps.BasicClusterStyleImpl;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class BasicClusterStyle extends ClusterStyle {
    private final BasicClusterStyleImpl a;

    public BasicClusterStyle() {
        this.a = new BasicClusterStyleImpl();
        this.m_pimpl = this.a;
    }

    public BasicClusterStyle(int i, int i2, int i3) {
        this.a = new BasicClusterStyleImpl();
        this.m_pimpl = this.a;
        this.a.setStrokeColorNative(i);
        this.a.setFillColorNative(i2);
        this.a.setFontColorNative(i3);
    }

    public BasicClusterStyle setFontColor(int i) {
        this.a.setFontColorNative(i);
        return this;
    }

    public int getFontColor() {
        return this.a.getFontColorNative();
    }

    public BasicClusterStyle setFillColor(int i) {
        this.a.setFillColorNative(i);
        return this;
    }

    public int getFillColor() {
        return this.a.getFillColorNative();
    }

    public BasicClusterStyle setStrokeColor(int i) {
        this.a.setStrokeColorNative(i);
        return this;
    }

    public int getStrokeColor() {
        return this.a.getStrokeColorNative();
    }

    public String toString() {
        return "BCS#" + (hashCode() % 1000);
    }
}
