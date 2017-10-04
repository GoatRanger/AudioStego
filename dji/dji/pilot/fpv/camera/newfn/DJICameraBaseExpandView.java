package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.LinearLayout.LayoutParams;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCameraSetPhoto.TYPE;
import dji.midware.data.model.P3.DataCameraSetSSDVideoFormat;
import dji.midware.data.model.P3.DataCameraSetVideoFormat;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.camera.newfn.PinnedHeaderExpandableListView.a;
import dji.pilot.fpv.camera.newfn.b.b;
import dji.pilot.fpv.d.c.s;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.phonecamera.a.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DJICameraBaseExpandView extends DJILinearLayout implements a, s, DJIStageView.a {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private static String[] s = new String[]{"480P", "480i", "640P", "640i", c.A, "720i", "960P", "960i", "960P", "960i", "1080P", "1080i", "1440P", "1440i", "4K", "4K", "4K", "4K", "4K", "4K", "4K", "4K", "4K", "4K", "2.7K", "2.7K"};
    private static String[] t = new String[]{"15", "24", "25", "30", "48", "50", "60", "120", "240", "480", "100", "96", "180", "24", "30", "48", "60"};
    protected PinnedHeaderExpandableListView d = null;
    protected int e = Integer.MAX_VALUE;
    protected int f = Integer.MAX_VALUE;
    protected dji.pilot.fpv.camera.newfn.a.a g = null;
    protected OnGroupClickListener h = null;
    protected OnClickListener i = null;
    protected dji.pilot.fpv.camera.newfn.a.a.c j = null;
    protected List<b> k = null;
    protected int l = Integer.MAX_VALUE;
    protected int m = Integer.MAX_VALUE;
    protected DJILinearLayout n;
    protected DJIImageView o;
    protected DJITextView p;
    protected DJITextView q;
    protected boolean r = false;

    public DJICameraBaseExpandView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void updateData(int i) {
        boolean z;
        if (1 == i) {
            int videoStandard = DataCameraGetPushShotParams.getInstance().getVideoStandard();
            z = videoStandard != this.f;
            this.f = videoStandard;
        } else {
            z = false;
        }
        if (z || this.e != i || this.r) {
            this.r = false;
            this.l = Integer.MAX_VALUE;
            this.m = Integer.MAX_VALUE;
            this.e = i;
            this.k = e();
            this.g.a(this.k);
            if (this.e == 1) {
                if (i.getInstance().c() != ProductType.OrangeRAW || this.n.isShown()) {
                    this.n.go();
                } else {
                    this.n.show();
                    this.d.setOnHeaderUpdateListener(this);
                }
            } else if (this.n != null && this.n.isShown()) {
                this.n.go();
            }
        }
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
    }

    protected void a(int i, int i2, int i3) {
        if (this.k != null && !this.k.isEmpty()) {
            int size = this.k.size();
            for (int i4 = 0; i4 < size; i4++) {
                b bVar = (b) this.k.get(i4);
                int size2;
                int i5;
                if (bVar.f == i2) {
                    if (!this.d.isGroupExpanded(i4)) {
                        this.d.expandGroup(i4, true);
                        this.d.setSelectedGroup(i4);
                    }
                    bVar.h = true;
                    if (bVar.j == 2) {
                        ((dji.pilot.fpv.camera.newfn.b.a) bVar.k.get(0)).b = i3;
                    } else {
                        size2 = bVar.k.size();
                        for (i5 = 0; i5 < size2; i5++) {
                            dji.pilot.fpv.camera.newfn.b.a aVar = (dji.pilot.fpv.camera.newfn.b.a) bVar.k.get(i5);
                            if (aVar.b == i3) {
                                aVar.d = true;
                                bVar.e = aVar.a;
                                bVar.g = i3;
                                if (this.n != null && this.n.isShown()) {
                                    this.p.setText(bVar.d);
                                    this.q.setText(bVar.e);
                                }
                            } else {
                                aVar.d = false;
                            }
                        }
                    }
                } else if (bVar.f == i) {
                    this.d.collapseGroup(i4);
                    bVar.h = false;
                    size2 = bVar.k.size();
                    for (i5 = 0; i5 < size2; i5++) {
                        ((dji.pilot.fpv.camera.newfn.b.a) bVar.k.get(i5)).d = false;
                    }
                } else {
                    this.d.collapseGroup(i4);
                    bVar.h = false;
                }
            }
            this.g.notifyDataSetChanged();
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        int videoFormat;
        int videoFps;
        if (1 == this.e) {
            if (DataCameraGetPushShotParams.getInstance().getVideoStandard() != this.f) {
                updateData(this.e);
                return;
            } else {
                videoFormat = dataCameraGetPushShotParams.getVideoFormat();
                videoFps = dataCameraGetPushShotParams.getVideoFps();
            }
        } else if (this.e == 0 || 2 == this.e) {
            videoFormat = dji.pilot.fpv.camera.more.a.getInstance().l().a();
            videoFps = dji.pilot.fpv.camera.more.a.getInstance().n();
        } else {
            videoFps = Integer.MAX_VALUE;
            videoFormat = Integer.MAX_VALUE;
        }
        if (videoFormat != Integer.MAX_VALUE && videoFps != Integer.MAX_VALUE) {
            if (videoFormat != this.l || videoFps != this.m) {
                a(this.l, videoFormat, videoFps);
                this.l = videoFormat;
                this.m = videoFps;
            }
        }
    }

    protected boolean a(ExpandableListView expandableListView, View view, int i, long j) {
        int i2 = 0;
        final b bVar = (b) this.k.get(i);
        if (bVar.f != this.l) {
            int i3;
            int a;
            if (this.e == 0 || 2 == this.e) {
                if (!DataCameraGetPushStateInfo.getInstance().getIsTimePhotoing()) {
                    TYPE find = TYPE.find(bVar.f);
                    if (bVar.k.isEmpty()) {
                        i3 = 0;
                    } else {
                        i3 = ((dji.pilot.fpv.camera.newfn.b.a) bVar.k.get(0)).b;
                    }
                    a = dji.pilot.fpv.camera.more.a.getInstance().a(find, i3);
                    Iterator it = bVar.k.iterator();
                    while (it.hasNext()) {
                        if (a == ((dji.pilot.fpv.camera.newfn.b.a) it.next()).b) {
                            i2 = 1;
                            break;
                        }
                    }
                    if (i2 != 0) {
                        i3 = a;
                    }
                    if (dji.pilot.fpv.camera.more.a.getInstance().b(find, i3)) {
                        a(this.l, bVar.f, i3);
                        this.l = bVar.f;
                        this.m = i3;
                    }
                }
            } else if (1 == this.e && !((i.getInstance().c() == ProductType.OrangeRAW && dji.pilot.fpv.d.b.t()) || bVar.d.contains("@T"))) {
                int i4;
                i3 = DataCameraGetPushShotParams.getInstance().getVideoFps();
                int size = bVar.k.size();
                for (a = 0; a < size; a++) {
                    if (((dji.pilot.fpv.camera.newfn.b.a) bVar.k.get(a)).b == i3) {
                        i2 = 1;
                        break;
                    }
                }
                if (i2 == 0) {
                    i4 = 1;
                } else {
                    i4 = i3;
                }
                DataCameraSetVideoFormat dataCameraSetVideoFormat = new DataCameraSetVideoFormat();
                dji.pilot.fpv.camera.a.a.a(null, "VideoFps-" + bVar.f + com.alipay.sdk.j.i.b + i4);
                dataCameraSetVideoFormat.a().a(bVar.f).b(i4).start(new d(this) {
                    final /* synthetic */ DJICameraBaseExpandView c;

                    public void onSuccess(Object obj) {
                        Map hashMap = new HashMap();
                        String str = "None";
                        if (bVar.f < DJICameraBaseExpandView.s.length) {
                            str = DJICameraBaseExpandView.s[bVar.f];
                        }
                        String str2 = "None";
                        if (i4 < DJICameraBaseExpandView.t.length) {
                            str2 = DJICameraBaseExpandView.t[i4];
                        }
                        hashMap.put(dji.pilot.fpv.d.d.dH, str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2);
                        e.a(s.dh, hashMap);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
                new DataCameraSetSSDVideoFormat().a(bVar.f).b(i4).start(new d(this) {
                    final /* synthetic */ DJICameraBaseExpandView b;

                    public void onSuccess(Object obj) {
                        DJILogHelper.getInstance().LOGD("", "set ssd format onSuccessfps:" + i4, false, true);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        DJILogHelper.getInstance().LOGD("", "set ssd format onFailure" + aVar, false, true);
                    }
                });
            }
        }
        return true;
    }

    protected void a(Object obj) {
        if (obj instanceof dji.pilot.fpv.camera.newfn.b.a) {
            dji.pilot.fpv.camera.newfn.b.a aVar = (dji.pilot.fpv.camera.newfn.b.a) obj;
            if (this.l != aVar.c || aVar.b != this.m) {
                if (1 == this.e) {
                    if (i.getInstance().c() != ProductType.OrangeRAW || !dji.pilot.fpv.d.b.t()) {
                        final int i = aVar.c;
                        final int i2 = aVar.b;
                        new DataCameraSetVideoFormat().a().a(i).b(i2).start(new d(this) {
                            final /* synthetic */ DJICameraBaseExpandView c;

                            public void onSuccess(Object obj) {
                                Map hashMap = new HashMap();
                                String str = "None";
                                if (i < DJICameraBaseExpandView.s.length) {
                                    str = DJICameraBaseExpandView.s[i];
                                }
                                String str2 = "None";
                                if (i2 < DJICameraBaseExpandView.t.length) {
                                    str2 = DJICameraBaseExpandView.t[i2];
                                }
                                hashMap.put(dji.pilot.fpv.d.d.dH, str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2);
                                e.a(s.dh, hashMap);
                                if (DataCameraGetPushStateInfo.getInstance().getCameraType() == CameraType.DJICameraTypeFC550Raw) {
                                    new DataCameraSetSSDVideoFormat().a(i).b(i2).start(new d(this) {
                                        final /* synthetic */ AnonymousClass3 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void onSuccess(Object obj) {
                                            DJILogHelper.getInstance().LOGD("", "set ssd format onSuccessfps:" + i2, false, true);
                                        }

                                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                                            DJILogHelper.getInstance().LOGD("", "set ssd format onFailure" + aVar, false, true);
                                            DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                                            bVar.b = R.string.fpv_setting_videoFormat_error;
                                            bVar.a = DJIErrorPopView.d.b;
                                            bVar.f = DJIErrorPopView.c.c;
                                            dji.thirdparty.a.c.a().e(bVar);
                                        }
                                    });
                                }
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                                bVar.b = R.string.fpv_setting_videoFormat_error;
                                bVar.a = DJIErrorPopView.d.b;
                                bVar.f = DJIErrorPopView.c.c;
                                dji.thirdparty.a.c.a().e(bVar);
                            }
                        });
                    }
                } else if (this.e == 0 && !DataCameraGetPushStateInfo.getInstance().getIsTimePhotoing()) {
                    dji.pilot.fpv.camera.more.a.getInstance().b(TYPE.find(aVar.c), aVar.b);
                    a(this.l, aVar.c, aVar.b);
                    this.l = aVar.c;
                    this.m = aVar.b;
                }
            }
        }
    }

    private void c() {
        this.h = new OnGroupClickListener(this) {
            final /* synthetic */ DJICameraBaseExpandView a;

            {
                this.a = r1;
            }

            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long j) {
                if (this.a.a(expandableListView, view, i, j)) {
                }
                return true;
            }
        };
        this.i = new OnClickListener(this) {
            final /* synthetic */ DJICameraBaseExpandView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(view.getTag());
            }
        };
        this.j = new dji.pilot.fpv.camera.newfn.a.a.c(this) {
            final /* synthetic */ DJICameraBaseExpandView a;

            {
                this.a = r1;
            }

            public void a(b bVar, dji.pilot.fpv.camera.newfn.b.a aVar) {
                if (2 == this.a.e) {
                    dji.pilot.fpv.camera.more.a.getInstance().b(TYPE.find(aVar.c), aVar.b);
                    this.a.l = aVar.c;
                    this.a.m = aVar.b;
                }
            }

            public void b(b bVar, dji.pilot.fpv.camera.newfn.b.a aVar) {
            }

            public void a(b bVar, dji.pilot.fpv.camera.newfn.b.a aVar, boolean z) {
            }
        };
        this.g = new dji.pilot.fpv.camera.newfn.a.a(getContext());
        this.g.a(this.i);
        this.g.a(this.j);
        this.n = (DJILinearLayout) findViewById(R.id.k_);
        this.o = (DJIImageView) this.n.findViewById(R.id.ki);
        this.o.go();
        this.p = (DJITextView) this.n.findViewById(R.id.kj);
        this.q = (DJITextView) this.n.findViewById(R.id.kk);
        dji.sdksharedlib.a.a.b(new dji.sdksharedlib.c.d(this) {
            final /* synthetic */ DJICameraBaseExpandView a;

            {
                this.a = r1;
            }

            public void onValueChange(dji.sdksharedlib.b.c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
                if (cVar != null && aVar2 != null && aVar2.e() != null) {
                    dji.pilot.fpv.camera.more.a.getInstance().a(((Boolean) aVar2.e()).booleanValue());
                    this.a.d();
                } else if (aVar2 != null && aVar2.e() == null) {
                    dji.pilot.fpv.camera.more.a.getInstance().a(false);
                    this.a.d();
                }
            }
        }, new String[]{dji.sdksharedlib.b.b.cb});
        dji.pilot.fpv.camera.more.a.getInstance().a(dji.sdksharedlib.a.a.b(dji.sdksharedlib.a.a.b(dji.sdksharedlib.b.b.cb)));
    }

    private void d() {
        this.r = true;
        updateData(this.e);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        c();
        this.d = (PinnedHeaderExpandableListView) findViewById(R.id.k9);
        this.d.setGroupIndicator(null);
        this.d.setOnGroupClickListener(this.h);
        this.d.setAdapter(this.g);
    }

    private List<b> e() {
        List arrayList = new ArrayList();
        dji.pilot.fpv.camera.more.a instance = dji.pilot.fpv.camera.more.a.getInstance();
        String[] k;
        int[] j;
        int[] i;
        int length;
        int i2;
        b bVar;
        int[] b;
        int length2;
        int i3;
        dji.pilot.fpv.camera.newfn.b.a aVar;
        if (this.e == 0) {
            k = instance.k();
            j = instance.j();
            i = instance.i();
            if (i != null && i.length > 0) {
                length = i.length;
                for (i2 = 0; i2 < length; i2++) {
                    bVar = new b();
                    bVar.d = k[i2];
                    bVar.c = j[i2];
                    bVar.f = i[i2];
                    b = instance.b(i[i2]);
                    if (b != null) {
                        length2 = b.length;
                        for (i3 = 0; i3 < length2; i3++) {
                            aVar = new dji.pilot.fpv.camera.newfn.b.a();
                            if (i[i2] == TYPE.g.a()) {
                                aVar.a = String.valueOf(b[i3]) + "s";
                            } else {
                                aVar.a = String.valueOf(b[i3]);
                            }
                            aVar.c = i[i2];
                            aVar.b = b[i3];
                            bVar.k.add(aVar);
                        }
                    }
                    arrayList.add(bVar);
                }
            }
        } else if (1 == this.e) {
            k = instance.Y();
            j = instance.W();
            i = instance.X();
            length = DataCameraGetPushShotParams.getInstance().getVideoStandard();
            if (i != null && i.length > 0) {
                int length3 = i.length;
                for (i2 = 0; i2 < length3; i2++) {
                    b bVar2 = new b();
                    bVar2.d = k[i2];
                    bVar2.c = j[i2];
                    bVar2.f = i[i2];
                    if (!k[i2].contains("@T")) {
                        int[] c = instance.c(instance.r(i[i2]), length);
                        if (c != null) {
                            String[] d = instance.d(instance.r(i[i2]), length);
                            int length4 = c.length;
                            for (i3 = 0; i3 < length4; i3++) {
                                dji.pilot.fpv.camera.newfn.b.a aVar2 = new dji.pilot.fpv.camera.newfn.b.a();
                                aVar2.a = d[i3];
                                aVar2.c = i[i2];
                                aVar2.b = c[i3];
                                bVar2.k.add(aVar2);
                            }
                        }
                    }
                    arrayList.add(bVar2);
                }
            }
        } else if (2 == this.e) {
            k = instance.q();
            j = instance.p();
            i = instance.o();
            if (i != null && i.length > 0) {
                length = i.length;
                for (i2 = 0; i2 < length; i2++) {
                    bVar = new b();
                    bVar.d = k[i2];
                    bVar.c = j[i2];
                    bVar.f = i[i2];
                    b = instance.c(i[i2]);
                    if (b != null) {
                        if (i[i2] == TYPE.g.a()) {
                            dji.pilot.fpv.camera.newfn.b.a aVar3 = new dji.pilot.fpv.camera.newfn.b.a();
                            aVar3.e = "s";
                            aVar3.f = b[0];
                            aVar3.g = b[1];
                            aVar3.c = i[i2];
                            aVar3.b = 7;
                            bVar.k.add(aVar3);
                            bVar.j = 2;
                        } else {
                            length2 = b.length;
                            for (i3 = 0; i3 < length2; i3++) {
                                aVar = new dji.pilot.fpv.camera.newfn.b.a();
                                aVar.a = String.valueOf(b[i3]);
                                aVar.c = i[i2];
                                aVar.b = b[i3];
                                bVar.k.add(aVar);
                            }
                        }
                    }
                    arrayList.add(bVar);
                }
            }
        }
        return arrayList;
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        dji.thirdparty.a.c.a().a(this);
    }

    public void dispatchOnPause() {
        dji.thirdparty.a.c.a().d(this);
    }

    public View getView() {
        return this;
    }

    public View getPinnedHeader() {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.camera_newfn_base_expandview_title, null);
        viewGroup.setLayoutParams(new LayoutParams(-1, -2));
        return viewGroup;
    }

    public void updatePinnedHeader(View view, int i) {
        DJITextView dJITextView = (DJITextView) view.findViewById(R.id.kl);
        if (i <= this.g.getGroupCount()) {
            while (i >= 0) {
                String str = ((b) this.g.getGroup(i)).d;
                if (str.contains("@T")) {
                    dJITextView.setText(str.replace("@T", ""));
                    return;
                }
                i--;
            }
        }
    }
}
