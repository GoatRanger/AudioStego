package dji.pilot2.usercenter.b;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import dji.pilot.R;
import dji.pilot.fpv.view.DJIStageView;
import dji.pilot.fpv.view.DJIStageView.e;
import dji.pilot.publics.objects.c;
import dji.pilot.usercenter.e.b;
import dji.pilot.usercenter.profile.DJISelectRegionView;
import dji.publics.DJIUI.DJITextView;

public class a extends c {
    private DJITextView a = null;
    private DJITextView b = null;
    private DJITextView c = null;
    private DJIStageView d = null;
    private e e = null;
    private OnClickListener f = null;
    private a g = null;

    public interface a {
        void a(b bVar);
    }

    public a(Context context) {
        super(context);
        b();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        c();
        a((int) this.N.getResources().getDimension(R.dimen.gb), (int) this.N.getResources().getDimension(R.dimen.gb), -100, 17, false, false);
        a(0.4f);
    }

    private void b() {
        this.f = new OnClickListener(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.c0r:
                        if (this.a.d.canGoBack()) {
                            this.a.d.destoryStageView(true);
                            return;
                        } else {
                            this.a.dismiss();
                            return;
                        }
                    case R.id.c0t:
                        this.a.d();
                        return;
                    default:
                        return;
                }
            }
        };
        this.e = new e(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(int i, int i2, int i3) {
                this.a.a(i, i2, i3);
            }

            public void a(int i) {
                this.a.dismiss();
            }
        };
    }

    protected void a(int i, int i2, int i3) {
        if (i == 1) {
            this.b.show();
            this.a.show();
            this.a.setText(R.string.app_cancel);
            return;
        }
        this.a.setText(R.string.app_back);
        this.a.show();
        this.b.go();
    }

    private void a(int i) {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.height = i;
        getWindow().setAttributes(attributes);
    }

    private void c() {
        setContentView(R.layout.profile_edit_view);
        this.c = (DJITextView) findViewById(R.id.c0s);
        this.c.go();
        this.a = (DJITextView) findViewById(R.id.c0r);
        this.a.setOnClickListener(this.f);
        this.b = (DJITextView) findViewById(R.id.c0t);
        this.b.setOnClickListener(this.f);
        this.d = (DJIStageView) findViewById(R.id.bje);
        this.d.setOnStageChangeListener(this.e);
        this.d.createStageView(R.layout.profile_select_region_view, R.string.usercenter_my_info_select_region, false);
        ((DJISelectRegionView) findViewById(R.id.bjf)).setOnSelectListener(new dji.pilot.usercenter.profile.DJISelectRegionView.a(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void a(b bVar) {
                this.a.g.a(bVar);
                this.a.dismiss();
            }
        });
    }

    protected void onStart() {
        super.onStart();
        this.d.dispatchOnStart(false);
    }

    protected void onStop() {
        this.d.dispatchOnStop(false);
        super.onStop();
    }

    public void a(a aVar) {
        if (aVar != null) {
            this.g = aVar;
        }
    }

    protected boolean a() {
        if (!this.d.canGoBack()) {
            return false;
        }
        this.d.destoryStageView(true);
        return true;
    }

    private void d() {
        dismiss();
        this.g.a(null);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyUp(i, keyEvent);
        }
        dismiss();
        return true;
    }
}
