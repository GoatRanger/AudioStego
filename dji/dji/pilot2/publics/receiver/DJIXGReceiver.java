package dji.pilot2.publics.receiver;

import android.content.Context;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;
import dji.pilot2.publics.a.b;
import dji.thirdparty.a.c;
import org.json.JSONException;
import org.json.JSONObject;

public class DJIXGReceiver extends XGPushBaseReceiver {
    private b a = b.getInstance();

    public enum a {
        SHOWED,
        CLICKED
    }

    public void onDeleteTagResult(Context context, int i, String str) {
    }

    public void onNotifactionClickedResult(Context context, XGPushClickedResult xGPushClickedResult) {
        try {
            String optString = new JSONObject(xGPushClickedResult.getCustomContent()).optString(b.a);
            if (!(optString == null || optString.equals(""))) {
                this.a.a(optString);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (xGPushClickedResult.getActionType() == 0) {
            this.a.a(true);
            c.a().e(a.CLICKED);
        }
    }

    public void onNotifactionShowedResult(Context context, XGPushShowedResult xGPushShowedResult) {
    }

    public void onRegisterResult(Context context, int i, XGPushRegisterResult xGPushRegisterResult) {
    }

    public void onSetTagResult(Context context, int i, String str) {
    }

    public void onTextMessage(Context context, XGPushTextMessage xGPushTextMessage) {
    }

    public void onUnregisterResult(Context context, int i) {
    }
}
