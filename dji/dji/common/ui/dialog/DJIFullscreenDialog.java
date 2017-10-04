package dji.common.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.dji.frame.c.c;
import dji.f.a;
import dji.phone.h.b;
import dji.pilot.fpv.R;

public class DJIFullscreenDialog extends AlertDialog {
    private static final String TAG = "DJIFullscreenDialog";
    LayoutParams lp;
    RelativeLayout mRootLy;
    boolean needHandleRotation = true;
    int oriHeight;
    int oriWidth;

    public DJIFullscreenDialog(Context context, int i) {
        super(context, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(8, 8);
        c.a(getWindow());
        a.a(this);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void show() {
        super.show();
        getWindow().clearFlags(8);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.mRootLy = (RelativeLayout) getWindow().getDecorView().getRootView().findViewById(R.id.lp_custom_dlg_ly);
        if (this.mRootLy != null) {
            this.lp = (LayoutParams) this.mRootLy.getLayoutParams();
        }
        onEventMainThread(dji.phone.preview.a.d);
    }

    public void setAutoHandleRotationEnable(boolean z) {
        this.needHandleRotation = z;
    }

    public void resize(b bVar) {
        if (this.mRootLy != null) {
            this.oriHeight = this.mRootLy.getMeasuredHeight();
            this.oriWidth = this.mRootLy.getMeasuredWidth();
        }
        if (this.lp != null && this.needHandleRotation) {
            int b = bVar.b();
            if (b == 0 || b == 180) {
                this.lp.height = this.oriHeight != 0 ? this.oriHeight : getContext().getResources().getDimensionPixelOffset(R.dimen.dp_300_in_sw320dp);
                this.lp.width = this.oriWidth != 0 ? this.oriWidth : getContext().getResources().getDimensionPixelOffset(R.dimen.dp_300_in_sw320dp);
            } else {
                this.lp.height = this.oriWidth != 0 ? this.oriWidth : getContext().getResources().getDimensionPixelOffset(R.dimen.dp_300_in_sw320dp);
                this.lp.width = this.oriWidth != 0 ? this.oriWidth : getContext().getResources().getDimensionPixelOffset(R.dimen.dp_300_in_sw320dp);
            }
            this.mRootLy.setLayoutParams(this.lp);
        }
    }

    public void dismiss() {
        super.dismiss();
        a.b(this);
    }

    public void onEventMainThread(b bVar) {
        if (this.needHandleRotation) {
            dji.phone.h.a.a(this.mRootLy, this.mRootLy.getRotation(), (float) bVar.b());
            resize(bVar);
        }
    }
}
