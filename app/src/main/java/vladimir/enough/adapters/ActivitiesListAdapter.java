package vladimir.enough.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import vladimir.enough.R;
import vladimir.enough.databinding.ActivityItemBinding;
import vladimir.enough.models.KindsOfActivity;

/**
 * Created by 32669 on 23.05.2017.
 */

public class ActivitiesListAdapter extends RecyclerView.Adapter<ActivitiesListAdapter.ViewHolder> {
    private ArrayList<KindsOfActivity>kindsOfActivities;
    private LayoutInflater layoutInflater;

    public ActivitiesListAdapter(ArrayList<KindsOfActivity> kindsOfActivities) {
        this.kindsOfActivities = kindsOfActivities;

    }
    public void setData(ArrayList<KindsOfActivity> kindsOfActivities){
        this.kindsOfActivities=kindsOfActivities;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ActivityItemBinding activityItemBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.activity_item, parent, false);
        return new ViewHolder(activityItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        KindsOfActivity kindsOfActivity=kindsOfActivities.get(position);
        holder.setItem(kindsOfActivity);
    }

    @Override
    public int getItemCount() {
        return kindsOfActivities.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private ActivityItemBinding activityItemBinding;
        private KindsOfActivity kindsOfActivity;

     public ViewHolder(ActivityItemBinding activityItemBinding) {
         super(activityItemBinding.getRoot());
         this.activityItemBinding = activityItemBinding;
         this.kindsOfActivity = kindsOfActivity;
         activityItemBinding.getRoot();
     }

     public void setItem(KindsOfActivity kindsOfActivity){
         this.kindsOfActivity=kindsOfActivity;
         activityItemBinding.tvActName.setText(kindsOfActivity.getActivityName());
         activityItemBinding.tvActVal.setText(String.valueOf( kindsOfActivity.getConsumptionVal())+" ккал/мин/кг");
     }
 }

}
