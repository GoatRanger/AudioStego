package dji.pilot.fpv.flightmode.a;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot.fpv.flightmode.c;
import dji.pilot.fpv.flightmode.c$a;
import dji.pilot.fpv.flightmode.c$b;
import dji.pilot.fpv.flightmode.c$d;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.util.ArrayList;
import java.util.List;

public class a extends PagerAdapter {
    private static final int[] b = new int[]{R.drawable.selector_flightmode_normal, R.drawable.selector_flightmode_point, R.drawable.selector_flightmode_track, R.drawable.selector_flightmode_selfie_track, R.drawable.gs_menu_point_of_insterest_bg, R.drawable.gs_menu_follow_me_bg, R.drawable.gs_menu_way_point_bg, R.drawable.gs_menu_home_lock_bg, R.drawable.gs_menu_course_lock_bg, R.drawable.gs_menu_terrain_tracking_bg, R.drawable.gs_menu_pano_icon};
    private static final int[] c = new int[]{R.string.fpv_flight_mode_normal, R.string.fpv_flight_mode_point, R.string.fpv_flight_mode_track, R.string.fpv_flight_mode_gesture, R.string.gs_point_of_insterest_menu, R.string.gs_follow_me, R.string.gs_way_point_menu, R.string.gs_home_lock_menu, R.string.gs_course_lock_menu, R.string.gs_terrain_tracking_menu, R.string.gs_pano_menu};
    private static final c$b[] d = new c$b[]{c$b.NORMAL, c$b.POINT, c$b.TRACK, c$b.TRACK_SELFIE};
    private static final c$d[] e = new c$d[]{c$d.POI_AUTO, c$d.FOLLOW_ME, c$d.WP_AUTO, c$d.HOME_LOCK, c$d.COURSE_LOCK, c$d.TERRAIN_TRACKING, c$d.PANO};
    private static final int f = 6;
    public int a = 0;
    private Context g;
    private List<View> h = new ArrayList();

    public class a extends BaseAdapter {
        final /* synthetic */ a a;
        private Context b;
        private List<b> c;

        public class a {
            public ImageView a;
            public TextView b;
            public c$a c = c$b.NONE;
            final /* synthetic */ a d;

            public a(a aVar) {
                this.d = aVar;
            }
        }

        public a(a aVar, Context context, List<b> list) {
            this.a = aVar;
            this.b = context;
            this.c = list;
        }

        public int getCount() {
            return this.c.size();
        }

        public Object getItem(int i) {
            return this.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                view = LayoutInflater.from(this.b).inflate(R.layout.flightmode_view_content_item, null);
                aVar = new a(this);
                aVar.a = (ImageView) view.findViewById(R.id.tf);
                aVar.b = (TextView) view.findViewById(R.id.tg);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.b.setText(((b) this.c.get(i)).b);
            aVar.a.setImageResource(((b) this.c.get(i)).a);
            aVar.c = ((b) this.c.get(i)).c;
            Object a = c.getInstance().a();
            if (a.equals(c$b.SMART)) {
                a = c.getInstance().c();
            }
            if (a.equals(aVar.c)) {
                aVar.a.setSelected(true);
                aVar.b.setSelected(true);
            } else {
                aVar.a.setSelected(false);
                aVar.b.setSelected(false);
            }
            if (aVar.c.equals(c$d.PANO)) {
                view.setVisibility(8);
            }
            return view;
        }
    }

    public class b {
        public int a;
        public int b;
        public c$a c = c$b.NONE;
        final /* synthetic */ a d;

        public b(a aVar) {
            this.d = aVar;
        }
    }

    public a(Context context, OnItemClickListener onItemClickListener) {
        int i = 0;
        this.g = context;
        this.a = (int) Math.ceil(((double) b.length) / 6.0d);
        while (i < this.a) {
            GridView gridView = new GridView(context);
            gridView.setLayoutParams(new LayoutParams(-1, -1));
            gridView.setNumColumns(3);
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
                gridView.setNumColumns(6);
            }
            gridView.setVerticalSpacing(this.g.getResources().getDimensionPixelOffset(R.dimen.gh));
            List arrayList = new ArrayList();
            int i2 = i * 6;
            while (i2 < (i + 1) * 6 && i2 < b.length) {
                b bVar = new b(this);
                bVar.a = b[i2];
                bVar.b = c[i2];
                if (i2 < d.length) {
                    bVar.c = d[i2];
                } else {
                    bVar.c = e[i2 - d.length];
                }
                arrayList.add(bVar);
                i2++;
            }
            gridView.setAdapter(new a(this, this.g, arrayList));
            gridView.setOnItemClickListener(onItemClickListener);
            this.h.add(gridView);
            i++;
        }
    }

    public void a() {
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.a;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View view = (View) this.h.get(i);
        viewGroup.addView(view);
        return view;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) this.h.get(i));
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }
}
