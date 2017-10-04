package dji.setting.ui.rc;

import android.content.Context;
import android.util.AttributeSet;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ChannelCustomModel;
import dji.midware.data.model.P3.DataRcSetControlMode.ChannelType;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.rc.RcStickMapView.a;
import java.util.ArrayList;

public class RcStickCustomMasterView extends RcStickMapView implements a {
    private int[] b = new int[]{2, 3, 1, 0};
    private int[] c = new int[]{R.drawable.setting_ui_rc_stick_up_normal, R.drawable.setting_ui_rc_stick_down_normal, R.drawable.setting_ui_rc_stick_left_normal, R.drawable.setting_ui_rc_stick_right_normal, R.drawable.setting_ui_rc_stick_forward_normal, R.drawable.setting_ui_rc_stick_back_normal, R.drawable.setting_ui_rc_stick_turnleft_normal, R.drawable.setting_ui_rc_stick_turnright_normal};
    private int[] d = new int[]{R.drawable.setting_ui_rc_stick_up_selected, R.drawable.setting_ui_rc_stick_down_selected, R.drawable.setting_ui_rc_stick_left_selected, R.drawable.setting_ui_rc_stick_right_selected, R.drawable.setting_ui_rc_stick_forward_selected, R.drawable.setting_ui_rc_stick_back_selected, R.drawable.setting_ui_rc_stick_turnleft_selected, R.drawable.setting_ui_rc_stick_turnright_selected};
    private int[] e = new int[]{R.string.setting_ui_rc_master_up, R.string.setting_ui_rc_master_down, R.string.setting_ui_rc_master_left, R.string.setting_ui_rc_master_right, R.string.setting_ui_rc_master_forword, R.string.setting_ui_rc_master_back, R.string.setting_ui_rc_master_turnleft, R.string.setting_ui_rc_master_turnright};

    public RcStickCustomMasterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            b();
            a();
        }
    }

    private void a() {
        DataRcGetControlMode.getInstance().start(new d(this) {
            final /* synthetic */ RcStickCustomMasterView a;

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
                        this.a.a.b();
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void b() {
        ArrayList channels = DataRcGetControlMode.getInstance().getChannels();
        int[] iArr = new int[]{-1, -1, -1, -1};
        boolean[] zArr = new boolean[]{true, true, true, true};
        for (int i = 0; i < 4; i++) {
            boolean z;
            ChannelCustomModel channelCustomModel = (ChannelCustomModel) channels.get(i);
            if (channelCustomModel.b == ChannelType.b.a()) {
                iArr[this.b[i]] = 1;
            } else if (channelCustomModel.b == ChannelType.c.a()) {
                iArr[this.b[i]] = 2;
            } else if (channelCustomModel.b == ChannelType.d.a()) {
                iArr[this.b[i]] = 0;
            } else if (channelCustomModel.b == ChannelType.e.a()) {
                iArr[this.b[i]] = 3;
            } else {
                iArr[this.b[i]] = -1;
            }
            int i2 = this.b[i];
            if (channelCustomModel.a == 0) {
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
            Object channelCustomModel;
            int i3 = iArr[this.b[i]];
            if (zArr[this.b[i]]) {
                i2 = 0;
            } else {
                i2 = 1;
            }
            if (i3 == 0) {
                channelCustomModel = new ChannelCustomModel(i2, ChannelType.d.a());
            } else if (i3 == 1) {
                channelCustomModel = new ChannelCustomModel(i2, ChannelType.b.a());
            } else if (i3 == 2) {
                channelCustomModel = new ChannelCustomModel(i2, ChannelType.c.a());
            } else if (i3 == 3) {
                channelCustomModel = new ChannelCustomModel(i2, ChannelType.e.a());
            } else {
                channelCustomModel = new ChannelCustomModel(i2, 0);
            }
            arrayList.add(channelCustomModel);
        }
        DataRcSetControlMode.getInstance().a(ControlMode.d).a(arrayList).start(new d(this) {
            final /* synthetic */ RcStickCustomMasterView a;

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
