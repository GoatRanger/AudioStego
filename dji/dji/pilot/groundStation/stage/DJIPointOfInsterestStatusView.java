package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataFlycHotPointResetParams;
import dji.midware.data.model.P3.DataFlycStartHotPointMissionWithInfo.ROTATION_DIR;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.groundStation.a.b;
import dji.pilot.groundStation.b.d;
import dji.pilot.groundStation.view.DJIGSSpeedBar;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.HashMap;
import java.util.Map;

public class DJIPointOfInsterestStatusView extends DJILinearLayout implements c$i, dji.pilot.fpv.view.DJIStageView.a {
    private OnClickListener A = new OnClickListener(this) {
        final /* synthetic */ DJIPointOfInsterestStatusView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.aml:
                    new d(this.a.getContext()).show();
                    return;
                case R.id.amu:
                    this.a.s.e(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                        }
                    });
                    return;
                case R.id.amw:
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_exit_current_mession_view, 26, false);
                    return;
                case R.id.amx:
                    dji.pilot.groundStation.a.a.getInstance(null).a();
                    ((DJIStageView) this.a.getParent()).stop();
                    return;
                default:
                    return;
            }
        }
    };
    private final Handler B = new Handler();
    private int C = 0;
    private final Runnable D = new Runnable(this) {
        final /* synthetic */ DJIPointOfInsterestStatusView a;

        {
            this.a = r1;
        }

        public void run() {
            dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
            if (instance.z() == dji.pilot.groundStation.a.a.d.POI_AUTO && instance.t()) {
                b instance2 = b.getInstance();
                double D = this.a.s.D();
                double E = this.a.s.E();
                if (!(D == 0.0d || E == 0.0d)) {
                    E = dji.gs.utils.a.a(D, E, instance2.b(), instance2.a());
                    this.a.s.b(E);
                    if (E > 500.0d) {
                        this.a.p.setText(">500M");
                        if (DJIGenSettingDataManager.getInstance().v() == 0) {
                            this.a.p.setText(">1640.4FT");
                        } else {
                            this.a.p.setText(">500M");
                        }
                    } else if (DJIGenSettingDataManager.getInstance().v() == 0) {
                        this.a.p.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a((float) E))}));
                    } else {
                        this.a.p.setText(String.format("%.1fM", new Object[]{Double.valueOf(E)}));
                    }
                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                        this.a.q.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a(instance2.c()))}));
                    } else {
                        this.a.q.setText(String.format("%.1fM", new Object[]{Float.valueOf(instance2.c())}));
                    }
                    float b = this.a.u;
                    if (DJIPointOfInsterestStatusView.maxAngularVelocityForRadius(E) < ((double) Math.abs(b))) {
                        if (this.a.u >= 0.0f) {
                            b = (float) DJIPointOfInsterestStatusView.maxAngularVelocityForRadius(E);
                        } else {
                            b = -((float) DJIPointOfInsterestStatusView.maxAngularVelocityForRadius(E));
                        }
                    }
                    this.a.a(b >= 0.0f ? b : -b);
                    float f = (float) ((((double) (180.0f * b)) / 3.141592653589793d) / E);
                    DJIPointOfInsterestStatusView dJIPointOfInsterestStatusView = this.a;
                    if (f < 0.0f) {
                        f = -f;
                    }
                    dJIPointOfInsterestStatusView.b(f);
                    int maxAngularVelocityForRadius = (int) (((double) (b * DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity)) / DJIPointOfInsterestStatusView.maxAngularVelocityForRadius(E));
                    if (maxAngularVelocityForRadius > 100) {
                        maxAngularVelocityForRadius = 100;
                    } else if (maxAngularVelocityForRadius < -100) {
                        maxAngularVelocityForRadius = -100;
                    }
                    this.a.n.setValue(maxAngularVelocityForRadius);
                    if (instance.i().l() != null) {
                        instance.i().l().b(dji.gs.utils.a.a(new dji.gs.e.b(instance.D(), instance.E())), E);
                    }
                }
                if (this.a.s.I()) {
                    this.a.x.setText(R.string.gs_resume);
                } else {
                    this.a.x.setText(R.string.gs_pause);
                }
                this.a.B.postDelayed(this.a.D, 500);
                this.a.C = this.a.C + 1;
                if (this.a.C >= 20) {
                    this.a.C = 0;
                }
            }
        }
    };
    private DJIGSSpeedBar n = null;
    private DJITextView o = null;
    private DJITextView p = null;
    private DJITextView q = null;
    private DJITextView r = null;
    private dji.pilot.groundStation.a.a s = null;
    private float t = 0.0f;
    private float u = 0.0f;
    private DJIImageView v = null;
    private DJIImageView w = null;
    private DJITextView x = null;
    private View y = null;
    private View z = null;

    private final class a extends PagerAdapter {
        final /* synthetic */ DJIPointOfInsterestStatusView a;

        public a(DJIPointOfInsterestStatusView dJIPointOfInsterestStatusView) {
            this.a = dJIPointOfInsterestStatusView;
        }

        public int getCount() {
            return 1;
        }

        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (viewGroup != null && i == 0) {
                viewGroup.removeView(this.a.y);
            }
        }

        public Object instantiateItem(ViewGroup viewGroup, int i) {
            View view = null;
            if (i == 0) {
                view = this.a.y;
            }
            viewGroup.addView(view);
            return view;
        }
    }

    public DJIPointOfInsterestStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        double F = this.s.F();
        this.u = (float) (((((double) this.s.H()) * F) * 3.141592653589793d) / 180.0d);
        float f = this.u;
        this.n.setProgress((int) (((double) (DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity * f)) / maxAngularVelocityForRadius(F)));
        DJITextView dJITextView;
        String str;
        Object[] objArr;
        if (DJIGenSettingDataManager.getInstance().v() == 0) {
            dJITextView = this.o;
            str = "%.1fMPH";
            objArr = new Object[1];
            if (f < 0.0f) {
                f = -f;
            }
            objArr[0] = Float.valueOf(dji.pilot.groundStation.b.b(f));
            dJITextView.setText(String.format(str, objArr));
        } else if (DJIGenSettingDataManager.getInstance().v() == 2) {
            dJITextView = this.o;
            str = "%.1fKM/H";
            objArr = new Object[1];
            if (f < 0.0f) {
                f = -f;
            }
            objArr[0] = Float.valueOf(dji.pilot.groundStation.b.b(f));
            dJITextView.setText(String.format(str, objArr));
        } else {
            dJITextView = this.o;
            str = "%.1fM/S";
            objArr = new Object[1];
            if (f < 0.0f) {
                f = -f;
            }
            objArr[0] = Float.valueOf(f);
            dJITextView.setText(String.format(str, objArr));
        }
        this.B.post(this.D);
        dji.pilot.groundStation.a.a.getInstance(null).i().l().b(dji.gs.utils.a.a(new dji.gs.e.b(this.s.D(), this.s.E())), this.s.F());
        this.C = 0;
        if (this.s.I()) {
            this.x.setText(R.string.gs_resume);
        } else {
            this.x.setText(R.string.gs_pause);
        }
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.s = dji.pilot.groundStation.a.a.getInstance(null);
            findViewById(R.id.amx).setOnClickListener(this.A);
            findViewById(R.id.amw).setOnClickListener(this.A);
            findViewById(R.id.amu).setOnClickListener(this.A);
            findViewById(R.id.aml).setOnClickListener(this.A);
            this.p = (DJITextView) findViewById(R.id.amm);
            this.q = (DJITextView) findViewById(R.id.amn);
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.q.setText("0FT");
                this.p.setText("0FT");
            } else {
                this.q.setText("0M");
                this.p.setText("0M");
            }
            this.v = (DJIImageView) findViewById(R.id.amq);
            this.w = (DJIImageView) findViewById(R.id.amr);
            this.w.setAlpha(0.3f);
            this.v.setAlpha(1.0f);
            this.y = findViewById(R.id.amt);
            a();
            this.z = LayoutInflater.from(getContext()).inflate(R.layout.gs_poi_control_left_and_right, null);
        }
    }

    private void a() {
        if (this.y != null) {
            if (this.o == null) {
                this.o = (DJITextView) this.y.findViewById(R.id.aly);
            }
            if (this.r == null) {
                this.r = (DJITextView) this.y.findViewById(R.id.am3);
            }
            if (this.x == null) {
                this.x = (DJITextView) this.y.findViewById(R.id.am4);
                this.x.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ DJIPointOfInsterestStatusView a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        boolean I = this.a.s.I();
                        Map hashMap = new HashMap();
                        if (I) {
                            hashMap.put(c$i.a, c$i.c);
                            e.a(c$i.l, hashMap);
                        } else {
                            hashMap.put(c$i.a, c$i.b);
                            e.a(c$i.l, hashMap);
                        }
                        this.a.s.a(new dji.midware.e.d(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                            }
                        }, !I);
                    }
                });
            }
            if (this.n == null) {
                this.n = (DJIGSSpeedBar) this.y.findViewById(R.id.am1);
                this.n.setRange(-100, 100, false);
                this.n.setOnValueChanged(new dji.pilot.groundStation.view.DJIGSSpeedBar.a(this) {
                    final /* synthetic */ DJIPointOfInsterestStatusView a;

                    {
                        this.a = r1;
                    }

                    public void a(View view, int i, boolean z) {
                        double F = this.a.s.F();
                        if (F != 0.0d) {
                            ROTATION_DIR rotation_dir;
                            float maxAngularVelocityForRadius = (float) ((((double) ((float) i)) * DJIPointOfInsterestStatusView.maxAngularVelocityForRadius(F)) / 100.0d);
                            final float b = this.a.u;
                            final float c = this.a.t;
                            this.a.u = maxAngularVelocityForRadius;
                            this.a.t = (float) ((((double) (180.0f * maxAngularVelocityForRadius)) / 3.141592653589793d) / F);
                            DJIPointOfInsterestStatusView dJIPointOfInsterestStatusView = this.a;
                            if (maxAngularVelocityForRadius < 0.0f) {
                                maxAngularVelocityForRadius = -maxAngularVelocityForRadius;
                            }
                            dJIPointOfInsterestStatusView.a(maxAngularVelocityForRadius);
                            this.a.b(this.a.t > 0.0f ? this.a.t : -this.a.t);
                            ROTATION_DIR rotation_dir2 = ROTATION_DIR.Clockwise;
                            if (this.a.t > 0.0f) {
                                rotation_dir = ROTATION_DIR.Anti_Clockwise;
                                maxAngularVelocityForRadius = this.a.t;
                            } else {
                                rotation_dir = ROTATION_DIR.Clockwise;
                                maxAngularVelocityForRadius = -this.a.t;
                            }
                            if (z) {
                                this.a.s.a(new dji.midware.e.d(this) {
                                    final /* synthetic */ AnonymousClass2 c;

                                    public void onSuccess(Object obj) {
                                        if (DataFlycHotPointResetParams.getInstance().getResult() == 0) {
                                        }
                                    }

                                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                                        this.c.a.B.post(new Runnable(this) {
                                            final /* synthetic */ AnonymousClass1 a;

                                            {
                                                this.a = r1;
                                            }

                                            public void run() {
                                                this.a.c.a.u = b;
                                                this.a.c.a.t = c;
                                                this.a.c.a.a(this.a.c.a.u >= 0.0f ? this.a.c.a.u : -this.a.c.a.u);
                                                this.a.c.a.b(this.a.c.a.t > 0.0f ? this.a.c.a.t : -this.a.c.a.t);
                                                dji.pilot.groundStation.a.a.getInstance(null).b(this.a.c.a.t);
                                            }
                                        });
                                    }
                                }, rotation_dir, maxAngularVelocityForRadius);
                            }
                        }
                    }
                });
            }
            this.r.setText(">1h");
        }
    }

    private void a(float f) {
        if (DJIGenSettingDataManager.getInstance().v() == 0) {
            this.o.setText(String.format("%.1fMPH", new Object[]{Float.valueOf(dji.pilot.groundStation.b.b(f))}));
        } else if (DJIGenSettingDataManager.getInstance().v() == 2) {
            this.o.setText(String.format("%.1fKM/H", new Object[]{Float.valueOf(dji.pilot.groundStation.b.c(f))}));
        } else {
            this.o.setText(String.format("%.1fM/S", new Object[]{Float.valueOf(f)}));
        }
    }

    private void b(float f) {
        if (f > 0.0f) {
            int i = (int) (360.0f / f);
            if (i < 3600) {
                int i2 = i / 60;
                int i3 = i % 60;
                CharSequence charSequence = "";
                if (i2 > 0) {
                    charSequence = String.format("%d'", new Object[]{Integer.valueOf(i2)});
                }
                if (i3 > 0) {
                    if (charSequence.isEmpty()) {
                        charSequence = String.format("%d''", new Object[]{Integer.valueOf(i3)});
                    } else {
                        charSequence = charSequence + String.format("%d''", new Object[]{Integer.valueOf(i3)});
                    }
                }
                this.r.setText(charSequence);
                return;
            }
            this.r.setText(">1h");
            return;
        }
        this.r.setText(">1h");
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
