package dji.device.camera.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.camera.a.a;
import dji.device.camera.a.b;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.log.DJILogHelper;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class DJIMenuSeptalLineLongan extends RelativeLayout {
    private static final String c = "DJIMenuSeptalLineLongan";
    private static final int g = 1;
    View a;
    View b;
    private Animation d;
    private LayoutParams e;
    private int f;
    private Handler h = new Handler(new Callback(this) {
        final /* synthetic */ DJIMenuSeptalLineLongan a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    this.a.startAnimation(this.a.d);
                    break;
            }
            return false;
        }
    });

    public DJIMenuSeptalLineLongan(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    private void a() {
        this.e = (LayoutParams) getLayoutParams();
        this.a = findViewById(R.id.longan_menu_line_land);
        this.b = findViewById(R.id.longan_menu_line_port);
        DJILogHelper.getInstance().LOGD(c, "init");
        this.d = AnimationUtils.loadAnimation(getContext(), R.anim.longan_menu_fade_in);
        this.d.setAnimationListener(new AnimationListener(this) {
            final /* synthetic */ DJIMenuSeptalLineLongan a;

            {
                this.a = r1;
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                this.a.setVisibility(0);
            }
        });
        this.f = (int) getResources().getDimension(R.dimen.longan_MenuSeptaLine_margin);
        b();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        DJILogHelper.getInstance().LOGD(c, "onConfigurationChanged");
        super.onConfigurationChanged(configuration);
        b();
    }

    private void b() {
        if (getResources().getConfiguration().orientation == 2) {
            this.e.width = -2;
            this.e.height = -1;
            this.e.setMargins(0, this.f, 0, this.f);
            this.e.addRule(2, 0);
            this.e.addRule(1, R.id.longan_level1_menu_layout);
            this.a.setVisibility(0);
            this.b.setVisibility(8);
        } else {
            this.e.height = -2;
            this.e.width = -1;
            this.e.setMargins(this.f, 0, this.f, 0);
            this.e.addRule(1, 0);
            this.e.addRule(2, R.id.longan_level1_menu_layout);
            this.a.setVisibility(8);
            this.b.setVisibility(0);
        }
        if (getVisibility() == 0) {
            setAnimationVisibility(0);
        }
    }

    public void setAnimationVisibility(int i) {
        if (i == 0) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public void handleViewChange() {
        if (getVisibility() == 0) {
            setVisibility(4);
        } else {
            setVisibility(0);
        }
    }

    public void show(boolean z) {
        if (z) {
            setAnimationVisibility(0);
        } else {
            setAnimationVisibility(4);
        }
    }

    public void setVisibility(int i) {
        if (a.getInstance().d() != a.a.RECORD || i != 0) {
            super.setVisibility(i);
        }
    }

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case HIDE_ALL:
                setVisibility(4);
                return;
            case SHOW_MENU:
                setVisibility(0);
                return;
            case HIDE_MENU:
                setVisibility(4);
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(b.c cVar) {
        if (cVar == b.c.TIMING) {
            setVisibility(4);
        }
    }

    public void onEventMainThread(a aVar) {
        if (aVar.d() == a.a.RECORD) {
            setAlpha(0.0f);
        } else if (aVar.d() == a.a.TAKEPHOTO) {
            setAlpha(1.0f);
        }
    }

    public void onEventMainThread(dji.device.camera.a.c cVar) {
        if (cVar.c() == dji.device.camera.a.c.b.TIMELAPSE) {
            setAlpha(0.0f);
        } else {
            setAlpha(1.0f);
        }
    }
}
