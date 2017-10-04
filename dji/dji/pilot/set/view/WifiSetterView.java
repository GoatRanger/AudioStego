package dji.pilot.set.view;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.dji.frame.c.l;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataWifiGetPassword;
import dji.midware.data.model.P3.DataWifiGetSSID;
import dji.midware.data.model.P3.DataWifiSetPassword;
import dji.midware.data.model.P3.DataWifiSetSSID;
import dji.pilot.set.R;
import dji.pilot.set.d;
import dji.pilot.set.e;
import java.util.regex.Pattern;

public class WifiSetterView extends LinearLayout implements OnClickListener {
    private static final int a = 0;
    private static final int b = 1;
    private static final int c = 2;
    private static final int d = 3;
    private static final int e = 4;
    private static final int f = 5;
    private static final String x = "[A-Z0-9a-z._-]*";
    private final DataWifiGetSSID g = new DataWifiGetSSID();
    private final DataWifiGetPassword h = new DataWifiGetPassword();
    private final DataWifiSetSSID i = new DataWifiSetSSID();
    private final DataWifiSetPassword j = new DataWifiSetPassword();
    private EditText k;
    private EditText l;
    private Button m;
    private ProgressBar n;
    private String o;
    private String p;
    private boolean q;
    private boolean r;
    private Context s;
    private Handler t = new Handler(new Callback(this) {
        final /* synthetic */ WifiSetterView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.k.setText(this.a.o);
                    break;
                case 1:
                    this.a.l.setText(this.a.p);
                    break;
                case 2:
                    this.a.d();
                    break;
                case 3:
                    this.a.e();
                    break;
                case 5:
                    d.a(this.a.getContext(), R.string.wifi_apply_success);
                    break;
            }
            return false;
        }
    });
    private int u = 0;
    private boolean v = false;
    private boolean w = false;

    public WifiSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.s = context;
    }

    private void a() {
        if (!isInEditMode()) {
            this.k = (EditText) findViewById(R.id.set_wifi_ssid);
            this.l = (EditText) findViewById(R.id.set_wifi_pwd);
            this.n = (ProgressBar) findViewById(R.id.set_wifi_progress);
            this.m = (Button) findViewById(R.id.set_wifi_btn);
            this.m.setOnClickListener(this);
        }
    }

    private void b() {
        this.g.setFromLongan(true).start(new dji.midware.e.d(this) {
            final /* synthetic */ WifiSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.o = this.a.g.getSSID();
                this.a.t.sendEmptyMessage(0);
            }

            public void onFailure(a aVar) {
                Log.v("Get Wifi Name result", "Failure");
            }
        });
        this.h.setFromLongan(true).start(new dji.midware.e.d(this) {
            final /* synthetic */ WifiSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.p = this.a.h.getPassword();
                this.a.t.sendEmptyMessage(1);
            }

            public void onFailure(a aVar) {
                Log.v("Get Pwd Name result", "Failure");
            }
        });
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            a();
            b();
        }
    }

    public void onClick(View view) {
        a(this.k.getText().toString(), this.l.getText().toString());
    }

    private void a(final String str, final String str2) {
        if (l.a(str)) {
            d.a(getContext(), R.string.wifi_ssid_empty_tip);
        } else if (!checkValid(str)) {
            d.a(getContext(), R.string.wifi_ssid_invalid_tip);
        } else if (l.a(str2)) {
            d.a(getContext(), R.string.wifi_pwd_empty_tip);
        } else if (!checkValid(str2)) {
            d.a(getContext(), R.string.wifi_pwd_invalid_tip);
        } else if (str2.length() < 8) {
            d.a(getContext(), R.string.wifi_pwd_tip);
        } else {
            this.q = false;
            this.r = false;
            this.v = false;
            this.w = false;
            this.u = 0;
            if (this.o.equals(str)) {
                this.v = true;
            } else {
                this.q = true;
                this.u++;
            }
            if (this.p.equals(str2)) {
                this.w = true;
            } else {
                this.r = true;
                this.u++;
            }
            if (this.q || this.r) {
                this.t.sendEmptyMessage(2);
                if (this.q || this.r) {
                    String string = getResources().getString(R.string.longan_wifi_apply_tip);
                    if (this.q) {
                        string = string + getResources().getString(R.string.longan_wifi_apply_tip_ssid) + str;
                    }
                    if (this.r) {
                        string = string + getResources().getString(R.string.longan_wifi_apply_tip_password) + str2;
                    }
                    e.a(this.s, string, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ WifiSetterView c;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (this.c.q) {
                                this.c.i.a(true).a(str.getBytes()).start(new dji.midware.e.d(this) {
                                    final /* synthetic */ AnonymousClass4 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onSuccess(Object obj) {
                                        this.a.c.v = true;
                                        this.a.c.c();
                                        this.a.c.o = str;
                                    }

                                    public void onFailure(a aVar) {
                                        this.a.c.v = false;
                                        this.a.c.c();
                                    }
                                });
                            }
                            if (this.c.r) {
                                this.c.j.a(true).a(str2.getBytes()).start(new dji.midware.e.d(this) {
                                    final /* synthetic */ AnonymousClass4 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onSuccess(Object obj) {
                                        this.a.c.w = true;
                                        this.a.c.c();
                                        this.a.c.p = str2;
                                    }

                                    public void onFailure(a aVar) {
                                        this.a.c.w = false;
                                        this.a.c.c();
                                    }
                                });
                            }
                        }
                    }, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ WifiSetterView a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (this.a.q && this.a.r) {
                                this.a.q = false;
                                this.a.r = false;
                                this.a.u = this.a.u - 1;
                            }
                            this.a.c();
                            this.a.t.sendEmptyMessage(1);
                            this.a.t.sendEmptyMessage(0);
                        }
                    });
                    return;
                }
                return;
            }
            d.a(getContext(), R.string.wifi_apply_success);
        }
    }

    private void c() {
        this.u--;
        if (this.u == 0) {
            if (this.w && this.v) {
                this.t.sendEmptyMessage(5);
            } else {
                this.t.sendMessage(this.t.obtainMessage(4, getResources().getString(R.string.wifi_apply_fail)));
            }
            this.t.sendEmptyMessage(3);
        }
    }

    private void d() {
        this.m.setVisibility(8);
        this.n.setVisibility(0);
    }

    private void e() {
        this.m.setVisibility(0);
        this.n.setVisibility(8);
    }

    public static boolean checkValid(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        return Pattern.compile(x).matcher(str).matches();
    }
}
