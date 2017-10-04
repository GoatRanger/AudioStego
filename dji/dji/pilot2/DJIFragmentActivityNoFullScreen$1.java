package dji.pilot2;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.pilot.publics.control.a;
import dji.pilot.publics.objects.g;

class DJIFragmentActivityNoFullScreen$1 implements OnClickListener {
    final /* synthetic */ long a;
    final /* synthetic */ DJIFragmentActivityNoFullScreen b;

    DJIFragmentActivityNoFullScreen$1(DJIFragmentActivityNoFullScreen dJIFragmentActivityNoFullScreen, long j) {
        this.b = dJIFragmentActivityNoFullScreen;
        this.a = j;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        g.a(this.b.getBaseContext(), a.d, this.a + 86400000);
        dialogInterface.dismiss();
    }
}
