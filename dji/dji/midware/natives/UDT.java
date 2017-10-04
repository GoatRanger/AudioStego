package dji.midware.natives;

import android.util.Log;

public class UDT {
    public static native void SwUdpClose();

    public static native int SwUdpConnect(String str, int i);

    public static native boolean SwUdpIsConnected();

    public static native int SwUdpJoyStickSend(byte[] bArr, int i, int i2);

    public static native int SwUdpSend(byte[] bArr, int i, int i2);

    public static native synchronized int cleanup();

    public static native int close(int i);

    public static native int connect(int i, String str, String str2);

    public static native int recv(int i, byte[] bArr, int i2, int i3, int i4);

    public static native int recvmsg(int i, byte[] bArr, int i2, int i3);

    public static native int send(int i, byte[] bArr, int i2, int i3, int i4);

    public static native int sendmsg(int i, byte[] bArr, int i2, int i3);

    public static native void setSwRecver(Object obj);

    public static native int socket();

    public static native int socketDgram();

    public static native synchronized int startup();

    static {
        try {
            Log.d("FPVController", "try to load udt.so");
            System.loadLibrary("stlport_shared");
            System.loadLibrary("udt");
            System.loadLibrary("udtjni");
        } catch (UnsatisfiedLinkError e) {
            Log.e("UDT", "Couldn't load lib");
        }
    }

    public static void loadLibrary() {
    }
}
