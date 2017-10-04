package com.tencent.android.tpush.service;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import com.alipay.sdk.j.i;
import com.d.a;
import com.d.c;
import com.tencent.android.tpush.XGPush4Msdk;
import com.tencent.android.tpush.XGPushConfig;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.p;
import com.tencent.android.tpush.service.a.b;
import com.tencent.android.tpush.service.cache.CacheManager;
import com.tencent.android.tpush.service.d.e;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@c(a = 1, b = 3, c = "20150316", e = {a.SERVICESCHECK}, f = "确认已进行安全校验")
public class XGWatchdog {
    public static Integer CURRENT_WD_VERSION = Integer.valueOf(2);
    private static final String LIB_FULL_NAME = "libtpnsWatchdog.so";
    private static final String LIB_NAME = "tpnsWatchdog";
    public static final String TAG = "TpnsWatchdog";
    private static String WatchdogPath = "";
    private static int defaultWatchdogPort = 55550;
    private static Handler handler = null;
    private static volatile XGWatchdog instance = null;
    private static Random random = new Random();
    private static final String watchdogPortName = "com.tencent.tpnsWatchdogPort";
    private Context context = null;
    volatile boolean isStarted = false;

    private XGWatchdog(Context context) {
        try {
            this.context = context.getApplicationContext();
            l.c(this.context);
            HandlerThread handlerThread = new HandlerThread("XGWatchdog.thread");
            handlerThread.start();
            handler = new Handler(handlerThread.getLooper());
        } catch (Throwable th) {
            com.tencent.android.tpush.a.a.c(TAG, "init XGWatchdog error", th);
        }
    }

    public static XGWatchdog getInstance(Context context) {
        if (instance == null) {
            synchronized (XGWatchdog.class) {
                if (instance == null) {
                    instance = new XGWatchdog(context);
                }
            }
        }
        return instance;
    }

    public static int getRandomInt(int i) {
        return random.nextInt(i);
    }

    public static int getRandomPort() {
        return getRandomInt(1000) + 55000;
    }

    public int getWatchdogPort() {
        ServerSocket serverSocket;
        Throwable e;
        try {
            return System.getInt(this.context.getContentResolver(), watchdogPortName);
        } catch (SettingNotFoundException e2) {
            int i = 0;
            while (i < 10) {
                int randomPort = getRandomPort();
                try {
                    serverSocket = new ServerSocket(randomPort);
                    try {
                        System.putInt(this.context.getContentResolver(), watchdogPortName, randomPort);
                        if (serverSocket == null) {
                            return randomPort;
                        }
                        try {
                            serverSocket.close();
                            return randomPort;
                        } catch (Exception e3) {
                            return randomPort;
                        }
                    } catch (Exception e4) {
                        e = e4;
                    }
                } catch (Exception e5) {
                    e = e5;
                    serverSocket = null;
                    try {
                        com.tencent.android.tpush.a.a.c(TAG, "create ServerSocket error", e);
                        if (serverSocket != null) {
                            try {
                                serverSocket.close();
                            } catch (Exception e6) {
                            }
                        }
                        i++;
                    } catch (Throwable th) {
                        e = th;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    serverSocket = null;
                }
            }
            return defaultWatchdogPort;
        }
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (Exception e7) {
            }
        }
        throw e;
        throw e;
    }

    public void sendHeartbeat2Watchdog(String str) {
        sendHeartbeat2Watchdog(str, null);
    }

    private String directSendContent(String str) {
        Socket socket;
        BufferedReader bufferedReader;
        BufferedWriter bufferedWriter;
        Throwable th;
        Throwable th2;
        String str2 = null;
        try {
            socket = new Socket("127.0.0.1", getWatchdogPort());
            try {
                socket.setSoTimeout(2000);
                bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                try {
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                } catch (Exception e) {
                    bufferedWriter = null;
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (Exception e2) {
                            com.tencent.android.tpush.a.a.h(TAG, "close socket failed " + e2.getMessage());
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e4) {
                        }
                    }
                    return str2;
                } catch (Throwable th3) {
                    th = th3;
                    bufferedWriter = null;
                    th2 = th;
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (Exception e22) {
                            com.tencent.android.tpush.a.a.h(TAG, "close socket failed " + e22.getMessage());
                        }
                    }
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (Exception e5) {
                        }
                    }
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (Exception e6) {
                        }
                    }
                    throw th2;
                }
            } catch (Exception e7) {
                bufferedWriter = null;
                bufferedReader = null;
                if (socket != null) {
                    socket.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                return str2;
            } catch (Throwable th32) {
                bufferedReader = null;
                th2 = th32;
                Object obj = null;
                if (socket != null) {
                    socket.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                throw th2;
            }
            try {
                String str3 = "xgapplist:" + getLocalXGApps();
                if (str == null) {
                    str = str3;
                }
                bufferedWriter.write(str);
                bufferedWriter.flush();
                str2 = bufferedReader.readLine();
                if (socket != null) {
                    try {
                        socket.close();
                    } catch (Exception e222) {
                        com.tencent.android.tpush.a.a.h(TAG, "close socket failed " + e222.getMessage());
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e8) {
                    }
                }
                if (bufferedWriter != null) {
                    try {
                        bufferedWriter.close();
                    } catch (Exception e9) {
                    }
                }
            } catch (Exception e10) {
                if (socket != null) {
                    socket.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                return str2;
            } catch (Throwable th4) {
                th2 = th4;
                if (socket != null) {
                    socket.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                throw th2;
            }
        } catch (Exception e11) {
            bufferedWriter = null;
            bufferedReader = null;
            socket = null;
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            return str2;
        } catch (Throwable th322) {
            bufferedReader = null;
            socket = null;
            th = th322;
            bufferedWriter = null;
            th2 = th;
            if (socket != null) {
                socket.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            throw th2;
        }
        return str2;
    }

    public void sendHeartbeat2Watchdog(String str, x xVar) {
        if (handler != null) {
            handler.post(new v(this, str, xVar));
        }
    }

    public void sendXGApp(String str, long j) {
        sendHeartbeat2Watchdog(str + "," + j + i.b);
    }

    public void sendAllLocalXGAppList() {
        sendHeartbeat2Watchdog(null);
    }

    public void sendDebugMode(boolean z) {
        sendHeartbeat2Watchdog("debug:" + (z ? "1" : "0"));
    }

    public String getLocalXGApps() {
        if (l.e() == null) {
            l.c(this.context);
        }
        List<ResolveInfo> a = e.a(this.context);
        List<y> arrayList = new ArrayList(10);
        if (a != null) {
            for (ResolveInfo resolveInfo : a) {
                String str = resolveInfo.activityInfo.packageName;
                if (!e.a(str)) {
                    com.tencent.android.tpush.data.a registerInfoByPkgName = CacheManager.getRegisterInfoByPkgName(str);
                    if (registerInfoByPkgName != null) {
                        com.tencent.android.tpush.service.a.c a2 = b.a(this.context, str);
                        float f = 1.0f;
                        if (a2 != null) {
                            f = a2.a;
                        }
                        y yVar = new y();
                        yVar.a = str;
                        yVar.c = registerInfoByPkgName.a;
                        yVar.b = f;
                        arrayList.add(yVar);
                    }
                }
            }
        }
        Collections.sort(arrayList);
        long accessId = XGPushConfig.getAccessId(this.context);
        if (accessId <= 0) {
            accessId = XGPush4Msdk.getQQAccessId(this.context);
        }
        y yVar2 = new y();
        yVar2.a = this.context.getPackageName();
        yVar2.c = accessId;
        yVar2.b = Constants.PUSH_SDK_VERSION;
        arrayList.add(0, yVar2);
        StringBuilder stringBuilder = new StringBuilder(1024);
        for (y yVar3 : arrayList) {
            stringBuilder.append(yVar3.a).append(",").append(yVar3.c).append(i.b);
        }
        return stringBuilder.toString();
    }

    private String domainToIp() {
        try {
            return InetAddress.getByName(Constants.UNSTALL_DOMAIN).getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            return "14.18.245.161";
        }
    }

    private void directStartWatchdog() {
        if (com.tencent.android.tpush.service.a.a.y == 0 || !loadWatchdog(this.context.getPackageName())) {
            return;
        }
        if (p.h(this.context)) {
            int watchdogPort = getWatchdogPort();
            List<com.tencent.android.tpush.data.a> registerInfo = CacheManager.getRegisterInfo(this.context);
            StringBuffer stringBuffer = new StringBuffer();
            StringBuffer stringBuffer2 = new StringBuffer();
            StringBuffer stringBuffer3 = new StringBuffer();
            for (com.tencent.android.tpush.data.a aVar : registerInfo) {
                stringBuffer.append(aVar.a).append(",");
                stringBuffer2.append(aVar.b).append(",");
                stringBuffer3.append(aVar.d).append(",");
            }
            String[] strArr = new String[7];
            strArr[0] = WatchdogPath;
            strArr[1] = getLocalXGApps();
            strArr[2] = String.valueOf(watchdogPort);
            strArr[3] = domainToIp();
            strArr[4] = new com.tencent.android.tpush.c.a(this.context).a();
            strArr[5] = "" + (XGPushConfig.isEnableDebug(this.context) ? "1" : "0");
            strArr[6] = "" + VERSION.SDK_INT;
            try {
                Runtime.getRuntime().exec(strArr);
                return;
            } catch (Throwable e) {
                com.tencent.android.tpush.a.a.c(TAG, "directStartWatchdog", e);
                return;
            }
        }
        com.tencent.android.tpush.a.a.h(TAG, "xg is disable.");
    }

    public void startWatchdog() {
        if (handler != null) {
            handler.post(new w(this));
        }
    }

    private static boolean loadWatchdog(String str) {
        if (!e.a(WatchdogPath)) {
            return true;
        }
        WatchdogPath = "";
        try {
            File file = new File(new StringBuffer(File.separator).append("data").append(File.separator).append("data").append(File.separator).append(str).append(File.separator).append("lib").append(File.separator).append(LIB_FULL_NAME).toString());
            boolean exists = file.exists();
            if (!exists) {
                return exists;
            }
            WatchdogPath = file.getAbsolutePath();
            return exists;
        } catch (Exception e) {
            com.tencent.android.tpush.a.a.h(TAG, "jniStartWatchdog loadWatchdog error:" + e.getMessage());
            return false;
        }
    }
}
