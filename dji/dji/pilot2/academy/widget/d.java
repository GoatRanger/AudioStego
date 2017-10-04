package dji.pilot2.academy.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;
import dji.midware.data.config.P3.ProductType;
import dji.pilot.R;
import dji.pilot.college.model.CollegeInfo;
import dji.pilot.college.widget.DJIArcProgressBar;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.b.c;
import dji.pilot.usercenter.protocol.e$a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class d extends BaseAdapter {
    protected LayoutInflater a;
    protected Context b;
    protected final List<CollegeInfo> c = new ArrayList();
    protected dji.pilot.college.a.a d = dji.pilot.college.a.a.getInstance();

    static final class a {
        public DJIImageView a = null;
        public DJIImageView b = null;
        public DJIImageView c = null;
        public DJIImageView d = null;
        public DJIArcProgressBar e = null;
        public DJITextView f = null;
        public DJITextView g = null;

        a() {
        }

        public void a(CollegeInfo collegeInfo) {
            if (collegeInfo.mbNew) {
                this.b.show();
            } else {
                this.b.go();
            }
            this.g.setText(collegeInfo.mName);
        }
    }

    public d(Context context) {
        this.a = LayoutInflater.from(context);
        this.b = context;
        a();
    }

    protected void a() {
        c.getInstance().a(this.b.getApplicationContext());
        this.d.a(this.b.getApplicationContext());
    }

    public void a(e$a dji_pilot_usercenter_protocol_e_a) {
        if (dji_pilot_usercenter_protocol_e_a != null) {
            this.d.a(dji_pilot_usercenter_protocol_e_a);
        }
    }

    public void b(e$a dji_pilot_usercenter_protocol_e_a) {
        this.d.b(dji_pilot_usercenter_protocol_e_a);
    }

    public void a(int i) {
        if (i == 65537) {
            notifyDataSetChanged();
        }
    }

    public void b(int i) {
        if (i == 65537) {
            notifyDataSetChanged();
        } else if (i == 65536) {
            b();
        }
    }

    public void c(int i) {
        if (i == 65537) {
            notifyDataSetChanged();
        }
    }

    public void a(int i, int i2, Object obj) {
        if (i == 65537) {
            if (obj instanceof CollegeInfo) {
                CollegeInfo collegeInfo = (CollegeInfo) obj;
                Toast.makeText(this.b.getApplicationContext(), this.b.getString(R.string.college_download_fail, new Object[]{collegeInfo.mName}), 0).show();
            }
            notifyDataSetChanged();
        } else if (i != 65536) {
        }
    }

    protected void b() {
        dji.pilot.college.model.a a = this.d.a(false);
        this.c.clear();
        if (!a.a.isEmpty()) {
            this.c.addAll(a.a);
        }
        notifyDataSetChanged();
    }

    public void a(ProductType productType) {
        this.d.a(productType);
        b();
        this.d.a(true);
    }

    public void d(int i) {
        CollegeInfo collegeInfo = (CollegeInfo) this.c.get(i);
        if (collegeInfo.mDownloadState == 0) {
            e.a("Academy_DocumentView_Button_Download");
            this.d.a(collegeInfo);
        } else if (collegeInfo.mDownloadState == 3) {
            e.a("Academy_DocumentView_Button_OpenDoc");
            File file = new File(this.d.a(collegeInfo.mName));
            if (dji.pilot.usercenter.f.c.a(file)) {
                this.d.c(collegeInfo);
                notifyDataSetChanged();
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.addFlags(268435456);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                this.b.startActivity(Intent.createChooser(intent, this.b.getString(R.string.app_look)));
                return;
            }
            collegeInfo.mDownloadState = 0;
            notifyDataSetChanged();
            Toast.makeText(this.b.getApplicationContext(), R.string.college_file_nonexist, 0).show();
        } else if (collegeInfo.mDownloadState == 1 || collegeInfo.mDownloadState == 2) {
            e.a("Academy_DocumentView_Button_CancelDownload");
            this.d.b(collegeInfo);
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        return this.c.size();
    }

    public Object getItem(int i) {
        return this.c.get(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a();
            view = this.a.inflate(R.layout.v2_academy_fly_doc_item, null);
            aVar.a = (DJIImageView) view.findViewById(R.id.c3v);
            aVar.b = (DJIImageView) view.findViewById(R.id.c3w);
            aVar.c = (DJIImageView) view.findViewById(R.id.c3x);
            aVar.d = (DJIImageView) view.findViewById(R.id.c3y);
            aVar.e = (DJIArcProgressBar) view.findViewById(R.id.c3z);
            aVar.f = (DJITextView) view.findViewById(R.id.c40);
            aVar.g = (DJITextView) view.findViewById(R.id.c41);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        CollegeInfo collegeInfo = (CollegeInfo) this.c.get(i);
        if (collegeInfo.mDownloadState == 0) {
            aVar.f.go();
            aVar.e.go();
            aVar.c.show();
            aVar.d.show();
        } else if (collegeInfo.mDownloadState == 2) {
            aVar.f.go();
            aVar.e.show();
            aVar.c.show();
            aVar.d.go();
            aVar.e.setProgress(collegeInfo.mProgress);
        } else if (collegeInfo.mDownloadState == 1) {
            aVar.f.go();
            aVar.e.show();
            aVar.e.setProgress(collegeInfo.mProgress);
            aVar.c.show();
            aVar.d.go();
        } else if (collegeInfo.mDownloadState == 3) {
            aVar.f.go();
            aVar.e.go();
            aVar.c.go();
            aVar.d.go();
        }
        aVar.a(collegeInfo);
        return view;
    }
}
