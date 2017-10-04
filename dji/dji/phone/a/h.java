package dji.phone.a;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import dji.common.ui.dialog.DJIFullscreenDialog;
import dji.pilot.fpv.R;

public class h extends DialogFragment implements OnClickListener {
    private static final String a = "LPBaseDialogFragment";
    private static final h b = new h();

    public static h getInstance() {
        return b;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(2, R.style.LpBaseDialog);
    }

    public Dialog onCreateDialog(Bundle bundle) {
        Dialog dJIFullscreenDialog = new DJIFullscreenDialog(getActivity(), R.style.LpBaseDialog);
        dJIFullscreenDialog.setAutoHandleRotationEnable(false);
        dJIFullscreenDialog.setView(LayoutInflater.from(getActivity()).inflate(R.layout.lp_ble_dialog, null));
        return dJIFullscreenDialog;
    }

    public void onClick(View view) {
    }
}
