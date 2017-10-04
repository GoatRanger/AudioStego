package dji.pilot.usercenter.mode;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

public class PhotoPreviewInfo implements Parcelable {
    public static final Creator<PhotoPreviewInfo> CREATOR = new Creator<PhotoPreviewInfo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public PhotoPreviewInfo a(Parcel parcel) {
            return new PhotoPreviewInfo(parcel);
        }

        public PhotoPreviewInfo[] a(int i) {
            return new PhotoPreviewInfo[i];
        }
    };
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j = null;
    public String k = null;
    public String l = null;
    public String m = null;
    public String n = null;
    public String o = null;
    public int p = 0;
    public long q;
    public int r;
    public long s;
    public long t;
    public int u;
    public int v;
    public int w;
    public int x;
    public String y;

    public String a() {
        return Scheme.FILE.crop(this.o);
    }

    public PhotoPreviewInfo(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.q = parcel.readLong();
        this.r = parcel.readInt();
        this.s = parcel.readLong();
        this.t = parcel.readLong();
        this.u = parcel.readInt();
        this.v = parcel.readInt();
        this.w = parcel.readInt();
        this.x = parcel.readInt();
        this.y = parcel.readString();
        this.p = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeLong(this.q);
        parcel.writeInt(this.r);
        parcel.writeLong(this.s);
        parcel.writeLong(this.t);
        parcel.writeInt(this.u);
        parcel.writeInt(this.v);
        parcel.writeInt(this.w);
        parcel.writeInt(this.x);
        parcel.writeString(this.y);
        parcel.writeInt(this.p);
    }
}
