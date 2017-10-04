package dji.pilot2;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class DJIFragmentActivityNoFullScreen$7 implements OnClickListener {
    final /* synthetic */ DJIFragmentActivityNoFullScreen a;

    DJIFragmentActivityNoFullScreen$7(DJIFragmentActivityNoFullScreen dJIFragmentActivityNoFullScreen) {
        this.a = dJIFragmentActivityNoFullScreen;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        DJIFragmentActivityNoFullScreen.a(this.a).dismiss();
    }
}
