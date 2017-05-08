package vladimir.enough.adapters;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vladimir.enough.R;
import vladimir.enough.databinding.HistoryItemBinding;
import vladimir.enough.interfaces.OnItemClickListener;
import vladimir.enough.models.PersonalConsumtion;

/**
 * Created by 32669 on 08.05.2017.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private ArrayList<PersonalConsumtion> personalConsumtions;
    private LayoutInflater layoutInflater;

    public HistoryAdapter(ArrayList<PersonalConsumtion> personalConsumtions
                          ) {
        this.personalConsumtions = personalConsumtions;
    }
    public void setData(ArrayList<PersonalConsumtion> personalConsumtions){
        this.personalConsumtions=personalConsumtions;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        HistoryItemBinding historyItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.history_item, parent, false);
        return new ViewHolder(historyItemBinding
                );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         PersonalConsumtion personalConsumtion=personalConsumtions.get(position);
        holder.setItem(personalConsumtion);
    }

    @Override
    public int getItemCount() {
    return personalConsumtions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private HistoryItemBinding historyItemBinding;
        private OnItemClickListener<PersonalConsumtion> personalConsumtionOnItemClickListener;
        private PersonalConsumtion personalConsumtion;

        public ViewHolder( HistoryItemBinding historyItemBinding
                           ) {
            super(historyItemBinding.getRoot());
            this.historyItemBinding = historyItemBinding;
            historyItemBinding.getRoot().setOnClickListener(this);
        }


        public void setItem(PersonalConsumtion personalConsumtion){
            this.personalConsumtion=personalConsumtion;
            historyItemBinding.twDate.setText(personalConsumtion.getDate());
            historyItemBinding.twCal.setText(String.valueOf(personalConsumtion.getDailyCallories()));
            historyItemBinding.twProt.setText(String.valueOf(personalConsumtion.getDailyProteins()));
            historyItemBinding.twLip.setText(String.valueOf(personalConsumtion.getDailyLipids()));
            historyItemBinding.twCarb.setText(String.valueOf(personalConsumtion.getDailyCarbonides()));
            historyItemBinding.twFactCal.setText(String.valueOf(personalConsumtion.getCurrentCallories()));
            historyItemBinding.twFactProt.setText(String.valueOf(personalConsumtion.getCurrentProteins()));
            historyItemBinding.twFactLip.setText(String.valueOf(personalConsumtion.getCurrentLipids()));
            historyItemBinding.twFactCarb.setText(String.valueOf(personalConsumtion.getCurrentCarbonides()));

        }


        @Override
        public void onClick(View v) {

        }
    }
}
