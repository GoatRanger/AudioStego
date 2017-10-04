package dji.setting.ui.gimbal.ronin;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import com.here.odnp.config.OdnpConfigStatic;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalGetUserParams;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import java.lang.ref.WeakReference;

public class CalsSystem extends ItemViewButtonBig {
    private static final String a = "system_calc";
    private static final int b = 30000;
    private AlertDialog d;
    private a e;

    private class a extends Handler {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        final /* synthetic */ CalsSystem f;
        private WeakReference<CalsSystem> g = null;
        private long h = 0;

        public a(CalsSystem calsSystem, CalsSystem calsSystem2) {
            this.f = calsSystem;
            this.g = new WeakReference(calsSystem2);
        }

        public void handleMessage(Message message) {
            final CalsSystem calsSystem = (CalsSystem) this.g.get();
            if (calsSystem != null) {
                switch (message.what) {
                    case 1:
                        if (calsSystem.d == null || !calsSystem.d.isShowing()) {
                            Builder builder = new Builder(calsSystem.getContext());
                            builder.setMessage(calsSystem.getContext().getString(R.string.setting_ui_ronin_system_calcing));
                            builder.setCancelable(false);
                            calsSystem.d = builder.create();
                            calsSystem.d.show();
                            calsSystem.e.sendEmptyMessageDelayed(2, 1000);
                            this.h = System.currentTimeMillis();
                            return;
                        }
                        return;
                    case 2:
                        if (System.currentTimeMillis() - this.h > 30000) {
                            calsSystem.d.setMessage(calsSystem.getContext().getString(R.string.setting_ui_ronin_system_calc_timeout));
                            calsSystem.e.sendEmptyMessageDelayed(5, 2000);
                            return;
                        }
                        DataGimbalGetUserParams.getInstance().setInfos(new String[]{CalsSystem.a}).start(new d(this) {
                            final /* synthetic */ a b;

                            public void onSuccess(Object obj) {
                                int intValue = e.read(CalsSystem.a).value.intValue();
                                if (intValue == 2) {
                                    calsSystem.e.sendEmptyMessage(4);
                                } else if (intValue == 3) {
                                    calsSystem.e.sendEmptyMessage(3);
                                } else {
                                    calsSystem.e.sendEmptyMessageDelayed(2, 1000);
                                }
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                calsSystem.e.sendEmptyMessageDelayed(2, 1000);
                            }
                        });
                        return;
                    case 3:
                        calsSystem.d.setMessage(calsSystem.getContext().getString(R.string.setting_ui_ronin_system_calc_success));
                        calsSystem.e.sendEmptyMessageDelayed(5, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                        return;
                    case 4:
                        calsSystem.d.setMessage(calsSystem.getContext().getString(R.string.setting_ui_ronin_system_calc_failed));
                        calsSystem.e.sendEmptyMessageDelayed(5, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                        return;
                    case 5:
                        if (calsSystem.d != null) {
                            calsSystem.d.dismiss();
                            calsSystem.d = null;
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public CalsSystem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = null;
        this.e = null;
        this.e = new a(this, this);
    }

    public void onClick(View view) {
        dji.setting.ui.widget.a.b(getContext(), R.string.setting_ui_ronin_calc_system_tip, new OnClickListener(this) {
            final /* synthetic */ CalsSystem a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                DataGimbalSetUserParams.getInstance().a(CalsSystem.a, Integer.valueOf(1)).start(new d(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a.e.sendEmptyMessage(1);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
            }
        });
    }
}
