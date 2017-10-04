package dji.pilot2.publics.object;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.Window;
import com.facebook.internal.aj;
import com.google.android.gms.location.places.Place;
import dji.pilot.visual.a.d;

public class a extends Dialog {
    protected Context a;

    public a(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.a = context;
    }

    public a(Context context, int i) {
        super(context, i);
        this.a = context;
    }

    public a(Context context) {
        super(context, aj.d);
        this.a = context;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCanceledOnTouchOutside(true);
        a();
    }

    private void a() {
        Window window = getWindow();
        window.getAttributes().dimAmount = d.c;
        window.getAttributes().windowAnimations = 16973910;
        window.setLayout(-1, -2);
        window.setGravity(80);
        window.setFlags(-1, Place.TYPE_SUBLOCALITY_LEVEL_4);
    }
}
