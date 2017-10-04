package dji.midware.data.manager.P3;

import android.content.Context;
import android.util.Log;
import com.dji.frame.c.f;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.midware.R;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.data.params.P3.ParamInfoBean;
import dji.thirdparty.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.List;

public class e {
    private static HashMap<String, ParamInfo> a = new HashMap();
    private static Context b;
    private static e c = null;

    protected static void a(Context context) {
        b = context;
        getInstance();
    }

    public static e getInstance() {
        if (c == null) {
            c = new e();
        }
        return c;
    }

    public static ParamInfo read(String str) {
        return (ParamInfo) a.get(str);
    }

    public static Number valueOf(String str) {
        return ((ParamInfo) a.get(str)).value;
    }

    public static void write(String str, Number number) {
        ParamInfo paramInfo = (ParamInfo) a.get(str);
        paramInfo.value = number;
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

    public e() {
        b();
    }

    private void b() {
        new Thread(new Runnable(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a(f.a(e.b, R.raw.gimbal_param_infos));
            }
        }).start();
    }

    private void a(String str) {
        try {
            List<ParamInfoBean> a = h.a(str, new TypeToken<List<ParamInfoBean>>(this) {
                final /* synthetic */ e a;

                {
                    this.a = r1;
                }
            });
            if (a != null && a.size() > 0) {
                for (ParamInfoBean paramInfo : a) {
                    ParamInfo paramInfo2 = paramInfo.getParamInfo();
                    if (paramInfo2 != null) {
                        a.put(paramInfo2.name, paramInfo2);
                    }
                }
                Log.d("DJIGimbalParamInfoManager", "readToMemory size = " + a.size());
            }
        } catch (Exception e) {
            DJILogHelper.getInstance().LOGE("gsonerror", "gimbal read to mem" + e.toString());
        }
    }
}
