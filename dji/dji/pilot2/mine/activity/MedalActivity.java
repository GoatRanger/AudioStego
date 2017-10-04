package dji.pilot2.mine.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.dji.frame.c.c;
import com.dji.frame.c.h;
import dji.pilot.R;
import dji.pilot.fpv.d.c$m;
import dji.pilot.fpv.d.e;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.mine.a.f;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean;
import dji.pilot2.mine.jsonbean.UserLevelJsonBean.LevelInfo;
import dji.pilot2.utils.k;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.thirdparty.afinal.f.a;
import java.util.ArrayList;
import java.util.List;

public class MedalActivity extends DJIActivityNoFullScreen implements c$m {
    public static final String G = "user_id";
    GridView H;
    f I;
    String J;
    String K;
    List<LevelInfo> L = null;

    @TargetApi(19)
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_medal);
        a();
        b();
    }

    private void a() {
        this.J = getIntent().getStringExtra("user_id");
        this.K = null;
        this.L = new ArrayList();
        this.I = new f(this);
        this.I.a(this.L);
    }

    private void b() {
        this.H = (GridView) findViewById(R.id.c9j);
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.H.setNumColumns(2);
        } else {
            this.H.setNumColumns(1);
        }
        this.H.setAdapter(this.I);
        this.H.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ MedalActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                e.b(c$m.dK_);
                Intent intent = new Intent(this.a, MedalIntroduceActivity.class);
                intent.putExtra(MedalIntroduceActivity.a, i);
                this.a.startActivity(intent);
            }
        });
        f();
    }

    private void f() {
        String i = dji.pilot.usercenter.b.f.getInstance().i();
        dji.pilot.usercenter.b.f.getInstance().n();
        String j = dji.pilot.usercenter.b.f.getInstance().j();
        if (this.J != null && this.J.equals(i)) {
            a(dji.pilot2.mine.b.e.getInstance().c(j));
        }
        c.b(this).a(k.t(this.J), new a<String>(this) {
            final /* synthetic */ MedalActivity a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                this.a.a(str);
            }

            public void a(Throwable th, int i, String str) {
            }
        });
    }

    private void a(String str) {
        if (str != null) {
            this.K = str;
            UserLevelJsonBean userLevelJsonBean = null;
            if (!(str == null || str.equals(""))) {
                userLevelJsonBean = (UserLevelJsonBean) h.b(str, UserLevelJsonBean.class);
            }
            if (userLevelJsonBean != null && userLevelJsonBean.medal != null) {
                this.L.clear();
                this.L.add(userLevelJsonBean.medal.level);
                this.L.add(userLevelJsonBean.medal.director);
                this.L.add(userLevelJsonBean.medal.photographer);
                this.L.add(userLevelJsonBean.medal.share);
                this.I.notifyDataSetChanged();
            }
        }
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.c9h:
                finish();
                return;
            case R.id.c9i:
                startActivity(new Intent(this, MedalIntroduceActivity.class));
                return;
            default:
                return;
        }
    }
}
