package com.nokia.maps;

import android.graphics.Color;
import com.here.android.mpa.ar.LineAttributes;
import com.here.android.mpa.mapping.MapPolyline.CapStyle;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public class LineAttributesImpl extends BaseNativeObject {
    private static k<LineAttributes, LineAttributesImpl> a;
    private static am<LineAttributes, LineAttributesImpl> b;

    private native void createNative();

    private native void createNative(int i, int i2, int i3, int i4, int i5, int i6, boolean z);

    private native int getAlphaNative();

    private native int getBlueNative();

    private native int getGreenNative();

    private native int getRedNative();

    private native void setDashPrimaryLengthNative(int i);

    private native void setDashSecondaryLengthNative(int i);

    private native void setLineColorNative(int i, int i2, int i3, int i4);

    public native void enablePerspective(boolean z);

    public native int getDashPrimaryLengthNative();

    public native int getDashSecondaryLengthNative();

    public native int getLineCapStyleNative();

    public native int getLineWidthNative();

    public native boolean isDashEnabledNative();

    public native boolean isPerspectiveEnable();

    public native void setDashEnabledNative(boolean z);

    public native boolean setLineCapStyleNative(int i);

    public native void setLineWidthNative(int i);

    static LineAttributesImpl a(LineAttributes lineAttributes) {
        if (a != null) {
            return (LineAttributesImpl) a.a(lineAttributes);
        }
        return null;
    }

    static LineAttributes a(LineAttributesImpl lineAttributesImpl) {
        if (lineAttributesImpl != null) {
            return (LineAttributes) b.a(lineAttributesImpl);
        }
        return null;
    }

    static {
        ce.a(LineAttributes.class);
    }

    public static void a(k<LineAttributes, LineAttributesImpl> kVar, am<LineAttributes, LineAttributesImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    public LineAttributesImpl() {
        createNative();
    }

    @OnlineNative
    private LineAttributesImpl(int i) {
        this.nativeptr = i;
    }

    public LineAttributesImpl(int i, int i2, CapStyle capStyle, boolean z) {
        createNative(i, Color.red(i2), Color.green(i2), Color.blue(i2), Color.alpha(i2), capStyle.value(), z);
    }

    public void a(int i) {
        setLineColorNative(Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i));
    }

    public int a() {
        return Color.argb(getAlphaNative(), getRedNative(), getGreenNative(), getBlueNative());
    }

    public void b(int i) {
        if (i < 0 || i > 100) {
            throw new IllegalArgumentException("Line width is not within the supported range [0..100].");
        }
        setLineWidthNative(i);
    }

    public void c(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Length of a dash segment must be > 0");
        }
        setDashPrimaryLengthNative(i);
    }

    public void d(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("Length of a dash segment must be > 0");
        }
        setDashSecondaryLengthNative(i);
    }
}
