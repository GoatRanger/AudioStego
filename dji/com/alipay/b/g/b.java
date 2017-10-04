package com.alipay.b.g;

class b implements Runnable {
    final /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    public void run() {
        while (!this.a.f.isEmpty()) {
            try {
                b bVar = (b) this.a.f.pollFirst();
                if (bVar != null) {
                    bVar.a();
                }
            } catch (Throwable th) {
            } finally {
                this.a.e = null;
            }
        }
    }
}
