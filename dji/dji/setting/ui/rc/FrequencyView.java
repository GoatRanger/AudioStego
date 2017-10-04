package dji.setting.ui.rc;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcSetFrequency;
import dji.midware.data.model.P3.DataRcSetFrequency.FreqCcode;
import dji.midware.data.model.P3.DataRcSetFrequency.FreqMode;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.e.d;
import dji.pilot.fpv.d.e;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.setting.ui.widget.a;
import java.util.Timer;
import java.util.TimerTask;

public class FrequencyView extends ItemViewButtonBig {
    private boolean a = false;
    private Timer b = null;
    private int d = 60;
    private AlertDialog e;

    public FrequencyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (DataRcGetMaster.getInstance().getMode() != MODE.a) {
            setVisibility(8);
        } else {
            setVisibility(0);
        }
    }

    public void onClick(View view) {
        this.a = false;
        e.a("FPV_RCSettings_MasterRCControlSettings_Button_LinkingRemoteController");
        if (DataOsdGetPushCommon.getInstance().isMotorUp()) {
            a.a(getContext(), R.string.setting_ui_rc_cannot_frequency_motorup);
        } else {
            a.a(getContext(), R.string.setting_ui_rc_set_frequency_title, new OnClickListener(this) {
                final /* synthetic */ FrequencyView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    DataRcSetFrequency.getInstance().a(FreqMode.b).start(new d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            this.a.a.a();
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                    dialogInterface.dismiss();
                }
            });
        }
    }

    private void a() {
        if (this.b == null) {
            this.b = new Timer();
            this.b.schedule(new TimerTask(this) {
                final /* synthetic */ FrequencyView a;

                {
                    this.a = r1;
                }

                public void run() {
                    DataRcSetFrequency.getInstance().a(FreqMode.a).start(new d(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            FreqCcode a = DataRcSetFrequency.getInstance().a();
                            if (a == FreqCcode.a) {
                                if (!this.a.a.a) {
                                    this.a.a.a(R.string.setting_ui_rc_pair_finished);
                                }
                                this.a.a.c();
                            } else if (a == FreqCcode.c) {
                                this.a.a.a(R.string.setting_ui_rc_pair_cancel_when_finished);
                                this.a.a.c();
                                this.a.a.post(new Runnable(this) {
                                    final /* synthetic */ AnonymousClass1 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void run() {
                                        this.a.a.a.e = null;
                                    }
                                });
                            } else {
                                this.a.a.b();
                                this.a.a.d = this.a.a.d - 1;
                                if (this.a.a.d == 0) {
                                    this.a.a.c();
                                    this.a.a.d();
                                    this.a.a.a(R.string.setting_ui_rc_pair_timeout);
                                    this.a.a.post(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void run() {
                                            this.a.a.a.e = null;
                                        }
                                    });
                                }
                            }
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                }
            }, 0, 1000);
        }
    }

    private void b() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ FrequencyView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.e == null) {
                    AlertDialog a = a.a(this.a.getContext());
                    a.setTitle(R.string.setting_ui_rc_pair_cacel_message);
                    a.setMessage(this.a.getResources().getString(R.string.setting_ui_rc_set_frequency_title));
                    a.setButton(-2, this.a.getResources().getString(R.string.app_cancel), new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.a.d();
                            dialogInterface.dismiss();
                            this.a.a.e = null;
                        }
                    });
                    this.a.e = a;
                }
                this.a.e.setMessage(this.a.getContext().getResources().getString(R.string.setting_ui_rc_paring_with_timeout, new Object[]{Integer.valueOf(this.a.d)}));
                this.a.e.show();
            }
        });
    }

    private void a(final int i) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ FrequencyView b;

            public void run() {
                a.a(this.b.getContext(), i);
            }
        });
    }

    private void c() {
        if (this.b != null) {
            this.b.cancel();
        }
        this.b = null;
        this.d = 60;
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ FrequencyView a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.e != null) {
                    this.a.e.dismiss();
                }
            }
        });
    }

    private void d() {
        DataRcSetFrequency.getInstance().a(FreqMode.c).start(new d(this) {
            final /* synthetic */ FrequencyView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a = true;
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }
}
