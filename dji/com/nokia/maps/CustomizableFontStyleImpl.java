package com.nokia.maps;

import com.here.android.mpa.mapping.customization.a;
import com.nokia.maps.annotation.HybridPlusNative;

public class CustomizableFontStyleImpl extends BaseNativeObject {
    private static k<a, CustomizableFontStyleImpl> a;
    private static am<a, CustomizableFontStyleImpl> b;

    private native void a();

    public static void a(k<a, CustomizableFontStyleImpl> kVar, am<a, CustomizableFontStyleImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    static CustomizableFontStyleImpl a(a aVar) {
        if (a != null) {
            return (CustomizableFontStyleImpl) a.a(aVar);
        }
        return null;
    }

    static a a(CustomizableFontStyleImpl customizableFontStyleImpl) {
        if (customizableFontStyleImpl != null) {
            return (a) b.a(customizableFontStyleImpl);
        }
        return null;
    }

    static {
        ce.a(a.class);
    }

    @HybridPlusNative
    public CustomizableFontStyleImpl(int i) {
        this.nativeptr = i;
    }

    protected void finalize() throws Throwable {
        super.finalize();
        if (this.nativeptr != 0) {
            a();
        }
    }
}
