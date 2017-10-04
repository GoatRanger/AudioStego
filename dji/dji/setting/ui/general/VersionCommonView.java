package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.setting.ui.widget.ItemViewText;
import dji.thirdparty.a.c;

public abstract class VersionCommonView extends ItemViewText {
    private DataCommonGetVersion a = new DataCommonGetVersion();

    protected abstract DeviceType getDeviceType();

    public VersionCommonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a.setDeviceType(getDeviceType());
        if (getDeviceModelId() >= 0) {
            this.a.setDeviceModel(getDeviceModelId());
        }
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
            this.a.start(new d(this) {
                final /* synthetic */ VersionCommonView a;

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
        setVisibility(8);
    }

    public void onEventMainThread(DataCommonGetVersion dataCommonGetVersion) {
        a();
    }

    protected int getDeviceModelId() {
        return -1;
    }
}
