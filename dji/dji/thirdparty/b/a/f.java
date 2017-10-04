package dji.thirdparty.b.a;

public abstract class f implements Runnable {
    protected final String b;

    protected abstract void f();

    public f(String str, Object... objArr) {
        this.b = String.format(str, objArr);
    }

    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.b);
        try {
            f();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
