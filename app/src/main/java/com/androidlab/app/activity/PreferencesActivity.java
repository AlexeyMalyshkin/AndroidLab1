//package com.androidlab.app.activity;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.widget.Spinner;
//
//import com.androidlab.app.R;
//import com.androidlab.app.Utils.FontStyle;
//import com.androidlab.app.Utils.Preferences;
//
///**
//* Created by Eternal on 02.12.2014.
//*/
//public class PreferencesActivity extends Activity {
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        getTheme().applyStyle(new Preferences(this).getFontStyle().getResId(), true);
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.preferences);
//
//        Preferences prefs = new Preferences(this);
//
//        Spinner fontStylesView = (Spinner) findViewById(R.id.font_styles);
//        FontStylesAdapter adapter = new FontStylesAdapter(this,
//                R.layout.font_styles_row, FontStyle.values());
//        fontStylesView.setAdapter(adapter);
//
//        fontStylesView.setSelection(prefs.getFontStyle().ordinal());
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getSupportMenuInflater();
//        inflater.inflate(R.menu.preferences, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_done:
//                onMenuDone();
//                finish();
//                return true;
//            case R.id.menu_cancel:
//                finish();
//                return true;
//            default:
//                return false;
//        }
//    }
//
//    private void onMenuDone() {
//        Preferences prefs = new Preferences(this);
//
//        Spinner fontStylesView = (Spinner) findViewById(R.id.font_styles);
//        prefs.setFontStyle((FontStyle) fontStylesView.getSelectedItem());
//    }
//}
