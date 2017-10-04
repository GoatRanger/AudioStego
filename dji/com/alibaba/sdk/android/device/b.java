package com.alibaba.sdk.android.device;

import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import com.alibaba.sdk.android.b.a;
import com.tencent.android.tpush.common.Constants;

final class b implements Runnable {
    b() {
    }

    public final void run() {
        try {
            Editor edit = a.a.getSharedPreferences("onesdk_device", 0).edit();
            edit.putString(Constants.FLAG_DEVICE_ID, DeviceInfo.deviceId);
            if (VERSION.SDK_INT >= 9) {
                edit.apply();
            } else {
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }
}
