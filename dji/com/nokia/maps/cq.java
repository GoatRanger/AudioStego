package com.nokia.maps;

import java.util.HashMap;

public class cq {
    private static boolean a = false;
    private static HashMap<String, Integer> b = null;
    private String c = null;

    public cq(String str) {
        if (a) {
            synchronized (b) {
                if (b.containsKey(str)) {
                    b.put(str, Integer.valueOf(((Integer) b.get(str)).intValue() + 1));
                } else {
                    b.put(str, Integer.valueOf(1));
                }
                this.c = str;
            }
        }
    }

    protected void finalize() {
        if (a) {
            synchronized (b) {
                if (b.containsKey(this.c)) {
                    b.remove(this.c);
                }
            }
        }
    }
}
