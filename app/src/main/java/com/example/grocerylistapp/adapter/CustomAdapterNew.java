package com.example.grocerylistapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;



import com.example.grocerylistapp.R;
import com.example.grocerylistapp.model.Product;

import java.util.ArrayList;

public class CustomAdapterNew extends BaseAdapter {

    private ArrayList<Product> productsList;
    private ArrayList<Product> selectedProducts = new ArrayList<>();

    private Context context;

    public static  final String TAG = CustomAdapterNew.class.getSimpleName();
    public CustomAdapterNew(ArrayList<Product> product,Context cont){
        this.context = cont;
        this.productsList = product;
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
        ViewHolder  holder= null;

        if(converView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            converView = inflater.inflate(R.layout.grocery_item1,null);
            holder = new ViewHolder();

            holder.textProduct =converView.findViewById(R.id.textView1);
            holder.checkBox = converView.findViewById(R.id.checkBox1);


            converView.setTag(holder);

        }else{
            holder = (ViewHolder) converView.getTag();
        }

        final Product product = productsList.get(position);

        Log.e(TAG,"products :"+product.getProduct());
        holder.textProduct.setText(product.getProduct());
        final ViewHolder finalHolder = holder;
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                            Log.e(TAG,"product Value Checked :"+product.getProduct());
                                    product.setIsSubmitted(1);
                                selectedProducts.add(product);
//                                finalHolder.checkBox.setChecked(false);
//                                finalHolder.checkBox.setClickable(false);
//                            getData(product.getProduct());
                }else {
                    product.setIsSubmitted(0);
                    selectedProducts.remove(product);
                    Log.e(TAG,"product Value Not Checked :"+product.getProduct());
                }
            }
        });
        return  converView;
    }

    public ArrayList<Product> getProductsList()
    {
        return selectedProducts;
    }


    private static class ViewHolder{
        public TextView textProduct;
        public CheckBox checkBox;
    }
}
