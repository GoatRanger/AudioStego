package dji.pilot2.account.forget;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.utils.m;

public class DJIForgetSuccessActivity extends DJIActivityNoFullScreen implements OnClickListener {
    public static final String a = "email";
    private static final String b = "DJIForgetSuccessActivity";

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_forget_success);
        a();
    }

    private void a() {
        CharSequence stringExtra = getIntent().getStringExtra("email");
        if (m.c(stringExtra)) {
            DJILogHelper.getInstance().LOGE(b, "error: email is null!");
            finish();
            return;
        }
        ((TextView) findViewById(R.id.c95)).setText(getResources().getString(R.string.home_account_forget_msg).replace("%s", stringExtra));
        findViewById(R.id.c96).setOnClickListener(this);
        findViewById(R.id.ci).setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ci:
                finish();
                return;
            case R.id.c96:
                finish();
                return;
            default:
                return;
        }
    }
}
