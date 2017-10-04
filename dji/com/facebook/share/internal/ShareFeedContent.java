package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareContent;

public class ShareFeedContent extends ShareContent<ShareFeedContent, a> {
    public static final Creator<ShareFeedContent> CREATOR = new Creator<ShareFeedContent>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ShareFeedContent a(Parcel parcel) {
            return new ShareFeedContent(parcel);
        }

        public ShareFeedContent[] a(int i) {
            return new ShareFeedContent[i];
        }
    };
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;

    public static final class a extends com.facebook.share.model.ShareContent.a<ShareFeedContent, a> {
        private String a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;

        public /* synthetic */ Object a() {
            return b();
        }

        public /* synthetic */ com.facebook.share.model.a b(Parcel parcel) {
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

        public a c(String str) {
            this.c = str;
            return this;
        }

        public a d(String str) {
            this.d = str;
            return this;
        }

        public a e(String str) {
            this.e = str;
            return this;
        }

        public a f(String str) {
            this.f = str;
            return this;
        }

        public a g(String str) {
            this.g = str;
            return this;
        }

        public ShareFeedContent b() {
            return new ShareFeedContent();
        }

        public a a(ShareFeedContent shareFeedContent) {
            return shareFeedContent == null ? this : ((a) super.a((ShareContent) shareFeedContent)).a(shareFeedContent.a()).b(shareFeedContent.b()).c(shareFeedContent.c()).d(shareFeedContent.d()).e(shareFeedContent.e()).f(shareFeedContent.f()).g(shareFeedContent.g());
        }

        public a a(Parcel parcel) {
            return a((ShareFeedContent) parcel.readParcelable(ShareFeedContent.class.getClassLoader()));
        }
    }

    private ShareFeedContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
        this.g = aVar.g;
    }

    ShareFeedContent(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
    }
}
