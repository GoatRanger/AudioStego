package dji.pilot.playback.litchi;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap.Config;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import dji.log.DJILogHelper;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.pilot.R;
import dji.pilot.usercenter.f.d;
import dji.pilot.usercenter.f.f;
import dji.pilot.usercenter.mode.g;
import dji.pilot.usercenter.widget.DJIShareProgressBar;
import dji.pilot2.main.a.a;
import dji.pilot2.nativeexplore.explorepost.ExplorePostSelectActivity;
import dji.pilot2.publics.b.a$e;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class b extends BaseExpandableListAdapter {
    private final LayoutInflater a;
    private int b;
    private int c;
    private final List<a> d;
    private SparseArray<g> e;
    private SparseArray<DJIAlbumFileInfo> f;
    private SparseArray<DJIAlbumFileInfo> g;
    private final OnClickListener h;
    private ImageLoader i;
    private final boolean j;
    private dji.logic.album.a.b k = null;
    private DisplayImageOptions l = null;
    private f m = f.getInstance();
    private DJIPlayBackActivity n = null;
    private final int o;
    private h p;

    public b(Context context, int i, int i2, int i3, List<a> list, SparseArray<g> sparseArray, ImageLoader imageLoader, OnClickListener onClickListener, boolean z) {
        this.a = LayoutInflater.from(context);
        this.o = i;
        this.b = i2;
        this.c = i3;
        this.d = list;
        this.e = sparseArray;
        this.h = onClickListener;
        this.i = imageLoader;
        this.j = z;
        this.m.a();
        this.l = new Builder().imageScaleType(ImageScaleType.EXACTLY).showImageOnLoading(R.color.ah).showImageOnFail(R.color.ah).displayer(new SimpleBitmapDisplayer()).considerExifParams(true).cacheInMemory(true).cacheOnDisc(false).bitmapConfig(Config.RGB_565).build();
        this.n = (DJIPlayBackActivity) context;
        this.k = dji.logic.album.a.b.getInstance(context);
    }

    public b(Context context, int i, int i2, int i3, List<a> list, SparseArray<DJIAlbumFileInfo> sparseArray, SparseArray<DJIAlbumFileInfo> sparseArray2, OnClickListener onClickListener, boolean z) {
        this.a = LayoutInflater.from(context);
        this.o = i;
        this.b = i2;
        this.c = i3;
        this.d = list;
        this.f = sparseArray;
        this.g = sparseArray2;
        this.h = onClickListener;
        this.j = z;
        this.l = new Builder().imageScaleType(ImageScaleType.EXACTLY).showImageOnLoading(R.color.ah).showImageOnFail(R.color.ah).displayer(new SimpleBitmapDisplayer()).considerExifParams(true).cacheInMemory(true).cacheOnDisc(false).bitmapConfig(Config.RGB_565).build();
        this.n = (DJIPlayBackActivity) context;
        this.p = h.getInstance();
        this.k = dji.logic.album.a.b.getInstance(context);
    }

    public int getGroupCount() {
        return this.d != null ? this.d.size() : 0;
    }

    public int getChildrenCount(int i) {
        if (this.d == null || this.d.size() <= i) {
            return 0;
        }
        a aVar = (a) this.d.get(i);
        int size;
        if (this.j) {
            size = aVar.b.size();
            if (size != 0) {
                return ((size - 1) / this.o) + 1;
            }
            return 0;
        }
        size = aVar.a.size();
        return size == 0 ? 0 : ((size - 1) / this.o) + 1;
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

    @SuppressLint({"InflateParams"})
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        i iVar;
        if (view == null) {
            iVar = new i();
            view = this.a.inflate(R.layout.playback_item_title_view, null);
            iVar.a = (DJITextView) view.findViewById(R.id.bhe);
            iVar.b = (DJITextView) view.findViewById(R.id.bhg);
            iVar.c = (DJITextView) view.findViewById(R.id.bhf);
            iVar.d = (DJITextView) view.findViewById(R.id.bhh);
            view.setTag(iVar);
        } else {
            iVar = (i) view.getTag();
        }
        DJILogHelper.getInstance().LOGD("adapter", "mAlbumGroup size:" + this.d.size(), true, false);
        a aVar = (a) this.d.get(i);
        if (aVar != null) {
            iVar.a.setText(aVar.a());
            iVar.b.setText(aVar.b() + "");
            iVar.c.setText(aVar.c() + "");
            this.n.b();
            this.n.b();
            if (e.h != -1) {
                iVar.a.hide();
                iVar.b.hide();
                iVar.c.hide();
                iVar.d.show();
            } else {
                iVar.a.show();
                iVar.b.show();
                iVar.c.show();
                iVar.d.hide();
            }
        }
        return view;
    }

    private int a(int i) {
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

    private void a(d dVar, DJIRelativeLayout dJIRelativeLayout) {
        dVar.a = dJIRelativeLayout;
        dVar.b = (DJIShareProgressBar) dJIRelativeLayout.findViewById(R.id.c1v);
        dVar.c = (DJILinearLayout) dJIRelativeLayout.findViewById(R.id.c1w);
        dVar.d = (DJIImageView) dJIRelativeLayout.findViewById(R.id.c22);
        dVar.e = (DJITextView) dJIRelativeLayout.findViewById(R.id.c1y);
        dVar.g = (DJIImageView) dJIRelativeLayout.findViewById(R.id.c20);
        dVar.h = (DJIImageView) dJIRelativeLayout.findViewById(R.id.c21);
        dVar.f = (DJIImageView) dJIRelativeLayout.findViewById(R.id.c1z);
        dVar.i = (DJIImageView) dJIRelativeLayout.findViewById(R.id.c23);
        LayoutParams layoutParams = dVar.a.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, this.b);
        } else {
            layoutParams.width = -1;
            layoutParams.height = this.b;
        }
        dVar.a.setLayoutParams(layoutParams);
        dVar.a.setOnClickListener(this.h);
    }

    @SuppressLint({"InflateParams"})
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        int i3;
        d[] dVarArr;
        if (view == null) {
            Object obj = new d[this.o];
            View inflate = this.a.inflate(R.layout.playback_album_content_item, null);
            for (i3 = 0; i3 < this.o; i3++) {
                obj[i3] = new d();
                a(obj[i3], (DJIRelativeLayout) inflate.findViewById(a(i3)));
            }
            ((LinearLayout) inflate).setWeightSum((float) this.o);
            inflate.setTag(obj);
            view = inflate;
            dVarArr = obj;
        } else {
            dVarArr = (d[]) view.getTag();
        }
        for (int i4 = 0; i4 < this.o; i4++) {
            dVarArr[i4].a.hide();
        }
        if (this.d != null && this.d.size() > i) {
            a aVar = (a) this.d.get(i);
            List list;
            int i5;
            if (this.j) {
                list = aVar.b;
                i5 = i2 * this.o;
                i3 = 0;
                while (i5 + i3 < list.size() && i3 < this.o) {
                    DJIAlbumFileInfo dJIAlbumFileInfo = (DJIAlbumFileInfo) list.get(i5 + i3);
                    dVarArr[i3].b.setTag(dJIAlbumFileInfo.b());
                    dVarArr[i3].a.show();
                    if (dJIAlbumFileInfo.j == dji.logic.album.a.b.f.f || dJIAlbumFileInfo.j == dji.logic.album.a.b.f.b || dJIAlbumFileInfo.j == dji.logic.album.a.b.f.a || dJIAlbumFileInfo.j == dji.logic.album.a.b.f.e) {
                        dVarArr[i3].c.go();
                        if (dJIAlbumFileInfo.j == dji.logic.album.a.b.f.b) {
                            dVarArr[i3].d.show();
                            dVarArr[i3].d.setImageDrawable(this.a.getContext().getResources().getDrawable(R.drawable.playback_raw));
                        } else if (dJIAlbumFileInfo.j == dji.logic.album.a.b.f.f) {
                            dVarArr[i3].d.show();
                            dVarArr[i3].d.setImageDrawable(this.a.getContext().getResources().getDrawable(R.drawable.playback_tiff));
                        } else {
                            dVarArr[i3].d.go();
                        }
                    } else if (dJIAlbumFileInfo.j == dji.logic.album.a.b.f.c || dJIAlbumFileInfo.j == dji.logic.album.a.b.f.d) {
                        dVarArr[i3].c.show();
                        dVarArr[i3].e.setText("");
                        if (dJIAlbumFileInfo.j == dji.logic.album.a.b.f.c) {
                            dVarArr[i3].d.go();
                        } else {
                            dVarArr[i3].d.go();
                        }
                    }
                    if (this.n.b().c()) {
                        if (this.k.b(dJIAlbumFileInfo.c()) && dVarArr[i3].b.getTag().equals(dJIAlbumFileInfo.b())) {
                            dVarArr[i3].b.setImageDrawable(this.a.getContext().getResources().getDrawable(R.color.ah));
                            dVarArr[i3].b.setImageBitmap(this.k.a(dJIAlbumFileInfo.c()));
                        } else if (this.k.d(dJIAlbumFileInfo.c()) && dVarArr[i3].b.getTag().equals(dJIAlbumFileInfo.b())) {
                            dVarArr[i3].b.setImageBitmap(this.k.c(dJIAlbumFileInfo.c()));
                        } else {
                            dVarArr[i3].b.setImageDrawable(this.a.getContext().getResources().getDrawable(R.color.ah));
                        }
                    } else if (this.n.b().d()) {
                        if (this.k.b(dJIAlbumFileInfo.c()) && dVarArr[i3].b.getTag().equals(dJIAlbumFileInfo.b())) {
                            dVarArr[i3].b.setImageBitmap(this.k.a(dJIAlbumFileInfo.c()));
                        } else if (this.k.d(dJIAlbumFileInfo.c()) && dVarArr[i3].b.getTag().equals(dJIAlbumFileInfo.b())) {
                            dVarArr[i3].b.setImageDrawable(this.a.getContext().getResources().getDrawable(R.color.ah));
                            dVarArr[i3].b.setImageBitmap(this.k.c(dJIAlbumFileInfo.c()));
                        } else {
                            dVarArr[i3].b.setImageDrawable(this.a.getContext().getResources().getDrawable(R.color.ah));
                            this.p.a(dVarArr[i3].b, dJIAlbumFileInfo, true);
                        }
                    } else if (this.k.b(dJIAlbumFileInfo.c()) && dVarArr[i3].b.getTag().equals(dJIAlbumFileInfo.b())) {
                        dVarArr[i3].b.setImageBitmap(this.k.a(dJIAlbumFileInfo.c()));
                    } else if (this.k.d(dJIAlbumFileInfo.c()) && dVarArr[i3].b.getTag().equals(dJIAlbumFileInfo.b())) {
                        dVarArr[i3].b.setImageBitmap(this.k.c(dJIAlbumFileInfo.c()));
                    } else {
                        this.p.a(dVarArr[i3].b, dJIAlbumFileInfo, true);
                    }
                    if (dJIAlbumFileInfo.j == dji.logic.album.a.b.f.e) {
                        ImageLoader.getInstance().displayImage(a$e.a + h.e + h.d + "/DJIPANO_" + dJIAlbumFileInfo.c + a.n, dVarArr[i3].b, this.l);
                    }
                    int hashCode = dJIAlbumFileInfo.hashCode();
                    if (this.g.indexOfKey(hashCode) >= 0) {
                        dVarArr[i3].i.show();
                    } else {
                        dVarArr[i3].i.go();
                    }
                    if (this.f.indexOfKey(hashCode) >= 0) {
                        dVarArr[i3].g.show();
                        dVarArr[i3].h.show();
                        this.n.f.show();
                        this.n.f.setText(this.a.getContext().getResources().getString(R.string.playback_select_num, new Object[]{this.f.size() + ""}));
                    } else {
                        dVarArr[i3].g.go();
                        dVarArr[i3].h.go();
                        if (this.f.size() > 0) {
                            this.n.f.setText(this.a.getContext().getResources().getString(R.string.playback_select_num, new Object[]{this.f.size() + ""}));
                        } else {
                            this.n.f.setText(this.a.getContext().getResources().getString(R.string.playback_select_tip));
                        }
                    }
                    dVarArr[i3].a.setTag(dJIAlbumFileInfo);
                    if (!(dVarArr[i3] == null || dJIAlbumFileInfo == null)) {
                        if (dJIAlbumFileInfo.g == 1 && (dJIAlbumFileInfo.j == dji.logic.album.a.b.f.c || dJIAlbumFileInfo.j == dji.logic.album.a.b.f.d)) {
                            dVarArr[i3].b.setRotation(90.0f);
                        } else {
                            dVarArr[i3].b.setRotation(0.0f);
                        }
                    }
                    i3++;
                }
            } else {
                list = aVar.a;
                i5 = i2 * this.o;
                i3 = 0;
                while (i5 + i3 < list.size() && i3 < this.o) {
                    g gVar = (g) list.get(i5 + i3);
                    dVarArr[i3].b.setTag(gVar.e);
                    dVarArr[i3].a.show();
                    dVarArr[i3].d.go();
                    if (d.b(gVar.g)) {
                        dVarArr[i3].c.show();
                        dVarArr[i3].e.setText(b(gVar.n));
                        this.m.a(dVarArr[i3].b, gVar.e, this.c, this.b);
                    } else {
                        dVarArr[i3].c.go();
                        this.i.displayImage(gVar.c(), dVarArr[i3].b, this.l);
                    }
                    if (this.e.indexOfKey(gVar.hashCode()) >= 0) {
                        dVarArr[i3].g.show();
                        dVarArr[i3].h.show();
                        Log.v(ExplorePostSelectActivity.a, "img show");
                        this.n.f.show();
                        this.n.f.setText(this.a.getContext().getResources().getString(R.string.playback_select_num, new Object[]{this.e.size() + ""}));
                    } else {
                        dVarArr[i3].g.go();
                        dVarArr[i3].h.go();
                        if (this.e.size() > 0) {
                            this.n.f.setText(this.a.getContext().getResources().getString(R.string.playback_select_num, new Object[]{this.e.size() + ""}));
                        } else {
                            this.n.f.setText(this.a.getContext().getResources().getString(R.string.playback_select_tip));
                        }
                    }
                    dVarArr[i3].a.setTag(gVar);
                    if (gVar.l == 2) {
                        dVarArr[i3].f.show();
                    } else {
                        dVarArr[i3].f.go();
                    }
                    i3++;
                }
            }
        }
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }

    private String b(int i) {
        int[] f = dji.pilot.fpv.d.b.f(i);
        return this.a.getContext().getString(R.string.fpv_videotime, new Object[]{Integer.valueOf(f[2]), Integer.valueOf(f[1]), Integer.valueOf(f[0])});
    }
}
