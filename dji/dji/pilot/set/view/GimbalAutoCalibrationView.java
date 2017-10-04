package dji.pilot.set.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.here.services.location.network.NetworkLocationApi.Options;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataGimbalAutoCalibration;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.midware.data.model.P3.DataGimbalGetPushAutoCalibrationStatus;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataGimbalRollFinetune;
import dji.pilot.set.R;
import dji.pilot.set.e;
import dji.pilot.set.view.base.SetButtonView;
import dji.thirdparty.a.c;

public class GimbalAutoCalibrationView extends SetButtonView {
    private static final int f = 0;
    private static final int g = 1;
    private static final int h = 2;
    private static final int i = 20000;
    ProgressDialog a = new ProgressDialog(getContext());
    boolean b = false;
    private Handler j = new Handler(new Callback(this) {
        final /* synthetic */ GimbalAutoCalibrationView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    e.a(this.a.getContext(), R.string.fpv_gensetting_format_sdcard_success);
                    this.a.b();
                    break;
                case 1:
                    e.a(this.a.getContext(), R.string.fpv_gensetting_format_sdcard_fail);
                    this.a.b();
                    break;
                case 2:
                    this.a.a.dismiss();
                    e.c(this.a.getContext(), R.string.set_gimbal_motor_calibration_failed);
                    break;
            }
            return false;
        }
    });

    public GimbalAutoCalibrationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a.setProgressStyle(1);
        this.a.setProgressPercentFormat(null);
        this.a.setProgressDrawable(getResources().getDrawable(R.drawable.longan_progress));
        this.a.setMessage(getResources().getString(R.string.set_gimbal_auto_calibration_tip));
        this.a.setCancelable(false);
        this.a.setMax(100);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected int getTitleId() {
        return R.string.set_gimbal_motor_calibration;
    }

    protected int getButtonStringId() {
        return R.string.set_gimbal_motor_calibration_btn;
    }

    public void onClick(View view) {
        if (!ServiceManager.getInstance().isConnected()) {
            Toast.makeText(getContext(), R.string.Bluetooth_disconnected, 1).show();
        } else if (DataGimbalGetPushAbnormalStatus.getInstance().getSleepMode() == 1) {
            Toast.makeText(getContext(), R.string.lp_setting_gimbal_sleep_warning, 1).show();
        } else if (DataGimbalGetPushAbnormalStatus.getInstance().isMotorProtected()) {
            Toast.makeText(getContext(), R.string.lp_setting_device_protected_notify, 1).show();
        } else if (DataGimbalGetPushAbnormalStatus.getInstance().isPhoneOutGimbal()) {
            Toast.makeText(getContext(), R.string.lp_setting_device_phone_out_notify, 1).show();
        } else {
            e.a(getContext(), R.string.set_gimbal_motor_calibration_tip, new OnClickListener(this) {
                final /* synthetic */ GimbalAutoCalibrationView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.d();
                    dialogInterface.dismiss();
                    this.a.a.show();
                    this.a.a.setProgress(0);
                    this.a.j.sendEmptyMessageDelayed(2, Options.MIN_DESIRED_INTERVAL);
                }
            });
        }
    }

    private void d() {
        DataGimbalRollFinetune.getInstance().setFineTuneValue((byte) (-DataGimbalGetPushParams.getInstance().getRollAdjust())).start(null);
        DataGimbalAutoCalibration.getInstance().start(null);
    }

    public void onEventMainThread(DataGimbalGetPushAutoCalibrationStatus dataGimbalGetPushAutoCalibrationStatus) {
        if (this.j.hasMessages(2)) {
            this.j.removeMessages(2);
        }
        int progress = dataGimbalGetPushAutoCalibrationStatus.getProgress();
        int status = dataGimbalGetPushAutoCalibrationStatus.getStatus();
        if (status == 1) {
            if (progress <= 100) {
                this.a.setProgress(dataGimbalGetPushAutoCalibrationStatus.getProgress());
            }
            this.j.sendEmptyMessageDelayed(2, Options.MIN_DESIRED_INTERVAL);
            this.b = false;
        } else if (status == 0) {
            if (!this.b) {
                this.b = true;
                this.a.dismiss();
                e.c(getContext(), R.string.set_gimbal_motor_calibration_success);
            }
            Log.d("AutoCalibration", "success progress:" + dataGimbalGetPushAutoCalibrationStatus.getProgress() + "status:" + dataGimbalGetPushAutoCalibrationStatus.getStatus());
        } else {
            Log.d("AutoCalibration", "failed progress:" + dataGimbalGetPushAutoCalibrationStatus.getProgress() + "status:" + dataGimbalGetPushAutoCalibrationStatus.getStatus());
            this.a.dismiss();
            e.c(getContext(), R.string.set_gimbal_motor_calibration_failed);
        }
        Log.d("AutoCalibration", "progress:" + dataGimbalGetPushAutoCalibrationStatus.getProgress() + "status:" + dataGimbalGetPushAutoCalibrationStatus.getStatus());
    }
}
