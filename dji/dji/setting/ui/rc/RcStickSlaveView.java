package dji.setting.ui.rc;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataRcGetMaster;
import dji.midware.data.model.P3.DataRcGetSlaveMode;
import dji.midware.data.model.P3.DataRcSetMaster.MODE;
import dji.midware.data.model.P3.DataRcSetSlaveMode;
import dji.midware.data.model.P3.DataRcSetSlaveMode.ControlMode;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DJISpinnerButton;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class RcStickSlaveView extends DividerLinearLayout implements b {
    private String a = "RcStickSlaveView";
    private DJISpinnerButton b;
    private ImageView c;
    private int[] d = new int[]{R.drawable.setting_ui_rc_slave, R.drawable.setting_ui_rc_custom};
    private int[] e = new int[]{R.string.setting_ui_rc_slave_1, R.string.setting_ui_rc_slave_2};
    private ControlMode f = ControlMode.a;

    public RcStickSlaveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet);
    }

    private void a(Context context, AttributeSet attributeSet) {
        a.a((View) this, R.layout.setting_ui_rc_stick);
        if (!isInEditMode()) {
            this.b = (DJISpinnerButton) findViewById(R.id.setting_ui_item_spinner_btn);
            this.c = (ImageView) findViewById(R.id.setting_ui_item_stick_img);
            this.b.setData(this.e, 0, (b) this);
            this.c.setImageResource(R.drawable.setting_ui_rc_custom);
            this.c.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RcStickSlaveView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.f == ControlMode.b) {
                        c.a().e(new dji.setting.ui.c(R.layout.setting_ui_group_rc_custom_stick_slave, R.string.setting_ui_rc_master_custom, this.a));
                    }
                }
            });
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c();
            c.a().a(this);
            a();
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        c();
    }

    private void a() {
        DataRcGetMaster.getInstance().start(new d(this) {
            final /* synthetic */ RcStickSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                MODE mode = DataRcGetMaster.getInstance().getMode();
                DJILogHelper.getInstance().LOGD(this.a.a, "get DataRcSetMaster success mode=" + mode, false, false);
                if (mode == MODE.a) {
                    this.a.b();
                } else {
                    this.a.getSlaveMode();
                }
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(this.a.a, "get is master=onFailure" + aVar, false, true);
            }
        });
    }

    private void getSlaveMode() {
        DataRcGetSlaveMode.getInstance().start(new d(this) {
            final /* synthetic */ RcStickSlaveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.f = DataRcGetSlaveMode.getInstance().getControlType();
                DJILogHelper.getInstance().LOGD(this.a.a, "getSlaveMode success mode=" + this.a.f, false, false);
                this.a.b();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(this.a.a, "getSlaveMode fail ccode=" + aVar.name(), false, false);
            }
        });
    }

    private void b() {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ RcStickSlaveView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c();
            }
        });
    }

    private void c() {
        boolean j = dji.pilot.publics.e.a.j(i.getInstance().a());
        DJILogHelper.getInstance().LOGD(this.a, "hasSlaveFunc:" + j, false, true);
        if (j) {
            DJILogHelper.getInstance().LOGD(this.a, "DataRcGetMaster.getInstance().getMode():" + DataRcGetMaster.getInstance().getMode(), false, true);
            if (DataRcGetMaster.getInstance().getMode() != MODE.b) {
                setVisibility(8);
                return;
            }
            setVisibility(0);
            if (this.f == ControlMode.a) {
                this.b.setData(this.e, 0, (b) this);
                this.c.setImageResource(this.d[0]);
                return;
            }
            this.b.setData(this.e, 1, (b) this);
            this.c.setImageResource(this.d[1]);
            return;
        }
        setVisibility(8);
    }

    public void onItemClick(final int i) {
        ControlMode controlMode;
        if (i == 0) {
            controlMode = ControlMode.a;
        } else {
            controlMode = ControlMode.b;
        }
        DataRcSetSlaveMode.getInstance().a(controlMode).a(DataRcGetSlaveMode.getInstance().getChannels()).start(new d(this) {
            final /* synthetic */ RcStickSlaveView b;

            public void onSuccess(Object obj) {
                if (i == 0) {
                    this.b.f = ControlMode.a;
                } else {
                    this.b.f = ControlMode.b;
                }
                DataRcSetSlaveMode.getInstance().start(null);
                DJILogHelper.getInstance().LOGD(this.b.a, "set slaveMode success");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                DJILogHelper.getInstance().LOGD(this.b.a, "set slaveMode " + aVar);
            }
        });
        this.c.setImageResource(this.d[i]);
        if (i == 1) {
            c.a().e(new dji.setting.ui.c(R.layout.setting_ui_group_rc_custom_stick_slave, R.string.setting_ui_rc_master_custom, (View) this));
        }
    }
}
