package dji.pilot.playback.litchi;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class f extends e {
    private OnClickListener l = null;
    private DJIPlayBackLocalView m = null;
    private DJITextView n = null;
    private DJIImageView o = null;
    private DJIImageView p = null;
    private DJIImageView q = null;

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.k = layoutInflater.inflate(R.layout.playback_local_view, viewGroup, false);
        o();
        n();
        r();
        return this.k;
    }

    public int k() {
        return h;
    }

    private void n() {
        h = -1;
    }

    private void o() {
        this.l = new OnClickListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.bh9 == id) {
                    this.a.e();
                } else if (R.id.bh7 == id) {
                    this.a.f();
                } else if (R.id.bhd == id) {
                    if (e.h == -1) {
                        e.h = 0;
                        this.a.n.setText(R.string.app_cancel);
                        this.a.p.show();
                        this.a.o.show();
                        this.a.q.show();
                        this.a.a().c.go();
                        this.a.a().f.show();
                        this.a.m.notifyDataChanged();
                    } else if (e.h == 0) {
                        e.h = -1;
                        this.a.n.setText(R.string.app_select);
                        this.a.p.go();
                        this.a.o.go();
                        this.a.q.go();
                        this.a.a().c.show();
                        this.a.a().f.go();
                        this.a.m.notifyDataChanged();
                        this.a.g();
                    }
                } else if (R.id.bh8 == id) {
                    this.a.h();
                }
            }
        };
    }

    private void r() {
        this.m = (DJIPlayBackLocalView) b(R.id.bhi);
        this.a = (DJIRelativeLayout) b(R.id.bhj);
        this.p = (DJIImageView) b(R.id.bh9);
        this.o = (DJIImageView) b(R.id.bh7);
        this.n = (DJITextView) b(R.id.bhd);
        this.q = (DJIImageView) b(R.id.bh8);
        this.p.setOnClickListener(this.l);
        this.o.setOnClickListener(this.l);
        this.n.setOnClickListener(this.l);
        this.q.setOnClickListener(this.l);
        this.m.attachFragment(this);
    }

    public void onStart() {
        super.onStart();
    }

    public void onStop() {
        super.onStop();
    }

    protected void l() {
    }

    public boolean a(KeyEvent keyEvent) {
        return super.a(keyEvent);
    }

    public boolean m() {
        return super.m();
    }

    public void e() {
        this.m.shareSelects();
    }

    public void f() {
        this.m.deleteSelects();
    }

    public void a(int i) {
        this.m.deleteAlbum(i);
    }

    public void g() {
        this.m.clearSelects();
    }

    public void h() {
        this.m.selectAllPic();
    }

    public boolean c() {
        return false;
    }

    public boolean d() {
        return false;
    }

    public void i() {
    }

    public void j() {
    }
}
