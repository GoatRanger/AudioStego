package com.facebook;

import android.os.Handler;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class u extends AbstractList<GraphRequest> {
    private static AtomicInteger a = new AtomicInteger();
    private Handler b;
    private List<GraphRequest> c;
    private int d;
    private final String e;
    private List<a> f;
    private String g;

    public interface a {
        void a(u uVar);
    }

    public interface b extends a {
        void a(u uVar, long j, long j2);
    }

    public /* synthetic */ void add(int i, Object obj) {
        a(i, (GraphRequest) obj);
    }

    public /* synthetic */ boolean add(Object obj) {
        return a((GraphRequest) obj);
    }

    public /* synthetic */ Object get(int i) {
        return b(i);
    }

    public /* synthetic */ Object remove(int i) {
        return c(i);
    }

    public /* synthetic */ Object set(int i, Object obj) {
        return b(i, (GraphRequest) obj);
    }

    public u() {
        this.c = new ArrayList();
        this.d = 0;
        this.e = Integer.valueOf(a.incrementAndGet()).toString();
        this.f = new ArrayList();
        this.c = new ArrayList();
    }

    public u(Collection<GraphRequest> collection) {
        this.c = new ArrayList();
        this.d = 0;
        this.e = Integer.valueOf(a.incrementAndGet()).toString();
        this.f = new ArrayList();
        this.c = new ArrayList(collection);
    }

    public u(GraphRequest... graphRequestArr) {
        this.c = new ArrayList();
        this.d = 0;
        this.e = Integer.valueOf(a.incrementAndGet()).toString();
        this.f = new ArrayList();
        this.c = Arrays.asList(graphRequestArr);
    }

    public u(u uVar) {
        this.c = new ArrayList();
        this.d = 0;
        this.e = Integer.valueOf(a.incrementAndGet()).toString();
        this.f = new ArrayList();
        this.c = new ArrayList(uVar);
        this.b = uVar.b;
        this.d = uVar.d;
        this.f = new ArrayList(uVar.f);
    }

    public int a() {
        return this.d;
    }

    public void a(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Argument timeoutInMilliseconds must be >= 0.");
        }
        this.d = i;
    }

    public void a(a aVar) {
        if (!this.f.contains(aVar)) {
            this.f.add(aVar);
        }
    }

    public void b(a aVar) {
        this.f.remove(aVar);
    }

    public final boolean a(GraphRequest graphRequest) {
        return this.c.add(graphRequest);
    }

    public final void a(int i, GraphRequest graphRequest) {
        this.c.add(i, graphRequest);
    }

    public final void clear() {
        this.c.clear();
    }

    public final GraphRequest b(int i) {
        return (GraphRequest) this.c.get(i);
    }

    public final GraphRequest c(int i) {
        return (GraphRequest) this.c.remove(i);
    }

    public final GraphRequest b(int i, GraphRequest graphRequest) {
        return (GraphRequest) this.c.set(i, graphRequest);
    }

    public final int size() {
        return this.c.size();
    }

    final String b() {
        return this.e;
    }

    final Handler c() {
        return this.b;
    }

    final void a(Handler handler) {
        this.b = handler;
    }

    final List<GraphRequest> d() {
        return this.c;
    }

    final List<a> e() {
        return this.f;
    }

    public final String f() {
        return this.g;
    }

    public final void a(String str) {
        this.g = str;
    }

    public final List<v> g() {
        return i();
    }

    public final t h() {
        return j();
    }

    List<v> i() {
        return GraphRequest.b(this);
    }

    t j() {
        return GraphRequest.c(this);
    }
}
