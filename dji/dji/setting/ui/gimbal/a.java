package dji.setting.ui.gimbal;

import dji.midware.data.model.P3.DataGimbalGetUserParams;
import dji.midware.data.model.P3.DataGimbalSaveUserParams;
import dji.midware.e.d;
import dji.thirdparty.a.c;

public class a {
    private static long a = 0;
    private static String[] b = new String[]{"table_choice", "pitch_expo", "pitch_exp", "yaw_expo", "yaw_follow_exp", "pitch_time_expo", "yaw_time_expo"};

    public static void a() {
        if (System.currentTimeMillis() - a > 1000) {
            d();
            a = System.currentTimeMillis();
        }
    }

    public static void b() {
        d();
        a = System.currentTimeMillis();
    }

    private static void d() {
        DataGimbalGetUserParams.getInstance().setInfos(b).start(new d() {
            public void onSuccess(Object obj) {
                c.a().e(new a());
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    public static void c() {
        dji.publics.a.a().postDelayed(new Runnable() {
            public void run() {
                DataGimbalSaveUserParams.getInstance().start(new d(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
            }
        }, 500);
    }
}
