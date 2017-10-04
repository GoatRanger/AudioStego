package dji.pilot.dji_groundstation.ui.a;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import dji.midware.data.model.P3.DataRcSetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.util.i;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.controller.e;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class b extends e {
    public static final String a = "GSPOI_CONTROL_HELP_NEVER_NOTICEE";
    private static final String g = "DJIGSPOIControlHelpDialog";
    private boolean h = false;
    private boolean i = false;
    private a j = null;

    public interface a {
        void a();

        void b();
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    public b(Context context, boolean z) {
        super(context);
        this.i = z;
        b();
    }

    private void b() {
        setContentView(R.layout.layout_gs_poi_control_help_dialog_new);
        findViewById(R.id.gs_poi_control_known).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
                if (this.a.i) {
                    e.a("gs://smartmode/poi/status", this.a.getContext());
                } else {
                    e.a("gs://smartmode/poi", this.a.getContext());
                }
                if (this.a.j != null) {
                    this.a.j.a();
                }
            }
        });
        findViewById(R.id.gs_poi_control_never_notice).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.h = true;
                i.a(this.a.getContext(), "GSPOI_CONTROL_HELP_NEVER_NOTICEE", this.a.h);
                this.a.dismiss();
                if (this.a.i) {
                    e.a("gs://smartmode/poi/status", this.a.getContext());
                } else {
                    e.a("gs://smartmode/poi", this.a.getContext());
                }
                if (this.a.j != null) {
                    this.a.j.b();
                }
            }
        });
        this.h = i.b(getContext(), "GSPOI_CONTROL_HELP_NEVER_NOTICEE", false);
        a(findViewById(R.id.gs_poi_control_container));
    }

    protected void onCreate(Bundle bundle) {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) f.a(400.0d, getContext());
        attributes.height = (int) f.a(300.0d, getContext());
        attributes.y = 0;
        attributes.x = 0;
        attributes.dimAmount = 0.0f;
        attributes.flags &= -3;
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.dialogWindowAnim);
    }

    public void show() {
        int i;
        int i2 = 8;
        View findViewById = findViewById(R.id.gs_poi_control_never_notice);
        if (this.h) {
            i = 8;
        } else {
            i = 0;
        }
        findViewById.setVisibility(i);
        View findViewById2 = findViewById(R.id.gs_poi_control_split);
        if (!this.h) {
            i2 = 0;
        }
        findViewById2.setVisibility(i2);
        a(findViewById(R.id.gs_poi_control_container));
        super.show();
    }

    public void a() {
        this.h = i.b(getContext(), "GSPOI_CONTROL_HELP_NEVER_NOTICEE", false);
        if (!this.h) {
            show();
        } else if (this.i) {
            e.a("gs://smartmode/poi/status", getContext());
        }
    }

    private void a(View view) {
        ControlMode a = DataRcSetControlMode.getInstance().a();
        if (a == ControlMode.a) {
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_up_label_view)).setText(R.string.gsnew_gs_remote_control_left_up_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_down_label_view)).setText(R.string.gsnew_gs_remote_control_left_down_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_left_label_view)).setText(R.string.gsnew_gs_remote_control_right_left_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_right_label_view)).setText(R.string.gsnew_gs_remote_control_right_right_label);
            ((DJIImageView) view.findViewById(R.id.gs_remote_control_left_icon_view)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_left_icon);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_up_label_view)).setText(R.string.gsnew_gs_remote_control_right_up_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_down_label_view)).setText(R.string.gsnew_gs_remote_control_right_down_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_right_label_view)).setText(R.string.gsnew_gs_remote_control_left_right_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_left_label_view)).setText(R.string.gsnew_gs_remote_control_left_left_label);
            ((DJIImageView) view.findViewById(R.id.gs_remote_control_right_icon_view)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_right_icon);
        } else if (a == ControlMode.c) {
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_up_label_view)).setText(R.string.gsnew_gs_remote_control_left_up_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_down_label_view)).setText(R.string.gsnew_gs_remote_control_left_down_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_left_label_view)).setText(R.string.gsnew_gs_remote_control_left_left_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_right_label_view)).setText(R.string.gsnew_gs_remote_control_left_right_label);
            ((DJIImageView) view.findViewById(R.id.gs_remote_control_left_icon_view)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_left_icon);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_up_label_view)).setText(R.string.gsnew_gs_remote_control_right_up_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_down_label_view)).setText(R.string.gsnew_gs_remote_control_right_down_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_left_label_view)).setText(R.string.gsnew_gs_remote_control_right_left_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_right_label_view)).setText(R.string.gsnew_gs_remote_control_right_right_label);
            ((DJIImageView) view.findViewById(R.id.gs_remote_control_right_icon_view)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_right_icon);
        } else {
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_up_label_view)).setText(R.string.gsnew_gs_remote_control_right_up_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_down_label_view)).setText(R.string.gsnew_gs_remote_control_right_down_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_left_label_view)).setText(R.string.gsnew_gs_remote_control_right_left_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_left_right_label_view)).setText(R.string.gsnew_gs_remote_control_right_right_label);
            ((DJIImageView) view.findViewById(R.id.gs_remote_control_left_icon_view)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_right_icon);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_up_label_view)).setText(R.string.gsnew_gs_remote_control_left_up_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_down_label_view)).setText(R.string.gsnew_gs_remote_control_left_down_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_left_label_view)).setText(R.string.gsnew_gs_remote_control_left_left_label);
            ((DJITextView) view.findViewById(R.id.gs_remote_control_right_right_label_view)).setText(R.string.gsnew_gs_remote_control_left_right_label);
            ((DJIImageView) view.findViewById(R.id.gs_remote_control_right_icon_view)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_left_icon);
        }
    }
}
