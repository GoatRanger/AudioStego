package dji.pilot.fpv.camera.control;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.pilot.R;
import dji.pilot.fpv.camera.control.b.b;
import dji.pilot.fpv.view.DJICameraAnimView;
import dji.pilot.newfpv.d;
import dji.pilot.newfpv.f;
import dji.pilot.newfpv.g;
import dji.pilot.newfpv.view.b.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.a.b.c;

public class DJICameraControlView extends DJILinearLayout implements OnClickListener, b {
    private g a = null;
    private int b = 0;
    private d c = null;
    @c(a = 2131362585)
    private DJIRelativeLayout d = null;
    @c(a = 2131362586)
    private DJIImageView e = null;
    @c(a = 2131362587)
    private DJIImageView f = null;
    @c(a = 2131362588)
    private DJIImageView g = null;
    @c(a = 2131362589)
    private DJILinearLayout h = null;
    @c(a = 2131362590)
    private DJIImageView i = null;
    @c(a = 2131362591)
    private DJITextView j = null;
    @c(a = 2131362581)
    private DJIImageView k = null;
    @c(a = 2131362582)
    private DJIRelativeLayout l = null;
    @c(a = 2131362583)
    private DJIImageView m = null;
    @c(a = 2131362584)
    private DJIImageView n = null;
    @c(a = 2131362593)
    private DJIImageView o = null;
    @c(a = 2131362592)
    private DJIImageView p = null;
    private a q = null;
    private DJICameraAnimView r = null;
    private Context s = null;
    private Animation t = null;
    private Animation u = null;
    private Animation v = null;
    private Animation w = null;

    public DJICameraControlView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = context;
        bindInfo(a.h, f.b.a, f.b.b);
    }

    public void bindAnimView(DJICameraAnimView dJICameraAnimView) {
        this.r = dJICameraAnimView;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            dji.pilot.publics.e.f.a(this);
            this.e.setOnClickListener(this);
            this.g.setOnClickListener(this);
            this.k.setOnClickListener(this);
            this.o.setOnClickListener(this);
            this.p.setOnClickListener(this);
            this.m.setOnClickListener(this);
            this.v = AnimationUtils.loadAnimation(this.s, R.anim.bp);
            this.w = AnimationUtils.loadAnimation(this.s, R.anim.bq);
            this.q = new a();
            this.q.a(this, 0);
        }
    }

    public void setSelfEnable(boolean z) {
        setEnabled(z);
        if (z) {
            setAlpha(1.0f);
        } else {
            setAlpha(dji.pilot.visual.a.d.c);
        }
    }

    public void setSwitchViewEnable(boolean z) {
        this.k.setEnabled(z);
    }

    public void setPhotoViewEnable(boolean z) {
        this.e.setEnabled(z);
    }

    public void setPIVViewEnable(boolean z) {
        this.m.setEnabled(z);
    }

    public void setVideoViewEnable(boolean z) {
        this.g.setEnabled(z);
    }

    public void setSettingViewEnable(boolean z) {
        this.p.setEnabled(z);
    }

    public void setPlayBackViewEnable(boolean z) {
        this.o.setEnabled(z);
    }

    public void updatePhotoView(int i, int i2) {
        this.e.setBackgroundResource(i);
    }

    public void updateSettingView(int i, int i2) {
        this.p.setImageResource(i);
    }

    public void startAnimVideo() {
        this.r.startVideo();
    }

    public void stopAnimVideo() {
        this.r.stopVideo();
    }

    public void startTakePhoto(TYPE type, int i) {
        this.r.startTakePic(type, i);
    }

    public void handleVisibilityEvent(boolean z) {
        if (!z) {
            startAnimation(this.w);
            setVisibility(8);
        } else if (needShow() && this.a.a(getViewInfo(), 0)) {
            startAnimation(this.v);
            setVisibility(0);
        }
    }

    public void handleMenuEvent(boolean z) {
        if (-1 == getLayoutParams().height) {
            if (z) {
                setBackgroundColor(getResources().getColor(R.color.a_));
            } else {
                setBackgroundColor(getResources().getColor(R.color.j5));
            }
        }
    }

    public void updateVideoTime(String str) {
        this.j.setText(str);
    }

    public void showVideoView() {
        this.d.go();
        this.g.show();
        this.k.setImageResource(R.drawable.wm220_ic_switch_video_nor);
    }

    public void showPhotoView() {
        this.g.go();
        this.d.show();
        this.k.setImageResource(R.drawable.wm220_ic_switch_cam_nor);
    }

    public void showPIVView() {
        this.l.show();
        this.k.go();
    }

    public void hidePIVView() {
        this.k.show();
        this.l.go();
    }

    public void showStoringView() {
        if (this.q.b(0)) {
            this.n.show();
            this.n.startAnimation(getSavingAnim());
            return;
        }
        this.f.show();
        this.f.startAnimation(getSavingAnim());
    }

    public void hideStoringView() {
        if (this.q.b(0)) {
            this.n.clearAnimation();
            this.n.go();
            return;
        }
        this.f.clearAnimation();
        this.f.go();
    }

    public void showRecordingView() {
        this.g.setImageResource(R.drawable.wm220_btn_cam_video_pause_nor);
        this.h.show();
        this.i.startAnimation(getRecordingAnim());
    }

    public void hideRecordingView() {
        this.g.setImageResource(R.drawable.wm220_btn_cam_video_play_nor);
        this.i.clearAnimation();
        this.h.hide();
    }

    private Animation getRecordingAnim() {
        if (this.u == null) {
            Animation alphaAnimation = new AlphaAnimation(1.0f, 0.3f);
            alphaAnimation.setDuration(800);
            alphaAnimation.setRepeatMode(2);
            alphaAnimation.setRepeatCount(-1);
            this.u = alphaAnimation;
        }
        return this.u;
    }

    private Animation getSavingAnim() {
        if (this.t == null) {
            this.t = AnimationUtils.loadAnimation(this.s, R.anim.b_);
            this.t.setAnimationListener(new 1(this));
        }
        return this.t;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (R.id.ut == id) {
            this.q.a(0);
        } else if (R.id.uv == id) {
            this.q.c();
        } else if (R.id.uo == id) {
            this.q.a();
        } else if (R.id.v0 == id) {
            this.q.d();
        } else if (R.id.uz == id) {
            this.q.e();
        } else if (R.id.uq == id) {
            this.q.b();
        }
    }

    public void bind(g gVar, int i) {
        this.a = gVar;
        this.b = i;
    }

    public void bindInfo(a aVar, f.b bVar, f.b bVar2) {
        this.c = new d(this, aVar, bVar, bVar2);
    }

    public a getViewId() {
        return this.c.b;
    }

    public d getViewInfo() {
        return this.c;
    }

    public boolean needShow() {
        return true;
    }

    public View getSelf() {
        return this;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            this.q.f();
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode()) {
            this.q.g();
        }
        super.onDetachedFromWindow();
    }
}
