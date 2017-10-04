package dji.pilot.gallery.entryActivity;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.dji.frame.b.c;
import dji.log.DJILogHelper;
import dji.pilot.fpv.model.DJIGeocoderResult;
import dji.pilot.fpv.model.DJIGeocoderResult.FirstLevel;
import dji.pilot.fpv.model.DJIGeocoderResult.SecondLevel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class h {
    public static final int a = 1;
    private Context b;
    private Map<dji.pilot.gallery.entryActivity.d.a, Boolean> c;
    private a d;
    private Handler e;
    private HandlerThread f;

    interface a {
        void a();
    }

    public h(Context context, Map<dji.pilot.gallery.entryActivity.d.a, Boolean> map) {
        this(context);
        this.c = map;
    }

    public h(Context context) {
        this.c = new HashMap();
        this.b = context;
        this.f = new HandlerThread("getlocation");
        this.f.start();
        this.e = new Handler(this, this.f.getLooper()) {
            final /* synthetic */ h a;

            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        this.a.c();
                        break;
                }
                super.handleMessage(message);
            }
        };
    }

    public void a(dji.pilot.gallery.entryActivity.d.a aVar) {
        synchronized (this.c) {
            if (!this.c.containsKey(aVar)) {
                this.c.put(aVar, Boolean.valueOf(false));
            }
        }
    }

    public void a(List<dji.pilot.gallery.entryActivity.d.a> list) {
        for (dji.pilot.gallery.entryActivity.d.a a : list) {
            a(a);
        }
        a();
    }

    public void a() {
        this.e.obtainMessage(1, null).sendToTarget();
    }

    public void b() {
        if (this.f != null) {
            this.f.quit();
            this.f = null;
        }
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    private void c() {
        synchronized (this.c) {
            for (Entry entry : this.c.entrySet()) {
                if (!((Boolean) entry.getValue()).booleanValue()) {
                    dji.pilot.gallery.entryActivity.d.a aVar = (dji.pilot.gallery.entryActivity.d.a) entry.getKey();
                    a(aVar.a, aVar.b, aVar);
                }
            }
        }
    }

    private boolean d() {
        for (Entry value : this.c.entrySet()) {
            if (!((Boolean) value.getValue()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    private void e() {
        if (d() && this.d != null) {
            this.d.a();
        }
    }

    private void a(String str, String str2, final dji.pilot.gallery.entryActivity.d.a aVar) {
        DJILogHelper.getInstance().LOGI("bob", "getLocation enter ");
        if (aVar != null) {
            DJILogHelper.getInstance().LOGI("bob", "locations " + aVar.a + "  " + aVar.b);
            if (str == null || "".equals(str) || str2 == null || "".equals(str2)) {
                this.c.put(aVar, Boolean.valueOf(true));
                e();
                DJILogHelper.getInstance().LOGI("bob", "getlocation no gps info");
                return;
            }
            DJIGeocoderResult.get(this.b, Double.valueOf(str).doubleValue(), Double.valueOf(str2).doubleValue(), new c(this) {
                final /* synthetic */ h b;

                public void a(Object obj) {
                    String str = null;
                    DJIGeocoderResult dJIGeocoderResult = (DJIGeocoderResult) obj;
                    if (dJIGeocoderResult == null || dJIGeocoderResult.status == null || !dJIGeocoderResult.status.equals("OK")) {
                        DJILogHelper.getInstance().LOGI("bob", "getlocation fail");
                        this.b.c.put(aVar, Boolean.valueOf(true));
                    } else {
                        FirstLevel streetAdress = DJIGeocoderResult.getStreetAdress(dJIGeocoderResult);
                        if (streetAdress != null) {
                            Iterator it = streetAdress.address_components.iterator();
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            String str5 = null;
                            while (it.hasNext()) {
                                String str6;
                                SecondLevel secondLevel = (SecondLevel) it.next();
                                if (secondLevel.types.contains("administrative_area_level_1")) {
                                    str6 = secondLevel.long_name;
                                    str = str2;
                                    str2 = str3;
                                    str3 = str4;
                                    str4 = str5;
                                } else if (secondLevel.types.contains(dji.pilot.usercenter.protocol.c.W) || secondLevel.types.contains("administrative_area_level_2")) {
                                    str4 = str5;
                                    r10 = str2;
                                    str2 = str3;
                                    str3 = secondLevel.long_name;
                                    str6 = str;
                                    str = r10;
                                } else if (secondLevel.types.contains("route")) {
                                    str3 = str4;
                                    str4 = str5;
                                    r10 = secondLevel.long_name;
                                    str6 = str;
                                    str = str2;
                                    str2 = r10;
                                } else if (secondLevel.types.contains("country")) {
                                    str2 = str3;
                                    str3 = str4;
                                    str4 = str5;
                                    r10 = secondLevel.long_name;
                                    str6 = str;
                                    str = r10;
                                } else if (secondLevel.types.contains("sublocality_level_1") || secondLevel.types.contains("sublocality")) {
                                    r10 = str;
                                    str = str2;
                                    str2 = str3;
                                    str3 = str4;
                                    str4 = secondLevel.long_name;
                                    str6 = r10;
                                } else {
                                    str6 = str;
                                    str = str2;
                                    str2 = str3;
                                    str3 = str4;
                                    str4 = str5;
                                }
                                str5 = str4;
                                str4 = str3;
                                str3 = str2;
                                str2 = str;
                                str = str6;
                            }
                            if (Locale.getDefault().getLanguage().contains("zh")) {
                                aVar.e = str5 + str3;
                                aVar.d = str2 + str + str4;
                            } else {
                                aVar.e = str3 + "," + str5;
                                aVar.d = str4 + str2;
                            }
                            this.b.c.put(aVar, Boolean.valueOf(true));
                            DJILogHelper.getInstance().LOGI("bob", "street = " + aVar.e + " city = " + aVar.d);
                        }
                        DJILogHelper.getInstance().LOGI("bob", "getlocation suc");
                    }
                    this.b.e();
                }
            });
            return;
        }
        this.c.put(aVar, Boolean.valueOf(true));
        e();
    }
}
