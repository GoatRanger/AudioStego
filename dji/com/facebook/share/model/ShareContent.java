package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class ShareContent<P extends ShareContent, E extends a> implements ShareModel {
    private final Uri a;
    private final List<String> b;
    private final String c;
    private final String d;

    public static abstract class a<P extends ShareContent, E extends a> implements a<P, E> {
        private Uri a;
        private List<String> b;
        private String c;
        private String d;

        public E a(@Nullable Uri uri) {
            this.a = uri;
            return this;
        }

        public E a(@Nullable List<String> list) {
            this.b = list == null ? null : Collections.unmodifiableList(list);
            return this;
        }

        public E h(@Nullable String str) {
            this.c = str;
            return this;
        }

        public E i(@Nullable String str) {
            this.d = str;
            return this;
        }

        public E a(P p) {
            return p == null ? this : a(p.h()).a(p.i()).h(p.j()).i(p.k());
        }
    }

    protected ShareContent(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
    }

    protected ShareContent(Parcel parcel) {
        this.a = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.b = a(parcel);
        this.c = parcel.readString();
        this.d = parcel.readString();
    }

    @Nullable
    public Uri h() {
        return this.a;
    }

    @Nullable
    public List<String> i() {
        return this.b;
    }

    @Nullable
    public String j() {
        return this.c;
    }

    @Nullable
    public String k() {
        return this.d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, 0);
        parcel.writeStringList(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }

    private List<String> a(Parcel parcel) {
        List arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        return arrayList.size() == 0 ? null : Collections.unmodifiableList(arrayList);
    }
}
