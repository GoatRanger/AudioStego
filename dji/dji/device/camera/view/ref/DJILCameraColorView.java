package dji.device.camera.view.ref;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.device.camera.datamanager.DJICameraDataManagerCompat.b;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class DJILCameraColorView extends DJILinearLayout {
    private static final int a = (b.BLACK.b() + 1);
    private DJITextView b = null;
    private final DJIImageView[] c = new DJIImageView[a];
    private final b[] d = new b[]{b.WHITE, b.YELLOW, b.RED, b.BLUE, b.GREEN, b.BLACK};
    private final int[] e = new int[]{R.id.camera_color_white_img, R.id.camera_color_yellow_img, R.id.camera_color_red_img, R.id.camera_color_blue_img, R.id.camera_color_green_img, R.id.camera_color_black_img};
    private b f = b.WHITE;
    private OnClickListener g = null;
    private a h = null;
    private DJIImageView i = null;

    public interface a {
        void a(b bVar, int i);
    }

    public DJILCameraColorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setOnColorListener(a aVar) {
        this.h = aVar;
    }

    public void setDescResId(int i) {
        this.b.setText(i);
    }

    public void setColor(b bVar) {
        a(bVar, false, false);
    }

    private void a(b bVar, boolean z, boolean z2) {
        if (z || bVar != this.f) {
            this.f = bVar;
            for (int i = 0; i < a; i++) {
                if (bVar == this.d[i]) {
                    a(i);
                    if (z2 && this.h != null) {
                        this.h.a(bVar, 0);
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
                final /* synthetic */ DJILCameraColorView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    int id = view.getId();
                    for (int i = 0; i < DJILCameraColorView.a; i++) {
                        if (id == this.a.e[i]) {
                            this.a.a(this.a.d[i], false, true);
                            return;
                        }
                    }
                }
            };
            this.b = (DJITextView) findViewById(R.id.camera_color_desc_tv);
            for (int i = 0; i < a; i++) {
                this.c[i] = (DJIImageView) findViewById(this.e[i]);
                this.c[i].setOnClickListener(this.g);
            }
            a(this.f, true, false);
        }
    }
}
