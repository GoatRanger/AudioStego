package dji.pilot.countrycode.a;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.dji.frame.c.h;
import dji.pilot.countrycode.model.CountryCodeModel;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.usercenter.mode.n;
import dji.thirdparty.afinal.c;
import java.util.HashMap;
import java.util.TimeZone;

class b {
    protected static final String a = "CountryCodeGetter";
    private static b d = null;
    private static final String e = "{\n\"America/New_York\":\"US\",\n\"Asia/Bangkok\":\"TH\",\n\"Asia/Chongqing\":\"CN\",\n\"Asia/Dubai\":\"AE\",\n\"Asia/Harbin\":\"CN\",\n\"Asia/Jakarta\":\"ID\",\n\"Asia/Macao\":\"MO\",\n\"Asia/Macau\":\"MO\",\n\"Asia/Seoul\":\"KR\",\n\"Asia/Shanghai\":\"CN\",\n\"Asia/Singapore\":\"SG\",\n\"Asia/Taipei\":\"TW\",\n\"Brazil/Acre\":\"BR\",\n\"Brazil/DeNoronha\":\"BR\",\n\"Brazil/East\":\"BR\",\n\"Brazil/West\":\"BR\",\n\"Canada/Atlantic\":\"CA\",\n\"Canada/Central\":\"CA\",\n\"Canada/East-Saskatchewan\":\"CA\",\n\"Canada/Eastern\":\"CA\",\n\"Canada/Mountain\":\"CA\",\n\"Canada/Newfoundland\":\"CA\",\n\"Canada/Pacific\":\"CA\",\n\"Canada/Saskatchewan\":\"CA\",\n\"Canada/Yukon\":\"CA\",\n\"Europe/London\":\"GB\",\n\"Europe/Moscow\":\"RU\",\n\"Japan\":\"JP\",\n\"Mexico/BajaNorte\":\"MX\",\n\"Mexico/BajaSur\":\"MX\",\n\"Mexico/General\":\"MX\",\n\"Singapore\":\"SG\"\n}\n";
    private static final HashMap<String, String> f = ((HashMap) h.a().fromJson(e, HashMap.class));
    private String[] b = new String[]{"", "", "", ""};
    private Context c = DJIApplication.a();

    public interface a {
        void a(String str);

        void a(String str, String str2);
    }

    private enum b {
        UAV_GPS,
        MOBILE_GPS,
        MCC,
        NO_GPS
    }

    private b() {
    }

    public static b getInstance() {
        if (d == null) {
            synchronized (b.class) {
                if (d == null) {
                    d = new b();
                }
            }
        }
        return d;
    }

    public String a() {
        a.a("mStrategyValue:UAV_GPS > MOBILE_GPS > MCC > NO_GPS:");
        a.a(this.b);
        for (String str : this.b) {
            if (!TextUtils.isEmpty(str)) {
                return str;
            }
        }
        a.a("mStrategyValue:bus-error:???????????????????????????????????????" + this.b);
        return "";
    }

    private boolean a(a aVar) {
        Object networkCountryIso = ((TelephonyManager) this.c.getSystemService("phone")).getNetworkCountryIso();
        if (TextUtils.isEmpty(networkCountryIso)) {
            aVar.a("getFromMcc():CountryCode is empty !");
            return false;
        }
        this.b[b.MCC.ordinal()] = networkCountryIso.toUpperCase();
        aVar.a(a(), "getFromMcc");
        return true;
    }

    private String b() {
        return (String) f.get(TimeZone.getDefault().getID());
    }

    private boolean a(String str) {
        boolean z;
        Context context = this.c;
        StringBuilder append = new StringBuilder().append("?????????????????????get??????????????????4:IP???????????????????????? ?????????????????????=").append(b()).append(",?????????????????????").append(str).append("???????????????=");
        if (TextUtils.isEmpty(str) || !str.equalsIgnoreCase(b())) {
            z = false;
        } else {
            z = true;
        }
        a.a(context, append.append(z).toString());
        if (TextUtils.isEmpty(str) || !str.equalsIgnoreCase(b())) {
            return false;
        }
        return true;
    }

    public void a(String str, String str2, dji.pilot.countrycode.model.a aVar, dji.pilot.countrycode.model.a aVar2, a aVar3) {
        StringBuilder stringBuilder = new StringBuilder(40);
        if (!aVar.a()) {
            a(str, str2, aVar, aVar3);
            stringBuilder.append("??????GPS?????????????????????1:UavGps??????");
        } else if (!aVar2.a()) {
            b(str, str2, aVar2, aVar3);
            stringBuilder.append("??????GPS?????????????????????GPS?????????????????????2:MobileGps??????");
        } else if (a(aVar3)) {
            stringBuilder.append("??????GPS?????????????????????GPS???????????????mCC?????????????????????3:Mcc??????");
        } else {
            a(str, str2, aVar3);
            stringBuilder.append("??????GPS?????????????????????GPS???????????????mCC???????????????????????????4:IP??????");
        }
        a.a(this.c, "?????????????????????get?????????????????????????????? " + stringBuilder);
    }

    private void a(String str, String str2, dji.pilot.countrycode.model.a aVar, final a aVar2) {
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        if (!TextUtils.isEmpty(str2)) {
            bVar.a("board_num", str2);
        }
        bVar.a(n.x, "" + aVar.b);
        bVar.a(n.y, "" + aVar.a);
        c a = com.dji.frame.c.c.a();
        a.a("token", str);
        a.a("getFromBeWithUavGps():param??? " + bVar + ",token=" + str);
        a.a(a.a, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b b;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                try {
                    CountryCodeModel countryCodeModel = (CountryCodeModel) h.b(str, CountryCodeModel.class);
                    if (!TextUtils.isEmpty(countryCodeModel.result.country_code)) {
                        this.b.b[b.UAV_GPS.ordinal()] = countryCodeModel.result.country_code;
                        aVar2.a(this.b.a(), "getFromBeWithUavGps");
                    }
                } catch (Exception e) {
                    a.a("getFromBeWithUavGps():onSuccess???but ??????????????????,ex=" + e);
                }
            }

            public void a(Throwable th, int i, String str) {
                a.a("???onFailure,t=" + th + ",errorNo=" + i + ",strMsg=" + str + ",callback=" + aVar2);
                this.b.a(aVar2);
                a.a(this.b.c, "getFromBeWithUavGps()???onFailure,??????????????????MCC????????????");
            }
        });
    }

    private void b(String str, String str2, dji.pilot.countrycode.model.a aVar, final a aVar2) {
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        if (TextUtils.isEmpty(str2)) {
            a.a("getFromBeWithMobileGps():flycSn??? " + DJIGlobalService.f);
        } else {
            bVar.a("board_num", str2);
        }
        bVar.a(n.x, "" + aVar.b);
        bVar.a(n.y, "" + aVar.a);
        c a = com.dji.frame.c.c.a();
        a.a("token", str);
        a.a("getFromBeWithMobileGps():param??? " + bVar + ",token=" + str);
        a.a(a.a, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b b;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                try {
                    CountryCodeModel countryCodeModel = (CountryCodeModel) h.b(str, CountryCodeModel.class);
                    if (!TextUtils.isEmpty(countryCodeModel.result.country_code)) {
                        this.b.b[b.MOBILE_GPS.ordinal()] = countryCodeModel.result.country_code;
                        aVar2.a(this.b.a(), "getFromBeWithMobileGps");
                    }
                } catch (Exception e) {
                    a.a("getFromBeWithMobileGps():onSuccess???but ??????????????????,ex=" + e);
                }
            }

            public void a(Throwable th, int i, String str) {
                a.a("getFromBeWithMobileGps:???onFailure,t=" + th + ",errorNo=" + i + ",strMsg=" + str + ",callback=" + aVar2);
                this.b.a(aVar2);
                a.a(this.b.c, "getFromBeWithMobileGps()???onFailure,??????????????????MCC????????????");
            }
        });
    }

    private void a(String str, String str2, final a aVar) {
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        if (!TextUtils.isEmpty(str2)) {
            bVar.a("board_num", str2);
        }
        c a = com.dji.frame.c.c.a();
        a.a("token", str);
        a.a("getFromBeWithoutGps():board_num = " + str2 + ",token=" + str);
        a.a(a.a, bVar, new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ b b;

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                try {
                    CountryCodeModel countryCodeModel = (CountryCodeModel) h.b(str, CountryCodeModel.class);
                    if (this.b.a(countryCodeModel.result.country_code)) {
                        this.b.b[b.NO_GPS.ordinal()] = countryCodeModel.result.country_code;
                        aVar.a(this.b.a(), "getFromBeWithoutGps");
                    } else if (aVar != null) {
                        aVar.a("getFromBeWithoutGps():onFailure ???????????????????????????");
                    }
                } catch (Exception e) {
                    String str2 = "getFromBeWithoutGps():onSuccess???but ??????????????????,ex=" + e + ",??????????????????=" + str;
                    a.a(str2);
                    if (aVar != null) {
                        aVar.a(str2);
                    }
                }
            }

            public void a(Throwable th, int i, String str) {
                a.a("???onFailure,t=" + th + ",errorNo=" + i + ",strMsg=" + str + ",callback=" + aVar);
                if (aVar != null) {
                    aVar.a("getFromBeWithoutGps():onFailure get CountryCode fail");
                }
            }
        });
    }
}
