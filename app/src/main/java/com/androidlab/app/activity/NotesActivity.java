package com.androidlab.app.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import com.androidlab.app.R;
import com.androidlab.app.Utils.Utils;
import com.androidlab.app.adapter.NoteAdapter;
import com.androidlab.app.constant.Priority;
import com.androidlab.app.db.NoteService;
import com.androidlab.app.domain.Note;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotesActivity extends Activity implements AdapterView.OnItemClickListener {

    private ListView notesListView;
    private static List<Note> noteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.notes_layout);
        NoteService service = new NoteService(getApplicationContext());

        notesListView = (ListView) findViewById(R.id.notesListView);
        noteList = service.getAll();

        NoteAdapter adapter = new NoteAdapter(noteList, this, R.layout.note_layout);
        notesListView.setAdapter(adapter);
        notesListView.setOnItemClickListener(this);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Choose an Option");
        builder.setItems(R.array.new_note_arr, dialogListener);
        AlertDialog newBtnDialog = builder.create();

        registerForContextMenu(notesListView);
        builder.setTitle("Sort by");
        builder.setItems(R.array.sort_options_arr, dialogListener);



        // db test:

            NoteService noteService = new NoteService(this);

            Note note1 = new Note();
            note1.setDateTime(new Date(0));
            note1.setDescription("note1desc");
            note1.setPriority(Priority.HIGH);
            note1.setTitle("note1title");

            noteService.put(note1);
            System.out.println("test");



    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.contextmenu_browse, menu);
        menu.setHeaderTitle("Choose an Option");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id = (int) info.id;

        switch (item.getItemId()) {
            case R.id.menu_edit:
                Note note = new Note();
                for (int i = 0; i < noteList.size(); i++) {
                    if (noteList.get(i).getId() == id) {
                        note = noteList.get(i);

                        Intent intent = new Intent(getApplicationContext(), AddNoteActivity.class);
                        intent.putExtra("note", note);

                        startActivity(intent);
                    }
                }

                break;

            case R.id.menu_delete:
                Note note2 = new Note();
                for (int i = 0; i < noteList.size(); i++) {
                    if (noteList.get(i).getId() == id) {
                        note2 = noteList.get(i);
                    }
                }

                NoteService service = new NoteService(getApplicationContext());
                service.delete(note2);

                noteList = service.getAll();

                NoteAdapter adapter = new NoteAdapter(noteList, this, R.layout.note_layout);
                notesListView.setAdapter(adapter);

                break;
        }
        return true;
    }

    private String lastSearchQuery = "";
    private String lastFilterQuery = "";

    private String[] data = {"All", "low", "middle", "high"};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);

        getMenuInflater().inflate(R.menu.note, menu);

        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));

        MenuItem mSpinnerItem1 = menu.findItem(R.id.menu_spinner1);
        View view1 = mSpinnerItem1.getActionView();
        if (view1 instanceof Spinner) {
            final Spinner spinner = (Spinner) view1;

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
            spinner.setAdapter(adapter);


            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                @Override
                public void onItemSelected(AdapterView<?> arg0, View arg1,
                                           int arg2, long arg3) {
                    lastFilterQuery = data[arg2];
                    repopulateList(lastSearchQuery, data[arg2]);
                    //  showDialog(arg2, arg3);
                    // TODO Auto-generated method stub

                }

                @Override
                public void onNothingSelected(AdapterView<?> arg0) {
                    // TODO Auto-generated method stub

                }
            });

        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                lastSearchQuery = s;
                repopulateList(s, lastFilterQuery);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                lastSearchQuery = s;

                repopulateList(s, lastFilterQuery);
                return true;
            }
        });
        return true;
    }

    private void repopulateList(String s, String f) {
        NoteService service = new NoteService(getApplicationContext());
        List<Note> noteList1 = service.getAll();
        List<Note> noteListResult = new ArrayList<Note>();
        for (int i = 0; i < noteList1.size(); i++) {
            if (noteList1.get(i).getTitle().contains(s)) {
                switch (f) {
                    case "low": {
                        if (noteList1.get(i).getPriority() != Priority.LOW)
                            continue;
                    }
                    case "middle": {
                        if (noteList1.get(i).getPriority() != Priority.MIDDLE)
                            continue;
                    }
                    case "high": {
                        if (noteList1.get(i).getPriority() != Priority.HIGH)
                            continue;
                    }
                }
                noteListResult.add(noteList1.get(i));

            }
        }

        setNoteAdapter(noteListResult);
    }


    private void setNoteAdapter(List<Note> list) {
        NoteAdapter adapter = new NoteAdapter(list, this, R.layout.note_layout);
        notesListView.setAdapter(adapter);
        notesListView.setOnItemClickListener(this);
    }

    private DialogInterface.OnClickListener dialogListener = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(getApplicationContext(), AddNoteActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
