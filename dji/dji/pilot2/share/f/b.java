package dji.pilot2.share.f;

import android.content.ComponentName;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.fpv.d.c$c;
import dji.pilot.fpv.d.c$r;
import dji.pilot.fpv.d.c$t;
import dji.pilot.fpv.d.c$u;
import dji.pilot.fpv.d.c.o;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.b.f;
import dji.pilot2.mine.activity.WebActivity;
import dji.pilot2.share.e.a.a;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.thirdparty.afinal.c;
import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

public class b implements o, c$r {

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] b = new int[ProductType.values().length];

        static {
            try {
                b[ProductType.Orange.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[ProductType.BigBanana.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[ProductType.Olives.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[ProductType.OrangeRAW.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[ProductType.litchiC.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                b[ProductType.litchiS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                b[ProductType.litchiX.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                b[ProductType.P34K.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                b[ProductType.Longan.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                b[ProductType.LonganPro.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                b[ProductType.LonganRaw.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                b[ProductType.LonganZoom.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                b[ProductType.N1.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                b[ProductType.Tomato.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                b[ProductType.Pomato.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                b[ProductType.KumquatX.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                b[ProductType.KumquatS.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                b[ProductType.OrangeCV600.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                b[ProductType.None.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                b[ProductType.OTHER.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            a = new int[dji.pilot2.share.e.a.b.values().length];
            try {
                a[dji.pilot2.share.e.a.b.PLATFORM_TYPE_INSTAGRAM.ordinal()] = 1;
            } catch (NoSuchFieldError e21) {
            }
            try {
                a[dji.pilot2.share.e.a.b.PLATFORM_TYPE_QQ.ordinal()] = 2;
            } catch (NoSuchFieldError e22) {
            }
            try {
                a[dji.pilot2.share.e.a.b.PLATFORM_TYPE_WECHAT.ordinal()] = 3;
            } catch (NoSuchFieldError e23) {
            }
            try {
                a[dji.pilot2.share.e.a.b.PLATFORM_TYPE_WECHAT_MOMENTS.ordinal()] = 4;
            } catch (NoSuchFieldError e24) {
            }
            try {
                a[dji.pilot2.share.e.a.b.PLATFORM_TYPE_WEIBO.ordinal()] = 5;
            } catch (NoSuchFieldError e25) {
            }
            try {
                a[dji.pilot2.share.e.a.b.PLATFORM_TYPE_FACKBOOK.ordinal()] = 6;
            } catch (NoSuchFieldError e26) {
            }
            try {
                a[dji.pilot2.share.e.a.b.PLATFORM_TYPE_TWITTER.ordinal()] = 7;
            } catch (NoSuchFieldError e27) {
            }
            try {
                a[dji.pilot2.share.e.a.b.PLATFORM_TYPE_TUMBLR.ordinal()] = 8;
            } catch (NoSuchFieldError e28) {
            }
            try {
                a[dji.pilot2.share.e.a.b.PLATFORM_TYPE_WHATSAPP.ordinal()] = 9;
            } catch (NoSuchFieldError e29) {
            }
            try {
                a[dji.pilot2.share.e.a.b.COPY_CHAINED_ADDRESS.ordinal()] = 10;
            } catch (NoSuchFieldError e30) {
            }
        }
    }

    public static String a(File file) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1048576];
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int read = fileInputStream.read(bArr, 0, 1048576);
                if (read != -1) {
                    instance.update(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    return new BigInteger(1, instance.digest()).toString(16);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(JSONArray jSONArray) {
        String str = "\"DJI Pilot Director\"";
        if (jSONArray != null && jSONArray.length() > 0) {
            int length = jSONArray.length();
            int i = 0;
            str = "";
            while (i < length) {
                try {
                    str = str + jSONArray.getString(i);
                    if (length > 1 && i != length - 1) {
                        str = str + ",";
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
        return str;
    }

    public static String a(Context context, Uri uri) {
        Cursor loadInBackground = new CursorLoader(context, uri, new String[]{"_data"}, null, null, null).loadInBackground();
        int columnIndexOrThrow = loadInBackground.getColumnIndexOrThrow("_data");
        loadInBackground.moveToFirst();
        return loadInBackground.getString(columnIndexOrThrow);
    }

    public static void a(Context context, String str, String str2, String str3, String str4) {
        List a = a(context, str, str2, str3);
        Intent createChooser = Intent.createChooser((Intent) a.remove(0), context.getResources().getString(R.string.app_share));
        if (createChooser != null) {
            createChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) a.toArray(new LabeledIntent[a.size()]));
            createChooser.setFlags(268435456);
            context.startActivity(createChooser);
        }
    }

    private static void b(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(str2);
        intent.putExtra("android.intent.extra.STREAM", Uri.fromFile(new File(str)));
        intent.setPackage("com.instagram.android");
        context.startActivity(intent);
    }

    public static void a(Context context, String str, a aVar, dji.pilot2.share.b.b.a aVar2) {
        String str2 = "image/*";
        if (aVar == a.CONTENT_IMG) {
            str2 = "image/*";
        } else if (aVar == a.CONTENT_VIDEO) {
            str2 = "video/*";
        }
        b(context, str, str2);
        a(dji.pilot2.share.e.a.b.PLATFORM_TYPE_INSTAGRAM, aVar, aVar2);
    }

    public static void a(Context context, String str, String str2, String str3, String str4, dji.pilot2.share.e.a.b bVar, a aVar, dji.pilot2.share.b.b.a aVar2) {
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(DJIWebviewFragment.q, true);
        context.startActivity(intent);
        dji.pilot2.share.e.a aVar3 = new dji.pilot2.share.e.a(context);
        aVar3.a(str, str2, str3, str4);
        aVar3.a(bVar, aVar);
        a(bVar, aVar, aVar2);
    }

    private static void a(dji.pilot2.share.e.a.b bVar, a aVar, dji.pilot2.share.b.b.a aVar2) {
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        if (aVar2 == dji.pilot2.share.b.b.a.EDIT_UPLOAD) {
            if (aVar == a.CONTENT_IMG) {
                switch (bVar) {
                    case PLATFORM_TYPE_INSTAGRAM:
                        if (language.equalsIgnoreCase("zh")) {
                            if (country.equalsIgnoreCase("CN")) {
                                e.b(c$t.h);
                                return;
                            } else {
                                e.b(c$t.q);
                                return;
                            }
                        } else if (language.equalsIgnoreCase("en")) {
                            e.b(c$t.z);
                            return;
                        } else {
                            e.b(c$t.I);
                            return;
                        }
                    case PLATFORM_TYPE_QQ:
                        e.b(c$r.q);
                        if (language.equalsIgnoreCase("zh")) {
                            if (country.equalsIgnoreCase("CN")) {
                                e.b(c$t.g);
                                return;
                            } else {
                                e.b(c$t.p);
                                return;
                            }
                        } else if (language.equalsIgnoreCase("en")) {
                            e.b(c$t.y);
                            return;
                        } else {
                            e.b(c$t.H);
                            return;
                        }
                    case PLATFORM_TYPE_WECHAT:
                        e.b(c$r.l);
                        if (language.equalsIgnoreCase("zh")) {
                            if (country.equalsIgnoreCase("CN")) {
                                e.b(c$t.c);
                                return;
                            } else {
                                e.b(c$t.l);
                                return;
                            }
                        } else if (language.equalsIgnoreCase("en")) {
                            e.b(c$t.u);
                            return;
                        } else {
                            e.b(c$t.D);
                            return;
                        }
                    case PLATFORM_TYPE_WECHAT_MOMENTS:
                        e.b(c$r.m);
                        if (language.equalsIgnoreCase("zh")) {
                            if (country.equalsIgnoreCase("CN")) {
                                e.b(c$t.d);
                                return;
                            } else {
                                e.b(c$t.m);
                                return;
                            }
                        } else if (language.equalsIgnoreCase("en")) {
                            e.b(c$t.v);
                            return;
                        } else {
                            e.b(c$t.E);
                            return;
                        }
                    case PLATFORM_TYPE_WEIBO:
                        e.b(c$r.n);
                        if (language.equalsIgnoreCase("zh")) {
                            if (country.equalsIgnoreCase("CN")) {
                                e.b(c$t.e);
                                return;
                            } else {
                                e.b(c$t.n);
                                return;
                            }
                        } else if (language.equalsIgnoreCase("en")) {
                            e.b(c$t.w);
                            return;
                        } else {
                            e.b(c$t.F);
                            return;
                        }
                    case PLATFORM_TYPE_FACKBOOK:
                        e.b(c$r.eQ_);
                        if (language.equalsIgnoreCase("zh")) {
                            if (country.equalsIgnoreCase("CN")) {
                                e.b(c$t.a);
                                return;
                            } else {
                                e.b(c$t.j);
                                return;
                            }
                        } else if (language.equalsIgnoreCase("en")) {
                            e.b(c$t.s);
                            return;
                        } else {
                            e.b(c$t.B);
                            return;
                        }
                    case PLATFORM_TYPE_TWITTER:
                        e.b(c$r.k);
                        if (language.equalsIgnoreCase("zh")) {
                            if (country.equalsIgnoreCase("CN")) {
                                e.b(c$t.b);
                                return;
                            } else {
                                e.b(c$t.k);
                                return;
                            }
                        } else if (language.equalsIgnoreCase("en")) {
                            e.b(c$t.t);
                            return;
                        } else {
                            e.b(c$t.C);
                            return;
                        }
                    case PLATFORM_TYPE_TUMBLR:
                        e.b(c$r.o);
                        return;
                    case PLATFORM_TYPE_WHATSAPP:
                        e.b(c$r.p);
                        if (language.equalsIgnoreCase("zh")) {
                            if (country.equalsIgnoreCase("CN")) {
                                e.b(c$t.f);
                                return;
                            } else {
                                e.b(c$t.o);
                                return;
                            }
                        } else if (language.equalsIgnoreCase("en")) {
                            e.b(c$t.x);
                            return;
                        } else {
                            e.b(c$t.G);
                            return;
                        }
                    case COPY_CHAINED_ADDRESS:
                        e.b(c$r.r);
                        if (language.equalsIgnoreCase("zh")) {
                            if (country.equalsIgnoreCase("CN")) {
                                e.b(c$t.i);
                                return;
                            } else {
                                e.b(c$t.r);
                                return;
                            }
                        } else if (language.equalsIgnoreCase("en")) {
                            e.b(c$t.A);
                            return;
                        } else {
                            e.b(c$t.J);
                            return;
                        }
                    default:
                        return;
                }
            }
            switch (bVar) {
                case PLATFORM_TYPE_INSTAGRAM:
                    if (language.equalsIgnoreCase("zh")) {
                        if (country.equalsIgnoreCase("CN")) {
                            e.b(c$u.h);
                            return;
                        } else {
                            e.b(c$u.q);
                            return;
                        }
                    } else if (language.equalsIgnoreCase("en")) {
                        e.b(c$u.z);
                        return;
                    } else {
                        e.b(c$u.I);
                        return;
                    }
                case PLATFORM_TYPE_QQ:
                    e.b(c$r.eO_);
                    if (language.equalsIgnoreCase("zh")) {
                        if (country.equalsIgnoreCase("CN")) {
                            e.b(c$u.g);
                            return;
                        } else {
                            e.b(c$u.p);
                            return;
                        }
                    } else if (language.equalsIgnoreCase("en")) {
                        e.b(c$u.y);
                        return;
                    } else {
                        e.b(c$u.H);
                        return;
                    }
                case PLATFORM_TYPE_WECHAT:
                    e.b(c$r.c);
                    if (language.equalsIgnoreCase("zh")) {
                        if (country.equalsIgnoreCase("CN")) {
                            e.b(c$u.c);
                            return;
                        } else {
                            e.b(c$u.l);
                            return;
                        }
                    } else if (language.equalsIgnoreCase("en")) {
                        e.b(c$u.u);
                        return;
                    } else {
                        e.b(c$u.D);
                        return;
                    }
                case PLATFORM_TYPE_WECHAT_MOMENTS:
                    e.b(c$r.d);
                    if (language.equalsIgnoreCase("zh")) {
                        if (country.equalsIgnoreCase("CN")) {
                            e.b(c$u.d);
                            return;
                        } else {
                            e.b(c$u.m);
                            return;
                        }
                    } else if (language.equalsIgnoreCase("en")) {
                        e.b(c$u.v);
                        return;
                    } else {
                        e.b(c$u.E);
                        return;
                    }
                case PLATFORM_TYPE_WEIBO:
                    e.b(c$r.e);
                    if (language.equalsIgnoreCase("zh")) {
                        if (country.equalsIgnoreCase("CN")) {
                            e.b(c$u.e);
                            return;
                        } else {
                            e.b(c$u.n);
                            return;
                        }
                    } else if (language.equalsIgnoreCase("en")) {
                        e.b(c$u.w);
                        return;
                    } else {
                        e.b(c$u.F);
                        return;
                    }
                case PLATFORM_TYPE_FACKBOOK:
                    e.b(c$r.a);
                    if (language.equalsIgnoreCase("zh")) {
                        if (country.equalsIgnoreCase("CN")) {
                            e.b(c$u.a);
                            return;
                        } else {
                            e.b(c$u.s);
                            return;
                        }
                    } else if (language.equalsIgnoreCase("en")) {
                        e.b(c$u.s);
                        return;
                    } else {
                        e.b(c$u.B);
                        return;
                    }
                case PLATFORM_TYPE_TWITTER:
                    e.b(c$r.b);
                    if (language.equalsIgnoreCase("zh")) {
                        if (country.equalsIgnoreCase("CN")) {
                            e.b(c$u.b);
                            return;
                        } else {
                            e.b(c$u.k);
                            return;
                        }
                    } else if (language.equalsIgnoreCase("en")) {
                        e.b(c$u.t);
                        return;
                    } else {
                        e.b(c$u.C);
                        return;
                    }
                case PLATFORM_TYPE_TUMBLR:
                    e.b(c$r.eM_);
                    return;
                case PLATFORM_TYPE_WHATSAPP:
                    e.b(c$r.eN_);
                    if (language.equalsIgnoreCase("zh")) {
                        if (country.equalsIgnoreCase("CN")) {
                            e.b(c$u.f);
                            return;
                        } else {
                            e.b(c$u.o);
                            return;
                        }
                    } else if (language.equalsIgnoreCase("en")) {
                        e.b(c$u.x);
                        return;
                    } else {
                        e.b(c$u.G);
                        return;
                    }
                case COPY_CHAINED_ADDRESS:
                    e.b(c$r.eP_);
                    if (language.equalsIgnoreCase("zh")) {
                        if (country.equalsIgnoreCase("CN")) {
                            e.b(c$u.i);
                            return;
                        } else {
                            e.b(c$u.r);
                            return;
                        }
                    } else if (language.equalsIgnoreCase("en")) {
                        e.b(c$u.A);
                        return;
                    } else {
                        e.b(c$u.J);
                        return;
                    }
                default:
                    return;
            }
        } else if (aVar2 == dji.pilot2.share.b.b.a.EXPLORE_MINE) {
            e.b(o.dY_);
            if (aVar == a.CONTENT_IMG) {
                e.b(o.dZ_);
            } else {
                e.b(o.cE_);
            }
            switch (bVar) {
                case PLATFORM_TYPE_QQ:
                    e.b(o.eg_);
                    return;
                case PLATFORM_TYPE_WECHAT:
                    e.b(o.eb_);
                    return;
                case PLATFORM_TYPE_WECHAT_MOMENTS:
                    e.b(o.ec_);
                    return;
                case PLATFORM_TYPE_WEIBO:
                    e.b(o.ed_);
                    return;
                case PLATFORM_TYPE_FACKBOOK:
                    e.b(o.cF_);
                    return;
                case PLATFORM_TYPE_TWITTER:
                    e.b(o.ea_);
                    return;
                case PLATFORM_TYPE_TUMBLR:
                    e.b(o.ee_);
                    return;
                case PLATFORM_TYPE_WHATSAPP:
                    e.b(o.ef_);
                    return;
                case COPY_CHAINED_ADDRESS:
                    e.b(o.eh_);
                    return;
                default:
                    return;
            }
        } else if (aVar2 == dji.pilot2.share.b.b.a.GIFT_SHARE) {
            DJILogHelper.getInstance().LOGI("bob", "logShare platform=" + bVar);
            a(bVar, aVar);
        }
    }

    private static void a(dji.pilot2.share.e.a.b bVar, a aVar) {
        switch (bVar) {
            case PLATFORM_TYPE_QQ:
                e.b(c$c.i);
                return;
            case PLATFORM_TYPE_WECHAT:
                e.b(c$c.j);
                return;
            case PLATFORM_TYPE_WECHAT_MOMENTS:
                e.b(c$c.e);
                return;
            case PLATFORM_TYPE_WEIBO:
                e.b(c$c.h);
                return;
            case PLATFORM_TYPE_FACKBOOK:
                e.b(c$c.f);
                return;
            case PLATFORM_TYPE_TWITTER:
                e.b(c$c.g);
                return;
            case PLATFORM_TYPE_TUMBLR:
                e.b(c$c.n);
                return;
            case PLATFORM_TYPE_WHATSAPP:
                e.b(c$c.m);
                return;
            case COPY_CHAINED_ADDRESS:
                e.b(c$c.o);
                return;
            default:
                return;
        }
    }

    private static List<Intent> a(Context context, String str, String str2, String str3) {
        Parcelable parcelable;
        List<Intent> list = null;
        Intent intent = new Intent("android.intent.action.SEND");
        if (str3 == null || str3.equals("")) {
            intent.setType("text/plain");
            parcelable = null;
        } else {
            File file = new File(str3);
            if (file != null && file.exists() && file.isFile()) {
                intent.setType("image/*");
                Object fromFile = Uri.fromFile(file);
            } else {
                parcelable = null;
            }
        }
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (!queryIntentActivities.isEmpty()) {
            list = new ArrayList();
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setType(intent.getType());
                intent2.putExtra("android.intent.extra.SUBJECT", str);
                if (intent2.getType().equals("image/*") && parcelable != null) {
                    intent2.putExtra("android.intent.extra.STREAM", parcelable);
                }
                ActivityInfo activityInfo = resolveInfo.activityInfo;
                String str4 = activityInfo.packageName;
                if (str4.contains("com.tencent.mm")) {
                    intent2.putExtra("Kdescription", str2);
                } else {
                    intent2.putExtra("android.intent.extra.TEXT", str2);
                }
                if (!intent.getType().equals("text/plain") || !str4.contains("com.tencent.mm")) {
                    intent2.setComponent(new ComponentName(str4, activityInfo.name));
                    list.add(new LabeledIntent(intent2, str4, resolveInfo.loadLabel(packageManager), resolveInfo.icon));
                }
            }
        }
        return list;
    }

    public static void a(Context context, String str, String str2) {
        c b = com.dji.frame.c.c.b(context);
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("url", str);
        bVar.a("type", str2);
        bVar.a("token", f.getInstance().n());
        b.b(a.a, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
            }

            public void a(Throwable th, int i, String str) {
            }
        });
    }

    public static String a(Context context, ProductType productType) {
        context.getString(R.string.v2_product_type_unknow);
        switch (AnonymousClass2.b[productType.ordinal()]) {
            case 1:
                return context.getString(R.string.v2_product_type_orange);
            case 2:
                return context.getString(R.string.v2_product_type_bigbanana);
            case 3:
                return context.getString(R.string.v2_product_type_olives);
            case 4:
                return context.getString(R.string.v2_product_type_orange_raw);
            case 5:
                return context.getString(R.string.v2_product_type_litchic);
            case 6:
                return context.getString(R.string.v2_product_type_litchis);
            case 7:
                return context.getString(R.string.v2_product_type_litchix);
            case 8:
                return context.getString(R.string.v2_product_type_litchixw);
            case 9:
                return context.getString(R.string.v2_product_type_longan);
            case 10:
                return context.getString(R.string.v2_product_type_longan_pro);
            case 11:
                return context.getString(R.string.v2_product_type_longan_raw);
            case 12:
                return context.getString(R.string.v2_product_type_longan_zoom);
            case 13:
                return context.getString(R.string.v2_product_type_n1);
            case 14:
                return context.getString(R.string.v2_product_type_tomato);
            case 15:
                return context.getString(R.string.v2_product_type_osmanthus);
            case 16:
                return context.getString(R.string.v2_product_type_wm220);
            case 17:
                return context.getString(R.string.v2_product_type_wm220_s);
            case 18:
                return context.getString(R.string.v2_product_type_orangecv600);
            case 19:
                return context.getString(R.string.v2_product_type_unknow);
            case 20:
                return context.getString(R.string.v2_product_type_unknow);
            default:
                return context.getString(R.string.v2_product_type_unknow);
        }
    }

    public static String a(Context context, String str) {
        String string = context.getString(R.string.v2_product_type_unknow);
        if (str.equalsIgnoreCase("FC350")) {
            return context.getString(R.string.v2_product_type_orange);
        }
        if (str.equalsIgnoreCase("FC300S")) {
            return context.getString(R.string.v2_product_type_litchis);
        }
        if (str.equalsIgnoreCase("FC300C")) {
            return context.getString(R.string.v2_product_type_litchic);
        }
        if (str.equalsIgnoreCase("FC300X")) {
            return context.getString(R.string.v2_product_type_litchix);
        }
        if (str.equalsIgnoreCase("HG310")) {
            return context.getString(R.string.v2_product_type_longan);
        }
        if (str.equalsIgnoreCase("OSMO PRO")) {
            return context.getString(R.string.v2_product_type_longan_pro);
        }
        if (str.equalsIgnoreCase("OSMO RAW")) {
            return context.getString(R.string.v2_product_type_longan_raw);
        }
        if ("FC300XW".equalsIgnoreCase(str)) {
            return context.getString(R.string.v2_product_type_litchixw);
        }
        if ("FC330".equalsIgnoreCase(str)) {
            return context.getString(R.string.v2_product_type_tomato);
        }
        if ("FLIR".equalsIgnoreCase(str)) {
            return context.getString(R.string.v2_product_type_flir);
        }
        if ("FC550RAW".equalsIgnoreCase(str)) {
            return context.getString(R.string.v2_product_type_orange_raw);
        }
        if ("FC220".equalsIgnoreCase(str)) {
            return context.getString(R.string.v2_product_type_wm220);
        }
        if ("FC220S".equalsIgnoreCase(str)) {
            return context.getString(R.string.v2_product_type_wm220_s);
        }
        return string;
    }
}
