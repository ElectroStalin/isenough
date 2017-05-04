package vladimir.enough;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

import vladimir.enough.models.KindsOfActivity;

/**
 * Created by 32669 on 03.05.2017.
 */

public class DB extends SQLiteAssetHelper {


    private static final String DATABASE_NAME = "isenough.db"; // Название файла с БД
    private static final int DATABASE_VERSION = 1;            //Версия БД


    public DB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    public ArrayList<KindsOfActivity> getAllActivities() {
        ArrayList<KindsOfActivity> activities = new ArrayList<>();

        String DATABASE_TABLE = "EnergyConsumption";

        String selectQuery = "SELECT * FROM " + DATABASE_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return new ArrayList<>();
        }
        if (cursor.moveToFirst()) {
            do {

                KindsOfActivity activity = new KindsOfActivity();
                activity.setId(cursor.getInt(0));
                activity.setActivityName(cursor.getString(1));
                activity.setConsumptionVal(Double.parseDouble(cursor.getString(2).replace(",", ".")));
                activity.setTime(cursor.getInt(3));
                activity.setType(cursor.getString(4));
                activities.add(activity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return activities;
    }


    public void addTime(int time,long id) {
        String DATABASE_TABLE = "EnergyConsumption";

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("time", time);


        db.update(DATABASE_TABLE, values, "_id" + " = ?",
                new String[] { String.valueOf(id) });

    }
}
