package dji.device.common.view;

import android.content.Context;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalRollFinetune;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJIGimbalRollFineTuneViewCompat extends DJILinearLayout {
    private DJITextView a = null;
    private DJIImageView b = null;
    private DJIImageView c = null;
    private DJIImageView d = null;
    private DJITextView e = null;
    private DJIImageView f = null;
    private int g = -32768;
    private OnClickListener h = null;
    private SoundPool i = null;
    private int j = 0;

    public DJIGimbalRollFineTuneViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
        a(DataGimbalGetPushParams.getInstance());
        c.a().a(this);
        resetView(getResources().getConfiguration());
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
        c();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetView(configuration);
    }

    public void resetView(Configuration configuration) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (configuration.orientation == 2) {
            layoutParams.bottomMargin = getResources().getDimensionPixelOffset(R.dimen.longan_gimbal_roll_adjust_margin_bottom_land);
        } else {
            layoutParams.bottomMargin = getResources().getDimensionPixelOffset(R.dimen.longan_gimbal_roll_adjust_margin_bottom_port);
        }
    }

    private void a() {
        this.h = new OnClickListener(this) {
            final /* synthetic */ DJIGimbalRollFineTuneViewCompat a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.dlg_titlebar_close_img == id) {
                    this.a.go();
                } else if (R.id.fpv_gimbal_roll_finetune_minus_img == id) {
                    DataGimbalRollFinetune.getInstance().setFineTuneValue((byte) -1).start(null);
                    this.a.a(this.a.j);
                } else if (R.id.fpv_gimbal_roll_finetune_plus_img == id) {
                    DataGimbalRollFinetune.getInstance().setFineTuneValue((byte) 1).start(null);
                    this.a.a(this.a.j);
                } else if (R.id.fpv_gimbal_roll_finetune_value_tv == id) {
                    this.a.a(this.a.j);
                    DataGimbalRollFinetune.getInstance().setFineTuneValue((byte) (-DataGimbalGetPushParams.getInstance().getRollAdjust())).start(null);
                }
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = (DJITextView) findViewById(R.id.dlg_titlebar_title_tv);
        this.b = (DJIImageView) findViewById(R.id.dlg_titlebar_close_img);
        this.c = (DJIImageView) findViewById(R.id.dlg_titlebar_back_img);
        this.d = (DJIImageView) findViewById(R.id.fpv_gimbal_roll_finetune_minus_img);
        this.e = (DJITextView) findViewById(R.id.fpv_gimbal_roll_finetune_value_tv);
        this.f = (DJIImageView) findViewById(R.id.fpv_gimbal_roll_finetune_plus_img);
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
        this.i = new SoundPool(2, 3, 0);
        this.j = this.i.load(getContext(), R.raw.camera_ev_center, 1);
    }

    private void c() {
        if (this.i != null) {
            this.i.unload(this.j);
            this.i.release();
            this.i = null;
        }
        this.j = 0;
    }

    private void a(int i) {
        float f = 0.3f;
        AudioManager audioManager = (AudioManager) getContext().getApplicationContext().getSystemService("audio");
        float streamVolume = ((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3));
        if (streamVolume >= 0.3f) {
            f = streamVolume;
        }
        this.i.play(i, f, f, 100, 0, 1.0f);
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
