package dji.pilot2.multimoment.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.SparseArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.Toast;
import dji.pilot.R;
import dji.pilot.fpv.d.c.k;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.b.a;
import dji.pilot.usercenter.b.a$a;
import dji.pilot.usercenter.b.a$b;
import dji.pilot.usercenter.mode.g;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.cutmoment.DJICutMomentActivity;
import dji.pilot2.library.a.f;
import dji.pilot2.library.widget.DJIHorizontalListView;
import dji.pilot2.main.activity.DJIMainFragmentActivity;
import dji.pilot2.multimoment.videolib.b;
import dji.pilot2.utils.d;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DJIMultiMomentAddActivity extends DJIActivityNoFullScreen implements k {
    public static final String K = "start_num";
    public static final String L = "total_num";
    public static final String M = "duration";
    public static final String N = "mode";
    protected DJITextView O;
    protected DJITextView P;
    protected DJITextView Q;
    protected ExpandableListView R = null;
    protected f S = null;
    protected OnClickListener T;
    public SparseArray<g> U = new SparseArray(12);
    public List<g> V = new ArrayList();
    protected boolean W = false;
    protected int X;
    protected int Y;
    protected int Z;
    protected b aa;
    protected int ab = 0;
    private a ac = a.getInstance();
    private a$b ad = null;
    private DJIHorizontalListView ae;
    private List<dji.pilot.playback.litchi.a> af;
    private List<g> ag;
    private List<g> ah;
    private List<a$a> ai = null;
    private int aj;
    private int ak;
    private View al = null;
    private Context am;
    private d an;
    private int ao = 3;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onCreate(android.os.Bundle r7) {
        /*
        r6 = this;
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r4 = 0;
        super.onCreate(r7);
        r0 = 2130969365; // 0x7f040315 float:1.754741E38 double:1.0528387556E-314;
        r6.setContentView(r0);
        r6.am = r6;
        r0 = r6.am;
        r1 = "window";
        r0 = r0.getSystemService(r1);
        r0 = (android.view.WindowManager) r0;
        r2 = new android.util.DisplayMetrics;
        r2.<init>();
        r0 = r0.getDefaultDisplay();
        r0.getMetrics(r2);
        r1 = r2.widthPixels;
        r0 = r2.heightPixels;
        r2 = dji.publics.DJIUI.DJIOriLayout.getDeviceType();
        r3 = dji.publics.DJIUI.DJIOriLayout.DJIDeviceType.Pad;
        if (r2 != r3) goto L_0x0137;
    L_0x0030:
        r2 = 6;
        r6.ao = r2;
        if (r0 <= r1) goto L_0x013c;
    L_0x0035:
        r1 = r6.ao;
        r1 = r1 + 1;
        r2 = r6.getResources();
        r3 = 2131427865; // 0x7f0b0219 float:1.8477358E38 double:1.0530652847E-314;
        r2 = r2.getDimensionPixelSize(r3);
        r1 = r1 * r2;
        r0 = r0 - r1;
        r1 = dji.publics.DJIUI.DJIOriLayout.getDeviceType();
        r2 = dji.publics.DJIUI.DJIOriLayout.DJIDeviceType.Pad;
        if (r1 != r2) goto L_0x013f;
    L_0x004e:
        r0 = r0 / 6;
        r6.ak = r0;
    L_0x0052:
        r0 = r6.ak;
        r6.aj = r0;
        r0 = new dji.pilot2.utils.d;
        r1 = r6.am;
        r0.<init>(r1);
        r6.an = r0;
        r0 = dji.pilot.usercenter.b.a.getInstance();
        r0 = r0.c();
        r6.ah = r0;
        r0 = 2131365754; // 0x7f0a0f7a float:1.8351382E38 double:1.053034598E-314;
        r0 = r6.findViewById(r0);
        r0 = (dji.publics.DJIUI.DJITextView) r0;
        r6.O = r0;
        r0 = 2131365755; // 0x7f0a0f7b float:1.8351384E38 double:1.0530345983E-314;
        r0 = r6.findViewById(r0);
        r0 = (dji.publics.DJIUI.DJITextView) r0;
        r6.P = r0;
        r0 = 2131365758; // 0x7f0a0f7e float:1.835139E38 double:1.0530345998E-314;
        r0 = r6.findViewById(r0);
        r0 = (dji.publics.DJIUI.DJITextView) r0;
        r6.Q = r0;
        r0 = 2131365761; // 0x7f0a0f81 float:1.8351396E38 double:1.053034601E-314;
        r0 = r6.findViewById(r0);
        r0 = (android.widget.ExpandableListView) r0;
        r6.R = r0;
        r0 = 2131365759; // 0x7f0a0f7f float:1.8351392E38 double:1.0530346E-314;
        r0 = r6.findViewById(r0);
        r0 = (dji.pilot2.library.widget.DJIHorizontalListView) r0;
        r6.ae = r0;
        r0 = new dji.pilot2.library.a.e;
        r1 = r6.am;
        r2 = r6.ah;
        r0.<init>(r1, r2, r5, r5);
        r1 = r6.ae;
        r1.setAdapter(r0);
        r0 = r6.ae;
        r1 = new dji.pilot2.multimoment.activity.DJIMultiMomentAddActivity$1;
        r1.<init>(r6);
        r0.setOnItemClickListener(r1);
        r0 = r6.ah;
        if (r0 == 0) goto L_0x00c4;
    L_0x00bc:
        r0 = r6.ah;
        r0 = r0.size();
        if (r0 != 0) goto L_0x0145;
    L_0x00c4:
        r0 = r6.ae;
        r0.setVisibility(r4);
    L_0x00c9:
        r0 = r6.R;
        r1 = 0;
        r0.setGroupIndicator(r1);
        r0 = new java.util.ArrayList;
        r0.<init>();
        r6.af = r0;
        r0 = 2131365762; // 0x7f0a0f82 float:1.8351398E38 double:1.0530346017E-314;
        r0 = r6.findViewById(r0);
        r6.al = r0;
        r0 = r6.O;
        r1 = new dji.pilot2.multimoment.activity.DJIMultiMomentAddActivity$2;
        r1.<init>(r6);
        r0.setOnClickListener(r1);
        r0 = r6.Q;
        r0.setEnabled(r4);
        r0 = r6.Q;
        r1 = new dji.pilot2.multimoment.activity.DJIMultiMomentAddActivity$3;
        r1.<init>(r6);
        r0.setOnClickListener(r1);
        r0 = r6.getIntent();
        r1 = "start_num";
        r0 = r0.getIntExtra(r1, r4);
        r6.X = r0;
        r0 = r6.getIntent();
        r1 = "total_num";
        r0 = r0.getIntExtra(r1, r4);
        r6.Y = r0;
        r0 = r6.getIntent();
        r1 = "duration";
        r0 = r0.getIntExtra(r1, r4);
        r6.Z = r0;
        r0 = r6.getIntent();
        r1 = "mode";
        r0 = r0.getIntExtra(r1, r4);
        r0 = dji.pilot2.multimoment.videolib.b.find(r0);
        r6.aa = r0;
        r0 = new dji.pilot2.multimoment.activity.DJIMultiMomentAddActivity$4;
        r0.<init>(r6);
        r6.T = r0;
        r6.f();
        return;
    L_0x0137:
        r2 = 3;
        r6.ao = r2;
        if (r0 < r1) goto L_0x0035;
    L_0x013c:
        r0 = r1;
        goto L_0x0035;
    L_0x013f:
        r0 = r0 / 3;
        r6.ak = r0;
        goto L_0x0052;
    L_0x0145:
        r0 = r6.ae;
        r0.setVisibility(r4);
        goto L_0x00c9;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.pilot2.multimoment.activity.DJIMultiMomentAddActivity.onCreate(android.os.Bundle):void");
    }

    public void a() {
        e.b(this);
        this.ai = this.ac.b();
        if (this.ai == null) {
            this.ac.a(this.ad);
            this.ac.c(this);
            this.W = true;
            return;
        }
        a(0);
    }

    public void b() {
        e.c(this);
        if (this.W) {
            this.ac.b(this.ad);
        }
    }

    private void h() {
        this.S.notifyDataSetChanged();
        for (int i = 0; i < this.S.getGroupCount(); i++) {
            this.R.expandGroup(i);
        }
    }

    public void f() {
        this.ad = new a$b(this) {
            final /* synthetic */ DJIMultiMomentAddActivity a;

            {
                this.a = r1;
            }

            public void a(String str, int i, Object obj) {
                this.a.g();
            }

            public void b(String str, int i, Object obj) {
                this.a.a(0);
            }

            public void c(String str, int i, Object obj) {
            }

            public void a(int i) {
            }
        };
    }

    private void a(g gVar) {
        String[] strArr = new String[]{Uri.parse(gVar.b().o).getPath()};
        Intent intent = new Intent(this.am, DJICutMomentActivity.class);
        intent.putExtra(DJICutMomentActivity.K, strArr);
        intent.putExtra(DJICutMomentActivity.N, gVar.y);
        intent.setAction("android.intent.action.INSERT");
        ((Activity) this.am).startActivityForResult(intent, 4097);
    }

    public void g() {
        this.ai = this.ac.b();
        if (this.ai.size() > 1) {
            this.ag = ((a$a) this.ai.get(1)).d;
            a(this.ag, this.af);
        }
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.S = new f(this, this.aj, this.ak, this.af, this.U, this.V, this.T, 6);
        } else {
            this.S = new f(this, this.aj, this.ak, this.af, this.U, this.V, this.T, 3);
        }
        this.S.a(this.X);
        this.R.setAdapter(this.S);
        this.R.setEmptyView(this.al);
        h();
    }

    private void a(int i) {
        if (i == 0) {
            this.ai = this.ac.b();
            if (this.ai.size() > 1) {
                this.ag = ((a$a) this.ai.get(1)).d;
                a(this.ag, this.af);
            }
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
                this.S = new f(this, this.aj, this.ak, this.af, this.U, this.V, this.T, 6);
            } else {
                this.S = new f(this, this.aj, this.ak, this.af, this.U, this.V, this.T, 3);
            }
            this.S.a(this.X);
            this.R.setAdapter(this.S);
            this.R.setEmptyView(this.al);
            h();
        }
    }

    protected void onStart() {
        super.onStart();
        a();
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onStop() {
        super.onStop();
        b();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void a(g gVar, boolean z, boolean z2) {
        int hashCode = gVar.hashCode();
        if (z2) {
            if (this.U.indexOfKey(hashCode) >= 0) {
                this.U.delete(hashCode);
                this.V.remove(gVar);
            } else {
                this.U.put(hashCode, gVar);
                this.V.add(gVar);
            }
        } else if (z) {
            if (this.U.indexOfKey(hashCode) < 0) {
                this.U.put(hashCode, gVar);
            }
        } else if (this.U.indexOfKey(hashCode) >= 0) {
            this.U.delete(hashCode);
        }
        h();
    }

    public void a(List<g> list, List<dji.pilot.playback.litchi.a> list2) {
        Collections.sort(list, new Comparator<g>(this) {
            final /* synthetic */ DJIMultiMomentAddActivity a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((g) obj, (g) obj2);
            }

            public int a(g gVar, g gVar2) {
                if (dji.pilot.usercenter.f.d.c(gVar.g)) {
                    if (dji.pilot.usercenter.f.d.c(gVar2.g)) {
                        return gVar2.a().h.compareTo(gVar.a().h);
                    }
                    return gVar2.b().h.compareTo(gVar.a().h);
                } else if (dji.pilot.usercenter.f.d.c(gVar2.g)) {
                    return gVar2.a().h.compareTo(gVar.b().h);
                } else {
                    return gVar2.b().h.compareTo(gVar.b().h);
                }
            }
        });
        b(list, list2);
    }

    public void b(List<g> list, List<dji.pilot.playback.litchi.a> list2) {
        String str = null;
        List arrayList = new ArrayList();
        list2.clear();
        if (list.size() > 0) {
            dji.pilot.playback.litchi.a aVar;
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i < list.size()) {
                int i4;
                String trim;
                if (dji.pilot.usercenter.f.d.c(((g) list.get(i)).g)) {
                    int i5;
                    if (i <= 0 || !dji.pilot.usercenter.f.d.c(((g) list.get(i - 1)).g)) {
                        if (i <= 0 || !dji.pilot.usercenter.f.d.b(((g) list.get(i - 1)).g)) {
                            i4 = i3 + 1;
                            trim = ((g) list.get(i)).a().h.subSequence(0, 10).toString().trim();
                            arrayList.add(list.get(i));
                            i5 = i2;
                            i2 = i4;
                            i4 = i5;
                        } else if (((g) list.get(i)).a().h.subSequence(0, 10).toString().trim().equals(((g) list.get(i - 1)).b().h.subSequence(0, 10).toString().trim())) {
                            i4 = i3 + 1;
                            trim = ((g) list.get(i - 1)).b().h.subSequence(0, 10).toString().trim();
                            arrayList.add(list.get(i));
                            i5 = i2;
                            i2 = i4;
                            i4 = i5;
                        } else {
                            aVar = new dji.pilot.playback.litchi.a();
                            aVar.a(str);
                            aVar.a(i3);
                            aVar.b(i2);
                            aVar.a(arrayList);
                            list2.add(aVar);
                            trim = ((g) list.get(i)).a().h.subSequence(0, 10).toString().trim();
                            arrayList.clear();
                            arrayList.add(list.get(i));
                            i4 = 0;
                            i2 = 1;
                        }
                    } else if (((g) list.get(i)).a().h.subSequence(0, 10).toString().trim().equals(((g) list.get(i - 1)).a().h.subSequence(0, 10).toString().trim())) {
                        i4 = i3 + 1;
                        trim = ((g) list.get(i - 1)).a().h.subSequence(0, 10).toString().trim();
                        arrayList.add(list.get(i));
                        i5 = i2;
                        i2 = i4;
                        i4 = i5;
                    } else {
                        aVar = new dji.pilot.playback.litchi.a();
                        aVar.a(str);
                        aVar.a(i3);
                        aVar.b(i2);
                        aVar.a(arrayList);
                        list2.add(aVar);
                        trim = ((g) list.get(i)).a().h.subSequence(0, 10).toString().trim();
                        arrayList.clear();
                        arrayList.add(list.get(i));
                        i4 = 0;
                        i2 = 1;
                    }
                } else if (!dji.pilot.usercenter.f.d.b(((g) list.get(i)).g)) {
                    trim = str;
                    i4 = i2;
                    i2 = i3;
                } else if (i <= 0 || !dji.pilot.usercenter.f.d.c(((g) list.get(i - 1)).g)) {
                    if (i <= 0 || !dji.pilot.usercenter.f.d.b(((g) list.get(i - 1)).g)) {
                        i4 = i2 + 1;
                        trim = ((g) list.get(i)).b().h.subSequence(0, 10).toString().trim();
                        arrayList.add(list.get(i));
                        i2 = i3;
                    } else if (((g) list.get(i)).b().h.subSequence(0, 10).toString().trim().equals(((g) list.get(i - 1)).b().h.subSequence(0, 10).toString().trim())) {
                        i4 = i2 + 1;
                        trim = ((g) list.get(i - 1)).b().h.subSequence(0, 10).toString().trim();
                        arrayList.add(list.get(i));
                        i2 = i3;
                    } else {
                        aVar = new dji.pilot.playback.litchi.a();
                        aVar.a(str);
                        aVar.a(i3);
                        aVar.b(i2);
                        aVar.a(arrayList);
                        list2.add(aVar);
                        trim = ((g) list.get(i)).b().h.subSequence(0, 10).toString().trim();
                        arrayList.clear();
                        arrayList.add(list.get(i));
                        i4 = 1;
                        i2 = 0;
                    }
                } else if (((g) list.get(i)).b().h.subSequence(0, 10).toString().trim().equals(((g) list.get(i - 1)).a().h.subSequence(0, 10).toString().trim())) {
                    i4 = i2 + 1;
                    trim = ((g) list.get(i - 1)).a().h.subSequence(0, 10).toString().trim();
                    arrayList.add(list.get(i));
                    i2 = i3;
                } else {
                    aVar = new dji.pilot.playback.litchi.a();
                    aVar.a(str);
                    aVar.a(i3);
                    aVar.b(i2);
                    aVar.a(arrayList);
                    list2.add(aVar);
                    trim = ((g) list.get(i)).b().h.subSequence(0, 10).toString().trim();
                    arrayList.clear();
                    arrayList.add(list.get(i));
                    i4 = 1;
                    i2 = 0;
                }
                i++;
                i3 = i2;
                i2 = i4;
                str = trim;
            }
            aVar = new dji.pilot.playback.litchi.a();
            aVar.a(str);
            aVar.a(i3);
            aVar.b(i2);
            aVar.a(arrayList);
            list2.add(aVar);
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 4097 && intent != null) {
            if (intent.getBooleanExtra(DJIMainFragmentActivity.N, false)) {
                Toast makeText = Toast.makeText(this.am, R.string.v2_library_12, 0);
                makeText.setGravity(17, 0, 0);
                makeText.show();
            }
            boolean booleanExtra = intent.getBooleanExtra(DJIMainFragmentActivity.P, false);
            ArrayList stringArrayListExtra = intent.getStringArrayListExtra(DJIMainFragmentActivity.O);
            if (stringArrayListExtra != null && stringArrayListExtra.size() != 0) {
                for (int i3 = 0; i3 < stringArrayListExtra.size(); i3++) {
                    File file = new File((String) stringArrayListExtra.get(i3));
                    if (file.isFile() && file.exists()) {
                        this.ac.a(file, booleanExtra);
                    }
                }
                g();
                c.a().e(dji.pilot2.library.a.MomentUpdateByAdd);
            }
        }
    }
}
