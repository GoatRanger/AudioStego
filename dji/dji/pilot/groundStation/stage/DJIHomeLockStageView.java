package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.s;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataA2PushCommom;
import dji.midware.data.model.P3.DataA2PushCommom.DJIA2CtrlMode;
import dji.midware.data.model.P3.DataFlycStartIoc;
import dji.midware.data.model.P3.DataFlycStartIoc.IOCType;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIErrorPopView.f;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIHomeLockStageView extends DJIRelativeLayout implements c$i, a {
    private DJITextView n = null;
    private OnClickListener o = new OnClickListener(this) {
        final /* synthetic */ DJIHomeLockStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ak2:
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    dji.pilot.groundStation.a.a.getInstance(null).a(this.a);
                    return;
                case R.id.ak3:
                    b bVar;
                    if (this.a.r <= 5.0d) {
                        bVar = new b();
                        bVar.a = d.b;
                        bVar.f = c.a;
                        bVar.g = f.a;
                        bVar.b = R.string.gs_error_too_close_to_home_point;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    } else if (i.getInstance().c() == ProductType.A2 && DataA2PushCommom.getInstance().e() == DJIA2CtrlMode.b) {
                        bVar = new b();
                        bVar.a = d.b;
                        bVar.f = c.a;
                        bVar.g = f.a;
                        bVar.b = R.string.a2_home_lock_mode_error;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    } else {
                        final dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
                        this.a.findViewById(R.id.ak3).setEnabled(false);
                        DJILogHelper.getInstance().LOGD("View", "DJIProductManager.getInstance().getType()=" + i.getInstance().c(), false, true);
                        instance.a(i.getInstance().c() == ProductType.A2 ? IOCType.IOCTypeHomeLockA2 : IOCType.IOCTypeHomeLock, new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void onSuccess(Object obj) {
                                int result = DataFlycStartIoc.getInstance().getResult();
                                if (result == 0 || (i.getInstance().c() == ProductType.A2 && s.m == s.find(result))) {
                                    e.c(c$i.h);
                                    instance.a(dji.pilot.groundStation.a.a.d.HOME_LOCK);
                                    this.b.a.p.post(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void run() {
                                            this.a.b.a.findViewById(R.id.ak3).setEnabled(true);
                                            ((DJIStageView) this.a.b.a.getParent()).createStageView(R.layout.gs_home_lock_status_view, 13, true);
                                        }
                                    });
                                    return;
                                }
                                this.b.a.a();
                                instance.a((int) R.string.gs_home_lock_send_command_failed, dji.pilot.groundStation.a.a(this.b.a.getContext(), result), false);
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                this.b.a.a();
                                instance.a((int) R.string.gs_home_lock_send_command_failed, aVar, false);
                            }
                        });
                        return;
                    }
                default:
                    return;
            }
        }
    };
    private final Handler p = new Handler();
    private boolean q = false;
    private double r = 0.0d;
    private final Runnable s = new Runnable(this) {
        final /* synthetic */ DJIHomeLockStageView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.q) {
                DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
                double latitude = instance.getLatitude();
                double longitude = instance.getLongitude();
                if (!(latitude == 0.0d && longitude == 0.0d)) {
                    dji.gs.e.b a = dji.gs.utils.a.a(new dji.gs.e.b(latitude, longitude));
                    dji.gs.e.b f = dji.pilot.groundStation.a.a.getInstance(null).i().l().f();
                    if (f != null) {
                        float atan2 = (float) ((Math.atan2((a.c - f.c) * 10000.0d, (-(a.b - f.b)) * 20000.0d) * 180.0d) / 3.141592653589793d);
                        if (atan2 < 0.0f) {
                            atan2 = (float) (((double) atan2) + 6.283185307179586d);
                        }
                        this.a.r = dji.gs.utils.a.a(a.b, a.c, f.b, f.c);
                        if (DJIGenSettingDataManager.getInstance().v() == 0) {
                            this.a.n.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a((float) this.a.r))}));
                        } else {
                            this.a.n.setText(String.format("%.1fM", new Object[]{Double.valueOf(this.a.r)}));
                        }
                        if (this.a.r <= 5.0d && i.getInstance().c() != ProductType.A2) {
                            this.a.n.setTextColor(SupportMenu.CATEGORY_MASK);
                        } else if (this.a.r > 10.0d || i.getInstance().c() != ProductType.A2) {
                            this.a.n.setTextColor(this.a.getContext().getResources().getColor(R.color.er));
                        } else {
                            this.a.n.setTextColor(SupportMenu.CATEGORY_MASK);
                        }
                    } else {
                        this.a.n.setText("N/A");
                        this.a.n.setTextColor(SupportMenu.CATEGORY_MASK);
                    }
                }
                this.a.p.postDelayed(this.a.s, 500);
            }
        }
    };

    public DJIHomeLockStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.q = true;
    }

    public void dispatchOnResume() {
        this.q = false;
        this.p.post(this.s);
        findViewById(R.id.ak3).setEnabled(true);
    }

    public void dispatchOnPause() {
        this.q = true;
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            findViewById(R.id.ak2).setOnClickListener(this.o);
            findViewById(R.id.ak3).setOnClickListener(this.o);
            this.n = (DJITextView) findViewById(R.id.ak4);
        }
    }

    private void a() {
        this.p.post(new Runnable(this) {
            final /* synthetic */ DJIHomeLockStageView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.findViewById(R.id.ak3).setEnabled(true);
            }
        });
    }
}
