package dji.midware.data.model.P3;

import dji.midware.data.manager.P3.n;

public class DataFlycGetPushBoardRecv extends n {
    private static DataFlycGetPushBoardRecv instance = null;

    public static synchronized DataFlycGetPushBoardRecv getInstance() {
        DataFlycGetPushBoardRecv dataFlycGetPushBoardRecv;
        synchronized (DataFlycGetPushBoardRecv.class) {
            if (instance == null) {
                instance = new DataFlycGetPushBoardRecv();
            }
            dataFlycGetPushBoardRecv = instance;
        }
        return dataFlycGetPushBoardRecv;
    }

    protected boolean isChanged(byte[] bArr) {
        return true;
    }

    public byte[] getRecvData() {
        return this._recData;
    }

    protected void doPack() {
    }
}
