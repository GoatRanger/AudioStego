package dji.phone.k;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import dji.pilot.fpv.R;
import java.lang.ref.WeakReference;

public enum b {
    builder;
    
    private Toast b;
    private a c;

    private static final class a extends Handler {
        private final WeakReference<b> a;

        public a(b bVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(bVar);
        }

        public void handleMessage(Message message) {
            if (((b) this.a.get()) != null) {
                ((b) this.a.get()).b.setText((String) message.obj);
                ((b) this.a.get()).b.setDuration(message.arg1);
                ((b) this.a.get()).b.show();
            }
        }
    }

    public void a(Context context) {
        this.b = Toast.makeText(context, "", 0);
        this.b.setGravity(80, 0, -context.getResources().getDimensionPixelOffset(R.dimen.dp_28_in_sw320dp));
        this.c = new a(this);
    }

    private void a(String str, int i) {
        this.c.sendMessage(this.c.obtainMessage(0, i, 0, str));
    }

    private void a(int i, int i2) {
        this.c.sendMessage(this.c.obtainMessage(0, i2, 0, this.b.getView().getContext().getString(i)));
    }

    public static Toast showShort(String str) {
        builder.a(str, 0);
        return builder.b;
    }

    public static Toast showShort(int i) {
        builder.a(i, 0);
        return builder.b;
    }

    public static Toast showLong(String str) {
        builder.a(str, 1);
        return builder.b;
    }

    public static Toast showLong(int i) {
        builder.a(i, 1);
        return builder.b;
    }
}
