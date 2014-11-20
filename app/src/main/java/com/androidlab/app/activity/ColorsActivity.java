package com.androidlab.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import com.androidlab.app.R;
import com.androidlab.app.constant.ColorsConstants;
import com.androidlab.app.listener.SeekBarListener;

public class ColorsActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.colors_layout);

        TextView currentColorTextView = (TextView) findViewById(R.id.currentColor);

        SeekBar redSeekBar = (SeekBar) findViewById(R.id.redColorSeekBar);
        SeekBar blueSeekBar = (SeekBar) findViewById(R.id.blueColorSeekBar);
        SeekBar greenSeekBar = (SeekBar) findViewById(R.id.greenColorSeekBar);

        redSeekBar.setTag(ColorsConstants.RED);
        blueSeekBar.setTag(ColorsConstants.BLUE);
        greenSeekBar.setTag(ColorsConstants.GREEN);

        SeekBarListener listener = new SeekBarListener(currentColorTextView);

        redSeekBar.setOnSeekBarChangeListener(listener);
        blueSeekBar.setOnSeekBarChangeListener(listener);
        greenSeekBar.setOnSeekBarChangeListener(listener);
    }
}
