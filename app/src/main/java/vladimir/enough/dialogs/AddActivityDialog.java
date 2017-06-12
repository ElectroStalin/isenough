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
import vladimir.enough.adapters.ActivitiesListAdapter;
import vladimir.enough.databinding.AddActivityFragmentBinding;
import vladimir.enough.models.KindsOfActivity;

/**
 * Created by 32669 on 29.05.2017.
 */

public class AddActivityDialog extends DialogFragment implements View.OnClickListener {

    private AddActivityFragmentBinding binding;
    private DB dbHelper;
    private KindsOfActivity activity;
    private ActivitiesListAdapter adapter;
    private ArrayList<KindsOfActivity> activities;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.add_activity_fragment, null, false);
        getDialog().setTitle("Добавить активность");
        
        binding.btnOK.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);
        activity=new KindsOfActivity();
        dbHelper=new DB(getActivity());

        return binding.getRoot();

    }
    private void insertActivity(){
        activity.setActivityName(String.valueOf(binding.etActName.getText()));
        activity.setConsumptionVal(Double.parseDouble(binding.etVal.getText().toString()));
        activity.setType(String.valueOf((binding.etType.getText())));
        activity.setTime(0);

    }

    public void getAdapter(ActivitiesListAdapter adapter){
        this.adapter=adapter;
    }

    private boolean checkEditBoxes() {
        if (binding.etActName.getText().equals("")) {
            return false;
        } else if (String.valueOf(binding.etVal.getText()).equals("")) {
            return false;
        } else if (String.valueOf(binding.etType.getText()).equals("")) {
            return false;
        } else return true;
    }
    private void makeToast(String a) {
        Toast toast = Toast.makeText(getActivity(),
                a,
                Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOK:
                if (checkEditBoxes()){
                    insertActivity();
                    dbHelper.insertActivity(activity);
                    activities=dbHelper.getAllActivities();
                    adapter.setData(activities);
                    makeToast("добавлено");
                    dismiss();

                }
                break;
            case R.id.btnCancel:
                dismiss();
            break;
        }
    }
}
