package vladimir.enough.activity;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.FoodHistoryAdapter;
import vladimir.enough.databinding.ActivityFoodHistoryBinding;
import vladimir.enough.models.Product;

public class FoodHistory extends AppCompatActivity implements View.OnClickListener {
    private ActivityFoodHistoryBinding binding;
    private DB dbhelper;
    private ArrayList<Product> products;
    private FoodHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_history);
        binding.btnDel.setOnClickListener(this);
        RecyclerView rvHistory = (RecyclerView) findViewById(R.id.rvFoodHist);
        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        dbhelper = new DB(this);
        products = dbhelper.getAllFood();
        adapter = new FoodHistoryAdapter(products);
        rvHistory.setAdapter(adapter);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDel:
                new MaterialDialog.Builder(this)
                        .title("Подтверждение")
                        .content("Очистить историю?")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dbhelper.clearFoodHistory();
                                products.clear();
                                products=dbhelper.getAllFood();
                                adapter.setData(products);
                                adapter.notifyDataSetChanged();

                            }


                        })
                        .backgroundColorRes(R.color.colorPrimaryDark)
                        .positiveColorRes(R.color.cardview_light_background)
                        .negativeColorRes(R.color.cardview_light_background)
                        .positiveText("Да")
                        .negativeText("Нет")
                        .show();
                break;
            default:
                break;
        }
    }
}
