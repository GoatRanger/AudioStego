package dji.logic.d;

import android.os.Handler;
import android.os.Looper;
import com.alipay.sdk.j.i;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.data.params.P3.a;
import dji.pilot.usercenter.protocol.d;
import dji.thirdparty.a.c;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class b implements a {
    private static final String E = b.class.getSimpleName();
    private static final int F = 4096;
    private static final int G = 4097;
    private static final int H = 0;
    private static final int I = 1;
    private static final String[] J = new String[]{a.b, a.c, a.d};
    private static final RcModeChannel[] K = new RcModeChannel[]{RcModeChannel.CHANNEL_F, RcModeChannel.CHANNEL_A, RcModeChannel.CHANNEL_P};
    private static final RcModeChannel[] L = new RcModeChannel[]{RcModeChannel.CHANNEL_A, RcModeChannel.CHANNEL_S, RcModeChannel.CHANNEL_P};
    private final RcModeChannel[] M;
    private final RcModeChannel[] N;
    private final ReentrantReadWriteLock O;
    private final ReadLock P;
    private final WriteLock Q;
    private volatile int R;
    private volatile boolean S;
    private final Handler T;
    final DataFlycGetParams a;

    public static b getInstance() {
        return a.a();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel a(int r3) {
        /*
        r2 = this;
        r0 = r2.P;
        r0.lock();
        if (r3 < 0) goto L_0x000c;
    L_0x0007:
        r0 = r2.M;	 Catch:{ all -> 0x001b }
        r0 = r0.length;	 Catch:{ all -> 0x001b }
        if (r3 < r0) goto L_0x0011;
    L_0x000c:
        r0 = r2.M;	 Catch:{ all -> 0x001b }
        r0 = r0.length;	 Catch:{ all -> 0x001b }
        r3 = r0 + -1;
    L_0x0011:
        r0 = r2.M;	 Catch:{ all -> 0x001b }
        r0 = r0[r3];	 Catch:{ all -> 0x001b }
        r1 = r2.P;
        r1.unlock();
        return r0;
    L_0x001b:
        r0 = move-exception;
        r1 = r2.P;
        r1.unlock();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.logic.d.b.a(int):dji.midware.data.model.P3.DataOsdGetPushCommon$RcModeChannel");
    }

    public void onEventBackgroundThread(p pVar) {
        if (p.ConnectLose == pVar) {
            c();
            this.S = false;
            this.R = Integer.MIN_VALUE;
            this.T.removeMessages(4096);
        }
    }

    public void onEventBackgroundThread(o oVar) {
        if (o.ConnectLose == oVar) {
            c();
            this.S = false;
            this.R = Integer.MIN_VALUE;
            this.T.removeMessages(4096);
        } else if (o.ConnectOK != oVar) {
        }
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        int flycVersion = dataOsdGetPushCommon.getFlycVersion();
        if (flycVersion != this.R) {
            this.R = flycVersion;
            this.T.removeMessages(4096);
            if (flycVersion >= 14) {
                this.S = false;
                b();
            }
            DJILogHelper.getInstance().LOGD(E, "Get RcMode version[" + this.R + d.H, false, true);
        }
    }

    public void onEventBackgroundThread(ProductType productType) {
        if (!this.S) {
            c();
        }
    }

    private void a() {
        this.S = true;
        ParamInfo read = dji.midware.data.manager.P3.d.read(a.b);
        ParamInfo read2 = dji.midware.data.manager.P3.d.read(a.c);
        ParamInfo read3 = dji.midware.data.manager.P3.d.read(a.d);
        this.N[0] = RcModeChannel.realFind(read.value.intValue());
        if (RcModeChannel.CHANNEL_P == this.N[0]) {
            this.N[0] = RcModeChannel.CHANNEL_F;
        }
        this.N[1] = RcModeChannel.realFind(read2.value.intValue());
        this.N[2] = RcModeChannel.realFind(read3.value.intValue());
        if (!(this.N[0] == this.M[0] && this.N[1] == this.M[1] && this.N[2] == this.M[2])) {
            this.Q.lock();
            try {
                this.M[0] = this.N[0];
                this.M[1] = this.N[1];
                this.M[2] = this.N[2];
                c.a().e(DataOsdGetPushCommon.getInstance());
                DJILogHelper.getInstance().LOGD(E, "Param ModeChls[" + this.M[0] + i.b + this.M[1] + i.b + this.M[2] + d.H, false, true);
            } finally {
                this.Q.unlock();
            }
        }
        this.T.sendMessageDelayed(this.T.obtainMessage(4097, 0, 0), 1500);
    }

    private void b() {
        if (!DataOsdGetPushCommon.getInstance().isGetted()) {
            return;
        }
        if (Integer.MIN_VALUE == this.R || this.R >= 14) {
            this.a.setInfos(J).start(new 2(this));
        }
    }

    private void c() {
        this.Q.lock();
        try {
            RcModeChannel[] rcModeChannelArr;
            RcModeChannel[] rcModeChannelArr2 = K;
            ProductType c = dji.midware.data.manager.P3.i.getInstance().c();
            if (c == ProductType.Tomato || c == ProductType.Pomato || c == ProductType.KumquatX || c == ProductType.KumquatS) {
                rcModeChannelArr = L;
            } else {
                rcModeChannelArr = rcModeChannelArr2;
            }
            int length = rcModeChannelArr.length;
            for (int i = 0; i < length; i++) {
                this.M[i] = rcModeChannelArr[i];
            }
        } finally {
            this.Q.unlock();
        }
    }

    private b() {
        this.M = new RcModeChannel[]{RcModeChannel.CHANNEL_F, RcModeChannel.CHANNEL_A, RcModeChannel.CHANNEL_P};
        this.N = new RcModeChannel[]{RcModeChannel.CHANNEL_F, RcModeChannel.CHANNEL_A, RcModeChannel.CHANNEL_P};
        this.O = new ReentrantReadWriteLock(false);
        this.P = this.O.readLock();
        this.Q = this.O.writeLock();
        this.R = Integer.MIN_VALUE;
        this.S = false;
        this.T = new Handler(Looper.getMainLooper(), new 1(this));
        this.a = new DataFlycGetParams();
        c.a().a((Object) this);
        c();
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventBackgroundThread(DataOsdGetPushCommon.getInstance());
        }
        if (ServiceManager.getInstance().isRemoteOK()) {
            onEventBackgroundThread(o.ConnectOK);
        }
    }
}
