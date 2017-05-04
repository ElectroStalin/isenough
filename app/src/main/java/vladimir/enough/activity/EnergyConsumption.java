package vladimir.enough.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.EnergyConsAdapter;
import vladimir.enough.databinding.ActivityEnergyConsumptionBinding;
import vladimir.enough.interfaces.OnItemClickListener;
import vladimir.enough.models.KindsOfActivity;

public class EnergyConsumption extends AppCompatActivity implements OnItemClickListener<KindsOfActivity>, View.OnClickListener {

    private ActivityEnergyConsumptionBinding binding;
    private EnergyConsAdapter adapter;
    DB dbHelper;
    private ArrayList<KindsOfActivity> acts;

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
                        int time = Integer.parseInt(input.toString());
                        item.setTime(time);
                        adapter.notifyDataSetChanged();
                        dbHelper.addTime(time,item.getId());

                    }
                })
                .negativeText("Cancel")
                .positiveText("Save")
                .show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_ok: {
                for (KindsOfActivity activity : acts) {
                    activity.getTime();
                }
            }
        }
    }
}
