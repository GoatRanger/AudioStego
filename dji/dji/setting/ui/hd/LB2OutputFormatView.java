package dji.setting.ui.hd;

import android.content.Context;
import android.util.AttributeSet;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataDm368GetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams;
import dji.midware.data.model.P3.DataDm368SetGParams.CmdId;
import dji.midware.e.d;
import dji.publics.a;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;
import dji.thirdparty.a.c;

public class LB2OutputFormatView extends ItemViewSpinner {
    private String[] a = new String[]{"1080P24", "1080P25", "1080P30", "1080P50", "1080P60", "1080I50", "1080I60", "720P24", "720P25", "720P30", "720P50", "720P60"};
    private String[] b = new String[]{"1080P24", "1080P25", "1080P30", "1080P50", "1080P60", "1080I50", "1080I60", "720P25", "720P30", "720P50", "720P60"};

    public LB2OutputFormatView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            a();
            a.f();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        a.a().postDelayed(new Runnable(this) {
            final /* synthetic */ LB2OutputFormatView a;

            {
                this.a = r1;
            }

            public void run() {
                a.f();
            }
        }, 700);
    }

    public void onEventMainThread(a aVar) {
        a();
    }

    private void a() {
        if (a.a()) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        if (DataDm368GetGParams.getInstance().getOutputEnable()) {
            int hDMIFormat;
            int outputDevice = DataDm368GetGParams.getInstance().getOutputDevice();
            if (outputDevice == 0) {
                hDMIFormat = DataDm368GetGParams.getInstance().getHDMIFormat();
            } else {
                hDMIFormat = DataDm368GetGParams.getInstance().getSDIFormat();
            }
            this.f.setData(outputDevice == 0 ? this.a : this.b, a(hDMIFormat, outputDevice), (b) this);
            return;
        }
        setVisibility(8);
    }

    private int a(int i, int i2) {
        if (i2 != 0) {
            switch (i) {
                case 0:
                    return 10;
                case 1:
                    return 9;
                case 2:
                    return 6;
                case 3:
                    return 5;
                case 4:
                    return 4;
                case 5:
                    return 3;
                case 6:
                    return 2;
                case 7:
                    return 0;
                case 8:
                    return 1;
                case 9:
                    return 8;
                case 10:
                    return 7;
                default:
                    break;
            }
        }
        switch (i) {
            case 0:
                return 11;
            case 1:
                return 10;
            case 2:
                return 6;
            case 3:
                return 5;
            case 4:
                return 4;
            case 5:
                return 3;
            case 6:
                return 2;
            case 7:
                return 0;
            case 8:
                return 1;
            case 9:
                return 9;
            case 10:
                return 8;
            case 11:
                return 7;
        }
        return 0;
    }

    private int b(int i, int i2) {
        if (i2 != 0) {
            switch (i) {
                case 0:
                    return 7;
                case 1:
                    return 8;
                case 2:
                    return 6;
                case 3:
                    return 5;
                case 4:
                    return 4;
                case 5:
                    return 3;
                case 6:
                    return 2;
                case 7:
                    return 10;
                case 8:
                    return 9;
                case 9:
                    return 1;
                case 10:
                    return 0;
                default:
                    break;
            }
        }
        switch (i) {
            case 0:
                return 7;
            case 1:
                return 8;
            case 2:
                return 6;
            case 3:
                return 5;
            case 4:
                return 4;
            case 5:
                return 3;
            case 6:
                return 2;
            case 7:
                return 11;
            case 8:
                return 10;
            case 9:
                return 9;
            case 10:
                return 1;
            case 11:
                return 0;
        }
        return 0;
    }

    public void onItemClick(int i) {
        CmdId cmdId;
        int outputDevice = DataDm368GetGParams.getInstance().getOutputDevice();
        int b = b(i, outputDevice);
        DataDm368SetGParams dataDm368SetGParams = new DataDm368SetGParams();
        if (outputDevice == 0) {
            cmdId = CmdId.j;
        } else {
            cmdId = CmdId.k;
        }
        dataDm368SetGParams.a(cmdId, b).start(new d(this) {
            final /* synthetic */ LB2OutputFormatView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD("", "successs", false, true);
                a.f();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.a();
                    }
                });
                DJILogHelper.getInstance().LOGD("", "fail" + aVar.name() + aVar.b(), false, true);
            }
        });
    }
}
