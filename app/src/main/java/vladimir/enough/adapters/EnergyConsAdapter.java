package vladimir.enough.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vladimir.enough.R;
import vladimir.enough.databinding.ListItemBinding;
import vladimir.enough.interfaces.OnItemClickListener;
import vladimir.enough.models.KindsOfActivity;

/**
 * Created by 32669 on 03.05.2017.
 */

public class EnergyConsAdapter extends RecyclerView.Adapter<EnergyConsAdapter.ViewHolder> {


    private ArrayList<KindsOfActivity> kof;
    private OnItemClickListener<KindsOfActivity> kindsOfActivitiesOnItemClickListener;

    private LayoutInflater layoutInflater;

    public EnergyConsAdapter(ArrayList<KindsOfActivity> kof, OnItemClickListener<KindsOfActivity> onItemClickListener) {
        this.kof = kof;
        kindsOfActivitiesOnItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ListItemBinding listItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_item, parent, false);
        return new ViewHolder(listItemBinding, kindsOfActivitiesOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        KindsOfActivity kindsOfActivity = kof.get(position);


        holder.setItem(kindsOfActivity);

    }

    @Override
    public int getItemCount() {
        return kof.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ListItemBinding listItemBinding;
        private OnItemClickListener<KindsOfActivity> kindsOfActivitiesOnItemClickListener;

        private KindsOfActivity kindsOfActivity;


        ViewHolder(ListItemBinding listItemBinding, OnItemClickListener<KindsOfActivity> kindsOfActivitiesOnItemClickListener) {
            super(listItemBinding.getRoot());

            this.listItemBinding = listItemBinding;
            this.kindsOfActivitiesOnItemClickListener = kindsOfActivitiesOnItemClickListener;

            listItemBinding.getRoot().setOnClickListener(this);
        }


        public void setItem(KindsOfActivity kindsOfActivity) {
            this.kindsOfActivity = kindsOfActivity;

            double x = kindsOfActivity.getConsumptionVal();

            listItemBinding.textViewHead.setText(kindsOfActivity.getActivityName());
            listItemBinding.textViewDescription.setText(String.valueOf(x));

            if (kindsOfActivity.getTime() != 0) {
                listItemBinding.time.setText(String.valueOf(kindsOfActivity.getTime()));
            }
        }

        @Override
        public void onClick(View v) {
            kindsOfActivitiesOnItemClickListener.onActivityClick(kindsOfActivity, getAdapterPosition());
        }
    }
}
