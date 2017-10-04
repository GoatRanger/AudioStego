package dji.pilot2.multimoment.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.nativeaudio.model.NetWorkBigFilmModel;
import dji.pilot2.nativeaudio.model.NetWorkBigFilmModel.MultiBigFilmModel;
import dji.pilot2.nativeaudio.model.NetWorkBigFilmModel.MutiBigFilmContent;
import dji.pilot2.nativeaudio.model.NetWorkBigFilmModel.MutiBigFilmLinkModel;
import dji.pilot2.nativeaudio.model.NetWorkBigFilmModel.MutiBigFilmPersonModel;
import dji.pilot2.template.c;
import dji.pilot2.utils.k;
import dji.pilot2.utils.p;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class BitFilmAdapter extends BaseAdapter {
    private int[] BigFilmPics = new int[]{R.drawable.v2_bigfilm_trip, R.drawable.v2_bigfilm_music, R.drawable.v2_bigfilm_change, R.drawable.v2_bigfilm_happiness, R.drawable.v2_bigfilm_love};
    private int[] BigFilmStrings = new int[]{R.string.v2_multimoment_bigfilm_temp1, R.string.v2_multimoment_bigfilm_temp2, R.string.v2_multimoment_bigfilm_temp3, R.string.v2_multimoment_bigfilm_temp4, R.string.v2_multimoment_bigfilm_temp5, R.string.v2_multimoment_bigfilm_temp6, R.string.v2_multimoment_bigfilm_temp7};
    private final String TAG = "BitFilmAdapter";
    private int TEMPNUM = 7;
    private boolean isLoadfinished = false;
    private Context mContext;
    private int mCurIndex = -1;
    private HashMap<Integer, Integer> mIDmap = new HashMap();
    private HashMap<Integer, Integer> mImgMap = new HashMap();
    private LayoutInflater mInflater;
    private NetWorkBigFilmModel mListModel;
    private int mLocalNum = 0;
    private ArrayList<c> mTemplateBeans;
    private dji.pilot2.multimoment.template.c mTemplateController;

    class a {
        DJITextView a = null;
        DJIImageView b;
        DJIImageView c;
        DJIImageView d;
        DJITextView e;
        DJITextView f;
        LinearLayout g;
        final /* synthetic */ BitFilmAdapter h;

        a(BitFilmAdapter bitFilmAdapter) {
            this.h = bitFilmAdapter;
        }

        public void a(int i, int i2, int i3) {
            this.b.setImageDrawable(this.h.mContext.getResources().getDrawable(i));
            if (this.h.isLoaded(i3)) {
                c singleTemplateBeanByPosition = this.h.getSingleTemplateBeanByPosition(i3);
                if (singleTemplateBeanByPosition != null) {
                    this.e.setText(p.e(((int) singleTemplateBeanByPosition.getTotalDurations()) / 1000));
                    this.f.setText("" + singleTemplateBeanByPosition.i);
                    this.a.setText(singleTemplateBeanByPosition.getTemplateName());
                }
                this.g.setVisibility(0);
                this.a.setVisibility(0);
                return;
            }
            this.g.setVisibility(8);
            this.a.setVisibility(8);
        }
    }

    public BitFilmAdapter(Context context) {
        int i = 0;
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mTemplateController = dji.pilot2.multimoment.template.c.getInstance();
        this.mTemplateBeans = (ArrayList) this.mTemplateController.a(context);
        getDownLoadInfoTask();
        this.mLocalNum = this.mTemplateBeans.size();
        Log.e("zhangchen", "init:" + this.mTemplateBeans.size());
        int i2;
        if ("zh".equals(Locale.getDefault().getLanguage())) {
            this.TEMPNUM = 5;
            for (i2 = 0; i2 < this.TEMPNUM; i2++) {
                this.mImgMap.put(Integer.valueOf(i2), Integer.valueOf(this.BigFilmPics[i2]));
            }
        } else {
            this.TEMPNUM = 5;
            for (i2 = 0; i2 < this.TEMPNUM; i2++) {
                this.mImgMap.put(Integer.valueOf(i2), Integer.valueOf(this.BigFilmPics[i2]));
            }
        }
        while (i < this.TEMPNUM) {
            this.mIDmap.put(Integer.valueOf(i), Integer.valueOf(i + 1001));
            i++;
        }
    }

    public int getCount() {
        return this.TEMPNUM;
    }

    public Object getItem(int i) {
        return null;
    }

    private void getDownLoadInfoTask() {
        com.dji.frame.c.c.b(this.mContext).a(k.x(), new dji.thirdparty.afinal.f.a<String>(this) {
            final /* synthetic */ BitFilmAdapter a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                this.a.isLoadfinished = true;
                DJILogHelper.getInstance().LOGD("BitFilmAdapter", str.toString());
                if (str != null) {
                    this.a.mListModel = (NetWorkBigFilmModel) h.b(str, NetWorkBigFilmModel.class);
                    if (!(this.a.mListModel == null || this.a.mListModel.templates == null || this.a.mListModel.templates.size() <= 0)) {
                        int size = this.a.mListModel.templates.size();
                        for (int i = 0; i < size; i++) {
                            MultiBigFilmModel multiBigFilmModel = (MultiBigFilmModel) this.a.mListModel.templates.get(i);
                            if (multiBigFilmModel != null) {
                                MutiBigFilmLinkModel mutiBigFilmLinkModel = new MutiBigFilmLinkModel();
                                mutiBigFilmLinkModel.videoLink = (MutiBigFilmContent) h.b(multiBigFilmModel.video, MutiBigFilmContent.class);
                                mutiBigFilmLinkModel.zipLink = (MutiBigFilmContent) h.b(multiBigFilmModel.zip_Android, MutiBigFilmContent.class);
                                multiBigFilmModel.personModel = (MutiBigFilmPersonModel) h.b(multiBigFilmModel.author, MutiBigFilmPersonModel.class);
                                multiBigFilmModel.linkModel = mutiBigFilmLinkModel;
                            }
                        }
                    }
                    this.a.notifyDataSetChanged();
                }
            }

            public void a(Throwable th, int i, String str) {
                DJILogHelper.getInstance().LOGD("BitFilmAdapter", th.toString());
                this.a.isLoadfinished = true;
                this.a.notifyDataSetChanged();
            }
        });
    }

    public MultiBigFilmModel getTempInfoByPosition(int i) {
        if (this.mListModel == null || this.mListModel.templates == null) {
            return null;
        }
        for (MultiBigFilmModel multiBigFilmModel : this.mListModel.templates) {
            if (multiBigFilmModel != null) {
                Log.i("zhang", "id" + multiBigFilmModel.template_id);
                if (multiBigFilmModel.template_id == ((Integer) this.mIDmap.get(Integer.valueOf(i))).intValue()) {
                    return multiBigFilmModel;
                }
            }
        }
        return null;
    }

    public int getPosToID(int i) {
        return ((Integer) this.mIDmap.get(Integer.valueOf(i))).intValue();
    }

    public c getSingleTemplateBeanByPosition(int i) {
        for (int i2 = 0; i2 < this.mLocalNum; i2++) {
            c cVar = (c) this.mTemplateBeans.get(i2);
            if (cVar != null && cVar.d() == ((Integer) this.mIDmap.get(Integer.valueOf(i))).intValue()) {
                return cVar;
            }
        }
        return null;
    }

    public boolean isLoaded(int i) {
        for (int i2 = 0; i2 < this.mLocalNum; i2++) {
            c cVar = (c) this.mTemplateBeans.get(i2);
            if (cVar != null && cVar.d() == ((Integer) this.mIDmap.get(Integer.valueOf(i))).intValue()) {
                return true;
            }
        }
        return false;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void setCurIndex(int i) {
        this.mCurIndex = i;
        notifyDataSetChanged();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.v2_multi_bigfilm_adapter, null);
            aVar = new a(this);
            aVar.b = (DJIImageView) view.findViewById(R.id.ctk);
            aVar.c = (DJIImageView) view.findViewById(R.id.ctl);
            aVar.a = (DJITextView) view.findViewById(R.id.cto);
            aVar.d = (DJIImageView) view.findViewById(R.id.ctm);
            aVar.e = (DJITextView) view.findViewById(R.id.ctq);
            aVar.f = (DJITextView) view.findViewById(R.id.cts);
            aVar.g = (LinearLayout) view.findViewById(R.id.ctp);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        if (isLoaded(i)) {
            aVar.d.go();
        } else {
            aVar.d.show();
        }
        if (this.mCurIndex == -1 || i != this.mCurIndex) {
            aVar.c.setVisibility(4);
            aVar.a.setTextColor(this.mContext.getResources().getColor(R.color.om));
            aVar.e.setTextColor(this.mContext.getResources().getColor(R.color.om));
            aVar.f.setTextColor(this.mContext.getResources().getColor(R.color.om));
        } else {
            aVar.c.setVisibility(0);
            aVar.a.setTextColor(this.mContext.getResources().getColor(R.color.nx));
            aVar.e.setTextColor(this.mContext.getResources().getColor(R.color.nx));
            aVar.f.setTextColor(this.mContext.getResources().getColor(R.color.nx));
        }
        aVar.a(((Integer) this.mImgMap.get(Integer.valueOf(i))).intValue(), this.BigFilmStrings[i], i);
        view.setTag(aVar);
        return view;
    }
}
