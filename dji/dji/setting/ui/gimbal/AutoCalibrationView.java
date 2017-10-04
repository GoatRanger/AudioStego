package dji.setting.ui.gimbal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.model.P3.DataGimbalGetPushType;
import dji.midware.data.model.P3.DataGimbalGetPushType.DJIGimbalType;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.e.a;
import dji.pilot.setting.ui.R;
import dji.setting.ui.SettingUIRootView;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.thirdparty.a.c;

public class AutoCalibrationView extends ItemViewButtonBig {
    public AutoCalibrationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
        a();
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        if (a.j() && DataGimbalGetPushType.getInstance().isGetted() && DataGimbalGetPushType.getInstance().getType() == DJIGimbalType.Ronin) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    public void onEventMainThread(DataGimbalGetPushType dataGimbalGetPushType) {
        a();
    }

    public void onClick(View view) {
        e.a("FPV_GeneralSettings_Camera_Button_GimbalAutoCalibration");
        dji.setting.ui.widget.a.b(getContext(), R.string.setting_ui_gimbal_auto_calibration_tip, new OnClickListener(this) {
            final /* synthetic */ AutoCalibrationView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                c.a().e(SettingUIRootView.a.CloseBtnClick);
                DJIGenSettingDataManager.getInstance().p();
            }
        });
    }
}
