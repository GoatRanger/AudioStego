package dji.pilot.liveshare;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import dji.midware.natives.FPVController;
import dji.pilot.R;
import dji.pilot.f.a.a;
import dji.pilot.fpv.d.b;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class LiveShareFpvTopView extends DJILinearLayout {
    private DJITextView mFPSTv;
    private DJIImageView mMicIv;
    private DJITextView mStatusTv;
    private DJIImageView mStreamingIv;
    private Handler mTimeHandler;
    private Runnable mTimeRunnable;
    private DJITextView mTimeText;
    private DJIImageView mVolumeIv;

    public LiveShareFpvTopView(Context context) {
        this(context, null);
    }

    public LiveShareFpvTopView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LiveShareFpvTopView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTimeHandler = null;
        this.mTimeRunnable = new Runnable() {
            long currentTime;

            public void run() {
                this.currentTime = b.getInstance().getStreamTime();
                LiveShareFpvTopView.this.mTimeText.setText("" + LiveShareFpvTopView.this.formatVideoTime(Math.round(((float) this.currentTime) * 1.0f)));
                LiveShareFpvTopView.this.mFPSTv.setText(String.format("%.1f", new Object[]{Float.valueOf(FPVController.native_getLiveStreamVideoFps())}));
                if (LiveShareFpvTopView.this.mTimeHandler != null) {
                    LiveShareFpvTopView.this.mTimeHandler.postDelayed(LiveShareFpvTopView.this.mTimeRunnable, 200);
                }
            }
        };
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private String formatVideoTime(int i) {
        int[] e = b.e(i);
        return String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(e[1]), Integer.valueOf(e[0])});
    }

    public void dispatchOnCreate() {
        handleEvent();
    }

    public void dispatchOnDestroy() {
        this.mTimeHandler = null;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        initWidgets();
    }

    private void initWidgets() {
        this.mTimeText = (DJITextView) findViewById(R.id.aze);
        this.mStatusTv = (DJITextView) findViewById(R.id.azd);
        this.mStreamingIv = (DJIImageView) findViewById(R.id.azc);
        this.mVolumeIv = (DJIImageView) findViewById(R.id.azi);
        this.mStreamingIv.setBackgroundResource(R.drawable.liveshare_streaming_lost);
        this.mFPSTv = (DJITextView) findViewById(R.id.azg);
        this.mMicIv = (DJIImageView) findViewById(R.id.azh);
        AnimationDrawable animationDrawable = (AnimationDrawable) this.mStreamingIv.getBackground();
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    public void handleEvent() {
        AnimationDrawable animationDrawable;
        if (b.getInstance().isRunning()) {
            if (this.mTimeHandler == null) {
                this.mTimeHandler = new Handler();
            }
            this.mTimeHandler.post(this.mTimeRunnable);
            this.mStatusTv.setText(R.string.fpv_gensetting_liveshare);
            this.mStreamingIv.setBackgroundResource(R.drawable.liveshare_streaming_custom);
            animationDrawable = (AnimationDrawable) this.mStreamingIv.getBackground();
            if (!animationDrawable.isRunning()) {
                animationDrawable.start();
                return;
            }
            return;
        }
        this.mTimeHandler = null;
        this.mStatusTv.setText(R.string.liveshare_live_topbar_connect);
        this.mStreamingIv.setBackgroundResource(R.drawable.liveshare_streaming_lost);
        animationDrawable = (AnimationDrawable) this.mStreamingIv.getBackground();
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    public void onEventMainThread(a aVar) {
        switch (aVar.I) {
            case 6:
                handleEvent();
                return;
            case 8:
                this.mStatusTv.setText(R.string.liveshare_live_topbar_connect);
                this.mStreamingIv.setBackgroundResource(R.drawable.liveshare_streaming_lost);
                AnimationDrawable animationDrawable = (AnimationDrawable) this.mStreamingIv.getBackground();
                if (!animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
                this.mVolumeIv.setImageLevel(0);
                return;
            case 258:
                if (FPVController.native_getIsLiveStreamAudioMute()) {
                    this.mVolumeIv.setImageLevel(0);
                    this.mMicIv.setImageResource(R.drawable.longan_record_recorder_no_voice);
                    return;
                }
                this.mMicIv.setImageResource(R.drawable.longan_record_recorder);
                if (aVar.K < 15.0d) {
                    this.mVolumeIv.setImageLevel(0);
                    return;
                } else if (aVar.K >= 15.0d && aVar.K < 30.0d) {
                    this.mVolumeIv.setImageLevel(1);
                    return;
                } else if (aVar.K >= 30.0d && aVar.K < 45.0d) {
                    this.mVolumeIv.setImageLevel(2);
                    return;
                } else if (aVar.K >= 45.0d && aVar.K < 60.0d) {
                    this.mVolumeIv.setImageLevel(3);
                    return;
                } else if (aVar.K >= 60.0d && aVar.K < 75.0d) {
                    this.mVolumeIv.setImageLevel(4);
                    return;
                } else if (aVar.K >= 75.0d) {
                    this.mVolumeIv.setImageLevel(5);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }
}
