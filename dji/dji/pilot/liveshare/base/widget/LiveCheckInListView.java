package dji.pilot.liveshare.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import dji.thirdparty.a.c;
import java.util.List;

public class LiveCheckInListView extends ListView implements OnItemClickListener {
    private static final String TAG = "LiveBaseCheckInView";
    private a adapter;
    private dji.pilot.f.a.a checkInSelectEvent = new dji.pilot.f.a.a(259);
    private dji.pilot.liveshare.base.view.LiveBaseCheckInView.a noPlace = new dji.pilot.liveshare.base.view.LiveBaseCheckInView.a(null, null);
    private List<dji.pilot.liveshare.base.view.LiveBaseCheckInView.a> placesList;

    private class a extends BaseAdapter {
        private Context context;

        public a(Context context) {
            this.context = context;
        }

        public int getCount() {
            return (LiveCheckInListView.this.placesList == null ? 0 : LiveCheckInListView.this.placesList.size()) + 1;
        }

        public Object getItem(int i) {
            return (i == 0 || LiveCheckInListView.this.placesList == null || LiveCheckInListView.this.placesList.size() > i + 1) ? LiveCheckInListView.this.noPlace : (dji.pilot.liveshare.base.view.LiveBaseCheckInView.a) LiveCheckInListView.this.placesList.get(i + 1);
        }

        public long getItemId(int i) {
            return (long) i;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            View liveCheckinSelectItem;
            if (view == null) {
                liveCheckinSelectItem = new LiveCheckinSelectItem(this.context);
            } else {
                liveCheckinSelectItem = view;
            }
            LiveCheckinSelectItem liveCheckinSelectItem2 = (LiveCheckinSelectItem) liveCheckinSelectItem;
            if (i == 0) {
                liveCheckinSelectItem2.setPlace(LiveCheckInListView.this.noPlace);
            } else {
                liveCheckinSelectItem2.setPlace((dji.pilot.liveshare.base.view.LiveBaseCheckInView.a) LiveCheckInListView.this.placesList.get(i - 1));
            }
            liveCheckinSelectItem2.setItemSelected(i == LiveCheckInListView.this.checkPlaceInPlacesList(dji.pilot.f.a.a.G) + 1);
            return liveCheckinSelectItem;
        }
    }

    public LiveCheckInListView(Context context) {
        super(context);
        init(context);
    }

    public LiveCheckInListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public LiveCheckInListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void setPlacesList(List<dji.pilot.liveshare.base.view.LiveBaseCheckInView.a> list) {
        this.placesList = list;
        updateView();
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return super.onTouchEvent(motionEvent);
    }

    private void init(Context context) {
        this.adapter = new a(context);
        setAdapter(this.adapter);
        setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        onSelectItem(i, (LiveCheckinSelectItem) view);
    }

    private void onSelectItem(int i, LiveCheckinSelectItem liveCheckinSelectItem) {
        dji.pilot.liveshare.base.view.LiveBaseCheckInView.a place = liveCheckinSelectItem.getPlace();
        dji.pilot.f.a.a.G = place.id;
        dji.pilot.f.a.a.H = place.name;
        c.a().e(this.checkInSelectEvent);
    }

    public void updateView() {
        post(new Runnable() {
            public void run() {
                LiveCheckInListView.this.adapter.notifyDataSetChanged();
            }
        });
    }

    private int checkPlaceInPlacesList(String str) {
        if (this.placesList == null || str == null) {
            return -1;
        }
        for (int i = 0; i < this.placesList.size(); i++) {
            if (str.equals(((dji.pilot.liveshare.base.view.LiveBaseCheckInView.a) this.placesList.get(i)).id)) {
                return i;
            }
        }
        return -1;
    }
}
