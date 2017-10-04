package dji.pilot.usercenter.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import dji.pilot.R;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.usercenter.a.a;
import dji.pilot.usercenter.e.a.c;
import dji.pilot.usercenter.e.b;
import dji.publics.DJIUI.DJIListView;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.List;

public class DJISelectRegionActivity extends DJIBaseActivity {
    public static final String a = "key_region";
    private b b = null;
    private ArrayList<b> c = new ArrayList();
    private a d = null;
    private DJIListView e = null;
    private DJITextView f = null;
    private c g = null;
    private OnItemClickListener h = null;
    private OnClickListener i = null;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        dji.pilot.usercenter.e.a.getInstance().a((Context) this);
        c();
        a();
        b();
        d();
    }

    private void a() {
        this.g = new c(this) {
            final /* synthetic */ DJISelectRegionActivity a;

            {
                this.a = r1;
            }

            public void a(List<b> list, b bVar) {
                this.a.c.clear();
                this.a.c.addAll(list);
                this.a.d.notifyDataSetChanged();
            }
        };
        this.h = new OnItemClickListener(this) {
            final /* synthetic */ DJISelectRegionActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                b bVar = (b) this.a.c.get(i);
                if (bVar.c) {
                    Intent intent = new Intent();
                    intent.putExtra(DJISelectRegionActivity.a, bVar);
                    intent.setClass(this.a, DJISelectRegionActivity.class);
                    this.a.startActivity(intent);
                }
            }
        };
        this.i = new OnClickListener(this) {
            final /* synthetic */ DJISelectRegionActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.finish();
            }
        };
    }

    private void b() {
        setContentView(R.layout.usercenter_myinfo_select_region);
        this.f = (DJITextView) findViewById(R.id.c3c);
        this.e = (DJIListView) findViewById(R.id.c3j);
        this.e.setEmptyView(findViewById(R.id.c3i));
        this.f.setOnClickListener(this.i);
        this.e.setOnItemClickListener(this.h);
        this.d = new a(this.c, this);
        this.e.setAdapter(this.d);
    }

    private void c() {
        Intent intent = getIntent();
        if (intent != null) {
            this.b = (b) intent.getSerializableExtra(a);
        }
    }

    private void d() {
        dji.pilot.usercenter.e.a.getInstance().a(this.b, this.g);
    }

    protected void onDestroy() {
        dji.pilot.usercenter.e.a.getInstance().c();
        super.onDestroy();
    }
}
