package vladimir.enough.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.models.PersonalConsumtion;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    DB dbHelper;


    Button btnConsEnergy,button2;
    TextView twDayCal, twDayProt, twDayLip, twDayCarb;
    TextView twCurCal, twCurProt, twCurLip, twCurCarb;
    PersonalConsumtion personalConsumtion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);


        dbHelper=new DB(this);

        personalConsumtion=dbHelper.getTodayPersonalEnergy();
        twDayCal.setText(String.valueOf( "каллории "+personalConsumtion.getDailyCallories()));
        twDayProt.setText(String.valueOf( "белки "+personalConsumtion.getDailyProteins()));
        twDayLip.setText(String.valueOf( "жиры "+personalConsumtion.getDailyLipids()));
        twDayCarb.setText(String.valueOf( "углеводы " +personalConsumtion.getDailyCarbonides()));


        twCurCal.setText(String.valueOf( personalConsumtion.getCurrentCallories()));
        twCurProt.setText(String.valueOf( personalConsumtion.getCurrentProteins()));
        twCurLip.setText(String.valueOf(personalConsumtion.getCurrentLipids()));
        twCurCarb.setText(String.valueOf(personalConsumtion.getCurrentCarbonides()));
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
            case R.id.button2:
                intent = new Intent(this, ConsumtionHistory.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
