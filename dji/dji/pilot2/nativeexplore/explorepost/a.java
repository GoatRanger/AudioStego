package dji.pilot2.nativeexplore.explorepost;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
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
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.library.model.DJIScanerMediaManager.c;
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

public class a extends BaseExpandableListAdapter {
    private Context a;
    private dji.pilot2.library.b.a.b b;
    private ImageLoader c;
    private DisplayImageOptions d;
    private ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> e;
    private ArrayList<Integer> f;
    private ArrayList<String> g;
    private boolean h = false;
    private d i;
    private int j = 0;
    private final String k = "LibMediaAdapter";
    private int l;
    private HashMap<Integer, ArrayList<Integer>> m;
    private HashMap<Integer, ArrayList<Integer>> n;
    private HashMap<Integer, View> o;
    private HashMap<Integer, HashMap<Integer, b>> p;

    class a {
        public DJITextView a;
        public ImageView b;
        final /* synthetic */ a c;

        a(a aVar) {
            this.c = aVar;
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
        final /* synthetic */ a j;

        b(a aVar) {
            this.j = aVar;
        }
    }

    public a(Context context) {
        this.a = context;
        this.e = new ArrayList();
        this.f = new ArrayList();
        this.m = new HashMap();
        this.o = new HashMap();
        this.p = new HashMap();
        this.n = new HashMap();
        this.c = ImageLoader.getInstance();
        this.i = new d(this.a);
        this.g = new ArrayList();
        Builder builder = new Builder();
        Options options = new Options();
        options.inPreferredConfig = Config.RGB_565;
        builder.showImageOnLoading(R.drawable.v2_photo_defalut).cacheInMemory(true).cacheOnDisc(true).decodingOptions(options);
        this.d = builder.build();
        WindowManager windowManager = (WindowManager) this.a.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.l = 6;
            this.j = (i - a(this.a, 10.0f)) / 6;
            return;
        }
        this.l = 3;
        this.j = (i - a(this.a, 10.0f)) / 3;
    }

    public static int a(Context context, float f) {
        return (int) ((context.getResources().getDisplayMetrics().density * f) + dji.pilot.visual.a.d.c);
    }

    public void a(dji.pilot2.library.b.a.b bVar) {
        this.b = bVar;
    }

    public void a(ArrayList<dji.pilot2.library.model.DJIScanerMediaManager.a> arrayList) {
        DJILogHelper.getInstance().LOGI("bob", "setGroupInfos infolist.size=" + arrayList.size());
        this.e = arrayList;
        notifyDataSetChanged();
    }

    public int getGroupCount() {
        return this.e != null ? this.e.size() : 0;
    }

    public int getChildrenCount(int i) {
        if (this.e == null || this.e.size() <= i || ((dji.pilot2.library.model.DJIScanerMediaManager.a) this.e.get(i)).d == null) {
            return 0;
        }
        int size = ((dji.pilot2.library.model.DJIScanerMediaManager.a) this.e.get(i)).d.size();
        return size == 0 ? 0 : ((size - 1) / this.l) + 1;
    }

    public Object getGroup(int i) {
        return (this.e == null || this.e.size() <= i) ? null : (dji.pilot2.library.model.DJIScanerMediaManager.a) this.e.get(i);
    }

    public Object getChild(int i, int i2) {
        return (this.e == null || this.e.size() <= i) ? null : (dji.pilot2.library.model.DJIScanerMediaManager.a) this.e.get(i2);
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

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            a aVar2 = new a(this);
            view = LayoutInflater.from(this.a).inflate(R.layout.v2_library_inputfragment_item, null);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.a = (DJITextView) view.findViewById(R.id.cq6);
        try {
            long currentTimeMillis = System.currentTimeMillis() - new SimpleDateFormat("yyyy-MM-dd").parse(((dji.pilot2.library.model.DJIScanerMediaManager.a) this.e.get(i)).b).getTime();
            if (currentTimeMillis < 86400000) {
                aVar.a.setText(R.string.v2_library_date_today);
            } else if (currentTimeMillis < 172800000) {
                aVar.a.setText(R.string.v2_library_date_yestoday);
            } else {
                aVar.a.setText(((dji.pilot2.library.model.DJIScanerMediaManager.a) this.e.get(i)).b);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        aVar.b = (ImageView) view.findViewById(R.id.cq5);
        aVar.b.setVisibility(8);
        view.setTag(aVar);
        this.o.put(Integer.valueOf(i), view);
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
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.j, this.j);
        bVar.e.setLayoutParams(layoutParams);
        bVar.i.setLayoutParams(layoutParams);
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

    private ArrayList<Integer> c(int i) {
        if (this.m.get(Integer.valueOf(i)) != null) {
            return (ArrayList) this.m.get(Integer.valueOf(i));
        }
        ArrayList<Integer> arrayList = new ArrayList();
        this.m.put(Integer.valueOf(i), arrayList);
        return arrayList;
    }

    private ArrayList<Integer> d(int i) {
        if (this.n.get(Integer.valueOf(i)) != null) {
            return (ArrayList) this.n.get(Integer.valueOf(i));
        }
        ArrayList<Integer> arrayList = new ArrayList();
        this.n.put(Integer.valueOf(i), arrayList);
        return arrayList;
    }

    private HashMap<Integer, b> e(int i) {
        if (this.p.get(Integer.valueOf(i)) != null) {
            return (HashMap) this.p.get(Integer.valueOf(i));
        }
        HashMap<Integer, b> hashMap = new HashMap();
        this.p.put(Integer.valueOf(i), hashMap);
        return hashMap;
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        b[] bVarArr;
        ArrayList arrayList = ((dji.pilot2.library.model.DJIScanerMediaManager.a) this.e.get(i)).d;
        c(i);
        d(i);
        HashMap e = e(i);
        if (view == null) {
            View inflate = LayoutInflater.from(this.a).inflate(R.layout.v2_library_group_adapter_item, null);
            Object obj = new b[this.l];
            for (int i3 = 0; i3 < this.l; i3++) {
                obj[i3] = new b(this);
                a(obj[i3], (DJIRelativeLayout) inflate.findViewById(b(i3)));
            }
            ((LinearLayout) inflate).setWeightSum((float) this.l);
            inflate.setTag(obj);
            bVarArr = obj;
            view = inflate;
        } else {
            bVarArr = (b[]) view.getTag();
        }
        for (int i4 = 0; i4 < this.l; i4++) {
            bVarArr[i4].b.hide();
            bVarArr[i4].a = i;
            bVarArr[i4].d.setVisibility(8);
            bVarArr[i4].g.setVisibility(8);
        }
        if (this.e != null && this.e.size() > i) {
            int i5 = this.l * i2;
            int i6 = 0;
            while (i5 + i6 < arrayList.size() && i6 < this.l) {
                b bVar = bVarArr[i6];
                final c cVar = (c) arrayList.get(i6 + i5);
                bVar.b.show();
                if (cVar.d != 0) {
                    String str = "";
                    bVar.c.setText((String) DateFormat.format("mm:ss", Long.valueOf((long) cVar.d).longValue()));
                } else {
                    bVar.c.setVisibility(8);
                }
                bVar.e.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a b;

                    public void onClick(View view) {
                        DJIPhotoPreveiwActivity.a(this.b.a, cVar.b, true, dji.pilot.publics.objects.a.a);
                    }
                });
                this.c.displayImage(a$e.a + cVar.b, bVar.e, this.d);
                if (e.get(Integer.valueOf(i5 + i6)) != null) {
                    e.remove(Integer.valueOf(i5 + i6));
                }
                e.put(Integer.valueOf(i5 + i6), bVar);
                i6++;
            }
        }
        return view;
    }

    public void a(int i) {
        ArrayList c = c(i);
        ArrayList d = d(i);
        HashMap e = e(i);
        c.clear();
        this.g.clear();
        d.clear();
        for (int i2 = 0; i2 < e.size(); i2++) {
            b bVar = (b) e.get(Integer.valueOf(i2));
            if (bVar != null && bVar.a == i) {
                this.i.a(bVar.e, this.j, this.j);
                bVar.d.setBackgroundDrawable(this.a.getResources().getDrawable(R.drawable.v2_library_itemselect_false));
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
