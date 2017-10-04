package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareVideoContent extends ShareContent<ShareVideoContent, a> implements ShareModel {
    public static final Creator<ShareVideoContent> CREATOR = new Creator<ShareVideoContent>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ShareVideoContent a(Parcel parcel) {
            return new ShareVideoContent(parcel);
        }

        public ShareVideoContent[] a(int i) {
            return new ShareVideoContent[i];
        }
    };
    private final String a;
    private final String b;
    private final SharePhoto c;
    private final ShareVideo d;

    public static final class a extends com.facebook.share.model.ShareContent.a<ShareVideoContent, a> {
        private String a;
        private String b;
        private SharePhoto c;
        private ShareVideo d;

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

        public a a(@Nullable SharePhoto sharePhoto) {
            this.c = sharePhoto == null ? null : new com.facebook.share.model.SharePhoto.a().a(sharePhoto).d();
            return this;
        }

        public a a(@Nullable ShareVideo shareVideo) {
            if (shareVideo != null) {
                this.d = new com.facebook.share.model.ShareVideo.a().a(shareVideo).b();
            }
            return this;
        }

        public ShareVideoContent b() {
            return new ShareVideoContent();
        }

        public a a(ShareVideoContent shareVideoContent) {
            return shareVideoContent == null ? this : ((a) super.a((ShareContent) shareVideoContent)).a(shareVideoContent.a()).b(shareVideoContent.b()).a(shareVideoContent.c()).a(shareVideoContent.d());
        }

        public a a(Parcel parcel) {
            return a((ShareVideoContent) parcel.readParcelable(ShareVideoContent.class.getClassLoader()));
        }
    }

    private ShareVideoContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
    }

    ShareVideoContent(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
        com.facebook.share.model.SharePhoto.a a = new com.facebook.share.model.SharePhoto.a().a(parcel);
        if (a.b() == null && a.c() == null) {
            this.c = null;
        } else {
            this.c = a.d();
        }
        this.d = new com.facebook.share.model.ShareVideo.a().a(parcel).b();
    }

    @Nullable
    public String a() {
        return this.a;
    }

    @Nullable
    public String b() {
        return this.b;
    }

    @Nullable
    public SharePhoto c() {
        return this.c;
    }

    @Nullable
    public ShareVideo d() {
        return this.d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeParcelable(this.c, 0);
        parcel.writeParcelable(this.d, 0);
    }
}
