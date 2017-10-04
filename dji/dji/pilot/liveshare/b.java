package dji.pilot.liveshare;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.format.Time;
import android.util.Log;
import dji.midware.natives.FPVController;
import dji.pilot.usercenter.protocol.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;

public class b {
    private static final int AUDIO_SAMPLE_RATE = 44100;
    private static final int MSG_ID_STATUS_CHANGE = 4096;
    private static final int MSG_ID_STATUS_ERROR = 8192;
    String broadcastDesc;
    String broadcastTitle;
    private boolean enableJackysInterface;
    private int enable_audio;
    private boolean isLaunch;
    private boolean isStart;
    private a mAudioRecorder;
    private final ArrayList<a> mStatusListeners;
    private c mUIHandler;
    private c mVideoStreamer;
    private volatile boolean mbRunning;
    String primaryServerUrl;
    String privacyStatus;
    long streamBeginTime;
    int streamMode;
    String streamName;
    long streamTime;
    Time t;
    private int testTag;
    private String url;

    public interface a {
        void onStatusChanged(int i);
    }

    private static final class b {
        private static final b mInstance = new b();

        private b() {
        }
    }

    private static final class c extends Handler {
        private final WeakReference<b> mOutcls;

        private c(b bVar) {
            super(Looper.getMainLooper());
            this.mOutcls = new WeakReference(bVar);
        }

        public void handleMessage(Message message) {
            b bVar = (b) this.mOutcls.get();
            if (bVar != null) {
                switch (message.what) {
                    case 4096:
                        bVar.notifyStatusChange(0);
                        return;
                    case 8192:
                        bVar.notifyStatusChange(-1);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public void setTestTag(int i) {
        this.testTag = i;
    }

    public int getTestTag() {
        return this.testTag;
    }

    public static b getInstance() {
        return b.mInstance;
    }

    public synchronized void registerListener(a aVar) {
        if (aVar != null) {
            if (!this.mStatusListeners.contains(aVar)) {
                this.mStatusListeners.add(aVar);
            }
        }
    }

    public synchronized void unregisterListener(a aVar) {
        this.mStatusListeners.remove(aVar);
    }

    public boolean isRunning() {
        return this.mbRunning;
    }

    public boolean isLaunch() {
        return this.isLaunch;
    }

    public void setLaunch(boolean z) {
        this.isLaunch = z;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int startStream() {
        if (this.streamMode == 1) {
            if (this.primaryServerUrl.equals("") || this.streamName.equals("")) {
                return -1;
            }
            this.url = this.primaryServerUrl + d.t + this.streamName;
        }
        if (this.streamMode == 2) {
            this.url = this.primaryServerUrl;
        }
        if (this.mbRunning) {
            return 0;
        }
        Log.e("FBLive", "final url: " + this.url);
        if (FPVController.native_initStreaming(this.url, this.enable_audio) != 0) {
            return -2;
        }
        FPVController.native_startStream(this.isStart);
        if (this.enableJackysInterface) {
            this.mVideoStreamer = c.getInstance();
            this.mVideoStreamer.onStartStream();
        }
        if (this.enable_audio == 1) {
            this.mAudioRecorder = a.getInstance();
            this.mAudioRecorder.start(44100);
        }
        if (Looper.myLooper() == this.mUIHandler.getLooper()) {
            notifyStatusChange(0);
        } else {
            this.mUIHandler.sendEmptyMessage(4096);
        }
        Log.d("", "startStream: end");
        this.mbRunning = true;
        return 0;
    }

    public void stopStream() {
        this.isLaunch = false;
        new Thread(new Runnable() {
            public void run() {
                if (b.this.mbRunning) {
                    b.this.mAudioRecorder = a.getInstance();
                    if (b.this.mAudioRecorder != null) {
                        b.this.mAudioRecorder.stop();
                    }
                    if (b.this.enableJackysInterface) {
                        b.this.mVideoStreamer = c.getInstance();
                        b.this.mVideoStreamer.onEndStream();
                    }
                    FPVController.native_stopStream();
                    b.this.mbRunning = false;
                    b.this.streamBeginTime = -1;
                    dji.pilot.liveshare.Youtube.a.getInstance().setStage(-1);
                    dji.pilot.liveshare.Youtube.a.getInstance().setWatchUrl(null);
                }
            }
        }).start();
    }

    public void pauseStream() {
    }

    public void continueStream() {
    }

    public int isEnable_audio() {
        return this.enable_audio;
    }

    public void setEnable_audio(int i) {
        this.enable_audio = i;
    }

    private void notifyStatusChange(int i) {
        synchronized (this.mStatusListeners) {
            Iterator it = this.mStatusListeners.iterator();
            while (it.hasNext()) {
                ((a) it.next()).onStatusChanged(i);
            }
        }
    }

    private b() {
        this.url = null;
        this.isStart = true;
        this.mAudioRecorder = null;
        this.mVideoStreamer = null;
        this.enable_audio = 1;
        this.enableJackysInterface = true;
        this.mStatusListeners = new ArrayList(2);
        this.mbRunning = false;
        this.isLaunch = false;
        this.mUIHandler = null;
        this.streamBeginTime = -1;
        this.t = new Time();
        this.streamMode = -1;
        this.streamName = null;
        this.primaryServerUrl = null;
        this.testTag = 0;
        this.mUIHandler = new c();
    }

    public long getStreamTime() {
        Log.d("", "getStreamTime: streamBeginTime = " + this.streamBeginTime);
        if (this.streamBeginTime == -1) {
            return 0;
        }
        this.streamTime = System.currentTimeMillis() - this.streamBeginTime;
        return this.streamTime / 1000;
    }

    public void setStreamBeginTime() {
        Log.d("", "setStreamBeginTime: ");
        this.streamBeginTime = System.currentTimeMillis();
        this.t.setToNow();
    }

    public int getStreamMode() {
        return this.streamMode;
    }

    public void setStreamMode(int i) {
        this.streamMode = i;
    }

    public void setPrimaryServerUrl(String str) {
        this.primaryServerUrl = str;
    }

    public String getPrimaryServerUrl() {
        return this.primaryServerUrl;
    }

    public void setStreamName(String str) {
        this.streamName = str;
    }

    public String getStreamName() {
        return this.streamName;
    }

    public long getStreamBeginTime() {
        return this.streamBeginTime;
    }

    public String getBroadcastTitle() {
        return this.broadcastTitle;
    }

    public void setBroadcastTitle(String str) {
        this.broadcastTitle = str;
    }

    public String getBroadcastDesc() {
        return this.broadcastDesc;
    }

    public void setBroadcastDesc(String str) {
        this.broadcastDesc = str;
    }
}
