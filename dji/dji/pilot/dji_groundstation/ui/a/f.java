package dji.pilot.dji_groundstation.ui.a;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.a;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.dji_groundstation.controller.b;
import dji.pilot.dji_groundstation.controller.e;
import dji.pilot.dji_groundstation.controller.h;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;

public class f extends e implements b, h {
    private static final String a = "DJIGSConfirmDialog";
    private DJITextView g = null;
    private DJITextView h = null;
    private DJITextView i = null;
    private DJITextView j = null;
    private DJILinearLayout k = null;
    private DJIImageView l = null;
    private int m = 0;
    private int n = 0;
    private String o = "";
    private String p = "";
    private boolean q = false;

    public f(Context context) {
        super(context);
        setContentView(R.layout.gs_confirm_dialog_new);
        this.g = (DJITextView) findViewById(R.id.gs_confirm_dialog_title);
        this.h = (DJITextView) findViewById(R.id.gs_confirm_dialog_desc);
        this.i = (DJITextView) findViewById(R.id.gs_confirm_dialog_left_btn);
        this.j = (DJITextView) findViewById(R.id.gs_confirm_dialog_right_btn);
        this.k = (DJILinearLayout) findViewById(R.id.gs_confirm_dialog_center_line);
        this.l = (DJIImageView) findViewById(R.id.gs_confirm_dialog_icon);
        this.i.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                e.a(this.a.o, this.a.getContext());
                this.a.hide();
            }
        });
        this.j.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                e.a(this.a.p, this.a.getContext());
                this.a.hide();
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        b();
    }

    private void b() {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = this.m;
        attributes.height = this.n;
        attributes.y = 0;
        attributes.x = this.q ? (dji.pilot.dji_groundstation.a.f.b(getContext()) - this.m) / 2 : 0;
        attributes.dimAmount = 0.0f;
        attributes.flags &= -3;
        attributes.gravity = 17;
        e();
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.dialogWindowAnim);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    public void a() {
        this.i.go();
        this.k.go();
    }

    public void setTitle(int i) {
        if (i <= 0) {
            this.g.setText("");
        } else {
            this.g.setText(i);
        }
    }

    public boolean a(int i) {
        if (i <= 0) {
            this.h.setText("");
            return false;
        }
        this.h.setText(i);
        return true;
    }

    public void b(int i) {
        if (i <= 0) {
            this.i.setText("");
        } else {
            this.i.setText(i);
        }
    }

    public void c(int i) {
        if (i <= 0) {
            this.j.setText("");
        } else {
            this.j.setText(i);
        }
    }

    public void a(String str) {
        this.h.setText(str);
    }

    public void d(int i) {
        if (i <= 0) {
            this.l.setVisibility(4);
        } else {
            this.l.setImageResource(i);
        }
    }

    public void a(dji.pilot.dji_groundstation.a.b bVar, Object obj) {
    }

    public void a(final g gVar, final Object obj) {
        a(new Runnable(this) {
            final /* synthetic */ f c;

            public void run() {
                switch (gVar) {
                    case EVENT_SMARTMODE_ERROR_PUSH_TO_CONFIRM_DIALOG:
                    case EVENT_SMARTMODE_WAYPOINT_NOTICE_TO_CONFIRM_DIALOG:
                        if (obj != null && (obj instanceof a)) {
                            this.c.a((a) obj);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        });
    }

    private void a(a aVar) {
        if (aVar != null) {
            setTitle(aVar.a);
            if (!a(aVar.b)) {
                a(aVar.c);
            }
            this.m = (int) dji.pilot.dji_groundstation.a.f.a((double) aVar.d, getContext());
            this.n = (int) dji.pilot.dji_groundstation.a.f.a((double) aVar.e, getContext());
            if (aVar.f > 0) {
                d(aVar.f);
            }
            if (aVar.h <= 0) {
                a();
            } else {
                this.o = aVar.g;
                this.i.setText(aVar.h);
            }
            if (aVar.j <= 0) {
                a();
                this.j.setText(aVar.h);
                this.p = aVar.g;
            } else {
                this.p = aVar.i;
                this.j.setText(aVar.j);
            }
            this.q = aVar.k;
            try {
                b();
                show();
            } catch (Exception e) {
                hide();
            }
        }
    }

    private void c() {
    }
}
