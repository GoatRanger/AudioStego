package dji.pilot2.nativeexplore.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.usercenter.b.f;
import dji.pilot2.explore.activity.DJIExploreCommentActivity;
import dji.pilot2.nativeexplore.b.b.a;
import dji.pilot2.nativeexplore.model.BottomCommentModel;
import dji.pilot2.nativeexplore.model.BottomCommentModel.CommentItemModel;
import dji.pilot2.nativeexplore.model.MiddleListModel;
import dji.pilot2.nativeexplore.model.MiddleListModel.MiddleItemModel;
import dji.pilot2.nativeexplore.model.TopTileModel;
import dji.pilot2.nativeexplore.model.b;
import dji.pilot2.publics.b.a$e;
import dji.pilot2.utils.k;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExploreGLView extends LinearLayout {
    private d a;
    private c b;
    private b c;
    private b d;
    private BottomCommentModel e;
    private EditText f;
    private dji.pilot2.nativeexplore.b.b g;
    private TextView h;
    private boolean i = false;
    private int j;
    private String k;
    private Context l;
    private TextWatcher m = new TextWatcher(this) {
        final /* synthetic */ ExploreGLView a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (this.a.i) {
                this.a.i = false;
            } else if (i3 >= 2) {
                CharSequence subSequence = charSequence.subSequence(i, i + i3);
                CharSequence subSequence2 = charSequence.subSequence(0, i);
                if (DJIExploreCommentActivity.a(subSequence.toString())) {
                    DJILogHelper.getInstance().LOGI("bob", "containsEmoji ");
                    this.a.i = true;
                    this.a.f.setText(subSequence2);
                    this.a.f.invalidate();
                    this.a.f.setSelection(i);
                    Toast.makeText(this.a.l, this.a.l.getString(R.string.v2_explore_comment_not_emotion_icon), 0).show();
                }
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!this.a.i) {
                this.a.j = this.a.f.getSelectionEnd();
                this.a.k = charSequence.toString();
            }
        }

        public void afterTextChanged(Editable editable) {
        }
    };

    public ExploreGLView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    public ExploreGLView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public ExploreGLView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ExploreGLView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.l = context;
        this.a = new d(null, context);
        this.b = new c(null, context);
        this.c = new b(null, context);
        this.g = dji.pilot2.nativeexplore.b.b.getInstance(context);
        this.g.a(new a(this) {
            final /* synthetic */ ExploreGLView a;

            {
                this.a = r1;
            }

            public void a(boolean z) {
                Log.i("zhang", "post:" + z);
            }

            public void a(boolean z, BottomCommentModel bottomCommentModel) {
            }

            public void b(boolean z) {
            }
        });
        setOrientation(1);
        addView(this.a.a());
        addView(this.b.a());
        addView(this.c.a());
        addView(initEditTextView(context));
    }

    public void addTitleData(TopTileModel topTileModel) {
        if (this.a != null) {
            this.a.b(topTileModel);
        }
    }

    public void setTopListListener(OnItemClickListener onItemClickListener) {
        if (this.a != null) {
            this.a.a(onItemClickListener);
        }
    }

    public b getSrcData() {
        return this.d;
    }

    public void addSrcData(b bVar) {
        this.d = bVar;
        TopTileModel topTileModel = new TopTileModel();
        topTileModel.avatarUrl = bVar.c.c;
        topTileModel.Content = bVar.e;
        topTileModel.ContentTitle = bVar.d;
        topTileModel.PicUrl = bVar.g;
        topTileModel.position = bVar.i + "," + bVar.h;
        topTileModel.title = bVar.d;
        topTileModel.id = bVar.c.a;
        topTileModel.account_name = bVar.c.b;
        topTileModel.country = bVar.c.e;
        topTileModel.isFollowed = bVar.o;
        List arrayList = new ArrayList();
        for (int i = 0; i < bVar.j; i++) {
            arrayList.add((i + 1) + "." + ((MiddleItemModel) bVar.q.items.get(i)).title);
        }
        topTileModel.positionList = arrayList;
        MiddleListModel middleListModel = bVar.q;
        middleListModel.location = topTileModel.position;
        addMiddleData(middleListModel);
        addTitleData(topTileModel);
    }

    public int getListPositionById(int i) {
        if (this.b != null) {
            return this.b.a(i);
        }
        return 0;
    }

    public int getTopPosition() {
        return this.a.c();
    }

    public void addCommentData(BottomCommentModel bottomCommentModel) {
        this.e = bottomCommentModel;
        if (this.e != null) {
            this.c.b(this.e);
        }
    }

    public void addMiddleData(MiddleListModel middleListModel) {
        if (this.b != null) {
            this.b.b(middleListModel);
        }
    }

    public void updateAllViews() {
        this.a.b();
        this.b.b();
        this.c.b();
    }

    public View initEditTextView(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.gl_editcomment_layout, null);
        this.f = (EditText) inflate.findViewById(R.id.ahv);
        this.f.addTextChangedListener(this.m);
        this.h = (TextView) inflate.findViewById(R.id.ahw);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ExploreGLView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String obj = this.a.f.getText().toString();
                if (obj != null && obj.length() > 0) {
                    this.a.g.a(k.a(this.a.d.b), obj);
                    this.a.f.setText("");
                    CommentItemModel commentItemModel = new CommentItemModel();
                    f instance = f.getInstance();
                    commentItemModel.avatar = a$e.a + instance.e();
                    commentItemModel.account_id = instance.m();
                    commentItemModel.created_at = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
                    commentItemModel.content = obj;
                    if (this.a.e.items == null) {
                        this.a.e.items = new ArrayList();
                    }
                    this.a.e.items.add(0, commentItemModel);
                    this.a.c.b(this.a.e);
                    this.a.c.b();
                }
            }
        });
        return inflate;
    }
}
