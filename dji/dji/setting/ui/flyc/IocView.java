package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class IocView extends ItemViewSwitch {
    private boolean a = false;

    public IocView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
        if (DataOsdGetPushHome.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetPushHome.getInstance());
        }
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetPushCommon.getInstance());
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (this.a != z) {
            if (z) {
                e.a("FPV_MCSettings_Switcher_EnableIOC_ON");
            } else {
                e.a("FPV_MCSettings_Switcher_EnableIOC_OFF");
            }
            DataFlycFunctionControl.getInstance().setCommand(z ? FLYC_COMMAND.IOCOpen : FLYC_COMMAND.IOCClose).start(new d(this) {
                final /* synthetic */ IocView a;

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
                            this.a.a.a = this.a.a.eS_.isChecked();
                        }
                    });
                }

                public void onFailure(a aVar) {
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
            });
        }
    }

    private void a() {
        this.eS_.setChecked(this.a);
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        boolean isIOCEnabled = DataOsdGetPushHome.getInstance().isIOCEnabled();
        if (this.a != isIOCEnabled) {
            this.a = isIOCEnabled;
            a();
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if ((dataOsdGetPushCommon.getFlycVersion() & 255) >= 7) {
            this.eS_.setEnabled(false);
        } else {
            this.eS_.setEnabled(true);
        }
    }
}
