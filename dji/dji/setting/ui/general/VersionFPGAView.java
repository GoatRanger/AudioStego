package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewText;
import dji.thirdparty.a.c;

public class VersionFPGAView extends ItemViewText {
    private DataCommonGetVersion a = new DataCommonGetVersion();

    public VersionFPGAView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a.setDeviceType(DeviceType.CAMERA);
        this.a.setDeviceModel(6);
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
            this.a.start(new d(this) {
                final /* synthetic */ VersionFPGAView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.a();
                        }
                    });
                }

                public void onFailure(a aVar) {
                }
            });
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        if (DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC550Raw) {
            setVisibility(0);
            if (this.a.isGetted()) {
                getContext().getString(R.string.setting_ui_general_loader, new Object[]{this.a.getLoader(".")});
                this.d.setText(this.a.getFirmVer("."));
                return;
            }
            this.d.setText(R.string.setting_ui_general_default_str);
            return;
        }
        setVisibility(8);
    }

    public void onEventMainThread(DataCommonGetVersion dataCommonGetVersion) {
        a();
    }
}
