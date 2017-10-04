package dji.pilot.dji_groundstation.controller;

import android.content.Context;
import android.os.Environment;
import dji.pilot.dji_groundstation.a.d$b;
import dji.pilot.dji_groundstation.a.d.c;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class i {
    private static final String a = "UIJsonGettor";
    private static i e = null;
    private final String b = Environment.getExternalStorageDirectory().getPath();
    private String c = "DJI/dji.pilot/dji.gs.enter/";
    private Context d = null;

    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] a = new int[c.values().length];

        static {
            try {
                a[c.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[c.c.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[c.d.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[c.e.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[c.f.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[c.h.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[c.i.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[c.j.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[c.g.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[c.k.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[c.l.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[c.m.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[c.n.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[c.o.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[c.p.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[c.r.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[c.q.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[c.t.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[c.u.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[c.s.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
        }
    }

    private i(Context context) {
        this.d = context;
        if (context != null) {
            this.c = "DJI/" + context.getPackageName() + "/dji.gs.enter/";
        }
    }

    public static synchronized i getInstance(Context context) {
        i iVar;
        synchronized (i.class) {
            if (e == null) {
                e = new i(context);
            }
            iVar = e;
        }
        return iVar;
    }

    public void a() {
        if (this.d != null) {
            this.d = null;
        }
    }

    public String b() {
        if (!Environment.getExternalStorageDirectory().equals("mounted")) {
            return "";
        }
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(this.b + this.c + dji.midware.data.manager.P3.i.getInstance().c()._name())));
            String str = "";
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    stringBuffer.append(readLine);
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "";
        } catch (IOException e2) {
            e2.printStackTrace();
            return "";
        }
    }

    public String c() {
        if (this.d == null) {
            return "";
        }
        int identifier = this.d.getResources().getIdentifier("product_" + dji.midware.data.manager.P3.i.getInstance().c().value(), "raw", this.d.getPackageName());
        if (identifier == 0) {
            identifier = this.d.getResources().getIdentifier("common_enter", "raw", this.d.getPackageName());
        }
        return a(identifier);
    }

    public String d() {
        if (this.d == null) {
            return "";
        }
        int identifier = this.d.getResources().getIdentifier(dji.midware.data.manager.P3.i.getInstance().c()._name(), "raw", this.d.getPackageName());
        if (identifier == 0) {
            identifier = this.d.getResources().getIdentifier("common_smart", "raw", this.d.getPackageName());
        }
        return a(identifier);
    }

    public String a(c cVar) {
        int b = b(cVar);
        if (b == 0) {
            return "";
        }
        return a(b);
    }

    public String a(d$b dji_pilot_dji_groundstation_a_d_b) {
        int b = b(dji_pilot_dji_groundstation_a_d_b);
        if (b == 0) {
            return "";
        }
        return a(b);
    }

    private int b(d$b dji_pilot_dji_groundstation_a_d_b) {
        String str = "";
        if (dji_pilot_dji_groundstation_a_d_b.a() == d$b.Take_Off_Warning.a()) {
            str = "smart_take_off_warning";
        }
        return this.d.getResources().getIdentifier(str, "raw", this.d.getPackageName());
    }

    private int b(c cVar) {
        String str = "";
        switch (AnonymousClass1.a[cVar.ordinal()]) {
            case 1:
                str = "smart_poi_sethotpoint";
                break;
            case 2:
                str = "smart_poi_start";
                break;
            case 3:
                str = "smart_poi_status";
                break;
            case 4:
                str = "smart_waypoint_createmission";
                break;
            case 5:
                str = "smart_waypoint_collection";
                break;
            case 6:
                str = "smart_waypoint_setting";
                break;
            case 7:
                str = "smart_waypoint_setreturnhomeheight";
                break;
            case 8:
                str = "smart_waypoint_uploadmission";
                break;
            case 9:
                str = "smart_waypoint_status";
                break;
            case 10:
                str = "smart_waypoint_add_new";
                break;
            case 11:
                str = "smart_waypoint_downloadmission";
                break;
            case 12:
                str = "smart_followme_start";
                break;
            case 13:
                str = "smart_followme_status";
                break;
            case 14:
                str = "smart_courselock_start";
                break;
            case 15:
                str = "smart_courselock_status";
                break;
            case 16:
                str = "smart_homelock_status";
                break;
            case 17:
                str = "smart_homelock_start";
                break;
            case 18:
                str = "smart_terraintracking_start";
                break;
            case 19:
                str = "smart_terraintracking_status";
                break;
        }
        return this.d.getResources().getIdentifier(str, "raw", this.d.getPackageName());
    }

    private String a(int i) {
        InputStream openRawResource = this.d.getResources().openRawResource(i);
        if (openRawResource == null) {
            return "";
        }
        try {
            byte[] bArr = new byte[openRawResource.available()];
            openRawResource.read(bArr);
            return new String(bArr);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
