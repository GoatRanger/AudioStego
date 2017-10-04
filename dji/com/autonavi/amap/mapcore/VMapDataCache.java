package com.autonavi.amap.mapcore;

import java.util.ArrayList;
import java.util.HashMap;

public class VMapDataCache {
    private static final int MAXSIZE = 400;
    private static VMapDataCache instance;
    HashMap<String, e> vCancelMapDataHs = new HashMap();
    ArrayList<String> vCancelMapDataList = new ArrayList();
    HashMap<String, e> vMapDataHs = new HashMap();
    ArrayList<String> vMapDataList = new ArrayList();

    public static VMapDataCache getInstance() {
        if (instance == null) {
            instance = new VMapDataCache();
        }
        return instance;
    }

    public synchronized void reset() {
        this.vMapDataHs.clear();
        this.vMapDataList.clear();
        this.vCancelMapDataHs.clear();
        this.vCancelMapDataList.clear();
    }

    public int getSize() {
        return this.vMapDataHs.size();
    }

    static String getKey(String str, int i) {
        return str + "-" + i;
    }

    public synchronized e getRecoder(String str, int i) {
        e eVar;
        eVar = (e) this.vMapDataHs.get(getKey(str, i));
        if (eVar != null) {
            eVar.d++;
        }
        return eVar;
    }

    public synchronized e getCancelRecoder(String str, int i) {
        e eVar;
        eVar = (e) this.vCancelMapDataHs.get(getKey(str, i));
        if (eVar != null && (System.currentTimeMillis() / 1000) - ((long) eVar.b) > 10) {
            eVar = null;
        }
        return eVar;
    }

    public synchronized e putRecoder(byte[] bArr, String str, int i) {
        e eVar;
        eVar = new e(str, i);
        if (eVar.a == null) {
            eVar = null;
        } else {
            if (this.vMapDataHs.size() > 400) {
                this.vMapDataHs.remove(this.vMapDataList.get(0));
                this.vMapDataList.remove(0);
            }
            this.vMapDataHs.put(getKey(str, i), eVar);
            this.vMapDataList.add(getKey(str, i));
        }
        return eVar;
    }

    public synchronized e putCancelRecoder(byte[] bArr, String str, int i) {
        e eVar = null;
        synchronized (this) {
            if (getRecoder(str, i) == null) {
                e eVar2 = new e(str, i);
                if (eVar2.a != null) {
                    if (this.vCancelMapDataHs.size() > 400) {
                        this.vCancelMapDataHs.remove(this.vMapDataList.get(0));
                        this.vCancelMapDataList.remove(0);
                    }
                    this.vCancelMapDataHs.put(getKey(str, i), eVar2);
                    this.vCancelMapDataList.add(getKey(str, i));
                    eVar = eVar2;
                }
            }
        }
        return eVar;
    }
}
