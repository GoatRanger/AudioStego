package dji.pilot2.usercenter.activate;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import dji.midware.data.model.P3.DataFlycSetPlaneName;
import dji.midware.e.d;
import dji.pilot.R;
import java.util.regex.Pattern;

public class ActivateSetNameView extends ActivateBaseView {
    private static final int d = 0;
    private static final int e = 2;
    private static final int f = 3;
    private EditText c = null;

    private class a implements d {
        final /* synthetic */ ActivateSetNameView a;
        private final String b;

        private a(ActivateSetNameView activateSetNameView, String str) {
            this.a = activateSetNameView;
            this.b = str;
        }

        public void onSuccess(Object obj) {
            c.a("plane name set is success :" + this.b);
            this.a.b.sendEmptyMessage(2);
            a.getInstance().d(this.b);
        }

        public void onFailure(dji.midware.data.config.P3.a aVar) {
            c.a("plane name set is fail :" + this.b);
            Message obtainMessage = this.a.b.obtainMessage();
            obtainMessage.what = 3;
            obtainMessage.obj = aVar.a() + "";
            this.a.b.sendMessage(obtainMessage);
        }
    }

    private class b extends Handler {
        final /* synthetic */ ActivateSetNameView a;

        private b(ActivateSetNameView activateSetNameView) {
            this.a = activateSetNameView;
        }

        public void handleMessage(Message message) {
            if (message.what == 0) {
                Toast.makeText(this.a.getContext(), R.string.activate_input_none, 0).show();
            } else if (message.what == 2) {
                this.a.a.a();
                ((InputMethodManager) this.a.getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 2);
            } else if (message.what == 3) {
                this.a.a.b((String) message.obj);
            }
        }
    }

    public ActivateSetNameView(Context context) {
        super(context);
    }

    public ActivateSetNameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ActivateSetNameView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public boolean handleGoNext() {
        return a();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = new b();
        initView();
    }

    public void initView() {
        this.c = (EditText) findViewById(R.id.e6);
    }

    public void setData() {
    }

    protected boolean a() {
        String trim = this.c.getText().toString().trim();
        if (trim.isEmpty() || containsEmoji(trim)) {
            this.b.sendEmptyMessage(0);
            return false;
        }
        DataFlycSetPlaneName.getInstance().a(trim).start(new a(trim));
        return true;
    }

    public static boolean containsEmoji(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        return Pattern.compile("[🀀-🏿]|[🐀-🟿]|[☀-⟿]", 66).matcher(str).find();
    }
}
