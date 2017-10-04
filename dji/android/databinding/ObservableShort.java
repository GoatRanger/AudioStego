package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableShort extends a implements Parcelable, Serializable {
    public static final Creator<ObservableShort> CREATOR = new Creator<ObservableShort>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ObservableShort a(Parcel parcel) {
            return new ObservableShort((short) parcel.readInt());
        }

        public ObservableShort[] a(int i) {
            return new ObservableShort[i];
        }
    };
    static final long a = 1;
    private short b;

    public ObservableShort(short s) {
        this.b = s;
    }

    public short b() {
        return this.b;
    }

    public void a(short s) {
        if (s != this.b) {
            this.b = s;
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
