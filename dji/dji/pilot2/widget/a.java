package dji.pilot2.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewConfiguration;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.dji.frame.c.c;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.utils.p;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;

public class a extends Dialog {
    public static final String a = "DJIFirstTipDialog";
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    public static final int i = 8;
    public static final int j = 9;
    public static final String k = "dji_prefile";
    public static final String l = "first_run";
    private static boolean n = false;
    private static boolean o = false;
    private static boolean p = false;
    int m = 1;
    private Context q;
    private DJITextView r;
    private TextView s;
    private RelativeLayout t;
    private DJIFirstTipCover u;
    private DJILinearLayout v;
    private DJIImageView w;
    private a x;

    public interface a {
        void a();
    }

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[com.dji.frame.c.c.a.values().length];

        static {
            try {
                a[com.dji.frame.c.c.a.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[com.dji.frame.c.c.a.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static boolean a(Context context, int i) {
        if (context.getSharedPreferences(k, 0).getBoolean(l + i, true)) {
            return true;
        }
        return false;
    }

    public static void b(Context context, int i) {
        Editor edit = context.getSharedPreferences(k, 0).edit();
        edit.putBoolean(l + i, false);
        edit.commit();
    }

    public static void c(Context context, int i) {
        Editor edit = context.getSharedPreferences(k, 0).edit();
        edit.putBoolean(l + i, true);
        edit.commit();
    }

    public a(Context context) {
        super(context);
        this.q = context;
    }

    public a(Context context, int i, int i2) {
        super(context, i);
        this.q = context;
        this.m = i2;
        a(i2);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.t = (RelativeLayout) findViewById(R.id.rw);
        this.s = (TextView) findViewById(R.id.s0);
        this.r = (DJITextView) findViewById(R.id.s2);
        this.r.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                DJILogHelper.getInstance().LOGI("wbc", "onClick DJIFirstTipDialog");
                if (this.a.x != null) {
                    this.a.x.a();
                }
                this.a.dismiss();
            }
        });
    }

    public void a(a aVar) {
        this.x = aVar;
    }

    public void a(int i) {
        switch (i) {
            case 1:
                setContentView(R.layout.dialog_ve_firsttip_1);
                return;
            case 2:
                setContentView(R.layout.dialog_ve_firsttip_2);
                return;
            case 3:
                setContentView(R.layout.dialog_ve_firsttip_3);
                return;
            case 4:
                setContentView(R.layout.dialog_ve_firsttip_4);
                return;
            case 6:
                setContentView(R.layout.dialog_ve_firsttip_5);
                return;
            case 7:
                setContentView(R.layout.dialog_ve_firsttip_7);
                return;
            case 8:
                setContentView(R.layout.dialog_ve_firsttip_8);
                return;
            default:
                return;
        }
    }

    public void setContentView(int i) {
        super.setContentView(i);
        c.a(getWindow());
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.thirdparty.a.c.a().e(com.dji.frame.c.c.a.a);
    }

    public void onEventMainThread(com.dji.frame.c.c.a aVar) {
        if (isShowing()) {
            switch (AnonymousClass2.a[aVar.ordinal()]) {
                case 1:
                    c.a(getWindow());
                    return;
                case 2:
                    return;
                default:
                    return;
            }
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        c.a(getWindow());
        if (motionEvent.getAction() == 0 && a(motionEvent) && b()) {
            return true;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public void a() {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.flags |= 32;
        getWindow().setAttributes(attributes);
    }

    public void a(float f) {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.dimAmount = f;
        attributes.flags |= 2;
        getWindow().setAttributes(attributes);
    }

    protected boolean b() {
        return false;
    }

    protected boolean a(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int scaledWindowTouchSlop = ViewConfiguration.get(this.q).getScaledWindowTouchSlop();
        View decorView = getWindow().getDecorView();
        return x < (-scaledWindowTouchSlop) || y < (-scaledWindowTouchSlop) || x > decorView.getWidth() + scaledWindowTouchSlop || y > decorView.getHeight() + scaledWindowTouchSlop;
    }

    public void a(int i, int i2, int i3, int i4) {
        if (VERSION.SDK_INT < 19) {
            i2 -= p.d(this.q);
        }
        switch (this.m) {
            case 1:
                f(i, i2, i3, i4);
                return;
            case 2:
                e(i, i2, i3, i4);
                return;
            case 3:
                d(i, i2, i3, i4);
                return;
            case 4:
                c(i, i2, i3, i4);
                return;
            case 6:
                b(i, i2, i3, i4);
                return;
            case 7:
                b(i, i2, i3, i4);
                return;
            case 8:
                b(i, i2, i3, i4);
                return;
            default:
                return;
        }
    }

    private void b(int i, int i2, int i3, int i4) {
        this.u = (DJIFirstTipCover) findViewById(R.id.rx);
        this.v = (DJILinearLayout) findViewById(R.id.ry);
        this.w = (DJIImageView) findViewById(R.id.s5);
        this.u.locationRing(i, i2, i3);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
        this.v.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        int measuredHeight = this.v.getMeasuredHeight();
        layoutParams.leftMargin = (i - this.v.getMeasuredWidth()) + (i3 * 2);
        layoutParams.topMargin = (i2 - measuredHeight) - i4;
        this.v.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.w.getLayoutParams();
        this.w.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        layoutParams2.rightMargin = i3 - (this.w.getMeasuredWidth() / 2);
        this.w.setLayoutParams(layoutParams2);
    }

    private void c(int i, int i2, int i3, int i4) {
        this.u = (DJIFirstTipCover) findViewById(R.id.rx);
        this.v = (DJILinearLayout) findViewById(R.id.ry);
        this.w = (DJIImageView) findViewById(R.id.s6);
        this.u.locationRing(i, i2, i3);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
        layoutParams.leftMargin = i;
        layoutParams.topMargin = ((i3 * 2) + i2) + i4;
        this.v.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.w.getLayoutParams();
        this.w.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        layoutParams2.leftMargin = i3 - (this.w.getMeasuredWidth() / 2);
        this.w.setVisibility(0);
        this.w.setLayoutParams(layoutParams2);
    }

    private void d(int i, int i2, int i3, int i4) {
        c(i, i2, i3, i4);
    }

    private void e(int i, int i2, int i3, int i4) {
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.u = (DJIFirstTipCover) findViewById(R.id.rx);
            this.v = (DJILinearLayout) findViewById(R.id.ry);
            this.w = (DJIImageView) findViewById(R.id.s5);
            this.u.locationRing(i, i2, i3);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
            this.v.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            int measuredHeight = this.v.getMeasuredHeight();
            layoutParams.leftMargin = i;
            layoutParams.topMargin = (i2 - measuredHeight) - i4;
            this.v.setLayoutParams(layoutParams);
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.w.getLayoutParams();
            this.w.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
            layoutParams2.leftMargin = i3 - (this.w.getMeasuredWidth() / 2);
            this.w.setLayoutParams(layoutParams2);
            this.w.setVisibility(0);
            return;
        }
        this.u = (DJIFirstTipCover) findViewById(R.id.rx);
        this.v = (DJILinearLayout) findViewById(R.id.ry);
        this.w = (DJIImageView) findViewById(R.id.s4);
        this.u.locationRing(i, i2, i3);
        layoutParams = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
        layoutParams.leftMargin = i;
        layoutParams.topMargin = ((i3 * 2) + i2) + i4;
        this.v.setLayoutParams(layoutParams);
        layoutParams2 = (LinearLayout.LayoutParams) this.w.getLayoutParams();
        this.w.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        layoutParams2.leftMargin = i3 - (this.w.getMeasuredWidth() / 2);
        this.w.setLayoutParams(layoutParams2);
        this.w.setVisibility(0);
    }

    private void f(int i, int i2, int i3, int i4) {
        this.u = (DJIFirstTipCover) findViewById(R.id.rx);
        this.v = (DJILinearLayout) findViewById(R.id.ry);
        this.w = (DJIImageView) findViewById(R.id.s3);
        this.u.locationRing(i, i2, i3);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.v.getLayoutParams();
        this.v.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth = this.v.getMeasuredWidth();
        int measuredHeight = this.v.getMeasuredHeight();
        layoutParams.leftMargin = i - ((measuredWidth / 2) - i3);
        layoutParams.topMargin = (i2 - measuredHeight) - i4;
        this.v.setLayoutParams(layoutParams);
        LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.w.getLayoutParams();
        this.w.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
        layoutParams2.leftMargin = (measuredWidth / 2) - (this.w.getMeasuredWidth() / 2);
        this.w.setLayoutParams(layoutParams2);
        this.w.setVisibility(0);
    }

    public static void a(Context context) {
        c(context, 1);
        c(context, 4);
        c(context, 2);
        c(context, 3);
        c(context, 5);
        c(context, 6);
        c(context, 7);
        c(context, 9);
        a(true);
        dji.thirdparty.a.c.a().e(dji.pilot2.library.a.ResetLibraryLayout);
    }

    public static void a(boolean z) {
        n = z;
    }

    public static boolean c() {
        return n;
    }

    public static void b(boolean z) {
        o = z;
    }

    public static boolean d() {
        return o;
    }

    public static void c(boolean z) {
        p = z;
    }

    public static boolean e() {
        return p;
    }
}
