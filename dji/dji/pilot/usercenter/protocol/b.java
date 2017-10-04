package dji.pilot.usercenter.protocol;

import android.content.Context;
import android.util.Log;
import com.dji.frame.c.c;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.pilot.usercenter.b.f;
import dji.pilot2.utils.k;
import dji.thirdparty.afinal.f.a;
import java.io.File;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

public class b implements c {
    public static void a(Context context, String str, final int i, final int i2, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, c.D, new Object[]{str, Integer.valueOf(i), Integer.valueOf(i2)}), new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a(1048576, z, 0, e$b.a(i, i2, obj, null));
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a(1048576, j2, j, 0, e$b.a(i, i2, obj, null));
            }

            public void a(String str) {
                Object a = dji.pilot.usercenter.protocol.a.b.a(str);
                dji_pilot_usercenter_protocol_e_a.a(1048576, i, 0, e$b.a(i, i2, obj, a), a);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a(1048576, i, 0, e$b.a(i, i2, obj, null));
            }
        });
    }

    public static void a(Context context, String str, String str2, String str3, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        try {
            String format = String.format(Locale.US, c.A, new Object[]{str, str3});
            HttpEntity fileEntity = new FileEntity(new File(str2), "binary/octet-stream");
            fileEntity.setContentEncoding("binary/octet-stream");
            Log.d("", "length[" + fileEntity.getContentLength() + d.H);
            c.b(context).a(format, fileEntity, "binary/octet-stream", new a<String>() {
                public void a(boolean z) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, z, 0, e$b.a(0, 0, obj, null));
                }

                public void a(long j, long j2) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, j2, j, 0, e$b.a(0, 0, obj, null));
                }

                public void a(String str) {
                    Object b = dji.pilot.usercenter.protocol.a.b.b(str);
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, 0, 0, e$b.a(0, 0, obj, b), b);
                }

                public void a(Throwable th, int i, String str) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, i, 0, e$b.a(0, 0, obj, null));
                }
            });
        } catch (Exception e) {
        }
    }

    public static void b(Context context, String str, String str2, String str3, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        try {
            String str4 = c.H;
            dji.thirdparty.afinal.f.b bVar = new dji.thirdparty.afinal.f.b();
            bVar.a("token", str);
            bVar.a("filename", str3);
            bVar.a(d.A, new File(str2));
            c.b(context).b(c.H, bVar, new a<String>() {
                public void a(boolean z) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, z, 0, e$b.a(0, 0, obj, null));
                }

                public void a(long j, long j2) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, j2, j, 0, e$b.a(0, 0, obj, null));
                }

                public void a(String str) {
                    Object b = dji.pilot.usercenter.protocol.a.b.b(str);
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, 0, 0, e$b.a(0, 0, obj, b), b);
                }

                public void a(Throwable th, int i, String str) {
                    Object a = e$b.a(0, 0, obj, null);
                    Log.e("flightrecord upload", "Upload fail: " + i + " Msg: " + str);
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, i, 0, a);
                }
            });
        } catch (Exception e) {
        }
    }

    public static void c(Context context, String str, String str2, String str3, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        try {
            String format = String.format(Locale.US, c.J, new Object[]{str, str3});
            HttpEntity fileEntity = new FileEntity(new File(str2), "binary/octet-stream");
            fileEntity.setContentEncoding("binary/octet-stream");
            Log.d("", "length[" + fileEntity.getContentLength() + d.H);
            c.b(context).a(format, fileEntity, "binary/octet-stream", new a<String>() {
                public void a(boolean z) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, z, 0, e$b.a(0, 0, obj, null));
                }

                public void a(long j, long j2) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, j2, j, 0, e$b.a(0, 0, obj, null));
                }

                public void a(String str) {
                    DJILogHelper.getInstance().LOGD("", str, false, true);
                    Object b = dji.pilot.usercenter.protocol.a.b.b(str);
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, 0, 0, e$b.a(0, 0, obj, b), b);
                }

                public void a(Throwable th, int i, String str) {
                    DJILogHelper.getInstance().LOGD("", "errorNo:" + i + "strMsg:" + str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + th.getMessage(), false, true);
                    dji_pilot_usercenter_protocol_e_a.a((int) c.d, i, 0, e$b.a(0, 0, obj, null));
                }
            });
        } catch (Exception e) {
        }
    }

    public static void a(Context context, String str, List<String> list, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        int i = 0;
        String format = String.format(Locale.US, c.C, new Object[]{str});
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            int size = list.size();
            while (i < size) {
                jSONArray.put(list.get(i));
                i++;
            }
            jSONObject.putOpt(c.P, jSONArray);
            DJILogHelper.getInstance().LOGD("", "flight del[" + jSONObject.toString() + d.H);
            c.b(context).a(format, new StringEntity(jSONObject.toString()), null, new a<String>() {
                public void a(boolean z) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.f, z, 0, e$b.a(0, 0, obj, null));
                }

                public void a(long j, long j2) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.f, j2, j, 0, e$b.a(0, 0, obj, null));
                }

                public void a(String str) {
                    Object c = dji.pilot.usercenter.protocol.a.b.c(str);
                    dji_pilot_usercenter_protocol_e_a.a((int) c.f, 0, 0, e$b.a(0, 0, obj, c), c);
                }

                public void a(Throwable th, int i, String str) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.f, i, 0, e$b.a(0, 0, obj, null));
                }
            });
        } catch (Exception e) {
        }
    }

    public static void a(Context context, String str, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, c.E, new Object[]{str}), new a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object a = dji.pilot.usercenter.protocol.a.b.a(str, true);
                dji_pilot_usercenter_protocol_e_a.a((int) c.j, 0, 0, e$b.a(0, 0, obj, a), a);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) c.j, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }

    public static void b(Context context, String str, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(String.format(Locale.US, c.K, new Object[]{str}), new a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) c.v, 0, 0, e$b.a(0, 0, obj, str), (Object) str);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) c.v, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }

    public static void a(Context context, double d, double d2, final e$a dji_pilot_usercenter_protocol_e_a) {
        try {
            String format = String.format(Locale.US, c.L, new Object[]{d + "", d2 + ""});
            DJILogHelper.getInstance().LOGD("", "url:" + format, false, true);
            c.b(context).a(format, new a<String>() {
                public void a(boolean z) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.u, false, 0, Integer.valueOf(0));
                }

                public void a(long j, long j2) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.u, j2, j, 0, Integer.valueOf(0));
                }

                public void a(String str) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.u, 0, 0, null, dji.pilot.usercenter.protocol.a.b.d(str));
                }

                public void a(Throwable th, int i, String str) {
                    dji_pilot_usercenter_protocol_e_a.a((int) c.u, i, 0, Integer.valueOf(0));
                }
            });
        } catch (Exception e) {
        }
    }

    public static void a(Context context, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        c.b(context).a(k.t(f.getInstance().o()), new a<String>() {
            public void a(boolean z) {
                dji_pilot_usercenter_protocol_e_a.a((int) c.w, false, 0, Integer.valueOf(0));
            }

            public void a(long j, long j2) {
                dji_pilot_usercenter_protocol_e_a.a((int) c.w, j2, j, 0, Integer.valueOf(0));
            }

            public void a(String str) {
                Object b = dji.pilot.usercenter.protocol.a.b.b(str, true);
                dji_pilot_usercenter_protocol_e_a.a((int) c.w, 0, 0, e$b.a(0, 0, obj, b), b);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) c.w, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }
}
