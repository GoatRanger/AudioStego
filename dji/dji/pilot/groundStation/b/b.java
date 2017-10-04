package dji.pilot.groundStation.b;

import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.pilot.R;
import dji.pilot.fpv.model.n.a;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.d;
import dji.pilot.fpv.view.DJIStageView.e;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;

public class b extends c implements dji.pilot.fpv.view.DJIStageView.b, d {
    public static final int A = 25;
    public static final int B = 26;
    public static final int C = 27;
    public static final int D = 28;
    public static final int E = 29;
    public static final int F = 30;
    public static final int G = 31;
    public static final int H = 32;
    public static final int I = 100;
    public static final int J = 101;
    public static final int K = 102;
    public static final int L = 103;
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    public static final int h = 6;
    public static final int i = 7;
    public static final int j = 8;
    public static final int k = 9;
    public static final int l = 10;
    public static final int m = 11;
    public static final int n = 12;
    public static final int o = 13;
    public static final int p = 14;
    public static final int q = 15;
    public static final int r = 16;
    public static final int s = 17;
    public static final int t = 18;
    public static final int u = 19;
    public static final int v = 20;
    public static final int w = 21;
    public static final int x = 22;
    public static final int y = 23;
    public static final int z = 24;
    private LinearLayout Q = null;
    private e R = null;
    private int S = 0;
    private boolean T = false;
    protected DJIStageView a = null;

    public b(Context context) {
        super(context);
        setContentView(R.layout.gs_dialog);
        this.Q = (LinearLayout) findViewById(R.id.aj6);
        this.a = (DJIStageView) findViewById(R.id.aj7);
        this.R = new e(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3) {
                this.a.S = i2;
                this.a.a(i2);
                dji.thirdparty.a.c.a().e(DJICustomType.r);
            }

            public void a(int i) {
                this.a.dismiss();
            }
        };
        this.a.setOnStageChangeListener(this.R);
        this.a.setOnOptListener(this);
    }

    protected void onCreate(Bundle bundle) {
        a(this.S);
    }

    public void onEventMainThread(a aVar) {
        if (aVar == a.b) {
            if (this.S == 100) {
                this.T = true;
            }
        } else if (aVar == a.c) {
            this.T = false;
        }
    }

    protected boolean a() {
        if (this.S == 100 || this.S == 103) {
            if (!this.T) {
                dismiss();
            }
            return true;
        }
        if (this.S == 0) {
            dismiss();
        }
        return super.a();
    }

    public int c() {
        return this.S;
    }

    private void a(int i) {
        LayoutParams attributes = getWindow().getAttributes();
        if (i == 0 || i == 100) {
            n();
            attributes.windowAnimations = R.style.e4;
        } else {
            o();
            attributes.windowAnimations = R.style.e5;
        }
        if (i == 0 || i == 100) {
            this.Q.setBackgroundColor(this.N.getResources().getColor(R.color.e9));
            attributes.width = -1;
            attributes.height = dji.pilot.fpv.model.b.a(this.N, R.dimen.fi);
            attributes.y = 0;
            attributes.x = 0;
            attributes.gravity = 80;
        } else if (i == 7 || i == 18) {
            this.Q.setBackgroundColor(this.N.getResources().getColor(R.color.e9));
            attributes.width = dji.pilot.fpv.model.b.a(this.N, R.dimen.o4) + dji.pilot.fpv.model.b.a(this.N, R.dimen.og);
            attributes.height = dji.pilot.fpv.model.b.a(this.N, R.dimen.o3);
            attributes.y = 0;
            attributes.x = (DJIBaseActivity.screenWidth - attributes.width) / 2;
            attributes.gravity = 17;
        } else if (i == 23) {
            this.Q.setBackground(this.N.getResources().getDrawable(R.drawable.gs_left_radius_corner_bg));
            attributes.width = (int) (((double) DJIBaseActivity.screenWidth) * 0.36d);
            attributes.height = DJIBaseActivity.screenHeight;
            attributes.y = 0;
            attributes.x = (DJIBaseActivity.screenWidth - attributes.width) / 2;
            attributes.gravity = 17;
        } else if (i == 24 || i == 25 || i == 26) {
            this.Q.setBackground(this.N.getResources().getDrawable(R.drawable.gs_radius_corner_bg));
            attributes.width = dji.pilot.fpv.model.b.a(this.N, R.dimen.o6);
            attributes.height = dji.pilot.fpv.model.b.a(this.N, R.dimen.o5);
            attributes.y = 0;
            attributes.x = 0;
            attributes.gravity = 17;
        } else if (i == 28 || i == 27) {
            this.Q.setBackground(this.N.getResources().getDrawable(R.drawable.gs_radius_corner_bg));
            attributes.width = dji.pilot.fpv.model.b.a(this.N, R.dimen.o4);
            attributes.height = dji.pilot.fpv.model.b.a(this.N, R.dimen.o3);
            attributes.y = 0;
            attributes.x = 0;
            attributes.gravity = 17;
        } else if (i == 102) {
            attributes.width = -1;
            attributes.height = -1;
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
                attributes.height = dji.pilot.fpv.model.b.a(this.N, R.dimen.g7);
            }
            attributes.y = 0;
            attributes.x = 0;
            attributes.gravity = 80;
        } else if (i == 103) {
            attributes.width = dji.pilot.fpv.model.b.a(this.N, R.dimen.fw);
            attributes.height = -1;
            attributes.y = 0;
            attributes.x = dji.pilot.fpv.model.b.a(this.N, R.dimen.gx);
            attributes.gravity = 3;
            this.T = false;
            o();
        } else if (101 == i) {
            this.Q.setBackground(this.N.getResources().getDrawable(R.drawable.gs_left_radius_corner_bg));
            attributes.width = (int) (((float) DJIBaseActivity.screenWidth) * 0.2f);
            attributes.height = DJIBaseActivity.screenHeight;
            attributes.y = 0;
            attributes.x = 0;
            attributes.gravity = 5;
        } else {
            this.Q.setBackground(this.N.getResources().getDrawable(R.drawable.gs_left_radius_corner_bg));
            attributes.width = (int) (((double) DJIBaseActivity.screenWidth) * 0.36d);
            attributes.height = DJIBaseActivity.screenHeight;
            attributes.y = 0;
            attributes.x = (DJIBaseActivity.screenWidth - dji.pilot.fpv.model.b.a(this.N, R.dimen.o4)) / 2;
            attributes.gravity = 17;
        }
        attributes.dimAmount = 0.0f;
        attributes.flags &= -3;
        getWindow().setAttributes(attributes);
    }

    public void a(int i, int i2) {
        this.a.createStageView(i, i2, false);
        show();
        a(i2);
    }

    public void show() {
        try {
            super.show();
            dji.thirdparty.a.c.a().e(DJICustomType.r);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onStart() {
        super.onStart();
        this.a.dispatchOnStart(false);
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
    }

    protected void onStop() {
        this.T = false;
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        this.a.dispatchOnStop(false);
        super.onStop();
    }

    public boolean b() {
        h();
        return true;
    }

    protected void h() {
        dismiss();
    }

    public void d() {
    }

    public void e() {
    }

    public void f() {
    }

    public void g() {
    }
}
