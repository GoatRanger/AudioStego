package com.here.android.mpa.urbanmobility;

import com.here.android.mpa.search.ErrorCode;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
@Deprecated
public interface ResponseListener<T> {
    void onError(ErrorCode errorCode);

    void onSuccess(T t);
}
