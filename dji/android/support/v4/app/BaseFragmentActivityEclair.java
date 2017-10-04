package android.support.v4.app;

import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;

abstract class BaseFragmentActivityEclair extends BaseFragmentActivityDonut {
    boolean mStartedIntentSenderFromFragment;

    BaseFragmentActivityEclair() {
    }

    public void startIntentSenderForResult(IntentSender intentSender, int i, @Nullable Intent intent, int i2, int i3, int i4) throws SendIntentException {
        if (!(this.mStartedIntentSenderFromFragment || i == -1)) {
            checkForValidRequestCode(i);
        }
        super.startIntentSenderForResult(intentSender, i, intent, i2, i3, i4);
    }

    void onBackPressedNotHandled() {
        super.onBackPressed();
    }

    static void checkForValidRequestCode(int i) {
        if ((SupportMenu.CATEGORY_MASK & i) != 0) {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
    }
}
