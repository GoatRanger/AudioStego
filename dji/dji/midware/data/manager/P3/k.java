package dji.midware.data.manager.P3;

public interface k {
    void destroy();

    boolean isConnected();

    boolean isOK();

    boolean isRemoteOK();

    void onConnect();

    void onDisconnect();

    void pauseParseThread();

    void pauseRecvThread();

    void pauseService(boolean z);

    void resumeParseThread();

    void resumeRecvThread();

    void sendmessage(byte[] bArr);

    void setDataMode(boolean z);

    void startStream();

    void stopStream();
}
