package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareOpenGraphAction extends ShareOpenGraphValueContainer<ShareOpenGraphAction, a> {
    public static final Creator<ShareOpenGraphAction> CREATOR = new Creator<ShareOpenGraphAction>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ShareOpenGraphAction a(Parcel parcel) {
            return new ShareOpenGraphAction(parcel);
        }

        public ShareOpenGraphAction[] a(int i) {
            return new ShareOpenGraphAction[i];
        }
    };

    public static final class a extends com.facebook.share.model.ShareOpenGraphValueContainer.a<ShareOpenGraphAction, a> {
        private static final String a = "og:type";

        public /* synthetic */ Object a() {
            return b();
        }

        public /* synthetic */ a b(Parcel parcel) {
            return a(parcel);
        }

        public a a(String str) {
            a(a, str);
            return this;
        }

        public ShareOpenGraphAction b() {
            return new ShareOpenGraphAction();
        }

        public a a(ShareOpenGraphAction shareOpenGraphAction) {
            return shareOpenGraphAction == null ? this : ((a) super.a((ShareOpenGraphValueContainer) shareOpenGraphAction)).a(shareOpenGraphAction.a());
        }

        public a a(Parcel parcel) {
            return a((ShareOpenGraphAction) parcel.readParcelable(ShareOpenGraphAction.class.getClassLoader()));
        }
    }

    private ShareOpenGraphAction(a aVar) {
        super((com.facebook.share.model.ShareOpenGraphValueContainer.a) aVar);
    }

    ShareOpenGraphAction(Parcel parcel) {
        super(parcel);
    }

    @Nullable
    public String a() {
        return j("og:type");
    }
}
