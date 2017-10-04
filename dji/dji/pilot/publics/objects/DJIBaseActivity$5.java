package dji.pilot.publics.objects;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class DJIBaseActivity$5 implements OnClickListener {
    final /* synthetic */ DJIBaseActivity a;

    DJIBaseActivity$5(DJIBaseActivity dJIBaseActivity) {
        this.a = dJIBaseActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("market://details?id=" + this.a.getPackageName()));
        this.a.startActivity(intent);
    }
}
