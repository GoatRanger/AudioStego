package com.tencent.android.tpush.service;

class y implements Comparable {
    public String a = "";
    public float b = 1.0f;
    public long c = 0;

    y() {
    }

    public /* synthetic */ int compareTo(Object obj) {
        return a((y) obj);
    }

    public int a(y yVar) {
        if (this.b > yVar.b) {
            return -1;
        }
        if (this.b < yVar.b) {
            return 1;
        }
        return 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("pkgName:").append(this.a).append(",accid:").append(this.c).append(",ver:").append(this.b);
        return stringBuilder.toString();
    }
}
