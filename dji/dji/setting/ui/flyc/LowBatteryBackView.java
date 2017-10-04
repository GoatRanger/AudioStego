package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class LowBatteryBackView extends ItemViewSwitch {
    private static final String a = "g_config.voltage2.user_set_smart_bat_0";

    public LowBatteryBackView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void setChecked(boolean z) {
        int i;
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        String str = a;
        if (z) {
            i = 2;
        } else {
            i = 1;
        }
        dataFlycSetParams.a(str, Integer.valueOf(i)).start(new d(this) {
            final /* synthetic */ LowBatteryBackView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                a.b().a(LowBatteryBackView.a);
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD("BeginerView", "fails code = " + aVar);
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
        });
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        if ((dji.midware.data.manager.P3.d.read(a).value.intValue() == 2) != z) {
            if (z) {
                setChecked(z);
            } else {
                dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_low_battery_back_tip, new OnClickListener(this) {
                    final /* synthetic */ LowBatteryBackView b;

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.b.setChecked(z);
                    }
                }, new OnClickListener(this) {
                    final /* synthetic */ LowBatteryBackView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        this.a.a();
                    }
                });
            }
        }
    }

    protected void a() {
        boolean z = false;
        if (i.getInstance().c() == ProductType.A3 || i.getInstance().c() == ProductType.N3) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        if (dji.midware.data.manager.P3.d.read(a).value.intValue() == 2) {
            z = true;
        }
        this.eS_.setChecked(z);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
            a.b().a(a);
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(a.a aVar) {
        if (aVar.a.equals(a)) {
            a();
        }
    }
}
