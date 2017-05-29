package vladimir.enough.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.ProductListAdapter;
import vladimir.enough.databinding.ActivityProductsListBinding;
import vladimir.enough.dialogs.AddProductDialog;
import vladimir.enough.models.Product;

public class ProductsList extends AppCompatActivity implements View.OnClickListener {
    private ProductListAdapter adapter;
    DB dbHelper;
    private ActivityProductsListBinding binding;
    private ArrayList<Product> products;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_products_list);

        RecyclerView rvFood=(RecyclerView)findViewById(R.id.rvFood);
        rvFood.setHasFixedSize(true);
        rvFood.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DB(this);
        products=dbHelper.getAllProducts();

        adapter=new ProductListAdapter(products);
        rvFood.setAdapter(adapter);

        floatingActionButton=(FloatingActionButton)findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.floatingActionButton:
                AddProductDialog dialog=new AddProductDialog();
                dialog.getAdapter(adapter);
                dialog.show(getFragmentManager(),"dialog");

                break;
        }
    }
}
