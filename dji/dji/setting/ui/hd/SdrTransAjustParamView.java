package dji.setting.ui.hd;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataOsdSetSdrAssitantRead;
import dji.midware.data.model.P3.DataOsdSetSdrAssitantRead.SdrCpuType;
import dji.midware.data.model.P3.DataOsdSetSdrAssitantRead.SdrDataType;
import dji.midware.data.model.P3.DataOsdSetSdrAssitantRead.SdrDeviceType;
import dji.midware.data.model.P3.DataOsdSetSdrAssitantWrite;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.ItemViewButtonBig;

public class SdrTransAjustParamView extends ItemViewButtonBig {
    private EditText a;
    private EditText b;

    public SdrTransAjustParamView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a() {
        if (a.d()) {
            setVisibility(8);
        } else {
            setVisibility(8);
        }
    }

    public void onEventMainThread(ProductType productType) {
        a();
    }

    public void onClick(View view) {
        b();
    }

    private void b() {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(getContext()).inflate(R.layout.setting_hd_assitant_dlg_view, null);
        final Dialog create = new Builder(getContext()).create();
        create.show();
        create.getWindow().setContentView(linearLayout);
        create.getWindow().clearFlags(131080);
        create.getWindow().setSoftInputMode(4);
        LayoutParams attributes = create.getWindow().getAttributes();
        attributes.flags |= 32;
        create.getWindow().setAttributes(attributes);
        create.setCancelable(false);
        final RadioButton radioButton = (RadioButton) linearLayout.findViewById(R.id.fpv_hd_assitant_rc_radio);
        final RadioButton radioButton2 = (RadioButton) linearLayout.findViewById(R.id.fpv_hd_assitant_uav_radio);
        final RadioButton radioButton3 = (RadioButton) linearLayout.findViewById(R.id.fpv_hd_assitant_cpu_cpa7_radio);
        final RadioButton radioButton4 = (RadioButton) linearLayout.findViewById(R.id.fpv_hd_assitant_cpu_1643_radio);
        final RadioButton radioButton5 = (RadioButton) linearLayout.findViewById(R.id.fpv_hd_assitant_cpu_4210_radio);
        final RadioButton radioButton6 = (RadioButton) linearLayout.findViewById(R.id.fpv_hd_assitant_cpu_ap_radio);
        final RadioButton radioButton7 = (RadioButton) linearLayout.findViewById(R.id.fpv_hd_assitant_data_int_radio);
        final RadioButton radioButton8 = (RadioButton) linearLayout.findViewById(R.id.fpv_hd_assitant_data_short_radio);
        final RadioButton radioButton9 = (RadioButton) linearLayout.findViewById(R.id.fpv_hd_assitant_data_byte_radio);
        this.a = (EditText) linearLayout.findViewById(R.id.fpv_hd_assitant_addr_et);
        this.b = (EditText) linearLayout.findViewById(R.id.fpv_hd_assitant_value_et);
        ((Button) linearLayout.findViewById(R.id.setting_hd_assitant_read_tv)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SdrTransAjustParamView j;

            public void onClick(View view) {
                int parseLong;
                int i = 0;
                String str = "";
                try {
                    parseLong = (int) Long.parseLong(this.j.a.getText().toString(), 16);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    parseLong = -1;
                }
                if (parseLong != -1) {
                    int i2;
                    int i3;
                    if (radioButton.isChecked()) {
                        i2 = 1;
                    } else if (radioButton2.isChecked()) {
                        i2 = 0;
                    } else {
                        i2 = 0;
                    }
                    if (radioButton3.isChecked()) {
                        i3 = 0;
                    } else if (radioButton4.isChecked()) {
                        i3 = 1;
                    } else if (radioButton5.isChecked()) {
                        i3 = 2;
                    } else if (radioButton6.isChecked()) {
                        i3 = 3;
                    } else {
                        i3 = 0;
                    }
                    if (!radioButton7.isChecked()) {
                        if (radioButton8.isChecked()) {
                            i = 1;
                        } else if (radioButton9.isChecked()) {
                            i = 2;
                        }
                    }
                    DataOsdSetSdrAssitantRead.getInstance().a(SdrDeviceType.find(i2));
                    DataOsdSetSdrAssitantRead.getInstance().a(SdrCpuType.find(i3));
                    DataOsdSetSdrAssitantRead.getInstance().a(SdrDataType.find(i));
                    DataOsdSetSdrAssitantRead.getInstance().a(parseLong);
                    DataOsdSetSdrAssitantRead.getInstance().start(new d(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onSuccess(Object obj) {
                            DJILogHelper.getInstance().LOGE("", "*******Sdr Assitant Read success ,value = " + DataOsdSetSdrAssitantRead.getInstance().a(), false, true);
                            this.a.j.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    this.a.a.j.b.setText("" + DataOsdSetSdrAssitantRead.getInstance().a());
                                }
                            });
                        }

                        public void onFailure(a aVar) {
                            DJILogHelper.getInstance().LOGE("", "*******Sdr Assitant Read fail", false, true);
                        }
                    });
                }
            }
        });
        ((Button) linearLayout.findViewById(R.id.setting_hd_assitant_write_tv)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SdrTransAjustParamView j;

            public void onClick(View view) {
                int parseLong;
                int i = 0;
                String str = "";
                try {
                    parseLong = (int) Long.parseLong(this.j.a.getText().toString(), 16);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    parseLong = -1;
                }
                if (parseLong != -1) {
                    int parseLong2;
                    str = "";
                    try {
                        parseLong2 = (int) Long.parseLong(this.j.b.getText().toString(), 10);
                    } catch (NumberFormatException e2) {
                        e2.printStackTrace();
                        parseLong2 = -1;
                    }
                    if (parseLong2 != -1) {
                        int i2;
                        int i3;
                        if (radioButton.isChecked()) {
                            i2 = 1;
                        } else if (radioButton2.isChecked()) {
                            i2 = 0;
                        } else {
                            i2 = 0;
                        }
                        if (radioButton3.isChecked()) {
                            i3 = 0;
                        } else if (radioButton4.isChecked()) {
                            i3 = 1;
                        } else if (radioButton5.isChecked()) {
                            i3 = 2;
                        } else if (radioButton6.isChecked()) {
                            i3 = 3;
                        } else {
                            i3 = 0;
                        }
                        if (!radioButton7.isChecked()) {
                            if (radioButton8.isChecked()) {
                                i = 1;
                            } else if (radioButton9.isChecked()) {
                                i = 2;
                            }
                        }
                        DataOsdSetSdrAssitantWrite.getInstance().a(SdrDeviceType.find(i2));
                        DataOsdSetSdrAssitantWrite.getInstance().a(SdrCpuType.find(i3));
                        DataOsdSetSdrAssitantWrite.getInstance().a(SdrDataType.find(i));
                        DataOsdSetSdrAssitantWrite.getInstance().a(parseLong);
                        DataOsdSetSdrAssitantWrite.getInstance().b(parseLong2);
                        DataOsdSetSdrAssitantWrite.getInstance().start(new d(this) {
                            final /* synthetic */ AnonymousClass2 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                                DJILogHelper.getInstance().LOGE("", "*******Sdr Assitant write success ,result = " + DataOsdSetSdrAssitantWrite.getInstance().d(), false, true);
                            }

                            public void onFailure(a aVar) {
                                DJILogHelper.getInstance().LOGE("", "*******Sdr Assitant write fail", false, true);
                            }
                        });
                    }
                }
            }
        });
        ((ImageView) linearLayout.findViewById(R.id.dlg_titlebar_close_img)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SdrTransAjustParamView b;

            public void onClick(View view) {
                Log.e("", "assitant close in");
                create.dismiss();
            }
        });
    }
}
