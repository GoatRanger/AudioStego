package dji.pilot.liveshare.base.view;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphRequest.b;
import com.facebook.v;
import com.facebook.w;
import com.here.odnp.config.OdnpConfigStatic;
import dji.pilot.R;
import dji.pilot.liveshare.base.widget.LiveCheckInListView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.publics.DJIUI.DJITextView;
import dji.thirdparty.a.c;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LiveBaseCheckInView extends RelativeLayout implements OnClickListener {
    private static final int FETCH_LIST_TIMEOUT = 3000;
    private static final int GET_LOC_RETRY_INTERVAL = 100;
    private static final int GET_LOC_RETRY_NUM = 50;
    private static final int MSG_FETCH_TIMEOUT = 0;
    private ImageButton backIb;
    private dji.pilot.f.a.a checkInFetchFailedEvent = new dji.pilot.f.a.a(261);
    private dji.pilot.f.a.a checkInFetchSuccessEvent = new dji.pilot.f.a.a(260);
    private LiveCheckInListView checkInLv;
    private DJITextView doneTv;
    private DJIRelativeLayout fetchFailedLy;
    private DJITextView fetchFailedReasonTv;
    private DJIRelativeLayout fetchingLy;
    private AtomicBoolean isFetchingPlacesList = new AtomicBoolean(false);
    private String lastPlaceId;
    private String lastPlaceName;
    private Handler timeoutCheckHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    if (LiveBaseCheckInView.this.isFetchingPlacesList.getAndSet(false)) {
                        LiveBaseCheckInView.this.checkInFetchFailedEvent.M = LiveBaseCheckInView.this.getResources().getString(R.string.liveshare_checkin_fetch_failed_timeout);
                        c.a().e(LiveBaseCheckInView.this.checkInFetchFailedEvent);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public static class a {
        public String id;
        public String name;

        public a(String str, String str2) {
            this.id = str;
            this.name = str2;
        }
    }

    public LiveBaseCheckInView(Context context) {
        super(context);
        init(context);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!c.a().c(this)) {
            c.a().a(this);
        }
        this.lastPlaceId = dji.pilot.f.a.a.G;
        this.lastPlaceName = dji.pilot.f.a.a.H;
        startFetchList();
    }

    protected void onDetachedFromWindow() {
        this.isFetchingPlacesList.set(false);
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.fpv_liveshare_base_check_in, this);
        this.checkInLv = (LiveCheckInListView) findViewById(R.id.a5b);
        this.backIb = (ImageButton) findViewById(R.id.a57);
        this.backIb.setOnClickListener(this);
        this.doneTv = (DJITextView) findViewById(R.id.a58);
        this.doneTv.setOnClickListener(this);
        this.fetchingLy = (DJIRelativeLayout) findViewById(R.id.a59);
        this.fetchFailedLy = (DJIRelativeLayout) findViewById(R.id.a5_);
        this.fetchFailedLy.setOnClickListener(this);
        this.fetchFailedReasonTv = (DJITextView) findViewById(R.id.a5a);
    }

    private void updatePlaces() {
        if (!this.isFetchingPlacesList.get()) {
            this.isFetchingPlacesList.set(true);
            new Thread(new Runnable() {
                public void run() {
                    dji.pilot.phonecamera.c c;
                    Location e;
                    Location location = null;
                    double d = -181.0d;
                    try {
                        c = dji.phone.c.a.c();
                    } catch (IllegalStateException e2) {
                        c = null;
                    }
                    if (c == null) {
                        e = dji.a.a.getInstance().e();
                    } else {
                        int i = 0;
                        while (i < 50) {
                            location = c.y();
                            if (location != null) {
                                e = location;
                                break;
                            } else {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e3) {
                                }
                                i++;
                            }
                        }
                        e = location;
                    }
                    double latitude = e == null ? -181.0d : e.getLatitude();
                    if (e != null) {
                        d = e.getLongitude();
                    }
                    if (latitude < -180.0d || d < -180.0d) {
                        LiveBaseCheckInView.this.checkInFetchFailedEvent.M = LiveBaseCheckInView.this.getResources().getString(R.string.liveshare_checkin_fetch_failed_no_loc);
                        c.a().e(LiveBaseCheckInView.this.checkInFetchFailedEvent);
                        LiveBaseCheckInView.this.isFetchingPlacesList.set(false);
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("type", "place");
                    bundle.putString("center", latitude + "," + d);
                    GraphRequest graphRequest = new GraphRequest(AccessToken.a(), "/search", bundle, w.a, new b() {
                        public void onCompleted(v vVar) {
                            JSONObject b = vVar.b();
                            if (b == null) {
                                LiveBaseCheckInView.this.checkInFetchFailedEvent.M = LiveBaseCheckInView.this.getResources().getString(R.string.liveshare_checkin_fetch_failed_unknown);
                                c.a().e(LiveBaseCheckInView.this.checkInFetchFailedEvent);
                                LiveBaseCheckInView.this.isFetchingPlacesList.set(false);
                                return;
                            }
                            JSONObject jSONObject;
                            try {
                                jSONObject = b.getJSONObject("error");
                            } catch (JSONException e) {
                                e.printStackTrace();
                                jSONObject = null;
                            }
                            if (jSONObject != null) {
                                LiveBaseCheckInView.this.checkInFetchFailedEvent.M = LiveBaseCheckInView.this.getResources().getString(R.string.liveshare_checkin_fetch_failed_unknown);
                                c.a().e(LiveBaseCheckInView.this.checkInFetchFailedEvent);
                                LiveBaseCheckInView.this.isFetchingPlacesList.set(false);
                                return;
                            }
                            try {
                                JSONArray jSONArray = b.getJSONArray("data");
                                List arrayList = new ArrayList(jSONArray.length());
                                for (int i = 0; i < jSONArray.length(); i++) {
                                    arrayList.add(new a(jSONArray.getJSONObject(i).getString("id"), jSONArray.getJSONObject(i).getString("name")));
                                }
                                LiveBaseCheckInView.this.checkInLv.setPlacesList(arrayList);
                                if (LiveBaseCheckInView.this.isFetchingPlacesList.getAndSet(false)) {
                                    c.a().e(LiveBaseCheckInView.this.checkInFetchSuccessEvent);
                                }
                            } catch (JSONException e2) {
                                e2.printStackTrace();
                            }
                        }
                    });
                    LiveBaseCheckInView.this.isFetchingPlacesList.set(true);
                    graphRequest.n();
                    LiveBaseCheckInView.this.timeoutCheckHandler.sendEmptyMessageDelayed(0, OdnpConfigStatic.CELL_NO_CHANGE_LIMITER_TIME);
                }
            }).start();
        }
    }

    public void startFetchList() {
        updatePlaces();
        this.checkInLv.setVisibility(8);
        this.fetchFailedLy.setVisibility(8);
        this.fetchingLy.setVisibility(0);
    }

    private void onFetchFinished(boolean z, String str) {
        if (this.timeoutCheckHandler != null && this.timeoutCheckHandler.hasMessages(0)) {
            this.timeoutCheckHandler.removeMessages(0);
        }
        if (z) {
            this.checkInLv.setVisibility(0);
            this.fetchFailedLy.setVisibility(8);
            this.fetchingLy.setVisibility(8);
            return;
        }
        CharSequence string = getResources().getString(R.string.liveshare_checkin_fetch_failed_retry);
        DJITextView dJITextView = this.fetchFailedReasonTv;
        if (str != null) {
            string = str + ", " + string;
        }
        dJITextView.setText(string);
        this.checkInLv.setVisibility(8);
        this.fetchFailedLy.setVisibility(0);
        this.fetchingLy.setVisibility(8);
    }

    public void onEventMainThread(dji.pilot.f.a.a aVar) {
        switch (aVar.I) {
            case 260:
                onFetchFinished(true, null);
                return;
            case 261:
                onFetchFinished(false, aVar.M);
                return;
            default:
                return;
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.a57) {
            dji.pilot.f.a.a.G = this.lastPlaceId;
            dji.pilot.f.a.a.H = this.lastPlaceName;
        }
        if (id == R.id.a57 || id == R.id.a58) {
            c.a().e(new dji.pilot.f.a.a(4));
        }
        if (id == R.id.a5_) {
            startFetchList();
        }
    }
}
