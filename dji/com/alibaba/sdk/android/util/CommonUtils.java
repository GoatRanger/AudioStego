package com.alibaba.sdk.android.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Process;
import com.alibaba.sdk.android.ConfigManager;
import com.alibaba.sdk.android.ResultCode;
import com.alibaba.sdk.android.b.a;
import com.alibaba.sdk.android.callback.FailureCallback;
import com.alibaba.sdk.android.message.Message;
import com.alibaba.sdk.android.task.InitWaitTask;
import com.tencent.android.tpush.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import javax.crypto.Cipher;

public class CommonUtils {
    private static String a = "\\u";
    private static String b;

    public static boolean isDebuggable() {
        if (ConfigManager.DEBUG) {
            return true;
        }
        try {
            if ((a.a.getPackageManager().getApplicationInfo(a.a.getPackageName(), 16384).flags & 2) == 0) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static String native2Ascii(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            String toHexString;
            char charAt = str.charAt(i);
            if (charAt > 'ÿ') {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append(a);
                String toHexString2 = Integer.toHexString(charAt >> 8);
                if (toHexString2.length() == 1) {
                    stringBuilder2.append("0");
                }
                stringBuilder2.append(toHexString2);
                toHexString = Integer.toHexString(charAt & 255);
                if (toHexString.length() == 1) {
                    stringBuilder2.append("0");
                }
                stringBuilder2.append(toHexString);
                toHexString = stringBuilder2.toString();
            } else {
                toHexString = Character.toString(charAt);
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    public static void onFailure(FailureCallback failureCallback, Message message) {
        a.f.postUITask(new a(failureCallback, message));
    }

    public static void onFailure(FailureCallback failureCallback, ResultCode resultCode) {
        a.f.postUITask(new b(failureCallback, resultCode));
    }

    public static void onFailure(FailureCallback failureCallback, int i, String str) {
        a.f.postUITask(new c(failureCallback, i, str));
    }

    public static void toastSystemException() {
        toast("com_taobao_tae_sdk_system_exception");
    }

    public static void toast(String str) {
        a.f.postUITask(new d(str));
    }

    private static PublicKey a(byte[] bArr) {
        try {
            try {
                return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr));
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    public static byte[] rsaDecrypt(byte[] bArr, byte[] bArr2) {
        Closeable byteArrayOutputStream;
        Throwable e;
        try {
            Key a = a(bArr2);
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(2, a);
            int blockSize = instance.getBlockSize();
            byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < bArr.length) {
                try {
                    int length;
                    if (bArr.length - i < blockSize) {
                        length = bArr.length - i;
                    } else {
                        length = blockSize;
                    }
                    byteArrayOutputStream.write(instance.doFinal(bArr, i, length));
                    i += blockSize;
                } catch (Exception e2) {
                    e = e2;
                }
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            IOUtils.closeQuietly(byteArrayOutputStream);
            return toByteArray;
        } catch (Exception e3) {
            e = e3;
            byteArrayOutputStream = null;
            try {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                e = th;
                IOUtils.closeQuietly(byteArrayOutputStream);
                throw e;
            }
        } catch (Throwable th2) {
            e = th2;
            byteArrayOutputStream = null;
            IOUtils.closeQuietly(byteArrayOutputStream);
            throw e;
        }
    }

    public static byte[] rsaEncrypt(byte[] bArr, byte[] bArr2) {
        Throwable e;
        Closeable byteArrayOutputStream;
        try {
            Key a = a(bArr2);
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, a);
            int blockSize = instance.getBlockSize();
            byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < bArr.length) {
                try {
                    int length;
                    if (bArr.length - i < blockSize) {
                        length = bArr.length - i;
                    } else {
                        length = blockSize;
                    }
                    byteArrayOutputStream.write(instance.doFinal(bArr, i, length));
                    i += blockSize;
                } catch (Exception e2) {
                    e = e2;
                }
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            IOUtils.closeQuietly(byteArrayOutputStream);
            return toByteArray;
        } catch (Exception e3) {
            e = e3;
            byteArrayOutputStream = null;
            try {
                throw new RuntimeException(e);
            } catch (Throwable th) {
                e = th;
                IOUtils.closeQuietly(byteArrayOutputStream);
                throw e;
            }
        } catch (Throwable th2) {
            e = th2;
            byteArrayOutputStream = null;
            IOUtils.closeQuietly(byteArrayOutputStream);
            throw e;
        }
    }

    public static boolean isNetworkAvailable() {
        if (a.a == null) {
            return true;
        }
        return isNetworkAvailable(a.a);
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        if (connectivityManager == null) {
            return false;
        }
        NetworkInfo[] allNetworkInfo = connectivityManager.getAllNetworkInfo();
        if (allNetworkInfo == null) {
            return false;
        }
        boolean z;
        for (NetworkInfo networkInfo : allNetworkInfo) {
            if (networkInfo != null && (networkInfo.getState() == State.CONNECTED || networkInfo.getState() == State.CONNECTING)) {
                z = true;
                break;
            }
        }
        z = false;
        return z;
    }

    public static void startInitWaitTask(Activity activity, FailureCallback failureCallback, Runnable runnable, String str) {
        new InitWaitTask(activity, failureCallback, runnable, str).execute(new Void[0]);
    }

    public static boolean isEqual(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    public static String getAndroidManifestMetadata(Context context, String str) {
        String str2 = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                Object obj = applicationInfo.metaData.get(str);
                if (obj != null) {
                    str2 = obj.toString();
                }
            }
        } catch (Exception e) {
        }
        return str2;
    }

    public static String toString(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    public static int isApplicationDefaultProcess() {
        if (a.a == null) {
            return -1;
        }
        String currentProcessName = getCurrentProcessName();
        if (currentProcessName != null) {
            return currentProcessName.equals(a.a.getPackageName()) ? 1 : 0;
        } else {
            return -1;
        }
    }

    public static String getCurrentProcessName() {
        if (a.a == null) {
            return null;
        }
        if (b != null) {
            return b;
        }
        try {
            List<RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) a.a.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return null;
            }
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == myPid) {
                    b = runningAppProcessInfo.processName;
                    return runningAppProcessInfo.processName;
                }
            }
            return null;
        } catch (Exception e) {
        }
    }

    public static boolean isConnectionTimeout(Throwable th) {
        boolean z = (th instanceof SocketTimeoutException) || (th instanceof EOFException) || (th instanceof ConnectException);
        if (z) {
            return true;
        }
        Throwable cause = th.getCause();
        if (cause == null) {
            return false;
        }
        if ((cause instanceof SocketTimeoutException) || (cause instanceof EOFException) || (cause instanceof ConnectException)) {
            return true;
        }
        return false;
    }
}
