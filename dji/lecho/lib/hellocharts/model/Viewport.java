package lecho.lib.hellocharts.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import dji.pilot.visual.a.d;

public class Viewport implements Parcelable {
    public static final Creator<Viewport> CREATOR = new Creator<Viewport>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public Viewport a(Parcel parcel) {
            Viewport viewport = new Viewport();
            viewport.a(parcel);
            return viewport;
        }

        public Viewport[] a(int i) {
            return new Viewport[i];
        }
    };
    public float a;
    public float b;
    public float c;
    public float d;

    public Viewport(float f, float f2, float f3, float f4) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public Viewport(Viewport viewport) {
        if (viewport == null) {
            this.d = 0.0f;
            this.c = 0.0f;
            this.b = 0.0f;
            this.a = 0.0f;
            return;
        }
        this.a = viewport.a;
        this.b = viewport.b;
        this.c = viewport.c;
        this.d = viewport.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Viewport viewport = (Viewport) obj;
        if (Float.floatToIntBits(this.d) != Float.floatToIntBits(viewport.d)) {
            return false;
        }
        if (Float.floatToIntBits(this.a) != Float.floatToIntBits(viewport.a)) {
            return false;
        }
        if (Float.floatToIntBits(this.c) != Float.floatToIntBits(viewport.c)) {
            return false;
        }
        if (Float.floatToIntBits(this.b) != Float.floatToIntBits(viewport.b)) {
            return false;
        }
        return true;
    }

    public final boolean a() {
        return this.a >= this.c || this.d >= this.b;
    }

    public void b() {
        this.d = 0.0f;
        this.b = 0.0f;
        this.c = 0.0f;
        this.a = 0.0f;
    }

    public final float c() {
        return this.c - this.a;
    }

    public final float d() {
        return this.b - this.d;
    }

    public final float e() {
        return (this.a + this.c) * d.c;
    }

    public final float f() {
        return (this.b + this.d) * d.c;
    }

    public void a(float f, float f2, float f3, float f4) {
        this.a = f;
        this.b = f2;
        this.c = f3;
        this.d = f4;
    }

    public void a(Viewport viewport) {
        this.a = viewport.a;
        this.b = viewport.b;
        this.c = viewport.c;
        this.d = viewport.d;
    }

    public void a(float f, float f2) {
        this.a += f;
        this.b += f2;
        this.c += f;
        this.d += f2;
    }

    public void b(float f, float f2) {
        this.c += f - this.a;
        this.d += f2 - this.b;
        this.a = f;
        this.b = f2;
    }

    public void c(float f, float f2) {
        this.a += f;
        this.b -= f2;
        this.c -= f;
        this.d += f2;
    }

    public boolean d(float f, float f2) {
        return this.a < this.c && this.d < this.b && f >= this.a && f < this.c && f2 >= this.d && f2 < this.b;
    }

    public boolean b(float f, float f2, float f3, float f4) {
        return this.a < this.c && this.d < this.b && this.a <= f && this.b >= f2 && this.c >= f3 && this.d <= f4;
    }

    public boolean b(Viewport viewport) {
        return this.a < this.c && this.d < this.b && this.a <= viewport.a && this.b >= viewport.b && this.c >= viewport.c && this.d <= viewport.d;
    }

    public void c(float f, float f2, float f3, float f4) {
        if (f < f3 && f4 < f2) {
            if (this.a >= this.c || this.d >= this.b) {
                this.a = f;
                this.b = f2;
                this.c = f3;
                this.d = f4;
                return;
            }
            if (this.a > f) {
                this.a = f;
            }
            if (this.b < f2) {
                this.b = f2;
            }
            if (this.c < f3) {
                this.c = f3;
            }
            if (this.d > f4) {
                this.d = f4;
            }
        }
    }

    public void c(Viewport viewport) {
        c(viewport.a, viewport.b, viewport.c, viewport.d);
    }

    public boolean d(float f, float f2, float f3, float f4) {
        if (this.a >= f3 || f >= this.c || this.d >= f2 || f4 >= this.b) {
            return false;
        }
        if (this.a < f) {
            this.a = f;
        }
        if (this.b > f2) {
            this.b = f2;
        }
        if (this.c > f3) {
            this.c = f3;
        }
        if (this.d < f4) {
            this.d = f4;
        }
        return true;
    }

    public boolean d(Viewport viewport) {
        return d(viewport.a, viewport.b, viewport.c, viewport.d);
    }

    public String toString() {
        return "Viewport [left=" + this.a + ", top=" + this.b + ", right=" + this.c + ", bottom=" + this.d + dji.pilot.usercenter.protocol.d.H;
    }

    public int hashCode() {
        return ((((((Float.floatToIntBits(this.d) + 31) * 31) + Float.floatToIntBits(this.a)) * 31) + Float.floatToIntBits(this.c)) * 31) + Float.floatToIntBits(this.b);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.a);
        parcel.writeFloat(this.b);
        parcel.writeFloat(this.c);
        parcel.writeFloat(this.d);
    }

    public void a(Parcel parcel) {
        this.a = parcel.readFloat();
        this.b = parcel.readFloat();
        this.c = parcel.readFloat();
        this.d = parcel.readFloat();
    }
}
