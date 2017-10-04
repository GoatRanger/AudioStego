package dji.playback.previewActivity;

import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.os.Build.VERSION;
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
    public String g;
    private Context h;
    private DJITextView i;
    private DJITextView j;
    private DJITextView k;
    private DJITextView l;
    private DJITextView m;
    private DJITextView n;
    private DJITextView o;
    private DJIStateTextView p;

    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;

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

        public a g(String str) {
            this.g = str;
            return this;
        }
    }

    public a(Context context) {
        super(context);
        this.h = context;
    }

    public a(Context context, boolean z) {
        super(context, z);
        this.h = context;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_hg_dialog_informations);
        b();
        setCanceledOnTouchOutside(true);
    }

    private void b() {
        this.p = (DJIStateTextView) findViewById(R.id.agh);
        this.i = (DJITextView) findViewById(R.id.agf);
        this.j = (DJITextView) findViewById(R.id.agd);
        this.k = (DJITextView) findViewById(R.id.agb);
        this.l = (DJITextView) findViewById(R.id.ag_);
        this.m = (DJITextView) findViewById(R.id.ag8);
        this.n = (DJITextView) findViewById(R.id.ag6);
        this.o = (DJITextView) findViewById(R.id.cpb);
        if (this.a == null || this.a.equals("")) {
            findViewById(R.id.cp9).setVisibility(8);
        } else {
            this.i.setText(this.a);
        }
        if (this.b == null || this.b.equals("")) {
            findViewById(R.id.cp8).setVisibility(8);
        } else {
            this.j.setText(this.b);
        }
        if (this.c == null || this.c.equals("")) {
            findViewById(R.id.cp7).setVisibility(8);
        } else {
            this.k.setText(this.c);
        }
        if (this.d == null || this.d.equals("")) {
            findViewById(R.id.cp6).setVisibility(8);
        } else {
            this.l.setText(this.d);
        }
        if (this.e == null || this.e.equals("")) {
            findViewById(R.id.cp5).setVisibility(8);
        } else {
            this.m.setText(this.e);
        }
        if (this.f == null || this.f.equals("")) {
            findViewById(R.id.cp4).setVisibility(8);
        } else {
            this.n.setText(this.f);
        }
        this.o.setText(this.g);
        this.p.setOnClickListener(this);
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

    private void c() {
        int i;
        if (VERSION.SDK_INT >= 19) {
            i = 5894;
        } else {
            i = 1798;
        }
        getWindow().getDecorView().setSystemUiVisibility(i);
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
    }

    public static void a(Context context, a aVar, OnDismissListener onDismissListener) {
        a aVar2 = new a(context);
        aVar2.a = aVar.a;
        aVar2.b = aVar.b;
        aVar2.c = aVar.c;
        aVar2.d = aVar.d;
        aVar2.e = aVar.e;
        aVar2.f = aVar.f;
        aVar2.g = aVar.g;
        aVar2.setOnDismissListener(onDismissListener);
        aVar2.show();
    }
}
