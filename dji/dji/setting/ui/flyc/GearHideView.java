package dji.setting.ui.flyc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.d;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.params.P3.ParamInfo;
import dji.midware.data.params.P3.a;
import dji.pilot.setting.ui.R;
import dji.setting.ui.flyc.a.b;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class GearHideView extends DividerLinearLayout implements OnCheckedChangeListener, a {
    private static final long E = 2000;
    private ParamInfo F = null;
    private Switch G = null;
    private final Runnable H = new Runnable(this) {
        final /* synthetic */ GearHideView a;

        {
            this.a = r1;
        }

        public void run() {
            a.b().a(this.a.a);
        }
    };
    String a = a.e;

    public GearHideView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, R.layout.setting_ui_flyc_gear_hide);
        if (!isInEditMode()) {
            int i = 1;
            if (DataOsdGetPushCommon.getInstance().isGetted()) {
                i = DataOsdGetPushCommon.getInstance().getFlycVersion();
            }
            if (i < 16) {
                this.F = d.read(this.a);
            } else {
                this.a = a.f;
                this.F = d.read(this.a);
            }
            this.G = (Switch) findViewById(R.id.setting_ui_item_switch);
            this.G.setOnCheckedChangeListener(this);
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        if (compoundButton == this.G && getVisibility() == 0) {
            if ((this.F.value.intValue() != 0) != z) {
                if (z) {
                    dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_flyc_gear_hide_tip, new OnClickListener(this) {
                        final /* synthetic */ GearHideView b;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.b.a(z);
                        }
                    }, new OnClickListener(this) {
                        final /* synthetic */ GearHideView a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.b();
                        }
                    }).setCancelable(false);
                } else {
                    a(z);
                }
            }
        }
    }

    private void a(final boolean z) {
        int i = 0;
        this.G.setEnabled(false);
        DataFlycSetParams dataFlycSetParams = new DataFlycSetParams();
        String str = this.a;
        if (z) {
            i = 1;
        }
        dataFlycSetParams.a(str, Integer.valueOf(i)).start(new dji.midware.e.d(this) {
            final /* synthetic */ GearHideView b;

            public void onSuccess(Object obj) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.G.setEnabled(true);
                        if (z) {
                            this.a.b.b(false);
                        }
                    }
                });
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass4 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.b();
                        this.a.b.G.setEnabled(true);
                    }
                });
            }
        });
    }

    private void b() {
        boolean z = this.F.value.intValue() != 0;
        this.G.setChecked(z);
        if (z) {
            b(true);
        } else {
            removeCallbacks(this.H);
        }
    }

    private void b(boolean z) {
        Object obj = this.F.value.intValue() != 0 ? 1 : null;
        if (!z || obj != null) {
            removeCallbacks(this.H);
            postDelayed(this.H, 2000);
        }
    }

    public void onEventMainThread(ProductType productType) {
        if (dji.pilot.publics.e.a.g()) {
            setVisibility(0);
            b();
            a.b().a(this.a);
            return;
        }
        setVisibility(8);
    }

    public void onEventMainThread(o oVar) {
        if (o.b == oVar && getVisibility() == 0) {
            a.b().a(this.a);
        }
    }

    public void onEventMainThread(a.a aVar) {
        if (this.a.equals(aVar.a)) {
            b();
        }
    }

    public void onEventMainThread(b bVar) {
        if (this.a.equals(bVar.a)) {
            a.b().a(this.a);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            onEventMainThread(i.getInstance().c());
        }
    }

    protected void onDetachedFromWindow() {
        if (!isInEditMode() && c.a().c(this)) {
            c.a().d(this);
        }
        removeCallbacks(this.H);
        super.onDetachedFromWindow();
    }
}
