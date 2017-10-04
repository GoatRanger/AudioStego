package dji.pilot.flyunlimit.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import dji.gs.b.c;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.List;

public class WarningTypeView extends DJILinearLayout implements dji.pilot.fpv.view.DJIStageView.a {
    private static final int[] d = new int[]{R.string.nfz_fly_zone_warn, R.string.nfz_fly_zone_warn_show, R.string.nfz_fly_zone_warn_ignore};
    private ListView a;
    private a b;
    private List<b> c = new ArrayList();

    public class a extends BaseAdapter {
        final /* synthetic */ WarningTypeView a;
        private LayoutInflater b;

        private final class a {
            public DJITextView a;
            public DJITextView b;
            final /* synthetic */ a c;

            private a(a aVar) {
                this.c = aVar;
            }
        }

        public a(WarningTypeView warningTypeView, Context context) {
            this.a = warningTypeView;
            this.b = LayoutInflater.from(context);
        }

        public int getCount() {
            return this.a.c.size();
        }

        public Object getItem(int i) {
            return this.a.c.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                aVar = new a();
                view = this.b.inflate(R.layout.warn_type_list_item, null);
                aVar.a = (DJITextView) view.findViewById(R.id.da9);
                aVar.b = (DJITextView) view.findViewById(R.id.daa);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a.setText(((b) this.a.c.get(i)).a);
            aVar.b.setText(this.a.getResources().getString(WarningTypeView.d[((b) this.a.c.get(i)).c]));
            return view;
        }
    }

    public static final class b {
        public String a;
        public int b;
        public int c;
    }

    public WarningTypeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            b();
        }
    }

    private void b() {
        this.a = (ListView) findViewById(R.id.dah);
        c();
        ListView listView = this.a;
        ListAdapter aVar = new a(this, getContext());
        this.b = aVar;
        listView.setAdapter(aVar);
        this.a.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ WarningTypeView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                ((WarningLevelSelectView) ((DJIStageView) this.a.getParent()).createStageView(R.layout.warning_level_select_view, R.string.nfz_fly_zone_warn, true)).setWarnAlertStruct((b) this.a.c.get(i), i);
            }
        });
    }

    private void c() {
        this.c.clear();
        String[] a = c.getInstance(getContext()).a();
        int[] b = c.getInstance(getContext()).b();
        ArrayList c = c.getInstance(getContext()).c();
        int length = a.length;
        for (int i = 0; i != length; i++) {
            b bVar = new b();
            bVar.a = a[i];
            bVar.b = b[i];
            bVar.c = ((Integer) c.get(i)).intValue();
            this.c.add(bVar);
        }
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        c();
        this.b.notifyDataSetChanged();
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
