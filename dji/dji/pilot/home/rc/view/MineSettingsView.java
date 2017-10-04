package dji.pilot.home.rc.view;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import dji.pilot.R;
import dji.pilot.home.rc.activity.DJIRcMineMoreSettingActivity;
import dji.pilot2.explore.fragment.DJINotificationExploreFragment;
import dji.pilot2.favourite.activity.MyCollectionActivity;
import dji.pilot2.mine.activity.SettingsActivity;
import dji.pilot2.mine.view.MineButton;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.setting.a.a;

public class MineSettingsView extends DJIRelativeLayout implements OnClickListener {
    private Context a;
    private DJILinearLayout b;
    private MineButton c;
    private MineButton d;
    private MineButton e;
    private MineButton f;
    private MineButton g;

    public MineSettingsView(Context context) {
        this(context, null);
    }

    public MineSettingsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        a();
    }

    private void a() {
        a.a((View) this, (int) R.layout.setting_system_setting_items);
        if (!isInEditMode()) {
            b();
        }
    }

    private void b() {
        this.b = (DJILinearLayout) findViewById(R.id.boq);
        this.c = (MineButton) findViewById(R.id.bor);
        this.d = (MineButton) findViewById(R.id.bos);
        this.e = (MineButton) findViewById(R.id.bot);
        this.f = (MineButton) findViewById(R.id.bou);
        this.g = (MineButton) findViewById(R.id.bov);
        this.c.setOnClickListener(this);
        this.c.setButtonIcon(getResources().getDrawable(R.drawable.ic_message));
        this.c.setButtonText(getResources().getString(R.string.mine_setting_message));
        this.d.setOnClickListener(this);
        this.d.setButtonIcon(getResources().getDrawable(R.drawable.ic_collection));
        this.d.setButtonText(getResources().getString(R.string.mine_setting_collection));
        this.e.setOnClickListener(this);
        this.e.setButtonIcon(getResources().getDrawable(R.drawable.ic_more));
        this.e.setButtonText(getResources().getString(R.string.mine_setting_more));
        this.f.setOnClickListener(this);
        this.f.setButtonIcon(getResources().getDrawable(R.drawable.ic_system_setting));
        this.f.setButtonText(getResources().getString(R.string.mine_setting_system));
        this.g.setOnClickListener(this);
        this.g.setButtonIcon(getResources().getDrawable(R.drawable.ic_application_setting));
        this.g.setButtonText(getResources().getString(R.string.mine_setting_application));
    }

    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.bor:
                intent = new Intent(this.a, DJINotificationExploreFragment.class);
                intent.putExtra("key_force_landscap", true);
                this.a.startActivity(intent);
                return;
            case R.id.bos:
                intent = new Intent(this.a, MyCollectionActivity.class);
                intent.putExtra("key_force_landscap", true);
                this.a.startActivity(intent);
                return;
            case R.id.bot:
                this.a.startActivity(new Intent(this.a, DJIRcMineMoreSettingActivity.class));
                return;
            case R.id.bou:
                this.a.startActivity(this.a.getPackageManager().getLaunchIntentForPackage("com.android.settings"));
                return;
            case R.id.bov:
                intent = new Intent(this.a, SettingsActivity.class);
                intent.putExtra("key_force_landscap", true);
                this.a.startActivity(intent);
                return;
            default:
                return;
        }
    }
}
