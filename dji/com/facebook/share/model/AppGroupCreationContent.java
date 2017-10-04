package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class AppGroupCreationContent implements ShareModel {
    public static final Creator<AppGroupCreationContent> CREATOR = new Creator<AppGroupCreationContent>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public AppGroupCreationContent a(Parcel parcel) {
            return new AppGroupCreationContent(parcel);
        }

        public AppGroupCreationContent[] a(int i) {
            return new AppGroupCreationContent[i];
        }
    };
    private final String a;
    private final String b;
    private a c;

    public enum a {
        Open,
        Closed
    }

    public static class b implements a<AppGroupCreationContent, b> {
        private String a;
        private String b;
        private a c;

        public /* synthetic */ Object a() {
            return b();
        }

        public /* synthetic */ a b(Parcel parcel) {
            return a(parcel);
        }

        public b a(String str) {
            this.a = str;
            return this;
        }

        public b b(String str) {
            this.b = str;
            return this;
        }

        public b a(a aVar) {
            this.c = aVar;
            return this;
        }

        public AppGroupCreationContent b() {
            return new AppGroupCreationContent();
        }

        public b a(AppGroupCreationContent appGroupCreationContent) {
            return appGroupCreationContent == null ? this : a(appGroupCreationContent.a()).b(appGroupCreationContent.b()).a(appGroupCreationContent.c());
        }

        public b a(Parcel parcel) {
            return a((AppGroupCreationContent) parcel.readParcelable(AppGroupCreationContent.class.getClassLoader()));
        }
    }

    private AppGroupCreationContent(b bVar) {
        this.a = bVar.a;
        this.b = bVar.b;
        this.c = bVar.c;
    }

    AppGroupCreationContent(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = (a) parcel.readSerializable();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public a c() {
        return this.c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeSerializable(this.c);
    }
}
