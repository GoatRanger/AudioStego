package com.e;

import android.location.Location;
import android.net.wifi.ScanResult;
import android.telephony.CellLocation;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import java.util.ArrayList;
import java.util.List;

public class ca {
    private static int c = 10;
    private static int d = 100;
    private static float f = dji.pilot.visual.a.d.c;
    protected d a = new d(this);
    protected a b = new a(this);
    private bw e;

    protected class a {
        b a = new b(null);
        Location b;
        final /* synthetic */ ca c;

        protected a(ca caVar) {
            this.c = caVar;
        }
    }

    protected static class b {
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        int c = Integer.MAX_VALUE;
        int d = Integer.MAX_VALUE;
        int e = Integer.MAX_VALUE;

        b(CellLocation cellLocation) {
            if (cellLocation != null) {
                a(cellLocation);
            }
        }

        private void a(CellLocation cellLocation) {
            try {
                if (cellLocation instanceof GsmCellLocation) {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    this.e = gsmCellLocation.getCid();
                    this.d = gsmCellLocation.getLac();
                } else if (cellLocation instanceof CdmaCellLocation) {
                    CdmaCellLocation cdmaCellLocation = (CdmaCellLocation) cellLocation;
                    this.c = cdmaCellLocation.getBaseStationId();
                    this.b = cdmaCellLocation.getNetworkId();
                    this.a = cdmaCellLocation.getSystemId();
                }
            } catch (Throwable th) {
                bc.a(th, "CustomCellLocation", "load");
            }
        }

        boolean a(b bVar) {
            return bVar.e == this.e && bVar.d == this.d && bVar.c == this.c && bVar.b == this.b && bVar.a == this.a;
        }
    }

    protected static class c {
        String a = "";

        public c(String str) {
            this.a = str;
        }
    }

    protected class d {
        List<c> a = new ArrayList();
        Location b;
        final /* synthetic */ ca c;

        protected d(ca caVar) {
            this.c = caVar;
        }
    }

    protected ca(bw bwVar) {
        this.e = bwVar;
    }

    protected static void a(int i) {
        c = i;
    }

    private void a(String str) {
    }

    private static boolean a(List<ScanResult> list, List<c> list2, float f) {
        if (list == null || list2 == null) {
            return false;
        }
        if (list == null || list2 == null) {
            return false;
        }
        try {
            int size = list.size();
            int size2 = list2.size();
            float f2 = (float) (size + size2);
            if (size == 0 && size2 == 0) {
                return true;
            }
            if (size == 0 || size2 == 0) {
                return false;
            }
            int i = 0;
            int i2 = 0;
            while (i < size) {
                int i3;
                String str = ((ScanResult) list.get(i)).BSSID;
                if (str == null) {
                    i3 = i2;
                } else {
                    for (int i4 = 0; i4 < size2; i4++) {
                        if (str.equals(((c) list2.get(i4)).a)) {
                            i3 = i2 + 1;
                            break;
                        }
                    }
                    i3 = i2;
                }
                i++;
                i2 = i3;
            }
            if (((float) (i2 * 2)) >= f2 * f) {
                return true;
            }
            return false;
        } catch (Throwable th) {
            bc.a(th, "CollectorStrategy", "isSameRate");
        }
    }

    protected static void b(int i) {
        d = i;
    }

    protected void a(int i, int i2) {
    }

    protected boolean a(Location location) {
        Throwable th;
        boolean z = false;
        if (this.e == null) {
            return false;
        }
        a("compare cell....");
        boolean z2;
        try {
            List h = this.e.h();
            if (h == null || location == null) {
                return false;
            }
            b bVar;
            a("cell.list.size: " + h.size());
            if (h.size() >= 2) {
                b bVar2 = new b((CellLocation) h.get(1));
                if (this.b.b == null) {
                    a("first cell causes cell collect");
                    bVar = bVar2;
                    z2 = true;
                } else {
                    boolean z3 = location.distanceTo(this.b.b) > ((float) d);
                    if (z3) {
                        try {
                            a("distance causes cell collect");
                        } catch (Throwable th2) {
                            th = th2;
                            z2 = z3;
                            bc.a(th, "CollectorStrategy", "collectCell");
                            return z2;
                        }
                    }
                    if (z3) {
                        z = z3;
                    } else {
                        if (!this.b.a.a(bVar2)) {
                            z = true;
                        }
                        if (z) {
                            a("different cells causes cell collect");
                        }
                    }
                    a("collect cell?: " + z);
                    z2 = z;
                    bVar = bVar2;
                }
            } else {
                z2 = false;
                bVar = null;
            }
            if (!z2) {
                return z2;
            }
            try {
                this.b.a = bVar;
                return z2;
            } catch (Throwable th3) {
                th = th3;
                bc.a(th, "CollectorStrategy", "collectCell");
                return z2;
            }
        } catch (Throwable th22) {
            Throwable th4 = th22;
            z2 = z;
            th = th4;
            bc.a(th, "CollectorStrategy", "collectCell");
            return z2;
        }
    }

    protected boolean b(Location location) {
        boolean z;
        Throwable th;
        int i = 0;
        if (this.e == null) {
            return false;
        }
        try {
            boolean z2;
            List list;
            List a = this.e.a(false);
            if (a.size() >= 2) {
                List list2 = (List) a.get(1);
                if (this.a.b == null) {
                    a("first wifi causes wifi collect");
                    z2 = true;
                    list = list2;
                } else {
                    if (list2 != null) {
                        if (list2.size() > 0) {
                            a("current info is valid");
                            z2 = location.distanceTo(this.a.b) > ((float) c);
                            if (z2) {
                                a("distance causes wifi collect");
                            }
                            if (!z2) {
                                z2 = !a(list2, this.a.a, f);
                                if (z2) {
                                    a("different wifis causes wifi collect");
                                }
                            }
                            list = list2;
                        }
                    }
                    list = list2;
                    z2 = false;
                }
            } else {
                list = null;
                z2 = false;
            }
            if (z2) {
                try {
                    this.a.a.clear();
                    int size = list.size();
                    while (i < size) {
                        this.a.a.add(new c(((ScanResult) list.get(i)).BSSID));
                        i++;
                    }
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    z = z2;
                    th = th3;
                    bc.a(th, "CollectorStrategy", "collectWifi");
                    return z;
                }
            }
            return z2;
        } catch (Throwable th22) {
            th = th22;
            z = false;
            bc.a(th, "CollectorStrategy", "collectWifi");
            return z;
        }
    }
}
