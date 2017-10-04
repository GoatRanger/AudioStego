package dji.pilot.groundStation.b;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.c;

public class g extends c {
    public g(Context context) {
        super(context);
        b();
    }

    private void b() {
        setContentView(R.layout.gs_way_point_add_point_help_dialog);
        findViewById(R.id.ans).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = b.a(this.N, R.dimen.g7);
        attributes.height = b.a(this.N, R.dimen.fs);
        attributes.y = 0;
        attributes.x = 0;
        attributes.dimAmount = 0.0f;
        attributes.flags &= -3;
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.e5);
    }
}
