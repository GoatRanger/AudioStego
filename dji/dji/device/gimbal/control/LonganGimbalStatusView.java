package dji.device.gimbal.control;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.gimbal.control.d.a;
import dji.log.DJILogHelper;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganGimbalStatusView extends ImageView {
    public LonganGimbalStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        d.getInstance().a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
        d.getInstance().b();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    private void a() {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen.longan_gimbal_status_size);
        layoutParams.width = dimensionPixelOffset;
        layoutParams.height = dimensionPixelOffset;
        if (getResources().getConfiguration().orientation == 2) {
            layoutParams.addRule(3, 0);
            layoutParams.addRule(0, R.id.longan_preview_setting_bar);
            layoutParams.setMargins(0, 0, 0, DJIPreviewActivityLongan.mScreenHeight - dimensionPixelOffset);
        } else {
            layoutParams.addRule(0, 0);
            layoutParams.addRule(3, R.id.longan_preview_setting_bar);
            layoutParams.setMargins(0, 0, DJIPreviewActivityLongan.mScreenWidth - dimensionPixelOffset, 0);
        }
        DJILogHelper.getInstance().LOGD("", "" + layoutParams.bottomMargin);
    }

    public void onEventMainThread(d dVar) {
        setVisibility(0);
        a c = d.getInstance().c();
        if (c == a.PORTRAIN) {
            setImageResource(R.drawable.longan_gimbal_portrait_corner);
        } else if (c == a.FLASHLIGHT) {
            setImageResource(R.drawable.longan_gimbal_flashlight_corner);
        } else if (c == a.UPSIGHTDOWN) {
            setImageResource(R.drawable.longan_gimbal_underslung_corner);
        } else {
            setVisibility(8);
        }
    }
}
