package dji.pilot.home.rc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot.usercenter.b.f;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.pilot2.account.sign.DJIAccountSignActivity;
import dji.pilot2.explore.activity.DJISupportShareWebviewActivity;
import dji.pilot2.explore.fragment.DJISupportShareWebviewFragment;
import dji.pilot2.mine.activity.ContactDjiActivity;
import dji.pilot2.mine.activity.RepairWebviewActivity;
import dji.pilot2.mine.activity.WebActivity;
import dji.pilot2.mine.view.MineButton;
import dji.pilot2.utils.k;
import dji.pilot2.welcome.fragment.DJIWebviewFragment;

public class DJIRcMineMoreSettingActivity extends DJIActivityNoFullScreen implements OnClickListener {
    private MineButton a;
    private MineButton b;
    private MineButton c;
    private MineButton d;
    private View t;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mine_more_setting);
        a();
    }

    private void a() {
        this.t = (DJIStateImageView) findViewById(R.id.hn);
        this.t.setOnClickListener(this);
        this.a = (MineButton) findViewById(R.id.ho);
        this.b = (MineButton) findViewById(R.id.hp);
        this.c = (MineButton) findViewById(R.id.hr);
        this.d = (MineButton) findViewById(R.id.hs);
        this.a.setOnClickListener(this);
        this.a.setButtonText(getResources().getString(R.string.mine_dji_dds));
        this.a.setButtonIcon(getResources().getDrawable(R.drawable.ic_my_earnings));
        this.b.setOnClickListener(this);
        this.b.setButtonText(getResources().getString(R.string.v2_6_mine_repair));
        this.b.setButtonIcon(getResources().getDrawable(R.drawable.ic_repair));
        this.c.setOnClickListener(this);
        this.c.setButtonText(getResources().getString(R.string.mine_contact_dji));
        this.c.setButtonIcon(getResources().getDrawable(R.drawable.ic_dji_support));
        this.d.setOnClickListener(this);
        this.d.setButtonText(getResources().getString(R.string.mine_feedback));
        this.d.setButtonIcon(getResources().getDrawable(R.drawable.ic_feedback));
        if (!"CN".equals(getResources().getConfiguration().locale.getCountry())) {
            this.b.setVisibility(8);
            findViewById(R.id.hq).setVisibility(8);
        }
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.hn:
                finish();
                return;
            case R.id.ho:
                if (f.getInstance().c()) {
                    intent = new Intent(this, DJISupportShareWebviewActivity.class);
                    this.a.setRedPointVisiblity(false);
                    intent.putExtra("key_force_landscap", true);
                    intent.putExtra(DJIWebviewFragment.o, k.o());
                    intent.putExtra(DJIWebviewFragment.s, true);
                    startActivity(intent);
                    return;
                }
                b();
                return;
            case R.id.hp:
                if (!f.getInstance().c() || f.getInstance().o() == null) {
                    b();
                    return;
                }
                String u = k.u(f.getInstance().o());
                Intent intent2 = new Intent(this, RepairWebviewActivity.class);
                intent2.putExtra("key_force_landscap", true);
                intent2.putExtra(DJISupportShareWebviewFragment.o, u);
                startActivity(intent2);
                return;
            case R.id.hr:
                intent = new Intent(this, ContactDjiActivity.class);
                intent.putExtra("key_force_landscap", true);
                startActivity(intent);
                return;
            case R.id.hs:
                intent = new Intent(this, WebActivity.class);
                intent.putExtra(DJIWebviewFragment.o, k.q());
                intent.putExtra(DJIWebviewFragment.s, false);
                intent.putExtra("title_text", getResources().getString(R.string.mine_feedback));
                intent.putExtra("key_force_landscap", true);
                startActivity(intent);
                return;
            default:
                return;
        }
    }

    private void b() {
        Intent intent = new Intent(this, DJIAccountSignActivity.class);
        intent.putExtra(DJIAccountSignActivity.c, false);
        intent.putExtra(DJIAccountSignActivity.b, true);
        intent.putExtra(DJIAccountSignActivity.a, 1008);
        startActivity(intent);
    }
}
