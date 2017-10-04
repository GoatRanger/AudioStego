package dji.pilot.fpv.leftmenu;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.publics.objects.c;

public class a extends c implements OnClickListener {
    private int a = R.dimen.gd;
    private a b = null;

    public interface a {
        void a(DialogInterface dialogInterface);

        void b(DialogInterface dialogInterface);

        void c(DialogInterface dialogInterface);
    }

    public a(Context context) {
        super(context);
        b();
    }

    private void b() {
        setContentView(R.layout.fpv_gohome_select_dialog);
        findViewById(R.id.a1r).setOnClickListener(this);
        findViewById(R.id.a1s).setOnClickListener(this);
        findViewById(R.id.a1t).setOnClickListener(this);
    }

    protected void onCreate(Bundle bundle) {
        a((int) getContext().getResources().getDimension(this.a), -2, 0, 17, true, false);
    }

    public void a(a aVar) {
        this.b = aVar;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.a1r) {
            if (this.b != null) {
                this.b.a(this);
            }
        } else if (id == R.id.a1s) {
            if (this.b != null) {
                this.b.b(this);
            }
        } else if (id == R.id.a1t && this.b != null) {
            this.b.c(this);
        }
    }
}
