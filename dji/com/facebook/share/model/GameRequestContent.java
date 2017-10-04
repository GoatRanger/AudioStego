package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import java.util.Arrays;
import java.util.List;

public final class GameRequestContent implements ShareModel {
    public static final Creator<GameRequestContent> CREATOR = new Creator<GameRequestContent>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public GameRequestContent a(Parcel parcel) {
            return new GameRequestContent(parcel);
        }

        public GameRequestContent[] a(int i) {
            return new GameRequestContent[i];
        }
    };
    private final String a;
    private final List<String> b;
    private final String c;
    private final String d;
    private final a e;
    private final String f;
    private final c g;
    private final List<String> h;

    public enum a {
        SEND,
        ASKFOR,
        TURN
    }

    public static class b implements a<GameRequestContent, b> {
        private String a;
        private List<String> b;
        private String c;
        private String d;
        private a e;
        private String f;
        private c g;
        private List<String> h;

        public /* synthetic */ Object a() {
            return b();
        }

        public /* synthetic */ a b(Parcel parcel) {
            return a(parcel);
        }

        public b a(String str) {
            this.a = str;
            return this;
        }

        public b b(String str) {
            if (str != null) {
                this.b = Arrays.asList(str.split(","));
            }
            return this;
        }

        public b a(List<String> list) {
            this.b = list;
            return this;
        }

        public b c(String str) {
            this.c = str;
            return this;
        }

        public b d(String str) {
            this.d = str;
            return this;
        }

        public b a(a aVar) {
            this.e = aVar;
            return this;
        }

        public b e(String str) {
            this.f = str;
            return this;
        }

        public b a(c cVar) {
            this.g = cVar;
            return this;
        }

        public b b(List<String> list) {
            this.h = list;
            return this;
        }

        public GameRequestContent b() {
            return new GameRequestContent();
        }

        public b a(GameRequestContent gameRequestContent) {
            return gameRequestContent == null ? this : a(gameRequestContent.a()).a(gameRequestContent.c()).d(gameRequestContent.d()).c(gameRequestContent.e()).a(gameRequestContent.f()).e(gameRequestContent.g()).a(gameRequestContent.h()).b(gameRequestContent.i());
        }

        public b a(Parcel parcel) {
            return a((GameRequestContent) parcel.readParcelable(GameRequestContent.class.getClassLoader()));
        }
    }

    public enum c {
        APP_USERS,
        APP_NON_USERS
    }

    private GameRequestContent(b bVar) {
        this.a = bVar.a;
        this.b = bVar.b;
        this.c = bVar.d;
        this.d = bVar.c;
        this.e = bVar.e;
        this.f = bVar.f;
        this.g = bVar.g;
        this.h = bVar.h;
    }

    GameRequestContent(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.createStringArrayList();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = (a) parcel.readSerializable();
        this.f = parcel.readString();
        this.g = (c) parcel.readSerializable();
        this.h = parcel.createStringArrayList();
        parcel.readStringList(this.h);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return c() != null ? TextUtils.join(",", c()) : null;
    }

    public List<String> c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public String e() {
        return this.d;
    }

    public a f() {
        return this.e;
    }

    public String g() {
        return this.f;
    }

    public c h() {
        return this.g;
    }

    public List<String> i() {
        return this.h;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeStringList(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeSerializable(this.e);
        parcel.writeString(this.f);
        parcel.writeSerializable(this.g);
        parcel.writeStringList(this.h);
    }
}
