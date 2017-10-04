package dji.pilot.fpv.camera.ref;

import android.content.Context;
import android.util.AttributeSet;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.a;
import dji.pilot.fpv.camera.more.a$a;
import dji.pilot.fpv.camera.more.d.b;
import dji.publics.DJIUI.DJIImageView;
import dji.thirdparty.a.c;

public class DJICameraPointRefView extends DJIImageView implements b {
    private final int[][] a = new int[][]{new int[]{R.drawable.camera_centerpoints_white1, R.drawable.camera_centerpoints_white6, R.drawable.camera_centerpoints_white7, R.drawable.camera_centerpoints_white3, R.drawable.camera_centerpoints_white2, R.drawable.camera_centerpoints_white5, R.drawable.camera_centerpoints_white4}, new int[]{R.drawable.camera_centerpoints_yellow1, R.drawable.camera_centerpoints_yellow6, R.drawable.camera_centerpoints_yellow7, R.drawable.camera_centerpoints_yellow3, R.drawable.camera_centerpoints_yellow2, R.drawable.camera_centerpoints_yellow5, R.drawable.camera_centerpoints_yellow4}, new int[]{R.drawable.camera_centerpoints_red1, R.drawable.camera_centerpoints_red6, R.drawable.camera_centerpoints_red7, R.drawable.camera_centerpoints_red3, R.drawable.camera_centerpoints_red2, R.drawable.camera_centerpoints_red5, R.drawable.camera_centerpoints_red4}, new int[]{R.drawable.camera_centerpoints_bule1, R.drawable.camera_centerpoints_bule6, R.drawable.camera_centerpoints_bule7, R.drawable.camera_centerpoints_bule3, R.drawable.camera_centerpoints_bule2, R.drawable.camera_centerpoints_bule5, R.drawable.camera_centerpoints_bule4}, new int[]{R.drawable.camera_centerpoints_green1, R.drawable.camera_centerpoints_green6, R.drawable.camera_centerpoints_green7, R.drawable.camera_centerpoints_green3, R.drawable.camera_centerpoints_green2, R.drawable.camera_centerpoints_green5, R.drawable.camera_centerpoints_green4}, new int[]{R.drawable.camera_centerpoints_black1, R.drawable.camera_centerpoints_black6, R.drawable.camera_centerpoints_black7, R.drawable.camera_centerpoints_black3, R.drawable.camera_centerpoints_black2, R.drawable.camera_centerpoints_black5, R.drawable.camera_centerpoints_black4}};

    public DJICameraPointRefView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onEventMainThread(a$a dji_pilot_fpv_camera_more_a_a) {
        if (dji_pilot_fpv_camera_more_a_a == a$a.CENTER_POINT_CHANGED) {
            a();
        } else if (dji_pilot_fpv_camera_more_a_a == a$a.CENTER_POINT_COLOR_CHANGED) {
            a();
        }
    }

    private void a() {
        int s = a.getInstance().s();
        if (s != 0) {
            setImageResource(this.a[a.getInstance().t().b()][s - 1]);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!(isInEditMode() || c.a().c(this))) {
            c.a().a(this);
        }
        a();
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
