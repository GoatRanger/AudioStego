package dji.log;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.media.TransportMediator;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ScrollView;
import android.widget.TextView;
import com.dji.frame.c.e;
import dji.midware.R;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCameraGetPushLog;
import dji.midware.data.model.P3.DataCenterGetPushLog;
import dji.midware.data.model.P3.DataFlycGetPushLog;
import dji.midware.data.model.P3.DataGimbalGetPushLog;
import dji.midware.data.model.P3.DataOsdGetPushLog;
import dji.midware.data.model.P3.DataRcGetPushLog;
import dji.midware.usbhost.P3.NativeRcController;

class LogDialog extends Dialog {
    private RadioButton appButton;
    private RadioButton cameraButton;
    private RadioButton centerButton;
    private DeviceType deviceType = DeviceType.APP;
    private RadioButton flycButton;
    private RadioButton gimbalButton;
    private Handler handler = new Handler(new Callback() {
        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    LogDialog.this.mRadioGroup.addView((RadioButton) message.obj, message.arg1);
                    break;
            }
            return false;
        }
    });
    private int mCheckedId = DeviceType.APP.value();
    private boolean mClosed = true;
    private int mDisplayHeight = 0;
    private ImageView mImgControl = null;
    private ImageView mImgLock = null;
    private boolean mLock = false;
    private TextView mLogView = null;
    private RadioGroup mRadioGroup = null;
    private int mScreenHeight = 0;
    private int mScreenWidth = 0;
    private ScrollView mScrollView = null;
    private Button mSetSreBtn;
    private NumberPicker mSreValue;
    private OnClickListener mWidgetClickListener = null;
    private RadioButton osdButton;
    private RadioButton rcButton;

    public LogDialog(Context context) {
        super(context, R.style.LogDialog);
        init();
    }

    private void init() {
        setContentView(R.layout.log_dialog_view);
        this.mScrollView = (ScrollView) findViewById(R.id.log_dlg_scroll);
        this.mLogView = (TextView) findViewById(R.id.log_dlg_content);
        this.mImgControl = (ImageView) findViewById(R.id.log_dlg_control);
        this.mImgLock = (ImageView) findViewById(R.id.log_dlg_lock);
        this.mRadioGroup = (RadioGroup) findViewById(R.id.log_dlg_rg);
        this.mSetSreBtn = (Button) findViewById(R.id.set_sre_button);
        this.mSreValue = (NumberPicker) findViewById(R.id.numberPicker1);
        this.mSreValue.setMinValue(0);
        this.mSreValue.setMaxValue(10);
        this.mSreValue.setValue(4);
        this.mSetSreBtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                NativeRcController.b(LogDialog.this.mSreValue.getValue());
            }
        });
        addButton(this.appButton, DeviceType.APP, 0);
        this.mRadioGroup.check(DeviceType.APP.value());
        this.mRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (LogDialog.this.mCheckedId != i) {
                    LogDialog.this.mCheckedId = i;
                    LogDialog.this.deviceType = DeviceType.find(i);
                    LogHelper.getIntance().updateLog();
                }
            }
        });
        this.mWidgetClickListener = new OnClickListener() {
            public void onClick(View view) {
                boolean z = true;
                LogDialog logDialog;
                if (view.getId() == R.id.log_dlg_control) {
                    LayoutParams attributes;
                    if (LogDialog.this.mClosed) {
                        attributes = LogDialog.this.getWindow().getAttributes();
                        attributes.height = LogDialog.this.mDisplayHeight;
                        LogDialog.this.getWindow().setAttributes(attributes);
                    } else {
                        attributes = LogDialog.this.getWindow().getAttributes();
                        attributes.height = e.b(LogDialog.this.getContext(), 30.0f);
                        LogDialog.this.getWindow().setAttributes(attributes);
                    }
                    if (!LogDialog.this.mLock) {
                        LogDialog.this.mScrollView.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
                    }
                    logDialog = LogDialog.this;
                    if (LogDialog.this.mClosed) {
                        z = false;
                    }
                    logDialog.mClosed = z;
                    view.setSelected(LogDialog.this.mClosed);
                } else if (view.getId() == R.id.log_dlg_lock) {
                    logDialog = LogDialog.this;
                    if (LogDialog.this.mLock) {
                        z = false;
                    }
                    logDialog.mLock = z;
                    view.setSelected(LogDialog.this.mLock);
                }
            }
        };
        this.mImgControl.setOnClickListener(this.mWidgetClickListener);
        this.mImgLock.setOnClickListener(this.mWidgetClickListener);
    }

    protected void onCreate(Bundle bundle) {
        Context context = getContext();
        LayoutParams attributes = getWindow().getAttributes();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mScreenHeight = displayMetrics.heightPixels;
        attributes.y = e.b(context, 40.0f);
        attributes.dimAmount = 0.0f;
        attributes.flags &= -3;
        attributes.flags &= -262145;
        attributes.flags |= 32;
        attributes.flags |= 8;
        attributes.type |= 2003;
        attributes.gravity = 49;
        getWindow().setAttributes(attributes);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.d("", "click onAttachedToWindow");
        int i = getContext().getResources().getConfiguration().orientation;
        LayoutParams attributes = getWindow().getAttributes();
        int i2 = this.mScreenWidth;
        i2 = this.mScreenHeight;
        if (i == 1) {
            i = this.mScreenHeight > this.mScreenWidth ? this.mScreenWidth : this.mScreenHeight;
            i2 = this.mScreenHeight > this.mScreenWidth ? this.mScreenHeight : this.mScreenWidth;
        } else {
            i = this.mScreenHeight > this.mScreenWidth ? this.mScreenHeight : this.mScreenWidth;
            i2 = this.mScreenHeight > this.mScreenWidth ? this.mScreenWidth : this.mScreenHeight;
        }
        this.mDisplayHeight = (int) (((float) i2) * 0.7f);
        attributes.width = (int) (((float) i) * 0.8f);
        this.mClosed = true;
        attributes.height = e.b(getContext(), 30.0f);
        getWindow().setAttributes(attributes);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.d("", "click onDetachedFromWindow");
    }

    public void updateLog(String str) {
        this.mLogView.setText(str);
        if (!this.mLock) {
            this.mScrollView.fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
        }
    }

    public DeviceType getDeviceType() {
        return this.deviceType;
    }

    private synchronized RadioButton addButton(RadioButton radioButton, DeviceType deviceType, int i) {
        if (radioButton == null) {
            CharSequence substring;
            radioButton = new RadioButton(getContext());
            ViewGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(-1, -2);
            layoutParams.setMargins(0, 10, 0, 10);
            radioButton.setLayoutParams(layoutParams);
            radioButton.setId(deviceType.value());
            radioButton.setTextSize(6.0f);
            String deviceType2 = deviceType.toString();
            if (deviceType2.length() > 2) {
                substring = deviceType2.substring(0, 3);
            } else {
                Object obj = deviceType2;
            }
            radioButton.setText(substring);
            radioButton.setButtonDrawable(R.drawable.btn_radio_selector);
            this.handler.sendMessage(this.handler.obtainMessage(0, i, 0, radioButton));
            Log.d("", "devieceName=" + deviceType2);
        }
        return radioButton;
    }

    private void addLog(DeviceType deviceType, int i, String str) {
    }

    public void onEventBackgroundThread(DataCameraGetPushLog dataCameraGetPushLog) {
        this.cameraButton = addButton(this.cameraButton, DeviceType.CAMERA, 1);
        addLog(DeviceType.CAMERA, dataCameraGetPushLog.getType(), dataCameraGetPushLog.getLog());
    }

    public void onEventBackgroundThread(DataOsdGetPushLog dataOsdGetPushLog) {
        this.osdButton = addButton(this.osdButton, DeviceType.OSD, 1);
        addLog(DeviceType.OSD, dataOsdGetPushLog.getType(), dataOsdGetPushLog.getLog());
    }

    public void onEventBackgroundThread(DataFlycGetPushLog dataFlycGetPushLog) {
        this.flycButton = addButton(this.flycButton, DeviceType.FLYC, 1);
        addLog(DeviceType.FLYC, dataFlycGetPushLog.getType(), dataFlycGetPushLog.getLog());
    }

    public void onEventBackgroundThread(DataRcGetPushLog dataRcGetPushLog) {
        this.rcButton = addButton(this.rcButton, DeviceType.RC, 1);
        addLog(DeviceType.RC, dataRcGetPushLog.getType(), dataRcGetPushLog.getLog());
    }

    public void onEventBackgroundThread(DataCenterGetPushLog dataCenterGetPushLog) {
        this.centerButton = addButton(this.centerButton, DeviceType.CENTER, 1);
        addLog(DeviceType.CENTER, dataCenterGetPushLog.getType(), dataCenterGetPushLog.getLog());
    }

    public void onEventBackgroundThread(DataGimbalGetPushLog dataGimbalGetPushLog) {
        this.gimbalButton = addButton(this.gimbalButton, DeviceType.GIMBAL, 1);
        addLog(DeviceType.GIMBAL, dataGimbalGetPushLog.getType(), dataGimbalGetPushLog.getLog());
    }
}
