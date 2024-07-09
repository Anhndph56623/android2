package fpoly.acount.ph56623_thi19.Demo2;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class TodoDatabaseHelper extends SQLiteOpenHelper {
    public TodoDatabaseHelper(@Nullable Context context) {
        super(context, "todo.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE="CREATE TABLE todos(\n" +
                "\tid integer PRIMARY KEY AUTOINCREMENT,\n" +
                "  \ttitle text,\n" +
                "  \tcontent text,\n" +
                "  \tdata text,\n" +
                "  \ttype text,\n" +
                "  \tstatus integer\n" +
                ");";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS todos");

    }
}
