package vladimir.enough.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.ConsumpFoodAdapter;
import vladimir.enough.databinding.ActivityConsumeFoodBinding;
import vladimir.enough.models.Product;

public class ConsumeFood extends AppCompatActivity {
    public Product product;
    public ArrayList<Product> products;
    private ActivityConsumeFoodBinding binding;
    public DB dbHelper;
    private ConsumpFoodAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_consume_food);

        binding.rvFoodList.setHasFixedSize(true);
        binding.rvFoodList.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DB(this);
        products = dbHelper.getAllProducts();
        adapter=new ConsumpFoodAdapter(products);
        adapter.getContext(this);
        adapter.getActivity(this);
        adapter.getdbHelper(dbHelper);
        binding.rvFoodList.setAdapter(adapter);
    }

}
