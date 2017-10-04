package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Switch;
import android.widget.TextView;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.midware.data.forbid.DJIFlyForbidController.CheckingDataState;
import dji.midware.data.forbid.DJIFlyForbidController.DataSwitchEvent;
import dji.midware.data.forbid.DJIFlyForbidController.GeoStatusEvent;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.util.i;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.setting.ui.widget.c;

public class GeoOpenView extends DividerLinearLayout implements OnClickListener, OnCheckedChangeListener {
    private Switch a;
    private TextView b;
    private c c;
    private c d;
    private int e = -1;

    public GeoOpenView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a.a((View) this, R.layout.setting_ui_general_open_geo);
        this.a = (Switch) findViewById(R.id.setting_ui_item_switch);
        this.b = (TextView) findViewById(R.id.setting_ui_geo_title);
        this.a.setOnCheckedChangeListener(this);
        this.a.setOnClickListener(this);
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
    }

    public void onClick(View view) {
        final Switch switchR = (Switch) view;
        if (switchR.isChecked()) {
            if (this.c == null) {
                this.c = new c(getContext(), R.string.setting_ui_dialog_warning, R.string.nfz_data_nfz_to_geo, R.string.nfz_data_switch_link_txt, "http://flysafe.dji.com", new OnClickListener(this) {
                    final /* synthetic */ GeoOpenView b;

                    public void onClick(View view) {
                        i.a(this.b.getContext(), DJIFlyForbidController.KEY_OPEN_GEO, switchR.isChecked());
                        i.a(this.b.getContext(), DJIFlyForbidController.KEY_FLY_FORBID_DATA_SOURCE, DJIFlyForbidController.AIRMAP_DATA_SOURCE);
                        dji.thirdparty.a.c.a().e(DataSwitchEvent.AIRMAP);
                        DJIFlyForbidController.getInstance().refreshDatabase();
                        this.b.c.dismiss();
                    }
                }, new OnClickListener(this) {
                    final /* synthetic */ GeoOpenView b;

                    public void onClick(View view) {
                        switchR.setChecked(false);
                        this.b.c.dismiss();
                    }
                });
            }
            this.c.show();
            return;
        }
        if (this.d == null) {
            this.d = new c(getContext(), R.string.setting_ui_dialog_warning, R.string.nfz_data_geo_to_nfz, R.string.nfz_data_switch_link_txt, "http://flysafe.dji.com", new OnClickListener(this) {
                final /* synthetic */ GeoOpenView b;

                public void onClick(View view) {
                    i.a(this.b.getContext(), DJIFlyForbidController.KEY_OPEN_GEO, switchR.isChecked());
                    i.a(this.b.getContext(), DJIFlyForbidController.KEY_FLY_FORBID_DATA_SOURCE, DJIFlyForbidController.DJI_DATA_SOURCE);
                    dji.thirdparty.a.c.a().e(DataSwitchEvent.DJI);
                    DJIFlyForbidController.getInstance().refreshDatabase();
                    this.b.d.dismiss();
                }
            }, new OnClickListener(this) {
                final /* synthetic */ GeoOpenView b;

                public void onClick(View view) {
                    switchR.setChecked(true);
                    this.b.d.dismiss();
                }
            });
        }
        this.d.show();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            dji.thirdparty.a.c.a().a(this);
            a();
        }
    }

    private void a() {
        if (i.b(getContext(), DJIFlyForbidController.KEY_CUR_USE_GEO_SYSTEM, false)) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
        if (i.b(getContext(), DJIFlyForbidController.KEY_OPEN_GEO, true)) {
            this.a.setChecked(true);
        } else {
            this.a.setChecked(false);
        }
        try {
            onEventMainThread(DJIFlyForbidController.getInstance().getIsCheckingData() ? CheckingDataState.CHECKING : CheckingDataState.FINISHED);
            onEventMainThread(DataOsdGetPushCommon.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDetachedFromWindow() {
        dji.thirdparty.a.c.a().d(this);
        this.c = null;
        this.d = null;
        this.e = -1;
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(GeoStatusEvent geoStatusEvent) {
        a();
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        int groundOrSky = dataOsdGetPushCommon.groundOrSky();
        if (groundOrSky != this.e) {
            this.e = groundOrSky;
            if (this.e == 2) {
                this.b.setText(R.string.general_open_geo_flying_tip);
                this.a.setEnabled(false);
                return;
            }
            this.b.setText(R.string.general_open_geo);
            this.a.setEnabled(true);
        }
    }

    public void onEventMainThread(CheckingDataState checkingDataState) {
        if (checkingDataState == CheckingDataState.CHECKING) {
            this.b.setText(R.string.general_open_geo_loading_tip);
            this.a.setEnabled(false);
            return;
        }
        this.b.setText(R.string.general_open_geo);
        this.a.setEnabled(true);
    }
}
