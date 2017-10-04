package dji.pilot.set.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetAudio;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraSetAudio;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.g;
import dji.pilot.set.view.base.SetSwitchView;
import dji.thirdparty.a.c;

public class VideoSoundSetterView extends SetSwitchView {
    private static final int a = 0;
    private DataCameraGetAudio g;
    private Handler h = new Handler(new Callback(this) {
        final /* synthetic */ VideoSoundSetterView a;

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

    public VideoSoundSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void b() {
        this.g = new DataCameraGetAudio();
        this.g.start(new d(this) {
            final /* synthetic */ VideoSoundSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.d = this.a.g.isEnable();
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
        new DataCameraSetAudio().a(z).start(new d(this) {
            final /* synthetic */ VideoSoundSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.b();
                c.a().e(new dji.pilot.set.a.a(g.j));
            }

            public void onFailure(a aVar) {
                this.a.b();
            }
        });
    }

    protected int getTitleId() {
        return R.string.set_video_sound;
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
    }
}
