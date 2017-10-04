package dji.device.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.midware.data.model.P3.DataOsdGetPushPowerStatus;
import dji.pilot.fpv.R;
import dji.pilot.set.e;
import dji.thirdparty.a.c;

public class LonganWarningContainerView extends RelativeLayout {
    LonganPopWarnView a;
    private boolean b = false;

    public enum a {
        CANNOT_SLEEP
    }

    public LonganWarningContainerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.a = LonganPopWarnView.getInstance(getContext());
        this.a.setVisibility(4);
        if (this.a.getParent() != null) {
            ((ViewGroup) this.a.getParent()).removeView(this.a);
        }
        addView(this.a);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        if (this.a != null) {
            ((LayoutParams) this.a.getLayoutParams()).addRule(13);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(DataOsdGetPushPowerStatus dataOsdGetPushPowerStatus) {
        if (dataOsdGetPushPowerStatus.getPowerStatus() == 2 && !this.b) {
            e.b(getContext(), R.string.longan_unsupport_sleepmode_tip, new OnClickListener(this) {
                final /* synthetic */ LonganWarningContainerView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    this.a.b = false;
                }
            });
            this.b = true;
        }
    }
}
