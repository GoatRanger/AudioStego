package dji.dbox.upgrade.p4.c;

import android.content.Context;
import com.google.api.client.http.UrlEncodedParser;
import dji.dbox.upgrade.p4.model.DJIUpUrlModel;
import dji.midware.natives.UpgradeVerify;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.afinal.c;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;
import org.apache.http.entity.StringEntity;

public class a {
    private final String a = "https://mydjiflight.dji.com";
    private final String b = "https://mydjiflight.dji.com/loadconfig/geturl";
    private final String c = UrlEncodedParser.CONTENT_TYPE;
    private final String d = "UTF-8";
    private String e = "tarFiles/";
    private String f = "out/";
    private String g = "";
    private String h = "";
    private String i = "";
    private c j;
    private DJIUpUrlModel k;
    private final boolean l = false;

    public a(Context context, String str) {
        this.j = com.dji.frame.c.c.b(context);
        this.i = str;
        this.g = context.getFilesDir().getAbsolutePath() + dji.dbox.upgrade.p4.a.a.i + str + d.t;
        this.e = this.g + this.e;
        this.f = this.e + this.f;
        File file = new File(this.f);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public boolean a(String str) {
        return this.i.equals(str);
    }

    public void a(DJIUpUrlModel dJIUpUrlModel) {
        this.k = dJIUpUrlModel;
    }

    public void a(String str, ArrayList<String> arrayList, a aVar) {
        this.h = c(str);
        new Thread(new 1(this, str, arrayList, aVar)).start();
    }

    private String c(String str) {
        return this.i + "_ac_fw_v" + str.replace(".", "-");
    }

    public String a() {
        return this.f + this.h + ".tar";
    }

    private String d(String str) {
        return this.f + c(str) + ".tar";
    }

    public boolean b(String str) {
        return new File(d(str)).exists();
    }

    private String e(String str) {
        String f = f(str);
        File file = new File(f);
        if (!file.exists()) {
            file.mkdirs();
        }
        return f + c(str) + ".cfg.sig";
    }

    private String f(String str) {
        return this.e + str + d.t;
    }

    public String a(String str, String str2) {
        return f(str) + str2;
    }

    public void a(dji.thirdparty.afinal.f.a<String> aVar) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        long currentTimeMillis = System.currentTimeMillis();
        String str = "android";
        stringBuilder.append("time=" + currentTimeMillis);
        stringBuilder.append("&os=" + str);
        stringBuilder.append("&version=" + dji.dbox.upgrade.p4.a.a.j);
        stringBuilder.append("&signature=" + com.dji.frame.c.a.a(UpgradeVerify.native_getMD5Input(currentTimeMillis + str + dji.dbox.upgrade.p4.a.a.j, 1)));
        this.j.a("https://mydjiflight.dji.com/loadconfig/geturl", new StringEntity(stringBuilder.toString(), "UTF-8"), UrlEncodedParser.CONTENT_TYPE, (dji.thirdparty.afinal.f.a) aVar);
    }

    public void b(dji.thirdparty.afinal.f.a<String> aVar) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("product_id=" + this.i);
        stringBuilder.append("&signature=" + com.dji.frame.c.a.a(UpgradeVerify.native_getMD5Input(this.i + dji.dbox.upgrade.p4.a.a.l)));
        if (dji.dbox.upgrade.p4.a.a.e()) {
            stringBuilder.append("&token=" + dji.dbox.upgrade.p4.a.a.l);
            stringBuilder.append("&account=" + dji.dbox.upgrade.p4.a.a.m);
        }
        String toLowerCase = Locale.getDefault().toString().toLowerCase();
        if (toLowerCase.contains("ja")) {
            toLowerCase = "ja";
        } else if (toLowerCase.contains("cn")) {
            toLowerCase = "zh_cn";
        } else if (toLowerCase.contains("tw")) {
            toLowerCase = "zh_tw";
        } else if (toLowerCase.contains("en")) {
            toLowerCase = "en";
        } else {
            toLowerCase = "en";
        }
        stringBuilder.append("&lan=" + toLowerCase);
        this.j.a(this.k.apis.allfile, new StringEntity(stringBuilder.toString(), "UTF-8"), UrlEncodedParser.CONTENT_TYPE, (dji.thirdparty.afinal.f.a) aVar);
    }

    public dji.thirdparty.afinal.f.c<File> a(dji.thirdparty.afinal.f.a<File> aVar, String str) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("product_id=" + this.i);
        stringBuilder.append("&product_version=" + str);
        stringBuilder.append("&signature=" + com.dji.frame.c.a.a(UpgradeVerify.native_getMD5Input(this.i + str + dji.dbox.upgrade.p4.a.a.l)));
        if (dji.dbox.upgrade.p4.a.a.e()) {
            stringBuilder.append("&token=" + dji.dbox.upgrade.p4.a.a.l);
            stringBuilder.append("&account=" + dji.dbox.upgrade.p4.a.a.m);
        }
        return this.j.a(this.k.apis.config, e(str), new StringEntity(stringBuilder.toString(), "UTF-8"), UrlEncodedParser.CONTENT_TYPE, false, false, aVar);
    }

    public void a(dji.thirdparty.afinal.f.a<String> aVar, String str, String str2, String str3) throws UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("signature=" + com.dji.frame.c.a.a(UpgradeVerify.native_getMD5Input(this.i + str + str3 + str2 + dji.dbox.upgrade.p4.a.a.l)));
        stringBuilder.append("&product_id=" + this.i);
        stringBuilder.append("&product_version=" + str);
        stringBuilder.append("&module_id=" + str3);
        stringBuilder.append("&module_version=" + str2);
        if (dji.dbox.upgrade.p4.a.a.e()) {
            stringBuilder.append("&token=" + dji.dbox.upgrade.p4.a.a.l);
        }
        this.j.a(this.k.apis.down, new StringEntity(stringBuilder.toString(), "UTF-8"), UrlEncodedParser.CONTENT_TYPE, (dji.thirdparty.afinal.f.a) aVar);
    }

    public dji.thirdparty.afinal.f.c<File> a(String str, dji.thirdparty.afinal.f.a<File> aVar, String str2, String str3, String str4, long j) throws UnsupportedEncodingException {
        Object file = new File(a(str2, str));
        if (!file.exists() || file.length() < j) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("signature=" + com.dji.frame.c.a.a(UpgradeVerify.native_getMD5Input(this.i + str2 + str4 + str3 + dji.dbox.upgrade.p4.a.a.l)));
            stringBuilder.append("&product_id=" + this.i);
            stringBuilder.append("&product_version=" + str2);
            stringBuilder.append("&module_id=" + str4);
            stringBuilder.append("&module_version=" + str3);
            if (dji.dbox.upgrade.p4.a.a.e()) {
                stringBuilder.append("&token=" + dji.dbox.upgrade.p4.a.a.l);
                stringBuilder.append("&account=" + dji.dbox.upgrade.p4.a.a.m);
            }
            return this.j.a(this.k.apis.firm, a(str2, str), new StringEntity(stringBuilder.toString(), "UTF-8"), UrlEncodedParser.CONTENT_TYPE, true, (dji.thirdparty.afinal.f.a) aVar);
        }
        aVar.a(j, j);
        aVar.a(file);
        return null;
    }

    public dji.thirdparty.afinal.f.c<File> a(String str, String str2, String str3, long j, dji.thirdparty.afinal.f.a<File> aVar) throws UnsupportedEncodingException {
        Object file = new File(a(str3, str2));
        if (!file.exists() || file.length() < j) {
            return this.j.a(str, a(str3, str2), true, (dji.thirdparty.afinal.f.a) aVar);
        }
        aVar.a(j, j);
        aVar.a(file);
        return null;
    }

    private String a(int i) {
        return "0x" + dji.midware.util.c.c((byte) i);
    }

    public boolean b() {
        return this.k != null;
    }
}
