package dji.phone.timelapse;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.media.TransportMediator;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.WheelHorizontalView;
import dji.device.common.view.DJIRoundLinearLayoutCompat;
import dji.device.common.view.set.view.DJISwitchCompat;
import dji.phone.a.g;
import dji.phone.e.a.e;
import dji.phone.timelapse.LpTlpPhotoView.b;
import dji.pilot.fpv.R;
import dji.pilot.phonecamera.a.a;
import dji.pilot.visual.a.d;
import dji.thirdparty.a.c;

public class DJILPTimelapseSetView extends DJIRoundLinearLayoutCompat implements OnClickListener, b {
    private static final String A = "DJILPTimelapseSetView";
    private static final float B = 30.0f;
    private boolean C;
    LayoutParams a;
    Button b;
    DJISwitchCompat c;
    FrameLayout d;
    LinearLayout e;
    LinearLayout f;
    RelativeLayout g;
    TextView h;
    TextView i;
    WheelHorizontalView j;
    WheelHorizontalView k;
    RelativeLayout l;
    TextView m;
    LinearLayout n;
    ScrollView o;
    TextView p;
    String[] q;
    String[] r;
    int[] s;
    int[] t;
    protected dji.device.widget.b<String> u = null;
    protected dji.device.widget.b<String> v = null;
    int w;
    a x = a.getInstance();
    int y;
    boolean z = false;

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] b = new int[a.values().length];

        static {
            c = new int[dji.phone.controview.b.a.values().length];
            try {
                c[dji.phone.controview.b.a.TAKEPHOTO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[dji.phone.controview.b.a.RECORD.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[a.f.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            a = new int[e.values().length];
            try {
                a[e.VIEW_TIMELAPSE_SETER.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public DJILPTimelapseSetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.x.a(this);
        c.a().a(this);
        this.y = 5;
        setRadius(getResources().getDimensionPixelSize(R.dimen.dp_4_in_sw320dp));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.a = (LayoutParams) getLayoutParams();
        this.h = (TextView) findViewById(R.id.longan_timelapse2_back_iv);
        this.i = (TextView) findViewById(R.id.longan_timelapse2_title_tv);
        this.b = (Button) findViewById(R.id.longan_timelapse2_bottom_btn);
        this.d = (FrameLayout) findViewById(R.id.longan_timelapse2_content_ly);
        this.e = (LinearLayout) findViewById(R.id.longan_timelapse2_static_ly);
        this.f = (LinearLayout) findViewById(R.id.longan_timelapse2_motion_ly);
        this.g = (RelativeLayout) findViewById(R.id.longan_timelapse2_tripod_ly);
        this.c = (DJISwitchCompat) findViewById(R.id.lp_timelapse2_tripod_switch);
        this.j = (WheelHorizontalView) findViewById(R.id.longan_timelapse2_interval_wheel);
        this.k = (WheelHorizontalView) findViewById(R.id.longan_timelapse2_duration_wheel);
        this.l = (RelativeLayout) findViewById(R.id.lp_timelapse_add_position_btn);
        this.n = (LinearLayout) findViewById(R.id.longan_timelapse2_motion_photo_ly);
        this.m = (TextView) findViewById(R.id.lp_timelapse_tips_tv);
        this.o = (ScrollView) findViewById(R.id.longan_timelapse2_motion_scroll_vertical);
        this.p = (TextView) findViewById(R.id.longan_new_timelapse2_gnr_tv);
        this.h.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ DJILPTimelapseSetView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.x.a(z ? 1 : 0);
            }
        });
        this.l.setOnClickListener(this);
        this.q = getResources().getStringArray(R.array.lp_timelapse_interval_name_array);
        this.r = getResources().getStringArray(R.array.lp_timelapse_duration_name_array);
        this.s = getResources().getIntArray(R.array.lp_timelapse_interval_value_array);
        this.t = getResources().getIntArray(R.array.lp_timelapse_duration_value_array);
        this.v = new dji.device.widget.b(getContext(), this.r);
        this.v.c(R.layout.lp_timelapse_wheel_item);
        this.v.d(R.id.lp_timelapse_wheel_text);
        this.v.h(getResources().getColor(R.color.blue_link));
        this.u = new dji.device.widget.b(getContext(), this.q);
        this.u.c(R.layout.lp_timelapse_wheel_item);
        this.u.d(R.id.lp_timelapse_wheel_text);
        this.u.h(getResources().getColor(R.color.blue_link));
        this.j.setViewAdapter(this.u);
        this.k.setViewAdapter(this.v);
        this.j.setCurrentItem(0);
        this.k.setCurrentItem(0);
        this.u.i(0);
        this.v.i(0);
        this.p.setText(generateTimeConvert(d.c, 1));
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        dji.f.a.b(this);
        this.x.a();
    }

    private void a() {
        this.j.addChangingListener(new antistatic.spinnerwheel.b(this) {
            final /* synthetic */ DJILPTimelapseSetView a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel, int i, int i2) {
                this.a.u.i(abstractWheel.getCurrentItem());
                try {
                    this.a.x.a(((float) this.a.s[i2]) / 10.0f);
                } catch (NumberFormatException e) {
                    this.a.x.a(1.0f);
                }
                this.a.g();
            }
        });
        this.k.addChangingListener(new antistatic.spinnerwheel.b(this) {
            final /* synthetic */ DJILPTimelapseSetView a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel, int i, int i2) {
                this.a.v.i(abstractWheel.getCurrentItem());
                try {
                    this.a.x.a((long) Integer.parseInt(this.a.r[i2]));
                } catch (NumberFormatException e) {
                    g.b(DJILPTimelapseSetView.A, "parse duration exception!");
                    this.a.x.a(1);
                }
                this.a.g();
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.longan_timelapse2_bottom_btn) {
            if (this.C) {
                this.x.a(this.x.c());
                return;
            }
            e();
        } else if (id == R.id.longan_timelapse2_back_iv) {
            if (!this.x.j() || this.f.isShown()) {
                h();
                return;
            }
            f();
            this.C = false;
        } else if (id != R.id.lp_timelapse_add_position_btn) {
        } else {
            if (this.x.i()) {
                c();
                if (this.w >= 2) {
                    a(this.o);
                    return;
                }
                return;
            }
            this.x.a(a.b.TLP_POSITION_NEAR);
        }
    }

    private void b() {
        if (this.x.f() < 2) {
            this.b.setEnabled(false);
        } else {
            this.b.setEnabled(true);
        }
    }

    private void c() {
        LpTlpPhotoView.a aVar;
        int childCount = this.n.getChildCount();
        if (childCount == 0) {
            aVar = LpTlpPhotoView.a.FIRST;
        } else if (childCount == this.y - 1) {
            aVar = LpTlpPhotoView.a.LAST;
            this.l.setVisibility(8);
            this.m.setVisibility(8);
        } else {
            aVar = LpTlpPhotoView.a.MIDDLE;
        }
        LpTlpPhotoView lpTlpPhotoView = new LpTlpPhotoView(getContext(), aVar, this.w);
        lpTlpPhotoView.setGravity(17);
        lpTlpPhotoView.setDeleteListener(this);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(14);
        try {
            lpTlpPhotoView.setPhoto(new BitmapDrawable(getResources(), dji.phone.preview.a.getInstance().a(dji.phone.c.a.c().w().width / 3, dji.phone.c.a.c().w().height / 3)));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
        a.b a = this.x.a(lpTlpPhotoView, this.w);
        if (a == a.b.TLP_OK) {
            this.n.addView(lpTlpPhotoView, layoutParams);
            d();
        } else if (a == a.b.TLP_NOTCONNECTED) {
            this.x.a(a);
            return;
        }
        this.w++;
    }

    private void a(final ScrollView scrollView) {
        scrollView.postDelayed(new Runnable(this) {
            final /* synthetic */ DJILPTimelapseSetView b;

            public void run() {
                scrollView.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
            }
        }, 300);
    }

    private void d() {
        int i = 1;
        int childCount = this.n.getChildCount();
        if (childCount != 0) {
            LpTlpPhotoView lpTlpPhotoView;
            if (childCount == 1) {
                lpTlpPhotoView = (LpTlpPhotoView) this.n.getChildAt(0);
                if (lpTlpPhotoView.getPosition() != LpTlpPhotoView.a.FIRST) {
                    lpTlpPhotoView.setPosition(LpTlpPhotoView.a.FIRST);
                    lpTlpPhotoView.setIndex(0);
                }
            } else {
                lpTlpPhotoView = (LpTlpPhotoView) this.n.getChildAt(0);
                if (lpTlpPhotoView.getPosition() != LpTlpPhotoView.a.FIRST) {
                    lpTlpPhotoView.setPosition(LpTlpPhotoView.a.FIRST);
                    lpTlpPhotoView.setIndex(0);
                }
                lpTlpPhotoView = (LpTlpPhotoView) this.n.getChildAt(childCount - 1);
                if (lpTlpPhotoView.getPosition() != LpTlpPhotoView.a.LAST) {
                    lpTlpPhotoView.setPosition(LpTlpPhotoView.a.LAST);
                }
                lpTlpPhotoView.setIndex(childCount - 1);
                while (i < childCount - 1) {
                    lpTlpPhotoView = (LpTlpPhotoView) this.n.getChildAt(i);
                    if (lpTlpPhotoView.getPosition() != LpTlpPhotoView.a.MIDDLE) {
                        lpTlpPhotoView.setPosition(LpTlpPhotoView.a.MIDDLE);
                    }
                    lpTlpPhotoView.setIndex(i);
                    i++;
                }
            }
            b();
        }
    }

    public void onEventMainThread(dji.phone.e.b bVar) {
        e eVar = bVar.a;
        dji.phone.e.a.c cVar = bVar.c;
        switch (eVar) {
            case VIEW_TIMELAPSE_SETER:
                if (cVar == dji.phone.e.a.c.f) {
                    animShow();
                    return;
                } else if (cVar == dji.phone.e.a.c.g) {
                    h();
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void onEventBackgroundThread(a aVar) {
        switch (AnonymousClass5.b[aVar.ordinal()]) {
            case 1:
                this.z = false;
                return;
            default:
                return;
        }
    }

    private void e() {
        this.e.setVisibility(0);
        this.f.setVisibility(4);
        this.i.setText(R.string.longan_timelapse2_title);
        this.b.setEnabled(true);
        this.b.setText(R.string.longan_timelapse2_start_txt);
        if (dji.phone.d.d.getInstance().i() == dji.phone.d.a.c.TIMELAPSE_STATIONARY) {
            this.g.setVisibility(0);
            this.h.setText(getResources().getString(R.string.app_close));
        } else if (dji.phone.d.d.getInstance().i() == dji.phone.d.a.c.TIMELAPSE_MOTION) {
            this.g.setVisibility(4);
            this.h.setText(getResources().getString(R.string.longan_timelapse_back));
            int[] a = this.x.a(this.t);
            this.v.a(a[0], a[1]);
        }
        this.C = true;
    }

    private void f() {
        this.e.setVisibility(4);
        this.i.setText(R.string.longan_timelapse2_create_position_txt);
        this.b.setText(R.string.longan_timelapse2_next_txt);
        this.f.setVisibility(0);
        this.h.setText(getResources().getString(R.string.app_close));
        b();
    }

    public void animShow() {
        if (this.x.j()) {
            f();
            this.C = false;
        } else {
            e();
            this.C = true;
        }
        super.animShow();
    }

    public void onDeleted(View view, int i) {
        this.n.removeView(view);
        this.x.c(i);
        this.w--;
        if (this.x.f() < this.y) {
            this.l.setVisibility(0);
            this.m.setVisibility(0);
        }
        d();
    }

    private void g() {
        this.p.setText(generateTimeConvert(this.x.d(), this.x.e() / 60));
    }

    public String generateTimeConvert(float f, int i) {
        if (i == 0) {
            return "-- : -- : --";
        }
        int i2 = (int) (((((float) i) * 60.0f) / f) / 30.0f);
        Log.d(A, "generateTimeConvert:duration:" + i + "inv:" + f);
        a aVar = this.x;
        return a.b((long) i2);
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        float a = dji.phone.k.c.a(bVar.b());
        dji.phone.h.a.a(this, getRotation(), a);
        if (this.a == null) {
            return;
        }
        if (a == 90.0f || a == 270.0f) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(this.a);
            layoutParams.rightMargin = (dji.phone.preview.a.e - this.a.width) / 2;
            setLayoutParams(layoutParams);
            return;
        }
        setLayoutParams(this.a);
    }

    public boolean isTripod() {
        return this.c.isChecked();
    }

    public void onTimelapseOver() {
        this.w = 0;
    }

    private void h() {
        if (isShown()) {
            super.hide();
            c.a().e(new dji.phone.e.b(dji.phone.e.a.a.d, dji.phone.e.a.c.i));
        }
    }

    public void hide() {
        if (isShown()) {
            setVisibility(4);
        }
    }

    public void clearGallery() {
        if (this.n.getChildCount() > 0) {
            this.n.removeAllViews();
            this.l.setVisibility(0);
        }
    }

    public void onEventMainThread(dji.phone.controview.b.a aVar) {
        switch (aVar) {
            case TAKEPHOTO:
                h();
                return;
            default:
                return;
        }
    }
}
