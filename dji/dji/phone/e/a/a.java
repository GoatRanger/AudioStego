package dji.phone.e.a;

import dji.phone.e.b;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum a {
    ACTION_SCROLLING,
    ACTION_PRESS_LONG,
    TIMELAPSE_EXIT,
    TIMELAPSE_OVER,
    ACTION_PHONE_CALL,
    ACTION_MAIN_METER,
    ACTION_MAIN_TRACKING,
    ACTION_MAIN_CLEAR_POP_VIEW,
    ACTION_MAIN_DARKEN_SCREEN,
    ACTION_MAIN_LIGHTEN_SCREEN,
    ACTION_DIALOG_POPED,
    ACTION_EXIT_TLP,
    ACTION_EXIT_LONG_EXP,
    TLP_REQUEST_START,
    OTHER;
    
    public List<b> p;

    public a a(b... bVarArr) {
        if (this.p == null) {
            this.p = new ArrayList();
        }
        this.p.addAll(Arrays.asList(bVarArr));
        return this;
    }

    public List<b> a(c cVar) {
        return this.p;
    }
}
