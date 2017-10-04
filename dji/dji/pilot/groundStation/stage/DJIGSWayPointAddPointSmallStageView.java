package dji.pilot.groundStation.stage;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnSystemUiVisibilityChangeListener;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.forbid.FlyForbidProtocol;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.c;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.b;
import dji.pilot.groundStation.b.g;
import dji.pilot.groundStation.db.DJIWPCollectionItem;
import dji.pilot.groundStation.db.DJIWPCollectionItem$WayPoint;
import dji.pilot.publics.widget.DJIStateTextView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class DJIGSWayPointAddPointSmallStageView extends DJIRelativeLayout implements a {
    private static final int g = 500;
    private DJITextView a = null;
    private DJITextView b = null;
    private DJITextView c = null;
    private int d = 0;
    private DJIWPCollectionItem e = null;
    private DJIStateTextView f = null;
    private OnClickListener h = new OnClickListener(this) {
        final /* synthetic */ DJIGSWayPointAddPointSmallStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ant:
                    if (this.a.e.getPoints().size() > 0) {
                        ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_way_point_add_point_back_confirm, 28, false);
                        return;
                    }
                    ((DJIStageView) this.a.getParent()).destoryStageView(false);
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_way_point_view, 5, false);
                    return;
                case R.id.anu:
                    new g(this.a.getContext()).show();
                    return;
                case R.id.anv:
                    this.a.a();
                    return;
                case R.id.anw:
                    if (this.a.e.getPoints().size() > 1) {
                        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(this.a.getContext());
                        List h = instance.h();
                        DJIWPCollectionItem L = instance.L();
                        L.setCreatedDate(System.currentTimeMillis());
                        if (!h.contains(L)) {
                            for (int i = 0; i < h.size(); i++) {
                                DJIWPCollectionItem dJIWPCollectionItem = (DJIWPCollectionItem) h.get(i);
                                int autoAddFlag = dJIWPCollectionItem.getAutoAddFlag();
                                if (autoAddFlag > 0 && autoAddFlag < 3) {
                                    dJIWPCollectionItem.setAutoAddFlag(autoAddFlag + 1);
                                } else if (autoAddFlag == 3) {
                                    instance.a(i);
                                    L.setAutoAddFlag(1);
                                    h.add(0, L);
                                    instance.a(L);
                                }
                            }
                            L.setAutoAddFlag(1);
                            h.add(0, L);
                            instance.a(L);
                        }
                        this.a.e.setCreatedDate(System.currentTimeMillis());
                        this.a.e.setDistance(b.a(this.a.e.getPoints()));
                        dji.pilot.groundStation.a.a.getInstance(this.a.getContext()).d(false);
                        ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_way_point_auto_fly_setting_view, 20, true);
                        return;
                    }
                    DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                    bVar.a = d.b;
                    bVar.f = c.a;
                    bVar.b = R.string.gs_way_point_collection_item_too_less_point;
                    dji.thirdparty.a.c.a().e(bVar);
                    return;
                case R.id.anx:
                    this.a.b();
                    return;
                default:
                    return;
            }
        }
    };
    private dji.pilot.groundStation.a.a.b i = new dji.pilot.groundStation.a.a.b(this) {
        final /* synthetic */ DJIGSWayPointAddPointSmallStageView a;

        {
            this.a = r1;
        }

        public void onClick(int i) {
            if (i == 1) {
                this.a.a();
            } else if (i == 2) {
                this.a.b();
            } else if (i == 100) {
                this.a.a(true);
            }
        }
    };
    private final Handler j = new Handler();
    private boolean k = false;
    private final Runnable l = new Runnable(this) {
        final /* synthetic */ DJIGSWayPointAddPointSmallStageView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.k) {
                if (!dji.pilot.groundStation.a.a.getInstance(null).i().n()) {
                    this.a.c();
                }
                this.a.j.postDelayed(this.a.l, 500);
            }
        }
    };

    private int getMaxMissionRadius() {
        return 500;
    }

    public DJIGSWayPointAddPointSmallStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        dji.pilot.groundStation.a.a.getInstance(null).a(null);
        this.k = true;
    }

    public void dispatchOnResume() {
        this.e = dji.pilot.groundStation.a.a.getInstance(null).L();
        this.d = this.e.getPoints().size();
        this.a.setText(String.format("%d", new Object[]{Integer.valueOf(this.d)}));
        a(true);
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).invalidate();
        }
        if (DJIGenSettingDataManager.getInstance().v() == 0) {
            this.c.setText(String.format(getContext().getString(R.string.gs_way_point_limits), new Object[]{Integer.valueOf((int) b.a((float) getMaxMissionRadius())), "FT", Integer.valueOf((int) b.a(5000.0f)), "FT"}));
        } else {
            this.c.setText(String.format(getContext().getString(R.string.gs_way_point_limits), new Object[]{Integer.valueOf(getMaxMissionRadius()), "M", Integer.valueOf(5000), "M"}));
        }
        dji.pilot.groundStation.a.a.getInstance(null).a(this.i);
        dji.pilot.groundStation.a.a.getInstance(null).i().o();
        this.k = false;
    }

    public void dispatchOnPause() {
        dji.pilot.groundStation.a.a.getInstance(null).a(null);
        this.k = true;
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJITextView) findViewById(R.id.anz);
            this.b = (DJITextView) findViewById(R.id.ao0);
            this.c = (DJITextView) findViewById(R.id.any);
            this.f = (DJIStateTextView) findViewById(R.id.anx);
            this.f.getPaint().setFlags(8);
            findViewById(R.id.ant).setOnClickListener(this.h);
            findViewById(R.id.anv).setOnClickListener(this.h);
            findViewById(R.id.anx).setOnClickListener(this.h);
            findViewById(R.id.anw).setOnClickListener(this.h);
            findViewById(R.id.anu).setOnClickListener(this.h);
            if (i.getInstance().c() != ProductType.litchiC) {
                ((DJITextView) findViewById(R.id.anv)).setText(getResources().getString(R.string.gs_way_point_add_point_btn) + "(C1)");
                ((DJITextView) findViewById(R.id.anx)).setText(getResources().getString(R.string.gs_way_point_del_point_btn) + "(C2)");
            }
            setOnSystemUiVisibilityChangeListener(new OnSystemUiVisibilityChangeListener(this) {
                final /* synthetic */ DJIGSWayPointAddPointSmallStageView a;

                {
                    this.a = r1;
                }

                public void onSystemUiVisibilityChange(int i) {
                    this.a.a(true);
                }
            });
        }
    }

    private void a() {
        if (this.e.getPoints().size() >= 99) {
            DJIErrorPopView.b bVar = new DJIErrorPopView.b();
            bVar.a = d.b;
            bVar.f = c.a;
            bVar.b = R.string.gs_way_point_add_point_size_limit;
            dji.thirdparty.a.c.a().e(bVar);
            return;
        }
        DataOsdGetPushCommon instance = DataOsdGetPushCommon.getInstance();
        if (!instance.isSwaveWork() || instance.getSwaveHeight() > 30) {
            dji.gs.e.b f = dji.pilot.groundStation.a.a.getInstance(null).i().l().f();
            if (instance.getGpsLevel() < 3 || f == null || f.b == 0.0d || f.c == 0.0d) {
                bVar = new DJIErrorPopView.b();
                bVar.a = d.b;
                bVar.f = c.a;
                bVar.b = R.string.gs_way_point_add_point_gps_weak;
                dji.thirdparty.a.c.a().e(bVar);
                return;
            } else if (a(false)) {
                dji.pilot.groundStation.a.b instance2 = dji.pilot.groundStation.a.b.getInstance();
                double b = instance2.b();
                double a = instance2.a();
                double c = (double) instance2.c();
                int yaw = instance.getYaw() / 10;
                int yawAngle = DataGimbalGetPushParams.getInstance().getYawAngle() / 10;
                dji.gs.e.b b2 = dji.gs.utils.a.b(f);
                if (dji.gs.utils.a.a(b, a, b2.b, b2.c) > ((double) getMaxMissionRadius())) {
                    bVar = new DJIErrorPopView.b();
                    bVar.a = d.b;
                    bVar.f = c.a;
                    bVar.b = R.string.gs_way_point_add_point_radius_limit;
                    dji.thirdparty.a.c.a().e(bVar);
                    return;
                }
                if (this.e.getPoints().size() > 0) {
                    DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) this.e.getPoints().get(this.e.getPoints().size() - 1);
                    double a2 = dji.gs.utils.a.a(b, a, dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng());
                    if (Math.sqrt((a2 * a2) + ((c - dJIWPCollectionItem$WayPoint.getHeight()) * (c - dJIWPCollectionItem$WayPoint.getHeight()))) < 5.0d) {
                        bVar = new DJIErrorPopView.b();
                        bVar.a = d.b;
                        bVar.f = c.a;
                        bVar.b = R.string.gs_way_point_add_point_distance_limit;
                        dji.thirdparty.a.c.a().e(bVar);
                        return;
                    }
                }
                if (c < 10.0d) {
                    DJIErrorPopView.b bVar2 = new DJIErrorPopView.b();
                    bVar2.a = d.b;
                    bVar2.f = c.a;
                    bVar2.b = R.string.gs_way_point_attitude_too_low_warning;
                    dji.thirdparty.a.c.a().e(bVar2);
                }
                DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint2 = new DJIWPCollectionItem$WayPoint(b, a, c);
                dJIWPCollectionItem$WayPoint2.setCraftYaw(yaw);
                dJIWPCollectionItem$WayPoint2.setGimbalYaw(yawAngle);
                this.e.getPoints().add(dJIWPCollectionItem$WayPoint2);
                dji.pilot.groundStation.a.a.getInstance(null).i().l().g(dji.gs.utils.a.a(new dji.gs.e.b(b, a)));
                this.d++;
                this.a.setText(String.format("%d", new Object[]{Integer.valueOf(this.d)}));
                a(true);
                return;
            } else {
                bVar = new DJIErrorPopView.b();
                bVar.a = d.b;
                bVar.f = c.a;
                bVar.b = R.string.gs_way_point_distance_too_long;
                dji.thirdparty.a.c.a().e(bVar);
                return;
            }
        }
        bVar = new DJIErrorPopView.b();
        bVar.a = d.b;
        bVar.f = c.a;
        bVar.b = R.string.gs_way_point_has_ultrasonic_data;
        dji.thirdparty.a.c.a().e(bVar);
    }

    private void b() {
        if (this.e.getPoints().size() > 0) {
            int size = this.e.getPoints().size() - 1;
            this.e.getPoints().remove(size);
            dji.pilot.groundStation.a.a.getInstance(null).i().l().d(size);
            this.d--;
            this.a.setText(String.format("%d", new Object[]{Integer.valueOf(this.d)}));
            a(true);
        }
    }

    private boolean a(boolean z) {
        if (this.e.getPoints().size() >= 2) {
            if (b.a(this.e.getPoints()) >= FlyForbidProtocol.STRONG_WARNING_CHECK_RADIUS) {
                return false;
            }
            if (z) {
                if (DJIGenSettingDataManager.getInstance().v() == 0) {
                    this.b.setText(String.format("%.1fFT", new Object[]{Float.valueOf(b.a((float) r2))}));
                } else {
                    this.b.setText(String.format("%.1fM", new Object[]{Double.valueOf(r2)}));
                }
            }
        } else if (z) {
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.b.setText("0FT");
            } else {
                this.b.setText("0M");
            }
        }
        return true;
    }

    private void c() {
        DJIWPCollectionItem dJIWPCollectionItem = this.e;
        if (dJIWPCollectionItem != null && dJIWPCollectionItem.getPoints().size() > 0) {
            dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(null);
            DataOsdGetPushCommon instance2 = DataOsdGetPushCommon.getInstance();
            dji.gs.e.b a = dji.gs.utils.a.a(new dji.gs.e.b(instance2.getLatitude(), instance2.getLongitude()));
            dji.gs.e.b bVar = new dji.gs.e.b(a.b, a.c);
            for (int i = 0; i < dJIWPCollectionItem.getPoints().size(); i++) {
                dji.gs.e.b a2 = dji.gs.utils.a.a(new dji.gs.e.b(((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(i)).getLat(), ((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(i)).getLng()));
                if (a.b > a2.b) {
                    a.b = a2.b;
                }
                if (a.c > a2.c) {
                    a.c = a2.c;
                }
                if (bVar.b < a2.b) {
                    bVar.b = a2.b;
                }
                if (bVar.c < a2.c) {
                    bVar.c = a2.c;
                }
            }
            instance.i().a(a, bVar);
        }
    }
}
