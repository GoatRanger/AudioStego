package dji.sdksharedlib.e.a;

import dji.midware.data.manager.P3.d;
import dji.midware.data.model.P3.DataFlycGetParamInfoByHash;
import dji.midware.data.model.P3.DataFlycGetParams;
import dji.midware.data.model.P3.DataFlycResetParamNew;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.data.params.P3.ParamInfoBean;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class a {
    private static a c;
    private HashMap<String, b> a = dji.sdksharedlib.e.a.a.a.a();
    private int b = 0;

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    public ParamInfo a(String str) {
        if (!b(str)) {
            return null;
        }
        String f = f(str);
        d.getInstance();
        return d.read(f);
    }

    private int a(String str, int i) {
        if (!b(str)) {
            return 0;
        }
        b bVar = (b) this.a.get(str);
        if (bVar == null) {
            return 0;
        }
        if (bVar.j == -1 && bVar.i >= this.b) {
            return i + 1;
        }
        if (bVar.i == -1 && bVar.j <= this.b) {
            return i + 1;
        }
        if (bVar.j > this.b || bVar.i < this.b) {
            return bVar.k != null ? a(bVar.k, i + 1) : 0;
        } else {
            return i + 1;
        }
    }

    public a() {
        a();
        DJISDKCache.getInstance().startListeningForUpdates(b.f(e.cm), new 1(this), false);
        this.b = dji.sdksharedlib.a.a.a(dji.sdksharedlib.a.a.e(e.cm));
    }

    public boolean b(String str) {
        if (!this.a.containsKey(str)) {
            return false;
        }
        b bVar = (b) this.a.get(str);
        if (bVar == null) {
            return false;
        }
        if (bVar.j == -1 && bVar.i >= this.b) {
            return true;
        }
        if (bVar.i == -1 && bVar.j <= this.b) {
            return true;
        }
        if (bVar.j > this.b || bVar.i < this.b) {
            return bVar.k != null ? b(bVar.k) : false;
        } else {
            return true;
        }
    }

    public boolean a(String str, Number number) {
        if (!b(str)) {
            return false;
        }
        b bVar = (b) this.a.get(str);
        if (bVar == null) {
            return false;
        }
        if (bVar.c == null || bVar.b == null || bVar.c.doubleValue() <= number.doubleValue() || bVar.b.doubleValue() >= number.doubleValue()) {
            return bVar.k != null ? a(bVar.k, number) : false;
        } else {
            return true;
        }
    }

    public boolean c(String str) {
        if (!b(str)) {
            return false;
        }
        b bVar = (b) this.a.get(str);
        if (bVar == null) {
            return false;
        }
        if ((bVar.e & 1) == 1) {
            return true;
        }
        return bVar.k != null ? e(bVar.k) : false;
    }

    public boolean d(String str) {
        if (!b(str)) {
            return false;
        }
        b bVar = (b) this.a.get(str);
        if (bVar == null) {
            return false;
        }
        if ((bVar.e & 2) == 2) {
            return true;
        }
        return bVar.k != null ? e(bVar.k) : false;
    }

    public boolean e(String str) {
        if (!b(str)) {
            return false;
        }
        b bVar = (b) this.a.get(str);
        if (bVar == null) {
            return false;
        }
        if ((bVar.e & 16) == 16) {
            return true;
        }
        return bVar.k != null ? e(bVar.k) : false;
    }

    private String f(String str) {
        if (!b(str)) {
            return null;
        }
        b bVar = (b) this.a.get(str);
        if (bVar == null) {
            return null;
        }
        if (bVar.j == -1 && bVar.i >= this.b) {
            return bVar.h;
        }
        if (bVar.i == -1 && bVar.j <= this.b) {
            return bVar.h;
        }
        if (bVar.j > this.b || bVar.i < this.b) {
            return bVar.k != null ? f(bVar.k) : null;
        } else {
            return bVar.h;
        }
    }

    public void a(String str, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        a(arrayList, eVar);
    }

    public void a(ArrayList<String> arrayList, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        String[] strArr = a((ArrayList) arrayList, null, true).a;
        new DataFlycGetParams().setInfos(strArr).start(new 2(this, strArr, eVar));
    }

    public void a(ArrayList<String> arrayList, ArrayList<Number> arrayList2, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        b a = a((ArrayList) arrayList, (ArrayList) arrayList2, false);
        String[] strArr = a.a;
        Number[] numberArr = a.b;
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        dataFlycSetParams.a(strArr);
        dataFlycSetParams.a(numberArr);
        dataFlycSetParams.start(new 3(this, strArr, eVar));
    }

    public void a(String str, Number number, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(number);
        a(arrayList, arrayList2, eVar);
    }

    public void b(String str, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        String f = f(str);
        DataFlycGetParamInfoByHash dataFlycGetParamInfoByHash = new DataFlycGetParamInfoByHash();
        dataFlycGetParamInfoByHash.setIndex(f).start(new 4(this, dataFlycGetParamInfoByHash, eVar));
    }

    public void b(ArrayList<String> arrayList, dji.sdksharedlib.hardware.abstractions.b.e eVar) {
        new DataFlycResetParamNew().setInfos(a((ArrayList) arrayList, null, false).a).start(new 5(this, eVar));
    }

    private void a() {
        for (b bVar : this.a.values()) {
            ParamInfoBean paramInfoBean = new ParamInfoBean();
            paramInfoBean.index = -1;
            paramInfoBean.attribute = 11;
            paramInfoBean.maxValue = bVar.c.toString();
            paramInfoBean.minValue = bVar.b.toString();
            paramInfoBean.defaultValue = bVar.d.toString();
            paramInfoBean.name = bVar.h;
            paramInfoBean.size = bVar.a;
            paramInfoBean.typeID = a(bVar.l);
            d.writeNewParamInfo(bVar.h, paramInfoBean.getParamInfo());
        }
    }

    private int a(Class cls) {
        if (cls.equals(Double.class)) {
            return 9;
        }
        if (cls.equals(Integer.class)) {
            return 5;
        }
        if (cls.equals(Float.class)) {
            return 8;
        }
        if (cls.equals(Byte.class)) {
            return 10;
        }
        if (cls.equals(Long.class)) {
            return 7;
        }
        if (cls.equals(Short.class)) {
            return 4;
        }
        return 8;
    }

    private b a(ArrayList<String> arrayList, ArrayList<Number> arrayList2, boolean z) {
        Number[] numberArr;
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            boolean c;
            if (z) {
                c = c(str);
            } else {
                c = d(str);
            }
            if (b(str) && r1) {
                String f = f(str);
                if (f != null) {
                    arrayList3.add(f);
                    if (arrayList2 != null) {
                        arrayList4.add(arrayList2.get(arrayList.indexOf(str)));
                    }
                }
            }
        }
        String[] strArr = (String[]) arrayList3.toArray(new String[arrayList3.size()]);
        if (arrayList2 != null) {
            numberArr = (Number[]) arrayList4.toArray(new Number[arrayList4.size()]);
        } else {
            numberArr = null;
        }
        return new b(this, strArr, numberArr);
    }
}
