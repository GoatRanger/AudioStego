package dji.setting.ui.rc;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataRcGetCustomFuction;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcSetCustomFuction;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class C1C2View extends DividerLinearLayout {
    private static final String a = "RcStickCustomView";
    private DJICustomType[] b;
    private CameraType c = CameraType.OTHER;
    private DJISpinnerButton d;
    private DJISpinnerButton e;
    private ImageView f;
    private b g = new b(this) {
        final /* synthetic */ C1C2View a;

        {
            this.a = r1;
        }

        public void onItemClick(int i) {
            e.a("FPV_RCSettings_RCControlSettings_PullDownView_C1");
            int a = this.a.b[i].a();
            int c1 = DataRcGetCustomFuction.getInstance().getC1();
            int c2 = DataRcGetCustomFuction.getInstance().getC2();
            if (c1 != a) {
                Log.d("", "c1value=" + c1);
                Log.d("", "position=" + i + " value=" + a);
                DataRcSetCustomFuction.getInstance().a(a).b(c2).start(new d(this) {
                    final /* synthetic */ AnonymousClass3 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a.getCvalues();
                    }

                    public void onFailure(a aVar) {
                        this.a.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a.b();
                            }
                        });
                    }
                });
            }
        }
    };
    private b h = new b(this) {
        final /* synthetic */ C1C2View a;

        {
            this.a = r1;
        }

        public void onItemClick(int i) {
            e.a("FPV_RCSettings_RCControlSettings_PullDownView_C2");
            int a = this.a.b[i].a();
            int c1 = DataRcGetCustomFuction.getInstance().getC1();
            if (DataRcGetCustomFuction.getInstance().getC2() != a) {
                Log.d("", "c1value=" + c1);
                Log.d("", "position=" + i + " value=" + a);
                DataRcSetCustomFuction.getInstance().a(c1).b(a).start(new d(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a.getCvalues();
                    }

                    public void onFailure(a aVar) {
                        this.a.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.a.a.b();
                            }
                        });
                    }
                });
            }
        }
    };

    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] a = new int[DJICustomType.values().length];

        static {
            try {
                a[DJICustomType.a.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[DJICustomType.b.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[DJICustomType.c.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[DJICustomType.d.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[DJICustomType.e.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[DJICustomType.f.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[DJICustomType.g.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                a[DJICustomType.r.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                a[DJICustomType.s.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                a[DJICustomType.t.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                a[DJICustomType.h.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            try {
                a[DJICustomType.i.ordinal()] = 12;
            } catch (NoSuchFieldError e12) {
            }
            try {
                a[DJICustomType.j.ordinal()] = 13;
            } catch (NoSuchFieldError e13) {
            }
            try {
                a[DJICustomType.k.ordinal()] = 14;
            } catch (NoSuchFieldError e14) {
            }
            try {
                a[DJICustomType.l.ordinal()] = 15;
            } catch (NoSuchFieldError e15) {
            }
            try {
                a[DJICustomType.m.ordinal()] = 16;
            } catch (NoSuchFieldError e16) {
            }
            try {
                a[DJICustomType.n.ordinal()] = 17;
            } catch (NoSuchFieldError e17) {
            }
            try {
                a[DJICustomType.o.ordinal()] = 18;
            } catch (NoSuchFieldError e18) {
            }
            try {
                a[DJICustomType.p.ordinal()] = 19;
            } catch (NoSuchFieldError e19) {
            }
            try {
                a[DJICustomType.q.ordinal()] = 20;
            } catch (NoSuchFieldError e20) {
            }
            try {
                a[DJICustomType.u.ordinal()] = 21;
            } catch (NoSuchFieldError e21) {
            }
        }
    }

    public static int getStringByType(DJICustomType dJICustomType) {
        switch (AnonymousClass5.a[dJICustomType.ordinal()]) {
            case 1:
                return R.string.setting_ui_rc_custom_1;
            case 2:
                if (dji.pilot.publics.e.a.a()) {
                    return R.string.setting_ui_rc_custom_2_litchi;
                }
                return R.string.setting_ui_rc_custom_2;
            case 3:
                return R.string.setting_ui_rc_custom_4;
            case 4:
                return R.string.setting_ui_rc_custom_5;
            case 5:
                return R.string.setting_ui_rc_custom_6;
            case 6:
                return R.string.setting_ui_rc_custom_9;
            case 7:
                return R.string.setting_ui_rc_custom_10;
            case 8:
                return R.string.setting_ui_rc_custom_11;
            case 9:
                return R.string.setting_ui_rc_custom_11;
            case 10:
                return R.string.setting_ui_rc_custom_11;
            case 11:
                return R.string.setting_ui_rc_custom_12;
            case 12:
                return R.string.setting_ui_rc_custom_13;
            case 13:
                return R.string.setting_ui_rc_custom_14;
            case 14:
                return R.string.setting_ui_rc_custom_vision1;
            case 15:
                return R.string.setting_ui_rc_custom_vision2;
            case 16:
                return R.string.setting_ui_rc_custom_navigation;
            case 17:
                return R.string.setting_ui_rc_custom_playback;
            case 18:
                return R.string.setting_ui_rc_custom_gimbal_recenter;
            case 19:
                return R.string.setting_ui_rc_custom_liveview_expand;
            case 20:
                return R.string.setting_ui_rc_custom_quick_circle;
            case 21:
                return R.string.setting_ui_rc_custom_center_focus;
            default:
                return R.string.setting_ui_rc_custom_11;
        }
    }

    public C1C2View(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_rc_custom);
        if (!isInEditMode()) {
            this.d = (DJISpinnerButton) findViewById(R.id.setting_ui_rc_c1);
            this.e = (DJISpinnerButton) findViewById(R.id.setting_ui_rc_c2);
            this.f = (ImageView) findViewById(R.id.setting_ui_item_stick_img);
            DJILogHelper.getInstance().LOGD(a, "DataRcGetCustomFuction success", false, true);
        }
    }

    private void a() {
        DataRcGetMaster.getInstance().start(new d(this) {
            final /* synthetic */ C1C2View a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.getCvalues();
            }

            public void onFailure(a aVar) {
            }
        });
    }

    private void getCvalues() {
        DataRcGetCustomFuction.getInstance().start(new d(this) {
            final /* synthetic */ C1C2View a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b();
                    }
                });
                DJILogHelper.getInstance().LOGD(C1C2View.a, "DataRcGetCustomFuction success", false, true);
            }

            public void onFailure(a aVar) {
            }
        });
    }

    public void onEventMainThread(ProductType productType) {
        b();
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        a(dataCameraGetPushStateInfo.getCameraType(), false);
    }

    private void a(CameraType cameraType, boolean z) {
        if (this.c != cameraType) {
            this.c = cameraType;
            b();
        }
    }

    private void b() {
        int i = 0;
        ProductType c = i.getInstance().c();
        if (dji.pilot.publics.e.a.u(c)) {
            setVisibility(0);
            if (dji.pilot.publics.e.a.c(c)) {
                this.f.setImageResource(R.drawable.setting_rc_c1c2_kumquat);
            } else {
                this.f.setImageResource(R.drawable.setting_ui_rc_custom_c1c2);
            }
            if (DataRcGetMaster.getInstance().isGetted() && DataRcGetCustomFuction.getInstance().isGetted()) {
                if (DataRcGetMaster.getInstance().getMode() == MODE.a) {
                    this.b = dji.pilot.publics.e.a.a(Boolean.valueOf(true));
                } else {
                    this.b = dji.pilot.publics.e.a.a(Boolean.valueOf(false));
                }
                int c1 = DataRcGetCustomFuction.getInstance().getC1();
                int c2 = DataRcGetCustomFuction.getInstance().getC2();
                DJILogHelper.getInstance().LOGD(a, "c1value = " + c1 + "; c2value = " + c2, false, true);
                int[] iArr = new int[this.b.length];
                while (i < this.b.length) {
                    iArr[i] = getStringByType(this.b[i]);
                    i++;
                }
                this.d.setData(iArr, a(c1), this.g);
                this.e.setData(iArr, a(c2), this.h);
                return;
            }
            return;
        }
        setVisibility(8);
    }

    private int a(int i) {
        DJILogHelper.getInstance().LOGD("", "pos:" + i, false, true);
        if (i == 255 || i == -1) {
            return this.b.length - 1;
        }
        for (int i2 = 0; i2 < this.b.length; i2++) {
            if (this.b[i2].a() == i) {
                return i2;
            }
        }
        return 0;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
            if (instance.isGetted()) {
                this.c = instance.getCameraType();
            } else {
                this.c = CameraType.OTHER;
            }
            b();
            a();
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
