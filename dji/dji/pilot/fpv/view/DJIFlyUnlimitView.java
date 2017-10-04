package dji.pilot.fpv.view;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.dji.frame.c.l;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.flyunlimit.b;
import dji.pilot.flyunlimit.b.c;
import dji.pilot.flyunlimit.jsonbean.DJIFlyUnlimitArea;
import dji.pilot.publics.objects.DJIGlobalService;
import dji.pilot.publics.widget.DJISwitch;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.util.List;

public class DJIFlyUnlimitView extends DJILinearLayout implements dji.pilot.fpv.view.DJIStageView.a {
    private b a;
    private DJIRelativeLayout b;
    private DJISwitch c;
    private ListView d;
    private c e;
    private d f;
    private Runnable g;
    private a h;

    private class a extends BaseAdapter {
        final /* synthetic */ DJIFlyUnlimitView a;

        private a(DJIFlyUnlimitView dJIFlyUnlimitView) {
            this.a = dJIFlyUnlimitView;
        }

        public int getCount() {
            if (this.a.a == null || this.a.a.a() == null) {
                return 0;
            }
            return this.a.a.a().size();
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(this.a.getContext()).inflate(R.layout.fpv_fly_unlimit_list_item, null);
            }
            if (!(this.a.a == null || this.a.a.a() == null || i >= this.a.a.a().size())) {
                DJITextView dJITextView = (DJITextView) view.findViewById(R.id.a1_);
                DJIFlyUnlimitArea dJIFlyUnlimitArea = (DJIFlyUnlimitArea) this.a.a.a().get(i);
                ((DJITextView) view.findViewById(R.id.a19)).setText(dJIFlyUnlimitArea.name);
                dJITextView.setText(dJIFlyUnlimitArea.create_at);
            }
            return view;
        }
    }

    public DJIFlyUnlimitView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = new c(this) {
            final /* synthetic */ DJIFlyUnlimitView a;

            {
                this.a = r1;
            }

            public void a(final boolean z) {
                this.a.c.post(new Runnable(this) {
                    final /* synthetic */ AnonymousClass9 b;

                    public void run() {
                        this.b.a.c.setChecked(z);
                    }
                });
            }
        };
        this.f = new d(this) {
            final /* synthetic */ DJIFlyUnlimitView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a((int) R.string.fpv_fly_unlimit_success);
                this.a.a(true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.a((int) R.string.fpv_fly_unlimit_error_send_cmd_failed);
            }
        };
        this.g = new Runnable(this) {
            final /* synthetic */ DJIFlyUnlimitView a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.b.setEnabled(true);
            }
        };
        this.h = null;
        this.a = b.getInstance(getContext());
    }

    public void dispatchOnStart() {
    }

    public void dispatchOnStop() {
        this.a.b(null);
    }

    public void dispatchOnResume() {
        this.a.b(this.e);
        if (!dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().a(this);
        }
    }

    public void dispatchOnPause() {
        this.a.b(null);
        if (dji.thirdparty.a.c.a().c(this)) {
            dji.thirdparty.a.c.a().d(this);
        }
    }

    public View getView() {
        return this;
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        if (dataOsdGetPushCommon.groundOrSky() == 2) {
            this.c.setEnabled(false);
            this.d.setEnabled(false);
            return;
        }
        this.c.setEnabled(true);
        this.d.setEnabled(true);
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            ((DJITextView) findViewById(R.id.a1a)).setText(DJIGlobalService.f);
            this.h = new a();
            this.d = (ListView) findViewById(R.id.a1e);
            this.d.setAdapter(this.h);
            this.d.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ DJIFlyUnlimitView a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long j) {
                    final List a = this.a.a.a();
                    if (a != null && a.size() > i) {
                        Builder builder = new Builder(this.a.getContext());
                        builder.setMessage(R.string.fpv_fly_unlimit_dialog_desc);
                        builder.setTitle(R.string.fpv_fly_unlimit_dialog_title);
                        builder.setPositiveButton(R.string.fpv_fly_unlimit_dialog_positive_btn, new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass1 c;

                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                List pilotSN = ((DJIFlyUnlimitArea) a.get(i)).getPilotSN();
                                if (pilotSN == null || pilotSN.isEmpty()) {
                                    this.c.a.a.a(i, new c(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void a(boolean z) {
                                            if (z) {
                                                List pilotSN = ((DJIFlyUnlimitArea) a.get(i)).getPilotSN();
                                                Object obj = null;
                                                int size = pilotSN.size() - 1;
                                                while (size >= 0) {
                                                    if (((String) pilotSN.get(size)).compareTo(DJIGlobalService.f) == 0 && !l.a((String) ((DJIFlyUnlimitArea) a.get(i)).getUnlimitData().get(size))) {
                                                        obj = 1;
                                                        this.a.c.a.a.a((String) ((DJIFlyUnlimitArea) a.get(i)).getUnlimitData().get(size), this.a.c.a.f);
                                                    }
                                                    size--;
                                                    obj = obj;
                                                }
                                                if (obj == null) {
                                                    this.a.c.a.a((int) R.string.fpv_fly_unlimit_error_invalid_license);
                                                    return;
                                                }
                                                return;
                                            }
                                            this.a.c.a.a((int) R.string.fpv_fly_unlimit_error_license_empty);
                                        }
                                    });
                                    return;
                                }
                                Object obj = null;
                                int size = pilotSN.size() - 1;
                                while (size >= 0) {
                                    if (((String) pilotSN.get(size)).compareTo(DJIGlobalService.f) == 0 && !l.a((String) ((DJIFlyUnlimitArea) a.get(i)).getUnlimitData().get(size))) {
                                        obj = 1;
                                        this.c.a.a.a((String) ((DJIFlyUnlimitArea) a.get(i)).getUnlimitData().get(size), this.c.a.f);
                                    }
                                    size--;
                                    obj = obj;
                                }
                                if (obj == null) {
                                    this.c.a.a((int) R.string.fpv_fly_unlimit_error_invalid_license);
                                }
                            }
                        });
                        builder.setNegativeButton(R.string.fpv_fly_unlimit_dialog_negative_btn, new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.create().show();
                    }
                }
            });
            this.b = (DJIRelativeLayout) findViewById(R.id.a1d);
            if (this.a.a() == null || this.a.a().size() == 0) {
                this.b.setEnabled(false);
                this.a.a(new c(this) {
                    final /* synthetic */ DJIFlyUnlimitView a;

                    {
                        this.a = r1;
                    }

                    public void a(boolean z) {
                        if (z) {
                            this.a.h.notifyDataSetChanged();
                        }
                        this.a.b.post(this.a.g);
                    }
                });
            }
            this.b.setOnClickListener(new View.OnClickListener(this) {
                final /* synthetic */ DJIFlyUnlimitView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.b.setEnabled(false);
                    this.a.a.a(new c(this) {
                        final /* synthetic */ AnonymousClass4 a;

                        {
                            this.a = r1;
                        }

                        public void a(boolean z) {
                            if (z) {
                                this.a.a.h.notifyDataSetChanged();
                            }
                            this.a.a.b.post(this.a.a.g);
                        }
                    });
                }
            });
            this.c = (DJISwitch) findViewById(R.id.a1c);
            this.c.setOnClickListener(new View.OnClickListener(this) {
                final /* synthetic */ DJIFlyUnlimitView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.a(this.a.c.isChecked());
                }
            });
        }
    }

    private void a(final int i) {
        this.b.post(new Runnable(this) {
            final /* synthetic */ DJIFlyUnlimitView b;

            public void run() {
                Builder builder = new Builder(this.b.getContext());
                builder.setMessage(i);
                builder.setTitle(R.string.fpv_fly_unlimit_dialog_title);
                builder.setPositiveButton(R.string.fpv_fly_unlimit_dialog_positive_btn, new OnClickListener(this) {
                    final /* synthetic */ AnonymousClass6 a;

                    {
                        this.a = r1;
                    }

                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.create().show();
            }
        });
    }

    private void a(boolean z) {
        b(false);
        this.a.a(z, new d(this) {
            final /* synthetic */ DJIFlyUnlimitView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.b(true);
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.b(true);
            }
        });
    }

    private void b(final boolean z) {
        this.c.post(new Runnable(this) {
            final /* synthetic */ DJIFlyUnlimitView b;

            public void run() {
                this.b.c.setEnabled(z);
            }
        });
    }
}
