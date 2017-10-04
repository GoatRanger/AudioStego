package dji.pilot2;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class DJIFragmentActivityNoFullScreen$6 implements OnClickListener {
    final /* synthetic */ DJIFragmentActivityNoFullScreen a;

    DJIFragmentActivityNoFullScreen$6(DJIFragmentActivityNoFullScreen dJIFragmentActivityNoFullScreen) {
        this.a = dJIFragmentActivityNoFullScreen;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://m.dji.net/djipilot"));
        this.a.startActivity(intent);
    }
}
