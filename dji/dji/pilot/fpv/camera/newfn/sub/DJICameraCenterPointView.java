package dji.pilot.fpv.camera.newfn.sub;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ScrollView;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.d$a;
import dji.pilot.fpv.camera.more.d.b;
import dji.pilot.fpv.camera.widget.DJICameraColorView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJICameraCenterPointView extends ScrollView implements b, dji.pilot.fpv.view.DJIStageView.a {
    private Context a = null;
    private final a[] b = new a[8];
    private OnClickListener n = null;
    private DJILinearLayout o = null;
    private DJICameraColorView p = null;
    private dji.pilot.fpv.camera.widget.DJICameraColorView.a q = null;

    private static final class a {
        private DJILinearLayout a;
        private DJIImageView b;
        private DJITextView c;
        private DJIImageView d;

        private a() {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    public DJICameraCenterPointView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
    }

    public void dispatchOnStart() {
        a(dji.pilot.fpv.camera.more.a.getInstance().s());
        this.p.setColor(dji.pilot.fpv.camera.more.a.getInstance().t());
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    private void a(int i) {
        if (this.o != null) {
            this.o.setSelected(false);
        }
        this.o = this.b[i].a;
        this.o.setSelected(true);
    }

    private void a() {
        this.n = new OnClickListener(this) {
            final /* synthetic */ DJICameraCenterPointView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int i = 0;
                while (i < 8) {
                    if (this.a.b[i].a != view) {
                        i++;
                    } else if (this.a.o != view) {
                        dji.pilot.fpv.camera.more.a.getInstance().d(i);
                        this.a.a(i);
                        return;
                    } else {
                        return;
                    }
                }
            }
        };
        this.q = new dji.pilot.fpv.camera.widget.DJICameraColorView.a(this) {
            final /* synthetic */ DJICameraCenterPointView a;

            {
                this.a = r1;
            }

            public void a(d$a dji_pilot_fpv_camera_more_d_a, int i) {
                dji.pilot.fpv.camera.more.a.getInstance().a(dji_pilot_fpv_camera_more_d_a);
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
            int[] iArr = new int[]{R.id.kq, R.id.kr, R.id.ks, R.id.kt, R.id.ku, R.id.kv, R.id.kw, R.id.kx};
            int[] iArr2 = new int[]{R.drawable.advanced_more_off, R.drawable.camera_centerpoints1, R.drawable.camera_centerpoints6, R.drawable.camera_centerpoints7, R.drawable.camera_centerpoints3, R.drawable.camera_centerpoints2, R.drawable.camera_centerpoints5, R.drawable.camera_centerpoints4};
            int[] iArr3 = new int[]{R.string.app_off_lower, R.string.camera_ref_point_standard, R.string.camera_ref_point_cross, R.string.camera_ref_point_cross_narrow, R.string.camera_ref_point_corner, R.string.camera_ref_point_corner_cross, R.string.camera_ref_point_square, R.string.camera_ref_point_square_cross};
            for (int i = 0; i < 8; i++) {
                a aVar = new a();
                DJILinearLayout dJILinearLayout = (DJILinearLayout) findViewById(iArr[i]);
                aVar.a = dJILinearLayout;
                aVar.b = (DJIImageView) dJILinearLayout.findViewById(R.id.l0);
                aVar.c = (DJITextView) dJILinearLayout.findViewById(R.id.l1);
                aVar.d = (DJIImageView) dJILinearLayout.findViewById(R.id.l2);
                dJILinearLayout.setOnClickListener(this.n);
                aVar.b.setImageResource(iArr2[i]);
                aVar.c.setText(iArr3[i]);
                aVar.d.go();
                this.b[i] = aVar;
            }
            this.p = (DJICameraColorView) findViewById(R.id.ky);
            this.p.setDescResId(R.string.camera_ref_point_color);
            this.p.setOnColorListener(this.q);
        }
    }

    public View getView() {
        return this;
    }
}
