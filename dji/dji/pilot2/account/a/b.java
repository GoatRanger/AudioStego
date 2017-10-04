package dji.pilot2.account.a;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateButton;
import dji.pilot2.utils.m;
import dji.publics.DJIUI.DJITextView;

public class b extends AlertDialog implements OnClickListener {
    private DJIStateButton a;
    private DJIStateButton b;
    private DJITextView c;
    private String d = null;
    private String e = null;
    private String f = null;
    private a g;

    public interface a {
        void a(b bVar);

        void b(b bVar);
    }

    public b(Context context) {
        super(context);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
    }

    private void a() {
        setContentView(R.layout.account_error_dialog);
        this.c = (DJITextView) findViewById(R.id.bq);
        this.a = (DJIStateButton) findViewById(R.id.br);
        this.a.setOnClickListener(this);
        this.b = (DJIStateButton) findViewById(R.id.bs);
        this.b.setOnClickListener(this);
        this.c.setText(this.f);
        b(this.d);
        c(this.e);
    }

    public void a(a aVar) {
        this.g = aVar;
    }

    public void a(String str) {
        if (this.c == null) {
            this.f = str;
        } else {
            this.c.setText(str);
        }
    }

    public void b(String str) {
        if (this.a == null) {
            this.d = str;
        } else if (m.c(str)) {
            this.a.setVisibility(8);
        } else {
            this.a.setVisibility(0);
            this.a.setText(str);
        }
    }

    public void c(String str) {
        if (this.b == null) {
            this.e = str;
        } else {
            this.b.setText(str);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.br:
                if (this.g != null) {
                    this.g.a(this);
                    return;
                }
                return;
            case R.id.bs:
                if (this.g != null) {
                    this.g.b(this);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
