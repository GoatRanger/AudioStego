package dji.pilot2.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import dji.pilot.R;
import dji.pilot.publics.objects.c;
import dji.pilot2.publics.object.DJINotificationDialog;

public class f {
    Handler a = new Handler(this) {
        final /* synthetic */ f a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (this.a.b != null) {
                this.a.b.dismiss();
            }
            switch (message.what) {
            }
        }
    };
    private c b;
    private Context c;
    private DJINotificationDialog d;

    public f(Context context) {
        this.c = context;
    }

    public void a(String str, boolean z, String str2) {
        if (this.d == null) {
            this.d = new DJINotificationDialog(this.c, str);
        } else {
            this.d.a(str);
        }
        if (z) {
            this.d.b(str2);
        }
        if (this.d == null || this.d.isShowing()) {
            this.d.b();
            return;
        }
        this.d.show();
        this.d.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ f a;

            {
                this.a = r1;
            }

            public void onDismiss(DialogInterface dialogInterface) {
                this.a.d = null;
            }
        });
    }

    public void a(final int i) {
        this.b = new c(this.c, R.style.a8, false);
        this.b.setContentView(R.layout.dialog_progress);
        Drawable background = ((ImageView) this.b.findViewById(R.id.rt)).getBackground();
        if (background instanceof AnimationDrawable) {
            ((AnimationDrawable) background).start();
        }
        this.b.show();
        this.b.setCancelable(false);
        this.b.setCanceledOnTouchOutside(false);
        new Thread(new Runnable(this) {
            final /* synthetic */ f b;

            public void run() {
                try {
                    Thread.sleep((long) i);
                    if (this.b.b != null) {
                        this.b.b();
                        this.b.a.sendEmptyMessage(1);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void a() {
        this.b = new c(this.c, R.style.a8, false);
        this.b.setContentView(R.layout.dialog_progress);
        Drawable background = ((ImageView) this.b.findViewById(R.id.rt)).getBackground();
        if (background instanceof AnimationDrawable) {
            ((AnimationDrawable) background).start();
        }
        this.b.show();
        this.b.setCancelable(false);
        this.b.setCanceledOnTouchOutside(false);
    }

    public void b() {
        if (this.b != null && this.b.isShowing()) {
            Drawable background = ((ImageView) this.b.findViewById(R.id.rt)).getBackground();
            if (background instanceof AnimationDrawable) {
                ((AnimationDrawable) background).stop();
            }
            this.b.dismiss();
            this.b = null;
        }
    }

    public void a(int i, int i2) {
        Window window = this.b.getWindow();
        LayoutParams attributes = window.getAttributes();
        attributes.gravity = 49;
        attributes.y = i2;
        attributes.width = -2;
        attributes.height = -2;
        window.setAttributes(attributes);
    }
}
