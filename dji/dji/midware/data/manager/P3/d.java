package dji.midware.data.manager.P3;

import android.content.Context;
import android.util.Log;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.data.params.P3.ParamInfoBean;
import java.util.HashMap;
import java.util.List;

public class d {
    private static HashMap<String, ParamInfo> a = new HashMap();
    private static Context b;
    private static d c = null;

    protected static void a(Context context) {
        b = context;
        getInstance();
    }

    public static d getInstance() {
        if (c == null) {
            c = new d();
        }
        return c;
    }

    public static boolean isNew() {
        return true;
    }

    public static boolean isNewForOsd() {
        return DataOsdGetPushCommon.getInstance().getFlycVersion() >= 1;
    }

    public static ParamInfo read(String str) {
        return (ParamInfo) a.get(str);
    }

    public static ParamInfo readByIndex(int i) {
        for (String str : a.keySet()) {
            ParamInfo paramInfo = (ParamInfo) a.get(str);
            if (paramInfo.index == i) {
                return paramInfo;
            }
        }
        return null;
    }

    public static ParamInfo readByHash(long j) {
        for (String str : a.keySet()) {
            ParamInfo paramInfo = (ParamInfo) a.get(str);
            if (paramInfo.hash == j) {
                return paramInfo;
            }
        }
        return null;
    }

    public static String getNameByIndex(int i) {
        for (String str : a.keySet()) {
            ParamInfo paramInfo = (ParamInfo) a.get(str);
            if (paramInfo.index == i) {
                return paramInfo.name;
            }
        }
        return "";
    }

    public static String getNameByHash(long j) {
        for (String str : a.keySet()) {
            ParamInfo paramInfo = (ParamInfo) a.get(str);
            if (paramInfo.hash == j) {
                return paramInfo.name;
            }
        }
        return "";
    }

    public static Number valueOf(String str) {
        return ((ParamInfo) a.get(str)).value;
    }

    public static void write(String str, Number number) {
        ParamInfo paramInfo = (ParamInfo) a.get(str);
        paramInfo.value = number;
        a.put(str, paramInfo);
    }

    public static void writeNewParamInfo(String str, ParamInfo paramInfo) {
        a.put(str, paramInfo);
    }

    public static void writeSetValue(String str, String str2) {
        ParamInfo paramInfo = (ParamInfo) a.get(str);
        paramInfo.setvalue = ((ParamInfo) a.get(str2)).value;
        a.put(str, paramInfo);
    }

    public static void writeSetValue(String str, Number number) {
        ParamInfo paramInfo = (ParamInfo) a.get(str);
        paramInfo.setvalue = number;
        a.put(str, paramInfo);
    }

    public d() {
        b();
    }

    private void b() {
        new Thread(new 1(this)).start();
    }

    private void a(String str) {
        try {
            List<ParamInfoBean> a = h.a(str, new 2(this));
            if (a != null && a.size() > 0) {
                for (ParamInfoBean paramInfo : a) {
                    ParamInfo paramInfo2 = paramInfo.getParamInfo();
                    if (paramInfo2 != null) {
                        a.put(paramInfo2.name, paramInfo2);
                    }
                }
                Log.d("DJIFlycParamInfoManager", "readToMemory size = " + a.size());
            }
        } catch (Exception e) {
            DJILogHelper.getInstance().LOGE("gsonerror", "flyc read to mem" + e.toString());
        }
    }
}
