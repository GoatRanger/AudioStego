package dji.pilot.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.pilot.set.R;
import dji.pilot.set.f;
import dji.pilot.set.longan.SetActivity.a;
import dji.pilot.set.view.base.SetButtonView;
import dji.thirdparty.a.c;

public class GimbalHorizonTuneView extends SetButtonView implements OnClickListener {
    public GimbalHorizonTuneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public GimbalHorizonTuneView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onClick(View view) {
        if (DataGimbalGetPushAbnormalStatus.getInstance().getSleepMode() == 1) {
            Toast.makeText(getContext(), R.string.lp_setting_gimbal_sleep_warning, 1).show();
        } else if (DataGimbalGetPushAbnormalStatus.getInstance().isMotorProtected()) {
            Toast.makeText(getContext(), R.string.lp_setting_device_protected_notify, 1).show();
        } else if (DataGimbalGetPushAbnormalStatus.getInstance().isPhoneOutGimbal()) {
            Toast.makeText(getContext(), R.string.lp_setting_device_phone_out_notify, 1).show();
        } else {
            if (i.getInstance().c() == ProductType.LonganMobile) {
                f.a("VIEW_GIMBAL_ROLL_TUNE");
            } else {
                f.a("SHOW_GIMBAL_ROLL_TUNE_VIEW");
            }
            c.a().e(a.CLOSE);
        }
    }

    protected int getTitleId() {
        return R.string.set_gimbal_horizon_tune;
    }

    protected int getButtonStringId() {
        return R.string.set_gimbal_motor_calibration_btn;
    }
}
