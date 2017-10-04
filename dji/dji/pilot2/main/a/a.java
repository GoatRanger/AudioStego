package dji.pilot2.main.a;

import android.content.Context;
import com.dji.frame.c.c;
import com.dji.frame.c.d;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.publics.objects.g;
import dji.pilot2.explore.fragment.DJISupportShareWebviewFragment;
import dji.pilot2.main.model.DJISplashNewDataModel;
import dji.pilot2.main.model.DJISplashNewDataModel.SplashImages;
import dji.pilot2.publics.b.a$m;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.thirdparty.afinal.f.b;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class a implements a$m {
    public static final String a = "splash_pre_lang";
    public static final String b = "DJI_SPALSH/";
    public static final String c = "type";
    public static final String d = "last_splash_";
    public static final String e = "next_splash_";
    public static final String f = "author";
    public static final String g = "device";
    public static final String h = "time_available";
    public static final String i = "duration";
    public static final String j = "target_url";
    public static final String k = "has_shown";
    public static final String l = "works";
    public static final String m = "ads";
    public static final String n = ".jpg";
    private static final String q = "lang";

    private static String a() {
        if (dji.pilot.c.a.u) {
            return a$m.o;
        }
        return a$m.p;
    }

    public static String a(Context context) {
        return d.a(context, b);
    }

    public static void b(final Context context) {
        b.a(context);
        b bVar = new b();
        String str = "en";
        if (k.s().equals(dji.pilot2.publics.b.a.p)) {
            str = "cn";
        }
        bVar.a(q, str);
        c.b(context).a(a(), bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                a.b(str, context);
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGI(DJISupportShareWebviewFragment.T, "splash_post_fail");
            }
        });
    }

    private static void b(String str, Context context) {
        DJISplashNewDataModel dJISplashNewDataModel = (DJISplashNewDataModel) h.b(str, DJISplashNewDataModel.class);
        if (dJISplashNewDataModel != null) {
            a(context, a(context, dJISplashNewDataModel.last_splash, d), a(context, dJISplashNewDataModel.next_splash, e));
        }
    }

    private static void a(Context context, String str, String str2) {
        File file = new File(a(context));
        if (file.exists()) {
            String a = a(context);
            for (String str3 : file.list()) {
                if (!(str3.equals(str) || str3.equals(str2))) {
                    new File(a + str3).delete();
                }
            }
        }
    }

    private static String a(Context context, SplashImages splashImages, String str) {
        if (splashImages == null) {
            return null;
        }
        String str2 = splashImages.time_available + n;
        g.a(context, str + "type", splashImages.type);
        g.a(context, str + f, splashImages.author);
        g.a(context, str + "device", splashImages.device);
        g.a(context, str + h, splashImages.time_available);
        g.a(context, str + "duration", splashImages.duration);
        g.a(context, str + j, splashImages.target_url);
        String a = a(context);
        Object obj = splashImages.mobile_md5;
        String str3 = splashImages.mobile_img;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            obj = splashImages.pad_md5;
            str3 = splashImages.pad_img;
        }
        File file = new File(a + str2);
        if (file.exists() && com.dji.videouploadsdk.a.b.a(file).equals(r1)) {
            return str2;
        }
        g.a(context, str + k, false);
        try {
            c.b(context).a(str3, a + str2, new dji.thirdparty.afinal.f.a<File>() {
                public void a(File file) {
                    DJILogHelper.getInstance().LOGD(DJISupportShareWebviewFragment.T, file.getName());
                }

                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(Throwable th, int i, String str) {
                }
            });
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return str2;
    }

    public static String c(Context context) {
        Date parse;
        Date date;
        ParseException e;
        Date time;
        String str;
        boolean b = g.b(context, "last_splash_has_shown", false);
        boolean b2 = g.b(context, "next_splash_has_shown", false);
        if (b && b2) {
            return null;
        }
        String b3 = g.b(context, "last_splash_time_available", "");
        String b4 = g.b(context, "next_splash_time_available", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            parse = simpleDateFormat.parse(b3);
            try {
                date = parse;
                parse = simpleDateFormat.parse(b4);
            } catch (ParseException e2) {
                e = e2;
                e.printStackTrace();
                date = parse;
                parse = null;
                if (date != null) {
                }
                time = Calendar.getInstance().getTime();
                if (date == null) {
                    if (!b) {
                        g.a(context, "last_splash_has_shown", true);
                        str = b3;
                    }
                    str = null;
                } else {
                    if (!b2) {
                        g.a(context, "next_splash_has_shown", true);
                        str = b4;
                    }
                    str = null;
                }
                if (str != null) {
                    return null;
                }
                try {
                    if (simpleDateFormat.parse(str).before(time)) {
                        return null;
                    }
                    return str;
                } catch (ParseException e3) {
                    e3.printStackTrace();
                }
            }
        } catch (ParseException e4) {
            e3 = e4;
            parse = null;
            e3.printStackTrace();
            date = parse;
            parse = null;
            if (date != null) {
            }
            time = Calendar.getInstance().getTime();
            if (date == null) {
                if (b2) {
                    g.a(context, "next_splash_has_shown", true);
                    str = b4;
                }
                str = null;
            } else {
                if (b) {
                    g.a(context, "last_splash_has_shown", true);
                    str = b3;
                }
                str = null;
            }
            if (str != null) {
                return null;
            }
            if (simpleDateFormat.parse(str).before(time)) {
                return str;
            }
            return null;
        }
        if (date != null && parse == null) {
            return null;
        }
        time = Calendar.getInstance().getTime();
        if (date == null || parse == null) {
            if (date == null) {
                if (b2) {
                    g.a(context, "next_splash_has_shown", true);
                    str = b4;
                }
                str = null;
            } else {
                if (b) {
                    g.a(context, "last_splash_has_shown", true);
                    str = b3;
                }
                str = null;
            }
            if (str != null) {
                return null;
            }
            if (simpleDateFormat.parse(str).before(time)) {
                return str;
            }
            return null;
        }
        if (parse.before(date)) {
            if (time.after(date) && !b) {
                g.a(context, "last_splash_has_shown", true);
                return b3;
            } else if (!time.after(parse) || b2) {
                return null;
            } else {
                g.a(context, "next_splash_has_shown", true);
                return b4;
            }
        } else if (time.after(parse) && !b2) {
            g.a(context, "next_splash_has_shown", true);
            return b4;
        } else if (!time.after(date) || b) {
            return null;
        } else {
            g.a(context, "last_splash_has_shown", true);
            return b3;
        }
    }
}
