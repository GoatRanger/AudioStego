package dji.pilot.set.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetVideoCaption;
import dji.midware.data.model.P3.DataCameraSetVideoCaption;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetSwitchView;

public class VideoCaptionSetterView extends SetSwitchView {
    private static final int a = 0;
    private DataCameraGetVideoCaption g;
    private Handler h = new Handler(new Callback(this) {
        final /* synthetic */ VideoCaptionSetterView a;

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

    public VideoCaptionSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void b() {
        this.g = new DataCameraGetVideoCaption();
        this.g.start(new d(this) {
            final /* synthetic */ VideoCaptionSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.d = this.a.g.isGenerateVideoCaptionEnable();
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
        DataCameraSetVideoCaption.getInstance().a().a(z).start(new d(this) {
            final /* synthetic */ VideoCaptionSetterView a;

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
        return R.string.set_video_caption;
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
    }
}
