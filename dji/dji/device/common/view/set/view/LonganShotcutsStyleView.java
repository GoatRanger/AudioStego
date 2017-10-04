package dji.device.common.view.set.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;
import dji.device.camera.datamanager.DJICameraDataManagerCompat;
import dji.device.common.view.DJIStateImageViewCompat;
import dji.device.common.view.set.view.DJIStageViewCompat.a;
import dji.midware.data.config.P3.b;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.data.model.P3.DataCameraGetPushShotParams;
import dji.midware.data.model.P3.DataCommonSetMultiParam;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganShotcutsStyleView extends ScrollView implements OnClickListener, OnCheckedChangeListener, a {
    private static final String a = "Sharpe";
    private static final String b = "Contrast";
    private static final String c = "Saturation";
    private static final String[] o = new String[]{a, "Contrast", "Saturation"};
    private RadioGroup d;
    private DJIStateImageViewCompat e;
    private DJIStateImageViewCompat f;
    private DJIStateImageViewCompat g;
    private DJIStateImageViewCompat h;
    private DJIStateImageViewCompat i;
    private DJIStateImageViewCompat j;
    private TextView k;
    private TextView l;
    private TextView m;
    private byte[] n = null;
    private int p = 0;
    private int q = 0;
    private int r = 0;
    private boolean s = false;

    public LonganShotcutsStyleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.d = (RadioGroup) findViewById(R.id.shotcuts_style_rg);
        this.d.setOnCheckedChangeListener(this);
        this.k = (TextView) findViewById(R.id.style_sharpness_tv);
        this.l = (TextView) findViewById(R.id.style_saturation_tv);
        this.m = (TextView) findViewById(R.id.style_contrast_tv);
        this.e = (DJIStateImageViewCompat) findViewById(R.id.style_sharpness_minus);
        this.f = (DJIStateImageViewCompat) findViewById(R.id.style_sharpness_plus);
        this.g = (DJIStateImageViewCompat) findViewById(R.id.style_saturation_minus);
        this.h = (DJIStateImageViewCompat) findViewById(R.id.style_saturation_plus);
        this.i = (DJIStateImageViewCompat) findViewById(R.id.style_contrast_minus);
        this.j = (DJIStateImageViewCompat) findViewById(R.id.style_contrast_plus);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.j.setOnClickListener(this);
        a(DataCameraGetPushShotParams.getInstance());
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.style_sharpness_minus) {
            a(a, false);
        } else if (id == R.id.style_sharpness_plus) {
            a(a, true);
        } else if (id == R.id.style_saturation_minus) {
            a("Saturation", false);
        } else if (id == R.id.style_saturation_plus) {
            a("Saturation", true);
        } else if (id == R.id.style_contrast_minus) {
            a("Contrast", false);
        } else if (id == R.id.style_contrast_plus) {
            a("Contrast", true);
        }
    }

    private void a(String str, boolean z) {
        DJICameraDataManagerCompat.getInstance().playSimpleSound(getContext());
        int i = z ? 1 : -1;
        int i2 = str == "Contrast" ? this.q : str == "Saturation" ? this.r : str == a ? this.p : Integer.MAX_VALUE;
        i += i2;
        if (i != Integer.MAX_VALUE) {
            new DataBaseCameraSetting().a(str).a(i).start(new d(this) {
                final /* synthetic */ LonganShotcutsStyleView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if (i == R.id.shotcuts_style_standard) {
            a(0);
            setMinPlusBtnVisibility(4);
        } else if (i == R.id.shotcuts_style_scenery) {
            a(1);
            setMinPlusBtnVisibility(4);
        } else if (i == R.id.shotcuts_style_soft) {
            a(2);
            setMinPlusBtnVisibility(4);
        } else if (i == R.id.shotcuts_style_custom) {
            a(3);
            setMinPlusBtnVisibility(0);
        }
    }

    private void setMinPlusBtnVisibility(int i) {
        this.e.setVisibility(i);
        this.f.setVisibility(i);
        this.g.setVisibility(i);
        this.h.setVisibility(i);
        this.i.setVisibility(i);
        this.j.setVisibility(i);
    }

    private void a(int i) {
        if (!this.s) {
            byte[] bArr = DJICameraDataManagerCompat.mPictureStyleValue[i];
            if (this.n == null) {
                this.n = new byte[10];
                this.n[0] = (byte) 3;
                this.n[1] = (byte) b.a.T.a();
                this.n[2] = (byte) 1;
                this.n[4] = (byte) b.a.V.a();
                this.n[5] = (byte) 1;
                this.n[7] = (byte) b.a.R.a();
                this.n[8] = (byte) 1;
            }
            DataCommonSetMultiParam dataCommonSetMultiParam = new DataCommonSetMultiParam();
            this.n[3] = bArr[1];
            this.n[6] = bArr[2];
            this.n[9] = bArr[0];
            dataCommonSetMultiParam.a(this.n);
            dataCommonSetMultiParam.start(new d(this) {
                final /* synthetic */ LonganShotcutsStyleView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
    }

    public void onEventMainThread(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        byte sharpe = (byte) dataCameraGetPushShotParams.getSharpe();
        byte contrast = (byte) dataCameraGetPushShotParams.getContrast();
        byte saturation = (byte) dataCameraGetPushShotParams.getSaturation();
        if (sharpe != this.p) {
            this.p = sharpe;
            this.k.setText("" + sharpe);
        }
        if (contrast != this.q) {
            this.q = contrast;
            this.m.setText("" + contrast);
        }
        if (saturation != this.r) {
            this.r = saturation;
            this.l.setText("" + saturation);
        }
    }

    private void a(DataCameraGetPushShotParams dataCameraGetPushShotParams) {
        byte sharpe = (byte) dataCameraGetPushShotParams.getSharpe();
        byte contrast = (byte) dataCameraGetPushShotParams.getContrast();
        byte saturation = (byte) dataCameraGetPushShotParams.getSaturation();
        byte[][] bArr = DJICameraDataManagerCompat.mPictureStyleValue;
        this.s = true;
        if (sharpe == bArr[0][0] && contrast == bArr[0][1] && saturation == bArr[0][2]) {
            this.d.check(R.id.shotcuts_style_standard);
        } else if (sharpe == bArr[1][0] && contrast == bArr[1][1] && saturation == bArr[1][2]) {
            this.d.check(R.id.shotcuts_style_scenery);
        } else if (sharpe == bArr[2][0] && contrast == bArr[2][1] && saturation == bArr[2][2]) {
            this.d.check(R.id.shotcuts_style_soft);
        } else {
            this.d.check(R.id.shotcuts_style_custom);
        }
        this.s = false;
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
    }

    public void dispatchOnResume() {
        onEventMainThread(DataCameraGetPushShotParams.getInstance());
        c.a().a(this);
    }

    public void dispatchOnPause() {
        c.a().d(this);
    }

    public View getView() {
        return this;
    }
}
