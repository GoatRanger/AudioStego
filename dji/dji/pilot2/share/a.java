package dji.pilot2.share;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;
import dji.pilot.R;

public class a implements TextWatcher {
    private int a = 0;
    private EditText b = null;
    private Context c = null;
    private boolean d = false;
    private boolean e = false;
    private String f = null;
    private int g = 0;

    public a(Context context, int i, EditText editText, boolean z) {
        this.a = i;
        this.b = editText;
        this.c = context;
        this.d = z;
        this.e = false;
        this.f = "";
        this.g = 0;
    }

    public void afterTextChanged(Editable editable) {
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (!this.e) {
            this.f = charSequence.toString();
            this.g = this.b.getSelectionEnd();
        }
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        int length = this.b.getText().length();
        if (this.e) {
            this.e = false;
        } else if (length > this.a) {
            this.e = true;
            this.b.setText(this.f);
            this.b.setSelection(this.g);
            Toast.makeText(this.c, String.format(this.c.getResources().getString(R.string.v2_photo_share_title_too_many_letters), new Object[]{Integer.valueOf(this.a)}), 0).show();
        } else if (a(charSequence.toString())) {
            this.e = true;
            this.b.setText(this.f);
            this.b.setSelection(this.g);
            Toast.makeText(this.c, this.c.getResources().getString(R.string.v2_explore_comment_not_emotion_icon), 0).show();
        }
    }

    private boolean a(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!a(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    private boolean a(char c) {
        return c == '\u0000' || c == '\t' || c == '\n' || c == '\r' || ((c >= ' ' && c <= '퟿') || ((c >= '' && c <= '�') || (c >= '\u0000' && c <= '￿')));
    }
}
