package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;

public abstract class ShareMedia implements ShareModel {
    private final Bundle a;

    public static abstract class a<M extends ShareMedia, B extends a> implements a<M, B> {
        private Bundle a = new Bundle();

        @Deprecated
        public B a(String str, String str2) {
            this.a.putString(str, str2);
            return this;
        }

        @Deprecated
        public B a(Bundle bundle) {
            this.a.putAll(bundle);
            return this;
        }

        public B a(M m) {
            return m == null ? this : a(m.a());
        }
    }

    protected ShareMedia(a aVar) {
        this.a = new Bundle(aVar.a);
    }

    ShareMedia(Parcel parcel) {
        this.a = parcel.readBundle();
    }

    @Deprecated
    public Bundle a() {
        return new Bundle(this.a);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.a);
    }
}
