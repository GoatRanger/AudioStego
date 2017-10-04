package dji.pilot2.account.forget;

import android.content.Context;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot.usercenter.protocol.e$a;
import dji.pilot2.account.forget.a.a;

public class b implements a {
    private Context a;
    private dji.pilot2.account.forget.a.b b;
    private String c;
    private e$a d = new e$a(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a(int i, int i2, int i3, Object obj, Object obj2) {
            this.a.b.a(true, this.a.c);
        }

        public void a(int i, int i2, int i3, Object obj) {
            String string;
            if (i2 == 107) {
                string = this.a.a.getResources().getString(R.string.home_account_email_illegal);
            } else if (i2 == -1) {
                string = this.a.a.getResources().getString(R.string.account_network_error);
            } else {
                string = this.a.a.getResources().getString(R.string.home_account_unknow_err);
            }
            this.a.b.a(false, string);
        }

        public void a(int i, long j, long j2, int i2, Object obj) {
        }

        public void a(int i, boolean z, int i2, Object obj) {
        }
    };

    public b(dji.pilot2.account.forget.a.b bVar, Context context) {
        this.a = context;
        this.b = bVar;
        this.b.a(this);
    }

    public void a(String str) {
        this.c = str;
        f.getInstance().a(str);
    }

    public void a() {
        f.getInstance().a(this.d);
    }

    public void b() {
        f.getInstance().b(this.d);
    }
}
