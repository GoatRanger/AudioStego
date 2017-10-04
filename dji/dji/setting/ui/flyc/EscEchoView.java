package dji.setting.ui.flyc;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataFlycSetEscEcho;
import dji.midware.data.model.P3.DataFlycSetEscEcho.SetEchoCmd;
import dji.midware.data.model.P3.DataFlycSetEscEcho.SetResult;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewSwitch;
import dji.thirdparty.a.c;

public class EscEchoView extends ItemViewSwitch {
    private boolean a = false;

    public EscEchoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
        if (this.a != z) {
            if (z && DataOsdGetPushCommon.getInstance().isMotorUp()) {
                Toast.makeText(getContext(), R.string.setting_ui_flyc_esc_echo_tip, 1).show();
                return;
            }
            this.eS_.setEnabled(false);
            final DataFlycSetEscEcho dataFlycSetEscEcho = new DataFlycSetEscEcho();
            dataFlycSetEscEcho.a(z ? SetEchoCmd.b : SetEchoCmd.a).a(0).start(new d(this) {
                final /* synthetic */ EscEchoView c;

                public void onSuccess(Object obj) {
                    this.c.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.eS_.setEnabled(true);
                            SetResult a = dataFlycSetEscEcho.a();
                            DJILogHelper.getInstance().LOGD("EscEcho", "EscEcho-" + a + ";54", false, true);
                            if (SetResult.a == a) {
                                this.a.c.a = z;
                            } else {
                                this.a.c.eS_.setChecked(this.a.c.a);
                            }
                        }
                    });
                }

                public void onFailure(a aVar) {
                    this.c.post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.c.eS_.setEnabled(true);
                            this.a.c.eS_.setChecked(this.a.c.a);
                        }
                    });
                }
            });
        }
    }

    private void a() {
        if (i.getInstance().c() != ProductType.Pomato || DataOsdGetPushCommon.getInstance().getFlycVersion() <= 15) {
            setVisibility(8);
            return;
        }
        setVisibility(0);
        final DataFlycSetEscEcho dataFlycSetEscEcho = new DataFlycSetEscEcho();
        dataFlycSetEscEcho.a(SetEchoCmd.e).a(0).start(new d(this) {
            final /* synthetic */ EscEchoView b;

            public void onSuccess(Object obj) {
                this.b.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        SetResult a = dataFlycSetEscEcho.a();
                        DJILogHelper.getInstance().LOGD("EscEcho", "EscEcho-" + a + ";92", false, true);
                        if (SetResult.a == a) {
                            this.a.b.a = dataFlycSetEscEcho.b();
                            this.a.b.eS_.setChecked(this.a.b.a);
                        }
                    }
                });
            }

            public void onFailure(a aVar) {
            }
        });
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            a();
            c.a().a(this);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }
}
