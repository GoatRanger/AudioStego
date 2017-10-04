package dji.device.active;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraActiveStatus;
import dji.midware.data.model.P3.DataGimbalActiveStatus;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.midware.data.model.b.a.b;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.thirdparty.a.c;

public class LonganActiveView extends LinearLayout implements OnClickListener {
    private Button a;
    private Button b;
    private TextView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private String g;
    private String h;
    private String i;

    public LonganActiveView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.active_layout, null);
        addView(inflate);
        this.a = (Button) inflate.findViewById(R.id.camera_btn);
        this.b = (Button) inflate.findViewById(R.id.ofdm_btn);
        this.e = (TextView) inflate.findViewById(R.id.camera_tv);
        this.f = (TextView) inflate.findViewById(R.id.ofdm_tv);
        this.c = (TextView) inflate.findViewById(R.id.push_camera_tv);
        this.d = (TextView) inflate.findViewById(R.id.push_ofdm_tv);
        this.a.setOnClickListener(this);
        this.b.setOnClickListener(this);
        inflate.findViewById(R.id.camera_active_btn).setOnClickListener(this);
        inflate.findViewById(R.id.ofdm_active_btn).setOnClickListener(this);
        inflate.findViewById(R.id.ofdm_request_btn).setOnClickListener(this);
        inflate.findViewById(R.id.camera_request_btn).setOnClickListener(this);
        inflate.findViewById(R.id.active_btn).setOnClickListener(this);
        inflate.findViewById(R.id.unactive_btn).setOnClickListener(this);
        if (DataOsdActiveStatus.getInstance().getRecData() != null) {
            this.d.setText("ofdm sn init: " + DataOsdActiveStatus.getInstance().getPushSN());
        }
        if (DataCameraActiveStatus.getInstance().getRecData() != null) {
            this.c.setText("camera sn init: " + DataCameraActiveStatus.getInstance().getPushSN());
        }
        DataOsdActiveStatus.getInstance().setType(b.b).start(new d(this) {
            final /* synthetic */ LonganActiveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.f.setText("ofdm sn:" + DataOsdActiveStatus.getInstance().getSN() + DataOsdActiveStatus.getInstance().getActiveStatus());
                        this.a.a.h = DataOsdActiveStatus.getInstance().getSN();
                    }
                });
            }

            public void onFailure(a aVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.f.setText("ofdm fails");
                    }
                });
            }
        });
        DataGimbalActiveStatus.getInstance().setVersion(dji.midware.data.model.b.a.a.b).setType(b.b).start(new d(this) {
            final /* synthetic */ LonganActiveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass9 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.f.setText("ofdm sn:" + DataGimbalActiveStatus.getInstance().getSN() + DataGimbalActiveStatus.getInstance().getActiveStatus());
                        this.a.a.i = DataGimbalActiveStatus.getInstance().getSN();
                    }
                });
            }

            public void onFailure(a aVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass9 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.f.setText("ofdm fails");
                    }
                });
            }
        });
        DataCameraActiveStatus.getInstance().setType(b.b).start(new d(this) {
            final /* synthetic */ LonganActiveView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.e.setText("camera sn:" + DataCameraActiveStatus.getInstance().getSN() + DataCameraActiveStatus.getInstance().getActiveStatus());
                        this.a.a.g = DataCameraActiveStatus.getInstance().getSN();
                    }
                });
            }

            public void onFailure(a aVar) {
                this.a.a(new Runnable(this) {
                    final /* synthetic */ AnonymousClass10 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.e.setText("camera fails");
                    }
                });
            }
        });
        c.a().a(this);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.camera_btn) {
            DataCameraActiveStatus.getInstance().setYear(0).setMonth(0).setType(b.c).setDay(0).setHour(0).setMin(0).setSecond(0).setSN(this.g).setActiveStatus(false).start(new d(this) {
                final /* synthetic */ LonganActiveView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a("camera reset success");
                }

                public void onFailure(a aVar) {
                    this.a.a("camera reset fails");
                }
            });
        } else if (id == R.id.ofdm_btn) {
            DataOsdActiveStatus.getInstance().setYear(0).setMonth(0).setType(b.c).setDay(0).setHour(0).setMin(0).setSecond(0).setSN(this.h).setActiveStatus(false).start(new d(this) {
                final /* synthetic */ LonganActiveView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a("ofdm reset success");
                }

                public void onFailure(a aVar) {
                    this.a.a("ofdm reset fails");
                }
            });
        } else if (id == R.id.camera_active_btn) {
            DataCameraActiveStatus.getInstance().setType(b.c).setYear(0).setMonth(0).setDay(0).setHour(0).setMin(0).setSecond(0).setSN(this.g).setActiveStatus(true).start(new d(this) {
                final /* synthetic */ LonganActiveView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a("camera reset success");
                }

                public void onFailure(a aVar) {
                    this.a.a("camera reset fails");
                }
            });
        } else if (id == R.id.ofdm_active_btn) {
            DataOsdActiveStatus.getInstance().setType(b.c).setYear(0).setMonth(0).setDay(0).setHour(0).setMin(0).setSecond(0).setSN(this.h).setActiveStatus(true).start(new d(this) {
                final /* synthetic */ LonganActiveView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a("ofdm reset success");
                }

                public void onFailure(a aVar) {
                    this.a.a("ofdm reset fails");
                }
            });
        } else if (id == R.id.ofdm_request_btn) {
            DataOsdActiveStatus.getInstance().setType(b.b).start(new d(this) {
                final /* synthetic */ LonganActiveView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass15 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.f.setText("ofdm sn:" + DataOsdActiveStatus.getInstance().getSN() + DataOsdActiveStatus.getInstance().getActiveStatus());
                            this.a.a.h = DataOsdActiveStatus.getInstance().getSN();
                        }
                    });
                }

                public void onFailure(a aVar) {
                    this.a.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass15 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.f.setText("ofdm fails");
                        }
                    });
                }
            });
        } else if (id == R.id.camera_request_btn) {
            DataCameraActiveStatus.getInstance().setType(b.b).start(new d(this) {
                final /* synthetic */ LonganActiveView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass16 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.e.setText("camera sn:" + DataCameraActiveStatus.getInstance().getSN() + DataCameraActiveStatus.getInstance().getActiveStatus());
                            this.a.a.g = DataCameraActiveStatus.getInstance().getSN();
                        }
                    });
                }

                public void onFailure(a aVar) {
                    this.a.a(new Runnable(this) {
                        final /* synthetic */ AnonymousClass16 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.e.setText("camera fails");
                        }
                    });
                }
            });
        } else if (id == R.id.active_btn) {
            if (i.getInstance().c() != ProductType.LonganMobile) {
                DataCameraActiveStatus.getInstance().setType(b.c).setYear(0).setMonth(0).setDay(0).setHour(0).setMin(0).setSecond(0).setSN(this.g).setActiveStatus(true).start(new d(this) {
                    final /* synthetic */ LonganActiveView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a("camera reset success");
                    }

                    public void onFailure(a aVar) {
                        this.a.a("camera reset fails");
                    }
                });
                DataOsdActiveStatus.getInstance().setType(b.c).setYear(0).setMonth(0).setDay(0).setHour(0).setMin(0).setSecond(0).setSN(this.h).setActiveStatus(true).start(new d(this) {
                    final /* synthetic */ LonganActiveView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a("ofdm reset success");
                    }

                    public void onFailure(a aVar) {
                        this.a.a("ofdm reset fails");
                    }
                });
                return;
            }
            DataGimbalActiveStatus.getInstance().setVersion(dji.midware.data.model.b.a.a.b).setType(b.c).setYear(0).setMonth(0).setDay(0).setHour(0).setMin(0).setSecond(0).setSN(this.i).setActiveStatus(true).start(new d(this) {
                final /* synthetic */ LonganActiveView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a("ofdm reset success");
                }

                public void onFailure(a aVar) {
                    this.a.a("ofdm reset fails");
                }
            });
        } else if (id != R.id.unactive_btn) {
        } else {
            if (i.getInstance().c() != ProductType.LonganMobile) {
                DataCameraActiveStatus.getInstance().setYear(0).setMonth(0).setType(b.c).setDay(0).setHour(0).setMin(0).setSecond(0).setSN(this.g).setActiveStatus(false).start(new d(this) {
                    final /* synthetic */ LonganActiveView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a("camera reset success");
                    }

                    public void onFailure(a aVar) {
                        this.a.a("camera reset fails");
                    }
                });
                DataOsdActiveStatus.getInstance().setYear(0).setMonth(0).setType(b.c).setDay(0).setHour(0).setMin(0).setSecond(0).setSN(this.h).setActiveStatus(false).start(new d(this) {
                    final /* synthetic */ LonganActiveView a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.a("ofdm reset success");
                    }

                    public void onFailure(a aVar) {
                        this.a.a("ofdm reset fails");
                    }
                });
                return;
            }
            DataGimbalActiveStatus.getInstance().setVersion(dji.midware.data.model.b.a.a.b).setType(b.c).setYear(0).setMonth(0).setDay(0).setHour(0).setMin(0).setSecond(0).setSN(this.i).setActiveStatus(false).start(new d(this) {
                final /* synthetic */ LonganActiveView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.a("ofdm reset success");
                }

                public void onFailure(a aVar) {
                    this.a.a("ofdm reset fails");
                }
            });
        }
    }

    private void a(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    private void a(final String str) {
        a(new Runnable(this) {
            final /* synthetic */ LonganActiveView b;

            public void run() {
                Toast.makeText(this.b.getContext(), str, 0).show();
            }
        });
    }

    public void onEventMainThread(DataOsdActiveStatus dataOsdActiveStatus) {
        this.d.setText("ofdm sn : " + dataOsdActiveStatus.getPushSN());
    }

    public void onEventMainThread(DataCameraActiveStatus dataCameraActiveStatus) {
        this.c.setText("camera sn : " + dataCameraActiveStatus.getPushSN());
    }
}
