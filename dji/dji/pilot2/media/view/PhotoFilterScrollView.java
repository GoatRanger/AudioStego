package dji.pilot2.media.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.ArrayList;

public class PhotoFilterScrollView extends HorizontalScrollView {
    private LayoutInflater a;
    private LinearLayout b;
    private Context c;
    private ArrayList<DJIImageView> d = new ArrayList();
    private int e = 0;
    private OnClickListener f;
    private a g;

    public interface a {
        void onClick(int i);
    }

    public PhotoFilterScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = context;
        this.a = LayoutInflater.from(context);
        a();
    }

    public void initInnerViews(LinearLayout linearLayout) {
        int a = dji.pilot2.media.a.a.a();
        for (int i = 0; i != a; i++) {
            View inflate = this.a.inflate(R.layout.v2_photo_filter_item, null);
            DJIImageView dJIImageView = (DJIImageView) inflate.findViewById(R.id.cxk);
            ((DJIImageView) inflate.findViewById(R.id.cxj)).setImageResource(dji.pilot2.media.a.a.b(i));
            ((DJITextView) inflate.findViewById(R.id.cxl)).setText(dji.pilot2.media.a.a.a(this.c, i));
            if (i == 0) {
                dJIImageView.show();
            }
            this.d.add(dJIImageView);
            inflate.setOnClickListener(this.f);
            inflate.setTag(Integer.valueOf(i));
            linearLayout.addView(inflate);
        }
    }

    private void a() {
        this.f = new OnClickListener(this) {
            final /* synthetic */ PhotoFilterScrollView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                int intValue = ((Integer) view.getTag()).intValue();
                if (intValue != this.a.e) {
                    ((DJIImageView) this.a.d.get(this.a.e)).go();
                    ((DJIImageView) this.a.d.get(intValue)).show();
                    this.a.e = intValue;
                    if (this.a.g != null) {
                        this.a.g.onClick(intValue);
                    }
                }
            }
        };
    }

    public int getCurSelectIndex() {
        return this.e;
    }

    public void setOnItemClickListener(a aVar) {
        this.g = aVar;
    }
}
