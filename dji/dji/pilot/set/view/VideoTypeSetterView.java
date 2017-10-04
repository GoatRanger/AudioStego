package dji.pilot.set.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import dji.midware.data.config.P3.b.a;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.pilot.set.R;
import dji.pilot.set.e;
import dji.pilot.set.view.base.SetGroupButtonView;

public class VideoTypeSetterView extends SetGroupButtonView {
    private static final int a = 1;
    private static final int d = 0;
    private Handler e = new Handler(new Callback(this) {
        final /* synthetic */ VideoTypeSetterView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.setSelect(0);
                    break;
                case 1:
                    this.a.setSelect(1);
                    break;
            }
            return false;
        }
    });
    private boolean f = true;

    public VideoTypeSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected int getTitleId() {
        return R.string.set_videotyle;
    }

    protected void a() {
        setSelect(DataCameraGetPushShotParams.getInstance().getVideoStandard());
        this.f = false;
    }

    protected void setValue(final int i) {
        if (!this.f) {
            e.a(getContext(), R.string.fpv_gensetting_videotyle_hint, new OnClickListener(this) {
                final /* synthetic */ VideoTypeSetterView b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
                    dataBaseCameraSetting.a(a.au);
                    dataBaseCameraSetting.a(i).start(this.b.c);
                }
            }, new OnClickListener(this) {
                final /* synthetic */ VideoTypeSetterView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (this.a.b == 0) {
                        this.a.e.sendEmptyMessage(1);
                    } else {
                        this.a.e.sendEmptyMessage(0);
                    }
                }
            });
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        a();
    }

    protected int getLeftBtnStrId() {
        return R.string.set_video_type_PAL;
    }

    protected int getRightBtnStrId() {
        return R.string.set_video_type_NTSC;
    }
}
