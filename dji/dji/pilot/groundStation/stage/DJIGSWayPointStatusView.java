package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.midware.data.model.P3.DataFlycGetPushWayPointMissionInfo;
import dji.midware.data.model.P3.DataFlycWayPointSetIdleSpeed;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIErrorPopView.b;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.db.DJIWPCollectionItem;
import dji.pilot.groundStation.db.DJIWPCollectionItem$WayPoint;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DJIGSWayPointStatusView extends DJIRelativeLayout implements c$i, a {
    private SeekBar n = null;
    private DJITextView o = null;
    private DJITextView p = null;
    private float q = 0.0f;
    private DJIImageView r = null;
    private boolean s = false;
    private DJITextView t = null;
    private OnClickListener u = new OnClickListener(this) {
        final /* synthetic */ DJIGSWayPointStatusView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            boolean z = true;
            switch (view.getId()) {
                case R.id.aol:
                    dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(this.a.getContext());
                    instance.f();
                    List h = instance.h();
                    DJIWPCollectionItem L = instance.L();
                    b bVar = new b();
                    bVar.a = d.a;
                    bVar.f = c.a;
                    if (this.a.s) {
                        e.c(c$i.k);
                        this.a.s = false;
                        this.a.r.setImageResource(R.drawable.gs_favorite_unselected);
                        if (h.contains(L)) {
                            L.setAutoAddFlag(0);
                            instance.a(h.indexOf(L));
                            bVar.b = R.string.gs_way_point_del_from_favorite;
                            dji.thirdparty.a.c.a().e(bVar);
                            return;
                        }
                        return;
                    }
                    this.a.s = true;
                    this.a.r.setImageResource(R.drawable.gs_favorite_selected);
                    if (L != null && L.getPoints().size() > 1) {
                        L.setAutoAddFlag(0);
                        if (h.contains(L)) {
                            instance.b(L);
                        } else {
                            h.add(0, L);
                            instance.a(L);
                        }
                        bVar.b = R.string.gs_way_point_add_to_favorite;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    }
                    return;
                case R.id.aom:
                    boolean N = dji.pilot.groundStation.a.a.getInstance(null).N();
                    Map hashMap = new HashMap();
                    if (N) {
                        hashMap.put(c$i.a, c$i.c);
                        e.a(c$i.m, hashMap);
                    } else {
                        hashMap.put(c$i.a, c$i.b);
                        e.a(c$i.m, hashMap);
                    }
                    this.a.t.setEnabled(false);
                    dji.pilot.groundStation.a.a instance2 = dji.pilot.groundStation.a.a.getInstance(null);
                    dji.midware.e.d anonymousClass1 = new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            this.a.a.b();
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.a.a.b();
                        }
                    };
                    if (N) {
                        z = false;
                    }
                    instance2.b(anonymousClass1, z);
                    return;
                case R.id.aon:
                    this.a.w = true;
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_exit_current_mession_view, 26, false);
                    return;
                case R.id.aoo:
                    this.a.w = true;
                    dji.pilot.groundStation.a.a.getInstance(null).a();
                    ((DJIStageView) this.a.getParent()).stop();
                    return;
                default:
                    return;
            }
        }
    };
    private final Handler v = new Handler();
    private boolean w = false;
    private int x = 0;
    private final Runnable y = new Runnable(this) {
        final /* synthetic */ DJIGSWayPointStatusView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.w) {
                DataFlycGetPushWayPointMissionInfo instance = DataFlycGetPushWayPointMissionInfo.getInstance();
                if (instance.getMissionType() == 1) {
                    this.a.x = instance.getTargetWayPoint();
                    this.a.c();
                }
                if (dji.pilot.groundStation.a.a.getInstance(null).N()) {
                    this.a.t.setText(R.string.gs_resume);
                } else {
                    this.a.t.setText(R.string.gs_pause);
                }
                this.a.v.postDelayed(this.a.y, 500);
            }
        }
    };
    private int z = 0;

    public DJIGSWayPointStatusView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.w = true;
    }

    public void dispatchOnResume() {
        int i = 0;
        this.w = false;
        this.v.post(this.y);
        this.q = dji.pilot.groundStation.a.a.getInstance(null).J();
        a(this.q);
        this.n.setProgress((int) (this.q * 20.0f));
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(getContext());
        DJIWPCollectionItem L = instance.L();
        if (L == null || L.getPoints().size() == 0) {
            ((DJIStageView) getParent()).createStageView(R.layout.gs_wait_download_mission_view, 29, false);
        } else if (instance.i().l().m().size() < 2) {
            instance.i().l().g();
            while (i < L.getPoints().size()) {
                DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) L.getPoints().get(i);
                instance.i().l().g(dji.gs.utils.a.a(new dji.gs.e.b(dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng())));
                i++;
            }
        }
        if (dji.pilot.groundStation.a.a.getInstance(null).N()) {
            this.t.setText(R.string.gs_resume);
        } else {
            this.t.setText(R.string.gs_pause);
        }
    }

    public void dispatchOnPause() {
        this.w = true;
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            findViewById(R.id.aoo).setOnClickListener(this.u);
            findViewById(R.id.aon).setOnClickListener(this.u);
            findViewById(R.id.aom).setOnClickListener(this.u);
            this.t = (DJITextView) findViewById(R.id.aom);
            this.o = (DJITextView) findViewById(R.id.aor);
            this.n = (SeekBar) findViewById(R.id.aoq);
            this.n.setThumbOffset(0);
            this.n.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
                final /* synthetic */ DJIGSWayPointStatusView a;

                {
                    this.a = r1;
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    this.a.q = ((float) i) / 20.0f;
                    this.a.a(this.a.q);
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                    this.a.q = ((float) seekBar.getProgress()) / 20.0f;
                    this.a.a(this.a.q);
                    dji.pilot.groundStation.a.a.getInstance(null).a(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            if (DataFlycWayPointSetIdleSpeed.getInstance().a() == 0) {
                                dji.pilot.groundStation.a.a.getInstance(null).c(this.a.a.q);
                            } else {
                                this.a.a.n.setProgress((int) (dji.pilot.groundStation.a.a.getInstance(null).J() * 20.0f));
                            }
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.a.a.n.setProgress((int) (dji.pilot.groundStation.a.a.getInstance(null).J() * 20.0f));
                        }
                    }, this.a.q);
                }
            });
            this.n.setProgress(70);
            this.p = (DJITextView) findViewById(R.id.aop);
            this.p.setText("N/A");
            this.r = (DJIImageView) findViewById(R.id.aol);
            this.r.setOnClickListener(this.u);
            this.s = a();
            if (this.s) {
                this.r.setImageResource(R.drawable.gs_favorite_selected);
            } else {
                this.r.setImageResource(R.drawable.gs_favorite_unselected);
            }
        }
    }

    private void a(float f) {
        this.z = DJIGenSettingDataManager.getInstance().v();
        if (DJIGenSettingDataManager.getInstance().v() == 0) {
            this.o.setText(String.format("%.1fMPH", new Object[]{Float.valueOf(dji.pilot.groundStation.b.b(f))}));
        } else if (DJIGenSettingDataManager.getInstance().v() == 2) {
            this.o.setText(String.format("%.1fKM/H", new Object[]{Float.valueOf(dji.pilot.groundStation.b.c(f))}));
        } else {
            this.o.setText(String.format("%.1fM/S", new Object[]{Float.valueOf(f)}));
        }
    }

    private boolean a() {
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(getContext());
        List h = instance.h();
        DJIWPCollectionItem L = instance.L();
        if (L.getAutoAddFlag() > 0) {
            return false;
        }
        return h.contains(L);
    }

    private void b() {
        this.t.post(new Runnable(this) {
            final /* synthetic */ DJIGSWayPointStatusView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.t.setEnabled(true);
            }
        });
    }

    private void c() {
        dji.pilot.groundStation.a.b instance = dji.pilot.groundStation.a.b.getInstance();
        DJIWPCollectionItem L = dji.pilot.groundStation.a.a.getInstance(null).L();
        double b = instance.b();
        double a = instance.a();
        double c = (double) instance.c();
        if (b != 0.0d || a != 0.0d) {
            int i = this.x;
            double d = 0.0d;
            while (i < L.getPoints().size()) {
                double a2;
                double d2 = ((DJIWPCollectionItem$WayPoint) L.getPoints().get(i)).lat;
                double d3 = ((DJIWPCollectionItem$WayPoint) L.getPoints().get(i)).lng;
                double d4 = ((DJIWPCollectionItem$WayPoint) L.getPoints().get(i)).height;
                if (i != this.x || i <= 0 || i >= L.getPoints().size() - 1) {
                    a2 = d + a(b, a, c, d2, d3, d4);
                } else {
                    a2 = ((DJIWPCollectionItem$WayPoint) L.getPoints().get(i - 1)).lat;
                    double d5 = ((DJIWPCollectionItem$WayPoint) L.getPoints().get(i - 1)).lng;
                    double d6 = ((DJIWPCollectionItem$WayPoint) L.getPoints().get(i - 1)).height;
                    double d7 = ((DJIWPCollectionItem$WayPoint) L.getPoints().get(i + 1)).lat;
                    double d8 = ((DJIWPCollectionItem$WayPoint) L.getPoints().get(i + 1)).lng;
                    double d9 = ((DJIWPCollectionItem$WayPoint) L.getPoints().get(i + 1)).height;
                    double a3 = a(a2, d5, d6, d2, d3, d4);
                    double a4 = a(a2, d5, d6, b, a, c);
                    double a5 = a(d2, d3, d4, b, a, c);
                    double a6 = a(d7, d8, d9, d2, d3, d4);
                    a2 = a(d7, d8, d9, b, a, c);
                    if (a4 < 2.0d || a2 == 0.0d || a5 == 0.0d) {
                        a6 = a(b, a, c, d2, d3, d4) + d;
                    } else if ((((a4 * a4) + (a5 * a5)) - (a3 * a3)) / ((2.0d * a4) * a5) > (((a2 * a2) + (a5 * a5)) - (a6 * a6)) / ((a2 * 2.0d) * a5)) {
                        a6 = a(b, a, c, d7, d8, d9) + d;
                        i++;
                    } else {
                        a6 = a(b, a, c, d2, d3, d4) + d;
                    }
                    a2 = a6;
                }
                i++;
                c = d4;
                a = d3;
                b = d2;
                d = a2;
            }
            if (this.z != DJIGenSettingDataManager.getInstance().v()) {
                a(this.q);
            }
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.p.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a((float) d))}));
                return;
            }
            this.p.setText(String.format("%.1fM", new Object[]{Double.valueOf(d)}));
        }
    }

    private double a(double d, double d2, double d3, double d4, double d5, double d6) {
        double a = dji.gs.utils.a.a(d, d2, d4, d5);
        return Math.sqrt((a * a) + ((d3 - d6) * (d3 - d6)));
    }
}
