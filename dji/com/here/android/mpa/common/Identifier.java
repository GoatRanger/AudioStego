package com.here.android.mpa.common;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.nokia.maps.IdentifierImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.k;

@Online
public final class Identifier implements Parcelable {
    public static final Creator<Identifier> CREATOR = new Creator<Identifier>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return a(i);
        }

        public Identifier a(Parcel parcel) {
            return IdentifierImpl.a(parcel);
        }

        public Identifier[] a(int i) {
            return new Identifier[i];
        }
    };
    private IdentifierImpl a;

    private Identifier(IdentifierImpl identifierImpl) {
        this.a = identifierImpl;
    }

    public String toString() {
        return this.a.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (Identifier.class.isInstance(obj)) {
            return this.a.equals(obj);
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() + 217;
    }

    static {
        IdentifierImpl.a(new k<Identifier, IdentifierImpl>() {
            public IdentifierImpl a(Identifier identifier) {
                return identifier.a;
            }
        }, new am<Identifier, IdentifierImpl>() {
            public Identifier a(IdentifierImpl identifierImpl) {
                if (identifierImpl != null) {
                    return new Identifier(identifierImpl);
                }
                return null;
            }
        });
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.a.b(parcel);
    }
}
