package dji.device.camera.view.focus.a;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.dji.frame.c.c;
import dji.pilot.fpv.R;

public class a extends AlertDialog {
    boolean[] a;
    boolean[] b;
    private final String c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;
    private Context k;
    private String l;
    private String m;
    private String[] n;
    private int o;
    private float p;
    private OnClickListener q;
    private OnClickListener r;

    public a(Context context) {
        super(context);
        this.c = a.class.getSimpleName();
        this.d = 0;
        this.e = 1;
        this.f = 2;
        this.g = 3;
        this.h = 4;
        this.i = 0;
        this.j = 1;
        this.l = null;
        this.m = null;
        this.n = new String[2];
        this.o = 0;
        this.p = 0.0f;
        this.a = new boolean[]{true, true, true, true, true};
        this.b = new boolean[]{true, true};
        this.k = context;
    }

    public a(Context context, boolean z, OnCancelListener onCancelListener) {
        super(context, z, onCancelListener);
        this.c = a.class.getSimpleName();
        this.d = 0;
        this.e = 1;
        this.f = 2;
        this.g = 3;
        this.h = 4;
        this.i = 0;
        this.j = 1;
        this.l = null;
        this.m = null;
        this.n = new String[2];
        this.o = 0;
        this.p = 0.0f;
        this.a = new boolean[]{true, true, true, true, true};
        this.b = new boolean[]{true, true};
        this.k = context;
    }

    public a(Context context, int i) {
        super(context, i);
        this.c = a.class.getSimpleName();
        this.d = 0;
        this.e = 1;
        this.f = 2;
        this.g = 3;
        this.h = 4;
        this.i = 0;
        this.j = 1;
        this.l = null;
        this.m = null;
        this.n = new String[2];
        this.o = 0;
        this.p = 0.0f;
        this.a = new boolean[]{true, true, true, true, true};
        this.b = new boolean[]{true, true};
        this.k = context;
    }

    public a a(String str) {
        this.l = str;
        return this;
    }

    public a a(int i) {
        this.l = (String) this.k.getText(i);
        return this;
    }

    public void setTitle(int i) {
        this.m = (String) this.k.getText(i);
    }

    public a b(String str) {
        this.m = str;
        return this;
    }

    public a a(OnClickListener onClickListener) {
        this.q = onClickListener;
        return this;
    }

    public a b(OnClickListener onClickListener) {
        this.r = onClickListener;
        return this;
    }

    public a a(boolean z) {
        this.a[0] = z;
        return this;
    }

    public a b(boolean z) {
        this.a[1] = z;
        return this;
    }

    public a c(boolean z) {
        this.a[2] = z;
        return this;
    }

    public a d(boolean z) {
        this.a[3] = z;
        return this;
    }

    public a e(boolean z) {
        this.a[4] = z;
        return this;
    }

    public a a(int i, boolean z) {
        if (i >= 0 && i <= 1) {
            this.b[i] = z;
        }
        return this;
    }

    public a b(int i) {
        this.o = i;
        return this;
    }

    public a a(int i, String str) {
        Log.d(this.c, "setBottonText: index = " + i + " title = " + str);
        if (i <= 1) {
            this.n[i] = str;
        }
        return this;
    }

    public a a(float f) {
        this.p = f;
        return this;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.longan_focus_view);
        ImageView imageView = (ImageView) findViewById(R.id.longan_follow_focus_img);
        TextView textView = (TextView) findViewById(R.id.longan_follow_focus_title);
        TextView textView2 = (TextView) findViewById(R.id.longan_follow_focus_message);
        Button button = (Button) findViewById(R.id.longan_focus_get_it);
        Button button2 = (Button) findViewById(R.id.longan_focus_not_remind);
        getWindow().setFlags(8, 8);
        c.a(getWindow());
        if (this.p > 0.0f) {
            textView.setTextSize(this.p);
            textView2.setTextSize(this.p);
        }
        if (!this.a[0]) {
            imageView.setVisibility(8);
        }
        if (!this.a[1]) {
            textView.setVisibility(8);
        } else if (this.m != null) {
            textView.setText(this.m);
        }
        if (!this.a[2]) {
            textView2.setVisibility(8);
        } else if (this.l != null) {
            textView2.setText(this.l);
        }
        if (this.a[3]) {
            if (!(this.n[0] == null || this.n[0].equals(""))) {
                Log.d(this.c, "onCreate: bottonText[INDEX_OK_BOTTON_TEXT] = " + this.n[0]);
                button.setText(this.n[0]);
            }
            if (this.q != null) {
                button.setOnClickListener(this.q);
            } else {
                button.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.dismiss();
                    }
                });
            }
        } else {
            button.setVisibility(8);
        }
        if (this.a[4]) {
            if (!(this.n[1] == null || this.n[1].equals(""))) {
                button2.setText(this.n[1]);
            }
            if (this.r != null) {
                button2.setOnClickListener(this.r);
            } else {
                button2.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        this.a.dismiss();
                    }
                });
            }
        } else {
            button2.setVisibility(8);
        }
        if (!this.b[0]) {
            findViewById(R.id.follow_focus_first_line).setVisibility(8);
        }
        if (!this.b[1]) {
            findViewById(R.id.follow_focus_second_line).setVisibility(8);
        }
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.8f;
        if (this.o > 0) {
            attributes.width = this.o;
        }
        window.setAttributes(attributes);
    }
}
