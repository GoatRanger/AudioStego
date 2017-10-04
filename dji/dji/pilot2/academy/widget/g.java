package dji.pilot2.academy.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.dji.frame.c.l;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import dji.pilot.R;
import dji.pilot.college.model.CollegeInfo;
import dji.pilot.college.model.a;
import dji.pilot.fpv.d.e;
import dji.pilot.usercenter.activity.DJIWebVideoActivity;
import dji.pilot.usercenter.mode.WebVideoInfo;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import java.util.Date;

public class g extends d {
    private ImageLoader e = null;
    private DisplayImageOptions f = null;
    private ImageLoadingListener g = null;

    public g(Context context) {
        super(context);
        c();
    }

    private void c() {
        this.e = ImageLoader.getInstance();
        this.f = new Builder().imageScaleType(ImageScaleType.EXACTLY).considerExifParams(true).bitmapConfig(Config.RGB_565).cacheInMemory(true).cacheOnDisc(true).build();
        this.g = new ImageLoadingListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onLoadingStarted(String str, View view) {
            }

            public void onLoadingFailed(String str, View view, FailReason failReason) {
                this.a.e.displayImage(str, (ImageView) view, this.a.f);
            }

            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
            }

            public void onLoadingCancelled(String str, View view) {
            }
        };
    }

    protected void b() {
        a a = this.d.a(false);
        if (this.c.isEmpty() || this.c.size() != a.b.size() || ((CollegeInfo) this.c.get(0)).mGuid != ((CollegeInfo) a.b.get(0)).mGuid) {
            this.c.clear();
            if (!a.b.isEmpty()) {
                this.c.addAll(a.b);
            }
            notifyDataSetChanged();
        }
    }

    public void b(int i) {
        if (i == 65536) {
            b();
        }
    }

    public void a(int i, int i2, Object obj) {
        if (i == 65536) {
            Toast.makeText(this.b, R.string.college_get_failed, 0).show();
        }
    }

    public void d(int i) {
        CollegeInfo collegeInfo = (CollegeInfo) this.c.get(i);
        e.a("Academy_VideosView_Button_PlayVideo");
        this.d.c(collegeInfo);
        notifyDataSetChanged();
        WebVideoInfo webVideoInfo = new WebVideoInfo();
        webVideoInfo.j = collegeInfo.getUrl();
        webVideoInfo.e = this.b.getString(R.string.college_instrutional_video);
        webVideoInfo.a = collegeInfo.mGuid;
        webVideoInfo.h = l.a(new Date(collegeInfo.mCreateTime));
        DJIWebVideoActivity.a(this.b, webVideoInfo, 3);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        a aVar;
        if (view == null) {
            aVar = new a();
            view = this.a.inflate(R.layout.v2_academy_video_item, null);
            aVar.a = (DJIImageView) view.findViewById(R.id.c4_);
            aVar.b = (DJIImageView) view.findViewById(R.id.c4a);
            aVar.g = (DJITextView) view.findViewById(R.id.c4c);
            view.setTag(aVar);
        } else {
            aVar = (a) view.getTag();
        }
        CollegeInfo collegeInfo = (CollegeInfo) this.c.get(i);
        this.e.displayImage(collegeInfo.mThumnailUrl, aVar.a, this.f, this.g);
        aVar.a(collegeInfo);
        return view;
    }
}
