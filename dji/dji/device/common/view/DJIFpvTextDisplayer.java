package dji.device.common.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.pilot.fpv.R;

public class DJIFpvTextDisplayer extends RelativeLayout {
    private static DJIFpvTextDisplayer l = null;
    int a;
    int b;
    int c;
    float d;
    float e;
    boolean f = false;
    boolean g = true;
    ObjectAnimator h;
    private TextView i;
    private final int j = 1;
    private final int k = 2;
    private Handler m = new Handler(this) {
        final /* synthetic */ DJIFpvTextDisplayer a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.i.setText(this.a.c + "");
                    if (this.a.c == 0) {
                        this.a.m.removeMessages(1);
                        this.a.hide();
                        this.a.f = false;
                        return;
                    }
                    this.a.m.sendEmptyMessageDelayed(1, 1000);
                    DJIFpvTextDisplayer dJIFpvTextDisplayer = this.a;
                    dJIFpvTextDisplayer.c--;
                    return;
                case 2:
                    this.a.hide();
                    return;
                default:
                    return;
            }
        }
    };

    public static synchronized DJIFpvTextDisplayer getInstance() {
        DJIFpvTextDisplayer dJIFpvTextDisplayer;
        synchronized (DJIFpvTextDisplayer.class) {
            dJIFpvTextDisplayer = l;
        }
        return dJIFpvTextDisplayer;
    }

    public DJIFpvTextDisplayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        l = this;
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getScreenSize();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        l = null;
    }

    private void a() {
        this.i = new TextView(getContext());
        this.i.setTextColor(getResources().getColor(R.color.white));
        this.i.setGravity(14);
        this.i.setShadowLayer(6.0f, 1.0f, 1.0f, getResources().getColor(R.color.black_60P_longan));
        addView(this.i);
        LayoutParams layoutParams = (LayoutParams) this.i.getLayoutParams();
        layoutParams.height = -2;
        layoutParams.width = -2;
        layoutParams.addRule(14);
        this.h = ObjectAnimator.ofFloat(this, "alpha", new float[]{1.0f, 0.0f});
        this.h.setDuration(300);
        this.h.setInterpolator(new AccelerateInterpolator());
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getScreenSize();
        b();
    }

    private void b() {
        setTextYPositionInPercent(this.e);
    }

    public DJIFpvTextDisplayer setTextColor(int i) {
        this.i.setTextColor(getResources().getColor(i));
        return this;
    }

    public DJIFpvTextDisplayer setText(String str) {
        this.i.setText(str);
        return this;
    }

    public DJIFpvTextDisplayer setTextFadeOut(String str, int i) {
        this.m.removeMessages(2);
        if (!this.g) {
            this.h.cancel();
            setAlpha(1.0f);
        }
        this.i.setText(str);
        this.m.sendMessageDelayed(this.m.obtainMessage(2), (long) i);
        return this;
    }

    public DJIFpvTextDisplayer setTextSize(int i) {
        this.i.setTextSize((float) i);
        return this;
    }

    public DJIFpvTextDisplayer setTextPositionInPercent(float f, float f2) {
        this.d = f;
        this.e = f2;
        this.i.setX((((float) this.a) * f) - ((float) (this.i.getWidth() / 2)));
        this.i.setY((((float) this.b) * f2) - ((float) (this.i.getHeight() / 2)));
        return this;
    }

    public DJIFpvTextDisplayer setTextYPositionInPercent(float f) {
        this.e = f;
        this.i.setY((((float) this.b) * f) - ((float) (this.i.getHeight() / 2)));
        return this;
    }

    public DJIFpvTextDisplayer setTextYPosition(float f) {
        this.e = (((float) (this.i.getHeight() / 2)) + f) / ((float) this.b);
        this.i.setY(f);
        return this;
    }

    public DJIFpvTextDisplayer setStartTime(int i) {
        this.c = i;
        this.m.sendEmptyMessage(1);
        this.f = true;
        return this;
    }

    public String getText() {
        return this.i.getText().toString();
    }

    @SuppressLint({"NewApi"})
    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        Point point = new Point();
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            this.a = displayMetrics.widthPixels;
            this.b = displayMetrics.heightPixels;
            return;
        }
        windowManager.getDefaultDisplay().getRealSize(point);
        this.a = point.x;
        this.b = point.y;
    }

    public boolean isTiming() {
        return this.f;
    }

    public void show() {
        setVisibility(0);
        setAlpha(0.0f);
        ObjectAnimator objectAnimator = new ObjectAnimator();
        objectAnimator = ObjectAnimator.ofFloat(this, "alpha", new float[]{0.0f, 1.0f});
        objectAnimator.setDuration(50);
        objectAnimator.setInterpolator(new AccelerateInterpolator());
        objectAnimator.start();
    }

    public void hide() {
        this.h.start();
        this.h.addListener(new AnimatorListenerAdapter(this) {
            final /* synthetic */ DJIFpvTextDisplayer a;

            {
                this.a = r1;
            }

            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                this.a.setVisibility(4);
                this.a.setAlpha(1.0f);
                this.a.g = true;
            }

            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                this.a.g = false;
            }
        });
    }
}
