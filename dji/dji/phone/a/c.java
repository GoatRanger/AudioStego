package dji.phone.a;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.fpv.R;

public class c extends AlertDialog {
    TextView a;
    ImageView b;
    boolean c;
    Button[] d;
    boolean[] e;
    OnClickListener[] f;
    int g;
    int h;
    String i;
    private Context j;

    public c(Context context) {
        super(context);
        this.c = true;
        this.d = new Button[2];
        this.e = new boolean[]{true, true};
        this.f = new OnClickListener[2];
        this.g = 0;
        this.h = 0;
        this.j = context;
    }

    public c(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.c = true;
        this.d = new Button[2];
        this.e = new boolean[]{true, true};
        this.f = new OnClickListener[2];
        this.g = 0;
        this.h = 0;
        this.j = context;
    }

    public c(Context context, int i) {
        super(context, i);
        this.c = true;
        this.d = new Button[2];
        this.e = new boolean[]{true, true};
        this.f = new OnClickListener[2];
        this.g = 0;
        this.h = 0;
        this.j = context;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.lp_phone_gimbal_exception_dialog);
        this.b = (ImageView) findViewById(R.id.confirm_status_iv);
        this.a = (TextView) findViewById(R.id.confirm_status_tv);
        this.d[0] = (Button) findViewById(R.id.lp_cancel_btn);
        this.d[1] = (Button) findViewById(R.id.lp_confirm_btn);
        getWindow().setFlags(8, 8);
        com.dji.frame.c.c.a(getWindow());
        if (this.h > 0) {
            this.a.setText(this.h);
        }
        if (this.c) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
        if (this.e[0]) {
            this.d[0].setVisibility(0);
            if (this.f[0] != null) {
                this.d[0].setOnClickListener(this.f[0]);
            } else {
                this.d[0].setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.dismiss();
                    }
                });
            }
        } else {
            this.d[0].setVisibility(8);
        }
        if (this.e[1]) {
            this.d[1].setVisibility(0);
            if (this.f[1] != null) {
                this.d[1].setOnClickListener(this.f[1]);
            } else {
                this.d[1].setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.dismiss();
                    }
                });
            }
        } else {
            this.d[1].setVisibility(8);
        }
        if (!(this.i == null || this.i.equals(""))) {
            this.a.setText(this.i);
        }
        if (this.g > 0) {
            this.b.setImageDrawable(this.j.getResources().getDrawable(this.g));
        }
    }

    public void a(int i, OnClickListener onClickListener) {
        this.f[i] = onClickListener;
    }

    public void a(boolean z) {
        this.c = z;
    }

    public void a(int i, boolean z) {
        this.e[i] = z;
    }

    public c a(int i) {
        this.g = i;
        return this;
    }

    public c a(String str) {
        this.i = str;
        return this;
    }
}
