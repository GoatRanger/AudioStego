package dji.phone.live;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import dji.apppublic.reflect.AppPublicReflect;
import dji.log.DJILogHelper;
import dji.midware.natives.FPVController;
import dji.pilot.f.a.a;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJILPLiveShareFpvView extends DJILinearLayout implements OnClickListener {
    private String a;
    private DJILPLivePresenter b;
    private DJITextView c;
    private DJITextView d;
    private DJIImageView e;
    private DJIImageView f;
    private DJITextView g;
    private DJIImageView h;
    private Context i;
    private Handler j;
    private Runnable k;

    public DJILPLiveShareFpvView(Context context) {
        this(context, null);
    }

    public DJILPLiveShareFpvView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public DJILPLiveShareFpvView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = "DJILPLiveShareFpvView";
        this.j = null;
        this.k = new Runnable(this) {
            long a;
            final /* synthetic */ DJILPLiveShareFpvView b;

            {
                this.b = r1;
            }

            public void run() {
                this.a = AppPublicReflect.getStreamTime();
                DJILogHelper.getInstance().LOGD(this.b.a, "time " + this.a, false, true);
                this.b.c.setText("" + this.b.a(Math.round(((float) this.a) * 1.0f)));
                this.b.g.setText(String.format("%.1f", new Object[]{Float.valueOf(FPVController.native_getLiveStreamVideoFps())}));
                if (this.b.j != null) {
                    Log.d(this.b.a, "run: postDelayed mTimeRunnable");
                    this.b.j.postDelayed(this.b.k, 800);
                }
            }
        };
        this.i = context;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d(this.a, "onAttachedToWindow: ");
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(this.a, "onDetachedFromWindow: ");
        if (c.a().c(this)) {
            c.a().d(this);
        }
        dispatchOnDestroy();
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.e(this.a, "onFinishInflate: ");
        dispatchOnCreate();
    }

    private String a(int i) {
        int[] formatSecondToMinuteAr = formatSecondToMinuteAr(i);
        return String.format("%1$02d:%2$02d", new Object[]{Integer.valueOf(formatSecondToMinuteAr[1]), Integer.valueOf(formatSecondToMinuteAr[0])});
    }

    public void onEventMainThread(a aVar) {
        Log.d(this.a, "onEventMainThread: " + aVar.I);
        switch (aVar.I) {
            case 6:
                handleEvent();
                return;
            case 8:
                this.d.setText(R.string.lp_live_connect);
                this.e.setBackgroundResource(R.drawable.lp_liveshare_streaming_lost);
                AnimationDrawable animationDrawable = (AnimationDrawable) this.e.getBackground();
                if (!animationDrawable.isRunning()) {
                    animationDrawable.start();
                }
                this.f.setImageLevel(0);
                Log.d(this.a, "onEventMainThread: EVENT_OVER ");
                if (this.j != null) {
                    Log.d(this.a, "onEventMainThread: removeCallbacks mTimeRunnable");
                    this.j.removeCallbacksAndMessages(null);
                    this.j = null;
                    return;
                }
                return;
            case 16:
            case a.y /*518*/:
                Log.d(this.a, "onEventMainThread: EVENT_YOUTUBE_VIDEO_LIVE_STOP or EVENT_FINISH");
                if (this.j != null) {
                    this.j.removeCallbacksAndMessages(null);
                    this.j = null;
                    return;
                }
                return;
            case 258:
                Log.d(this.a, "onEventMainThread: EVENT_AUDIO_REFRESH volume = " + aVar.K);
                if (FPVController.native_getIsLiveStreamAudioMute()) {
                    this.f.setImageLevel(0);
                    this.h.setImageResource(R.drawable.longan_record_recorder_no_voice);
                    return;
                }
                this.h.setImageResource(R.drawable.longan_record_recorder);
                if (aVar.K < 15.0d) {
                    this.f.setImageLevel(0);
                    return;
                } else if (aVar.K >= 15.0d && aVar.K < 30.0d) {
                    this.f.setImageLevel(1);
                    return;
                } else if (aVar.K >= 30.0d && aVar.K < 45.0d) {
                    this.f.setImageLevel(2);
                    return;
                } else if (aVar.K >= 45.0d && aVar.K < 60.0d) {
                    this.f.setImageLevel(3);
                    return;
                } else if (aVar.K >= 60.0d && aVar.K < 75.0d) {
                    this.f.setImageLevel(4);
                    return;
                } else if (aVar.K >= 75.0d) {
                    this.f.setImageLevel(5);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public void setPresenter(DJILPLivePresenter dJILPLivePresenter) {
        this.b = dJILPLivePresenter;
    }

    public void dispatchOnCreate() {
        Log.e(this.a, "dispatchOnCreate: ");
        a();
        handleEvent();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        Log.e(this.a, "dispatchOnCreate: setOnClickListener");
        this.e.setOnClickListener(this);
        this.e.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJILPLiveShareFpvView a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (view.getId() != R.id.liveshare_streaming_iv) {
                    return false;
                }
                switch (motionEvent.getAction()) {
                    case 1:
                        Log.e(this.a.a, "onTouch: ACTION_UP");
                        AppPublicReflect.handleLiveShare(this.a.i);
                        if (this.a.b == null) {
                            return true;
                        }
                        this.a.b;
                        DJILPLivePresenter.setliveButtonState(true);
                        return true;
                    default:
                        return true;
                }
            }
        });
    }

    public void dispatchOnDestroy() {
        this.j = null;
    }

    private void a() {
        Log.d(this.a, "initWidgets: ");
        this.c = (DJITextView) findViewById(R.id.liveshare_time_txt);
        this.d = (DJITextView) findViewById(R.id.liveshare_streaming_tv);
        this.e = (DJIImageView) findViewById(R.id.liveshare_streaming_iv);
        this.f = (DJIImageView) findViewById(R.id.liveshare_voice_level_img);
        this.e.setBackgroundResource(R.drawable.lp_liveshare_streaming_lost);
        this.g = (DJITextView) findViewById(R.id.liveshare_fpv_value);
        this.h = (DJIImageView) findViewById(R.id.liveshare_mic_iv);
        AnimationDrawable animationDrawable = (AnimationDrawable) this.e.getBackground();
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        Log.e(this.a, "onInterceptTouchEvent: " + motionEvent.getAction());
        boolean onInterceptTouchEvent = super.onInterceptTouchEvent(motionEvent);
        Log.e(this.a, "onInterceptTouchEvent: result = " + onInterceptTouchEvent);
        Log.e(this.a, "onTouchEvent: isFocusable = " + this.e.isFocusable() + " isclickable = " + this.e.isClickable() + " isFocused = " + this.e.isFocused() + " isFocusableInTouchMode" + this.e.isFocusableInTouchMode());
        return onInterceptTouchEvent;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        Log.e(this.a, "onTouchEvent: " + motionEvent.getAction());
        return super.onTouchEvent(motionEvent);
    }

    public void handleEvent() {
        AnimationDrawable animationDrawable;
        if (AppPublicReflect.isLaunch() && AppPublicReflect.isRunning()) {
            if (this.j == null) {
                this.j = new Handler();
            }
            this.j.post(this.k);
            this.d.setText(R.string.lp_gensetting_liveshare);
            Log.d(this.a, "handleEvent: isRunning");
            this.e.setBackgroundResource(R.drawable.lp_liveshare_streaming_custom);
            animationDrawable = (AnimationDrawable) this.e.getBackground();
            if (!animationDrawable.isRunning()) {
                animationDrawable.start();
                return;
            }
            return;
        }
        this.j = null;
        this.d.setText(R.string.lp_live_connect);
        this.e.setBackgroundResource(R.drawable.lp_liveshare_streaming_lost);
        animationDrawable = (AnimationDrawable) this.e.getBackground();
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }

    public int[] formatSecondToMinuteAr(int i) {
        return new int[]{i % 60, i / 60};
    }

    public void onClick(View view) {
        Log.e(this.a, "onClick: ");
        AppPublicReflect.handleLiveShare(this.i);
        if (this.b != null) {
            DJILPLivePresenter dJILPLivePresenter = this.b;
            DJILPLivePresenter.setliveButtonState(true);
        }
    }
}
