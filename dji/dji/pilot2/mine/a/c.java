package dji.pilot2.mine.a;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.publics.objects.DJINetWorkReceiver;
import dji.pilot.usercenter.b.f;
import dji.pilot.visual.a.d;
import dji.pilot2.media.activity.DJIVideoPreveiwActivity;
import dji.pilot2.media.activity.DraftPhotoPreviewActivity;
import dji.pilot2.share.activity.DJIShareActivity;
import dji.pilot2.share.d.b;
import dji.pilot2.utils.l;
import dji.pilot2.widget.RoundProgressBar;
import java.io.File;

public class c extends BaseAdapter {
    b a = new b(this.b);
    private Context b;

    public class a {
        public dji.pilot2.mine.e.b a;
        public int b;
        public ImageView c;
        public RoundProgressBar d;
        public TextView e;
        public TextView f;
        public TextView g;
        public TextView h;
        public ImageView i;
        public ImageView j;
        public ImageView k;
        public ImageView l;
        public ImageView m;
        public View n;
        public View o;
        final /* synthetic */ c p;

        public a(c cVar) {
            this.p = cVar;
        }

        public void a() {
            this.d.setRoundWidth(3.0f);
            this.d.setProgress(this.a.h());
            this.d.setVisibility(0);
            this.i.setVisibility(4);
            this.j.setVisibility(0);
            this.j.setAlpha(1.0f);
            this.k.setVisibility(4);
            this.l.setVisibility(4);
            this.m.setVisibility(4);
            this.n.setVisibility(0);
            this.h.setVisibility(8);
            this.g.setVisibility(8);
        }

        public void b() {
            this.d.setRoundWidth(3.0f);
            this.d.setVisibility(4);
            this.d.setProgress(0);
            this.i.setVisibility(0);
            this.j.setVisibility(4);
            this.k.setVisibility(0);
            this.m.setVisibility(4);
            this.n.setVisibility(4);
            if (this.a.d().equals("video")) {
                this.l.setVisibility(0);
            } else {
                this.l.setVisibility(4);
            }
            this.h.setVisibility(8);
            this.g.setVisibility(8);
        }

        public void c() {
            this.d.setRoundWidth(3.0f);
            this.d.setVisibility(4);
            this.d.setProgress(0);
            this.i.setVisibility(4);
            this.j.setVisibility(4);
            this.k.setVisibility(0);
            this.m.setVisibility(0);
            this.n.setVisibility(4);
            if (this.a.d().equals("video")) {
                this.l.setVisibility(0);
            } else {
                this.l.setVisibility(4);
            }
            this.h.setVisibility(8);
            this.g.setVisibility(0);
        }

        public void d() {
            this.d.setRoundWidth(3.0f);
            this.d.setVisibility(4);
            this.d.setProgress(0);
            this.i.setVisibility(0);
            this.j.setVisibility(4);
            this.k.setVisibility(0);
            this.m.setVisibility(4);
            this.n.setVisibility(4);
            if (this.a.d().equals("video")) {
                this.l.setVisibility(0);
            } else {
                this.l.setVisibility(4);
            }
            this.h.setVisibility(0);
            this.g.setVisibility(8);
        }

        public void e() {
            this.d.setVisibility(0);
            this.d.setProgress(this.a.h());
            this.i.setVisibility(4);
            this.j.setVisibility(0);
            this.j.setAlpha(d.c);
            this.k.setVisibility(4);
            this.m.setVisibility(4);
            this.n.setVisibility(0);
            this.l.setVisibility(4);
            this.h.setVisibility(8);
            this.g.setVisibility(8);
        }

        public void f() {
            this.d.setVisibility(4);
            this.i.setVisibility(4);
            this.j.setVisibility(4);
            this.k.setVisibility(4);
            this.m.setVisibility(4);
            this.n.setVisibility(4);
            this.l.setVisibility(4);
            this.h.setVisibility(8);
            this.g.setVisibility(8);
        }

        public void a(dji.pilot2.mine.e.b bVar) {
            if (!(this.a == null || this.a == bVar)) {
                dji.pilot2.mine.b.c.getInstance().d(this.a.e());
            }
            if (this.a != bVar) {
                this.a = bVar;
                dji.pilot2.mine.b.c.getInstance().a(this.a.e(), this);
            }
            if (this.a.g() == 2) {
                a();
            } else if (this.a.g() == 1) {
                b();
            } else if (this.a.g() == 4) {
                c();
            } else if (this.a.g() == 16) {
                d();
            } else if (this.a.g() == 32) {
                e();
            } else {
                f();
            }
            if (this.a.k() == 0) {
                File file = new File(this.a.e());
                if (file.exists()) {
                    this.a.a(file.lastModified());
                    dji.pilot2.mine.b.c.getInstance().b(this.a);
                }
            }
            this.f.setText(this.a.j());
            if (this.a.d().equals("video")) {
                this.e.setText(MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.a.l());
                this.e.setVisibility(0);
            } else {
                this.e.setText("");
                this.e.setVisibility(8);
            }
            if (this.a.d().equals("video")) {
                this.l.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.a.e() != null) {
                            File file = new File(this.a.a.e());
                            if (file != null && file.exists()) {
                                DJIVideoPreveiwActivity.a(this.a.p.b, this.a.a.e(), dji.pilot.publics.objects.a.a);
                                return;
                            } else if (file.getName().length() > 5) {
                                File file2 = new File(this.a.a.e().replace(file.getName(), "") + file.getName().substring(1, file.getName().length() - 4));
                                if (file2 == null || !file2.exists()) {
                                    Toast.makeText(this.a.p.b, R.string.mine_draft_file_not_found, 0).show();
                                    return;
                                } else {
                                    DJIVideoPreveiwActivity.a(this.a.p.b, file2.getPath(), dji.pilot.publics.objects.a.a);
                                    return;
                                }
                            } else {
                                Toast.makeText(this.a.p.b, R.string.mine_draft_file_not_found, 0).show();
                                return;
                            }
                        }
                        Toast.makeText(this.a.p.b, R.string.mine_draft_file_not_found, 0).show();
                    }
                });
            } else {
                this.l.setClickable(false);
            }
            if (this.a.d().equals("photo")) {
                this.c.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        if (this.a.a.e() != null) {
                            File file = new File(this.a.a.e());
                            if (file == null || !file.exists()) {
                                Toast.makeText(this.a.p.b, R.string.mine_draft_file_not_found, 0).show();
                                return;
                            }
                            Intent intent = new Intent();
                            intent.setClass(this.a.p.b, DraftPhotoPreviewActivity.class);
                            intent.putExtra(DraftPhotoPreviewActivity.a, this.a.a.e());
                            this.a.p.b.startActivity(intent);
                            return;
                        }
                        Toast.makeText(this.a.p.b, R.string.mine_draft_file_not_found, 0).show();
                    }
                });
            } else {
                this.c.setClickable(false);
            }
            this.c.setImageBitmap(null);
            this.c.setBackgroundResource(R.drawable.v2_photo_defalut);
            String n = this.a.n();
            if (n != null && new File(n).exists()) {
                com.dji.frame.c.c.a(this.p.b).a(this.c, this.a.n());
            }
            this.i.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!new File(this.a.a.e()).exists()) {
                        return;
                    }
                    if (DJINetWorkReceiver.a(this.a.p.b)) {
                        Intent intent = new Intent(this.a.p.b, DJIShareActivity.class);
                        intent.putExtra("file_path", this.a.a.e());
                        intent.putExtra(DJIShareActivity.N, this.a.a.b());
                        intent.putExtra(DJIShareActivity.M, this.a.a.c());
                        intent.putExtra(DJIShareActivity.O, this.a.a.p());
                        intent.putExtra(DJIShareActivity.P, true);
                        if (this.a.a.d().compareTo("video") == 0) {
                            intent.putExtra(DJIShareActivity.L, 2);
                        } else {
                            intent.putExtra(DJIShareActivity.L, 1);
                        }
                        this.a.p.b.startActivity(intent);
                        return;
                    }
                    Builder bVar = new dji.pilot2.publics.object.b(this.a.p.b);
                    bVar.setMessage(R.string.rcupgrade_nonet_tip);
                    bVar.setPositiveButton(17039379, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent("android.settings.WIFI_SETTINGS");
                            if (this.a.a.p.b instanceof Activity) {
                                this.a.a.p.b.startActivity(intent);
                            }
                        }
                    });
                    bVar.setNegativeButton(17039369, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    bVar.show();
                }
            });
            if (this.a.g() == 2) {
                this.j.setClickable(true);
                this.j.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onClick(View view) {
                        Builder bVar = new dji.pilot2.publics.object.b(this.a.p.b);
                        DJILogHelper.getInstance().LOGI("Lyric", "stop pressed");
                        bVar.setMessage(R.string.mine_draft_confirm_stop_upload);
                        bVar.setPositiveButton(17039379, new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                dji.pilot2.c.b.b a = dji.pilot2.c.b.a.getInstance().a(this.a.a.a.e());
                                if (a != null) {
                                    dji.pilot2.c.b.a.getInstance().b(a);
                                }
                            }
                        });
                        bVar.setNegativeButton(17039369, new DialogInterface.OnClickListener(this) {
                            final /* synthetic */ AnonymousClass4 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        });
                        bVar.show();
                    }
                });
            } else {
                this.j.setOnClickListener(null);
                this.j.setClickable(false);
            }
            this.k.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Builder bVar = new dji.pilot2.publics.object.b(this.a.p.b);
                    bVar.setMessage(R.string.mine_draft_confirm_delete);
                    bVar.setNeutralButton(R.string.mine_draft_only_delete_task, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dji.pilot2.mine.b.c.getInstance().c(this.a.a.a);
                            this.a.a.a.s();
                            this.a.a.a.r();
                            this.a.a.a.a(this.a.a.p.b);
                            l.getInstance().a(this.a.a.a.e(), true);
                            if (dji.pilot2.mine.b.c.getInstance().b() == 0 && (this.a.a.p.b instanceof Activity)) {
                                ((Activity) this.a.a.p.b).finish();
                            }
                        }
                    });
                    bVar.setPositiveButton(R.string.mine_draft_delete_task_and_file, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                            dji.pilot2.mine.b.c.getInstance().c(this.a.a.a);
                            this.a.a.a.q();
                            this.a.a.a.s();
                            this.a.a.a.r();
                            this.a.a.a.a(this.a.a.p.b);
                            l.getInstance().a(this.a.a.a.e(), true);
                            if (dji.pilot2.mine.b.c.getInstance().b() == 0 && (this.a.a.p.b instanceof Activity)) {
                                ((Activity) this.a.a.p.b).finish();
                            }
                        }
                    });
                    bVar.setNegativeButton(17039369, new DialogInterface.OnClickListener(this) {
                        final /* synthetic */ AnonymousClass5 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    bVar.show();
                }
            });
            this.m.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    dji.pilot2.mine.e.a.a aVar = new dji.pilot2.mine.e.a.a();
                    if (f.getInstance().c()) {
                        aVar.b = this.a.a.f() + "?account_id=" + f.getInstance().i();
                    } else {
                        aVar.b = this.a.a.f();
                    }
                    aVar.c = this.a.a.b();
                    aVar.d = this.a.a.c();
                    aVar.f = this.a.a.n();
                    dji.pilot2.share.b.b bVar = new dji.pilot2.share.b.b(this.a.p.b);
                    if (this.a.a.d().equals("photo")) {
                        bVar.a(dji.pilot2.share.e.a.a.CONTENT_IMG);
                    } else if (this.a.a.d().equals("video")) {
                        bVar.a(dji.pilot2.share.e.a.a.CONTENT_VIDEO);
                    }
                    bVar.a(dji.pilot2.share.b.b.a.EDIT_UPLOAD);
                    bVar.a(aVar);
                    bVar.a(aVar.b());
                    bVar.show();
                }
            });
        }
    }

    public c(Context context) {
        this.b = context;
    }

    public int getCount() {
        return dji.pilot2.mine.b.c.getInstance().b();
    }

    public Object getItem(int i) {
        return dji.pilot2.mine.b.c.getInstance().b(i);
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int i) {
        return false;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = LayoutInflater.from(this.b).inflate(R.layout.v2_draft_list_item, null);
            a aVar2 = new a(this);
            view.setTag(aVar2);
            aVar = aVar2;
        } else {
            aVar = (a) view.getTag();
        }
        aVar.b = i;
        aVar.c = (ImageView) view.findViewById(R.id.ck4);
        aVar.d = (RoundProgressBar) view.findViewById(R.id.ck6);
        aVar.e = (TextView) view.findViewById(R.id.ck_);
        aVar.f = (TextView) view.findViewById(R.id.ck9);
        aVar.g = (TextView) view.findViewById(R.id.cka);
        aVar.h = (TextView) view.findViewById(R.id.ckb);
        aVar.i = (ImageView) view.findViewById(R.id.ckc);
        aVar.j = (ImageView) view.findViewById(R.id.ckf);
        aVar.k = (ImageView) view.findViewById(R.id.cke);
        aVar.l = (ImageView) view.findViewById(R.id.ck7);
        aVar.m = (ImageView) view.findViewById(R.id.ckd);
        aVar.n = view.findViewById(R.id.ck5);
        aVar.o = view.findViewById(R.id.ckg);
        if (i == getCount() - 1) {
            aVar.o.setVisibility(8);
        } else {
            aVar.o.setVisibility(0);
        }
        aVar.a((dji.pilot2.mine.e.b) getItem(i));
        return view;
    }
}
