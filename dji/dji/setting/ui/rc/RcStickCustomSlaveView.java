package dji.setting.ui.rc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataRcGetSlaveMode;
import dji.midware.data.model.P3.DataRcSetSlaveMode;
import dji.midware.data.model.P3.DataRcSetSlaveMode.ControlMode;
import dji.midware.data.model.P3.DataRcSetSlaveMode.ModeFunction;
import dji.midware.data.model.P3.DataRcSetSlaveMode.SlaveCustomModel;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.rc.RcStickMapView.a;
import dji.thirdparty.a.c;
import java.util.ArrayList;

public class RcStickCustomSlaveView extends RcStickMapView implements a {
    private int[] b = new int[]{2, 3, 1, 0};
    private int[] c = new int[]{R.drawable.setting_ui_rc_stick_pitchup_normal, R.drawable.setting_ui_rc_stick_pitchdown_normal, R.drawable.setting_ui_rc_stick_yawleft_normal, R.drawable.setting_ui_rc_stick_yawright_normal, R.drawable.setting_ui_rc_stick_rollclockwise_normal, R.drawable.setting_ui_rc_stick_rollanticlockwise_normal};
    private int[] d = new int[]{R.drawable.setting_ui_rc_stick_pitchup_selected, R.drawable.setting_ui_rc_stick_pitchdown_selected, R.drawable.setting_ui_rc_stick_yawleft_selected, R.drawable.setting_ui_rc_stick_yawright_selected, R.drawable.setting_ui_rc_stick_rollclockwise_selected, R.drawable.setting_ui_rc_stick_rollanticlockwise_selected};
    private int[] e = new int[]{R.string.setting_ui_rc_up, R.string.setting_ui_rc_down, R.string.setting_ui_rc_left_rotate, R.string.setting_ui_rc_right_rotate, R.string.setting_ui_rc_left, R.string.setting_ui_rc_right};

    public RcStickCustomSlaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.a.setVisibility(0);
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RcStickCustomSlaveView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!dji.setting.a.a.a()) {
                        c.a().e(new dji.setting.ui.c(R.layout.setting_ui_group_rc_gimbal_speed, R.string.setting_ui_rc_gimbal_speed_desc, this.a));
                    }
                }
            });
            b();
            a();
        }
    }

    private void a() {
        DataRcGetSlaveMode.getInstance().start(new d(this) {
            final /* synthetic */ RcStickCustomSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void b() {
        ArrayList channels = DataRcGetSlaveMode.getInstance().getChannels();
        int[] iArr = new int[]{-1, -1, -1, -1};
        boolean[] zArr = new boolean[]{true, true, true, true};
        for (int i = 0; i < 4; i++) {
            boolean z;
            SlaveCustomModel slaveCustomModel = (SlaveCustomModel) channels.get(i);
            if (slaveCustomModel.b == ModeFunction.b.a()) {
                iArr[this.b[i]] = 0;
            } else if (slaveCustomModel.b == ModeFunction.c.a()) {
                iArr[this.b[i]] = 2;
            } else if (slaveCustomModel.b == ModeFunction.d.a()) {
                iArr[this.b[i]] = 1;
            } else {
                iArr[this.b[i]] = -1;
            }
            int i2 = this.b[i];
            if (slaveCustomModel.a == 0) {
                z = true;
            } else {
                z = false;
            }
            zArr[i2] = z;
        }
        setData(this.e, this.c, this.d, iArr, zArr, this);
    }

    public void onMapChange(int[] iArr, boolean[] zArr) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 4; i++) {
            int i2;
            Object slaveCustomModel;
            int i3 = iArr[this.b[i]];
            if (zArr[this.b[i]]) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if (i3 == 0) {
                slaveCustomModel = new SlaveCustomModel(i2, ModeFunction.b.a());
            } else if (i3 == 1) {
                slaveCustomModel = new SlaveCustomModel(i2, ModeFunction.d.a());
            } else if (i3 == 2) {
                slaveCustomModel = new SlaveCustomModel(i2, ModeFunction.c.a());
            } else {
                slaveCustomModel = new SlaveCustomModel(i2, 0);
            }
            arrayList.add(slaveCustomModel);
        }
        DataRcSetSlaveMode.getInstance().a(ControlMode.b).a(arrayList).start(new d(this) {
            final /* synthetic */ RcStickCustomSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("View", "set slaveMode success");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD("View", "set slaveMode " + aVar);
            }
        });
    }
}
