package com.nokia.maps;

import android.content.Context;
import android.content.res.Resources;
import com.nokia.maps.annotation.Online;

@Online
public class ResourceManager {
    private static final String a = ResourceManager.class.getName();
    private static volatile boolean b = false;
    private static Object c = new Object();
    private static int[] e = new int[]{12324, 8, 12323, 8, 12322, 8, 12321, 0, 12325, 16, 12326, 8, 12338, 0, 12337, 0, 12352, 4, 12344};
    private Resources d = null;

    private static native boolean deployToDisk(String str, boolean z);

    private static native byte[] getResourceDataNative(String str);

    private static boolean a(Context context) {
        if (!b) {
            synchronized (c) {
                if (!b) {
                    if (!bf.a(context)) {
                        bj.b("ResourceManager", "Library ( stlPort ) failed to load ...", new Object[0]);
                        return false;
                    } else if (bf.a("SdkResourcePkg").booleanValue()) {
                        b = true;
                    } else {
                        bj.b("ResourceManager", "Library (SdkResourcePkg) failed to load ...", new Object[0]);
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static byte[] a(Context context, String str) {
        byte[] bArr = new byte[0];
        if (str == null || str.length() == 0 || !a(context)) {
            return bArr;
        }
        byte[] resourceDataNative = getResourceDataNative(str);
        if (resourceDataNative != null) {
            return resourceDataNative;
        }
        return bArr;
    }
}
