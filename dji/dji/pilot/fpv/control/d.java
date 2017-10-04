package dji.pilot.fpv.control;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.pilot.R;
import dji.pilot.publics.widget.c;
import java.lang.ref.WeakReference;

public class d {
    private Context a = null;
    private c b = null;
    private a c = null;

    private static final class a extends Handler {
        private final WeakReference<d> a;

        public a(d dVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dVar);
        }

        public void handleMessage(Message message) {
            d dVar = (d) this.a.get();
            if (dVar != null) {
                dVar.a(FLYC_COMMAND.Calibration);
            }
        }
    }

    public d(Context context) {
        this.a = context;
        this.c = new a(this);
    }

    private void a(FLYC_COMMAND flyc_command) {
        DataFlycFunctionControl.getInstance().setCommand(flyc_command).start(new dji.midware.e.d(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void a() {
        if (this.b == null) {
            this.b = new c(this.a);
            this.b.a(this.a.getString(R.string.fpv_checklist_compass_tip_1));
            this.b.c().d((int) R.string.fpv_checklist_cancel_cele).a(new OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.d();
                }
            }).b(new OnClickListener(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.c();
                }
            });
        }
        if (!this.b.isShowing()) {
            a(0);
            this.b.show();
        }
    }

    private void b() {
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
            this.b = null;
        }
    }

    private void c() {
        b();
        a(FLYC_COMMAND.DropCalibration);
        this.c.sendEmptyMessageDelayed(0, 50);
    }

    private void d() {
        a(FLYC_COMMAND.Calibration);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a(FLYC_COMMAND.DropCalibration);
            }
        }, 500);
    }

    private void a(int i) {
        if (this.b != null && this.b.isShowing()) {
            if (i == 0) {
                this.b.a(this.a.getString(R.string.fpv_checklist_compass_tip_1));
                this.b.b(0);
                this.b.b(this.a.getString(dji.pilot.publics.c.d.getInstance().m(i.getInstance().c())));
                this.b.a(0);
                this.b.c(dji.pilot.publics.c.d.getInstance().l(i.getInstance().c()));
                this.b.c().d((int) R.string.fpv_checklist_cancel_cele);
            } else if (i == 1) {
                this.b.a(this.a.getString(R.string.fpv_checklist_compass_tip_2));
                this.b.b(0);
                this.b.b(this.a.getString(dji.pilot.publics.c.d.getInstance().o(i.getInstance().c())));
                this.b.a(0);
                this.b.c(dji.pilot.publics.c.d.getInstance().n(i.getInstance().c()));
                this.b.c().d((int) R.string.fpv_checklist_cancel_cele);
            } else if (i != 2 && i == 3) {
                this.b.a(0);
                this.b.a(this.a.getString(R.string.fpv_compass_adjust_fail));
                this.b.b(8);
                this.b.b().d((int) R.string.app_cancel);
                this.b.e(R.string.app_retry);
            }
        }
    }

    public void a(DataOsdGetPushHome dataOsdGetPushHome) {
        if (dataOsdGetPushHome.isCompassCeleing()) {
            a();
        } else {
            b();
        }
        int compassCeleStatus = dataOsdGetPushHome.getCompassCeleStatus();
        if (compassCeleStatus == 0) {
            a(0);
        } else if (compassCeleStatus == 1) {
            a(1);
        } else if (compassCeleStatus == 3) {
            a(3);
        }
    }
}
