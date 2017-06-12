package vladimir.enough.dialogs;

import android.app.DialogFragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import vladimir.enough.Calculations;
import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.activity.MainActivity;
import vladimir.enough.adapters.ConsumpFoodAdapter;
import vladimir.enough.databinding.AddConsFoodFragmentBinding;
import vladimir.enough.models.PersonalConsumtion;
import vladimir.enough.models.Product;

/**
 * Created by 32669 on 06.06.2017.
 */

public class AddConsumeFoodDialog extends DialogFragment implements View.OnClickListener {
    public Product product;
    private AddConsFoodFragmentBinding binding;
    public DB dbHelper;
    private ConsumpFoodAdapter adapter;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_cons_food_fragment, null, false);
        getDialog().setTitle("Добавить прием пищи");
        binding.btnOK.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);
        dbHelper=new DB(getActivity());
        return binding.getRoot();


    }
    public void getAdapter(ConsumpFoodAdapter adapter){
        this.adapter=adapter;
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

    public void getProduct(Product product){
        this.product=product;
    }
    private void insertFood(){
        product.setWeight(Integer.parseInt(binding.etWeight.getText().toString()));
        String h= String.valueOf(binding.timePicker.getCurrentHour());
        String m= String.valueOf(binding.timePicker.getCurrentMinute());
        String time=h+":"+m;
        product.setTime(time);
        Date today = new Date();
        SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
        String date = DATE_FORMAT.format(today);
        product.setDate(date);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOK:
                if (checkEditBoxes()){
                    insertFood();
                    dbHelper.addConsumeFood(product);
                    PersonalConsumtion personalConsumtion;
                    personalConsumtion = dbHelper.getTodayPersonalEnergy();
                    Calculations calculations = new Calculations(dbHelper);
                    calculations.calculateCurrentConsumptionAdd(personalConsumtion, product);

                    dbHelper.setCurrentPersonalEnergy(personalConsumtion);
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    makeToast("добавлено");

                }

                dismiss();
                break;
            case R.id.btnCancel:
                dismiss();
                break;
        }
    }
}
