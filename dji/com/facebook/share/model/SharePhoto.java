package com.facebook.share.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class SharePhoto extends ShareMedia {
    public static final Creator<SharePhoto> CREATOR = new Creator<SharePhoto>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public SharePhoto a(Parcel parcel) {
            return new SharePhoto(parcel);
        }

        public SharePhoto[] a(int i) {
            return new SharePhoto[i];
        }
    };
    private final Bitmap a;
    private final Uri b;
    private final boolean c;
    private final String d;

    public static final class a extends com.facebook.share.model.ShareMedia.a<SharePhoto, a> {
        private Bitmap a;
        private Uri b;
        private boolean c;
        private String d;

        public /* synthetic */ Object a() {
            return d();
        }

        public /* synthetic */ a b(Parcel parcel) {
            return a(parcel);
        }

        public a a(@Nullable Bitmap bitmap) {
            this.a = bitmap;
            return this;
        }

        public a a(@Nullable Uri uri) {
            this.b = uri;
            return this;
        }

        public a a(boolean z) {
            this.c = z;
            return this;
        }

        public a a(@Nullable String str) {
            this.d = str;
            return this;
        }

        Uri b() {
            return this.b;
        }

        Bitmap c() {
            return this.a;
        }

        public SharePhoto d() {
            return new SharePhoto();
        }

        public a a(SharePhoto sharePhoto) {
            return sharePhoto == null ? this : ((a) super.a((ShareMedia) sharePhoto)).a(sharePhoto.b()).a(sharePhoto.c()).a(sharePhoto.d()).a(sharePhoto.e());
        }

        public a a(Parcel parcel) {
            return a((SharePhoto) parcel.readParcelable(SharePhoto.class.getClassLoader()));
        }

        public static void a(Parcel parcel, List<SharePhoto> list) {
            List arrayList = new ArrayList();
            for (SharePhoto add : list) {
                arrayList.add(add);
            }
            parcel.writeTypedList(arrayList);
        }

        public static List<SharePhoto> c(Parcel parcel) {
            List<SharePhoto> arrayList = new ArrayList();
            parcel.readTypedList(arrayList, SharePhoto.CREATOR);
            return arrayList;
        }
    }

    private SharePhoto(a aVar) {
        super((com.facebook.share.model.ShareMedia.a) aVar);
        this.a = aVar.a;
        this.b = aVar.b;
        this.c = aVar.c;
        this.d = aVar.d;
    }

    SharePhoto(Parcel parcel) {
        super(parcel);
        this.a = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.b = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.c = parcel.readByte() != (byte) 0;
        this.d = parcel.readString();
    }

    @Nullable
    public Bitmap b() {
        return this.a;
    }

    @Nullable
    public Uri c() {
        return this.b;
    }

    public boolean d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 0;
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, 0);
        parcel.writeParcelable(this.b, 0);
        if (this.c) {
            i2 = 1;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.d);
    }
}
