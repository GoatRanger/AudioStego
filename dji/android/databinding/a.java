package android.databinding;

public class a implements s {
    private transient z a;

    public synchronized void a(android.databinding.s.a aVar) {
        if (this.a == null) {
            this.a = new z();
        }
        this.a.a((Object) aVar);
    }

    public synchronized void b(android.databinding.s.a aVar) {
        if (this.a != null) {
            this.a.b((Object) aVar);
        }
    }

    public synchronized void a() {
        if (this.a != null) {
            this.a.a(this, 0, null);
        }
    }

    public void a(int i) {
        if (this.a != null) {
            this.a.a(this, i, null);
        }
    }
}
