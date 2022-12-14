package com.mob.commons.appcollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import com.here.odnp.config.OdnpConfigStatic;
import com.mob.commons.a;
import com.mob.commons.c;
import com.mob.commons.e;
import com.mob.tools.MobHandlerThread;
import com.mob.tools.MobLog;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class PackageCollector {
    private static PackageCollector b;
    private final String[] a = new String[]{"android.intent.action.PACKAGE_ADDED", "android.intent.action.PACKAGE_CHANGED", "android.intent.action.PACKAGE_REMOVED", "android.intent.action.PACKAGE_REPLACED"};
    private Context c;
    private DeviceHelper d;
    private Hashon e;
    private Handler f;

    public static synchronized boolean register(Context context, String str) {
        synchronized (PackageCollector.class) {
            startCollector(context);
        }
        return true;
    }

    public static synchronized void startCollector(Context context) {
        synchronized (PackageCollector.class) {
            if (b == null) {
                b = new PackageCollector(context);
                b.a();
            }
        }
    }

    private PackageCollector(Context context) {
        this.c = context.getApplicationContext();
        this.d = DeviceHelper.getInstance(this.c);
        this.e = new Hashon();
    }

    private void a() {
        MobHandlerThread anonymousClass1 = new MobHandlerThread(this) {
            final /* synthetic */ PackageCollector a;

            {
                this.a = r1;
            }

            public void run() {
                e.a(new File(R.getCacheRoot(this.a.c), "comm/locks/.pkg_lock"), new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        if (a.d(this.a.a.c)) {
                            this.a.a.b();
                        }
                        this.a.a.e();
                        this.a.a();
                    }
                });
            }

            private void a() {
                super.run();
            }
        };
        anonymousClass1.start();
        this.f = new Handler(anonymousClass1.getLooper(), new Callback(this) {
            final /* synthetic */ PackageCollector a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                this.a.f();
                return false;
            }
        });
    }

    private void b() {
        ArrayList c = c();
        if (c == null || c.isEmpty()) {
            c = this.d.getInstalledApp(false);
            a(a.m(this.c), "APPS_ALL", c);
            a(c);
            a(a.a(this.c) + (a.e(this.c) * 1000));
            return;
        }
        long a = a.a(this.c);
        if (a >= d()) {
            ArrayList installedApp = this.d.getInstalledApp(false);
            a(a.m(this.c), "APPS_ALL", installedApp);
            a(installedApp);
            a(a + (a.e(this.c) * 1000));
            return;
        }
        f();
    }

    private ArrayList<HashMap<String, String>> c() {
        File file = new File(R.getCacheRoot(this.c), "comm/dbs/.al");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            try {
                ArrayList<HashMap<String, String>> arrayList = new ArrayList();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file)), "utf-8"));
                for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                    HashMap fromJson = this.e.fromJson(readLine);
                    if (fromJson != null) {
                        arrayList.add(fromJson);
                    }
                }
                bufferedReader.close();
                return arrayList;
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
            }
        }
        return new ArrayList();
    }

    private void a(long j, String str, ArrayList<HashMap<String, String>> arrayList) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", str);
        hashMap.put("list", arrayList);
        hashMap.put("datetime", Long.valueOf(a.a(this.c)));
        c.a(this.c).a(j, hashMap);
    }

    private void a(ArrayList<HashMap<String, String>> arrayList) {
        File file = new File(R.getCacheRoot(this.c), "comm/dbs/.al");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new GZIPOutputStream(new FileOutputStream(file)), "utf-8");
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                outputStreamWriter.append(this.e.fromHashMap((HashMap) it.next())).append('\n');
            }
            outputStreamWriter.flush();
            outputStreamWriter.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private void a(long j) {
        File file = new File(R.getCacheRoot(this.c), "comm/dbs/.nulal");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            dataOutputStream.writeLong(j);
            dataOutputStream.flush();
            dataOutputStream.close();
        } catch (Throwable th) {
            MobLog.getInstance().d(th);
        }
    }

    private long d() {
        File file = new File(R.getCacheRoot(this.c), "comm/dbs/.nulal");
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists()) {
            try {
                DataInputStream dataInputStream = new DataInputStream(new FileInputStream(file));
                long readLong = dataInputStream.readLong();
                dataInputStream.close();
                return readLong;
            } catch (Throwable th) {
                MobLog.getInstance().d(th);
            }
        }
        return 0;
    }

    private void e() {
        IntentFilter intentFilter = new IntentFilter();
        for (String addAction : b.a) {
            intentFilter.addAction(addAction);
        }
        intentFilter.addDataScheme("package");
        this.c.registerReceiver(new BroadcastReceiver(this) {
            final /* synthetic */ PackageCollector a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                String str = null;
                if (intent != null) {
                    str = intent.getAction();
                }
                if (this.a.a(str) && this.a.f != null) {
                    this.a.f.removeMessages(1);
                    this.a.f.sendEmptyMessageDelayed(1, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
                }
            }
        }, intentFilter);
    }

    private boolean a(String str) {
        for (String equals : this.a) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private void f() {
        ArrayList c = c();
        ArrayList installedApp = this.d.getInstalledApp(false);
        if (c == null || c.isEmpty()) {
            a(a.m(this.c), "APPS_ALL", installedApp);
            a(installedApp);
            a(a.a(this.c) + (a.e(this.c) * 1000));
            return;
        }
        ArrayList a = a(installedApp, c);
        if (!a.isEmpty()) {
            a(a.a(this.c), "APPS_INCR", a);
        }
        c = a(c, installedApp);
        if (!c.isEmpty()) {
            a(a.a(this.c), "UNINSTALL", c);
        }
        a(installedApp);
        a(a.a(this.c) + (a.e(this.c) * 1000));
    }

    private ArrayList<HashMap<String, String>> a(ArrayList<HashMap<String, String>> arrayList, ArrayList<HashMap<String, String>> arrayList2) {
        ArrayList<HashMap<String, String>> arrayList3 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap hashMap = (HashMap) it.next();
            String str = (String) hashMap.get("pkg");
            if (!TextUtils.isEmpty(str)) {
                Object obj;
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    if (str.equals(((HashMap) it2.next()).get("pkg"))) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    arrayList3.add(hashMap);
                }
            }
        }
        return arrayList3;
    }
}
