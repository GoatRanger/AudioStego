package com.facebook;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import com.alipay.sdk.j.i;
import java.net.HttpURLConnection;
import java.util.Collection;
import java.util.List;

public class t extends AsyncTask<Void, Void, List<v>> {
    private static final String a = t.class.getCanonicalName();
    private final HttpURLConnection b;
    private final u c;
    private Exception d;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((List) obj);
    }

    public t(GraphRequest... graphRequestArr) {
        this(null, new u(graphRequestArr));
    }

    public t(Collection<GraphRequest> collection) {
        this(null, new u((Collection) collection));
    }

    public t(u uVar) {
        this(null, uVar);
    }

    public t(HttpURLConnection httpURLConnection, GraphRequest... graphRequestArr) {
        this(httpURLConnection, new u(graphRequestArr));
    }

    public t(HttpURLConnection httpURLConnection, Collection<GraphRequest> collection) {
        this(httpURLConnection, new u((Collection) collection));
    }

    public t(HttpURLConnection httpURLConnection, u uVar) {
        this.c = uVar;
        this.b = httpURLConnection;
    }

    protected final Exception a() {
        return this.d;
    }

    protected final u b() {
        return this.c;
    }

    public String toString() {
        return "{RequestAsyncTask: " + " connection: " + this.b + ", requests: " + this.c + i.d;
    }

    protected void onPreExecute() {
        super.onPreExecute();
        if (o.d()) {
            Log.d(a, String.format("execute async task: %s", new Object[]{this}));
        }
        if (this.c.c() == null) {
            Handler handler;
            if (Thread.currentThread() instanceof HandlerThread) {
                handler = new Handler();
            } else {
                handler = new Handler(Looper.getMainLooper());
            }
            this.c.a(handler);
        }
    }

    protected void a(List<v> list) {
        super.onPostExecute(list);
        if (this.d != null) {
            Log.d(a, String.format("onPostExecute: exception encountered during request: %s", new Object[]{this.d.getMessage()}));
        }
    }

    protected List<v> a(Void... voidArr) {
        try {
            if (this.b == null) {
                return this.c.g();
            }
            return GraphRequest.a(this.b, this.c);
        } catch (Exception e) {
            this.d = e;
            return null;
        }
    }
}
