package dji.pilot.set.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetRecordFan;
import dji.midware.data.model.P3.DataCameraSetRecordFan;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetSwitchView;

public class VideoFanSetterView extends SetSwitchView {
    private static final int a = 0;
    private DataCameraGetRecordFan g;
    private Handler h = new Handler(new Callback(this) {
        final /* synthetic */ VideoFanSetterView a;

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

    public VideoFanSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void b() {
        this.g = new DataCameraGetRecordFan();
        this.g.start(new d(this) {
            final /* synthetic */ VideoFanSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.d = this.a.g.isForceTurnOffFan();
                this.a.h.sendEmptyMessage(0);
            }

            public void onFailure(a aVar) {
                this.a.d = false;
                this.a.h.sendEmptyMessage(0);
            }
        });
    }

    protected void a() {
        b();
    }

    protected void setValue(boolean z) {
        DataCameraSetRecordFan.getInstance().a().a(z).start(new d(this) {
            final /* synthetic */ VideoFanSetterView a;

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

    protected int getTitleId() {
        return R.string.set_video_fan_switch;
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
    }
}
