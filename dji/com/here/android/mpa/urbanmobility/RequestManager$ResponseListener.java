package com.here.android.mpa.urbanmobility;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public interface RequestManager$ResponseListener<T> {
    void onError(ErrorCode errorCode, String str);

    void onSuccess(T t);
}
