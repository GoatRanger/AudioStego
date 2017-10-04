package dji.pilot2.mine.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import dji.midware.data.forbid.DJIFlyForbidController;
import dji.pilot.R;
import dji.pilot.flyunlimit.b.f;
import dji.pilot.flyunlimit.jsonbean.UnlockListItem;
import dji.pilot.publics.widget.DJIStateImageView;
import dji.pilot2.DJIActivityNoFullScreen;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.afinal.b;
import java.io.Serializable;
import java.util.List;

public class UnlockNFZActivity extends DJIActivityNoFullScreen {
    private DJIRelativeLayout a;
    private ListView b;
    private DJITextView c;
    private a d;
    private DJIStateImageView t;
    private b u;
    private List<UnlockListItem> v;
    private boolean w = false;

    public class a extends BaseAdapter {
        final /* synthetic */ UnlockNFZActivity a;
        private LayoutInflater b;
        private Context c;
        private List<UnlockListItem> d;

        private final class a {
            public DJITextView a;
            public DJITextView b;
            final /* synthetic */ a c;

            private a(a aVar) {
                this.c = aVar;
            }
        }

        public a(UnlockNFZActivity unlockNFZActivity, Context context, List<UnlockListItem> list) {
            this.a = unlockNFZActivity;
            this.b = LayoutInflater.from(context);
            this.c = context;
            this.d = list;
        }

        public void a(List<UnlockListItem> list) {
            this.d = list;
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.d.size();
        }

        public Object getItem(int i) {
            return this.d.get(i);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            a aVar;
            if (view == null) {
                aVar = new a();
                view = this.b.inflate(R.layout.v2_unlock_list_item, null);
                aVar.a = (DJITextView) view.findViewById(R.id.d0a);
                aVar.b = (DJITextView) view.findViewById(R.id.d0b);
                view.setTag(aVar);
            } else {
                aVar = (a) view.getTag();
            }
            aVar.a.setText(((UnlockListItem) this.d.get(i)).places);
            aVar.b.setText(((UnlockListItem) this.d.get(i)).status);
            return view;
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.v2_activity_unlock_nfz);
        a();
        b();
        h();
    }

    private void a() {
        this.a = (DJIRelativeLayout) findViewById(R.id.cgn);
        this.b = (ListView) findViewById(R.id.cgo);
        this.c = (DJITextView) findViewById(R.id.cgp);
        this.t = (DJIStateImageView) findViewById(R.id.cgl);
        this.b.setOnItemClickListener(new OnItemClickListener(this) {
            final /* synthetic */ UnlockNFZActivity a;

            {
                this.a = r1;
            }

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (this.a.v != null && !this.a.v.isEmpty()) {
                    Intent intent = new Intent(this.a, UnlockNFZDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(UnlockNFZDetailActivity.a, (Serializable) this.a.v.get(i));
                    intent.putExtras(bundle);
                    this.a.startActivity(intent);
                }
            }
        });
    }

    public void onClickHandler(View view) {
        switch (view.getId()) {
            case R.id.cgl:
                finish();
                return;
            case R.id.cgp:
                h();
                return;
            default:
                return;
        }
    }

    private void b() {
        this.u = DJIFlyForbidController.getInstance().getDb();
        this.v = this.u.c(UnlockListItem.class);
        if (this.v != null && !this.v.isEmpty()) {
            this.d = new a(this, this, this.v);
            this.b.setAdapter(this.d);
            this.b.setVisibility(0);
            this.a.go();
        }
    }

    private void f() {
        this.w = false;
        this.c.setEnabled(true);
    }

    private void g() {
        this.w = true;
        this.c.setEnabled(false);
    }

    private void h() {
        if (!this.w) {
            g();
            dji.pilot.flyunlimit.b.getInstance(this).a(new f(this) {
                final /* synthetic */ UnlockNFZActivity a;

                {
                    this.a = r1;
                }

                public void a(List<UnlockListItem> list) {
                    this.a.f();
                    this.a.v = list;
                    for (int size = this.a.v.size() - 1; size >= 0; size--) {
                        UnlockListItem unlockListItem = (UnlockListItem) this.a.v.get(size);
                        if ("android".equals(unlockListItem.os) || "ios".equals(unlockListItem.os)) {
                            this.a.v.remove(size);
                        }
                    }
                    if (this.a.v.isEmpty()) {
                        this.a.b.setVisibility(8);
                        this.a.a.show();
                        return;
                    }
                    if (this.a.d == null) {
                        this.a.d = new a(this.a, this.a, this.a.v);
                        this.a.b.setAdapter(this.a.d);
                    } else {
                        this.a.d.a(this.a.v);
                    }
                    this.a.b.setVisibility(0);
                    this.a.a.go();
                }

                public void a() {
                    this.a.f();
                }
            });
        }
    }
}
