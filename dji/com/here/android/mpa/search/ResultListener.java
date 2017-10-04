package com.here.android.mpa.search;

import com.nokia.maps.annotation.Online;

@Online
public interface ResultListener<T> {
    void onCompleted(T t, ErrorCode errorCode);
}
