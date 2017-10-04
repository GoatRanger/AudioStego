package dji.phone.controview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import dji.device.common.a.a;
import dji.device.common.view.LonganBLN;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJILPCameraRecordBottomBar extends DJIRelativeLayout {
    private static final String i = DJILPCameraRecordBottomBar.class.getSimpleName();
    private static int[] j = new int[]{R.drawable.lp_record_volume0, R.drawable.lp_record_volume1, R.drawable.lp_record_volume2, R.drawable.lp_record_volume3, R.drawable.lp_record_volume4, R.drawable.lp_record_volume5, R.drawable.lp_record_volume6, R.drawable.lp_record_volume7};
    Animation a;
    DJITextView b;
    ImageView c;
    DJILinearLayout d;
    DJITextView e;
    DJILinearLayout f;
    LonganBLN g = null;
    int h = 8;

    public DJILPCameraRecordBottomBar(Context context) {
        super(context);
    }

    public DJILPCameraRecordBottomBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJILPCameraRecordBottomBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void a() {
        this.b = (DJITextView) findViewById(R.id.longan_bottombar_time_tv);
        this.a = AnimationUtils.loadAnimation(getContext(), R.anim.longan_fade_in);
        this.f = (DJILinearLayout) findViewById(R.id.longan_bottombar_record_info_ly);
        this.d = (DJILinearLayout) findViewById(R.id.lp_bottom_longexposure_ly);
        this.e = (DJITextView) findViewById(R.id.lp_bottom_longexposure_remain_time_tv);
        this.c = (ImageView) findViewById(R.id.longan_record_volume_iv);
        this.g = (LonganBLN) findViewById(R.id.longan_sd_bln);
    }

    private void b() {
        this.f.setVisibility(0);
        this.d.setVisibility(8);
        this.b.setVisibility(0);
    }

    public void updateTimeTv(long j, long j2) {
        String a = a.a((int) j2, getContext());
        String a2 = a.a((int) j, getContext());
        Log.d(i, "updateTime: remainTime = " + a + " totalTime = " + a2);
        this.b.setText(a2 + " | " + a);
    }

    public void updateAudioVolume(long j) {
        double log10;
        if (this.c.getVisibility() == 8) {
            this.c.setVisibility(0);
        }
        Log.d(i, "updateAudioVolume: radio = " + j);
        if (j > 1) {
            log10 = 20.0d * Math.log10((double) j);
        } else {
            log10 = 0.0d;
        }
        Log.d(i, "updateAudioVolume: db = " + log10);
        if (log10 == 0.0d) {
            this.c.setImageResource(j[0]);
            return;
        }
        int i = (int) ((log10 - 30.0d) / ((double) this.h));
        if (i <= 7) {
            this.c.setImageResource(j[i]);
        } else {
            this.c.setImageResource(j[7]);
        }
    }

    public void hideVoiceView() {
        this.c.setVisibility(8);
    }

    public void showTimeForLongEx(boolean z) {
        if (z) {
            this.f.setVisibility(8);
            this.d.setVisibility(0);
            this.e.setVisibility(0);
            setVisibility(0);
            return;
        }
        hide();
    }

    public void updateLongExTimeTv(long j, long j2, boolean z) {
        if (z) {
            String a = a.a((int) j2, getContext());
            String a2 = a.a((int) j, getContext());
            Log.d(i, "updateTime: remainTime = " + a + " totalTime = " + a2);
            this.e.setText(a2 + " | " + a);
            return;
        }
        CharSequence a3 = a.a((int) j, getContext());
        Log.d(i, "updateTime: totalTime = " + a3);
        this.e.setText(a3);
    }

    public void show(boolean z) {
        if (z) {
            b();
            this.g.startAnim();
            setVisibility(0);
            return;
        }
        this.g.stopAnim();
        setVisibility(4);
    }

    public void show() {
        b();
        this.g.startAnim();
        setVisibility(0);
    }

    public void hide() {
        this.g.stopAnim();
        setVisibility(4);
    }
}
