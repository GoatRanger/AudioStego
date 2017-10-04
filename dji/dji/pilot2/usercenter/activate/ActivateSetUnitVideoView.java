package dji.pilot2.usercenter.activate;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import dji.midware.data.model.P3.DataBaseCameraSetting;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager;

public class ActivateSetUnitVideoView extends ActivateBaseView {
    private static final int h = 1;
    private static final int i = 2;
    private final int c = 1;
    private final int d = 0;
    private int e = 0;
    private int f = 0;
    private int g;

    private class a extends Handler {
        final /* synthetic */ ActivateSetUnitVideoView a;

        private a(ActivateSetUnitVideoView activateSetUnitVideoView) {
            this.a = activateSetUnitVideoView;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.a.a != null) {
                switch (message.what) {
                    case 1:
                        this.a.a.a();
                        return;
                    case 2:
                        this.a.a.b((String) message.obj);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public ActivateSetUnitVideoView(Context context) {
        super(context);
    }

    public ActivateSetUnitVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateSetUnitVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        a();
        b();
    }

    private void a() {
        this.b = new a();
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.ei);
        ((RadioGroup) findViewById(R.id.ee)).setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ActivateSetUnitVideoView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.ef) {
                    this.a.e = 1;
                } else if (i == R.id.eh) {
                    this.a.e = 0;
                } else {
                    this.a.e = 2;
                }
            }
        });
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ ActivateSetUnitVideoView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.ej) {
                    this.a.f = 0;
                } else {
                    this.a.f = 1;
                }
            }
        });
    }

    private void b() {
        if (!isInEditMode()) {
            int v = DJIGenSettingDataManager.getInstance().v();
            if (v == 1) {
                ((RadioButton) findViewById(R.id.ef)).setChecked(true);
            } else if (v == 0) {
                ((RadioButton) findViewById(R.id.eh)).setChecked(true);
            } else {
                ((RadioButton) findViewById(R.id.eg)).setChecked(true);
            }
            DJIGenSettingDataManager.getInstance().b();
            this.g = DJIGenSettingDataManager.getInstance().n();
            if (this.g == 0) {
                ((RadioButton) findViewById(R.id.ej)).setChecked(true);
            } else {
                ((RadioButton) findViewById(R.id.ek)).setChecked(true);
            }
        }
    }

    public void setData() {
        if (this.e == 1) {
            DJIGenSettingDataManager.getInstance().b(1);
        } else if (this.e == 0) {
            DJIGenSettingDataManager.getInstance().b(0);
        } else {
            DJIGenSettingDataManager.getInstance().b(2);
        }
        DataBaseCameraSetting dataBaseCameraSetting = new DataBaseCameraSetting();
        dataBaseCameraSetting.a(dji.midware.data.config.P3.b.a.au);
        dataBaseCameraSetting.a(1000, 1);
        dataBaseCameraSetting.a(this.f).start(new d(this) {
            final /* synthetic */ ActivateSetUnitVideoView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.b.sendEmptyMessage(1);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                if (aVar == dji.midware.data.config.P3.a.a || aVar == dji.midware.data.config.P3.a.E) {
                    this.a.b.sendEmptyMessage(1);
                    return;
                }
                Message obtainMessage = this.a.b.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.obj = aVar.a() + "";
                this.a.b.sendMessage(obtainMessage);
            }
        });
    }
}
