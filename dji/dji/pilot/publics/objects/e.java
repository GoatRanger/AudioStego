package dji.pilot.publics.objects;

import android.text.InputFilter;
import android.text.Spanned;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

public class e implements InputFilter {
    private int a;

    public e(int i) {
        this.a = i;
    }

    public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        char[] toCharArray = charSequence.toString().toCharArray();
        byte[] bytes = charSequence.toString().getBytes(Charset.forName("UTF-8"));
        byte[] bytes2 = spanned.toString().getBytes(Charset.forName("UTF-8"));
        int length = bytes.length;
        int length2 = bytes2.length;
        if (length + length2 <= this.a) {
            return charSequence;
        }
        length = 0;
        while (length < toCharArray.length) {
            length2 += a(toCharArray[length]).length;
            if (length2 > this.a) {
                break;
            }
            length++;
        }
        if (length > 0) {
            return charSequence.subSequence(i, length);
        }
        return "";
    }

    private byte[] a(char c) {
        Charset forName = Charset.forName("UTF-8");
        CharBuffer allocate = CharBuffer.allocate(1);
        allocate.put(c);
        allocate.flip();
        return forName.encode(allocate).array();
    }
}
