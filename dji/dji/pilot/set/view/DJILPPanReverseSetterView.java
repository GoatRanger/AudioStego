package dji.pilot.set.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataGimbalGetHandleParams;
import dji.midware.data.model.P3.DataGimbalSetHandleParams;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetSwitchView;

public class DJILPPanReverseSetterView extends SetSwitchView {
    public static final String a = ".";
    private static final long g = 1300099;
    private static final int h = 0;
    private DataCommonGetVersion i;
    private DataGimbalGetHandleParams j;
    private Handler k;

    public DJILPPanReverseSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.i = null;
        this.k = new Handler(new Callback(this) {
            final /* synthetic */ DJILPPanReverseSetterView a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 0:
                        this.a.setValueView(this.a.d);
                        break;
                }
                return false;
            }
        });
        this.f = true;
    }

    private void b() {
        this.j = new DataGimbalGetHandleParams();
        this.j.start(new d(this) {
            final /* synthetic */ DJILPPanReverseSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                boolean z = true;
                DJILPPanReverseSetterView dJILPPanReverseSetterView = this.a;
                if (this.a.j.getPanDirection() != 1) {
                    z = false;
                }
                dJILPPanReverseSetterView.d = z;
                this.a.k.sendEmptyMessage(0);
            }

            public void onFailure(a aVar) {
                this.a.k.sendEmptyMessage(0);
            }
        });
    }

    protected void a() {
        this.i = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL);
        this.i.start(new d(this) {
            final /* synthetic */ DJILPPanReverseSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                long parseLong = Long.parseLong(this.a.i.getFirmVer(".").replace(".", ""));
                if (parseLong != 0 && parseLong >= DJILPPanReverseSetterView.g) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.setVisibility(0);
                            this.a.a.b();
                        }
                    });
                }
            }

            public void onFailure(a aVar) {
            }
        }, 1000, 3);
    }

    protected void setValue(boolean z) {
        int i;
        DataGimbalSetHandleParams dataGimbalSetHandleParams = new DataGimbalSetHandleParams();
        if (z) {
            i = 1;
        } else {
            i = 0;
        }
        dataGimbalSetHandleParams.c(i).start(new d(this) {
            final /* synthetic */ DJILPPanReverseSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.b();
            }

            public void onFailure(a aVar) {
                this.a.b();
            }
        });
    }

    public void onEventMainThread(ResetGimbalSettingView resetGimbalSettingView) {
        a();
    }

    protected int getTitleId() {
        return R.string.set_gimbal_pan_reverse;
    }
}
