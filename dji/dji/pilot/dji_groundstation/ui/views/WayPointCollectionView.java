package dji.pilot.dji_groundstation.ui.views;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem$WayPoint;
import dji.pilot.dji_groundstation.controller.DataMgr.e;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.usercenter.protocol.d;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;

public class WayPointCollectionView extends DJILinearLayout {
    private static final String a = "WayPointCollectionView";
    private ListView b = null;
    private DJITextView c = null;
    private DJITextView d = null;
    private DJITextView e = null;
    private DJITextView f = null;
    private a g = null;
    private WayPointCollectionDeleteView h = null;

    private class a extends BaseAdapter {
        final /* synthetic */ WayPointCollectionView a;
        private int b = 0;

        public a(WayPointCollectionView wayPointCollectionView) {
            this.a = wayPointCollectionView;
            a(this.b);
        }

        public int getCount() {
            int k = e.getInstance().k();
            if (k == 0) {
                e.getInstance().f(null);
                e.getInstance().d(null);
            }
            return k;
        }

        public Object getItem(int i) {
            return e.getInstance().d(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public void a(int i) {
            this.b = i;
            Object item = getItem(i);
            if (item != null && (item instanceof DJIWPCollectionItem)) {
                notifyDataSetChanged();
                DJIWPCollectionItem dJIWPCollectionItem = (DJIWPCollectionItem) item;
                this.a.b(dJIWPCollectionItem);
                this.a.a(dJIWPCollectionItem);
                e.getInstance().d(dJIWPCollectionItem);
                e.getInstance().f(dJIWPCollectionItem);
            }
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (i < getCount()) {
                TextView textView;
                if (view == null) {
                    textView = new TextView(this.a.getContext());
                    textView.setLayoutParams(new LayoutParams(-1, (int) f.a(45.0d, this.a.getContext())));
                    textView.setTextColor(-1);
                    textView.setTextSize(1, 12.0f);
                    textView.setGravity(19);
                    textView.setPadding((int) f.a(10.0d, this.a.getContext()), 0, 0, 0);
                    view = textView;
                } else {
                    textView = (TextView) view;
                }
                textView.setLongClickable(false);
                String str = "";
                DJIWPCollectionItem d = e.getInstance().d(i);
                if (d != null) {
                    CharSequence charSequence = DateFormat.format("yyyy-MM-dd HH:mm", d.getCreatedDate()).toString();
                    String location = d.getLocation();
                    if (d.getAutoAddFlag() > 0) {
                        charSequence = charSequence + d.G + this.a.getContext().getString(R.string.gsnew_gs_way_point_favorite_auto_add) + String.format("%d", new Object[]{Integer.valueOf(d.getAutoAddFlag())}) + d.H;
                    } else if (!(location == null || location.trim().isEmpty())) {
                        charSequence = charSequence + d.G + location + d.H;
                    }
                    textView.setText(charSequence);
                    if (this.b == i) {
                        textView.setBackgroundColor(this.a.getContext().getResources().getColor(R.color.battery_content_bg));
                    } else {
                        textView.setBackgroundColor(0);
                    }
                }
            }
            return view;
        }
    }

    public WayPointCollectionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setOrientation(0);
        LayoutInflater.from(context).inflate(R.layout.view_waypoint_collection, this);
        if (!c.a().c(this)) {
            c.a().a(this);
        }
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.b = (ListView) findViewById(R.id.gs_way_point_collection_listview);
        this.c = (DJITextView) findViewById(R.id.gs_way_point_collection_created_time);
        this.d = (DJITextView) findViewById(R.id.gs_way_point_collection_location);
        this.e = (DJITextView) findViewById(R.id.gs_way_point_collection_distance);
        this.f = (DJITextView) findViewById(R.id.gs_way_point_collection_nodes);
        this.h = (WayPointCollectionDeleteView) findViewById(R.id.itemdeletebtn);
        a();
    }

    public void onEventMainThread(dji.pilot.dji_groundstation.a.e eVar) {
        if (eVar != null && eVar.s == 23 && this.g != null) {
            this.g.notifyDataSetChanged();
            if (this.g.getCount() <= 0) {
                this.c.setText(R.string.gsnew_fpv_default_str);
                this.f.setText(R.string.gsnew_fpv_default_str);
                this.d.setText(R.string.gsnew_fpv_default_str);
                this.e.setText(R.string.gsnew_fpv_default_str);
                e.getInstance().d(null);
            }
        }
    }

    private void a() {
        this.g = new a(this);
        this.b.setAdapter(this.g);
        this.b.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ WayPointCollectionView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                this.a.g.a(i);
            }
        });
        this.b.setOnItemSelectedListener(new OnItemSelectedListener(this) {
            final /* synthetic */ WayPointCollectionView a;

            {
                this.a = r1;
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            }

            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WayPointCollectionView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.deleteItem((DJIWPCollectionItem) this.a.g.getItem(this.a.g.b));
            }
        });
    }

    private void a(DJIWPCollectionItem dJIWPCollectionItem) {
        if (dJIWPCollectionItem != null) {
            dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 1;
            c.a().e(eVar);
            eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 2;
            eVar.t = dJIWPCollectionItem;
            c.a().e(eVar);
        }
    }

    private void b(DJIWPCollectionItem dJIWPCollectionItem) {
        if (dJIWPCollectionItem != null) {
            this.c.setText(DateFormat.format("yyyy/MM/dd-HH:mm", dJIWPCollectionItem.getCreatedDate()));
            this.f.setText(Integer.toString(dJIWPCollectionItem.getPoints().size()));
            if (dJIWPCollectionItem.getLocation() != null && !dJIWPCollectionItem.getLocation().trim().isEmpty()) {
                this.d.setText(dJIWPCollectionItem.getLocation());
            } else if (dJIWPCollectionItem.getPoints().size() > 0) {
                this.d.setText(String.format("(%f, %f)", new Object[]{Double.valueOf(((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(0)).lat), Double.valueOf(((DJIWPCollectionItem$WayPoint) dJIWPCollectionItem.getPoints().get(0)).lng)}));
            }
            if (DJIGenSettingDataManager.getInstance().v() == 0) {
                this.e.setText(String.format("%.1fFT", new Object[]{Float.valueOf(f.a((float) dJIWPCollectionItem.getDistance()))}));
                return;
            }
            this.e.setText(String.format("%.1fM", new Object[]{Double.valueOf(dJIWPCollectionItem.getDistance())}));
        }
    }

    public void deleteItem(DJIWPCollectionItem dJIWPCollectionItem) {
        if (dJIWPCollectionItem != null) {
            e.getInstance().e(dJIWPCollectionItem);
            dji.pilot.dji_groundstation.a.a aVar = new dji.pilot.dji_groundstation.a.a();
            aVar.a = 0;
            aVar.f = 0;
            aVar.b = R.string.gsnew_gs_delete_confirm_desc;
            aVar.h = R.string.gsnew_gs_delete_confirm_left_btn;
            aVar.j = R.string.gsnew_gs_delete_confirm_right_btn;
            aVar.i = "gs://smartmode/waypoint/collection/delete";
            aVar.d = 250;
            aVar.e = 270;
            aVar.k = false;
            dji.pilot.dji_groundstation.controller.d.getInstance().a(g.EVENT_SMARTMODE_WAYPOINT_NOTICE_TO_CONFIRM_DIALOG, aVar);
        }
    }
}
