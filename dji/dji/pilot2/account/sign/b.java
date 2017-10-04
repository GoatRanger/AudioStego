package dji.pilot2.account.sign;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.flyforbid.FlyforbidUpdateService;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.protocol.d;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.account.sign.a.a;
import dji.pilot2.nativeexplore.model.ExploreEvent;
import dji.pilot2.utils.m;
import dji.thirdparty.a.c;
import java.util.Random;

public class b implements a {
    private static final String a = "DJIAccountSignPresenter";
    private dji.pilot2.account.sign.a.b b;
    private Context c;
    private f d;
    private Handler e;
    private String f;
    private String g;
    private e$a h = new e$a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a(int i, int i2, int i3, Object obj, Object obj2) {
            switch (i) {
                case d.l /*196608*/:
                    this.a.e.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.b.a(true, null);
                        }
                    });
                    FlyforbidUpdateService.d = false;
                    return;
                case d.m /*196624*/:
                    this.a.d.b(this);
                    this.a.d.a(this.a.i);
                    return;
                case d.r /*196704*/:
                    this.a.e.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.b.b(true, null);
                        }
                    });
                    return;
                default:
                    return;
            }
        }

        public void a(int i, int i2, int i3, Object obj) {
            DJILogHelper.getInstance().LOGD(b.a, "onFail: cmdId =" + i + " errCode =" + i2);
            String str;
            if (d.l == i) {
                if (i2 == d.b || i2 == 500) {
                    this.a.c.getResources().getString(R.string.home_account_pwd_invalid);
                    this.a.b.a(false, i2);
                } else if (i2 == 107) {
                    this.a.c.getResources().getString(R.string.home_account_email_illegal);
                    this.a.b.a(false, i2);
                } else if (i2 == -1) {
                    this.a.b.a(false, this.a.c.getResources().getString(R.string.account_network_error));
                } else if (i2 == 300) {
                    this.a.b.a(false, this.a.c.getResources().getString(R.string.home_account_verification_code_invalid));
                } else if (i2 == d.k) {
                    this.a.b.a(false, this.a.c.getResources().getString(R.string.home_account_input_verification_code));
                    this.a.b.b();
                } else {
                    if (obj instanceof String) {
                        str = (String) obj;
                    } else {
                        str = this.a.c.getResources().getString(R.string.home_account_sign_fail);
                    }
                    this.a.b.a(false, str);
                }
            } else if (d.m == i) {
                if (i2 == 200) {
                    obj = this.a.c.getResources().getString(R.string.home_account_email_illegal);
                } else if (i2 == d.f) {
                    obj = this.a.c.getResources().getString(R.string.home_account_email_registered);
                } else if (i2 == -1) {
                    obj = this.a.c.getResources().getString(R.string.account_network_error);
                } else if (i2 == 300) {
                    obj = this.a.c.getResources().getString(R.string.home_account_verification_code_invalid);
                } else if (i2 == d.k) {
                    obj = this.a.c.getResources().getString(R.string.home_account_input_verification_code);
                    this.a.b.c(false, obj);
                    this.a.b.d();
                } else if (obj instanceof String) {
                    str = (String) obj;
                } else {
                    obj = this.a.c.getResources().getString(R.string.home_account_singup_fail);
                }
                this.a.b.c(false, obj);
            } else if (d.r == i) {
                if (i2 == 200) {
                    obj = this.a.c.getResources().getString(R.string.home_account_email_illegal);
                } else if (i2 == d.f) {
                    obj = this.a.c.getResources().getString(R.string.home_account_email_registered);
                } else if (i2 == -1) {
                    obj = this.a.c.getResources().getString(R.string.account_network_error);
                } else if (i2 == 300) {
                    obj = this.a.c.getResources().getString(R.string.home_account_verification_code_invalid);
                } else if (i2 == d.k) {
                    obj = this.a.c.getResources().getString(R.string.home_account_input_verification_code);
                    this.a.b.c();
                } else if (obj instanceof String) {
                    str = (String) obj;
                } else {
                    obj = this.a.c.getResources().getString(R.string.account_network_error);
                }
                this.a.b.b(false, obj);
            } else if (d.o == i) {
                DJILogHelper.getInstance().LOGE(b.a, "CMDID_ACCOUNT_GET_PROFILES failed");
                this.a.e.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.b.a(true, null);
                    }
                });
            }
            this.a.a();
        }

        public void a(int i, long j, long j2, int i2, Object obj) {
        }

        public void a(int i, boolean z, int i2, Object obj) {
        }
    };
    private e$a i = new e$a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a(int i, int i2, int i3, Object obj, Object obj2) {
            c.a().e(ExploreEvent.USER_LOGIN);
            dji.pilot.fpv.d.a.getInstance().a(f.getInstance().m(), f.getInstance().i());
            this.a.c();
        }

        public void a(int i, int i2, int i3, Object obj) {
            this.a.c();
        }

        public void a(int i, long j, long j2, int i2, Object obj) {
        }

        public void a(int i, boolean z, int i2, Object obj) {
        }
    };

    public b(dji.pilot2.account.sign.a.b bVar, Context context) {
        this.c = context;
        this.b = bVar;
        this.b.a(this);
        this.e = new Handler(Looper.getMainLooper());
        this.d = f.getInstance();
    }

    public void a(String str, String str2) {
        this.d.b(str, str2);
    }

    public void a(String str, String str2, String str3) {
        this.d.b(str, str2, str3, this.g);
    }

    public void a() {
        this.g = d();
        this.b.a("https://www.skypixel.com/api/website/validate_code_img?key=" + this.g);
    }

    public void b(String str, String str2) {
        if (m.c(str) || !dji.pilot.publics.e.d.b(str)) {
            this.b.b(false, this.c.getString(R.string.home_account_email_illegal));
            return;
        }
        this.f = str;
        this.d.a(str, str2, this.g);
    }

    public void a(String str) {
        this.d.a(this.f, str);
    }

    public void c(String str, String str2) {
        this.d.a(this.f, str, str2, this.g);
    }

    public void b() {
        this.d.b(this.h);
    }

    public void a(int i) {
        this.d.a(this.h);
    }

    private void c() {
        this.e.post(new Runnable(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b.c(true, null);
            }
        });
        FlyforbidUpdateService.d = false;
        this.d.b(this.i);
    }

    private String d() {
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            stringBuffer.append(str.charAt(random.nextInt(36)));
        }
        return stringBuffer.toString();
    }
}
