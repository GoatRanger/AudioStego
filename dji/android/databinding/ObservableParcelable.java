package android.databinding;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;

public class ObservableParcelable<T extends Parcelable> extends v<T> implements Parcelable, Serializable {
    public static final Creator<ObservableParcelable> CREATOR = new Creator<ObservableParcelable>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public ObservableParcelable a(Parcel parcel) {
            return new ObservableParcelable(parcel.readParcelable(getClass().getClassLoader()));
        }

        public ObservableParcelable[] a(int i) {
            return new ObservableParcelable[i];
        }
    };
    static final long b = 1;

    public ObservableParcelable(T t) {
        super(t);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable((Parcelable) b(), 0);
    }
}
