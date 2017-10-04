package dji.midware.data.manager.P3;

import dji.log.DJILogHelper;
import dji.midware.data.a.a.b;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Timer;

public class DJIVideoPackManager extends h {
    private static DJIVideoPackManager instance = null;
    private int cttt = 0;
    private long currentSize;
    private File file = new File("/sdcard/DJI/save3s.file");
    private boolean isStart = false;
    private boolean isStartParse = false;
    private a onStartListener;
    private Timer progTimer;
    private Runnable runnable = new Runnable(this) {
        final /* synthetic */ DJIVideoPackManager a;

        {
            this.a = r1;
        }

        public void run() {
            DJILogHelper.getInstance().LOGD("", "isstart=" + this.a.isStart + " rate=" + this.a.currentSize + "b/s", false, true);
            this.a.currentSize = 0;
        }
    };
    private boolean saveFile = false;
    private FileOutputStream stream;
    private long tmpSize;

    public interface a {
        void a();
    }

    public static synchronized DJIVideoPackManager getInstance() {
        DJIVideoPackManager dJIVideoPackManager;
        synchronized (DJIVideoPackManager.class) {
            if (instance == null) {
                instance = new DJIVideoPackManager();
            }
            dJIVideoPackManager = instance;
        }
        return dJIVideoPackManager;
    }

    private DJIVideoPackManager() {
    }

    public void clearVideoData() {
    }

    protected void debug(b bVar) {
    }

    public void setOnStartListener(a aVar) {
        this.onStartListener = aVar;
    }

    public void start() {
        clearVideoData();
        ServiceManager.getInstance().setDataMode(true);
        this.isStart = true;
        if (this.progTimer != null) {
            this.progTimer.cancel();
            this.progTimer = null;
        }
    }

    public boolean isStart() {
        return this.isStart;
    }

    public void stop() {
        if (this.progTimer != null) {
            this.progTimer.cancel();
            this.progTimer = null;
        }
        this.isStart = false;
        this.isStartParse = false;
        if (this.stream != null) {
            try {
                this.stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.stream = null;
        }
        ServiceManager.getInstance().setDataMode(false);
    }

    public synchronized void parseData(byte[] bArr, int i, int i2) {
        if (this.isStart) {
            this.currentSize += (long) i2;
            if (this.onStartListener != null) {
                this.onStartListener.a();
            }
            if (!this.isStartParse) {
                this.isStartParse = true;
                if (this.saveFile) {
                    if (this.file.exists()) {
                        this.file.delete();
                    }
                    try {
                        this.file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (this.stream == null) {
                        try {
                            this.stream = new FileOutputStream(this.file);
                        } catch (FileNotFoundException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            }
            if (i2 > 0) {
                if (i2 == 6) {
                }
                if (this.saveFile) {
                    try {
                        this.stream.write(bArr, i, i2);
                        this.stream.flush();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                parse(bArr, i, i2);
            }
        }
    }
}
