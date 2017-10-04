package dji.pilot.visual.view;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k.a;
import dji.pilot.visual.a.g$d;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJIFingerFlyPreView extends DJILinearLayout implements a {
    private static final int l = 256;
    private static final long m = 3000;
    private DJILinearLayout a;
    private DJITextView b;
    private DJITextView c;
    private boolean d;
    private final Animation[] e;
    private AnimatorListener f;
    private AnimatorListener g;
    private float h;
    private float i;
    private int j;
    private int k;
    private k n;

    public DJIFingerFlyPreView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = new Animation[2];
        this.f = null;
        this.g = null;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 186;
        this.k = 186;
        this.n = null;
        this.n = new k(this, new Callback(this) {
            final /* synthetic */ DJIFingerFlyPreView a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 256:
                        dji.pilot.visual.beginner.a.getInstance().b();
                        this.a.dimSelf();
                        break;
                }
                return true;
            }
        });
    }

    public void handleFirst(boolean z) {
        if (this.d != z) {
            this.d = z;
            if (z) {
                this.a.setBackgroundResource(R.drawable.visual_point_enter);
                this.b.setText(R.string.visual_go);
                this.c.show();
                return;
            }
            this.a.setBackgroundResource(R.drawable.visual_point_tag);
            this.b.setText("");
            this.c.go();
        }
    }

    public void handleMotion(float f, float f2, boolean z) {
        this.h = f;
        this.i = f2;
        setX(f - ((float) (this.j / 2)));
        setY(f2 - ((float) (this.k / 2)));
        if (this.d) {
            setAlpha(0.2f);
            setScaleX(0.2f);
            setScaleY(0.2f);
            animate().alpha(1.0f).scaleX(1.0f).scaleY(1.0f).setDuration(150).setListener(this.f).start();
            this.n.sendEmptyMessageDelayed(256, 3000);
        } else {
            setAlpha(1.0f);
            setScaleX(1.0f);
            setScaleY(1.0f);
            show();
        }
        this.b.startAnimation(this.e[0]);
        this.c.startAnimation(this.e[1]);
    }

    public void dimSelf() {
        this.n.removeMessages(256);
        if (this.d) {
            animate().alpha(0.2f).scaleX(0.2f).scaleY(0.2f).setDuration(150).setListener(this.g).start();
        } else {
            animate().alpha(0.2f).setDuration(300).setListener(this.g).start();
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJILinearLayout) findViewById(R.id.d95);
            this.b = (DJITextView) findViewById(R.id.d96);
            this.c = (DJITextView) findViewById(R.id.d97);
            this.e[0] = AnimationUtils.loadAnimation(getContext(), R.anim.g);
            this.e[1] = AnimationUtils.loadAnimation(getContext(), R.anim.g);
            Drawable background = this.a.getBackground();
            if (background != null) {
                this.j = background.getIntrinsicWidth();
                this.k = background.getIntrinsicHeight();
            }
            this.f = new AnimatorListener(this) {
                final /* synthetic */ DJIFingerFlyPreView a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animator animator) {
                    this.a.show();
                }

                public void onAnimationRepeat(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                }

                public void onAnimationCancel(Animator animator) {
                }
            };
            this.g = new AnimatorListener(this) {
                final /* synthetic */ DJIFingerFlyPreView a;

                {
                    this.a = r1;
                }

                public void onAnimationStart(Animator animator) {
                }

                public void onAnimationEnd(Animator animator) {
                    this.a.go();
                }

                public void onAnimationCancel(Animator animator) {
                }

                public void onAnimationRepeat(Animator animator) {
                }
            };
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJIFingerFlyPreView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (DataOsdGetPushCommon.getInstance().groundOrSky() != 2) {
                        b bVar = new b();
                        bVar.b = R.string.visual_track_exception_ground;
                        bVar.a = d.b;
                        c.a().e(bVar);
                        return;
                    }
                    dji.pilot.visual.a.c instance = dji.pilot.visual.a.c.getInstance();
                    if (!instance.c()) {
                        float[] a = instance.a(this.a.h, this.a.i, 0.0f, 0.0f);
                        instance.a().a(a[0], a[1]);
                        this.a.dimSelf();
                        dji.pilot.visual.beginner.a.getInstance().c();
                        c.a().e(g$d.HIDE_MODE_VIEW);
                    }
                }
            });
        }
    }

    public boolean isFinished() {
        return getVisibility() != 0;
    }
}
