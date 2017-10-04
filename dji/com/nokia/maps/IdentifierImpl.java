package com.nokia.maps;

import android.os.Parcel;
import com.here.android.mpa.common.Identifier;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;
import java.util.ArrayList;
import java.util.List;

@Online
public class IdentifierImpl extends BaseNativeObject {
    static k<Identifier, IdentifierImpl> a = null;
    static am<Identifier, IdentifierImpl> b = null;
    private cq c = new cq(IdentifierImpl.class.getName());

    public enum a {
        SINGLE,
        PAIR,
        STRING,
        STRING_ID;

        public static a[] a() {
            return (a[]) e.clone();
        }
    }

    private native void createIdentifierNative(int i, String str);

    private native void createIdentifierNative(IdentifierImpl identifierImpl);

    private native void destroyIdentifierNative();

    private native String getRawIdNative();

    private native int getTypeNative();

    private native boolean isEqualNative(IdentifierImpl identifierImpl);

    private native String toStringNative();

    static {
        ce.a(Identifier.class);
    }

    public static IdentifierImpl a(Identifier identifier) {
        if (a != null) {
            return (IdentifierImpl) a.a(identifier);
        }
        return null;
    }

    static IdentifierImpl[] a(List<Identifier> list) {
        if (list == null) {
            return null;
        }
        IdentifierImpl[] identifierImplArr = new IdentifierImpl[list.size()];
        int i = 0;
        for (Identifier identifier : list) {
            identifierImplArr[i] = identifier == null ? null : a(identifier);
            i++;
        }
        return identifierImplArr;
    }

    static Identifier a(IdentifierImpl identifierImpl) {
        if (identifierImpl != null) {
            return (Identifier) b.a(identifierImpl);
        }
        return null;
    }

    static List<Identifier> a(IdentifierImpl[] identifierImplArr) {
        List<Identifier> arrayList = new ArrayList(identifierImplArr.length);
        for (IdentifierImpl a : identifierImplArr) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    public static void a(k<Identifier, IdentifierImpl> kVar, am<Identifier, IdentifierImpl> amVar) {
        a = kVar;
        b = amVar;
    }

    @OnlineNative
    private IdentifierImpl(int i) {
        this.nativeptr = i;
    }

    public IdentifierImpl(a aVar, String str) {
        createIdentifierNative(aVar.ordinal(), str);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (IdentifierImpl.class.isInstance(obj)) {
            obj = (IdentifierImpl) obj;
        } else if (!Identifier.class.isInstance(obj)) {
            return false;
        } else {
            obj = a((Identifier) obj);
        }
        return isEqualNative(obj);
    }

    public int hashCode() {
        return toStringNative().hashCode() + 217;
    }

    public String toString() {
        return toStringNative();
    }

    public a a() {
        return a.a()[getTypeNative()];
    }

    public String b() {
        return getRawIdNative();
    }

    protected void finalize() {
        destroyIdentifierNative();
    }

    public static Identifier a(Parcel parcel) {
        return (Identifier) b.a(new IdentifierImpl(a.a()[parcel.readInt()], parcel.readString()));
    }

    public void b(Parcel parcel) {
        parcel.writeInt(getTypeNative());
        parcel.writeString(toStringNative());
    }
}
