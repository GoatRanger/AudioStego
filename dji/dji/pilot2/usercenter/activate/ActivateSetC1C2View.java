package dji.pilot2.usercenter.activate;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataRcGetCustomFuction;
import dji.midware.data.model.P3.DataRcSetCustomFuction;
import dji.midware.data.model.P3.DataRcSetCustomFuction.DJICustomType;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.reflect.SettingUIReflect;

public class ActivateSetC1C2View extends ActivateBaseView {
    private static final int g = 1;
    private static final int h = 2;
    private DJICustomType[] c;
    private Spinner d;
    private Spinner e;
    private ImageView f;

    private class a extends Handler {
        final /* synthetic */ ActivateSetC1C2View a;

        private a(ActivateSetC1C2View activateSetC1C2View) {
            this.a = activateSetC1C2View;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    this.a.a.a();
                    return;
                case 2:
                    this.a.a.b((String) message.obj);
                    return;
                default:
                    return;
            }
        }
    }

    public ActivateSetC1C2View(Context context) {
        super(context);
    }

    public ActivateSetC1C2View(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateSetC1C2View(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = new a();
        a();
        b();
    }

    private void a() {
        this.d = (Spinner) findViewById(R.id.e9);
        this.e = (Spinner) findViewById(R.id.e_);
        this.f = (ImageView) findViewById(R.id.ea);
    }

    private void b() {
        if (!isInEditMode()) {
            int i;
            int rcCustomStringId;
            this.c = b.a(Boolean.valueOf(true));
            ProductType c = i.getInstance().c();
            int length = this.c.length;
            String[] strArr = new String[length];
            for (i = 0; i < length; i++) {
                rcCustomStringId = SettingUIReflect.getRcCustomStringId(this.c[i]);
                strArr[i] = rcCustomStringId == 0 ? String.valueOf(this.c[i]) : getContext().getString(rcCustomStringId);
            }
            SpinnerAdapter arrayAdapter = new ArrayAdapter(getContext(), R.layout.v2_profile_artwork_spinner_layout, strArr);
            this.d.setAdapter(arrayAdapter);
            this.e.setAdapter(arrayAdapter);
            if (dji.pilot.publics.e.a.c(null)) {
                i = DJICustomType.u.a();
                rcCustomStringId = DJICustomType.n.a();
            } else if (b.k(c) || b.a(c)) {
                i = DJICustomType.b.a();
                rcCustomStringId = DJICustomType.a.a();
            } else {
                i = DataRcGetCustomFuction.getInstance().getC1();
                rcCustomStringId = DataRcGetCustomFuction.getInstance().getC2();
            }
            this.d.setSelection(a(i));
            this.e.setSelection(a(rcCustomStringId));
            if (dji.pilot.publics.e.a.c(null)) {
                this.f.setImageResource(R.drawable.activate_set_c1c2_kumquat);
            }
        }
    }

    private int a(int i) {
        DJILogHelper.getInstance().LOGD("", "pos:" + i, false, true);
        if (i == 255 || i == -1) {
            return this.c.length - 1;
        }
        for (int i2 = 0; i2 < this.c.length; i2++) {
            if (this.c[i2].a() == i) {
                return i2;
            }
        }
        return 0;
    }

    public void setData() {
        int a = this.c[this.d.getSelectedItemPosition()].a();
        DataRcSetCustomFuction.getInstance().a(a).b(this.c[this.e.getSelectedItemPosition()].a()).start(new d(this) {
            final /* synthetic */ ActivateSetC1C2View a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DataRcGetCustomFuction.getInstance().start(null);
                this.a.b.sendEmptyMessage(1);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                Message obtainMessage = this.a.b.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.obj = aVar.a() + "";
                this.a.b.sendMessage(obtainMessage);
            }
        });
    }
}
