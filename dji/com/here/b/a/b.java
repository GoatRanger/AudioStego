package com.here.b.a;

import com.a.a.e;
import com.here.b.c.a;
import com.here.b.d.c;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

class b extends e {
    b() {
    }

    public boolean a() {
        return a.j();
    }

    public HttpURLConnection a(String str) throws IOException {
        a.c();
        if (str == null) {
            throw new IOException("Could not fetch the Host URL");
        }
        String str2;
        if (str.contains("cdn")) {
            str2 = a.k().c("settingsHost") + a.a(a.g());
            c.b("Fetching project settings from " + str2);
            a(b(str2));
        } else if (str.contains("api")) {
            str2 = a.k().c(com.alipay.sdk.b.c.f) + ((String) a.b.get("import"));
            c.b("Uploading to URL : " + str2);
        } else {
            throw new IllegalArgumentException("This is not a valid URL");
        }
        return super.a(str2);
    }

    static JSONObject b(String str) throws IOException {
        Throwable th;
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        if (httpURLConnection.getResponseCode() == 200) {
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()), 8192);
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine);
                    } catch (IOException e) {
                    }
                }
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    httpURLConnection.disconnect();
                }
            } catch (IOException e3) {
                bufferedReader = null;
                try {
                    c.a("Could not connect to the URL " + str);
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                        httpURLConnection.disconnect();
                    }
                    return new JSONObject(stringBuilder.toString());
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                bufferedReader = null;
                th = th4;
                if (bufferedReader != null) {
                    bufferedReader.close();
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        }
        try {
            return new JSONObject(stringBuilder.toString());
        } catch (JSONException e4) {
            c.c("parse fails or doesn't yield a JSONObject");
            return null;
        }
    }

    static void a(JSONObject jSONObject) {
        if (jSONObject.has("scbe")) {
            try {
                String string = new JSONObject(jSONObject.getString("scbe")).getString("overrideURL");
                if (string == null) {
                    c.c("Scbe settings does not contain 'overrideURL' key/value pair");
                    return;
                }
                c.a("initializing hostURL to " + string);
                a.a(string);
                return;
            } catch (JSONException e) {
                c.c("Failed to convert to a JsonObject");
                return;
            }
        }
        c.a("No Scbe override settings");
    }
}
