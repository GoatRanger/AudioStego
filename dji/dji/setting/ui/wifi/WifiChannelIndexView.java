package dji.setting.ui.wifi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.meetme.android.horizontallistview.HorizontalListView;
import dji.common.airlink.WiFiFrequencyBand;
import dji.log.WM220LogUtil;
import dji.logic.c.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataWifiGetChannelList;
import dji.midware.data.model.P3.DataWifiSetModeChannel;
import dji.midware.e.d;
import dji.pilot.setting.ui.R;
import dji.setting.ui.widget.DividerLinearLayout;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.List;

public class WifiChannelIndexView extends DividerLinearLayout {
    private HorizontalListView a;
    private a b;
    private ArrayList<Integer> c = new ArrayList();
    private int[] d = new int[0];
    private int[] e = new int[0];
    private WiFiFrequencyBand f = WiFiFrequencyBand.FrequencyBand2Dot4G;
    private int g;

    private class a<T> extends ArrayAdapter<T> {
        final /* synthetic */ WifiChannelIndexView a;
        private int b = -1;

        public a(WifiChannelIndexView wifiChannelIndexView, Context context, int i, List list) {
            this.a = wifiChannelIndexView;
            super(context, i, list);
        }

        public void a(int i) {
            this.b = i;
        }

        @NonNull
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            view2.setSelected(false);
            if (i == this.b) {
                view2.setSelected(true);
            }
            return view2;
        }
    }

    public WifiChannelIndexView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    private void a() {
        int i = 0;
        dji.setting.a.a.a((View) this, R.layout.setting_ui_wifi_channel_index_view);
        if (!isInEditMode()) {
            Integer[] numArr = new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(3), Integer.valueOf(4), Integer.valueOf(5), Integer.valueOf(6), Integer.valueOf(7), Integer.valueOf(8), Integer.valueOf(9), Integer.valueOf(10), Integer.valueOf(11)};
            while (i != numArr.length) {
                this.c.add(numArr[i]);
                i++;
            }
            this.a = (HorizontalListView) findViewById(R.id.setting_ui_wifi_channel_index_hv);
            HorizontalListView horizontalListView = this.a;
            ListAdapter aVar = new a(this, getContext(), R.layout.setting_ui_wifi_channel_index_item, this.c);
            this.b = aVar;
            horizontalListView.setAdapter(aVar);
            this.a.setOnItemClickListener(new OnItemClickListener(this) {
                final /* synthetic */ WifiChannelIndexView a;

                {
                    this.a = r1;
                }

                public void onItemClick(AdapterView<?> adapterView, View view, final int i, long j) {
                    if (!view.isSelected()) {
                        dji.setting.ui.widget.a.b(this.a.getContext(), R.string.setting_ui_wifi_setting_changed_restart_tip, new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void onClick(DialogInterface dialogInterface, int i) {
                                this.b.a.setWifiChannelIndex(i);
                            }
                        }, new OnClickListener(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                    }
                }
            });
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            c.a().a(this);
            if (i.getInstance().c() != null) {
                onEventMainThread(i.getInstance().c());
            }
        }
    }

    protected void onDetachedFromWindow() {
        c.a().d(this);
        super.onDetachedFromWindow();
    }

    public void onEventMainThread(ProductType productType) {
        if (b.getInstance().a(productType)) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }

    private void setWifiChannelIndex(final int i) {
        DataWifiSetModeChannel.getInstance().a(this.f == WiFiFrequencyBand.FrequencyBand2Dot4G ? 0 : 1).b(((Integer) this.c.get(i)).intValue()).start(new d(this) {
            final /* synthetic */ WifiChannelIndexView b;

            public void onSuccess(Object obj) {
                this.b.runOnUiThread(new Runnable(this) {
                    final /* synthetic */ AnonymousClass2 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b.a.setSelection(i);
                    }
                });
                WM220LogUtil.LOGI("DataWifiSetModeChannel set channel success");
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                WM220LogUtil.LOGI("DataWifiSetModeChannel set channel fail!");
            }
        });
    }

    public void onWifiChannelGetted(WiFiFrequencyBand wiFiFrequencyBand) {
        this.d = DataWifiGetChannelList.getInstance().get24GChannelList();
        this.e = DataWifiGetChannelList.getInstance().get5GChannelList();
        this.g = DataWifiGetChannelList.getInstance().getCurChannel();
        onShowedChannelModeChanged(wiFiFrequencyBand);
    }

    public void onShowedChannelModeChanged(WiFiFrequencyBand wiFiFrequencyBand) {
        int i = 0;
        this.f = wiFiFrequencyBand;
        if (this.d.length == 0) {
            this.f = WiFiFrequencyBand.FrequencyBand5G;
        } else if (this.e.length == 0) {
            this.f = WiFiFrequencyBand.FrequencyBand2Dot4G;
        }
        int i2 = -1;
        if (this.f == WiFiFrequencyBand.FrequencyBand2Dot4G) {
            this.c.clear();
            while (i != this.d.length) {
                this.c.add(Integer.valueOf(this.d[i]));
                if (this.g == this.d[i]) {
                    i2 = i;
                }
                i++;
            }
        } else {
            this.c.clear();
            while (i != this.e.length) {
                this.c.add(Integer.valueOf(this.e[i]));
                if (this.g == this.e[i]) {
                    i2 = i;
                }
                i++;
            }
        }
        if (this.c.size() > 0) {
            HorizontalListView horizontalListView = this.a;
            ListAdapter aVar = new a(this, getContext(), R.layout.setting_ui_wifi_channel_index_item, this.c);
            this.b = aVar;
            horizontalListView.setAdapter(aVar);
            this.b.a(i2);
            this.b.notifyDataSetChanged();
        }
    }
}
