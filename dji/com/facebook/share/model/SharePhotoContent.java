package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class SharePhotoContent extends ShareContent<SharePhotoContent, a> {
    public static final Creator<SharePhotoContent> CREATOR = new Creator<SharePhotoContent>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public SharePhotoContent a(Parcel parcel) {
            return new SharePhotoContent(parcel);
        }

        public SharePhotoContent[] a(int i) {
            return new SharePhotoContent[i];
        }
    };
    private final List<SharePhoto> a;

    public static class a extends com.facebook.share.model.ShareContent.a<SharePhotoContent, a> {
        private final List<SharePhoto> a = new ArrayList();

        public /* synthetic */ Object a() {
            return b();
        }

        public /* synthetic */ a b(Parcel parcel) {
            return a(parcel);
        }

        public a a(@Nullable SharePhoto sharePhoto) {
            if (sharePhoto != null) {
                this.a.add(new com.facebook.share.model.SharePhoto.a().a(sharePhoto).d());
            }
            return this;
        }

        public a b(@Nullable List<SharePhoto> list) {
            if (list != null) {
                for (SharePhoto a : list) {
                    a(a);
                }
            }
            return this;
        }

        public SharePhotoContent b() {
            return new SharePhotoContent();
        }

        public a a(SharePhotoContent sharePhotoContent) {
            return sharePhotoContent == null ? this : ((a) super.a((ShareContent) sharePhotoContent)).b(sharePhotoContent.a());
        }

        public a a(Parcel parcel) {
            return a((SharePhotoContent) parcel.readParcelable(SharePhotoContent.class.getClassLoader()));
        }

        public a c(@Nullable List<SharePhoto> list) {
            this.a.clear();
            b((List) list);
            return this;
        }
    }

    private SharePhotoContent(a aVar) {
        super((com.facebook.share.model.ShareContent.a) aVar);
        this.a = Collections.unmodifiableList(aVar.a);
    }

    SharePhotoContent(Parcel parcel) {
        super(parcel);
        this.a = Collections.unmodifiableList(com.facebook.share.model.SharePhoto.a.c(parcel));
    }

    @Nullable
    public List<SharePhoto> a() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        com.facebook.share.model.SharePhoto.a.a(parcel, this.a);
    }
}
