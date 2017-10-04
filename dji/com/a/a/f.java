package com.a.a;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

class f extends AsyncTask<Context, Void, Pair<String, Boolean>> {
    final b a;

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Context[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Pair) obj);
    }

    f(b bVar) {
        this.a = bVar;
    }

    protected Pair<String, Boolean> a(Context... contextArr) {
        Context context = contextArr[0];
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
            return Pair.create((String) invoke.getClass().getMethod("getId", new Class[0]).invoke(invoke, new Object[0]), (Boolean) invoke.getClass().getMethod("isLimitAdTrackingEnabled", new Class[0]).invoke(invoke, new Object[0]));
        } catch (Exception e) {
            return null;
        }
    }

    protected void a(Pair<String, Boolean> pair) {
        super.onPostExecute(pair);
        if (pair != null) {
            this.a.c().a((String) pair.first, ((Boolean) pair.second).booleanValue());
        }
    }
}
