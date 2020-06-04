package com.example.grocerylistapp;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.grocerylistapp.adapter.CustomAdapter;
import com.example.grocerylistapp.adapter.CustomAdapterOrder;
import com.example.grocerylistapp.helper.DatabaseHelper;
import com.example.grocerylistapp.model.Product;
import com.example.grocerylistapp.model.ProductOrder;

import java.util.ArrayList;

public class OrderHistoryFragment extends Fragment {

    public static final String TITLE= "Order History";

    ProductOrder productOrder;

    ListView  listView;

    CustomAdapterOrder adapterOrder;

    Button btnback;

    ArrayList<ProductOrder> productOrders;

    SQLiteDatabase sqLiteDatabaseObj;

    private static final String TABLE_NAME = "AndroidJSonTable";

    public static final String TAG= OrderHistoryFragment.class.getSimpleName();

    int isSelectedValue = 0;

    public static OrderHistoryFragment newInstance(){
        return new OrderHistoryFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_second,container,false);
        listView = rootView.findViewById(R.id.listViewOrder);

        btnback = rootView.findViewById(R.id.btnBack);
        DatabaseHelper helper = new DatabaseHelper(rootView.getContext());

        if(helper.getAllOrderProduct() == null){
            String [] values = new String[]{"Canned & Jarred Food","Cereal & Muesli","Baking Supplies","Dried Fruits","Nuts & Seeds","Jams","Honey","Spreads","Poultry & Seafood","Pasta & Noodles","Cheese","Beer","Milk","Cereals","Frozen dinners","Oil & Fat","Condiments","Dried Produce","Soups","Dairy","Pasta","Salad Dressings","Sauces","Eggs","Tea and coffee"};

            final ArrayList<String> arrayList = new ArrayList<String>();

            for(int i=0;i<values.length;++i){
                arrayList.add(values[i]);
            }
            //   List<String> namesList = readContacts(getActivity());
            final CustomAdapter adapter1 = new CustomAdapter(getActivity(),arrayList);
            listView.setAdapter(adapter1);
        }else {
            productOrders = helper.getAllOrderProduct();

           for (int i=0;i<productOrders.size();i++){
               productOrder =productOrders.get(i);

               Log.e(TAG,"products data :"+productOrder.getProduct());

           }
           adapterOrder = new CustomAdapterOrder(productOrders,rootView.getContext());
           listView.setAdapter(adapterOrder);
        }

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            for (ProductOrder p: productOrders){
                Log.e(TAG,"Products are :"+p.getProduct());
                isSelectedValue =0;

                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                InsertDataIntoSQLiteDatabase();

                Intent intent = new Intent(getContext(),MainActivity.class);
                startActivity(intent);
            }
            }
        });
        return  rootView;
    }

    public void InsertDataIntoSQLiteDatabase() {
        Log.e(TAG,"products Value Checked : "+productOrder.getProduct());
        for(int i=0;i<productOrders.size();i++){
            sqLiteDatabaseObj.execSQL("UPDATE "+TABLE_NAME+" SET isSubmitted = "+"'"+isSelectedValue+"' "+ "WHERE name = "+"'"+productOrder.getProduct()+"'");
        }
    }
    public void SQLiteTableBuild(){
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS AndroidJSonTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR,isSubmitted Integer);");
    }

    public void SQLiteDataBaseBuild(){
//        sqLiteDatabaseObj = SQLiteDatabase.openOrCreateDatabase("AndroidJSonDataBase",Context.MODE_PRIVATE,null);
        sqLiteDatabaseObj = getActivity().openOrCreateDatabase("AndroidJSonDataBase", Context.MODE_PRIVATE, null);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
