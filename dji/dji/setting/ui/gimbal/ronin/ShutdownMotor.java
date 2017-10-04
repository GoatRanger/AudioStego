package dji.setting.ui.gimbal.ronin;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.Switch;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalGetUserParams;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.e.d;
import dji.setting.ui.widget.ItemViewSwitch;
import java.lang.ref.WeakReference;

public class ShutdownMotor extends ItemViewSwitch {
    private static final String a = "shut_down_motor";
    private a e;

    private class a extends Handler {
        public static final int a = 1;
        final /* synthetic */ ShutdownMotor b;
        private WeakReference<ShutdownMotor> c = null;

        public a(ShutdownMotor shutdownMotor, ShutdownMotor shutdownMotor2) {
            this.b = shutdownMotor;
            this.c = new WeakReference(shutdownMotor2);
        }

        public void handleMessage(Message message) {
            boolean z = true;
            ShutdownMotor shutdownMotor = (ShutdownMotor) this.c.get();
            if (shutdownMotor != null) {
                switch (message.what) {
                    case 1:
                        shutdownMotor.eS_.setOnCheckedChangeListener(null);
                        Switch c = shutdownMotor.eS_;
                        if (e.read(ShutdownMotor.a).value.intValue() != 1) {
                            z = false;
                        }
                        c.setChecked(z);
                        shutdownMotor.eS_.setOnCheckedChangeListener(this.b);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public ShutdownMotor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = null;
        this.e = new a(this, this);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        DataGimbalGetUserParams.getInstance().setInfos(new String[]{a}).start(new d(this) {
            final /* synthetic */ ShutdownMotor a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.e.sendEmptyMessage(1);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        DataGimbalSetUserParams.getInstance().a(a, Integer.valueOf(z ? 1 : 0)).start(new d(this) {
            final /* synthetic */ ShutdownMotor a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.e.sendEmptyMessage(1);
            }
        });
    }
}
