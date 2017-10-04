package com.dji.videouploadsdk.a;

import android.os.AsyncTask;
import android.util.Log;
import com.dji.videouploadsdk.model.InitResultEntity;
import com.dji.videouploadsdk.model.VideoEntity;
import com.dji.videouploadsdk.model.commitResultEntity;
import com.tencent.bugly.crashreport.BuglyLog;
import dji.log.DJILogHelper;
import dji.thirdparty.gson.Gson;
import dji.thirdparty.gson.JsonSyntaxException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

public class a {
    private RandomAccessFile a = null;
    private String b = null;
    private String c = null;
    private String d = null;
    private long e = 0;
    private int f = 0;
    private String g = null;
    private int h = 0;
    private boolean i = false;
    private VideoEntity j = null;
    private InitResultEntity k = null;
    private c l = null;
    private int m = 0;
    private int n = 0;

    class a extends AsyncTask<File, Integer, String> {
        final /* synthetic */ a a;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((File[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((String) obj);
        }

        public a(a aVar) {
            this.a = aVar;
        }

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected void a(String str) {
            super.onPostExecute(str);
            if (this.a.g == null || this.a.j == null || this.a.d == null) {
                if (this.a.l != null) {
                    this.a.l.b(this.a.n);
                }
                Log.e("", "m_url or m_entiry is null ");
                return;
            }
            this.a.a(this.a.g, this.a.j);
        }

        protected String a(File... fileArr) {
            this.a.d = b.a(fileArr[0]);
            this.a.c = fileArr[0].getName();
            this.a.e = fileArr[0].length();
            return this.a.d;
        }
    }

    class b extends Thread {
        HttpPost a;
        final /* synthetic */ a b;

        public b(a aVar, HttpPost httpPost) {
            this.b = aVar;
            this.a = httpPost;
        }

        public void run() {
            HttpResponse execute;
            try {
                execute = a.e().execute(this.a);
            } catch (IOException e) {
                this.b.n = -2;
                e.printStackTrace();
                execute = null;
            } catch (Exception e2) {
                e2.printStackTrace();
                execute = null;
            }
            try {
                InitResultEntity initResultEntity;
                String entityUtils = EntityUtils.toString(execute.getEntity(), "utf-8");
                Log.e("", "JSON: " + entityUtils);
                try {
                    initResultEntity = (InitResultEntity) new Gson().fromJson(entityUtils, InitResultEntity.class);
                } catch (Exception e22) {
                    BuglyLog.e("gsonerror", "[UploadInitTask]stack" + e22.toString());
                    initResultEntity = null;
                }
                if (initResultEntity != null) {
                    if (initResultEntity.getStatus() == 0) {
                        Log.e("", "upload_url  " + initResultEntity.getUpload_url());
                        this.b.k = initResultEntity;
                        if (initResultEntity.getStatus() == 0 && !this.b.i) {
                            this.b.c();
                            return;
                        }
                        return;
                    }
                }
                if (this.b.l == null) {
                    return;
                }
                if (initResultEntity == null) {
                    this.b.l.b(this.b.n);
                } else {
                    this.b.l.b(initResultEntity.getStatus());
                }
            } catch (Exception e222) {
                e222.printStackTrace();
                if (this.b.l != null) {
                    this.b.l.b(this.b.n);
                }
            }
        }
    }

    public interface c {
        void a();

        void a(int i);

        void a(String str);

        void b(int i);
    }

    public void a(c cVar) {
        this.l = cVar;
    }

    public a(String str, VideoEntity videoEntity, String str2, int i) {
        this.b = str;
        this.j = videoEntity;
        this.g = str2;
        this.h = i * 1000;
        this.i = false;
        Log.e("", "init------------------ ");
    }

    public void a() {
        Log.e("", "start------------------ ");
        this.i = false;
        if (this.b != null) {
            if (this.l != null) {
                this.l.a();
            }
            a(this.b);
        }
    }

    public void b() {
        this.i = true;
        d();
    }

    public void a(String str) {
        File file = new File(this.b);
        new a(this).execute(new File[]{file});
    }

    public void a(String str, VideoEntity videoEntity) {
        videoEntity.setFile_md5(this.d);
        videoEntity.setFile_size(String.valueOf(this.e));
        videoEntity.setUpload_mode("0");
        String toJson = new Gson().toJson(videoEntity);
        Log.e("Lyric", "jsonStr--->" + toJson);
        LinkedList linkedList = new LinkedList();
        List linkedList2 = new LinkedList();
        linkedList2.add(new BasicNameValuePair("info", toJson));
        linkedList2.add(new BasicNameValuePair("flag", "dji_pilot"));
        try {
            HttpPost httpPost = new HttpPost(str);
            httpPost.setEntity(new UrlEncodedFormEntity(linkedList2, "utf-8"));
            new b(this, httpPost).start();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            if (this.l != null) {
                this.l.b(this.n);
            }
        }
    }

    public void c() {
        dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
        bVar.a("upload_token", this.k.getUpload_token());
        bVar.a("file_size", String.valueOf(this.e));
        bVar.a("file_md5", this.d);
        String str = this.k.getUpload_url() + "?" + "file_size=" + this.e + "&file_md5=" + this.d + "&upload_token=" + this.k.getUpload_token();
        DJILogHelper.getInstance().LOGI("Lyric", "parameterUrl: " + str);
        str = a(str, this.b);
        if (!this.i) {
            if (this.m != 200) {
                this.n = this.m;
                if (this.l != null) {
                    this.l.b(this.n);
                    return;
                }
                return;
            }
            commitResultEntity com_dji_videouploadsdk_model_commitResultEntity;
            Log.e("", "JSON: %@" + str);
            try {
                com_dji_videouploadsdk_model_commitResultEntity = (commitResultEntity) new Gson().fromJson(str, commitResultEntity.class);
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                com_dji_videouploadsdk_model_commitResultEntity = null;
            } catch (Exception e2) {
                BuglyLog.e("gsonerror", "[startUpload]stack" + e2.toString());
                com_dji_videouploadsdk_model_commitResultEntity = null;
            }
            if (com_dji_videouploadsdk_model_commitResultEntity == null || com_dji_videouploadsdk_model_commitResultEntity.getSkypixel() == null || com_dji_videouploadsdk_model_commitResultEntity.getSkypixel().getStatus() != 0 || com_dji_videouploadsdk_model_commitResultEntity.getSkypixel().getVid() == null) {
                if (this.l != null) {
                    this.l.b(this.n);
                }
            } else if (com_dji_videouploadsdk_model_commitResultEntity.getState() == 0 && !this.i) {
                if (this.l != null) {
                    this.l.a(com_dji_videouploadsdk_model_commitResultEntity.getSkypixel().getVid());
                }
                d();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r18, java.lang.String r19) {
        /*
        r17 = this;
        r6 = "";
        r2 = "";
        if (r19 == 0) goto L_0x0248;
    L_0x0006:
        r2 = new java.io.File;
        r0 = r19;
        r2.<init>(r0);
        r2 = r2.getName();
        r3 = r2;
    L_0x0012:
        r7 = "\r\n";
        r8 = "--";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r4 = java.util.UUID.randomUUID();
        r4 = r4.toString();
        r2 = r2.append(r4);
        r4 = java.util.UUID.randomUUID();
        r4 = r4.toString();
        r2 = r2.append(r4);
        r4 = java.util.UUID.randomUUID();
        r4 = r4.toString();
        r2 = r2.append(r4);
        r4 = java.util.UUID.randomUUID();
        r4 = r4.toString();
        r2 = r2.append(r4);
        r9 = r2.toString();
        r10 = new java.io.File;
        r0 = r19;
        r10.<init>(r0);
        r4 = r10.length();
        r12 = 100;
        r4 = r4 / r12;
        r5 = (int) r4;
        r2 = 131072; // 0x20000 float:1.83671E-40 double:6.47582E-319;
        r4 = 1048576; // 0x100000 float:1.469368E-39 double:5.180654E-318;
        if (r2 <= r5) goto L_0x006d;
    L_0x0064:
        r4 = r10.isFile();
        if (r4 != 0) goto L_0x0073;
    L_0x006a:
        r2 = "";
    L_0x006c:
        return r2;
    L_0x006d:
        if (r5 <= r4) goto L_0x0071;
    L_0x006f:
        r2 = r4;
        goto L_0x0064;
    L_0x0071:
        r2 = r5;
        goto L_0x0064;
    L_0x0073:
        r5 = new java.io.FileInputStream;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r5.<init>(r10);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r0 = r18;
        r4.<init>(r0);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r10 = r5.available();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r11 = java.lang.Math.min(r10, r2);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r2 = r4.openConnection();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r2 = (javax.net.ssl.HttpsURLConnection) r2;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = 1;
        r2.setDoInput(r4);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = 1;
        r2.setDoOutput(r4);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = 0;
        r2.setUseCaches(r4);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r0 = r17;
        r4 = r0.h;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r2.setReadTimeout(r4);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r2.setChunkedStreamingMode(r11);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = "POST";
        r2.setRequestMethod(r4);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = "Connection";
        r12 = "Keep-Alive";
        r2.setRequestProperty(r4, r12);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = "ENCTYPE";
        r12 = "multipart/form-data";
        r2.setRequestProperty(r4, r12);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = "Content-Type";
        r12 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12.<init>();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r13 = "multipart/form-data;boundary=";
        r12 = r12.append(r13);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12 = r12.append(r9);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12 = r12.toString();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r2.setRequestProperty(r4, r12);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = "video_file";
        r2.setRequestProperty(r4, r3);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12 = new java.io.DataOutputStream;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = r2.getOutputStream();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12.<init>(r4);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4.<init>();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = r4.append(r8);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = r4.append(r9);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = r4.append(r7);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = r4.toString();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = r4.getBytes();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12.write(r4);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4.<init>();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r13 = "Content-Disposition: form-data; name=\"video_file\";filename=\"";
        r4 = r4.append(r13);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r4.append(r3);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = "\"";
        r3 = r3.append(r4);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r3.append(r7);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r3.toString();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r3.getBytes();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12.write(r3);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r7.getBytes();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12.write(r3);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12.flush();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = new byte[r11];	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = 0;
    L_0x0129:
        r13 = 0;
        r13 = r5.read(r4, r13, r11);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        if (r13 <= 0) goto L_0x0153;
    L_0x0130:
        r14 = 1120272384; // 0x42c60000 float:99.0 double:5.53488099E-315;
        r15 = (float) r3;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r14 = r14 * r15;
        r15 = (float) r10;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r14 = r14 / r15;
        r14 = (int) r14;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r0 = r17;
        r15 = r0.i;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        if (r15 != 0) goto L_0x0129;
    L_0x013d:
        r0 = r17;
        r15 = r0.l;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        if (r15 == 0) goto L_0x014a;
    L_0x0143:
        r0 = r17;
        r15 = r0.l;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r15.a(r14);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
    L_0x014a:
        r14 = 0;
        r12.write(r4, r14, r13);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12.flush();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r3 + r13;
        goto L_0x0129;
    L_0x0153:
        r0 = r17;
        r3 = r0.i;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        if (r3 != 0) goto L_0x0192;
    L_0x0159:
        r3 = r7.getBytes();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12.write(r3);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3.<init>();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r3.append(r8);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r3.append(r9);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r3.append(r8);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r3.append(r7);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r3.toString();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r3.getBytes();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12.write(r3);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r12.flush();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r0 = r17;
        r3 = r0.l;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        if (r3 == 0) goto L_0x0192;
    L_0x0189:
        r0 = r17;
        r3 = r0.l;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = 100;
        r3.a(r4);	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
    L_0x0192:
        r0 = r17;
        r3 = r0.i;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        if (r3 != 0) goto L_0x0246;
    L_0x0198:
        r3 = r2.getResponseCode();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r0 = r17;
        r0.m = r3;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r2.getResponseMessage();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r0 = r17;
        r3 = r0.m;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r4 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r3 != r4) goto L_0x0246;
    L_0x01ab:
        r4 = 0;
        r4 = r2.getInputStream();	 Catch:{ IOException -> 0x01c1 }
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x01c1 }
        r3.<init>();	 Catch:{ IOException -> 0x01c1 }
    L_0x01b5:
        r7 = r4.read();	 Catch:{ IOException -> 0x01c1 }
        r8 = -1;
        if (r7 == r8) goto L_0x01f6;
    L_0x01bc:
        r7 = (char) r7;	 Catch:{ IOException -> 0x01c1 }
        r3.append(r7);	 Catch:{ IOException -> 0x01c1 }
        goto L_0x01b5;
    L_0x01c1:
        r3 = move-exception;
        r3.printStackTrace();	 Catch:{ all -> 0x0215 }
        if (r4 == 0) goto L_0x0246;
    L_0x01c7:
        r4.close();	 Catch:{ Exception -> 0x020f, MalformedURLException -> 0x021c, IOException -> 0x0225 }
        r3 = r6;
    L_0x01cb:
        r5.close();	 Catch:{ MalformedURLException -> 0x0205, IOException -> 0x023f, Exception -> 0x0238 }
        r12.flush();	 Catch:{ MalformedURLException -> 0x0205, IOException -> 0x023f, Exception -> 0x0238 }
        r12.close();	 Catch:{ MalformedURLException -> 0x0205, IOException -> 0x023f, Exception -> 0x0238 }
        r2.disconnect();	 Catch:{ MalformedURLException -> 0x0205, IOException -> 0x023f, Exception -> 0x0238 }
        r2 = r3;
    L_0x01d8:
        r3 = dji.log.DJILogHelper.getInstance();
        r4 = "Lyric";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "result: ";
        r5 = r5.append(r6);
        r5 = r5.append(r2);
        r5 = r5.toString();
        r3.LOGI(r4, r5);
        goto L_0x006c;
    L_0x01f6:
        r3 = r3.toString();	 Catch:{ IOException -> 0x01c1 }
        if (r4 == 0) goto L_0x01cb;
    L_0x01fc:
        r4.close();	 Catch:{ Exception -> 0x0200, MalformedURLException -> 0x0205, IOException -> 0x023f }
        goto L_0x01cb;
    L_0x0200:
        r4 = move-exception;
        r4.printStackTrace();	 Catch:{ MalformedURLException -> 0x0205, IOException -> 0x023f, Exception -> 0x0238 }
        goto L_0x01cb;
    L_0x0205:
        r2 = move-exception;
        r16 = r2;
        r2 = r3;
        r3 = r16;
    L_0x020b:
        r3.printStackTrace();
        goto L_0x01d8;
    L_0x020f:
        r3 = move-exception;
        r3.printStackTrace();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        r3 = r6;
        goto L_0x01cb;
    L_0x0215:
        r2 = move-exception;
        if (r4 == 0) goto L_0x021b;
    L_0x0218:
        r4.close();	 Catch:{ Exception -> 0x0220, MalformedURLException -> 0x021c, IOException -> 0x0225 }
    L_0x021b:
        throw r2;	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
    L_0x021c:
        r2 = move-exception;
        r3 = r2;
        r2 = r6;
        goto L_0x020b;
    L_0x0220:
        r3 = move-exception;
        r3.printStackTrace();	 Catch:{ MalformedURLException -> 0x021c, IOException -> 0x0225, Exception -> 0x0231 }
        goto L_0x021b;
    L_0x0225:
        r2 = move-exception;
        r3 = r2;
        r2 = r6;
    L_0x0228:
        r4 = -2;
        r0 = r17;
        r0.n = r4;
        r3.printStackTrace();
        goto L_0x01d8;
    L_0x0231:
        r2 = move-exception;
        r3 = r2;
        r2 = r6;
    L_0x0234:
        r3.printStackTrace();
        goto L_0x01d8;
    L_0x0238:
        r2 = move-exception;
        r16 = r2;
        r2 = r3;
        r3 = r16;
        goto L_0x0234;
    L_0x023f:
        r2 = move-exception;
        r16 = r2;
        r2 = r3;
        r3 = r16;
        goto L_0x0228;
    L_0x0246:
        r3 = r6;
        goto L_0x01cb;
    L_0x0248:
        r3 = r2;
        goto L_0x0012;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.dji.videouploadsdk.a.a.a(java.lang.String, java.lang.String):java.lang.String");
    }

    public void b(String str) {
        try {
            this.a = new RandomAccessFile(str, "r");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public byte[] a(int i, int i2) {
        int read;
        byte[] bArr;
        byte[] bArr2 = new byte[i2];
        try {
            this.a.seek((long) i);
            read = this.a.read(bArr2);
        } catch (IOException e) {
            e.printStackTrace();
            read = 0;
        }
        if (read == -1) {
            bArr = new byte[0];
        } else {
            bArr = Arrays.copyOf(bArr2, read);
        }
        Log.e("", "buffer length: " + read);
        return bArr;
    }

    public void d() {
        try {
            if (this.a != null) {
                this.a.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HttpClient e() {
        try {
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load(null, null);
            SocketFactory dVar = new d(instance);
            dVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme(com.alipay.sdk.b.b.a, dVar, 443));
            return new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }
}
