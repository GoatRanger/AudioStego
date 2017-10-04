package dji.pilot.publics.control.rc;

import java.io.File;
import java.io.FilenameFilter;

class b$1 implements FilenameFilter {
    final /* synthetic */ String a;
    final /* synthetic */ b b;

    b$1(b bVar, String str) {
        this.b = bVar;
        this.a = str;
    }

    public boolean accept(File file, String str) {
        return str.startsWith(this.a);
    }
}
