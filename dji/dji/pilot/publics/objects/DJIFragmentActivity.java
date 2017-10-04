package dji.pilot.publics.objects;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.KeyEvent;

public abstract class DJIFragmentActivity extends DJIBaseActivity {
    protected FragmentManager g = null;

    protected abstract d a();

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g = getFragmentManager();
    }

    protected void onDestroy() {
        d a = a();
        if (a != null) {
            a.q();
        }
        super.onDestroy();
    }

    public void onBackPressed() {
        d a = a();
        if (a == null || !a.m()) {
            finishThis();
        }
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        d a = a();
        if (a != null && a.a(keyEvent)) {
            return false;
        }
        if (a(keyEvent) || super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        return false;
    }

    public void finishThis() {
        finish();
    }

    protected boolean a(KeyEvent keyEvent) {
        return false;
    }
}
