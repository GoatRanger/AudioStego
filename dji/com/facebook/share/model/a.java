package com.facebook.share.model;

import android.os.Parcel;
import com.facebook.share.b;

public interface a<P extends ShareModel, E extends a> extends b<P, E> {
    E a(P p);

    E b(Parcel parcel);
}
