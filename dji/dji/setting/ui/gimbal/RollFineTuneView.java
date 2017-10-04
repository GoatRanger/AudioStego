package dji.setting.ui.gimbal;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.e.d;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.e;
import dji.setting.ui.SettingUIRootView.a;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.thirdparty.a.c;

public class RollFineTuneView extends ItemViewButtonBig {
    public RollFineTuneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        e.a("FPV_GeneralSettings_Gimbal_Button_AdjustGimbalRoll");
        DJIGenSettingDataManager.getInstance().d(true);
        c.a().e(a.CloseBtnClick);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        DataRcGetMaster.getInstance().start(new d(this) {
            final /* synthetic */ RollFineTuneView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void a() {
        if (dji.setting.ui.hd.a.a()) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
        if (dji.pilot.publics.e.a.d(null)) {
            setVisibility(0);
        }
    }
}
