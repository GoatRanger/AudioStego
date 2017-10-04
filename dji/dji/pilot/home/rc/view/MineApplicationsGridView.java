package dji.pilot.home.rc.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import dji.pilot.R;
import dji.pilot.b;
import dji.publics.DJIUI.DJIGridView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MineApplicationsGridView extends LinearLayout {
    private Context a;
    private DJIGridView b;
    private dji.pilot.home.rc.a.a c;
    private a d;
    private IntentFilter e;

    class a extends BroadcastReceiver {
        final /* synthetic */ MineApplicationsGridView a;

        a(MineApplicationsGridView mineApplicationsGridView) {
            this.a = mineApplicationsGridView;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
                this.a.updateApplist();
            }
            if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
                this.a.updateApplist();
            }
        }
    }

    public MineApplicationsGridView(Context context) {
        this(context, null);
    }

    public MineApplicationsGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        a();
    }

    private void a() {
        dji.setting.a.a.a((View) this, (int) R.layout.setting_applications);
        if (!isInEditMode()) {
            this.e = new IntentFilter();
            this.e.addAction("android.intent.action.PACKAGE_ADDED");
            this.e.addAction("android.intent.action.PACKAGE_REMOVED");
            this.e.addDataScheme("package");
            this.d = new a(this);
            this.a.registerReceiver(this.d, this.e);
            this.b = (DJIGridView) findViewById(R.id.bnx);
            this.c = new dji.pilot.home.rc.a.a(this.a, getApplicationList());
            this.b.setAdapter(this.c);
        }
    }

    private ArrayList<dji.pilot.home.rc.b.a> getApplicationList() {
        Object arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.MAIN", null);
        intent.addCategory("android.intent.category.LAUNCHER");
        PackageManager packageManager = this.a.getPackageManager();
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            if (!resolveInfo.activityInfo.packageName.contains(b.b)) {
                dji.pilot.home.rc.b.a aVar = new dji.pilot.home.rc.b.a();
                aVar.a = resolveInfo.loadLabel(packageManager).toString();
                aVar.b = resolveInfo.activityInfo.packageName;
                aVar.e = resolveInfo.loadIcon(packageManager);
                arrayList.add(aVar);
            }
        }
        Collections.sort(arrayList, new Comparator<dji.pilot.home.rc.b.a>(this) {
            final /* synthetic */ MineApplicationsGridView a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((dji.pilot.home.rc.b.a) obj, (dji.pilot.home.rc.b.a) obj2);
            }

            public int a(dji.pilot.home.rc.b.a aVar, dji.pilot.home.rc.b.a aVar2) {
                return aVar.a.compareTo(aVar2.a);
            }
        });
        return arrayList;
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            this.a.registerReceiver(this.d, this.e);
        }
    }

    public void updateApplist() {
        this.c.a(getApplicationList());
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.a.unregisterReceiver(this.d);
    }
}
