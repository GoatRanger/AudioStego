package dji.midware.data.manager.P3;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import dji.midware.data.a.a.b;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.model.P3.DataAppOperation;
import dji.midware.data.model.P3.DataAppOperation.APP_OPERATION_STATE;
import dji.midware.e.d;
import dji.thirdparty.a.c;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class n {
    private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
    protected static Handler handler = new Handler(Looper.getMainLooper(), new Callback() {
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    ((n) message.obj).setPushLose();
                    break;
            }
            return false;
        }
    });
    private static HashMap<Integer, ArrayList<Integer>> mytestlist = new HashMap();
    protected byte[] _recData;
    protected byte[] _sendData;
    private a dataType;
    private c encryManager;
    protected boolean isNeedPushLosed;
    protected boolean isPushLosed;
    protected boolean isRegist;
    protected boolean isRemoteModel;
    private Thread joinThread;
    private Thread me;
    protected dji.midware.data.a.a.a pack;
    private int recordType;
    protected b recvPack;
    private boolean testDump;

    protected enum a {
        PUSH,
        LOCAL,
        REMOTE
    }

    protected abstract void doPack();

    public n() {
        this(true);
    }

    public n(boolean z) {
        this.dataType = a.REMOTE;
        this.isRegist = true;
        this.isPushLosed = true;
        this.isNeedPushLosed = false;
        this.isRemoteModel = false;
        this.recordType = -1;
        this.testDump = false;
        this.dataType = getDataType();
        this.encryManager = c.getInstance();
        this.isRegist = z;
        if (z) {
            c.a().a((Object) this, 100);
        }
    }

    protected a getDataType() {
        return this.dataType;
    }

    public void onEventBackgroundThread(p pVar) {
        if (pVar == p.ConnectLose) {
            clear();
        }
    }

    public void clear() {
        this._recData = null;
    }

    protected void LogPack(String str) {
    }

    public int getRecDataLen() {
        return this._recData != null ? this._recData.length : 0;
    }

    public byte[] getRecData() {
        return this._recData;
    }

    public void setRecData(byte[] bArr) {
        this._recData = bArr;
    }

    public void setRecPack(b bVar) {
    }

    public void outerSetPushRecPack(dji.midware.data.a.a.a aVar) {
        setPushRecPack(aVar);
    }

    protected void setPushRecPack(dji.midware.data.a.a.a aVar) {
        if (this.isNeedPushLosed) {
            this.isPushLosed = false;
            handler.removeMessages(0, this);
            handler.sendMessageDelayed(handler.obtainMessage(0, this), 4000);
        }
        this.pack = aVar;
        setPushRecData(aVar.p);
    }

    protected void setPushLose() {
        this.isPushLosed = true;
        if (!this.isRegist || !ServiceManager.getInstance().isConnected() || !this.isRemoteModel || !this.isRemoteModel || ServiceManager.getInstance().isRemoteOK()) {
        }
    }

    protected void post() {
        if (ServiceManager.isAlive()) {
            c.a().e(this);
        }
    }

    public boolean isPushLosed() {
        return this.isPushLosed;
    }

    protected boolean isWantPush() {
        return true;
    }

    protected boolean isChanged(byte[] bArr) {
        if ((this.pack == null || this.pack.n != dji.midware.data.config.P3.d.a.GetPushUpgradeStatus.a() || this.pack.m != p.COMMON.a()) && Arrays.equals(this._recData, bArr)) {
            return false;
        }
        return true;
    }

    protected void setPushRecData(byte[] bArr) {
        boolean isChanged = isChanged(bArr);
        setRecData(bArr);
        if (isChanged && isWantPush() && this.isRegist) {
            post();
        }
    }

    public boolean isGetted() {
        return this._recData != null;
    }

    public boolean isGettedPack() {
        return this.pack != null;
    }

    protected byte[] getSendData() {
        doPack();
        return this._sendData;
    }

    public <T extends Number> T get(int i, int i2, Class<T> cls) {
        byte[] bArr;
        if (this._recData == null) {
            bArr = new byte[i2];
        } else if (this._recData.length < i + i2) {
            bArr = new byte[i2];
            if (this._recData.length > i) {
                System.arraycopy(this._recData, i, bArr, 0, this._recData.length - i);
            }
        } else {
            bArr = dji.midware.util.c.e(this._recData, i, i2);
        }
        if (cls == Short.class) {
            return Short.valueOf(dji.midware.util.c.a(bArr));
        }
        if (cls == Integer.class) {
            return Integer.valueOf(dji.midware.util.c.b(bArr));
        }
        if (cls == Long.class) {
            return Long.valueOf(dji.midware.util.c.c(bArr));
        }
        if (cls == Float.class) {
            return Float.valueOf(dji.midware.util.c.d(bArr));
        }
        if (cls == Double.class) {
            return Double.valueOf(dji.midware.util.c.e(bArr));
        }
        if (cls == BigInteger.class) {
            return Integer.valueOf(dji.midware.util.c.b(bArr));
        }
        return cls == Byte.class ? Short.valueOf(dji.midware.util.c.b(bArr[0])) : null;
    }

    protected String get(int i, int i2) {
        if (this._recData == null) {
            return "";
        }
        return dji.midware.util.c.f(dji.midware.util.c.e(this._recData, i, i2));
    }

    protected String getUTF8(int i, int i2) {
        if (this._recData == null) {
            return "";
        }
        return dji.midware.util.c.g(dji.midware.util.c.e(this._recData, i, i2));
    }

    protected void start(final dji.midware.data.a.a.c cVar, final d dVar) {
        if (!this.testDump || cVar == null) {
            cVar.p = getSendData();
            cVar.a();
            q.getInstance().a((dji.midware.data.a.a.a) cVar, getClass());
            recordSend(cVar);
            if (!(this.encryManager.a() || cVar.m == p.COMMON.a() || !this.encryManager.b(cVar.r))) {
                cVar.r = this.encryManager.a(cVar.r, cVar.i);
                cVar.l = q.b.SIMPLE.a();
                this.encryManager.c(cVar.r);
                cVar.b();
            }
            this.me = new Thread(new Runnable(this) {
                final /* synthetic */ n c;

                public void run() {
                    if (this.c.joinThread != null) {
                        try {
                            this.c.joinThread.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    if (this.c.dataType != a.LOCAL || this.c._recData == null) {
                        if (cVar.w == 2 && cVar.v == 1000) {
                            if (cVar.m == p.FLYC.a()) {
                                cVar.w = 3;
                            } else if (cVar.m == p.CAMERA.a()) {
                                if (cVar.w < 2) {
                                    cVar.w = 2;
                                }
                                if (cVar.v < 1000) {
                                    cVar.v = 1000;
                                }
                            }
                        }
                        b synSendCmd = a.synSendCmd(cVar);
                        this.c.recvPack = synSendCmd;
                        if (dVar == null) {
                            return;
                        }
                        if (synSendCmd.t) {
                            if (dji.midware.data.config.P3.a.OK.b(synSendCmd.o) || (cVar.m == p.CAMERA.a() && dji.midware.data.config.P3.a.SUCCEED.b(synSendCmd.o))) {
                                this.c.setRecData(synSendCmd.p);
                                this.c.setRecPack(synSendCmd);
                                this.c.callbackSuccess(dVar);
                                this.c.recordAckSuccess(cVar);
                                return;
                            }
                            this.c.setRecData(synSendCmd.p);
                            this.c.setRecPack(synSendCmd);
                            this.c.callBackFailure(dVar, dji.midware.data.config.P3.a.find(synSendCmd.o));
                            this.c.recordAckFailed(cVar);
                            return;
                        } else if (synSendCmd.p != null) {
                            this.c.setRecData(synSendCmd.p);
                            this.c.setRecPack(synSendCmd);
                            this.c.callbackSuccess(dVar);
                            this.c.recordAckSuccess(cVar);
                            return;
                        } else {
                            this.c.callBackFailure(dVar, dji.midware.data.config.P3.a.INVALID_PARAM);
                            this.c.recordAckFailed(cVar);
                            return;
                        }
                    }
                    this.c.callbackSuccess(dVar);
                }
            });
            this.me.start();
            return;
        }
        collectpackInfo(cVar);
    }

    private void callbackSuccess(d dVar) {
        dVar.onSuccess(this);
    }

    private void callBackFailure(d dVar, dji.midware.data.config.P3.a aVar) {
        dVar.onFailure(aVar);
    }

    public void join() {
        try {
            this.me.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setJoin(n nVar) {
        this.joinThread = nVar.me;
    }

    public static void dumpPackInfo() {
        String str = "";
        if (mytestlist.size() > 0) {
            String str2 = str;
            for (Integer num : mytestlist.keySet()) {
                Integer num2;
                str2 = (((str2 + "cmdset=0x") + dji.midware.util.c.d(num2.intValue())) + "\n") + "cmdid=";
                Iterator it = ((ArrayList) mytestlist.get(num2)).iterator();
                while (it.hasNext()) {
                    num2 = (Integer) it.next();
                    str2 = ((str2 + "0x") + dji.midware.util.c.d(num2.intValue())) + ", ";
                }
                str2 = str2 + "\n";
            }
            Log.e("", str2);
        }
    }

    private static void collectpackInfo(dji.midware.data.a.a.c cVar) {
        if (cVar.f == DeviceType.APP.value()) {
            ArrayList arrayList;
            if (mytestlist.containsKey(Integer.valueOf(cVar.m))) {
                arrayList = (ArrayList) mytestlist.get(Integer.valueOf(cVar.m));
            } else {
                arrayList = new ArrayList();
            }
            if (!arrayList.contains(Integer.valueOf(cVar.n))) {
                arrayList.add(Integer.valueOf(cVar.n));
                mytestlist.put(Integer.valueOf(cVar.m), arrayList);
            }
        }
    }

    protected void start(dji.midware.data.a.a.c cVar) {
        if (!this.testDump || cVar == null) {
            cVar.p = getSendData();
            cVar.a();
            q.getInstance().a((dji.midware.data.a.a.a) cVar, getClass());
            if (!this.encryManager.a() && this.encryManager.b(cVar.r)) {
                cVar.r = this.encryManager.a(cVar.r, cVar.i);
                cVar.l = q.b.SIMPLE.a();
                this.encryManager.c(cVar.r);
                cVar.b();
            }
            a.asynSendCmd(cVar);
            return;
        }
        collectpackInfo(cVar);
    }

    protected boolean needRecord(dji.midware.data.a.a.c cVar) {
        if (cVar == null || cVar.m != p.FLYC.a()) {
            return false;
        }
        if (cVar.n == dji.midware.data.config.P3.g.a.FunctionControl.a()) {
            this.recordType = 0;
            return true;
        } else if (cVar.n != dji.midware.data.config.P3.g.a.SetHomePoint.a()) {
            return false;
        } else {
            this.recordType = 1;
            return true;
        }
    }

    protected void recordSend(dji.midware.data.a.a.c cVar) {
        if (needRecord(cVar) && cVar.p != null && cVar.p.length >= 1) {
            DataAppOperation dataAppOperation = new DataAppOperation(false);
            int i = cVar.p[0];
            if (this.recordType == 0) {
                dataAppOperation.a(i, APP_OPERATION_STATE.SEND);
            } else if (this.recordType == 1) {
                dataAppOperation.a(i, APP_OPERATION_STATE.SEND, true);
            }
            c.a().e(dataAppOperation);
        }
    }

    protected void recordAckSuccess(dji.midware.data.a.a.c cVar) {
        if (needRecord(cVar) && cVar.p != null && cVar.p.length >= 1) {
            DataAppOperation dataAppOperation = new DataAppOperation(false);
            int i = cVar.p[0];
            if (this.recordType == 0) {
                dataAppOperation.a(i, APP_OPERATION_STATE.ACK_SUCCESS);
            } else if (this.recordType == 1) {
                dataAppOperation.a(i, APP_OPERATION_STATE.ACK_SUCCESS, true);
            }
            c.a().e(dataAppOperation);
        }
    }

    protected void recordAckFailed(dji.midware.data.a.a.c cVar) {
        if (needRecord(cVar) && cVar.p != null && cVar.p.length >= 1) {
            DataAppOperation dataAppOperation = new DataAppOperation(false);
            int i = cVar.p[0];
            if (this.recordType == 0) {
                dataAppOperation.a(i, APP_OPERATION_STATE.ACK_FAILED);
            } else if (this.recordType == 1) {
                dataAppOperation.a(i, APP_OPERATION_STATE.ACK_FAILED, true);
            }
            c.a().e(dataAppOperation);
        }
    }
}
