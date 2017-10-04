package dji.midware.util;

import android.util.Log;
import dji.midware.data.a.a.a;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.Data1860GetPushCheckStatus;
import dji.midware.data.model.P3.Data2100GetPushCheckStatus;
import dji.midware.data.model.P3.DataBatteryGetPushCheckStatus;
import dji.midware.data.model.P3.DataCenterGetPushCheckStatus;
import dji.midware.data.model.P3.DataDm368_gGetPushCheckStatus;
import dji.midware.data.model.P3.DataFlycGetPushCheckStatus;
import dji.midware.data.model.P3.DataGimbalGetPushCheckStatus;
import dji.midware.data.model.P3.DataOfdmGetPushCheckStatus;

public class k {
    public static a a(a aVar) {
        if (aVar == null || aVar.p.length < 2) {
            return null;
        }
        int i = aVar.p[0] & 31;
        int i2 = aVar.p[0] >> 5;
        byte b = aVar.p[1];
        Object obj = new byte[b];
        System.arraycopy(aVar.p, 2, obj, 0, b);
        aVar.p = obj;
        if (i == DeviceType.CAMERA.value()) {
            DataGimbalGetPushCheckStatus.getInstance().outerSetPushRecPack(aVar);
        } else if (i == DeviceType.FLYC.value()) {
            DataFlycGetPushCheckStatus.getInstance().outerSetPushRecPack(aVar);
        } else if (i == DeviceType.GIMBAL.value()) {
            DataGimbalGetPushCheckStatus.getInstance().outerSetPushRecPack(aVar);
        } else if (i == DeviceType.CENTER.value()) {
            DataCenterGetPushCheckStatus.getInstance().outerSetPushRecPack(aVar);
        } else if (i == DeviceType.RC.value()) {
            Log.i("NewCheckStatusHelper", "into senderType: " + i);
        } else if (i == DeviceType.OFDM.value()) {
            DataOfdmGetPushCheckStatus.getInstance().outerSetPushRecPack(aVar);
        } else if (i == DeviceType.BATTERY.value()) {
            DataBatteryGetPushCheckStatus.getInstance().outerSetPushRecPack(aVar);
        } else if (i == DeviceType.DM368_G.value()) {
            DataDm368_gGetPushCheckStatus.getInstance().outerSetPushRecPack(aVar);
        } else if (i == DeviceType.OSD.value()) {
            return aVar;
        } else {
            if (i == DeviceType.TRANSFORM_G.value()) {
                DataDm368_gGetPushCheckStatus.getInstance().outerSetPushRecPack(aVar);
            } else if (i == DeviceType.DM368.value()) {
                if (i2 == 0) {
                    Data2100GetPushCheckStatus.getInstance().outerSetPushRecPack(aVar);
                } else if (i2 == 1) {
                    Data1860GetPushCheckStatus.getInstance().outerSetPushRecPack(aVar);
                }
            }
        }
        return null;
    }
}
