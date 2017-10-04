package dji.device.common.view.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.camera.datamanager.DJICameraDataManagerCompat.b;
import dji.device.camera.view.ref.DJILCameraColorView;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJILCameraCenterPointView extends ScrollView implements dji.device.common.view.set.view.DJIStageViewCompat.a, dji.device.common.view.set.view.a.a {
    private Context l = null;
    private final a[] m = new a[8];
    private OnClickListener n = null;
    private DJIRelativeLayout o = null;
    private DJILCameraColorView p = null;
    private dji.device.camera.view.ref.DJILCameraColorView.a q = null;

    private static final class a {
        private DJIRelativeLayout a;
        private DJITextView b;
        private DJIImageView c;
        private DJIImageView d;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public DJILCameraCenterPointView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.l = context;
    }

    public void dispatchOnStart() {
        a(DJICameraDataManagerCompat.getInstance().getCenterPoint());
        this.p.setColor(DJICameraDataManagerCompat.getInstance().getCPColor());
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    private void a(int i) {
        if (this.o != null) {
            this.o.findViewById(R.id.longan_shotcuts_itemlist_arrow).setVisibility(4);
        }
        this.o = this.m[i].a;
        this.o.findViewById(R.id.longan_shotcuts_itemlist_arrow).setVisibility(0);
    }

    private void a() {
        this.n = new OnClickListener(this) {
            final /* synthetic */ DJILCameraCenterPointView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                for (int i = 0; i < 8; i++) {
                    if (this.a.m[i].a != view) {
                        this.a.m[i].d.setVisibility(4);
                    } else if (this.a.o != view) {
                        DJICameraDataManagerCompat.getInstance().updateCenterPoint(i);
                        this.a.a(i);
                        this.a.m[i].d.setVisibility(0);
                    }
                }
            }
        };
        this.q = new dji.device.camera.view.ref.DJILCameraColorView.a(this) {
            final /* synthetic */ DJILCameraCenterPointView a;

            {
                this.a = r1;
            }

            public void a(b bVar, int i) {
                DJICameraDataManagerCompat.getInstance().updateCPColor(bVar);
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
            int[] iArr = new int[]{R.id.camera_newfn_cp_none_ly, R.id.camera_newfn_cp_standard_ly, R.id.camera_newfn_cp_cross_ly, R.id.camera_newfn_cp_crossn_ly, R.id.camera_newfn_cp_corner_ly, R.id.camera_newfn_cp_corner_cross_ly, R.id.camera_newfn_cp_square_ly, R.id.camera_newfn_cp_square_cross_ly};
            int[] iArr2 = new int[]{R.drawable.advanced_more_off, R.drawable.longan_camera_centerpoints1, R.drawable.longan_camera_centerpoints6, R.drawable.longan_camera_centerpoints7, R.drawable.longan_camera_centerpoints3, R.drawable.longan_camera_centerpoints2, R.drawable.longan_camera_centerpoints5, R.drawable.longan_camera_centerpoints4};
            int[] iArr3 = new int[]{R.string.app_off, R.string.camera_ref_point_standard, R.string.camera_ref_point_cross, R.string.camera_ref_point_cross_narrow, R.string.camera_ref_point_corner, R.string.camera_ref_point_corner_cross, R.string.camera_ref_point_square, R.string.camera_ref_point_square_cross};
            for (int i = 0; i < 8; i++) {
                a aVar = new a();
                DJIRelativeLayout dJIRelativeLayout = (DJIRelativeLayout) findViewById(iArr[i]);
                aVar.a = dJIRelativeLayout;
                aVar.b = (DJITextView) dJIRelativeLayout.findViewById(R.id.longan_shotcuts_itemlist_title);
                aVar.c = (DJIImageView) dJIRelativeLayout.findViewById(R.id.longan_shotcuts_itemlist_value_iv);
                aVar.d = (DJIImageView) dJIRelativeLayout.findViewById(R.id.longan_shotcuts_itemlist_arrow);
                dJIRelativeLayout.setOnClickListener(this.n);
                aVar.c.setImageResource(iArr2[i]);
                aVar.b.setText(iArr3[i]);
                aVar.d.setVisibility(4);
                this.m[i] = aVar;
            }
            this.p = (DJILCameraColorView) findViewById(R.id.camera_newfn_cp_color_ly);
            this.p.setDescResId(R.string.camera_ref_point_color);
            this.p.setOnColorListener(this.q);
        }
    }

    public View getView() {
        return this;
    }
}
