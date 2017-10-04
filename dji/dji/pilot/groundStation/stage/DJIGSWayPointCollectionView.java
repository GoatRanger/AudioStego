package dji.pilot.groundStation.stage;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.dji.frame.c.l;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.fpv.d.c$i;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.model.b;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.groundStation.db.DJIWPCollectionItem;
import dji.pilot.groundStation.db.DJIWPCollectionItem$WayPoint;
import dji.pilot.usercenter.protocol.d;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class DJIGSWayPointCollectionView extends DJILinearLayout implements c$i, dji.pilot.fpv.view.DJIStageView.a {
    private ListView n = null;
    private DJITextView o = null;
    private DJITextView p = null;
    private DJITextView q = null;
    private DJITextView r = null;
    private OnClickListener s = new OnClickListener(this) {
        final /* synthetic */ DJIGSWayPointCollectionView a;

        {
            this.a = r1;
        }

        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.aoc:
                    dji.pilot.groundStation.a.a.getInstance(null).i().l().g();
                    dji.pilot.groundStation.a.a.getInstance(null).M();
                    ((DJIStageView) this.a.getParent()).destoryStageView(true);
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_way_point_view, 5, true);
                    return;
                case R.id.aoi:
                    e.c(c$i.j);
                    dji.pilot.groundStation.a.a.getInstance(this.a.getContext()).c(dji.pilot.groundStation.a.a.getInstance(this.a.getContext()).c(this.a.t.a()));
                    dji.pilot.groundStation.a.a.getInstance(this.a.getContext()).d(true);
                    ((DJIStageView) this.a.getParent()).createStageView(R.layout.gs_way_point_auto_fly_setting_view, 20, true);
                    return;
                default:
                    return;
            }
        }
    };
    private a t = null;

    private class a extends BaseAdapter {
        final /* synthetic */ DJIGSWayPointCollectionView a;
        private int b = 0;

        public a(DJIGSWayPointCollectionView dJIGSWayPointCollectionView) {
            this.a = dJIGSWayPointCollectionView;
        }

        public void a(int i) {
            if (i >= 0 && i < dji.pilot.groundStation.a.a.getInstance(this.a.getContext()).h().size()) {
                this.b = i;
            }
        }

        public int a() {
            return this.b;
        }

        public int getCount() {
            return dji.pilot.groundStation.a.a.getInstance(this.a.getContext()).h().size();
        }

        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            List h = dji.pilot.groundStation.a.a.getInstance(this.a.getContext()).h();
            if (view == null) {
                view = LayoutInflater.from(this.a.getContext()).inflate(R.layout.gs_way_point_collection_item, null);
            }
            DJITextView dJITextView = (DJITextView) view.findViewById(R.id.aob);
            dJITextView.setEnabled(false);
            dJITextView.setClickable(false);
            if (i < h.size()) {
                DJIWPCollectionItem dJIWPCollectionItem = (DJIWPCollectionItem) h.get(i);
                if (dJIWPCollectionItem != null) {
                    CharSequence charSequence;
                    String charSequence2 = DateFormat.format("yyyy-MM-dd HH:mm", dJIWPCollectionItem.getCreatedDate()).toString();
                    String location = dJIWPCollectionItem.getLocation();
                    if (dJIWPCollectionItem.getAutoAddFlag() > 0) {
                        charSequence = charSequence2 + " [" + this.a.getContext().getString(R.string.gs_way_point_favorite_auto_add) + String.format("%d", new Object[]{Integer.valueOf(dJIWPCollectionItem.getAutoAddFlag())}) + d.H;
                    } else if (l.a(location)) {
                        Object obj = charSequence2;
                    } else {
                        charSequence = charSequence2 + " [" + location + d.H;
                    }
                    dJITextView.setText(charSequence);
                    if (this.b == i) {
                        dJITextView.setBackgroundColor(this.a.getContext().getResources().getColor(R.color.db));
                    } else {
                        dJITextView.setBackgroundColor(0);
                    }
                }
            }
            return view;
        }
    }

    public DJIGSWayPointCollectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        this.n.setSelection(0);
        this.t.a(0);
        this.t.notifyDataSetInvalidated();
        a(0);
        b(0);
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.o = (DJITextView) findViewById(R.id.aoe);
            this.p = (DJITextView) findViewById(R.id.aof);
            this.q = (DJITextView) findViewById(R.id.aog);
            this.r = (DJITextView) findViewById(R.id.aoh);
            findViewById(R.id.aoc).setOnClickListener(this.s);
            findViewById(R.id.aoi).setOnClickListener(this.s);
            this.n = (ListView) findViewById(R.id.aod);
            this.t = new a(this);
            this.n.setAdapter(this.t);
            this.n.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ DJIGSWayPointCollectionView a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    this.a.t.a(i);
                    this.a.t.notifyDataSetInvalidated();
                    this.a.a(i);
                    this.a.b(i);
                }
            });
            this.n.setOnItemLongClickListener(new OnItemLongClickListener(this) {
                final /* synthetic */ DJIGSWayPointCollectionView a;

                {
                    this.a = r1;
                }

                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long j) {
                    dji.pilot.groundStation.b.a aVar = new dji.pilot.groundStation.b.a(this.a.getContext(), b.a(this.a.getContext(), R.dimen.o6), b.a(this.a.getContext(), R.dimen.o6), false);
                    aVar.setTitle("");
                    aVar.d(-1);
                    aVar.a((int) R.string.gs_delete_confirm_desc);
                    aVar.b((int) R.string.gs_delete_confirm_left_btn);
                    aVar.c(R.string.gs_delete_confirm_right_btn);
                    aVar.b(new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass2 b;

                        public void onClick(View view) {
                            dji.pilot.groundStation.a.a.getInstance(this.b.a.getContext()).a(i);
                            this.b.a.t.notifyDataSetInvalidated();
                            if (dji.pilot.groundStation.a.a.getInstance(this.b.a.getContext()).h().size() > 0) {
                                this.b.a.a(0);
                                this.b.a.b(0);
                                return;
                            }
                            dji.pilot.groundStation.a.a.getInstance(null).i().l().g();
                            ((DJIStageView) this.b.a.getParent()).destoryStageView(true);
                        }
                    });
                    aVar.show();
                    return false;
                }
            });
        }
    }

    private void a(int i) {
        List h = dji.pilot.groundStation.a.a.getInstance(getContext()).h();
        if (h.size() > 0 && i < h.size()) {
            DJIWPCollectionItem dJIWPCollectionItem = (DJIWPCollectionItem) h.get(i);
            this.o.setText(DateFormat.format("yyyy/MM/dd-HH:mm", dJIWPCollectionItem.getCreatedDate()));
            this.r.setText(String.format("%d", new Object[]{Integer.valueOf(dJIWPCollectionItem.getPoints().size())}));
            if (!l.a(dJIWPCollectionItem.getLocation())) {
                this.p.setText(dJIWPCollectionItem.getLocation());
            } else if (dJIWPCollectionItem.getPoints().size() > 0) {
                this.p.setText(String.format("(%f, %f)", new Object[]{Double.valueOf(((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(0)).lat), Double.valueOf(((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(0)).lng)}));
            }
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.q.setText(String.format("%.1fFT", new Object[]{Float.valueOf(dji.pilot.groundStation.b.a((float) dJIWPCollectionItem.getDistance()))}));
                return;
            }
            this.q.setText(String.format("%.1fM", new Object[]{Double.valueOf(dJIWPCollectionItem.getDistance())}));
        }
    }

    private void b(int i) {
        int i2 = 1;
        dji.pilot.groundStation.a.a instance = dji.pilot.groundStation.a.a.getInstance(getContext());
        instance.i().l().g();
        List h = instance.h();
        if (h.size() > 0 && i < h.size()) {
            DJIWPCollectionItem dJIWPCollectionItem = (DJIWPCollectionItem) h.get(i);
            if (dJIWPCollectionItem != null && dJIWPCollectionItem.getPoints().size() > 1) {
                dji.gs.e.b a = dji.gs.utils.a.a(new dji.gs.e.b(((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(0)).getLat(), ((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(0)).getLng()));
                dji.gs.e.b bVar = new dji.gs.e.b(a.b, a.c);
                instance.i().l().g(a);
                while (i2 < dJIWPCollectionItem.getPoints().size()) {
                    dji.gs.e.b a2 = dji.gs.utils.a.a(new dji.gs.e.b(((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(i2)).getLat(), ((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(i2)).getLng()));
                    instance.i().l().g(a2);
                    if (a.b > a2.b) {
                        a.b = a2.b;
                    }
                    if (a.c > a2.c) {
                        a.c = a2.c;
                    }
                    if (bVar.b < a2.b) {
                        bVar.b = a2.b;
                    }
                    if (bVar.c < a2.c) {
                        bVar.c = a2.c;
                    }
                    i2++;
                }
                instance.i().a(a, bVar);
            }
        }
    }
}
