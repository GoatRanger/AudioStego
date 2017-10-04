package dji.pilot.groundStation.b;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.midware.data.model.P3.DataOsdGetPushCommon.RcModeChannel;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.params.P3.ParamInfo;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.groundStation.a.a;
import dji.pilot.publics.objects.c;
import dji.pilot.publics.widget.DJIEditText;
import dji.publics.DJIUI.DJITextView;

public class e extends c {
    private OnClickListener a = null;
    private OnClickListener b = null;
    private DJIEditText c = null;
    private ParamInfo d = null;
    private DJITextView e = null;
    private boolean f = false;
    private ParamInfo g;
    private int h = -1;
    private Runnable i = new Runnable(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.f) {
                this.a.e.setText(this.a.getContext().getString(R.string.gs_return_to_home_attitude_dialog_current_attitude) + String.format(" %dm", new Object[]{Integer.valueOf(DataOsdGetPushCommon.getInstance().getHeight() / 10)}));
                this.a.e.postDelayed(this.a.i, 1000);
                if (!b.q(i.getInstance().c())) {
                    DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
                    if (instance.getFlycState() == FLYC_STATE.GoHome || instance.getFlycState() == FLYC_STATE.AutoLanding || instance.getModeChannel() != RcModeChannel.CHANNEL_F) {
                        this.a.dismiss();
                    }
                }
            }
        }
    };
    private OnClickListener j = new OnClickListener(this) {
        final /* synthetic */ e a;

        {
            this.a = r1;
        }

        public void onClick(final View view) {
            switch (view.getId()) {
                case R.id.an8:
                    if (this.a.a != null) {
                        this.a.a.onClick(view);
                    }
                    this.a.dismiss();
                    return;
                case R.id.an9:
                    DJIErrorPopView.b bVar;
                    try {
                        if (this.a.h == -1) {
                            this.a.b();
                            bVar = new DJIErrorPopView.b();
                            bVar.a = d.b;
                            bVar.f = DJIErrorPopView.c.a;
                            bVar.b = R.string.gs_get_limit_height_failed;
                            dji.thirdparty.a.c.a().e(bVar);
                            return;
                        }
                        final int parseInt = Integer.parseInt(this.a.c.getText().toString());
                        if (parseInt > this.a.h) {
                            Builder builder = new Builder(this.a.getContext());
                            builder.setMessage(R.string.gs_error_go_home_height_heigher_than_limit_height);
                            builder.setPositiveButton(R.string.btn_dlg_yes, null);
                            builder.create().show();
                            return;
                        } else if (parseInt == DataOsdGetPushHome.getInstance().getGoHomeHeight()) {
                            if (this.a.b != null) {
                                this.a.b.onClick(view);
                            }
                            this.a.dismiss();
                            return;
                        } else if (parseInt <= 0 || !this.a.d.isCorrect(Integer.valueOf(parseInt))) {
                            bVar = new DJIErrorPopView.b();
                            bVar.a = d.b;
                            bVar.f = DJIErrorPopView.c.a;
                            bVar.b = R.string.gs_return_to_home_attitude_invalidate_parameter;
                            dji.thirdparty.a.c.a().e(bVar);
                            return;
                        } else {
                            new DataFlycSetParams().a(this.a.d.name, Integer.valueOf(parseInt)).start(new dji.midware.e.d(this) {
                                final /* synthetic */ AnonymousClass4 c;

                                public void onSuccess(Object obj) {
                                    this.c.a.c.post(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void run() {
                                            if (this.a.c.a.b != null) {
                                                this.a.c.a.b.onClick(view);
                                            }
                                            a.getInstance(null).b(parseInt);
                                            this.a.c.a.dismiss();
                                        }
                                    });
                                }

                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                    DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                                    bVar.a = d.b;
                                    bVar.f = DJIErrorPopView.c.a;
                                    bVar.c = aVar.toString();
                                    dji.thirdparty.a.c.a().e(bVar);
                                }
                            });
                            return;
                        }
                    } catch (Exception e) {
                        bVar = new DJIErrorPopView.b();
                        bVar.a = d.b;
                        bVar.f = DJIErrorPopView.c.a;
                        bVar.b = R.string.gs_return_to_home_attitude_invalidate_parameter;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    }
                default:
                    return;
            }
        }
    };

    public e(Context context) {
        super(context);
        setContentView(R.layout.gs_set_return_home_attitude_dialog);
        findViewById(R.id.an8).setOnClickListener(this.j);
        findViewById(R.id.an9).setOnClickListener(this.j);
        this.c = (DJIEditText) findViewById(R.id.amk);
        this.c.setText(String.format("%d", new Object[]{Integer.valueOf(DataOsdGetPushHome.getInstance().getGoHomeHeight())}));
        this.d = dji.midware.data.manager.P3.d.read("g_config.go_home.fixed_go_home_altitude_0");
        this.e = (DJITextView) findViewById(R.id.ana);
        this.e.setText(getContext().getString(R.string.gs_return_to_home_attitude_dialog_current_attitude) + String.format(" %dm", new Object[]{Integer.valueOf(DataOsdGetPushCommon.getInstance().getHeight() / 10)}));
        this.f = false;
        setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                this.a.f = true;
            }
        });
        this.e.postDelayed(this.i, 1000);
        this.g = dji.midware.data.manager.P3.d.read("g_config.flying_limit.max_height_0");
        b();
    }

    private void b() {
        new DataFlycGetParams().setInfos(new String[]{this.g.name}).start(new dji.midware.e.d(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.g = dji.midware.data.manager.P3.d.read(this.a.g.name);
                this.a.h = this.a.g.value.intValue();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    protected void onCreate(Bundle bundle) {
        a(dji.pilot.fpv.model.b.a(this.N, R.dimen.g1) + dji.pilot.fpv.model.b.a(this.N, R.dimen.o4), dji.pilot.fpv.model.b.a(this.N, R.dimen.o3) - dji.pilot.fpv.model.b.a(this.N, R.dimen.gl), 0, 17, false, false);
    }

    public void a(boolean z, OnClickListener onClickListener) {
        if (z) {
            this.a = onClickListener;
        } else {
            this.b = onClickListener;
        }
    }
}
