package dji.device.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.midware.data.model.P3.DataOsdSetPower;
import dji.midware.data.model.P3.DataOsdSetPower.POWER_TYPE;
import dji.pilot.fpv.R;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJILinearLayout;
import dji.thirdparty.a.c;

public class DJILWarningView extends DJILinearLayout {
    TextView a;
    TextView b;
    TextView c;
    LayoutParams d;
    int e;
    boolean f = false;
    private SDCardState g = null;

    public enum a {
        PROTECTED,
        NEED_REBOOT
    }

    public DJILWarningView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        b();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        resetView();
    }

    private void b() {
        this.d = (LayoutParams) getLayoutParams();
        this.a = (TextView) findViewById(R.id.fpv_top_sdstatus_tv);
        this.b = (TextView) findViewById(R.id.fpv_top_gimbalstatus_tv);
        this.c = (TextView) findViewById(R.id.fpv_top_gimbal_protected_tv);
        this.e = (int) ((((float) ((int) getResources().getDimension(R.dimen.longan_sdcard_status_y_offset))) * getContext().getResources().getDisplayMetrics().density) + d.c);
        resetView();
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventMainThread(DataCameraGetPushStateInfo.getInstance());
            onEventMainThread(DataGimbalGetPushAbnormalStatus.getInstance());
        }
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJILWarningView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.f) {
                    DataOsdSetPower.getInstance().a(POWER_TYPE.RESET).start(null);
                }
            }
        });
    }

    public void resetView() {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.addRule(5, R.id.longan_top_bar_layout);
        if (getResources().getConfiguration().orientation == 2) {
            layoutParams.addRule(10, 0);
            layoutParams.addRule(3, R.id.longan_top_bar_layout);
            layoutParams.topMargin = getResources().getDimensionPixelOffset(R.dimen.dp_5_in_sw320dp);
            layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.dp_5_in_sw320dp);
            return;
        }
        layoutParams.addRule(10);
        layoutParams.topMargin = ((int) (((float) (DJIPreviewActivityLongan.mScreenHeight - dji.device.common.a.a.a(getContext()))) / 2.0f)) + getResources().getDimensionPixelOffset(R.dimen.dp_10_in_sw320dp);
        layoutParams.leftMargin = getResources().getDimensionPixelOffset(R.dimen.dp_5_in_sw320dp);
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        if (dataCameraGetPushStateInfo.getSDCardState() != this.g) {
            this.g = dataCameraGetPushStateInfo.getSDCardState();
            a();
        }
    }

    protected void a() {
        if (this.g != null) {
            if (this.g == SDCardState.None) {
                this.a.setText(R.string.sdcardstatus_removal);
                this.a.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_no, 0, 0, 0);
            } else if (this.g == SDCardState.Full) {
                this.a.setText(R.string.sdcardstatus_full);
                this.a.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_full, 0, 0, 0);
            } else if (this.g != SDCardState.Normal) {
                if (this.g == SDCardState.BecomeSlow) {
                    this.a.setText(R.string.sdcardstatus_becoming_slow);
                    this.a.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_slow, 0, 0, 0);
                } else {
                    this.a.setText(dji.device.common.a.a.b(this.g));
                    this.a.setCompoundDrawablesWithIntrinsicBounds(R.drawable.osd_sdcard_slow, 0, 0, 0);
                }
            }
            if (this.g != SDCardState.Normal) {
                this.a.setVisibility(0);
            } else {
                this.a.setVisibility(8);
            }
        }
    }

    public void onEventMainThread(m mVar) {
        switch (mVar) {
            case INFO_BAR_SHOWEN:
                d();
                return;
            case INFO_BAR_HIDDEN:
                c();
                return;
            case HIDE_ALL:
                c();
                return;
            case SHOW_ALL:
                d();
                return;
            default:
                return;
        }
    }

    private void c() {
        hide();
    }

    private void d() {
        if (!DJIPreviewActivityLongan.isPopViewShown()) {
            show();
        }
    }

    public void onEventMainThread(DataGimbalGetPushAbnormalStatus dataGimbalGetPushAbnormalStatus) {
        if (dataGimbalGetPushAbnormalStatus.isGimbalLocked()) {
            this.b.setVisibility(0);
            this.b.setText(R.string.gimbal_limit_warning);
            this.b.setCompoundDrawablesWithIntrinsicBounds(R.drawable.longan_gimbal_lock, 0, 0, 0);
            this.f = false;
        } else if (dataGimbalGetPushAbnormalStatus.isJointLockAfterStartup() || dataGimbalGetPushAbnormalStatus.isMotorProtected()) {
            if (dataGimbalGetPushAbnormalStatus.isMotorProtected() && i.getInstance().c() == ProductType.LonganZoom) {
                this.c.setVisibility(0);
                this.c.setText(getResources().getString(R.string.longan_gimbal_protected_tip));
            }
            if (dataGimbalGetPushAbnormalStatus.getFanDirection() == 0) {
                this.f = true;
                this.b.setText(getResources().getString(R.string.longan_gimbal_reboot_tip));
            } else if (dataGimbalGetPushAbnormalStatus.getFanDirection() == 1) {
                this.b.setText(getResources().getString(R.string.longan_gimbal_reboot_tip_unreboot));
            }
            this.b.setVisibility(0);
            this.b.setCompoundDrawablesWithIntrinsicBounds(R.drawable.longan_gimbal_lock, 0, 0, 0);
        } else {
            this.b.setVisibility(8);
            this.c.setVisibility(8);
            this.f = false;
        }
    }
}
