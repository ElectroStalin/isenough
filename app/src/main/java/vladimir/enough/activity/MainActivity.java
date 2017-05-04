package vladimir.enough.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import vladimir.enough.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnConsEnergy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnConsEnergy=(Button)findViewById(R.id.btnConsEnergy);
        btnConsEnergy.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnConsEnergy:
                Intent intent = new Intent(this, EnergyConsumption.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
