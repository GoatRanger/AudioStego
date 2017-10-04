package com.e;

import com.amap.api.maps.model.HeatmapTileProvider;
import dji.midware.data.forbid.FlyForbidProtocol;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.StringTokenizer;

public class as {
    private ArrayList<a> a = new ArrayList();
    private ArrayList<c> b = new ArrayList();
    private short[][] c = ((short[][]) Array.newInstance(Short.TYPE, new int[]{128, 128}));
    private b d = new b(this);

    class a implements Comparable<a> {
        final /* synthetic */ as a;
        private ArrayList<c> b;
        private ArrayList<c> c;
        private double d;
        private String e;

        public a(as asVar) {
            this.a = asVar;
            this.b = new ArrayList();
            this.c = new ArrayList();
            this.d = 0.0d;
            this.e = "";
            this.d = 0.0d;
            this.e = "";
        }

        public int a(a aVar) {
            double d = aVar.d - this.d;
            return d > 0.0d ? 1 : d == 0.0d ? 0 : -1;
        }

        public void a() {
            double size = (double) this.c.size();
            Iterator it = this.b.iterator();
            double d = 0.0d;
            while (it.hasNext()) {
                d = (((c) it.next()).e > 1 ? HeatmapTileProvider.DEFAULT_OPACITY : 1.0d) + d;
            }
            this.d = (d * 3.0d) + size;
            this.d += ((d * 3.0d) + 0.1d) * (size + 0.1d);
        }

        public void a(c cVar) {
            if (cVar.e == 0) {
                this.c.add(cVar);
            } else if (cVar.e > 0) {
                this.b.add(cVar);
            }
            if ("0".equals(this.e)) {
                this.e = cVar.d;
            }
        }

        public c b() {
            double d = 0.0d;
            int i = this.b.isEmpty() ? 0 : 3;
            if (this.c.isEmpty()) {
                Iterator it = this.b.iterator();
                if (!it.hasNext()) {
                    return null;
                }
                c cVar = (c) it.next();
                if (cVar.e == 1) {
                    return new c(this.a, cVar.a, cVar.b, cVar.c, this.e, this.d, 1);
                }
                int i2 = 0 < cVar.c ? cVar.c : 0;
                double d2 = 0.0d + 1.0d;
                return new c(this.a, (0.0d + cVar.a) / d2, (0.0d + cVar.b) / d2, i2 > 5000 ? 5000 : i2, this.e, this.d, 2);
            }
            ArrayList arrayList = new ArrayList();
            Iterator it2 = this.c.iterator();
            double d3 = 0.0d;
            double d4 = 0.0d;
            while (it2.hasNext()) {
                c cVar2 = (c) it2.next();
                arrayList.add(Integer.valueOf(cVar2.c));
                d3 += cVar2.a;
                d4 = 1.0d + d4;
                d = cVar2.b + d;
            }
            Collections.sort(arrayList);
            i2 = arrayList.size() == 1 ? ((Integer) arrayList.get(0)).intValue() : ((Integer) arrayList.get(arrayList.size() / 2)).intValue();
            int i3 = this.c.size() == 1 ? 500 : i2 > 500 ? 300 : i2 < 30 ? 30 : i2;
            return new c(this.a, d3 / d4, d / d4, i3, this.e, this.d, i);
        }

        public /* synthetic */ int compareTo(Object obj) {
            return a((a) obj);
        }
    }

    private class b {
        final /* synthetic */ as a;
        private ArrayList<ArrayList<Integer>> b;

        public b(as asVar) {
            this.a = asVar;
            this.b = null;
            this.b = new ArrayList();
        }

        private void a(int i, int i2) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(Integer.valueOf(i));
            arrayList.add(Integer.valueOf(i2));
            this.a.c[i][i2] = (short) -1;
            this.a.c[i2][i] = (short) -1;
            ArrayList arrayList2 = new ArrayList();
            int i3 = 0;
            while (i3 < this.a.b.size()) {
                if (!(this.a.c[i][i3] == (short) 0 || this.a.c[i2][i3] == (short) 0)) {
                    arrayList2.add(Integer.valueOf(i3));
                }
                i3++;
            }
            while (!arrayList2.isEmpty()) {
                int intValue = ((Integer) arrayList2.get(0)).intValue();
                arrayList2.remove(0);
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    Integer num = (Integer) it.next();
                    this.a.c[intValue][num.intValue()] = (short) -1;
                    this.a.c[num.intValue()][intValue] = (short) -1;
                }
                arrayList.add(Integer.valueOf(intValue));
                int i4 = 0;
                while (i4 < arrayList2.size()) {
                    if (this.a.c[intValue][((Integer) arrayList2.get(i4)).intValue()] == (short) 0) {
                        arrayList2.remove(i4);
                        i3 = i4;
                    } else {
                        i3 = i4 + 1;
                    }
                    i4 = i3;
                }
            }
            this.b.add(arrayList);
        }

        public void a() throws Exception {
            int i;
            int size = this.a.b.size();
            for (i = 0; i < size; i++) {
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.a.c[i][i2] > (short) 0) {
                        a(i, i2);
                    }
                }
            }
            for (int i3 = 0; i3 < size; i3++) {
                Object obj = 1;
                i = 0;
                while (i < size) {
                    if (this.a.c[i3][i] > (short) 0) {
                        throw new Exception("non visited edge");
                    } else if (this.a.c[i3][i] < (short) 0) {
                        obj = null;
                        break;
                    } else {
                        i++;
                    }
                }
                if (obj != null) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(Integer.valueOf(i3));
                    this.b.add(arrayList);
                }
            }
        }
    }

    public class c {
        public double a = 0.0d;
        public double b = 0.0d;
        public int c = 0;
        public String d = "0";
        public int e = -1;
        final /* synthetic */ as f;

        public c(as asVar, double d, double d2) {
            this.f = asVar;
            this.a = d;
            this.b = d2;
        }

        public c(as asVar, double d, double d2, int i, int i2) {
            this.f = asVar;
            this.a = d;
            this.b = d2;
            this.c = i;
            this.e = i2;
        }

        public c(as asVar, double d, double d2, int i, String str, double d3, int i2) {
            this.f = asVar;
            this.a = d;
            this.b = d2;
            this.d = str;
            this.c = i;
            this.e = i2;
        }

        private boolean a(c cVar) {
            boolean z = false;
            double b = b(cVar);
            if (b < 500.0d) {
                return true;
            }
            if ((this.e > 0 && cVar.e == 0) || (this.e == 0 && cVar.e > 0)) {
                return (this.e == 1 || cVar.e == 1) ? b < 3000.0d && (b < ((double) this.c) * 1.5d || b < ((double) cVar.c) * 1.5d) : b < FlyForbidProtocol.STRONG_WARNING_CHECK_RADIUS;
            } else {
                if (this.e > 0 || cVar.e > 0) {
                    if (b < FlyForbidProtocol.STRONG_WARNING_CHECK_RADIUS || b < ((double) this.c) || b < ((double) cVar.c)) {
                        z = true;
                    }
                    return z;
                }
                if (b < ((double) this.c) || b < ((double) cVar.c) || b < 500.0d) {
                    z = true;
                }
                return z;
            }
        }

        private double b(c cVar) {
            double d = 6356725.0d + ((21412.0d * (90.0d - this.a)) / 90.0d);
            double cos = (Math.cos((this.a * 3.1415926535898d) / 180.0d) * d) * (((cVar.b - this.b) * 3.1415926535898d) / 180.0d);
            d *= ((cVar.a - this.a) * 3.1415926535898d) / 180.0d;
            return Math.sqrt((d * d) + (cos * cos));
        }
    }

    public ArrayList<c> a(double d, double d2) throws Exception {
        this.d.a();
        ArrayList<c> arrayList = new ArrayList();
        Iterator it = this.d.b.iterator();
        while (it.hasNext()) {
            ArrayList arrayList2 = (ArrayList) it.next();
            a aVar = new a(this);
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                aVar.a((c) this.b.get(((Integer) it2.next()).intValue()));
            }
            aVar.a();
            this.a.add(aVar);
        }
        if (this.a.isEmpty()) {
            return null;
        }
        Collections.sort(this.a);
        if (!(d == 0.0d || d2 == 0.0d)) {
            c cVar = new c(this, d2, d);
            for (int i = 0; i < this.a.size(); i++) {
                c b = ((a) this.a.get(i)).b();
                double b2 = cVar.b(b);
                if (b.e > 0) {
                    if (b2 < 7000.0d && b2 > 2.0d) {
                        ((a) this.a.get(i)).d = ((a) this.a.get(i)).d * 5.0d;
                    }
                } else if (b2 < 10000.0d && b2 > 2.0d) {
                    ((a) this.a.get(i)).d = ((a) this.a.get(i)).d * 5.0d;
                }
            }
            Collections.sort(this.a);
        }
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            if (arrayList.size() >= 5) {
                return arrayList;
            }
            arrayList.add(((a) this.a.get(i2)).b());
        }
        return arrayList;
    }

    public void a(int i, String str) throws Exception {
        int size = this.b.size();
        StringTokenizer stringTokenizer = new StringTokenizer(str, "\\|");
        if (stringTokenizer.countTokens() >= 3) {
            ArrayList arrayList = new ArrayList();
            while (stringTokenizer.hasMoreElements()) {
                arrayList.add(stringTokenizer.nextToken());
            }
            this.b.add(new c(this, Double.parseDouble((String) arrayList.get(0)), Double.parseDouble((String) arrayList.get(1)), Double.valueOf(Double.parseDouble((String) arrayList.get(2))).intValue(), i));
            if (this.b.size() > 128) {
                throw new Exception("Atomic Pos Larger Than MAXN 512!");
            }
            for (int i2 = 0; i2 < size; i2++) {
                for (int i3 = size; i3 < this.b.size(); i3++) {
                    if (((c) this.b.get(i2)).a((c) this.b.get(i3))) {
                        this.c[i2][i3] = (short) 1;
                        this.c[i3][i2] = (short) 1;
                    }
                }
            }
            arrayList.clear();
        }
    }
}
