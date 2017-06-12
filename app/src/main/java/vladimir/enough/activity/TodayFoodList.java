package vladimir.enough.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.TodayFoodListAdapter;
import vladimir.enough.databinding.ActivityTodayFoodListBinding;
import vladimir.enough.models.Product;

public class TodayFoodList extends AppCompatActivity implements View.OnClickListener{
    public ActivityTodayFoodListBinding binding;
    public Product product;
    public ArrayList<Product> products;
    public DB dbHelper;
    private TodayFoodListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_today_food_list);
        binding.btnOK.setOnClickListener(this);
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);
        binding.tvToday.setText("Съедено сегодня: " +date);
        binding.rvTodayFood.setHasFixedSize(true);
        binding.rvTodayFood.setLayoutManager(new LinearLayoutManager(this));
        dbHelper = new DB(this);
        products = dbHelper.getTodayFood();
        adapter=new TodayFoodListAdapter(products);
        adapter.getContext(this);
        adapter.getActivity(this);
        adapter.getAdapter(adapter);
        adapter.getdbHelper(dbHelper);
        binding.rvTodayFood.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOK:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
        }
    }
}
