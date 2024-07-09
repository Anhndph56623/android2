package fpoly.acount.ph56623_thi19.Demo2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TodoDao {
    private SQLiteDatabase db;

    public TodoDao(Context context) {
        TodoDatabaseHelper dbHelper = new TodoDatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long addTodo(Todo todo) {
        ContentValues values = new ContentValues();
        values.put("title", todo.getTitle());
        values.put("content", todo.getContent());
        values.put("data", todo.getData());
        values.put("type", todo.getType());
        values.put("status", todo.getStatus());
        return db.insert("todos", null, values);
    }

    public List<Todo> getAllTodos() {
        List<Todo> todos = new ArrayList<>();
        Cursor cursor = db.query("todos", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") Todo todo = new Todo(
                        cursor.getInt(cursor.getColumnIndex("id")),
                        cursor.getInt(cursor.getColumnIndex("status")),
                        cursor.getString(cursor.getColumnIndex("title")),
                        cursor.getString(cursor.getColumnIndex("content")),
                        cursor.getString(cursor.getColumnIndex("data")),
                        cursor.getString(cursor.getColumnIndex("type"))

                );
            } while (cursor.moveToNext());
        }
        cursor.close();
        return todos;
    }

    //ham update trang thai
    public void updateTodoStatus(int id, int status) {
        ContentValues values = new ContentValues();
        values.put("status", status);
        db.update("todos", values, "id=?", new String[]{String.valueOf(id)});
    }

    //ham update bang dlieu
    public void updateTodo(Todo todo) {
        ContentValues values = new ContentValues();
        values.put("title", todo.getTitle());
        values.put("content", todo.getContent());
        values.put("data", todo.getData());
        values.put("type", todo.getType());
        values.put("status", todo.getStatus());
        db.update("todos", values, "id=?", new String[]{String.valueOf(todo.getId())});
    }
    //ham delete

    public void deleteTodo(int id) {
        db.delete("todos", "id=?", new String[]{String.valueOf(id)});
    }
}
