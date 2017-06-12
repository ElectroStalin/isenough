package vladimir.enough.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import vladimir.enough.Calculations;
import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.EnergyConsAdapter;
import vladimir.enough.databinding.ActivityEnergyConsumptionBinding;
import vladimir.enough.interfaces.OnItemClickListener;
import vladimir.enough.models.KindsOfActivity;
import vladimir.enough.models.PersonalConsumtion;
import vladimir.enough.models.PersonalInfo;

public class EnergyConsumption extends AppCompatActivity implements OnItemClickListener<KindsOfActivity>, View.OnClickListener {
    private int curTime = 0;
    private ActivityEnergyConsumptionBinding binding;
    private EnergyConsAdapter adapter;
    DB dbHelper;
    private ArrayList<KindsOfActivity> acts;
    private ArrayList<PersonalInfo> personalInfo;

    private String sex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_energy_consumption);

        binding.buttonOk.setOnClickListener(this);


        RecyclerView rvCons = (RecyclerView) findViewById(R.id.rvCons);
        rvCons.setHasFixedSize(true);
        rvCons.setLayoutManager(new LinearLayoutManager(this));


        dbHelper = new DB(this);
        acts = dbHelper.getAllActivities();
        personalInfo = dbHelper.getPersonalInfo();
        adapter = new EnergyConsAdapter(acts, this);
        rvCons.setAdapter(adapter);


        setAntropometryValues();
        chooseColor();
        setTimeSum();


    }

    private void chooseColor() {
        if (curTime > 1440) {
            binding.twTimeLeft.setTextColor(Color.parseColor("#DD2C00"));
        } else {
            binding.twTimeLeft.setTextColor(Color.parseColor("#EEEEEE"));
        }
    }

    private void setTimeSum() {
        ArrayList<String> t;
        t = dbHelper.getTime();
        for (int i = 0; i < t.size(); i++) {
            curTime += Integer.parseInt(t.get(i).toString());
        }
        binding.twTimeLeft.setText(String.valueOf(curTime) + "/1440");
    }

    private boolean editBoxesValidations() {
        int age=0;
        int height=0;
        int weight=0;
        if(!(String.valueOf(binding.etAge.getText()).equals(""))){
            age = Integer.parseInt(String.valueOf(binding.etAge.getText()));
        }
        if(!(String.valueOf(binding.etHeight.getText()).equals(""))){
             height = Integer.parseInt(String.valueOf(binding.etHeight.getText()));
        }
        if(!(String.valueOf(binding.etWeight.getText()).equals(""))){
            weight = Integer.parseInt(String.valueOf(binding.etWeight.getText()));
        }
        int flag=0;
        if (!(age > 16 && age < 120)) {
            binding.etAge.setText("0");

            flag++;
        }
        if (!(height > 145 && height < 230)) {
            binding.etHeight.setText("0");

            flag++;
        }
        if (!(weight > 35 && weight < 200)) {
            binding.etWeight.setText("0");

        flag++;
    }
        if (flag==0){
            return true;
        }else {
            makeToast("недопустимые антропометрические значения");
            return false;
        }

    }



    private void setAntropometryValues() {
        binding.etAge.setText(String.valueOf(personalInfo.get(0).getAge()));
        binding.etHeight.setText(String.valueOf(personalInfo.get(0).getHeight()));
        binding.etWeight.setText(String.valueOf(personalInfo.get(0).getWeight()));
        sex = personalInfo.get(0).getSex();



        binding.rbW.setOnClickListener(radioButtonClickListener);

        binding.rbM.setOnClickListener(radioButtonClickListener);

        switch (sex) {
            case "M":
                binding.rbM.setChecked(true);
                break;
            case "W":
                binding.rbW.setChecked(true);
            default:
                binding.rbW.setChecked(false);
                binding.rbW.setChecked(false);
        }
    }


    @Override
    public void onActivityClick(final KindsOfActivity item, final int position) {
        final int oldTime = item.getTime();
        new MaterialDialog.Builder(this)
                .title("Продолжительность")

                .backgroundColorRes(R.color.colorPrimaryDark)
                .positiveColorRes(R.color.cardview_light_background)
                .negativeColorRes(R.color.cardview_light_background)

                .inputType(InputType.TYPE_CLASS_NUMBER)
                .inputRangeRes(1, 3, R.color.colorAccent)
                .input("Введите время", "", new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog dialog, CharSequence input) {
                                int time = 0;
                                time = Integer.parseInt(input.toString());
                                item.setTime(time);
                                adapter.notifyDataSetChanged();
                                dbHelper.addTime(Integer.parseInt(input.toString()), item.getId());

                                if (oldTime == 0) {
                                    curTime += time;
                                } else if (time == 0) {
                                    curTime -= oldTime;
                                } else {
                                    curTime += time - oldTime;
                                }
                                chooseColor();
                                binding.twTimeLeft.setText(String.valueOf(curTime) + "/1440");

                            }
                        }
                )

                .negativeText("Отмена")
                .positiveText("ОК")
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


    private void saveAndCount() {
        int age = Integer.parseInt(binding.etAge.getText().toString());
        int weight = Integer.parseInt(binding.etWeight.getText().toString());
        int height = Integer.parseInt(binding.etHeight.getText().toString());
        dbHelper.setPersonalInfo(age, weight, height, sex, 1);

        dbHelper.insertRowInPersonalEnergy();

        PersonalConsumtion personalConsumtion = dbHelper.getTodayPersonalEnergy();
        Calculations calculations = new Calculations(dbHelper);
        calculations.calculateDailyConsumption(personalConsumtion);
        dbHelper.setTargetPersonalEnergy(personalConsumtion);
    }

    private void makeToast(String a) {
        Toast toast = Toast.makeText(getApplicationContext(),
                a,
                Toast.LENGTH_SHORT);
        toast.show();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok: {
                if (curTime == 1440) {
                    if (editBoxesValidations()) {
                        saveAndCount();
                        Intent intent = new Intent(this, MainActivity.class);
                        startActivity(intent);
                    }
                }else {
                    makeToast("заполните все поля");
                }
            }
        }
    }
}
