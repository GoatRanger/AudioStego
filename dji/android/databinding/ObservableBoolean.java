package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableBoolean extends a implements Parcelable, Serializable {
    public static final Creator<ObservableBoolean> CREATOR = new Creator<ObservableBoolean>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ObservableBoolean a(Parcel parcel) {
            boolean z = true;
            if (parcel.readInt() != 1) {
                z = false;
            }
            return new ObservableBoolean(z);
        }

        public ObservableBoolean[] a(int i) {
            return new ObservableBoolean[i];
        }
    };
    static final long a = 1;
    private boolean b;

    public ObservableBoolean(boolean z) {
        this.b = z;
    }

    public boolean b() {
        return this.b;
    }

    public void a(boolean z) {
        if (z != this.b) {
            this.b = z;
            a();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.b ? 1 : 0);
    }
}
