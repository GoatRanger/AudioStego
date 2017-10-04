package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public abstract class ShareOpenGraphValueContainer<P extends ShareOpenGraphValueContainer, E extends a> implements ShareModel {
    private final Bundle a;

    public static abstract class a<P extends ShareOpenGraphValueContainer, E extends a> implements a<P, E> {
        private Bundle a = new Bundle();

        public E a(String str, boolean z) {
            this.a.putBoolean(str, z);
            return this;
        }

        public E a(String str, @Nullable boolean[] zArr) {
            this.a.putBooleanArray(str, zArr);
            return this;
        }

        public E a(String str, double d) {
            this.a.putDouble(str, d);
            return this;
        }

        public E a(String str, @Nullable double[] dArr) {
            this.a.putDoubleArray(str, dArr);
            return this;
        }

        public E a(String str, int i) {
            this.a.putInt(str, i);
            return this;
        }

        public E a(String str, @Nullable int[] iArr) {
            this.a.putIntArray(str, iArr);
            return this;
        }

        public E a(String str, long j) {
            this.a.putLong(str, j);
            return this;
        }

        public E a(String str, @Nullable long[] jArr) {
            this.a.putLongArray(str, jArr);
            return this;
        }

        public E a(String str, @Nullable ShareOpenGraphObject shareOpenGraphObject) {
            this.a.putParcelable(str, shareOpenGraphObject);
            return this;
        }

        public E a(String str, @Nullable ArrayList<ShareOpenGraphObject> arrayList) {
            this.a.putParcelableArrayList(str, arrayList);
            return this;
        }

        public E a(String str, @Nullable SharePhoto sharePhoto) {
            this.a.putParcelable(str, sharePhoto);
            return this;
        }

        public E b(String str, @Nullable ArrayList<SharePhoto> arrayList) {
            this.a.putParcelableArrayList(str, arrayList);
            return this;
        }

        public E a(String str, @Nullable String str2) {
            this.a.putString(str, str2);
            return this;
        }

        public E c(String str, @Nullable ArrayList<String> arrayList) {
            this.a.putStringArrayList(str, arrayList);
            return this;
        }

        public E a(P p) {
            if (p != null) {
                this.a.putAll(p.b());
            }
            return this;
        }
    }

    protected ShareOpenGraphValueContainer(a<P, E> aVar) {
        this.a = (Bundle) aVar.a.clone();
    }

    ShareOpenGraphValueContainer(Parcel parcel) {
        this.a = parcel.readBundle(a.class.getClassLoader());
    }

    @Nullable
    public Object a(String str) {
        return this.a.get(str);
    }

    public boolean a(String str, boolean z) {
        return this.a.getBoolean(str, z);
    }

    @Nullable
    public boolean[] b(String str) {
        return this.a.getBooleanArray(str);
    }

    public double a(String str, double d) {
        return this.a.getDouble(str, d);
    }

    @Nullable
    public double[] c(String str) {
        return this.a.getDoubleArray(str);
    }

    public int a(String str, int i) {
        return this.a.getInt(str, i);
    }

    @Nullable
    public int[] d(String str) {
        return this.a.getIntArray(str);
    }

    public long a(String str, long j) {
        return this.a.getLong(str, j);
    }

    @Nullable
    public long[] e(String str) {
        return this.a.getLongArray(str);
    }

    public ShareOpenGraphObject f(String str) {
        Object obj = this.a.get(str);
        return obj instanceof ShareOpenGraphObject ? (ShareOpenGraphObject) obj : null;
    }

    @Nullable
    public ArrayList<ShareOpenGraphObject> g(String str) {
        ArrayList parcelableArrayList = this.a.getParcelableArrayList(str);
        if (parcelableArrayList == null) {
            return null;
        }
        ArrayList<ShareOpenGraphObject> arrayList = new ArrayList();
        Iterator it = parcelableArrayList.iterator();
        while (it.hasNext()) {
            Parcelable parcelable = (Parcelable) it.next();
            if (parcelable instanceof ShareOpenGraphObject) {
                arrayList.add((ShareOpenGraphObject) parcelable);
            }
        }
        return arrayList;
    }

    @Nullable
    public SharePhoto h(String str) {
        Parcelable parcelable = this.a.getParcelable(str);
        return parcelable instanceof SharePhoto ? (SharePhoto) parcelable : null;
    }

    @Nullable
    public ArrayList<SharePhoto> i(String str) {
        ArrayList parcelableArrayList = this.a.getParcelableArrayList(str);
        if (parcelableArrayList == null) {
            return null;
        }
        ArrayList<SharePhoto> arrayList = new ArrayList();
        Iterator it = parcelableArrayList.iterator();
        while (it.hasNext()) {
            Parcelable parcelable = (Parcelable) it.next();
            if (parcelable instanceof SharePhoto) {
                arrayList.add((SharePhoto) parcelable);
            }
        }
        return arrayList;
    }

    @Nullable
    public String j(String str) {
        return this.a.getString(str);
    }

    @Nullable
    public ArrayList<String> k(String str) {
        return this.a.getStringArrayList(str);
    }

    public Bundle b() {
        return (Bundle) this.a.clone();
    }

    public Set<String> c() {
        return this.a.keySet();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.a);
    }
}
