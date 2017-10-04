package dji.pilot.dji_groundstation.ui.a;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.d$a;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.e;
import dji.pilot.dji_groundstation.controller.DataMgr.b;
import dji.pilot.dji_groundstation.controller.d;
import dji.pilot.dji_groundstation.controller.f;

public class a extends e {
    private static final String a = "DJIGSExitModeDialog";

    public a(Context context) {
        super(context);
        a();
    }

    private void a() {
        b();
        setContentView(R.layout.gs_exit_current_mission_view);
        findViewById(R.id.gs_exix_current_mession_ok).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                e eVar;
                c b = d.getInstance().b();
                if (b.ordinal() == c.d.ordinal() || b.ordinal() == c.g.ordinal() || b.ordinal() == c.n.ordinal() || b.ordinal() == c.p.ordinal() || b.ordinal() == c.r.ordinal() || b.ordinal() == c.u.ordinal()) {
                    if (b.ordinal() == c.n.ordinal()) {
                        dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().j();
                        dji.pilot.dji_groundstation.controller.DataMgr.c.getInstance().e();
                    } else if (b.ordinal() == c.p.ordinal()) {
                        b.getInstance().b(false);
                        b.getInstance().e();
                    } else if (b.ordinal() == c.g.ordinal()) {
                        dji.pilot.dji_groundstation.controller.DataMgr.e.getInstance().e();
                        eVar = new e();
                        eVar.s = 1;
                        dji.thirdparty.a.c.a().e(eVar);
                        dji.pilot.dji_groundstation.controller.a.getInstance(this.a.getContext()).a(-1);
                    } else if (b.ordinal() == c.d.ordinal()) {
                        dji.pilot.dji_groundstation.controller.DataMgr.d.getInstance().e();
                        eVar = new e();
                        eVar.s = 1;
                        dji.thirdparty.a.c.a().e(eVar);
                    }
                }
                d.getInstance().a(false, 2, new dji.midware.e.d(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
                d.getInstance().a(d$a.Normal);
                d.getInstance().c();
                f.getInstance(this.a.getContext()).b(false);
                eVar = new e();
                eVar.s = 16;
                eVar.t = new Integer(R.drawable.mini_normal);
                dji.thirdparty.a.c.a().e(eVar);
                this.a.dismiss();
            }
        });
        findViewById(R.id.gs_exix_current_mession_cancel).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
            }
        });
    }

    private void b() {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) dji.pilot.dji_groundstation.a.f.a(250.0d, getContext());
        attributes.height = (int) dji.pilot.dji_groundstation.a.f.a(270.0d, getContext());
        attributes.y = 0;
        attributes.x = 0;
        attributes.gravity = 17;
        attributes.flags &= -33;
        attributes.flags &= -9;
        attributes.flags &= -3;
        attributes.dimAmount = 0.0f;
        getWindow().setAttributes(attributes);
    }

    public void show() {
        b();
        super.show();
    }
}
