package dji.midware.usbhost.P3;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.DJIVideoPackManager;
import dji.midware.data.manager.P3.g;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.k;
import dji.midware.f.a;
import dji.midware.f.b;
import dji.midware.media.DJIVideoDataRecver;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class UsbHostServiceRC implements k {
    static boolean firstRecv = true;
    private static UsbHostServiceRC instance = null;
    private static final String saveVideoPath = (Environment.getExternalStorageDirectory().getPath() + "usbhost.264");
    private final boolean IS_PRINT_RATE = true;
    private final String TAG = getClass().getSimpleName();
    private byte[] boxbuffer = new byte[16384];
    private int count = 0;
    private boolean dataMode;
    private FileOutputStream fileOutputStream = null;
    private int getVideoIndex = 0;
    private boolean isPauseRecvThread = false;
    private boolean isPauseService = false;
    private boolean isPaused = false;
    private final boolean isSaveVideoToFile = false;
    private boolean isStartStream;
    private long lastT = 0;
    private boolean mOsdRun;
    private boolean mParseRun;
    private boolean mParseVideoRun;
    private boolean mVodRun;
    private boolean m_cmd_serial = true;
    private byte[] osdbuffer = new byte[4096];
    private g packManager;
    private Thread parseVideoThread;
    private int setVideoIndex = 0;
    private ArrayList<byte[]> videoList = new ArrayList(50);
    private DJIVideoPackManager videoPackManager;
    private ArrayList<Integer> videoSizeList = new ArrayList(50);

    public static synchronized UsbHostServiceRC getInstance() {
        UsbHostServiceRC usbHostServiceRC;
        synchronized (UsbHostServiceRC.class) {
            if (instance == null) {
                instance = new UsbHostServiceRC();
            }
            usbHostServiceRC = instance;
        }
        return usbHostServiceRC;
    }

    public static void Pause() {
        if (instance != null) {
            instance.pause();
        }
    }

    public static void Destroy() {
        if (instance != null) {
            instance.destroy();
        }
    }

    public UsbHostServiceRC() {
        System.out.println("xxxxxxxxxxxxxx UsbHostServiceRC construct");
        startStream();
        this.packManager = g.getInstance();
        this.videoPackManager = DJIVideoPackManager.getInstance();
        NativeRcController.b();
        NativeRcController.a(this);
    }

    public void start(Context context) {
        context.getApplicationContext();
        NativeRcController.b();
    }

    public void stop(Context context) {
        NativeRcController.c();
    }

    public void startStream() {
        this.isStartStream = true;
        Log.d("", "xx usb host startStream");
    }

    public void stopStream() {
        this.isStartStream = false;
        Log.d("", "usb host stopStream");
        this.mVodRun = false;
    }

    protected void startThreads() {
    }

    public void onSerialRecv(byte[] bArr, int i) {
        if (!this.isPaused) {
            printRate(i);
            this.packManager.parse(bArr, 0, i);
            if (firstRecv) {
                firstRecv = false;
                onConnect();
            }
        }
    }

    @Deprecated
    public void onVideoRecv(byte[] bArr, int i) {
    }

    @Deprecated
    public void onVideoRecv(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
    }

    public void onVideoRecv(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        if (i10 == 1) {
            boolean z;
            DJIVideoDataRecver instance = DJIVideoDataRecver.getInstance();
            if (i3 == 0) {
                z = false;
            } else {
                z = true;
            }
            instance.onVideoRecv(bArr, i, i2, z, i4, i5, i6, i7, i8, i9, false);
        } else if (!this.isPaused) {
            printRate(i);
            this.videoPackManager.parse(bArr, 0, i);
            if (firstRecv) {
                firstRecv = false;
                onConnect();
            }
        }
    }

    private void startRecvVodThread() {
    }

    private void handleOldMethod(int i) {
        if (this.dataMode) {
            DJIVideoPackManager.getInstance().parseData(this.boxbuffer, 0, i);
        } else {
            putVideoBuffer(this.boxbuffer, i);
        }
    }

    public void handleNewMethod(byte[] bArr, int i, int i2) {
    }

    private void putVideoBuffer(byte[] bArr, int i) {
        this.videoSizeList.set(this.setVideoIndex, Integer.valueOf(i));
        System.arraycopy(bArr, 0, (byte[]) this.videoList.get(this.setVideoIndex), 0, i);
        if (this.setVideoIndex == this.videoList.size() - 1) {
            this.setVideoIndex = 0;
        } else {
            this.setVideoIndex++;
        }
    }

    public synchronized void sendmessage(byte[] bArr) {
        NativeRcController.a(bArr, bArr.length);
    }

    public boolean isConnected() {
        return true;
    }

    public boolean isOK() {
        return isConnected();
    }

    public void resume() {
        this.isPaused = false;
    }

    private void pause() {
        this.isPaused = true;
        firstRecv = true;
    }

    public void destroy() {
        this.mVodRun = false;
        this.mOsdRun = false;
        this.mParseRun = false;
        instance = null;
    }

    private void printRate(int i) {
        this.count += i;
        if (getTickCount() - this.lastT > 1000) {
            System.out.println(String.format(i.getInstance().c() + " serial rate %d KB\n", new Object[]{Integer.valueOf(this.count / 1024)}));
            this.lastT = getTickCount();
            this.count = 0;
        }
    }

    private long getTickCount() {
        return System.currentTimeMillis();
    }

    private void printUI(String str) {
        DJILogHelper.getInstance().LOGE(this.TAG, str, false, true);
    }

    private void print(String str) {
        DJILogHelper.getInstance().LOGE(this.TAG, str, false, false);
    }

    public boolean isRemoteOK() {
        return this.packManager.c();
    }

    public void setDataMode(boolean z) {
    }

    public void pauseRecvThread() {
        this.isPauseRecvThread = true;
    }

    public void resumeRecvThread() {
        this.isPauseRecvThread = false;
    }

    public void pauseParseThread() {
    }

    public void resumeParseThread() {
    }

    public void pauseService(boolean z) {
        if (this.isPauseService != z) {
            this.isPauseService = z;
            if (this.isPauseService) {
                g.getInstance().a();
            } else {
                g.getInstance().b();
            }
        }
    }

    public void onDisconnect() {
        this.mVodRun = false;
        this.mOsdRun = false;
        this.mParseRun = false;
        firstRecv = true;
        instance = null;
        a.getInstance().a(b.NON);
    }

    public void onConnect() {
        System.out.println("serial USBHost DJILinkDaemonService.getInstance().setLinkType(DJILinkType.HOSTRC) ");
        a.getInstance().a(b.HOSTRC);
    }
}
