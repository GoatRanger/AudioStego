package dji.setting.ui.general;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewSwitchBottomDesc;
import dji.setting.ui.widget.a;
import dji.thirdparty.a.c;

public class VideoCacheAutoCleanSwitch extends ItemViewSwitchBottomDesc {
    private DJIGenSettingDataManager d = DJIGenSettingDataManager.getInstance();
    private boolean e = true;

    public VideoCacheAutoCleanSwitch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (this.b.isChecked() == this.d.E()) {
            this.e = false;
        } else {
            this.b.setChecked(this.d.E());
        }
        a();
    }

    private void a() {
        int D = this.d.D() + 1;
        this.c.setText(getResources().getString(R.string.setting_ui_general_videocache_autoclean_desc, new Object[]{Integer.valueOf(D)}));
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            if (this.d.C()) {
                setVisibility(0);
            } else {
                setVisibility(8);
            }
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(a aVar) {
        if (aVar.equals(a.OPEN_SWITCH_OFF)) {
            setVisibility(8);
            this.b.setChecked(false);
        } else if (aVar.equals(a.OPEN_SWITCH_ON)) {
            setVisibility(0);
        } else if (aVar.equals(a.SIZE_CHANGED)) {
            a();
        }
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        if (this.e) {
            this.e = false;
            c.a().e(a.AUTO_CLEAR_SWITCH_CHANGED);
        } else if (z) {
            int D = this.d.D() + 1;
            a.b(getContext(), getResources().getString(R.string.setting_ui_general_videocache_autoclean_title), new OnClickListener(this) {
                final /* synthetic */ VideoCacheAutoCleanSwitch b;

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.b.d.k(z);
                    c.a().e(a.AUTO_CLEAR_SWITCH_CHANGED);
                }
            }, new OnClickListener(this) {
                final /* synthetic */ VideoCacheAutoCleanSwitch a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    this.a.b.setChecked(false);
                }
            }).setCanceledOnTouchOutside(false);
        } else {
            this.d.k(z);
            c.a().e(a.AUTO_CLEAR_SWITCH_CHANGED);
        }
    }
}
