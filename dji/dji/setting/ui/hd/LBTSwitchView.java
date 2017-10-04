package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdGetSdrLBT;
import dji.midware.data.model.P3.DataOsdSetSdrLBT;
import dji.midware.e.d;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class LBTSwitchView extends ItemViewSwitch {
    private boolean a = false;

    public LBTSwitchView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private void a() {
        if (a.d()) {
            setVisibility(8);
        } else {
            setVisibility(8);
        }
        DataOsdGetSdrLBT.getInstance().start(new d(this) {
            final /* synthetic */ LBTSwitchView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a = DataOsdGetSdrLBT.getInstance().getLBTStatue();
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.eS_.setChecked(this.a.a.a);
                    }
                });
            }

            public void onFailure(a aVar) {
                DJILogHelper.getInstance().LOGD("", "SDR LBT get fail", false, true);
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        DataOsdSetSdrLBT.getInstance().a(z).start(new d(this) {
            final /* synthetic */ LBTSwitchView b;

            public void onSuccess(Object obj) {
                this.b.a = z;
            }

            public void onFailure(a aVar) {
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.eS_.setChecked(this.a.b.a);
                    }
                });
            }
        });
    }
}
