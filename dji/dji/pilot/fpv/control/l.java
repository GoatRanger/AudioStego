package dji.pilot.fpv.control;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataGimbalGetPushAutoCalibrationStatus;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.pilot.R;
import dji.pilot.publics.widget.b;
import dji.thirdparty.a.c;

public class l {
    private Context a;
    private b b = null;
    private boolean c = false;

    public l(Context context) {
        this.a = context;
        c.a().a(this);
    }

    public void a() {
        c.a().d(this);
        this.b = null;
    }

    private void a(int i) {
        b a = b.a(this.a, (int) R.string.app_tip, i, (int) R.string.app_enter, new OnClickListener(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }, R.string.app_enter, new OnClickListener(this) {
            final /* synthetic */ l a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        a.b();
        a.show();
    }

    private void b() {
        b(-1);
    }

    private void b(int i) {
        String replace;
        if (i == -1) {
            replace = this.a.getResources().getString(R.string.fpv_gensetting_gimbal_caling, new Object[]{""}).replace("%", "");
        } else {
            replace = this.a.getResources().getString(R.string.fpv_gensetting_gimbal_caling, new Object[]{Integer.valueOf(i)});
        }
        DJILogHelper.getInstance().LOGD("gimbal", "progress: " + i + replace);
        if (this.b == null) {
            this.b = b.a(this.a, this.a.getResources().getString(R.string.app_tip), replace, this.a.getResources().getString(R.string.app_isee), new OnClickListener(this) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, this.a.getResources().getString(R.string.app_enter), new OnClickListener(this) {
                final /* synthetic */ l a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).b().f().d();
            this.b.a(false);
        }
        if (this.b.isShowing()) {
            this.b.b(replace);
        } else {
            this.b.show();
        }
    }

    private void c() {
        if (this.b != null && this.b.isShowing()) {
            this.b.dismiss();
            this.b = null;
        }
    }

    public void onEventMainThread(DataGimbalGetPushAutoCalibrationStatus dataGimbalGetPushAutoCalibrationStatus) {
        switch (dataGimbalGetPushAutoCalibrationStatus.getStatus()) {
            case 1:
                b(dataGimbalGetPushAutoCalibrationStatus.getProgress());
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        boolean isAutoCalibration = dataGimbalGetPushParams.isAutoCalibration();
        if (this.c != isAutoCalibration) {
            if (this.c && !isAutoCalibration) {
                c();
                if (dataGimbalGetPushParams.autoCalibrationResult()) {
                    a(R.string.fpv_gensetting_gimbal_cali_success);
                } else {
                    a(R.string.fpv_gensetting_gimbal_cali_fail);
                }
            } else if (!this.c && isAutoCalibration) {
                b();
            }
            this.c = isAutoCalibration;
        }
    }
}
