package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataFlycGetFsAction.FS_ACTION;
import dji.midware.data.model.P3.DataFlycSetParams;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.phonecamera.h;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DJISpinnerButton.b;
import dji.setting.ui.widget.ItemViewSpinner;
import dji.thirdparty.a.c;

public class LoseControlActionView extends ItemViewSpinner {
    private static final String a = "g_config.fail_safe.protect_action_0";
    private static final int[] b = new int[]{R.string.setting_ui_flyc_fs_gohome, R.string.setting_ui_flyc_fs_landing, R.string.setting_ui_flyc_fs_hover};

    public LoseControlActionView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        if (!isInEditMode()) {
            this.f.setData(b, 0, (b) this);
        }
    }

    private void a(FS_ACTION fs_action) {
        new DataFlycSetParams().a(a, Integer.valueOf(fs_action.value())).start(new d(this) {
            final /* synthetic */ LoseControlActionView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                a.b().a(LoseControlActionView.a);
            }

            public void onFailure(a aVar) {
                this.a.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        DJILogHelper.getInstance().LOGD("lose", h.e, false, true);
                        this.a.a.b();
                    }
                });
            }
        });
    }

    private void b() {
        int i = 0;
        DJILogHelper.getInstance().LOGD("lose", "updateValue : " + FS_ACTION.find(dji.midware.data.manager.P3.d.read(a).value.intValue()), false, true);
        if (DataOsdGetPushCommon.getInstance().getFlycVersion() < 6) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        FS_ACTION find = FS_ACTION.find(dji.midware.data.manager.P3.d.read(a).value.intValue());
        if (find != FS_ACTION.GoHome) {
            if (find == FS_ACTION.Landing) {
                i = 1;
            } else {
                i = 2;
            }
        }
        this.f.setIndex(i);
    }

    private void setRgFsEnable(boolean z) {
        this.f.setEnabled(z);
    }

    public void onEventMainThread(p pVar) {
        dji.publics.a.a().postDelayed(new Runnable(this) {
            final /* synthetic */ LoseControlActionView a;

            {
                this.a = r1;
            }

            public void run() {
                DJILogHelper.getInstance().LOGD("lose", "DataEvent", false, true);
                this.a.b();
            }
        }, 1000);
    }

    public void onEventMainThread(a.a aVar) {
        if (aVar.a.equals(a)) {
            DJILogHelper.getInstance().LOGD("lose", "FlycEvent", false, true);
            b();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            b();
            a.b().a(a);
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onItemClick(int i) {
        FS_ACTION fs_action = null;
        if (i == 0) {
            fs_action = FS_ACTION.GoHome;
        } else if (1 == i) {
            fs_action = FS_ACTION.Landing;
        } else if (2 == i) {
            fs_action = FS_ACTION.Hover;
        }
        FS_ACTION find = FS_ACTION.find(dji.midware.data.manager.P3.d.read(a).value.intValue());
        DJILogHelper.getInstance().LOGD("lose", "curAction : " + find + ", action : " + fs_action, false, true);
        if (fs_action != find) {
            a(fs_action);
        }
    }
}
