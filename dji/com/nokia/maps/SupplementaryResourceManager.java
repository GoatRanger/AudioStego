package com.nokia.maps;

import android.content.Context;
import com.nokia.maps.annotation.Online;
import java.io.File;
import java.lang.reflect.Method;

@Online
class SupplementaryResourceManager {
    private static final String a = SupplementaryResourceManager.class.getSimpleName();
    private static final String[] b = new String[]{"NanumGothicFontPkg", "PureThaiFontPkg", "PureIndicSouthFontPkg", "PureArabicFontPkg", "PureChineseFontPkg", "LohitIndicFontPkg"};

    private static native boolean deployLohitIndicFontPkgToDisk(String str, boolean z);

    private static native boolean deployNanumGothicFontPkgToDisk(String str, boolean z);

    private static native boolean deployPureArabicFontPkgToDisk(String str, boolean z);

    private static native boolean deployPureChineseFontPkgToDisk(String str, boolean z);

    private static native boolean deployPureIndicSouthFontPkgToDisk(String str, boolean z);

    private static native boolean deployPureThaiFontPkgToDisk(String str, boolean z);

    SupplementaryResourceManager() {
    }

    static boolean a(Context context, String str) {
        bj.a(a, "DestDir=" + str, new Object[0]);
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            return false;
        }
        String[] strArr = b;
        int length = strArr.length;
        int i = 0;
        boolean z = false;
        while (i < length) {
            boolean booleanValue;
            if (bf.a(strArr[i]).booleanValue()) {
                Object format = String.format("deploy%sToDisk", new Object[]{strArr[i]});
                try {
                    Method declaredMethod = SupplementaryResourceManager.class.getDeclaredMethod(format, new Class[]{String.class, Boolean.TYPE});
                    if (declaredMethod != null) {
                        try {
                            format = declaredMethod.invoke(null, new Object[]{str, Boolean.valueOf(true)});
                            if (((Boolean) format) != null) {
                                booleanValue = ((Boolean) format).booleanValue() | z;
                            }
                        } catch (Exception e) {
                            bj.c(a, "Unable to invoke method: %s.  Exception: %s", new Object[]{format, e.getLocalizedMessage()});
                            booleanValue = z;
                        }
                    }
                    booleanValue = z;
                } catch (Exception e2) {
                    bj.c(a, "Unable to find method: %s.  Exception: %s", new Object[]{format, e2.getLocalizedMessage()});
                    booleanValue = z;
                }
            } else {
                bj.b(a, "Library %s failed to load.", new Object[]{strArr[i]});
                booleanValue = z;
            }
            i++;
            z = booleanValue;
        }
        return z;
    }
}
