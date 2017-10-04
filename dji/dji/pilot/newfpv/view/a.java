package dji.pilot.newfpv.view;

import dji.pilot.newfpv.g;
import dji.pilot.newfpv.h;

public abstract class a {
    public static void a(boolean z, g gVar, h hVar) {
        if (!z) {
            hVar.getSelf().setVisibility(8);
        } else if (hVar.needShow() && gVar.a(hVar.getViewInfo(), 0)) {
            hVar.getSelf().setVisibility(0);
        }
    }
}
