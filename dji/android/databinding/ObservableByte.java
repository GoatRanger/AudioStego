package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableByte extends a implements Parcelable, Serializable {
    public static final Creator<ObservableByte> CREATOR = new Creator<ObservableByte>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ObservableByte a(Parcel parcel) {
            return new ObservableByte(parcel.readByte());
        }

        public ObservableByte[] a(int i) {
            return new ObservableByte[i];
        }
    };
    static final long a = 1;
    private byte b;

    public ObservableByte(byte b) {
        this.b = b;
    }

    public byte b() {
        return this.b;
    }

    public void a(byte b) {
        if (b != this.b) {
            this.b = b;
            a();
        }
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.b);
    }
}
