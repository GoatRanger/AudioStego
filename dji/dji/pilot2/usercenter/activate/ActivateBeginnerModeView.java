package dji.pilot2.usercenter.activate;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import dji.common.error.DJIError;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.widget.DJISwitch;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.h;

public class ActivateBeginnerModeView extends ActivateBaseView {
    private static final int e = 1;
    private static final int f = 2;
    private DJISwitch c;
    private ImageView d;

    private class a extends Handler {
        final /* synthetic */ ActivateBeginnerModeView a;

        private a(ActivateBeginnerModeView activateBeginnerModeView) {
            this.a = activateBeginnerModeView;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    this.a.a.a();
                    return;
                case 2:
                    this.a.a.b((String) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    public ActivateBeginnerModeView(Context context) {
        super(context);
    }

    public ActivateBeginnerModeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateBeginnerModeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean onShow() {
        a();
        DJISDKCache.getInstance().setValue(b.f(e.J), Boolean.valueOf(true), new h(this) {
            final /* synthetic */ ActivateBeginnerModeView a;

            {
                this.a = r1;
            }

            public void a() {
                DJISDKCache.getInstance().setValue(b.f(e.I), new Float(480.0f), new h(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void a() {
                    }

                    public void a(DJIError dJIError) {
                    }
                });
            }

            public void a(DJIError dJIError) {
            }
        });
        return true;
    }

    private void a() {
        if (this.d == null) {
            return;
        }
        if (DJIGenSettingDataManager.getInstance().v() == 0) {
            this.d.setImageResource(R.drawable.activate_flight_range_beginner_mode_ft);
        } else {
            this.d.setImageResource(R.drawable.activate_flight_range_beginner_mode);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = new a();
        this.c = (DJISwitch) findViewById(R.id.cn);
        this.d = (ImageView) findViewById(R.id.co);
    }

    public void setData() {
        b();
    }

    private void b() {
        int i;
        boolean isChecked = this.c.isChecked();
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        String str = dji.midware.data.params.P3.a.B;
        if (isChecked) {
            i = 1;
        } else {
            i = 0;
        }
        dataFlycSetParams.a(str, Integer.valueOf(i)).start(new d(this) {
            final /* synthetic */ ActivateBeginnerModeView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                c.a("Beginner mode set success");
                this.a.b.sendEmptyMessage(1);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                c.a("Beginner mode set fail");
                Message obtainMessage = this.a.b.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.obj = aVar.a() + "";
                this.a.b.sendMessage(obtainMessage);
            }
        });
    }
}
