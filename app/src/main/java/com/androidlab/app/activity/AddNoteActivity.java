package com.androidlab.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import com.androidlab.app.R;

public class AddNoteActivity extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_note_layout);


        String[] data = {"low", "middle", "high"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.prioritySpinner);
        spinner.setAdapter(adapter);

        imageView = (ImageView) findViewById(R.id.imageView);


    }

    public void imageClick(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);

        Uri uri = intent.getData();
        imageView.setImageURI(uri);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri imageUri = data.getData();
        imageView.setImageURI(imageUri);

        super.onActivityResult(requestCode, resultCode, data);
    }
}
