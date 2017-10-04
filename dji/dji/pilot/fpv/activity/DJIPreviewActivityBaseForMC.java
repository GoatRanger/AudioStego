package dji.pilot.fpv.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import com.here.services.location.hybrid.HybridLocationApi.Options;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.m;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataEyeGetPushFlatCheck;
import dji.midware.data.model.P3.DataFlycGetPushAvoidParam;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushWirelessState;
import dji.midware.data.model.P3.DataOsdGetPushWirelessState.SdrWirelessState;
import dji.midware.data.params.P3.ParamInfo;
import dji.pilot.R;
import dji.pilot.c.d;
import dji.pilot.dji_groundstation.controller.f;
import dji.pilot.fpv.control.l;
import dji.pilot.fpv.control.r;
import dji.pilot.fpv.model.n;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.publics.c.e;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.visual.selfcal.b;

public class DJIPreviewActivityBaseForMC extends DJIBaseActivity {
    l V;
    protected r W = null;
    protected b m_ = null;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.V = new l(this);
        this.W = new r(this);
        this.m_ = new b(this);
        if (DJIUpgradeP4Service.f()) {
            finish();
        }
        e.getInstance().a(this);
    }

    public void onEventMainThread(a aVar) {
        switch (3.a[aVar.ordinal()]) {
            case 1:
                finish();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(DataFlycGetPushAvoidParam dataFlycGetPushAvoidParam) {
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() >= 13) {
            this.W.b();
        }
        this.W.c();
    }

    public void onEventMainThread(DataEyeGetPushFlatCheck dataEyeGetPushFlatCheck) {
        this.W.a();
    }

    public void onEventMainThread(n.a aVar) {
        if (aVar == n.a.ARMACTION) {
            this.W.g();
        }
    }

    public void onEventMainThread(DataCommonGetVersion dataCommonGetVersion) {
        if (d.f == 1) {
            this.W.a(dataCommonGetVersion);
        }
    }

    public void l() {
        this.W.f();
    }

    protected void onDestroy() {
        e.getInstance().a();
        super.onDestroy();
        this.m_.d();
        this.V.a();
        this.V = null;
    }

    protected void onStop() {
        super.onStop();
        f.getInstance(this).j();
        this.handler.postDelayed(new 1(this), 2000);
    }

    protected void onResume() {
        super.onResume();
        f.getInstance(this).c(this);
    }

    protected void G() {
        int i = 1;
        if (dji.pilot.publics.e.a.a(i.getInstance().c(), DataOsdGetPushCommon.getInstance().getFlycVersion())) {
            ParamInfo read = dji.midware.data.manager.P3.d.read(dji.midware.data.params.P3.a.g);
            DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
            String str = dji.midware.data.params.P3.a.g;
            if (read.value.intValue() == 1) {
                i = 0;
            }
            dataFlycSetParams.a(str, Integer.valueOf(i)).start(new 2(this));
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushWirelessState dataOsdGetPushWirelessState) {
        SdrWirelessState eventCode = dataOsdGetPushWirelessState.getEventCode();
        DJIErrorPopView.b bVar = new DJIErrorPopView.b();
        if (eventCode == SdrWirelessState.STRONG_DISTURBANCE) {
            bVar.b = R.string.sdr_strong_disturbance_tip;
            bVar.h = 10000;
        } else if (eventCode == SdrWirelessState.VIDEO_DISTURBANCE) {
            bVar.b = R.string.sdr_video_disturbance_tip;
            bVar.h = 8000;
        } else if (eventCode == SdrWirelessState.RC_DISTURBANCE) {
            bVar.b = R.string.sdr_rc_disturbance_tip;
            bVar.h = 8000;
        } else if (eventCode == SdrWirelessState.LOW_SIGNAL_POWER) {
            bVar.b = R.string.sdr_low_signal_power_tip;
            bVar.h = 10000;
        } else if (eventCode == SdrWirelessState.CUSTOM_SIGNAL_DISTURBANCE) {
            bVar.b = R.string.sdr_custom_signal_disturbance_tip;
        } else if (eventCode == SdrWirelessState.RC_TO_GLASS_DIST) {
            bVar.b = R.string.sdr_rc_to_glass_tip;
            bVar.h = 10000;
        } else if (eventCode == SdrWirelessState.UAV_HAL_RESTART) {
            bVar.b = R.string.sdr_uav_restart_tip;
        } else if (eventCode == SdrWirelessState.GLASS_DIST_RC_ANTENNA) {
            bVar.b = R.string.sdr_rc_antenna_to_glass_tip;
            bVar.h = 10000;
        } else if (eventCode == SdrWirelessState.DISCONNECT_RC_DISTURB) {
            bVar.b = R.string.sdr_video_disturbance_tip;
            bVar.h = Options.DEFAULT_DESIRED_INTERVAL;
        } else if (eventCode == SdrWirelessState.DISCONNECT_UAV_DISTURB) {
            bVar.b = R.string.sdr_rc_disturbance_tip;
            bVar.h = Options.DEFAULT_DESIRED_INTERVAL;
        } else if (eventCode == SdrWirelessState.DISCONNECT_WEEK_SIGNAL) {
            bVar.b = R.string.sdr_low_signal_avoid_tip;
            bVar.h = Options.DEFAULT_DESIRED_INTERVAL;
        } else if (eventCode != SdrWirelessState.NONE && eventCode == SdrWirelessState.INTERNAL_EVENT) {
        }
        bVar.f = c.AUTODISAPPEAR;
        dji.thirdparty.a.c.a().e(bVar);
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.b) {
            DJIErrorPopView.b.b(DJIErrorPopView.d.WARNING, R.string.sdr_video_disturbance_tip, 0, c.AUTODISAPPEAR, DJIErrorPopView.f.REMOVE);
            DJIErrorPopView.b.b(DJIErrorPopView.d.WARNING, R.string.sdr_rc_disturbance_tip, 0, c.AUTODISAPPEAR, DJIErrorPopView.f.REMOVE);
            DJIErrorPopView.b.b(DJIErrorPopView.d.WARNING, R.string.sdr_low_signal_avoid_tip, 0, c.AUTODISAPPEAR, DJIErrorPopView.f.REMOVE);
        }
    }
}
