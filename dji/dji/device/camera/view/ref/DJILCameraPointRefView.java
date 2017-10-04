package dji.device.camera.view.ref;

import android.content.Context;
import android.util.AttributeSet;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.view.set.view.a.a;
import dji.logic.f.b;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.thirdparty.a.c;

public class DJILCameraPointRefView extends DJIImageView implements a {
    private final int[][] l;

    public DJILCameraPointRefView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        r0 = new int[6][];
        r0[0] = new int[]{R.drawable.longan_camera_centerpoints_white1, R.drawable.longan_camera_centerpoints_white6, R.drawable.longan_camera_centerpoints_white7, R.drawable.longan_camera_centerpoints_white3, R.drawable.longan_camera_centerpoints_white2, R.drawable.longan_camera_centerpoints_white5, R.drawable.longan_camera_centerpoints_white4};
        r0[1] = new int[]{R.drawable.longan_camera_centerpoints_yellow1, R.drawable.longan_camera_centerpoints_yellow6, R.drawable.longan_camera_centerpoints_yellow7, R.drawable.longan_camera_centerpoints_yellow3, R.drawable.longan_camera_centerpoints_yellow2, R.drawable.longan_camera_centerpoints_yellow5, R.drawable.lonagn_camera_centerpoints_yellow4};
        r0[2] = new int[]{R.drawable.longan_camera_centerpoints_red1, R.drawable.longan_camera_centerpoints_red6, R.drawable.longan_camera_centerpoints_red7, R.drawable.longan_camera_centerpoints_red3, R.drawable.longan_camera_centerpoints_red2, R.drawable.longan_camera_centerpoints_red5, R.drawable.longan_camera_centerpoints_red4};
        r0[3] = new int[]{R.drawable.longan_camera_centerpoints_bule1, R.drawable.longan_camera_centerpoints_bule6, R.drawable.longan_camera_centerpoints_bule7, R.drawable.longan_camera_centerpoints_bule3, R.drawable.longan_camera_centerpoints_bule2, R.drawable.longan_camera_centerpoints_bule5, R.drawable.longan_camera_centerpoints_bule4};
        r0[4] = new int[]{R.drawable.longan_camera_centerpoints_green1, R.drawable.longan_camera_centerpoints_green6, R.drawable.longan_camera_centerpoints_green7, R.drawable.longan_camera_centerpoints_green3, R.drawable.longan_camera_centerpoints_green2, R.drawable.longan_camera_centerpoints_green5, R.drawable.longan_camera_centerpoints_green4};
        r0[5] = new int[]{R.drawable.longan_camera_centerpoints_black1, R.drawable.longan_camera_centerpoints_black6, R.drawable.longan_camera_centerpoints_black7, R.drawable.longan_camera_centerpoints_black3, R.drawable.longan_camera_centerpoints_black2, R.drawable.longan_camera_centerpoints_black5, R.drawable.longan_camera_centerpoints_black4};
        this.l = r0;
    }

    public void onEventMainThread(DJICameraDataManagerCompat.a aVar) {
        if (aVar == DJICameraDataManagerCompat.a.CENTER_POINT_CHANGED) {
            a();
        } else if (aVar == DJICameraDataManagerCompat.a.CENTER_POINT_COLOR_CHANGED) {
            a();
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (b.l(dataCameraGetPushStateInfo.getCameraType())) {
            DJICameraDataManagerCompat.getInstance().updateCenterPoint(0);
        } else {
            DJICameraDataManagerCompat.getInstance().updateCenterPoint(0);
        }
    }

    private void a() {
        int centerPoint = DJICameraDataManagerCompat.getInstance().getCenterPoint();
        if (centerPoint != 0) {
            if (!isShown()) {
                setVisibility(0);
            }
            setImageResource(this.l[DJICameraDataManagerCompat.getInstance().getCPColor().b()][centerPoint - 1]);
            return;
        }
        setVisibility(8);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!(isInEditMode() || c.a().c(this))) {
            c.a().a(this);
        }
        a();
        onEventMainThread(DataCameraGetPushStateInfo.getInstance());
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
