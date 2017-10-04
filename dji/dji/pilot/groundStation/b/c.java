package dji.pilot.groundStation.b;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.fpv.model.b;

public class c extends dji.pilot.publics.objects.c {
    private OnClickListener a = null;
    private OnClickListener b = null;
    private OnClickListener c = new OnClickListener(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.aj8:
                    if (this.a.a != null) {
                        this.a.a.onClick(view);
                    }
                    this.a.dismiss();
                    return;
                case R.id.aj9:
                    if (this.a.b != null) {
                        this.a.b.onClick(view);
                    }
                    this.a.dismiss();
                    return;
                default:
                    return;
            }
        }
    };

    public c(Context context) {
        super(context);
        setContentView(R.layout.gs_disclaimer_dialog);
        findViewById(R.id.aj8).setOnClickListener(this.c);
        findViewById(R.id.aj9).setOnClickListener(this.c);
    }

    protected void onCreate(Bundle bundle) {
        a(b.a(this.N, R.dimen.o4), b.a(this.N, R.dimen.o3), 0, 17, true, false);
    }

    public void a(boolean z, OnClickListener onClickListener) {
        if (z) {
            this.a = onClickListener;
        } else {
            this.b = onClickListener;
        }
    }
}
