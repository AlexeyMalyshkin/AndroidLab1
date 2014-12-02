package com.androidlab.app.db;

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
