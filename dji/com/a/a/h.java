package com.a.a;

import android.app.Activity;
import android.os.Bundle;
import com.a.a.a.a;
import com.a.a.a.a.a.c;
import com.a.a.a.a.a.d;
import com.a.a.a.a.a.e;
import com.a.a.a.a.a.f;
import com.a.a.a.b;
import java.util.Map;

abstract class h {
    abstract void a(a aVar, j jVar);

    static boolean a(p pVar, a aVar) {
        if (b.a((Map) pVar) || "Segment.io".equals(aVar.b())) {
            return true;
        }
        String b = aVar.b();
        if (pVar.containsKey(b)) {
            return pVar.b(b, true);
        }
        if (pVar.containsKey("All")) {
            return pVar.b("All", true);
        }
        return true;
    }

    static boolean b(p pVar, a aVar) {
        boolean b = pVar.b("enabled", true);
        if (b) {
            return a(pVar.a("integrations"), aVar);
        }
        return b;
    }

    static h a(final Activity activity, final Bundle bundle) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.a(activity, bundle);
            }

            public String toString() {
                return "Activity Created";
            }
        };
    }

    static h a(final Activity activity) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.a(activity);
            }

            public String toString() {
                return "Activity Started";
            }
        };
    }

    static h b(final Activity activity) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.b(activity);
            }

            public String toString() {
                return "Activity Resumed";
            }
        };
    }

    static h c(final Activity activity) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.c(activity);
            }

            public String toString() {
                return "Activity Paused";
            }
        };
    }

    static h d(final Activity activity) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.d(activity);
            }

            public String toString() {
                return "Activity Stopped";
            }
        };
    }

    static h b(final Activity activity, final Bundle bundle) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.b(activity, bundle);
            }

            public String toString() {
                return "Activity Save Instance";
            }
        };
    }

    static h e(final Activity activity) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.e(activity);
            }

            public String toString() {
                return "Activity Destroyed";
            }
        };
    }

    static h a(final d dVar) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.a(dVar);
            }

            public String toString() {
                return dVar.toString();
            }
        };
    }

    static h a(final c cVar) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.a(cVar);
            }

            public String toString() {
                return cVar.toString();
            }
        };
    }

    static h a(final f fVar) {
        return new h() {
            public void a(a aVar, j jVar) {
                Map c = jVar.c();
                boolean z = true;
                if (!b.a(c)) {
                    String a = fVar.a();
                    if (c.containsKey(a)) {
                        z = h.b(c.a(a), aVar);
                    }
                }
                if (z) {
                    aVar.a(fVar);
                }
            }

            public String toString() {
                return fVar.toString();
            }
        };
    }

    static h a(final e eVar) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.a(eVar);
            }

            public String toString() {
                return eVar.toString();
            }
        };
    }

    static h a(final com.a.a.a.a.a.a aVar) {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.a(aVar);
            }

            public String toString() {
                return aVar.toString();
            }
        };
    }

    static h a() {
        return new h() {
            public void a(a aVar, j jVar) {
                aVar.c();
            }

            public String toString() {
                return "Flush";
            }
        };
    }

    private h() {
    }
}
