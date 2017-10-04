package dji.pilot.visual.util;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJITextView;

public class a extends c implements OnClickListener {
    private CheckBox a;
    private DJITextView b;
    private DialogInterface.OnClickListener c = null;

    public a(Context context) {
        super(context);
        c();
    }

    public a(Context context, boolean z) {
        super(context, z);
    }

    public void onClick(View view) {
        if (R.id.afh == view.getId() && this.c != null) {
            this.c.onClick(this, 0);
        }
        dismiss();
    }

    public a a(DialogInterface.OnClickListener onClickListener) {
        this.c = onClickListener;
        return this;
    }

    private void c() {
        setContentView(R.layout.selfie_tracking_mode_how_to_use);
        this.a = (CheckBox) findViewById(R.id.bl9);
        this.b = (DJITextView) findViewById(R.id.afh);
        this.b.setOnClickListener(this);
    }

    public boolean b() {
        return this.a.isChecked();
    }

    protected void onCreate(Bundle bundle) {
        a(-1, -1, 0, 17, false, false);
    }
}
