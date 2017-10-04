package dji.pilot.fpv.control;

import dji.log.DJILogHelper;
import dji.log.WM220LogUtil;
import dji.logic.c.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycSetJoyStickParams.FlycMode;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.thirdparty.a.c;
import java.util.HashMap;
import java.util.Map;

public class u {
    private static u a = null;
    private FlycMode b = FlycMode.b;
    private Map<FlycMode, RcModeChannel> c = new HashMap();
    private DataSpecialControl d = DataSpecialControl.getInstance();
    private RcModeChannel e = RcModeChannel.CHANNEL_UNKNOWN;

    public static synchronized u getInstance() {
        u uVar;
        synchronized (u.class) {
            if (a == null) {
                a = new u();
            }
            uVar = a;
        }
        return uVar;
    }

    private u() {
        c.a().a((Object) this);
        this.c.put(FlycMode.a, RcModeChannel.CHANNEL_S);
        this.c.put(FlycMode.b, RcModeChannel.CHANNEL_P);
        this.c.put(FlycMode.c, RcModeChannel.CHANNEL_A);
    }

    public FlycMode a() {
        return this.b;
    }

    public void a(FlycMode flycMode) {
        this.b = flycMode;
    }

    public boolean b() {
        return b.getInstance().b(i.getInstance().c());
    }

    public void b(FlycMode flycMode) {
        if (b()) {
            WM220LogUtil.LOGD("--into setrcmode: " + flycMode);
            this.d.setFlycMode(flycMode).start(20);
            this.b = flycMode;
            this.e = (RcModeChannel) this.c.get(this.b);
        }
    }

    public void onEventMainThread(ProductType productType) {
        WM220LogUtil.LOGD("into FlycModeSwitchController ProductType event");
        b(this.b);
    }

    public void onEventMainThread(o oVar) {
        if (oVar == o.ConnectOK) {
            WM220LogUtil.LOGD("into FlycModeSwitchController camera connect ok");
            b(this.b);
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        RcModeChannel modeChannel = dataOsdGetPushCommon.getModeChannel();
        if (this.e != modeChannel) {
            this.e = modeChannel;
            DJILogHelper.getInstance().LOGE(getClass().getSimpleName(), "channel: " + modeChannel);
            if (this.e == RcModeChannel.CHANNEL_S) {
                a(FlycMode.a);
            } else if (this.e == RcModeChannel.CHANNEL_P) {
                a(FlycMode.b);
            } else if (this.e == RcModeChannel.CHANNEL_A) {
                a(FlycMode.c);
            }
        }
    }
}
