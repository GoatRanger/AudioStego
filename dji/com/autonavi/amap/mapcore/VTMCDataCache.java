package com.autonavi.amap.mapcore;

import java.util.ArrayList;
import java.util.Hashtable;

public class VTMCDataCache {
    public static final int MAXSIZE = 500;
    public static final int MAX_EXPIREDTIME = 300;
    private static VTMCDataCache instance;
    static Hashtable<String, f> vtmcHs = new Hashtable();
    static ArrayList<String> vtmcList = new ArrayList();
    public int mNewestTimeStamp = 0;

    public static VTMCDataCache getInstance() {
        if (instance == null) {
            instance = new VTMCDataCache();
        }
        return instance;
    }

    public void reset() {
        vtmcList.clear();
        vtmcHs.clear();
    }

    public int getSize() {
        return vtmcList.size();
    }

    private void deleteData(String str) {
        vtmcHs.remove(str);
        for (int i = 0; i < vtmcList.size(); i++) {
            if (((String) vtmcList.get(i)).equals(str)) {
                vtmcList.remove(i);
                return;
            }
        }
    }

    public synchronized f getData(String str, boolean z) {
        f fVar;
        fVar = (f) vtmcHs.get(str);
        if (!z) {
            if (fVar == null) {
                fVar = null;
            } else if (((int) (System.currentTimeMillis() / 1000)) - fVar.c > 300) {
                fVar = null;
            } else if (this.mNewestTimeStamp > fVar.e) {
                fVar = null;
            }
        }
        return fVar;
    }

    public synchronized f putData(byte[] bArr) {
        f fVar;
        f fVar2 = new f(bArr);
        if (this.mNewestTimeStamp < fVar2.e) {
            this.mNewestTimeStamp = fVar2.e;
        }
        fVar = (f) vtmcHs.get(fVar2.b);
        if (fVar != null) {
            if (fVar.d.equals(fVar2.d)) {
                fVar.a(this.mNewestTimeStamp);
            } else {
                deleteData(fVar2.b);
            }
        }
        if (vtmcList.size() > 500) {
            vtmcHs.remove(vtmcList.get(0));
            vtmcList.remove(0);
        }
        vtmcHs.put(fVar2.b, fVar2);
        vtmcList.add(fVar2.b);
        fVar = fVar2;
        return fVar;
    }
}
