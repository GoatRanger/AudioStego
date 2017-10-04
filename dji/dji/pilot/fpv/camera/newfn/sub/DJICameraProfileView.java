package dji.pilot.fpv.camera.newfn.sub;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import dji.midware.data.model.P3.DataCameraLoadParams;
import dji.midware.data.model.P3.DataCameraSaveParams;
import dji.midware.data.model.P3.DataCameraSaveParams.USER;
import dji.pilot.R;
import dji.pilot.fpv.camera.more.a;
import dji.pilot.fpv.camera.more.a.b;
import dji.pilot.fpv.camera.newfn.DJICameraBaseListView;
import dji.pilot.publics.widget.d;
import java.util.ArrayList;
import java.util.List;

public class DJICameraProfileView extends DJICameraBaseListView {
    private b B = a.getInstance().aq();
    private dji.pilot.publics.widget.b C = null;
    private d D = null;

    public DJICameraProfileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected boolean a(int i, int i2, Object obj) {
        b(i);
        return true;
    }

    private void b(final int i) {
        final dji.pilot.fpv.camera.newfn.b.d dVar = (dji.pilot.fpv.camera.newfn.b.d) this.y.get(i);
        if (this.D == null) {
            this.D = d.a(getContext(), (int) R.string.camera_profile_rename, (int) R.string.app_cancel, new OnClickListener(this) {
                final /* synthetic */ DJICameraProfileView a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.b();
                }
            }, (int) R.string.app_save, new OnClickListener(this) {
                final /* synthetic */ DJICameraProfileView c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    if (this.c.a(i, dVar, this.c.D.c())) {
                        this.c.b();
                    }
                }
            }).b().b(dVar.f);
        }
        if (!this.D.isShowing()) {
            this.D.show();
        }
    }

    private boolean a(int i, dji.pilot.fpv.camera.newfn.b.d dVar, String str) {
        int a = this.B.a(i, str, new dji.midware.e.d(this) {
            final /* synthetic */ DJICameraProfileView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                String[] a = this.a.B.a();
                for (int i = 0; i < a.length; i++) {
                    ((dji.pilot.fpv.camera.newfn.b.d) this.a.y.get(i)).f = a[i];
                }
                this.a.w.notifyDataSetChanged();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                Toast.makeText(this.a.getContext().getApplicationContext(), R.string.camera_profile_setfail, 1).show();
            }
        });
        if (a != 0) {
            Toast.makeText(getContext().getApplicationContext(), a, 1).show();
        }
        if (a == 0) {
            return true;
        }
        return false;
    }

    private void b() {
        if (this.D != null && this.D.isShowing()) {
            this.D.dismiss();
            this.D = null;
        }
    }

    protected boolean a(AdapterView<?> adapterView, View view, int i, long j) {
        c(i);
        return true;
    }

    private void c(int i) {
        final dji.pilot.fpv.camera.newfn.b.d dVar = (dji.pilot.fpv.camera.newfn.b.d) this.y.get(i);
        if (this.C == null) {
            this.C = dji.pilot.publics.widget.b.a(getContext(), (int) R.string.app_tip, (int) R.string.camera_profile_tip, (int) R.string.camera_profile_save, new OnClickListener(this) {
                final /* synthetic */ DJICameraProfileView b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.b.c();
                    DataCameraSaveParams.getInstance().setMode(USER.find(dVar.j)).start(null);
                }
            }, (int) R.string.camera_profile_load, new OnClickListener(this) {
                final /* synthetic */ DJICameraProfileView b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.b.c();
                    DataCameraLoadParams.getInstance().setMode(USER.find(dVar.j)).start(null);
                }
            });
        }
        if (!this.C.isShowing()) {
            this.C.show();
        }
    }

    private void c() {
        if (this.C != null && this.C.isShowing()) {
            this.C.dismiss();
            this.C = null;
        }
    }

    protected List<dji.pilot.fpv.camera.newfn.b.d> a() {
        List<dji.pilot.fpv.camera.newfn.b.d> arrayList = new ArrayList();
        a(2, this.B.a(), null, this.B.b(), dji.pilot.fpv.camera.more.d.p_, arrayList);
        return arrayList;
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.y = a();
        this.w.a(this.y);
    }

    public void dispatchOnStart() {
        super.dispatchOnStart();
    }

    public void dispatchOnStop() {
        c();
        b();
        super.dispatchOnStop();
    }

    public void dispatchOnPause() {
        super.dispatchOnPause();
    }

    public void dispatchOnResume() {
        super.dispatchOnResume();
    }
}
