package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableDouble extends a implements Parcelable, Serializable {
    public static final Creator<ObservableDouble> CREATOR = new Creator<ObservableDouble>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ObservableDouble a(Parcel parcel) {
            return new ObservableDouble(parcel.readDouble());
        }

        public ObservableDouble[] a(int i) {
            return new ObservableDouble[i];
        }
    };
    static final long a = 1;
    private double b;

    public ObservableDouble(double d) {
        this.b = d;
    }

    public double b() {
        return this.b;
    }

    public void a(double d) {
        if (d != this.b) {
            this.b = d;
            a();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.b);
    }
}
