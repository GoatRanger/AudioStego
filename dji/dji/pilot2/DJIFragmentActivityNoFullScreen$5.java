package dji.pilot2;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class DJIFragmentActivityNoFullScreen$5 implements OnClickListener {
    final /* synthetic */ DJIFragmentActivityNoFullScreen a;

    DJIFragmentActivityNoFullScreen$5(DJIFragmentActivityNoFullScreen dJIFragmentActivityNoFullScreen) {
        this.a = dJIFragmentActivityNoFullScreen;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=" + this.a.getPackageName()));
        this.a.startActivity(intent);
    }
}
