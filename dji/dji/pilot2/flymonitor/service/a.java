package dji.pilot2.flymonitor.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot2.flymonitor.model.response.FlyLimitAreaModel;
import dji.pilot2.flymonitor.model.response.FlyLimitAreaModel.LimitAreaModel;
import dji.pilot2.flymonitor.model.response.FlyLimitAreaModel.PositionModel;
import java.util.ArrayList;
import java.util.List;

public class a {
    public static final String a = "fly_limit_area_preference_tag";
    public static final String b = "fly_limit_config_notice_tag";
    SharedPreferences c;
    List<LimitAreaModel> d;
    Context e;

    public a(Context context) {
        this.e = context;
        this.c = context.getSharedPreferences(a, 4);
        FlyLimitAreaModel flyLimitAreaModel = (FlyLimitAreaModel) h.b(this.c.getString(a, ""), FlyLimitAreaModel.class);
        if (!(flyLimitAreaModel == null || flyLimitAreaModel.data == null)) {
            this.d = flyLimitAreaModel.data;
        }
        if (this.d == null) {
            this.d = new ArrayList();
        }
    }

    public void a(String str) {
        if (str != null && !str.equals("")) {
            FlyLimitAreaModel flyLimitAreaModel = (FlyLimitAreaModel) h.b(str, FlyLimitAreaModel.class);
            if (flyLimitAreaModel != null && flyLimitAreaModel.data != null) {
                this.d = flyLimitAreaModel.data;
                this.c.edit().putString(a, str).apply();
                DJILogHelper.getInstance().LOGI(FlyMonitorService.a, "Fly limit area updated; Size: " + flyLimitAreaModel.data.size());
            }
        }
    }

    public void a(FlyLimitAreaModel flyLimitAreaModel) {
        a(h.a((Object) flyLimitAreaModel));
    }

    public boolean a() {
        return this.c.getBoolean(b, true);
    }

    public LimitAreaModel a(PositionModel positionModel) {
        return a(positionModel.latitude, positionModel.longitude);
    }

    public LimitAreaModel a(double d, double d2) {
        float[] fArr = new float[1];
        for (LimitAreaModel limitAreaModel : this.d) {
            if (limitAreaModel.pos != null) {
                Location.distanceBetween(d, d2, limitAreaModel.pos.latitude, limitAreaModel.pos.longitude, fArr);
                if (fArr[0] <= ((float) limitAreaModel.radius)) {
                    return limitAreaModel;
                }
            }
        }
        return null;
    }
}
