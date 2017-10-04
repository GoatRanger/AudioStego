package dji.pilot.fpv.activity;

import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import dji.midware.util.a.a;
import dji.midware.util.a.b.b;

class DJIBaseNewPreviewActivity$1 implements a {
    final /* synthetic */ DJIBaseNewPreviewActivity a;

    DJIBaseNewPreviewActivity$1(DJIBaseNewPreviewActivity dJIBaseNewPreviewActivity) {
        this.a = dJIBaseNewPreviewActivity;
    }

    public void a(int i, int i2, int i3, int i4) {
        System.out.println("AutoVideoSizeCalculator onVideoSizeChanged");
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(i, i2);
        layoutParams.addRule(13, -1);
        this.a.c.setLayoutParams(layoutParams);
        this.a.y = this.a.O.g();
        this.a.z = this.a.O.h();
        b b = this.a.O.a().b();
        this.a.a(this.a.y, this.a.z);
        this.a.a(this.a.y, this.a.z, b, false);
        this.a.w();
        this.a.d.setLayoutParams(layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(i3, i4);
        layoutParams.addRule(13, -1);
        this.a.f.setLayoutParams(layoutParams);
    }
}
