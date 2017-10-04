package dji.setting.ui.gimbal;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.widget.RadioGroup;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataGimbalControl.MODE;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.pilot.publics.e.a;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewRadio;
import dji.thirdparty.a.c;

public class ModeView extends ItemViewRadio {
    SparseArray<MODE> a = new SparseArray();

    public ModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            if (i.getInstance().b() == CameraType.DJICameraTypeGD600) {
                this.a.put(this.g.getId(), MODE.YawNoFollow);
                this.g.setText(R.string.setting_ui_gimbal_mode_notfollow);
                this.a.put(this.h.getId(), MODE.YawFollow);
                this.h.setText(R.string.setting_ui_gimbal_mode_follow);
                return;
            }
            this.a.put(this.g.getId(), MODE.FPV);
            this.g.setText(R.string.setting_ui_gimbal_mode_fpv);
            this.a.put(this.h.getId(), MODE.YawFollow);
            this.h.setText(R.string.setting_ui_gimbal_mode_follow);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (getVisibility() == 0) {
            a();
        }
    }

    private void a() {
        if (a.b(i.getInstance().c()) || i.getInstance().b() == CameraType.DJICameraTypeGD600) {
            setVisibility(0);
            MODE mode = DataGimbalGetPushParams.getInstance().getMode();
            DJILogHelper.getInstance().LOGD("", "Gimbal-" + mode, false, true);
            if (mode == MODE.FPV || mode == MODE.YawNoFollow) {
                this.g.setChecked(true);
                return;
            } else {
                this.h.setChecked(true);
                return;
            }
        }
        setVisibility(8);
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        MODE mode = (MODE) this.a.get(i);
        if (mode != DataGimbalGetPushParams.getInstance().getMode()) {
            DataSpecialControl.getInstance().setGimbalMode(mode).start(20);
        }
    }
}
