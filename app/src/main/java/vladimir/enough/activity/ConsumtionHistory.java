package vladimir.enough.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.HistoryAdapter;
import vladimir.enough.databinding.ActivityConsumtionHistoryBinding;
import vladimir.enough.models.PersonalConsumtion;

public class ConsumtionHistory extends AppCompatActivity implements View.OnClickListener {

    AlertDialog.Builder ad;
    private HistoryAdapter adapter;
    DB dbhelper;
    private ArrayList<PersonalConsumtion> personalConsumtions;
    private ActivityConsumtionHistoryBinding binding;
    Button btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_consumtion_history);


        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);

        RecyclerView rvHistory = (RecyclerView) findViewById(R.id.rvHistory);
        rvHistory.setHasFixedSize(true);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));
        dbhelper = new DB(this);
        personalConsumtions = dbhelper.getAllPersonalEnergy();

        adapter = new HistoryAdapter(personalConsumtions);
        rvHistory.setAdapter(adapter);


    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnClear:
                new MaterialDialog.Builder(this)
                        .title("Подтверждение")
                        .content("Очистить историю?")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dbhelper.clearPersonalEnergy();
                                personalConsumtions.clear();

                                personalConsumtions = dbhelper.getAllPersonalEnergy();
                                adapter.setData(personalConsumtions);

                                adapter.notifyDataSetChanged();

                            }


                        })
                        .positiveText("Да")
                        .negativeText("Нет")
                        .show();
                default:
                    break;
        }
    }
}