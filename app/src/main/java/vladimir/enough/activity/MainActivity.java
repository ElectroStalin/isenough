package vladimir.enough.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vladimir.enough.Calculations;
import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.models.PersonalConsumtion;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DB dbHelper;
    PersonalConsumtion personalConsumtion;

    Button btnConsEnergy,btnConsHistory,btnProducts,btnActs;
    TextView twDayCal, twDayProt, twDayLip, twDayCarb;
    TextView twCurCal, twCurProt, twCurLip, twCurCarb;
    TextView tvBX, tvIM;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvBX=(TextView)findViewById(R.id.tvBX);
        tvIM=(TextView)findViewById(R.id.tvIM);

        twDayCal=(TextView)findViewById(R.id.twDayCal);
        twDayProt=(TextView)findViewById(R.id.twDayProt);
        twDayLip=(TextView)findViewById(R.id.twDayLip);
        twDayCarb=(TextView)findViewById(R.id.twDayCarb);

        twCurCal=(TextView)findViewById(R.id.twCurCal);
        twCurProt=(TextView)findViewById(R.id.twCurProt);
        twCurLip=(TextView)findViewById(R.id.twCurLip);
        twCurCarb=(TextView)findViewById(R.id.twCurCarb);


        btnConsEnergy=(Button)findViewById(R.id.btnConsEnergy);
        btnConsEnergy.setOnClickListener(this);
        btnConsHistory=(Button)findViewById(R.id.btnConsHistory);
        btnConsHistory.setOnClickListener(this);
        btnProducts=(Button)findViewById(R.id.btnProducts);
        btnProducts.setOnClickListener(this);
        btnActs=(Button)findViewById(R.id.btnActs);
        btnActs.setOnClickListener(this);
        dbHelper=new DB(this);

        personalConsumtion=dbHelper.getTodayPersonalEnergy();


        twDayCal.setText(String.valueOf( "Ккал "+personalConsumtion.getDailyCallories()));
        twDayProt.setText(String.valueOf( "белки "+personalConsumtion.getDailyProteins()));
        twDayLip.setText(String.valueOf( "жиры "+personalConsumtion.getDailyLipids()));
        twDayCarb.setText(String.valueOf( "углеводы " +personalConsumtion.getDailyCarbohydrates()));


        twCurCal.setText(String.valueOf( personalConsumtion.getCurrentCallories()));
        twCurProt.setText(String.valueOf( personalConsumtion.getCurrentProteins()));
        twCurLip.setText(String.valueOf(personalConsumtion.getCurrentLipids()));
        twCurCarb.setText(String.valueOf(personalConsumtion.getCurrentCarbohydrates()));

        Calculations calculations=new Calculations(dbHelper);

        tvBX.setText("OO "+String.valueOf(calculations.round( personalConsumtion.getBasicExchenge())));
        tvIM.setText("ИМТ "+String.valueOf(personalConsumtion.getWeightIndex()));
        dbHelper.close();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
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

            default:
                break;
        }
    }
}
