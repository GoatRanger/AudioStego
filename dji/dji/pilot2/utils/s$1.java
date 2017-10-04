package dji.pilot2.utils;

import android.util.Log;
import com.tencent.android.tpush.XGIOperateCallback;
import com.tencent.android.tpush.common.Constants;

class s$1 implements XGIOperateCallback {
    s$1() {
    }

    public void onSuccess(Object obj, int i) {
        Log.w(Constants.LogTag, "+++ register push sucess. token:" + obj);
    }

    public void onFail(Object obj, int i, String str) {
        Log.w(Constants.LogTag, "+++ register push fail. token:" + obj + ", errCode:" + i + ",msg:" + str);
    }
}
