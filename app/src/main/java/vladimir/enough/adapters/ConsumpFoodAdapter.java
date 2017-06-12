package vladimir.enough.adapters;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.databinding.ConsumpFoodItemBinding;
import vladimir.enough.dialogs.AddConsumeFoodDialog;
import vladimir.enough.models.PersonalConsumtion;
import vladimir.enough.models.Product;

/**
 * Created by 32669 on 06.06.2017.
 */

public class ConsumpFoodAdapter extends RecyclerView.Adapter<ConsumpFoodAdapter.ViewHolder> {

    private ArrayList<Product> products;
    private DB dbHelper;
    private LayoutInflater layoutInflater;
    private Context context;
    private PersonalConsumtion personalConsumtion;
    private ConsumpFoodAdapter adapter;
    private Activity activity;
    public ConsumpFoodAdapter(ArrayList<Product> products) {
        this.products = products;
    }

    public void getdbHelper(DB dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void getContext(Context context) {
        this.context = context;
    }


    public void getActivity(Activity activity){
     this.activity=activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ConsumpFoodItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.consump_food_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.setItem(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ConsumpFoodItemBinding binding;
        private Product product;

        public ViewHolder(ConsumpFoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.ibAddFood.setOnClickListener(this);
        }

        public void setItem(Product product) {
            this.product = product;
            binding.tvName.setText(product.getName());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ibAddFood:
                    AddConsumeFoodDialog dialog=new AddConsumeFoodDialog();
                    dialog.getAdapter(adapter);
                    dialog.getProduct(product);
                    dialog.show(activity.getFragmentManager(), "dialog");

                    break;
            }

        }
    }
}


