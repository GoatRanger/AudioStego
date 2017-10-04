package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataSimulatorGetPushConnectHeartPacket extends n {
    private static DataSimulatorGetPushConnectHeartPacket instance;

    public static synchronized DataSimulatorGetPushConnectHeartPacket getInstance() {
        DataSimulatorGetPushConnectHeartPacket dataSimulatorGetPushConnectHeartPacket;
        synchronized (DataSimulatorGetPushConnectHeartPacket.class) {
            if (instance == null) {
                instance = new DataSimulatorGetPushConnectHeartPacket();
            }
            dataSimulatorGetPushConnectHeartPacket = instance;
        }
        return dataSimulatorGetPushConnectHeartPacket;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public int getResult() {
        return ((Integer) get(1, 1, Integer.class)).intValue();
    }

    protected void doPack() {
    }
}
