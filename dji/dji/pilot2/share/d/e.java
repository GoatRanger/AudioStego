package dji.pilot2.share.d;

import android.content.Context;
import android.util.Log;
import com.dji.frame.c.l;
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.VerificationCodeReceiver;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow.Builder;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTubeScopes;
import dji.pilot.R;
import dji.pilot2.share.c.a;
import dji.pilot2.share.c.c;
import dji.pilot2.share.c.d;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class e {
    public static final HttpTransport a = new NetHttpTransport();
    public static final JsonFactory b = new JacksonFactory();
    public static final String c = "urn:ietf:wg:oauth:2.0:oob";
    private static final String f = ".oauth-credentials";
    private static final String g = "video/*";
    private static AuthorizationCodeFlow h;
    private static Credential i;
    private static VerificationCodeReceiver k;
    String d = "";
    String e = "";
    private Context j;

    private int a(long j) {
        int i = (int) (((j / 100) / 262144) * ((long) 4194304));
        if (i <= 0) {
            return 262144;
        }
        if (i < MediaHttpUploader.DEFAULT_CHUNK_SIZE) {
            return i;
        }
        return MediaHttpUploader.DEFAULT_CHUNK_SIZE;
    }

    public void a(final String str, final d dVar) {
        new Thread(this) {
            final /* synthetic */ e c;

            public void run() {
                try {
                    e.i = e.h.createAndStoreCredential(e.h.newTokenRequest(str).setRedirectUri("urn:ietf:wg:oauth:2.0:oob").execute(), "user");
                    dVar.onTokenGet(e.i.getAccessToken());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public e(Context context) {
        this.j = context;
        try {
            h();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void h() throws IOException {
        Collection arrayList = new ArrayList();
        arrayList.add(YouTubeScopes.YOUTUBE);
        arrayList.add("https://www.googleapis.com/auth/userinfo.profile");
        arrayList.add("email");
        GoogleClientSecrets load = GoogleClientSecrets.load(b, new InputStreamReader(this.j.getResources().openRawResource(R.raw.client_secrets)));
        h = new Builder(a, b, load, arrayList).setCredentialDataStore(new FileDataStoreFactory(new File(i() + "/googleOauth/" + f)).getDataStore("uploadVideo")).build();
        k = new LocalServerReceiver.Builder().setHost("urn:ietf:wg:oauth:2.0:oob").setPort(80).build();
        i = h.loadCredential("user");
        a(null);
    }

    public void a() {
        i = null;
        File file = new File(i() + "/googleOauth/" + f + "/uploadVideo");
        if (file.exists()) {
            file.delete();
        }
    }

    public void a(a aVar) {
        if (i == null || (i.getRefreshToken() == null && i.getExpiresInSeconds().longValue() <= 60)) {
            aVar.onOauthUriGet(h.newAuthorizationUrl().setRedirectUri("urn:ietf:wg:oauth:2.0:oob").build());
            return;
        }
        a(null);
        aVar.onAccessTokenGet(i.getAccessToken());
    }

    public boolean b() {
        if (i == null || (i.getRefreshToken() == null && i.getExpiresInSeconds().longValue() <= 60)) {
            return false;
        }
        return true;
    }

    public Credential c() {
        return i;
    }

    private String i() {
        return com.dji.frame.c.d.a(this.j, "VideoEditor");
    }

    public void a(final c cVar) {
        if (i != null && i.getAccessToken() != null) {
            new Thread(this) {
                final /* synthetic */ e b;

                public void run() {
                    String str = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=" + e.i.getAccessToken();
                    Log.v(dji.pilot2.mine.b.e.p, str);
                    try {
                        HttpResponse execute = new DefaultHttpClient().execute(new HttpGet(str));
                        if (execute.getStatusLine().getStatusCode() == 200) {
                            str = EntityUtils.toString(execute.getEntity());
                            JSONObject jSONObject = new JSONObject(str);
                            Log.v(dji.pilot2.mine.b.e.p, str);
                            this.b.d = jSONObject.getString("name");
                            this.b.e = jSONObject.getString("email");
                            if (cVar != null) {
                                cVar.onGetUserInfo(this.b.d, this.b.e);
                                return;
                            }
                            return;
                        }
                        Log.v(dji.pilot2.mine.b.e.p, EntityUtils.toString(execute.getEntity()));
                    } catch (ClientProtocolException e) {
                        e.printStackTrace();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
            }.start();
        } else if (!l.a(this.d) && !l.a(this.e) && cVar != null) {
            cVar.onGetUserInfo(this.d, this.e);
        }
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }
}
