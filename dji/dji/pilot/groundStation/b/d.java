package dji.pilot.groundStation.b;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import dji.midware.data.model.P3.DataRcGetControlMode;
import dji.midware.data.model.P3.DataRcSetControlMode.ControlMode;
import dji.midware.util.i;
import dji.pilot.R;
import dji.pilot.fpv.model.b;
import dji.pilot.publics.objects.c;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;

public class d extends c {
    public static final String a = "GSPOI_CONTROL_HELP_NEVER_NOTICEE";
    private static final String b = "DJIGSPOIControlHelpDialog";
    private boolean c = false;
    private a d = null;

    public interface a {
        void a();

        void b();
    }

    public void a(a aVar) {
        this.d = aVar;
    }

    public d(Context context) {
        super(context);
        b();
    }

    private void b() {
        setContentView(R.layout.layout_gs_poi_control_help_dialog);
        findViewById(R.id.ay_).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
                if (this.a.d != null) {
                    this.a.d.a();
                }
            }
        });
        findViewById(R.id.ay8).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c = true;
                i.a(this.a.getContext(), "GSPOI_CONTROL_HELP_NEVER_NOTICEE", this.a.c);
                this.a.dismiss();
                if (this.a.d != null) {
                    this.a.d.b();
                }
            }
        });
        this.c = i.b(getContext(), "GSPOI_CONTROL_HELP_NEVER_NOTICEE", false);
        a(findViewById(R.id.ay7));
    }

    protected void onCreate(Bundle bundle) {
        LayoutParams attributes = getWindow().getAttributes();
        attributes.width = b.a(this.N, R.dimen.gk);
        attributes.height = b.a(this.N, R.dimen.gb);
        attributes.y = 0;
        attributes.x = 0;
        attributes.dimAmount = 0.0f;
        attributes.flags &= -3;
        attributes.gravity = 17;
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.e5);
    }

    public void show() {
        int i;
        int i2 = 8;
        View findViewById = findViewById(R.id.ay8);
        if (this.c) {
            i = 8;
        } else {
            i = 0;
        }
        findViewById.setVisibility(i);
        View findViewById2 = findViewById(R.id.ay9);
        if (!this.c) {
            i2 = 0;
        }
        findViewById2.setVisibility(i2);
        super.show();
    }

    private void a(View view) {
        ControlMode controlType = DataRcGetControlMode.getInstance().getControlType();
        if (controlType == ControlMode.a) {
            ((DJITextView) view.findViewById(R.id.amz)).setText(R.string.gs_remote_control_left_up_label);
            ((DJITextView) view.findViewById(R.id.an2)).setText(R.string.gs_remote_control_left_down_label);
            ((DJITextView) view.findViewById(R.id.an0)).setText(R.string.gs_remote_control_right_left_label);
            ((DJITextView) view.findViewById(R.id.an1)).setText(R.string.gs_remote_control_right_right_label);
            ((DJIImageView) view.findViewById(R.id.amy)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_left_icon);
            ((DJITextView) view.findViewById(R.id.an4)).setText(R.string.gs_remote_control_right_up_label);
            ((DJITextView) view.findViewById(R.id.an7)).setText(R.string.gs_remote_control_right_down_label);
            ((DJITextView) view.findViewById(R.id.an6)).setText(R.string.gs_remote_control_left_right_label);
            ((DJITextView) view.findViewById(R.id.an5)).setText(R.string.gs_remote_control_left_left_label);
            ((DJIImageView) view.findViewById(R.id.an3)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_right_icon);
        } else if (controlType == ControlMode.c) {
            ((DJITextView) view.findViewById(R.id.amz)).setText(R.string.gs_remote_control_left_up_label);
            ((DJITextView) view.findViewById(R.id.an2)).setText(R.string.gs_remote_control_left_down_label);
            ((DJITextView) view.findViewById(R.id.an0)).setText(R.string.gs_remote_control_left_left_label);
            ((DJITextView) view.findViewById(R.id.an1)).setText(R.string.gs_remote_control_left_right_label);
            ((DJIImageView) view.findViewById(R.id.amy)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_left_icon);
            ((DJITextView) view.findViewById(R.id.an4)).setText(R.string.gs_remote_control_right_up_label);
            ((DJITextView) view.findViewById(R.id.an7)).setText(R.string.gs_remote_control_right_down_label);
            ((DJITextView) view.findViewById(R.id.an5)).setText(R.string.gs_remote_control_right_left_label);
            ((DJITextView) view.findViewById(R.id.an6)).setText(R.string.gs_remote_control_right_right_label);
            ((DJIImageView) view.findViewById(R.id.an3)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_right_icon);
        } else {
            ((DJITextView) view.findViewById(R.id.amz)).setText(R.string.gs_remote_control_right_up_label);
            ((DJITextView) view.findViewById(R.id.an2)).setText(R.string.gs_remote_control_right_down_label);
            ((DJITextView) view.findViewById(R.id.an0)).setText(R.string.gs_remote_control_right_left_label);
            ((DJITextView) view.findViewById(R.id.an1)).setText(R.string.gs_remote_control_right_right_label);
            ((DJIImageView) view.findViewById(R.id.amy)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_right_icon);
            ((DJITextView) view.findViewById(R.id.an4)).setText(R.string.gs_remote_control_left_up_label);
            ((DJITextView) view.findViewById(R.id.an7)).setText(R.string.gs_remote_control_left_down_label);
            ((DJITextView) view.findViewById(R.id.an5)).setText(R.string.gs_remote_control_left_left_label);
            ((DJITextView) view.findViewById(R.id.an6)).setText(R.string.gs_remote_control_left_right_label);
            ((DJIImageView) view.findViewById(R.id.an3)).setImageResource(R.drawable.gs_point_of_insterest_manual_model_left_icon);
        }
    }
}
