package dji.pilot.fpv.camera.osd;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.logic.c.b;
import dji.midware.data.manager.P3.o;
import dji.pilot.R;
import dji.pilot.fpv.rightbar.DJISwitchModeView.a;
import dji.pilot.newfpv.f.k;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.thirdparty.a.c;

public class DJISwitchFocusKumquatView extends DJIStateImageView {
    private boolean a = false;

    public DJISwitchFocusKumquatView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJISwitchFocusKumquatView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a();
                }
            });
        }
    }

    private void a() {
        a("startFocus mIsStartFocus=" + this.a);
        if (!this.a) {
            this.a = true;
            c.a().e(k.CAMERA_START_FOCUS);
            setBackgroundResource(R.drawable.v2_circle_press);
        }
    }

    public boolean isEnableFocus() {
        return this.a;
    }

    public void onEventBackgroundThread(o oVar) {
        if (oVar == o.b && b.getInstance().a(null)) {
            show();
            this.a = false;
            return;
        }
        go();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        if (b.getInstance().a(null)) {
            show();
            c.a().e(a.FOCUS);
            return;
        }
        go();
    }

    protected void onDetachedFromWindow() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    private void a(String str) {
    }
}
