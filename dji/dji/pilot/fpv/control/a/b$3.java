package dji.pilot.fpv.control.a;

import java.io.File;
import java.io.FilenameFilter;

class b$3 implements FilenameFilter {
    final /* synthetic */ String a;
    final /* synthetic */ b b;

    b$3(b bVar, String str) {
        this.b = bVar;
        this.a = str;
    }

    public boolean accept(File file, String str) {
        return !str.equals(new File(this.a).getName());
    }
}
