package dji.device.timelapse;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import antistatic.spinnerwheel.AbstractWheel;
import antistatic.spinnerwheel.WheelVerticalView;
import antistatic.spinnerwheel.d;
import dji.device.common.DJIUIEventManagerLongan.k;
import dji.device.widget.b;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class DJIMotionTimelapseDurButton extends RelativeLayout {
    int a;
    int b;
    int c;
    AlertDialog d;
    WheelVerticalView e;
    Button f;
    public int g = 0;
    int h = 0;
    final String[] i;
    final int[] j;
    int k = 0;
    b<String> l;
    LonganTimelapseImageView m;
    final DJIMotionTimelapseDurButton n;

    public DJIMotionTimelapseDurButton(Context context, int i) {
        super(context);
        this.g = i;
        this.i = getResources().getStringArray(R.array.longan_timelapse_duration_name_array);
        this.i[0] = "";
        this.j = getResources().getIntArray(R.array.longan_timelapse_duration_value_array);
        a();
        this.n = this;
        c.a().e(k.UPDATE_GENERATE_TIME.a(null));
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public int getDuraton() {
        return this.g;
    }

    private void a() {
        this.b = getResources().getDimensionPixelOffset(R.dimen.longan_timelapse_motion_dur_btn_height);
        this.c = getResources().getDimensionPixelOffset(R.dimen.longan_timelapse_motion_dur_wheel_height);
        View inflate = View.inflate(getContext(), R.layout.longan_duration_seletor_dlg, this);
        this.f = (Button) findViewById(R.id.durtation_selector_btn);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIMotionTimelapseDurButton a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.e.setVisibility(0);
                this.a.f.setVisibility(8);
                this.a.n.setTranslationY(this.a.n.getTranslationY() - ((float) ((this.a.c - this.a.b) / 2)));
            }
        });
        this.e = (WheelVerticalView) inflate.findViewById(R.id.durtation_selector_wheel);
        int wheelPos = LonganTimelapseSetLayout.getWheelPos(this.g, this.j);
        this.l = new b(getContext(), this.i);
        this.l.c(R.layout.longan_motion_duration_wheel_item);
        this.l.d(R.id.longan_motion_duration_wheel_text);
        this.l.i(wheelPos);
        this.l.h(getResources().getColor(R.color.duration_selector_yellow));
        this.e.setViewAdapter(this.l);
        this.e.setCurrentItem(wheelPos);
        this.e.setSelectorPaintCoeff(0.05f);
        this.h = wheelPos;
        this.f.setText(this.i[this.h]);
        this.e.addChangingListener(new antistatic.spinnerwheel.b(this) {
            final /* synthetic */ DJIMotionTimelapseDurButton a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel, int i, int i2) {
                this.a.l.i(abstractWheel.getCurrentItem());
            }
        });
        this.e.addScrollingListener(new d(this) {
            final /* synthetic */ DJIMotionTimelapseDurButton a;

            {
                this.a = r1;
            }

            public void a(AbstractWheel abstractWheel) {
            }

            public void b(AbstractWheel abstractWheel) {
                this.a.h = abstractWheel.getCurrentItem();
                if (this.a.h == 0) {
                    this.a.h = 1;
                }
                this.a.e.setCurrentItem(this.a.h);
                this.a.l.i(this.a.h);
                this.a.g = this.a.j[this.a.h];
                this.a.e.setVisibility(8);
                this.a.f.setVisibility(0);
                this.a.f.setText(this.a.i[this.a.h]);
                this.a.n.setTranslationY(this.a.n.getTranslationY() + ((float) ((this.a.c - this.a.b) / 2)));
                c.a().e(k.DURATION_CHANGED.b(this.a.k));
                c.a().e(k.UPDATE_GENERATE_TIME.a(null));
            }
        });
    }

    public void setNumber(int i) {
        this.k = i;
    }

    public int getDuration() {
        return this.g;
    }
}
