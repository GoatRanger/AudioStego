package dji.pilot2.mine.activity;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import dji.pilot.publics.objects.g;
import dji.pilot2.publics.b.a$j;
import java.util.Locale;

public class LanguageSettingBaseActivity extends Activity {
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        a();
    }

    private void a() {
        Resources resources = getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        configuration.locale = b();
        resources.updateConfiguration(configuration, displayMetrics);
    }

    private Locale b() {
        String b = g.b(getApplicationContext(), LanguageSettingActivity.a, "auto");
        if (b.equals("auto")) {
            return Resources.getSystem().getConfiguration().locale;
        }
        if (b.equals("cn")) {
            return Locale.SIMPLIFIED_CHINESE;
        }
        if (b.equals("tw")) {
            return Locale.TRADITIONAL_CHINESE;
        }
        if (b.equals(a$j.e)) {
            return Locale.JAPAN;
        }
        if (b.equals("en")) {
            return Locale.ENGLISH;
        }
        return Resources.getSystem().getConfiguration().locale;
    }
}
