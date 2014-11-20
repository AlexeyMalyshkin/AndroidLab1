package com.androidlab.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.androidlab.app.R;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

//        Button colorsButton = (Button) findViewById(R.id.colors_button);
//        Button calcButton = (Button) findViewById(R.id.calc_button);
//        Button notesButton = (Button) findViewById(R.id.notes_button);
    }

    public void goToColorsActivity(View view) {
        startActivity(new Intent(this, ColorsActivity.class));
    }

    public void goToCalcActivity(View view) {
        startActivity(new Intent(this, CalcActivity.class));
    }

    public void goToNotesActivity(View view) {
        startActivity(new Intent(this, NotesActivity.class));
    }
}
