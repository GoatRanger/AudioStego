package dji.midware.usbhost.P3;

class UsbHostServiceRC$a implements Runnable {
    public static final String a = "VideoStream_Parse_Thread";
    final /* synthetic */ UsbHostServiceRC b;
    private int c = 0;
    private long d = -1;
    private long e = 0;
    private long f = 0;

    private UsbHostServiceRC$a(UsbHostServiceRC usbHostServiceRC) {
        this.b = usbHostServiceRC;
    }

    public void run() {
        UsbHostServiceRC.access$000(this.b, "ParseVideoRunnable.end");
    }
}
