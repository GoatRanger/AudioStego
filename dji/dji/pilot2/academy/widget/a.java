package dji.pilot2.academy.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.f.c;
import dji.pilot2.academy.model.AcademyDocInfo.DocInfo;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class a extends BaseAdapter {
    private List<DocInfo> a = new ArrayList();
    private Context b;
    private LayoutInflater c;
    private String d;
    private int e = 2;

    final class a {
        public RelativeLayout a;
        public DJIImageView b;
        public DJITextView c;
        public DJITextView d;
        public DJITextView e;
        public DJIImageView f;
        public DJITextView g;
        public DJIImageView h;
        public DJIAcademyProgressBar i;
        public int j;
        final /* synthetic */ a k;

        a(a aVar) {
            this.k = aVar;
        }

        public void a(int i) {
            this.a.setVisibility(i);
        }

        public void a(View view) {
            this.b = (DJIImageView) view.findViewById(R.id.cvn);
            this.c = (DJITextView) view.findViewById(R.id.cvo);
            this.d = (DJITextView) view.findViewById(R.id.cvp);
            this.e = (DJITextView) view.findViewById(R.id.cvq);
            this.f = (DJIImageView) view.findViewById(R.id.cvr);
            this.g = (DJITextView) view.findViewById(R.id.cvs);
            this.h = (DJIImageView) view.findViewById(R.id.cvt);
            this.i = (DJIAcademyProgressBar) view.findViewById(R.id.cvu);
        }

        public void a(int i, DocInfo docInfo) {
            if (docInfo == null) {
                this.a.setVisibility(4);
                return;
            }
            this.j = i;
            this.c.setText(docInfo.name);
            this.d.setVisibility(8);
            this.g.setVisibility(8);
            this.e.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(dji.pilot2.academy.b.a.a(docInfo.updated_at))));
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    DocInfo a = this.a.k.e(this.a.j);
                    if (a.mDownloadState == 0) {
                        e.a("Academy_DocumentView_Button_Download");
                        dji.pilot2.academy.a.b.getInstance().b(a);
                    } else if (a.mDownloadState == 3) {
                        e.a("Academy_DocumentView_Button_OpenDoc");
                        File file = new File(dji.pilot2.academy.a.b.getInstance().a(a.name));
                        if (c.a(file)) {
                            dji.pilot2.academy.a.b.getInstance().a(a);
                            this.a.k.notifyDataSetChanged();
                            Intent intent = new Intent("android.intent.action.VIEW");
                            intent.addCategory("android.intent.category.DEFAULT");
                            intent.addFlags(268435456);
                            intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                            this.a.k.b.startActivity(Intent.createChooser(intent, this.a.k.b.getString(R.string.app_look)));
                            return;
                        }
                        a.mDownloadState = 0;
                        this.a.k.notifyDataSetChanged();
                        Toast.makeText(this.a.k.b.getApplicationContext(), R.string.college_file_nonexist, 0).show();
                    } else if (a.mDownloadState == 1 || a.mDownloadState == 2) {
                        e.a("Academy_DocumentView_Button_CancelDownload");
                        dji.pilot2.academy.a.b.getInstance().c(a);
                        this.a.k.notifyDataSetChanged();
                    }
                }
            });
            if (docInfo.mDownloadState == 0) {
                this.f.show();
                this.h.show();
                this.h.go();
                this.i.go();
            } else if (docInfo.mDownloadState == 1) {
                this.f.go();
                this.h.show();
                this.i.show();
            } else if (docInfo.mDownloadState == 2) {
                this.f.go();
                this.h.show();
                this.i.show();
                this.i.setProgress(docInfo.mProgress);
            } else if (docInfo.mDownloadState == 3) {
                this.f.go();
                this.h.go();
                this.i.go();
            }
        }
    }

    final class b {
        public a a = new a(this.f);
        public a b = new a(this.f);
        public a c = new a(this.f);
        public a d = new a(this.f);
        public a e = new a(this.f);
        final /* synthetic */ a f;

        b(a aVar) {
            this.f = aVar;
        }

        public void a(int i, int i2) {
            int i3 = i * i2;
            int i4 = (i * i2) + 1;
            int i5 = (i * i2) + 2;
            int i6 = (i * i2) + 3;
            int i7 = (i * i2) + 4;
            if (i2 == 2) {
                this.c.a(8);
                this.d.a(8);
                this.e.a(8);
                this.a.a(i3, this.f.e(i3));
                this.b.a(i4, this.f.e(i4));
                return;
            }
            this.a.a(i3, this.f.e(i3));
            this.b.a(i4, this.f.e(i4));
            this.c.a(i5, this.f.e(i5));
            this.d.a(i6, this.f.e(i6));
            this.e.a(i7, this.f.e(i7));
        }
    }

    public a(Context context, String str) {
        this.c = LayoutInflater.from(context);
        this.b = context;
        this.d = str;
        a();
    }

    public void a(int i) {
        this.e = i;
    }

    protected void a() {
        dji.pilot.usercenter.b.c.getInstance().a(this.b.getApplicationContext());
        dji.pilot2.academy.a.b.getInstance().a(this.b);
    }

    public boolean a(String str) {
        if (dji.pilot2.academy.a.b.getInstance().b()) {
            return false;
        }
        dji.pilot2.academy.a.b.getInstance().c(this.b, str);
        return true;
    }

    public void b(int i) {
        DJILogHelper.getInstance().LOGI("bob", "DJIAcademyDocAdapter handleDataUpdate cmdId = " + i);
        if (i == 3) {
            notifyDataSetChanged();
        }
    }

    public void c(int i) {
        DJILogHelper.getInstance().LOGI("bob", "DJIAcademyDocAdapter handleDataSuccess cmdId = " + i);
        if (i == 3) {
            notifyDataSetChanged();
        } else if (i == 2) {
            b();
        }
    }

    public void d(int i) {
        DJILogHelper.getInstance().LOGI("bob", "DJIAcademyDocAdapter handleDataStart cmdId = " + i);
        if (i == 3) {
            notifyDataSetChanged();
        }
    }

    public void a(int i, int i2, Object obj) {
        DJILogHelper.getInstance().LOGI("bob", "DJIAcademyDocAdapter handleDataFail cmdId = " + i);
        if (i == 3) {
            if (obj instanceof DocInfo) {
                DocInfo docInfo = (DocInfo) obj;
                Toast.makeText(this.b.getApplicationContext(), this.b.getString(R.string.college_download_fail, new Object[]{docInfo.name}), 0).show();
            }
            notifyDataSetChanged();
        } else if (i != 2) {
        }
    }

    protected void b() {
        Collection b = dji.pilot2.academy.a.b.getInstance().b(this.b, this.d);
        this.a.clear();
        if (!(b == null || b.isEmpty())) {
            this.a.addAll(b);
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        int i = 0;
        if (this.a == null) {
            return 0;
        }
        int size = this.a.size();
        int i2 = size / this.e;
        if (size % this.e != 0) {
            i = 1;
        }
        return i + i2;
    }

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        b bVar;
        if (view == null || view.getTag() == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.v2_new_academy_instruction_cview, null);
            b bVar2 = new b(this);
            bVar2.a.a = (RelativeLayout) view.findViewById(R.id.cvi);
            bVar2.b.a = (RelativeLayout) view.findViewById(R.id.cvj);
            bVar2.c.a = (RelativeLayout) view.findViewById(R.id.cvk);
            bVar2.d.a = (RelativeLayout) view.findViewById(R.id.cvl);
            bVar2.e.a = (RelativeLayout) view.findViewById(R.id.cvm);
            bVar2.a.a(bVar2.a.a);
            bVar2.b.a(bVar2.b.a);
            bVar2.c.a(bVar2.c.a);
            bVar2.d.a(bVar2.d.a);
            bVar2.e.a(bVar2.e.a);
            view.setTag(bVar2);
            bVar = bVar2;
        } else {
            bVar = (b) view.getTag();
        }
        bVar.a(i, this.e);
        return view;
    }

    private DocInfo e(int i) {
        if (this.a.size() <= i) {
            return null;
        }
        return (DocInfo) this.a.get(i);
    }
}
