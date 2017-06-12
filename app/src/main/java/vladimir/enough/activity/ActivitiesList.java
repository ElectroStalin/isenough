package vladimir.enough.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.ActivitiesListAdapter;
import vladimir.enough.databinding.ActivityActivitiesListBinding;
import vladimir.enough.dialogs.AddActivityDialog;
import vladimir.enough.models.KindsOfActivity;

public class ActivitiesList extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<KindsOfActivity> kindsOfActivities;
    private ActivityActivitiesListBinding binding;
    private DB dbHelper;
    private ActivitiesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_activities_list);

        RecyclerView rvActList = (RecyclerView) findViewById(R.id.rvActList);
        rvActList.setHasFixedSize(true);
        rvActList.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DB(this);
        kindsOfActivities = dbHelper.getAllActivities();
        adapter = new ActivitiesListAdapter(kindsOfActivities);
        adapter.getContext(this);
        adapter.getdbHelper(dbHelper);
        adapter.getAdapter(adapter);
        adapter.getActivity(this);
        rvActList.setAdapter(adapter);

        binding.fabAddActiv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fabAddActiv:
                AddActivityDialog dialog = new AddActivityDialog();
                dialog.getAdapter(adapter);
                dialog.show(getFragmentManager(), "dialog");
                break;
        }
    }
}
