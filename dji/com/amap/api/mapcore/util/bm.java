package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Handler;
import com.amap.api.maps.offlinemap.OfflineMapCity;
import com.amap.api.maps.offlinemap.OfflineMapProvince;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class bm {
    public ArrayList<OfflineMapProvince> a = new ArrayList();
    private bx b;
    private Context c;
    private Handler d;

    public bm(Context context, Handler handler) {
        this.c = context;
        this.d = handler;
        this.b = bx.a(context);
    }

    private void a(bs bsVar) {
        if (this.b != null && bsVar != null) {
            this.b.a(bsVar);
        }
    }

    private void d(String str) {
        if (this.b != null) {
            this.b.c(str);
        }
    }

    private boolean a(int i, int i2) {
        return i2 != 1 || i <= 2 || i >= 98;
    }

    private boolean b(int i) {
        if (i == 4) {
            return true;
        }
        return false;
    }

    private boolean a(OfflineMapProvince offlineMapProvince) {
        if (offlineMapProvince == null) {
            return false;
        }
        Iterator it = offlineMapProvince.getCityList().iterator();
        while (it.hasNext()) {
            if (((OfflineMapCity) it.next()).getState() != 4) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<OfflineMapProvince> a() {
        ArrayList<OfflineMapProvince> arrayList = new ArrayList();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            arrayList.add((OfflineMapProvince) it.next());
        }
        return arrayList;
    }

    public OfflineMapCity a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                OfflineMapCity offlineMapCity = (OfflineMapCity) it2.next();
                if (offlineMapCity.getCode().equals(str)) {
                    return offlineMapCity;
                }
            }
        }
        return null;
    }

    public OfflineMapCity b(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                OfflineMapCity offlineMapCity = (OfflineMapCity) it2.next();
                if (offlineMapCity.getCity().trim().equalsIgnoreCase(str.trim())) {
                    return offlineMapCity;
                }
            }
        }
        return null;
    }

    public OfflineMapProvince c(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
            if (offlineMapProvince.getProvinceName().trim().equalsIgnoreCase(str.trim())) {
                return offlineMapProvince;
            }
        }
        return null;
    }

    public ArrayList<OfflineMapCity> b() {
        ArrayList<OfflineMapCity> arrayList = new ArrayList();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            Iterator it2 = ((OfflineMapProvince) it.next()).getCityList().iterator();
            while (it2.hasNext()) {
                arrayList.add((OfflineMapCity) it2.next());
            }
        }
        return arrayList;
    }

    public void a(List<OfflineMapProvince> list) {
        if (this.a.size() > 0) {
            for (int i = 0; i < this.a.size(); i++) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) this.a.get(i);
                OfflineMapProvince offlineMapProvince2 = (OfflineMapProvince) list.get(i);
                a(offlineMapProvince, offlineMapProvince2);
                ArrayList cityList = offlineMapProvince.getCityList();
                ArrayList cityList2 = offlineMapProvince2.getCityList();
                for (int i2 = 0; i2 < cityList.size(); i2++) {
                    a((OfflineMapCity) cityList.get(i2), (OfflineMapCity) cityList2.get(i2));
                }
            }
            return;
        }
        for (OfflineMapProvince offlineMapProvince3 : list) {
            this.a.add(offlineMapProvince3);
        }
    }

    private void a(OfflineMapCity offlineMapCity, OfflineMapCity offlineMapCity2) {
        offlineMapCity.setUrl(offlineMapCity2.getUrl());
        offlineMapCity.setVersion(offlineMapCity2.getVersion());
    }

    private void a(OfflineMapProvince offlineMapProvince, OfflineMapProvince offlineMapProvince2) {
        offlineMapProvince.setUrl(offlineMapProvince2.getUrl());
        offlineMapProvince.setVersion(offlineMapProvince2.getVersion());
    }

    public ArrayList<OfflineMapCity> c() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                for (OfflineMapCity offlineMapCity : ((OfflineMapProvince) it.next()).getCityList()) {
                    if (offlineMapCity.getState() == 4) {
                        arrayList.add(offlineMapCity);
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapProvince> d() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (offlineMapProvince.getState() == 4) {
                    arrayList.add(offlineMapProvince);
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapCity> e() {
        ArrayList<OfflineMapCity> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                for (OfflineMapCity offlineMapCity : ((OfflineMapProvince) it.next()).getCityList()) {
                    if (a(offlineMapCity.getState())) {
                        arrayList.add(offlineMapCity);
                    }
                }
            }
        }
        return arrayList;
    }

    public ArrayList<OfflineMapProvince> f() {
        ArrayList<OfflineMapProvince> arrayList;
        synchronized (this.a) {
            arrayList = new ArrayList();
            Iterator it = this.a.iterator();
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                if (a(offlineMapProvince.getState())) {
                    arrayList.add(offlineMapProvince);
                }
            }
        }
        return arrayList;
    }

    public boolean a(int i) {
        return i == 0 || i == 2 || i == 3 || i == 1;
    }

    public void a(bg bgVar) {
        String adcode = bgVar.getAdcode();
        synchronized (this.a) {
            Iterator it = this.a.iterator();
            loop0:
            while (it.hasNext()) {
                OfflineMapProvince offlineMapProvince = (OfflineMapProvince) it.next();
                for (OfflineMapCity offlineMapCity : offlineMapProvince.getCityList()) {
                    if (offlineMapCity.getAdcode().trim().equals(adcode.trim())) {
                        a(bgVar, offlineMapCity);
                        a(bgVar, offlineMapProvince);
                        break loop0;
                    }
                }
            }
        }
    }

    private void a(bg bgVar, OfflineMapCity offlineMapCity) {
        int b = bgVar.c().b();
        if (bgVar.c().equals(bgVar.a)) {
            d(bgVar.getAdcode());
        } else {
            if (bgVar.c().equals(bgVar.f)) {
                cf.a("saveJSONObjectToFile  CITY " + bgVar.getCity());
                bgVar.w().d();
            }
            if (a(bgVar.getcompleteCode(), bgVar.c().b())) {
                a(bgVar.w());
            }
        }
        offlineMapCity.setState(b);
        offlineMapCity.setCompleteCode(bgVar.getcompleteCode());
    }

    private void a(bg bgVar, OfflineMapProvince offlineMapProvince) {
        int b = bgVar.c().b();
        if (b == 6) {
            offlineMapProvince.setState(b);
            offlineMapProvince.setCompleteCode(0);
            d(offlineMapProvince.getProvinceCode());
            try {
                cf.a(offlineMapProvince.getProvinceCode(), this.c);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else if (b(b) && a(offlineMapProvince)) {
            bs bsVar;
            if (bgVar.getAdcode().equals(offlineMapProvince.getProvinceCode())) {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(bgVar.getcompleteCode());
                offlineMapProvince.setVersion(bgVar.getVersion());
                offlineMapProvince.setUrl(bgVar.getUrl());
                bsVar = new bs(offlineMapProvince, this.c);
                bsVar.a(bgVar.a());
                bsVar.c(bgVar.getCode());
            } else {
                offlineMapProvince.setState(b);
                offlineMapProvince.setCompleteCode(100);
                bsVar = new bs(offlineMapProvince, this.c);
            }
            bsVar.d();
            a(bsVar);
            cf.a("saveJSONObjectToFile  province " + bsVar.e());
        }
    }

    public void g() {
        h();
        this.d = null;
        this.b = null;
        this.c = null;
    }

    public void h() {
        this.a.clear();
    }
}
