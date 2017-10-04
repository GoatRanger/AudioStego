package dji.pilot.flyunlimit;

import com.dji.frame.c.l;
import dji.midware.e.d;
import dji.pilot.flyunlimit.b.c;
import dji.pilot.flyunlimit.jsonbean.DJIFlyUnlimitArea;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.thirdparty.afinal.f.a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class b$2 extends a<String> {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ c c;
    final /* synthetic */ b d;

    b$2(b bVar, String str, int i, c cVar) {
        this.d = bVar;
        this.a = str;
        this.b = i;
        this.c = cVar;
    }

    public void a(boolean z) {
    }

    public void a(long j, long j2) {
    }

    public void a(String str) {
        boolean z = false;
        if (!l.a(str)) {
            try {
                boolean z2;
                JSONObject jSONObject = new JSONObject(str).getJSONObject(this.a);
                if (jSONObject != null) {
                    JSONArray names = jSONObject.names();
                    if (names == null || names.length() <= 0) {
                        b.a(this.d, 11);
                        z2 = false;
                    } else {
                        String str2 = "";
                        for (int length = names.length() - 1; length >= 0; length--) {
                            String obj = names.get(length).toString();
                            if (this.b >= 0) {
                                ((DJIFlyUnlimitArea) b.a(this.d).get(this.b)).addUnlimitData(jSONObject.getString(obj));
                                ((DJIFlyUnlimitArea) b.a(this.d).get(this.b)).addPilotSN(obj);
                            } else {
                                str2 = jSONObject.getString(obj);
                                if (obj.compareTo(DJIGlobalService.f) == 0 && !l.a(str2)) {
                                    this.d.a(str2, new d(this) {
                                        final /* synthetic */ b$2 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void onSuccess(Object obj) {
                                            this.a.d.a(true, new d(this) {
                                                final /* synthetic */ AnonymousClass1 a;

                                                {
                                                    this.a = r1;
                                                }

                                                public void onSuccess(Object obj) {
                                                    if (this.a.a.c != null) {
                                                        this.a.a.c.a(true);
                                                    }
                                                }

                                                public void onFailure(dji.midware.data.config.P3.a aVar) {
                                                    b.a(this.a.a.d, 14);
                                                    if (this.a.a.c != null) {
                                                        this.a.a.c.a(false);
                                                    }
                                                }
                                            });
                                        }

                                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                                            b.a(this.a.d, 14);
                                            if (this.a.c != null) {
                                                this.a.c.a(false);
                                            }
                                        }
                                    });
                                    return;
                                }
                            }
                        }
                        b.a(this.d, 12);
                        z2 = true;
                    }
                } else {
                    b.a(this.d, 10);
                    z2 = false;
                }
                z = z2;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (this.c != null) {
            this.c.a(z);
        }
    }

    public void a(Throwable th, int i, String str) {
        b.a(this.d, 13);
        if (this.c != null) {
            this.c.a(false);
        }
    }
}
