package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.byted.camp.todolist.beans.Note;
import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract.TodoEntry;
import com.byted.camp.todolist.db.TodoDbHelper;
import com.byted.camp.todolist.ui.NoteListAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_ADD = 1002;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.ENGLISH);
    private NoteListAdapter notesAdapter;

    private TodoDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(
                        new Intent(MainActivity.this, NoteActivity.class),
                        REQUEST_CODE_ADD);
            }
        });

        dbHelper = new TodoDbHelper(this);

        RecyclerView recyclerView = findViewById(R.id.list_todo);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        notesAdapter = new NoteListAdapter(new NoteOperator() {
            @Override
            public void deleteNote(Note note) {
                MainActivity.this.deleteNote(note);
            }

            @Override
            public void updateNote(Note note) {
                MainActivity.this.updateNode(note);
            }
        });
        recyclerView.setAdapter(notesAdapter);

        notesAdapter.refresh(loadNotesFromDatabase());
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ADD
                && resultCode == Activity.RESULT_OK) {
            notesAdapter.refresh(loadNotesFromDatabase());
        }
    }

    private List<Note> loadNotesFromDatabase() {
//        return null;
        // TODO 从数据库中查询数据，并转换成 JavaBeans
        List<Note> notes = new ArrayList<>();

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                TodoEntry.COLUMN_NAME_ID,
                TodoEntry.COLUMN_NAME_DATE,
                TodoEntry.COLUMN_NAME_STATE,
                TodoEntry.COLUMN_NAME_PRIORITY,
                TodoEntry.COLUMN_NAME_CONTENT
        };
        String orderBy = TodoEntry.COLUMN_NAME_PRIORITY + " DESC"; // order by pri
        Cursor cursor = db.query(
                TodoEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                orderBy
        );

        while (cursor.moveToNext()) {
            Note note = new Note(cursor.getLong(cursor.getColumnIndex(TodoEntry.COLUMN_NAME_ID)));
            try {
                note.setDate(SIMPLE_DATE_FORMAT.parse(cursor.getString(cursor.getColumnIndex(TodoEntry.COLUMN_NAME_DATE))));
            } catch (ParseException e) {
                note.setDate(null);
                Log.d("MainActivity", "loadNotesFromDatabase: " + e.getMessage());
            }
            note.setState(State.from(cursor.getInt(cursor.getColumnIndex(TodoEntry.COLUMN_NAME_STATE))));
            note.setPriority(Priority.from(cursor.getInt(cursor.getColumnIndex(TodoEntry.COLUMN_NAME_PRIORITY))));
            note.setContent(cursor.getString(cursor.getColumnIndex(TodoEntry.COLUMN_NAME_CONTENT)));

            notes.add(note);
        }
        cursor.close();

        return notes;
    }

    private void deleteNote(Note note) {
        // TODO 删除数据
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        // filter by id
        String selection = TodoEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {String.valueOf(note.id)};

        int deleteRows = db.delete(TodoEntry.TABLE_NAME, selection, selectionArgs);
        Log.d("MainActivity", "delete " + deleteRows + "row(s)\n");

        notesAdapter.refresh(loadNotesFromDatabase());
    }

    private void updateNode(Note note) {
        // 更新数据
        // 主要针对条目状态State字段更新
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TodoEntry.COLUMN_NAME_STATE, note.getState().intValue);
        String selectoon = TodoEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {String.valueOf(note.id)};
        int updateRows = db.update(TodoEntry.TABLE_NAME, values, selectoon, selectionArgs);

        Log.d("MainActivity", "update " + updateRows + "row(s)\n");
        notesAdapter.refresh(loadNotesFromDatabase());
    }

}
