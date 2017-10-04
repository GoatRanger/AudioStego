package dji.setting.ui.wifi;

import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.model.P3.DataWifiRestart;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.a.a;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;

public class WifiResetView extends DividerLinearLayout {
    public WifiResetView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        a.a((View) this, R.layout.setting_ui_wifi_reset_view);
        if (!isInEditMode()) {
            findViewById(R.id.setting_ui_wifi_rest_btn).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ WifiResetView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    dji.setting.ui.widget.a.a(this.a.getContext(), R.string.setting_ui_reset_wifi_confirm_txt, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            this.a.a.b();
                        }
                    }, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                }
            });
        }
    }

    private void b() {
        DataWifiRestart.getInstance().start(new d(this) {
            final /* synthetic */ WifiResetView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c();
            c.a().a(this);
        }
    }

    private void c() {
        if (dji.pilot.publics.e.a.c(null)) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        c();
    }
}
