package dji.setting.ui.general;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.View;
import dji.apppublic.reflect.AppPublicReflect;
import dji.midware.media.j.g;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import dji.setting.ui.widget.a;
import dji.thirdparty.a.c;

public class VideoCacheClearButton extends ItemViewButtonBig {
    public VideoCacheClearButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        a.b(getContext(), getResources().getString(R.string.setting_ui_general_videocache_clean_title), new OnClickListener(this) {
            final /* synthetic */ VideoCacheClearButton a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                g.e();
                c.a().e(a.CLEAR_DONE);
                AppPublicReflect.postVideoAllDeleteEvent();
                dialogInterface.dismiss();
                a.a(this.a.getContext(), R.string.setting_ui_general_videocache_cleancache_done);
            }
        });
    }
}
