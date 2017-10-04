package dji.setting.ui;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.dji.frame.c.c;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.a;

public class b extends Dialog {
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final int i = 4;
    public static final int j = 5;
    public static final int k = 6;
    public static final int l = 7;
    public static final int m = 8;
    public static final int n = 9;
    public static final int o = 10;
    public static final int p = 11;
    private static final int q = 12;
    public int a;
    public int b;
    public float c;
    public boolean d = true;
    private SettingUIRootView r;
    private Handler s = new Handler(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (this.a.getWindow().getDecorView().getSystemUiVisibility() != a.a) {
                this.a.getWindow().getDecorView().setSystemUiVisibility(a.a);
            }
            if (this.a.isShowing()) {
                this.a.s.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

    public b(Context context) {
        super(context, R.style.setting_ui_dialog);
        setContentView(R.layout.setting_dialog);
        this.r = (SettingUIRootView) findViewById(R.id.content_view);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f();
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (this.a * 75) / 100;
        attributes.height = -1;
        attributes.gravity = 5;
        attributes.dimAmount = 0.0f;
        getWindow().setAttributes(attributes);
    }

    public void show() {
        if (this.d) {
            getWindow().setFlags(8, 8);
            c.a(getWindow());
            a.a = getWindow().getDecorView().getSystemUiVisibility();
        }
        getWindow().setSoftInputMode(2);
        super.show();
        if (this.d) {
            getWindow().clearFlags(8);
            this.s.sendEmptyMessage(0);
        }
    }

    protected void onStart() {
        super.onStart();
        dji.thirdparty.a.c.a().a(this);
    }

    protected void onStop() {
        dji.thirdparty.a.c.a().d(this);
        super.onStop();
    }

    public void onEventMainThread(SettingUIRootView.a aVar) {
        if (aVar == SettingUIRootView.a.CloseBtnClick) {
            dismiss();
        }
    }

    public void a() {
        this.r.gotoGoHomeItem();
    }

    public void b() {
        this.r.gotoFlycSensor();
    }

    public void c() {
        this.r.gotoFlycCompass();
    }

    public void d() {
        this.r.gotoRcMode();
    }

    public void e() {
        a(0);
    }

    public void a(int i) {
        int i2;
        if (!isShowing()) {
            show();
        }
        if (i == 0) {
            i2 = R.id.setting_ui_root_btn_flyc;
        } else if (i == 1) {
            i2 = R.id.setting_ui_root_btn_rc;
        } else if (i == 2) {
            i2 = R.id.setting_ui_root_btn_hd;
        } else if (i == 3) {
            if (dji.pilot.publics.e.a.d(null)) {
                i2 = R.id.setting_ui_root_btn_battery_m600;
            } else if (ProductType.A3 == i.getInstance().c() || ProductType.N3 == i.getInstance().c()) {
                i2 = R.id.setting_ui_root_btn_battery_non_smart;
            } else {
                i2 = R.id.setting_ui_root_btn_battery;
            }
        } else if (i == 4) {
            if (DataGimbalGetPushType.getInstance().getType() == DJIGimbalType.Ronin) {
                i2 = R.id.setting_ui_root_btn_gimbal_ronin;
            } else {
                i2 = R.id.setting_ui_root_btn_gimbal;
            }
        } else if (i == 5) {
            i2 = R.id.setting_ui_root_btn_wifi;
        } else if (i == 6) {
            i2 = R.id.setting_ui_root_btn_general;
        } else if (i == 7) {
            i2 = R.id.setting_ui_root_btn_vision;
        } else if (i == 8) {
            i2 = R.id.setting_ui_root_btn_guidance;
        } else {
            i2 = R.id.setting_ui_root_btn_flyc;
        }
        if (i2 != this.r.getCurTabId()) {
            this.r.gotoTab(i2);
        }
    }

    @SuppressLint({"NewApi"})
    private void f() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        if (this.a == 0) {
            if (VERSION.SDK_INT < 17) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                this.a = displayMetrics.widthPixels;
                this.b = displayMetrics.heightPixels;
                if (this.a < this.b) {
                    int i = this.a;
                    this.a = this.b;
                    this.b = i;
                }
            } else {
                Display defaultDisplay = windowManager.getDefaultDisplay();
                Point point = new Point();
                defaultDisplay.getRealSize(point);
                this.a = point.x > point.y ? point.x : point.y;
                this.b = point.x > point.y ? point.y : point.x;
            }
            this.c = (((float) this.a) * 1.0f) / ((float) this.b);
        }
    }
}
