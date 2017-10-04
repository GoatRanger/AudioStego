package com.nokia.maps;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Log;
import android.util.Pair;
import android.util.SparseArray;
import com.here.android.mpa.common.Image;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

class c extends AsyncTask<String, Void, byte[]> {
    private static SparseArray<a> d = new SparseArray();
    private static List<Pair<Integer, c>> e = new CopyOnWriteArrayList();
    private static fd f = null;
    final ar a = new ar();
    private volatile String b = null;
    private volatile boolean c = false;

    static class a {
        int[] a;
        int b;
        int c;

        a() {
        }
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((byte[]) obj);
    }

    c() {
    }

    synchronized void a(String str) {
        if (!(this.c || str == null || str.isEmpty())) {
            this.b = e(str);
        }
    }

    synchronized a b(String str) {
        a(str);
        return a();
    }

    @SuppressLint({"NewApi"})
    synchronized a a() {
        a aVar;
        if (this.c) {
            aVar = null;
        } else if (this.b == null || this.b.isEmpty()) {
            throw new IllegalArgumentException("Valid URL has to provided");
        } else if (this.a.b()) {
            throw new IllegalArgumentException("No one is listening for the download:" + this.b);
        } else {
            aVar = c(this.b);
            if (aVar != null) {
                this.a.a(this, aVar);
                d();
            } else {
                Object obj;
                if (f == null) {
                    f = new fd();
                }
                int hashCode = this.b.hashCode();
                synchronized (e) {
                    for (Pair pair : e) {
                        if (((Integer) pair.first).intValue() == hashCode) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    e.add(new Pair(Integer.valueOf(this.b.hashCode()), this));
                }
                if (obj == null) {
                    if (VERSION.SDK_INT >= 11) {
                        executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{this.b});
                    } else {
                        execute(new String[]{this.b});
                    }
                }
                this.c = true;
                aVar = null;
            }
        }
        return aVar;
    }

    synchronized boolean b() {
        boolean z = false;
        synchronized (this) {
            if (this.c) {
                synchronized (e) {
                    for (Pair pair : e) {
                        if (pair.second == this) {
                            e.remove(pair);
                            break;
                        }
                    }
                }
                z = cancel(true);
                this.c = false;
                this.a.a(this, null);
            }
        }
        return z;
    }

    protected byte[] a(String... strArr) {
        if (this.a.b() || strArr.length != 1 || strArr[0].isEmpty()) {
            return null;
        }
        return d(strArr[0]);
    }

    protected void a(final byte[] bArr) {
        f.a(new Runnable(this) {
            final /* synthetic */ c b;

            public void run() {
                this.b.b(bArr);
            }
        });
    }

    private void b(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            int hashCode = this.b.hashCode();
            Image a = bc.a(bArr, bc.b(this.b));
            if (a != null) {
                a aVar = new a();
                aVar.a = ImageImpl.a(a).getImageRawData();
                aVar.b = (int) a.getWidth();
                aVar.c = (int) a.getHeight();
                synchronized (d) {
                    d.put(hashCode, aVar);
                }
                List<Pair> copyOnWriteArrayList = new CopyOnWriteArrayList();
                synchronized (e) {
                    for (Pair pair : e) {
                        if (((Integer) pair.first).intValue() == hashCode) {
                            copyOnWriteArrayList.add(pair);
                        }
                    }
                    for (Pair pair2 : copyOnWriteArrayList) {
                        e.remove(pair2);
                    }
                }
                for (Pair pair22 : copyOnWriteArrayList) {
                    c cVar = (c) pair22.second;
                    cVar.d();
                    cVar.a.a(cVar, aVar);
                }
                copyOnWriteArrayList.clear();
                this.c = false;
            }
        }
    }

    static a c(String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        a aVar;
        synchronized (d) {
            aVar = (a) d.get(str.hashCode());
        }
        return aVar;
    }

    private static synchronized byte[] d(String str) {
        byte[] a;
        synchronized (c.class) {
            try {
                a = a(new URL(str).openStream());
            } catch (Exception e) {
                Log.d(h.a, e.toString());
                e.printStackTrace();
                a = null;
            }
        }
        return a;
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            throw new IOException("InputStream is null");
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            return toByteArray;
        } finally {
            try {
                inputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static synchronized void c() {
        synchronized (c.class) {
            synchronized (d) {
                d.clear();
            }
            List<Pair> copyOnWriteArrayList = new CopyOnWriteArrayList();
            synchronized (e) {
                for (Pair add : e) {
                    copyOnWriteArrayList.add(add);
                }
                e.clear();
            }
            for (Pair add2 : copyOnWriteArrayList) {
                c cVar = (c) add2.second;
                cVar.cancel(true);
                cVar.d();
                cVar.a.a(cVar, null);
            }
            if (f != null) {
                f.a();
                f = null;
            }
        }
    }

    private String e(String str) {
        try {
            URL url = new URL(str);
            try {
                return new URL(url.getProtocol(), url.getHost(), url.getFile().replace("categories", "categories/symbols").replace(".icon", ".svg")).toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private synchronized void d() {
        this.c = false;
    }
}
