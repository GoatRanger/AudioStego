package dji.sdksharedlib;

import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.c.h;
import dji.sdksharedlib.d.a;

public interface b {
    a getAvailableValue(c cVar);

    void getValue(c cVar, dji.sdksharedlib.c.c cVar2);

    void setValue(c cVar, Object obj, h hVar);

    boolean startListeningForUpdates(c cVar, d dVar, boolean z);

    void stopListening(d dVar);

    void stopListeningOnKey(c cVar, d dVar);
}
