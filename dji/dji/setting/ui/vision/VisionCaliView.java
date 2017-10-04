package dji.setting.ui.vision;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.Data2100GetPushCheckStatus;
import dji.midware.data.model.P3.DataEyeGetPushFunctionList;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.fpv.model.n;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class VisionCaliView extends DividerLinearLayout implements OnClickListener {
    protected Button a;

    public VisionCaliView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_vision_cali);
        if (!isInEditMode()) {
            this.a = (Button) findViewById(R.id.setting_ui_item_btn);
            this.a.setOnClickListener(this);
        }
    }

    public void onClick(View view) {
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            Toast.makeText(getContext(), R.string.setting_ui_vision_selfcal_motorup_tip, 0).show();
        } else {
            dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_vision_selfcal_tip, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ VisionCaliView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ VisionCaliView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    c.a().e(n.a.g);
                }
            });
        }
    }

    public void onEventMainThread(Data2100GetPushCheckStatus data2100GetPushCheckStatus) {
        b();
    }

    public void onEventMainThread(DataEyeGetPushFunctionList dataEyeGetPushFunctionList) {
        b();
    }

    public void onEventMainThread(ProductType productType) {
        b();
    }

    private boolean a(Data2100GetPushCheckStatus data2100GetPushCheckStatus, DataEyeGetPushFunctionList dataEyeGetPushFunctionList) {
        return dataEyeGetPushFunctionList.supportSelfCal() && (data2100GetPushCheckStatus.isForeSightDemarkAbnormal() || data2100GetPushCheckStatus.isBackSightDemarkAbnormal() || data2100GetPushCheckStatus.isDownSightDemarkAbnormal());
    }

    private void b() {
        if (dji.pilot.publics.e.a.k(i.getInstance().c()) && a(Data2100GetPushCheckStatus.getInstance(), DataEyeGetPushFunctionList.getInstance())) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            onEventMainThread(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }
}
