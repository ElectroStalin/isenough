package vladimir.enough.dialogs;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vladimir.enough.Calculations;
import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.TodayFoodListAdapter;
import vladimir.enough.databinding.AddConsFoodFragmentBinding;
import vladimir.enough.models.PersonalConsumtion;
import vladimir.enough.models.Product;

/**
 * Created by 32669 on 07.06.2017.
 */

public class EditComsumeFoodDialog extends DialogFragment implements View.OnClickListener {
    public Product product;
    private ArrayList<Product> products;
    private AddConsFoodFragmentBinding binding;
    public DB dbHelper;
    private TodayFoodListAdapter adapter;
    private int oldWeight;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_cons_food_fragment, null, false);

        binding.btnOK.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);
        dbHelper = new DB(getActivity());
        oldWeight = product.getWeight();
        binding.etWeight.setText(String.valueOf(product.getWeight()));
        return binding.getRoot();


    }

    public void getAdapter(TodayFoodListAdapter adapter) {
        this.adapter = adapter;
    }

    private boolean checkEditBoxes() {
        if (String.valueOf(binding.etWeight.getText()).equals("")) {
            return false;
        } else return true;
    }

    private void makeToast(String a) {
        Toast toast = Toast.makeText(getActivity(),
                a,
                Toast.LENGTH_SHORT);
        toast.show();
    }

    public void getProduct(Product product) {
        this.product = product;
    }

    private void insertFood() {
        product.setWeight(Integer.parseInt(binding.etWeight.getText().toString()));
        String h = String.valueOf(binding.timePicker.getCurrentHour());
        String m = String.valueOf(binding.timePicker.getCurrentMinute());
        String time = h + ":" + m;
        product.setTime(time);
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);
        product.setDate(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOK:
                if (checkEditBoxes()) {
                    insertFood();

                    dbHelper.editTodayFood(product);
                    int newWeight=product.getWeight();
                    product=dbHelper.getProduct(product.getProdID());
                    product.setWeight(oldWeight);
                    PersonalConsumtion personalConsumtion;
                    personalConsumtion = dbHelper.getTodayPersonalEnergy();
                    Calculations calculations = new Calculations(dbHelper);
                    calculations.calculateCurrentConsumptionDel(personalConsumtion, product);
                    dbHelper.setCurrentPersonalEnergy(personalConsumtion);
                    product = dbHelper.getProduct(product.getProdID());
                    product.setWeight(newWeight);
                    personalConsumtion = dbHelper.getTodayPersonalEnergy();
                    calculations.calculateCurrentConsumptionAdd(personalConsumtion, product);
                    dbHelper.setCurrentPersonalEnergy(personalConsumtion);


                    products = dbHelper.getTodayFood();
                    adapter.setData(products);
                    adapter.notifyDataSetChanged();

                    makeToast("изменения сохранены");

                }

                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
