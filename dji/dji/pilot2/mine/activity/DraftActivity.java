package dji.pilot2.mine.activity;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import dji.pilot.R;
import dji.pilot.fpv.d.c.j;
import dji.pilot.fpv.d.c.k;
import dji.pilot.usercenter.b.f;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.main.fragment.DJIMineFragment;
import dji.pilot2.mine.a.c;
import dji.pilot2.mine.b.c.b;
import dji.pilot2.mine.b.e;
import dji.pilot2.mine.b.e$a;
import dji.pilot2.share.model.UploadEvent;
import dji.pilot2.utils.l;
import dji.publics.DJIUI.DJIOriLayout;
import java.io.File;

public class DraftActivity extends DJIActivityNoFullScreen implements j, k, b {
    public static a K = new a();
    private ListView L;
    private c M;

    public static class a extends Handler {
        public void handleMessage(Message message) {
            dji.pilot2.mine.e.b b;
            super.handleMessage(message);
            Bundle data = message.getData();
            String string = data.getString(dji.pilot2.share.mode.a.m);
            dji.pilot2.mine.a.c.a e = dji.pilot2.mine.b.c.getInstance().e(string);
            int b2 = dji.pilot2.mine.b.c.getInstance().b();
            for (int i = 0; i < b2; i++) {
                b = dji.pilot2.mine.b.c.getInstance().b(i);
                if (b.e().equals(string)) {
                    break;
                }
            }
            b = null;
            if (b != null) {
                switch (message.what) {
                    case 0:
                        l.getInstance().a(b.e(), true);
                        b.a(2);
                        if (e != null) {
                            e.a(b);
                            return;
                        }
                        return;
                    case 1:
                        int i2 = data.getInt(dji.pilot2.share.mode.a.n);
                        if (b.g() != 2) {
                            b.a(2);
                        }
                        if (!e.getInstance().h() && e.getInstance().j() == e$a.CELLULAR) {
                            dji.pilot2.c.b.a.getInstance().b(dji.pilot2.c.b.a.getInstance().a(b.e()));
                        }
                        b.b(i2);
                        if (!DraftActivity.K.hasMessages(1) && e != null) {
                            if (b.g() != 2) {
                                e.a(b);
                                return;
                            } else {
                                e.d.setProgress(i2);
                                return;
                            }
                        }
                        return;
                    case 2:
                        l.getInstance().a(b.e());
                        e.getInstance().a(e.getInstance().l() + 1);
                        b.a(16);
                        if (e != null) {
                            e.a(b);
                        }
                        if (DJIMineFragment.P != null) {
                            DJIMineFragment.P.sendEmptyMessage(2);
                            DJIMineFragment.P.sendEmptyMessage(3);
                            return;
                        }
                        return;
                    case 3:
                        b.f(dji.pilot2.utils.k.d(b.d(), data.getString(dji.pilot2.share.mode.a.q)));
                        b.a(4);
                        if (e != null) {
                            e.a(b);
                        }
                        if (b.d() == "video") {
                            File file = new File(b.e() + ".info");
                            if (file.exists()) {
                                file.delete();
                            }
                        }
                        dji.pilot2.mine.b.c.getInstance().c(b);
                        b.q();
                        b.s();
                        b.r();
                        b.a(dji.pilot2.b.a.a());
                        if (DJIMineFragment.P != null) {
                            DJIMineFragment.P.sendEmptyMessage(2);
                        }
                        dji.pilot2.mine.b.a.getInstance().a(f.getInstance().i(), 1, 10, null);
                        return;
                    case 5:
                        b.a(1);
                        b.b(0);
                        if (e != null) {
                            e.a(b);
                            return;
                        }
                        return;
                    case 6:
                        b.a(32);
                        if (e != null) {
                            e.a(b);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        setContentView(R.layout.v2_activity_draft);
        DJIOriLayout.setOrientationByDevice(this);
        e.getInstance().a(0);
        dji.thirdparty.a.c.a().a(this);
        b();
        f();
        super.onCreate(bundle);
    }

    private void b() {
        this.M = new c(this);
        int i = 0;
        while (i < dji.pilot2.mine.b.c.getInstance().b()) {
            dji.pilot2.mine.e.b b = dji.pilot2.mine.b.c.getInstance().b(i);
            if (new File(b.e()).exists() || b.g() == 4) {
                i++;
            } else {
                dji.pilot2.mine.b.c.getInstance().c(b);
            }
        }
        dji.pilot2.mine.b.c.getInstance().a((b) this);
    }

    private void f() {
        this.L = (ListView) findViewById(R.id.c8c);
        this.L.addFooterView(LayoutInflater.from(this).inflate(R.layout.v2_mine_draft_footer, null), null, false);
        this.L.setAdapter(this.M);
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.c8b:
                finish();
                return;
            default:
                return;
        }
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onDestroy() {
        dji.pilot2.mine.b.c.getInstance().b((b) this);
        e.getInstance().a(0);
        dji.thirdparty.a.c.a().d(this);
        super.onDestroy();
    }

    public void a() {
        if (K != null) {
            K.post(new Runnable(this) {
                final /* synthetic */ DraftActivity a;

                {
                    this.a = r1;
                }

                public void run() {
                    if (this.a.M != null) {
                        this.a.M.notifyDataSetChanged();
                    }
                }
            });
        }
    }

    public void onEventMainThread(UploadEvent uploadEvent) {
        if (uploadEvent.result == dji.pilot2.share.model.UploadEvent.a.UPLOAD_SUCCESS) {
            finish();
        }
    }

    protected void onStart() {
        super.onStart();
        dji.pilot.fpv.d.e.b(this);
    }

    protected void onStop() {
        dji.pilot.fpv.d.e.c(this);
        super.onStop();
    }
}
