package dji.pilot.set.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.model.P3.DataOsdOsmoCalibration;
import dji.midware.data.model.P3.DataOsdOsmoPushCalibration;
import dji.pilot.set.R;
import dji.pilot.set.e;
import dji.pilot.set.view.base.SetButtonView;

public class GimbalCompassCalibrationView extends SetButtonView {
    public GimbalCompassCalibrationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getTitleId() {
        return R.string.set_gimbal_compass_calibration;
    }

    protected int getButtonStringId() {
        return R.string.set_gimbal_compass_calibration_btn;
    }

    public void onClick(View view) {
        if (DataOsdOsmoPushCalibration.getInstance().b() != 1) {
            e.a(getContext(), R.string.set_gimbal_compass_calibration_tip, new OnClickListener(this) {
                final /* synthetic */ GimbalCompassCalibrationView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.c();
                    DataOsdOsmoCalibration.getInstance().start(null);
                    dialogInterface.dismiss();
                }
            });
        }
    }

    public void onEventMainThread(DataOsdOsmoPushCalibration dataOsdOsmoPushCalibration) {
        int a = dataOsdOsmoPushCalibration.a();
        if (a != 1) {
            b();
        }
        if (a == 2) {
            e.c(getContext(), R.string.set_gimbal_compass_calibration_success);
        } else if (a == 3) {
            e.c(getContext(), R.string.set_gimbal_compass_calibration_fails);
        }
    }
}
