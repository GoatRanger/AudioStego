package dji.common.ui.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.pilot.fpv.R;

public class DJIAlertDialogUtil implements OnClickListener {
    private static final String TAG = "DJIAlertDialogUtil";
    private FrameLayout mContentLayout;
    private Context mCtx;
    private RelativeLayout mCustomLayout;
    private AlertDialog mDialog;
    private FrameLayout mFrameLayout;
    private Button mLeftBtn;
    private DialogInterface.OnClickListener mLeftBtnListener;
    private TextView mMessageTv;
    private Button mRightBtn;
    private DialogInterface.OnClickListener mRightBtnListener;
    private TextView mTitleTv;

    public DJIAlertDialogUtil(Context context, int i, int i2) {
        this.mCtx = context;
        this.mDialog = new DJIFullscreenDialog(context, i2);
        this.mFrameLayout = (FrameLayout) LayoutInflater.from(context).inflate(i, null);
        this.mCustomLayout = (RelativeLayout) this.mFrameLayout.findViewById(R.id.lp_custom_dlg_ly);
        this.mContentLayout = (FrameLayout) this.mFrameLayout.findViewById(R.id.l_dialog_content_frame);
        this.mTitleTv = (TextView) this.mFrameLayout.findViewById(R.id.lp_dialog_title_content_tv);
        this.mMessageTv = (TextView) this.mFrameLayout.findViewById(R.id.lp_dialog_message_tv);
        this.mLeftBtn = (Button) this.mFrameLayout.findViewById(R.id.lp_dialog_left_btn);
        this.mLeftBtn.setOnClickListener(this);
        this.mRightBtn = (Button) this.mFrameLayout.findViewById(R.id.lp_dialog_right_btn);
        this.mRightBtn.setOnClickListener(this);
    }

    public DJIAlertDialogUtil(Context context) {
        this(context, R.layout.lp_custom_dialog, R.style.LpBaseDialog);
    }

    public AlertDialog getDialog() {
        return this.mDialog;
    }

    public DJIAlertDialogUtil setTitle(String str) {
        this.mTitleTv.setText(str);
        return this;
    }

    public DJIAlertDialogUtil setTitle(int i) {
        setTitle(this.mCtx.getString(i));
        return this;
    }

    public DJIAlertDialogUtil setMessage(String str) {
        this.mMessageTv.setText(str);
        return this;
    }

    public DJIAlertDialogUtil setMessage(int i) {
        setMessage(this.mCtx.getString(i));
        return this;
    }

    public DJIAlertDialogUtil setContentView(int i) {
        View inflate = LayoutInflater.from(this.mDialog.getContext()).inflate(i, null);
        this.mContentLayout.removeAllViews();
        this.mContentLayout.addView(inflate);
        return this;
    }

    public DJIAlertDialogUtil setView(int i) {
        View inflate = LayoutInflater.from(this.mDialog.getContext()).inflate(i, null);
        this.mCustomLayout.removeAllViews();
        this.mCustomLayout.addView(inflate);
        return this;
    }

    public DJIAlertDialogUtil setLeftButtonListener(DialogInterface.OnClickListener onClickListener) {
        this.mLeftBtnListener = onClickListener;
        return this;
    }

    public DJIAlertDialogUtil setRightButtonListener(DialogInterface.OnClickListener onClickListener) {
        this.mRightBtnListener = onClickListener;
        return this;
    }

    public void show() {
        this.mDialog.show();
        this.mDialog.setContentView(this.mFrameLayout);
    }

    public static AlertDialog show(Context context, int i) {
        DJIAlertDialogUtil dJIAlertDialogUtil = new DJIAlertDialogUtil(context);
        dJIAlertDialogUtil.setView(i).show();
        return dJIAlertDialogUtil.getDialog();
    }

    public static AlertDialog show(Context context, int i, int i2, DialogInterface.OnClickListener onClickListener) {
        DJIAlertDialogUtil dJIAlertDialogUtil = new DJIAlertDialogUtil(context);
        dJIAlertDialogUtil.setTitle(i).setMessage(i2).setRightButtonListener(onClickListener).show();
        return dJIAlertDialogUtil.getDialog();
    }

    public static AlertDialog show(Context context, String str, String str2, DialogInterface.OnClickListener onClickListener) {
        DJIAlertDialogUtil dJIAlertDialogUtil = new DJIAlertDialogUtil(context);
        dJIAlertDialogUtil.setTitle(str).setMessage(str2).setRightButtonListener(onClickListener).show();
        return dJIAlertDialogUtil.getDialog();
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.lp_dialog_left_btn) {
            if (this.mLeftBtnListener != null) {
                this.mLeftBtnListener.onClick(this.mDialog, -2);
            } else {
                this.mDialog.dismiss();
            }
        } else if (id != R.id.lp_dialog_right_btn) {
        } else {
            if (this.mRightBtnListener != null) {
                this.mRightBtnListener.onClick(this.mDialog, -1);
            } else {
                this.mDialog.dismiss();
            }
        }
    }
}
