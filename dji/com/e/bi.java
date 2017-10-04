package com.e;

public class bi extends bj<String, bd> {
    public bi() {
        super(1048576);
    }

    protected int a(String str, bd bdVar) {
        int i = 0;
        if (bdVar == null) {
            return i;
        }
        try {
            return (int) bdVar.g();
        } catch (Throwable th) {
            bc.a(th, "OfflineFileCache", "sizeOf");
            return i;
        }
    }

    protected void a(boolean z, String str, bd bdVar, bd bdVar2) {
        if (bdVar != null) {
            try {
                bdVar.b();
            } catch (Throwable th) {
                bc.a(th, "OfflineFileCache", "entryRemoved");
            }
        }
        super.a(z, str, bdVar, bdVar2);
    }
}
