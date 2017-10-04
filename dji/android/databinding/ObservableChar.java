package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableChar extends a implements Parcelable, Serializable {
    public static final Creator<ObservableChar> CREATOR = new Creator<ObservableChar>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ObservableChar a(Parcel parcel) {
            return new ObservableChar((char) parcel.readInt());
        }

        public ObservableChar[] a(int i) {
            return new ObservableChar[i];
        }
    };
    static final long a = 1;
    private char b;

    public ObservableChar(char c) {
        this.b = c;
    }

    public char b() {
        return this.b;
    }

    public void a(char c) {
        if (c != this.b) {
            this.b = c;
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
