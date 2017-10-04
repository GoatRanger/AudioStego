package dji.pilot.fpv.view;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.e;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJIGimbalRollFineTuneView extends DJILinearLayout {
    private DJITextView a = null;
    private DJIImageView b = null;
    private DJIImageView c = null;
    private DJIImageView d = null;
    private DJITextView e = null;
    private DJIImageView f = null;
    private int g = -32768;
    private OnClickListener h = null;
    private final DJIGenSettingDataManager i = DJIGenSettingDataManager.getInstance();
    private SoundPool j = null;
    private int k = 0;

    public DJIGimbalRollFineTuneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        this.h = new OnClickListener(this) {
            final /* synthetic */ DJIGimbalRollFineTuneView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.sl == id) {
                    e.a("FPV_GimbalRollAdjustView_Button_Close");
                    DJIGenSettingDataManager.getInstance().d(false);
                    this.a.go();
                } else if (R.id.a1o == id) {
                    e.a("FPV_GimbalRollAdjustView_Button_Decrease");
                    this.a.a(this.a.k);
                    this.a.i.a((byte) -1);
                } else if (R.id.a1q == id) {
                    e.a("FPV_GimbalRollAdjustView_Button_Increase");
                    this.a.a(this.a.k);
                    this.a.i.a((byte) 1);
                } else if (R.id.a1p == id) {
                    this.a.a(this.a.k);
                    this.a.i.a((byte) 0);
                }
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (DJITextView) findViewById(R.id.sj);
        this.b = (DJIImageView) findViewById(R.id.sl);
        this.c = (DJIImageView) findViewById(R.id.sg);
        this.d = (DJIImageView) findViewById(R.id.a1o);
        this.e = (DJITextView) findViewById(R.id.a1p);
        this.f = (DJIImageView) findViewById(R.id.a1q);
        this.c.go();
        this.a.setText(R.string.fpv_gimbal_roll);
        this.b.setOnClickListener(this.h);
        this.d.setOnClickListener(this.h);
        this.f.setOnClickListener(this.h);
        this.e.setOnClickListener(this.h);
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        a(dataGimbalGetPushParams);
    }

    private void b() {
        this.j = new SoundPool(2, 3, 0);
        this.k = this.j.load(getContext(), R.raw.camera_ev_center, 1);
    }

    private void c() {
        if (this.j != null) {
            this.j.unload(this.k);
            this.j.release();
            this.j = null;
        }
        this.k = 0;
    }

    private void a(int i) {
        float f = 0.3f;
        AudioManager audioManager = (AudioManager) getContext().getApplicationContext().getSystemService("audio");
        float streamVolume = ((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3));
        if (streamVolume >= 0.3f) {
            f = streamVolume;
        }
        this.j.play(i, f, f, 100, 0, 1.0f);
    }

    private void a(DataGimbalGetPushParams dataGimbalGetPushParams) {
        byte rollAdjust = dataGimbalGetPushParams.getRollAdjust();
        if (this.g != rollAdjust) {
            this.g = rollAdjust;
            float rollAdjust2 = ((float) dataGimbalGetPushParams.getRollAdjust()) * 0.1f;
            this.e.setText(String.format("%.1f", new Object[]{Float.valueOf(rollAdjust2)}));
            if (rollAdjust == (byte) 0) {
                this.e.setEnabled(false);
            } else {
                this.e.setEnabled(true);
            }
        }
    }

    public void show() {
        b();
        super.show();
        a(DataGimbalGetPushParams.getInstance());
        c.a().a(this);
    }

    public void hide() {
        c.a().d(this);
        c();
        super.hide();
    }

    public void go() {
        c.a().d(this);
        c();
        super.go();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }
}
