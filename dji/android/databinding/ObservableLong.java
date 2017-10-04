package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableLong extends a implements Parcelable, Serializable {
    public static final Creator<ObservableLong> CREATOR = new Creator<ObservableLong>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ObservableLong a(Parcel parcel) {
            return new ObservableLong(parcel.readLong());
        }

        public ObservableLong[] a(int i) {
            return new ObservableLong[i];
        }
    };
    static final long a = 1;
    private long b;

    public ObservableLong(long j) {
        this.b = j;
    }

    public long b() {
        return this.b;
    }

    public void a(long j) {
        if (j != this.b) {
            this.b = j;
            a();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.b);
    }
}
