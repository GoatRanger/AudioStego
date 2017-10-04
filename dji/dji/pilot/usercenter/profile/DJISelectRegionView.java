package dji.pilot.usercenter.profile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.e.a.c;
import dji.pilot.usercenter.e.b;
import dji.publics.DJIUI.DJIListView;
import java.util.ArrayList;
import java.util.List;

public class DJISelectRegionView extends FrameLayout implements dji.pilot.fpv.view.DJIStageView.a {
    private DJIListView a = null;
    private ArrayList<b> b = new ArrayList();
    private dji.pilot.usercenter.a.a c = null;
    private c d = null;
    private OnItemClickListener e = null;
    private a f;

    public interface a {
        void a(b bVar);
    }

    public DJISelectRegionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public void setOnSelectListener(a aVar) {
        this.f = aVar;
    }

    private void a() {
        b();
    }

    private void b() {
        this.d = new c(this) {
            final /* synthetic */ DJISelectRegionView a;

            {
                this.a = r1;
            }

            public void a(List<b> list, b bVar) {
                this.a.b.clear();
                this.a.b.addAll(list);
                this.a.c.notifyDataSetChanged();
            }
        };
        this.e = new OnItemClickListener(this) {
            final /* synthetic */ DJISelectRegionView a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                f.getInstance().h().R = (b) this.a.b.get(i);
                View view2 = (View) this.a.getParent();
                if (view2 instanceof DJIStageView) {
                    ((DJIStageView) view2).destoryStageView(true);
                }
                if (this.a.f != null) {
                    this.a.f.a((b) this.a.b.get(i));
                }
            }
        };
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (DJIListView) findViewById(R.id.bjg);
            this.a.setEmptyView(findViewById(R.id.bjh));
            this.a.setOnItemClickListener(this.e);
            this.c = new dji.pilot.usercenter.a.a(this.b, getContext());
            this.a.setAdapter(this.c);
        }
    }

    public void dispatchOnStart() {
        dji.pilot.usercenter.e.a.getInstance().a(getContext());
        dji.pilot.usercenter.e.a.getInstance().a(null, this.d);
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
    }

    public void dispatchOnPause() {
    }

    public View getView() {
        return this;
    }
}
