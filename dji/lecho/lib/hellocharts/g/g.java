package lecho.lib.hellocharts.g;

import android.content.Context;
import lecho.lib.hellocharts.f.b;
import lecho.lib.hellocharts.f.d;
import lecho.lib.hellocharts.view.a;

public class g extends f {
    private e s;
    private h t;

    public g(Context context, a aVar, b bVar, d dVar) {
        this(context, aVar, new e(context, aVar, bVar), new h(context, aVar, dVar));
    }

    public g(Context context, a aVar, e eVar, d dVar) {
        this(context, aVar, eVar, new h(context, aVar, dVar));
    }

    public g(Context context, a aVar, b bVar, h hVar) {
        this(context, aVar, new e(context, aVar, bVar), hVar);
    }

    public g(Context context, a aVar, e eVar, h hVar) {
        super(context, aVar);
        this.s = eVar;
        this.t = hVar;
        this.q.add(this.s);
        this.q.add(this.t);
    }
}
