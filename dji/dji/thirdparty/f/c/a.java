package dji.thirdparty.f.c;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public final class a extends RuntimeException {
    private static final long a = 3026362227162912146L;
    private final List<Throwable> b;
    private final String c;
    private Throwable d;

    static final class a extends RuntimeException {
        static String a = "Chain of Causes for CompositeException In Order Received =>";
        private static final long b = 3875212506787802066L;

        a() {
        }

        public String getMessage() {
            return a;
        }
    }

    private static abstract class b {
        abstract Object a();

        abstract void a(Object obj);

        private b() {
        }
    }

    private static class c extends b {
        private final PrintStream a;

        c(PrintStream printStream) {
            super();
            this.a = printStream;
        }

        Object a() {
            return this.a;
        }

        void a(Object obj) {
            this.a.println(obj);
        }
    }

    private static class d extends b {
        private final PrintWriter a;

        d(PrintWriter printWriter) {
            super();
            this.a = printWriter;
        }

        Object a() {
            return this.a;
        }

        void a(Object obj) {
            this.a.println(obj);
        }
    }

    public a(String str, Collection<? extends Throwable> collection) {
        this.d = null;
        Collection linkedHashSet = new LinkedHashSet();
        List arrayList = new ArrayList();
        if (collection != null) {
            for (Throwable th : collection) {
                if (th instanceof a) {
                    linkedHashSet.addAll(((a) th).a());
                } else if (th != null) {
                    linkedHashSet.add(th);
                } else {
                    linkedHashSet.add(new NullPointerException());
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException());
        }
        arrayList.addAll(linkedHashSet);
        this.b = Collections.unmodifiableList(arrayList);
        this.c = this.b.size() + " exceptions occurred. ";
    }

    public a(Collection<? extends Throwable> collection) {
        this(null, collection);
    }

    @dji.thirdparty.f.b.b
    public a(Throwable... thArr) {
        this.d = null;
        Collection linkedHashSet = new LinkedHashSet();
        List arrayList = new ArrayList();
        if (thArr != null) {
            for (Object obj : thArr) {
                if (obj instanceof a) {
                    linkedHashSet.addAll(((a) obj).a());
                } else if (obj != null) {
                    linkedHashSet.add(obj);
                } else {
                    linkedHashSet.add(new NullPointerException());
                }
            }
        } else {
            linkedHashSet.add(new NullPointerException());
        }
        arrayList.addAll(linkedHashSet);
        this.b = Collections.unmodifiableList(arrayList);
        this.c = this.b.size() + " exceptions occurred. ";
    }

    public List<Throwable> a() {
        return this.b;
    }

    public String getMessage() {
        return this.c;
    }

    public synchronized Throwable getCause() {
        if (this.d == null) {
            Throwable aVar = new a();
            Set hashSet = new HashSet();
            Throwable th = aVar;
            for (Throwable th2 : this.b) {
                Throwable th22;
                if (!hashSet.contains(th22)) {
                    hashSet.add(th22);
                    Throwable th3 = th22;
                    for (Throwable th222 : a(th222)) {
                        if (hashSet.contains(th222)) {
                            th3 = new RuntimeException("Duplicate found in causal chain so cropping to prevent loop ...");
                        } else {
                            hashSet.add(th222);
                        }
                    }
                    try {
                        th.initCause(th3);
                        th222 = th.getCause();
                    } catch (Throwable th4) {
                        th222 = th3;
                    }
                    th = th222;
                }
            }
            this.d = aVar;
        }
        return this.d;
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        a(new c(printStream));
    }

    public void printStackTrace(PrintWriter printWriter) {
        a(new d(printWriter));
    }

    private void a(b bVar) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this).append("\n");
        for (Object append : getStackTrace()) {
            stringBuilder.append("\tat ").append(append).append("\n");
        }
        int i = 1;
        for (Throwable th : this.b) {
            stringBuilder.append("  ComposedException ").append(i).append(" :").append("\n");
            a(stringBuilder, th, "\t");
            i++;
        }
        synchronized (bVar.a()) {
            bVar.a(stringBuilder.toString());
        }
    }

    private void a(StringBuilder stringBuilder, Throwable th, String str) {
        stringBuilder.append(str).append(th).append("\n");
        for (Object append : th.getStackTrace()) {
            stringBuilder.append("\t\tat ").append(append).append("\n");
        }
        if (th.getCause() != null) {
            stringBuilder.append("\tCaused by: ");
            a(stringBuilder, th.getCause(), "");
        }
    }

    private List<Throwable> a(Throwable th) {
        List<Throwable> arrayList = new ArrayList();
        Throwable cause = th.getCause();
        if (cause == null) {
            return arrayList;
        }
        while (true) {
            arrayList.add(cause);
            if (cause.getCause() == null) {
                return arrayList;
            }
            cause = cause.getCause();
        }
    }
}
