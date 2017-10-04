package dji.phone.set.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.device.common.view.set.view.DJIStageViewCompat;
import dji.device.common.view.set.view.DJIStageViewCompat.e;
import dji.phone.e.b;
import dji.phone.k.a;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJILPGimbalSetView extends DJILinearLayout {
    protected e a = null;
    LayoutParams b;
    protected DJIRelativeLayout c = null;
    protected DJIImageView d = null;
    protected DJITextView e = null;
    protected DJIStageViewCompat f;
    Context g;
    LayoutParams h;

    public DJILPGimbalSetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = context;
        a();
    }

    protected void onAttachedToWindow() {
        c.a().a(this);
        this.h = (LayoutParams) getLayoutParams();
    }

    public void onEventMainThread(b bVar) {
        if (bVar.a == dji.phone.e.a.e.VIEW_SHOTCUT_GIMBAL) {
            a.a(this, bVar);
        }
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.d = (DJIStateImageViewCompat) findViewById(R.id.longan_shotcuts_gimbal_title_back);
            this.e = (DJITextView) findViewById(R.id.longan_shotcuts_gimble_title);
            this.d.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJILPGimbalSetView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.f.canGoBack()) {
                        this.a.f.destoryStageView(true);
                    }
                }
            });
            this.f = (DJIStageViewCompat) findViewById(R.id.longan_shotcuts_gimbal_contect_ly);
            this.f.setOnStageChangeListener(this.a);
            this.f.createStageView(R.layout.lp_gimble_shotcuts_list_view, R.string.longan_shotcuts_gimbal_title, false, getLayoutParams().width, getLayoutParams().height);
        }
    }

    protected void a() {
        if (!isInEditMode()) {
            this.a = new e(this) {
                final /* synthetic */ DJILPGimbalSetView a;

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
        this.e.setText(i2);
        if (i == 1) {
            this.d.setVisibility(8);
        } else if (i > 1) {
            this.d.setVisibility(0);
        }
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        float a = dji.phone.k.c.a(bVar.b());
        dji.phone.h.a.a(this, getRotation(), a);
        if (a == 90.0f || a == 270.0f) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(this.h);
            layoutParams.rightMargin -= (dji.phone.preview.a.f - this.h.width) / 2;
            layoutParams.width = dji.phone.preview.a.f;
            layoutParams.height = this.h.width;
            setLayoutParams(layoutParams);
            return;
        }
        setLayoutParams(this.h);
    }
}
