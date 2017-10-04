package dji.pilot2.nativeaudio.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.multimoment.template.TemplateController;
import dji.pilot2.nativeaudio.model.DownloadTemplateBean;
import dji.pilot2.nativeaudio.model.NetworkAudioListModel.NetworkAudioTemplateModel;
import java.util.List;
import java.util.Locale;

public class c extends BaseAdapter {
    private Context a;
    private List<NetworkAudioTemplateModel> b;
    private List<DownloadTemplateBean> c = com.dji.frame.c.c.c(this.a).c(DownloadTemplateBean.class);

    private class a {
        Context a;
        NetworkAudioTemplateModel b;
        ImageView c;
        TextView d;
        TextView e;
        TextView f;
        TextView g;
        final /* synthetic */ c h;

        public a(c cVar, Context context) {
            this.h = cVar;
            this.a = context;
        }

        private void a() {
            int i;
            com.dji.frame.c.c.a(this.a).a(this.c, this.b.thumb_large);
            b();
            if (this.e.getText() == null || this.e.getText().equals("")) {
                this.e.setVisibility(8);
            } else {
                this.e.setVisibility(0);
            }
            this.f.setText(this.b.duration);
            if (this.h.c != null) {
                int i2 = 0;
                while (i2 < this.h.c.size()) {
                    DownloadTemplateBean downloadTemplateBean = (DownloadTemplateBean) this.h.c.get(i2);
                    if (downloadTemplateBean == null || this.b.id != downloadTemplateBean.listId) {
                        i2++;
                    } else {
                        if (TemplateController.getInstance().containsId(downloadTemplateBean.templateId)) {
                            i = 1;
                        } else {
                            this.h.c.remove(downloadTemplateBean);
                            com.dji.frame.c.c.c(this.h.a).a(DownloadTemplateBean.class, "listId=" + downloadTemplateBean.id);
                            i = 0;
                        }
                        if (i != 0) {
                            this.g.setVisibility(4);
                        } else {
                            this.g.setVisibility(0);
                        }
                    }
                }
            }
            i = 0;
            if (i != 0) {
                this.g.setVisibility(0);
            } else {
                this.g.setVisibility(4);
            }
        }

        private void b() {
            if (Locale.getDefault().getCountry().equals("CN")) {
                if (this.b.titleModel == null || this.b.titleModel.cn == null) {
                    this.d.setText("");
                } else {
                    this.d.setText(this.b.titleModel.cn);
                }
                if (this.b.subtitleModel == null || this.b.subtitleModel.cn == null) {
                    this.e.setText("");
                } else {
                    this.e.setText(this.b.subtitleModel.cn);
                }
            } else if (Locale.getDefault().getCountry().equals("JP")) {
                if (this.b.titleModel == null || this.b.titleModel.jp == null) {
                    this.d.setText("");
                } else {
                    this.d.setText(this.b.titleModel.jp);
                }
                if (this.b.subtitleModel == null || this.b.subtitleModel.jp == null) {
                    this.e.setText("");
                } else {
                    this.e.setText(this.b.subtitleModel.jp);
                }
            } else {
                if (this.b.titleModel == null || this.b.titleModel.en == null) {
                    this.d.setText("");
                } else {
                    this.d.setText(this.b.titleModel.en);
                }
                if (this.b.subtitleModel == null || this.b.subtitleModel.en == null) {
                    this.e.setText("");
                } else {
                    this.e.setText(this.b.subtitleModel.en);
                }
            }
        }
    }

    public c(Context context) {
        this.a = context;
    }

    public void a(List<NetworkAudioTemplateModel> list) {
        this.b = list;
    }

    public void a() {
        this.c = com.dji.frame.c.c.c(this.a).c(DownloadTemplateBean.class);
        for (int i = 0; i < this.c.size(); i++) {
            DownloadTemplateBean downloadTemplateBean = (DownloadTemplateBean) this.c.get(i);
            DJILogHelper.getInstance().LOGI("Lyric", "DownloadTemplateBean: " + downloadTemplateBean.listId + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + downloadTemplateBean.templateId);
        }
    }

    public int getCount() {
        if (this.b != null) {
            return this.b.size();
        }
        return 0;
    }

    public Object getItem(int i) {
        return this.b.get(i);
    }

    public long getItemId(int i) {
        return (long) ((NetworkAudioTemplateModel) this.b.get(i)).id;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null || view.getTag() == null) {
            view = LayoutInflater.from(this.a).inflate(R.layout.v2_network_audio_item, null);
            a aVar = new a(this, this.a);
            aVar.c = (ImageView) view.findViewById(R.id.cva);
            aVar.d = (TextView) view.findViewById(R.id.cvc);
            aVar.e = (TextView) view.findViewById(R.id.cvd);
            aVar.f = (TextView) view.findViewById(R.id.cve);
            aVar.g = (TextView) view.findViewById(R.id.cvb);
            view.setTag(aVar);
        }
        a aVar2 = (a) view.getTag();
        aVar2.b = (NetworkAudioTemplateModel) getItem(i);
        aVar2.a();
        return view;
    }
}
