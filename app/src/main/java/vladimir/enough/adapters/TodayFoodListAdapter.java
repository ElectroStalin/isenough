package vladimir.enough.adapters;

import android.app.Activity;
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

import vladimir.enough.Calculations;
import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.databinding.TodayFoodItemBinding;
import vladimir.enough.dialogs.EditComsumeFoodDialog;
import vladimir.enough.models.PersonalConsumtion;
import vladimir.enough.models.Product;

/**
 * Created by 32669 on 07.06.2017.
 */

public class TodayFoodListAdapter extends RecyclerView.Adapter<TodayFoodListAdapter.ViewHolder> {
    private LayoutInflater layoutInflater;
    private DB dbHelper;
    private Context context;
    private TodayFoodListAdapter adapter;
    private Product product;
    private ArrayList<Product> products;
    private Activity act;
    private ConsumpFoodAdapter consumpFoodAdapter;

    public TodayFoodListAdapter(ArrayList<Product> products) {
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

    public void getAdapter(TodayFoodListAdapter adapter) {
        this.adapter = adapter;
    }
    public void getConsumpFoodAdapterAdapter(ConsumpFoodAdapter adapter) {
        this.consumpFoodAdapter = adapter;
    }



    public void getActivity(Activity act) {
        this.act = act;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        TodayFoodItemBinding todayFoodItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.today_food_item, parent, false);
        return new ViewHolder(todayFoodItemBinding);
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
        private TodayFoodItemBinding binding;
        private Product product;

        public ViewHolder(TodayFoodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.ibEdit.setOnClickListener(this);
            binding.ibDel.setOnClickListener(this);
            binding.getRoot();
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

        public void setItem(Product product) {
            this.product = product;
            binding.tvName.setText(product.getName());
            binding.tvTime.setText("Время " + formatTime(product.getTime()));
            binding.tvWeight.setText(String.valueOf(product.getWeight()) + " г.");
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ibDel:
                    new MaterialDialog.Builder(context)
                            .title("Удалить элемент?")
                            .positiveText("Да")
                            .negativeText("Нет")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                                    Product prodInfo = dbHelper.getProduct(product.getProdID());
                                    Calculations calculations = new Calculations(dbHelper);
                                    prodInfo.setWeight(product.getWeight());
                                    PersonalConsumtion personalConsumtion = dbHelper.getTodayPersonalEnergy();
                                    calculations.calculateCurrentConsumptionDel(personalConsumtion, prodInfo);
                                    dbHelper.setCurrentPersonalEnergy(personalConsumtion);
                                    dbHelper.deleteTodayFood(product);
                                    products = dbHelper.getTodayFood();
                                    adapter.setData(products);
                                    adapter.notifyDataSetChanged();


                                }
                            })
                            .backgroundColorRes(R.color.colorPrimaryDark)
                            .positiveColorRes(R.color.cardview_light_background)
                            .negativeColorRes(R.color.cardview_light_background)
                            .show();
                    break;

                case R.id.ibEdit:
                    EditComsumeFoodDialog dialog=new EditComsumeFoodDialog();
                    dialog.getAdapter(adapter);
                    dialog.getProduct(product);
                    dialog.show(act.getFragmentManager(), "dialog");

                    break;
            }
        }
    }
}
