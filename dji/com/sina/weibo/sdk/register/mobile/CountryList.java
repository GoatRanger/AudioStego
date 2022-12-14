package com.sina.weibo.sdk.register.mobile;

import com.sina.weibo.sdk.exception.WeiboException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CountryList implements Serializable {
    private static final long serialVersionUID = 1;
    public List<Country> countries;

    public CountryList(String str) {
        initFromJsonStr(str);
    }

    private void initFromJsonStr(String str) {
        try {
            initFromJsonObject(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void initFromJsonObject(JSONObject jSONObject) throws WeiboException {
        try {
            this.countries = new ArrayList();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                JSONObject optJSONObject = jSONObject.optJSONObject(str);
                String string = optJSONObject.getString("code");
                JSONArray optJSONArray = optJSONObject.optJSONObject("rule").optJSONArray("mcc");
                String[] strArr = new String[optJSONArray.length()];
                for (int i = 0; i < optJSONArray.length(); i++) {
                    strArr[i] = optJSONArray.getString(i);
                }
                Country country = new Country(str, string);
                country.setMccs(strArr);
                this.countries.add(country);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            throw new WeiboException(e);
        }
    }
}
