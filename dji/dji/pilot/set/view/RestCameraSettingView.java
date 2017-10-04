package dji.pilot.set.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraLoadParams;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.e;
import dji.pilot.set.view.base.SetButtonView;

public class RestCameraSettingView extends SetButtonView {
    private static final int a = 0;
    private static final int b = 1;
    private static final int f = 2;
    private Handler g = new Handler(new Callback(this) {
        final /* synthetic */ RestCameraSettingView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    e.c(this.a.getContext(), ((Integer) message.obj).intValue());
                    break;
                case 1:
                    this.a.setVisibility(true);
                    break;
                case 2:
                    this.a.setVisibility(false);
                    break;
            }
            return false;
        }
    });

    public RestCameraSettingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        e.a(getContext(), R.string.set_camera_resetting_confirm, new OnClickListener(this) {
            final /* synthetic */ RestCameraSettingView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.g.sendEmptyMessage(2);
                dialogInterface.dismiss();
                this.a.d();
            }
        });
    }

    protected int getTitleId() {
        return R.string.set_camera_resetting;
    }

    protected int getButtonStringId() {
        return R.string.set_camera_resetting_start;
    }

    private void d() {
        DataCameraLoadParams.getInstance().setMode(USER.DEFAULT).start(new d(this) {
            final /* synthetic */ RestCameraSettingView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.g.sendMessage(this.a.g.obtainMessage(0, Integer.valueOf(R.string.set_camera_resetting_success)));
                this.a.g.sendEmptyMessage(1);
            }

            public void onFailure(a aVar) {
                this.a.g.sendMessage(this.a.g.obtainMessage(0, Integer.valueOf(R.string.set_camera_resetting_fail)));
                this.a.g.sendEmptyMessage(1);
            }
        });
    }

    private void setVisibility(boolean z) {
        if (z) {
            this.d.setEnabled(true);
            this.d.setAlpha(1.0f);
            return;
        }
        this.d.setEnabled(false);
        this.d.setAlpha(0.3f);
    }
}
