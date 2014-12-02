package com.androidlab.app.db;

<<<<<<< HEAD
import android.database.SQLException;
import com.androidlab.app.domain.Note;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.util.List;

public class NoteService {
    private Dao<Note, String> dao;

    public NoteService() throws java.sql.SQLException {
        String url = "jdbc:sqlite:main.sqlite";
        ConnectionSource source = new JdbcConnectionSource(url);
        dao = DaoManager.createDao(source, Note.class);
    }

    public List<Note> getAll() throws SQLException, java.sql.SQLException {
        return dao.queryForAll();
    }

    public void put(Note note) throws java.sql.SQLException {
        dao.create(note);
    }
}
=======
import android.content.Context;
import com.androidlab.app.domain.Note;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteService {
    private DatabaseHelper helper;

    public NoteService(Context context){
        helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }


    public List<Note> getAll()  {
        try {
            return helper.getDao(Note.class).queryForAll();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    public void put(Note note) {
        try {
            helper.getDao(Note.class).create(note);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Note note){
        try {
            helper.getDao(Note.class).update(note);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Note note){
        try {
            helper.getDao(Note.class).delete(note);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
>>>>>>> a9b5c11f7413475fc0f2605e97862557cb8930db
