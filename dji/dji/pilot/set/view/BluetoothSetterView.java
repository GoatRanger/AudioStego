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
import dji.midware.b.c;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataWifiGetSSID;
import dji.midware.data.model.P3.DataWifiSetSSID;
import dji.pilot.set.R;
import dji.pilot.set.d;
import dji.pilot.set.e;
import java.io.UnsupportedEncodingException;

public class BluetoothSetterView extends LinearLayout implements OnClickListener {
    private static final int a = 0;
    private static final int b = 2;
    private static final int c = 3;
    private static final int d = 4;
    private static final int e = 5;
    private static final String f = BluetoothSetterView.class.getSimpleName();
    private final DataWifiGetSSID g = new DataWifiGetSSID();
    private final DataWifiSetSSID h = new DataWifiSetSSID();
    private EditText i;
    private Button j;
    private ProgressBar k;
    private String l = "";
    private boolean m;
    private Context n;
    private Handler o = new Handler(new Callback(this) {
        final /* synthetic */ BluetoothSetterView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    this.a.i.setText(this.a.l);
                    break;
                case 2:
                    this.a.e();
                    break;
                case 3:
                    this.a.f();
                    break;
                case 5:
                    d.a(this.a.getContext(), R.string.bluetooth_apply_success);
                    break;
            }
            return false;
        }
    });
    private int p = 0;
    private boolean q = false;

    public BluetoothSetterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.n = context;
    }

    private void b() {
        if (!isInEditMode()) {
            this.i = (EditText) findViewById(R.id.set_bluetooth_ssid);
            if (c.getInstance().f().k() != null) {
                this.i.setText(c.getInstance().f().k());
            }
            this.k = (ProgressBar) findViewById(R.id.set_bluetooth_progress);
            this.j = (Button) findViewById(R.id.set_bluetooth_btn);
            this.j.setOnClickListener(this);
        }
    }

    private void c() {
        this.g.setFromLongan(true).start(new dji.midware.e.d(this) {
            final /* synthetic */ BluetoothSetterView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.l = this.a.g.getSSID();
                Log.d(BluetoothSetterView.f, "onSuccess: ssid = " + this.a.l);
                this.a.o.sendEmptyMessage(0);
            }

            public void onFailure(a aVar) {
                this.a.l = this.a.g.getSSID();
                Log.d(BluetoothSetterView.f, "onFailure: ssid = " + this.a.l);
                this.a.o.sendEmptyMessage(0);
            }
        });
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            b();
        }
    }

    public void onClick(View view) {
        a(this.i.getText().toString());
    }

    private void a(final String str) {
        if (!c.getInstance().isConnected()) {
            d.a(getContext(), R.string.Bluetooth_disconnected);
        } else if (l.a(str)) {
            d.a(getContext(), R.string.Bluetooth_ssid_empty_tip);
        } else {
            try {
                if (str.getBytes("utf-8").length > 20) {
                    d.a(getContext(), R.string.Bluetooth_ssid_length_tip);
                    return;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            this.m = false;
            this.q = false;
            this.p = 0;
            if (this.l.equals(str)) {
                this.q = true;
            } else {
                this.m = true;
                this.p++;
            }
            if (this.m) {
                this.o.sendEmptyMessage(2);
                if (this.m) {
                    String str2 = new String();
                    if (this.m) {
                        str2 = str2 + getResources().getString(R.string.bluetooth_apply_tip_ssid) + str;
                    }
                    e.a(this.n, str2, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ BluetoothSetterView b;

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (this.b.m) {
                                this.b.h.a(true).a(str.getBytes()).start(new dji.midware.e.d(this) {
                                    final /* synthetic */ AnonymousClass3 a;

                                    {
                                        this.a = r1;
                                    }

                                    public void onSuccess(Object obj) {
                                        this.a.b.q = true;
                                        Log.d(BluetoothSetterView.f, "onSuccess: " + obj);
                                        this.a.b.d();
                                        this.a.b.l = str;
                                    }

                                    public void onFailure(a aVar) {
                                        this.a.b.q = false;
                                        Log.d(BluetoothSetterView.f, "onSuccess: " + aVar);
                                        this.a.b.d();
                                    }
                                });
                            }
                        }
                    }, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ BluetoothSetterView a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (this.a.m) {
                                this.a.m = false;
                                this.a.p = this.a.p - 1;
                            }
                            this.a.d();
                            this.a.o.sendEmptyMessage(0);
                        }
                    });
                    return;
                }
                return;
            }
            d.a(getContext(), R.string.bluetooth_apply_success);
        }
    }

    private void d() {
        this.p--;
        Log.d(f, "calResult: updateCount = " + this.p + "ssidResult = " + this.q);
        if (this.p == 0) {
            if (this.q) {
                this.o.sendEmptyMessage(5);
            } else {
                this.o.sendMessage(this.o.obtainMessage(4, getResources().getString(R.string.bluetooth_apply_fail)));
            }
            this.o.sendEmptyMessage(3);
        }
    }

    private void e() {
        this.j.setVisibility(8);
        this.k.setVisibility(0);
    }

    private void f() {
        this.j.setVisibility(0);
        this.k.setVisibility(8);
    }
}
