package dji.pilot2.nativeexplore.c;

import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateTextView;

public class b extends dji.pilot2.publics.object.a {
    private DJIStateTextView b;
    private DJIStateTextView c;
    private DJIStateTextView d;
    private a e;

    public interface a {
        void a();

        void b();

        void c();
    }

    public b(Context context) {
        super(context);
    }

    public b(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
    }

    public b(Context context, int i) {
        super(context, i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_postbottom_dialog);
        a();
    }

    private void a() {
        this.b = (DJIStateTextView) findViewById(R.id.cxz);
        this.c = (DJIStateTextView) findViewById(R.id.cy0);
        this.d = (DJIStateTextView) findViewById(R.id.re);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e != null) {
                    this.a.e.a();
                }
                this.a.dismiss();
            }
        });
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e != null) {
                    this.a.e.b();
                }
                this.a.dismiss();
            }
        });
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.e != null) {
                    this.a.e.c();
                }
                this.a.dismiss();
            }
        });
    }

    public void a(a aVar) {
        this.e = aVar;
    }

    public static void a(Context context, a aVar) {
        b bVar = new b(context);
        bVar.a(aVar);
        bVar.show();
    }
}
