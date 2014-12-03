package com.androidlab.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import com.androidlab.app.R;
import com.androidlab.app.Utils.Utils;
import com.androidlab.app.constant.Priority;
import com.androidlab.app.db.NoteService;
import com.androidlab.app.domain.Note;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddNoteActivity extends Activity {

    private ImageView imageView;
    private Note note;
    private static boolean editMode = false;

    private static final Map<String, Priority> map = new HashMap<String, Priority>(){{
        put("low", Priority.LOW);
        put("middle", Priority.MIDDLE);
        put("high", Priority.HIGH);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.add_note_layout);
        String[] data = {"low", "middle", "high"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.prioritySpinner);
        spinner.setAdapter(adapter);

        imageView = (ImageView) findViewById(R.id.imageView);

        // ON EDIT:

        Note editNote = getIntent().getParcelableExtra("note");
        if(editNote!=null){
            editMode = true;
            note = editNote;

            EditText titleEditText = (EditText) findViewById(R.id.titleAddNote);
            titleEditText.setText(note.getTitle());

            EditText descriptionEditText = (EditText) findViewById(R.id.descriptionAddNote);
            descriptionEditText.setText(note.getDescription());

            if(note.getImageId()!=null) {
                if(!note.getImageId().isEmpty()) {
                    imageView.setImageURI(Uri.parse(note.getImageId()));
                }
            }

            spinner.setSelection(1,true);

            CalendarView calendarView = (CalendarView) findViewById(R.id.calendarAddNote);
            calendarView.setDate(note.getDateTime().getTime());
        }
    }

    public void imageClick(View view){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 0);
    }

    public void addNote(View view){
        EditText titleEditText = (EditText) findViewById(R.id.titleAddNote);
        EditText descriptionEditText = (EditText) findViewById(R.id.descriptionAddNote);
        Spinner spinner = (Spinner) findViewById(R.id.prioritySpinner);
        CalendarView calendarView = (CalendarView) findViewById(R.id.calendarAddNote);

        if(note == null){
            note = new Note();
        }

        note.setImageId((String) imageView.getTag());
        note.setTitle(titleEditText.getText().toString());
        note.setDescription(descriptionEditText.getText().toString());

        String selectedSpinnerItem = (String) spinner.getSelectedItem();
        note.setPriority(map.get(selectedSpinnerItem));

        note.setDateTime(new Date(calendarView.getDate()));

        // db usage:

        NoteService service = new NoteService(getApplicationContext());

        if(editMode){
            service.update(note);
            editMode = false;
        } else {
            service.put(note);
        }

        Intent intent = new Intent(getApplicationContext(), NotesActivity.class);

        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data!=null) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                imageView.setImageURI(imageUri);
                imageView.setTag(imageUri.toString());
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


}
