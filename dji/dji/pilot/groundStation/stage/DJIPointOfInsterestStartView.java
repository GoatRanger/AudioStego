package dji.pilot.groundStation.stage;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Handler;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.CAMERA_DIR;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.ROTATION_DIR;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.TO_START_POINT_MODE;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.util.i;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.publics.widget.DJIEditText;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class DJIPointOfInsterestStartView extends DJIRelativeLayout implements c$i, a {
    private dji.pilot.groundStation.a.a n = null;
    private float o = 0.0f;
    private float p = 0.0f;
    private DJITextView q = null;
    private DJITextView r = null;
    private DJIEditText s = null;
    private OnClickListener t = new OnClickListener(this) {
        final /* synthetic */ DJIPointOfInsterestStartView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ama:
                    this.a.x = true;
                    dji.pilot.groundStation.a.a.getInstance(null).i().l().b(null, 0.0d);
                    ((DJIStageView) this.a.getParent()).destoryStageView(true);
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_point_of_insterest_set_hot_point_view, 15, true);
                    return;
                case R.id.amd:
                    b bVar;
                    if (0.1d * ((double) DataOsdGetPushCommon.getInstance().getHeight()) <= 5.0d) {
                        bVar = new b();
                        bVar.a = d.b;
                        bVar.f = c.a;
                        if (DJIGenSettingDataManager.getInstance().v() == 0) {
                            bVar.c = String.format(this.a.getContext().getString(R.string.gs_point_of_insterest_height_limits), new Object[]{Float.valueOf(dji.pilot.groundStation.b.a(5.0f)), "FT"});
                        } else {
                            bVar.c = String.format(this.a.getContext().getString(R.string.gs_point_of_insterest_height_limits), new Object[]{Double.valueOf(5.0d), "M"});
                        }
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    }
                    double F = this.a.n.F();
                    if (F <= 5.0d || F >= 500.0d) {
                        bVar = new b();
                        bVar.a = d.b;
                        bVar.f = c.a;
                        bVar.b = R.string.gs_point_of_insterest_radius_limits;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    }
                    this.a.findViewById(R.id.amd).setEnabled(false);
                    if (i.b(this.a.getContext(), "GSPOI_CONTROL_HELP_NEVER_NOTICEE", false)) {
                        this.a.b();
                        return;
                    }
                    dji.pilot.groundStation.b.d dVar = new dji.pilot.groundStation.b.d(this.a.getContext());
                    dVar.a(new dji.pilot.groundStation.b.d.a(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void a() {
                            this.a.a.b();
                        }

                        public void b() {
                            this.a.a.b();
                        }
                    });
                    dVar.show();
                    return;
                default:
                    return;
            }
        }
    };
    private int u = -1;
    private ParamInfo v = null;
    private final Handler w = new Handler();
    private boolean x = false;
    private final Runnable y = new Runnable(this) {
        final /* synthetic */ DJIPointOfInsterestStartView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.x) {
                dji.pilot.groundStation.a.b instance = dji.pilot.groundStation.a.b.getInstance();
                dji.pilot.groundStation.a.a instance2 = dji.pilot.groundStation.a.a.getInstance(null);
                double b = instance.b();
                double a = instance.a();
                if (!(b == 0.0d || a == 0.0d)) {
                    double a2 = dji.gs.utils.a.a(instance2.D(), instance2.E(), b, a);
                    instance2.b(a2);
                    if (a2 < 5.0d || a2 > 500.0d) {
                        this.a.q.setTextColor(SupportMenu.CATEGORY_MASK);
                    } else {
                        this.a.q.setTextColor(this.a.getContext().getResources().getColor(R.color.er));
                    }
                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                        this.a.q.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a((float) a2))}));
                    } else {
                        this.a.q.setText(String.format("%.1fM", new Object[]{Double.valueOf(a2)}));
                    }
                    if (a2 >= 5.0d) {
                        int j = (int) (((double) (this.a.p * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)) / DJIPointOfInsterestStartView.maxAngularVelocityForRadius(a2));
                        if (j > 100) {
                            this.a.p = (float) ((100.0d * DJIPointOfInsterestStartView.maxAngularVelocityForRadius(a2)) / 100.0d);
                            this.a.o = (float) ((((double) (this.a.p * 180.0f)) / 3.141592653589793d) / a2);
                        } else if (j < -100) {
                            this.a.p = (float) ((-100.0d * DJIPointOfInsterestStartView.maxAngularVelocityForRadius(a2)) / 100.0d);
                            this.a.o = (float) ((((double) (this.a.p * 180.0f)) / 3.141592653589793d) / a2);
                        } else {
                            Math.abs(this.a.p);
                            this.a.o = (float) ((((double) (this.a.p * 180.0f)) / 3.141592653589793d) / a2);
                        }
                    }
                    instance2.i().l().b(dji.gs.utils.a.a(new dji.gs.e.b(instance2.D(), instance2.E())), a2);
                }
                float c = instance.c();
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    this.a.r.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a(c))}));
                } else {
                    this.a.r.setText(String.format("%.1fM", new Object[]{Float.valueOf(c)}));
                }
                if (c > 5.0f) {
                    this.a.r.setTextColor(this.a.getContext().getResources().getColor(R.color.er));
                } else {
                    this.a.r.setTextColor(SupportMenu.CATEGORY_MASK);
                }
                this.a.w.postDelayed(this.a.y, 500);
            }
        }
    };

    public DJIPointOfInsterestStartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.x = true;
    }

    public void dispatchOnResume() {
        findViewById(R.id.amd).setEnabled(true);
        this.x = false;
        this.w.post(this.y);
        this.p = 1.0f;
        this.o = (float) ((((double) (this.p * 180.0f)) / 3.141592653589793d) / 5.0d);
    }

    public void dispatchOnPause() {
        this.x = true;
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.n = dji.pilot.groundStation.a.a.getInstance(null);
            findViewById(R.id.ama).setOnClickListener(this.t);
            findViewById(R.id.amd).setOnClickListener(this.t);
            this.q = (DJITextView) findViewById(R.id.amh);
            this.r = (DJITextView) findViewById(R.id.ami);
            this.s = (DJIEditText) findViewById(R.id.amk);
            this.s.setText("30");
            this.s.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJIPointOfInsterestStartView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.s.setFocusable(true);
                    this.a.s.setFocusableInTouchMode(true);
                    this.a.s.requestFocus();
                    ((InputMethodManager) this.a.s.getContext().getSystemService("input_method")).showSoftInput(this.a.s, 0);
                }
            });
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.q.setText("0.0FT");
                this.r.setText("0.0FT");
            } else {
                this.q.setText("0.0M");
                this.r.setText("0.0M");
            }
            a();
        }
    }

    private void a() {
        this.v = dji.midware.data.manager.P3.d.read("g_config.flying_limit.max_height_0");
        new DataFlycGetParams().setInfos(new String[]{this.v.name}).start(new dji.midware.e.d(this) {
            final /* synthetic */ DJIPointOfInsterestStartView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.v = dji.midware.data.manager.P3.d.read(this.a.v.name);
                this.a.u = this.a.v.value.intValue();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    private void b() {
        b bVar;
        try {
            if (this.u == -1) {
                a();
                bVar = new b();
                bVar.a = d.b;
                bVar.f = c.a;
                bVar.b = R.string.gs_get_limit_height_failed;
                dji.thirdparty.a.c.a().e(bVar);
                d();
                return;
            }
            final int parseInt = Integer.parseInt(this.s.getText().toString());
            if (parseInt > this.u) {
                Builder builder = new Builder(getContext());
                builder.setMessage(R.string.gs_error_go_home_height_heigher_than_limit_height);
                builder.setPositiveButton(R.string.btn_dlg_yes, null);
                builder.create().show();
                d();
            } else if (parseInt == DataOsdGetPushHome.getInstance().getGoHomeHeight()) {
                c();
            } else if (parseInt <= 0 || !(this.v == null || this.v.isCorrect(Integer.valueOf(parseInt)))) {
                bVar = new b();
                bVar.a = d.b;
                bVar.f = c.a;
                bVar.b = R.string.gs_return_to_home_attitude_invalidate_parameter;
                dji.thirdparty.a.c.a().e(bVar);
                d();
            } else {
                new DataFlycSetParams().a(this.v.name, Integer.valueOf(parseInt)).start(new dji.midware.e.d(this) {
                    final /* synthetic */ DJIPointOfInsterestStartView b;

                    public void onSuccess(Object obj) {
                        this.b.s.post(new Runnable(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.b.c();
                                dji.pilot.groundStation.a.a.getInstance(null).b(parseInt);
                            }
                        });
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        b bVar = new b();
                        bVar.a = d.b;
                        bVar.f = c.a;
                        bVar.c = aVar.toString();
                        dji.thirdparty.a.c.a().e(bVar);
                        this.b.d();
                    }
                });
            }
        } catch (Exception e) {
            bVar = new b();
            bVar.a = d.b;
            bVar.f = c.a;
            bVar.b = R.string.gs_return_to_home_attitude_invalidate_parameter;
            dji.thirdparty.a.c.a().e(bVar);
            d();
        }
    }

    private void c() {
        ROTATION_DIR rotation_dir;
        float f;
        double height = 0.1d * ((double) DataOsdGetPushCommon.getInstance().getHeight());
        ROTATION_DIR rotation_dir2 = ROTATION_DIR.Anti_Clockwise;
        this.o = (float) ((((double) (this.p * 180.0f)) / 3.141592653589793d) / this.n.F());
        if (this.o > 0.0f) {
            rotation_dir = ROTATION_DIR.Anti_Clockwise;
            f = this.o;
        } else {
            rotation_dir = ROTATION_DIR.Clockwise;
            f = -this.o;
        }
        this.n.a(height);
        this.n.a(new dji.midware.e.d(this) {
            final /* synthetic */ DJIPointOfInsterestStartView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                int result = DataFlycStartHotPointMissionWithInfo.getInstance().getResult();
                if (result == 0) {
                    this.a.n.a(dji.pilot.groundStation.a.a.d.POI_AUTO);
                    this.a.x = true;
                    this.a.w.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.findViewById(R.id.amd).setEnabled(true);
                            ((DJIStageView) this.a.a.getParent()).createStageView(R.layout.gs_point_of_insterest_status_view, 22, true);
                        }
                    });
                    return;
                }
                this.a.d();
                this.a.n.a((int) R.string.gs_point_of_insterest_set_auto_flight_failed, dji.pilot.groundStation.a.a(this.a.getContext(), result), false);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.d();
                this.a.n.a((int) R.string.gs_point_of_insterest_set_auto_flight_failed, aVar, false);
            }
        }, CAMERA_DIR.Forwards_Hot_Point, rotation_dir, TO_START_POINT_MODE.Initi, f);
    }

    private void d() {
        this.w.post(new Runnable(this) {
            final /* synthetic */ DJIPointOfInsterestStartView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.findViewById(R.id.amd).setEnabled(true);
            }
        });
    }

    public static double maxAngularVelocityForRadius(double d) {
        double d2;
        if (d < 5.0d) {
            d = 5.0d;
        } else if (d > 500.0d) {
            d = 500.0d;
        }
        if (3 <= dji.pilot.publics.control.a.getInstance().b("0300", 1)) {
            d2 = (((d - 5.0d) / 25.0d) * 1.5d) + 2.0d;
            if (d2 > 3.5d) {
                d2 = 3.5d;
            }
            d2 = Math.sqrt(d2 * d);
        } else {
            d2 = (10.0d * 10.0d) / d > 3.47d ? Math.sqrt(d * 3.47d) : 10.0d;
        }
        if (d2 > 10.0d) {
            return 10.0d;
        }
        return d2;
    }
}
