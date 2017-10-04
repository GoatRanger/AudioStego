package dji.pilot2.main.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.facebook.f;
import com.facebook.share.model.ShareLinkContent;
import com.tencent.android.tpush.common.Constants;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.log.DJILogHelper;
import dji.logic.h.a;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCenterGetBoardNumber;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.natives.FPVController;
import dji.midware.natives.GroudStation;
import dji.pilot.R;
import dji.pilot.battery.model.InvalidBatteryEvent;
import dji.pilot.battery.service.BatteryCheckService;
import dji.pilot.flightrecord.DJIRecordService;
import dji.pilot.flyforbid.FlyforbidUpdateService;
import dji.pilot.fpv.d.c.d;
import dji.pilot.fpv.d.c.e;
import dji.pilot.fpv.d.c.j;
import dji.pilot.fpv.d.c.k;
import dji.pilot.main.activity.DJISplashActivity;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.usercenter.mode.g;
import dji.pilot2.DJIFragmentActivityNoFullScreen;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.flymonitor.model.event.FlyUploadConfigEvent;
import dji.pilot2.flymonitor.service.FlyMonitorService;
import dji.pilot2.library.DJILibraryPhotoView;
import dji.pilot2.library.MixAlbumSyncManager;
import dji.pilot2.library.model.DJIScanerMediaManager;
import dji.pilot2.library.model.DJISycAlbumModel;
import dji.pilot2.main.fragment.DJILibraryFragment;
import dji.pilot2.main.fragment.DJIMineFragment;
import dji.pilot2.main.fragment.DJIPhantomFragment;
import dji.pilot2.main.model.ClickMediaTipEvent;
import dji.pilot2.media.activity.DJIPhotoEditorActivity;
import dji.pilot2.multimoment.activity.DJIMultiMomentEditActivity;
import dji.pilot2.multimoment.videolib.EditRecoverInfo;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.publics.object.DJINotificationDialog;
import dji.pilot2.publics.object.b;
import dji.pilot2.share.model.UploadEvent;
import dji.pilot2.utils.l;
import dji.pilot2.utils.l$a;
import dji.pilot2.utils.m;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressLint({"InflateParams"})
public class DJIMainFragmentActivity extends DJIFragmentActivityNoFullScreen implements OnClickListener, d, e, j, k, l$a {
    public static final int K = 4097;
    public static final int L = 4098;
    public static final String M = "initial_tab";
    public static final String N = "cut_moment_is_delete_cache";
    public static final String O = "cut_moment_file_name";
    public static final String P = "cut_moment_is_hd";
    public static volatile int R = 0;
    private static final long aU = 5000;
    private static final int ab = 2000;
    private static final int al = 0;
    private static final int am = 1;
    private static final int an = 2;
    private static final int ao = 3;
    private static final int ap = 4;
    private static int aq = 0;
    private static final int as = 0;
    private static final int at = 1;
    private static final int au = 2;
    private static final int av = 3;
    private static final int aw = 4;
    private static final int ax = 5;
    private static final int ay = 6;
    public LinearLayout Q;
    Handler S = new Handler(new 2(this));
    private String[] T = new String[]{"Device", "Library", "Skypixel", "Mine"};
    private int[] U = new int[]{R.drawable.v2_main_tab_phantom_selector, R.drawable.v2_main_tab_library_selector, R.drawable.v2_main_tab_explore_selector, R.drawable.v2_main_tab_mine_selector, R.drawable.v2_main_tab_library_anintion};
    private FragmentManager V;
    private FragmentTransaction W;
    private List<View> X;
    private int Y;
    private Fragment[] Z;
    private Intent aA;
    private Intent aB;
    private Intent aC;
    private HashMap<String, TextView> aD;
    private Context aE;
    private Boolean aF = Boolean.valueOf(false);
    private float aG = 0.0f;
    private boolean aH;
    private int aI;
    private volatile boolean aJ;
    private boolean aK;
    private String aL = "";
    private f aM;
    private com.facebook.share.widget.f aN;
    private final String aO = getClass().getSimpleName();
    private OnClickListener aP = new 9(this);
    private boolean aQ = true;
    private String aR = null;
    private boolean aS = false;
    private long aT = 0;
    private long aV = 0;
    private long aa = 0;
    private DJIOriLayout ac;
    private boolean ad = false;
    private DJIRelativeLayout ae = null;
    private DJIRelativeLayout af;
    private DJIImageView ag = null;
    private DJITextView ah = null;
    private DJITextView ai = null;
    private Animation aj = null;
    private ImageView ak = null;
    private int ar = 1;
    private Intent az;

    public int a() {
        Log.i("DJIFirstTipDialog.TAG", "currentTabIndex" + this.Y);
        return this.Y;
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (R == 0) {
            int[] iArr = new int[2];
            this.Q.getLocationOnScreen(iArr);
            R = iArr[1];
        }
        if (this.aK) {
            this.S.post(new 1(this));
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.aH = false;
        this.aK = false;
        DJIUpgradeP4Service.a((Context) this);
        this.az = new Intent(this, DJIUpgradeP4Service.class);
        startService(this.az);
        this.aI = getIntent().getIntExtra(M, -1);
        this.aA = new Intent(this, DJIGlobalService.class);
        startService(this.aA);
        this.aC = new Intent(this, FlyforbidUpdateService.class);
        startService(this.aC);
        a.getInstance().a(dji.pilot.publics.c.a.a);
        this.aB = new Intent(this, DJIRecordService.class);
        startService(this.aB);
        if (dji.pilot.usercenter.b.f.getInstance().c() && m.c(dji.pilot.usercenter.b.f.getInstance().o())) {
            dji.pilot.usercenter.b.f.getInstance().p();
        }
        this.aE = this;
        setContentView(R.layout.v2_activity_main);
        getWindow().addFlags(128);
        this.aD = new HashMap();
        j();
        DJIOriLayout.setOrientationByDevice(this);
        k();
        l();
        this.aj = AnimationUtils.loadAnimation(this, R.anim.c2);
        l.getInstance().a(this);
        dji.pilot2.utils.a.a.getInstance().b(new 6(this));
        dji.pilot2.utils.a.a.getInstance().b(this);
        MixAlbumSyncManager.getInstance(this).getDeleteDb(this);
        o();
        if (ServiceManager.getInstance().isRemoteOK()) {
            this.S.sendEmptyMessageDelayed(0, 500);
        }
        i();
        h();
        Log.i("zyx", "send notice!");
        this.S.sendEmptyMessageDelayed(6, 1000);
        Intent intent = new Intent(this, FlyMonitorService.class);
        intent.putExtra("service_type", 0);
        startService(intent);
        this.S.postDelayed(new 7(this), 5000);
        s();
        t();
    }

    private void h() {
        String stringExtra = getIntent().getStringExtra(DJISplashActivity.f);
        if (stringExtra != null) {
            Intent intent = new Intent(this, DJISupportShareWebviewActivity.class);
            intent.putExtra(DJIWebviewFragment.o, stringExtra);
            startActivity(intent);
        }
    }

    private void i() {
        new Handler().post(new 8(this));
    }

    protected void onStart() {
        super.onStart();
        dji.pilot.fpv.d.e.b((Context) this);
        c.a().e(a.FINISH_LAUNCHER);
    }

    protected void onResume() {
        super.onResume();
        if (dji.pilot.usercenter.b.f.getInstance().c()) {
            this.aH = true;
        } else {
            this.aH = true;
        }
        if (this.aI >= 0 && this.aI < this.X.size()) {
            ((View) this.X.get(this.aI)).performClick();
        }
        this.aI = -1;
    }

    protected void onPause() {
        super.onPause();
        Log.e(this.aO, "onPause");
        this.aH = false;
    }

    protected void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        m();
        super.onDestroy();
        n();
    }

    public void onBackPressed() {
        if (this.Y == 3) {
            DJIMineFragment dJIMineFragment = (DJIMineFragment) getFragmentManager().findFragmentById(R.id.c9e);
            if (dJIMineFragment != null && dJIMineFragment.e()) {
                dJIMineFragment.f();
                return;
            }
        }
        if (aq == 2 || aq == 1 || aq == 3 || aq == 4) {
            ((DJILibraryFragment) getFragmentManager().findFragmentById(R.id.c9c)).a.exitSelectMode();
            f();
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.aa >= 2000) {
            this.aa = currentTimeMillis;
            Toast.makeText(this, R.string.main_back_tip, 0).show();
            return;
        }
        dji.pilot2.simulator.d.a();
        m();
        onPause();
        onStop();
        onDestroy();
    }

    private void j() {
        FPVController.loadLibrary();
        GroudStation.loadLibrary();
        this.aJ = true;
    }

    private void k() {
        this.V = getFragmentManager();
        this.W = this.V.beginTransaction();
        this.Z = new Fragment[4];
        this.Z[0] = this.V.findFragmentById(R.id.c9b);
        this.Z[1] = this.V.findFragmentById(R.id.c9c);
        this.Z[2] = this.V.findFragmentById(R.id.c9d);
        this.Z[3] = this.V.findFragmentById(R.id.c9e);
        this.W = this.V.beginTransaction().hide(this.Z[1]).hide(this.Z[2]).hide(this.Z[3]).show(this.Z[0]);
        this.W.commitAllowingStateLoss();
        this.ac = (DJIOriLayout) findViewById(R.id.c7j);
        this.Q = (LinearLayout) findViewById(R.id.c9g);
        this.ae = (DJIRelativeLayout) findViewById(R.id.bk0);
        this.af = (DJIRelativeLayout) findViewById(R.id.bjz);
        this.ai = (DJITextView) findViewById(R.id.bk3);
        this.ag = (DJIImageView) findViewById(R.id.bk2);
        this.ah = (DJITextView) findViewById(R.id.bk1);
        this.ah.setVisibility(8);
        this.ag.setOnClickListener(this);
        this.ai.setOnClickListener(this);
    }

    private void l() {
        this.X = new ArrayList();
        this.Y = 0;
        this.T[0] = getString(R.string.v2_tabhost_equipment);
        this.T[1] = getString(R.string.v2_tabhost_library);
        this.T[2] = getString(R.string.v2_tabhost_discovery);
        this.T[3] = getString(R.string.v2_tabhost_me);
        for (int i = 0; i < this.T.length; i++) {
            Object obj;
            View inflate = getLayoutInflater().inflate(R.layout.v2_main_tab_item, null);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.cs6);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.cs7);
            if (i == 2) {
                obj = (TextView) inflate.findViewById(R.id.cs8);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(45, 45);
                layoutParams.addRule(11);
                obj.setLayoutParams(layoutParams);
            } else {
                TextView textView = (TextView) inflate.findViewById(R.id.cs8);
            }
            imageView.setImageResource(this.U[i]);
            if (i == 1) {
                this.ak = imageView2;
            }
            ((TextView) inflate.findViewById(R.id.cs9)).setText(this.T[i]);
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams(0, -1);
            layoutParams2.weight = 1.0f;
            inflate.setOnClickListener(this.aP);
            if (i == 0) {
                inflate.setSelected(true);
            }
            this.X.add(inflate);
            this.Q.addView(inflate, layoutParams2);
            this.aD.put(this.T[i], obj);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bk2:
                q();
                return;
            case R.id.bk3:
                if (this.aJ) {
                    this.aJ = false;
                    DJILibraryFragment dJILibraryFragment = (DJILibraryFragment) getFragmentManager().findFragmentById(R.id.c9c);
                    if (this.ar == 2) {
                        DJISycAlbumModel dJISycAlbumModel = (DJISycAlbumModel) DJILibraryPhotoView.B.get(0);
                        if (dJISycAlbumModel != null) {
                            Intent intent = new Intent();
                            intent.setClass(this, DJIPhotoEditorActivity.class);
                            if (dJISycAlbumModel.isOrg) {
                                intent.putExtra("key_filepath", dJISycAlbumModel.orgPath);
                            } else {
                                intent.putExtra("key_filepath", dJISycAlbumModel.mLocalInfo.e);
                            }
                            startActivity(intent);
                        }
                    } else if (this.ar == 1) {
                        List list = dJILibraryFragment.a.r.q;
                        String[] strArr = new String[list.size()];
                        int[] iArr = new int[list.size()];
                        int i = 0;
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            strArr[i2] = ((g) list.get(i2)).e;
                            iArr[i2] = dji.pilot2.multimoment.videolib.d.a(strArr[i2]);
                            i += iArr[i2];
                        }
                        if (i <= dji.pilot2.multimoment.videolib.c.b || list.size() <= 1) {
                            if (strArr.length == 1) {
                                dji.pilot.fpv.d.e.b(k.bx_);
                            } else {
                                dji.pilot.fpv.d.e.b(k.bw_);
                            }
                            Intent intent2 = new Intent(this, DJIMultiMomentEditActivity.class);
                            intent2.putExtra(DJIMultiMomentEditActivity.M, strArr);
                            intent2.putExtra("duration", iArr);
                            startActivity(intent2);
                        } else {
                            b(i);
                            this.aJ = true;
                            return;
                        }
                    } else if (this.ar == 3) {
                        Log.i("zc", "click input !");
                        r1 = (DJILibraryFragment) getFragmentManager().findFragmentById(R.id.c9c);
                        if (r1 != null) {
                            r1.a(DJIScanerMediaManager.getInstance(this.aE).getFlagVideo());
                        }
                    } else if (this.ar == 4) {
                        Log.i("zc", "click delete !");
                        r1 = (DJILibraryFragment) getFragmentManager().findFragmentById(R.id.c9c);
                        if (r1 != null) {
                            r1.a();
                        }
                    }
                    dJILibraryFragment.a.exitSelectMode();
                    f();
                    this.aJ = true;
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void a(int i, int i2) {
        Log.i("zvb", "enter" + i2);
        Log.i("zvb", "enter typeMode" + i);
        aq = i;
        this.Q.setVisibility(4);
        this.ac.setBackgroundColor(getResources().getColor(R.color.na));
        this.ae.setBackgroundColor(getResources().getColor(R.color.na));
        if (i == 1) {
            this.ai.setClickable(true);
            this.ai.show();
            this.ag.show();
            this.ar = 1;
            if (i2 > 1) {
                this.ai.setText(getResources().getString(R.string.v2_library_video_selected_mult));
                this.ai.setEnabled(true);
            } else {
                this.ai.setText(getResources().getString(R.string.v2_library_video_selected_single));
                this.ai.setEnabled(true);
            }
            this.ae.show();
        } else if (i == 2) {
            this.ar = 2;
            this.ag.show();
            this.ai.show();
            if (i2 == 1) {
                this.ai.setClickable(true);
                this.ai.setText(getResources().getString(R.string.v2_photo_preview_share_photo));
                this.ai.setEnabled(true);
            } else {
                this.ai.setClickable(false);
                this.ai.setText(getResources().getString(R.string.v2_photo_preview_share_photo));
                this.ai.setEnabled(false);
            }
            this.ae.show();
        } else if (i == 3) {
            this.ar = 3;
            this.ag.go();
            this.Q.setVisibility(8);
            this.ai.setText(getResources().getString(R.string.v2_lib_input));
            if (i2 == 0) {
                this.ai.setClickable(false);
                this.ai.setEnabled(false);
                this.af.go();
                this.ai.go();
                this.ae.go();
                this.ac.setBackgroundColor(getResources().getColor(R.color.om));
                this.ae.setBackgroundColor(getResources().getColor(R.color.om));
                return;
            }
            this.af.show();
            this.ai.show();
            this.ai.setClickable(true);
            this.ai.setEnabled(true);
            this.ae.show();
            this.ac.setBackgroundColor(getResources().getColor(R.color.na));
            this.ae.setBackgroundColor(getResources().getColor(R.color.na));
        } else if (i == 4) {
            this.ar = 4;
            this.ai.setText(getResources().getString(R.string.delete));
            this.Q.setVisibility(8);
            this.ag.go();
            if (i2 == 0) {
                this.ai.setClickable(false);
                this.ai.setEnabled(false);
                this.af.go();
                this.ai.go();
                this.ac.setBackgroundColor(getResources().getColor(R.color.om));
                this.ae.setBackgroundColor(getResources().getColor(R.color.om));
                return;
            }
            this.ai.setClickable(true);
            this.ai.setEnabled(true);
            this.ai.show();
            this.af.go();
            this.ac.setBackgroundColor(getResources().getColor(R.color.na));
            this.ae.setBackgroundColor(getResources().getColor(R.color.na));
            this.ae.show();
        }
    }

    public void f() {
        aq = 0;
        this.Q.setVisibility(0);
        if (this.Y == 0) {
            this.ac.setBackgroundColor(getResources().getColor(R.color.om));
        } else {
            this.ac.setBackgroundColor(getResources().getColor(R.color.om));
        }
        this.ae.go();
        this.af.show();
    }

    private void m() {
        if (!this.ad) {
            p();
            this.ad = true;
            DJILogHelper.getInstance().closeLog();
            dji.pilot.fpv.d.e.c((Context) this);
            stopService(this.aB);
            stopService(this.aA);
            stopService(this.az);
            stopService(this.aC);
        }
    }

    private void n() {
        ServiceManager.Destroy();
        System.exit(0);
        ((ActivityManager) getSystemService(Constants.FLAG_ACTIVITY_NAME)).killBackgroundProcesses(getPackageName());
    }

    private void o() {
        c.a().a((Object) this);
    }

    private void p() {
        c.a().d((Object) this);
    }

    private void q() {
        CharSequence string;
        Builder bVar = new b(this);
        if (this.ar == 1) {
            string = getResources().getString(R.string.fpv_playback_del_moment);
        } else {
            string = getResources().getString(R.string.fpv_playback_del_image);
        }
        bVar.setMessage(string);
        bVar.setPositiveButton(R.string.btn_dlg_yes, new 10(this));
        bVar.setNegativeButton(R.string.btn_dlg_no, new 11(this));
        bVar.show();
    }

    private void r() {
        if (this.ar == 1) {
            c.a().e(dji.pilot2.library.a.d);
        } else {
            c.a().e(dji.pilot2.library.a.v);
        }
        f();
        ((DJILibraryFragment) getFragmentManager().findFragmentById(R.id.c9c)).a.exitSelectMode();
    }

    private void a(int i) {
        Builder bVar = new b(this);
        bVar.setMessage(getResources().getString(R.string.v2_library_15) + i + getResources().getString(R.string.v2_library_16));
        bVar.setNeutralButton(R.string.v2_library_02, new 12(this));
        bVar.show();
    }

    private void b(int i) {
        Builder bVar = new b(this);
        bVar.setMessage(getResources().getString(R.string.v2_multimoment_selectduration_limit));
        bVar.setNeutralButton(R.string.v2_library_02, new 13(this));
        bVar.show();
    }

    public void onEventBackgroundThread(o oVar) {
        if (oVar == o.ConnectOK) {
            if (!this.S.hasMessages(0)) {
                this.S.sendEmptyMessageDelayed(0, 2000);
            }
        } else if (oVar == o.ConnectLose && !this.S.hasMessages(1)) {
            this.S.sendEmptyMessageDelayed(1, 500);
        }
    }

    public void onEventBackgroundThread(dji.pilot2.library.a aVar) {
        switch (5.a[aVar.ordinal()]) {
            case 1:
                this.S.sendEmptyMessage(2);
                return;
            case 2:
                this.S.sendEmptyMessageDelayed(3, 1500);
                return;
            case 3:
                this.S.sendEmptyMessage(4);
                return;
            case 4:
                this.S.sendEmptyMessage(5);
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        SDCardState sDCardState = DataCameraGetPushStateInfo.getInstance().getSDCardState();
        if (sDCardState == SDCardState.Normal || sDCardState == SDCardState.Full) {
            if (!this.aQ) {
                this.aQ = true;
                if (!this.S.hasMessages(0)) {
                    this.S.sendEmptyMessageDelayed(0, 500);
                }
            }
        } else if (sDCardState == SDCardState.None && this.aQ) {
            this.aQ = false;
            if (!this.S.hasMessages(1)) {
                this.S.sendEmptyMessageDelayed(1, 500);
            }
        }
    }

    public void onEventMainThread(ClickMediaTipEvent clickMediaTipEvent) {
        if (clickMediaTipEvent == ClickMediaTipEvent.ClickMediaTipButtonEvent) {
            ((View) this.X.get(1)).performClick();
        }
    }

    public void onEventMainThread(dji.pilot2.share.b.b.b bVar) {
        if (bVar.a == dji.pilot2.share.e.a.b.e) {
            a(bVar.b);
        }
    }

    public void onEventMainThread(UploadEvent uploadEvent) {
        if (uploadEvent.result != UploadEvent.a.a) {
            return;
        }
        if (this.aH) {
            ((View) this.X.get(2)).performClick();
        } else {
            this.aI = 2;
        }
    }

    public void onEventMainThread(ExploreEvent exploreEvent) {
        if (exploreEvent.equals(ExploreEvent.GOTO_LIBRARY)) {
            if (this.aH) {
                ((View) this.X.get(1)).performClick();
            } else {
                this.aI = 1;
            }
        } else if (exploreEvent.equals(ExploreEvent.GOTO_EXPLORE)) {
            if (this.aH) {
                ((View) this.X.get(2)).performClick();
            } else {
                this.aI = 2;
            }
        } else if (!exploreEvent.equals(ExploreEvent.GOTO_DEVICE)) {
        } else {
            if (this.aH) {
                ((View) this.X.get(0)).performClick();
            } else {
                this.aI = 0;
            }
        }
    }

    public void onEventMainThread(FlyUploadConfigEvent flyUploadConfigEvent) {
        if (!this.aH || DJINotificationDialog.b) {
            this.aK = true;
            this.aL = flyUploadConfigEvent.content;
            return;
        }
        dji.pilot2.flymonitor.a.a aVar = new dji.pilot2.flymonitor.a.a(this);
        aVar.a(flyUploadConfigEvent.content);
        aVar.show();
    }

    public void onEventBackgroundThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (System.currentTimeMillis() - this.aT > 5000) {
            this.aT = System.currentTimeMillis();
            DataCenterGetBoardNumber.getInstance().start(new 3(this));
            if (this.aS && dataOsdGetPushCommon.isMotorUp()) {
                Intent intent = new Intent(this.aE, BatteryCheckService.class);
                intent.putExtra("service_type", 3);
                DJILogHelper.getInstance().LOGI("BatteryCheck", "Auto landing");
                startService(intent);
            }
        }
    }

    public void onEventMainThread(InvalidBatteryEvent invalidBatteryEvent) {
        if (invalidBatteryEvent.equals(InvalidBatteryEvent.INVALID)) {
            this.aS = true;
        }
    }

    public void a(String str, boolean z) {
        TextView textView = (TextView) this.aD.get(str);
        Log.i("zhang", "tab:" + str);
        if (textView != null) {
            if (z) {
                if (str.equals(this.T[0])) {
                    ((DJIPhantomFragment) getFragmentManager().findFragmentById(R.id.c9b)).a();
                }
                if (!str.equals(this.T[2]) || l.c) {
                    textView.setVisibility(0);
                    return;
                }
                Log.i("zhang", "setText:" + l.getInstance().a());
                int a = l.getInstance().a();
                if (a > 99) {
                    textView.setText("99+");
                } else {
                    textView.setText("" + a);
                }
                textView.setBackground(getResources().getDrawable(R.drawable.v2_tab_redpoint_view));
                textView.setVisibility(0);
                l.c = true;
                return;
            }
            textView.setVisibility(8);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.aM.a(i, i2, intent);
        if (i == 4097) {
            if (intent != null) {
                if (intent.getBooleanExtra(N, false)) {
                    Toast makeText = Toast.makeText(this.aE, R.string.v2_library_12, 0);
                    makeText.setGravity(17, 0, 0);
                    makeText.show();
                }
                boolean booleanExtra = intent.getBooleanExtra(P, false);
                ArrayList stringArrayListExtra = intent.getStringArrayListExtra(O);
                if (stringArrayListExtra != null && stringArrayListExtra.size() != 0) {
                    ((DJILibraryFragment) getFragmentManager().findFragmentById(R.id.c9c)).a.r.handleNewMemontCreate(stringArrayListExtra, booleanExtra);
                }
            }
        } else if (i == 4098 && this.X != null) {
            this.aP.onClick((View) this.X.get(1));
        }
    }

    private void s() {
        File file = new File(getFilesDir(), DJIMultiMomentEditActivity.X);
        if (file.exists()) {
            Log.i("rxq", "last exit is abnormal for the instance for recover is found.");
            try {
                InputStream openFileInput = openFileInput(DJIMultiMomentEditActivity.X);
                ObjectInputStream objectInputStream = new ObjectInputStream(openFileInput);
                EditRecoverInfo editRecoverInfo = (EditRecoverInfo) objectInputStream.readObject();
                objectInputStream.close();
                openFileInput.close();
                file.delete();
                if (!editRecoverInfo.isRecovering() || editRecoverInfo.getHowLong() >= 30000) {
                    editRecoverInfo.setHowLong(0);
                    Intent intent = new Intent(this, DJIMultiMomentEditActivity.class);
                    intent.putExtra(DJIMultiMomentEditActivity.X, editRecoverInfo);
                    startActivityForResult(intent, 4098);
                    return;
                }
                return;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            } catch (StreamCorruptedException e2) {
                e2.printStackTrace();
                return;
            } catch (IOException e3) {
                e3.printStackTrace();
                return;
            } catch (ClassNotFoundException e4) {
                e4.printStackTrace();
                return;
            }
        }
        Log.i("rxq", "last exit is normal.");
    }

    private void t() {
        com.facebook.o.a(getApplicationContext());
        this.aM = f.a.a();
        this.aN = new com.facebook.share.widget.f((Activity) this);
        this.aN.a(this.aM, new 4(this));
    }

    private void a(dji.pilot2.mine.e.a.a aVar) {
        if (com.facebook.share.widget.f.a(ShareLinkContent.class)) {
            this.aN.b(((ShareLinkContent.a) new ShareLinkContent.a().b(aVar.c()).a(aVar.d()).a(Uri.parse(aVar.b()))).b());
            DJILogHelper.getInstance().LOGI("bob", "shareFacebook");
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() == 2 && motionEvent.getAction() == 261) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - this.aV < 800) {
                try {
                    Class.forName("com.dji.tools.base.InnerToolsDialog").getMethod("showInnerTools", new Class[]{Context.class}).invoke(null, new Object[]{this});
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.aV = currentTimeMillis;
        }
        return super.dispatchTouchEvent(motionEvent);
    }
}
