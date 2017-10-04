package dji.pilot2.main.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.Html;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.config.OdnpConfigStatic;
import com.sina.weibo.sdk.component.ShareRequestParam;
import dji.dbox.upgrade.p4.statemachine.DJIUpgradeP4Service;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataCameraGetMode.MODE;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetMode;
import dji.midware.data.model.P3.DataFlycActiveStatus;
import dji.midware.data.model.P3.DataGimbalActiveStatus;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.midware.data.model.P3.DataUpgradeSelfRequest;
import dji.midware.data.model.P3.DataWifiGetPushFirstAppMac;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.fpv.d.c.e;
import dji.pilot.fpv.d.d;
import dji.pilot.main.activity.DJIHubActivity;
import dji.pilot.publics.model.DJIProductListModel;
import dji.pilot.publics.model.DJIProductListModel.DJIProductModel;
import dji.pilot.publics.objects.DJIApplication;
import dji.pilot.publics.objects.g;
import dji.pilot.usercenter.b.f;
import dji.pilot2.academy.activity.DJINewAcademyActivity;
import dji.pilot2.academy.model.AcademyProductTypeModel.ProductTypeStruct;
import dji.pilot2.academy.model.a;
import dji.pilot2.academy.widget.h;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.main.activity.DJIChangeAppActivity;
import dji.pilot2.main.activity.DJIHowToConnectActivity;
import dji.pilot2.main.activity.DJIQuickStartActivity;
import dji.pilot2.main.model.ClickMediaTipEvent;
import dji.pilot2.scan.android.CaptureActivity;
import dji.pilot2.upgrade.ble.BleDevicesActivity;
import dji.pilot2.usercenter.activity.DJIFlightRecordActivity;
import dji.pilot2.utils.k;
import dji.pilot2.utils.l;
import dji.pilot2.utils.r;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;
import dji.pilot2.widget.DJIMarqueeTextView;
import dji.pilot2.widget.DJIPhantomScrollView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import dji.publics.widget.djiviewpager.IndicatorBar;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@SuppressLint({"ClickableViewAccessibility"})
public class DJIPhantomFragment extends Fragment implements e {
    private static final int A = 1025;
    private static final int B = 1026;
    private static final int C = 1027;
    private static final int D = 512;
    private static final int E = 0;
    private static final int F = 1;
    private static boolean an = true;
    private static final int ao = 1;
    private static final int ap = 2;
    private static final int aq = 3;
    private static final int ar = 10;
    private static final int as = 11;
    private static final int at = 12;
    private static final int au = 20;
    private static final int av = 30;
    private static final int aw = 40;
    public static final String l = "phantomname";
    public static final String m = "INSPIRE";
    public static int n = 0;
    public static int o = 1;
    public static int p = 2;
    public static int q = 3;
    public static int r = 4;
    public static boolean v = false;
    private static final String w = "DJIPhantomFragment";
    private static final String x = "scan_ssid_string";
    private static final boolean y = false;
    private static final int z = 1024;
    private Button G;
    private Button H;
    private Button I;
    private TextView J;
    private DJITextView K;
    private DJITextView L;
    private DJIImageView M = null;
    private LinearLayout N;
    private LinearLayout O;
    private IndicatorBar P;
    private h Q = null;
    private ImageView R;
    private Button S;
    private DJIMarqueeTextView T;
    private RelativeLayout U;
    private LinearLayout V;
    private LinearLayout W;
    private LinearLayout X;
    private LinearLayout Y;
    private View Z;
    private int aA = -1;
    private OnClickListener aB = new OnClickListener(this) {
        final /* synthetic */ DJIPhantomFragment a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            int i = 0;
            if (view.getId() == R.id.cx8) {
                if (f.getInstance().c()) {
                    this.a.ai.startActivity(new Intent(this.a.ai, DJIFlightRecordActivity.class));
                    this.a.Z.setVisibility(8);
                    l.getInstance().b(l.a[0], false);
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(this.a.getActivity(), DJIAccountSignActivity.class);
                intent.putExtra(DJIAccountSignActivity.a, 1006);
                this.a.getActivity().startActivity(intent);
            } else if (view.getId() == R.id.cx_) {
                View inflate = LayoutInflater.from(this.a.ai).inflate(R.layout.mainframe_topbar_listview, null);
                final PopupWindow popupWindow = new PopupWindow(inflate);
                popupWindow.setWidth(b.a(this.a.ai, 135.0f));
                popupWindow.setHeight(-2);
                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                ListView listView = (ListView) inflate.findViewById(R.id.bdz);
                ListAdapter aVar = new a(this.a.ai, this.a.n());
                if (listView != null) {
                    listView.setAdapter(aVar);
                    listView.setOnItemClickListener(new OnItemClickListener(this) {
                        final /* synthetic */ AnonymousClass16 b;

                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                            switch (i) {
                                case 0:
                                    final r rVar = new r(this.b.a.ai);
                                    if (rVar.b()) {
                                        this.b.a.startActivityForResult(new Intent(DJIApplication.a(), CaptureActivity.class), 512);
                                    } else {
                                        final dji.pilot.publics.widget.f fVar = new dji.pilot.publics.widget.f(this.b.a.ai, R.style.c7);
                                        fVar.b(this.b.a.ai.getString(R.string.v2_phantom_fragment_scan_wifi_open_wifi_request));
                                        fVar.f(-16777216);
                                        fVar.c(this.b.a.ai.getString(R.string.v2_phantom_fragment_scan_wifi_cancel));
                                        fVar.c((int) R.string.v2_phantom_fragment_scan_wifi_ok);
                                        fVar.d(-1);
                                        fVar.a(new DialogInterface.OnClickListener(this) {
                                            final /* synthetic */ AnonymousClass1 b;

                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                fVar.dismiss();
                                            }
                                        });
                                        fVar.b(new DialogInterface.OnClickListener(this) {
                                            final /* synthetic */ AnonymousClass1 c;

                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                rVar.a();
                                                fVar.dismiss();
                                            }
                                        });
                                        fVar.show();
                                    }
                                    popupWindow.dismiss();
                                    return;
                                case 1:
                                    dji.pilot.fpv.d.e.b(e.ey_);
                                    Intent intent = new Intent(this.b.a.ai, DJINewAcademyActivity.class);
                                    if (ServiceManager.getInstance().isRemoteOK()) {
                                        Iterator it = this.b.a.k.products.iterator();
                                        int i2 = 0;
                                        while (it.hasNext()) {
                                            int a;
                                            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
                                            if (dJIProductModel.value == i.getInstance().c().value()) {
                                                a = this.b.a.a(dJIProductModel.series, dJIProductModel.pageLoc);
                                            } else {
                                                a = i2;
                                            }
                                            i2 = a;
                                        }
                                        intent.putExtra("key_product_index", i2);
                                    } else {
                                        intent.putExtra("key_product_index", this.b.a.a(this.b.a.s[this.b.a.j.getCurrentPager()], ((Integer) this.b.a.u.get(this.b.a.s[this.b.a.j.getCurrentPager()])).intValue()));
                                    }
                                    this.b.a.ai.startActivity(intent);
                                    popupWindow.dismiss();
                                    return;
                                default:
                                    return;
                            }
                        }
                    });
                    popupWindow.showAsDropDown(this.a.H);
                }
            } else if (view.getId() == R.id.cwo) {
                this.a.c();
            } else if (view.getId() == R.id.cwp) {
                int i2;
                if (ServiceManager.getInstance().isRemoteOK()) {
                    Iterator it = this.a.k.products.iterator();
                    i2 = 0;
                    while (it.hasNext()) {
                        int a;
                        DJIProductModel dJIProductModel = (DJIProductModel) it.next();
                        if (dJIProductModel.value == i.getInstance().c().value()) {
                            a = this.a.a(dJIProductModel.series, dJIProductModel.pageLoc);
                        } else {
                            a = i2;
                        }
                        i2 = a;
                    }
                } else {
                    i2 = this.a.a(this.a.s[this.a.j.getCurrentPager()], ((Integer) this.a.u.get(this.a.s[this.a.j.getCurrentPager()])).intValue());
                }
                HashMap hashMap = new HashMap();
                if (i2 == -1) {
                    while (i < a.getInstance(this.a.getActivity()).a().size()) {
                        if (i.getInstance().c().value() == ProductType.A2.value() || i.getInstance().c().value() == ProductType.Grape2.value()) {
                            if (ProductType.A2.value() == ((ProductTypeStruct) a.getInstance(this.a.getActivity()).a().get(i)).value || ProductType.Grape2.value() == ((ProductTypeStruct) a.getInstance(this.a.getActivity()).a().get(i)).value) {
                                i2 = i;
                                break;
                            }
                        } else if (i.getInstance().c().value() == ((ProductTypeStruct) a.getInstance(this.a.getActivity()).a().get(i)).value) {
                            i2 = i;
                        }
                        i++;
                    }
                }
                String productType = ((ProductTypeStruct) a.getInstance(this.a.getActivity()).a().get(i2)).mProductCode.toString();
                DJILogHelper.getInstance().LOGD("", "curProductString" + productType, true, true);
                hashMap.put(d.dI, productType);
                dji.pilot.fpv.d.e.a(e.eG_, hashMap);
                Intent intent2 = new Intent(this.a.getActivity(), DJISupportShareWebviewActivity.class);
                productType = this.a.w();
                if (ServiceManager.getInstance().isRemoteOK()) {
                    productType = this.a.v();
                }
                intent2.putExtra(DJIWebviewFragment.r, true);
                intent2.putExtra(DJIWebviewFragment.o, productType);
                this.a.startActivity(intent2);
            }
        }
    };
    private int aC = 0;
    private Handler aD = new Handler(new Callback(this) {
        final /* synthetic */ DJIPhantomFragment a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (!ServiceManager.getInstance().isRemoteOK()) {
                        if (!ServiceManager.getInstance().isConnected()) {
                            this.a.ac = false;
                            this.a.ad = false;
                            this.a.al = 1;
                            this.a.j.gotoPage(this.a.a(g.b(this.a.ai, DJIPhantomFragment.l, DJIPhantomFragment.m)));
                            this.a.U.setVisibility(0);
                            this.a.V.setVisibility(4);
                            this.a.W.setVisibility(0);
                            this.a.X.setVisibility(4);
                            this.a.Y.setVisibility(0);
                            break;
                        }
                        this.a.ac = false;
                        this.a.ad = true;
                        this.a.al = 2;
                        this.a.j.gotoPage(this.a.a(g.b(this.a.ai, DJIPhantomFragment.l, DJIPhantomFragment.m)));
                        this.a.Y.setVisibility(0);
                        this.a.U.setVisibility(0);
                        this.a.V.setVisibility(4);
                        this.a.W.setVisibility(0);
                        this.a.X.setVisibility(4);
                        break;
                    }
                    this.a.ad = true;
                    this.a.ac = true;
                    this.a.al = 3;
                    DJILogHelper.getInstance().LOGD("", "***************VIEW_BEGIN********************", true, true);
                    this.a.U.setVisibility(4);
                    this.a.V.setVisibility(0);
                    this.a.W.setVisibility(4);
                    this.a.Y.setVisibility(0);
                    this.a.c(this.a.t());
                    DJILogHelper.getInstance().LOGD("", "***************ShowViewPhant********************", true, true);
                    this.a.e(this.a.j.getCurrentPager());
                    this.a.X.setVisibility(0);
                    this.a.j.gotoPage(this.a.a(g.b(this.a.ai, DJIPhantomFragment.l, DJIPhantomFragment.m)));
                    break;
                case 2:
                    this.a.j.gotoPage(this.a.j.getCurrentPager());
                    break;
                case 3:
                    this.a.j.gotoPage(this.a.a(g.b(this.a.ai, DJIPhantomFragment.l, DJIPhantomFragment.m)));
                    break;
                case 10:
                    MODE mode = DataCameraGetPushStateInfo.getInstance().getMode();
                    DJILogHelper.getInstance().LOGD("", "***************首页 mode=" + mode + "********************");
                    if (mode != MODE.PLAYBACK && mode != MODE.NEW_PLAYBACK) {
                        if (this.a.aC != 0) {
                            this.a.aC = 0;
                            break;
                        }
                        this.a.aD.sendEmptyMessageDelayed(10, 300);
                        this.a.aC = this.a.aC + 1;
                        break;
                    }
                    DataCameraSetMode.getInstance().a(dji.pilot.c.d.a).start(new dji.midware.e.d(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            this.a.a.aC = 0;
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.a.a.aC = 0;
                            this.a.a.aD.sendEmptyMessage(10);
                        }
                    });
                    break;
                    break;
                case 11:
                    dji.pilot2.library.d.getInstance().b(false);
                    if (ServiceManager.getInstance().isConnected()) {
                        c.a().e(dji.pilot2.library.a.PhotoTake);
                        break;
                    }
                    break;
                case 12:
                    ProductType c = i.getInstance().c();
                    if (!ServiceManager.getInstance().isRemoteOK() || (c != ProductType.Pomato && c != ProductType.Orange2)) {
                        this.a.g();
                        break;
                    }
                    this.a.startActivity(new Intent(this.a.getActivity(), DJIChangeAppActivity.class));
                    break;
                case 20:
                    this.a.d(this.a.j.getCurrentPager());
                    break;
                case 30:
                    this.a.h();
                    break;
            }
            return false;
        }
    });
    private Spinner aa;
    private int ab = 0;
    private volatile boolean ac = false;
    private volatile boolean ad = false;
    private volatile CameraType ae = CameraType.OTHER;
    private boolean af;
    private int ag;
    private int ah;
    private Context ai;
    private View aj = null;
    private dji.pilot2.widget.a ak;
    private int al = 1;
    private boolean am;
    private BroadcastReceiver ax;
    private ArrayList<ProductTypeStruct> ay = new ArrayList();
    private long az = -1;
    public DJIPhantomScrollView j;
    public DJIProductListModel k;
    public String[] s;
    public HashMap<String, ArrayList<ProductTypeStruct>> t = new HashMap();
    public HashMap<String, Integer> u = new HashMap();

    static /* synthetic */ class AnonymousClass9 {
        static final /* synthetic */ int[] a = new int[DJIUpgradeP4Service.a.values().length];

        static {
            c = new int[dji.pilot2.upgrade.b.d.values().length];
            try {
                c[dji.pilot2.upgrade.b.d.UpgradeFinish.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                c[dji.pilot2.upgrade.b.d.Upgrading.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                c[dji.pilot2.upgrade.b.d.NeedUpgrade.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            b = new int[DJIHubActivity.a.values().length];
            try {
                b[DJIHubActivity.a.START.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                b[DJIHubActivity.a.FINISH.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                a[DJIUpgradeP4Service.a.c.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                a[DJIUpgradeP4Service.a.b.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
        }
    }

    private void d() {
        this.k = dji.pilot.publics.c.d.getInstance().a();
        Iterator it = this.k.products.iterator();
        while (it.hasNext()) {
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (dJIProductModel.showPage > this.ab) {
                this.ab = dJIProductModel.showPage;
            }
        }
        this.ab++;
        this.s = new String[this.ab];
        it = this.k.products.iterator();
        while (it.hasNext()) {
            dJIProductModel = (DJIProductModel) it.next();
            if (dJIProductModel.showPage != -1) {
                this.s[dJIProductModel.showPage] = dJIProductModel.series;
            }
        }
    }

    private int a(int i, int i2) {
        Iterator it = this.k.products.iterator();
        int i3 = 100;
        int i4 = 100;
        while (it.hasNext()) {
            int i5;
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (dJIProductModel.showPage == i) {
                if (i3 == 100) {
                    i3 = dJIProductModel.value;
                }
                if (dJIProductModel.pageLoc == i2) {
                    i4 = dJIProductModel.value;
                    i5 = i3;
                    i3 = i4;
                    i4 = i3;
                    i3 = i5;
                }
            }
            i5 = i3;
            i3 = i4;
            i4 = i3;
            i3 = i5;
        }
        return i4 == 100 ? i3 : i4;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        d();
        this.ai = getActivity();
        if (this.aj == null) {
            this.aj = layoutInflater.inflate(R.layout.v2_main_phantom_fragment, viewGroup, false);
            a(this.aj);
        }
        if (f.getInstance().c()) {
            dji.pilot.usercenter.b.d.getInstance().a(this.ai);
            dji.pilot.usercenter.b.d.getInstance().a(true, new dji.pilot.usercenter.b.d.b());
        }
        this.ax = new BroadcastReceiver(this) {
            final /* synthetic */ DJIPhantomFragment a;

            {
                this.a = r1;
            }

            public void onReceive(Context context, Intent intent) {
                Log.d("longanbroadcast", "receive");
                c.a().e(DJIHubActivity.a.FINISH);
            }
        };
        getActivity().registerReceiver(this.ax, new IntentFilter("dji.device.activity.DJIPreviewActivityLongan.FINISH"));
        ViewGroup viewGroup2 = (ViewGroup) this.aj.getParent();
        if (viewGroup2 != null) {
            viewGroup2.removeView(this.aj);
        }
        onEventBackgroundThread(i.getInstance().c());
        return this.aj;
    }

    public void onAttach(Activity activity) {
        e();
        super.onAttach(activity);
    }

    public void onDestroy() {
        f();
        super.onDestroy();
        getActivity().unregisterReceiver(this.ax);
        Log.d("longanbroadcast", "unregisterReceiver");
    }

    private void e() {
        c.a().a(this);
    }

    private void f() {
        c.a().d(this);
    }

    private void a(View view) {
        b(view);
        i();
    }

    public void onStart() {
        super.onStart();
    }

    public void onPause() {
        super.onPause();
        this.am = true;
    }

    public void onResume() {
        super.onResume();
        this.am = false;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            this.aD.sendEmptyMessageDelayed(2, 200);
        }
    }

    public void onEventBackgroundThread(DJIUpgradeP4Service.a aVar) {
        switch (AnonymousClass9.a[aVar.ordinal()]) {
            case 1:
            case 2:
                a(false, 50);
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(DataUpgradeSelfRequest dataUpgradeSelfRequest) {
        a(true, 0);
    }

    public void onEventBackgroundThread(DJIHubActivity.a aVar) {
        switch (aVar) {
            case START:
                r();
                return;
            case FINISH:
                this.aD.sendEmptyMessageDelayed(11, 2000);
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(ProductType productType) {
        DJILogHelper.getInstance().LOGD("", "******************Snake飞机监听控制�" + productType + "****************", true, true);
        if (dji.pilot.publics.e.a.c(productType)) {
            dji.pilot.c.d.i = 1;
        } else {
            dji.pilot.c.d.i = 0;
        }
        if (!this.aD.hasMessages(12)) {
            this.aD.sendEmptyMessageDelayed(12, 500);
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        Object obj = (!b.j(cameraType) || this.M.getVisibility() == 0) ? null : 1;
        if (this.ae != cameraType || obj != null) {
            this.ae = cameraType;
            if (!this.aD.hasMessages(12)) {
                this.aD.sendEmptyMessageDelayed(12, 500);
            }
        }
    }

    public void onEventBackgroundThread(p pVar) {
        DJILogHelper.getInstance().LOGD("", "******************Snake飞机监听控制�" + pVar.toString() + "****************", true, true);
        int i = 500;
        if (pVar == p.b) {
            this.ad = true;
        } else if (pVar == p.a) {
            this.ad = false;
            an = true;
            this.ae = CameraType.OTHER;
            i = 100;
        }
        if (!this.aD.hasMessages(12)) {
            this.aD.sendEmptyMessageDelayed(12, (long) i);
        }
    }

    public void onEventBackgroundThread(o oVar) {
        DJILogHelper.getInstance().LOGD("", "******************Snake飞机监听飞机" + oVar.toString() + "****************", true, true);
        int i = 500;
        if (oVar == o.b) {
            this.ac = true;
            DJILogHelper.getInstance().LOGD("", "首页已连接", false, true);
        } else if (oVar == o.a) {
            this.ac = false;
            i = 100;
            this.ae = CameraType.OTHER;
            DJILogHelper.getInstance().LOGD("", "首页断开连接", false, true);
        }
        if (!this.aD.hasMessages(12)) {
            this.aD.sendEmptyMessageDelayed(12, (long) i);
        }
    }

    public void onEventMainThread(DataWifiGetPushFirstAppMac dataWifiGetPushFirstAppMac) {
        String m = new r(this.ai).m();
        if (dataWifiGetPushFirstAppMac.getMac() != null && !m.equals("NULL") && b.h(null) && !dataWifiGetPushFirstAppMac.getMac().equals(m)) {
            Toast.makeText(getActivity(), String.format(this.ai.getString(R.string.v2_phantom_wifi_crash_tip), new Object[]{i.getInstance().c()._name()}), 0).show();
        }
    }

    private void g() {
        DJILogHelper.getInstance().LOGD("", "***************setNowView*******************", true, true);
        if (this.ad) {
            if (i.getInstance().c() != ProductType.Grape2) {
                this.az = -1;
            } else if (this.az == -1) {
                this.az = System.currentTimeMillis();
                this.aD.sendEmptyMessageDelayed(12, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                return;
            } else if (System.currentTimeMillis() - this.az < 7000) {
                this.aD.sendEmptyMessageDelayed(12, 4000);
                return;
            }
            if (this.ac || i.getInstance().e()) {
                j();
                this.al = 3;
                c(t());
                a(false, 500);
                b(false, 500);
                return;
            }
            a(true, 500);
            b(a(g.b(this.ai, l, m)));
            this.al = 2;
            return;
        }
        this.az = -1;
        if (this.ac || i.getInstance().e()) {
            j();
            this.al = 3;
        } else {
            c(t());
            String b = g.b(this.ai, l, m);
            DJILogHelper.getInstance().LOGD("", "***************phantomName" + a(b) + "********************", true, true);
            a(a(b));
            this.al = 1;
        }
        a(true, 500);
    }

    private void h() {
        if (this.ad && this.ac && this.S.isEnabled() && isResumed() && !isHidden() && DJIUpgradeP4Service.d() && !DataUpgradeSelfRequest.getInstance().isGettedPack()) {
            boolean z;
            ProductType c = i.getInstance().c();
            Iterator it = this.k.products.iterator();
            while (it.hasNext()) {
                DJIProductModel dJIProductModel = (DJIProductModel) it.next();
                if (dJIProductModel.value == c.value()) {
                    if (dJIProductModel.showPage == -1) {
                        z = false;
                        if (z) {
                            this.S.setEnabled(false);
                            this.aD.postDelayed(new Runnable(this) {
                                final /* synthetic */ DJIPhantomFragment a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.S.setEnabled(true);
                                }
                            }, 2000);
                            if (c != ProductType.Grape2 || c == ProductType.A2 || b.h(c) || g.b(getActivity(), "is_FirstTime", false)) {
                                q();
                            } else {
                                s();
                            }
                        }
                    }
                    z = true;
                    if (z) {
                        this.S.setEnabled(false);
                        this.aD.postDelayed(/* anonymous class already generated */, 2000);
                        if (c != ProductType.Grape2) {
                        }
                        q();
                    }
                }
            }
            z = true;
            if (z) {
                this.S.setEnabled(false);
                this.aD.postDelayed(/* anonymous class already generated */, 2000);
                if (c != ProductType.Grape2) {
                }
                q();
            }
        }
        if (an && a(i.getInstance().c())) {
            q();
        }
    }

    private boolean a(ProductType productType) {
        dji.pilot2.upgrade.b.d c = dji.pilot2.upgrade.b.getInstance().c();
        if (this.am || dji.pilot.publics.control.a.getInstance().o() || c == dji.pilot2.upgrade.b.d.NeedUpgrade || c == dji.pilot2.upgrade.b.d.Upgrading || productType != ProductType.LonganMobile || DataGimbalGetPushAbnormalStatus.getInstance().isPhoneOutGimbal()) {
            return false;
        }
        return true;
    }

    private void a(boolean z, long j) {
    }

    private void b(boolean z, long j) {
        if (!dji.logic.f.d.h(null)) {
            return;
        }
        if (z) {
            this.aD.removeMessages(30);
        } else {
            this.aD.sendEmptyMessageDelayed(30, j);
        }
    }

    private void i() {
        final Handler handler = new Handler();
        handler.post(new Runnable(this) {
            final /* synthetic */ DJIPhantomFragment b;

            public void run() {
                DJILogHelper.getInstance().LOGD("", "***************VIEW_BEGIN********************", true, true);
                if (ServiceManager.getInstance().isRemoteOK()) {
                    this.b.ad = true;
                    this.b.ac = true;
                    this.b.al = 3;
                    this.b.U.setVisibility(4);
                    this.b.V.setVisibility(0);
                    this.b.W.setVisibility(4);
                    this.b.Y.setVisibility(0);
                    this.b.c(this.b.t());
                    this.b.e(this.b.j.getCurrentPager());
                    this.b.X.setVisibility(0);
                    this.b.j.gotoPage(this.b.a(g.b(this.b.ai, DJIPhantomFragment.l, DJIPhantomFragment.m)));
                    this.b.a(false, 500);
                } else if (ServiceManager.getInstance().isConnected()) {
                    this.b.ac = false;
                    this.b.ad = true;
                    this.b.al = 2;
                    this.b.j.gotoPage(this.b.a(g.b(this.b.ai, DJIPhantomFragment.l, DJIPhantomFragment.m)));
                    this.b.Y.setVisibility(0);
                    this.b.U.setVisibility(0);
                    this.b.V.setVisibility(4);
                    this.b.W.setVisibility(0);
                    this.b.X.setVisibility(4);
                } else {
                    this.b.ac = false;
                    this.b.ad = false;
                    this.b.al = 1;
                    this.b.j.gotoPage(this.b.a(g.b(this.b.ai, DJIPhantomFragment.l, DJIPhantomFragment.m)));
                    this.b.U.setVisibility(0);
                    this.b.V.setVisibility(4);
                    this.b.W.setVisibility(0);
                    this.b.X.setVisibility(4);
                    this.b.Y.setVisibility(0);
                }
                handler.removeCallbacks(this);
            }
        });
    }

    private void a(int i) {
        this.U.setVisibility(0);
        this.V.setVisibility(4);
        this.W.setVisibility(0);
        this.X.setVisibility(4);
        this.Y.setVisibility(0);
        DJILogHelper.getInstance().LOGD("", "***************ShowViewFirstsCurrentView" + this.al + "********************", true, true);
        if (this.al == 2) {
            e(this.j.getCurrentPager());
        } else {
            this.j.gotoPage(i);
        }
    }

    private void b(int i) {
        this.Y.setVisibility(0);
        this.U.setVisibility(0);
        this.V.setVisibility(4);
        this.W.setVisibility(0);
        this.X.setVisibility(4);
        DJILogHelper.getInstance().LOGD("", "***************ShowViewControlsCurrentView" + this.al + "********************", true, true);
        if (this.al == 1) {
            e(this.j.getCurrentPager());
        } else {
            this.j.gotoPage(i);
        }
    }

    private void j() {
        this.U.setVisibility(4);
        this.V.setVisibility(0);
        this.W.setVisibility(4);
        this.Y.setVisibility(0);
        DJILogHelper.getInstance().LOGD("", "***************ShowViewPhant********************", true, true);
        e(this.j.getCurrentPager());
        this.X.setVisibility(0);
    }

    private void c(int i) {
        if (this.ae == CameraType.OTHER && DataCameraGetPushStateInfo.getInstance().isGetted()) {
            this.ae = DataCameraGetPushStateInfo.getInstance().getCameraType();
        }
        if (b.j(this.ae)) {
            this.M.show();
        } else {
            this.M.go();
        }
        if (this.aA != i) {
            this.aA = i;
            int c = dji.pilot.publics.c.d.getInstance().c(i);
            if (c != 0) {
                this.K.setText(Html.fromHtml(getResources().getString(c)));
                this.R.setImageDrawable(dji.pilot.publics.c.d.getInstance().b(i));
                c = dji.pilot.publics.c.d.getInstance().d(i);
                if (c != 0) {
                    this.L.setText(Html.fromHtml(getResources().getString(c)));
                } else {
                    this.L.setText("");
                }
            }
        }
    }

    private void b(View view) {
        this.G = (Button) view.findViewById(R.id.cx8);
        this.H = (Button) view.findViewById(R.id.cx_);
        this.I = (Button) view.findViewById(R.id.cwp);
        this.I.setOnClickListener(this.aB);
        this.G.setOnClickListener(this.aB);
        this.H.setOnClickListener(this.aB);
        this.K = (DJITextView) view.findViewById(R.id.cx0);
        this.L = (DJITextView) view.findViewById(R.id.cx2);
        this.M = (DJIImageView) view.findViewById(R.id.cx1);
        this.J = (TextView) view.findViewById(R.id.cx3);
        this.J.setText(Html.fromHtml(getResources().getString(R.string.v2_phantom_top_1)));
        this.j = (DJIPhantomScrollView) view.findViewById(R.id.cws);
        this.N = (LinearLayout) view.findViewById(R.id.cwt);
        k();
        this.aa = (Spinner) view.findViewById(R.id.cx4);
        this.P = (IndicatorBar) view.findViewById(R.id.cwu);
        this.P.setCount(5);
        this.P.setIndicatorResource(R.drawable.v2_circle_press, R.drawable.v2_circle);
        this.P.setItemDisatance(22);
        this.P.setItemSize(20);
        this.R = (ImageView) view.findViewById(R.id.cwq);
        this.S = (Button) view.findViewById(R.id.cwo);
        this.T = (DJIMarqueeTextView) view.findViewById(R.id.cwn);
        this.S.setOnClickListener(this.aB);
        this.U = (RelativeLayout) view.findViewById(R.id.cs1);
        this.V = (LinearLayout) view.findViewById(R.id.cs2);
        this.W = (LinearLayout) view.findViewById(R.id.cs3);
        this.X = (LinearLayout) view.findViewById(R.id.cs4);
        this.Y = (LinearLayout) view.findViewById(R.id.cs5);
        this.Q = new h(this.ai, (ArrayList) this.t.get(this.s[0]));
        this.aa.setAdapter(this.Q);
        this.aa.setOnItemSelectedListener(new OnItemSelectedListener(this) {
            final /* synthetic */ DJIPhantomFragment a;

            {
                this.a = r1;
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                Log.i("phanzcx", "onItemSelected:" + i);
                ((ImageView) this.a.N.getChildAt(this.a.j.getCurrentPager()).getTag()).setImageDrawable(dji.pilot.publics.c.d.getInstance().a(this.a.a(this.a.j.getCurrentPager(), i)));
                this.a.u.put(this.a.s[this.a.j.getCurrentPager()], Integer.valueOf(i));
                this.a.Q.a(i);
                this.a.e(this.a.j.getCurrentPager());
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.Z = view.findViewById(R.id.csj);
        l();
        this.G.setOnLongClickListener(new OnLongClickListener(this) {
            final /* synthetic */ DJIPhantomFragment a;

            {
                this.a = r1;
            }

            public boolean onLongClick(View view) {
                if (!com.dji.frame.c.b.c(this.a.ai)) {
                    return false;
                }
                dji.pilot.c.a.w = true;
                Toast.makeText(this.a.ai, "FlightRecord debug open", 0).show();
                return true;
            }
        });
    }

    private void k() {
        if (this.t.size() == 0) {
            for (int i = 0; i < this.s.length; i++) {
                this.t.put(this.s[i], new ArrayList());
                this.u.put(this.s[i], Integer.valueOf(0));
            }
        }
        this.ay = a.getInstance(this.ai).a();
        for (int i2 = 0; i2 < this.ay.size(); i2++) {
            for (int i3 = 0; i3 < this.t.size(); i3++) {
                if (((ProductTypeStruct) this.ay.get(i2)).mSeries.equals(this.s[i3])) {
                    Iterator it = this.k.products.iterator();
                    while (it.hasNext()) {
                        DJIProductModel dJIProductModel = (DJIProductModel) it.next();
                        if (dJIProductModel.value == ((ProductTypeStruct) this.ay.get(i2)).value && dJIProductModel.showPage != -1) {
                            ArrayList arrayList = (ArrayList) this.t.get(this.s[i3]);
                            if (arrayList == null) {
                                arrayList = new ArrayList();
                            }
                            arrayList.add(this.ay.get(i2));
                        }
                    }
                }
            }
        }
    }

    private void l() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.ag = displayMetrics.widthPixels;
        this.ah = displayMetrics.heightPixels;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            if (this.ah > this.ag) {
                this.ag = this.ah;
            }
            this.j.setIsPad(Boolean.valueOf(true), this.ab);
        } else {
            if (this.ah < this.ag) {
                this.ag = this.ah;
            }
            this.j.setIsPad(Boolean.valueOf(false), this.ab);
        }
        this.j.setList(this.ag);
        LayoutInflater from = LayoutInflater.from(getActivity());
        for (int i = 0; i < this.ab; i++) {
            View inflate = from.inflate(R.layout.v2_phantom_content_scrollview, this.N, false);
            this.O = (LinearLayout) inflate.findViewById(R.id.cwv);
            ImageView imageView = (ImageView) inflate.findViewById(R.id.cww);
            Button button = (Button) inflate.findViewById(R.id.cwz);
            this.O.setLayoutParams(new LayoutParams(this.ag, -1));
            button.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DJIPhantomFragment a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(d.dI, ((ProductTypeStruct) a.getInstance(this.a.getActivity()).a().get(this.a.j.getCurrentPager())).mProductCode.toString());
                    dji.pilot.fpv.d.e.a(e.eG_, hashMap);
                    Intent intent = new Intent(this.a.getActivity(), DJISupportShareWebviewActivity.class);
                    String k = this.a.w();
                    if (ServiceManager.getInstance().isRemoteOK()) {
                        k = this.a.v();
                    }
                    intent.putExtra(DJIWebviewFragment.o, k);
                    this.a.startActivity(intent);
                }
            });
            button.getParent().requestDisallowInterceptTouchEvent(true);
            imageView.setImageDrawable(dji.pilot.publics.c.d.getInstance().a(a(i, 0)));
            inflate.setTag(imageView);
            this.N.addView(inflate);
        }
        this.j.setOnScrollViewListener(new DJIPhantomScrollView.a(this) {
            final /* synthetic */ DJIPhantomFragment a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.j.setVisibility(0);
                if (this.a.aD.hasMessages(20)) {
                    this.a.aD.removeMessages(20);
                }
                this.a.aD.sendEmptyMessageDelayed(20, 500);
                this.a.e(this.a.j.getCurrentPager());
            }
        });
        this.j.setVisibility(4);
    }

    private void d(int i) {
        int intValue = ((Integer) this.u.get(this.s[i])).intValue();
        this.Q = new h(this.ai, (ArrayList) this.t.get(this.s[i]));
        this.aa.setAdapter(this.Q);
        this.aa.setSelection(intValue);
        this.Q.a(intValue);
        if (this.Q.getCount() == 1) {
            this.aa.setClickable(false);
            this.aa.setEnabled(false);
        } else {
            this.aa.setEnabled(true);
            this.aa.setClickable(true);
        }
        this.P.setSelectedIndex(i);
    }

    private void e(int i) {
        if (!dji.logic.f.d.a(m()) || ServiceManager.getInstance().isRemoteOK()) {
            this.T.setVisibility(0);
        } else {
            this.T.setVisibility(4);
        }
        if (this.ac) {
            this.T.setVisibility(0);
            this.S.setBackgroundResource(R.drawable.v2_main_plantom_buttom_camera_blue_selector);
            this.S.setTextColor(getResources().getColor(R.color.om));
            if (dji.logic.f.d.a(null)) {
                this.S.setText(R.string.phantom_dji_osmo_camera);
                this.T.setText(Html.fromHtml(getResources().getString(R.string.phantom_dji_connect_device_done)));
            } else {
                this.S.setText(R.string.phantom_dji_camera);
                this.T.setText(Html.fromHtml(getResources().getString(R.string.phantom_dji_connect_done)));
            }
        } else if (this.ad) {
            this.T.setText(Html.fromHtml(getResources().getString(R.string.phantom_dji_control_connect)));
            this.S.setText(R.string.phantom_dji_camera);
            this.S.setBackgroundResource(R.drawable.v2_main_plantom_buttom_camera_selector);
            this.S.setTextColor(getResources().getColor(R.color.om));
        } else if (f(i)) {
            this.T.setText(Html.fromHtml(getResources().getString(R.string.phantom_dji_learn_advice)));
            if (dji.logic.f.d.a(m())) {
                this.S.setText(R.string.phantom_dji_osmo_camera);
            } else {
                this.S.setText(R.string.phantom_dji_camera);
            }
            this.S.setBackgroundResource(R.drawable.v2_main_plantom_buttom_camera_selector);
            this.S.setTextColor(getResources().getColor(R.color.om));
        } else {
            this.T.setText(Html.fromHtml(getResources().getString(R.string.phantom_dji_learn_advice)));
            this.S.setText(R.string.phantom_dji_how_to_connect);
            try {
                this.S.setTextColor(ColorStateList.createFromXml(getResources(), getResources().getXml(R.xml.c)));
            } catch (Exception e) {
            }
            this.S.setBackgroundResource(R.drawable.v2_main_plantom_buttom_selector);
        }
        if (this.ad) {
            if (dji.logic.f.d.c(null)) {
                this.T.setVisibility(0);
                this.T.setText(Html.fromHtml(getResources().getString(R.string.phantom_dji_connect_device_done)));
                this.S.setBackgroundResource(R.drawable.v2_main_plantom_buttom_camera_blue_selector);
                this.S.setTextColor(getResources().getColor(R.color.om));
                this.S.setText(R.string.phantom_dji_osmo_camera);
            }
        } else if (dji.logic.f.d.c(m())) {
            this.T.setVisibility(4);
            this.S.setText(R.string.phantom_dji_hlz_connect_device);
            this.S.setBackgroundResource(R.drawable.v2_main_plantom_buttom_camera_blue_selector);
            this.S.setTextColor(getResources().getColor(R.color.om));
        }
    }

    private ProductType m() {
        int currentPager = this.j.getCurrentPager();
        int selectedItemPosition = this.aa.getSelectedItemPosition();
        ArrayList arrayList = (ArrayList) this.t.get(this.s[currentPager]);
        if (selectedItemPosition < arrayList.size()) {
            return ((ProductTypeStruct) arrayList.get(selectedItemPosition)).mProductCode;
        }
        return i.getInstance().c();
    }

    private List<Map<String, Object>> n() {
        List<Map<String, Object>> arrayList = new ArrayList();
        Map hashMap = new HashMap();
        hashMap.put(ShareRequestParam.REQ_UPLOAD_PIC_PARAM_IMG, Integer.valueOf(R.drawable.v2_phantom_selector_scan));
        hashMap.put("title", Integer.valueOf(R.string.mainframe_topbar_listview_item_scan));
        arrayList.add(hashMap);
        hashMap = new HashMap();
        hashMap.put(ShareRequestParam.REQ_UPLOAD_PIC_PARAM_IMG, Integer.valueOf(R.drawable.v2_phantom_selector_colleague));
        hashMap.put("title", Integer.valueOf(R.string.mainframe_topbar_listview_item_tutorial));
        arrayList.add(hashMap);
        return arrayList;
    }

    private void o() {
        if (DJIUpgradeP4Service.f()) {
            Builder bVar = new dji.pilot2.publics.object.b(getActivity());
            bVar.setMessage(R.string.connectmc_notallowfpv);
            bVar.setPositiveButton(17039379, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPhantomFragment a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            bVar.show();
            return;
        }
        this.S.setEnabled(false);
        new Handler().postDelayed(new Runnable(this) {
            final /* synthetic */ DJIPhantomFragment a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.S.setEnabled(true);
            }
        }, 2000);
        if (p() != ProductType.LonganMobile || this.ad) {
            if (!f(this.j.getCurrentPager()) && !ServiceManager.getInstance().isConnected() && (!b.h(null) || !ServiceManager.getInstance().isConnected())) {
                dji.pilot.fpv.d.e.b(e.ez_);
                Intent intent = new Intent(getActivity(), DJIHowToConnectActivity.class);
                intent.putExtra("title_text", getString(R.string.phantom_dji_how_to_connect));
                intent.putExtra(DJIHowToConnectActivity.r, a(this.s[this.j.getCurrentPager()], ((Integer) this.u.get(this.s[this.j.getCurrentPager()])).intValue()));
                startActivity(intent);
            } else if (this.ac && !g.b(getActivity(), "is_FirstTime", false)) {
                ProductType c = i.getInstance().c();
                if (c == ProductType.Grape2 || c == ProductType.A2 || b.h(c)) {
                    q();
                } else {
                    s();
                }
            } else if (!this.ac || g.b(getActivity(), "is_FirstTime", false)) {
                q();
            } else {
                s();
            }
        } else if (VERSION.SDK_INT >= 19) {
            getActivity().startActivity(new Intent(getActivity(), BleDevicesActivity.class));
        } else {
            new Builder(getActivity()).setMessage(R.string.lp_android_version_toolow_tip).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ DJIPhantomFragment a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
    }

    private ProductType p() {
        return ((ProductTypeStruct) ((ArrayList) this.t.get(this.s[this.j.getCurrentPager()])).get(this.aa.getSelectedItemPosition())).mProductCode;
    }

    private void q() {
        an = false;
        r();
        Intent intent = new Intent();
        intent.setClass(getActivity(), DJIHubActivity.class);
        intent.putExtra(dji.pilot.c.b.a, true);
        startActivityForResult(intent, 1024);
    }

    private void r() {
        this.aD.removeMessages(11);
        dji.pilot2.library.d.getInstance().b(true);
        if (dji.pilot.publics.e.c.a() || dji.pilot.publics.e.c.c()) {
            DJILogHelper.getInstance().LOGD("", "*********首页 进入FPV    MSG_SETMODE**********", true, true);
            this.aD.sendEmptyMessage(10);
        }
        dji.pilot.playback.litchi.h.getInstance().g().c();
        c.a().e(dji.pilot2.library.a.PhotoDIsConnect);
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1024) {
            if (i == 512) {
                switch (i2) {
                    case 1025:
                        c();
                        break;
                    case 1026:
                        Toast.makeText(this.ai, R.string.v2_phantom_fragment_ssid_not_found, 0).show();
                        dji.pilot2.scan.view.a aVar = new dji.pilot2.scan.view.a(this.ai);
                        aVar.requestWindowFeature(1);
                        aVar.show();
                        break;
                    default:
                        break;
                }
            }
        }
        this.af = dji.pilot2.widget.a.a(getActivity(), 9);
        if (this.af) {
            dji.pilot2.widget.a.b(getActivity(), 9);
            x();
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        this.aD.postDelayed(new Runnable(this) {
            final /* synthetic */ DJIPhantomFragment a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.j.gotoPage(this.a.j.getCurrentPager());
            }
        }, 500);
    }

    private void s() {
        this.aD.removeMessages(11);
        dji.pilot2.library.d.getInstance().b(true);
        if (dji.pilot.publics.e.c.a() || dji.pilot.publics.e.c.c()) {
            this.aD.sendEmptyMessage(10);
        }
        Intent intent = new Intent();
        intent.setClass(getActivity(), DJIQuickStartActivity.class);
        startActivityForResult(intent, 1024);
    }

    public void a() {
        this.Z.setVisibility(0);
    }

    public void b() {
        this.Z.setVisibility(8);
    }

    private int a(String str) {
        Iterator it = this.k.products.iterator();
        while (it.hasNext()) {
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (str == dJIProductModel.series && dJIProductModel.showPage != -1) {
                return dJIProductModel.showPage;
            }
        }
        return 0;
    }

    public int a(String str, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < this.t.size(); i3++) {
            if (!str.equals(this.s[i3])) {
                i2 += ((ArrayList) this.t.get(this.s[i3])).size();
            } else if (i < ((ArrayList) this.t.get(this.s[i3])).size()) {
                return i2 + i;
            }
        }
        return -1;
    }

    private boolean f(int i) {
        Iterator it = this.k.products.iterator();
        while (it.hasNext()) {
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (dJIProductModel.showPage == i && g.b(this.ai, dJIProductModel.series, false)) {
                return true;
            }
        }
        return false;
    }

    private int t() {
        ProductType c = i.getInstance().c();
        DJILogHelper.getInstance().LOGD("", "***************首页获得飞机类型" + c.toString() + "********************", true, true);
        Iterator it = this.k.products.iterator();
        while (it.hasNext()) {
            DJIProductModel dJIProductModel = (DJIProductModel) it.next();
            if (dJIProductModel.value == c.value()) {
                g.a(this.ai, dJIProductModel.series, true);
                g.a(this.ai, l, dJIProductModel.series);
                return c.value();
            }
        }
        return c.value();
    }

    private void u() {
        this.ak = new dji.pilot2.widget.a(this.ai, R.style.hf, 4);
        int[] c = dji.pilot2.utils.p.c(this.ai);
        WindowManager.LayoutParams attributes = this.ak.getWindow().getAttributes();
        attributes.width = c[0];
        attributes.height = c[1];
        this.ak.getWindow().setAttributes(attributes);
        this.ak.a(0.0f);
        c = new int[2];
        this.G.getLocationInWindow(c);
        Log.i(dji.pilot2.widget.a.a, "location icon x: " + c[0] + ", y: " + c[1] + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
        int width = this.G.getWidth() / 2;
        this.ak.a(c[0], c[1], width, getResources().getDimensionPixelSize(R.dimen.fj));
        this.ak.show();
    }

    private String v() {
        String a;
        ProductType c = i.getInstance().c();
        String str = "";
        if (c.equals(ProductType.A2) || c.equals(ProductType.Grape2)) {
            a = a.getInstance(getActivity()).a(ProductType.Grape2);
        } else {
            a = a.getInstance(getActivity()).a(c);
        }
        return k.n(a);
    }

    private String w() {
        return k.n(((ProductTypeStruct) ((ArrayList) this.t.get(this.s[this.j.getCurrentPager()])).get(this.aa.getSelectedItemPosition())).getLearnMoreNeverUrl());
    }

    private void x() {
        Builder builder = new Builder(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.dialog_v2_main_media_guide, null);
        builder.setView(inflate);
        builder.setCancelable(false);
        final AlertDialog create = builder.create();
        inflate.findViewById(R.id.ru).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPhantomFragment b;

            public void onClick(View view) {
                if (create != null && create.isShowing()) {
                    create.dismiss();
                }
            }
        });
        inflate.findViewById(R.id.rv).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ DJIPhantomFragment b;

            public void onClick(View view) {
                c.a().e(ClickMediaTipEvent.ClickMediaTipButtonEvent);
                if (create != null && create.isShowing()) {
                    create.dismiss();
                }
            }
        });
        create.show();
    }

    public void onEventMainThread(dji.midware.b.a.c cVar) {
        if (cVar == dji.midware.b.a.c.a) {
            DJILogHelper.getInstance().LOGD(w, "phantom fragment received ble info");
            if (p() != ProductType.LonganMobile) {
                int pageid = this.k.getPageid(ProductType.LonganMobile.value());
                int pageLoc = this.k.getPageLoc(ProductType.LonganMobile.value());
                int pageLoc2 = this.k.getPageLoc(ProductType.LonganMobile.value());
                if (pageid < this.ab) {
                    this.j.gotoPage(pageid);
                    if (pageLoc < this.Q.getCount()) {
                        this.Q.a(pageLoc);
                        this.Q.notifyDataSetChanged();
                        this.aa.setSelection(pageLoc2);
                    }
                }
                DJILogHelper.getInstance().LOGD(w, "page count" + this.j.getChildCount(), false, true);
            }
        }
    }

    public void onEventBackgroundThread(dji.pilot2.upgrade.b.d dVar) {
        switch (dVar) {
            case UpgradeFinish:
            case Upgrading:
            case NeedUpgrade:
                an = false;
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(DataGimbalActiveStatus dataGimbalActiveStatus) {
        an = false;
    }

    public void c() {
        if ((f(this.j.getCurrentPager()) || this.ad) && ServiceManager.getInstance().isOK() && ServiceManager.getInstance().isRemoteOK() && (i.getInstance().c() == ProductType.A3 || dji.pilot.publics.e.a.d(null) || i.getInstance().c() == ProductType.N3)) {
            final DataFlycActiveStatus dataFlycActiveStatus = new DataFlycActiveStatus();
            dataFlycActiveStatus.setType(dji.midware.data.model.b.a.b.b).start(new dji.midware.e.d(this) {
                final /* synthetic */ DJIPhantomFragment b;

                public void onSuccess(Object obj) {
                    this.b.j.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass8 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            if (dataFlycActiveStatus.getActiveStatus()) {
                                this.a.b.o();
                                return;
                            }
                            int i = R.string.fpv_a3_is_not_activite;
                            if (i.getInstance().c() == ProductType.PM820) {
                                i = R.string.fpv_m600_is_not_activite;
                            } else if (i.getInstance().c() == ProductType.PM820PRO) {
                                i = R.string.fpv_m600_pro_is_not_activite;
                            } else if (i.getInstance().c() == ProductType.N3) {
                                i = R.string.fpv_n3_is_not_activite;
                            }
                            new Builder(this.a.b.getActivity()).setMessage(i).setPositiveButton(R.string.ok, new DialogInterface.OnClickListener(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                        }
                    });
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
            return;
        }
        o();
    }
}
