package dji.pilot.publics.b;

import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataDm368_gGetPushCheckStatus;
import dji.midware.data.model.P3.DataRcGetPushConnectStatus;
import dji.setting.ui.rc.RcMasterSlaveView;
import dji.thirdparty.a.c;

public class a {
    private a a;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private boolean e = false;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i = true;

    public interface a {
        void a();

        void a(boolean z);
    }

    public a() {
        c.a().a(this);
        if (DataDm368_gGetPushCheckStatus.getInstance().isGetted()) {
            this.c = DataDm368_gGetPushCheckStatus.getInstance().getHDMIConnectStatus();
        }
        if (DataRcGetPushConnectStatus.getInstance().isGetted()) {
            this.d = DataRcGetPushConnectStatus.getInstance().isSlaveConnected();
        }
        SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
        if (sDCardState == SDCardState.None || sDCardState == SDCardState.Invalid || sDCardState == SDCardState.Illegal) {
            this.e = false;
        } else {
            this.e = true;
        }
        if (DataCameraGetPushStateInfo.getInstance().getRecordState() != RecordType.NO) {
            this.f = true;
        } else {
            this.f = false;
        }
        if (ServiceManager.getInstance().isRemoteOK()) {
            this.g = true;
        } else {
            this.g = false;
        }
        if (ServiceManager.getInstance().isConnected()) {
            this.h = true;
        } else {
            this.h = false;
        }
        if (DataCameraGetPushStateInfo.getInstance().getVerstion() < 1) {
            this.i = true;
        } else {
            this.i = false;
        }
        a();
    }

    public void a(a aVar) {
        this.a = aVar;
        aVar.a(this.b);
    }

    private void a() {
        if (this.a != null) {
            this.a.a();
        }
        boolean c = dji.pilot.publics.e.c.c();
        if (c != this.b) {
            this.b = c;
            if (this.a != null) {
                this.a.a(this.b);
            }
        }
    }

    public void onEventBackgroundThread(RcMasterSlaveView.c cVar) {
        a();
    }

    public void onEventBackgroundThread(DataDm368_gGetPushCheckStatus dataDm368_gGetPushCheckStatus) {
        boolean hDMIConnectStatus = dataDm368_gGetPushCheckStatus.getHDMIConnectStatus();
        if (this.c != hDMIConnectStatus) {
            this.c = hDMIConnectStatus;
            a();
        }
    }

    public void onEventBackgroundThread(DataRcGetPushConnectStatus dataRcGetPushConnectStatus) {
        boolean isSlaveConnected = dataRcGetPushConnectStatus.isSlaveConnected();
        if (this.d != isSlaveConnected) {
            this.d = isSlaveConnected;
            a();
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        boolean z;
        boolean z2 = true;
        if (DataCameraGetPushStateInfo.getInstance().getVerstion() < 1) {
            z = true;
        } else {
            z = false;
        }
        if (z != this.i) {
            this.i = z;
            a();
        }
        SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
        if (sDCardState == SDCardState.None || sDCardState == SDCardState.Invalid || sDCardState == SDCardState.Illegal) {
            z = false;
        } else {
            z = true;
        }
        if (this.e != z) {
            this.e = z;
            a();
            return;
        }
        if (DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.NO) {
            z2 = false;
        }
        if (this.f != z2) {
            this.f = z2;
            a();
        }
    }

    public void onEventBackgroundThread(p pVar) {
        boolean z = false;
        if (pVar == p.b) {
            z = true;
        } else if (pVar == p.a) {
        }
        if (this.h != z) {
            this.h = z;
            a();
        }
    }
}
