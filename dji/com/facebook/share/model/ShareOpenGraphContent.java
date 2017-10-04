package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareOpenGraphContent extends ShareContent<ShareOpenGraphContent, a> {
    public static final Creator<ShareOpenGraphContent> CREATOR = new Creator<ShareOpenGraphContent>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ShareOpenGraphContent a(Parcel parcel) {
            return new ShareOpenGraphContent(parcel);
        }

        public ShareOpenGraphContent[] a(int i) {
            return new ShareOpenGraphContent[i];
        }
    };
    private final ShareOpenGraphAction a;
    private final String b;

    public static final class a extends com.facebook.share.model.ShareContent.a<ShareOpenGraphContent, a> {
        private ShareOpenGraphAction a;
        private String b;

        public /* synthetic */ Object a() {
            return b();
        }

        public /* synthetic */ a b(Parcel parcel) {
            return a(parcel);
        }

        public a a(@Nullable ShareOpenGraphAction shareOpenGraphAction) {
            this.a = shareOpenGraphAction == null ? null : new com.facebook.share.model.ShareOpenGraphAction.a().a(shareOpenGraphAction).b();
            return this;
        }

        public a a(@Nullable String str) {
            this.b = str;
            return this;
        }

        public ShareOpenGraphContent b() {
            return new ShareOpenGraphContent();
        }

        public a a(ShareOpenGraphContent shareOpenGraphContent) {
            return shareOpenGraphContent == null ? this : ((a) super.a((ShareContent) shareOpenGraphContent)).a(shareOpenGraphContent.a()).a(shareOpenGraphContent.b());
        }

        public a a(Parcel parcel) {
            return a((ShareOpenGraphContent) parcel.readParcelable(ShareOpenGraphContent.class.getClassLoader()));
        }
    }

    private ShareOpenGraphContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.a = aVar.a;
        this.b = aVar.b;
    }

    ShareOpenGraphContent(Parcel parcel) {
        super(parcel);
        this.a = new com.facebook.share.model.ShareOpenGraphAction.a().a(parcel).b();
        this.b = parcel.readString();
    }

    @Nullable
    public ShareOpenGraphAction a() {
        return this.a;
    }

    @Nullable
    public String b() {
        return this.b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, 0);
        parcel.writeString(this.b);
    }
}
