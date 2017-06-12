package vladimir.enough.adapters;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import vladimir.enough.DB;
import vladimir.enough.R;
import vladimir.enough.databinding.ActivityItemBinding;
import vladimir.enough.dialogs.EditActivityDialog;
import vladimir.enough.models.KindsOfActivity;

/**
 * Created by 32669 on 23.05.2017.
 */

public class ActivitiesListAdapter extends RecyclerView.Adapter<ActivitiesListAdapter.ViewHolder> {
    private ArrayList<KindsOfActivity> kindsOfActivities;
    private LayoutInflater layoutInflater;
    private DB dbHelper;
    private Context context;
    private ActivitiesListAdapter adapter;
    private Activity act;

    public ActivitiesListAdapter(ArrayList<KindsOfActivity> kindsOfActivities) {
        this.kindsOfActivities = kindsOfActivities;

    }

    public void setData(ArrayList<KindsOfActivity> kindsOfActivities) {
        this.kindsOfActivities = kindsOfActivities;
    }

    public void getdbHelper(DB dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void getContext(Context context) {
        this.context = context;
    }

    public void getAdapter(ActivitiesListAdapter adapter) {
        this.adapter = adapter;
    }

    public void getActivity(Activity act) {
        this.act = act;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ActivityItemBinding activityItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.activity_item, parent, false);
        return new ViewHolder(activityItemBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        KindsOfActivity kindsOfActivity = kindsOfActivities.get(position);
        holder.setItem(kindsOfActivity);
    }

    @Override
    public int getItemCount() {
        return kindsOfActivities.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ActivityItemBinding activityItemBinding;
        private KindsOfActivity kindsOfActivity;

        public ViewHolder(ActivityItemBinding activityItemBinding) {
            super(activityItemBinding.getRoot());
            this.activityItemBinding = activityItemBinding;
            activityItemBinding.ibDelAct.setOnClickListener(this);
            activityItemBinding.ibEditAct.setOnClickListener(this);
            activityItemBinding.getRoot();


        }

        public void setItem(KindsOfActivity kindsOfActivity) {
            this.kindsOfActivity = kindsOfActivity;
            activityItemBinding.tvActName.setText(kindsOfActivity.getActivityName());
            activityItemBinding.tvActVal.setText(String.valueOf(kindsOfActivity.getConsumptionVal()) + " ккал/мин/кг");
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ibDelAct:
                    new MaterialDialog.Builder(context)
                            .title("Удалить элемент?")
                            .positiveText("Да")
                            .negativeText("Нет")
                            .onPositive(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dbHelper.deleteActivity(kindsOfActivity);
                                    kindsOfActivities = dbHelper.getAllActivities();
                                    adapter.setData(kindsOfActivities);
                                    adapter.notifyDataSetChanged();
                                }
                            })
                            .backgroundColorRes(R.color.colorPrimaryDark)
                            .positiveColorRes(R.color.cardview_light_background)
                            .negativeColorRes(R.color.cardview_light_background)
                            .show();
                    break;
                case R.id.ibEditAct:
                    EditActivityDialog dialog = new EditActivityDialog();
                    dialog.getData(kindsOfActivity);
                    dialog.getAdapter(adapter);
                    dialog.show(act.getFragmentManager(), "dialog");
                    break;
            }

        }


    }

}
