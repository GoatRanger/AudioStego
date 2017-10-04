package dji.pilot.set.view;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.Toast;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetVideoContrastEnhance;
import dji.midware.data.model.P3.DataCameraSetVideoContrastEnhance;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.view.base.SetSwitchView;

public class VideoContrastEnhanceSetterView extends SetSwitchView {
    private static final int a = 0;
    private static final int g = 1;
    private static final int h = 2;
    private boolean i = false;
    private Handler j = new Handler(new Callback(this) {
        final /* synthetic */ VideoContrastEnhanceSetterView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.setValueView(this.a.i);
                    break;
                case 1:
                    Toast.makeText(this.a.getContext(), (String) message.obj, 0).show();
                    break;
                case 2:
                    this.a.setValueView(false);
                    break;
            }
            return false;
        }
    });

    public VideoContrastEnhanceSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getTitleId() {
        return R.string.set_video_contrast_enhanced;
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        if (DataCameraGetPushShotParams.getInstance().getConstrastEhance() != 0) {
            a();
        }
    }

    protected void a() {
        DataCameraGetVideoContrastEnhance.getInstance().start(new d(this) {
            final /* synthetic */ VideoContrastEnhanceSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.i = DataCameraGetVideoContrastEnhance.getInstance().getEnhancementDegree();
                this.a.j.sendEmptyMessage(0);
            }

            public void onFailure(a aVar) {
                this.a.j.sendEmptyMessage(2);
            }
        });
    }

    protected void setValue(boolean z) {
        DataCameraSetVideoContrastEnhance.getInstance().a(z ? 4 : 0).start(new d(this) {
            final /* synthetic */ VideoContrastEnhanceSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.j.sendMessage(this.a.j.obtainMessage(1, "Success"));
            }

            public void onFailure(a aVar) {
                this.a.j.sendMessage(this.a.j.obtainMessage(1, "Fail"));
            }
        });
    }
}
