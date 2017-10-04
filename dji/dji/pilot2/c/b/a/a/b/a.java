package dji.pilot2.c.b.a.a.b;

import com.dji.videouploadsdk.a.a.c;
import com.dji.videouploadsdk.model.PositionModel;
import com.dji.videouploadsdk.model.VideoEntity;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.mode.n;
import dji.pilot2.c.b.b;
import org.json.JSONException;
import org.json.JSONObject;

public class a extends b {
    private String a;
    private VideoEntity b;
    private PositionModel c;
    private com.dji.videouploadsdk.a.a d;
    private int e;

    private a(String str, String str2, String str3) {
        double d;
        JSONException e;
        String[] strArr;
        double d2 = 0.0d;
        int i = 0;
        super(str, str2, str3);
        this.e = 0;
        dji.pilot2.share.mode.a aVar = new dji.pilot2.share.mode.a(str);
        if (!(aVar == null || aVar.I == null)) {
            try {
                if (aVar.I.length() > 0) {
                    JSONObject jSONObject = aVar.I.getJSONObject(0);
                    if (jSONObject != null) {
                        d = jSONObject.getDouble(n.y);
                        try {
                            d2 = jSONObject.getDouble(n.x);
                        } catch (JSONException e2) {
                            e = e2;
                            e.printStackTrace();
                            this.a = dji.pilot2.share.f.a.d;
                            this.b = new VideoEntity();
                            this.c = new PositionModel();
                            if (aVar != null) {
                            }
                            strArr = null;
                            this.c.setLat(Double.toString(d2));
                            this.c.setLng(Double.toString(d));
                            this.b.setPosition(this.c);
                            this.b.setDrones(strArr);
                            this.b.setVideo_title(str2);
                            this.b.setVideo_desc(str3);
                            this.b.setAccess_token(f.getInstance().n());
                            this.d = new com.dji.videouploadsdk.a.a(str, this.b, this.a, 3600);
                            this.d.a(new c(this) {
                                final /* synthetic */ a a;

                                {
                                    this.a = r1;
                                }

                                public void a(String str) {
                                    this.a.a(str);
                                }

                                public void a() {
                                    this.a.d();
                                }

                                public void a(int i) {
                                    this.a.a(i);
                                }

                                public void b(int i) {
                                    this.a.e = i;
                                    this.a.e();
                                }
                            });
                        }
                        this.a = dji.pilot2.share.f.a.d;
                        this.b = new VideoEntity();
                        this.c = new PositionModel();
                        if (aVar != null || aVar.J == null) {
                            strArr = null;
                        } else {
                            String[] strArr2 = new String[aVar.J.length()];
                            while (i < aVar.J.length()) {
                                try {
                                    strArr2[i] = aVar.J.getString(i);
                                } catch (JSONException e3) {
                                    e3.printStackTrace();
                                }
                                i++;
                            }
                            strArr = strArr2;
                        }
                        this.c.setLat(Double.toString(d2));
                        this.c.setLng(Double.toString(d));
                        this.b.setPosition(this.c);
                        this.b.setDrones(strArr);
                        this.b.setVideo_title(str2);
                        this.b.setVideo_desc(str3);
                        this.b.setAccess_token(f.getInstance().n());
                        this.d = new com.dji.videouploadsdk.a.a(str, this.b, this.a, 3600);
                        this.d.a(/* anonymous class already generated */);
                    }
                }
            } catch (JSONException e4) {
                e3 = e4;
                d = d2;
                e3.printStackTrace();
                this.a = dji.pilot2.share.f.a.d;
                this.b = new VideoEntity();
                this.c = new PositionModel();
                if (aVar != null) {
                }
                strArr = null;
                this.c.setLat(Double.toString(d2));
                this.c.setLng(Double.toString(d));
                this.b.setPosition(this.c);
                this.b.setDrones(strArr);
                this.b.setVideo_title(str2);
                this.b.setVideo_desc(str3);
                this.b.setAccess_token(f.getInstance().n());
                this.d = new com.dji.videouploadsdk.a.a(str, this.b, this.a, 3600);
                this.d.a(/* anonymous class already generated */);
            }
        }
        d = d2;
        this.a = dji.pilot2.share.f.a.d;
        this.b = new VideoEntity();
        this.c = new PositionModel();
        if (aVar != null) {
        }
        strArr = null;
        this.c.setLat(Double.toString(d2));
        this.c.setLng(Double.toString(d));
        this.b.setPosition(this.c);
        this.b.setDrones(strArr);
        this.b.setVideo_title(str2);
        this.b.setVideo_desc(str3);
        this.b.setAccess_token(f.getInstance().n());
        this.d = new com.dji.videouploadsdk.a.a(str, this.b, this.a, 3600);
        this.d.a(/* anonymous class already generated */);
    }

    public a(String str, String str2, String str3, String str4) {
        this(str, str2, str3);
        e(str4);
    }

    protected void b() {
        d();
        this.d.a();
    }

    protected void c() {
        this.d.b();
    }

    private void e(String str) {
        if (str != null) {
            this.b.setVideo_tag_list(str.split(","));
            return;
        }
        this.b.setVideo_tag_list(new String[0]);
    }

    public int a() {
        return this.e;
    }
}
