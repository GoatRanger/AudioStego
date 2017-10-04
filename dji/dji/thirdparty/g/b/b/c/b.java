package dji.thirdparty.g.b.b.c;

import dji.thirdparty.g.a.a;
import dji.thirdparty.g.a.e;
import dji.thirdparty.g.b.b.a.f;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public abstract class b implements a, f {
    protected final int j;

    public abstract void a(OutputStream outputStream, h hVar) throws IOException, dji.thirdparty.g.f;

    public b() {
        this.j = 73;
    }

    public b(int i) {
        this.j = i;
    }

    protected static final int a(int i) {
        return (4 - (i % 4)) % 4;
    }

    protected i a(h hVar) throws dji.thirdparty.g.f {
        List a = hVar.a();
        if (1 > a.size()) {
            throw new dji.thirdparty.g.f("No directories.");
        }
        g gVar = null;
        f fVar = null;
        f fVar2 = null;
        f fVar3 = null;
        ArrayList arrayList = new ArrayList();
        Map hashMap = new HashMap();
        g gVar2 = null;
        g gVar3 = null;
        int i = 0;
        while (i < a.size()) {
            g gVar4;
            g gVar5 = (e) a.get(i);
            int i2 = gVar5.j;
            Integer num = new Integer(i2);
            hashMap.put(num, gVar5);
            if (i2 < 0) {
                switch (i2) {
                    case -4:
                        if (gVar == null) {
                            gVar4 = gVar2;
                            gVar2 = gVar3;
                            gVar3 = gVar5;
                            break;
                        }
                        throw new dji.thirdparty.g.f("More than one Interoperability directory.");
                    case -3:
                        if (gVar3 == null) {
                            gVar3 = gVar;
                            gVar4 = gVar2;
                            gVar2 = gVar5;
                            break;
                        }
                        throw new dji.thirdparty.g.f("More than one GPS directory.");
                    case -2:
                        if (gVar2 == null) {
                            gVar2 = gVar3;
                            gVar4 = gVar5;
                            gVar3 = gVar;
                            break;
                        }
                        throw new dji.thirdparty.g.f("More than one EXIF directory.");
                    default:
                        throw new dji.thirdparty.g.f("Unknown directory: " + i2);
                }
            } else if (arrayList.contains(num)) {
                throw new dji.thirdparty.g.f("More than one directory with index: " + i2 + ".");
            } else {
                arrayList.add(new Integer(i2));
                gVar4 = gVar2;
                gVar2 = gVar3;
                gVar3 = gVar;
            }
            HashSet hashSet = new HashSet();
            ArrayList a2 = gVar5.a();
            int i3 = 0;
            while (i3 < a2.size()) {
                f fVar4 = (f) a2.get(i3);
                Integer num2 = new Integer(fVar4.j);
                if (hashSet.contains(num2)) {
                    fVar4 = fVar3;
                    fVar3 = fVar2;
                    fVar2 = fVar;
                } else {
                    hashSet.add(num2);
                    f fVar5;
                    if (fVar4.j == eh.k) {
                        if (fVar != null) {
                            throw new dji.thirdparty.g.f("More than one Exif directory offset field.");
                        }
                        fVar5 = fVar3;
                        fVar3 = fVar2;
                        fVar2 = fVar4;
                        fVar4 = fVar5;
                    } else if (fVar4.j == gL.k) {
                        if (fVar3 != null) {
                            throw new dji.thirdparty.g.f("More than one Interoperability directory offset field.");
                        }
                        fVar3 = fVar2;
                        fVar2 = fVar;
                    } else if (fVar4.j != ew.k) {
                        fVar4 = fVar3;
                        fVar3 = fVar2;
                        fVar2 = fVar;
                    } else if (fVar2 != null) {
                        throw new dji.thirdparty.g.f("More than one GPS directory offset field.");
                    } else {
                        fVar2 = fVar;
                        fVar5 = fVar4;
                        fVar4 = fVar3;
                        fVar3 = fVar5;
                    }
                }
                i3++;
                fVar = fVar2;
                fVar2 = fVar3;
                fVar3 = fVar4;
            }
            i++;
            gVar = gVar3;
            gVar3 = gVar2;
            gVar2 = gVar4;
        }
        if (arrayList.size() < 1) {
            throw new dji.thirdparty.g.f("Missing root directory.");
        }
        e eVar;
        Collections.sort(arrayList);
        e eVar2 = null;
        i = 0;
        while (i < arrayList.size()) {
            Integer num3 = (Integer) arrayList.get(i);
            if (num3.intValue() != i) {
                throw new dji.thirdparty.g.f("Missing directory: " + i + ".");
            }
            eVar = (e) hashMap.get(num3);
            if (eVar2 != null) {
                eVar2.a(eVar);
            }
            i++;
            eVar2 = eVar;
        }
        eVar = (e) hashMap.get(new Integer(0));
        i iVar = new i(this.j, eVar, hashMap);
        if (gVar != null || fVar3 == null) {
            if (gVar != null) {
                if (gVar2 == null) {
                    gVar2 = hVar.j();
                }
                if (fVar3 == null) {
                    fVar3 = f.a(gL, this.j);
                    gVar2.a(fVar3);
                }
                iVar.a(gVar, fVar3);
            }
            if (gVar2 != null || fVar == null) {
                if (gVar2 != null) {
                    if (fVar == null) {
                        fVar = f.a(eh, this.j);
                        eVar.a(fVar);
                    }
                    iVar.a(gVar2, fVar);
                }
                if (gVar3 != null || fVar2 == null) {
                    if (gVar3 != null) {
                        if (fVar2 == null) {
                            fVar2 = f.a(ew, this.j);
                            eVar.a(fVar2);
                        }
                        iVar.a(gVar3, fVar2);
                    }
                    return iVar;
                }
                throw new dji.thirdparty.g.f("Output set has GPS Directory Offset field, but no GPS Directory");
            }
            throw new dji.thirdparty.g.f("Output set has Exif Directory Offset field, but no Exif Directory");
        }
        throw new dji.thirdparty.g.f("Output set has Interoperability Directory Offset field, but no Interoperability Directory");
    }

    protected void a(e eVar) throws IOException, dji.thirdparty.g.f {
        a(eVar, 8);
    }

    protected void a(e eVar, int i) throws IOException, dji.thirdparty.g.f {
        eVar.write(this.j);
        eVar.write(this.j);
        eVar.d(42);
        eVar.b(i);
    }
}
