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
import com.here.services.location.network.NetworkLocationApi.Options;
import dji.midware.data.manager.P3.e;
import dji.midware.data.model.P3.DataGimbalGetUserParams;
import dji.midware.data.model.P3.DataGimbalSetUserParams;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;
import java.lang.ref.WeakReference;

public class BalanceTest extends ItemViewButtonBig {
    private static final String a = "balance_test";
    private AlertDialog b;
    private a d;

    private class a extends Handler {
        public static final int a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        final /* synthetic */ BalanceTest e;
        private WeakReference<BalanceTest> f = null;
        private long g = 0;

        public a(BalanceTest balanceTest, BalanceTest balanceTest2) {
            this.e = balanceTest;
            this.f = new WeakReference(balanceTest2);
        }

        public void handleMessage(Message message) {
            final BalanceTest balanceTest = (BalanceTest) this.f.get();
            if (balanceTest != null) {
                switch (message.what) {
                    case 1:
                        if (balanceTest.b == null || !balanceTest.b.isShowing()) {
                            Builder builder = new Builder(balanceTest.getContext());
                            builder.setMessage(balanceTest.getContext().getString(R.string.setting_ui_ronin_balance_testing));
                            builder.setCancelable(false);
                            balanceTest.b = builder.create();
                            balanceTest.b.show();
                            balanceTest.d.sendEmptyMessageDelayed(2, 1000);
                            this.g = System.currentTimeMillis();
                            return;
                        }
                        return;
                    case 2:
                        if (System.currentTimeMillis() - this.g > Options.MIN_DESIRED_INTERVAL) {
                            balanceTest.b.setMessage(balanceTest.getContext().getString(R.string.setting_ui_ronin_balance_test_timeout));
                            balanceTest.d.sendEmptyMessageDelayed(4, 2000);
                            return;
                        }
                        DataGimbalGetUserParams.getInstance().setInfos(new String[]{BalanceTest.a}).start(new d(this) {
                            final /* synthetic */ a b;

                            public void onSuccess(Object obj) {
                                if (((e.read(BalanceTest.a).value.intValue() >> 6) & 3) != 2) {
                                    balanceTest.d.sendEmptyMessageDelayed(2, 1000);
                                } else {
                                    balanceTest.d.sendEmptyMessage(3);
                                }
                            }

                            public void onFailure(dji.midware.data.config.P3.a aVar) {
                                balanceTest.d.sendEmptyMessageDelayed(2, 1000);
                            }
                        });
                        return;
                    case 3:
                        int intValue = e.read(BalanceTest.a).value.intValue();
                        int i = (intValue >> 2) & 3;
                        intValue = (intValue >> 4) & 3;
                        String[] stringArray = balanceTest.getContext().getResources().getStringArray(R.array.setting_ui_ronin_balance_test_result_roll);
                        String[] stringArray2 = balanceTest.getContext().getResources().getStringArray(R.array.setting_ui_ronin_balance_test_result_roll);
                        balanceTest.b.setMessage(String.format("%s\n%s %s\n%s %s", new Object[]{balanceTest.getContext().getString(R.string.setting_ui_ronin_balance_test_res), balanceTest.getContext().getString(R.string.setting_ui_ronin_balance_test_pitch), stringArray2[i], balanceTest.getContext().getString(R.string.setting_ui_ronin_balance_test_roll), stringArray[intValue]}));
                        balanceTest.d.sendEmptyMessageDelayed(4, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                        return;
                    case 4:
                        if (balanceTest.b != null) {
                            balanceTest.b.dismiss();
                            balanceTest.b = null;
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public BalanceTest(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.b = null;
        this.d = null;
        this.d = new a(this, this);
    }

    public void onClick(View view) {
        dji.setting.ui.widget.a.b(getContext(), R.string.setting_ui_ronin_balance_test_tip, new OnClickListener(this) {
            final /* synthetic */ BalanceTest a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                DataGimbalSetUserParams.getInstance().a(BalanceTest.a, Integer.valueOf(1)).start(new d(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a.d.sendEmptyMessage(1);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                    }
                });
            }
        });
    }
}
