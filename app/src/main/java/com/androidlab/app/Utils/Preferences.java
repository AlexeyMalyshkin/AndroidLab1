package com.androidlab.app.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.androidlab.app.R;

/**
 * Created by Eternal on 02.12.2014.
 */
public class Preferences {
    private final static String FONT_STYLE = "FONT_STYLE";

    private final Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    protected SharedPreferences open() {
        return context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }

    protected SharedPreferences.Editor edit() {
        return open().edit();
    }

    public FontStyle getFontStyle() {
        return FontStyle.valueOf(open().getString(FONT_STYLE,
                FontStyle.Large.name()));
    }

    public void setFontStyle(FontStyle style) {
        edit().putString(FONT_STYLE, style.name()).commit();
    }
}