package dji.thirdparty.a;

class a implements Runnable {
    private final h a = new h();
    private final c b;

    a(c cVar) {
        this.b = cVar;
    }

    public void a(l lVar, Object obj) {
        this.a.a(g.a(lVar, obj));
        c.a.execute(this);
    }

    public void run() {
        g a = this.a.a();
        if (a == null) {
            throw new IllegalStateException("No pending post available");
        }
        this.b.a(a);
    }
}
