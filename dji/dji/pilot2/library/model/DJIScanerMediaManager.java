package dji.pilot2.library.model;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.provider.MediaStore.Images;
import android.provider.MediaStore.Video.Media;
import android.provider.MediaStore.Video.Thumbnails;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import com.dji.frame.c.d;
import com.dji.frame.c.f;
import dji.log.DJILogHelper;
import dji.midware.usb.P3.DJIUsbAccessoryReceiver;
import dji.pilot.publics.objects.DJIApplication;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class DJIScanerMediaManager {
    private static DJIScanerMediaManager manager;
    private final String TAG = "DJIScanerMediaManager";
    private dji.logic.album.a.b mCacheManager;
    private ContentResolver mContentResolver;
    private Context mContext;
    private boolean mIsVideo = false;
    private b mListener;
    private ArrayList<MediaInfoBean> mMediaInfoBeans;
    private ArrayList<String> mSelectedPaths = new ArrayList();
    private ArrayList<Integer> mThumbnailSelectedList;
    private ArrayList<c> mThumbnails;

    public interface b {
        void a();
    }

    public static class a {
        public int a;
        public String b;
        public boolean c;
        public ArrayList<c> d;
    }

    public static class c {
        public int a;
        public String b;
        public String c;
        public int d;
        public String e;
        private View f;

        public void a(View view) {
            this.f = view;
        }

        public View a() {
            return this.f;
        }
    }

    public void setOnMediaDataGetListener(b bVar) {
        this.mListener = bVar;
    }

    private DJIScanerMediaManager(Context context) {
        this.mContext = context;
        this.mThumbnails = new ArrayList();
        this.mThumbnailSelectedList = new ArrayList();
        this.mCacheManager = dji.logic.album.a.b.getInstance();
        if (this.mCacheManager == null) {
            this.mCacheManager = dji.logic.album.a.b.getInstance(this.mContext);
        }
        this.mMediaInfoBeans = new ArrayList();
    }

    public static DJIScanerMediaManager getInstance(Context context) {
        if (manager == null) {
            manager = new DJIScanerMediaManager(context);
        }
        return manager;
    }

    public void setFlagVideo(boolean z) {
        this.mIsVideo = z;
    }

    public boolean getFlagVideo() {
        return this.mIsVideo;
    }

    public void updateSelectedPaths(ArrayList<String> arrayList) {
        this.mSelectedPaths = arrayList;
    }

    public ArrayList<String> getSelectedPaths() {
        return this.mSelectedPaths;
    }

    public ArrayList<MediaInfoBean> getMediaList(boolean z) {
        if (this.mThumbnailSelectedList != null && this.mThumbnailSelectedList.size() > 0) {
            for (int i = 0; i < this.mThumbnailSelectedList.size(); i++) {
                MediaInfoBean originMediaInfo = getOriginMediaInfo(((Integer) this.mThumbnailSelectedList.get(i)).intValue(), z);
                if (originMediaInfo != null) {
                    ArrayList arrayList;
                    String format = String.format("mediaId = %d", new Object[]{Integer.valueOf(originMediaInfo.getMediaId())});
                    Log.i("zhang", "sql:" + format);
                    synchronized (this) {
                        arrayList = (ArrayList) com.dji.frame.c.c.c(this.mContext).c(MediaInfoBean.class, format);
                    }
                    Log.i("zhang", "size:" + arrayList.size());
                    if (arrayList == null || arrayList.size() <= 0) {
                        this.mMediaInfoBeans.add(originMediaInfo);
                        saveMediaListToDb(originMediaInfo);
                    } else if (z) {
                        this.mMediaInfoBeans.add(originMediaInfo);
                    }
                }
            }
        }
        return this.mMediaInfoBeans;
    }

    public ArrayList<Integer> getSelectedList() {
        return this.mThumbnailSelectedList;
    }

    public ArrayList<c> getThumbnailInfo() {
        return this.mThumbnails;
    }

    public void updateThumbnailList(ArrayList<Integer> arrayList) {
        this.mThumbnailSelectedList = arrayList;
    }

    public void resetLists() {
        this.mThumbnailSelectedList.clear();
        this.mThumbnails.clear();
        this.mMediaInfoBeans.clear();
    }

    public void deleteMediaFromDb(String str) {
        if (str != null) {
            String str2 = "title like '" + str + "'";
            Log.i("DJIScanerMediaManager", str2);
            synchronized (this) {
                com.dji.frame.c.c.c(this.mContext).a(MediaInfoBean.class, str2);
            }
        }
    }

    public void deleteMediaFromDbByPath(String str) {
        if (str != null) {
            String str2 = "absPath like '" + str + "'";
            Log.i("DJIScanerMediaManager", str2);
            synchronized (this) {
                com.dji.frame.c.c.c(this.mContext).a(MediaInfoBean.class, str2);
            }
        }
    }

    public ArrayList<MediaInfoBean> getVideoFromDb() {
        return (ArrayList) com.dji.frame.c.c.c(this.mContext).c(MediaInfoBean.class, "type like 'video%'");
    }

    public ArrayList<MediaInfoBean> getImageFromDb() {
        return (ArrayList) com.dji.frame.c.c.c(this.mContext).c(MediaInfoBean.class, "type like 'image%'");
    }

    public void updateImageDateToDb(MediaInfoBean mediaInfoBean, String str) {
        String str2 = "id=" + mediaInfoBean.getMediaId();
        mediaInfoBean.setAddDate(str);
        synchronized (this) {
            com.dji.frame.c.c.c(this.mContext).c(mediaInfoBean, str2);
        }
    }

    public void scaneAllMedias(boolean z) {
        DJILogHelper.getInstance().LOGD("DJIScanerMediaManager", "scaneAllMedias");
        if (this.mContext != null) {
            Cursor query;
            this.mContentResolver = this.mContext.getContentResolver();
            String[] strArr;
            String[] strArr2;
            if (z) {
                strArr = new String[]{"_id", "_data", "date_added", "duration", "width", "height"};
                strArr2 = new String[]{"%/%DJI%/%", "%mp4", "%mov"};
                query = this.mContentResolver.query(Media.EXTERNAL_CONTENT_URI, strArr, "_data not like ? and ( _data like ? or _data like ? ) and  ( duration >= 10000 and duration <= 18000000 )", strArr2, "_id asc");
            } else {
                strArr = new String[]{"_id", "_data", "date_added"};
                strArr2 = new String[]{"%/%DJI%/%"};
                query = this.mContentResolver.query(Images.Media.EXTERNAL_CONTENT_URI, strArr, "_data not like ?", strArr2, "_id asc");
            }
            if (query != null) {
                query.moveToFirst();
                for (int i = 0; i < query.getCount(); i++) {
                    try {
                        long j = query.getLong(0);
                        String string = query.getString(1);
                        String string2 = query.getString(2);
                        if (z) {
                            long j2 = query.getLong(4);
                            int i2 = query.getInt(5);
                            if (j2 == 0 || i2 == 0) {
                                MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                                mediaMetadataRetriever.setDataSource(string);
                                String extractMetadata = mediaMetadataRetriever.extractMetadata(18);
                                String extractMetadata2 = mediaMetadataRetriever.extractMetadata(19);
                                if (extractMetadata != null) {
                                    j2 = (long) Integer.valueOf(extractMetadata).intValue();
                                }
                                if (extractMetadata2 != null) {
                                    i2 = Integer.valueOf(extractMetadata2).intValue();
                                }
                            }
                            if (j2 >= 3500 || r1 >= com.alipay.sdk.c.a.a) {
                                query.moveToNext();
                            }
                        }
                        c cVar = new c();
                        cVar.e = DateFormat.format("yyyy-MM-dd", Long.valueOf(string2).longValue() * 1000).toString();
                        if (z) {
                            cVar.d = query.getInt(3);
                            if (string.contains("mp4") || string.contains("mov")) {
                                String thumbnailPath = getThumbnailPath(j);
                                if (thumbnailPath == null) {
                                    query.moveToNext();
                                } else {
                                    cVar.a = (int) j;
                                    cVar.b = thumbnailPath;
                                }
                            }
                        } else {
                            cVar.a = (int) j;
                            cVar.b = string;
                        }
                        if (cVar.b != null && f.a(cVar.b).booleanValue()) {
                            this.mThumbnails.add(cVar);
                            if (this.mListener != null) {
                                this.mListener.a();
                            }
                        }
                        query.moveToNext();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                query.close();
            }
            if (this.mListener != null) {
                this.mListener.a();
            }
        }
    }

    public void ScanMedias(boolean z) {
        DJILogHelper.getInstance().LOGD("DJIScanerMediaManager", "ScanMedias");
        if (this.mContext != null) {
            Cursor query;
            this.mContentResolver = this.mContext.getContentResolver();
            String[] strArr;
            if (z) {
                strArr = new String[]{"_id", "video_id", "_data"};
                query = this.mContentResolver.query(Thumbnails.EXTERNAL_CONTENT_URI, strArr, null, null, "_id asc");
            } else {
                strArr = new String[]{"_id", "image_id", "_data"};
                query = this.mContentResolver.query(Images.Thumbnails.EXTERNAL_CONTENT_URI, strArr, null, null, "_id asc");
            }
            if (query != null) {
                int columnIndexOrThrow;
                int columnIndexOrThrow2;
                query.moveToFirst();
                if (z) {
                    columnIndexOrThrow = query.getColumnIndexOrThrow("video_id");
                    columnIndexOrThrow2 = query.getColumnIndexOrThrow("_data");
                } else {
                    columnIndexOrThrow = query.getColumnIndexOrThrow("image_id");
                    columnIndexOrThrow2 = query.getColumnIndexOrThrow("_data");
                }
                for (int i = 0; i < query.getCount(); i++) {
                    c cVar = new c();
                    cVar.a = query.getInt(columnIndexOrThrow);
                    cVar.b = query.getString(columnIndexOrThrow2);
                    MediaInfoBean originMediaInfo = getOriginMediaInfo(cVar.a, this.mIsVideo);
                    String createVideoThumbnails;
                    if (cVar.b == null || !f.a(cVar.b).booleanValue() || originMediaInfo.getAbsPath() == null) {
                        if (!(originMediaInfo == null || originMediaInfo.getAbsPath() == null || originMediaInfo.getType() == null)) {
                            if (!this.mIsVideo) {
                                cVar.b = originMediaInfo.getAbsPath();
                            } else if (originMediaInfo.getType().contains("mp4") || originMediaInfo.getType().contains("mov")) {
                                createVideoThumbnails = createVideoThumbnails(originMediaInfo.getAbsPath(), originMediaInfo.getTitle());
                                Log.i("zc123", "path is " + createVideoThumbnails);
                                cVar.b = createVideoThumbnails;
                            }
                        }
                        query.moveToNext();
                    } else if (!(cVar.b.contains(DJIUsbAccessoryReceiver.c) || cVar == null)) {
                        ArrayList arrayList;
                        createVideoThumbnails = String.format("mediaId = %d", new Object[]{Integer.valueOf(cVar.a)});
                        synchronized (this) {
                            arrayList = (ArrayList) com.dji.frame.c.c.c(this.mContext).c(MediaInfoBean.class, createVideoThumbnails);
                        }
                        if (arrayList == null || arrayList.size() <= 0) {
                            Log.i("DJIScanerMediaManager", cVar.b);
                            this.mThumbnails.add(cVar);
                            query.moveToNext();
                        } else {
                            query.moveToNext();
                        }
                    }
                }
                query.close();
            }
        }
    }

    public String getThumbnailPath(long j) {
        String[] strArr = new String[]{"_data"};
        String[] strArr2 = new String[]{String.valueOf(j)};
        Thumbnails.getThumbnail(this.mContentResolver, j, 3, null);
        Cursor query = this.mContentResolver.query(Thumbnails.EXTERNAL_CONTENT_URI, strArr, "video_id = ?", strArr2, null);
        if (query == null || query.getCount() <= 0) {
            return null;
        }
        query.moveToFirst();
        return query.getString(query.getColumnIndex("_data"));
    }

    public String createVideoThumbnails(String str, String str2) {
        Bitmap b = dji.pilot.usercenter.f.f.b(str, 0);
        File file = new File(d.a(DJIApplication.a(), dji.pilot.c.b.e) + str2 + "thumbnai.jpg");
        try {
            OutputStream fileOutputStream = new FileOutputStream(file.getAbsoluteFile());
            b.compress(CompressFormat.JPEG, 50, fileOutputStream);
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<a> getMediaTimeStamps() {
        if (this.mThumbnails == null || this.mThumbnails.size() <= 0) {
            return null;
        }
        return sortAndReGroupMedias(this.mThumbnails);
    }

    public ArrayList<a> sortAndReGroupMedias(ArrayList<c> arrayList) {
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        ArrayList<a> arrayList2 = new ArrayList();
        Collections.sort(arrayList, new Comparator<c>(this) {
            final /* synthetic */ DJIScanerMediaManager a;

            {
                this.a = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return a((c) obj, (c) obj2);
            }

            public int a(c cVar, c cVar2) {
                if (cVar.e == null || cVar2.e == null) {
                    return 0;
                }
                return cVar2.e.compareTo(cVar.e);
            }
        });
        ArrayList arrayList3 = new ArrayList();
        int i = 1;
        int i2 = 1;
        String str = ((c) arrayList.get(0)).e;
        while (i < arrayList.size()) {
            if (str == null || str.equals(((c) arrayList.get(i)).e)) {
                i2++;
            } else {
                Log.i("zc", "time >>>>>>>" + ((c) arrayList.get(i)).e);
                str = ((c) arrayList.get(i)).e;
                arrayList3.add(Integer.valueOf(i2));
                i2 = 1;
            }
            i++;
        }
        arrayList3.add(Integer.valueOf(i2));
        int i3 = 0;
        for (i = 0; i < arrayList3.size(); i++) {
            Log.i("zc", "groups:" + arrayList3.get(i));
            a aVar = new a();
            aVar.b = ((c) arrayList.get(i3)).e;
            ArrayList arrayList4 = new ArrayList();
            for (i2 = 0; i2 < ((Integer) arrayList3.get(i)).intValue(); i2++) {
                arrayList4.add(arrayList.get(i3 + i2));
            }
            aVar.c = this.mIsVideo;
            aVar.a = ((Integer) arrayList3.get(i)).intValue();
            aVar.d = arrayList4;
            i3 += ((Integer) arrayList3.get(i)).intValue();
            arrayList2.add(aVar);
        }
        return arrayList2;
    }

    public void saveMediaListToDb(MediaInfoBean mediaInfoBean) {
        mediaInfoBean.setAddDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        synchronized (this) {
            com.dji.frame.c.c.c(this.mContext).a(mediaInfoBean);
        }
    }

    public boolean loadMedias() {
        if (this.mThumbnails == null || this.mThumbnails.size() == 0) {
            return false;
        }
        for (int i = 0; i < this.mThumbnails.size(); i++) {
            Bitmap readFileToBitmap = readFileToBitmap(((c) this.mThumbnails.get(i)).b);
            if (readFileToBitmap != null) {
                this.mCacheManager.a(String.valueOf(((c) this.mThumbnails.get(i)).a), readFileToBitmap);
            }
        }
        return true;
    }

    public MediaInfoBean getOriginMediaInfo(int i, boolean z) {
        Log.i("DJIScanerMediaManager", "getOriginMediaInfo");
        MediaInfoBean mediaInfoBean = new MediaInfoBean();
        if (this.mContext != null) {
            Cursor query;
            this.mContentResolver = this.mContext.getContentResolver();
            String[] strArr;
            if (z) {
                strArr = new String[]{"_id", "_display_name", "duration", "_data", "mime_type", "date_added"};
                query = this.mContentResolver.query(Media.EXTERNAL_CONTENT_URI, strArr, "_id=" + i, null, null);
            } else {
                strArr = new String[]{"_id", "_display_name", "_data", "mime_type", "date_added"};
                query = this.mContentResolver.query(Images.Media.EXTERNAL_CONTENT_URI, strArr, "_id=" + i, null, null);
            }
            if (query != null && query.getCount() > 0) {
                query.moveToFirst();
                if (z) {
                    Log.i("zc", "ID:" + query.getLong(2));
                    for (int i2 = 0; i2 < this.mThumbnails.size(); i2++) {
                        if (((c) this.mThumbnails.get(i2)).a == i) {
                            Log.i("zc", "find sub path!");
                            mediaInfoBean.setSubNailpath(((c) this.mThumbnails.get(i2)).b);
                        }
                    }
                    mediaInfoBean.setMediaId(query.getInt(0));
                    mediaInfoBean.setTitle(query.getString(1));
                    mediaInfoBean.setDuration(query.getLong(2) / 1000);
                    mediaInfoBean.setAbsPath(query.getString(3));
                    mediaInfoBean.setType(query.getString(4));
                    mediaInfoBean.setdate(query.getString(5));
                } else {
                    mediaInfoBean.setMediaId(query.getInt(0));
                    mediaInfoBean.setTitle(query.getString(1));
                    mediaInfoBean.setAbsPath(query.getString(2));
                    mediaInfoBean.setType(query.getString(3));
                    mediaInfoBean.setdate(query.getString(4));
                }
                query.close();
            }
        }
        return mediaInfoBean;
    }

    public String getLocalDate(String str) {
        String str2 = null;
        if (str != null) {
            try {
                str2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str).toString();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    public Bitmap readFileToBitmap(String str) {
        Log.i("DJIScanerMediaManager", "readFileToBitmap:" + str);
        if (str == null) {
            return null;
        }
        Bitmap decodeFile = BitmapFactory.decodeFile(str);
        if (decodeFile == null) {
            return null;
        }
        Bitmap createScaledBitmap = Bitmap.createScaledBitmap(decodeFile, 400, 400, false);
        decodeFile.recycle();
        return createScaledBitmap;
    }
}
