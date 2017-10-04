package com.nokia.maps.nlp;

import android.content.Context;
import android.util.Log;
import com.nokia.maps.MapSettings;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.bf;
import java.io.File;

@Online
public class NlpResourceManager {
    private static final String a = ("nlp" + File.separator + "db" + File.separator);

    private static native boolean deployToDisk(String str, boolean z);

    public static String a() {
        return MapSettings.a() + File.separator + a;
    }

    public static boolean a(Context context) {
        return a(context, new File(a()).getAbsolutePath());
    }

    static boolean a(Context context, String str) {
        File file = new File(str);
        if (!file.exists() && !file.mkdirs()) {
            Log.e("here_nlp", "Error: " + str + " cannot be accessed");
            return false;
        } else if (bf.a("NlpResourcePkg").booleanValue()) {
            return deployToDisk(str, false);
        } else {
            Log.e("here_nlp", "Error: NlpResourcePkg is ABSENT!!!");
            return false;
        }
    }
}
