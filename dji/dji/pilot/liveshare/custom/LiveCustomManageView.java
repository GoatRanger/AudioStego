package dji.pilot.liveshare.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.midware.natives.FPVController;
import dji.pilot.R;
import dji.pilot.publics.e.b;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.setting.a.a;
import dji.thirdparty.a.c;

public class LiveCustomManageView extends DJIRelativeLayout implements OnClickListener, OnCheckedChangeListener {
    protected Activity mActivity;
    protected DJITextView mAudioByteRateTv;
    protected Switch mAudioSwitch;
    protected DJITextView mCacheQueueTv;
    protected Button mFinish;
    protected DJITextView mFpsTv;
    protected DJITextView mNetworkTv;
    protected DJITextView mVideoByteRateTv;
    private boolean update;

    public LiveCustomManageView(Activity activity) {
        this(activity, null);
        this.mActivity = activity;
    }

    public LiveCustomManageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.update = true;
        init();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.update = false;
    }

    protected void init() {
        a.a((View) this, (int) R.layout.fpv_liveshare_custom_manage);
        if (!isInEditMode()) {
            this.mFpsTv = (DJITextView) findViewById(R.id.a6t);
            this.mVideoByteRateTv = (DJITextView) findViewById(R.id.a6u);
            this.mAudioByteRateTv = (DJITextView) findViewById(R.id.a6v);
            this.mCacheQueueTv = (DJITextView) findViewById(R.id.a6w);
            this.mNetworkTv = (DJITextView) findViewById(R.id.a6x);
            this.mFinish = (Button) findViewById(R.id.a6o);
            this.mFinish.setOnClickListener(this);
            this.mAudioSwitch = (Switch) findViewById(R.id.a6r);
            this.mAudioSwitch.setOnCheckedChangeListener(this);
            this.mAudioSwitch.setChecked(!FPVController.native_getIsLiveStreamAudioMute());
            updateStatus();
            new Thread(new Runnable() {
                public void run() {
                    while (LiveCustomManageView.this.update) {
                        try {
                            Thread.sleep(1000);
                            LiveCustomManageView.this.mActivity.runOnUiThread(new Runnable() {
                                public void run() {
                                    LiveCustomManageView.this.updateStatus();
                                }
                            });
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            return;
                        }
                    }
                }
            }).start();
        }
    }

    protected void updateStatus() {
        this.mFpsTv.setText(String.format("%.1f", new Object[]{Float.valueOf(FPVController.native_getLiveStreamVideoFps())}));
        this.mVideoByteRateTv.setText("" + FPVController.native_getLiveStreamVideoBitRate());
        this.mAudioByteRateTv.setText("" + FPVController.native_getLiveStreamAudioBitRate());
        this.mCacheQueueTv.setText("" + FPVController.native_getLiveStreamVideoBufSize());
        updateNetwork();
    }

    protected void updateNetwork() {
        switch (b.a(getContext())) {
            case 0:
                this.mNetworkTv.setText(R.string.liveshare_custom_network_none);
                return;
            case 1:
                this.mNetworkTv.setText(R.string.liveshare_custom_network_wifi);
                return;
            case 2:
                this.mNetworkTv.setText(R.string.liveshare_custom_network_2g);
                return;
            case 3:
                this.mNetworkTv.setText(R.string.liveshare_custom_network_3g);
                return;
            case 4:
                this.mNetworkTv.setText(R.string.liveshare_custom_network_4g);
                return;
            case 5:
                this.mNetworkTv.setText(R.string.liveshare_custom_network_mobile);
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

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.a6o:
                dji.pilot.liveshare.b.getInstance().stopStream();
                c.a().e(new dji.pilot.f.a.a(8));
                return;
            default:
                return;
        }
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
}
