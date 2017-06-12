package vladimir.enough;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vladimir.enough.models.KindsOfActivity;
import vladimir.enough.models.PersonalConsumtion;
import vladimir.enough.models.PersonalInfo;
import vladimir.enough.models.Product;

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

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        try {

            cursor = db.query(DATABASE_TABLE, null, null, null, null, null, "type");
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
                activity.setConsumptionVal(Double.parseDouble(cursor.getString(2)));
                activity.setTime(cursor.getInt(3));
                activity.setType(cursor.getString(4));
                activities.add(activity);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return activities;
    }

    public void insertActivity(KindsOfActivity activity) {
        String DATABASE_TABLE = "EnergyConsumption";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("activity", activity.getActivityName());
        values.put("consumption", activity.getConsumptionVal());
        values.put("time", activity.getTime());
        values.put("type", activity.getType());


        db.insert(DATABASE_TABLE, null, values);
        db.close();

    }

    public void editActivity(KindsOfActivity activity) {
        String DATABASE_TABLE = "EnergyConsumption";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("activity", activity.getActivityName());
        values.put("consumption", activity.getConsumptionVal());
        values.put("time", activity.getTime());
        values.put("type", activity.getType());

        db.update(DATABASE_TABLE, values, "_id = " + activity.getId(), null);
    }

    public void deleteActivity(KindsOfActivity kindsOfActivity) {
        String DATABASE_TABLE = "EnergyConsumption";
        SQLiteDatabase db = this.getWritableDatabase();
        long id = kindsOfActivity.getId();
        db.delete(DATABASE_TABLE, "_id = " + id, null);


    }


    public void addTime(int time, long id) {
        String DATABASE_TABLE = "EnergyConsumption";

        SQLiteDatabase db = this.getWritableDatabase();


        ContentValues values = new ContentValues();
        values.put("time", time);


        db.update(DATABASE_TABLE, values, "_id" + " = ?",
                new String[]{String.valueOf(id)});
        db.close();
    }

    public ArrayList<String> getTime() {
        int sum = 0;
        ArrayList<String> times = new ArrayList<>();
        String DATABASE_TABLE = "EnergyConsumption";

        String selectQuery = "SELECT time FROM " + DATABASE_TABLE;

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

                times.add(String.valueOf(cursor.getInt(0)));

            } while (cursor.moveToNext());
        }

        cursor.close();


        return times;
    }

    public void setPersonalInfo(int age, int weight, int height, String sex, int id) {
        String DATABASE_TABLE = "PersonalInfo";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("height", height);
        values.put("weight", weight);
        values.put("age", age);
        values.put("sex", sex);
        db.update(DATABASE_TABLE, values, "_id" + " = ?",
                new String[]{String.valueOf(id)});

        db.close();

    }


    public ArrayList<PersonalInfo> getPersonalInfo() {
        ArrayList<PersonalInfo> personalInfos = new ArrayList<>();
        String DATABASE_TABLE = "PersonalInfo";

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

                PersonalInfo personalInfo = new PersonalInfo();
                personalInfo.setHeight(cursor.getInt(1));
                personalInfo.setWeight(cursor.getInt(2));
                personalInfo.setAge(cursor.getInt(3));
                personalInfo.setSex(cursor.getString(4));
                personalInfos.add(personalInfo);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return personalInfos;

    }

    public ArrayList<PersonalConsumtion> getAllPersonalEnergy() {

        ArrayList<PersonalConsumtion> personalConsumtions = new ArrayList<>();

        String DATABASE_TABLE = "PersonalEnergy";

        String selectQuery = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(DATABASE_TABLE, null, null, null, null, null, null);

        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return new ArrayList<>();
        }

        if (cursor.moveToFirst()) {
            do {
                PersonalConsumtion personalConsumtion = new PersonalConsumtion();
                personalConsumtion.setDate(cursor.getString(0));
                personalConsumtion.setDailyCallories(cursor.getDouble(1));
                personalConsumtion.setDailyProteins(cursor.getDouble(2));
                personalConsumtion.setDailyLipids(cursor.getDouble(3));
                personalConsumtion.setDailyCarbohydrates(cursor.getDouble(4));
                personalConsumtion.setCurrentCallories(cursor.getDouble(5));
                personalConsumtion.setCurrentProteins(cursor.getDouble(6));
                personalConsumtion.setCurrentLipids(cursor.getDouble(7));
                personalConsumtion.setDailyCarbohydrates(cursor.getDouble(8));
                personalConsumtion.setBasicExchenge(cursor.getDouble(9));
                personalConsumtion.setWeightIndex(cursor.getDouble(10));
                personalConsumtions.add(personalConsumtion);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return personalConsumtions;

    }


    public PersonalConsumtion getTodayPersonalEnergy() {
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);

        String[] selectionArgs = new String[1];
        selectionArgs[0] = date;
        PersonalConsumtion personalConsumtion = new PersonalConsumtion();
        String DATABASE_TABLE = "PersonalEnergy";

        String selectQuery = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.rawQuery(selectQuery, null);
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return new PersonalConsumtion();
        }
        String selection = "date = ?";

        cursor = db.query(DATABASE_TABLE, null, selection, selectionArgs, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                personalConsumtion.setDate(cursor.getString(0));
                personalConsumtion.setDailyCallories(cursor.getDouble(1));
                personalConsumtion.setDailyProteins(cursor.getDouble(2));
                personalConsumtion.setDailyLipids(cursor.getDouble(3));
                personalConsumtion.setDailyCarbohydrates(cursor.getDouble(4));
                personalConsumtion.setCurrentCallories(cursor.getDouble(5));
                personalConsumtion.setCurrentProteins(cursor.getDouble(6));
                personalConsumtion.setCurrentLipids(cursor.getDouble(7));
                personalConsumtion.setCurrentCarbohydrates(cursor.getDouble(8));
                personalConsumtion.setBasicExchenge(cursor.getDouble(9));
                personalConsumtion.setWeightIndex(cursor.getDouble(10));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return personalConsumtion;
    }

//    public PersonalConsumtion getTodayCurrentPersonalEnergy() {
//        Date today = new Date();
//        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
//        String date = DATE_FORMAT.format(today);
//
//        String[] selectionArgs = new String[1];
//        selectionArgs[0] = date;
//        PersonalConsumtion personalConsumtion = new PersonalConsumtion();
//        String DATABASE_TABLE = "PersonalEnergy";
//
//        String selectQuery = "SELECT * FROM " + DATABASE_TABLE;
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        Cursor cursor = null;
//        try {
//            cursor = db.rawQuery(selectQuery, null);
//        } catch (android.database.SQLException e) {
//            e.printStackTrace();
//        }
//
//        if (cursor == null) {
//            return new PersonalConsumtion();
//        }
//        String selection = "date = ?";
//
//        cursor = db.query(DATABASE_TABLE, null, selection, selectionArgs, null, null, null);
//        if (cursor.moveToFirst()) {
//            do {
//                personalConsumtion.setCurrentCallories(cursor.getDouble(5));
//                personalConsumtion.setCurrentProteins(cursor.getDouble(6));
//                personalConsumtion.setCurrentLipids(cursor.getDouble(7));
//                personalConsumtion.setCurrentCarbohydrates(cursor.getDouble(8));
//             } while (cursor.moveToNext());
//        }
//        cursor.close();
//
//        return personalConsumtion;
//    }
    public void setTargetPersonalEnergy(PersonalConsumtion personalConsumtion) {

        String DATABASE_TABLE = "PersonalEnergy";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);

        values.put("daily_callories", personalConsumtion.getDailyCallories());
        values.put("daily_proteins", personalConsumtion.getDailyProteins());
        values.put("daily_lipids", personalConsumtion.getDailyLipids());
        values.put("daily_carbohydrates", personalConsumtion.getDailyCarbohydrates());
        values.put("basic_exchenge", personalConsumtion.getBasicExchenge());
        values.put("weight_index", personalConsumtion.getWeightIndex());

        String[] args = new String[1];
        args[0] = date;
        db.update(DATABASE_TABLE, values, "date" + " = ?",
                args);

        db.close();

    }
    public void setCurrentPersonalEnergy(PersonalConsumtion personalConsumtion) {

        String DATABASE_TABLE = "PersonalEnergy";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();



        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);

        values.put("current_callories", personalConsumtion.getCurrentCallories());
        values.put("current_proteins", personalConsumtion.getCurrentProteins());
        values.put("current_lipids", personalConsumtion.getCurrentLipids());
        values.put("current_carbohydrates", personalConsumtion.getCurrentCarbohydrates());

        String[] args = new String[1];
        args[0] = date;
        db.update(DATABASE_TABLE, values, "date" + " = ?",
                args);

        db.close();

    }

    public void insertRowInPersonalEnergy() {
        String DATABASE_TABLE = "PersonalEnergy";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);
        double val = 0;
        values.put("date", date);
        values.put("daily_callories", val);
        values.put("daily_proteins", val);
        values.put("daily_lipids", val);
        values.put("daily_carbohydrates", val);
        values.put("current_callories", val);
        values.put("current_proteins", val);
        values.put("current_lipids", val);
        values.put("current_carbohydrates", val);
        values.put("basic_exchenge", val);
        values.put("weight_index", val);
        db.insert(DATABASE_TABLE, null, values);
        db.close();

    }

    public void clearPersonalEnergy() {
        String DATABASE_TABLE = "PersonalEnergy";
        SQLiteDatabase db = this.getWritableDatabase();

        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);
        String[] d = new String[1];
        d[0] = date;
        db.delete(DATABASE_TABLE, "date <> ? ", d);

    }

    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> products = new ArrayList<>();

        String DATABASE_TABLE = "Products";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(DATABASE_TABLE, null, null, null, null, null, "type");
        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return new ArrayList<>();
        }

        if (cursor.moveToFirst()) {
            do {

                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setProdID(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setCallories(cursor.getDouble(2));
                product.setProteins(cursor.getDouble(3));
                product.setLipids(cursor.getDouble(4));
                product.setCarbohydrates(cursor.getDouble(5));
                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }
    public Product getProduct(int id) {
        Product product=new Product();
        String DATABASE_TABLE = "Products";

        String selection = "_id = ?";

        String[] selectionArgs = new String[1];
        selectionArgs[0] = String.valueOf(id);

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        try {
            cursor = db.query(DATABASE_TABLE, null, selection, selectionArgs, null, null, null);


        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return new Product();
        }

        if (cursor.moveToFirst()) {
            do {

                product.setProdID(cursor.getInt(0));
                product.setName(cursor.getString(1));
                product.setCallories(cursor.getDouble(2));
                product.setProteins(cursor.getDouble(3));
                product.setLipids(cursor.getDouble(4));
                product.setCarbohydrates(cursor.getDouble(5));

            } while (cursor.moveToNext());
        }
        cursor.close();
        return product;
    }

    public void insertProduct(Product product) {
        String DATABASE_TABLE = "Products";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("name", product.getName());
        values.put("callories", product.getCallories());
        values.put("proteins", product.getProteins());
        values.put("lipids", product.getLipids());
        values.put("carbohydrates", product.getCarbohydrates());
        values.put("type", product.getType());

        db.insert(DATABASE_TABLE, null, values);
        db.close();

    }

    public void deleteProduct(Product product) {
        String DATABASE_TABLE = "Products";
        SQLiteDatabase db = this.getWritableDatabase();
        int id = product.getId();
        db.delete(DATABASE_TABLE, "_id = " + id, null);


    }

    public void addConsumeFood(Product product) {
        String DATABASE_TABLE = "FoodHistory";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("prodID",product.getProdID());
        values.put("name", product.getName());
        values.put("weight",product.getWeight());
        values.put("date",product.getDate());
        values.put("time",product.getTime());

        db.insert(DATABASE_TABLE, null, values);
        db.close();

    }
    public void deleteTodayFood(Product product) {
        String DATABASE_TABLE = "FoodHistory";
        SQLiteDatabase db = this.getWritableDatabase();
        int id = product.getId();
        db.delete(DATABASE_TABLE, "_id = " + id, null);


    }

    public ArrayList<Product> getAllFood() {
        ArrayList<Product> products = new ArrayList<>();

        String DATABASE_TABLE = "FoodHistory";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        try {


            cursor = db.query(DATABASE_TABLE, null, null, null, null, null, "date");

        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return new ArrayList<>();
        }

        if (cursor.moveToFirst()) {
            do {

                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setProdID(cursor.getInt(1));
                product.setDate(cursor.getString(2));
                product.setTime(cursor.getString(3));
                product.setName(cursor.getString(4));
                product.setWeight(cursor.getInt(5));
                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }
    public ArrayList<Product> getTodayFood() {
        ArrayList<Product> products = new ArrayList<>();

        String DATABASE_TABLE = "FoodHistory";
        String selection = "date = ?";
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);
        String[] selectionArgs = new String[1];
        selectionArgs[0] = date;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        try {


            cursor = db.query(DATABASE_TABLE, null, selection, selectionArgs, null, null, null);

        } catch (android.database.SQLException e) {
            e.printStackTrace();
        }

        if (cursor == null) {
            return new ArrayList<>();
        }

        if (cursor.moveToFirst()) {
            do {

                Product product = new Product();
                product.setId(cursor.getInt(0));
                product.setProdID(cursor.getInt(1));
                product.setDate(cursor.getString(2));
                product.setTime(cursor.getString(3));
                product.setName(cursor.getString(4));
                product.setWeight(cursor.getInt(5));
                products.add(product);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return products;
    }
    public void clearFoodHistory() {
        String DATABASE_TABLE = "FoodHistory";
        SQLiteDatabase db = this.getWritableDatabase();

        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);
        String[] d = new String[1];
        d[0] = date;
        db.delete(DATABASE_TABLE, "date <> ? ", d);

    }
    public void editTodayFood(Product product) {
        String DATABASE_TABLE = "FoodHistory";
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put("time", product.getTime());
        values.put("weight", product.getWeight());

        db.update(DATABASE_TABLE, values, "_id = " + product.getId(), null);
    }

}
