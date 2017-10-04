package dji.setting.ui.general;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Toast;
import dji.midware.media.j.g.d;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinnerWithDesc;
import dji.thirdparty.a.c;

public class VideoCacheSizeSpinner extends ItemViewSpinnerWithDesc {
    DJIGenSettingDataManager a = DJIGenSettingDataManager.getInstance();
    private final String[] f = new String[]{"1G", "2G", "3G", "4G", "5G", "6G", "7G", "8G", "9G", "10G", "11G", "12G", "13G", "14G", "15G", "16G"};

    public VideoCacheSizeSpinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.e.setData(this.f, this.a.D(), (b) this);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a(this.a.D());
            c.a().a(this);
            if (this.a.C()) {
                setVisibility(0);
            } else {
                setVisibility(8);
            }
        }
    }

    private void a(int i) {
        if (((long) this.a.d(i)) > 0) {
            this.d.setText(getContext().getResources().getString(R.string.setting_ui_general_videocache_size_desc_available, new Object[]{Long.valueOf(r0)}));
        } else if (this.a.E()) {
            this.d.setText(R.string.setting_ui_general_videocache_size_desc_autoclean);
        } else {
            this.d.setText(R.string.setting_ui_general_videocache_size_desc_full);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void setEnabled(boolean z) {
        super.setEnabled(z);
        this.e.setEnabled(z);
    }

    public void onItemClick(int i) {
        if (i != this.a.D()) {
            a(i);
            c.a().e(a.SIZE_CHANGED);
        }
    }

    public void onEventMainThread(a aVar) {
        if (aVar.equals(a.OPEN_SWITCH_OFF)) {
            setVisibility(8);
        } else if (aVar.equals(a.OPEN_SWITCH_ON)) {
            setVisibility(0);
        } else if (aVar.equals(a.CLEAR_DONE)) {
            a(this.e.getCurIndex());
        } else if (aVar.equals(a.AUTO_CLEAR_SWITCH_CHANGED)) {
            a(this.e.getCurIndex());
        }
    }

    public void onEventMainThread(d dVar) {
        if (dVar.equals(d.a)) {
            a(this.e.getCurIndex());
            runOnUiThread(new Runnable(this) {
                final /* synthetic */ VideoCacheSizeSpinner a;

                {
                    this.a = r1;
                }

                public void run() {
                    Toast.makeText(this.a.getContext(), "RELEASE_CACHE_DONE", 1).show();
                }
            });
        }
    }
}
