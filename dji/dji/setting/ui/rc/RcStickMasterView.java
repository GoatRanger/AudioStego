package dji.setting.ui.rc;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcSetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class RcStickMasterView extends DividerLinearLayout implements b {
    private String a = "RcStickMasterView";
    private DJISpinnerButton b;
    private ImageView c;
    private TextView d = null;
    private ControlMode e = ControlMode.b;
    private int[] f = new int[]{R.string.setting_ui_rc_master_japan, R.string.setting_ui_rc_master_usa, R.string.setting_ui_rc_master_china, R.string.setting_ui_rc_master_custom};
    private int[] g = new int[]{R.drawable.setting_ui_rc_japan, R.drawable.setting_ui_rc_usa, R.drawable.setting_ui_rc_china, R.drawable.setting_ui_rc_custom};
    private DataCommonGetVersion h = null;

    public RcStickMasterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_rc_stick);
        if (!isInEditMode()) {
            this.b = (DJISpinnerButton) findViewById(R.id.setting_ui_item_spinner_btn);
            this.c = (ImageView) findViewById(R.id.setting_ui_item_stick_img);
            this.d = (TextView) findViewById(R.id.setting_ui_item_title);
            this.b.setData(this.f, 0, (b) this);
            this.c.setImageResource(R.drawable.setting_ui_rc_custom);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RcStickMasterView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.e == ControlMode.d) {
                        c.a().e(new dji.setting.ui.c(R.layout.setting_ui_group_rc_custom_stick_master, R.string.setting_ui_rc_master_custom, this.a));
                    }
                }
            });
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
    }

    private void a() {
        DataRcGetMaster.getInstance().start(new d(this) {
            final /* synthetic */ RcStickMasterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                MODE mode = DataRcGetMaster.getInstance().getMode();
                DJILogHelper.getInstance().LOGD(this.a.a, "DataRcGetMaster success mode=" + mode, false, true);
                if (mode == MODE.a || mode == MODE.b) {
                    this.a.getMasterMode();
                } else {
                    this.a.b();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(this.a.a, "DataRcGetMaster onFailure" + aVar.name(), false, true);
            }
        });
    }

    private void getMasterMode() {
        DataRcGetControlMode.getInstance().start(new d(this) {
            final /* synthetic */ RcStickMasterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.e = DataRcGetControlMode.getInstance().getControlType();
                DJILogHelper.getInstance().LOGD(this.a.a, "getMasterMode success mode=" + this.a.e, false, true);
                this.a.b();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(this.a.a, "getMasterMode fail ccode=" + aVar.name(), false, true);
            }
        });
    }

    private void b() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ RcStickMasterView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.d();
            }
        });
    }

    public void onEventMainThread(DataCommonGetVersion dataCommonGetVersion) {
        if (dataCommonGetVersion.getDeviceType() == DeviceType.OSD) {
            d();
        }
    }

    public void onEventMainThread(i.a aVar) {
        d();
    }

    private boolean c() {
        if (this.h == null) {
            this.h = new DataCommonGetVersion();
            this.h.setDeviceType(DeviceType.OSD);
            this.h.start(new d(this) {
                final /* synthetic */ RcStickMasterView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        } else if (!this.h.isGetted()) {
            this.h.start(new d(this) {
                final /* synthetic */ RcStickMasterView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                }
            });
        }
        return dji.pilot.publics.e.a.a(i.getInstance().a(), dji.pilot.publics.e.a.e(this.h.isGetted() ? this.h.getFirmVer(".") : ""));
    }

    private void d() {
        if (DataRcGetMaster.getInstance().getMode() == MODE.a) {
            this.d.setText(R.string.setting_ui_rc_stick);
        } else if (c() && DataRcGetMaster.getInstance().getMode() == MODE.b) {
            this.d.setText(R.string.setting_ui_rc_coach_stick);
        } else {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        this.c.setImageResource(this.g[a(this.e)]);
        this.b.setData(this.f, a(this.e), (b) this);
    }

    public void onItemClick(final int i) {
        dji.setting.ui.widget.a.a(getContext(), R.string.setting_ui_rc_stick_desc, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ RcStickMasterView b;

            public void onClick(DialogInterface dialogInterface, int i) {
                this.b.setIndex(i);
            }
        }, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ RcStickMasterView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                this.a.d();
            }
        });
    }

    private void setIndex(final int i) {
        this.c.setImageResource(this.g[i]);
        DataRcSetControlMode.getInstance().a(a(i)).start(new d(this) {
            final /* synthetic */ RcStickMasterView b;

            public void onSuccess(Object obj) {
                DJILogHelper.getInstance().LOGD(this.b.a, "set slaveMode success");
                this.b.e = this.b.a(i);
                DataRcGetControlMode.getInstance().start(null);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(this.b.a, "set slaveMode " + aVar);
            }
        });
        if (a(i) == ControlMode.d) {
            c.a().e(new dji.setting.ui.c(R.layout.setting_ui_group_rc_custom_stick_master, R.string.setting_ui_rc_master_custom, (View) this));
        }
    }

    private ControlMode a(int i) {
        return ControlMode.find(i + 1);
    }

    private int a(ControlMode controlMode) {
        if (controlMode == ControlMode.e) {
            return 0;
        }
        return controlMode.a() - 1;
    }
}
