package dji.pilot.usercenter.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.usercenter.f.e;
import dji.publics.DJIUI.DJITextView;

public class DJIModifyMobileActivity extends DJIBaseActivity implements OnClickListener {
    public static final String a = "key_mobile";
    private DJITextView b = null;
    private EditText c = null;
    private DJITextView d = null;
    private String e = null;
    private TextWatcher f = new TextWatcher(this) {
        final /* synthetic */ DJIModifyMobileActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.a.e == null || !this.a.e.equals(charSequence.toString())) {
                this.a.b.setEnabled(true);
            } else {
                this.a.b.setEnabled(false);
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
        a();
    }

    private void a() {
        setContentView(R.layout.usercenter_myinfo_modify_mobile);
        this.c = (EditText) findViewById(R.id.c3g);
        this.b = (DJITextView) findViewById(R.id.c3f);
        this.d = (DJITextView) findViewById(R.id.c3c);
        this.b.setEnabled(false);
        this.d.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.c.addTextChangedListener(this.f);
        if (this.e != null) {
            this.c.setText(this.e);
        }
    }

    private void b() {
        Intent intent = getIntent();
        if (intent != null) {
            this.e = intent.getStringExtra(a);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.c3c:
                finish();
                return;
            case R.id.c3f:
                c();
                return;
            default:
                return;
        }
    }

    private void c() {
        if (e.c(this.c.getEditableText().toString())) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "illegal mobile", 0).show();
        }
    }
}
