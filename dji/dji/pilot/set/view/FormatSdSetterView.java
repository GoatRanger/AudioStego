package dji.pilot.set.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraFormatSDCard;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.e;
import dji.pilot.set.view.base.SetButtonView;

public class FormatSdSetterView extends SetButtonView {
    private static final int a = 0;
    private static final int b = 1;
    private static final int f = 2;
    private Handler g = new Handler(new Callback(this) {
        final /* synthetic */ FormatSdSetterView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            if (this.a.isShown()) {
                switch (message.what) {
                    case 0:
                        e.c(this.a.getContext(), ((Integer) message.obj).intValue());
                        break;
                    case 1:
                        this.a.setVisibility(true);
                        break;
                    case 2:
                        this.a.setVisibility(false);
                        break;
                    default:
                        break;
                }
            }
            return false;
        }
    });

    public FormatSdSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onClick(View view) {
        e.a(getContext(), R.string.fpv_gensetting_format_sdcard_confirm, new OnClickListener(this) {
            final /* synthetic */ FormatSdSetterView a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                this.a.g.sendEmptyMessage(2);
                dialogInterface.dismiss();
                this.a.d();
            }
        });
    }

    protected int getTitleId() {
        return R.string.fpv_gensetting_format_sdcard;
    }

    protected int getButtonStringId() {
        return R.string.fpv_gensetting_format_sdcard_start;
    }

    private void d() {
        DataCameraFormatSDCard.getInstance().start(new d(this) {
            final /* synthetic */ FormatSdSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.g.sendMessage(this.a.g.obtainMessage(0, Integer.valueOf(R.string.fpv_gensetting_format_sdcard_success)));
                this.a.g.sendEmptyMessage(1);
            }

            public void onFailure(a aVar) {
                this.a.g.sendMessage(this.a.g.obtainMessage(0, Integer.valueOf(R.string.fpv_gensetting_format_sdcard_fail)));
                this.a.g.sendEmptyMessage(1);
            }
        });
    }

    private void setVisibility(boolean z) {
        if (z) {
            this.d.setEnabled(true);
            this.d.setAlpha(1.0f);
            return;
        }
        this.d.setEnabled(false);
        this.d.setAlpha(0.3f);
    }
}
