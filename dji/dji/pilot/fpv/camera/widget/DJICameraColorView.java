package dji.pilot.fpv.camera.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.d$a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJICameraColorView extends DJILinearLayout {
    private static final int a = (d$a.BLACK.b() + 1);
    private DJITextView b = null;
    private final DJIImageView[] c = new DJIImageView[a];
    private final d$a[] d = new d$a[]{d$a.WHITE, d$a.YELLOW, d$a.RED, d$a.BLUE, d$a.GREEN, d$a.BLACK};
    private final int[] e = new int[]{R.id.ik, R.id.il, R.id.im, R.id.in, R.id.io, R.id.ip};
    private d$a f = d$a.WHITE;
    private OnClickListener g = null;
    private a h = null;
    private DJIImageView i = null;

    public interface a {
        void a(d$a dji_pilot_fpv_camera_more_d_a, int i);
    }

    public DJICameraColorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnColorListener(a aVar) {
        this.h = aVar;
    }

    public void setDescResId(int i) {
        this.b.setText(i);
    }

    public void setColor(d$a dji_pilot_fpv_camera_more_d_a) {
        a(dji_pilot_fpv_camera_more_d_a, false, false);
    }

    private void a(d$a dji_pilot_fpv_camera_more_d_a, boolean z, boolean z2) {
        if (z || dji_pilot_fpv_camera_more_d_a != this.f) {
            this.f = dji_pilot_fpv_camera_more_d_a;
            for (int i = 0; i < a; i++) {
                if (dji_pilot_fpv_camera_more_d_a == this.d[i]) {
                    a(i);
                    if (z2 && this.h != null) {
                        this.h.a(dji_pilot_fpv_camera_more_d_a, 0);
                        return;
                    }
                    return;
                }
            }
        }
    }

    private void a(int i) {
        if (this.i != null) {
            this.i.setSelected(false);
        }
        this.i = this.c[i];
        this.i.setSelected(true);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.g = new OnClickListener(this) {
                final /* synthetic */ DJICameraColorView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    int id = view.getId();
                    for (int i = 0; i < DJICameraColorView.a; i++) {
                        if (id == this.a.e[i]) {
                            this.a.a(this.a.d[i], false, true);
                            return;
                        }
                    }
                }
            };
            this.b = (DJITextView) findViewById(R.id.ij);
            for (int i = 0; i < a; i++) {
                this.c[i] = (DJIImageView) findViewById(this.e[i]);
                this.c[i].setOnClickListener(this.g);
            }
            a(this.f, true, false);
        }
    }
}
