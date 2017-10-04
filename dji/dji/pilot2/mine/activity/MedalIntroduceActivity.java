package dji.pilot2.mine.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.mine.a.e;
import java.util.ArrayList;
import java.util.List;

public class MedalIntroduceActivity extends DJIActivityNoFullScreen {
    public static final String a = "active_index";
    private List<TextView> b;
    private int[] c = new int[]{0, 0, 0, 0};
    private int[] d = new int[]{0, 0, 0, 0};
    private e t;
    private ListView u;
    private int v;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_medal_introduce);
        a();
        b();
    }

    private void a() {
        this.v = getIntent().getIntExtra(a, 0);
        this.t = new e(this);
        this.t.a(this.v);
    }

    private void b() {
        this.b = new ArrayList();
        this.b.add((TextView) findViewById(R.id.c9k));
        this.b.add((TextView) findViewById(R.id.c9l));
        this.b.add((TextView) findViewById(R.id.c9m));
        this.b.add((TextView) findViewById(R.id.c9n));
        ((TextView) this.b.get(this.v)).setSelected(true);
        ((TextView) this.b.get(this.v)).setTextColor(getResources().getColor(R.color.kw));
        this.u = (ListView) findViewById(R.id.c9o);
        this.u.setAdapter(this.t);
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.c9h:
                finish();
                return;
            case R.id.c9k:
            case R.id.c9l:
            case R.id.c9m:
            case R.id.c9n:
                int i;
                int firstVisiblePosition = this.u.getFirstVisiblePosition();
                View childAt = this.u.getChildAt(0);
                if (view == null) {
                    i = 0;
                } else {
                    i = childAt.getTop() - this.u.getPaddingTop();
                }
                this.c[this.t.a()] = firstVisiblePosition;
                this.d[this.t.a()] = i;
                for (firstVisiblePosition = 0; firstVisiblePosition < this.b.size(); firstVisiblePosition++) {
                    if (this.b.get(firstVisiblePosition) != null) {
                        if (view == this.b.get(firstVisiblePosition)) {
                            this.t.a(firstVisiblePosition);
                            ((TextView) this.b.get(firstVisiblePosition)).setSelected(true);
                            ((TextView) this.b.get(firstVisiblePosition)).setTextColor(getResources().getColor(R.color.kw));
                        } else {
                            ((TextView) this.b.get(firstVisiblePosition)).setSelected(false);
                            ((TextView) this.b.get(firstVisiblePosition)).setTextColor(getResources().getColor(R.color.a_));
                        }
                    }
                }
                this.t.notifyDataSetChanged();
                this.u.setSelectionFromTop(this.c[this.t.a()], this.d[this.t.a()]);
                return;
            default:
                return;
        }
    }
}
