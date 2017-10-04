package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableInt extends a implements Parcelable, Serializable {
    public static final Creator<ObservableInt> CREATOR = new Creator<ObservableInt>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ObservableInt a(Parcel parcel) {
            return new ObservableInt(parcel.readInt());
        }

        public ObservableInt[] a(int i) {
            return new ObservableInt[i];
        }
    };
    static final long a = 1;
    private int b;

    public ObservableInt(int i) {
        this.b = i;
    }

    public int b() {
        return this.b;
    }

    public void b(int i) {
        if (i != this.b) {
            this.b = i;
            a();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b);
    }
}
