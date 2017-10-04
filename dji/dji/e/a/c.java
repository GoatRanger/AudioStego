package dji.e.a;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Vector;

public class c {
    private static final String a = "StateMachine";
    public static final boolean c = true;
    public static final boolean d = false;
    private static final int e = -1;
    private static final int f = -2;
    private String b;
    private c g;
    private HandlerThread h;

    public static class a {
        private long a;
        private int b;
        private String c;
        private b d;
        private b e;

        a(Message message, String str, b bVar, b bVar2) {
            a(message, str, bVar, bVar2);
        }

        public void a(Message message, String str, b bVar, b bVar2) {
            this.a = System.currentTimeMillis();
            this.b = message != null ? message.what : 0;
            this.c = str;
            this.d = bVar;
            this.e = bVar2;
        }

        public long a() {
            return this.a;
        }

        public long b() {
            return (long) this.b;
        }

        public String c() {
            return this.c;
        }

        public b d() {
            return this.d;
        }

        public b e() {
            return this.e;
        }

        public String a(c cVar) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("time=");
            Calendar.getInstance().setTimeInMillis(this.a);
            stringBuilder.append(String.format("%tm-%td %tH:%tM:%tS.%tL", new Object[]{r0, r0, r0, r0, r0, r0}));
            stringBuilder.append(" state=");
            stringBuilder.append(this.d == null ? "<null>" : this.d.c());
            stringBuilder.append(" orgState=");
            stringBuilder.append(this.e == null ? "<null>" : this.e.c());
            stringBuilder.append(" what=");
            Object d = cVar.d(this.b);
            if (TextUtils.isEmpty(d)) {
                stringBuilder.append(this.b);
                stringBuilder.append("(0x");
                stringBuilder.append(Integer.toHexString(this.b));
                stringBuilder.append(")");
            } else {
                stringBuilder.append(d);
            }
            if (!TextUtils.isEmpty(this.c)) {
                stringBuilder.append(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR);
                stringBuilder.append(this.c);
            }
            return stringBuilder.toString();
        }
    }

    private static class b {
        private static final int a = 20;
        private Vector<a> b;
        private int c;
        private int d;
        private int e;

        private b() {
            this.b = new Vector();
            this.c = 20;
            this.d = 0;
            this.e = 0;
        }

        synchronized void a(int i) {
            this.c = i;
            this.e = 0;
            this.b.clear();
        }

        synchronized int a() {
            return this.b.size();
        }

        synchronized int b() {
            return this.e;
        }

        synchronized void c() {
            this.b.clear();
        }

        synchronized a b(int i) {
            a aVar;
            int i2 = this.d + i;
            if (i2 >= this.c) {
                i2 -= this.c;
            }
            if (i2 >= a()) {
                aVar = null;
            } else {
                aVar = (a) this.b.get(i2);
            }
            return aVar;
        }

        synchronized void a(Message message, String str, b bVar, b bVar2) {
            this.e++;
            if (this.b.size() < this.c) {
                this.b.add(new a(message, str, bVar, bVar2));
            } else {
                a aVar = (a) this.b.get(this.d);
                this.d++;
                if (this.d >= this.c) {
                    this.d = 0;
                }
                aVar.a(message, str, bVar, bVar2);
            }
        }
    }

    private static class c extends Handler {
        private static final Object b = new Object();
        private boolean a;
        private Message c;
        private b d;
        private boolean e;
        private c[] f;
        private int g;
        private c[] h;
        private int i;
        private a j;
        private b k;
        private c l;
        private HashMap<b, c> m;
        private b n;
        private b o;
        private ArrayList<Message> p;

        private class a extends b {
            final /* synthetic */ c a;

            private a(c cVar) {
                this.a = cVar;
            }

            public boolean a(Message message) {
                this.a.l.c(message);
                return true;
            }
        }

        private class b extends b {
            final /* synthetic */ c a;

            private b(c cVar) {
                this.a = cVar;
            }

            public boolean a(Message message) {
                return false;
            }
        }

        private class c {
            b a;
            c b;
            boolean c;
            final /* synthetic */ c d;

            private c(c cVar) {
                this.d = cVar;
            }

            public String toString() {
                String str;
                StringBuilder append = new StringBuilder().append("state=").append(this.a.c()).append(",active=").append(this.c).append(",parent=");
                if (this.b == null) {
                    str = "null";
                } else {
                    str = this.b.a.c();
                }
                return append.append(str).toString();
            }
        }

        public final void handleMessage(Message message) {
            if (this.a) {
                Log.d(c.a, "handleMessage: E msg.what=" + message.what);
            }
            this.c = message;
            if (this.e) {
                a(message);
            } else if (!this.e && this.c.what == -2 && this.c.obj == b) {
                this.e = true;
                a(0);
            } else {
                throw new RuntimeException("StateMachine.handleMessage: The start method not called, received msg: " + message);
            }
            a();
            if (this.a) {
                Log.d(c.a, "handleMessage: X");
            }
        }

        private void a() {
            b bVar = null;
            while (this.o != null) {
                if (this.a) {
                    Log.d(c.a, "handleMessage: new destination call exit");
                }
                bVar = this.o;
                this.o = null;
                a(a(bVar));
                a(e());
                d();
            }
            if (bVar == null) {
                return;
            }
            if (bVar == this.k) {
                this.l.C();
                b();
            } else if (bVar == this.j) {
                this.l.B();
            }
        }

        private final void b() {
            if (this.l.h != null) {
                getLooper().quit();
                this.l.h = null;
            }
            this.l.g = null;
            this.l = null;
            this.c = null;
            this.d.c();
            this.f = null;
            this.h = null;
            this.m.clear();
            this.n = null;
            this.o = null;
            this.p.clear();
        }

        private final void c() {
            if (this.a) {
                Log.d(c.a, "completeConstruction: E");
            }
            int i = 0;
            for (c cVar : this.m.values()) {
                int i2 = 0;
                c cVar2;
                while (cVar2 != null) {
                    cVar2 = cVar2.b;
                    i2++;
                }
                if (i >= i2) {
                    i2 = i;
                }
                i = i2;
            }
            if (this.a) {
                Log.d(c.a, "completeConstruction: maxDepth=" + i);
            }
            this.f = new c[i];
            this.h = new c[i];
            f();
            sendMessageAtFrontOfQueue(obtainMessage(-2, b));
            if (this.a) {
                Log.d(c.a, "completeConstruction: X");
            }
        }

        private final void a(Message message) {
            c cVar = this.f[this.g];
            if (this.a) {
                Log.d(c.a, "processMsg: " + cVar.a.c());
            }
            if (c(message)) {
                a(this.k);
                return;
            }
            while (!cVar.a.a(message)) {
                cVar = cVar.b;
                if (cVar == null) {
                    this.l.b(message);
                    break;
                } else if (this.a) {
                    Log.d(c.a, "processMsg: " + cVar.a.c());
                }
            }
            if (!this.l.d(message)) {
                return;
            }
            if (cVar != null) {
                this.d.a(message, this.l.e(message), cVar.a, this.f[this.g].a);
                return;
            }
            this.d.a(message, this.l.e(message), null, null);
        }

        private final void a(c cVar) {
            while (this.g >= 0 && this.f[this.g] != cVar) {
                b bVar = this.f[this.g].a;
                if (this.a) {
                    Log.d(c.a, "invokeExitMethods: " + bVar.c());
                }
                bVar.b();
                this.f[this.g].c = false;
                this.g--;
            }
        }

        private final void a(int i) {
            while (i <= this.g) {
                if (this.a) {
                    Log.d(c.a, "invokeEnterMethods: " + this.f[i].a.c());
                }
                this.f[i].a.a();
                this.f[i].c = true;
                i++;
            }
        }

        private final void d() {
            for (int size = this.p.size() - 1; size >= 0; size--) {
                Message message = (Message) this.p.get(size);
                if (this.a) {
                    Log.d(c.a, "moveDeferredMessageAtFrontOfQueue; what=" + message.what);
                }
                sendMessageAtFrontOfQueue(message);
            }
            this.p.clear();
        }

        private final int e() {
            int i = this.g + 1;
            int i2 = i;
            for (int i3 = this.i - 1; i3 >= 0; i3--) {
                if (this.a) {
                    Log.d(c.a, "moveTempStackToStateStack: i=" + i3 + ",j=" + i2);
                }
                this.f[i2] = this.h[i3];
                i2++;
            }
            this.g = i2 - 1;
            if (this.a) {
                Log.d(c.a, "moveTempStackToStateStack: X mStateStackTop=" + this.g + ",startingIndex=" + i + ",Top=" + this.f[this.g].a.c());
            }
            return i;
        }

        private final c a(b bVar) {
            this.i = 0;
            c cVar = (c) this.m.get(bVar);
            do {
                c[] cVarArr = this.h;
                int i = this.i;
                this.i = i + 1;
                cVarArr[i] = cVar;
                cVar = cVar.b;
                if (cVar == null) {
                    break;
                }
            } while (!cVar.c);
            if (this.a) {
                Log.d(c.a, "setupTempStateStackWithStatesToEnter: X mTempStateStackCount=" + this.i + ",curStateInfo: " + cVar);
            }
            return cVar;
        }

        private final void f() {
            if (this.a) {
                Log.d(c.a, "setupInitialStateStack: E mInitialState=" + this.n.c());
            }
            c cVar = (c) this.m.get(this.n);
            this.i = 0;
            while (cVar != null) {
                this.h[this.i] = cVar;
                cVar = cVar.b;
                this.i++;
            }
            this.g = -1;
            e();
        }

        private final Message g() {
            return this.c;
        }

        private final a h() {
            return this.f[this.g].a;
        }

        private final c a(b bVar, b bVar2) {
            c cVar;
            c a;
            if (this.a) {
                String str;
                String str2 = c.a;
                StringBuilder append = new StringBuilder().append("addStateInternal: E state=").append(bVar.c()).append(",parent=");
                if (bVar2 == null) {
                    str = "";
                } else {
                    str = bVar2.c();
                }
                Log.d(str2, append.append(str).toString());
            }
            if (bVar2 != null) {
                cVar = (c) this.m.get(bVar2);
                a = cVar == null ? a(bVar2, null) : cVar;
            } else {
                a = null;
            }
            cVar = (c) this.m.get(bVar);
            if (cVar == null) {
                cVar = new c();
                this.m.put(bVar, cVar);
            }
            if (cVar.b == null || cVar.b == a) {
                cVar.a = bVar;
                cVar.b = a;
                cVar.c = false;
                if (this.a) {
                    Log.d(c.a, "addStateInternal: X stateInfo: " + cVar);
                }
                return cVar;
            }
            throw new RuntimeException("state already added");
        }

        private c(Looper looper, c cVar) {
            super(looper);
            this.a = false;
            this.d = new b();
            this.g = -1;
            this.j = new a();
            this.k = new b();
            this.m = new HashMap();
            this.p = new ArrayList();
            this.l = cVar;
            a(this.j, null);
            a(this.k, null);
        }

        private final void b(b bVar) {
            if (this.a) {
                Log.d(c.a, "setInitialState: initialState=" + bVar.c());
            }
            this.n = bVar;
        }

        private final void a(a aVar) {
            this.o = (b) aVar;
            if (this.a) {
                Log.d(c.a, "transitionTo: destState=" + this.o.c());
            }
        }

        private final void b(Message message) {
            if (this.a) {
                Log.d(c.a, "deferMessage: msg=" + message.what);
            }
            Message obtainMessage = obtainMessage();
            obtainMessage.copyFrom(message);
            this.p.add(obtainMessage);
        }

        private final void i() {
            if (this.a) {
                Log.d(c.a, "quit:");
            }
            sendMessage(obtainMessage(-1, b));
        }

        private final void j() {
            if (this.a) {
                Log.d(c.a, "abort:");
            }
            sendMessageAtFrontOfQueue(obtainMessage(-1, b));
        }

        private final boolean c(Message message) {
            return message.what == -1 && message.obj == b;
        }

        private final boolean k() {
            return this.a;
        }

        private final void a(boolean z) {
            this.a = z;
        }
    }

    private void a(String str, Looper looper) {
        this.b = str;
        this.g = new c(looper, this);
    }

    protected c(String str) {
        this.h = new HandlerThread(str);
        this.h.start();
        a(str, this.h.getLooper());
    }

    protected c(String str, Looper looper) {
        a(str, looper);
    }

    protected final void a(b bVar, b bVar2) {
        this.g.a(bVar, bVar2);
    }

    protected final Message y() {
        return this.g.g();
    }

    protected final a z() {
        return this.g.h();
    }

    protected final void a(b bVar) {
        this.g.a(bVar, null);
    }

    protected final void b(b bVar) {
        this.g.b(bVar);
    }

    protected final void a(a aVar) {
        this.g.a(aVar);
    }

    protected final void A() {
        this.g.a(this.g.j);
    }

    protected final void a(Message message) {
        this.g.b(message);
    }

    protected void b(Message message) {
        if (this.g.a) {
            Log.e(a, this.b + " - unhandledMessage: msg.what=" + message.what);
        }
    }

    protected void c(Message message) {
    }

    protected void B() {
    }

    protected void C() {
    }

    public final String D() {
        return this.b;
    }

    public final void b(int i) {
        this.g.d.a(i);
    }

    public final int E() {
        return this.g.d.a();
    }

    public final int F() {
        return this.g.d.b();
    }

    public final a c(int i) {
        return this.g.d.b(i);
    }

    protected void c(String str) {
        this.g.d.a(null, str, null, null);
    }

    protected void a(String str, b bVar) {
        this.g.d.a(null, str, bVar, null);
    }

    protected boolean d(Message message) {
        return true;
    }

    protected String e(Message message) {
        return "";
    }

    protected String d(int i) {
        return null;
    }

    public final Handler G() {
        return this.g;
    }

    public final Message H() {
        if (this.g == null) {
            return null;
        }
        return Message.obtain(this.g);
    }

    public final Message e(int i) {
        if (this.g == null) {
            return null;
        }
        return Message.obtain(this.g, i);
    }

    public final Message a(int i, Object obj) {
        if (this.g == null) {
            return null;
        }
        return Message.obtain(this.g, i, obj);
    }

    public final Message a(int i, int i2, int i3) {
        if (this.g == null) {
            return null;
        }
        return Message.obtain(this.g, i, i2, i3);
    }

    public final Message a(int i, int i2, int i3, Object obj) {
        if (this.g == null) {
            return null;
        }
        return Message.obtain(this.g, i, i2, i3, obj);
    }

    public final void f(int i) {
        if (this.g != null) {
            this.g.sendMessage(e(i));
        }
    }

    public final void b(int i, Object obj) {
        if (this.g != null) {
            this.g.sendMessage(a(i, obj));
        }
    }

    public final void f(Message message) {
        if (this.g != null) {
            this.g.sendMessage(message);
        }
    }

    public final void a(int i, long j) {
        if (this.g != null) {
            this.g.sendMessageDelayed(e(i), j);
        }
    }

    public final void a(int i, Object obj, long j) {
        if (this.g != null) {
            this.g.sendMessageDelayed(a(i, obj), j);
        }
    }

    public final void a(Message message, long j) {
        if (this.g != null) {
            this.g.sendMessageDelayed(message, j);
        }
    }

    protected final void c(int i, Object obj) {
        this.g.sendMessageAtFrontOfQueue(a(i, obj));
    }

    protected final void g(int i) {
        this.g.sendMessageAtFrontOfQueue(e(i));
    }

    protected final void g(Message message) {
        this.g.sendMessageAtFrontOfQueue(message);
    }

    protected final void h(int i) {
        this.g.removeMessages(i);
    }

    protected final void I() {
        if (this.g != null) {
            this.g.i();
        }
    }

    protected final void J() {
        if (this.g != null) {
            this.g.j();
        }
    }

    public boolean K() {
        if (this.g == null) {
            return false;
        }
        return this.g.k();
    }

    public void a(boolean z) {
        if (this.g != null) {
            this.g.a(z);
        }
    }

    public void a() {
        if (this.g != null) {
            this.g.c();
        }
    }

    public void a(FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.println(D() + ":");
        printWriter.println(" total records=" + F());
        for (int i = 0; i < E(); i++) {
            printWriter.printf(" rec[%d]: %s\n", new Object[]{Integer.valueOf(i), c(i).a(this)});
            printWriter.flush();
        }
        printWriter.println("curState=" + z().c());
    }
}
