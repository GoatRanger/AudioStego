package com.here.odnp.debug;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.FeatureInfo;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.OnScanCompletedListener;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import com.alibaba.sdk.android.SdkConstants;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.debug.ProcessUtils.ProcessInfo;
import com.here.odnp.util.Timer;
import com.here.odnp.util.Timer.Task;
import dji.pilot.dji_groundstation.controller.e;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public final class LogCapture {
    private static final String TAG = "odnp.debug.LogCapture";
    private final Context mContext;
    private BroadcastReceiver mDeviceStatusListener;
    private volatile boolean mFileTracesEnabled = false;
    private File mLogFile;
    private Process mLogcatProcess;
    private int mStartErrorCounter;
    private Task mStatusCheckTask;
    private Timer mStatusCheckTimer;

    class AnonymousClass2 extends Task {
        AnonymousClass2(Timer timer) {
            timer.getClass();
            super();
        }

        public void run() {
            if (!LogCapture.this.mFileTracesEnabled) {
                return;
            }
            if (LogCapture.this.mStartErrorCounter < 10) {
                LogCapture.this.checkCaptureStatus();
                LogCapture.this.startStatusCheckTimer(60000);
                return;
            }
            LogCapture.this.stopLogcat();
        }
    }

    public LogCapture(Context context) {
        this.mContext = context;
    }

    public synchronized void startFileTracing() {
    }

    public synchronized void stopFileTracing() {
    }

    @TargetApi(17)
    private void startDeviceStatusListener() {
        if (this.mDeviceStatusListener == null) {
            this.mDeviceStatusListener = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    intent.getIntExtra("android.intent.extra.user_handle", Integer.MAX_VALUE);
                }
            };
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.USER_INITIALIZE");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            intentFilter.addAction("android.intent.action.USER_BACKGROUND");
            intentFilter.addAction("android.intent.action.USER_FOREGROUND");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            this.mContext.registerReceiver(this.mDeviceStatusListener, intentFilter);
        }
    }

    @TargetApi(17)
    private void stopDeviceStatusListener() {
        if (this.mDeviceStatusListener != null) {
            this.mContext.unregisterReceiver(this.mDeviceStatusListener);
            this.mDeviceStatusListener = null;
        }
    }

    private void startLogcat() {
        stopLogcat();
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                this.mLogFile = DebugFile.getTimestampFile("trace", "logfile.log");
                writeLogHeader(this.mLogFile);
                List linkedList = new LinkedList();
                linkedList.add("logcat");
                linkedList.add("-v");
                linkedList.add("threadtime");
                linkedList.add("-b");
                linkedList.add(e.j);
                if (!isEmulator()) {
                    linkedList.add("-b");
                    linkedList.add(SdkConstants.SYSTEM_PLUGIN_NAME);
                }
                if (Build.MANUFACTURER.equalsIgnoreCase("amazon")) {
                    linkedList.add("-b");
                    linkedList.add("amazon_main");
                }
                linkedList.add("-f");
                linkedList.add(this.mLogFile.getAbsolutePath());
                linkedList.add("*:V");
                this.mLogcatProcess = new ProcessBuilder(linkedList).redirectErrorStream(true).start();
                scanFile(this.mLogFile);
                return;
            }
            throw new IOException("Disk is not writable");
        } catch (Exception e) {
        }
    }

    private void stopLogcat() {
        if (this.mLogcatProcess != null) {
            this.mLogcatProcess.destroy();
            this.mLogcatProcess = null;
        }
        this.mLogFile = null;
    }

    private boolean isLogcatRunning() {
        if (this.mLogcatProcess == null) {
            this.mStartErrorCounter++;
            return false;
        } else if (!isProcessAlive(this.mLogcatProcess)) {
            getProcessExitValue(this.mLogcatProcess);
            this.mStartErrorCounter++;
            return false;
        } else if (this.mLogFile == null || !this.mLogFile.exists()) {
            this.mStartErrorCounter++;
            return false;
        } else {
            this.mStartErrorCounter = 0;
            return true;
        }
    }

    private void killLogcatProcesses() {
        int i = 0;
        ProcessInfo[] process = ProcessUtils.getProcess(null, this.mContext.getPackageName() + ":remote");
        if (process.length == 1) {
            process = ProcessUtils.getProcess(process[0].user, "logcat");
            int length = process.length;
            while (i < length) {
                Process.killProcess(process[i].pid);
                i++;
            }
        }
    }

    private void writeLogHeader(File file) throws IOException {
        PrintStream printStream = new PrintStream(file, Charset.defaultCharset().name());
        printStream.print("ODNP Service logcat capture, started ");
        printStream.println(getTimeInLogFormat(System.currentTimeMillis()));
        printStream.println();
        writeDeviceInfo(printStream);
        printStream.println();
        writeOdnpInfo(printStream);
        printStream.println();
        printStream.close();
        if (printStream.checkError()) {
            throw new IOException("Could not write log file header");
        }
    }

    private static boolean isProcessAlive(Process process) {
        if (getProcessExitValue(process) == null) {
            return true;
        }
        return false;
    }

    private static Integer getProcessExitValue(Process process) {
        try {
            return Integer.valueOf(process.exitValue());
        } catch (IllegalThreadStateException e) {
            return null;
        }
    }

    private void startStatusCheckTimer(long j) {
        stopStatusCheckTimer();
        Timer timer = this.mStatusCheckTimer;
        timer.getClass();
        this.mStatusCheckTask = new AnonymousClass2(timer);
        this.mStatusCheckTimer.schedule(this.mStatusCheckTask, j);
    }

    private void stopStatusCheckTimer() {
        if (this.mStatusCheckTask != null) {
            try {
                this.mStatusCheckTask.cancel();
            } catch (Exception e) {
            } finally {
                this.mStatusCheckTask = null;
            }
        }
        if (this.mStatusCheckTimer != null) {
            this.mStatusCheckTimer.cancel();
        }
    }

    private void checkCaptureStatus() {
        if (!isLogcatRunning()) {
            startLogcat();
        }
    }

    private static String getTimeInLogFormat(long j) {
        return new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.US).format(new Date(j));
    }

    @TargetApi(21)
    private void writeDeviceInfo(PrintStream printStream) {
        PackageManager packageManager = this.mContext.getPackageManager();
        printStream.println("Device info:");
        printStream.println("  Manufacturer: " + Build.MANUFACTURER);
        printStream.println("  Model: " + Build.MODEL);
        printStream.println("  Serial: " + Build.SERIAL);
        printStream.println("  Android API level: " + VERSION.SDK_INT);
        printStream.println("  Android Release: " + VERSION.RELEASE);
        printStream.println("  Firmware: " + Build.FINGERPRINT);
        printStream.println("  ABI's: " + getAbis());
        printStream.println("  Features:");
        FeatureInfo[] systemAvailableFeatures = packageManager.getSystemAvailableFeatures();
        if (systemAvailableFeatures == null || systemAvailableFeatures.length == 0) {
            printStream.println("  -");
            return;
        }
        for (FeatureInfo featureInfo : systemAvailableFeatures) {
            String str = featureInfo.name;
            if (str != null) {
                printStream.println("    " + str);
            }
        }
    }

    @TargetApi(21)
    private static String getAbis() {
        StringBuilder stringBuilder = new StringBuilder();
        if (VERSION.SDK_INT >= 21) {
            stringBuilder.append(TextUtils.join(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR, Build.SUPPORTED_ABIS));
        } else {
            stringBuilder.append(Build.CPU_ABI);
            if (!"".equals(Build.CPU_ABI2)) {
                stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuilder.append(Build.CPU_ABI2);
            }
        }
        return stringBuilder.toString();
    }

    private void writeOdnpInfo(PrintStream printStream) {
        printStream.println("ODNP Service info:");
        printStream.println("  Package: " + this.mContext.getPackageName());
        printStream.println("  Version: 99.99.99");
    }

    private static boolean isEmulator() {
        if (Build.PRODUCT.matches(".*sdk.*") && Build.FINGERPRINT.startsWith("generic")) {
            return true;
        }
        return false;
    }

    private void scanFile(File file) {
        MediaScannerConnection.scanFile(this.mContext, new String[]{file.getAbsolutePath()}, null, new OnScanCompletedListener() {
            public void onScanCompleted(String str, Uri uri) {
            }
        });
    }
}
