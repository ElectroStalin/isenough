package vladimir.enough;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class PersonalInfo extends AppCompatActivity implements View.OnClickListener {
    EditText etAge,etHeight,etWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        RadioButton rbM=(RadioButton)findViewById(R.id.rbM);
        rbM.setOnClickListener(this);

        RadioButton rbW=(RadioButton)findViewById(R.id.rbW);
        rbM.setOnClickListener(this);

        etAge=(EditText)findViewById(R.id.etAge);
        etHeight=(EditText)findViewById(R.id.etHeight);
        etWeight=(EditText)findViewById(R.id.etWeight);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rbM:

                break;
            case R.id.rbW:

                break;
        }
    }
}
