package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.params.P3.a;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class ForearmLampView extends ItemViewSwitch implements a {
    private static final String a = "g_config.misc_cfg.forearm_lamp_ctrl_0";

    public ForearmLampView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int i = 1;
        if ((d.read("g_config.misc_cfg.forearm_lamp_ctrl_0").value.intValue() == 1) != z) {
            DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
            String str = "g_config.misc_cfg.forearm_lamp_ctrl_0";
            if (!z) {
                i = 0;
            }
            dataFlycSetParams.a(str, Integer.valueOf(i)).start(new dji.midware.e.d(this) {
                final /* synthetic */ ForearmLampView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
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

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
            getForearmData();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void getForearmData() {
        new DataFlycGetParams().setInfos(new String[]{"g_config.misc_cfg.forearm_lamp_ctrl_0"}).start(new dji.midware.e.d(this) {
            final /* synthetic */ ForearmLampView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

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

    private void a() {
        boolean z = true;
        if (!dji.pilot.publics.e.a.a(i.getInstance().c(), DataOsdGetPushCommon.getInstance().getFlycVersion()) || dji.pilot.publics.e.a.c(i.getInstance().c())) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        if (d.read("g_config.misc_cfg.forearm_lamp_ctrl_0").value.intValue() != 1) {
            z = false;
        }
        this.eS_.setChecked(z);
    }
}
