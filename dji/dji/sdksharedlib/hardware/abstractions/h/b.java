package dji.sdksharedlib.hardware.abstractions.h;

import dji.common.util.CallbackUtils;
import dji.midware.data.model.P3.DataRcGetPushRcCustomButtonsStatus;
import dji.midware.data.model.P3.DataRcSetCalibration;
import dji.midware.data.model.P3.DataRcSetCalibration.MODE;
import dji.sdksharedlib.b.i;
import dji.sdksharedlib.d.c;
import dji.sdksharedlib.hardware.abstractions.b.e;
import dji.sdksharedlib.hardware.abstractions.b.f;
import dji.sdksharedlib.hardware.abstractions.h.a.a;
import java.util.ArrayList;
import java.util.Iterator;

public class b extends a {
    private static final int a = 500;
    private a e;

    public void a(String str, int i, c cVar, f fVar) {
        super.a(str, i, cVar, fVar);
        this.e = new a();
    }

    public void e() {
        super.e();
        this.e = null;
    }

    protected void a() {
        super.a();
        a(i.class, getClass());
    }

    @dji.sdksharedlib.hardware.abstractions.a(a = "RemoteControllerCalibration")
    public void v(e eVar) {
        DataRcSetCalibration.getInstance().a(MODE.b).start(new 1(this));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RemoteControllerBAxisStatus")
    public void w(e eVar) {
        this.e.a(i.ac, new 3(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RemoteControllerCAxisStatus")
    public void x(e eVar) {
        this.e.a(i.ad, new 4(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RemoteControllerDAxisStatus")
    public void y(e eVar) {
        this.e.a(i.ae, new 5(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RemoteControllerEAxisStatus")
    public void z(e eVar) {
        this.e.a(i.af, new 6(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RemoteControllerFAxisStatus")
    public void A(e eVar) {
        this.e.a(i.ag, new 7(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RemoteControllerGAxisStatus")
    public void B(e eVar) {
        this.e.a(i.ah, new 8(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RemoteControllerAAxisStatus")
    public void C(e eVar) {
        this.e.a(i.ab, new 9(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RemoteControllerHAxisStatus")
    public void D(e eVar) {
        this.e.a(i.ai, new 10(this, eVar));
    }

    @dji.sdksharedlib.hardware.abstractions.e(a = "RemoteControllerCalibrationNumberOfFragment")
    public void E(e eVar) {
        this.e.a(i.aa, new 2(this, eVar));
    }

    private void a(ArrayList<e> arrayList, Object obj) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            CallbackUtils.onSuccess((e) it.next(), obj);
        }
    }

    public void onEventBackgroundThread(DataRcGetPushRcCustomButtonsStatus dataRcGetPushRcCustomButtonsStatus) {
        a((Object) Boolean.valueOf(dataRcGetPushRcCustomButtonsStatus.isUp()), a(i.aj));
        a((Object) Boolean.valueOf(dataRcGetPushRcCustomButtonsStatus.isDown()), a(i.ak));
        a((Object) Boolean.valueOf(dataRcGetPushRcCustomButtonsStatus.isPressed()), a(i.al));
        a((Object) Boolean.valueOf(dataRcGetPushRcCustomButtonsStatus.isLeft()), a(i.am));
        a((Object) Boolean.valueOf(dataRcGetPushRcCustomButtonsStatus.isRight()), a(i.an));
    }
}
