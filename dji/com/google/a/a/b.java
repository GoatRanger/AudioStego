package com.google.a.a;

import com.google.a.a.b.a;
import com.google.a.c;
import com.google.a.e;
import com.google.a.h;
import com.google.a.m;
import com.google.a.p;
import com.google.a.r;
import com.google.a.s;
import com.google.a.t;
import com.google.a.u;
import java.util.List;
import java.util.Map;

public final class b implements p {
    public r a(c cVar) throws m, h {
        return a(cVar, null);
    }

    public r a(c cVar, Map<e, ?> map) throws m, h {
        a a;
        t[] e;
        com.google.a.c.e a2;
        m mVar;
        m e2;
        com.google.a.c.e eVar;
        t[] tVarArr;
        h e3;
        u uVar;
        r rVar;
        List c;
        String d;
        h hVar;
        h hVar2 = null;
        a aVar = new a(cVar.c());
        try {
            a = aVar.a(false);
            e = a.e();
            try {
                a2 = new com.google.a.a.a.a().a(a);
                mVar = null;
            } catch (m e4) {
                e2 = e4;
                mVar = e2;
                a2 = null;
                if (a2 == null) {
                    eVar = a2;
                    tVarArr = e;
                } else {
                    try {
                        a = aVar.a(true);
                        eVar = new com.google.a.a.a.a().a(a);
                        tVarArr = a.e();
                    } catch (m e5) {
                        e3 = e5;
                        if (mVar != null) {
                            throw mVar;
                        } else if (hVar2 == null) {
                            throw hVar2;
                        } else {
                            throw e3;
                        }
                    } catch (h e6) {
                        e3 = e6;
                        if (mVar != null) {
                            throw mVar;
                        } else if (hVar2 == null) {
                            throw e3;
                        } else {
                            throw hVar2;
                        }
                    }
                }
                if (map != null) {
                    uVar = (u) map.get(e.NEED_RESULT_POINT_CALLBACK);
                    if (uVar != null) {
                        for (t a3 : tVarArr) {
                            uVar.a(a3);
                        }
                    }
                }
                rVar = new r(eVar.b(), eVar.a(), tVarArr, com.google.a.a.AZTEC);
                c = eVar.c();
                if (c != null) {
                    rVar.a(s.BYTE_SEGMENTS, c);
                }
                d = eVar.d();
                if (d != null) {
                    rVar.a(s.ERROR_CORRECTION_LEVEL, d);
                }
                return rVar;
            } catch (h e7) {
                e3 = e7;
                mVar = null;
                hVar = e3;
                a2 = null;
                hVar2 = hVar;
                if (a2 == null) {
                    a = aVar.a(true);
                    eVar = new com.google.a.a.a.a().a(a);
                    tVarArr = a.e();
                } else {
                    eVar = a2;
                    tVarArr = e;
                }
                if (map != null) {
                    uVar = (u) map.get(e.NEED_RESULT_POINT_CALLBACK);
                    if (uVar != null) {
                        while (r1 < r5) {
                            uVar.a(a3);
                        }
                    }
                }
                rVar = new r(eVar.b(), eVar.a(), tVarArr, com.google.a.a.AZTEC);
                c = eVar.c();
                if (c != null) {
                    rVar.a(s.BYTE_SEGMENTS, c);
                }
                d = eVar.d();
                if (d != null) {
                    rVar.a(s.ERROR_CORRECTION_LEVEL, d);
                }
                return rVar;
            }
        } catch (m e8) {
            e2 = e8;
            e = null;
            mVar = e2;
            a2 = null;
            if (a2 == null) {
                eVar = a2;
                tVarArr = e;
            } else {
                a = aVar.a(true);
                eVar = new com.google.a.a.a.a().a(a);
                tVarArr = a.e();
            }
            if (map != null) {
                uVar = (u) map.get(e.NEED_RESULT_POINT_CALLBACK);
                if (uVar != null) {
                    while (r1 < r5) {
                        uVar.a(a3);
                    }
                }
            }
            rVar = new r(eVar.b(), eVar.a(), tVarArr, com.google.a.a.AZTEC);
            c = eVar.c();
            if (c != null) {
                rVar.a(s.BYTE_SEGMENTS, c);
            }
            d = eVar.d();
            if (d != null) {
                rVar.a(s.ERROR_CORRECTION_LEVEL, d);
            }
            return rVar;
        } catch (h e9) {
            e3 = e9;
            e = null;
            mVar = null;
            hVar = e3;
            a2 = null;
            hVar2 = hVar;
            if (a2 == null) {
                a = aVar.a(true);
                eVar = new com.google.a.a.a.a().a(a);
                tVarArr = a.e();
            } else {
                eVar = a2;
                tVarArr = e;
            }
            if (map != null) {
                uVar = (u) map.get(e.NEED_RESULT_POINT_CALLBACK);
                if (uVar != null) {
                    while (r1 < r5) {
                        uVar.a(a3);
                    }
                }
            }
            rVar = new r(eVar.b(), eVar.a(), tVarArr, com.google.a.a.AZTEC);
            c = eVar.c();
            if (c != null) {
                rVar.a(s.BYTE_SEGMENTS, c);
            }
            d = eVar.d();
            if (d != null) {
                rVar.a(s.ERROR_CORRECTION_LEVEL, d);
            }
            return rVar;
        }
        if (a2 == null) {
            a = aVar.a(true);
            eVar = new com.google.a.a.a.a().a(a);
            tVarArr = a.e();
        } else {
            eVar = a2;
            tVarArr = e;
        }
        if (map != null) {
            uVar = (u) map.get(e.NEED_RESULT_POINT_CALLBACK);
            if (uVar != null) {
                while (r1 < r5) {
                    uVar.a(a3);
                }
            }
        }
        rVar = new r(eVar.b(), eVar.a(), tVarArr, com.google.a.a.AZTEC);
        c = eVar.c();
        if (c != null) {
            rVar.a(s.BYTE_SEGMENTS, c);
        }
        d = eVar.d();
        if (d != null) {
            rVar.a(s.ERROR_CORRECTION_LEVEL, d);
        }
        return rVar;
    }

    public void a() {
    }
}
