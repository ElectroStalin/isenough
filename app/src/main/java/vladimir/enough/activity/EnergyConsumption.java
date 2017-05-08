package vladimir.enough.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import vladimir.enough.Calculations;
import vladimir.enough.CheckInputedValues;
import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.EnergyConsAdapter;
import vladimir.enough.databinding.ActivityEnergyConsumptionBinding;
import vladimir.enough.interfaces.OnItemClickListener;
import vladimir.enough.models.KindsOfActivity;
import vladimir.enough.models.PersonalConsumtion;
import vladimir.enough.models.PersonalInfo;

public class EnergyConsumption extends AppCompatActivity implements OnItemClickListener<KindsOfActivity>, View.OnClickListener {

    private ActivityEnergyConsumptionBinding binding;
    private EnergyConsAdapter adapter;
    DB dbHelper;
    private ArrayList<KindsOfActivity> acts;
    private ArrayList<PersonalInfo> personalInfo;
    EditText etAge, etHeight, etWeight;
    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_energy_consumption);

        binding.buttonOk.setOnClickListener(this);

        etAge = (EditText) findViewById(R.id.etAge);
        etHeight = (EditText) findViewById(R.id.etHeight);
        etWeight = (EditText) findViewById(R.id.etWeight);

        RadioButton rbW = (RadioButton) findViewById(R.id.rbW);
        rbW.setOnClickListener(radioButtonClickListener);
        RadioButton rbM = (RadioButton) findViewById(R.id.rbM);
        rbM.setOnClickListener(radioButtonClickListener);


        RecyclerView rvCons = (RecyclerView) findViewById(R.id.rvCons);
        rvCons.setHasFixedSize(true);
        rvCons.setLayoutManager(new LinearLayoutManager(this));


        dbHelper = new DB(this);
        acts = dbHelper.getAllActivities();

        personalInfo = dbHelper.getPersonalInfo();

        etAge.setText(String.valueOf(personalInfo.get(0).getAge()));
        etHeight.setText(String.valueOf(personalInfo.get(0).getHeight()));
        etWeight.setText(String.valueOf(personalInfo.get(0).getWeight()));
        sex = personalInfo.get(0).getSex();
        switch (sex) {
            case "M":
                rbM.setChecked(true);
                break;
            case "W":
                rbW.setChecked(true);
            default:
                rbW.setChecked(false);
                rbW.setChecked(false);
        }

        adapter = new EnergyConsAdapter(acts, this);
        rvCons.setAdapter(adapter);


    }

    @Override
    public void onActivityClick(final KindsOfActivity item, int position) {
        new MaterialDialog.Builder(this)
                .title("Продолжительность")
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input("Введите время", "", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        int time = 0;
                        if (!input.toString().equals("")) {
                            time = Integer.parseInt(input.toString());
                        }
                        item.setTime(time);
                        adapter.notifyDataSetChanged();
                        dbHelper.addTime(time, item.getId());


                    }
                })
                .negativeText("Cancel")
                .positiveText("Save")
                .show();

    }

    View.OnClickListener radioButtonClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rbM:
                    sex = "M";
                    break;
                case R.id.rbW:
                    sex = "W";
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok: {

                CheckInputedValues check = new CheckInputedValues(dbHelper, acts);
                if ((check.CheckTime())) {
                    for (KindsOfActivity activity : acts) {
                        activity.getTime();

                    }
                    int age = Integer.parseInt(etAge.getText().toString());
                    int weight = Integer.parseInt(etWeight.getText().toString());
                    int height = Integer.parseInt(etHeight.getText().toString());
                    dbHelper.setPersonalInfo(age, weight, height, sex, 1);

                    dbHelper.insertRowInPersonalEnergy();

                    PersonalConsumtion personalConsumtion=dbHelper.getTodayPersonalEnergy();
                    Calculations calculations=new Calculations(dbHelper);
                    calculations.calculateDailyConsumption(personalConsumtion);
                    dbHelper.setTodayPersonalEnergy(personalConsumtion);


                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                } else {

                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Всего должно быть 1440 минут ",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

        }
    }
}
