package dji.device.common.view.set.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIRoundLinearLayoutCompat;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.device.common.view.set.view.DJIStageViewCompat.e;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class LonganGimbalNewShotcutsView extends DJIRoundLinearLayoutCompat {
    public static boolean a = false;
    protected a b = null;
    protected e c = null;
    LayoutParams d;
    protected DJIRelativeLayout e = null;
    protected DJIImageView f = null;
    protected DJITextView g = null;
    protected DJIStageViewCompat h;
    Context i;

    public interface a {
        void a(boolean z);
    }

    public LonganGimbalNewShotcutsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = context;
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.d = (LayoutParams) getLayoutParams();
        b();
        c.a().a(this);
        if (!isInEditMode()) {
            this.f = (DJIStateImageViewCompat) findViewById(R.id.longan_shotcuts_gimbal_title_back);
            this.g = (DJITextView) findViewById(R.id.longan_shotcuts_gimble_title);
            this.f.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ LonganGimbalNewShotcutsView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.h.canGoBack()) {
                        this.a.h.destoryStageView(true);
                    }
                }
            });
            this.h = (DJIStageViewCompat) findViewById(R.id.longan_shotcuts_gimbal_contect_ly);
            this.h.setOnStageChangeListener(this.c);
            this.h.createStageView(R.layout.longan_gimble_shotcuts_list_view, R.string.longan_shotcuts_gimbal_title, false);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        b();
    }

    private void b() {
        if (getResources().getConfiguration().orientation == 2) {
            this.d.addRule(3, 0);
            this.d.addRule(14, 0);
            this.d.addRule(0, R.id.longan_preview_setting_bar);
            this.d.addRule(15);
            this.d.height = getResources().getDimensionPixelOffset(R.dimen.longan_shotcuts_camera_view_height_land);
            this.d.width = getResources().getDimensionPixelOffset(R.dimen.longan_shotcuts_view_width);
            return;
        }
        this.d.addRule(0, 0);
        this.d.addRule(15, 0);
        this.d.addRule(3, R.id.longan_preview_setting_bar);
        this.d.addRule(14);
        this.d.height = getResources().getDimensionPixelOffset(R.dimen.longan_shotcuts_camera_view_height_port);
        this.d.width = getResources().getDimensionPixelOffset(R.dimen.longan_shotcuts_view_width_port);
    }

    public void setOnVisibilityChangeListener(a aVar) {
        this.b = aVar;
    }

    protected void onFinishInflate() {
    }

    protected void a() {
        if (!isInEditMode()) {
            this.c = new e(this) {
                final /* synthetic */ LonganGimbalNewShotcutsView a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                }

                public void a(int i, int i2, int i3) {
                    this.a.a(i, i2, i3);
                }
            };
        }
    }

    protected void a(int i, int i2, int i3) {
        this.g.setText(i2);
        if (i == 1) {
            this.f.setVisibility(8);
        } else if (i > 1) {
            this.f.setVisibility(0);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.HIDE_SHOTCUTS_GIMBAL_LY) {
            hide();
            a = false;
        } else if (mVar == m.SHOW_SHOTCUTS_GIMBAL_LY) {
            show();
            a = true;
        } else if (mVar == m.HIDE_ALL) {
            hide();
            a = false;
        } else if (mVar == m.SHOW_TIMELAPSE_LY) {
            hide();
            a = false;
        } else if (mVar == m.SHOW_CONFLICT_RIGHT_VIEW) {
            hide();
            a = false;
        }
    }
}
