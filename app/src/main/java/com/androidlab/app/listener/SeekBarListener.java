package com.androidlab.app.listener;

import android.graphics.Color;
import android.widget.SeekBar;
import android.widget.TextView;
import com.androidlab.app.constant.ColorsConstants;

public class SeekBarListener implements SeekBar.OnSeekBarChangeListener  {

    private TextView currentColorTextView;

    public static int red = 0;
    public static int blue = 0;
    public static int green = 0;

    public SeekBarListener(TextView currentColorTextView){
        this.currentColorTextView = currentColorTextView;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        String seekBarColor = seekBar.getTag().toString();

        if(ColorsConstants.RED.equals(seekBarColor)) {
            red = i;
        } else if(ColorsConstants.GREEN.equals(seekBarColor)) {
            green = i;
        } else if (ColorsConstants.BLUE.equals(seekBarColor)) {
            blue = i;
        }

        currentColorTextView.setBackgroundColor(Color.rgb(red, green, blue));

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public TextView getCurrentColorTextView() {
        return currentColorTextView;
    }

    public void setCurrentColorTextView(TextView currentColorTextView) {
        this.currentColorTextView = currentColorTextView;
    }
}
