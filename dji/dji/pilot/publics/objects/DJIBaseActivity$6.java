package dji.pilot.publics.objects;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class DJIBaseActivity$6 implements OnClickListener {
    final /* synthetic */ DJIBaseActivity a;

    DJIBaseActivity$6(DJIBaseActivity dJIBaseActivity) {
        this.a = dJIBaseActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("http://m.dji.net/djipilot"));
        this.a.startActivity(intent);
    }
}
