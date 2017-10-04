package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;
import dji.midware.data.model.P3.DataFlycFunctionControl;
import dji.midware.data.model.P3.DataFlycFunctionControl.FLYC_COMMAND;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.setting.ui.widget.a;
import dji.thirdparty.a.c;

public class ResetIocView extends ItemViewButtonBig {
    public ResetIocView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetPushCommon.getInstance());
        }
    }

    public void onClick(View view) {
        e.a("FPV_MCSettings_Button_ResetIOC");
        a.a(getContext(), R.string.setting_ui_flyc_ioc_reset_confirm, new OnClickListener(this) {
            final /* synthetic */ ResetIocView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                DataFlycFunctionControl.getInstance().setCommand(FLYC_COMMAND.HOMEPOINT_LOC).start(new d(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                Toast.makeText(this.a.a.a.getContext(), R.string.setting_ui_flyc_ioc_reset_success, 0).show();
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.a.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                Toast.makeText(this.a.a.a.getContext(), R.string.setting_ui_flyc_ioc_reset_fail, 0).show();
                            }
                        });
                    }
                });
                dialogInterface.dismiss();
            }
        });
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if ((dataOsdGetPushCommon.getFlycVersion() & 255) >= 7) {
            this.c.setEnabled(false);
        } else {
            this.c.setEnabled(true);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }
}
