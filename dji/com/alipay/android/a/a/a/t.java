package com.alipay.android.a.a.a;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

final class t extends FutureTask<ab> {
    final /* synthetic */ x a;
    final /* synthetic */ s b;

    t(s sVar, Callable callable, x xVar) {
        this.b = sVar;
        this.a = xVar;
        super(callable);
    }

    protected final void done() {
        aa a = this.a.a();
        if (a.f() == null) {
            super.done();
            return;
        }
        try {
            get();
            if (isCancelled() || a.h()) {
                a.g();
                if (!isCancelled() || !isDone()) {
                    cancel(false);
                }
            }
        } catch (InterruptedException e) {
            new StringBuilder().append(e);
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof a)) {
                new StringBuilder().append(e2);
                return;
            }
            a aVar = (a) e2.getCause();
            aVar.a();
            aVar.b();
        } catch (CancellationException e3) {
            a.g();
        } catch (Throwable th) {
            RuntimeException runtimeException = new RuntimeException("An error occured while executing http request", th);
        }
    }
}
