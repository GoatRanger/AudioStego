package dji.phone.bluetooth;

import dji.pilot.fpv.R;

public enum b {
    NOTCONNECTED(R.string.lp_ble_device_disconnected),
    CONNECTING(R.string.lp_ble_device_connecting),
    CONNECTED(R.string.lp_ble_device_connected),
    ISSUE_CONNECTED(R.string.lp_ble_device_connected_issue);
    
    public int e;

    private b(int i) {
        this.e = i;
    }
}
