package dji.pilot2.share.mode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.http.util.EncodingUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class a {
    public static final String A = "video_uniq_id";
    public static final int a = 0;
    public static final int b = 10;
    public static final int c = 12;
    public static final int d = 13;
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    public static final int h = 3;
    public static final int i = 4;
    public static final int j = 5;
    public static final int k = 6;
    public static final String l = "video_id";
    public static final String m = "video_path";
    public static final String n = "video_progress";
    public static final String o = "video_status";
    public static final String p = "video_platform";
    public static final String q = "share_page_id";
    public static final String r = "1";
    public static final String s = "2";
    public static final String t = "video_status";
    public static final String u = "video_id";
    public static final String v = "video_platform";
    public static final String w = "upload_title";
    public static final String x = "upload_desc";
    public static final String y = "video_positions";
    public static final String z = "video_drones";
    public String B = "";
    public String C = "";
    public int D = 0;
    public String E = "";
    public String F = "";
    public String G = "";
    public String H = "";
    public JSONArray I = new JSONArray();
    public JSONArray J = new JSONArray();

    public a(String str) {
        this.G = str;
        a(str);
    }

    private void a(String str) {
        if (new File(str).exists()) {
            File file = new File(str + ".info");
            if (file.exists()) {
                try {
                    FileInputStream fileInputStream = new FileInputStream(file);
                    byte[] bArr = new byte[fileInputStream.available()];
                    fileInputStream.read(bArr);
                    fileInputStream.close();
                    try {
                        JSONObject jSONObject = new JSONObject(EncodingUtils.getString(bArr, "UTF-8"));
                        try {
                            this.D = jSONObject.getInt("video_status");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            this.B = jSONObject.getString("video_id");
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                        try {
                            this.C = jSONObject.getString("video_platform");
                        } catch (JSONException e22) {
                            e22.printStackTrace();
                        }
                        try {
                            this.E = jSONObject.getString(w);
                        } catch (JSONException e222) {
                            e222.printStackTrace();
                        }
                        try {
                            this.F = jSONObject.getString(x);
                        } catch (JSONException e2222) {
                            e2222.printStackTrace();
                        }
                        try {
                            this.I = jSONObject.getJSONArray(y);
                        } catch (JSONException e22222) {
                            e22222.printStackTrace();
                        }
                        try {
                            this.J = jSONObject.getJSONArray(z);
                        } catch (JSONException e222222) {
                            e222222.printStackTrace();
                        }
                        try {
                            this.H = jSONObject.getString(A);
                        } catch (JSONException e2222222) {
                            e2222222.printStackTrace();
                        }
                    } catch (JSONException e22222222) {
                        e22222222.printStackTrace();
                    }
                } catch (FileNotFoundException e3) {
                    e3.printStackTrace();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
        }
    }

    public void a() {
        if (new File(this.G).exists()) {
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(new File(this.G + ".info"));
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("video_status", this.D);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    jSONObject.put("video_id", this.B);
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                try {
                    jSONObject.put("video_platform", this.C);
                } catch (JSONException e22) {
                    e22.printStackTrace();
                }
                try {
                    jSONObject.put(w, this.E);
                } catch (JSONException e222) {
                    e222.printStackTrace();
                }
                try {
                    jSONObject.put(x, this.F);
                } catch (JSONException e2222) {
                    e2222.printStackTrace();
                }
                try {
                    jSONObject.put(y, this.I);
                } catch (JSONException e22222) {
                    e22222.printStackTrace();
                }
                try {
                    jSONObject.put(z, this.J);
                } catch (JSONException e222222) {
                    e222222.printStackTrace();
                }
                try {
                    jSONObject.put(A, this.H);
                } catch (JSONException e2222222) {
                    e2222222.printStackTrace();
                }
                try {
                    fileOutputStream.write(EncodingUtils.getBytes(jSONObject.toString(), "utf-8"));
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                try {
                    fileOutputStream.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            } catch (FileNotFoundException e4) {
                e4.printStackTrace();
            }
        }
    }
}
