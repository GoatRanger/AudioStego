package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.pilot.R;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.newfpv.d;
import dji.pilot.newfpv.f.c;
import dji.pilot.newfpv.g;
import dji.pilot.newfpv.h;
import dji.pilot.newfpv.view.b.a;

public class DJICameraMenuView extends DJIBaseTabStageView implements s, h<c> {
    private static final int[] A = new int[]{R.id.j8, R.id.j9, R.id.j_, R.id.ja};
    private static final int[] y = new int[]{R.id.jg, R.id.jh, R.id.ji, R.id.jj};
    private static final int[] z = new int[]{R.layout.camera_setting_view, R.layout.camera_newfn_photo_view, R.layout.camera_newfn_video_view, R.layout.camera_newfn_other_view};
    private g B = null;
    private int C = 0;
    private d D = null;

    public DJICameraMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        bindInfo(a.ViewId_CameraMenu, c.TO_SHOW, c.TO_HIDE);
        setRadius(0);
        a(y, z, A, false);
    }

    public void handleMenuClick() {
        if (getVisibility() != 0) {
            e.c(s.dg);
            showView();
            return;
        }
        hideView();
    }

    public void showView() {
        if (getVisibility() != 0) {
            d(false);
        }
    }

    private void d(boolean z) {
        int i;
        int i2 = 1;
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        int displayedChild = this.f.getDisplayedChild();
        if (mode == MODE.RECORD) {
            i = 1 == displayedChild ? 1 : 0;
            this.g[2].setVisibility(0);
            this.g[1].setVisibility(8);
            i2 = 2;
        } else if (mode == MODE.TAKEPHOTO) {
            if (2 == displayedChild) {
                i = 1;
            } else {
                i = 0;
            }
            this.g[2].setVisibility(8);
            this.g[1].setVisibility(0);
        } else {
            i = 0;
            i2 = -1;
        }
        if (i != 0) {
            if (z) {
                b(i2, b(i2));
            } else {
                a(i2);
            }
        } else if (!z) {
            a(-1);
        }
    }

    protected void a(boolean z) {
        dji.thirdparty.a.c.a().e(z ? c.SHOW : c.HIDE);
    }

    protected int getRequestedWidth() {
        return this.o.getResources().getDimensionPixelSize(R.dimen.ef);
    }

    protected void a(View view) {
        view.setBackground(null);
    }

    public a getViewId() {
        return this.D.b;
    }

    public void bind(g gVar, int i) {
        this.B = gVar;
        this.C = i;
    }

    public void bindInfo(a aVar, c cVar, c cVar2) {
        this.D = new d(this, aVar, cVar, cVar2);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (getVisibility() == 0) {
            d(true);
        }
    }

    public void onEventMainThread(c cVar) {
        if (c.TO_SHOW == cVar) {
            if (needShow() && this.B.a(getViewInfo(), 0)) {
                showView();
            }
        } else if (c.TO_HIDE == cVar) {
            hideView();
        }
    }

    public d getViewInfo() {
        return this.D;
    }

    public boolean needShow() {
        return true;
    }

    public View getSelf() {
        return this;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
