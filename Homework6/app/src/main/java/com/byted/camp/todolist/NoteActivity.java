package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.Priority;
import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract.TodoEntry;
import com.byted.camp.todolist.db.TodoDbHelper;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private RadioGroup radioGroup;
    private TodoDbHelper dbHelper;
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT =
            new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss", Locale.ENGLISH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);

        dbHelper = new TodoDbHelper(this);
        editText = findViewById(R.id.edit_text);
        radioGroup = findViewById(R.id.priority);

        editText.setFocusable(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }

        Button addBtn = findViewById(R.id.btn_add);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }

                Priority priority;
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.high:
                        priority = Priority.HIGH; break;
                    case R.id.higher:
                        priority = Priority.HIGHER; break;
                    default:
                        priority = Priority.NORMAL; break;
                }

                boolean succeed = saveNote2Database(content.toString().trim(), priority);
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }

    private boolean saveNote2Database(String content, Priority priority) {
        // TODO 插入一条新数据，返回是否插入成功
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TodoEntry.COLUMN_NAME_DATE, SIMPLE_DATE_FORMAT.format(System.currentTimeMillis()));
        values.put(TodoEntry.COLUMN_NAME_STATE, State.TODO.intValue);
        values.put(TodoEntry.COLUMN_NAME_PRIORITY, priority.intValue);
        values.put(TodoEntry.COLUMN_NAME_CONTENT, content);

        long newRowId = db.insert(TodoEntry.TABLE_NAME, null, values);
        if (newRowId == -1) {
            Log.d("NoteActivity", "insert new row failed!\n");
            return false;
        } else {
            Log.d("NoteActivity", "insert new row: " + newRowId + "\n");
            return true;
        }
    }
}
