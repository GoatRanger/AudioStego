package dji.pilot2.mine.activity;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import dji.pilot.R;
import dji.pilot.fpv.d.c$m;
import dji.pilot.fpv.d.e;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.mine.c.a;
import dji.pilot2.mine.view.MineContactButton;
import dji.pilot2.mine.view.MineFlowLayout;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;

public class ContactDjiActivity extends DJIActivityNoFullScreen implements c$m {
    public static final String G = "key_force_landscap";
    private static final String I = "phantom3";
    private static final String J = "inspire1";
    private static final String K = "call";
    private static final String L = "live800";
    private static final String M = "mail";
    private static final String N = "+1(818)235-0789";
    private static final String O = "+49(0)9747-9304200";
    private static final String P = "400-700-0303";
    private static final String Q = "support@dji.com";
    private static final String R = "us.support@dji.com";
    private static final String S = "support.europe@dji.com";
    private static final String T = "support.jp@dji.com";
    public boolean H = false;
    private MineContactButton U;
    private MineContactButton V;
    private MineContactButton W;
    private MineFlowLayout X;

    protected void onCreate(Bundle bundle) {
        setContentView(R.layout.v2_mine_contact_dji);
        this.H = getIntent().getBooleanExtra("key_force_landscap", false);
        a();
        super.onCreate(bundle);
    }

    private void a() {
        this.U = (MineContactButton) findViewById(R.id.cst);
        this.V = (MineContactButton) findViewById(R.id.csu);
        this.W = (MineContactButton) findViewById(R.id.csv);
        this.U.setButtonText(getResources().getString(R.string.mine_contact_dji_repair));
        this.V.setButtonText(getResources().getString(R.string.mine_contact_dji_live_800));
        this.W.setButtonText(getResources().getString(R.string.mine_contact_dji_mail));
        this.U.setButtonIcon(getResources().getDrawable(R.drawable.v2_contact_dji_repair));
        this.V.setButtonIcon(getResources().getDrawable(R.drawable.v2_contact_dji_live800));
        this.W.setButtonIcon(getResources().getDrawable(R.drawable.v2_contact_dji_mail));
        this.X = (MineFlowLayout) findViewById(R.id.css);
        this.X.setColCount(3);
        this.X.setBorderColor(getResources().getColor(R.color.l1));
    }

    protected void onResume() {
        super.onResume();
        if (this.H && getRequestedOrientation() != 0) {
            setRequestedOrientation(0);
        }
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.csp:
                finish();
                return;
            case R.id.cst:
                c(k.b((Context) this));
                return;
            case R.id.csu:
                e.b(c$m.dH_);
                d(k.c((Context) this));
                return;
            case R.id.csv:
                a aVar = new a(this);
                aVar.a(new OnClickListener(this) {
                    final /* synthetic */ ContactDjiActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        e.b(c$m.dI_);
                        this.a.b(ContactDjiActivity.R);
                        e.b(c$m.dM_);
                    }
                }, new OnClickListener(this) {
                    final /* synthetic */ ContactDjiActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        e.b(c$m.dI_);
                        this.a.b(ContactDjiActivity.S);
                        e.b(c$m.dN_);
                    }
                }, new OnClickListener(this) {
                    final /* synthetic */ ContactDjiActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        e.b(c$m.dI_);
                        this.a.b("support@dji.com");
                        e.b(c$m.dO_);
                    }
                }, new OnClickListener(this) {
                    final /* synthetic */ ContactDjiActivity a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        e.b(c$m.dI_);
                        this.a.b(ContactDjiActivity.T);
                        e.b(c$m.dP_);
                    }
                });
                aVar.show();
                return;
            default:
                return;
        }
    }

    private void a(String str) {
        try {
            startActivity(new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str)));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.mine_contact_dji_no_call_app, 0).show();
        }
    }

    private void b(String str) {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType("message/rfc822");
        intent.putExtra("android.intent.extra.EMAIL", new String[]{str});
        intent.putExtra("android.intent.extra.SUBJECT", "");
        intent.putExtra("android.intent.extra.TEXT", "");
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.mine_contact_dji_no_email_app, 0).show();
        }
    }

    private void c(String str) {
        Intent intent = new Intent();
        intent.setClass(this, WebActivity.class);
        intent.putExtra(DJIWebviewFragment.o, str);
        startActivity(intent);
    }

    private void d(String str) {
        startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
    }

    protected void onStart() {
        super.onStart();
        e.b(this);
    }

    protected void onStop() {
        e.c(this);
        super.onStop();
    }
}
