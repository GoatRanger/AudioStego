package dji.pilot.set.view.gimbal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.e;
import dji.pilot.set.view.base.SetButtonView;

public class GimbalCompassCalibration extends SetButtonView {
    private static final int a = 0;
    private static final int b = 1;
    private static final int f = 2;
    private Handler g = new Handler(new Callback(this) {
        final /* synthetic */ GimbalCompassCalibration a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    e.b(this.a.getContext(), ((Integer) message.obj).intValue());
                    break;
                case 1:
                    this.a.setVisibility(true);
                    break;
                case 2:
                    this.a.setVisibility(false);
                    break;
            }
            return false;
        }
    });

    public GimbalCompassCalibration(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        e.a(getContext(), R.string.gimbal_compass_calibration, new OnClickListener(this) {
            final /* synthetic */ GimbalCompassCalibration a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.g.sendEmptyMessage(2);
                dialogInterface.dismiss();
                this.a.d();
            }
        });
    }

    protected int getTitleId() {
        return R.string.gimbal_compass_calibration_title;
    }

    protected int getButtonStringId() {
        return R.string.gimbal_compass_calibration_btn;
    }

    private void d() {
        DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.Calibration).start(new d(this) {
            final /* synthetic */ GimbalCompassCalibration a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.g.sendMessage(this.a.g.obtainMessage(0, Integer.valueOf(R.string.set_camera_resetting_success)));
                this.a.g.sendEmptyMessage(1);
            }

            public void onFailure(a aVar) {
                this.a.g.sendMessage(this.a.g.obtainMessage(0, Integer.valueOf(R.string.set_camera_resetting_fail)));
                this.a.g.sendEmptyMessage(1);
            }
        });
    }

    private void setVisibility(boolean z) {
        if (z) {
            this.d.setEnabled(true);
            this.d.setAlpha(1.0f);
            return;
        }
        this.d.setEnabled(false);
        this.d.setAlpha(0.3f);
    }
}
