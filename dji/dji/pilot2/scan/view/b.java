package dji.pilot2.scan.view;

import com.google.a.t;
import com.google.a.u;

public final class b implements u {
    private final ViewfinderView a;

    public b(ViewfinderView viewfinderView) {
        this.a = viewfinderView;
    }

    public void a(t tVar) {
        this.a.addPossibleResultPoint(tVar);
    }
}
