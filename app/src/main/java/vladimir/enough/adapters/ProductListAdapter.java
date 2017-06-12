package vladimir.enough.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.databinding.ProductInDbItemBinding;
import vladimir.enough.models.Product;

/**
 * Created by 32669 on 17.05.2017.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {
    private ArrayList<Product> products;
    private LayoutInflater layoutInflater;
    private DB dbHelper;
    private Context context;
    private ProductListAdapter adapter;

    public ProductListAdapter(ArrayList<Product> products) {
        this.products = products;

    }

    public void setData(ArrayList<Product> products) {
        this.products = products;

    }

    public void getdbHelper(DB dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void getContext(Context context) {
        this.context = context;
    }

    public void getAdapter(ProductListAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ProductInDbItemBinding productInDbItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.product_in_db_item, parent, false);
        return new ViewHolder(productInDbItemBinding);
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
        private ProductInDbItemBinding productInDbItemBinding;
        private Product product;


        public ViewHolder(ProductInDbItemBinding productInDbItemBinding) {
            super(productInDbItemBinding.getRoot());
            this.productInDbItemBinding = productInDbItemBinding;
            productInDbItemBinding.ibDelProd.setOnClickListener(this);
        }

        public void setItem(Product product) {
            this.product = product;
            productInDbItemBinding.tvProdName.setText(product.getName());
            productInDbItemBinding.tvProdCal.setText("Ккал " + String.valueOf(product.getCallories()));
            productInDbItemBinding.tvProdProt.setText(" Б " + String.valueOf(product.getProteins()));
            productInDbItemBinding.tvProdLip.setText(" Ж " + String.valueOf(product.getLipids()));
            productInDbItemBinding.tvProdCar.setText(" У " + String.valueOf(product.getCarbohydrates()));

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ibDelProd:
                    new MaterialDialog.Builder(context)
                            .title("Удалить элемент?")
                            .positiveText("Да")
                            .negativeText("Нет")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {



                                    dbHelper.deleteProduct(product);
                                    products = dbHelper.getAllProducts();
                                    adapter.setData(products);
                                    adapter.notifyDataSetChanged();
                                }
                            })
                            .backgroundColorRes(R.color.colorPrimaryDark)
                            .positiveColorRes(R.color.cardview_light_background)
                            .negativeColorRes(R.color.cardview_light_background)
                            .show();
                    break;
            }
        }
    }
}
