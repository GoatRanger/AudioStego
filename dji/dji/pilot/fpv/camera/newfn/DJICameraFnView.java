package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.pilot.R;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;

public class DJICameraFnView extends DJIBaseTabStageView implements s {
    private static final int[] A = new int[]{R.layout.camera_newfn_photo_view, R.layout.camera_newfn_video_view, R.layout.camera_newfn_other_view};
    private static final int[] B = new int[]{R.id.j9, R.id.j_, R.id.ja};
    private static final int[] z = new int[]{R.id.jh, R.id.ji, R.id.jj};
    protected a y = null;

    public interface a {
        void a(boolean z);
    }

    public DJICameraFnView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(z, A, B, false);
    }

    public void setOnVisibilityChangeListener(a aVar) {
        this.y = aVar;
    }

    public void handleFnClick() {
        if (getVisibility() != 0) {
            e.c(s.dg);
            showView();
            return;
        }
        hideView();
    }

    public void showView() {
        MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
        int i = -1;
        if (mode == MODE.RECORD) {
            i = 1;
        } else if (mode == MODE.TAKEPHOTO) {
            i = 0;
        }
        a(i);
    }

    protected void a(boolean z) {
        if (this.y != null) {
            this.y.a(z);
        }
    }

    protected int getRequestedWidth() {
        return this.o.getResources().getDimensionPixelSize(R.dimen.i9);
    }
}
