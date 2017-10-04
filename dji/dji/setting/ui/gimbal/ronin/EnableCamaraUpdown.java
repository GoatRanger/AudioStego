package dji.setting.ui.gimbal.ronin;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.Switch;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalGetUserParams;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.e.d;
import dji.setting.ui.widget.ItemViewSwitch;
import java.lang.ref.WeakReference;

public class EnableCamaraUpdown extends ItemViewSwitch {
    private static final String a = "enable_camera_up_side_down";
    private a e;

    private class a extends Handler {
        public static final int a = 1;
        final /* synthetic */ EnableCamaraUpdown b;
        private WeakReference<EnableCamaraUpdown> c = null;

        public a(EnableCamaraUpdown enableCamaraUpdown, EnableCamaraUpdown enableCamaraUpdown2) {
            this.b = enableCamaraUpdown;
            this.c = new WeakReference(enableCamaraUpdown2);
        }

        public void handleMessage(Message message) {
            EnableCamaraUpdown enableCamaraUpdown = (EnableCamaraUpdown) this.c.get();
            if (enableCamaraUpdown != null) {
                switch (message.what) {
                    case 1:
                        boolean z;
                        enableCamaraUpdown.eS_.setOnCheckedChangeListener(null);
                        Switch c = enableCamaraUpdown.eS_;
                        if (e.read(EnableCamaraUpdown.a).value.intValue() == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                        c.setChecked(z);
                        enableCamaraUpdown.eS_.setOnCheckedChangeListener(this.b);
                        DJILogHelper.getInstance().LOGD("", "set DataGimbalGetUserParams true", false, true);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public EnableCamaraUpdown(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = null;
        this.e = new a(this, this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        DataGimbalGetUserParams.getInstance().setInfos(new String[]{a}).start(new d(this) {
            final /* synthetic */ EnableCamaraUpdown a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.e.sendEmptyMessage(1);
                DJILogHelper.getInstance().LOGD("", "DataGimbalGetUserParams true", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("", "DataGimbalGetUserParams failed", false, true);
            }
        });
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        DataGimbalSetUserParams.getInstance().a(a, Integer.valueOf(z ? 1 : 0)).start(new d(this) {
            final /* synthetic */ EnableCamaraUpdown a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "set DataGimbalSetUserParams success", false, true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.e.sendEmptyMessage(1);
            }
        });
    }
}
