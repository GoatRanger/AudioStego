package android.databinding.a;

import android.databinding.f;
import android.databinding.g;
import android.databinding.n;
import android.databinding.o;
import android.databinding.p;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

@g(a = {@f(a = AdapterView.class, b = "android:onItemClick", c = "setOnItemClickListener"), @f(a = AdapterView.class, b = "android:onItemLongClick", c = "setOnItemLongClickListener")})
@p(a = {@o(a = AdapterView.class, b = "android:selectedItemPosition")})
public class e {

    public interface a {
        void a(AdapterView<?> adapterView, View view, int i, long j);
    }

    public static class b implements OnItemSelectedListener {
        private final a a;
        private final c b;
        private final n c;

        public b(a aVar, c cVar, n nVar) {
            this.a = aVar;
            this.b = cVar;
            this.c = nVar;
        }

        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            if (this.a != null) {
                this.a.a(adapterView, view, i, j);
            }
            if (this.c != null) {
                this.c.a();
            }
        }

        public void onNothingSelected(AdapterView<?> adapterView) {
            if (this.b != null) {
                this.b.a(adapterView);
            }
            if (this.c != null) {
                this.c.a();
            }
        }
    }

    public interface c {
        void a(AdapterView<?> adapterView);
    }

    @android.databinding.c(a = {"android:selectedItemPosition"})
    public static void a(AdapterView adapterView, int i) {
        if (adapterView.getSelectedItemPosition() != i) {
            adapterView.setSelection(i);
        }
    }

    @android.databinding.c(a = {"android:onItemSelected", "android:onNothingSelected", "android:selectedItemPositionAttrChanged"}, b = false)
    public static void a(AdapterView adapterView, a aVar, c cVar, n nVar) {
        if (aVar == null && cVar == null && nVar == null) {
            adapterView.setOnItemSelectedListener(null);
        } else {
            adapterView.setOnItemSelectedListener(new b(aVar, cVar, nVar));
        }
    }
}
