package com.facebook;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.internal.ah;
import com.facebook.internal.ah.c;
import com.facebook.internal.ai;
import com.facebook.internal.r;
import org.json.JSONException;
import org.json.JSONObject;

public final class Profile implements Parcelable {
    public static final Creator<Profile> CREATOR = new Creator() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public Profile a(Parcel parcel) {
            return new Profile(parcel);
        }

        public Profile[] a(int i) {
            return new Profile[i];
        }
    };
    private static final String a = "id";
    private static final String b = "first_name";
    private static final String c = "middle_name";
    private static final String d = "last_name";
    private static final String e = "name";
    private static final String f = "link_uri";
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final Uri l;

    public static Profile a() {
        return aa.a().b();
    }

    public static void a(Profile profile) {
        aa.a().a(profile);
    }

    public static void b() {
        AccessToken a = AccessToken.a();
        if (a == null) {
            a(null);
        } else {
            ah.a(a.c(), new c() {
                public void onSuccess(JSONObject jSONObject) {
                    String optString = jSONObject.optString("id");
                    if (optString != null) {
                        String optString2 = jSONObject.optString("link");
                        Profile.a(new Profile(optString, jSONObject.optString("first_name"), jSONObject.optString(Profile.c), jSONObject.optString("last_name"), jSONObject.optString("name"), optString2 != null ? Uri.parse(optString2) : null));
                    }
                }

                public void onFailure(k kVar) {
                }
            });
        }
    }

    public Profile(String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Uri uri) {
        ai.a(str, "id");
        this.g = str;
        this.h = str2;
        this.i = str3;
        this.j = str4;
        this.k = str5;
        this.l = uri;
    }

    public Uri a(int i, int i2) {
        return r.a(this.g, i, i2);
    }

    public String c() {
        return this.g;
    }

    public String d() {
        return this.h;
    }

    public String e() {
        return this.i;
    }

    public String f() {
        return this.j;
    }

    public String g() {
        return this.k;
    }

    public Uri h() {
        return this.l;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Profile)) {
            return false;
        }
        Profile profile = (Profile) obj;
        if (this.g.equals(profile.g) && this.h == null) {
            if (profile.h != null) {
                return false;
            }
            return true;
        } else if (this.h.equals(profile.h) && this.i == null) {
            if (profile.i != null) {
                return false;
            }
            return true;
        } else if (this.i.equals(profile.i) && this.j == null) {
            if (profile.j != null) {
                return false;
            }
            return true;
        } else if (this.j.equals(profile.j) && this.k == null) {
            if (profile.k != null) {
                return false;
            }
            return true;
        } else if (!this.k.equals(profile.k) || this.l != null) {
            return this.l.equals(profile.l);
        } else {
            if (profile.l != null) {
                return false;
            }
            return true;
        }
    }

    public int hashCode() {
        int hashCode = this.g.hashCode() + 527;
        if (this.h != null) {
            hashCode = (hashCode * 31) + this.h.hashCode();
        }
        if (this.i != null) {
            hashCode = (hashCode * 31) + this.i.hashCode();
        }
        if (this.j != null) {
            hashCode = (hashCode * 31) + this.j.hashCode();
        }
        if (this.k != null) {
            hashCode = (hashCode * 31) + this.k.hashCode();
        }
        if (this.l != null) {
            return (hashCode * 31) + this.l.hashCode();
        }
        return hashCode;
    }

    JSONObject i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.g);
            jSONObject.put("first_name", this.h);
            jSONObject.put(c, this.i);
            jSONObject.put("last_name", this.j);
            jSONObject.put("name", this.k);
            if (this.l == null) {
                return jSONObject;
            }
            jSONObject.put(f, this.l.toString());
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    Profile(JSONObject jSONObject) {
        Uri uri = null;
        this.g = jSONObject.optString("id", null);
        this.h = jSONObject.optString("first_name", null);
        this.i = jSONObject.optString(c, null);
        this.j = jSONObject.optString("last_name", null);
        this.k = jSONObject.optString("name", null);
        String optString = jSONObject.optString(f, null);
        if (optString != null) {
            uri = Uri.parse(optString);
        }
        this.l = uri;
    }

    private Profile(Parcel parcel) {
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readString();
        String readString = parcel.readString();
        this.l = readString == null ? null : Uri.parse(readString);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeString(this.k);
        parcel.writeString(this.l == null ? null : this.l.toString());
    }
}
