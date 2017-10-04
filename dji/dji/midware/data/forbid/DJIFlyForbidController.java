package dji.midware.data.forbid;

import android.content.Context;
import android.location.Location;
import android.support.v4.widget.AutoScrollHelper;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.midware.data.forbid.FlyForbidProtocol.DJIWarningAreaState;
import dji.midware.data.forbid.FlyForbidProtocol.LevelType;
import dji.midware.data.forbid.NfzAccountEvent.UnlimitAreasChanged;
import dji.midware.data.model.P3.DataFlycGetPushForbidStatus.DJIFlightLimitAreaState;
import dji.midware.data.model.P3.DataFlycGetPushRequestLimitUpdate;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.util.i;
import dji.thirdparty.a.c;
import dji.thirdparty.afinal.b;
import dji.thirdparty.afinal.d.b.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class DJIFlyForbidController {
    public static final String AIRMAP_DATA_SOURCE = "airmap";
    public static final String DEFAULT_RECORD_EMAIL = "unknown";
    public static final String DJI_DATA_SOURCE = "dji";
    public static final String KEY_CUR_USE_GEO_SYSTEM = "key_cur_use_geo_system";
    public static final String KEY_DJI_SERVER_TIME = "key_dji_server_time";
    public static final String KEY_FLY_FORBID_DATA_SOURCE = "key_fly_forbid_data_source";
    public static final String KEY_OPEN_GEO = "key_open_geo";
    public static final int dbversion = 5;
    private static DJIFlyForbidController instance = null;
    private static b mFinalDb = null;
    private b.b dbUpdateListener = new 1(this);
    private boolean isCheckingData = false;
    boolean isCheckingRemovedList = false;
    boolean isSDKLogic = false;
    private FlyForbidElement lastStrongWarningArea;
    private List<FlyForbidElement> mCheckResultList = null;
    private Context mContext;
    private FlyForbidElement mCurForbidArea;
    private List<FlyForbidElement> mCurSWAreasAround = new ArrayList();
    private List<FlyForbidElement> mCurUnlockableAreasAround = new ArrayList();
    private FlyForbidElement mCurWarningArea;
    private DJIWarningAreaState mCurWarningState = DJIWarningAreaState.None;
    private List<FlyForbidElementAirMap> mDatabaseAirMapList = null;
    private List<FlyForbidElement> mDatabaseList = null;
    private List<UnlimitAreaRecordElement> mHaveRemovedList;
    private double mLastCheckedLat = 0.0d;
    private double mLastCheckedLng = 0.0d;
    private boolean mNeedPushLimitData = false;
    private boolean mNeedRefreshDatabase = false;
    private double mRefreshDBLastLat = 0.0d;
    private double mRefreshDBLastLng = 0.0d;
    private List<FlyForbidElement> mStrongWarningAreaList;
    private List<StrongWarningUnlockedElement> mStrongWarningUnlockedList;
    private List<FlyForbidElement> mWarningAreaList;

    public static synchronized DJIFlyForbidController getInstance(Context context) {
        DJIFlyForbidController dJIFlyForbidController;
        synchronized (DJIFlyForbidController.class) {
            if (instance == null) {
                instance = new DJIFlyForbidController(context);
            }
            dJIFlyForbidController = instance;
        }
        return dJIFlyForbidController;
    }

    public void setIsCheckingData(boolean z) {
        this.isCheckingData = z;
        NFZLogUtil.LOGD("**into setIsCheckingData value: " + z);
        if (this.isCheckingData) {
            c.a().e(CheckingDataState.CHECKING);
        } else {
            c.a().e(CheckingDataState.FINISHED);
        }
    }

    public boolean getIsCheckingData() {
        return this.isCheckingData;
    }

    public static synchronized DJIFlyForbidController getInstance() {
        DJIFlyForbidController dJIFlyForbidController;
        synchronized (DJIFlyForbidController.class) {
            dJIFlyForbidController = instance;
        }
        return dJIFlyForbidController;
    }

    public DJIFlyForbidController(Context context) {
        this.mContext = context;
        this.mDatabaseList = new ArrayList();
        this.mCheckResultList = new ArrayList();
        this.mDatabaseAirMapList = new ArrayList();
        this.mWarningAreaList = new ArrayList();
        this.mStrongWarningAreaList = new ArrayList();
        if (mFinalDb == null) {
            mFinalDb = b.a(context.getApplicationContext(), null, "dji.nfzdb", false, 5, this.dbUpdateListener);
        }
        this.mHaveRemovedList = new ArrayList();
        this.mStrongWarningUnlockedList = new ArrayList();
        c.a().a((Object) this);
    }

    public b getDb() {
        if (mFinalDb == null) {
            mFinalDb = b.a(this.mContext.getApplicationContext(), null, "dji.nfzdb", false, 5, this.dbUpdateListener);
        }
        return mFinalDb;
    }

    public void refreshUnlockList(String str, String str2) {
        refreshRemovedList(str, str2);
        refreshSWUnlockedList(str2);
        this.mLastCheckedLat = 0.0d;
        this.mLastCheckedLng = 0.0d;
    }

    public void setSDKLogic(boolean z) {
        this.isSDKLogic = z;
    }

    public void refreshRemovedList(String str, String str2) {
        if (!"".equals(str2)) {
            this.isCheckingRemovedList = true;
            this.mHaveRemovedList.clear();
            NFZLogUtil.LOGD("**into refreshRemovedList user_id: " + str + " flycsn: " + str2);
            this.mHaveRemovedList = mFinalDb.c(UnlimitAreaRecordElement.class);
            for (int size = this.mHaveRemovedList.size() - 1; size >= 0; size--) {
                UnlimitAreaRecordElement unlimitAreaRecordElement = (UnlimitAreaRecordElement) this.mHaveRemovedList.get(size);
                NFZLogUtil.LOGD("**into refreshRemovedList id: " + unlimitAreaRecordElement.user_id + "sn: " + unlimitAreaRecordElement.flycsn + "Nsn: " + str2);
                String str3;
                String str4;
                Object substring;
                if (this.isSDKLogic) {
                    if (!(!unlimitAreaRecordElement.is_offline_unlocked || unlimitAreaRecordElement.user_id == null || unlimitAreaRecordElement.user_id.equals(str))) {
                        this.mHaveRemovedList.remove(size);
                    }
                    str3 = unlimitAreaRecordElement.flycsn;
                    if (unlimitAreaRecordElement.flycsn.length() >= 10) {
                        str3 = unlimitAreaRecordElement.flycsn.substring(0, 10);
                    }
                    if (str2.length() < 10) {
                        str4 = str2;
                    } else {
                        substring = str2.substring(0, 10);
                    }
                    if (str3.equals(substring)) {
                        if (unlimitAreaRecordElement.begin_at <= currentTimeSencs()) {
                        }
                        mFinalDb.f(unlimitAreaRecordElement);
                        this.mHaveRemovedList.remove(size);
                    } else {
                        this.mHaveRemovedList.remove(size);
                    }
                } else {
                    if (unlimitAreaRecordElement.is_offline_unlocked && !unlimitAreaRecordElement.user_id.equals(str)) {
                        this.mHaveRemovedList.remove(size);
                    }
                    str3 = unlimitAreaRecordElement.flycsn;
                    if (unlimitAreaRecordElement.flycsn.length() >= 10) {
                        str3 = unlimitAreaRecordElement.flycsn.substring(0, 10);
                    }
                    if (str2.length() < 10) {
                        substring = str2.substring(0, 10);
                    } else {
                        str4 = str2;
                    }
                    if (str3.equals(substring)) {
                        this.mHaveRemovedList.remove(size);
                    } else if (unlimitAreaRecordElement.begin_at <= currentTimeSencs() || unlimitAreaRecordElement.end_at < currentTimeSencs()) {
                        mFinalDb.f(unlimitAreaRecordElement);
                        this.mHaveRemovedList.remove(size);
                    }
                }
            }
            this.isCheckingRemovedList = false;
        }
    }

    public List<UnlimitAreaRecordElement> getUnlimitAreaRecordElementsFromDataBase(String str) {
        if (this.isCheckingRemovedList) {
            return this.mHaveRemovedList;
        }
        this.mHaveRemovedList.clear();
        NFZLogUtil.LOGD("**into refreshRemovedList user_id: " + str);
        this.mHaveRemovedList = mFinalDb.c(UnlimitAreaRecordElement.class);
        for (int size = this.mHaveRemovedList.size() - 1; size >= 0; size--) {
            UnlimitAreaRecordElement unlimitAreaRecordElement = (UnlimitAreaRecordElement) this.mHaveRemovedList.get(size);
            if (unlimitAreaRecordElement.user_id == null || unlimitAreaRecordElement.user_id.equals(str)) {
                String str2 = unlimitAreaRecordElement.flycsn;
                if (unlimitAreaRecordElement.flycsn.length() >= 10) {
                    unlimitAreaRecordElement.flycsn.substring(0, 10);
                }
                if (unlimitAreaRecordElement.begin_at > currentTimeSencs() || unlimitAreaRecordElement.end_at < currentTimeSencs()) {
                    mFinalDb.f(unlimitAreaRecordElement);
                    this.mHaveRemovedList.remove(size);
                }
            } else {
                this.mHaveRemovedList.remove(size);
            }
        }
        return this.mHaveRemovedList;
    }

    public void refreshSWUnlockedList(String str) {
        if (!"".equals(str)) {
            this.mStrongWarningUnlockedList.clear();
            this.mStrongWarningUnlockedList = mFinalDb.c(StrongWarningUnlockedElement.class, String.format("flycsn = '%s'", new Object[]{str}));
            for (int size = this.mStrongWarningUnlockedList.size() - 1; size >= 0; size--) {
                StrongWarningUnlockedElement strongWarningUnlockedElement = (StrongWarningUnlockedElement) this.mStrongWarningUnlockedList.get(size);
                if (strongWarningUnlockedElement.begin_at > currentTimeSencs() || strongWarningUnlockedElement.end_at < currentTimeSencs()) {
                    mFinalDb.f(strongWarningUnlockedElement);
                    this.mStrongWarningUnlockedList.remove(size);
                }
            }
        }
    }

    private int getTableCount(Class<?> cls) {
        if (mFinalDb == null) {
            return 0;
        }
        return mFinalDb.a("SELECT COUNT(*) AS c FROM " + f.a((Class) cls).b()).c("c");
    }

    public synchronized int getDataSize(boolean z) {
        int i;
        if (mFinalDb == null) {
            i = -1;
        } else if (z) {
            c.a().e(DataFlycGetPushRequestLimitUpdate.getInstance());
            i = getTableCount(FlyForbidElementAirMap.class);
        } else {
            c.a().e(DataFlycGetPushRequestLimitUpdate.getInstance());
            i = getTableCount(FlyForbidElement.class);
        }
        return i;
    }

    public void refreshDatabase() {
        this.mLastCheckedLat = 0.0d;
        this.mLastCheckedLng = 0.0d;
        if (this.lastStrongWarningArea == null) {
            this.lastStrongWarningArea = new FlyForbidElement();
        }
        this.lastStrongWarningArea.lat = 0.0d;
        this.lastStrongWarningArea.lng = 0.0d;
        this.mNeedRefreshDatabase = true;
        c.a().e(DataFlycGetPushRequestLimitUpdate.getInstance());
    }

    private <T> List<T> getDataAround(Class<T> cls, double d, double d2) {
        if (mFinalDb == null) {
            return null;
        }
        String format;
        if (d2 < ((double) (DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle + 1.0f))) {
            format = String.format(Locale.getDefault(), " lat<'%f' and lat>'%f' and (lng<'%f' or lng>'%f')", new Object[]{Double.valueOf(((double) 1065353216) + d), Double.valueOf(d - ((double) 1065353216)), Double.valueOf(((double) 1065353216) + d2), Double.valueOf((d2 - ((double) 1065353216)) + 360.0d)});
        } else if (d2 > ((double) (180.0f - 1.0f))) {
            format = String.format(Locale.getDefault(), " lat<'%f' and lat>'%f' and (lng>'%f' or lng<'%f')", new Object[]{Double.valueOf(((double) 1065353216) + d), Double.valueOf(d - ((double) 1065353216)), Double.valueOf(d2 - ((double) 1065353216)), Double.valueOf((((double) 1065353216) + d2) - 360.0d)});
        } else {
            format = String.format(Locale.getDefault(), " lat<'%f' and lat>'%f' and lng<'%f' and lng>'%f'", new Object[]{Double.valueOf(((double) 1065353216) + d), Double.valueOf(d - ((double) 1065353216)), Double.valueOf(((double) 1065353216) + d2), Double.valueOf(d2 - ((double) 1065353216))});
        }
        return mFinalDb.c((Class) cls, format);
    }

    private <T> List<T> getDataAroundSlow(Class<T> cls, double d, double d2) {
        ArrayList arrayList = new ArrayList();
        List<T> arrayList2 = new ArrayList();
        List a = mFinalDb.a((Class) cls, "id", "" + 0 + "," + 2000);
        if (a == null) {
            return arrayList2;
        }
        long j = 0;
        List list = a;
        while (true) {
            int size = list.size();
            if (size <= 0) {
                return arrayList2;
            }
            for (int i = size - 1; i >= 0; i--) {
                Object obj = list.get(i);
                double d3 = 0.0d;
                double d4 = 0.0d;
                if (cls == FlyForbidElementAirMap.class) {
                    d3 = ((FlyForbidElementAirMap) obj).lat;
                    d4 = ((FlyForbidElementAirMap) obj).lng;
                } else if (cls == FlyForbidElement.class) {
                    d3 = ((FlyForbidElement) obj).lat;
                    d4 = ((FlyForbidElement) obj).lng;
                }
                if (d2 < ((double) (DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle + 1.0f))) {
                    if (d3 < ((double) 1065353216) + d && d3 > d - ((double) 1065353216) && (r10 < ((double) 1065353216) + d2 || r10 > (d2 - ((double) 1065353216)) + 360.0d)) {
                        arrayList2.add(obj);
                    }
                } else if (d2 > ((double) (180.0f - 1.0f))) {
                    if (d3 < ((double) 1065353216) + d && d3 > d - ((double) 1065353216) && (r10 > d2 - ((double) 1065353216) || r10 < (((double) 1065353216) + d2) - 360.0d)) {
                        arrayList2.add(obj);
                    }
                } else if (d3 < ((double) 1065353216) + d && d3 > d - ((double) 1065353216) && r10 < ((double) 1065353216) + d2 && r10 > d2 - ((double) 1065353216)) {
                    arrayList2.add(obj);
                }
            }
            list.clear();
            long j2 = j + ((long) 2000);
            a = mFinalDb.a((Class) cls, "id", "" + j2 + "," + 2000);
            j = j2;
            list = a;
        }
    }

    public synchronized List<FlyForbidElement> checkNearFlyForbidArea(double d, double d2) {
        double distance = (double) getDistance(this.mLastCheckedLat, this.mLastCheckedLng, d, d2);
        if ((this.mLastCheckedLat == 0.0d && this.mLastCheckedLng == 0.0d) || distance > 10000.0d || this.mCheckResultList == null || this.mCheckResultList.isEmpty()) {
            int size;
            NFZLogUtil.LOGD("********** checkNearFlyForbidArea in if");
            this.mLastCheckedLat = d;
            this.mLastCheckedLng = d2;
            NFZLogUtil.savedLOG("nfz log 2 i c f a");
            Object obj = getDistance(this.mRefreshDBLastLat, this.mRefreshDBLastLng, d, d2) > 50000.0f ? 1 : null;
            NFZLogUtil.LOGD(getDataSource());
            boolean z;
            if (getDataSource().equals(AIRMAP_DATA_SOURCE)) {
                z = this.mNeedRefreshDatabase || obj != null;
                this.mCheckResultList = getNearFlyForbidAreaAirMap(d, d2, z);
            } else {
                z = this.mNeedRefreshDatabase || obj != null;
                this.mCheckResultList = getNearFlyForbidArea(d, d2, z);
            }
            if (this.mCheckResultList != null) {
                NFZLogUtil.savedLOG("nfz log 4 i c l s" + this.mCheckResultList.size());
            } else {
                NFZLogUtil.savedLOG("nfz log 4 i c l s null");
            }
            if (this.mCheckResultList == null) {
                this.mCheckResultList = new ArrayList();
            }
            DJILogHelper.getInstance().LOGD("nfz", "********** checkNearFlyForbidArea mCheckResultList size: " + this.mCheckResultList.size(), false, true);
            for (UnlimitAreaRecordElement unlimitAreaRecordElement : this.mHaveRemovedList) {
                for (size = this.mCheckResultList.size() - 1; size >= 0; size--) {
                    if (unlimitAreaRecordElement.area_id == ((FlyForbidElement) this.mCheckResultList.get(size)).area_id) {
                        this.mCheckResultList.remove(size);
                        break;
                    }
                }
            }
            DJILogHelper.getInstance().LOGD("nfz", "********** checkNearFlyForbidArea mCheckResultList after removed size: " + this.mCheckResultList.size(), false, true);
            this.mWarningAreaList.clear();
            this.mStrongWarningAreaList.clear();
            for (size = this.mCheckResultList.size() - 1; size >= 0; size--) {
                if (((FlyForbidElement) this.mCheckResultList.get(size)).level == LevelType.WARNING.value()) {
                    this.mWarningAreaList.add(this.mCheckResultList.get(size));
                } else if (((FlyForbidElement) this.mCheckResultList.get(size)).type == 13) {
                    r1 = null;
                    for (StrongWarningUnlockedElement strongWarningUnlockedElement : this.mStrongWarningUnlockedList) {
                        if (strongWarningUnlockedElement.area_id == ((FlyForbidElement) this.mCheckResultList.get(size)).area_id) {
                            obj = 1;
                        } else {
                            obj = r1;
                        }
                        r1 = obj;
                    }
                    if (r1 == null) {
                        FlyForbidElement copyOf = ((FlyForbidElement) this.mCheckResultList.get(size)).copyOf();
                        copyOf.radius = 8047;
                        this.mStrongWarningAreaList.add(copyOf);
                    }
                } else if (((FlyForbidElement) this.mCheckResultList.get(size)).level == 3) {
                    r1 = null;
                    for (StrongWarningUnlockedElement strongWarningUnlockedElement2 : this.mStrongWarningUnlockedList) {
                        if (strongWarningUnlockedElement2.area_id == ((FlyForbidElement) this.mCheckResultList.get(size)).area_id) {
                            obj = 1;
                        } else {
                            obj = r1;
                        }
                        r1 = obj;
                    }
                    if (r1 == null) {
                        this.mStrongWarningAreaList.add(this.mCheckResultList.get(size));
                    }
                }
            }
            this.mNeedPushLimitData = true;
        }
        return this.mCheckResultList;
    }

    public String getDataSource() {
        return i.b(this.mContext, KEY_FLY_FORBID_DATA_SOURCE, DJI_DATA_SOURCE);
    }

    public boolean isInStrongWarningArea(double d, double d2) {
        if (this.lastStrongWarningArea != null) {
            if (getDistance(d, d2, this.lastStrongWarningArea.lat, this.lastStrongWarningArea.lng) < ((float) this.lastStrongWarningArea.radius)) {
                return true;
            }
        }
        this.lastStrongWarningArea = null;
        for (int size = this.mStrongWarningAreaList.size() - 1; size >= 0; size--) {
            FlyForbidElement flyForbidElement = (FlyForbidElement) this.mStrongWarningAreaList.get(size);
            if (getDistance(d, d2, flyForbidElement.lat, flyForbidElement.lng) < ((float) flyForbidElement.radius)) {
                this.lastStrongWarningArea = flyForbidElement;
                return true;
            }
        }
        return false;
    }

    public void unlockSWArea(double d, double d2, String str) {
        for (int size = this.mStrongWarningAreaList.size() - 1; size >= 0; size--) {
            FlyForbidElement flyForbidElement = (FlyForbidElement) this.mStrongWarningAreaList.get(size);
            if (getDistance(d, d2, flyForbidElement.lat, flyForbidElement.lng) < ((float) flyForbidElement.radius)) {
                Object strongWarningUnlockedElement = new StrongWarningUnlockedElement(flyForbidElement.area_id, currentTimeSencs(), currentTimeSencs() + FlyForbidProtocol.UNLIMIT_AREA_EXPIRED_TIME, str);
                mFinalDb.a(strongWarningUnlockedElement);
                this.mStrongWarningUnlockedList.add(strongWarningUnlockedElement);
                this.mStrongWarningAreaList.remove(flyForbidElement);
                this.lastStrongWarningArea = null;
            }
        }
    }

    private long currentTimeSencs() {
        return System.currentTimeMillis() / 1000;
    }

    public DJIWarningAreaState handleWarningArea(double d, double d2) {
        float distance;
        if (this.mCurWarningState.equals(DJIWarningAreaState.InnerLimit)) {
            distance = getDistance(d, d2, this.mCurWarningArea.lat, this.mCurWarningArea.lng);
            if (distance < ((float) this.mCurWarningArea.radius)) {
                return this.mCurWarningState;
            }
            if (distance < ((float) (this.mCurWarningArea.radius + 100))) {
                this.mCurWarningState = DJIWarningAreaState.NearLimit;
                return this.mCurWarningState;
            }
            this.mCurWarningState = DJIWarningAreaState.None;
        }
        if (this.mCurWarningState.equals(DJIWarningAreaState.NearLimit)) {
            distance = getDistance(d, d2, this.mCurWarningArea.lat, this.mCurWarningArea.lng);
            if (distance < ((float) this.mCurWarningArea.radius)) {
                this.mCurWarningState = DJIWarningAreaState.InnerLimit;
                return this.mCurWarningState;
            } else if (distance < ((float) (this.mCurWarningArea.radius + 100))) {
                return this.mCurWarningState;
            } else {
                this.mCurWarningState = DJIWarningAreaState.None;
            }
        }
        FlyForbidElement flyForbidElement = null;
        if (this.mCurWarningState == DJIWarningAreaState.None && !this.mWarningAreaList.isEmpty()) {
            FlyForbidElement flyForbidElement2;
            int size = this.mWarningAreaList.size() - 1;
            while (size >= 0) {
                flyForbidElement2 = (FlyForbidElement) this.mWarningAreaList.get(size);
                float distance2 = getDistance(d, d2, flyForbidElement2.lat, flyForbidElement2.lng);
                if (distance2 < ((float) flyForbidElement2.radius)) {
                    break;
                }
                if (flyForbidElement != null || distance2 >= ((float) (flyForbidElement2.radius + 100))) {
                    flyForbidElement2 = flyForbidElement;
                }
                size--;
                flyForbidElement = flyForbidElement2;
            }
            flyForbidElement2 = null;
            if (flyForbidElement2 != null) {
                this.mCurWarningArea = flyForbidElement2;
                this.mCurWarningState = DJIWarningAreaState.InnerLimit;
            } else if (flyForbidElement != null) {
                this.mCurWarningArea = flyForbidElement;
                this.mCurWarningState = DJIWarningAreaState.NearLimit;
            }
        }
        return this.mCurWarningState;
    }

    public FlyForbidElement getCurWarningArea() {
        return this.mCurWarningArea;
    }

    public List<FlyForbidElement> getFlyForbidElementsByIds(ArrayList<Integer> arrayList) {
        double latitude = DataOsdGetPushCommon.getInstance().getLatitude();
        double longitude = DataOsdGetPushCommon.getInstance().getLongitude();
        if (arrayList == null || arrayList.size() == 0) {
            return null;
        }
        if (this.mCheckResultList == null || this.mCheckResultList.isEmpty()) {
            checkNearFlyForbidArea(latitude, longitude);
        }
        if (this.mCheckResultList == null) {
            return null;
        }
        List<FlyForbidElement> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Integer num = (Integer) it.next();
            for (FlyForbidElement flyForbidElement : this.mCheckResultList) {
                FlyForbidElement flyForbidElement2;
                if (flyForbidElement2.area_id == num.intValue()) {
                    arrayList2.add(flyForbidElement2);
                } else {
                    flyForbidElement2 = new FlyForbidElement();
                    flyForbidElement2.area_id = num.intValue();
                    arrayList2.add(flyForbidElement2);
                }
            }
        }
        return arrayList2;
    }

    public List<FlyForbidElement> removeItemFromCheckResult(double d, double d2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Object obj = null;
        float f = AutoScrollHelper.NO_MAX;
        Object obj2 = null;
        if (this.mCheckResultList == null || this.mCheckResultList.isEmpty()) {
            checkNearFlyForbidArea(d, d2);
        }
        if (this.mCheckResultList != null) {
            int size = this.mCheckResultList.size() - 1;
            while (size >= 0) {
                Object obj3;
                float f2;
                Object obj4;
                FlyForbidElement flyForbidElement = (FlyForbidElement) this.mCheckResultList.get(size);
                if (flyForbidElement.level != LevelType.CAN_UNLIMIT.value()) {
                    obj3 = obj2;
                    f2 = f;
                    obj4 = obj;
                } else {
                    Object obj5;
                    float distance = getDistance(d, d2, flyForbidElement.lat, flyForbidElement.lng);
                    if (distance < ((float) flyForbidElement.radius)) {
                        obj5 = 1;
                    } else {
                        obj5 = obj2;
                    }
                    if (((double) distance) < ((double) flyForbidElement.radius) + FlyForbidProtocol.UNLOCK_RADIUS) {
                        arrayList.add(flyForbidElement);
                        DJILogHelper.getInstance().LOGD("nfz", "remove inner id: " + flyForbidElement.area_id, false, true);
                    }
                    if (Math.abs(distance - ((float) flyForbidElement.radius)) < f) {
                        Object obj6 = obj5;
                        f2 = Math.abs(distance - ((float) flyForbidElement.radius));
                        FlyForbidElement flyForbidElement2 = flyForbidElement;
                        obj3 = obj6;
                    } else {
                        obj3 = obj5;
                        obj4 = obj;
                        f2 = f;
                    }
                }
                size--;
                obj2 = obj3;
                f = f2;
                obj = obj4;
            }
            if (!arrayList.isEmpty()) {
                DJILogHelper.getInstance().LOGD("nfz", "remove size: " + arrayList.size(), false, true);
            }
        }
        if (obj != null) {
            arrayList2.add(obj);
        }
        if (obj2 != null) {
            return arrayList;
        }
        return arrayList2;
    }

    public List<FlyForbidElement> getCurUnlockableAreasAround() {
        return this.mCurUnlockableAreasAround;
    }

    public int checkAreaNumAround(double d, double d2) {
        int i = 0;
        this.mCurUnlockableAreasAround.clear();
        if (this.mCheckResultList == null || this.mCheckResultList.isEmpty()) {
            checkNearFlyForbidArea(d, d2);
        }
        if (this.mCheckResultList == null) {
            return 0;
        }
        float f = AutoScrollHelper.NO_MAX;
        FlyForbidElement flyForbidElement = null;
        int size = this.mCheckResultList.size() - 1;
        while (size >= 0) {
            float f2;
            int i2;
            FlyForbidElement flyForbidElement2 = (FlyForbidElement) this.mCheckResultList.get(size);
            if (flyForbidElement2.level == LevelType.STRONG_WARNING.value()) {
                flyForbidElement2 = flyForbidElement;
                f2 = f;
                i2 = i;
            } else if (flyForbidElement2.level == LevelType.WARNING.value()) {
                flyForbidElement2 = flyForbidElement;
                f2 = f;
                i2 = i;
            } else {
                f2 = getDistance(d, d2, flyForbidElement2.lat, flyForbidElement2.lng);
                if (f2 < ((float) flyForbidElement2.radius) && flyForbidElement2.level == LevelType.CAN_NOT_UNLIMIT.value()) {
                    return 0;
                }
                int i3;
                FlyForbidElement flyForbidElement3;
                float f3;
                if (((double) f2) >= ((double) flyForbidElement2.radius) + FlyForbidProtocol.UNLOCK_RADIUS || flyForbidElement2.level != LevelType.CAN_UNLIMIT.value()) {
                    i3 = i;
                } else {
                    NFZLogUtil.LOGD("" + flyForbidElement2.area_id);
                    this.mCurForbidArea = flyForbidElement2;
                    this.mCurUnlockableAreasAround.add(flyForbidElement2);
                    i3 = i + 1;
                }
                float f4 = f2 - ((float) flyForbidElement2.radius);
                if (f4 < f) {
                    flyForbidElement3 = flyForbidElement2;
                    f3 = f4;
                } else {
                    flyForbidElement3 = flyForbidElement;
                    f3 = f;
                }
                if (f4 > f3 || flyForbidElement2.level != LevelType.CAN_NOT_UNLIMIT.value()) {
                    flyForbidElement2 = flyForbidElement3;
                    i2 = i3;
                    f2 = f3;
                } else {
                    f2 = f4;
                    i2 = i3;
                }
            }
            size--;
            flyForbidElement = flyForbidElement2;
            f = f2;
            i = i2;
        }
        if (flyForbidElement == null || flyForbidElement.level != LevelType.CAN_NOT_UNLIMIT.value()) {
            return i;
        }
        return -1;
    }

    public List<FlyForbidElement> getCurSWAreasAround() {
        return this.mCurSWAreasAround;
    }

    public int checkSWAreasAround(double d, double d2) {
        int i = 0;
        this.mCurSWAreasAround.clear();
        if (this.mCheckResultList == null || this.mCheckResultList.isEmpty()) {
            checkNearFlyForbidArea(d, d2);
        }
        if (this.mCheckResultList != null) {
            int size = this.mCheckResultList.size() - 1;
            while (size >= 0) {
                int i2;
                FlyForbidElement flyForbidElement = (FlyForbidElement) this.mCheckResultList.get(size);
                if (flyForbidElement.level != LevelType.STRONG_WARNING.value()) {
                    i2 = i;
                } else {
                    if (((double) getDistance(d, d2, flyForbidElement.lat, flyForbidElement.lng)) < ((double) flyForbidElement.radius) + FlyForbidProtocol.STRONG_WARNING_CHECK_RADIUS) {
                        NFZLogUtil.LOGD("" + flyForbidElement.area_id);
                        this.mCurSWAreasAround.add(flyForbidElement);
                        i2 = i + 1;
                    } else {
                        i2 = i;
                    }
                }
                size--;
                i = i2;
            }
        }
        return i;
    }

    public void removeArrayFromCheckResult(List<FlyForbidElement> list, String str, String str2) {
        long currentTimeSencs = currentTimeSencs();
        for (FlyForbidElement flyForbidElement : list) {
            Object unlimitAreaRecordElement = new UnlimitAreaRecordElement(flyForbidElement.area_id, currentTimeSencs / 1000, FlyForbidProtocol.UNLIMIT_AREA_EXPIRED_TIME + currentTimeSencs, str2, str, false);
            this.mHaveRemovedList.add(unlimitAreaRecordElement);
            mFinalDb.a(unlimitAreaRecordElement);
            this.mCheckResultList.remove(flyForbidElement);
            if (flyForbidElement.type == 13) {
                unlimitAreaRecordElement = new StrongWarningUnlockedElement(flyForbidElement.area_id, currentTimeSencs(), currentTimeSencs() + FlyForbidProtocol.UNLIMIT_AREA_EXPIRED_TIME, str2);
                mFinalDb.a(unlimitAreaRecordElement);
                this.mStrongWarningUnlockedList.add(unlimitAreaRecordElement);
                this.mStrongWarningAreaList.remove(flyForbidElement);
                this.lastStrongWarningArea = null;
            }
        }
    }

    public List<FlyForbidElement> getCheckResult() {
        return this.mCheckResultList;
    }

    public FlyForbidElement findAreaByCoordinate(double d, double d2, int i) {
        if (this.mCheckResultList == null || this.mCheckResultList.isEmpty()) {
            checkNearFlyForbidArea(d, d2);
        }
        DJILogHelper.getInstance().LOGD("nfz", "findAreaByCoordinate forbidAreaState: " + i, false, true);
        FlyForbidElement flyForbidElement = null;
        if (this.mCheckResultList != null) {
            float f = AutoScrollHelper.NO_MAX;
            float f2 = AutoScrollHelper.NO_MAX;
            FlyForbidElement flyForbidElement2 = null;
            float f3 = AutoScrollHelper.NO_MAX;
            FlyForbidElement flyForbidElement3 = null;
            for (FlyForbidElement flyForbidElement4 : this.mCheckResultList) {
                FlyForbidElement flyForbidElement42;
                if (!(flyForbidElement42.level == LevelType.WARNING.value() || flyForbidElement42.level == LevelType.STRONG_WARNING.value())) {
                    float distance;
                    FlyForbidElement flyForbidElement5;
                    float f4;
                    float f5;
                    FlyForbidElement flyForbidElement6;
                    if (i == DJIFlightLimitAreaState.InnerLimit.value()) {
                        distance = getDistance(d, d2, flyForbidElement42.lat, flyForbidElement42.lng);
                        if (distance <= ((float) flyForbidElement42.radius)) {
                            if (distance < f2 && flyForbidElement42.level == LevelType.CAN_NOT_UNLIMIT.value()) {
                                DJILogHelper.getInstance().LOGD("nfz", "findAreaByCoordinate in red: id: " + flyForbidElement42.area_id + " dist: " + distance, false, true);
                                flyForbidElement5 = flyForbidElement42;
                                f4 = distance;
                                f5 = f;
                                flyForbidElement6 = flyForbidElement;
                                flyForbidElement42 = flyForbidElement3;
                                distance = f3;
                            } else if (distance < f3 && flyForbidElement42.level == LevelType.CAN_UNLIMIT.value()) {
                                DJILogHelper.getInstance().LOGD("nfz", "findAreaByCoordinate in yellow " + flyForbidElement42.area_id + " dist: " + distance, false, true);
                                flyForbidElement5 = flyForbidElement2;
                                f4 = f2;
                                f5 = f;
                                flyForbidElement6 = flyForbidElement;
                            }
                        }
                        flyForbidElement42 = flyForbidElement3;
                        distance = f3;
                        flyForbidElement5 = flyForbidElement2;
                        f4 = f2;
                        f5 = f;
                        flyForbidElement6 = flyForbidElement;
                    } else {
                        if (i == DJIFlightLimitAreaState.NearLimit.value() || i == DJIFlightLimitAreaState.InSlowDownArea.value() || i == DJIFlightLimitAreaState.InHalfLimit.value()) {
                            distance = Math.abs(getDistance(d, d2, flyForbidElement42.lat, flyForbidElement42.lng) - ((float) flyForbidElement42.radius));
                            if (distance < f) {
                                DJILogHelper.getInstance().LOGD("nfz", "findAreaByCoordinate near min", false, true);
                                flyForbidElement5 = flyForbidElement2;
                                f4 = f2;
                                f5 = distance;
                                flyForbidElement6 = flyForbidElement42;
                                flyForbidElement42 = flyForbidElement3;
                                distance = f3;
                            }
                        }
                        flyForbidElement42 = flyForbidElement3;
                        distance = f3;
                        flyForbidElement5 = flyForbidElement2;
                        f4 = f2;
                        f5 = f;
                        flyForbidElement6 = flyForbidElement;
                    }
                    flyForbidElement3 = flyForbidElement42;
                    f3 = distance;
                    flyForbidElement2 = flyForbidElement5;
                    f2 = f4;
                    f = f5;
                    flyForbidElement = flyForbidElement6;
                }
            }
            if (i == DJIFlightLimitAreaState.InnerLimit.value()) {
                if (flyForbidElement2 != null) {
                    flyForbidElement = flyForbidElement2;
                } else if (flyForbidElement3 != null) {
                    flyForbidElement = flyForbidElement3;
                }
            }
            if (flyForbidElement != null) {
                this.mCurForbidArea = flyForbidElement;
            }
        }
        return flyForbidElement;
    }

    public boolean needPushLimitData() {
        if (!this.mNeedPushLimitData) {
            return this.mNeedPushLimitData;
        }
        this.mNeedPushLimitData = false;
        return true;
    }

    public FlyForbidElement getCurForbidArea() {
        return this.mCurForbidArea;
    }

    private void bubbleSortForAirmapDatas(ArrayList<FlyForbidElementAirMap> arrayList, ArrayList<Double> arrayList2) {
        int size = arrayList2.size();
        Object obj = 1;
        while (obj != null) {
            int i = 0;
            obj = null;
            while (i != size - 1) {
                Object obj2;
                if (((Double) arrayList2.get(i)).doubleValue() > ((Double) arrayList2.get(i + 1)).doubleValue()) {
                    double doubleValue = ((Double) arrayList2.get(i)).doubleValue();
                    arrayList2.set(i, arrayList2.get(i + 1));
                    arrayList2.set(i + 1, Double.valueOf(doubleValue));
                    FlyForbidElementAirMap flyForbidElementAirMap = (FlyForbidElementAirMap) arrayList.get(i);
                    arrayList.set(i, arrayList.get(i + 1));
                    arrayList.set(i + 1, flyForbidElementAirMap);
                    obj2 = 1;
                } else {
                    obj2 = obj;
                }
                i++;
                obj = obj2;
            }
        }
    }

    private long getCurTimeStamp() {
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        long b = i.b(this.mContext, KEY_DJI_SERVER_TIME, 0);
        return (b == 0 || Math.abs(currentTimeMillis - b) <= 432000000) ? currentTimeMillis : b;
    }

    private List<FlyForbidElement> getNearFlyForbidAreaAirMap(double d, double d2, boolean z) {
        if (this.mDatabaseAirMapList == null || this.mDatabaseAirMapList.size() <= 0 || z) {
            try {
                this.mDatabaseAirMapList = getDataAround(FlyForbidElementAirMap.class, d, d2);
            } catch (Exception e) {
                NFZLogUtil.savedLOG(e.getMessage());
            }
            if (this.mDatabaseAirMapList == null || this.mDatabaseAirMapList.size() == 0) {
                NFZLogUtil.savedLOG("nfz log 3 f d a in slow");
                this.mDatabaseAirMapList = getDataAroundSlow(FlyForbidElementAirMap.class, d, d2);
            }
            if (this.mDatabaseAirMapList != null) {
                NFZLogUtil.savedLOG("nfz log 3 f d a" + this.mDatabaseAirMapList.size());
            } else {
                NFZLogUtil.savedLOG("nfz log 3 f d a null");
            }
            this.mNeedRefreshDatabase = false;
            this.mRefreshDBLastLat = d;
            this.mRefreshDBLastLng = d2;
            if (this.mDatabaseAirMapList == null) {
                return null;
            }
        }
        ArrayList arrayList = new ArrayList();
        long curTimeStamp = getCurTimeStamp();
        ArrayList arrayList2 = new ArrayList();
        for (FlyForbidElementAirMap flyForbidElementAirMap : this.mDatabaseAirMapList) {
            FlyForbidElementAirMap flyForbidElementAirMap2;
            if (flyForbidElementAirMap2.disable != 1) {
                double distance = (double) (getDistance(d, d2, flyForbidElementAirMap2.lat, flyForbidElementAirMap2.lng) - ((float) flyForbidElementAirMap2.radius));
                if (distance < FlyForbidProtocol.SEARCH_RADIUS && (flyForbidElementAirMap2.end_at == 0 || (flyForbidElementAirMap2.end_at != 0 && curTimeStamp > flyForbidElementAirMap2.begin_at && curTimeStamp < flyForbidElementAirMap2.end_at))) {
                    arrayList.add(flyForbidElementAirMap2);
                    arrayList2.add(Double.valueOf(distance));
                }
            }
        }
        if (arrayList2.size() > 100) {
            bubbleSortForAirmapDatas(arrayList, arrayList2);
        }
        if (arrayList.size() > 0) {
            if (this.mCheckResultList == null) {
                this.mCheckResultList = new ArrayList();
            } else if (!this.mCheckResultList.isEmpty()) {
                this.mCheckResultList.clear();
            }
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                flyForbidElementAirMap2 = (FlyForbidElementAirMap) it.next();
                FlyForbidElement flyForbidElement = new FlyForbidElement();
                flyForbidElement.id = flyForbidElementAirMap2.id;
                flyForbidElement.area_id = flyForbidElementAirMap2.area_id;
                flyForbidElement.type = flyForbidElementAirMap2.type;
                flyForbidElement.shape = flyForbidElementAirMap2.shape;
                flyForbidElement.lat = flyForbidElementAirMap2.lat;
                flyForbidElement.lng = flyForbidElementAirMap2.lng;
                flyForbidElement.radius = flyForbidElementAirMap2.radius;
                flyForbidElement.warning = flyForbidElementAirMap2.warning;
                flyForbidElement.level = flyForbidElementAirMap2.level;
                flyForbidElement.disable = flyForbidElementAirMap2.disable;
                flyForbidElement.updated_at = flyForbidElementAirMap2.updated_at;
                flyForbidElement.begin_at = flyForbidElementAirMap2.begin_at;
                flyForbidElement.end_at = flyForbidElementAirMap2.end_at;
                flyForbidElement.name = flyForbidElementAirMap2.name;
                flyForbidElement.country = flyForbidElementAirMap2.country;
                flyForbidElement.city = flyForbidElementAirMap2.city;
                flyForbidElement.points = flyForbidElementAirMap2.points;
                if (!(flyForbidElement.radius <= 65535 || flyForbidElement.level == LevelType.WARNING.value() || flyForbidElement.level == LevelType.STRONG_WARNING.value())) {
                    flyForbidElement.radius = 65535;
                }
                if (flyForbidElement.type == 14 || flyForbidElement.type == 31) {
                    flyForbidElement.radius = 8047;
                }
                if (flyForbidElement.level == LevelType.CAN_NOT_UNLIMIT.getSubType()) {
                    flyForbidElement.level = LevelType.CAN_NOT_UNLIMIT.value();
                }
                this.mCheckResultList.add(flyForbidElement);
            }
        }
        if (this.mCheckResultList != null && this.mCheckResultList.size() > 1) {
            Collections.sort(this.mCheckResultList, new DataComparator(this));
        }
        return this.mCheckResultList;
    }

    private List<FlyForbidElement> getNearFlyForbidArea(double d, double d2, boolean z) {
        if (this.mDatabaseList == null || this.mDatabaseList.size() <= 0 || z) {
            try {
                this.mDatabaseList = getDataAround(FlyForbidElement.class, d, d2);
            } catch (Exception e) {
            }
            if (this.mDatabaseList != null) {
                NFZLogUtil.savedLOG("nfz log 3 f d a dji" + this.mDatabaseList.size());
            } else {
                NFZLogUtil.savedLOG("nfz log 3 f d a dji null");
            }
            this.mRefreshDBLastLat = d;
            this.mRefreshDBLastLng = d2;
            this.mNeedRefreshDatabase = false;
            if (this.mDatabaseList == null || this.mDatabaseList.size() <= 0) {
                return null;
            }
        }
        if (this.mCheckResultList == null) {
            this.mCheckResultList = new ArrayList();
        }
        if (this.mCheckResultList.size() > 0) {
            this.mCheckResultList.clear();
        }
        long curTimeStamp = getCurTimeStamp();
        for (FlyForbidElement flyForbidElement : this.mDatabaseList) {
            if (flyForbidElement.disable != 1) {
                if (((double) getDistance(d, d2, flyForbidElement.lat, flyForbidElement.lng)) <= FlyForbidProtocol.SEARCH_RADIUS && (flyForbidElement.end_at == 0 || (flyForbidElement.end_at != 0 && curTimeStamp > flyForbidElement.begin_at && curTimeStamp < flyForbidElement.end_at))) {
                    this.mCheckResultList.add(flyForbidElement);
                }
            }
        }
        if (this.mCheckResultList.size() <= 0) {
            return null;
        }
        if (this.mCheckResultList.size() > 1) {
            Collections.sort(this.mCheckResultList, new DataComparator(this));
        }
        return this.mCheckResultList;
    }

    public float getDistance(double d, double d2, double d3, double d4) {
        float[] fArr = new float[1];
        Location.distanceBetween(d, d2, d3, d4, fArr);
        return fArr[0];
    }

    public void onEventBackgroundThread(NfzAccountEvent nfzAccountEvent) {
        if (nfzAccountEvent.getAreasChanged() == UnlimitAreasChanged.TRUE) {
            refreshRemovedList(nfzAccountEvent.getAccount(), nfzAccountEvent.getFlycsn());
            refreshSWUnlockedList(nfzAccountEvent.getFlycsn());
            if (this.mLastCheckedLat != 0.0d && this.mLastCheckedLng != 0.0d) {
                this.mCheckResultList.clear();
                c.a().e(DataFlycGetPushRequestLimitUpdate.getInstance());
                DJILogHelper.getInstance().LOGD("nfz", "UnlimitAreasChanged refresh checkNearFlyForbidArea", false, true);
            }
        }
    }
}
