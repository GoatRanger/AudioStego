package dji.midware.data.manager.P3;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.location.places.Place;
import dji.log.DJILogHelper;
import dji.midware.data.a.a.b;
import dji.midware.e.a;
import dji.midware.natives.GroudStation;
import dji.midware.util.c;
import java.lang.reflect.Method;

public abstract class h {
    private int byteOffset;
    private int count;
    protected o curCameraEvent = o.ConnectLose;
    protected boolean enabledSetDataEvent;
    private c encryManager;
    protected Handler handler = new Handler(Looper.getMainLooper(), new 1(this));
    byte[] header = new byte[4];
    protected boolean isCheck = true;
    protected boolean isGeneralPack;
    byte[] mCopybuffer;
    byte[] mTempbuffer = new byte[20480];
    byte[] mZerobuffer = new byte[20480];
    byte[] mbuffer = new byte[20480];
    private j queueManager;

    protected abstract void debug(b bVar);

    public h() {
        if (!this.enabledSetDataEvent) {
            this.queueManager = j.getInstance();
            this.encryManager = c.getInstance();
        }
    }

    public synchronized void parse(byte[] bArr, int i, int i2) {
        System.arraycopy(bArr, i, this.mbuffer, this.count, i2);
        this.count += i2;
        do {
        } while (parseOne());
        System.arraycopy(this.mZerobuffer, 0, this.mTempbuffer, 0, this.count);
        System.arraycopy(this.mbuffer, this.byteOffset, this.mTempbuffer, 0, this.count);
        this.mCopybuffer = this.mbuffer;
        this.mbuffer = this.mTempbuffer;
        this.mTempbuffer = this.mCopybuffer;
        this.byteOffset = 0;
    }

    private boolean parseOne() {
        if (this.count < 13) {
            return false;
        }
        if (!findHead()) {
            return false;
        }
        if (this.count < 4) {
            return false;
        }
        System.arraycopy(this.mbuffer, this.byteOffset, this.header, 0, 4);
        if (GroudStation.native_verifyCrc8(this.header)) {
            int length = getLength();
            if (this.enabledSetDataEvent) {
            }
            if (length > this.count) {
                return false;
            }
            byte[] bArr = new byte[length];
            System.arraycopy(this.mbuffer, this.byteOffset, bArr, 0, length);
            if (this.enabledSetDataEvent) {
            }
            if (GroudStation.native_verifyCrc16(bArr)) {
                this.byteOffset += length;
                this.count -= length;
                if (this.enabledSetDataEvent) {
                }
                if (c.a(bArr[4]) == (short) 28 || c.a(bArr[5]) == (short) 28) {
                }
                if (this.enabledSetDataEvent && this.encryManager.a(bArr)) {
                    this.encryManager.a(false);
                    byte[] bArr2 = new byte[2];
                    System.arraycopy(bArr, 6, bArr2, 0, 2);
                    bArr = this.encryManager.b(bArr, c.b(bArr2));
                } else if (this.encryManager.b(bArr)) {
                    this.encryManager.a(true);
                }
                b bVar = new b(bArr);
                debug(bVar);
                setMsg(bVar);
            } else {
                this.byteOffset += 4;
                this.count -= 4;
                if (!this.enabledSetDataEvent) {
                    DJILogHelper.getInstance().LOGD("PackManager", "fullBuffer crc16=fail=" + c.i(bArr));
                }
            }
            return true;
        }
        if (this.enabledSetDataEvent) {
            this.byteOffset++;
            this.count--;
        } else {
            this.byteOffset++;
            this.count--;
        }
        return true;
    }

    private void setMsg(b bVar) {
        if (bVar.v != null) {
            a b = bVar.v.b();
            if (b != null) {
                boolean b2 = b.b(bVar.n);
                boolean a = b.a(bVar.n);
                if (b2 || a) {
                    this.queueManager.a(bVar.m).b(bVar);
                }
                if (!b2) {
                    try {
                        n e = b.e(bVar.n);
                        if (e == null) {
                            Class d = b.d(bVar.n);
                            if (d == null) {
                                d = Class.forName(b.a(bVar.f, bVar.e, bVar.n));
                            }
                            Method method = d.getMethod("getInstance", new Class[0]);
                            method.setAccessible(true);
                            e = (n) method.invoke(d, new Object[0]);
                        }
                        if (e instanceof dji.midware.e.c) {
                            ((dji.midware.e.c) e).stop();
                            return;
                        }
                        e.setPushRecPack(bVar);
                        q.getInstance().b((dji.midware.data.a.a.a) bVar, e.getClass());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean findHead() {
        int indexOf = indexOf((byte) 85);
        boolean z = indexOf >= 0;
        if (z) {
            this.byteOffset += indexOf;
            this.count -= indexOf;
        } else {
            this.byteOffset = 0;
            this.count = 0;
        }
        return z;
    }

    private int indexOf(byte b) {
        for (int i = 0; i < this.count; i++) {
            if (this.mbuffer[this.byteOffset + i] == b) {
                return i;
            }
        }
        return -1;
    }

    private int getLength() {
        return c.a(new byte[]{this.mbuffer[this.byteOffset + 1], this.mbuffer[this.byteOffset + 2]}) & Place.TYPE_SUBLOCALITY_LEVEL_1;
    }
}
