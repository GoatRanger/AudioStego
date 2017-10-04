package dji.midware.data.config.P3;

import java.util.Locale;

public class r {
    public static String getDataModelName(String str, String str2) {
        return "dji.midware.data.model.P3.Data" + str.substring(0, 1).toUpperCase(Locale.ENGLISH) + str.substring(1).toLowerCase(Locale.ENGLISH) + str2;
    }

    public static String getDataModelNameExtra(String str, String str2) {
        return "com.dji.midware.extension.Data" + str.substring(0, 1).toUpperCase(Locale.ENGLISH) + str.substring(1).toLowerCase(Locale.ENGLISH) + str2;
    }

    public static String getDataModelNameNon(String str, String str2) {
        return "dji.midware.data.model.P3.Data" + str + str2;
    }
}
