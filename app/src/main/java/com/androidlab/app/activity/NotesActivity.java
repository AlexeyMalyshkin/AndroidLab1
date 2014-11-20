package com.androidlab.app.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.androidlab.app.R;
import com.androidlab.app.adapter.NoteAdapter;
import com.androidlab.app.constant.Priority;
import com.androidlab.app.domain.Note;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotesActivity extends Activity {

    private ListView notesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes_layout);

        notesListView = (ListView) findViewById(R.id.notesListView);


        Note note = getIntent().getParcelableExtra("note");
        List<Note> noteList = populateList();

        if (note != null) {
            noteList.add(note);
        }

        NoteAdapter adapter = new NoteAdapter(noteList, this, R.layout.note_layout);
        notesListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), AddNoteActivity.class);

            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Note> populateList() {
        return new ArrayList<Note>() {{
            add(new Note() {{
                setTitle("1title");
                setDateTime(new Date(0));
                setPriority(Priority.HIGH);
                setImageId("priority_high");
            }});
            add(new Note() {{
                setTitle("2title");
                setDateTime(new Date(0));
                setPriority(Priority.HIGH);
                setImageId("priority_high");
            }});
        }};
    }
}
