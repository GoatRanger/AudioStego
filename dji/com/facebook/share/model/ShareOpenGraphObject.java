package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.internal.ab;

public final class ShareOpenGraphObject extends ShareOpenGraphValueContainer<ShareOpenGraphObject, a> {
    public static final Creator<ShareOpenGraphObject> CREATOR = new Creator<ShareOpenGraphObject>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ShareOpenGraphObject a(Parcel parcel) {
            return new ShareOpenGraphObject(parcel);
        }

        public ShareOpenGraphObject[] a(int i) {
            return new ShareOpenGraphObject[i];
        }
    };

    public static final class a extends com.facebook.share.model.ShareOpenGraphValueContainer.a<ShareOpenGraphObject, a> {
        public /* synthetic */ Object a() {
            return b();
        }

        public /* synthetic */ a b(Parcel parcel) {
            return a(parcel);
        }

        public a() {
            a(ab.ad, true);
        }

        public ShareOpenGraphObject b() {
            return new ShareOpenGraphObject();
        }

        public a a(Parcel parcel) {
            return (a) a((ShareOpenGraphValueContainer) (ShareOpenGraphObject) parcel.readParcelable(ShareOpenGraphObject.class.getClassLoader()));
        }
    }

    private ShareOpenGraphObject(a aVar) {
        super((com.facebook.share.model.ShareOpenGraphValueContainer.a) aVar);
    }

    ShareOpenGraphObject(Parcel parcel) {
        super(parcel);
    }
}
