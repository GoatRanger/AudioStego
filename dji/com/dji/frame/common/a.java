package com.dji.frame.common;

import android.os.AsyncTask;
import com.dji.frame.b.b;

public class a extends AsyncTask<Object, Object, Object> {
    private b a;

    public a(b bVar) {
        this.a = bVar;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Object doInBackground(Object... objArr) {
        return this.a.a().toString();
    }

    protected void onPostExecute(Object obj) {
        this.a.a(obj);
        super.onPostExecute(obj);
    }
}
