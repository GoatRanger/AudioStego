package dji.pilot.countrycode.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class CountryCodeActivity$2 implements OnClickListener {
    final /* synthetic */ CountryCodeActivity a;

    CountryCodeActivity$2(CountryCodeActivity countryCodeActivity) {
        this.a = countryCodeActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        CountryCodeActivity.c = false;
        this.a.finish();
    }
}
