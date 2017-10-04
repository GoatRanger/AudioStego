package dji.pilot.groundStation.b;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class a extends c {
    private DJITextView a = null;
    private DJITextView b = null;
    private DJITextView c = null;
    private DJITextView d = null;
    private DJILinearLayout e = null;
    private OnClickListener f = null;
    private OnClickListener g = null;
    private DJIImageView h = null;
    private int i = 0;
    private int j = 0;
    private boolean k = false;

    public a(Context context, int i, int i2, boolean z) {
        super(context);
        this.i = i;
        this.j = i2;
        this.k = z;
        setContentView(R.layout.gs_confirm_dialog);
        this.a = (DJITextView) findViewById(R.id.aip);
        this.b = (DJITextView) findViewById(R.id.aiq);
        this.c = (DJITextView) findViewById(R.id.air);
        this.d = (DJITextView) findViewById(R.id.ait);
        this.e = (DJILinearLayout) findViewById(R.id.ais);
        this.h = (DJIImageView) findViewById(R.id.aio);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.f != null) {
                    this.a.f.onClick(view);
                }
                this.a.dismiss();
            }
        });
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.g != null) {
                    this.a.g.onClick(view);
                }
                this.a.dismiss();
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = this.i;
        attributes.height = this.j;
        attributes.y = 0;
        attributes.x = this.k ? (DJIBaseActivity.screenWidth - this.i) / 2 : 0;
        attributes.dimAmount = 0.0f;
        attributes.flags &= -3;
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.e5);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    public void a(OnClickListener onClickListener) {
        this.f = onClickListener;
    }

    public void b(OnClickListener onClickListener) {
        this.g = onClickListener;
    }

    public void b() {
        this.c.go();
        this.e.go();
        this.d.setBackground(getContext().getResources().getDrawable(R.drawable.gs_click_bottom_round_background));
    }

    public void setTitle(int i) {
        this.a.setText(i);
    }

    public void a(int i) {
        this.b.setText(i);
    }

    public void b(int i) {
        this.c.setText(i);
    }

    public void c(int i) {
        this.d.setText(i);
    }

    public void a(String str) {
        this.b.setText(str);
    }

    public void d(int i) {
        this.h.setImageResource(i);
    }
}
