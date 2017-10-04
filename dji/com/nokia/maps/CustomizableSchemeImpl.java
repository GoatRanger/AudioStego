package com.nokia.maps;

import android.graphics.Color;
import com.here.android.mpa.mapping.customization.CustomizableScheme;
import com.here.android.mpa.mapping.customization.CustomizableScheme.ErrorCode;
import com.here.android.mpa.mapping.customization.SchemeColorProperty;
import com.here.android.mpa.mapping.customization.SchemeFloatProperty;
import com.here.android.mpa.mapping.customization.SchemeIntegerProperty;
import com.here.android.mpa.mapping.customization.ZoomRange;
import com.here.android.mpa.mapping.customization.a;
import com.here.android.mpa.mapping.customization.b;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;

@HybridPlus
public class CustomizableSchemeImpl extends BaseNativeObject {
    private static k<CustomizableScheme, CustomizableSchemeImpl> b;
    private static am<CustomizableScheme, CustomizableSchemeImpl> c;
    private final p a = l.a();

    private native void deleteNative();

    private native int[] getColorVariableValueNative(String str, double d);

    private native float getFloatValue(String str, double d);

    private native CustomizableFontStyleImpl getFontStyleNative(String str, double d);

    private native int getIntegeralue(String str, double d);

    private native int setColorVariableValueNative(String str, int i, int i2, int i3, int i4, double d, double d2);

    private native int setFloatNative(String str, float f, double d, double d2);

    private native int setFontStyleNative(String str, CustomizableFontStyleImpl customizableFontStyleImpl, double d, double d2);

    private native int setIntegerNative(String str, int i, double d, double d2);

    public native String getNameNative();

    public native boolean isValidNative();

    public static void a(k<CustomizableScheme, CustomizableSchemeImpl> kVar, am<CustomizableScheme, CustomizableSchemeImpl> amVar) {
        b = kVar;
        c = amVar;
    }

    static CustomizableScheme a(CustomizableSchemeImpl customizableSchemeImpl) {
        if (customizableSchemeImpl != null) {
            return (CustomizableScheme) c.a(customizableSchemeImpl);
        }
        return null;
    }

    static {
        ce.a(CustomizableScheme.class);
    }

    @HybridPlusNative
    public CustomizableSchemeImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.nativeptr != 0) {
            deleteNative();
        }
    }

    public ErrorCode a(SchemeColorProperty schemeColorProperty, int i, ZoomRange zoomRange) {
        if (schemeColorProperty == null || !a(zoomRange)) {
            return ErrorCode.ERROR_INVALID_PARAMETERS;
        }
        ErrorCode code = ErrorCode.getCode(setColorVariableValueNative(schemeColorProperty.getName(), Color.red(i), Color.green(i), Color.blue(i), Color.alpha(i), zoomRange.getMin(), zoomRange.getMax()));
        if (code != ErrorCode.ERROR_NONE) {
            return code;
        }
        this.a.b("setvariable");
        return code;
    }

    public int a(SchemeColorProperty schemeColorProperty, double d) {
        dy.a((Object) schemeColorProperty, "SchemeColorProperty can not be null");
        if (a(d)) {
            int[] colorVariableValueNative = getColorVariableValueNative(schemeColorProperty.getName(), d);
            if (colorVariableValueNative == null || colorVariableValueNative.length == 0) {
                return -1;
            }
            return Color.argb(colorVariableValueNative[0], colorVariableValueNative[1], colorVariableValueNative[2], colorVariableValueNative[3]);
        }
        throw new IllegalArgumentException("Zoom level is not valid");
    }

    public ErrorCode a(b bVar, a aVar, ZoomRange zoomRange) {
        if (bVar == null || aVar == null || !a(zoomRange)) {
            return ErrorCode.ERROR_INVALID_PARAMETERS;
        }
        ErrorCode code = ErrorCode.getCode(setFontStyleNative(bVar.a(), CustomizableFontStyleImpl.a(aVar), zoomRange.getMin(), zoomRange.getMax()));
        if (code != ErrorCode.ERROR_NONE) {
            return code;
        }
        this.a.b("setvariable");
        return code;
    }

    public a a(b bVar, double d) {
        dy.a((Object) bVar, "SchemeFontStyleProperty can not be null");
        if (a(d)) {
            return CustomizableFontStyleImpl.a(getFontStyleNative(bVar.a(), d));
        }
        throw new IllegalArgumentException("Zoom level is not valid");
    }

    public ErrorCode a(SchemeFloatProperty schemeFloatProperty, float f, ZoomRange zoomRange) {
        if (schemeFloatProperty == null || !a(zoomRange)) {
            return ErrorCode.ERROR_INVALID_PARAMETERS;
        }
        ErrorCode code = ErrorCode.getCode(setFloatNative(schemeFloatProperty.getName(), f, zoomRange.getMin(), zoomRange.getMax()));
        if (code != ErrorCode.ERROR_NONE) {
            return code;
        }
        this.a.b("setvariable");
        return code;
    }

    public float a(SchemeFloatProperty schemeFloatProperty, double d) {
        dy.a((Object) schemeFloatProperty, "SchemeFloatProperty can not be null");
        if (a(d)) {
            return getFloatValue(schemeFloatProperty.getName(), d);
        }
        throw new IllegalArgumentException("Zoom level is not valid");
    }

    public ErrorCode a(SchemeIntegerProperty schemeIntegerProperty, int i, ZoomRange zoomRange) {
        if (schemeIntegerProperty == null || !a(zoomRange)) {
            return ErrorCode.ERROR_INVALID_PARAMETERS;
        }
        ErrorCode code = ErrorCode.getCode(setIntegerNative(schemeIntegerProperty.getName(), i, zoomRange.getMin(), zoomRange.getMax()));
        if (code != ErrorCode.ERROR_NONE) {
            return code;
        }
        this.a.b("setvariable");
        return code;
    }

    public int a(SchemeIntegerProperty schemeIntegerProperty, double d) {
        dy.a((Object) schemeIntegerProperty, "SchemeIntegerProperty can not be null");
        if (a(d)) {
            return getIntegeralue(schemeIntegerProperty.getName(), d);
        }
        throw new IllegalArgumentException("Zoom level is not valid");
    }

    public boolean a(ZoomRange zoomRange) {
        if (zoomRange == null || zoomRange.getMin() < 0.0d || zoomRange.getMax() > 20.0d) {
            return false;
        }
        return true;
    }

    public boolean a(double d) {
        if (d < 0.0d || d > 20.0d) {
            return false;
        }
        return true;
    }
}
