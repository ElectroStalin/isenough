package vladimir.enough.dialogs;

import android.app.DialogFragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.ProductListAdapter;
import vladimir.enough.databinding.AddProductFragmentBinding;
import vladimir.enough.models.Product;

/**
 * Created by 32669 on 20.05.2017.
 */

public class AddProductDialog extends DialogFragment implements View.OnClickListener {
    private Product product;
    private ArrayList<Product> products;
    private DB dbHelper;
    private AddProductFragmentBinding binding;
    private ProductListAdapter adapter;
    Toast toast;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_product_fragment, null, false);
        getDialog().setTitle("Добавить продукт");
        binding.btnOk.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);
        binding.getRoot().findViewById(R.id.btnOk);
        dbHelper = new DB(getActivity());
        product=new Product();

        return binding.getRoot();

    }
public void getAdapter(ProductListAdapter adapter){
    this.adapter=adapter;
}

    private boolean checkEditBoxes() {
        if (binding.etAddName.getText().equals("")) {
            makeToast("добавте название");
            return false;
        } else if (String.valueOf(binding.etAddCal.getText()).equals("")) {
            return false;
        } else if (String.valueOf(binding.etAddProt.getText()).equals("")) {
            return false;
        } else if (String.valueOf(binding.etAddLip.getText()).equals("")) {
            return false;
        } else if (String.valueOf(binding.etAddCar.getText()).equals("")) {
            return false;
        } else if (String.valueOf(binding.etAddType.getText()).equals("")) {
            return false;
        } else return true;
    }

    private void makeToast(String a) {
        Toast toast = Toast.makeText(getActivity(),
                a,
                Toast.LENGTH_SHORT);
        toast.show();
    }
    private void insertProduct() {
        product.setName(String.valueOf((binding.etAddName.getText())));
        product.setCallories(Double.parseDouble(binding.etAddCal.getText().toString()));
        product.setProteins(Double.parseDouble(binding.etAddProt.getText().toString()));
        product.setLipids(Double.parseDouble(binding.etAddLip.getText().toString()));
        product.setCarbohydrates(Double.parseDouble(binding.etAddCar.getText().toString()));
        product.setType(String.valueOf((binding.etAddType.getText())));

    }


    public static AddProductDialog newInstance() {
        AddProductDialog fragment = new AddProductDialog();
        return fragment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOk:
                if (checkEditBoxes()) {
                    insertProduct();
                    dbHelper.insertProduct(product);
                    products = dbHelper.getAllProducts();
                    adapter.setData(products);
                    adapter.notifyDataSetChanged();
//                    toast = Toast.makeText(getActivity(),"добавлено", Toast.LENGTH_SHORT);
//                toast.show();
                    makeToast("добавлено");
                    dismiss();

                }
            case R.id.btnCancel:
                dismiss();

        }
    }
}