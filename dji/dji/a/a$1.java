package dji.a;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.OnEngineInitListener.Error;

class a$1 implements OnEngineInitListener {
    final /* synthetic */ Context a;
    final /* synthetic */ a b;

    a$1(a aVar, Context context) {
        this.b = aVar;
        this.a = context;
    }

    public void onEngineInitializationCompleted(Error error) {
        if (error == Error.NONE) {
            a.a(true);
            return;
        }
        a.a(false);
        Log.e("initHere", "init map error : " + error);
        if (error != null) {
            Log.e("initHere", error.getDetails());
            Log.d("initHere", error.getStackTrace());
        }
        if (a.f() < 1) {
            a.g();
            a.a(this.b, this.a);
            return;
        }
        Toast.makeText(this.a, "init map error : " + error, 1).show();
    }
}
