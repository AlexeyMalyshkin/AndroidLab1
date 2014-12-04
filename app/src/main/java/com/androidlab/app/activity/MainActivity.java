package com.androidlab.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.androidlab.app.R;
import com.androidlab.app.Utils.FontStyle;
import com.androidlab.app.Utils.Preferences;
import com.androidlab.app.Utils.Utils;


public class MainActivity extends Activity {
private static boolean isFirstRun = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(isFirstRun)
        {

            Preferences prefs = new Preferences(this);
            prefs.setFontStyle(FontStyle.Medium); isFirstRun = false;
        }

//
        super.onCreate(savedInstanceState);// Utils.onActivityCreateSetTheme(this);


      //  this.setTheme(R.style.AppTheme);
       // getTheme().applyStyle(new Preferences(this).getFontStyle().getResId(), true);

        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.main_layout);
//        Button colorsButton = (Button) findViewById(R.id.colors_button);
//        Button calcButton = (Button) findViewById(R.id.calc_button);
//        Button notesButton = (Button) findViewById(R.id.notes_button);
    }

    public void goToColorsActivity(View view) {
        startActivity(new Intent(this, ColorsActivity.class));
    }
    public void goToFragmentsActivity(View view) {
        startActivity(new Intent(this, FragmentLayout.class));
    }
    public void goToCalcActivity(View view) {
        startActivity(new Intent(this, CalcActivity.class));
    }

    public void goToNotesActivity(View view) {
        startActivity(new Intent(this, NotesActivity.class));
    }
    public void onThemeButtonClick(View v) {
        switch (v.getId()) {
            case R.id.defaultTheme: Utils.changeToTheme(this, Utils.THEME_DEFAULT); break;
          case R.id.CustomTheme: Utils.changeToTheme(this, Utils.THEME_WHITE); break;
          default:Utils.changeToTheme(this, Utils.THEME_WHITE); break;
//            case R.id.button3: Utils.changeToTheme(this, Utils.THEME_BLUE); break;
        }
    }
    public void onFontSizeButtonClick(View v) {
        switch (v.getId()) {
            case R.id.smallFont: {
                Preferences prefs = new Preferences(this);
                prefs.setFontStyle(FontStyle.Small);
            } break;
            case R.id.middleFont: {
                Preferences prefs = new Preferences(this);
                prefs.setFontStyle(FontStyle.Medium);
            } break;
            case R.id.largeFont: {
                Preferences prefs = new Preferences(this);
                prefs.setFontStyle(FontStyle.Large);
            } break;
            default:Utils.changeToTheme(this, Utils.THEME_WHITE); break;
//            case R.id.button3: Utils.changeToTheme(this, Utils.THEME_BLUE); break;
        }
    }


}
