package com.here.odnp.adaptations;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class BatteryManager {
    private static final String TAG = "odnp.adaptations.BatteryManager";
    private final Context mContext;
    private final IListener mListener;
    private BroadcastReceiver mReceiver;

    public interface IListener {
        void onBatteryLevelChanged(int i);
    }

    public BatteryManager(Context context, IListener iListener) {
        if (iListener == null) {
            throw new IllegalArgumentException("listener is null");
        }
        this.mContext = context;
        this.mListener = iListener;
    }

    public void start() {
        if (this.mReceiver == null) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
            this.mReceiver = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                        BatteryManager.this.updateBatteryLevel(intent);
                    }
                }
            };
            updateBatteryLevel(this.mContext.registerReceiver(this.mReceiver, intentFilter));
        }
    }

    public void stop() {
        if (this.mReceiver != null) {
            this.mContext.unregisterReceiver(this.mReceiver);
            this.mReceiver = null;
        }
    }

    private void updateBatteryLevel(Intent intent) {
        if (intent != null) {
            int intExtra = intent.getIntExtra("level", -1);
            int intExtra2 = intent.getIntExtra("scale", -1);
            if (intExtra >= 0 && intExtra2 != 0 && intExtra2 >= intExtra) {
                this.mListener.onBatteryLevelChanged(Math.min(Math.max(0, (intExtra * 100) / intExtra2), 100));
            }
        }
    }
}
