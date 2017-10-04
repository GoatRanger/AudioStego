package com.nokia.maps;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.here.android.mpa.search.Category;
import com.here.android.mpa.search.ErrorCode;
import com.here.android.mpa.search.ResultListener;
import com.nokia.maps.MapsEngine.h;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class PlacesCategoryGraph {
    private static boolean a = false;
    private static a b = null;
    private final Semaphore c;
    private CategoryGraphData d;
    private b e;

    private class a extends Thread {
        final /* synthetic */ PlacesCategoryGraph a;
        private PlacesCategoryGraphRequest b;
        private boolean c = false;

        public a(PlacesCategoryGraph placesCategoryGraph) {
            this.a = placesCategoryGraph;
        }

        public void a() {
            if (this.b != null) {
                this.b.c();
                this.b = null;
            }
            this.c = true;
        }

        public void run() {
            ErrorCode errorCode = ErrorCode.NONE;
            try {
                this.a.c.acquire();
                final String b = du.b();
                this.b = PlacesApi.a().c(b);
                this.c = false;
                errorCode = this.b.a(new ResultListener<CategoryGraphData>(this) {
                    final /* synthetic */ a b;

                    public /* synthetic */ void onCompleted(Object obj, ErrorCode errorCode) {
                        a((CategoryGraphData) obj, errorCode);
                    }

                    public void a(CategoryGraphData categoryGraphData, ErrorCode errorCode) {
                        if (this.b.b != null && !this.b.c && this.b.a.c != null) {
                            this.b.a.c.release();
                            if (errorCode == ErrorCode.NONE && categoryGraphData != null) {
                                CategoryGraphData.a(categoryGraphData, b);
                                synchronized (this.b.a.d) {
                                    this.b.a.d = categoryGraphData;
                                }
                                this.b.a.d(dt.a().a(categoryGraphData));
                            }
                            this.b.c = true;
                            this.b.b = null;
                        }
                    }
                });
                if (errorCode == ErrorCode.NONE) {
                    while (!this.c) {
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                errorCode = ErrorCode.INCOMPLETE;
            }
            if (errorCode != ErrorCode.NONE) {
                try {
                    Thread.sleep(com.alipay.e.a.a.c.a.a.b);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                this.a.b();
            }
        }
    }

    private class b implements h {
        final /* synthetic */ PlacesCategoryGraph a;

        private b(PlacesCategoryGraph placesCategoryGraph) {
            this.a = placesCategoryGraph;
        }

        public void a(Context context, Intent intent) {
            this.a.c(true);
        }
    }

    private static class c {
        public static PlacesCategoryGraph a = new PlacesCategoryGraph();
    }

    public static PlacesCategoryGraph a() {
        return c.a;
    }

    public static PlacesCategoryGraph a(boolean z) {
        PlacesCategoryGraph placesCategoryGraph = c.a;
        placesCategoryGraph.b(z);
        return placesCategoryGraph;
    }

    PlacesCategoryGraph() {
        this(false);
    }

    PlacesCategoryGraph(boolean z) {
        this.c = new Semaphore(1, true);
        a = z;
        this.d = new CategoryGraphData(this);
        try {
            this.e = new b();
            MapsEngine.c().a(this.e);
        } catch (Exception e) {
            e.printStackTrace();
        }
        b(z);
    }

    protected void finalize() {
        c();
    }

    public void b(boolean z) {
        boolean e = e();
        if (z != a) {
            a = z;
            if (!a) {
                c();
            } else if (e) {
                c(false);
            } else {
                b();
            }
        }
    }

    private void b() {
        b = new a(this);
        b.setName("CategoryGraph");
        b.setPriority(1);
        b.start();
    }

    private void c() {
        if (b != null && b.isAlive()) {
            b.a();
            b = null;
        }
    }

    private void c(boolean z) {
        if (!a) {
            return;
        }
        if (d() || z) {
            b();
        }
    }

    private boolean d() {
        File file = new File(MapSettings.d() + "/places/CategoryGraphJSON.txt");
        if (!file.exists() || this.d == null || !TextUtils.isEmpty(this.d.b())) {
            return true;
        }
        String language;
        if (Locale.getDefault().getCountry().isEmpty()) {
            language = Locale.getDefault().getLanguage();
        } else {
            language = Locale.getDefault().getLanguage() + "-" + Locale.getDefault().getCountry();
        }
        if (!this.d.b().matches(language)) {
            return true;
        }
        boolean z;
        if (System.currentTimeMillis() - file.lastModified() > 604800000) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    private final boolean e() {
        Exception e;
        BufferedReader bufferedReader;
        Throwable th;
        if (this.d != null && !this.d.b().isEmpty()) {
            return true;
        }
        BufferedReader bufferedReader2 = null;
        try {
            BufferedReader bufferedReader3;
            boolean z;
            this.c.acquire();
            File file = new File(MapSettings.d() + "/places/CategoryGraphJSON.txt");
            if (file.exists()) {
                bufferedReader3 = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
                try {
                    this.d = (CategoryGraphData) dt.a().a(bufferedReader3.readLine(), CategoryGraphData.class);
                    z = true;
                } catch (Exception e2) {
                    e = e2;
                    bufferedReader = bufferedReader3;
                    try {
                        e.printStackTrace();
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                        this.c.release();
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        this.c.release();
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader2 = bufferedReader3;
                    if (bufferedReader2 != null) {
                        bufferedReader2.close();
                    }
                    this.c.release();
                    throw th;
                }
            }
            bufferedReader3 = null;
            z = false;
            if (bufferedReader3 != null) {
                try {
                    bufferedReader3.close();
                } catch (IOException e42) {
                    e42.printStackTrace();
                }
            }
            this.c.release();
            return z;
        } catch (Exception e5) {
            e = e5;
            bufferedReader = null;
            e.printStackTrace();
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            this.c.release();
            return false;
        } catch (Throwable th4) {
            th = th4;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            this.c.release();
            throw th;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void d(java.lang.String r7) {
        /*
        r6 = this;
        r1 = 0;
        r0 = new java.io.File;	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r2.<init>();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r3 = com.nokia.maps.MapSettings.d();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r3 = java.io.File.separator;	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r3 = "places";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r2 = r0.exists();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        if (r2 != 0) goto L_0x00b3;
    L_0x0029:
        r2 = r0.mkdirs();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        if (r2 != 0) goto L_0x00b3;
    L_0x002f:
        r2 = new java.io.IOException;	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r3.<init>();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r4 = "Failed to create ";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r0 = r0.getName();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r0 = r3.append(r0);	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r0 = r0.toString();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r2.<init>(r0);	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        throw r2;	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
    L_0x004c:
        r0 = move-exception;
        r2 = r1;
    L_0x004e:
        r0.printStackTrace();	 Catch:{ all -> 0x016c }
        if (r2 == 0) goto L_0x0056;
    L_0x0053:
        r2.close();	 Catch:{ IOException -> 0x011a }
    L_0x0056:
        r2 = r1;
    L_0x0057:
        r0 = r6.c;	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r0.acquire();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        if (r2 == 0) goto L_0x015b;
    L_0x005e:
        r0 = r2.exists();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        if (r0 == 0) goto L_0x015b;
    L_0x0064:
        r0 = new java.io.File;	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r1 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r1.<init>();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r3 = com.nokia.maps.MapSettings.d();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r1 = r1.append(r3);	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r3 = "/places/CategoryGraphJSON.txt";
        r1 = r1.append(r3);	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r1 = r1.toString();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r0.<init>(r1);	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r1 = r0.exists();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        if (r1 == 0) goto L_0x012d;
    L_0x0086:
        r1 = r0.delete();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        if (r1 != 0) goto L_0x012d;
    L_0x008c:
        r1 = new java.io.IOException;	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r2 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r2.<init>();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r3 = "Failed to delete ";
        r2 = r2.append(r3);	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r0 = r0.getName();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r0 = r2.append(r0);	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r0 = r0.toString();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r1.<init>(r0);	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        throw r1;	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
    L_0x00a9:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0162 }
        r0 = r6.c;
        r0.release();
    L_0x00b2:
        return;
    L_0x00b3:
        r2 = new java.io.File;	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r0 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r0.<init>();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r3 = com.nokia.maps.MapSettings.d();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r0 = r0.append(r3);	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r3 = "/places/tmp.txt";
        r0 = r0.append(r3);	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r0 = r0.toString();	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r2.<init>(r0);	 Catch:{ IOException -> 0x004c, all -> 0x0121 }
        r0 = r2.exists();	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        if (r0 == 0) goto L_0x00fe;
    L_0x00d5:
        r0 = r2.delete();	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        if (r0 != 0) goto L_0x00fe;
    L_0x00db:
        r0 = new java.io.IOException;	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r3.<init>();	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r4 = "Failed to delete ";
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r4 = r2.getName();	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r3 = r3.append(r4);	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r3 = r3.toString();	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r0.<init>(r3);	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        throw r0;	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
    L_0x00f8:
        r0 = move-exception;
        r5 = r2;
        r2 = r1;
        r1 = r5;
        goto L_0x004e;
    L_0x00fe:
        r3 = new java.io.OutputStreamWriter;	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r0 = new java.io.FileOutputStream;	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r0.<init>(r2);	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r4 = "UTF-8";
        r3.<init>(r0, r4);	 Catch:{ IOException -> 0x00f8, all -> 0x0121 }
        r3.write(r7);	 Catch:{ IOException -> 0x016f, all -> 0x0169 }
        if (r3 == 0) goto L_0x0057;
    L_0x010f:
        r3.close();	 Catch:{ IOException -> 0x0114 }
        goto L_0x0057;
    L_0x0114:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0057;
    L_0x011a:
        r0 = move-exception;
        r0.printStackTrace();
        r2 = r1;
        goto L_0x0057;
    L_0x0121:
        r0 = move-exception;
    L_0x0122:
        if (r1 == 0) goto L_0x0127;
    L_0x0124:
        r1.close();	 Catch:{ IOException -> 0x0128 }
    L_0x0127:
        throw r0;
    L_0x0128:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0127;
    L_0x012d:
        r0 = r2.renameTo(r0);	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        if (r0 != 0) goto L_0x015b;
    L_0x0133:
        r0 = new java.io.IOException;	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r1 = new java.lang.StringBuilder;	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r1.<init>();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r3 = "Failed to rename ";
        r1 = r1.append(r3);	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r2 = r2.getName();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r1 = r1.append(r2);	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r1 = r1.toString();	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        r0.<init>(r1);	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
        throw r0;	 Catch:{ InterruptedException -> 0x00a9, IOException -> 0x0150 }
    L_0x0150:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0162 }
        r0 = r6.c;
        r0.release();
        goto L_0x00b2;
    L_0x015b:
        r0 = r6.c;
        r0.release();
        goto L_0x00b2;
    L_0x0162:
        r0 = move-exception;
        r1 = r6.c;
        r1.release();
        throw r0;
    L_0x0169:
        r0 = move-exception;
        r1 = r3;
        goto L_0x0122;
    L_0x016c:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0122;
    L_0x016f:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x004e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nokia.maps.PlacesCategoryGraph.d(java.lang.String):void");
    }

    public final synchronized Category a(String str) {
        Category category;
        category = null;
        synchronized (this.d) {
            for (PlacesCategory placesCategory : this.d.a()) {
                Category a;
                if (placesCategory.b().matches(str)) {
                    a = PlacesCategory.a(placesCategory);
                } else {
                    a = category;
                }
                category = a;
            }
        }
        return category;
    }

    public final synchronized Category b(String str) {
        Category a;
        synchronized (this.d) {
            for (PlacesCategory placesCategory : this.d.a()) {
                if (placesCategory.b().matches(str)) {
                    if (placesCategory.g().isEmpty()) {
                        a = PlacesCategory.a(placesCategory);
                    } else {
                        a = a((String) placesCategory.g().get(0));
                    }
                }
            }
            a = null;
        }
        return a;
    }

    public final synchronized List<Category> c(String str) {
        List<Category> arrayList;
        arrayList = new ArrayList();
        synchronized (this.d) {
            for (PlacesCategory placesCategory : this.d.a()) {
                if (!placesCategory.g().isEmpty() && ((String) placesCategory.g().get(0)).matches(str)) {
                    arrayList.add(PlacesCategory.a(placesCategory));
                }
            }
        }
        return arrayList;
    }
}
