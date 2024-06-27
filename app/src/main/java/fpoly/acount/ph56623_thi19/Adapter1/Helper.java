package fpoly.acount.ph56623_thi19.Adapter1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import fpoly.acount.ph56623_thi19.Moder1.Model;

public class Helper extends SQLiteOpenHelper {
    //tao csdl
    public Helper(Context context) {
        super(context, "name", null, 1);
    }
    //tao bang dlieu
    @Override
    public void onCreate(SQLiteDatabase db) {
        // tao bang csdl
        String CREAT_TABLE="CREATE TABLE model(\n" +
                "\tid integer PRIMARY KEY,\n" +
                "  \ttitle text,\n" +
                "  \tdescription text\n" +
                ");";
        db.execSQL(CREAT_TABLE);
    }
    //update bang du lieu
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS model");
    }
    public void them(Model d){
        SQLiteDatabase db=this.getWritableDatabase();
        //ghi dlieu vao database
        ContentValues values=new ContentValues();
        //cbi du lieu de insert
        values.put("title",d.getTitle());
        values.put("description",d.getDescription());
        db.insert("model",null,values);
        db.close();
    }
    public List<Model> getAllData(){
        List<Model> list=new ArrayList<>();
        String selectQuery="SELECT * FROM model";
        SQLiteDatabase db=this.getWritableDatabase();
        //con tro doc dulieu
        Cursor cursor=db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Model d=new Model();
                d.setTitle(cursor.getString(1));
                d.setDescription(cursor.getString(2));
                list.add(d);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }
}
