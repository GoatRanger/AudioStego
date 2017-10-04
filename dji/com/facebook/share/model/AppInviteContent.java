package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class AppInviteContent implements ShareModel {
    public static final Creator<AppInviteContent> CREATOR = new Creator<AppInviteContent>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public AppInviteContent a(Parcel parcel) {
            return new AppInviteContent(parcel);
        }

        public AppInviteContent[] a(int i) {
            return new AppInviteContent[i];
        }
    };
    private final String a;
    private final String b;

    public static class a implements a<AppInviteContent, a> {
        private String a;
        private String b;

        public /* synthetic */ Object a() {
            return b();
        }

        public /* synthetic */ a b(Parcel parcel) {
            return a(parcel);
        }

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public AppInviteContent b() {
            return new AppInviteContent();
        }

        public a a(AppInviteContent appInviteContent) {
            return appInviteContent == null ? this : a(appInviteContent.a()).b(appInviteContent.b());
        }

        public a a(Parcel parcel) {
            return a((AppInviteContent) parcel.readParcelable(AppInviteContent.class.getClassLoader()));
        }
    }

    private AppInviteContent(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
    }

    AppInviteContent(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
