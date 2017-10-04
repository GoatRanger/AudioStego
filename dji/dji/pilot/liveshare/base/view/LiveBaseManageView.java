package dji.pilot.liveshare.base.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.midware.natives.FPVController;
import dji.pilot.R;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.setting.a.a;
import dji.thirdparty.a.c;

public abstract class LiveBaseManageView extends DJIRelativeLayout implements OnClickListener, OnCheckedChangeListener {
    protected static final String TAG = LiveBaseManageView.class.getName();
    protected Activity mActivity;
    protected Switch mAudioSwitch;
    protected Button mOverButton;

    protected abstract void initWidgetType();

    protected abstract void stopStream();

    public LiveBaseManageView(Activity activity) {
        this(activity, null);
        this.mActivity = activity;
    }

    public LiveBaseManageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init();
    }

    private void init() {
        a.a((View) this, (int) R.layout.fpv_liveshare_base_over);
        if (!isInEditMode()) {
            this.mOverButton = (Button) findViewById(R.id.a5o);
            this.mOverButton.setOnClickListener(this);
            this.mAudioSwitch = (Switch) findViewById(R.id.a5n);
            this.mAudioSwitch.setOnCheckedChangeListener(this);
            this.mAudioSwitch.setChecked(!FPVController.native_getIsLiveStreamAudioMute());
            initWidgetType();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(TAG, "onAttachedToWindow");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d(TAG, "onDetachedFromWindow");
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (compoundButton != this.mAudioSwitch) {
            return;
        }
        if (z) {
            enableAudio();
        } else {
            disableAudio();
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a5o:
                stopStream();
                c.a().e(new dji.pilot.f.a.a(8));
                return;
            default:
                return;
        }
    }

    protected void enableAudio() {
        FPVController.native_setIsLiveStreamAudioMute(false);
    }

    protected void disableAudio() {
        FPVController.native_setIsLiveStreamAudioMute(true);
    }
}
