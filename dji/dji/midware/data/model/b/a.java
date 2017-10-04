package dji.midware.data.model.b;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.n;
import dji.midware.e.e;
import dji.midware.util.c;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public abstract class a extends n implements e {
    protected String TAG = getClass().getSimpleName();
    private int activeStatus;
    private int day;
    private String getSN = "";
    private int hour;
    private int min;
    private int month;
    private String pushSN = "";
    private int second;
    private String sn;
    private int snLen = 10;
    private b type;
    private a version = a.Ver1_0;
    private int year;

    public enum a {
        Ver1_0(0),
        Ver1_1(1),
        OTHER(100);
        
        private int d;

        private a(int i) {
            this.d = i;
        }

        public int a() {
            return this.d;
        }

        public boolean a(int i) {
            return this.d == i;
        }

        public static a find(int i) {
            a aVar = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return aVar;
        }
    }

    public enum b {
        GetVer(0),
        b(1, 17),
        SET(2, 18),
        PUSH(3, 19),
        OTHER(100);
        
        private int f;
        private int g;

        private b(int i) {
            this.f = i;
        }

        private b(int i, int i2) {
            this.f = i;
            this.g = i2;
        }

        public int a() {
            return this.f;
        }

        public int a(a aVar) {
            int i = this.f;
            if (this.g == 0) {
                return this.f;
            }
            switch (aVar) {
                case Ver1_0:
                    return this.f;
                case Ver1_1:
                    return this.g;
                default:
                    return i;
            }
        }

        public boolean a(int i) {
            return this.f == i;
        }

        public static b find(int i) {
            b bVar = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2].a(i)) {
                    return values()[i2];
                }
            }
            return bVar;
        }
    }

    public boolean isGetted() {
        return (this._recData == null || this._recData.length < 11 || this._recData.length == 18) ? false : true;
    }

    protected void setPushRecData(byte[] bArr) {
        setRecData(bArr);
        if (bArr != null && bArr.length >= 11 && this.pack.j != 1 && bArr.length != 18) {
            DJILogHelper.getInstance().LOGD(this.TAG, "** senderType=" + this.pack.f + " len=" + bArr.length + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + c.i(bArr), false, false);
            int intValue = ((Integer) get(0, 1, Integer.class)).intValue();
            if (intValue == b.PUSH.a(a.Ver1_0)) {
                this.version = a.Ver1_0;
                if (DeviceType.BATTERY._equals(this.pack.h)) {
                    this.pushSN = getInvertSn(1, 10);
                } else {
                    this.pushSN = getRevertSn(1, 10);
                }
            } else if (intValue == b.PUSH.a(a.Ver1_1)) {
                this.version = a.Ver1_1;
                this.snLen = ((Integer) get(1, 1, Integer.class)).intValue();
                DJILogHelper.getInstance().LOGD(this.TAG, getClass().getSimpleName() + " snLen=" + this.snLen, false, true);
                if (this.snLen > 16) {
                    this.pushSN = "";
                } else {
                    this.pushSN = getRevertSn(2, this.snLen);
                }
                DJILogHelper.getInstance().LOGD(this.TAG, getClass().getSimpleName() + " pushSN=" + this.pushSN, false, true);
            }
            dji.thirdparty.a.c.a().e(this);
        }
    }

    public void clear() {
    }

    public a setVersion(a aVar) {
        this.version = aVar;
        return this;
    }

    public a getVersion() {
        return this.version;
    }

    public String getPushSN() {
        if (this.pushSN.equals("")) {
            this.pushSN = getSN();
        }
        return this.pushSN;
    }

    public boolean isPushSnAvailable() {
        int i = 1;
        while (i < this.snLen + 1) {
            if (this._recData != null && this._recData.length > i && this._recData[i] != (byte) 0) {
                return true;
            }
            i++;
        }
        return false;
    }

    public boolean getActiveStatus() {
        return (((Integer) get(0, 1, Integer.class)).intValue() & 1) == 1;
    }

    public int getYear() {
        return ((Integer) get(1, 2, Integer.class)).intValue();
    }

    public int getMonth() {
        return ((Integer) get(3, 1, Integer.class)).intValue();
    }

    public int getDay() {
        return ((Integer) get(4, 1, Integer.class)).intValue();
    }

    public int getHour() {
        return ((Integer) get(5, 1, Integer.class)).intValue();
    }

    public int getMin() {
        return ((Integer) get(6, 1, Integer.class)).intValue();
    }

    public int getSecond() {
        return ((Integer) get(7, 1, Integer.class)).intValue();
    }

    public long getTime() {
        try {
            return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.ENGLISH).parse(getYear() + "-" + getMonth() + "-" + getDay() + "-" + getHour() + "-" + getMin() + "-" + getSecond()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getSN() {
        if (this.version != a.Ver1_0) {
            int intValue = ((Integer) get(8, 1, Integer.class)).intValue();
            if (intValue > 16) {
                this.getSN = "";
            } else {
                this.getSN = getRevertSn(9, intValue);
            }
        } else if (this.pack == null || !DeviceType.BATTERY._equals(this.pack.h)) {
            this.getSN = getRevertSn(8, 10);
        } else {
            this.getSN = "";
            if (this._recData == null || this._recData.length < 18) {
                return "";
            }
            this.getSN = getInvertSn(8, 10);
        }
        return this.getSN;
    }

    protected String getInvertSn(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder(i2);
        if (this._recData != null && i + i2 <= this._recData.length) {
            int i3 = (i + i2) - 1;
            while (i3 >= i && (byte) -1 != this._recData[i3]) {
                if (c.e(this._recData[i3])) {
                    stringBuilder.append((char) this._recData[i3]);
                } else {
                    stringBuilder.append(this._recData[i3]);
                }
                i3--;
            }
        }
        return stringBuilder.toString();
    }

    protected String getRevertSn(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder(i2);
        if (this._recData != null && i + i2 <= this._recData.length) {
            int i3 = i;
            while (i3 < i + i2 && (byte) -1 != this._recData[i3]) {
                if (c.e(this._recData[i3])) {
                    stringBuilder.append((char) this._recData[i3]);
                } else {
                    stringBuilder.append(this._recData[i3]);
                }
                i3++;
            }
        }
        return stringBuilder.toString();
    }

    @Deprecated
    protected String getInvertSnOld(int i, int i2) {
        String str = "";
        if (this._recData != null && i + i2 <= this._recData.length) {
            int i3 = (i + i2) - 1;
            while (i3 >= i && this._recData[i3] != (byte) -1) {
                String str2 = str + this._recData[i3];
                i3--;
                str = str2;
            }
        }
        return str;
    }

    @Deprecated
    protected String getRevertSnOld(int i, int i2) {
        if (this._recData == null || i + i2 > this._recData.length) {
            return "";
        }
        return get(i, i2);
    }

    public a setType(b bVar) {
        this.type = bVar;
        return this;
    }

    public a setActiveStatus(boolean z) {
        this.activeStatus = z ? 1 : 0;
        return this;
    }

    public a setYear(int i) {
        this.year = i;
        return this;
    }

    public a setMonth(int i) {
        this.month = i;
        return this;
    }

    public a setDay(int i) {
        this.day = i;
        return this;
    }

    public a setHour(int i) {
        this.hour = i;
        return this;
    }

    public a setMin(int i) {
        this.min = i;
        return this;
    }

    public a setSecond(int i) {
        this.second = i;
        return this;
    }

    public a setSN(String str) {
        this.sn = str;
        return this;
    }

    protected void doPack() {
        int i = 16;
        int i2 = 10;
        if (this.type == b.b) {
            this._sendData = new byte[1];
            this._sendData[0] = (byte) this.type.a(this.version);
        } else if (this.type == b.SET) {
            Object a = c.a(this.sn);
            DJILogHelper.getInstance().LOGD("", "active set sn=" + this.sn, true, true);
            DJILogHelper.getInstance().LOGD("", "active set sn len=" + a.length, true, true);
            if (this.version == a.Ver1_0) {
                this._sendData = new byte[19];
            } else {
                this._sendData = new byte[(a.length + 10)];
                DJILogHelper.getInstance().LOGD("", "active set sn2 len=" + this._sendData.length, true, true);
            }
            this._sendData[0] = (byte) this.type.a(this.version);
            this._sendData[1] = (byte) this.activeStatus;
            c.a(c.b(this.year), this._sendData, 2);
            this._sendData[4] = (byte) this.month;
            this._sendData[5] = (byte) this.day;
            this._sendData[6] = (byte) this.hour;
            this._sendData[7] = (byte) this.min;
            this._sendData[8] = (byte) this.second;
            if (this.version != a.Ver1_0) {
                if (a.length < 16) {
                    i = a.length;
                }
                DJILogHelper.getInstance().LOGD("", "active set len=" + i, false, true);
                this._sendData[9] = (byte) i;
                if (a.length > 0) {
                    System.arraycopy(a, 0, this._sendData, 10, i);
                }
            } else if (a.length > 0) {
                Object obj = this._sendData;
                if (a.length < 10) {
                    i2 = a.length;
                }
                System.arraycopy(a, 0, obj, 9, i2);
            }
        }
    }

    protected void LogPack(String str) {
    }
}
