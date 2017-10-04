package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.alipay.sdk.c.c;
import com.alipay.sdk.f.a.d;
import com.alipay.sdk.h.b;
import com.alipay.sdk.j.f;
import com.alipay.sdk.j.i;
import com.alipay.sdk.j.j;
import com.alipay.sdk.j.l;
import com.facebook.internal.ab;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class PayTask {
    static final Object a = f.class;
    private Activity b;
    private com.alipay.sdk.k.a c;
    private String d = "wappaygw.alipay.com/service/rest.htm";
    private String e = "mclient.alipay.com/service/rest.htm";
    private String f = "mclient.alipay.com/home/exterfaceAssign.htm";
    private Map<String, a> g = new HashMap();

    private class a {
        String a;
        String b;
        final /* synthetic */ PayTask c;

        private a(PayTask payTask) {
            this.c = payTask;
            this.a = "";
            this.b = "";
        }

        private String a() {
            return this.a;
        }

        private void a(String str) {
            this.a = str;
        }

        private String b() {
            return this.b;
        }

        private void b(String str) {
            this.b = str;
        }
    }

    public PayTask(Activity activity) {
        this.b = activity;
        b a = b.a();
        Context context = this.b;
        c.a();
        a.a(context);
        com.alipay.sdk.app.a.a.a(activity);
        this.c = new com.alipay.sdk.k.a(activity, com.alipay.sdk.k.a.b);
    }

    public synchronized String pay(String str, boolean z) {
        String str2;
        Object obj = null;
        synchronized (this) {
            String a;
            if (z) {
                b();
            }
            try {
                Context context;
                String[] split;
                int i;
                String[] split2;
                int i2;
                String a2 = new com.alipay.sdk.h.a(this.b).a(str);
                if (!a2.contains("paymethod=\"expressGateway\"") && l.b(this.b)) {
                    f fVar = new f(this.b, new g(this));
                    a = fVar.a(a2);
                    fVar.a = null;
                    if (!TextUtils.equals(a, f.b)) {
                        if (TextUtils.isEmpty(a)) {
                            a = h.a();
                        }
                        context = this.b;
                        if (!TextUtils.isEmpty(a)) {
                            split = a.split(i.b);
                            i = 0;
                            while (i < split.length) {
                                if (split[i].startsWith(i.c) && split[i].endsWith(i.d)) {
                                    split2 = split[i].substring(8, split[i].length() - 1).split(com.alipay.sdk.h.a.b);
                                    i2 = 0;
                                    while (i2 < split2.length) {
                                        if (!split2[i2].startsWith(i.e) && split2[i2].endsWith("\"")) {
                                            obj = split2[i2].substring(13, split2[i2].length() - 1);
                                            break;
                                        } else if (split2[i2].startsWith(i.g)) {
                                            obj = split2[i2].substring(12);
                                            break;
                                        } else {
                                            i2++;
                                        }
                                    }
                                }
                                i++;
                            }
                        }
                        if (!TextUtils.isEmpty(obj)) {
                            j.a(context, i.a, obj);
                        }
                        com.alipay.sdk.c.a.b().a(this.b);
                        c();
                        com.alipay.sdk.app.a.a.a(this.b, str);
                        str2 = a;
                    }
                }
                a = b(a2);
                context = this.b;
                if (TextUtils.isEmpty(a)) {
                    split = a.split(i.b);
                    i = 0;
                    while (i < split.length) {
                        split2 = split[i].substring(8, split[i].length() - 1).split(com.alipay.sdk.h.a.b);
                        i2 = 0;
                        while (i2 < split2.length) {
                            if (!split2[i2].startsWith(i.e)) {
                            }
                            if (split2[i2].startsWith(i.g)) {
                                obj = split2[i2].substring(12);
                                break;
                            }
                            i2++;
                        }
                        i++;
                    }
                }
                if (TextUtils.isEmpty(obj)) {
                    j.a(context, i.a, obj);
                }
            } catch (Throwable th) {
                try {
                    str2 = h.a();
                    com.alipay.sdk.c.a.b().a(this.b);
                    c();
                    com.alipay.sdk.app.a.a.a(this.b, str);
                } catch (Throwable th2) {
                    com.alipay.sdk.c.a.b().a(this.b);
                    c();
                    com.alipay.sdk.app.a.a.a(this.b, str);
                }
            }
            com.alipay.sdk.c.a.b().a(this.b);
            c();
            com.alipay.sdk.app.a.a.a(this.b, str);
            str2 = a;
        }
        return str2;
    }

    public synchronized String fetchTradeToken() {
        return j.b(this.b, i.a, "");
    }

    public synchronized String fetchOrderInfoFromH5PayUrl(String str) {
        String trim;
        if (!TextUtils.isEmpty(str)) {
            String trim2;
            trim = str.trim();
            if (trim.startsWith("https://" + this.d) || trim.startsWith("http://" + this.d)) {
                trim2 = trim.replaceFirst("(http|https)://" + this.d + "\\?", "").trim();
                if (!TextUtils.isEmpty(trim2)) {
                    trim = "_input_charset=\"utf-8\"&ordertoken=\"" + l.a("<request_token>", "</request_token>", (String) l.a(trim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new com.alipay.sdk.h.a(this.b).a("sc", "h5tonative") + "\"";
                }
            }
            try {
                if (trim.startsWith("https://" + this.e) || trim.startsWith("http://" + this.e)) {
                    trim2 = trim.replaceFirst("(http|https)://" + this.e + "\\?", "").trim();
                    if (!TextUtils.isEmpty(trim2)) {
                        trim = "_input_charset=\"utf-8\"&ordertoken=\"" + l.a("<request_token>", "</request_token>", (String) l.a(trim2).get("req_data")) + "\"&pay_channel_id=\"alipay_sdk\"&bizcontext=\"" + new com.alipay.sdk.h.a(this.b).a("sc", "h5tonative") + "\"";
                    }
                }
                if ((trim.startsWith("https://" + this.f) || trim.startsWith("http://" + this.f)) && trim.contains("alipay.wap.create.direct.pay.by.user") && !TextUtils.isEmpty(trim.replaceFirst("(http|https)://" + this.f + "\\?", "").trim())) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("url", str);
                        jSONObject.put("bizcontext", new com.alipay.sdk.h.a(this.b).a("sc", "h5tonative"));
                        trim = "new_external_info==" + jSONObject.toString();
                    } catch (Throwable th) {
                    }
                }
                if (Pattern.compile("^(http|https)://(maliprod\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mali\\.alipay\\.com\\/w\\/trade_pay\\.do.?|mclient\\.alipay\\.com\\/w\\/trade_pay\\.do.?)").matcher(str).find()) {
                    trim = l.a("?", "", str);
                    if (!TextUtils.isEmpty(trim)) {
                        Map a = l.a(trim);
                        StringBuilder stringBuilder = new StringBuilder();
                        if (a(false, true, com.alipay.sdk.app.a.c.r, stringBuilder, a, com.alipay.sdk.app.a.c.r, "alipay_trade_no")) {
                            a(true, false, "pay_phase_id", stringBuilder, a, "payPhaseId", "pay_phase_id", "out_relation_id");
                            stringBuilder.append("&biz_sub_type=\"TRADE\"");
                            stringBuilder.append("&biz_type=\"trade\"");
                            trim = (String) a.get(ab.y);
                            if (TextUtils.isEmpty(trim) && !TextUtils.isEmpty((CharSequence) a.get("cid"))) {
                                trim = "ali1688";
                            } else if (TextUtils.isEmpty(trim) && !(TextUtils.isEmpty((CharSequence) a.get("sid")) && TextUtils.isEmpty((CharSequence) a.get("s_id")))) {
                                trim = "tb";
                            }
                            stringBuilder.append("&app_name=\"" + trim + "\"");
                            if (a(true, true, "extern_token", stringBuilder, a, "extern_token", "cid", "sid", "s_id")) {
                                a(true, false, "appenv", stringBuilder, a, "appenv");
                                stringBuilder.append("&pay_channel_id=\"alipay_sdk\"");
                                a aVar = new a();
                                aVar.a = (String) a.get("return_url");
                                aVar.b = (String) a.get("pay_order_id");
                                trim = stringBuilder.toString() + "&bizcontext=\"" + new com.alipay.sdk.h.a(this.b).a("sc", "h5tonative") + "\"";
                                this.g.put(trim, aVar);
                            } else {
                                trim = "";
                            }
                        }
                    }
                }
            } catch (Throwable th2) {
            }
        }
        trim = "";
        return trim;
    }

    private static boolean a(boolean z, boolean z2, String str, StringBuilder stringBuilder, Map<String, String> map, String... strArr) {
        String str2;
        String str3 = "";
        for (Object obj : strArr) {
            if (!TextUtils.isEmpty((CharSequence) map.get(obj))) {
                str2 = (String) map.get(obj);
                break;
            }
        }
        str2 = str3;
        if (TextUtils.isEmpty(str2)) {
            if (z2) {
                return false;
            }
        } else if (z) {
            stringBuilder.append(com.alipay.sdk.h.a.b).append(str).append("=\"").append(str2).append("\"");
        } else {
            stringBuilder.append(str).append("=\"").append(str2).append("\"");
        }
        return true;
    }

    public synchronized com.alipay.sdk.j.a h5Pay(String str, boolean z) {
        com.alipay.sdk.j.a aVar;
        synchronized (this) {
            com.alipay.sdk.j.a aVar2 = new com.alipay.sdk.j.a();
            try {
                str.trim();
                String[] split = pay(str, z).split(i.b);
                Map hashMap = new HashMap();
                for (String str2 : split) {
                    String substring = str2.substring(0, str2.indexOf("={"));
                    String str3 = substring + "={";
                    hashMap.put(substring, str2.substring(str3.length() + str2.indexOf(str3), str2.lastIndexOf(i.d)));
                }
                if (hashMap.containsKey("resultStatus")) {
                    aVar2.b((String) hashMap.get("resultStatus"));
                }
                if (hashMap.containsKey("callBackUrl")) {
                    aVar2.a((String) hashMap.get("callBackUrl"));
                } else if (hashMap.containsKey("result")) {
                    try {
                        String str4 = (String) hashMap.get("result");
                        if (str4.length() > 15) {
                            a aVar3 = (a) this.g.get(str);
                            if (aVar3 != null) {
                                if (TextUtils.isEmpty(aVar3.b)) {
                                    aVar2.a(aVar3.a);
                                } else {
                                    aVar2.a(com.alipay.sdk.c.a.b().i.replace("$OrderId$", aVar3.b));
                                }
                                this.g.remove(str);
                                aVar = aVar2;
                            } else {
                                CharSequence a = l.a("&callBackUrl=\"", "\"", str4);
                                if (TextUtils.isEmpty(a)) {
                                    a = l.a("&call_back_url=\"", "\"", str4);
                                    if (TextUtils.isEmpty(a)) {
                                        a = l.a("&return_url=\"", "\"", str4);
                                        if (TextUtils.isEmpty(a)) {
                                            a = URLDecoder.decode(l.a("&return_url=", com.alipay.sdk.h.a.b, str4), "utf-8");
                                            if (TextUtils.isEmpty(a)) {
                                                str4 = URLDecoder.decode(l.a("&callBackUrl=", com.alipay.sdk.h.a.b, str4), "utf-8");
                                                if (TextUtils.isEmpty(str4)) {
                                                    str4 = com.alipay.sdk.c.a.b().i;
                                                }
                                                aVar2.a(URLDecoder.decode(str4, "utf-8"));
                                            }
                                        }
                                    }
                                }
                                CharSequence charSequence = a;
                                if (TextUtils.isEmpty(str4)) {
                                    str4 = com.alipay.sdk.c.a.b().i;
                                }
                                aVar2.a(URLDecoder.decode(str4, "utf-8"));
                            }
                        } else {
                            a aVar4 = (a) this.g.get(str);
                            if (aVar4 != null) {
                                aVar2.a(aVar4.a);
                                this.g.remove(str);
                                aVar = aVar2;
                            }
                        }
                    } catch (Throwable th) {
                    }
                }
            } catch (Throwable th2) {
            }
            aVar = aVar2;
        }
        return aVar;
    }

    private static String a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf(i.d));
    }

    private com.alipay.sdk.j.f.a a() {
        return new g(this);
    }

    private void b() {
        if (this.c != null) {
            this.c.a();
        }
    }

    private void c() {
        if (this.c != null) {
            this.c.b();
        }
    }

    private String a(String str) {
        String a = new com.alipay.sdk.h.a(this.b).a(str);
        if (a.contains("paymethod=\"expressGateway\"")) {
            return b(a);
        }
        if (!l.b(this.b)) {
            return b(a);
        }
        f fVar = new f(this.b, new g(this));
        String a2 = fVar.a(a);
        fVar.a = null;
        if (TextUtils.equals(a2, f.b)) {
            return b(a);
        }
        if (TextUtils.isEmpty(a2)) {
            return h.a();
        }
        return a2;
    }

    public String getVersion() {
        return com.alipay.sdk.b.a.e;
    }

    private String b(String str) {
        com.alipay.sdk.i.a aVar;
        i iVar;
        int i = 0;
        b();
        try {
            List a = com.alipay.sdk.g.b.a(new d().a(this.b, str).a().optJSONObject(com.alipay.sdk.b.c.c).optJSONObject(com.alipay.sdk.b.c.d));
            for (int i2 = 0; i2 < a.size(); i2++) {
                if (((com.alipay.sdk.g.b) a.get(i2)).a == com.alipay.sdk.g.a.Update) {
                    String[] strArr = ((com.alipay.sdk.g.b) a.get(i2)).b;
                    if (strArr.length == 3 && TextUtils.equals(com.alipay.sdk.b.b.c, strArr[0])) {
                        Context context = b.a().a;
                        com.alipay.sdk.i.b a2 = com.alipay.sdk.i.b.a();
                        if (!(TextUtils.isEmpty(strArr[1]) || TextUtils.isEmpty(strArr[2]))) {
                            a2.a = strArr[1];
                            a2.b = strArr[2];
                            aVar = new com.alipay.sdk.i.a(context);
                            aVar.a(com.alipay.sdk.j.b.a(context).a(), com.alipay.sdk.j.b.a(context).b(), a2.a, a2.b);
                            aVar.close();
                        }
                    }
                }
            }
            c();
            while (i < a.size()) {
                if (((com.alipay.sdk.g.b) a.get(i)).a == com.alipay.sdk.g.a.WapPay) {
                    String a3 = a((com.alipay.sdk.g.b) a.get(i));
                    c();
                    return a3;
                }
                i++;
            }
            c();
            iVar = null;
        } catch (Exception e) {
            aVar.close();
        } catch (Throwable e2) {
            i a4 = i.a(i.NETWORK_ERROR.h);
            com.alipay.sdk.app.a.a.a(com.alipay.sdk.app.a.c.a, e2);
            c();
            iVar = a4;
        } catch (Throwable th) {
            c();
        }
        if (iVar == null) {
            iVar = i.a(i.FAILED.h);
        }
        return h.a(iVar.h, iVar.i, "");
    }

    private String a(com.alipay.sdk.g.b bVar) {
        String[] strArr = bVar.b;
        Intent intent = new Intent(this.b, H5PayActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("url", strArr[0]);
        if (strArr.length == 2) {
            bundle.putString("cookie", strArr[1]);
        }
        intent.putExtras(bundle);
        this.b.startActivity(intent);
        synchronized (a) {
            try {
                a.wait();
            } catch (InterruptedException e) {
                return h.a();
            }
        }
        String str = h.a;
        if (TextUtils.isEmpty(str)) {
            return h.a();
        }
        return str;
    }
}
