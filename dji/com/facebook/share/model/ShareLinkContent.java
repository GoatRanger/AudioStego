package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareLinkContent extends ShareContent<ShareLinkContent, a> {
    public static final Creator<ShareLinkContent> CREATOR = new Creator<ShareLinkContent>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ShareLinkContent a(Parcel parcel) {
            return new ShareLinkContent(parcel);
        }

        public ShareLinkContent[] a(int i) {
            return new ShareLinkContent[i];
        }
    };
    private final String a;
    private final String b;
    private final Uri c;

    public static final class a extends com.facebook.share.model.ShareContent.a<ShareLinkContent, a> {
        private String a;
        private String b;
        private Uri c;

        public /* synthetic */ Object a() {
            return b();
        }

        public /* synthetic */ a b(Parcel parcel) {
            return a(parcel);
        }

        public a a(@Nullable String str) {
            this.a = str;
            return this;
        }

        public a b(@Nullable String str) {
            this.b = str;
            return this;
        }

        public a b(@Nullable Uri uri) {
            this.c = uri;
            return this;
        }

        public ShareLinkContent b() {
            return new ShareLinkContent();
        }

        public a a(ShareLinkContent shareLinkContent) {
            return shareLinkContent == null ? this : ((a) super.a((ShareContent) shareLinkContent)).a(shareLinkContent.a()).b(shareLinkContent.c()).b(shareLinkContent.b());
        }

        public a a(Parcel parcel) {
            return a((ShareLinkContent) parcel.readParcelable(ShareLinkContent.class.getClassLoader()));
        }
    }

    private ShareLinkContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
    }

    ShareLinkContent(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    public String a() {
        return this.a;
    }

    @Nullable
    public String b() {
        return this.b;
    }

    @Nullable
    public Uri c() {
        return this.c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeParcelable(this.c, 0);
    }
}
