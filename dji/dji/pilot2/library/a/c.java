package dji.pilot2.library.a;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import dji.pilot.R;
import dji.pilot.usercenter.f.f;
import dji.pilot2.cutmoment.DJICutMomentActivity;
import dji.pilot2.library.h;
import dji.pilot2.library.model.DJIScanerMediaManager;
import dji.pilot2.library.model.MediaInfoBean;
import dji.pilot2.main.fragment.DJILibraryFragment;
import dji.pilot2.media.activity.DJIMomentPreveiwActivity;
import dji.pilot2.media.activity.DJIPhotoPreveiwActivity;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.utils.d;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class c extends BaseExpandableListAdapter {
    private Context a;
    private DJILibraryFragment b;
    private dji.pilot2.library.b.a.b c;
    private ImageLoader d;
    private DisplayImageOptions e;
    private ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> f;
    private ArrayList<Integer> g;
    private ArrayList<String> h;
    private DJIScanerMediaManager i;
    private boolean j = false;
    private d k;
    private int l = 0;
    private final String m = "LibMediaAdapter";
    private int n;
    private HashMap<Integer, ArrayList<Integer>> o;
    private HashMap<Integer, ArrayList<Integer>> p;
    private HashMap<Integer, View> q;
    private HashMap<Integer, HashMap<Integer, b>> r;

    class a {
        public DJITextView a;
        public ImageView b;
        final /* synthetic */ c c;

        a(c cVar) {
            this.c = cVar;
        }
    }

    class b {
        public int a;
        public DJIRelativeLayout b = null;
        public TextView c;
        public ImageView d;
        public ImageView e;
        public View f;
        public View g;
        public boolean h;
        public ImageView i;
        final /* synthetic */ c j;

        b(c cVar) {
            this.j = cVar;
        }
    }

    public c(Context context) {
        this.a = context;
        this.f = new ArrayList();
        this.g = new ArrayList();
        this.o = new HashMap();
        this.q = new HashMap();
        this.r = new HashMap();
        this.p = new HashMap();
        this.d = ImageLoader.getInstance();
        this.k = new d(this.a);
        this.h = new ArrayList();
        this.i = DJIScanerMediaManager.getInstance(this.a);
        Builder builder = new Builder();
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        builder.showImageOnLoading(R.drawable.v2_photo_defalut).cacheInMemory(true).cacheOnDisc(true).decodingOptions(options);
        this.e = builder.build();
        WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.n = 6;
            this.l = (i - a(this.a, 10.0f)) / 6;
            return;
        }
        this.n = 3;
        this.l = (i - a(this.a, 10.0f)) / 3;
    }

    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + dji.pilot.visual.a.d.c);
    }

    public void a(DJILibraryFragment dJILibraryFragment) {
        this.b = dJILibraryFragment;
    }

    public void a(dji.pilot2.library.b.a.b bVar) {
        this.c = bVar;
    }

    public void a(boolean z) {
        this.j = z;
    }

    public void a(ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> arrayList) {
        this.f = arrayList;
    }

    public void a() {
        notifyDataSetChanged();
    }

    public int getGroupCount() {
        return this.f != null ? this.f.size() : 0;
    }

    public int getChildrenCount(int i) {
        if (this.f == null || this.f.size() <= i || ((dji.pilot2.library.model.DJIScanerMediaManager.a) this.f.get(i)).d == null) {
            return 0;
        }
        int size = ((dji.pilot2.library.model.DJIScanerMediaManager.a) this.f.get(i)).d.size();
        return size == 0 ? 0 : ((size - 1) / this.n) + 1;
    }

    public Object getGroup(int i) {
        return (this.f == null || this.f.size() <= i) ? null : (dji.pilot2.library.model.DJIScanerMediaManager.a) this.f.get(i);
    }

    public Object getChild(int i, int i2) {
        return (this.f == null || this.f.size() <= i) ? null : (dji.pilot2.library.model.DJIScanerMediaManager.a) this.f.get(i2);
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

    public View getGroupView(final int i, boolean z, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a(this);
            view = LayoutInflater.from(this.a).inflate(R.layout.v2_library_inputfragment_item, null);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a = (DJITextView) view.findViewById(R.id.cq6);
        if (this.j) {
            aVar.a.setText(this.a.getResources().getString(R.string.v2_lib_managevideo));
        } else {
            try {
                long currentTimeMillis = System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd").parse(((dji.pilot2.library.model.DJIScanerMediaManager.a) this.f.get(i)).b).getTime();
                if (currentTimeMillis < 86400000) {
                    aVar.a.setText(R.string.v2_library_date_today);
                } else if (currentTimeMillis < 172800000) {
                    aVar.a.setText(R.string.v2_library_date_yestoday);
                } else {
                    aVar.a.setText(((dji.pilot2.library.model.DJIScanerMediaManager.a) this.f.get(i)).b);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        aVar.b = (ImageView) view.findViewById(R.id.cq5);
        if (this.g.size() > 0) {
            for (int i2 = 0; i2 < this.g.size(); i2++) {
                if (i == ((Integer) this.g.get(i2)).intValue()) {
                    aVar.b.setBackground(this.a.getResources().getDrawable(R.drawable.v2_library_itemselect_true));
                    break;
                }
                aVar.b.setBackground(this.a.getResources().getDrawable(R.drawable.v2_library_itemselectnew_false));
            }
        } else {
            aVar.b.setBackground(this.a.getResources().getDrawable(R.drawable.v2_library_itemselectnew_false));
        }
        aVar.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ c b;

            public void onClick(View view) {
                boolean z;
                if (this.b.g.size() > 0) {
                    for (int i = 0; i < this.b.g.size(); i++) {
                        if (i == ((Integer) this.b.g.get(i)).intValue()) {
                            view.setBackground(this.b.a.getResources().getDrawable(R.drawable.v2_library_itemselectnew_false));
                            this.b.c.a(this.b.d(i), -1, true);
                            this.b.a(i);
                            this.b.g.remove(Integer.valueOf(i));
                            z = true;
                            break;
                        }
                    }
                }
                z = false;
                if (!z) {
                    this.b.g.add(Integer.valueOf(i));
                    view.setBackground(this.b.a.getResources().getDrawable(R.drawable.v2_library_itemselect_true));
                    this.b.b(i);
                    this.b.c.a(this.b.d(i), -1, false);
                }
                this.b.i.updateSelectedPaths(this.b.h);
            }
        });
        view.setTag(aVar);
        this.q.put(Integer.valueOf(i), view);
        return view;
    }

    private void a(b bVar, DJIRelativeLayout dJIRelativeLayout) {
        bVar.b = dJIRelativeLayout;
        bVar.d = (ImageView) dJIRelativeLayout.findViewById(R.id.cq3);
        bVar.c = (TextView) dJIRelativeLayout.findViewById(R.id.cq4);
        bVar.e = (ImageView) dJIRelativeLayout.findViewById(R.id.cq2);
        bVar.f = dJIRelativeLayout.findViewById(R.id.cps);
        bVar.g = dJIRelativeLayout.findViewById(R.id.cpu);
        bVar.i = (ImageView) dJIRelativeLayout.findViewById(R.id.cq1);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.l, this.l);
        bVar.e.setLayoutParams(layoutParams);
        bVar.i.setLayoutParams(layoutParams);
    }

    private int c(int i) {
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

    private ArrayList<Integer> d(int i) {
        if (this.o.get(Integer.valueOf(i)) != null) {
            return (ArrayList) this.o.get(Integer.valueOf(i));
        }
        ArrayList<Integer> arrayList = new ArrayList();
        this.o.put(Integer.valueOf(i), arrayList);
        return arrayList;
    }

    private ArrayList<Integer> e(int i) {
        if (this.p.get(Integer.valueOf(i)) != null) {
            return (ArrayList) this.p.get(Integer.valueOf(i));
        }
        ArrayList<Integer> arrayList = new ArrayList();
        this.p.put(Integer.valueOf(i), arrayList);
        return arrayList;
    }

    private HashMap<Integer, b> f(int i) {
        if (this.r.get(Integer.valueOf(i)) != null) {
            return (HashMap) this.r.get(Integer.valueOf(i));
        }
        HashMap<Integer, b> hashMap = new HashMap();
        this.r.put(Integer.valueOf(i), hashMap);
        return hashMap;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        b[] bVarArr;
        final ArrayList arrayList = ((dji.pilot2.library.model.DJIScanerMediaManager.a) this.f.get(i)).d;
        final ArrayList d = d(i);
        final ArrayList e = e(i);
        HashMap f = f(i);
        if (view == null) {
            View inflate = LayoutInflater.from(this.a).inflate(R.layout.v2_library_group_adapter_item, null);
            Object obj = new b[this.n];
            for (int i3 = 0; i3 < this.n; i3++) {
                obj[i3] = new b(this);
                a(obj[i3], (DJIRelativeLayout) inflate.findViewById(c(i3)));
            }
            ((LinearLayout) inflate).setWeightSum((float) this.n);
            inflate.setTag(obj);
            bVarArr = obj;
            view = inflate;
        } else {
            bVarArr = (b[]) view.getTag();
        }
        for (int i4 = 0; i4 < this.n; i4++) {
            bVarArr[i4].b.hide();
            bVarArr[i4].a = i;
        }
        if (this.f != null && this.f.size() > i) {
            int i5 = this.n * i2;
            int i6 = 0;
            while (i5 + i6 < arrayList.size() && i6 < this.n) {
                final b bVar = bVarArr[i6];
                final dji.pilot2.library.model.DJIScanerMediaManager.c cVar = (dji.pilot2.library.model.DJIScanerMediaManager.c) arrayList.get(i6 + i5);
                bVar.b.show();
                if (cVar.d != 0) {
                    CharSequence charSequence;
                    String str = "";
                    if (this.j) {
                        charSequence = (String) DateFormat.format("mm:ss", Long.valueOf((long) (cVar.d * 1000)).longValue());
                    } else {
                        str = (String) DateFormat.format("mm:ss", Long.valueOf((long) cVar.d).longValue());
                    }
                    bVar.c.setText(charSequence);
                } else {
                    bVar.c.setVisibility(8);
                }
                final int i7 = i2;
                final int i8 = i;
                bVar.g.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c h;

                    public void onClick(View view) {
                        boolean z;
                        if (d.size() > 0) {
                            z = false;
                            for (int i = 0; i < d.size(); i++) {
                                if (cVar.a == ((Integer) d.get(i)).intValue()) {
                                    d.remove(Integer.valueOf(cVar.a));
                                    this.h.h.remove(cVar.b);
                                    bVar.d.setBackground(this.h.a.getResources().getDrawable(R.drawable.v2_library_itemselect_false));
                                    this.h.c.a(null, Integer.valueOf(cVar.a).intValue(), true);
                                    this.h.k.a(bVar.e, this.h.l, this.h.l);
                                    bVar.h = false;
                                    e.remove(Integer.valueOf(i7));
                                    z = true;
                                }
                            }
                        } else {
                            z = false;
                        }
                        if (!z) {
                            d.add(Integer.valueOf(cVar.a));
                            this.h.h.add(cVar.b);
                            e.add(Integer.valueOf(i7));
                            bVar.d.setBackground(this.h.a.getResources().getDrawable(R.drawable.v2_library_itemselect_true));
                            this.h.k.c(bVar.f);
                            this.h.k.d(bVar.e, this.h.l, this.h.l);
                            bVar.h = true;
                            this.h.c.a(null, Integer.valueOf(cVar.a).intValue(), false);
                        }
                        this.h.i.updateSelectedPaths(this.h.h);
                        this.h.a(z, i8, arrayList, d);
                    }
                });
                i7 = i2;
                i8 = i;
                bVar.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ c h;

                    public void onClick(View view) {
                        boolean z = false;
                        for (int i = 0; i < d.size(); i++) {
                            if (((Integer) d.get(i)).intValue() == cVar.a) {
                                z = true;
                            }
                        }
                        if (this.h.i.getFlagVideo()) {
                            String str;
                            MediaInfoBean originMediaInfo = this.h.i.getOriginMediaInfo(cVar.a, true);
                            if (this.h.j) {
                                str = cVar.b;
                            } else {
                                str = originMediaInfo.getAbsPath();
                            }
                            if (this.h.j) {
                                String[] strArr = new String[]{Uri.parse(cVar.b).getPath()};
                                Intent intent = new Intent(this.h.a, DJICutMomentActivity.class);
                                intent.putExtra(DJICutMomentActivity.K, strArr);
                                intent.putExtra(DJICutMomentActivity.M, true);
                                intent.setAction("android.intent.action.INSERT");
                                ((Activity) this.h.a).startActivityForResult(intent, 4097);
                                return;
                            }
                            DJIMomentPreveiwActivity.a(this.h.a, str, z, true, dji.pilot.publics.objects.a.a, new h(this) {
                                final /* synthetic */ AnonymousClass3 a;

                                {
                                    this.a = r1;
                                }

                                public void a(boolean z) {
                                    boolean z2 = true;
                                    if (z) {
                                        d.add(Integer.valueOf(cVar.a));
                                        this.a.h.h.add(cVar.b);
                                        bVar.d.setBackground(this.a.h.a.getResources().getDrawable(R.drawable.v2_library_itemselect_true));
                                        this.a.h.k.c(bVar.f);
                                        this.a.h.k.d(bVar.e, this.a.h.l, this.a.h.l);
                                        e.add(Integer.valueOf(i7));
                                        this.a.h.c.a(null, Integer.valueOf(cVar.a).intValue(), false);
                                    } else {
                                        d.remove(Integer.valueOf(cVar.a));
                                        this.a.h.h.remove(cVar.b);
                                        bVar.d.setBackground(this.a.h.a.getResources().getDrawable(R.drawable.v2_library_itemselect_false));
                                        this.a.h.k.a(bVar.e, this.a.h.l, this.a.h.l);
                                        e.remove(Integer.valueOf(i7));
                                        this.a.h.c.a(null, Integer.valueOf(cVar.a).intValue(), true);
                                    }
                                    c cVar = this.a.h;
                                    if (z) {
                                        z2 = false;
                                    }
                                    cVar.a(z2, i8, arrayList, d);
                                }
                            }, this.h.j);
                            return;
                        }
                        DJIPhotoPreveiwActivity.a(this.h.a, cVar.b, true, z, dji.pilot.publics.objects.a.a, new h(this) {
                            final /* synthetic */ AnonymousClass3 a;

                            {
                                this.a = r1;
                            }

                            public void a(boolean z) {
                                boolean z2 = true;
                                if (z) {
                                    d.add(Integer.valueOf(cVar.a));
                                    this.a.h.h.add(cVar.b);
                                    bVar.d.setBackground(this.a.h.a.getResources().getDrawable(R.drawable.v2_library_itemselect_true));
                                    this.a.h.k.c(bVar.f);
                                    this.a.h.k.d(bVar.e, this.a.h.l, this.a.h.l);
                                    e.add(Integer.valueOf(i7));
                                    this.a.h.c.a(null, Integer.valueOf(cVar.a).intValue(), false);
                                } else {
                                    d.remove(Integer.valueOf(cVar.a));
                                    this.a.h.h.remove(cVar.b);
                                    bVar.d.setBackground(this.a.h.a.getResources().getDrawable(R.drawable.v2_library_itemselect_false));
                                    this.a.h.k.a(bVar.e, this.a.h.l, this.a.h.l);
                                    e.remove(Integer.valueOf(i7));
                                    this.a.h.c.a(null, Integer.valueOf(cVar.a).intValue(), true);
                                }
                                c cVar = this.a.h;
                                if (z) {
                                    z2 = false;
                                }
                                cVar.a(z2, i8, arrayList, d);
                            }
                        });
                    }
                });
                if (!this.j) {
                    this.d.displayImage(a$e.a + cVar.b, bVar.e, this.e);
                } else if (cVar.c != null) {
                    this.d.displayImage(a$e.a + cVar.c, bVar.e);
                } else {
                    f.getInstance();
                    bVar.e.setImageBitmap(f.a(cVar.b, 1500000));
                }
                if (d.size() > 0) {
                    for (int i9 = 0; i9 < d.size(); i9++) {
                        if (((Integer) d.get(i9)).intValue() == cVar.a) {
                            bVar.d.setBackground(this.a.getResources().getDrawable(R.drawable.v2_library_itemselect_true));
                            this.k.b(bVar.e, this.l, this.l);
                            break;
                        }
                        bVar.d.setBackground(this.a.getResources().getDrawable(R.drawable.v2_library_itemselect_false));
                    }
                } else {
                    bVar.d.setBackground(this.a.getResources().getDrawable(R.drawable.v2_library_itemselect_false));
                    this.k.c(bVar.e, this.l, this.l);
                }
                if (f.get(Integer.valueOf(i5 + i6)) != null) {
                    f.remove(Integer.valueOf(i5 + i6));
                }
                f.put(Integer.valueOf(i5 + i6), bVar);
                i6++;
            }
        }
        return view;
    }

    public void a(int i) {
        ArrayList d = d(i);
        ArrayList e = e(i);
        HashMap f = f(i);
        d.clear();
        this.h.clear();
        e.clear();
        for (int i2 = 0; i2 < f.size(); i2++) {
            b bVar = (b) f.get(Integer.valueOf(i2));
            if (bVar != null && bVar.a == i) {
                this.k.a(bVar.e, this.l, this.l);
                bVar.d.setBackgroundDrawable(this.a.getResources().getDrawable(R.drawable.v2_library_itemselect_false));
            }
        }
    }

    public void b(int i) {
        int i2 = 0;
        ArrayList d = d(i);
        ArrayList e = e(i);
        HashMap f = f(i);
        ArrayList arrayList = ((dji.pilot2.library.model.DJIScanerMediaManager.a) this.f.get(i)).d;
        d.clear();
        this.h.clear();
        for (int i3 = 0; i3 < f.size(); i3++) {
            int i4;
            b bVar = (b) f.get(Integer.valueOf(i3));
            if (e.size() != 0) {
                for (int i5 = 0; i5 < e.size(); i5++) {
                    if (((Integer) e.get(i5)).intValue() == i3) {
                        i4 = 1;
                        break;
                    }
                }
            }
            i4 = 0;
            if (i4 == 0 && bVar != null && bVar.a == i) {
                this.k.d(bVar.e, this.l, this.l);
                bVar.d.setBackgroundDrawable(this.a.getResources().getDrawable(R.drawable.v2_library_itemselect_true));
            }
        }
        e.clear();
        while (i2 < arrayList.size()) {
            d.add(Integer.valueOf(((dji.pilot2.library.model.DJIScanerMediaManager.c) arrayList.get(i2)).a));
            this.h.add(((dji.pilot2.library.model.DJIScanerMediaManager.c) arrayList.get(i2)).b);
            e.add(Integer.valueOf(i2));
            i2++;
        }
    }

    private void a(boolean z, int i, ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.c> arrayList, ArrayList<Integer> arrayList2) {
        View findViewById = ((View) this.q.get(Integer.valueOf(i))).findViewById(R.id.cq5);
        if (arrayList2.size() == arrayList.size()) {
            this.g.add(Integer.valueOf(i));
            findViewById.setBackground(this.a.getResources().getDrawable(R.drawable.v2_library_itemselect_true));
            return;
        }
        for (int i2 = 0; i2 < this.g.size(); i2++) {
            if (((Integer) this.g.get(i2)).intValue() == i && z) {
                this.g.remove(Integer.valueOf(i));
                findViewById.setBackground(this.a.getResources().getDrawable(R.drawable.v2_library_itemselect_false));
                return;
            }
        }
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isChildSelectable(int i, int i2) {
        return false;
    }
}
