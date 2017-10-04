package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.proguard.ad;
import java.util.Date;
import java.util.Map;

public class StrategyBean implements Parcelable {
    public static final Creator<StrategyBean> CREATOR = new Creator<StrategyBean>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public StrategyBean a(Parcel parcel) {
            return new StrategyBean(parcel);
        }

        public StrategyBean[] a(int i) {
            return new StrategyBean[i];
        }
    };
    public static String a;
    public long b;
    public long c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    public boolean i;
    public boolean j;
    public boolean k;
    public long l;
    public long m;
    public String n;
    public String o;
    public String p;
    public Map<String, String> q;

    public StrategyBean() {
        this.b = -1;
        this.c = -1;
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = true;
        this.k = true;
        this.m = 30000;
        this.n = "http://rqd.uu.qq.com/rqd/sync";
        this.o = "http://rqd.uu.qq.com/rqd/sync";
        this.c = new Date().getTime();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("S(").append("@L@L").append("@)");
        a = stringBuilder.toString();
        stringBuilder.setLength(0);
        stringBuilder.append("*^").append("@K#K").append("@!");
        this.p = stringBuilder.toString();
    }

    public StrategyBean(Parcel parcel) {
        boolean z = true;
        this.b = -1;
        this.c = -1;
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
        this.i = true;
        this.j = true;
        this.k = true;
        this.m = 30000;
        this.n = "http://rqd.uu.qq.com/rqd/sync";
        this.o = "http://rqd.uu.qq.com/rqd/sync";
        try {
            boolean z2;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("S(").append("@L@L").append("@)");
            a = stringBuilder.toString();
            this.c = parcel.readLong();
            this.d = parcel.readByte() == (byte) 1;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.e = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.f = z2;
            this.n = parcel.readString();
            this.o = parcel.readString();
            this.p = parcel.readString();
            this.q = ad.b(parcel);
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.g = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.j = z2;
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.k = z2;
            this.m = parcel.readLong();
            if (parcel.readByte() == (byte) 1) {
                z2 = true;
            } else {
                z2 = false;
            }
            this.h = z2;
            if (parcel.readByte() != (byte) 1) {
                z = false;
            }
            this.i = z;
            this.l = parcel.readLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3 = 1;
        parcel.writeLong(this.c);
        parcel.writeByte((byte) (this.d ? 1 : 0));
        if (this.e) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        ad.b(parcel, this.q);
        if (this.g) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.j) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.k) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        parcel.writeLong(this.m);
        if (this.h) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (!this.i) {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        parcel.writeLong(this.l);
    }
}
