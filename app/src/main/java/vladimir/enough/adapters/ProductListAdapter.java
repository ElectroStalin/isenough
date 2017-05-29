package vladimir.enough.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vladimir.enough.R;
import vladimir.enough.databinding.ProductInDbItemBinding;
import vladimir.enough.models.Product;

/**
 * Created by 32669 on 17.05.2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private ArrayList<Product> products;
    private LayoutInflater layoutInflater;


    public ProductListAdapter(ArrayList<Product> products) {
        this.products = products;

    }
    public void setData(ArrayList<Product> products){
        this.products=products;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ProductInDbItemBinding productInDbItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.product_in_db_item,parent,false);
        return new ViewHolder(productInDbItemBinding);
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


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ProductInDbItemBinding productInDbItemBinding;
        private Product product;


        public ViewHolder(ProductInDbItemBinding productInDbItemBinding) {
            super(productInDbItemBinding.getRoot());
            this.productInDbItemBinding=productInDbItemBinding;

            productInDbItemBinding.getRoot().setOnClickListener(this);
        }

        public void setItem(Product product){
            this.product=product;
            productInDbItemBinding.tvProdName.setText(product.getName());
            productInDbItemBinding.tvProdCal.setText("Ккал "+ String.valueOf( product.getCallories()));
            productInDbItemBinding.tvProdProt.setText(" Б " + String.valueOf( product.getProteins()));
            productInDbItemBinding.tvProdLip.setText(" Ж "+ String.valueOf( product.getLipids()));
            productInDbItemBinding.tvProdCar.setText(" У "+String.valueOf( product.getCarbohydrates()));

        }
        @Override
        public void onClick(View v) {

        }
    }
}
