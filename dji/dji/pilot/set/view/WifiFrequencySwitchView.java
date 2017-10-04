package dji.pilot.set.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataWifiGetWifiFrequency;
import dji.midware.data.model.P3.DataWifiRestart;
import dji.midware.data.model.P3.DataWifiSetWifiFrequency;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.e;
import dji.pilot.set.view.base.SetGroupButtonView;

public class WifiFrequencySwitchView extends SetGroupButtonView {
    private static final int a = 0;
    private static final int d = 1;
    private static final int e = 2;
    private Context f;
    private Handler g = new Handler(new Callback(this) {
        final /* synthetic */ WifiFrequencySwitchView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.setResultToToast((String) message.obj);
                    break;
                case 1:
                    this.a.setSelect(this.a.b);
                    break;
                case 2:
                    e.b(this.a.f, R.string.set_wifi_hint, new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            new DataWifiRestart().a(true).start(this.a.a.c);
                        }
                    });
                    break;
            }
            return false;
        }
    });

    private void setResultToToast(String str) {
        Toast.makeText(this.f, str, 0).show();
    }

    public WifiFrequencySwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context;
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        a();
    }

    protected void setValue(int i) {
        Log.v("onClick", i + "");
        new DataWifiSetWifiFrequency().a(false).a(i).start(new d(this) {
            final /* synthetic */ WifiFrequencySwitchView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.g.sendEmptyMessage(2);
            }

            public void onFailure(a aVar) {
                this.a.g.sendMessage(this.a.g.obtainMessage(0, aVar.name()));
            }
        });
    }

    protected void a() {
        final DataWifiGetWifiFrequency dataWifiGetWifiFrequency = new DataWifiGetWifiFrequency();
        dataWifiGetWifiFrequency.setFromLongan(false).start(new d(this) {
            final /* synthetic */ WifiFrequencySwitchView b;

            public void onSuccess(Object obj) {
                this.b.b = dataWifiGetWifiFrequency.getResult();
                this.b.g.sendEmptyMessage(1);
            }

            public void onFailure(a aVar) {
                this.b.g.sendMessage(this.b.g.obtainMessage(0, aVar.name()));
            }
        });
    }

    protected int getTitleId() {
        return R.string.set_wifi_freq_title;
    }

    protected int getLeftBtnStrId() {
        return R.string.set_wifi_2_4_G;
    }

    protected int getRightBtnStrId() {
        return R.string.set_wifi_5_0_G;
    }
}
