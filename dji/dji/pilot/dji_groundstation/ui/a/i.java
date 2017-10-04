package dji.pilot.dji_groundstation.ui.a;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import dji.pilot.dji_groundstation.R;
import dji.pilot.dji_groundstation.a.a;
import dji.pilot.dji_groundstation.a.d$b;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.a.f;
import dji.pilot.dji_groundstation.a.g;
import dji.pilot.dji_groundstation.controller.DataMgr.e;
import dji.pilot.dji_groundstation.controller.b;
import dji.pilot.dji_groundstation.controller.f.d;
import dji.pilot.dji_groundstation.controller.h;
import dji.pilot.dji_groundstation.ui.views.AddViewWithAnimLayout;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;

public class i extends e implements b, h {
    private static final String a = "SmartModeDialog";
    private DJIRelativeLayout g = null;
    private AddViewWithAnimLayout h = null;
    private AddViewWithAnimLayout i = null;
    private DJITextView j = null;
    private DJIImageView k = null;
    private DJIImageView l = null;
    private DJITextView m = null;
    private DJITextView n = null;
    private String o = "";

    public i(Context context) {
        super(context);
        setContentView(R.layout.layout_smartmodedialog);
        setCancelable(false);
        this.g = (DJIRelativeLayout) findViewById(R.id.content_title_container);
        this.h = (AddViewWithAnimLayout) findViewById(R.id.dialog_container);
        this.i = (AddViewWithAnimLayout) findViewById(R.id.bottom_btn_container);
        this.j = (DJITextView) findViewById(R.id.smartmode_title);
        this.k = (DJIImageView) findViewById(R.id.mode_topleft_img);
        this.l = (DJIImageView) findViewById(R.id.mode_topright_img);
        this.m = (DJITextView) findViewById(R.id.mode_topleft_text);
        this.n = (DJITextView) findViewById(R.id.mode_topright_text);
    }

    public void a(View view) {
        if (view != null) {
            new LayoutParams(-1, -1).addRule(13);
            this.h.addView(view);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        f();
    }

    public void a(d dVar) {
        if (dVar != null && dVar.b > 0) {
            View inflate = LayoutInflater.from(getContext()).inflate(dVar.b, null);
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            if (!(dVar.a.equals("waypoint_collection") || dVar.a.equals("waypoint_setting"))) {
                layoutParams.leftMargin = (int) f.a(15.0d, getContext());
                layoutParams.rightMargin = (int) f.a(15.0d, getContext());
            }
            this.h.addView(inflate, layoutParams);
        }
    }

    public void a(int i) {
        if (this.j != null && i > 0) {
            this.j.setText(getContext().getResources().getString(i));
        }
    }

    public void b(int i) {
        if (this.k != null) {
            this.k.setImageResource(i);
        }
    }

    public void c(int i) {
        if (this.l != null) {
            this.l.setImageResource(i);
        }
    }

    private void a() {
        if (this.h != null) {
            this.h.removeAllViews();
        }
        if (this.i != null) {
            this.i.removeAllViews();
        }
        f();
    }

    private void b() {
        if (dji.pilot.dji_groundstation.controller.d.getInstance().b() != c.k || e.getInstance().v() <= 0) {
            dji.pilot.dji_groundstation.controller.e.a(this.o, getContext());
            return;
        }
        a aVar = new a();
        aVar.a = 0;
        aVar.f = 0;
        aVar.b = R.string.gsnew_gs_way_point_add_point_small_back_confirm;
        aVar.h = R.string.gsnew_gs_exix_current_mession_cancel;
        aVar.j = R.string.gsnew_gs_exix_current_mession_ok;
        aVar.i = "gs://smartmode/back";
        aVar.d = 250;
        aVar.e = 270;
        aVar.k = false;
        dji.pilot.dji_groundstation.controller.d.getInstance().a(g.EVENT_SMARTMODE_WAYPOINT_NOTICE_TO_CONFIRM_DIALOG, aVar);
    }

    private void b(final d dVar) {
        if (dVar != null) {
            WindowManager.LayoutParams attributes = getWindow().getAttributes();
            attributes.width = (int) dVar.o;
            attributes.height = (int) dVar.p;
            attributes.y = dVar.n;
            attributes.x = dVar.m;
            attributes.gravity = dVar.q;
            this.o = dVar.l;
            a(dVar.c);
            if (dVar.r) {
                e();
            } else {
                d();
            }
            if (dVar.d > 0) {
                this.k.setVisibility(0);
                this.m.setVisibility(8);
                b(dVar.d);
                this.k.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ i b;

                    public void onClick(View view) {
                        if (dVar.d == R.drawable.gs_left_arrow_icon) {
                            this.b.b();
                        } else {
                            dji.pilot.dji_groundstation.controller.e.a(dVar.g, this.b.getContext());
                        }
                    }
                });
            } else if (dVar.e > 0) {
                this.k.setVisibility(8);
                this.m.setVisibility(0);
                this.m.setText(dVar.e);
                this.m.setTextColor(Color.parseColor(dVar.f));
                this.m.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ i b;

                    public void onClick(View view) {
                        dji.pilot.dji_groundstation.controller.e.a(dVar.g, this.b.getContext());
                    }
                });
            } else {
                this.k.setVisibility(4);
                this.m.setVisibility(8);
            }
            if (dVar.h > 0) {
                this.l.setVisibility(0);
                this.n.setVisibility(8);
                c(dVar.h);
                this.l.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ i b;

                    public void onClick(View view) {
                        dji.pilot.dji_groundstation.controller.e.a(dVar.k, this.b.getContext());
                    }
                });
            } else if (dVar.i > 0) {
                this.l.setVisibility(8);
                this.n.setVisibility(0);
                this.n.setText(dVar.i);
                this.n.setTextColor(Color.parseColor(dVar.j));
                this.n.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ i b;

                    public void onClick(View view) {
                        dji.pilot.dji_groundstation.controller.e.a(dVar.k, this.b.getContext());
                    }
                });
            } else {
                this.l.setVisibility(4);
                this.n.setVisibility(8);
            }
            a(dVar);
            View dJILinearLayout = new DJILinearLayout(getContext());
            dJILinearLayout.setOrientation(0);
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -1);
            layoutParams2.weight = 1.0f;
            for (int i = 0; i < dVar.s.size(); i++) {
                final dji.pilot.dji_groundstation.controller.f.c cVar = (dji.pilot.dji_groundstation.controller.f.c) dVar.s.get(i);
                if (cVar != null) {
                    View textView = new TextView(getContext());
                    textView.setClickable(true);
                    textView.setGravity(17);
                    if (cVar.b.toString().trim().isEmpty()) {
                        textView.setTextColor(-1);
                    } else {
                        textView.setTextColor(Color.parseColor(cVar.b.toString()));
                    }
                    if (cVar.c.toString().trim().isEmpty()) {
                        textView.setBackground(getContext().getResources().getDrawable(R.drawable.gs_content_dialog_btn_bkg_new));
                    } else {
                        textView.setBackgroundColor(Color.parseColor(cVar.c.toString()));
                    }
                    textView.setText(cVar.a);
                    textView.setTextSize(1, 12.0f);
                    textView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ i b;

                        public void onClick(View view) {
                            dji.pilot.dji_groundstation.controller.e.a(cVar.d, this.b.getContext());
                        }
                    });
                    dJILinearLayout.addView(textView, layoutParams2);
                    if (i != dVar.s.size() - 1) {
                        View view = new View(getContext());
                        int a = f.a(1, getContext());
                        view.setBackgroundColor(getContext().getResources().getColor(R.color.gs_split_line_color));
                        dJILinearLayout.addView(view, new LinearLayout.LayoutParams(a, -1));
                    }
                }
            }
            this.i.addView(dJILinearLayout, layoutParams);
            attributes.dimAmount = 0.0f;
            getWindow().setAttributes(attributes);
            if (dji.pilot.dji_groundstation.controller.d.getInstance().b().ordinal() != c.h.ordinal()) {
                return;
            }
            if (e.getInstance().x()) {
                this.l.setImageResource(R.drawable.gs_favorite_selected);
            } else {
                this.l.setImageResource(R.drawable.gs_favorite_unselected);
            }
        }
    }

    public void a(dji.pilot.dji_groundstation.a.b bVar, final Object obj) {
        if (bVar.a() != dji.pilot.dji_groundstation.a.b.EVENT_SMARTDIALOG_DATA_FINISH.a() || obj == null) {
            if (bVar.a() == dji.pilot.dji_groundstation.a.b.EVENT_SMARTDIALOG_DISMISS.a()) {
                dismiss();
            }
        } else if (obj instanceof d) {
            a(new Runnable(this) {
                final /* synthetic */ i b;

                public void run() {
                    this.b.a();
                    this.b.b((d) obj);
                    this.b.show();
                }
            });
        }
    }

    public void show() {
        if (!isShowing()) {
            dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 20;
            eVar.t = null;
            dji.thirdparty.a.c.a().e(eVar);
            eVar = new dji.pilot.dji_groundstation.a.e();
            eVar.s = 8;
            eVar.t = null;
            dji.thirdparty.a.c.a().e(eVar);
        }
        super.show();
        dji.pilot.dji_groundstation.controller.d.getInstance().a(dji.pilot.dji_groundstation.controller.d.getInstance().b(), 0);
    }

    public void dismiss() {
        super.dismiss();
        dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
        eVar.s = 19;
        eVar.t = null;
        dji.thirdparty.a.c.a().e(eVar);
    }

    public void hide() {
        super.hide();
        dji.pilot.dji_groundstation.a.e eVar = new dji.pilot.dji_groundstation.a.e();
        eVar.s = 19;
        eVar.t = null;
        dji.thirdparty.a.c.a().e(eVar);
    }

    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
        b();
    }

    private void b(g gVar, Object obj) {
        if (gVar != null) {
            final c a = a(gVar);
            if (a != c.a) {
                a(new Runnable(this) {
                    final /* synthetic */ i b;

                    public void run() {
                        if (dji.pilot.dji_groundstation.controller.d.getInstance().b() == a) {
                            this.b.a();
                            if (a == c.h) {
                                if (this.b.l != null) {
                                    this.b.l.setVisibility(0);
                                }
                            } else if (this.b.l != null) {
                                this.b.l.setVisibility(8);
                            }
                            dji.pilot.dji_groundstation.controller.f.getInstance(this.b.b).a(a);
                        }
                    }
                });
            }
        }
    }

    private c a(g gVar) {
        if (gVar == null) {
            return c.a;
        }
        switch (gVar) {
            case EVENT_SMARTMODE_SWITCH_POI:
                return c.b;
            case EVENT_SMARTMODE_SWITCH_POI_START:
                return c.c;
            case EVENT_SMARTMODE_SWITCH_POI_STATUS:
                return c.d;
            case EVENT_SMARTMODE_SWITCH_FOLLOWME:
                return c.m;
            case EVENT_SMARTMODE_SWITCH_FOLLOWME_STATUS:
                return c.n;
            case EVENT_SMARTMODE_SWITCH_WAYPOINT:
                return c.e;
            case EVENT_SMARTMODE_SWITCH_WAYPOINT_COLLECTION:
                return c.f;
            case EVENT_SMARTMODE_SWITCH_WAYPOINT_SETTING:
                return c.h;
            case EVENT_SMARTMODE_SWITCH_WAYPOINT_SETRETURNHOMEHEIGHT:
                return c.i;
            case EVENT_SMARTMODE_SWITCH_WAYPOINT_UPLOADMISSION:
                return c.j;
            case EVENT_SMARTMODE_SWITCH_WAYPOINT_STATUS:
                return c.g;
            case EVENT_SMARTMODE_SWITCH_WAYPOINT_PAGE_ADD_NEW:
                return c.k;
            case EVENT_SMARTMODE_SWITCH_WAYPOINT_DOWNLOAD_MISSION:
                return c.l;
            case EVENT_SMARTMODE_SWITCH_COURSELOCK:
                return c.o;
            case EVENT_SMARTMODE_SWITCH_COURSELOCK_STATUS:
                return c.p;
            case EVENT_SMARTMODE_SWITCH_HOMELOCK:
                return c.q;
            case EVENT_SMARTMODE_SWITCH_HOMELOCK_STATUS:
                return c.r;
            case EVENT_SMARTMODE_SWITCH_TERRAINTRACKING:
                return c.t;
            case EVENT_SMARTMODE_SWITCH_TERRAINTRACKING_STATUS:
                return c.u;
            case EVENT_SMARTMODE_SWITCH_TRIPOD:
                return c.v;
            default:
                return c.a;
        }
    }

    private void a(String str) {
        Builder builder = new Builder(getContext());
        builder.setMessage(str);
        builder.setPositiveButton(R.string.gsnew_btn_dlg_yes, null);
        builder.create().show();
    }

    public void a(g gVar, Object obj) {
        if (gVar.ordinal() == g.EVENT_FLIGHTMODE_SWITCH_SMART_BUT_TAKE_OFF_WARNING.ordinal()) {
            a(new Runnable(this) {
                final /* synthetic */ i a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.a();
                    dji.pilot.dji_groundstation.controller.f.getInstance(this.a.b).a(d$b.Take_Off_Warning);
                }
            });
        } else if (gVar.ordinal() == g.EVENT_SMARTMODE_SWITCH_POI_ERROR_PUSH_TO_FPV.ordinal()) {
            if (obj instanceof Bundle) {
                int i = ((Bundle) obj).getInt("contentid");
                String str = "";
                str = getContext().getString(i);
                if (i == R.string.gsnew_gs_point_of_insterest_height_limits) {
                    Object format;
                    if (DJIGenSettingDataManager.getInstance().v() == 0) {
                        format = String.format(getContext().getString(R.string.gsnew_gs_point_of_insterest_height_limits), new Object[]{Float.valueOf(f.a(5.0f)), "FT"});
                    } else {
                        format = String.format(getContext().getString(R.string.gsnew_gs_point_of_insterest_height_limits), new Object[]{Double.valueOf(5.0d), "M"});
                    }
                    dji.pilot.dji_groundstation.controller.d.getInstance().a(7, format);
                    return;
                }
                a(str);
            }
        } else if (gVar.ordinal() != g.EVENT_SMARTMODE_SWITCH_WAYPOINT_FAVORITE.ordinal()) {
            b(gVar, obj);
        } else if (this.l == null) {
        } else {
            if (e.getInstance().x()) {
                this.l.setImageResource(R.drawable.gs_favorite_selected);
                dji.pilot.dji_groundstation.controller.d.getInstance().a(8, getContext().getResources().getString(R.string.gsnew_gs_way_point_add_to_favorite));
                return;
            }
            this.l.setImageResource(R.drawable.gs_favorite_unselected);
        }
    }
}
