package dji.pilot.groundStation.stage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.os.Handler;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.FINISH_ACTION;
import dji.midware.data.model.P3.DataFlycUploadWayPointMissionMsg.YAW_MODE;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.a;
import dji.pilot.groundStation.a.b;
import dji.pilot.groundStation.db.DJIWPCollectionItem;
import dji.pilot.groundStation.db.DJIWPCollectionItem$WayPoint;
import dji.pilot.publics.widget.CustomerSpinner;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.util.List;

public class DJIGSWayPointAutoFlySettingStageView extends DJIRelativeLayout implements c$i, a {
    private boolean A = false;
    private final Runnable B = new Runnable(this) {
        final /* synthetic */ DJIGSWayPointAutoFlySettingStageView a;

        {
            this.a = r1;
        }

        public void run() {
            if (!this.a.A) {
                DJIWPCollectionItem L = dji.pilot.groundStation.a.a.getInstance(this.a.getContext()).L();
                if (L != null && L.getPoints().size() > 0) {
                    DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) L.getPoints().get(0);
                    b instance = b.getInstance();
                    double b = instance.b();
                    double a = instance.a();
                    if (!(a == 0.0d && b == 0.0d)) {
                        this.a.w = (float) dji.gs.utils.a.a(b, a, dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng());
                        b = ((double) instance.c()) - dJIWPCollectionItem$WayPoint.getHeight();
                        this.a.v = (float) Math.sqrt((b * b) + ((double) (this.a.w * this.a.w)));
                        if (DJIGenSettingDataManager.getInstance().v() == 0) {
                            this.a.u.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a(this.a.v))}));
                        } else {
                            this.a.u.setText(String.format("%.1fM", new Object[]{Float.valueOf(this.a.v)}));
                        }
                        if (this.a.w > DJIFlightControllerDataType.DJIVirtualStickYawControlMaxAngularVelocity) {
                            this.a.u.setTextColor(SupportMenu.CATEGORY_MASK);
                        } else {
                            this.a.u.setTextColor(-16711936);
                        }
                    }
                }
                this.a.z.postDelayed(this.a.B, 200);
            }
        }
    };
    private CustomerSpinner n = null;
    private CustomerSpinner o = null;
    private SeekBar p = null;
    private DJITextView q = null;
    private float r = 0.0f;
    private DJIImageView s = null;
    private boolean t = false;
    private DJITextView u = null;
    private float v = 1000000.0f;
    private float w = 1000000.0f;
    private View x = null;
    private OnClickListener y = new OnClickListener(this) {
        final /* synthetic */ DJIGSWayPointAutoFlySettingStageView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.ao1:
                    if (((DJIStageView) this.a.getParent()).destoryStageView(false) == null) {
                        ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_way_point_add_point_small_view, 23, false);
                        return;
                    }
                    return;
                case R.id.ao2:
                    dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(this.a.getContext());
                    instance.f();
                    c.a().e(dji.pilot.groundStation.a.a.c.HideFavoriteTip);
                    List h = instance.h();
                    DJIWPCollectionItem L = instance.L();
                    L.setAutoAddFlag(0);
                    DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                    bVar.a = d.a;
                    bVar.f = DJIErrorPopView.c.a;
                    if (this.a.t) {
                        e.c(c$i.k);
                        this.a.t = false;
                        this.a.s.setImageResource(R.drawable.gs_favorite_unselected);
                        if (h.contains(L)) {
                            instance.a(h.indexOf(L));
                            bVar.b = R.string.gs_way_point_del_from_favorite;
                            c.a().e(bVar);
                            return;
                        }
                        return;
                    }
                    this.a.t = true;
                    this.a.s.setImageResource(R.drawable.gs_favorite_selected);
                    if (h.contains(L)) {
                        instance.b(L);
                    } else {
                        h.add(0, L);
                        instance.a(L);
                    }
                    bVar.b = R.string.gs_way_point_add_to_favorite;
                    c.a().e(bVar);
                    return;
                case R.id.ao3:
                    DJIErrorPopView.b bVar2;
                    if (DataOsdGetPushCommon.getInstance().groundOrSky() != 2) {
                        bVar2 = new DJIErrorPopView.b();
                        bVar2.a = d.a;
                        bVar2.f = DJIErrorPopView.c.a;
                        bVar2.b = R.string.gs_not_take_off_warning_title;
                        c.a().e(bVar2);
                        return;
                    }
                    int voltageWarning = DataOsdGetPushCommon.getInstance().getVoltageWarning();
                    if (voltageWarning == 1 || voltageWarning == 2) {
                        bVar2 = new DJIErrorPopView.b();
                        bVar2.a = d.b;
                        bVar2.f = DJIErrorPopView.c.a;
                        bVar2.b = R.string.battery_low_warning;
                        c.a().e(bVar2);
                        return;
                    }
                    dji.pilot.groundStation.b.e eVar = new dji.pilot.groundStation.b.e(this.a.getContext());
                    this.a.x.setEnabled(false);
                    eVar.setOnDismissListener(new OnDismissListener(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onDismiss(DialogInterface dialogInterface) {
                            this.a.a.x.setEnabled(true);
                        }
                    });
                    eVar.a(false, new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass2 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            dji.pilot.groundStation.a.a.getInstance(null).c(this.a.a.r);
                            FINISH_ACTION finish_action = FINISH_ACTION.NO_LIMIT;
                            switch (this.a.a.n.getSelectedItemPosition()) {
                                case 0:
                                    finish_action = FINISH_ACTION.NO_LIMIT;
                                    break;
                                case 1:
                                    finish_action = FINISH_ACTION.GOHOME;
                                    break;
                            }
                            dji.pilot.groundStation.a.a.getInstance(null).a(finish_action);
                            YAW_MODE yaw_mode = YAW_MODE.PATH_COURSE;
                            switch (this.a.a.o.getSelectedItemPosition()) {
                                case 0:
                                    yaw_mode = YAW_MODE.PATH_COURSE;
                                    break;
                                case 1:
                                    yaw_mode = YAW_MODE.AUTO_COURSE;
                                    break;
                                case 2:
                                    yaw_mode = YAW_MODE.REMOTE_CONTROL;
                                    break;
                            }
                            dji.pilot.groundStation.a.a.getInstance(null).a(yaw_mode);
                            if (this.a.a.v > 50.0f) {
                                ((DJIStageView) this.a.a.getParent()).createStageView(R.layout.gs_way_point_start_confirm, 27, false);
                            } else {
                                ((DJIStageView) this.a.a.getParent()).createStageView(R.layout.gs_way_point_wait_upload_mission_view, 11, true);
                            }
                        }
                    });
                    eVar.show();
                    return;
                default:
                    return;
            }
        }
    };
    private final Handler z = new Handler();

    public DJIGSWayPointAutoFlySettingStageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.A = true;
        c.a().e(dji.pilot.groundStation.a.a.c.HideFavoriteTip);
    }

    public void dispatchOnResume() {
        this.A = false;
        this.z.post(this.B);
        this.t = a();
        if (this.t) {
            this.s.setImageResource(R.drawable.gs_favorite_selected);
        } else {
            this.s.setImageResource(R.drawable.gs_favorite_unselected);
        }
        if (!dji.pilot.groundStation.a.a.getInstance(null).e()) {
            c.a().e(dji.pilot.groundStation.a.a.c.ShowFavoriteTip);
        }
        this.x.setEnabled(true);
    }

    public void dispatchOnPause() {
        this.A = true;
        c.a().e(dji.pilot.groundStation.a.a.c.HideFavoriteTip);
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.x = findViewById(R.id.ao3);
            this.x.setOnClickListener(this.y);
            findViewById(R.id.ao1).setOnClickListener(this.y);
            this.n = (CustomerSpinner) findViewById(R.id.ao8);
            this.n.setData(new String[]{getContext().getString(R.string.gs_way_point_auto_fly_setting_finish_action_hove), getContext().getString(R.string.gs_way_point_auto_fly_setting_finish_action_go_home)});
            this.q = (DJITextView) findViewById(R.id.aoa);
            this.p = (SeekBar) findViewById(R.id.ao_);
            this.p.setThumbOffset(0);
            this.p.setOnSeekBarChangeListener(new OnSeekBarChangeListener(this) {
                final /* synthetic */ DJIGSWayPointAutoFlySettingStageView a;

                {
                    this.a = r1;
                }

                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    this.a.r = ((float) i) / 20.0f;
                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                        this.a.q.setText(String.format("%.1fMPH", new Object[]{Float.valueOf(dji.pilot.groundStation.b.b(this.a.r))}));
                    } else if (DJIGenSettingDataManager.getInstance().v() == 2) {
                        this.a.q.setText(String.format("%.1fKM/H", new Object[]{Float.valueOf(dji.pilot.groundStation.b.c(this.a.r))}));
                    } else {
                        this.a.q.setText(String.format("%.1fM/S", new Object[]{Float.valueOf(this.a.r)}));
                    }
                }

                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                public void onStopTrackingTouch(SeekBar seekBar) {
                }
            });
            this.p.setProgress(70);
            this.s = (DJIImageView) findViewById(R.id.ao2);
            this.s.setOnClickListener(this.y);
            this.u = (DJITextView) findViewById(R.id.ao6);
            this.o = (CustomerSpinner) findViewById(R.id.ao7);
            this.o.setData(new String[]{getContext().getString(R.string.gs_way_point_auto_fly_setting_yaw_mode_1), getContext().getString(R.string.gs_way_point_auto_fly_setting_yaw_mode_2), getContext().getString(R.string.gs_way_point_auto_fly_setting_yaw_mode_3)});
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
}
