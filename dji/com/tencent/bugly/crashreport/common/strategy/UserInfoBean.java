package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.tencent.bugly.proguard.ad;
import java.util.Map;

public class UserInfoBean implements Parcelable {
    public static final Creator<UserInfoBean> CREATOR = new Creator<UserInfoBean>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public UserInfoBean a(Parcel parcel) {
            return new UserInfoBean(parcel);
        }

        public UserInfoBean[] a(int i) {
            return new UserInfoBean[i];
        }
    };
    public long a;
    public int b;
    public String c;
    public String d;
    public long e;
    public long f;
    public boolean g = false;
    public String h;
    public int i;
    public int j = -1;
    public int k = -1;
    public Map<String, String> l = null;
    public Map<String, String> m = null;

    public UserInfoBean(Parcel parcel) {
        boolean z = true;
        this.b = parcel.readInt();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readLong();
        this.f = parcel.readLong();
        if (parcel.readByte() != (byte) 1) {
            z = false;
        }
        this.g = z;
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = ad.b(parcel);
        this.m = ad.b(parcel);
        this.h = parcel.readString();
        this.i = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeLong(this.e);
        parcel.writeLong(this.f);
        parcel.writeByte((byte) (this.g ? 1 : 0));
        parcel.writeInt(this.j);
        parcel.writeInt(this.k);
        ad.b(parcel, this.l);
        ad.b(parcel, this.m);
        parcel.writeString(this.h);
        parcel.writeInt(this.i);
    }
}
