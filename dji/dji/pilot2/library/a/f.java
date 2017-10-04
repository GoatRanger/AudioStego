package dji.pilot2.library.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.playback.litchi.a;
import dji.pilot.usercenter.mode.g;
import dji.pilot.usercenter.widget.DJIShareProgressBar;
import dji.pilot2.utils.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class f extends BaseExpandableListAdapter {
    private final LayoutInflater a;
    private final int b;
    private final int c;
    private final List<a> d;
    private SparseArray<g> e;
    private List<g> f;
    private final OnClickListener g;
    private int h = 0;
    private d i;
    private dji.pilot.usercenter.f.f j = dji.pilot.usercenter.f.f.getInstance();
    private int k = 3;
    private Context l;

    public f(Context context, int i, int i2, List<a> list, SparseArray<g> sparseArray, List<g> list2, OnClickListener onClickListener, int i3) {
        this.a = LayoutInflater.from(context);
        this.l = context;
        this.b = i;
        this.c = i2;
        this.f = list2;
        this.d = list;
        this.e = sparseArray;
        this.g = onClickListener;
        this.j.a();
        this.k = i3;
        this.i = new d(context);
    }

    public void a(int i) {
        this.h = i;
    }

    public int getGroupCount() {
        return this.d != null ? this.d.size() : 0;
    }

    public int getChildrenCount(int i) {
        if (this.d == null || this.d.size() <= i) {
            return 0;
        }
        int size = ((a) this.d.get(i)).a.size();
        return size == 0 ? 0 : ((size - 1) / this.k) + 1;
    }

    public Object getGroup(int i) {
        return (this.d == null || this.d.size() <= i) ? null : (a) this.d.get(i);
    }

    public Object getChild(int i, int i2) {
        return (this.d == null || this.d.size() <= i) ? null : (a) this.d.get(i2);
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint({"InflateParams", "SimpleDateFormat"})
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null) {
            bVar = new b();
            view = this.a.inflate(R.layout.v2_library_group_title_moment, null);
            bVar.a = (DJITextView) view.findViewById(R.id.bhe);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        a aVar = (a) this.d.get(i);
        if (aVar != null) {
            try {
                long currentTimeMillis = System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd").parse(aVar.a()).getTime();
                if (currentTimeMillis < 86400000) {
                    bVar.a.setText(R.string.v2_library_date_today);
                } else if (currentTimeMillis < 172800000) {
                    bVar.a.setText(R.string.v2_library_date_yestoday);
                } else {
                    bVar.a.setText(aVar.a());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    private int b(int i) {
        if (i == 1) {
            return R.id.bgp;
        }
        if (i == 2) {
            return R.id.bgq;
        }
        if (i == 3) {
            return R.id.bgr;
        }
        if (i == 4) {
            return R.id.bgs;
        }
        if (i == 5) {
            return R.id.bgt;
        }
        return R.id.bgo;
    }

    private void a(a aVar, DJIRelativeLayout dJIRelativeLayout) {
        aVar.a = dJIRelativeLayout;
        aVar.b = (DJIShareProgressBar) dJIRelativeLayout.findViewById(R.id.c1v);
        aVar.d = (DJILinearLayout) dJIRelativeLayout.findViewById(R.id.c1w);
        aVar.f = (DJITextView) dJIRelativeLayout.findViewById(R.id.c1y);
        aVar.i = (DJIImageView) dJIRelativeLayout.findViewById(R.id.c20);
        aVar.h = (DJIImageView) dJIRelativeLayout.findViewById(R.id.c1z);
        aVar.l = (DJIImageView) dJIRelativeLayout.findViewById(R.id.cpt);
        aVar.m = (DJITextView) dJIRelativeLayout.findViewById(R.id.cpr);
        aVar.p = dJIRelativeLayout.findViewById(R.id.cps);
        aVar.q = dJIRelativeLayout.findViewById(R.id.cpu);
        LayoutParams layoutParams = aVar.a.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, this.b);
        } else {
            layoutParams.width = -1;
            layoutParams.height = this.b;
        }
        aVar.a.setLayoutParams(layoutParams);
        aVar.a.setOnClickListener(this.g);
        aVar.q.setOnClickListener(this.g);
    }

    @SuppressLint({"InflateParams"})
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        int i3;
        a[] aVarArr;
        Log.i("zxcv", "LINE_NUMBER:" + this.k);
        if (view == null) {
            Object obj = new a[this.k];
            View inflate = this.a.inflate(R.layout.v2_library_group_library_item, null);
            for (i3 = 0; i3 < this.k; i3++) {
                obj[i3] = new a();
                a(obj[i3], (DJIRelativeLayout) inflate.findViewById(b(i3)));
            }
            ((LinearLayout) inflate).setWeightSum((float) this.k);
            inflate.setTag(obj);
            view = inflate;
            aVarArr = obj;
        } else {
            aVarArr = (a[]) view.getTag();
        }
        for (int i4 = 0; i4 < this.k; i4++) {
            aVarArr[i4].a.hide();
        }
        if (this.d != null && this.d.size() > i) {
            List list = ((a) this.d.get(i)).a;
            int i5 = i2 * this.k;
            i3 = 0;
            while (i5 + i3 < list.size() && i3 < this.k) {
                g gVar = (g) list.get(i5 + i3);
                aVarArr[i3].b.setTag(gVar.e);
                aVarArr[i3].a.show();
                if (dji.pilot.usercenter.f.d.b(gVar.g)) {
                    aVarArr[i3].f.setText(c(gVar.n));
                    this.j.a(aVarArr[i3].b, gVar.e, this.c, this.b);
                }
                int hashCode = gVar.hashCode();
                if (this.e.indexOfKey(hashCode) >= 0) {
                    aVarArr[i3].i.show();
                    aVarArr[i3].l.setBackground(this.a.getContext().getResources().getDrawable(R.drawable.v2_library_itemselect_true));
                    aVarArr[i3].f.go();
                    aVarArr[i3].f.setTextColor(this.a.getContext().getResources().getColor(R.color.kq));
                    int i6 = 0;
                    if (this.f != null) {
                        i6 = this.f.indexOf(this.e.get(hashCode));
                    }
                    aVarArr[i3].m.setText(((i6 + 1) + this.h) + "");
                    aVarArr[i3].m.show();
                    this.i.b(aVarArr[i3].b, this.c, this.b);
                } else {
                    aVarArr[i3].f.show();
                    aVarArr[i3].i.go();
                    aVarArr[i3].l.setBackground(this.a.getContext().getResources().getDrawable(R.drawable.v2_library_itemselect_false));
                    aVarArr[i3].f.setText(c(gVar.n));
                    aVarArr[i3].f.setTextColor(this.a.getContext().getResources().getColor(R.color.kq));
                    aVarArr[i3].m.hide();
                    this.i.c(aVarArr[i3].b, this.c, this.b);
                }
                h hVar = new h();
                hVar.a = aVarArr[i3].b;
                hVar.c = gVar;
                hVar.b = aVarArr[i3].p;
                hVar.e = aVarArr[i3].l;
                aVarArr[i3].a.setTag(hVar);
                aVarArr[i3].q.setTag(hVar);
                hVar.e.setTag(hVar);
                String language = this.l.getResources().getConfiguration().locale.getLanguage();
                if (language.equals("cn") || language.equals("zh")) {
                    aVarArr[i3].h.setImageDrawable(this.l.getResources().getDrawable(R.drawable.v2_library_origin_cn));
                }
                if (gVar.l == 2 || gVar.z) {
                    aVarArr[i3].h.show();
                } else {
                    aVarArr[i3].h.go();
                }
                i3++;
            }
        }
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    private String c(int i) {
        int[] f = b.f(i);
        return this.a.getContext().getString(R.string.v2_cachelist_videotime, new Object[]{Integer.valueOf(f[1]), Integer.valueOf(f[0])});
    }
}
