package dji.pilot.fpv.camera.newfn;

import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewParent;
import android.widget.Toast;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.RecordType;
import dji.midware.data.model.P3.DataCameraGetStateInfo.SDCardState;
import dji.midware.data.model.P3.DataCameraTauTriggerFFC;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.pilot.fpv.view.DJIStageView;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.b.b;

class DJICameraFnOtherView$6 implements OnClickListener {
    final /* synthetic */ DJICameraFnOtherView a;

    DJICameraFnOtherView$6(DJICameraFnOtherView dJICameraFnOtherView) {
        this.a = dJICameraFnOtherView;
    }

    public void onClick(View view) {
        int id = view.getId();
        ViewParent parent;
        if (id == R.id.my) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJICameraBaseListView) ((DJIStageView) parent).createStageView(R.layout.camera_newfn_base_listview, R.string.fpv_gensetting_showgrid, true)).updateData(5);
            }
        } else if (id == R.id.nc) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJICameraBaseListView) ((DJIStageView) parent).createStageView(R.layout.camera_newfn_base_listview, R.string.fpv_gensetting_fileindex, true)).updateData(8);
            }
        } else if (id == R.id.n7) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJICameraBaseListView) ((DJIStageView) parent).createStageView(R.layout.camera_newfn_base_listview, R.string.fpv_camera_antiblink, true)).updateData(6);
            }
        } else if (id == R.id.n_) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJICameraBaseListView) ((DJIStageView) parent).createStageView(R.layout.camera_newfn_base_listview, R.string.fpv_gensetting_quickpreview, true)).updateData(7);
            }
        } else if (id == R.id.o9) {
            e.a("FPV_GeneralSettings_Camera_Button_ResetCameraSettings");
            if (a.a(a.b(b.bY), false) || (DataCameraGetPushStateInfo.getInstance().isGetted() && DataCameraGetPushStateInfo.getInstance().getIsTimePhotoing())) {
                dji.pilot.publics.widget.b.a(this.a.getContext(), (int) R.string.reset_camera_param_busy_title, (int) R.string.reset_camera_param_busy_tip, (int) R.string.cancel, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ DJICameraFnOtherView$6 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            } else {
                DJICameraFnOtherView.a(this.a, 0, this.a.getContext().getString(R.string.fpv_gensetting_reset_camera_setting_confirm));
            }
        } else if (id == R.id.o_) {
            DataCameraGetPushStateInfo instance = DataCameraGetPushStateInfo.getInstance();
            SDCardState sDCardState = instance.getSDCardState(true);
            if (!(instance.getSDCardInsertState() || sDCardState == SDCardState.USBConnected)) {
                sDCardState = SDCardState.None;
            }
            if (sDCardState == SDCardState.None || sDCardState == SDCardState.USBConnected) {
                Toast.makeText(this.a.getContext(), this.a.getContext().getString(R.string.sdcardstatus_cant_format, new Object[]{this.a.getContext().getString(dji.pilot.fpv.d.b.a(sDCardState))}), 1).show();
                return;
            }
            e.a("FPV_GeneralSettings_Camera_Button_FormatSDCard");
            DJICameraFnOtherView.a(this.a, 1, this.a.getContext().getString(R.string.fpv_gensetting_format_sdcard_confirm));
        } else if (id == R.id.oa) {
            e.a("FPV_GeneralSettings_Camera_Button_FormatSSD");
            if (DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.START || DataCameraGetPushStateInfo.getInstance().getRecordState() == RecordType.STARTING) {
                dji.pilot.publics.widget.b.a(this.a.getContext(), (int) R.string.app_tip, (int) R.string.format_ssd_when_recording, (int) R.string.app_isee, new DialogInterface.OnClickListener(this) {
                    final /* synthetic */ DJICameraFnOtherView$6 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            } else {
                DJICameraFnOtherView.a(this.a, 3, this.a.getContext().getString(R.string.fpv_gensetting_format_ssd_confirm));
            }
        } else if (id == R.id.o5) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJIStageView) parent).createStageView(R.layout.camera_newfn_info, R.string.fpv_camera_info, true);
            }
        } else if (id == R.id.o8) {
            dji.pilot.fpv.camera.more.a.getInstance().e();
        } else if (id == R.id.nf) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJICameraBaseListView) ((DJIStageView) parent).createStageView(R.layout.camera_newfn_base_listview, R.string.tau_roi_desc, true)).updateData(101);
            }
        } else if (id == R.id.ni) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJICameraBaseListView) ((DJIStageView) parent).createStageView(R.layout.camera_newfn_base_listview, R.string.tau_palette_desc, true)).updateData(102);
            }
        } else if (id == R.id.nl) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJICameraBaseListView) ((DJIStageView) parent).createStageView(R.layout.camera_newfn_base_listview, R.string.tau_scene_desc, true)).updateData(103);
            }
        } else if (id == R.id.no) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJIStageView) parent).createStageView(R.layout.camera_newfn_isotherm, R.string.tau_camera_isotherm, true);
            }
        } else if (id == R.id.nr) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJICameraBaseListView) ((DJIStageView) parent).createStageView(R.layout.camera_newfn_base_listview, R.string.tau_gainmode_desc, true)).updateData(106);
            }
        } else if (id == R.id.o7) {
            new DataCameraTauTriggerFFC().b(false).start(null);
        } else if (id == R.id.o0) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJICameraBaseListView) ((DJIStageView) parent).createStageView(R.layout.camera_newfn_base_listview, R.string.tau_ffc_desc, true)).updateData(107);
            }
        } else if (id == R.id.nu) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJIStageView) parent).createStageView(R.layout.camera_newfn_tempalert_view, R.string.tau_temp_alert, true);
            }
        } else if (id == R.id.nx) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJIStageView) parent).createStageView(R.layout.camera_newfn_external_param, R.string.tau_exter_params, true);
            }
        } else if (id == R.id.n1) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJIStageView) parent).createStageView(R.layout.camera_newfn_center_point, R.string.camera_ref_point, true);
            }
        } else if (id == R.id.n4) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJIStageView) parent).createStageView(R.layout.camera_newfn_line_view, R.string.camera_ref_line, true);
            }
        } else if (id == R.id.o3) {
            parent = this.a.getParent();
            if (parent instanceof DJIStageView) {
                ((DJIStageView) parent).createStageView(R.layout.camera_newfn_profile_view, R.string.camera_profile_title, true);
            }
        }
    }
}
