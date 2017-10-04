package dji.pilot2.mine.a;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Space;
import android.widget.TextView;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.pilot.R;
import dji.pilot.fpv.d.c$m;
import dji.pilot.fpv.d.e;
import dji.pilot2.mine.e.d;
import dji.pilot2.mine.view.FixRatioImageView;
import dji.pilot2.nativeexplore.activity.ArtworkDetailActivity;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class b extends BaseExpandableListAdapter implements c$m {
    public static final int G = 0;
    public static final int H = 1;
    private Context I;
    private List<dji.pilot2.mine.b.b> J;
    private dji.pilot2.mine.b.a K = null;
    private int L = 1;
    private volatile int M = 3;
    private b N = null;
    private dji.pilot2.mine.activity.ProfileTestActivity.a O;
    private boolean P;

    public class a {
        dji.pilot2.mine.e.a a;
        ImageView b;
        FixRatioImageView c;
        TextView d;
        ImageView e;
        TextView f;
        TextView g;
        final /* synthetic */ b h;

        public a(b bVar) {
            this.h = bVar;
        }

        public void a(int i, int i2, int i3) {
            String d = this.a.d();
            if (this.b instanceof FixRatioImageView) {
                FixRatioImageView fixRatioImageView = (FixRatioImageView) this.b;
                if (fixRatioImageView != null) {
                    if (i3 == 1) {
                        fixRatioImageView.setWeight(1.0f, 1.0f);
                        if (this.c != null) {
                            this.c.setWeight(1.0f, 1.0f);
                        }
                    } else {
                        fixRatioImageView.setWeight(dji.gs.e.b.a, 9.0f);
                        if (this.c != null) {
                            this.c.setWeight(dji.gs.e.b.a, 9.0f);
                        }
                    }
                }
            }
            this.b.setBackgroundResource(17170445);
            this.b.setImageBitmap(null);
            c.a(this.h.I).a(this.b, d);
            this.g.setVisibility(8);
            if (this.a instanceof d) {
                if ("compressing".equals(((d) this.a).r)) {
                    this.d.setVisibility(4);
                    this.e.setVisibility(0);
                    this.g.setVisibility(0);
                    this.b.setBackgroundColor(this.h.I.getResources().getColor(R.color.m9));
                } else {
                    this.d.setVisibility(0);
                    this.e.setVisibility(0);
                    this.d.setText(a(((d) this.a).i()));
                }
                if (this.c != null) {
                    this.c.setVisibility(0);
                }
            } else {
                this.d.setVisibility(8);
                this.e.setVisibility(8);
                if (this.c != null) {
                    this.c.setVisibility(4);
                }
            }
            if (i3 == 1) {
                this.f.setVisibility(8);
            } else {
                this.f.setVisibility(0);
                this.f.setText(a(this.a.c() * 1000));
            }
            this.b.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    e.b(c$m.eD_);
                    e.b(c$m.dt_);
                    this.a.a.e();
                    Intent intent = new Intent(this.a.h.I, ArtworkDetailActivity.class);
                    if (this.a.a instanceof d) {
                        intent.putExtra("duration", ((d) this.a.a).i());
                        intent.putExtra("type", "videos");
                    } else if (this.a.a instanceof dji.pilot2.mine.e.c) {
                        intent.putExtra("type", "photos");
                    }
                    intent.putExtra("id", this.a.a.b());
                    if (this.a.a.p != null) {
                        intent.putExtra(ArtworkDetailActivity.d, h.a(this.a.a.p));
                    }
                    this.a.h.I.startActivity(intent);
                }
            });
        }

        public String a(int i) {
            int i2 = i % 60;
            return String.format("%d:%02d", new Object[]{Integer.valueOf(i / 60), Integer.valueOf(i2)}).toLowerCase();
        }

        public String a(long j) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(j)).toString();
        }
    }

    public interface b {
        void a();
    }

    public b(Context context, List<dji.pilot2.mine.b.b> list) {
        this.I = context;
        this.J = list;
    }

    public void a(b bVar) {
        this.N = bVar;
    }

    public void a(dji.pilot2.mine.b.a aVar) {
        this.K = aVar;
    }

    public void a(int i, int i2) {
        if (this.L != i2) {
            this.M = i;
            if (i2 == 1) {
                c();
                return;
            }
            b();
            dji.pilot2.mine.b.b bVar = (dji.pilot2.mine.b.b) getGroup(0);
        }
    }

    public void a(dji.pilot2.mine.activity.ProfileTestActivity.a aVar) {
        this.O = aVar;
    }

    private synchronized void b() {
        if (this.L == 1) {
            this.L = 0;
            this.J.clear();
            a();
        }
    }

    private synchronized void c() {
        if (this.L == 0) {
            this.L = 1;
            this.J.clear();
            a();
        }
    }

    public void a(boolean z) {
        this.P = z;
    }

    public synchronized void a() {
        synchronized (this.K) {
            dji.pilot2.mine.b.b bVar;
            if (this.L == 1) {
                this.J.clear();
                for (dji.pilot2.mine.e.a aVar : this.K.h) {
                    long c = aVar.c() / 2592000;
                    int i = 0;
                    while (i < this.J.size()) {
                        if (((dji.pilot2.mine.b.b) this.J.get(i)).a() == c) {
                            ((dji.pilot2.mine.b.b) this.J.get(i)).a(aVar);
                            break;
                        }
                        i++;
                    }
                    if (i == this.J.size()) {
                        bVar = new dji.pilot2.mine.b.b();
                        bVar.a(c);
                        bVar.a(aVar);
                        this.J.add(bVar);
                        Collections.sort(this.J);
                    }
                    for (int i2 = 0; i2 < this.J.size(); i2++) {
                        if (this.J.get(i2) != null) {
                            ((dji.pilot2.mine.b.b) this.J.get(i2)).c();
                        }
                    }
                }
            } else {
                this.J.clear();
                if (this.J.size() <= 0) {
                    dji.pilot2.mine.b.b bVar2 = new dji.pilot2.mine.b.b();
                    this.J.add(bVar2);
                    bVar = bVar2;
                } else {
                    bVar = (dji.pilot2.mine.b.b) this.J.get(0);
                }
                for (dji.pilot2.mine.e.a aVar2 : this.K.h) {
                    bVar.a(aVar2);
                }
                bVar.c();
            }
        }
    }

    public void a(int i) {
        if (this.M != i) {
            this.M = i;
            notifyDataSetChanged();
        }
    }

    public int getGroupCount() {
        return this.J.size();
    }

    public int getChildrenCount(int i) {
        int d = ((dji.pilot2.mine.b.b) this.J.get(i)).d();
        int i2 = d / this.M;
        if (d % this.M != 0) {
            return i2 + 1;
        }
        return i2;
    }

    public Object getGroup(int i) {
        return this.J.get(i);
    }

    public Object getChild(int i, int i2) {
        return ((dji.pilot2.mine.b.b) this.J.get(i)).a(i2);
    }

    public long getGroupId(int i) {
        return ((dji.pilot2.mine.b.b) this.J.get(i)).a();
    }

    public long getChildId(int i, int i2) {
        return 0;
    }

    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        if (this.K.a() > 0 || this.O == null) {
            if (this.L == 0) {
                return new View(this.I);
            }
            if (view == null || !(view instanceof RelativeLayout)) {
                view = LayoutInflater.from(this.I).inflate(R.layout.v2_profile_group_layout, null);
            }
            TextView textView = (TextView) view.findViewById(R.id.cyg);
            long a = ((dji.pilot2.mine.b.b) getGroup(i)).a();
            if (textView == null) {
                return view;
            }
            textView.setText(new SimpleDateFormat("yyyy-MM").format(new Date(a * 2592000000L)).toString());
            return view;
        } else if (!this.P) {
            return this.O.a();
        } else {
            view = LayoutInflater.from(this.I).inflate(R.layout.v2_coupon_refreshheader, null);
            ((AnimationDrawable) ((ImageView) view.findViewById(R.id.cj8)).getDrawable()).start();
            return view;
        }
    }

    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(this.I).inflate(R.layout.v2_profile_child_layout, viewGroup, false);
        }
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.cy9);
        linearLayout.setWeightSum((float) this.M);
        View findViewById = view.findViewById(R.id.cya);
        View findViewById2 = view.findViewById(R.id.cyb);
        View findViewById3 = view.findViewById(R.id.cyc);
        Space space = (Space) view.findViewById(R.id.cy_);
        Space space2 = (Space) view.findViewById(R.id.cyd);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
            space.setVisibility(8);
            space2.setVisibility(8);
        } else if (this.L == 1) {
            space.setVisibility(8);
            space2.setVisibility(8);
        } else {
            linearLayout.setWeightSum(((float) this.M) + dji.pilot.visual.a.d.c);
            space.setVisibility(0);
            space2.setVisibility(0);
        }
        View findViewById4 = view.findViewById(R.id.cye);
        View findViewById5 = view.findViewById(R.id.cyf);
        if (z || this.L == 0) {
            findViewById4.setVisibility(0);
            findViewById5.setVisibility(0);
        } else {
            findViewById4.setVisibility(8);
            findViewById5.setVisibility(8);
        }
        List arrayList = new ArrayList();
        arrayList.add(findViewById);
        arrayList.add(findViewById2);
        arrayList.add(findViewById3);
        int d = ((dji.pilot2.mine.b.b) getGroup(i)).d();
        int i3 = i2 * this.M;
        for (int i4 = 0; i4 < 3; i4++) {
            if (i4 >= this.M) {
                ((View) arrayList.get(i4)).setVisibility(8);
            } else if (i3 + i4 < d) {
                ((View) arrayList.get(i4)).setVisibility(0);
                a((View) arrayList.get(i4), i, i3 + i4, this.L);
            } else {
                ((View) arrayList.get(i4)).setVisibility(8);
            }
        }
        return view;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (this.N != null) {
            this.N.a();
        }
    }

    public boolean isChildSelectable(int i, int i2) {
        return false;
    }

    private void a(View view, int i, int i2, int i3) {
        a aVar;
        a aVar2 = (a) view.getTag();
        if (aVar2 == null) {
            aVar = new a(this);
            aVar.b = (ImageView) view.findViewById(R.id.cy6);
            aVar.c = (FixRatioImageView) view.findViewById(R.id.cyj);
            aVar.d = (TextView) view.findViewById(R.id.cy7);
            aVar.e = (ImageView) view.findViewById(R.id.cyk);
            aVar.f = (TextView) view.findViewById(R.id.cyh);
            aVar.g = (TextView) view.findViewById(R.id.cuu);
            view.setTag(aVar);
        } else {
            aVar = aVar2;
        }
        aVar.a = ((dji.pilot2.mine.b.b) getGroup(i)).a(i2);
        aVar.a(i, i2, i3);
    }
}
