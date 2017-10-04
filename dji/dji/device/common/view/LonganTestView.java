package dji.device.common.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.pilot.fpv.R;

public class LonganTestView extends GridLayout {
    boolean a = false;
    Button b;
    Button c;
    Button d;
    Button e;
    TextView f;
    TextView g;
    int h = 0;
    int i = 0;
    Handler j = new Handler(new Callback(this) {
        final /* synthetic */ LonganTestView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            return false;
        }
    });

    public LonganTestView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.a) {
            setVisibility(0);
        }
        this.b = (Button) findViewById(R.id.longan_btn_1);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LonganTestView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
        this.c = (Button) findViewById(R.id.longan_btn_2);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LonganTestView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
            }
        });
        this.f = (TextView) findViewById(R.id.longan_tv_1);
        this.g = (TextView) findViewById(R.id.longan_tv_2);
        this.d = (Button) findViewById(R.id.longan_btn_3);
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LonganTestView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c();
            }
        });
        this.e = (Button) findViewById(R.id.longan_btn_4);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LonganTestView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
            }
        });
        a(getResources().getConfiguration());
    }

    private void a() {
        int i = this.h - 1;
        if (i >= 0) {
            i--;
        }
    }

    private void b() {
        int i = this.h + 1;
        if (i <= 100) {
            i++;
        }
    }

    private void c() {
        int i = this.i - 1;
        if (i >= 0) {
            i--;
        }
    }

    private void d() {
        int i = this.i + 1;
        if (i <= 100) {
            i++;
        }
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(configuration);
    }

    private void a(Configuration configuration) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        if (configuration.orientation == 2) {
            layoutParams.rightMargin = 100;
        } else {
            layoutParams.rightMargin = 20;
        }
    }
}
