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
import dji.phone.a.g;
import dji.phone.e.b;
import dji.phone.k.a;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class DJILPCameraSetView extends DJILinearLayout {
    public static boolean a = false;
    private static final String h = "DJILPCameraShortcusView";
    protected e b = null;
    protected DJIImageView c = null;
    protected DJITextView d = null;
    protected DJIStageViewCompat e;
    protected Context f;
    LayoutParams g;

    public DJILPCameraSetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context;
        a();
    }

    protected void onFinishInflate() {
        g.a(h, "DJILPCameraSetView onFinishInflate");
        if (!isInEditMode()) {
            this.c = (DJIStateImageViewCompat) findViewById(R.id.longan_shotcuts_camera_title_back);
            this.d = (DJITextView) findViewById(R.id.longan_shotcuts_camera_title);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJILPCameraSetView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.e.canGoBack()) {
                        this.a.e.destoryStageView(true);
                    }
                }
            });
            this.e = (DJIStageViewCompat) findViewById(R.id.longan_shotcuts_contect_ly);
            this.e.setOnStageChangeListener(this.b);
            this.e.createStageView(R.layout.lp_camera_shotcuts_contect_view, R.string.fpv_camera_shotcuts_title, false, getLayoutParams().width, getLayoutParams().height);
        }
    }

    protected void a() {
        if (!isInEditMode()) {
            this.b = new e(this) {
                final /* synthetic */ DJILPCameraSetView a;

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
        this.d.setText(i2);
        if (i == 1) {
            this.c.setVisibility(8);
        } else if (i > 1) {
            this.c.setVisibility(0);
        }
    }

    protected void onAttachedToWindow() {
        c.a().a(this);
        this.g = (LayoutParams) getLayoutParams();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(b bVar) {
        if (bVar.a == dji.phone.e.a.e.VIEW_SHOTCUT_CAMERA) {
            a.a(this, bVar);
        }
    }

    public void onEventMainThread(dji.phone.h.b bVar) {
        float a = dji.phone.k.c.a(bVar.b());
        dji.phone.h.a.a(this, getRotation(), a);
        if (a == 90.0f || a == 270.0f) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(this.g);
            layoutParams.rightMargin -= (dji.phone.preview.a.f - this.g.width) / 2;
            layoutParams.width = dji.phone.preview.a.f;
            layoutParams.height = this.g.width;
            setLayoutParams(layoutParams);
            return;
        }
        setLayoutParams(this.g);
    }
}
