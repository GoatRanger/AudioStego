package dji.pilot.fpv.a;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJITextView;

public class a extends c implements OnClickListener {
    private DJITextView a = null;
    private DialogInterface.OnClickListener b = null;

    public a(Context context) {
        super(context);
        setContentView(R.layout.new_off_dialog);
        this.a = (DJITextView) findViewById(R.id.ber);
        this.a.setOnClickListener(this);
    }

    public a a(DialogInterface.OnClickListener onClickListener) {
        this.b = onClickListener;
        return this;
    }

    protected void onCreate(Bundle bundle) {
        a((int) (((float) DJIBaseActivity.screenWidth) * 0.6875f), DJIBaseActivity.screenHeight - b.a(this.N, R.dimen.gp), 0, 17, false, false);
    }

    public void onClick(View view) {
        if (R.id.ber != view.getId()) {
            return;
        }
        if (this.b == null) {
            dismiss();
        } else {
            this.b.onClick(this, 0);
        }
    }
}
