package dji.pilot.popup.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot.popup.b.b;
import dji.pilot.popup.model.PopupModel.Result;
import dji.pilot.publics.objects.DJIApplication;

public class a extends AlertDialog {
    public static final String a = "SpKeyIsShowPopupAgainWith";
    private static int c = -1126;
    @Deprecated
    private static final String n = "      ";
    private static final int o = (n.length() * 2);
    private Context b;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private String h;
    private String i;
    private String j;
    private String k;
    private a l;
    private b m;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.popup_dlg_activity);
        setCanceledOnTouchOutside(false);
        if (c != -1126) {
            getWindow().setFlags(8, 8);
            getWindow().getDecorView().setSystemUiVisibility(c);
        }
        b();
        a();
        c();
        this.g.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    private void a() {
        if (this.h != null) {
            this.f.setText(this.h);
        }
        if (this.i != null) {
            if (this.i.trim().length() >= 50) {
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
                int dimensionPixelSize = this.g.getContext().getResources().getDimensionPixelSize(R.dimen.a3r);
                layoutParams.setMargins(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, 0);
                this.g.setLayoutParams(layoutParams);
            }
            this.g.setText(this.i);
        }
        if (this.j != null) {
            this.d.setText(this.j);
        } else {
            this.d.setVisibility(4);
        }
        if (this.k != null) {
            this.e.setText(this.k);
        } else {
            this.e.setVisibility(4);
        }
    }

    private void b() {
        this.d = (TextView) findViewById(R.id.biy);
        this.e = (TextView) findViewById(R.id.biz);
        this.f = (TextView) findViewById(R.id.g8);
        this.g = (TextView) findViewById(R.id.g9);
    }

    private void c() {
        this.d.setOnClickListener(new 1(this));
        this.e.setOnClickListener(new 2(this));
    }

    private void a(String str) {
        this.h = str;
    }

    private void b(String str) {
        this.i = str;
    }

    public static void a(Context context, Result result) {
        boolean a = b.getInstance().a();
        dji.pilot.popup.c.a.a(context, "showSystemDialog(), isFlying= " + a);
        String str = result.title;
        String str2 = result.content;
        String str3 = result.left_button_msg;
        String str4 = result.right_button_msg;
        Context a2 = DJIApplication.a();
        if (1 == result.popup_time || (result.popup_time == 0 && !a)) {
            a(DJIApplication.a(), new 3(result, a2), new 4(context, result, a2), str, str2, str3, str4);
        }
    }

    public static a a(Context context, a aVar, b bVar, String... strArr) {
        a aVar2 = new a(context, aVar, bVar, strArr);
        aVar2.getWindow().setType(2003);
        aVar2.show();
        return aVar2;
    }

    private void a(String str, a aVar) {
        if (str != null) {
            this.k = str;
        }
        this.l = aVar;
    }

    private void a(String str, b bVar) {
        if (str != null) {
            this.j = str;
        }
        this.m = bVar;
    }

    private a(Context context, a aVar, b bVar, String... strArr) {
        int i;
        int i2 = 1;
        super(context, R.style.cx);
        int length = strArr.length;
        if (length >= 1) {
            a(strArr[0]);
        } else {
            i2 = 0;
        }
        if (length >= i2 + 1) {
            i = i2 + 1;
            b(strArr[i2]);
        } else {
            i = i2;
        }
        if (length >= i + 1) {
            i2 = i + 1;
            a(strArr[i], aVar);
        } else {
            i2 = i;
        }
        if (length >= i2 + 1) {
            i = i2 + 1;
            a(strArr[i2], bVar);
        }
    }

    public static a b(Context context, a aVar, b bVar, String... strArr) {
        a aVar2 = new a(context, aVar, bVar, strArr);
        aVar2.show();
        return aVar2;
    }

    @Deprecated
    public void show() {
        super.show();
        if (c != -1126) {
            getWindow().clearFlags(8);
        }
    }

    private static String a(Context context, int i) {
        return c(context.getResources().getString(i));
    }

    private static String c(String str) {
        int length = o - str.length();
        if (length <= 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(n.substring(0, length / 2)).append(str).append(n.substring(0, length / 2));
        return stringBuilder.toString();
    }
}
