package cn.sharesdk.framework.b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import cn.sharesdk.framework.b.a.e;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.mob.tools.network.KVPair;
import com.mob.tools.network.NetworkHelper;
import com.mob.tools.network.NetworkHelper.NetworkTimeOut;
import com.mob.tools.utils.Data;
import com.mob.tools.utils.DeviceHelper;
import com.mob.tools.utils.Hashon;
import com.mob.tools.utils.R;
import dji.pilot.usercenter.protocol.d;
import java.util.ArrayList;
import java.util.HashMap;

public class c {
    private String a;
    private Context b;
    private e c = e.a(this.b);
    private DeviceHelper d = DeviceHelper.getInstance(this.b);
    private NetworkHelper e = new NetworkHelper();
    private Hashon f = new Hashon();
    private String g;
    private String h;
    private boolean i;
    private HashMap<String, String> j;

    public c(Context context, String str) {
        this.a = str;
        this.b = context.getApplicationContext();
        try {
            this.j = (HashMap) this.c.h("buffered_server_paths");
        } catch (Throwable th) {
            this.j = new HashMap();
        }
        g();
    }

    private void g() {
        String str = this.d.getPackageName() + d.t + this.d.getAppVersionName();
        this.g = str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + "ShareSDK/2.7.9" + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + ("Android/" + this.d.getOSVersionInt());
        this.h = "http://api.share.mob.com:80";
        this.i = true;
    }

    public void a(String str) {
        this.h = str;
    }

    public void a(HashMap<String, String> hashMap) {
        this.j = hashMap;
        this.c.a("buffered_server_paths", this.j);
    }

    private String h() {
        return this.h + "/conn";
    }

    public HashMap<String, Object> a() throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", this.a));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", this.g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 30000;
        networkTimeOut.connectionTimeout = 30000;
        cn.sharesdk.framework.utils.d.a().i(" isConnectToServer response == %s", new Object[]{this.e.httpPost(h(), arrayList, null, arrayList2, networkTimeOut)});
        return this.f.fromJson(this.e.httpPost(h(), arrayList, null, arrayList2, networkTimeOut));
    }

    private String i() {
        if (this.j == null || !this.j.containsKey("/date")) {
            return this.h + "/date";
        }
        return ((String) this.j.get("/date")) + "/date";
    }

    public long b() throws Throwable {
        if (!this.c.g()) {
            return 0;
        }
        String str = "{}";
        try {
            str = this.e.httpGet(i(), null, null, null);
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().d(th);
        }
        HashMap fromJson = this.f.fromJson(str);
        if (!fromJson.containsKey(dji.pilot.usercenter.protocol.c.ad)) {
            return this.c.a();
        }
        try {
            long currentTimeMillis = System.currentTimeMillis() - R.parseLong(String.valueOf(fromJson.get(dji.pilot.usercenter.protocol.c.ad)));
            this.c.a("service_time", Long.valueOf(currentTimeMillis));
            return currentTimeMillis;
        } catch (Throwable th2) {
            cn.sharesdk.framework.utils.d.a().d(th2);
            return this.c.a();
        }
    }

    private String j() {
        return this.h + "/conf5";
    }

    public HashMap<String, Object> c() throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", this.a));
        arrayList.add(new KVPair("device", this.d.getDeviceKey()));
        arrayList.add(new KVPair("plat", String.valueOf(this.d.getPlatformCode())));
        arrayList.add(new KVPair("apppkg", this.d.getPackageName()));
        arrayList.add(new KVPair("appver", String.valueOf(this.d.getAppVersion())));
        arrayList.add(new KVPair("sdkver", String.valueOf(60067)));
        arrayList.add(new KVPair("networktype", this.d.getDetailNetworkTypeForStatic()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", this.g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 10000;
        networkTimeOut.connectionTimeout = 10000;
        cn.sharesdk.framework.utils.d.a().i(" get server config response == %s", new Object[]{this.e.httpPost(j(), arrayList, null, arrayList2, networkTimeOut)});
        return this.f.fromJson(this.e.httpPost(j(), arrayList, null, arrayList2, networkTimeOut));
    }

    private String k() {
        return "http://up.sharesdk.cn/upload/image";
    }

    public HashMap<String, Object> b(String str) throws Throwable {
        KVPair kVPair = new KVPair(d.A, str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("User-Agent", this.g));
        cn.sharesdk.framework.utils.d.a().i("upload file response == %s", new Object[]{this.e.httpPost(k(), null, kVPair, arrayList, null)});
        return this.f.fromJson(this.e.httpPost(k(), null, kVPair, arrayList, null));
    }

    private String l() {
        if (this.j == null || !this.j.containsKey("/log4")) {
            return this.h + "/log4";
        }
        return ((String) this.j.get("/log4")) + "/log4";
    }

    public boolean a(String str, boolean z) {
        try {
            if ("none".equals(this.d.getDetailNetworkTypeForStatic())) {
                throw new IllegalStateException("network is disconnected!");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(new KVPair("m", str));
            arrayList.add(new KVPair("t", z ? "1" : "0"));
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new KVPair("User-Agent", this.g));
            NetworkTimeOut networkTimeOut = new NetworkTimeOut();
            networkTimeOut.readTimout = 30000;
            networkTimeOut.connectionTimeout = 30000;
            Object httpPost = this.e.httpPost(l(), arrayList, null, arrayList2, networkTimeOut);
            cn.sharesdk.framework.utils.d.a().i("> Upload All Log  resp: %s", new Object[]{httpPost});
            if (TextUtils.isEmpty(httpPost) || ((Integer) this.f.fromJson(httpPost).get("status")).intValue() == 200) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            cn.sharesdk.framework.utils.d.a().d(th);
            return false;
        }
    }

    private String m() {
        return "http://l.mob.com/url/ShareSdkMapping.do";
    }

    public HashMap<String, Object> a(String str, ArrayList<String> arrayList, int i, String str2) throws Throwable {
        if (!this.i) {
            return null;
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair(d.M, this.a));
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            arrayList2.add(new KVPair("urls", ((String) arrayList.get(i2)).toString()));
        }
        arrayList2.add(new KVPair("deviceid", this.d.getDeviceKey()));
        arrayList2.add(new KVPair("snsplat", String.valueOf(i)));
        CharSequence d = d(str2);
        if (TextUtils.isEmpty(d)) {
            return null;
        }
        arrayList2.add(new KVPair("m", d));
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add(new KVPair("User-Agent", this.g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 5000;
        networkTimeOut.connectionTimeout = 5000;
        Object httpPost = this.e.httpPost(m(), arrayList2, null, arrayList3, networkTimeOut);
        cn.sharesdk.framework.utils.d.a().i("> SERVER_SHORT_LINK_URL  resp: %s", new Object[]{httpPost});
        if (TextUtils.isEmpty(httpPost)) {
            this.i = false;
            return null;
        }
        HashMap<String, Object> fromJson = this.f.fromJson(httpPost);
        if (((Integer) fromJson.get("status")).intValue() == 200) {
            return fromJson;
        }
        return null;
    }

    private String d(String str) throws Throwable {
        boolean b = this.c.b();
        boolean c = this.c.c();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Data.urlEncode(this.d.getPackageName(), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(this.d.getAppVersionName(), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(String.valueOf(60067), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(String.valueOf(this.d.getPlatformCode()), "utf-8")).append("|");
        stringBuilder.append(Data.urlEncode(this.d.getDetailNetworkTypeForStatic(), "utf-8")).append("|");
        if (b) {
            stringBuilder.append(Data.urlEncode(String.valueOf(this.d.getOSVersionInt()), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.d.getScreenSize(), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.d.getManufacturer(), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.d.getModel(), "utf-8")).append("|");
            stringBuilder.append(Data.urlEncode(this.d.getCarrier(), "utf-8")).append("|");
        } else {
            stringBuilder.append("|||||");
        }
        if (c) {
            stringBuilder.append(str);
        } else {
            stringBuilder.append(str.split("\\|")[0]);
            stringBuilder.append("|||||");
        }
        cn.sharesdk.framework.utils.d.a().i("shorLinkMsg ===>>>>", new Object[]{stringBuilder.toString()});
        return Base64.encodeToString(Data.AES128Encode(Data.rawMD5(String.format("%s:%s", new Object[]{this.d.getDeviceKey(), this.a})), stringBuilder.toString()), 2);
    }

    private String n() {
        if (this.j == null || !this.j.containsKey("/snsconf")) {
            return this.h + "/snsconf";
        }
        return ((String) this.j.get("/snsconf")) + "/snsconf";
    }

    public HashMap<String, Object> d() throws Throwable {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KVPair("appkey", this.a));
        arrayList.add(new KVPair("device", this.d.getDeviceKey()));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(new KVPair("User-Agent", this.g));
        NetworkTimeOut networkTimeOut = new NetworkTimeOut();
        networkTimeOut.readTimout = 10000;
        networkTimeOut.connectionTimeout = 10000;
        return this.f.fromJson(this.e.httpPost(n(), arrayList, null, arrayList2, networkTimeOut));
    }

    public void a(cn.sharesdk.framework.b.b.c cVar) throws Throwable {
        cn.sharesdk.framework.b.a.d.a(this.b, cVar.toString(), cVar.e);
    }

    public ArrayList<cn.sharesdk.framework.b.a.c> e() throws Throwable {
        ArrayList<cn.sharesdk.framework.b.a.c> a = cn.sharesdk.framework.b.a.d.a(this.b);
        if (a == null) {
            return new ArrayList();
        }
        return a;
    }

    public void a(ArrayList<String> arrayList) throws Throwable {
        cn.sharesdk.framework.b.a.d.a(this.b, arrayList);
    }

    public HashMap<String, Object> f() throws Throwable {
        return this.f.fromJson(this.c.e(this.a));
    }

    public void b(HashMap<String, Object> hashMap) throws Throwable {
        this.c.a(this.a, this.f.fromHashMap(hashMap));
    }

    public HashMap<String, Object> c(String str) throws Throwable {
        return this.f.fromJson(new String(Data.AES128Decode(Data.rawMD5(this.a + ":" + this.d.getDeviceKey()), Base64.decode(str, 2)), "UTF-8").trim());
    }
}
