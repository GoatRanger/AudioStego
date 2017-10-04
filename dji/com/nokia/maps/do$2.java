package com.nokia.maps;

import com.here.android.mpa.search.DiscoveryResultPage;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.ResultListener;

class do$2 implements ResultListener<DiscoveryResultPage> {
    final /* synthetic */ do a;

    do$2(do doVar) {
        this.a = doVar;
    }

    public /* synthetic */ void onCompleted(Object obj, ErrorCode errorCode) {
        a((DiscoveryResultPage) obj, errorCode);
    }

    public void a(final DiscoveryResultPage discoveryResultPage, final ErrorCode errorCode) {
        do.a(this.a, null);
        if (errorCode != ErrorCode.NONE) {
            ez.a(new Runnable(this) {
                final /* synthetic */ do$2 b;

                public void run() {
                    if (do.a(this.b.a) != null) {
                        do.a(this.b.a).onCompleted(null, errorCode);
                    }
                }
            });
        } else {
            new Thread(new Runnable(this) {
                final /* synthetic */ do$2 b;

                public void run() {
                    do.a(this.b.a, discoveryResultPage);
                }
            }).start();
        }
    }
}
