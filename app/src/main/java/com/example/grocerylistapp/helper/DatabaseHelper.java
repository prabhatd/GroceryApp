package com.example.grocerylistapp.helper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.grocerylistapp.model.Product;
import com.example.grocerylistapp.model.ProductOrder;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static  final String DATABASE_NAME = "AndroidJSonDataBase";
    public static  final String TABLE_NAME = "AndroidJSonTable";
    public static final String COL_1= "id";
    public static final String COL_2 = "name";
    public static final int version = 1;

    public int isSelectedValue = 1;
    private String SQLiteDataBaseQueryHolder;

    public static final String TAG = DatabaseHelper.class.getSimpleName();
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS AndroidJSonTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, isSubmitted Integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
    }

//    public void insertData(){
//        SQLiteDataBaseQueryHolder = "INSERT INTO AndroidJSonTable (name) VALUES('"+product+"');";
//        sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
//    }

    public ArrayList<Product> getAllData(){
        ArrayList<Product> productArrayList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor res= database.rawQuery("Select * from "+TABLE_NAME,null);

        Log.e(TAG,"res :"+res.toString());
        while (res.moveToNext()){
            String productName = res.getString(1);


            Product product = new Product(productName);
            productArrayList.add(product);
        }
        return  productArrayList;
    }

    public ArrayList<ProductOrder> getAllOrderProduct(){
        ArrayList<ProductOrder> productArrayList = new ArrayList<>();

        SQLiteDatabase database = this.getWritableDatabase();

        Cursor res= database.rawQuery("Select * from "+TABLE_NAME +" WHERE isSubmitted = "+"'"+isSelectedValue+"' ",null);

        Log.e(TAG,"res :"+res.toString());
        while (res.moveToNext()){
            String productName = res.getString(1);


            ProductOrder product = new ProductOrder(productName);
            productArrayList.add(product);
        }
        return  productArrayList;
    }
}
