package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareModel;

public class LikeContent implements ShareModel {
    public static final Creator<LikeContent> CREATOR = new Creator<LikeContent>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public LikeContent a(Parcel parcel) {
            return new LikeContent(parcel);
        }

        public LikeContent[] a(int i) {
            return new LikeContent[i];
        }
    };
    private final String a;
    private final String b;

    public static class a implements com.facebook.share.model.a<LikeContent, a> {
        private String a;
        private String b;

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

        public LikeContent b() {
            return new LikeContent();
        }

        public a a(LikeContent likeContent) {
            return likeContent == null ? this : a(likeContent.a()).b(likeContent.b());
        }

        public a a(Parcel parcel) {
            return a((LikeContent) parcel.readParcelable(LikeContent.class.getClassLoader()));
        }
    }

    private LikeContent(a aVar) {
        this.a = aVar.a;
        this.b = aVar.b;
    }

    LikeContent(Parcel parcel) {
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
