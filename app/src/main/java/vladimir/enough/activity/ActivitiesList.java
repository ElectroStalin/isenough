package vladimir.enough.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.adapters.ActivitiesListAdapter;
import vladimir.enough.databinding.ActivityActivitiesListBinding;
import vladimir.enough.models.KindsOfActivity;

public class ActivitiesList extends AppCompatActivity {
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
        kindsOfActivities=dbHelper.getAllActivities();
        adapter=new ActivitiesListAdapter(kindsOfActivities);
        rvActList.setAdapter(adapter);
    }
}
