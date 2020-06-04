package com.example.grocerylistapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.grocerylistapp.R;
import com.example.grocerylistapp.model.ProductOrder;

import java.util.ArrayList;

public class CustomAdapterOrder extends BaseAdapter {
    private ArrayList<ProductOrder> productsList;

    private Context context;

    public static final String TAG = CustomAdapterOrder.class.getSimpleName();

    public CustomAdapterOrder(ArrayList<ProductOrder> productOrders,Context context){
        this.context =context;
        this.productsList = productOrders;
    }
    @Override
    public int getCount() {
        return productsList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.productsList.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        ViewHolder holder = null;

        if(converView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converView = inflater.inflate(R.layout.grocery_item2,null);
            holder = new ViewHolder();

            holder.textViewOrder =converView.findViewById(R.id.textViewOrder);

            converView.setTag(holder);
        }else {
            holder = (ViewHolder) converView.getTag();
        }

        final ProductOrder productOrder= productsList.get(position);
        Log.e(TAG,"products :"+productOrder.getProduct());
        holder.textViewOrder.setText(productOrder.getProduct());

        return converView;
    }

    public static  class ViewHolder{
        public TextView textViewOrder;
    }
}
