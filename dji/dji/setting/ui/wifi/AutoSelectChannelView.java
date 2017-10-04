package dji.setting.ui.wifi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import dji.log.WM220LogUtil;
import dji.midware.data.model.P3.DataWifiRestart;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.setting.ui.widget.a;

public class AutoSelectChannelView extends ItemViewButtonBig {
    public AutoSelectChannelView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        if (a.a()) {
            setVisibility(8);
        } else {
            setVisibility(8);
        }
    }

    public void onClick(View view) {
        a.b(getContext(), R.string.setting_ui_wifi_auto_select_channel_tip, new OnClickListener(this) {
            final /* synthetic */ AutoSelectChannelView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                DataWifiRestart.getInstance().start(new d(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        WM220LogUtil.LOGD("restart wifi onSuccess");
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        WM220LogUtil.LOGD("restart wifi onFailure");
                    }
                });
            }
        });
    }
}
