package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataA2PushCommom;
import dji.midware.data.model.P3.DataFlycSetHomePoint.HOMETYPE;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.DroneType;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.pilot.c.d;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;

public class HomePointView extends DividerLinearLayout {
    private static final int a = 20;

    public HomePointView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_flyc_homepoint);
        if (!isInEditMode()) {
            findViewById(R.id.setting_ui_flyc_homepoint_now).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HomePointView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    b.a(HOMETYPE.a, this.a.getContext());
                }
            });
            findViewById(R.id.setting_ui_flyc_homepoint_rc).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ HomePointView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    b.a(HOMETYPE.b, this.a.getContext());
                }
            });
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        b();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    private void b() {
        DroneType droneType = DataOsdGetPushCommon.getInstance().getDroneType();
        if (d.b == MODE.b || droneType == DroneType.NoFlyc || droneType == DroneType.Unknown) {
            setVisibility(8);
        } else if (i.getInstance().c() != ProductType.A2) {
            setVisibility(0);
        } else if (DataA2PushCommom.getInstance().b() >= 1) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}
