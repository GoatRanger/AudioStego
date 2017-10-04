package dji.pilot.gallery.previewActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJITextView;

public class a extends c implements OnClickListener {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    private Context g;
    private DJITextView h;
    private DJITextView i;
    private DJITextView j;
    private DJITextView k;
    private DJITextView l;
    private DJITextView m;
    private DJIStateTextView n;

    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public a c(String str) {
            this.c = str;
            return this;
        }

        public a d(String str) {
            this.d = str;
            return this;
        }

        public a e(String str) {
            this.e = str;
            return this;
        }

        public a f(String str) {
            this.f = str;
            return this;
        }
    }

    public a(Context context) {
        super(context);
        this.g = context;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.gallery_dialog_informations);
        b();
        setCanceledOnTouchOutside(true);
    }

    private void b() {
        this.n = (DJIStateTextView) findViewById(R.id.agh);
        this.h = (DJITextView) findViewById(R.id.agf);
        this.i = (DJITextView) findViewById(R.id.agd);
        this.j = (DJITextView) findViewById(R.id.agb);
        this.k = (DJITextView) findViewById(R.id.ag_);
        this.l = (DJITextView) findViewById(R.id.ag8);
        this.m = (DJITextView) findViewById(R.id.ag6);
        this.h.setText(this.a);
        this.i.setText(this.b);
        this.j.setText(this.c);
        this.k.setText(this.d);
        this.l.setText(this.e);
        this.m.setText(this.f);
        this.n.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.agh:
                dismiss();
                return;
            default:
                return;
        }
    }

    public static void a(Context context, a aVar) {
        a aVar2 = new a(context);
        aVar2.a = aVar.a;
        aVar2.b = aVar.b;
        aVar2.c = aVar.c;
        aVar2.d = aVar.d;
        aVar2.e = aVar.e;
        aVar2.f = aVar.f;
        aVar2.show();
    }
}
