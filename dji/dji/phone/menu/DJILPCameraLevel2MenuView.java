package dji.phone.menu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewStub;
import android.widget.RadioButton;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.common.view.DJIRadioGroup;
import dji.device.common.view.DJIRadioGroup.b;
import dji.log.DJILogHelper;
import dji.phone.controview.a;
import dji.phone.d.d;
import dji.phone.e.a.e;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJILinearLayout;
import dji.thirdparty.a.c;

public class DJILPCameraLevel2MenuView extends DJILinearLayout implements b {
    LayoutParams a;
    DJIRadioGroup b;
    DJIRadioGroup c;
    DJIRadioGroup d;
    DJIRadioGroup e = null;
    int f = R.id.longan_handle_mode_level2_single_0s;
    int g = R.id.longan_handle_mode_level2_single_0s;
    private final String h = "DJILevel2MenuViewLongan";
    private a i;

    public DJILPCameraLevel2MenuView(Context context) {
        super(context);
    }

    public DJILPCameraLevel2MenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJILPCameraLevel2MenuView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            init();
            DJILogHelper.getInstance().LOGD("init log", "L2 init");
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void init() {
        this.a = (LayoutParams) getLayoutParams();
        c.a().a(this);
        this.b = (DJIRadioGroup) findViewById(R.id.longan_radiogroup_single);
        if (!dji.phone.c.a.b()) {
            this.b.findViewById(R.id.longan_handle_mode_level2_single_hdr).setVisibility(4);
        } else if (dji.phone.c.a.c().a(dji.pilot.phonecamera.c.a.e)) {
            this.b.findViewById(R.id.longan_handle_mode_level2_single_hdr).setVisibility(0);
        }
        this.b.setOnCheckedChangeListener(this);
    }

    public void setAnimationVisibility(int i) {
        if (i == 0) {
            setVisibility(0);
        } else {
            setVisibility(4);
        }
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
    }

    public void show(boolean z) {
        if (z) {
            setAnimationVisibility(0);
        } else {
            setAnimationVisibility(4);
        }
    }

    private void a(dji.phone.d.a.b bVar, boolean z) {
        switch (bVar) {
            case SINGLE:
                this.b.setVisibility(0);
                this.e = this.b;
                switch (d.getInstance().j()) {
                    case SINGLE_0s:
                        this.g = R.id.longan_handle_mode_level2_single_0s;
                        break;
                    case SINGLE_2s:
                        this.g = R.id.longan_handle_mode_level2_single_2s;
                        break;
                    case SINGLE_5s:
                        this.g = R.id.longan_handle_mode_level2_single_5s;
                        break;
                    case SINGLE_10s:
                        this.g = R.id.longan_handle_mode_level2_single_10s;
                        break;
                    case SINGLE_HDR:
                        this.g = R.id.longan_handle_mode_level2_single_hdr;
                        break;
                    default:
                        this.g = R.id.longan_handle_mode_level2_single_0s;
                        break;
                }
                this.b.clearCheck();
                break;
            case PANO:
                this.c = a(this.c, R.id.lp_radiogroup_pano_vs);
                switch (d.getInstance().j()) {
                    case PANO_180:
                        this.g = R.id.lp_pano_180_rb;
                        break;
                    case PANO_330:
                        this.g = R.id.lp_pano_330_rb;
                        break;
                    case PANO_WIDE:
                        this.g = R.id.lp_pano_wide_rb;
                        break;
                    default:
                        this.g = R.id.lp_pano_180_rb;
                        break;
                }
                this.e = this.c;
                break;
            case NOT_PHOTOING:
                a();
                break;
            case LONGEXPOSURE:
                this.d = a(this.d, R.id.longan_radiogroup_longex_vs);
                switch (d.getInstance().j()) {
                    case LONGEXPOSURE_4s:
                        this.g = R.id.lp_handle_mode_level2_longex_4s;
                        break;
                    case LONGEXPOSURE_8s:
                        this.g = R.id.lp_handle_mode_level2_longex_8s;
                        break;
                    case LONGEXPOSURE_16s:
                        this.g = R.id.lp_handle_mode_level2_longex_16s;
                        break;
                    case LONGEXPOSURE_32s:
                        this.g = R.id.lp_handle_mode_level2_longex_32s;
                        break;
                    case LONGEXPOSURE_INFINITY:
                        this.g = R.id.lp_handle_mode_level2_longex_infinity;
                        break;
                    default:
                        this.g = R.id.lp_handle_mode_level2_longex_4s;
                        break;
                }
                this.e = this.d;
                break;
            default:
                this.b.setVisibility(0);
                this.g = R.id.longan_handle_mode_level2_single_0s;
                this.e = this.b;
                break;
        }
        if (this.e != null) {
            this.e.setOnCheckedChangeListener(this);
            if (this.g != 0 && z) {
                this.e.check(this.g);
            }
            this.e.setOrientation(getOrientation());
        }
    }

    private DJIRadioGroup a(DJIRadioGroup dJIRadioGroup, int i) {
        if (findViewById(i) != null) {
            return (DJIRadioGroup) ((ViewStub) findViewById(i)).inflate();
        }
        dJIRadioGroup.setVisibility(0);
        return dJIRadioGroup;
    }

    private void a() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(8);
        }
    }

    public void onCheckedChanged(DJIRadioGroup dJIRadioGroup, int i) {
        dJIRadioGroup.clearCheckedId();
        if (i != this.f) {
            RadioButton radioButton = (RadioButton) findViewById(this.f);
            if (radioButton != null) {
                radioButton.setChecked(false);
            }
            this.f = i;
            if (i == R.id.longan_handle_mode_level2_single_0s) {
                d.getInstance().a(dji.phone.d.a.a.SINGLE_0s, true);
            } else if (i == R.id.longan_handle_mode_level2_single_2s) {
                d.getInstance().a(dji.phone.d.a.a.SINGLE_2s, true);
            } else if (i == R.id.longan_handle_mode_level2_single_5s) {
                d.getInstance().a(dji.phone.d.a.a.SINGLE_5s, true);
            } else if (i == R.id.longan_handle_mode_level2_single_10s) {
                d.getInstance().a(dji.phone.d.a.a.SINGLE_10s, true);
            } else if (i == R.id.longan_handle_mode_level2_single_hdr) {
                dji.phone.c.a.c().p();
                d.getInstance().a(dji.phone.d.a.a.SINGLE_HDR, true);
            } else if (i == R.id.lp_pano_180_rb) {
                d.getInstance().a(dji.phone.d.a.a.PANO_180, true);
                b();
            } else if (i == R.id.lp_pano_330_rb) {
                d.getInstance().a(dji.phone.d.a.a.PANO_330, true);
                b();
            } else if (i == R.id.lp_pano_wide_rb) {
                d.getInstance().a(dji.phone.d.a.a.PANO_WIDE, true);
                b();
            } else if (i == R.id.lp_handle_mode_level2_longex_4s) {
                d.getInstance().a(dji.phone.d.a.a.LONGEXPOSURE_4s, true);
            } else if (i == R.id.lp_handle_mode_level2_longex_8s) {
                d.getInstance().a(dji.phone.d.a.a.LONGEXPOSURE_8s, true);
            } else if (i == R.id.lp_handle_mode_level2_longex_16s) {
                d.getInstance().a(dji.phone.d.a.a.LONGEXPOSURE_16s, true);
            } else if (i == R.id.lp_handle_mode_level2_longex_32s) {
                d.getInstance().a(dji.phone.d.a.a.LONGEXPOSURE_32s, true);
            } else if (i == R.id.lp_handle_mode_level2_longex_infinity) {
                d.getInstance().a(dji.phone.d.a.a.LONGEXPOSURE_INFINITY, true);
            } else {
                this.f = 0;
            }
            if (this.f != this.g && this.f != 0) {
                c.a().e(new dji.phone.e.b(e.BTN_CAMERA_MODE, dji.phone.e.a.c.e));
            }
        }
    }

    public void onEventMainThread(dji.phone.d.a.b bVar) {
        switch (bVar) {
            case SINGLE:
                a();
                a(bVar, true);
                return;
            case PANO:
                a();
                a(bVar, true);
                return;
            case NOT_PHOTOING:
                a(dji.phone.d.a.b.NOT_PHOTOING, true);
                return;
            case LONGEXPOSURE:
                a();
                a(bVar, true);
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(dji.phone.controview.b.a aVar) {
        switch (aVar) {
            case TAKEPHOTO:
                onEventMainThread(d.getInstance().h());
                return;
            case RECORD:
                a();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(dji.phone.e.b bVar) {
        if (bVar.a != e.VIEW_CAMERA_MODE) {
            return;
        }
        if (bVar.c == dji.phone.e.a.c.f) {
            onEventMainThread(dji.phone.controview.b.getInstance().b());
            show();
        } else if (bVar.c == dji.phone.e.a.c.g) {
            hide();
        }
    }

    public void setCameraPresenter(a aVar) {
        this.i = aVar;
    }

    private void b() {
        dji.phone.c.a.c().c(0);
    }
}
