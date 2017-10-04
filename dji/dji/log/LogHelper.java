package dji.log;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import dji.midware.data.config.P3.DeviceType;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;

class LogHelper {
    private static final String LOG_FORMAT = "[%1$s][%2$s][%3$s]:%4$s";
    protected static final int MAX_LINES = 100;
    private static final int MSG_ID_UPDATE_LOG = 4096;
    private static final String STR_NULL = "null";
    private static final String STR_UNKNOWN = "Unknown";
    private static LogHelper mIntance;
    private static String mLog = "";
    private HashMap<DeviceType, ArrayList<String>> list = new HashMap();
    private volatile boolean mClosed = true;
    private Context mContext = null;
    private volatile boolean mInit = false;
    private LogDialog mLogDialog = null;
    private LogHandler mLogHandler = null;
    private LogHandlerThread mLogThread = null;
    private final LogUIHandler mLogUIHandler = new LogUIHandler(this);

    private static class LogHandler extends Handler {
        private final WeakReference<LogHelper> mOutCls;

        public LogHandler(LogHelper logHelper, Looper looper) {
            super(looper);
            this.mOutCls = new WeakReference(logHelper);
        }

        public void handleMessage(Message message) {
            LogHelper logHelper = (LogHelper) this.mOutCls.get();
            if (logHelper != null && logHelper.canDo()) {
                switch (message.what) {
                    case 4096:
                        logHelper.updateLogInternal();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    private static class LogHandlerThread extends HandlerThread {
        public LogHandlerThread(String str) {
            this(str, 1);
        }

        public LogHandlerThread(String str, int i) {
            super(str, i);
        }
    }

    private static class LogUIHandler extends Handler {
        private final WeakReference<LogHelper> mOutCls;

        public LogUIHandler(LogHelper logHelper) {
            super(Looper.getMainLooper());
            this.mOutCls = new WeakReference(logHelper);
        }

        public void handleMessage(Message message) {
            LogHelper logHelper = (LogHelper) this.mOutCls.get();
            if (logHelper != null && logHelper.canDo()) {
                switch (message.what) {
                    case 4096:
                        logHelper.showDialog(LogHelper.mLog);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    protected static synchronized LogHelper createIntance(Context context) {
        LogHelper logHelper;
        synchronized (LogHelper.class) {
            if (mIntance == null) {
                mIntance = new LogHelper(context);
            }
            logHelper = mIntance;
        }
        return logHelper;
    }

    protected static synchronized LogHelper getIntance() {
        LogHelper logHelper;
        synchronized (LogHelper.class) {
            logHelper = mIntance;
        }
        return logHelper;
    }

    private LogHelper(Context context) {
        if (DJILogHelper.OPEN) {
            initializeHelper(context);
        }
    }

    private synchronized void initializeHelper(Context context) {
        if (!this.mInit) {
            this.mContext = context.getApplicationContext();
            this.mLogThread = new LogHandlerThread("djilog-1");
            this.mLogThread.start();
            this.mLogHandler = new LogHandler(this, this.mLogThread.getLooper());
            this.mClosed = true;
            this.mInit = true;
            showDialog("<< log dump start now >>");
        }
    }

    private synchronized void finalizeHelper() {
        if (this.mInit) {
            closeLog();
            this.mLogHandler.removeMessages(4096);
            this.mLogHandler = null;
            this.mLogThread.quit();
            this.mLogThread = null;
            this.mLogUIHandler.removeMessages(4096);
            this.mContext = null;
            this.mInit = false;
        }
    }

    protected void updateLog(DeviceType deviceType, String str) {
        if (canDo()) {
            if (deviceType == DeviceType.APP && str == null) {
                str = STR_NULL;
            }
            synchronized (this) {
                ArrayList arrayList;
                if (this.list.containsKey(deviceType)) {
                    arrayList = (ArrayList) this.list.get(deviceType);
                    if (arrayList.size() == 100) {
                        arrayList.remove(0);
                    }
                    arrayList.add(str);
                    this.list.put(deviceType, arrayList);
                } else {
                    arrayList = new ArrayList();
                    arrayList.add(str);
                    this.list.put(deviceType, arrayList);
                }
            }
            this.mLogHandler.obtainMessage(4096).sendToTarget();
        }
    }

    protected void updateLog() {
        this.mLogHandler.obtainMessage(4096).sendToTarget();
    }

    protected void autoHandle() {
        if (canDo()) {
            Log.d("", "click autoHandle " + this.mClosed);
            if (this.mClosed) {
                openLog();
            } else {
                closeLog();
            }
        }
    }

    protected void openLog() {
        if (canDo() && this.mClosed) {
            this.mClosed = false;
            this.mLogUIHandler.obtainMessage(4096).sendToTarget();
        }
    }

    protected void closeLog() {
        if (canDo() && !this.mClosed) {
            this.mClosed = true;
            this.mLogHandler.removeMessages(4096);
            this.mLogUIHandler.removeMessages(4096);
            hideDialog();
        }
    }

    private void showDialog(String str) {
        if (this.mLogDialog == null) {
            this.mLogDialog = new LogDialog(this.mContext);
        }
        if (!this.mClosed) {
            if (!(this.mLogDialog == null || this.mLogDialog.isShowing())) {
                this.mLogDialog.show();
                Log.d("", "click show");
            }
            this.mLogDialog.updateLog(str);
        }
    }

    private void hideDialog() {
        Log.d("", "click hideDialog");
        if (this.mLogDialog != null && this.mLogDialog.isShowing()) {
            this.mLogDialog.dismiss();
        }
    }

    private boolean canDo() {
        return this.mInit;
    }

    private void updateLogInternal() {
        if (canDo()) {
            synchronized (this) {
                StringBuilder stringBuilder = new StringBuilder();
                for (DeviceType deviceType : this.list.keySet()) {
                    DeviceType deviceType2 = this.mLogDialog.getDeviceType();
                    if (deviceType2 != null && deviceType2 == deviceType) {
                        ArrayList arrayList = (ArrayList) this.list.get(deviceType);
                        int size = arrayList.size();
                        for (int i = 0; i < size; i++) {
                            stringBuilder.append((String) arrayList.get(i)).append("\n");
                        }
                        mLog = stringBuilder.toString();
                    }
                }
                mLog = stringBuilder.toString();
            }
            this.mLogUIHandler.obtainMessage(4096).sendToTarget();
        }
    }
}
