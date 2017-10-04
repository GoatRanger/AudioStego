package dji.pilot.set.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdOsmoCalibration;
import dji.midware.data.model.P3.DataOsdOsmoPushCalibration;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.e;
import dji.pilot.set.view.base.SetButtonView;
import dji.thirdparty.a.c;

public class GimbalMotorCalibrationView extends SetButtonView {
    private boolean a = false;

    public GimbalMotorCalibrationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getTitleId() {
        return R.string.set_gimbal_motor_calibration;
    }

    protected int getButtonStringId() {
        return R.string.set_gimbal_motor_calibration_btn;
    }

    public void onClick(View view) {
        if (DataOsdOsmoPushCalibration.getInstance().a() != 1) {
            e.a(getContext(), R.string.set_gimbal_motor_calibration_tip, new OnClickListener(this) {
                final /* synthetic */ GimbalMotorCalibrationView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.c();
                    DataOsdOsmoCalibration instance = DataOsdOsmoCalibration.getInstance();
                    instance.a(1);
                    instance.start(new d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(a aVar) {
                        }
                    });
                    dialogInterface.dismiss();
                    this.a.a = true;
                }
            });
        }
    }

    public void onEventMainThread(DataOsdOsmoPushCalibration dataOsdOsmoPushCalibration) {
        Toast.makeText(getContext(), "motor status " + dataOsdOsmoPushCalibration.a() + "; compass status " + dataOsdOsmoPushCalibration.b(), 0).show();
        int a = dataOsdOsmoPushCalibration.a();
        if (a != 1) {
            b();
        }
        if (a == 2) {
            e.c(getContext(), R.string.set_gimbal_motor_calibration_success);
        } else if (a == 3) {
            e.c(getContext(), R.string.set_gimbal_motor_calibration_fails);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }
}
