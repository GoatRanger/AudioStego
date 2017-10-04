package dji.pilot2.library.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import dji.logic.album.a.b;
import dji.logic.album.model.DJIAlbumFileInfo;
import dji.pilot.R;
import dji.pilot.playback.litchi.a;
import dji.pilot.playback.litchi.h;
import dji.pilot.usercenter.widget.DJIShareProgressBar;
import dji.pilot2.library.model.DJISycAlbumModel;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.utils.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJILinearLayout;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class g extends BaseExpandableListAdapter {
    private static Handler t;
    final String a = Environment.getExternalStorageDirectory().getAbsolutePath();
    private final LayoutInflater b;
    private final int c;
    private final int d;
    private final List<a> e;
    private SparseArray<DJISycAlbumModel> f;
    private SparseArray<DJIAlbumFileInfo> g;
    private List<DJISycAlbumModel> h;
    private final OnClickListener i;
    private ImageLoader j;
    private b k = null;
    private DisplayImageOptions l = null;
    private DisplayImageOptions m = null;
    private Boolean n = Boolean.valueOf(false);
    private d o;
    private int p = 3;
    private h q;
    private Boolean r = Boolean.valueOf(false);
    private Options s = null;
    private Context u;
    private int v = 0;
    private int w = 0;

    public g(Context context, int i, int i2, List<a> list, SparseArray<DJISycAlbumModel> sparseArray, List<DJISycAlbumModel> list2, ImageLoader imageLoader, OnClickListener onClickListener) {
        this.u = context;
        this.b = LayoutInflater.from(context);
        this.c = i;
        this.d = i2;
        this.h = list2;
        this.e = list;
        this.f = sparseArray;
        this.i = onClickListener;
        this.v = i2;
        this.w = i;
        this.j = imageLoader;
        this.s = new Options();
        this.s.inSampleSize = 8;
        this.l = new Builder().imageScaleType(ImageScaleType.EXACTLY).showImageOnLoading(R.drawable.v2_photo_defalut).showImageOnFail(R.drawable.v2_photo_defalut).displayer(new SimpleBitmapDisplayer()).considerExifParams(true).cacheInMemory(false).cacheOnDisc(false).bitmapConfig(Config.RGB_565).build();
        this.m = new Builder().imageScaleType(ImageScaleType.EXACTLY).showImageOnLoading(R.drawable.v2_photo_defalut).showImageOnFail(R.drawable.v2_photo_defalut).displayer(new SimpleBitmapDisplayer()).decodingOptions(this.s).considerExifParams(true).cacheInMemory(false).cacheOnDisc(false).bitmapConfig(Config.RGB_565).build();
        this.k = b.getInstance(context);
        this.o = new d(context);
        this.q = h.getInstance();
        if (DJIOriLayout.getDeviceType() == DJIDeviceType.Pad) {
            this.p = 6;
        } else {
            this.p = 3;
        }
    }

    public void a(Handler handler) {
        t = handler;
    }

    public int getGroupCount() {
        return this.e != null ? this.e.size() : 0;
    }

    public int getChildrenCount(int i) {
        if (this.e == null || this.e.size() <= i) {
            return 0;
        }
        int size = ((a) this.e.get(i)).c.size();
        return size == 0 ? 0 : ((size - 1) / this.p) + 1;
    }

    public Object getGroup(int i) {
        return (this.e == null || this.e.size() <= i) ? null : (a) this.e.get(i);
    }

    public Object getChild(int i, int i2) {
        return (this.e == null || this.e.size() <= i) ? null : (a) this.e.get(i2);
    }

    public long getGroupId(int i) {
        return (long) i;
    }

    public long getChildId(int i, int i2) {
        return (long) i2;
    }

    public boolean hasStableIds() {
        return false;
    }

    @SuppressLint({"InflateParams", "SimpleDateFormat"})
    public View getGroupView(int i, boolean z, View view, ViewGroup viewGroup) {
        b bVar;
        a aVar;
        int childrenCount = getChildrenCount(i);
        if (view == null) {
            bVar = new b();
            view = this.b.inflate(R.layout.v2_library_group_title_moment, null);
            bVar.a = (DJITextView) view.findViewById(R.id.bhe);
            view.setTag(bVar);
        } else {
            bVar = (b) view.getTag();
        }
        if (this.e.size() <= 0 || i >= this.e.size()) {
            aVar = null;
        } else {
            aVar = (a) this.e.get(i);
        }
        if (childrenCount != 0 || aVar == null) {
            view.setVisibility(0);
        } else {
            this.e.remove(aVar);
            notifyDataSetChanged();
            if (this.e.size() != 0 && i <= this.e.size()) {
                if (i == this.e.size()) {
                    view.setVisibility(8);
                    if (t != null) {
                        t.sendEmptyMessage(1);
                    }
                } else {
                    aVar = (a) this.e.get(i);
                }
            }
        }
        if (!(aVar == null || aVar.a() == null)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Log.i("date", "adapter before:" + aVar.a());
                Date parse = simpleDateFormat.parse(aVar.a());
                Log.i("date", "adapter date:" + parse);
                long currentTimeMillis = System.currentTimeMillis() - parse.getTime();
                if (currentTimeMillis < 0) {
                    bVar.a.setText(aVar.a());
                } else if (currentTimeMillis < 86400000) {
                    bVar.a.setText(R.string.v2_library_date_today);
                } else if (currentTimeMillis < 172800000) {
                    bVar.a.setText(R.string.v2_library_date_yestoday);
                } else {
                    bVar.a.setText(aVar.a());
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return view;
    }

    private int a(int i) {
        if (i == 1) {
            return R.id.bgp;
        }
        if (i == 2) {
            return R.id.bgq;
        }
        if (i == 3) {
            return R.id.bgr;
        }
        if (i == 4) {
            return R.id.bgs;
        }
        if (i == 5) {
            return R.id.bgt;
        }
        return R.id.bgo;
    }

    private void a(a aVar, DJIRelativeLayout dJIRelativeLayout) {
        aVar.a = dJIRelativeLayout;
        aVar.c = (DJIRelativeLayout) dJIRelativeLayout.findViewById(R.id.cpq);
        aVar.b = (DJIShareProgressBar) dJIRelativeLayout.findViewById(R.id.c1v);
        aVar.g = (DJITextView) dJIRelativeLayout.findViewById(R.id.cq7);
        aVar.d = (DJILinearLayout) dJIRelativeLayout.findViewById(R.id.c1w);
        aVar.f = (DJITextView) dJIRelativeLayout.findViewById(R.id.c1y);
        aVar.i = (DJIImageView) dJIRelativeLayout.findViewById(R.id.c20);
        aVar.h = (DJIImageView) dJIRelativeLayout.findViewById(R.id.c1z);
        aVar.l = (DJIImageView) dJIRelativeLayout.findViewById(R.id.cpt);
        aVar.p = dJIRelativeLayout.findViewById(R.id.cps);
        aVar.q = dJIRelativeLayout.findViewById(R.id.cpu);
        LayoutParams layoutParams = aVar.a.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, this.c);
        } else {
            layoutParams.width = -1;
            layoutParams.height = this.c;
        }
        aVar.a.setLayoutParams(layoutParams);
        aVar.a.setOnClickListener(this.i);
        aVar.q.setOnClickListener(this.i);
    }

    @SuppressLint({"InflateParams", "ResourceAsColor"})
    public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
        int i3;
        a[] aVarArr;
        if (view == null) {
            Object obj = new a[this.p];
            View inflate = this.b.inflate(R.layout.v2_library_group_photo_item, null);
            for (i3 = 0; i3 < this.p; i3++) {
                obj[i3] = new a();
                a(obj[i3], (DJIRelativeLayout) inflate.findViewById(a(i3)));
            }
            ((LinearLayout) inflate).setWeightSum((float) this.p);
            inflate.setTag(obj);
            view = inflate;
            aVarArr = obj;
        } else {
            aVarArr = (a[]) view.getTag();
        }
        for (int i4 = 0; i4 < this.p; i4++) {
            aVarArr[i4].a.hide();
        }
        if (this.e != null && this.e.size() > i) {
            List list = ((a) this.e.get(i)).c;
            int i5 = i2 * this.p;
            i3 = 0;
            while (i5 + i3 < list.size() && i3 < this.p) {
                DJISycAlbumModel dJISycAlbumModel = (DJISycAlbumModel) list.get(i5 + i3);
                if (dJISycAlbumModel.isfileLeve2To) {
                    aVarArr[i3].b.setTag(dJISycAlbumModel.mRemoteInfo.b());
                    aVarArr[i3].f.setTag(dJISycAlbumModel.mRemoteInfo.b());
                } else if (dJISycAlbumModel.isThumb) {
                    aVarArr[i3].b.setTag(dJISycAlbumModel.mThumbmAbsPath);
                    aVarArr[i3].f.setTag(dJISycAlbumModel.mThumbmAbsPath);
                } else {
                    aVarArr[i3].b.setTag(dJISycAlbumModel.mLocalInfo.e);
                    aVarArr[i3].f.setTag(dJISycAlbumModel.mLocalInfo.e);
                }
                aVarArr[i3].a.show();
                this.r = Boolean.valueOf(false);
                if (dJISycAlbumModel.isfileLeve2To) {
                    if (dJISycAlbumModel.isDownLoadEnd) {
                        aVarArr[i3].g.setVisibility(8);
                        aVarArr[i3].f.setVisibility(8);
                        aVarArr[i3].d.setClickable(true);
                        aVarArr[i3].b.setClickable(false);
                    } else if (dJISycAlbumModel.downloadState == dji.pilot2.library.d.m || dJISycAlbumModel.downloadState == dji.pilot2.library.d.l) {
                        aVarArr[i3].b.setClickable(true);
                        aVarArr[i3].d.setClickable(false);
                        aVarArr[i3].g.setVisibility(0);
                        if (aVarArr[i3].f.getTag().equals(dJISycAlbumModel.mRemoteInfo.b())) {
                            aVarArr[i3].g.setText(R.string.v2_library_05);
                        }
                    } else if (dJISycAlbumModel.downloadState == dji.pilot2.library.d.o) {
                        aVarArr[i3].d.setClickable(true);
                        aVarArr[i3].b.setClickable(false);
                        aVarArr[i3].f.setVisibility(8);
                        aVarArr[i3].g.setVisibility(8);
                    } else if (dJISycAlbumModel.downloadState == dji.pilot2.library.d.p || dJISycAlbumModel.downloadState == dji.pilot2.library.d.q) {
                        aVarArr[i3].d.setClickable(false);
                        aVarArr[i3].b.setClickable(true);
                        if (aVarArr[i3].f.getTag().equals(dJISycAlbumModel.mRemoteInfo.b())) {
                            aVarArr[i3].g.setText(R.string.v2_library_05);
                        }
                        aVarArr[i3].g.setVisibility(0);
                    }
                    if (this.k.b("file:///" + this.k.h(dJISycAlbumModel.mRemoteInfo.c())) && aVarArr[i3].b.getTag().equals(dJISycAlbumModel.mRemoteInfo.b())) {
                        aVarArr[i3].b.setImageBitmap(this.k.a("file:///" + this.k.h(dJISycAlbumModel.mRemoteInfo.c())));
                    } else if (this.k.d(dJISycAlbumModel.mRemoteInfo.c()) && aVarArr[i3].b.getTag().equals(dJISycAlbumModel.mRemoteInfo.b())) {
                        this.j.displayImage("file:///" + this.k.h(dJISycAlbumModel.mRemoteInfo.c()), aVarArr[i3].b, this.l, new ImageLoadingListener(this) {
                            final /* synthetic */ g a;

                            {
                                this.a = r1;
                            }

                            public void onLoadingStarted(String str, View view) {
                            }

                            public void onLoadingFailed(String str, View view, FailReason failReason) {
                            }

                            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                                this.a.k.a(str, bitmap);
                            }

                            public void onLoadingCancelled(String str, View view) {
                            }
                        });
                    } else {
                        aVarArr[i3].b.setImageDrawable(this.b.getContext().getResources().getDrawable(R.drawable.v2_photo_defalut));
                    }
                } else if (dJISycAlbumModel.islocal) {
                    aVarArr[i3].b.setLayoutParams(new RelativeLayout.LayoutParams(this.v / 4, this.w / 4));
                    aVarArr[i3].b.setVisibility(4);
                    aVarArr[i3].h.go();
                    aVarArr[i3].c.setBackgroundResource(R.drawable.v2_photo_defalut);
                    this.j.displayImage(dJISycAlbumModel.mLocalInfo.c(), aVarArr[i3].b, this.m, new ImageLoadingListener(this) {
                        final /* synthetic */ g a;

                        {
                            this.a = r1;
                        }

                        public void onLoadingStarted(String str, View view) {
                        }

                        public void onLoadingFailed(String str, View view, FailReason failReason) {
                        }

                        public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                            view.setLayoutParams(new RelativeLayout.LayoutParams(this.a.v, this.a.w));
                            view.setVisibility(0);
                            this.a.k.a(str, bitmap);
                        }

                        public void onLoadingCancelled(String str, View view) {
                        }
                    });
                } else {
                    aVarArr[i3].f.setVisibility(8);
                    aVarArr[i3].d.setClickable(true);
                    aVarArr[i3].g.setVisibility(8);
                    aVarArr[i3].b.setClickable(false);
                    if (dJISycAlbumModel.isThumb) {
                        if (this.k.b(a$e.a + dJISycAlbumModel.mThumbmAbsPath) && aVarArr[i3].b.getTag().equals(dJISycAlbumModel.mThumbmAbsPath)) {
                            aVarArr[i3].b.setImageBitmap(this.k.a(a$e.a + dJISycAlbumModel.mThumbmAbsPath));
                        } else if (this.k.d(a$e.a + dJISycAlbumModel.mThumbmAbsPath) && aVarArr[i3].b.getTag().equals(dJISycAlbumModel.mThumbmAbsPath)) {
                            aVarArr[i3].b.setImageBitmap(this.k.c(a$e.a + dJISycAlbumModel.mThumbmAbsPath));
                        } else {
                            this.j.displayImage(a$e.a + dJISycAlbumModel.mThumbmAbsPath, aVarArr[i3].b, this.l, new ImageLoadingListener(this) {
                                final /* synthetic */ g a;

                                {
                                    this.a = r1;
                                }

                                public void onLoadingStarted(String str, View view) {
                                }

                                public void onLoadingFailed(String str, View view, FailReason failReason) {
                                }

                                public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                                    this.a.k.a(str, bitmap);
                                }

                                public void onLoadingCancelled(String str, View view) {
                                }
                            });
                        }
                    } else if (this.k.b(a$e.a + dJISycAlbumModel.mLocalInfo.e) && aVarArr[i3].b.getTag().equals(dJISycAlbumModel.mLocalInfo.e)) {
                        aVarArr[i3].b.setImageBitmap(this.k.a(a$e.a + dJISycAlbumModel.mLocalInfo.e));
                        aVarArr[i3].c.setBackgroundResource(R.drawable.v2_photo_defalut_nopic);
                    } else {
                        aVarArr[i3].b.setLayoutParams(new RelativeLayout.LayoutParams(this.v / 4, this.w / 4));
                        aVarArr[i3].b.setVisibility(4);
                        aVarArr[i3].h.go();
                        aVarArr[i3].c.setBackgroundResource(R.drawable.v2_photo_defalut);
                        this.j.displayImage(dJISycAlbumModel.mLocalInfo.c(), aVarArr[i3].b, this.m, new ImageLoadingListener(this) {
                            final /* synthetic */ g a;

                            {
                                this.a = r1;
                            }

                            public void onLoadingStarted(String str, View view) {
                            }

                            public void onLoadingFailed(String str, View view, FailReason failReason) {
                            }

                            public void onLoadingComplete(String str, View view, Bitmap bitmap) {
                                view.setLayoutParams(new RelativeLayout.LayoutParams(this.a.v, this.a.w));
                                view.setVisibility(0);
                                this.a.k.a(str, bitmap);
                            }

                            public void onLoadingCancelled(String str, View view) {
                            }
                        });
                    }
                }
                String language = Locale.getDefault().getLanguage();
                if (language.equals("cn") || language.equals("zh")) {
                    aVarArr[i3].h.setImageDrawable(this.u.getResources().getDrawable(R.drawable.v2_library_origin_cn));
                }
                if (dJISycAlbumModel.isOrg) {
                    aVarArr[i3].h.show();
                } else {
                    aVarArr[i3].h.go();
                }
                if (this.f.indexOfKey(dJISycAlbumModel.hashCode()) >= 0) {
                    this.o.b(aVarArr[i3].b, this.d, this.c);
                    aVarArr[i3].i.show();
                    aVarArr[i3].l.setBackground(this.b.getContext().getResources().getDrawable(R.drawable.v2_library_itemselect_true));
                } else {
                    this.o.c(aVarArr[i3].b, this.d, this.c);
                    aVarArr[i3].i.go();
                    aVarArr[i3].l.setBackground(this.b.getContext().getResources().getDrawable(R.drawable.v2_library_itemselect_false));
                }
                h hVar = new h();
                hVar.b = aVarArr[i3].p;
                hVar.a = aVarArr[i3].b;
                hVar.d = dJISycAlbumModel;
                hVar.e = aVarArr[i3].l;
                aVarArr[i3].a.setTag(hVar);
                aVarArr[i3].l.setTag(hVar);
                aVarArr[i3].q.setTag(hVar);
                i3++;
            }
        }
        return view;
    }

    public boolean isChildSelectable(int i, int i2) {
        return true;
    }
}
