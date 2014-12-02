package com.androidlab.app.db;

import android.content.Context;
import com.androidlab.app.domain.Note;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.util.List;

import android.content.Context;
import com.androidlab.app.domain.Note;
import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NoteService {
    private DatabaseHelper helper;

    public NoteService(Context context) {
        helper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
    }


    public List<Note> getAll() {
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
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Note note) {
        try {
            helper.getDao(Note.class).update(note);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Note note) {
        try {
            helper.getDao(Note.class).delete(note);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
}
