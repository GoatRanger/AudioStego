package dji.pilot2.publics.a;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import dji.pilot.main.activity.DJISplashActivity;
import dji.pilot.publics.objects.g;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.publics.object.DJINotificationDialog;
import dji.pilot2.utils.s;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import java.util.Locale;

public class b {
    public static final String a = "open_url";
    private static final String b = "en";
    private static final String c = "zh";
    private static final String d = "ja";
    private static final String e = "lang_";
    private static final String f = "key_xg_lang_code_tag";
    private static final String g = "dji_test_tag";
    private boolean h = false;
    private String i = null;
    private DJINotificationDialog j = null;
    private Context k = null;
    private boolean l = false;

    private static final class a {
        private static final b a = new b();

        private a() {
        }
    }

    public synchronized void a(Context context) {
        if (!this.l) {
            this.k = context.getApplicationContext();
            s.a(this.k);
            d();
            this.l = true;
        }
    }

    public boolean a() {
        return this.h;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public String b() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public static b getInstance() {
        return a.a;
    }

    public void b(Context context) {
        if (this.j == null) {
            this.j = new DJINotificationDialog(context, this.i);
        }
        if (this.j != null && !this.j.isShowing() && !dji.pilot.publics.control.a.getInstance().q().isEmpty()) {
            this.j.show();
            this.j.setOnDismissListener(new OnDismissListener(this) {
                final /* synthetic */ b a;

                {
                    this.a = r1;
                }

                public void onDismiss(DialogInterface dialogInterface) {
                    this.a.j = null;
                }
            });
        }
    }

    public void c(Context context) {
        if (!(context instanceof DJISplashActivity) && this.h) {
            this.h = false;
            if (this.i != null) {
                if (context instanceof DJISupportShareWebviewActivity) {
                    ((DJISupportShareWebviewActivity) context).a(this.i);
                } else {
                    Intent intent = new Intent(context, DJISupportShareWebviewActivity.class);
                    intent.putExtra(DJIWebviewFragment.o, this.i);
                    context.startActivity(intent);
                }
                this.i = null;
            }
            d();
        }
    }

    private String c() {
        String str = "en";
        if (Locale.SIMPLIFIED_CHINESE.getLanguage().equals(Locale.getDefault().getLanguage()) && Locale.SIMPLIFIED_CHINESE.getCountry().equals(Locale.getDefault().getCountry())) {
            str = c;
        } else if (Locale.JAPANESE.getLanguage().equals(Locale.getDefault().getLanguage())) {
            str = d;
        }
        return e + str;
    }

    private void d() {
        String b = g.b(this.k, f, "");
        String c = c();
        if (b.equals(c)) {
            s.a(this.k, c);
            return;
        }
        if (!"".equals(b)) {
            s.b(this.k, b);
        }
        s.a(this.k, c);
        g.a(this.k, f, c);
    }
}
