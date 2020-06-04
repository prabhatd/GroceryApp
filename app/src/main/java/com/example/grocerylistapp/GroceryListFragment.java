package com.example.grocerylistapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

import com.example.grocerylistapp.adapter.CustomAdapter;
import com.example.grocerylistapp.adapter.CustomAdapterNew;
import com.example.grocerylistapp.helper.DatabaseHelper;
import com.example.grocerylistapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public class GroceryListFragment extends Fragment {

    public static final String TITLE= "Grocery List";

    ListView listView;

    List<String> items = new ArrayList<String>();

    Product product;


    Button btnAdd;
    Button btnSubmit;
    CustomAdapterNew adapter;

    LinearLayout linearLayout;
    public static final String TAG = GroceryListFragment.class.getSimpleName();

    String SQLiteDataBaseQueryHolder;

    SQLiteDatabase sqLiteDatabaseObj;

    public ArrayList<Product> getData;
    private static final String TABLE_NAME = "AndroidJSonTable";
    int isSelectedValue = 0;
    public static GroceryListFragment newInstance(){
        return new GroceryListFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

         View rootView = inflater.inflate(R.layout.fragment_first,container,false);
        listView = rootView.findViewById(R.id.listView1);

        btnAdd =rootView. findViewById(R.id.btnAdd1);
        btnSubmit = rootView.findViewById(R.id.btnSubmit1);
        linearLayout = rootView.findViewById(R.id.fragment_container1);

        DatabaseHelper helper = new DatabaseHelper(rootView.getContext());

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  adapter.getProductsList();
               getData = adapter.getProductsList();

                for(Product p :getData){
                    Log.e(TAG,"getData :"+p.getProduct());
                    Log.e(TAG,"isChecked Value :"+p.getIsSubmitted());
                    isSelectedValue = 1;
                    SQLiteDataBaseBuild();
                    SQLiteTableBuild();
                   InsertDataIntoSQLiteDatabase();
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAdd.setVisibility(View.GONE);
                btnSubmit.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                FragmentManager fragmentManager =getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                AddGroceryFragment addGroceryFragment = new AddGroceryFragment();
                fragmentTransaction.add(R.id.fragment_container1,addGroceryFragment,"Add Grocery");
                fragmentTransaction.commit();
            }
        });
        if(helper.getAllData() == null){
             String [] values = new String[]{"Canned & Jarred Food","Cereal & Muesli","Baking Supplies","Dried Fruits","Nuts & Seeds","Jams","Honey","Spreads","Poultry & Seafood","Pasta & Noodles","Cheese","Beer","Milk","Cereals","Frozen dinners","Oil & Fat","Condiments","Dried Produce","Soups","Dairy","Pasta","Salad Dressings","Sauces","Eggs","Tea and coffee"};

            final ArrayList<String> arrayList = new ArrayList<String>();

        for(int i=0;i<values.length;++i){
            arrayList.add(values[i]);
        }
     //   List<String> namesList = readContacts(getActivity());
            final CustomAdapter adapter1 = new CustomAdapter(getActivity(),arrayList);
             listView.setAdapter(adapter1);
        }else {
            final ArrayList<Product>  products = helper.getAllData();

            for(int i=0;i<products.size();i++){
                 product= products.get(i);
                Log.e(TAG,"products data :"+product.getProduct());
              //  products.add(product);
            }
            Log.e(TAG,"prodcts :"+products);

            adapter = new CustomAdapterNew(products,rootView.getContext());

            listView.setAdapter(adapter);
        }

        return rootView;
    }

    public void InsertDataIntoSQLiteDatabase() {
         Log.e(TAG,"products Value Checked : "+product.getProduct());
         for(int i=0;i<getData.size();i++){
             sqLiteDatabaseObj.execSQL("UPDATE "+TABLE_NAME+" SET isSubmitted = "+"'"+isSelectedValue+"' "+ "WHERE name = "+"'"+product.getProduct()+"'");
         }
    }
    public void SQLiteTableBuild(){
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS AndroidJSonTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR,isSubmitted Integer);");
    }

    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = getActivity().openOrCreateDatabase("AndroidJSonDataBase", Context.MODE_PRIVATE, null);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
