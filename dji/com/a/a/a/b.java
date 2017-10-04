package com.a.a.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public final class b {
    @SuppressLint({"SimpleDateFormat"})
    private static final DateFormat a = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");

    public static class a extends ThreadPoolExecutor {
        public a() {
            super(1, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), new c());
        }
    }

    private static class b extends Thread {
        private static final AtomicInteger a = new AtomicInteger(1);

        public b(Runnable runnable) {
            super(runnable, "SegmentAnalytics-" + a.getAndIncrement());
        }

        public void run() {
            Process.setThreadPriority(10);
            super.run();
        }
    }

    public static class c implements ThreadFactory {
        public Thread newThread(Runnable runnable) {
            return new b(runnable);
        }
    }

    public static class d<K, V> extends ConcurrentHashMap<K, V> {
        public d(Map<? extends K, ? extends V> map) {
            super(map);
        }

        public V put(K k, V v) {
            if (k == null || v == null) {
                return null;
            }
            return super.put(k, v);
        }
    }

    public static String a(Date date) {
        return a.format(date);
    }

    public static boolean a(Context context, String str) {
        return context.checkCallingOrSelfPermission(str) == 0;
    }

    public static <T> T b(Context context, String str) {
        return context.getSystemService(str);
    }

    public static boolean a(CharSequence charSequence) {
        return TextUtils.isEmpty(charSequence) || TextUtils.getTrimmedLength(charSequence) == 0;
    }

    public static boolean a(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static boolean a(Map map) {
        return map == null || map.size() == 0;
    }

    public static SharedPreferences a(Context context) {
        return context.getSharedPreferences("analytics-android", 0);
    }

    public static boolean b(Context context) {
        if (!a(context, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) b(context, "connectivity")).getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
        return z;
    }

    public static boolean a(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static BufferedReader a(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream));
    }

    public static String b(InputStream inputStream) throws IOException {
        return a(a(inputStream));
    }

    public static String a(BufferedReader bufferedReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuilder.toString();
            }
            stringBuilder.append(readLine);
        }
    }

    public static <T> Map<String, T> a() {
        return new d();
    }

    public static void a(File file) throws IOException {
        if (!file.exists() && !file.mkdirs() && !file.isDirectory()) {
            throw new IOException("Could not create directory at " + file);
        }
    }

    public static void a(String str, Object... objArr) {
        Log.d("Segment", String.format(str, objArr));
    }

    public static void a(Throwable th, String str, Object... objArr) {
        Log.e("Segment", String.format(str, objArr), th);
    }
}
