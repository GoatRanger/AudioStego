package com.nokia.maps.a;

import com.here.a.a.a.i;
import com.here.android.mpa.urbanmobility.RequestManager$ResponseListener;

public abstract class b<SDKType, UMType, UMReqType extends i> extends c<SDKType, UMType, UMReqType> {
    public abstract int a();

    public abstract void a(int i);

    public b(int i, UMReqType uMReqType, RequestManager$ResponseListener<SDKType> requestManager$ResponseListener) {
        super(i, (i) uMReqType, (RequestManager$ResponseListener) requestManager$ResponseListener);
    }

    public b(int[] iArr, UMReqType uMReqType, RequestManager$ResponseListener<SDKType> requestManager$ResponseListener) {
        super(iArr, (i) uMReqType, (RequestManager$ResponseListener) requestManager$ResponseListener);
    }
}
