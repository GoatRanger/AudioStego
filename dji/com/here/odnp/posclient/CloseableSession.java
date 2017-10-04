package com.here.odnp.posclient;

public abstract class CloseableSession implements ICloseableSession {
    private static final String TAG = "odnp.posclient.CloseableSession";
    private volatile boolean mOpened;
    protected final PosClientManager mPosClientManager;

    protected abstract void onClose();

    protected abstract void onOpen();

    public CloseableSession(PosClientManager posClientManager) {
        this.mPosClientManager = posClientManager;
    }

    public boolean open() {
        if (this.mOpened) {
            return true;
        }
        onOpen();
        this.mOpened = true;
        return this.mOpened;
    }

    public void close() {
        if (this.mOpened) {
            this.mOpened = false;
            onClose();
        }
    }
}
