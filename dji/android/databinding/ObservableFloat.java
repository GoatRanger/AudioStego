package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableFloat extends a implements Parcelable, Serializable {
    public static final Creator<ObservableFloat> CREATOR = new Creator<ObservableFloat>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ObservableFloat a(Parcel parcel) {
            return new ObservableFloat(parcel.readFloat());
        }

        public ObservableFloat[] a(int i) {
            return new ObservableFloat[i];
        }
    };
    static final long a = 1;
    private float b;

    public ObservableFloat(float f) {
        this.b = f;
    }

    public float b() {
        return this.b;
    }

    public void a(float f) {
        if (f != this.b) {
            this.b = f;
            a();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.b);
    }
}
