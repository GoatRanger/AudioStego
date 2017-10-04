package dji.pilot.playback.litchi;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.pilot.publics.widget.b;
import dji.pilot.usercenter.widget.DJIProgressBar;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class g extends e {
    private OnClickListener l = null;
    private DJIPlayBackRemoteView m = null;
    private DJITextView n = null;
    private DJIImageView o = null;
    private DJIImageView p = null;
    private DJIRelativeLayout q = null;
    private b r = null;
    private b s = null;
    private DJITextView t = null;
    private DJITextView u = null;
    private DJIProgressBar v = null;

    protected View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.k = layoutInflater.inflate(R.layout.playback_remote_view, viewGroup, false);
        s();
        r();
        t();
        return this.k;
    }

    public int k() {
        return h;
    }

    private void r() {
        h = -1;
    }

    private void s() {
        this.l = new OnClickListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int id = view.getId();
                if (R.id.bh9 == id) {
                    e.a("PlayBack_BottomBarView_Button_SharePhotos");
                    this.a.e();
                } else if (R.id.bh7 == id) {
                    e.a("PlayBack_BottomBarView_Button_DeletePhotos");
                    this.a.f();
                } else if (R.id.bh5 == id) {
                    e.a("PlayBack_BottomBarView_Button_DownloadPhotos");
                    if (!h.getInstance().a) {
                        this.a.u();
                    }
                } else if (R.id.bhd == id) {
                    if (e.h == -1) {
                        e.a("PlayBack_BottomBarView_Button_Select");
                        e.h = 1;
                        this.a.n.setText(R.string.app_cancel);
                        this.a.i = false;
                        this.a.d.setImageDrawable(this.a.getResources().getDrawable(R.drawable.fpv_playback_unselectall));
                        this.a.c.show();
                        this.a.b.show();
                        this.a.d.show();
                        this.a.a().c.go();
                        this.a.a().f.show();
                        this.a.m.notifyDataChanged();
                    } else if (e.h == 1) {
                        e.a("PlayBack_BottomBarView_Button_Cancel");
                        this.a.g();
                    }
                } else if (R.id.bh6 == id) {
                    e.a("PlayBack_BottomBarView_Button_CancelDownload");
                    this.a.g();
                } else if (R.id.bh8 == id) {
                    e.a("PlayBack_BottomBarView_Button_SelectAll");
                    if (this.a.i) {
                        this.a.m.clearSelects();
                        this.a.i = false;
                        this.a.d.setImageDrawable(this.a.getResources().getDrawable(R.drawable.fpv_playback_unselectall));
                        return;
                    }
                    this.a.h();
                    this.a.i = true;
                    this.a.d.setImageDrawable(this.a.getResources().getDrawable(R.drawable.fpv_playback_selectall));
                }
            }
        };
    }

    private void t() {
        this.m = (DJIPlayBackRemoteView) b(R.id.bhw);
        this.a = (DJIRelativeLayout) b(R.id.bhj);
        this.n = (DJITextView) b(R.id.bhd);
        this.d = (DJIImageView) b(R.id.bh8);
        this.b = (DJIImageView) b(R.id.bh7);
        this.c = (DJIImageView) b(R.id.bh5);
        this.o = (DJIImageView) b(R.id.bh6);
        this.p = (DJIImageView) b(R.id.bh4);
        this.q = (DJIRelativeLayout) b(R.id.bh_);
        this.t = (DJITextView) b(R.id.bha);
        this.u = (DJITextView) b(R.id.bhb);
        this.v = (DJIProgressBar) b(R.id.bhc);
        this.b.setOnClickListener(this.l);
        this.b.setEnabled(false);
        this.c.setOnClickListener(this.l);
        this.c.setEnabled(false);
        this.n.setOnClickListener(this.l);
        this.o.setOnClickListener(this.l);
        this.d.setOnClickListener(this.l);
        this.m.attachFragment(this);
    }

    private void u() {
        if (this.r == null) {
            this.r = b.a(this.j, (int) R.string.fpv_playback_download_files, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.w();
                }
            }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.x();
                }
            });
            this.r.setCancelable(true);
            this.r.setCanceledOnTouchOutside(true);
        }
        if (this.r != null && !this.r.isShowing()) {
            this.r.a((int) R.string.fpv_playback_download_files);
            this.r.show();
        }
    }

    private void v() {
        if (this.r != null && this.r.isShowing()) {
            this.r.dismiss();
            this.r = null;
        }
    }

    private void w() {
        v();
    }

    private void x() {
        v();
        n();
    }

    private void y() {
        if (this.s == null) {
            this.s = b.a(this.j, (int) R.string.fpv_playback_download_cancel, (int) R.string.btn_dlg_no, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.A();
                }
            }, (int) R.string.btn_dlg_yes, new DialogInterface.OnClickListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.B();
                }
            });
            this.s.setCancelable(true);
            this.s.setCanceledOnTouchOutside(true);
        }
        if (this.s != null && !this.s.isShowing()) {
            this.s.a((int) R.string.fpv_playback_download_cancel);
            this.s.show();
        }
    }

    private void z() {
        if (this.s != null && this.s.isShowing()) {
            this.s.dismiss();
            this.s = null;
        }
    }

    private void A() {
        z();
    }

    private void B() {
        z();
        h = -1;
        h.getInstance().c();
        h.getInstance().h();
        this.m.clearSelects();
        this.p.go();
        this.q.go();
        this.n.setText(R.string.app_select);
        this.n.show();
        this.o.go();
        this.d.go();
        a().c.show();
        a().f.go();
        this.m.notifyDataChanged();
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

    public void n() {
        this.n.go();
        this.c.go();
        this.b.go();
        this.d.go();
        this.o.show();
        this.p.show();
        this.q.show();
        this.m.downloadSelects(this.p, this.t, this.u, this.v);
    }

    public void e() {
    }

    public void f() {
        this.m.deleteSelects();
    }

    public void g() {
        if (h.getInstance().a) {
            y();
            return;
        }
        h = -1;
        this.m.clearSelects();
        this.n.setText(R.string.app_select);
        this.n.show();
        this.p.go();
        this.q.go();
        this.o.go();
        this.b.go();
        this.c.go();
        this.d.go();
        a().c.show();
        a().f.go();
        this.m.notifyDataChanged();
    }

    public void h() {
        this.m.selectAllPic();
    }

    public void o() {
        if (this.m != null) {
            this.m.cancelCurrentTask();
        }
    }

    public void a(int i) {
        this.m.deleteAlbum(i);
    }

    public boolean c() {
        return this.m.b;
    }

    public boolean d() {
        return this.m.c;
    }

    public void i() {
        this.m.checkDownload();
        this.m.notifyDataChanged();
    }

    public void j() {
        this.m.d = true;
        Log.d("cancel list", "cancelCurrentTask");
    }
}
