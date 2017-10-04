package dji.pilot.newfpv;

import android.view.View;
import dji.pilot.newfpv.view.b.a;
import dji.pilot.publics.objects.i;

public interface h<E> extends i<g> {
    void bind(g gVar, int i);

    void bindInfo(a aVar, E e, E e2);

    View getSelf();

    a getViewId();

    d getViewInfo();

    boolean needShow();
}
