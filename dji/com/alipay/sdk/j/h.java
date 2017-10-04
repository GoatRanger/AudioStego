package com.alipay.sdk.j;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.alipay.android.app.IRemoteServiceCallback.Stub;

final class h extends Stub {
    final /* synthetic */ f a;

    h(f fVar) {
        this.a = fVar;
    }

    public final boolean isHideLoadingScreen() throws RemoteException {
        return false;
    }

    public final void payEnd(boolean z, String str) throws RemoteException {
    }

    public final void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
        Intent intent = new Intent("android.intent.action.MAIN", null);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            bundle.putInt("CallingPid", i);
            intent.putExtras(bundle);
        } catch (Exception e) {
        }
        intent.setClassName(str, str2);
        if (this.a.a != null) {
            this.a.a.startActivity(intent);
        }
    }
}
