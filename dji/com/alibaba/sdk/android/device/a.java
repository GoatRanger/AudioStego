package com.alibaba.sdk.android.device;

import com.tencent.android.tpush.common.Constants;

final class a implements Runnable {
    a() {
    }

    public final void run() {
        try {
            DeviceInfo.deviceId = com.alibaba.sdk.android.b.a.a.getSharedPreferences("onesdk_device", 0).getString(Constants.FLAG_DEVICE_ID, null);
        } catch (Throwable th) {
        }
    }
}
