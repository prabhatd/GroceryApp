package com.example.grocerylistapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class AddGroceryFragment extends Fragment {

    private EditText editProduct;
    private Button btnAdd;
    private Button btnSave;

    String product;

    String SQLiteDataBaseQueryHolder;
    SQLiteDatabase sqLiteDatabaseObj;

    Boolean EditTextEmptyHold= false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add,container,false);

        editProduct = view.findViewById(R.id.editTextProduct);
        btnAdd = view.findViewById(R.id.addProduct);
        btnSave = view.findViewById(R.id.saveProduct);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                checkEditTextStatus();

                InsertDataIntoSQLiteDatabase();

                EmptyEditTextAfterDataInsert();
            }
        });
        return view;
    }

    public void SQLiteDataBaseBuild(){
//        sqLiteDatabaseObj = SQLiteDatabase.openOrCreateDatabase("AndroidJSonDataBase",Context.MODE_PRIVATE,null);
        sqLiteDatabaseObj = getActivity().openOrCreateDatabase("AndroidJSonDataBase", Context.MODE_PRIVATE, null);
    }

    public void SQLiteTableBuild(){
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS AndroidJSonTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR,isSubmitted Integer);");
    }

    public void checkEditTextStatus(){
        product = editProduct.getText().toString();

        if(TextUtils.isEmpty(product)){
           EditTextEmptyHold = false;
        }else {
            EditTextEmptyHold = true;
        }
    }

    public void InsertDataIntoSQLiteDatabase(){
        if(EditTextEmptyHold == true){
            SQLiteDataBaseQueryHolder = "INSERT INTO AndroidJSonTable (name) VALUES('"+product+"');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            Toast.makeText(getContext(),"Data Inserted Successfully", Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(getContext(),"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
        }
    }

    public void EmptyEditTextAfterDataInsert(){

        editProduct.getText().clear();

        moveTofragment();

    }

    public void moveTofragment(){
//        Fragment fragment = new GroceryListFragment();
//        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.addToBackStack(null);
//        fragmentTransaction.commit();

        Intent intent = new Intent(getContext(),MainActivity.class);
        startActivity(intent);
    }
}
