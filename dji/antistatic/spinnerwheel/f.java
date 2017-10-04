package antistatic.spinnerwheel;

import android.view.View;
import android.widget.LinearLayout;
import java.util.LinkedList;
import java.util.List;

public class f {
    private static final String a = f.class.getName();
    private List<View> b;
    private List<View> c;
    private AbstractWheel d;

    public f(AbstractWheel abstractWheel) {
        this.d = abstractWheel;
    }

    public int a(LinearLayout linearLayout, int i, a aVar) {
        int i2 = 0;
        int i3 = i;
        while (i2 < linearLayout.getChildCount()) {
            if (aVar.a(i)) {
                i2++;
            } else {
                a(linearLayout.getChildAt(i2), i);
                linearLayout.removeViewAt(i2);
                if (i2 == 0) {
                    i3++;
                }
            }
            i++;
        }
        return i3;
    }

    public View a() {
        return a(this.b);
    }

    public View b() {
        return a(this.c);
    }

    public void c() {
        if (this.b != null) {
            this.b.clear();
        }
        if (this.c != null) {
            this.c.clear();
        }
    }

    private List<View> a(View view, List<View> list) {
        if (list == null) {
            list = new LinkedList();
        }
        list.add(view);
        return list;
    }

    private void a(View view, int i) {
        int h = this.d.getViewAdapter().h();
        if ((i < 0 || i >= h) && !this.d.isCyclic()) {
            this.c = a(view, this.c);
            return;
        }
        while (i < 0) {
            i += h;
        }
        h = i % h;
        this.b = a(view, this.b);
    }

    private View a(List<View> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        View view = (View) list.get(0);
        list.remove(0);
        return view;
    }
}
