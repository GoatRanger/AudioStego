package com.nokia.maps;

import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.Place;
import com.here.android.mpa.search.ResultListener;
import java.util.concurrent.atomic.AtomicBoolean;

class do$3 implements ResultListener<Place> {
    final /* synthetic */ AtomicBoolean a;
    final /* synthetic */ do b;

    do$3(do doVar, AtomicBoolean atomicBoolean) {
        this.b = doVar;
        this.a = atomicBoolean;
    }

    public /* synthetic */ void onCompleted(Object obj, ErrorCode errorCode) {
        a((Place) obj, errorCode);
    }

    public void a(Place place, ErrorCode errorCode) {
        if (errorCode == ErrorCode.NONE) {
            place.getLocation().getAddress().setText(place.getLocation().getAddress().getText().replaceAll("<br/>", ", "));
            this.b.g = place.getLocation().getAddress();
        }
        this.a.set(true);
    }
}
