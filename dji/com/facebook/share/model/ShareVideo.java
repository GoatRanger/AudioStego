package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareVideo extends ShareMedia {
    public static final Creator<ShareVideo> CREATOR = new Creator<ShareVideo>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ShareVideo a(Parcel parcel) {
            return new ShareVideo(parcel);
        }

        public ShareVideo[] a(int i) {
            return new ShareVideo[i];
        }
    };
    private final Uri a;

    public static final class a extends com.facebook.share.model.ShareMedia.a<ShareVideo, a> {
        private Uri a;

        public /* synthetic */ Object a() {
            return b();
        }

        public /* synthetic */ a b(Parcel parcel) {
            return a(parcel);
        }

        public a a(@Nullable Uri uri) {
            this.a = uri;
            return this;
        }

        public ShareVideo b() {
            return new ShareVideo();
        }

        public a a(ShareVideo shareVideo) {
            return shareVideo == null ? this : ((a) super.a((ShareMedia) shareVideo)).a(shareVideo.b());
        }

        public a a(Parcel parcel) {
            return a((ShareVideo) parcel.readParcelable(ShareVideo.class.getClassLoader()));
        }
    }

    private ShareVideo(a aVar) {
        super((com.facebook.share.model.ShareMedia.a) aVar);
        this.a = aVar.a;
    }

    ShareVideo(Parcel parcel) {
        super(parcel);
        this.a = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
    }

    @Nullable
    public Uri b() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, 0);
    }
}
