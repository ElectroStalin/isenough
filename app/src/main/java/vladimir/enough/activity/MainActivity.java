package vladimir.enough.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import vladimir.enough.Calculations;
import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.databinding.ActivityMainBinding;
import vladimir.enough.models.PersonalConsumtion;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public DB dbHelper;
    public PersonalConsumtion personalConsumtion;

    private ActivityMainBinding binding;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        Calculations calculations = new Calculations(dbHelper);

        binding.btnConsEnergy.setOnClickListener(this);
        binding.btnConsHistory.setOnClickListener(this);
        binding.btnProducts.setOnClickListener(this);
        binding.btnActs.setOnClickListener(this);
        binding.fabConsumeFood.setOnClickListener(this);
        binding.btnTodayFood.setOnClickListener(this);
        binding.btnFoodHist.setOnClickListener(this);
        binding.btnInf.setOnClickListener(this);

        dbHelper = new DB(this);

        personalConsumtion = dbHelper.getTodayPersonalEnergy();


        binding.twDayCal.setText(String.valueOf(calculations.round(personalConsumtion.getDailyCallories())));
        binding.twDayProt.setText(String.valueOf(calculations.round(personalConsumtion.getDailyProteins())));
        binding.twDayLip.setText(String.valueOf(calculations.round(personalConsumtion.getDailyLipids())));
        binding.twDayCarb.setText(String.valueOf(calculations.round(personalConsumtion.getDailyCarbohydrates())));


        binding.twCurCal.setText(String.valueOf(calculations.round(personalConsumtion.getCurrentCallories())));
        binding.twCurProt.setText(String.valueOf(calculations.round(personalConsumtion.getCurrentProteins())));
        binding.twCurLip.setText(String.valueOf(calculations.round(personalConsumtion.getCurrentLipids())));
        binding.twCurCarb.setText(String.valueOf(calculations.round(personalConsumtion.getCurrentCarbohydrates())));


        binding.tvBX.setText("OO " + String.valueOf(calculations.round(personalConsumtion.getBasicExchenge())));
        binding.tvIM.setText("ИМТ " + String.valueOf(personalConsumtion.getWeightIndex()));
        dbHelper.close();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        binding.twCurCal.setText(String.valueOf(personalConsumtion.getCurrentCallories()));
        binding.twCurProt.setText(String.valueOf(personalConsumtion.getCurrentProteins()));
        binding.twCurLip.setText(String.valueOf(personalConsumtion.getCurrentLipids()));
        binding.twCurCarb.setText(String.valueOf(personalConsumtion.getCurrentCarbohydrates()));

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnConsEnergy:

                intent = new Intent(this, EnergyConsumption.class);
                startActivity(intent);

                break;
            case R.id.btnConsHistory:
                intent = new Intent(this, ConsumtionHistory.class);
                startActivity(intent);
                break;
            case R.id.btnProducts:
                intent = new Intent(this, ProductsList.class);
                startActivity(intent);
                break;
            case R.id.btnActs:
                intent = new Intent(this, ActivitiesList.class);
                startActivity(intent);
                break;
            case R.id.fabConsumeFood:
                intent = new Intent(this, ConsumeFood.class);
                startActivity(intent);
                break;
            case R.id.btnTodayFood:
                intent = new Intent(this, TodayFoodList.class);
                startActivity(intent);
                break;
            case R.id.btnFoodHist:
                intent = new Intent(this, FoodHistory.class);
                startActivity(intent);
                break;
            case R.id.btnInf:
                intent = new Intent(this, Info.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
