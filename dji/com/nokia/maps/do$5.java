package com.nokia.maps;

import com.here.android.mpa.search.Address;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.Location;
import com.here.android.mpa.search.ResultListener;
import com.nokia.maps.dd.c;
import java.util.List;

class do$5 extends bi {
    final /* synthetic */ ResultListener a;
    final /* synthetic */ do b;

    do$5(do doVar, c cVar, ResultListener resultListener) {
        this.b = doVar;
        this.a = resultListener;
        super(cVar);
    }

    protected void a(List<Location> list) {
        if (this.a != null) {
            final Address address = list.size() > 0 ? ((Location) list.get(0)).getAddress() : null;
            ez.a(new Runnable(this) {
                final /* synthetic */ do$5 b;

                public void run() {
                    do.a(this.b.b, this.b.a, address, ErrorCode.NONE);
                }
            });
        }
    }

    protected void a(final ErrorCode errorCode) {
        if (this.a != null) {
            ez.a(new Runnable(this) {
                final /* synthetic */ do$5 b;

                public void run() {
                    do.a(this.b.b, this.b.a, null, errorCode);
                }
            });
        }
    }
}
