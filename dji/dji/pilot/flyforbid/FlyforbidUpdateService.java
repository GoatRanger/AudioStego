package dji.pilot.flyforbid;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.os.IBinder;
import android.preference.PreferenceManager;
import com.dji.frame.c.h;
import com.here.services.location.network.NetworkLocationApi.Options;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.FlyForbidElement;
import dji.midware.data.forbid.FlyForbidElementAirMap;
import dji.midware.data.forbid.NFZLogUtil;
import dji.midware.data.forbid.NfzAccountEvent;
import dji.midware.data.forbid.StrongWarningUnlockedElement;
import dji.midware.data.forbid.UnlimitAreaRecordElement;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.flyforbid.jsonbean.FlyforbidServerResult;
import dji.pilot.flyforbid.jsonbean.FlyforbidServerResultAirMap;
import dji.pilot.flyunlimit.jsonbean.UnlockListItem;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.b.f;
import dji.thirdparty.a.c;
import dji.thirdparty.afinal.b;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class FlyforbidUpdateService extends Service {
    public static final String a = "flyforbid_updte_at";
    public static final String b = "flyforbid_updte_at_airmap";
    public static final String c = "key_cur_airmap_flyforbid_version";
    public static boolean d = false;
    private static final String e = "flyforbid_inited_local";
    private static final String f = "flyforbid_reinit_local";
    private static int g = 0;
    private static boolean h = false;
    private static boolean i = false;
    private static final String[] t = new String[]{"flyforbid_airmap1.json", "flyforbid_airmap10.json", "flyforbid_airmap11.json", "flyforbid_airmap12.json", "flyforbid_airmap13.json", "flyforbid_airmap14.json", "flyforbid_airmap15.json", "flyforbid_airmap16.json", "flyforbid_airmap17.json", "flyforbid_airmap18.json", "flyforbid_airmap19.json", "flyforbid_airmap2.json", "flyforbid_airmap20.json", "flyforbid_airmap21.json", "flyforbid_airmap22.json", "flyforbid_airmap23.json", "flyforbid_airmap24.json", "flyforbid_airmap25.json", "flyforbid_airmap26.json", "flyforbid_airmap27.json", "flyforbid_airmap28.json", "flyforbid_airmap3.json", "flyforbid_airmap4.json", "flyforbid_airmap5.json", "flyforbid_airmap6.json", "flyforbid_airmap7.json", "flyforbid_airmap8.json", "flyforbid_airmap9.json"};
    private static final String[] u = new String[]{"89f3a374654d899401d34cae372cd007", "c684604f274a336b49f850c772892f5a", "cdef362f9e171df16532b723f7b8230a", "07a031d40aed45f1b3294a6bd975e7b5", "2927fed81319c6ca0a10e5a86e6eb34f", "d941ed8192ff2802d24e170482f877b7", "e193d11f98814058a9fd4cea27723fb0", "5477e2aacf0730e66da0382ce863a126", "dc085a699905b94b7b280b734b25dc28", "c085cedd7309b31ec2a338f4c2460108", "4c9bca6fe15efcadf96ad51ac350c9fb", "8ea1d74be15e0cb3cd6128c3fe4ea03a", "b3e691bd0f99cffc94400c64c38e6a0e", "a74c50418d66362a74a6d259d48139df", "e53128b467d5ec9e5cc02bf77f637d42", "0e57c0447549131d0c819b05e862046e", "a9138836fcb4830985b422d944acc7ea", "4de5a84ef5996b6bb980077f540f5d72", "37bf409fa1c4dffaaff5a83ef94eed70", "f442c33e9f1ad64638b38a7fb78eadf2", "b9c10be19d75d85cda86142b9a8dd1f5", "24df04e914931b2ed62c69c90fe9c5f3", "7054d9506cda54d70b231992c26a8e05", "0a0cee290aaee618fbf2c22bc8671aab", "0649efb896e2cdc406f87c20b1bfe00d", "9cd63e755c080c719e9a664a236f540c", "9cc7ad055f2273a47c90c9db7b312128", "fd3f6a0c9679ecf0fb3ca36c4ef15938"};
    private static final long v = 60000;
    private static boolean w = false;
    private static boolean x = false;
    private boolean[] A = new boolean[t.length];
    private Context j = null;
    private b k;
    private SharedPreferences l;
    private Editor m;
    private boolean n = false;
    private boolean o = false;
    private long p = 0;
    private long q = 0;
    private long r = 0;
    private int s = 0;
    private Thread y = new Thread(new Runnable(this) {
        final /* synthetic */ FlyforbidUpdateService a;

        {
            this.a = r1;
        }

        public void run() {
            while (FlyforbidUpdateService.i) {
                if (!FlyforbidUpdateService.d && DJINetWorkReceiver.a(this.a.j) && f.getInstance().c()) {
                    FlyforbidUpdateService.d = true;
                    dji.pilot.flyunlimit.b.getInstance(this.a.j).a(new dji.pilot.flyunlimit.b.f(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void a(List<UnlockListItem> list) {
                            FlyforbidUpdateService.x = true;
                        }

                        public void a() {
                        }
                    });
                }
                if (!ServiceManager.getInstance().isRemoteOK() || DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if ("".equals(a.getInstance(this.a).d())) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                } else {
                    ArrayList arrayList;
                    int size;
                    ArrayList arrayList2 = (ArrayList) this.a.k.c(UnlimitAreaRecordElement.class);
                    if (arrayList2 == null) {
                        arrayList = new ArrayList();
                    } else {
                        arrayList = arrayList2;
                    }
                    long currentTimeMillis = System.currentTimeMillis() / 1000;
                    String str = "unknown";
                    String o = f.getInstance().o();
                    for (size = arrayList.size() - 1; size >= 0; size--) {
                        UnlimitAreaRecordElement unlimitAreaRecordElement = (UnlimitAreaRecordElement) arrayList.get(size);
                        NFZLogUtil.LOGD("mCheckForbidAreaThread time_stamp -: " + (currentTimeMillis - unlimitAreaRecordElement.end_at));
                        if (currentTimeMillis < unlimitAreaRecordElement.begin_at || currentTimeMillis > unlimitAreaRecordElement.end_at) {
                            FlyforbidUpdateService.x = true;
                            this.a.k.f(unlimitAreaRecordElement);
                        }
                    }
                    arrayList2 = (ArrayList) this.a.k.c(StrongWarningUnlockedElement.class);
                    if (arrayList2 == null) {
                        arrayList = new ArrayList();
                    } else {
                        arrayList = arrayList2;
                    }
                    for (size = arrayList.size() - 1; size >= 0; size--) {
                        StrongWarningUnlockedElement strongWarningUnlockedElement = (StrongWarningUnlockedElement) arrayList.get(size);
                        if (currentTimeMillis < strongWarningUnlockedElement.begin_at || currentTimeMillis > strongWarningUnlockedElement.end_at) {
                            FlyforbidUpdateService.x = true;
                            this.a.k.f(strongWarningUnlockedElement);
                        }
                    }
                    NfzAccountEvent nfzAccountEvent;
                    if (FlyforbidUpdateService.x) {
                        nfzAccountEvent = new NfzAccountEvent();
                        nfzAccountEvent.setAccount(o);
                        nfzAccountEvent.setFlycsn(a.getInstance(this.a).d());
                        c.a().e(nfzAccountEvent);
                        NFZLogUtil.LOGD("mCheckForbidAreaThread deleted unlimit area");
                        FlyforbidUpdateService.x = false;
                    } else if (!FlyforbidUpdateService.w) {
                        FlyforbidUpdateService.w = true;
                        nfzAccountEvent = new NfzAccountEvent();
                        nfzAccountEvent.setAccount(o);
                        nfzAccountEvent.setFlycsn(a.getInstance(this.a).d());
                        c.a().e(nfzAccountEvent);
                        FlyforbidUpdateService.x = false;
                    }
                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e22) {
                        e22.printStackTrace();
                    }
                }
            }
        }
    });
    private Thread z = new Thread(new Runnable(this) {
        final /* synthetic */ FlyforbidUpdateService a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.f();
            this.a.h();
            while (FlyforbidUpdateService.h) {
                try {
                    if (this.a.n) {
                        FlyforbidUpdateService.h = false;
                    } else {
                        if (!this.a.o) {
                            this.a.s = DJIFlyForbidController.getInstance().getDataSize(false);
                            if (this.a.s == -1) {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            } else if (this.a.s < FlyforbidUpdateService.g) {
                                this.a.i();
                                this.a.s = DJIFlyForbidController.getInstance().getDataSize(false);
                                if (this.a.s >= FlyforbidUpdateService.g) {
                                    this.a.o = true;
                                    this.a.m.putBoolean(FlyforbidUpdateService.e, true);
                                    this.a.m.commit();
                                } else {
                                    continue;
                                }
                            } else {
                                this.a.o = true;
                            }
                        }
                        if (!g.b(this.a.j, FlyforbidUpdateService.c, "").equals(this.a.j.getString(R.string.versionname))) {
                            this.a.j();
                            DJIFlyForbidController.getInstance().refreshDatabase();
                        }
                        try {
                            Thread.sleep(Options.MIN_DESIRED_INTERVAL);
                        } catch (InterruptedException e2) {
                            e2.printStackTrace();
                        }
                    }
                } catch (OutOfMemoryError e3) {
                    System.gc();
                } catch (Exception e4) {
                    e4.printStackTrace();
                    return;
                }
            }
        }
    });

    public enum a {
        failed,
        appExpired
    }

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        this.j = getApplicationContext();
        this.l = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        this.m = this.l.edit();
        this.s = 0;
        DJIFlyForbidController.getInstance(this.j);
        this.k = DJIFlyForbidController.getInstance().getDb();
        a.getInstance(this);
        if (!h) {
            h = true;
            this.z.start();
        }
        if (!i) {
            i = true;
            this.y.start();
        }
        try {
            com.dji.frame.c.c.c(this.j).a(FlyForbidElement.class);
        } catch (Exception e) {
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    public void onDestroy() {
        super.onDestroy();
        h = false;
        i = false;
        this.s = 0;
    }

    private void f() {
        this.r = g();
        long j = this.l.getLong(a, 0);
        boolean z = this.l.getBoolean(e, false);
        if (this.l.getBoolean(f, true)) {
            this.m.putBoolean(f, false);
            this.m.commit();
            try {
                this.k.a(FlyForbidElement.class);
            } catch (Exception e) {
            }
        }
        if (j != 0 && j < this.r && z) {
            this.m.putBoolean(e, false);
            this.m.commit();
            try {
                this.k.a(FlyForbidElement.class);
            } catch (Exception e2) {
            }
        }
    }

    private long g() {
        FlyforbidServerResult flyforbidServerResult = (FlyforbidServerResult) h.b(a(this.j.getResources().openRawResource(R.raw.flyforbid), "utf-8"), FlyforbidServerResult.class);
        long j = -1;
        if (flyforbidServerResult != null) {
            g = flyforbidServerResult.release_limits.size();
            for (FlyForbidElement flyForbidElement : flyforbidServerResult.release_limits) {
                long j2;
                if (j < flyForbidElement.updated_at) {
                    j2 = flyForbidElement.updated_at;
                } else {
                    j2 = j;
                }
                j = j2;
            }
        }
        if (this.p < j) {
            this.p = j;
            this.m.putLong(a, this.p);
            this.m.commit();
        }
        return j;
    }

    private long h() {
        AssetManager assets = getAssets();
        try {
            for (String str : assets.list("flyforbid_airmap")) {
                if (str != null && !str.equals("") && !str.isEmpty() && !a(str, a(assets.open("flyforbid_airmap/" + str)))) {
                    c.a().e(a.failed);
                    break;
                }
            }
            if (!k()) {
                c.a().e(a.failed);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        NFZLogUtil.LOGD("airmap check md5 done");
        return -1;
    }

    private void i() {
        this.p = this.l.getLong(a, 0);
        FlyforbidServerResult flyforbidServerResult = (FlyforbidServerResult) h.b(a(this.j.getResources().openRawResource(R.raw.flyforbid), "utf-8"), FlyforbidServerResult.class);
        if (!(flyforbidServerResult == null || flyforbidServerResult.release_limits == null || flyforbidServerResult.release_limits.size() <= 0)) {
            this.k.a(FlyForbidElement.class);
            this.k.b(new FlyForbidElement());
            for (Object obj : flyforbidServerResult.release_limits) {
                if (obj.city == null) {
                    obj.city = "";
                }
                if (obj.name == null) {
                    obj.name = "";
                }
                if (obj.points == null) {
                    obj.points = "";
                }
                this.k.c(obj);
            }
            this.k.a();
        }
        NFZLogUtil.LOGD("checkInitFromLocalJson dji done");
        this.m.putLong(a, this.p);
        this.m.commit();
    }

    private void j() {
        FlyforbidServerResultAirMap flyforbidServerResultAirMap;
        this.q = this.l.getLong(b, 0);
        AssetManager assets = getAssets();
        try {
            String[] list = assets.list("flyforbid_airmap");
            Object obj = 1;
            int length = list.length;
            int i = 0;
            while (i < length) {
                flyforbidServerResultAirMap = (FlyforbidServerResultAirMap) h.b(a(assets.open("flyforbid_airmap/" + list[i]), "utf-8"), FlyforbidServerResultAirMap.class);
                if (!(flyforbidServerResultAirMap == null || flyforbidServerResultAirMap.release_limits == null || flyforbidServerResultAirMap.release_limits.size() <= 0)) {
                    if (obj != null) {
                        this.k.a(FlyForbidElementAirMap.class);
                        obj = null;
                    }
                    this.k.b(new FlyForbidElementAirMap());
                    for (Object obj2 : flyforbidServerResultAirMap.release_limits) {
                        if (obj2.points == null) {
                            obj2.points = "";
                        }
                        if (obj2.city == null) {
                            obj2.city = "";
                        }
                        if (obj2.name == null) {
                            obj2.name = "";
                        }
                        this.k.c(obj2);
                    }
                    this.k.a();
                }
                i++;
                obj = obj;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dji.pilot.c.a.C) {
            flyforbidServerResultAirMap = (FlyforbidServerResultAirMap) h.b(a(this.j.getResources().openRawResource(R.raw.fake_airmap), "utf-8"), FlyforbidServerResultAirMap.class);
            if (!(flyforbidServerResultAirMap == null || flyforbidServerResultAirMap.release_limits == null || flyforbidServerResultAirMap.release_limits.size() <= 0)) {
                this.k.b(new FlyForbidElementAirMap());
                for (Object obj22 : flyforbidServerResultAirMap.release_limits) {
                    if (obj22.points == null) {
                        obj22.points = "";
                    }
                    this.k.c(obj22);
                }
                this.k.a();
            }
        }
        NFZLogUtil.LOGD("checkInitFromLocalJson airmap done");
        NFZLogUtil.savedLOG("nfz log 0 s d");
        this.m.putLong(b, this.q);
        this.m.commit();
        g.a(this.j, c, this.j.getString(R.string.versionname));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.io.InputStream r6, java.lang.String r7) {
        /*
        r5 = this;
        r0 = 4096; // 0x1000 float:5.74E-42 double:2.0237E-320;
        r0 = new char[r0];
        if (r7 == 0) goto L_0x000e;
    L_0x0006:
        r1 = "";
        r1 = r7.equals(r1);	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        if (r1 == 0) goto L_0x0010;
    L_0x000e:
        r7 = "utf-8";
    L_0x0010:
        r1 = new java.io.BufferedReader;	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r2 = new java.io.InputStreamReader;	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r2.<init>(r6, r7);	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r1.<init>(r2);	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r2 = new java.lang.StringBuffer;	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r2.<init>();	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
    L_0x001f:
        r3 = r1.read(r0);	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        r4 = -1;
        if (r3 == r4) goto L_0x0032;
    L_0x0026:
        r4 = 0;
        r2.append(r0, r4, r3);	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        goto L_0x001f;
    L_0x002b:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x002f:
        r0 = "";
    L_0x0031:
        return r0;
    L_0x0032:
        r0 = r2.toString();	 Catch:{ UnsupportedEncodingException -> 0x002b, IOException -> 0x0037 }
        goto L_0x0031;
    L_0x0037:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot.flyforbid.FlyforbidUpdateService.a(java.io.InputStream, java.lang.String):java.lang.String");
    }

    private String a(InputStream inputStream) {
        byte[] bArr = new byte[8192];
        try {
            int read;
            MessageDigest instance = MessageDigest.getInstance("MD5");
            while (true) {
                read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                instance.update(bArr, 0, read);
            }
            inputStream.close();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : instance.digest()) {
                stringBuilder.append(Integer.toHexString((b >> 4) & 15));
                stringBuilder.append(Integer.toHexString(b & 15));
            }
            return stringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private boolean a(String str, String str2) {
        boolean z = false;
        for (int i = 0; i < t.length; i++) {
            if (t[i].compareTo(str) == 0) {
                boolean[] zArr = this.A;
                if (u[i].compareTo(str2) == 0) {
                    z = true;
                }
                zArr[i] = z;
                return this.A[i];
            }
        }
        return false;
    }

    private boolean k() {
        for (boolean z : this.A) {
            if (!z) {
                return false;
            }
        }
        return true;
    }
}
