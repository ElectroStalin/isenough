package vladimir.enough.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import vladimir.enough.R;
import vladimir.enough.databinding.FoodHistoryItemBinding;
import vladimir.enough.models.Product;

/**
 * Created by 32669 on 07.06.2017.
 */

public class FoodHistoryAdapter extends RecyclerView.Adapter<FoodHistoryAdapter.ViewHolder> {
 private ArrayList<Product> products;
    private LayoutInflater layoutInflater;

    public FoodHistoryAdapter(ArrayList<Product> products) {
        this.products = products;
    }
    public void setData(ArrayList<Product> products) {
        this.products = products;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        FoodHistoryItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.food_history_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product=products.get(position);
        holder.setItem(product);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private FoodHistoryItemBinding binding;
        private Product product;

        public ViewHolder (FoodHistoryItemBinding binding){
            super(binding.getRoot());
            this.binding=binding;
        }
        public String formatTime(String time) {

            String[] s = time.split(":");
            if (s[0].length() == 1) {
                s[0] = "0" + s[0];
            }
            if (s[1].length() == 1) {
                s[1] = "0" + s[1];
            }
            return time = s[0] + ":" + s[1];
        }
        public void setItem(Product product){
            this.product=product;
            binding.tvDate.setText(product.getDate());
            binding.tvTime.setText(formatTime(product.getTime()));
            binding.tvName.setText(product.getName());
            binding.tvWeight.setText(String.valueOf( product.getWeight())+" г");
        }
    }

}
