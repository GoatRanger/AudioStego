package cn.sharesdk.onekeyshare.themes.classic.port;

import android.content.Context;
import cn.sharesdk.onekeyshare.themes.classic.PlatformPage;
import cn.sharesdk.onekeyshare.themes.classic.PlatformPageAdapter;
import com.mob.tools.utils.R;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class PlatformPageAdapterPort extends PlatformPageAdapter {
    private static final int DESIGN_LOGO_HEIGHT = 76;
    private static final int DESIGN_PADDING_TOP = 20;
    private static final int DESIGN_SCREEN_WIDTH_P = 720;
    private static final int DESIGN_SEP_LINE_WIDTH = 1;
    private static final int LINE_SIZE_P = 4;
    private static final int PAGE_SIZE_P = 12;

    public PlatformPageAdapterPort(PlatformPage platformPage, ArrayList<Object> arrayList) {
        super(platformPage, arrayList);
    }

    protected void calculateSize(Context context, ArrayList<Object> arrayList) {
        int i = 1;
        int screenWidth = R.getScreenWidth(context);
        this.lineSize = 4;
        float f = ((float) screenWidth) / 720.0f;
        this.sepLineWidth = (int) (1.0f * f);
        if (this.sepLineWidth >= 1) {
            i = this.sepLineWidth;
        }
        this.sepLineWidth = i;
        this.logoHeight = (int) (76.0f * f);
        this.paddingTop = (int) (20.0f * f);
        this.bottomHeight = (int) (52.0f * f);
        this.cellHeight = (screenWidth - (this.sepLineWidth * 3)) / 4;
        if (arrayList.size() <= this.lineSize) {
            this.panelHeight = this.cellHeight + this.sepLineWidth;
        } else if (arrayList.size() <= 12 - this.lineSize) {
            this.panelHeight = (this.cellHeight + this.sepLineWidth) * 2;
        } else {
            this.panelHeight = (this.cellHeight + this.sepLineWidth) * 3;
        }
    }

    protected void collectCells(ArrayList<Object> arrayList) {
        int i;
        int size = arrayList.size();
        if (size < 12) {
            i = size / this.lineSize;
            if (size % this.lineSize != 0) {
                i++;
            }
            this.cells = (Object[][]) Array.newInstance(Object.class, new int[]{1, i * this.lineSize});
        } else {
            i = size / 12;
            if (size % 12 != 0) {
                i++;
            }
            this.cells = (Object[][]) Array.newInstance(Object.class, new int[]{i, 12});
        }
        for (i = 0; i < size; i++) {
            int i2 = i / 12;
            this.cells[i2][i - (i2 * 12)] = arrayList.get(i);
        }
    }
}
