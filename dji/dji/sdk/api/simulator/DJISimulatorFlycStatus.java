package dji.sdk.api.simulator;

import dji.midware.util.c;

public class DJISimulatorFlycStatus {
    public int length;
    public byte[] recvData;

    public float get(int i) {
        return c.d(c.e(this.recvData, (i * 4) + 2, 4));
    }

    public boolean getMotoStateFlag() {
        return (c.b(c.e(this.recvData, 1, 1)) & 1) == 1;
    }

    public boolean getPlaneStateFlag() {
        return (c.b(c.e(this.recvData, 1, 1)) & 2) == 2;
    }
}
