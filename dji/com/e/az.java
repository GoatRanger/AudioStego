package com.e;

import android.content.Context;
import android.os.HandlerThread;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import com.here.android.mpa.routing.Route;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class az {
    HandlerThread a = null;
    boolean b = false;
    private Context c;
    private int d = 9;
    private ArrayList<ay> e = new ArrayList();
    private int f = -113;
    private TelephonyManager g = null;
    private Object h;
    private long i = 0;
    private JSONObject j;
    private PhoneStateListener k;
    private CellLocation l;
    private boolean m = false;
    private Object n = new Object();
    private PhoneStateListener o = null;

    class a extends HandlerThread {
        final /* synthetic */ az a;

        public a(az azVar, String str) {
            this.a = azVar;
            super(str);
        }

        protected void onLooperPrepared() {
            try {
                super.onLooperPrepared();
                synchronized (this.a.n) {
                    if (!this.a.m) {
                        this.a.p();
                    }
                }
            } catch (Throwable th) {
            }
        }

        public void run() {
            try {
                super.run();
            } catch (Throwable th) {
            }
        }
    }

    public az(Context context, JSONObject jSONObject) {
        if (context == null) {
            this.j = jSONObject;
            this.c = context;
        } else {
            this.j = jSONObject;
            this.c = context;
        }
        if (this.g == null) {
            this.g = (TelephonyManager) br.a(this.c, "phone");
        }
    }

    private CellLocation a(List<?> list) {
        Throwable th;
        if (list == null || list.isEmpty()) {
            return null;
        }
        int i;
        CellLocation cdmaCellLocation;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        CellLocation cellLocation = null;
        int i2 = 0;
        CellLocation cellLocation2 = null;
        for (int i3 = 0; i3 < list.size(); i3++) {
            Object obj = list.get(i3);
            if (obj != null) {
                try {
                    Object cast;
                    Class loadClass = systemClassLoader.loadClass("android.telephony.CellInfoGsm");
                    Class loadClass2 = systemClassLoader.loadClass("android.telephony.CellInfoWcdma");
                    Class loadClass3 = systemClassLoader.loadClass("android.telephony.CellInfoLte");
                    Class loadClass4 = systemClassLoader.loadClass("android.telephony.CellInfoCdma");
                    if (loadClass.isInstance(obj)) {
                        i = 1;
                        try {
                            cast = loadClass.cast(obj);
                        } catch (Throwable th2) {
                            th = th2;
                            i2 = i;
                            bc.a(th, "CgiManager", "getCgi");
                        }
                    } else if (loadClass2.isInstance(obj)) {
                        i = 2;
                        cast = loadClass2.cast(obj);
                    } else if (loadClass3.isInstance(obj)) {
                        i = 3;
                        cast = loadClass3.cast(obj);
                    } else if (loadClass4.isInstance(obj)) {
                        i = 4;
                        cast = loadClass4.cast(obj);
                    } else {
                        i = 0;
                        cast = null;
                    }
                    if (i > 0) {
                        Object a = bp.a(cast, "getCellIdentity", new Object[0]);
                        if (a == null) {
                            i2 = i;
                        } else if (i == 4) {
                            cdmaCellLocation = new CdmaCellLocation();
                            try {
                                cdmaCellLocation.setCellLocationData(bp.b(a, "getBasestationId", new Object[0]), bp.b(a, "getLatitude", new Object[0]), bp.b(a, "getLongitude", new Object[0]), bp.b(a, "getSystemId", new Object[0]), bp.b(a, "getNetworkId", new Object[0]));
                                cellLocation2 = cellLocation;
                                break;
                            } catch (Throwable th3) {
                                th = th3;
                                cellLocation2 = cdmaCellLocation;
                                i2 = i;
                            }
                        } else if (i == 3) {
                            r3 = bp.b(a, "getTac", new Object[0]);
                            r2 = bp.b(a, "getCi", new Object[0]);
                            cdmaCellLocation = new GsmCellLocation();
                            try {
                                cdmaCellLocation.setLacAndCid(r3, r2);
                                r12 = cellLocation2;
                                cellLocation2 = cdmaCellLocation;
                                cdmaCellLocation = r12;
                                break;
                            } catch (Throwable th4) {
                                th = th4;
                                cellLocation = cdmaCellLocation;
                                i2 = i;
                            }
                        } else {
                            r3 = bp.b(a, "getLac", new Object[0]);
                            r2 = bp.b(a, "getCid", new Object[0]);
                            cdmaCellLocation = new GsmCellLocation();
                            try {
                                cdmaCellLocation.setLacAndCid(r3, r2);
                                r12 = cellLocation2;
                                cellLocation2 = cdmaCellLocation;
                                cdmaCellLocation = r12;
                                break;
                            } catch (Throwable th5) {
                                th = th5;
                                cellLocation = cdmaCellLocation;
                                i2 = i;
                            }
                        }
                    } else {
                        i2 = i;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    bc.a(th, "CgiManager", "getCgi");
                }
            }
        }
        i = i2;
        cdmaCellLocation = cellLocation2;
        cellLocation2 = cellLocation;
        return i != 4 ? cellLocation2 : cdmaCellLocation;
    }

    private ay a(NeighboringCellInfo neighboringCellInfo) {
        if (br.c() < 5) {
            return null;
        }
        try {
            ay ayVar = new ay(1);
            String[] a = br.a(this.g);
            ayVar.a = a[0];
            ayVar.b = a[1];
            ayVar.c = bp.b(neighboringCellInfo, "getLac", new Object[0]);
            ayVar.d = neighboringCellInfo.getCid();
            ayVar.j = br.a(neighboringCellInfo.getRssi());
            return ayVar;
        } catch (Throwable th) {
            bc.a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    private void a(int i) {
        if (i == -113) {
            this.f = -113;
            return;
        }
        this.f = i;
        switch (this.d) {
            case 1:
            case 2:
                if (!this.e.isEmpty()) {
                    try {
                        ((ay) this.e.get(0)).j = this.f;
                        return;
                    } catch (Throwable th) {
                        bc.a(th, "CgiManager", "hdlCgiSigStrenChange");
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }

    private void a(CellLocation cellLocation, boolean z) {
        if (cellLocation != null && this.g != null) {
            this.e.clear();
            if (a(cellLocation)) {
                this.d = 1;
                this.e.add(b(cellLocation));
                if (!z) {
                    List<NeighboringCellInfo> neighboringCellInfo = this.g.getNeighboringCellInfo();
                    if (neighboringCellInfo != null && !neighboringCellInfo.isEmpty()) {
                        for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
                            if (neighboringCellInfo2 != null && a(neighboringCellInfo2.getLac(), neighboringCellInfo2.getCid())) {
                                ay a = a(neighboringCellInfo2);
                                if (!(a == null || this.e.contains(a))) {
                                    this.e.add(a);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean a(int i, int i2) {
        return (i == -1 || i == 0 || i > 65535 || i2 == -1 || i2 == 0 || i2 == 65535 || i2 >= Route.WHOLE_ROUTE) ? false : true;
    }

    private synchronized CellLocation b(boolean z, boolean z2) {
        CellLocation l;
        br.a();
        this.b = z;
        if (!(this.b || this.g == null)) {
            l = l();
            if (!a(l)) {
                l = m();
            }
            if (a(l)) {
                this.l = l;
            }
        }
        if (a(this.l)) {
            switch (br.a(this.b, this.l, this.c)) {
                case 1:
                    a(this.l, z2);
                    break;
                case 2:
                    b(this.l, z2);
                    break;
            }
            l = this.l;
        } else {
            l = null;
        }
        return l;
    }

    private ay b(CellLocation cellLocation) {
        GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
        ay ayVar = new ay(1);
        String[] a = br.a(this.g);
        ayVar.a = a[0];
        ayVar.b = a[1];
        ayVar.c = gsmCellLocation.getLac();
        ayVar.d = gsmCellLocation.getCid();
        ayVar.j = this.f;
        return ayVar;
    }

    private void b(CellLocation cellLocation, boolean z) {
        Object obj = 1;
        if (cellLocation != null) {
            this.e.clear();
            if (br.c() >= 5) {
                try {
                    if (this.h != null) {
                        Object obj2;
                        Field declaredField = cellLocation.getClass().getDeclaredField("mGsmCellLoc");
                        if (!declaredField.isAccessible()) {
                            declaredField.setAccessible(true);
                        }
                        CellLocation cellLocation2 = (GsmCellLocation) declaredField.get(cellLocation);
                        if (cellLocation2 != null && a(cellLocation2)) {
                            a(cellLocation2, z);
                            obj2 = 1;
                            if (obj2 != null) {
                                return;
                            }
                        }
                        obj2 = null;
                        if (obj2 != null) {
                            return;
                        }
                    }
                } catch (Throwable th) {
                    bc.a(th, "CgiManager", "hdlCdmaLocChange");
                    return;
                }
                if (a(cellLocation)) {
                    this.d = 2;
                    String[] a = br.a(this.g);
                    ay ayVar = new ay(2);
                    ayVar.a = a[0];
                    ayVar.b = a[1];
                    ayVar.g = bp.b(cellLocation, "getSystemId", new Object[0]);
                    ayVar.h = bp.b(cellLocation, "getNetworkId", new Object[0]);
                    ayVar.i = bp.b(cellLocation, "getBaseStationId", new Object[0]);
                    ayVar.j = this.f;
                    ayVar.e = bp.b(cellLocation, "getBaseStationLatitude", new Object[0]);
                    ayVar.f = bp.b(cellLocation, "getBaseStationLongitude", new Object[0]);
                    if (ayVar.e < 0 || ayVar.f < 0 || ayVar.e == Integer.MAX_VALUE || ayVar.f == Integer.MAX_VALUE || (ayVar.e == ayVar.f && ayVar.e > 0)) {
                        ayVar.e = 0;
                        ayVar.f = 0;
                        obj = null;
                    }
                    if (obj == null) {
                    }
                    if (!this.e.contains(ayVar)) {
                        this.e.add(ayVar);
                    }
                }
            }
        }
    }

    public static int k() {
        int i = 0;
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            i = 1;
        } catch (Throwable th) {
        }
        if (i != 0) {
            return i;
        }
        try {
            Class.forName("android.telephony.TelephonyManager2");
            return 2;
        } catch (Throwable th2) {
            return i;
        }
    }

    private CellLocation l() {
        CellLocation cellLocation = null;
        TelephonyManager telephonyManager = this.g;
        if (telephonyManager == null) {
            return cellLocation;
        }
        CellLocation cellLocation2;
        try {
            cellLocation2 = telephonyManager.getCellLocation();
        } catch (Throwable th) {
            cellLocation2 = cellLocation;
        }
        if (a(cellLocation2)) {
            return cellLocation2;
        }
        try {
            cellLocation2 = a((List) bp.a(telephonyManager, "getAllCellInfo", new Object[0]));
        } catch (NoSuchMethodException e) {
        } catch (Throwable th2) {
        }
        if (a(cellLocation2)) {
            return cellLocation2;
        }
        try {
            Object a = bp.a(telephonyManager, "getCellLocationExt", Integer.valueOf(1));
            cellLocation2 = a != null ? (CellLocation) a : cellLocation2;
        } catch (NoSuchMethodException e2) {
        } catch (Throwable th3) {
        }
        if (a(cellLocation2)) {
            return cellLocation2;
        }
        try {
            a = bp.a(telephonyManager, "getCellLocationGemini", Integer.valueOf(1));
            cellLocation2 = a != null ? (CellLocation) a : cellLocation2;
        } catch (NoSuchMethodException e3) {
        } catch (Throwable th4) {
        }
        return a(cellLocation2) ? cellLocation2 : cellLocation2;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.telephony.CellLocation m() {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.h;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return r1;
    L_0x0006:
        r2 = r7.n();	 Catch:{ Throwable -> 0x0070 }
        r3 = r2.isInstance(r0);	 Catch:{ Throwable -> 0x0070 }
        if (r3 == 0) goto L_0x0096;
    L_0x0010:
        r3 = r2.cast(r0);	 Catch:{ Throwable -> 0x0070 }
        r4 = "getCellLocation";
        r0 = 0;
        r0 = new java.lang.Object[r0];	 Catch:{ NoSuchMethodException -> 0x005a, Throwable -> 0x005d }
        r2 = com.e.bp.a(r3, r4, r0);	 Catch:{ NoSuchMethodException -> 0x005a, Throwable -> 0x005d }
    L_0x001d:
        if (r2 != 0) goto L_0x002e;
    L_0x001f:
        r0 = 1;
        r0 = new java.lang.Object[r0];	 Catch:{ NoSuchMethodException -> 0x0094, Throwable -> 0x0067 }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ NoSuchMethodException -> 0x0094, Throwable -> 0x0067 }
        r0[r5] = r6;	 Catch:{ NoSuchMethodException -> 0x0094, Throwable -> 0x0067 }
        r2 = com.e.bp.a(r3, r4, r0);	 Catch:{ NoSuchMethodException -> 0x0094, Throwable -> 0x0067 }
    L_0x002e:
        if (r2 != 0) goto L_0x0085;
    L_0x0030:
        r0 = "getCellLocationGemini";
        r4 = 1;
        r4 = new java.lang.Object[r4];	 Catch:{ NoSuchMethodException -> 0x007a, Throwable -> 0x007d }
        r5 = 0;
        r6 = 1;
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ NoSuchMethodException -> 0x007a, Throwable -> 0x007d }
        r4[r5] = r6;	 Catch:{ NoSuchMethodException -> 0x007a, Throwable -> 0x007d }
        r0 = com.e.bp.a(r3, r0, r4);	 Catch:{ NoSuchMethodException -> 0x007a, Throwable -> 0x007d }
    L_0x0041:
        if (r0 != 0) goto L_0x0054;
    L_0x0043:
        r0 = "getAllCellInfo";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ NoSuchMethodException -> 0x0087, Throwable -> 0x008a }
        r0 = com.e.bp.a(r3, r0, r2);	 Catch:{ NoSuchMethodException -> 0x0087, Throwable -> 0x008a }
        r0 = (java.util.List) r0;	 Catch:{ NoSuchMethodException -> 0x0087, Throwable -> 0x008a }
    L_0x004e:
        r0 = r7.a(r0);	 Catch:{ Throwable -> 0x0070 }
        if (r0 == 0) goto L_0x0054;
    L_0x0054:
        if (r0 == 0) goto L_0x0078;
    L_0x0056:
        r0 = (android.telephony.CellLocation) r0;	 Catch:{ Throwable -> 0x0070 }
    L_0x0058:
        r1 = r0;
        goto L_0x0005;
    L_0x005a:
        r0 = move-exception;
        r2 = r1;
        goto L_0x001d;
    L_0x005d:
        r0 = move-exception;
        r2 = "CgiManager";
        r5 = "getSim2Cgi15";
        com.e.bc.a(r0, r2, r5);	 Catch:{ Throwable -> 0x0070 }
        r2 = r1;
        goto L_0x001d;
    L_0x0067:
        r0 = move-exception;
        r4 = "CgiManager";
        r5 = "getSim2Cgi14";
        com.e.bc.a(r0, r4, r5);	 Catch:{ Throwable -> 0x0070 }
        goto L_0x002e;
    L_0x0070:
        r0 = move-exception;
        r2 = "CgiManager";
        r3 = "getSim2Cgi";
        com.e.bc.a(r0, r2, r3);
    L_0x0078:
        r0 = r1;
        goto L_0x0058;
    L_0x007a:
        r0 = move-exception;
        r0 = r2;
        goto L_0x0041;
    L_0x007d:
        r0 = move-exception;
        r4 = "CgiManager";
        r5 = "getSim2Cgi13";
        com.e.bc.a(r0, r4, r5);	 Catch:{ Throwable -> 0x0070 }
    L_0x0085:
        r0 = r2;
        goto L_0x0041;
    L_0x0087:
        r0 = move-exception;
        r0 = r1;
        goto L_0x004e;
    L_0x008a:
        r0 = move-exception;
        r2 = "CgiManager";
        r3 = "getSim2Cgi1";
        com.e.bc.a(r0, r2, r3);	 Catch:{ Throwable -> 0x0070 }
        r0 = r1;
        goto L_0x004e;
    L_0x0094:
        r0 = move-exception;
        goto L_0x002e;
    L_0x0096:
        r0 = r1;
        goto L_0x0054;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.az.m():android.telephony.CellLocation");
    }

    private Class<?> n() {
        String str;
        Class<?> cls = null;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        switch (k()) {
            case 0:
                str = "android.telephony.TelephonyManager";
                break;
            case 1:
                str = "android.telephony.MSimTelephonyManager";
                break;
            case 2:
                str = "android.telephony.TelephonyManager2";
                break;
            default:
                str = cls;
                break;
        }
        try {
            cls = systemClassLoader.loadClass(str);
        } catch (Throwable th) {
            bc.a(th, "CgiManager", "getSim2TmClass");
        }
        return cls;
    }

    private void o() {
        Object obj = 1;
        JSONObject jSONObject = this.j;
        if (jSONObject != null) {
            try {
                if (jSONObject.has("cellupdate") && jSONObject.getString("cellupdate").equals("0")) {
                    obj = null;
                }
            } catch (Throwable th) {
                bc.a(th, "CgiManager", "updateCgi1");
            }
        }
        if (obj != null) {
            try {
                CellLocation.requestLocationUpdate();
            } catch (Throwable th2) {
                bc.a(th2, "CgiManager", "updateCgi");
            }
            this.i = br.b();
        }
    }

    private void p() {
        this.k = new PhoneStateListener(this) {
            final /* synthetic */ az a;

            {
                this.a = r1;
            }

            public void onCellLocationChanged(CellLocation cellLocation) {
                try {
                    if (this.a.a(cellLocation)) {
                        this.a.l = cellLocation;
                        if (this.a.o != null) {
                            this.a.o.onCellLocationChanged(cellLocation);
                        }
                    }
                } catch (Throwable th) {
                    bc.a(th, "CgiManager", "initPhoneStateListener7");
                }
            }

            public void onServiceStateChanged(ServiceState serviceState) {
                try {
                    switch (serviceState.getState()) {
                        case 0:
                            this.a.o();
                            break;
                        case 1:
                            this.a.q();
                            break;
                    }
                    if (this.a.o != null) {
                        this.a.o.onServiceStateChanged(serviceState);
                    }
                } catch (Throwable th) {
                    bc.a(th, "CgiManager", "initPhoneStateListener4");
                }
            }

            public void onSignalStrengthChanged(int i) {
                int i2 = -113;
                try {
                    switch (this.a.d) {
                        case 1:
                            i2 = br.a(i);
                            break;
                        case 2:
                            i2 = br.a(i);
                            break;
                    }
                    this.a.a(i2);
                    if (this.a.o != null) {
                        this.a.o.onSignalStrengthChanged(i);
                    }
                } catch (Throwable th) {
                    bc.a(th, "CgiManager", "initPhoneStateListener6");
                }
            }

            public void onSignalStrengthsChanged(SignalStrength signalStrength) {
                if (signalStrength != null) {
                    int i = -113;
                    try {
                        switch (this.a.d) {
                            case 1:
                                i = br.a(signalStrength.getGsmSignalStrength());
                                break;
                            case 2:
                                i = signalStrength.getCdmaDbm();
                                break;
                        }
                        this.a.a(i);
                        if (this.a.o != null) {
                            this.a.o.onSignalStrengthsChanged(signalStrength);
                        }
                    } catch (Throwable th) {
                        bc.a(th, "CgiManager", "initPhoneStateListener5");
                    }
                }
            }
        };
        String str = "android.telephony.PhoneStateListener";
        String str2 = "";
        int i = 0;
        if (br.c() < 7) {
            try {
                i = bp.b(str, "LISTEN_SIGNAL_STRENGTH");
            } catch (Throwable th) {
            }
        } else {
            try {
                i = bp.b(str, "LISTEN_SIGNAL_STRENGTHS");
            } catch (Throwable th2) {
            }
        }
        if (i == 0) {
            this.g.listen(this.k, 16);
        } else {
            try {
                this.g.listen(this.k, i | 16);
            } catch (Throwable th3) {
                bc.a(th3, "CgiManager", "initPhoneStateListener1");
            }
        }
        try {
            switch (k()) {
                case 0:
                    this.h = br.a(this.c, "phone2");
                    return;
                case 1:
                    this.h = br.a(this.c, "phone_msim");
                    return;
                case 2:
                    this.h = br.a(this.c, "phone2");
                    return;
                default:
                    return;
            }
        } catch (Throwable th32) {
            bc.a(th32, "CgiManager", "initPhoneStateListener");
        }
        bc.a(th32, "CgiManager", "initPhoneStateListener");
    }

    private void q() {
        this.l = null;
        this.d = 9;
        this.e.clear();
    }

    public CellLocation a(boolean z, boolean z2) {
        return b(z, z2);
    }

    public void a() {
        o();
        try {
            this.d = br.a(this.b, this.g.getCellLocation(), this.c);
        } catch (Throwable th) {
            bc.a(th, "CgiManager", "CgiManager");
            this.d = 9;
        }
        if (this.a == null) {
            this.a = new a(this, "listenerPhoneStateThread");
            this.a.start();
        }
    }

    public void a(PhoneStateListener phoneStateListener) {
        this.o = phoneStateListener;
    }

    public void a(JSONObject jSONObject) {
        this.j = jSONObject;
    }

    public boolean a(CellLocation cellLocation) {
        boolean z = false;
        if (cellLocation != null) {
            boolean z2 = true;
            switch (br.a(this.b, cellLocation, this.c)) {
                case 1:
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    z = a(gsmCellLocation.getLac(), gsmCellLocation.getCid());
                    break;
                case 2:
                    try {
                        if (bp.b(cellLocation, "getSystemId", new Object[0]) <= 0 || bp.b(cellLocation, "getNetworkId", new Object[0]) < 0 || bp.b(cellLocation, "getBaseStationId", new Object[0]) < 0) {
                            z2 = false;
                        }
                        z = z2;
                        break;
                    } catch (Throwable th) {
                        bc.a(th, "CgiManager", "cgiUseful");
                        z = true;
                        break;
                    }
                default:
                    z = true;
                    break;
            }
            if (!z) {
                this.d = 9;
            }
        }
        return z;
    }

    public boolean a(boolean z) {
        return (z || this.i == 0 || br.b() - this.i < 30000) ? false : true;
    }

    public ArrayList<ay> b() {
        return this.e;
    }

    public ay c() {
        ArrayList arrayList = this.e;
        return arrayList.size() >= 1 ? (ay) arrayList.get(0) : null;
    }

    public int d() {
        return this.d;
    }

    public CellLocation e() {
        CellLocation cellLocation = null;
        if (this.g != null) {
            try {
                cellLocation = this.g.getCellLocation();
                if (a(cellLocation)) {
                    this.l = cellLocation;
                }
            } catch (Throwable th) {
                bc.a(th, "CgiManager", "getCellLocation");
            }
        }
        return cellLocation;
    }

    public TelephonyManager f() {
        return this.g;
    }

    public void g() {
        q();
    }

    public void h() {
        o();
    }

    public void i() {
        if (!(this.g == null || this.k == null)) {
            try {
                this.g.listen(this.k, 0);
            } catch (Throwable th) {
                bc.a(th, "CgiManager", "destroy");
            }
        }
        this.k = null;
        synchronized (this.n) {
            this.m = true;
        }
        if (this.a != null) {
            this.a.quit();
            this.a = null;
        }
        this.e.clear();
        this.f = -113;
        this.g = null;
        this.h = null;
    }

    public void j() {
        switch (this.d) {
            case 1:
                if (this.e.isEmpty()) {
                    this.d = 9;
                    return;
                }
                return;
            case 2:
                if (this.e.isEmpty()) {
                    this.d = 9;
                    return;
                }
                return;
            default:
                return;
        }
    }
}
