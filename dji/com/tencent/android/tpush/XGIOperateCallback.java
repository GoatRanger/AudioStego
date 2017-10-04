package com.tencent.android.tpush;

public interface XGIOperateCallback {
    void onFail(Object obj, int i, String str);

    void onSuccess(Object obj, int i);
}
