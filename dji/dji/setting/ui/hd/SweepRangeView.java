package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RadioGroup;
import com.tencent.android.tpush.common.Constants;
import dji.log.WM220LogUtil;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataOsdSetSweepFrequency;
import dji.midware.e.d;
import dji.setting.ui.widget.ItemViewRadio;

public class SweepRangeView extends ItemViewRadio {
    private a a;
    private int b = 1;

    public interface a {
        void a(int i);
    }

    public SweepRangeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g.setText(Constants.UNSTALL_PORT);
        this.h.setText("300");
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        if (a.d()) {
            setVisibility(8);
        } else {
            setVisibility(8);
        }
        this.g.setChecked(true);
        this.b = 0;
        b();
    }

    private void b() {
        DataOsdSetSweepFrequency.getInstance().b(0).a(this.b).a(true).start(new d(this) {
            final /* synthetic */ SweepRangeView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                WM220LogUtil.LOGI("setSweepRange success");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                WM220LogUtil.LOGI("setSweepRange fail!");
                if (this.a.a != null) {
                    this.a.a.a(this.a.b);
                }
            }
        });
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == this.h.getId()) {
            this.b = 1;
        } else {
            this.b = 0;
        }
        b();
    }

    public void setOnRangeChangedListener(a aVar) {
        this.a = aVar;
    }
}
