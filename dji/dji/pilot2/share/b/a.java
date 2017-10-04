package dji.pilot2.share.b;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.Window;
import com.facebook.internal.aj;
import com.google.android.gms.location.places.Place;
import dji.pilot.R;
import lecho.lib.hellocharts.d.c;

public class a extends Dialog {
    public a(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public a(Context context, int i) {
        super(context, i);
    }

    public a(Context context) {
        super(context, aj.d);
    }

    protected void onCreate(Bundle bundle) {
        setContentView(R.layout.v2_share_dialog_loading_layout);
        Window window = getWindow();
        window.getAttributes().dimAmount = c.a;
        window.setLayout(-1, -1);
        window.setFlags(-1, Place.TYPE_SUBLOCALITY_LEVEL_4);
        super.onCreate(bundle);
    }
}
