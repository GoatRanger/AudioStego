package cn.sharesdk.system.text;

import java.util.HashMap;

public interface ActionListener {
    void onComplete(HashMap<String, Object> hashMap);

    void onError(Throwable th);

    void onStart(HashMap<String, Object> hashMap);
}
